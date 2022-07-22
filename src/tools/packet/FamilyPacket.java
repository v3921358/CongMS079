package tools.packet;

import handling.world.family.MapleFamilyCharacter;
import handling.world.family.MapleFamily;
import tools.Pair;
import handling.world.World.Family;
import client.MapleCharacter;
import java.util.Iterator;
import java.util.List;
import handling.world.family.MapleFamilyBuff.MapleFamilyBuffEntry;
import handling.world.family.MapleFamilyBuff;
import handling.SendPacketOpcode;
import tools.data.MaplePacketLittleEndianWriter;

public class FamilyPacket
{
    public static byte[] getFamilyData() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.FAMILY_PRIVILEGE_LIST.getValue());
        final List<MapleFamilyBuffEntry> entries = MapleFamilyBuff.getBuffEntry();
        mplew.writeInt(entries.size());
        for (final MapleFamilyBuffEntry entry : entries) {
            mplew.write(entry.type);
            mplew.writeInt(entry.rep);
            mplew.writeInt(entry.count);
            mplew.writeMapleAsciiString(entry.name);
            mplew.writeMapleAsciiString(entry.desc);
        }
        return mplew.getPacket();
    }
    
    public static byte[] changeRep(final int r) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.FAMILY_FAMOUS_POINT_INC_RESULT.getValue());
        mplew.writeInt(r);
        mplew.writeInt(0);
        return mplew.getPacket();
    }
    
    public static byte[] getFamilyInfo(final MapleCharacter chr) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.FAMILY_INFO_RESULT.getValue());
        mplew.writeInt(chr.getCurrentRep());
        mplew.writeInt(chr.getTotalRep());
        mplew.writeInt(chr.getTotalRep());
        mplew.writeShort(chr.getNoJuniors());
        mplew.writeShort(2);
        mplew.writeShort(chr.getNoJuniors());
        final MapleFamily family = Family.getFamily(chr.getFamilyId());
        if (family != null) {
            mplew.writeInt(family.getLeaderId());
            mplew.writeMapleAsciiString(family.getLeaderName());
            mplew.writeMapleAsciiString(family.getNotice());
        }
        else {
            mplew.writeLong(0L);
        }
        final List<Pair<Integer, Integer>> b = chr.usedBuffs();
        mplew.writeInt(b.size());
        for (final Pair<Integer, Integer> ii : b) {
            mplew.writeInt((int)Integer.valueOf(ii.getLeft()));
            mplew.writeInt((int)Integer.valueOf(ii.getRight()));
        }
        return mplew.getPacket();
    }
    
    public static void addFamilyCharInfo(final MapleFamilyCharacter ldr, final MaplePacketLittleEndianWriter mplew) {
        mplew.writeInt(ldr.getId());
        mplew.writeInt(ldr.getSeniorId());
        mplew.writeShort(ldr.getJobId());
        mplew.write(ldr.getLevel());
        mplew.write((int)(ldr.isOnline() ? 1 : 0));
        mplew.writeInt(ldr.getCurrentRep());
        mplew.writeInt(ldr.getTotalRep());
        mplew.writeInt(ldr.getTotalRep());
        mplew.writeInt(ldr.getTotalRep());
        mplew.writeLong((long)Math.max(ldr.getChannel(), 0));
        mplew.writeMapleAsciiString(ldr.getName());
    }
    
    public static byte[] getFamilyPedigree(final MapleCharacter chr) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.FAMILY_CHART_RESULT.getValue());
        mplew.writeInt(chr.getId());
        final MapleFamily family = Family.getFamily(chr.getFamilyId());
        int descendants = 2;
        int gens = 0;
        int generations = 0;
        if (family == null) {
            mplew.writeInt(2);
            addFamilyCharInfo(new MapleFamilyCharacter(chr, 0, 0, 0, 0), mplew);
        }
        else {
            mplew.writeInt(family.getMFC(chr.getId()).getPedigree().size() + 1);
            addFamilyCharInfo(family.getMFC(family.getLeaderId()), mplew);
            if (chr.getSeniorId() > 0) {
                final MapleFamilyCharacter senior = family.getMFC(chr.getSeniorId());
                if (senior.getSeniorId() > 0) {
                    addFamilyCharInfo(family.getMFC(senior.getSeniorId()), mplew);
                }
                addFamilyCharInfo(senior, mplew);
            }
        }
        addFamilyCharInfo((chr.getMFC() == null) ? new MapleFamilyCharacter(chr, 0, 0, 0, 0) : chr.getMFC(), mplew);
        if (family != null) {
            if (chr.getSeniorId() > 0) {
                final MapleFamilyCharacter senior = family.getMFC(chr.getSeniorId());
                if (senior != null) {
                    if (senior.getJunior1() > 0 && senior.getJunior1() != chr.getId()) {
                        addFamilyCharInfo(family.getMFC(senior.getJunior1()), mplew);
                    }
                    else if (senior.getJunior2() > 0 && senior.getJunior2() != chr.getId()) {
                        addFamilyCharInfo(family.getMFC(senior.getJunior2()), mplew);
                    }
                }
            }
            if (chr.getJunior1() > 0) {
                addFamilyCharInfo(family.getMFC(chr.getJunior1()), mplew);
            }
            if (chr.getJunior2() > 0) {
                addFamilyCharInfo(family.getMFC(chr.getJunior2()), mplew);
            }
            if (chr.getJunior1() > 0) {
                final MapleFamilyCharacter junior = family.getMFC(chr.getJunior1());
                if (junior.getJunior1() > 0) {
                    ++descendants;
                    addFamilyCharInfo(family.getMFC(junior.getJunior1()), mplew);
                }
                if (junior.getJunior2() > 0) {
                    ++descendants;
                    addFamilyCharInfo(family.getMFC(junior.getJunior2()), mplew);
                }
            }
            if (chr.getJunior2() > 0) {
                final MapleFamilyCharacter junior = family.getMFC(chr.getJunior2());
                if (junior.getJunior1() > 0) {
                    ++descendants;
                    addFamilyCharInfo(family.getMFC(junior.getJunior1()), mplew);
                }
                if (junior.getJunior2() > 0) {
                    ++descendants;
                    addFamilyCharInfo(family.getMFC(junior.getJunior2()), mplew);
                }
            }
            gens = family.getGens();
            generations = family.getMemberSize();
        }
        mplew.writeLong((long)descendants);
        mplew.writeInt(gens);
        mplew.writeInt(-1);
        mplew.writeInt(generations);
        if (family != null) {
            if (chr.getJunior1() > 0) {
                final MapleFamilyCharacter junior = family.getMFC(chr.getJunior1());
                if (junior.getJunior1() > 0) {
                    mplew.writeInt(junior.getJunior1());
                    mplew.writeInt(family.getMFC(junior.getJunior1()).getDescendants());
                }
                if (junior.getJunior2() > 0) {
                    mplew.writeInt(junior.getJunior2());
                    mplew.writeInt(family.getMFC(junior.getJunior2()).getDescendants());
                }
            }
            if (chr.getJunior2() > 0) {
                final MapleFamilyCharacter junior = family.getMFC(chr.getJunior2());
                if (junior.getJunior1() > 0) {
                    mplew.writeInt(junior.getJunior1());
                    mplew.writeInt(family.getMFC(junior.getJunior1()).getDescendants());
                }
                if (junior.getJunior2() > 0) {
                    mplew.writeInt(junior.getJunior2());
                    mplew.writeInt(family.getMFC(junior.getJunior2()).getDescendants());
                }
            }
        }
        final List<Pair<Integer, Integer>> b = chr.usedBuffs();
        mplew.writeInt(b.size());
        for (final Pair<Integer, Integer> ii : b) {
            mplew.writeInt((int)Integer.valueOf(ii.getLeft()));
            mplew.writeInt((int)Integer.valueOf(ii.getRight()));
        }
        mplew.writeShort(2);
        return mplew.getPacket();
    }
    
    public static byte[] sendFamilyInvite(final int cid, final int otherLevel, final int otherJob, final String inviter) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.FAMILY_JOIN_REQUEST.getValue());
        mplew.writeInt(cid);
        mplew.writeMapleAsciiString(inviter);
        return mplew.getPacket();
    }
    
    public static byte[] sendFamilyMessage(final int type, final int mesos) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(6);
        mplew.writeShort((int)SendPacketOpcode.FAMILY_RESULT.getValue());
        mplew.writeInt(type);
        mplew.writeInt(mesos);
        return mplew.getPacket();
    }
    
    public static byte[] getSeniorMessage(final String name) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.FAMILY_JOIN_ACCEPTED.getValue());
        mplew.writeMapleAsciiString(name);
        return mplew.getPacket();
    }
    
    public static byte[] sendFamilyJoinResponse(final boolean accepted, final String added) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.FAMILY_JOIN_REQUEST_RESULT.getValue());
        mplew.write((int)(accepted ? 1 : 0));
        mplew.writeMapleAsciiString(added);
        return mplew.getPacket();
    }
    
    public static byte[] familyBuff(final int type, final int buffnr, final int amount, final int time) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.FAMILY_SET_PRIVILEGE.getValue());
        mplew.write(type);
        if (type >= 2 && type <= 4) {
            mplew.writeInt(buffnr);
            mplew.writeInt((type == 3) ? 0 : amount);
            mplew.writeInt((type == 2) ? 0 : amount);
            mplew.write(0);
            mplew.writeInt(time);
        }
        return mplew.getPacket();
    }
    
    public static byte[] cancelFamilyBuff() {
        return familyBuff(0, 0, 0, 0);
    }
    
    public static byte[] familyLoggedIn(final boolean online, final String name) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.FAMILY_NOTIFY_LOGIN_OR_LOGOUT.getValue());
        mplew.write((int)(online ? 1 : 0));
        mplew.writeMapleAsciiString(name);
        return mplew.getPacket();
    }
    
    public static byte[] familySummonRequest(final String name, final String mapname) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.FAMILY_SUMMON_REQUEST.getValue());
        mplew.writeMapleAsciiString(name);
        mplew.writeMapleAsciiString(mapname);
        return mplew.getPacket();
    }
}
