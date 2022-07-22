package handling.channel.handler;

import tools.FilePrinter;
import constants.GameConstants;
import client.ISkill;
import client.PlayerStats;
import java.util.Map;
import client.SkillFactory;
import server.Randomizer;
import tools.MaplePacketCreator;
import java.util.EnumMap;
import client.MapleStat;
import client.MapleCharacter;
import client.MapleClient;
import tools.data.LittleEndianAccessor;

public class StatsHandling
{
    public static final void DistributeAP(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        final Map<MapleStat, Integer> statupdate = new EnumMap<MapleStat, Integer>(MapleStat.class);
        if (chr != null) {
            c.sendPacket(MaplePacketCreator.updatePlayerStats(statupdate, true, chr));
            chr.updateTick(slea.readInt());
            final PlayerStats stat = chr.getStat();
            final int job = chr.getJob();
            final int statValue = slea.readInt();
            final MapleStat state = MapleStat.getByValue(statValue);
            if (chr.getRemainingAp() > 0) {
                switch (state) {
                    case STR: {
                        if (stat.getStr() >= 30000) {
                            return;
                        }
                        stat.setStr((short)(stat.getStr() + 1));
                        statupdate.put(MapleStat.STR, Integer.valueOf((int)stat.getStr()));
                        break;
                    }
                    case DEX: {
                        if (stat.getDex() >= 30000) {
                            return;
                        }
                        stat.setDex((short)(stat.getDex() + 1));
                        statupdate.put(MapleStat.DEX, Integer.valueOf((int)stat.getDex()));
                        break;
                    }
                    case INT: {
                        if (stat.getInt() >= 30000) {
                            return;
                        }
                        stat.setInt((short)(stat.getInt() + 1));
                        statupdate.put(MapleStat.INT, Integer.valueOf((int)stat.getInt()));
                        break;
                    }
                    case LUK: {
                        if (stat.getLuk() >= 30000) {
                            return;
                        }
                        stat.setLuk((short)(stat.getLuk() + 1));
                        statupdate.put(MapleStat.LUK, Integer.valueOf((int)stat.getLuk()));
                        break;
                    }
                    case MAXHP: {
                        short maxhp = stat.getMaxHp();
                        if (chr.getHpMpApUsed() >= 10000 || maxhp >= 30000) {
                            return;
                        }
                        if (job == 0) {
                            maxhp += (short)Randomizer.rand(8, 12);
                        }
                        else if ((job >= 100 && job <= 132) || (job >= 3200 && job <= 3212)) {
                            final ISkill improvingMaxHP = SkillFactory.getSkill(1000001);
                            final int improvingMaxHPLevel = c.getPlayer().getSkillLevel(improvingMaxHP);
                            maxhp += (short)Randomizer.rand(20, 25);
                            if (improvingMaxHPLevel >= 1) {
                                maxhp += (short)improvingMaxHP.getEffect(improvingMaxHPLevel).getX();
                            }
                        }
                        else if (job >= 200 && job <= 232) {
                            maxhp += (short)Randomizer.rand(10, 20);
                        }
                        else if ((job >= 300 && job <= 322) || (job >= 400 && job <= 434) || (job >= 1300 && job <= 1312) || (job >= 1400 && job <= 1412) || (job >= 3300 && job <= 3312)) {
                            maxhp += (short)Randomizer.rand(16, 20);
                        }
                        else if ((job >= 500 && job <= 522) || (job >= 3500 && job <= 3512)) {
                            final ISkill improvingMaxHP = SkillFactory.getSkill(5100000);
                            final int improvingMaxHPLevel = c.getPlayer().getSkillLevel(improvingMaxHP);
                            maxhp += (short)Randomizer.rand(18, 22);
                            if (improvingMaxHPLevel >= 1) {
                                maxhp += (short)improvingMaxHP.getEffect(improvingMaxHPLevel).getY();
                            }
                        }
                        else if (job >= 1500 && job <= 1512) {
                            final ISkill improvingMaxHP = SkillFactory.getSkill(15100000);
                            final int improvingMaxHPLevel = c.getPlayer().getSkillLevel(improvingMaxHP);
                            maxhp += (short)Randomizer.rand(18, 22);
                            if (improvingMaxHPLevel >= 1) {
                                maxhp += (short)improvingMaxHP.getEffect(improvingMaxHPLevel).getY();
                            }
                        }
                        else if (job >= 1100 && job <= 1112) {
                            final ISkill improvingMaxHP = SkillFactory.getSkill(11000000);
                            final int improvingMaxHPLevel = c.getPlayer().getSkillLevel(improvingMaxHP);
                            maxhp += (short)Randomizer.rand(36, 42);
                            if (improvingMaxHPLevel >= 1) {
                                maxhp += (short)improvingMaxHP.getEffect(improvingMaxHPLevel).getY();
                            }
                        }
                        else if (job >= 1200 && job <= 1212) {
                            maxhp += (short)Randomizer.rand(15, 21);
                        }
                        else if (job >= 2000 && job <= 2112) {
                            maxhp += (short)Randomizer.rand(40, 50);
                        }
                        else {
                            maxhp += (short)Randomizer.rand(50, 100);
                        }
                        maxhp = (short)Math.min(30000, Math.abs((int)maxhp));
                        chr.setHpMpApUsed((short)(chr.getHpMpApUsed() + 1));
                        stat.setMaxHp(maxhp);
                        statupdate.put(MapleStat.MAXHP, Integer.valueOf((int)maxhp));
                        break;
                    }
                    case MAXMP: {
                        short maxmp = stat.getMaxMp();
                        if (chr.getHpMpApUsed() >= 10000 || stat.getMaxMp() >= 30000) {
                            return;
                        }
                        if (job == 0) {
                            maxmp += (short)Randomizer.rand(6, 8);
                            maxmp += (short)(stat.getTotalInt() / 20);
                        }
                        else if (job >= 100 && job <= 132) {
                            maxmp += (short)Randomizer.rand(2, 4);
                            maxmp += (short)(stat.getTotalInt() / 20);
                        }
                        else if ((job >= 200 && job <= 232) || (job >= 3200 && job <= 3212)) {
                            final ISkill improvingMaxMP = SkillFactory.getSkill(2000001);
                            final int improvingMaxMPLevel = c.getPlayer().getSkillLevel(improvingMaxMP);
                            maxmp += (short)Randomizer.rand(18, 20);
                            if (improvingMaxMPLevel >= 1) {
                                maxmp += (short)(improvingMaxMP.getEffect(improvingMaxMPLevel).getY() * 2);
                            }
                        }
                        else if ((job >= 300 && job <= 322) || (job >= 400 && job <= 434) || (job >= 500 && job <= 522) || (job >= 3200 && job <= 3212) || (job >= 3500 && job <= 3512) || (job >= 1300 && job <= 1312) || (job >= 1400 && job <= 1412) || (job >= 1500 && job <= 1512)) {
                            maxmp += (short)Randomizer.rand(10, 12);
                            maxmp += (short)(stat.getTotalInt() / 20);
                        }
                        else if (job >= 1100 && job <= 1112) {
                            maxmp += (short)Randomizer.rand(6, 9);
                            maxmp += (short)(stat.getTotalInt() / 20);
                        }
                        else if (job >= 1200 && job <= 1212) {
                            final ISkill improvingMaxMP = SkillFactory.getSkill(12000000);
                            final int improvingMaxMPLevel = c.getPlayer().getSkillLevel(improvingMaxMP);
                            maxmp += (short)Randomizer.rand(18, 20);
                            if (improvingMaxMPLevel >= 1) {
                                maxmp += (short)(improvingMaxMP.getEffect(improvingMaxMPLevel).getY() * 2);
                            }
                        }
                        else if (job >= 2000 && job <= 2112) {
                            maxmp += (short)Randomizer.rand(6, 9);
                            maxmp += (short)(stat.getTotalInt() / 20);
                        }
                        else {
                            maxmp += (short)Randomizer.rand(50, 100);
                        }
                        maxmp = (short)Math.min(30000, Math.abs((int)maxmp));
                        chr.setHpMpApUsed((short)(chr.getHpMpApUsed() + 1));
                        stat.setMaxMp(maxmp);
                        statupdate.put(MapleStat.MAXMP, Integer.valueOf((int)maxmp));
                        break;
                    }
                    default: {
                        c.sendPacket(MaplePacketCreator.enableActions());
                        return;
                    }
                }
                chr.setRemainingAp((short)(chr.getRemainingAp() - 1));
                statupdate.put(MapleStat.AVAILABLEAP, Integer.valueOf((int)chr.getRemainingAp()));
                c.sendPacket(MaplePacketCreator.updatePlayerStats(statupdate, true, chr));
            }
        }
    }
    
