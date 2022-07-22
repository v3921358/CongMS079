package handling.channel.handler;

import java.util.Objects;
import java.util.LinkedList;
import handling.world.guild.MapleGuildResponse;
import java.util.Iterator;
import handling.channel.ChannelServer;
import handling.world.World.Find;
import handling.world.World.Alliance;
import handling.world.guild.MapleGuild;
import handling.world.World.Guild;
import tools.data.LittleEndianAccessor;
import tools.FileoutputUtil;
import client.MapleCharacter;
import tools.MaplePacketCreator;
import client.MapleClient;
import java.util.List;

public class GuildHandler
{
    private static final List<Invited> invited;
    private static long nextPruneTime;
    
    public static final void denyGuildRequest(final String from, final MapleClient c) {
        final MapleCharacter cfrom = c.getChannelServer().getPlayerStorage().getCharacterByName(from);
        if (cfrom != null) {
            cfrom.getClient().sendPacket(MaplePacketCreator.denyGuildInvitation(c.getPlayer().getName()));
        }
    }
    
    private static boolean isGuildNameAcceptable(final String name) {
        return name.length() <= 15 && name.length() >= 3;
    }
    
    private static void respawnPlayer(final MapleCharacter mc) {
        mc.getMap().broadcastMessage(mc, MaplePacketCreator.removePlayerFromMap(mc.getId()), false);
        mc.getMap().broadcastMessage(mc, MaplePacketCreator.spawnPlayerMapobject(mc), false);
    }
    
