package handling.channel.handler;

import server.MapleCarnivalFactory.MCSkill;
import java.util.Iterator;
import server.life.MapleMonster;
import java.util.List;
import client.MapleDisease;
import server.Randomizer;
import server.MapleCarnivalFactory;
import tools.packet.MonsterCarnivalPacket;
import client.MapleCharacter;
import server.life.MapleLifeFactory;
import tools.Pair;
import tools.MaplePacketCreator;
import client.MapleClient;
import tools.data.LittleEndianAccessor;

public class MonsterCarnivalHandler
{
    public static final void MonsterCarnival(final LittleEndianAccessor slea, final MapleClient c) {
        if (c.getPlayer().getCarnivalParty() == null) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        final int tab = slea.readByte();
        final int num = slea.readInt();
        switch (tab) {
            case 0: {
                final List<Pair<Integer, Integer>> mobs = c.getPlayer().getMap().getMobsToSpawn();
                if (num >= mobs.size() || c.getPlayer().getAvailableCP() < (int)Integer.valueOf(((Pair<Integer, Integer>)mobs.get(num)).right)) {
                    c.getPlayer().dropMessage(5, "你沒有足夠的 CP.");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                final MapleMonster mons = MapleLifeFactory.getMonster((int)Integer.valueOf(((Pair<Integer, Integer>)mobs.get(num)).left));
                if (mons != null && c.getPlayer().getMap().makeCarnivalSpawn(c.getPlayer().getCarnivalParty().getTeam(), mons, num)) {
                    c.getPlayer().getCarnivalParty().useCP(c.getPlayer(), (int)Integer.valueOf(((Pair<Integer, Integer>)mobs.get(num)).right));
                    c.getPlayer().CPUpdate(false, c.getPlayer().getAvailableCP(), c.getPlayer().getTotalCP(), 0);
                    for (final MapleCharacter chr : c.getPlayer().getMap().getCharactersThreadsafe()) {
                        chr.CPUpdate(true, c.getPlayer().getCarnivalParty().getAvailableCP(), c.getPlayer().getCarnivalParty().getTotalCP(), c.getPlayer().getCarnivalParty().getTeam());
                    }
                    c.getPlayer().getMap().broadcastMessage(MonsterCarnivalPacket.playerSummoned(c.getPlayer().getName(), tab, num));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    break;
                }
                c.getPlayer().dropMessage(5, "你不可以在召喚怪物。");
                c.sendPacket(MaplePacketCreator.enableActions());
                break;
            }
            case 1: {
                final List<Integer> skillid = c.getPlayer().getMap().getSkillIds();
                if (num >= skillid.size()) {
                    c.getPlayer().dropMessage(5, "未知的錯誤");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                final MCSkill skil = MapleCarnivalFactory.getInstance().getSkill((int)Integer.valueOf(skillid.get(num)));
                if (skil == null || c.getPlayer().getAvailableCP() < skil.cpLoss) {
                    c.getPlayer().dropMessage(5, "你沒有足夠的 CP.");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                final MapleDisease dis = skil.getDisease();
                boolean found = false;
                for (final MapleCharacter chr2 : c.getPlayer().getMap().getCharactersThreadsafe()) {
                    if ((chr2.getParty() == null || (c.getPlayer().getParty() != null && chr2.getParty().getId() != c.getPlayer().getParty().getId())) && (skil.targetsAll || Randomizer.nextBoolean())) {
                        found = true;
                        if (dis == null) {
                            chr2.dispel();
                        }
                        else if (skil.getMobSkill() == null) {
                            chr2.getDiseaseBuff(dis, 1, 30000L, MapleDisease.getByDisease(dis), 1);
                        }
                        else {
                            chr2.getDiseaseBuff(dis, skil.getMobSkill());
                        }
                        if (!skil.targetsAll) {
                            break;
                        }
                        continue;
                    }
                }
                if (found) {
                    c.getPlayer().getCarnivalParty().useCP(c.getPlayer(), skil.cpLoss);
                    c.getPlayer().CPUpdate(false, c.getPlayer().getAvailableCP(), c.getPlayer().getTotalCP(), 0);
                    for (final MapleCharacter chr2 : c.getPlayer().getMap().getCharactersThreadsafe()) {
                        chr2.CPUpdate(true, c.getPlayer().getCarnivalParty().getAvailableCP(), c.getPlayer().getCarnivalParty().getTotalCP(), c.getPlayer().getCarnivalParty().getTeam());
                    }
                    c.getPlayer().getMap().broadcastMessage(MonsterCarnivalPacket.playerSummoned(c.getPlayer().getName(), tab, num));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    break;
                }
                c.getPlayer().dropMessage(5, "未知的錯誤.");
                c.sendPacket(MaplePacketCreator.enableActions());
                break;
            }
            case 2: {
                final MCSkill skil2 = MapleCarnivalFactory.getInstance().getGuardian(num);
                if (skil2 == null || c.getPlayer().getAvailableCP() < skil2.cpLoss) {
                    c.getPlayer().dropMessage(5, "你沒有足夠的 CP.");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (c.getPlayer().getMap().makeCarnivalReactor(c.getPlayer().getCarnivalParty().getTeam(), num)) {
                    c.getPlayer().getCarnivalParty().useCP(c.getPlayer(), skil2.cpLoss);
                    c.getPlayer().CPUpdate(false, c.getPlayer().getAvailableCP(), c.getPlayer().getTotalCP(), 0);
                    for (final MapleCharacter chr3 : c.getPlayer().getMap().getCharactersThreadsafe()) {
                        chr3.CPUpdate(true, c.getPlayer().getCarnivalParty().getAvailableCP(), c.getPlayer().getCarnivalParty().getTotalCP(), c.getPlayer().getCarnivalParty().getTeam());
                    }
                    c.getPlayer().getMap().broadcastMessage(MonsterCarnivalPacket.playerSummoned(c.getPlayer().getName(), tab, num));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    break;
                }
                c.getPlayer().dropMessage(5, "你不可以在召喚。");
                c.sendPacket(MaplePacketCreator.enableActions());
                break;
            }
        }
    }
}