    public static final void DistributeSP(final int skillid, final MapleClient c, final MapleCharacter chr) {
        boolean isBeginnerSkill = false;
        int remainingSp = 0;
        switch (skillid) {
            case 1000:
            case 1001:
            case 1002: {
                final int snailsLevel = chr.getSkillLevel(SkillFactory.getSkill(1000));
                final int recoveryLevel = chr.getSkillLevel(SkillFactory.getSkill(1001));
                final int nimbleFeetLevel = chr.getSkillLevel(SkillFactory.getSkill(1002));
                remainingSp = Math.min(chr.getLevel() - 1, 6) - snailsLevel - recoveryLevel - nimbleFeetLevel;
                isBeginnerSkill = true;
                break;
            }
            case 10001000:
            case 10001001:
            case 10001002: {
                final int snailsLevel = chr.getSkillLevel(SkillFactory.getSkill(10001000));
                final int recoveryLevel = chr.getSkillLevel(SkillFactory.getSkill(10001001));
                final int nimbleFeetLevel = chr.getSkillLevel(SkillFactory.getSkill(10001002));
                remainingSp = Math.min(chr.getLevel() - 1, 6) - snailsLevel - recoveryLevel - nimbleFeetLevel;
                isBeginnerSkill = true;
                break;
            }
            case 20001000:
            case 20001001:
            case 20001002: {
                final int snailsLevel = chr.getSkillLevel(SkillFactory.getSkill(20001000));
                final int recoveryLevel = chr.getSkillLevel(SkillFactory.getSkill(20001001));
                final int nimbleFeetLevel = chr.getSkillLevel(SkillFactory.getSkill(20001002));
                remainingSp = Math.min(chr.getLevel() - 1, 6) - snailsLevel - recoveryLevel - nimbleFeetLevel;
                isBeginnerSkill = true;
                break;
            }
            case 20011000:
            case 20011001:
            case 20011002: {
                final int snailsLevel = chr.getSkillLevel(SkillFactory.getSkill(20011000));
                final int recoveryLevel = chr.getSkillLevel(SkillFactory.getSkill(20011001));
                final int nimbleFeetLevel = chr.getSkillLevel(SkillFactory.getSkill(20011002));
                remainingSp = Math.min(chr.getLevel() - 1, 6) - snailsLevel - recoveryLevel - nimbleFeetLevel;
                isBeginnerSkill = true;
                break;
            }
            case 30000002:
            case 30001000:
            case 30001001: {
                final int snailsLevel = chr.getSkillLevel(SkillFactory.getSkill(30001000));
                final int recoveryLevel = chr.getSkillLevel(SkillFactory.getSkill(30001001));
                final int nimbleFeetLevel = chr.getSkillLevel(SkillFactory.getSkill(30000002));
                remainingSp = Math.min(chr.getLevel() - 1, 9) - snailsLevel - recoveryLevel - nimbleFeetLevel;
                isBeginnerSkill = true;
                break;
            }
            default: {
                remainingSp = chr.getRemainingSp(GameConstants.getSkillBookForSkill(skillid));
                break;
            }
        }
        final ISkill skill = SkillFactory.getSkill(skillid);
        if (skill != null) {
            if (skill.hasRequiredSkill() && chr.getSkillLevel(SkillFactory.getSkill(skill.getRequiredSkillId())) < skill.getRequiredSkillLevel()) {
                return;
            }
            final int maxlevel = skill.isFourthJob() ? chr.getMasterLevel(skill) : skill.getMaxLevel();
            final int curLevel = chr.getSkillLevel(skill);
            if (skill.isInvisible() && chr.getSkillLevel(skill) == 0 && ((skill.isFourthJob() && chr.getMasterLevel(skill) == 0) || (!skill.isFourthJob() && maxlevel < 10 && !isBeginnerSkill))) {
                return;
            }
            for (final int i : GameConstants.blockedSkills) {
                if (skill.getId() == i) {
                    chr.dropMessage(1, "You may not add this skill.");
                    return;
                }
            }
            if (remainingSp > 0 && curLevel + 1 <= maxlevel && (skill.canBeLearnedBy((int)chr.getJob()) || isBeginnerSkill)) {
                if (!isBeginnerSkill) {
                    final int skillbook = GameConstants.getSkillBookForSkill(skillid);
                    chr.setRemainingSp(chr.getRemainingSp(skillbook) - 1, skillbook);
                }
                c.sendPacket(MaplePacketCreator.updateSp(chr, false));
                chr.changeSkillLevel(skill, (byte)(curLevel + 1), chr.getMasterLevel(skill));
            }
            else if (!skill.canBeLearnedBy((int)chr.getJob())) {}
        }
    }
    