    private static void SaveCharDb(final MapleCharacter c) {
        try {
            c.saveToDB(false, false);
        }
        catch (Exception e) {
            System.out.println("公会的錯誤:" + (Object)e);
            FileoutputUtil.logToFile("logs/公会存檔保存数据異常.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getClient().getSession().remoteAddress().toString().split(":")[0] + " 账号 " + c.getClient().getAccountName() + " 账号ID " + c.getClient().getAccID() + " 角色名 " + c.getName() + " 角色ID " + c.getId());
            FileoutputUtil.outError("logs/公会存檔保存数据異常.txt", (Throwable)e);
        }
    }
    
    public static final void HandleGuild(final LittleEndianAccessor slea, final MapleClient c) {
        if (System.currentTimeMillis() >= GuildHandler.nextPruneTime) {
            final Iterator<Invited> itr = GuildHandler.invited.iterator();
            while (itr.hasNext()) {
                final Invited inv = (Invited)itr.next();
                if (System.currentTimeMillis() >= inv.expiration) {
                    itr.remove();
                }
            }
            GuildHandler.nextPruneTime = System.currentTimeMillis() + 1200000L;
        }
        final GuildOperation operation = GuildOperation.getByValue(slea.readByte());
        if (operation == null) {
            return;
        }
        switch (operation) {
            case CREATE: {
                final int cost = 10000000;
                if (c.getPlayer().getGuildId() > 0 || c.getPlayer().getMapId() != 200000301) {
                    c.getPlayer().dropMessage(1, "无法建立公会\r\n已经有公会或不在英雄之殿");
                    return;
                }
                if (c.getPlayer().getMeso() < cost) {
                    c.getPlayer().dropMessage(1, "你沒有足夠的金币建立公会。目前建立公会需要: " + cost + " 的金币。");
                    return;
                }
                final String guildName = slea.readMapleAsciiString();
                if (!isGuildNameAcceptable(guildName)) {
                    c.getPlayer().dropMessage(1, "这个公会名称是不被准许的.");
                    return;
                }
                final int guildId = Guild.createGuild(c.getPlayer().getId(), guildName);
                if (guildId == 0) {
                    c.sendPacket(MaplePacketCreator.genericGuildMessage((byte)28));
                    return;
                }
                c.getPlayer().gainMeso(-cost, true, false, true);
                c.getPlayer().setGuildId(guildId);
                c.getPlayer().setGuildRank((byte)1);
                c.getPlayer().saveGuildStatus();
                Guild.setGuildMemberOnline(c.getPlayer().getMGC(), true, c.getChannel());
                c.sendPacket(MaplePacketCreator.showGuildInfo(c.getPlayer()));
                Guild.gainGP(c.getPlayer().getGuildId(), 500);
                Guild.setGuildMemberOnline(c.getPlayer().getMGC(), true, c.getChannel());
                c.getPlayer().dropMessage(1, "恭喜你成功创建一個公会.");
                respawnPlayer(c.getPlayer());
                break;
            }
            case INVITE: {
                if (c.getPlayer().getGuildId() <= 0 || c.getPlayer().getGuildRank() > 2) {
                    return;
                }
                final String playerName = slea.readMapleAsciiString();
                final MapleGuildResponse mgr = MapleGuild.sendInvite(c, playerName);
                if (mgr != null) {
                    c.sendPacket(mgr.getPacket());
                    break;
                }
                final Invited inv2 = new Invited(playerName, c.getPlayer().getGuildId());
                if (!GuildHandler.invited.contains((Object)inv2)) {
                    GuildHandler.invited.add(inv2);
                }
                break;
            }
            case ACCEPTED: {
                if (c.getPlayer().getGuildId() > 0) {
                    return;
                }
                final int guildId2 = slea.readInt();
                final int cid = slea.readInt();
                if (cid != c.getPlayer().getId()) {
                    return;
                }
                final String playerName2 = c.getPlayer().getName().toLowerCase();
                final Iterator<Invited> itr2 = GuildHandler.invited.iterator();
                while (itr2.hasNext()) {
                    final Invited inv3 = (Invited)itr2.next();
                    if (guildId2 == inv3.gid && playerName2.equals((Object)inv3.name)) {
                        c.getPlayer().setGuildId(guildId2);
                        c.getPlayer().setGuildRank((byte)5);
                        itr2.remove();
                        final int s = Guild.addGuildMember(c.getPlayer().getMGC());
                        if (s == 0) {
                            c.getPlayer().dropMessage(1, "你想要加入的公会已經滿了.");
                            c.getPlayer().setGuildId(0);
                            return;
                        }
                        c.sendPacket(MaplePacketCreator.showGuildInfo(c.getPlayer()));
                        final MapleGuild gs = Guild.getGuild(guildId2);
                        for (final byte[] pack : Alliance.getAllianceInfo(gs.getAllianceId(), true)) {
                            if (pack != null) {
                                c.sendPacket(pack);
                            }
                        }
                        c.getPlayer().saveGuildStatus();
                        respawnPlayer(c.getPlayer());
                        break;
                    }
                }
                break;
            }
            case LEAVING: {
                final int cid2 = slea.readInt();
                final String name = slea.readMapleAsciiString();
                if (cid2 != c.getPlayer().getId() || !name.equals((Object)c.getPlayer().getName()) || c.getPlayer().getGuildId() <= 0) {
                    return;
                }
                if (c.getPlayer().getMapId() == 990001000) {
                    c.getPlayer().dropMessage(5, "無法在當前地图退出工会。");
                    return;
                }
                Guild.leaveGuild(c.getPlayer().getMGC());
                c.sendPacket(MaplePacketCreator.showGuildInfo(null));
                break;
            }
            case EXPEL: {
                final int cid2 = slea.readInt();
                final String name = slea.readMapleAsciiString();
                MapleCharacter victim = null;
                final int ch = Find.findChannel(name);
                if (ch >= 1) {
                    victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
                    if (victim != null && victim.getMapId() == 990001000) {
                        c.getPlayer().dropMessage(5, "當前無法驅除" + victim.getName() + "工会。");
                        return;
                    }
                }
                if (c.getPlayer().getGuildRank() > 2 || c.getPlayer().getGuildId() <= 0) {
                    return;
                }
                Guild.expelMember(c.getPlayer().getMGC(), name, cid2);
                break;
            }
            case CHANGE_RANK_TITLE: {
                if (c.getPlayer().getGuildId() <= 0 || c.getPlayer().getGuildRank() != 1) {
                    return;
                }
                final String[] ranks = new String[5];
                for (int i = 0; i < 5; ++i) {
                    ranks[i] = slea.readMapleAsciiString();
                }
                Guild.changeRankTitle(c.getPlayer().getGuildId(), ranks);
                break;
            }
            case CHANGE_RANK: {
                final int cid2 = slea.readInt();
                final byte newRank = slea.readByte();
                if (newRank <= 1 || newRank > 5 || c.getPlayer().getGuildRank() > 2 || (newRank <= 2 && c.getPlayer().getGuildRank() != 1) || c.getPlayer().getGuildId() <= 0) {
                    return;
                }
                Guild.changeRank(c.getPlayer().getGuildId(), cid2, (int)newRank);
                break;
            }
            case CHANGE_EMBLEM: {
                if (c.getPlayer().getGuildId() <= 0 || c.getPlayer().getGuildRank() != 1 || c.getPlayer().getMapId() != 200000301) {
                    return;
                }
                if (c.getPlayer().getMeso() < 1000000) {
                    c.getPlayer().dropMessage(1, "你的金币不夠,無法創建公会徽章");
                    return;
                }
                final short bg = slea.readShort();
                final byte bgcolor = slea.readByte();
                final short logo = slea.readShort();
                final byte logocolor = slea.readByte();
                Guild.setGuildEmblem(c.getPlayer().getGuildId(), bg, bgcolor, logo, logocolor);
                c.getPlayer().gainMeso(-1000000, true, false, true);
                respawnPlayer(c.getPlayer());
                break;
            }
            case CHANGE_NOTICE: {
                final String notice = slea.readMapleAsciiString();
                if (notice.length() > 100 || c.getPlayer().getGuildId() <= 0 || c.getPlayer().getGuildRank() > 2) {
                    return;
                }
                Guild.setGuildNotice(c.getPlayer().getGuildId(), notice);
                break;
            }
        }
    }
    
    static {
        invited = new LinkedList<Invited>();
        GuildHandler.nextPruneTime = System.currentTimeMillis() + 1200000L;
    }
    
    private enum GuildOperation
    {
        CREATE((byte)2), 
        INVITE((byte)5), 
        ACCEPTED((byte)6), 
        LEAVING((byte)7), 
        EXPEL((byte)8), 
        CHANGE_RANK_TITLE((byte)13), 
        CHANGE_RANK((byte)14), 
        CHANGE_EMBLEM((byte)15), 
        CHANGE_NOTICE((byte)16);
        
        byte value;
        
        private GuildOperation(final byte op) {
            this.value = op;
        }
        
        public static final GuildOperation getByValue(final byte value) {
            for (final GuildOperation op : values()) {
                if (op.value == value) {
                    return op;
                }
            }
            return null;
        }
    }
    
    private static final class Invited
    {
        public String name;
        public int gid;
        public long expiration;
        
        public Invited(final String n, final int id) {
            this.name = n.toLowerCase();
            this.gid = id;
            this.expiration = System.currentTimeMillis() + 3600000L;
        }
        
        @Override
        public final boolean equals(final Object other) {
            if (!(other instanceof Invited)) {
                return false;
            }
            final Invited oth = (Invited)other;
            return this.gid == oth.gid && this.name.equals((Object)oth.name);
        }
        
        @Override
        public int hashCode() {
            int hash = 3;
            hash = 67 * hash + Objects.hashCode((Object)this.name);
            hash = 67 * hash + this.gid;
            return hash;
        }
    }
}
