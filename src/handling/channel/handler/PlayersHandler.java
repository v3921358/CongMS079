package handling.channel.handler;

import server.maps.MapleMap;
import client.MapleLieDetector;
import server.maps.FieldLimitType;
import java.util.Map;
import client.inventory.ItemFlag;
import tools.ArrayMap;
import java.awt.Point;
import java.awt.geom.Point2D;
import server.events.MapleCoconut.MapleCoconuts;
import server.events.MapleCoconut;
import scripting.ReactorScriptManager;
import server.maps.MapleReactor;
import server.MapleInventoryManipulator;
import client.inventory.MapleInventoryType;
import server.MapleItemInformationProvider;
import constants.GameConstants;
import java.util.Iterator;
import server.maps.MapleDoor;
import server.maps.MapleMapObject;
import client.MapleStat;
import handling.world.World.Broadcast;
import tools.MaplePacketCreator;
import tools.FileoutputUtil;
import server.maps.MapleMapObjectType;
import client.MapleClient;
import client.inventory.IItem;
import client.MapleCharacter;
import tools.data.LittleEndianAccessor;

public class PlayersHandler
{
    public static void Note(final LittleEndianAccessor slea, final MapleCharacter chr) {
        final byte type = slea.readByte();
        switch (type) {
            case 0: {
                final String name = slea.readMapleAsciiString();
                final String msg = slea.readMapleAsciiString();
                final boolean fame = slea.readByte() > 0;
                slea.readInt();
                final IItem itemz = chr.getCashInventory().findByCashId((int)slea.readLong());
                if (itemz == null || !itemz.getGiftFrom().equalsIgnoreCase(name) || !chr.getCashInventory().canSendNote(itemz.getUniqueId())) {
                    return;
                }
                try {
                    chr.sendNote(name, msg, (int)(fame ? 1 : 0));
                    chr.getCashInventory().sendedNote(itemz.getUniqueId());
                }
                catch (Exception ex) {}
                break;
            }
            case 1: {
                final byte num = slea.readByte();
                slea.skip(2);
                for (int i = 0; i < num; ++i) {
                    final int id = slea.readInt();
                    chr.deleteNote(id, 0);
                }
                break;
            }
            default: {
                System.out.println("Unhandled note action, " + (int)type + "");
                break;
            }
        }
    }
    