    public static final void AutoAssignAP(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        chr.updateTick(slea.readInt());
        slea.skip(4);
        if (chr.getRemainingAp() < 1) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (slea.available() < 16L) {
            System.err.println("AutoAssignAP UnHandled : \n" + slea.toString(true));
            FilePrinter.printError("PacketLogsExcpt.txt", "slea.toString(true)");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        short total = 0;
        short extras = 0;
        for (int i = 0; i < 2; ++i) {
            final int type = slea.readInt();
            final int tempVal = slea.readInt();
            if (tempVal < 0 || tempVal > c.getPlayer().getRemainingAp()) {
                return;
            }
            total += (short)tempVal;
            extras += (short)gainStatByType(chr, MapleStat.getByValue(type), tempVal);
        }
        final short remainingAp = (short)(chr.getRemainingAp() - total + extras);
        chr.setRemainingAp(remainingAp);
        chr.updateSingleStat(MapleStat.AVAILABLEAP, (int)remainingAp);
        c.sendPacket(MaplePacketCreator.enableActions());
    }
    
    static int gainStatByType(final MapleCharacter chr, final MapleStat type, final int gain) {
        if (chr != null) {
            short newVal = 0;
            if (type.equals((Object)MapleStat.STR)) {
                newVal = (short)(chr.getStat().getStr() + gain);
                if (newVal > 999) {
                    chr.getStat().setStr((short)999);
                }
                else {
                    chr.getStat().setStr(newVal);
                }
            }
            else if (type.equals((Object)MapleStat.INT)) {
                newVal = (short)(chr.getStat().getInt() + gain);
                if (newVal > 999) {
                    chr.getStat().setInt((short)999);
                }
                else {
                    chr.getStat().setInt(newVal);
                }
            }
            else if (type.equals((Object)MapleStat.LUK)) {
                newVal = (short)(chr.getStat().getLuk() + gain);
                if (newVal > 999) {
                    chr.getStat().setLuk((short)999);
                }
                else {
                    chr.getStat().setLuk(newVal);
                }
            }
            else if (type.equals((Object)MapleStat.DEX)) {
                newVal = (short)(chr.getStat().getDex() + gain);
                if (newVal > 999) {
                    chr.getStat().setDex((short)999);
                }
                else {
                    chr.getStat().setDex(newVal);
                }
            }
            if (newVal > 999) {
                chr.updateSingleStat(type, 999);
                return newVal - 999;
            }
            chr.updateSingleStat(type, (int)newVal);
        }
        return 0;
    }
}