    public static void GiveFame(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        final int who = slea.readInt();
        final int mode = slea.readByte();
        final int famechange = (mode == 0) ? -1 : 1;
        final MapleCharacter target = (MapleCharacter)chr.getMap().getMapObject(who, MapleMapObjectType.PLAYER);
        if (target != null) {
            if (target.getId() == chr.getId()) {
                FileoutputUtil.logToFile("logs/Hack/Ban/修改封包.txt", "\r\n " + FileoutputUtil.NowTime() + " 玩家：" + chr.getName() + "(" + chr.getId() + ") 修改名聲封包，使用時封鎖。加自己名聲");
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[封鎖系統] " + chr.getName() + " 因為修改封包而被管理員永久停權。"));
                Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语]  " + chr.getName() + "(" + chr.getId() + ") 修改名聲封包，使用時封鎖。加自己名聲"));
                chr.ban("修改封包", true, true, false);
                chr.getClient().getSession().close();
                return;
            }
            if (chr.getLevel() < 15) {
                FileoutputUtil.logToFile("logs/Hack/Ban/修改封包.txt", "\r\n " + FileoutputUtil.NowTime() + " 玩家：" + chr.getName() + "(" + chr.getId() + ")(等級:" + (int)chr.getLevel() + ") 修改名聲封包，使用時封鎖。十五等以下加名聲");
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[封鎖系統] " + chr.getName() + " 因為修改封包而被管理員永久停權。"));
                Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语]  " + chr.getName() + "(" + chr.getId() + ")(等級:" + (int)chr.getLevel() + ") 修改名聲封包，使用時封鎖。十五等以下加名聲"));
                chr.ban("修改封包", true, true, false);
                chr.getClient().getSession().close();
                return;
            }
            switch (chr.canGiveFame(target)) {
                case OK: {
                    if (Math.abs(target.getFame() + famechange) <= 30000) {
                        target.addFame(famechange);
                        target.updateSingleStat(MapleStat.FAME, (int)target.getFame());
                    }
                    if (!chr.isGM()) {
                        chr.hasGivenFame(target);
                    }
                    c.sendPacket(MaplePacketCreator.giveFameResponse(mode, target.getName(), (int)target.getFame()));
                    target.getClient().sendPacket(MaplePacketCreator.receiveFame(mode, chr.getName()));
                    break;
                }
                case NOT_TODAY: {
                    c.sendPacket(MaplePacketCreator.giveFameErrorResponse(3));
                    break;
                }
                case NOT_THIS_MONTH: {
                    c.sendPacket(MaplePacketCreator.giveFameErrorResponse(4));
                    break;
                }
            }
        }
    }
    
    public static void UseDoor(final LittleEndianAccessor slea, final MapleCharacter chr) {
        final int oid = slea.readInt();
        final boolean mode = slea.readByte() == 0;
        for (final MapleMapObject obj : chr.getMap().getAllDoorsThreadsafe()) {
            final MapleDoor door = (MapleDoor)obj;
            if (door.getOwnerId() == oid) {
                door.warp(chr, mode);
                break;
            }
        }
    }
    
    public static void TransformPlayer(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        chr.updateTick(slea.readInt());
        final byte slot = (byte)slea.readShort();
        final int itemId = slea.readInt();
        final String target = slea.readMapleAsciiString().toLowerCase();
        final MapleInventoryType type = GameConstants.getInventoryType(itemId);
        IItem toUse = null;
        if (type != null && chr.getInventory(type) != null) {
            toUse = chr.getInventory(type).findById(itemId);
        }
        if (toUse == null || toUse.getQuantity() < 1 || toUse.getItemId() != itemId) {
            c.getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
            return;
        }
        switch (itemId) {
            case 2212000: {
                for (final MapleCharacter search_chr : c.getPlayer().getMap().getCharactersThreadsafe()) {
                    if (search_chr.getName().toLowerCase().equals((Object)target)) {
                        MapleItemInformationProvider.getInstance().getItemEffect(2210023).applyTo(search_chr);
                        search_chr.dropMessage(6, chr.getName() + " has played a prank on you!");
                        MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, (short)slot, (short)1, false);
                    }
                }
                break;
            }
        }
    }
    
    public static void HitReactor(final LittleEndianAccessor slea, final MapleClient c) {
        final int oid = slea.readInt();
        final int charPos = slea.readInt();
        final short stance = slea.readShort();
        final MapleReactor reactor = c.getPlayer().getMap().getReactorByOid(oid);
        if (reactor == null || !reactor.isAlive()) {
            return;
        }
        reactor.hitReactor(charPos, stance, c);
    }
    
    public static void TouchReactor(final LittleEndianAccessor slea, final MapleClient c) {
        final int oid = slea.readInt();
        final boolean touched = slea.readByte() > 0;
        final MapleReactor reactor = c.getPlayer().getMap().getReactorByOid(oid);
        if (!touched || reactor == null || !reactor.isAlive() || reactor.getReactorId() < 6109013 || reactor.getReactorId() > 6109027) {
            return;
        }
        ReactorScriptManager.getInstance().act(c, reactor);
    }
    
    public static void hitCoconut(final LittleEndianAccessor slea, final MapleClient c) {
        final int id = slea.readShort();
        String co = "農夫的樂趣";
        final MapleCoconut map = null;
        if (map == null || !map.isRunning()) {
            co = "可樂熊";
            if (map == null || !map.isRunning()) {
                return;
            }
        }
        final MapleCoconuts nut = map.getCoconut(id);
        if (nut == null || !nut.isHittable()) {
            return;
        }
        if (System.currentTimeMillis() < nut.getHitTime()) {
            return;
        }
        if (nut.getHits() > 2 && Math.random() < 0.4 && !nut.isStopped()) {
            nut.setHittable(false);
            if (Math.random() < 0.01 && map.getStopped() > 0) {
                nut.setStopped(true);
                map.stopCoconut();
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.hitCoconut(false, id, 1));
                return;
            }
            nut.resetHits();
            if (Math.random() < 0.05 && map.getBombings() > 0) {
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.hitCoconut(false, id, 2));
                map.bombCoconut();
            }
            else if (map.getFalling() > 0) {
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.hitCoconut(false, id, 3));
                map.fallCoconut();
                if (c.getPlayer().getTeam() == 0) {
                    map.addMapleScore();
                }
                else {
                    map.addStoryScore();
                }
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.coconutScore(map.getCoconutScore()));
            }
        }
        else {
            nut.hit();
            c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.hitCoconut(false, id, 1));
        }
    }
    
    public static void FollowRequest(final LittleEndianAccessor slea, final MapleClient c) {
        MapleCharacter tt = c.getPlayer().getMap().getCharacterById(slea.readInt());
        if (slea.readByte() > 0) {
            tt = c.getPlayer().getMap().getCharacterById(c.getPlayer().getFollowId());
            if (tt != null && tt.getFollowId() == c.getPlayer().getId()) {
                tt.setFollowOn(true);
                c.getPlayer().setFollowOn(true);
            }
            else {
                c.getPlayer().checkFollow();
            }
            return;
        }
        if (slea.readByte() > 0) {
            tt = c.getPlayer().getMap().getCharacterById(c.getPlayer().getFollowId());
            if (tt != null && tt.getFollowId() == c.getPlayer().getId() && c.getPlayer().isFollowOn()) {
                c.getPlayer().checkFollow();
            }
            return;
        }
        if (tt != null && tt.getPosition().distanceSq((Point2D)c.getPlayer().getPosition()) < 10000.0 && tt.getFollowId() == 0 && c.getPlayer().getFollowId() == 0 && tt.getId() != c.getPlayer().getId()) {
            tt.setFollowId(c.getPlayer().getId());
            tt.setFollowOn(false);
            tt.setFollowInitiator(false);
            c.getPlayer().setFollowOn(false);
            c.getPlayer().setFollowInitiator(false);
            tt.getClient().sendPacket(MaplePacketCreator.followRequest(c.getPlayer().getId()));
        }
        else {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "You are too far away."));
        }
    }
    
    public static void FollowReply(final LittleEndianAccessor slea, final MapleClient c) {
        if (c.getPlayer().getFollowId() > 0 && c.getPlayer().getFollowId() == slea.readInt()) {
            final MapleCharacter tt = c.getPlayer().getMap().getCharacterById(c.getPlayer().getFollowId());
            if (tt != null && tt.getPosition().distanceSq((Point2D)c.getPlayer().getPosition()) < 10000.0 && tt.getFollowId() == 0 && tt.getId() != c.getPlayer().getId()) {
                final boolean accepted = slea.readByte() > 0;
                if (accepted) {
                    tt.setFollowId(c.getPlayer().getId());
                    tt.setFollowOn(true);
                    tt.setFollowInitiator(true);
                    c.getPlayer().setFollowOn(true);
                    c.getPlayer().setFollowInitiator(false);
                    c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.followEffect(tt.getId(), c.getPlayer().getId(), null));
                }
                else {
                    c.getPlayer().setFollowId(0);
                    tt.setFollowId(0);
                    tt.getClient().sendPacket(MaplePacketCreator.getFollowMsg(5));
                }
            }
            else {
                if (tt != null) {
                    tt.setFollowId(0);
                    c.getPlayer().setFollowId(0);
                }
                c.sendPacket(MaplePacketCreator.serverNotice(1, "You are too far away."));
            }
        }
        else {
            c.getPlayer().setFollowId(0);
        }
    }
    
    public static void UnlockItem(final LittleEndianAccessor slea, final MapleClient c) {
        final short Itemsize = slea.readShort();
        final short _type = slea.readShort();
        final short slot = slea.readShort();
        final MapleInventoryType type = MapleInventoryType.getByType((byte)_type);
        final IItem item = c.getPlayer().getInventory(type).getItem(slot);
        if (item == null) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        boolean add = false;
        final int UnlockItem = 2051000;
        final Map<IItem, MapleInventoryType> eqs = new ArrayMap<IItem, MapleInventoryType>();
        if (ItemFlag.LOCK.check((int)item.getFlag())) {
            item.setFlag((byte)(item.getFlag() - ItemFlag.LOCK.getValue()));
            add = true;
            c.getPlayer().reloadC();
            c.getPlayer().dropMessage(1, "已經解鎖！");
        }
        else if (ItemFlag.UNTRADEABLE.check((int)item.getFlag())) {
            item.setFlag((byte)(item.getFlag() - ItemFlag.UNTRADEABLE.getValue()));
            add = true;
            c.getPlayer().reloadC();
            c.getPlayer().dropMessage(1, "已經解鎖！");
        }
        if (add) {
            eqs.put(item, type);
            MapleInventoryManipulator.removeById(c.getPlayer().getClient(), MapleInventoryType.USE, 2051000, 1, false, false);
        }
        add = false;
    }
    
    public static void Solomon(final LittleEndianAccessor slea, final MapleClient c) {
        c.sendPacket(MaplePacketCreator.enableActions());
        c.getPlayer().updateTick(slea.readInt());
        final IItem item = c.getPlayer().getInventory(MapleInventoryType.USE).getItem(slea.readShort());
        if (item == null || item.getItemId() != slea.readInt() || item.getQuantity() <= 0 || c.getPlayer().getGachExp() > 0 || c.getPlayer().getLevel() > 50 || MapleItemInformationProvider.getInstance().getItemEffect(item.getItemId()).getEXP() <= 0) {
            return;
        }
        c.getPlayer().setGachExp(c.getPlayer().getGachExp() + MapleItemInformationProvider.getInstance().getItemEffect(item.getItemId()).getEXP());
        MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, item.getPosition(), (short)1, false);
        c.getPlayer().updateSingleStat(MapleStat.GACHAPONEXP, c.getPlayer().getGachExp());
    }
    
    public static void GachExp(final LittleEndianAccessor slea, final MapleClient c) {
        c.sendPacket(MaplePacketCreator.enableActions());
        c.getPlayer().updateTick(slea.readInt());
        if (c.getPlayer().getGachExp() <= 0) {
            return;
        }
        c.getPlayer().gainExp(c.getPlayer().getGachExp() * GameConstants.getExpRate_Quest((int)c.getPlayer().getLevel()), true, true, false);
        c.getPlayer().setGachExp(0);
        c.getPlayer().updateSingleStat(MapleStat.GACHAPONEXP, 0);
    }
    
    public static void RingAction(final LittleEndianAccessor slea, final MapleClient c) {
        final byte mode = slea.readByte();
        if (mode == 0) {
            final String name = slea.readMapleAsciiString();
            final int itemid = slea.readInt();
            final int newItemId = 1112300 + (itemid - 2240004);
            final MapleCharacter chr = c.getChannelServer().getPlayerStorage().getCharacterByName(name);
            int errcode = 0;
            if (c.getPlayer().getMarriageId() > 0) {
                errcode = 23;
            }
            else {
                if (c.getPlayer().haveItem(newItemId)) {
                    c.getPlayer().dropMessage("請先將身上的戒指丟棄唷。");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (chr == null) {
                    errcode = 18;
                }
                else if (chr.getMapId() != c.getPlayer().getMapId()) {
                    errcode = 19;
                }
                else if (!c.getPlayer().haveItem(itemid, 1) || itemid < 2240004 || itemid > 2240015) {
                    errcode = 13;
                }
                else if (chr.getMarriageId() > 0 || chr.getMarriageItemId() > 0) {
                    errcode = 24;
                }
                else if (!MapleInventoryManipulator.checkSpace(c, newItemId, 1, "")) {
                    errcode = 20;
                }
                else if (!MapleInventoryManipulator.checkSpace(chr.getClient(), newItemId, 1, "")) {
                    errcode = 21;
                }
            }
            if (errcode > 0) {
                c.sendPacket(MaplePacketCreator.sendEngagement((byte)errcode, 0, null, null));
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            c.getPlayer().setMarriageItemId(itemid);
            if (chr != null) {
                chr.getClient().sendPacket(MaplePacketCreator.sendEngagementRequest(c.getPlayer().getName(), c.getPlayer().getId()));
            }
        }
        else if (mode == 1) {
            c.getPlayer().setMarriageItemId(0);
        }
        else if (mode == 2) {
            final boolean accepted = slea.readByte() > 0;
            final String name2 = slea.readMapleAsciiString();
            final int id = slea.readInt();
            final MapleCharacter chr = c.getChannelServer().getPlayerStorage().getCharacterByName(name2);
            if (c.getPlayer().getMarriageId() > 0 || chr == null || chr.getId() != id || chr.getMarriageItemId() <= 0 || !chr.haveItem(chr.getMarriageItemId(), 1) || chr.getMarriageId() > 0) {
                c.sendPacket(MaplePacketCreator.sendEngagement((byte)29, 0, null, null));
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            if (accepted) {
                final int newItemId2 = 1112300 + (chr.getMarriageItemId() - 2240004);
                if (!MapleInventoryManipulator.checkSpace(c, newItemId2, 1, "") || !MapleInventoryManipulator.checkSpace(chr.getClient(), newItemId2, 1, "")) {
                    c.sendPacket(MaplePacketCreator.sendEngagement((byte)21, 0, null, null));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                MapleInventoryManipulator.addById(c, newItemId2, (short)1);
                MapleInventoryManipulator.removeById(chr.getClient(), MapleInventoryType.USE, chr.getMarriageItemId(), 1, false, false);
                MapleInventoryManipulator.addById(chr.getClient(), newItemId2, (short)1);
                chr.getClient().sendPacket(MaplePacketCreator.sendEngagement((byte)16, newItemId2, chr, c.getPlayer()));
                chr.setMarriageId(c.getPlayer().getId());
                c.getPlayer().setMarriageId(chr.getId());
            }
            else {
                chr.getClient().sendPacket(MaplePacketCreator.sendEngagement((byte)30, 0, null, null));
            }
            c.sendPacket(MaplePacketCreator.enableActions());
            chr.setMarriageItemId(0);
        }
        else if (mode == 3) {
            final int itemId = slea.readInt();
            final MapleInventoryType type = GameConstants.getInventoryType(itemId);
            final IItem item = c.getPlayer().getInventory(type).findById(itemId);
            if (item != null && type == MapleInventoryType.ETC && itemId / 10000 == 421) {
                MapleInventoryManipulator.drop(c, type, item.getPosition(), item.getQuantity());
            }
        }
    }
    
    public static void UpdateCharInfo(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (slea.available() == 0L) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        final int type = slea.readByte();
        if (type == 0) {
            final String charmessage = slea.readMapleAsciiString();
            c.getPlayer().setcharmessage(charmessage);
        }
        else if (type == 1) {
            final int expression = slea.readByte();
            c.getPlayer().setexpression(expression);
        }
        else if (type == 2) {
            final int blood = slea.readByte();
            final int month = slea.readByte();
            final int day = slea.readByte();
            final int constellation = slea.readByte();
            c.getPlayer().setblood(blood);
            c.getPlayer().setmonth(month);
            c.getPlayer().setday(day);
            c.getPlayer().setconstellation(constellation);
        }
    }
    
    public static void LieDetector(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr, final boolean isItem) {
        if (chr == null || chr.getMap() == null) {
            return;
        }
        final String target = slea.readMapleAsciiString();
        byte slot = 0;
        if (isItem) {
            if (!chr.getCheatTracker().canLieDetector()) {
                chr.dropMessage(1, "您已使用過一次測謊儀。暫時無法使用測謊儀道具。");
                c.getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
                return;
            }
            slot = (byte)slea.readShort();
            final int itemId = slea.readInt();
            final IItem toUse = chr.getInventory(MapleInventoryType.USE).getItem((short)slot);
            if (toUse == null || toUse.getQuantity() <= 0 || toUse.getItemId() != itemId || itemId != 2190000) {
                c.getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
                return;
            }
        }
        else if (!chr.isGM()) {
            c.getSession().close();
            return;
        }
        if ((FieldLimitType.PotionUse.check(chr.getMap().getFieldLimit()) && isItem) || chr.getMap().getReturnMapId() == chr.getMapId()) {
            chr.dropMessage(5, "當前地图無法使用測謊儀。");
            c.getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
            return;
        }
        final MapleCharacter search_chr = chr.getMap().getCharacterByName(target);
        if (search_chr == null || search_chr.getId() == chr.getId() || (search_chr.isGM() && !chr.isGM())) {
            chr.dropMessage(1, "未找到角色。");
            c.getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
            return;
        }
        if (search_chr.getEventInstance() != null || search_chr.getMapId() == 180000001) {
            chr.dropMessage(5, "當前地图無法使用測謊儀。");
            c.getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
            return;
        }
        if (search_chr.getAntiMacro().inProgress()) {
            c.getSession().writeAndFlush((Object)MaplePacketCreator.LieDetectorResponse((byte)3));
            c.getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
            return;
        }
        if (search_chr.getAntiMacro().isPassed()) {
            search_chr.getAntiMacro().setPassed(false);
        }
        if ((search_chr.getAntiMacro().isPassed() && isItem) || search_chr.getAntiMacro().canDetector(System.currentTimeMillis())) {
            c.getSession().writeAndFlush((Object)MaplePacketCreator.LieDetectorResponse((byte)2));
            c.getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
            return;
        }
        if (!search_chr.getAntiMacro().startLieDetector(chr.getName(), isItem, false)) {
            chr.dropMessage(5, "使用測謊儀失敗。");
            c.getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
            return;
        }
        if (isItem) {
            MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, (short)slot, (short)1, false);
        }
        search_chr.dropMessage(5, chr.getName() + " 對你使用測謊儀");
    }
    
    public static void LieDetectorResponse(final LittleEndianAccessor slea, final MapleClient c) {
        if (c.getPlayer() == null || c.getPlayer().getMap() == null) {
            return;
        }
        final String answer = slea.readMapleAsciiString();
        final MapleLieDetector ld = c.getPlayer().getAntiMacro();
        if (!ld.inProgress() || (ld.isPassed() && ld.getLastType() == 0) || ld.getAnswer() == null || answer.length() <= 0) {
            c.getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
            return;
        }
        if (answer.equalsIgnoreCase(ld.getAnswer())) {
            final MapleCharacter search_chr = c.getPlayer().getMap().getCharacterByName(ld.getTester());
            if (search_chr != null && search_chr.getId() != c.getPlayer().getId()) {
                search_chr.dropMessage(1, c.getPlayer().getName() + " 通過測謊儀的檢測。");
            }
            ld.end();
            c.getSession().writeAndFlush((Object)MaplePacketCreator.LieDetectorResponse((byte)9, (byte)0));
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语] 玩家: " + c.getPlayer().getName() + " (等級 " + (int)c.getPlayer().getLevel() + ") 通過了測謊儀檢測。"));
        }
        else if (ld.getAttempt() < 2) {
            ld.startLieDetector(ld.getTester(), ld.getLastType() == 0, true);
        }
        else {
            final MapleCharacter search_chr = c.getPlayer().getMap().getCharacterByName(ld.getTester());
            if (search_chr != null && search_chr.getId() != c.getPlayer().getId()) {
                search_chr.dropMessage(1, c.getPlayer().getName() + " 沒有通過測謊儀檢測。");
            }
            ld.end();
            c.getPlayer().getClient().getSession().writeAndFlush((Object)MaplePacketCreator.LieDetectorResponse((byte)7, (byte)0));
            final MapleMap map = c.getPlayer().getMap().getReturnMap();
            c.getPlayer().changeMap(map, map.getPortal(0));
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语] 玩家: " + c.getPlayer().getName() + " (等級 " + (int)c.getPlayer().getLevel() + ") 未通過測謊儀檢測，疑似使用腳本外掛！"));
        }
    }
}
