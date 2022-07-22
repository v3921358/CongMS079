package tools.packet;

import tools.MaplePacketCreator;
import handling.SendPacketOpcode;
import tools.data.MaplePacketLittleEndianWriter;

public class UIPacket
{
    public static final byte[] EarnTitleMsg(final String msg) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.EARN_TITLE_MSG.getValue());
        mplew.writeMapleAsciiString(msg);
        return mplew.getPacket();
    }
    
    public static byte[] getSPMsg(final byte sp, final short job) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SHOW_STATUS_INFO.getValue());
        mplew.write(3);
        mplew.writeShort((int)job);
        mplew.write(sp);
        return mplew.getPacket();
    }
    
    public static byte[] getGPMsg(final int itemid) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SHOW_STATUS_INFO.getValue());
        mplew.write(6);
        mplew.writeInt(itemid);
        return mplew.getPacket();
    }
    
    public static byte[] getTopMsg(final String msg) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SCRIPT_PROGRESS_MESSAGE.getValue());
        mplew.writeMapleAsciiString(msg);
        return mplew.getPacket();
    }
    
    public static byte[] getTopMsg1(final String msg) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.TOP_MSG.getValue());
        mplew.writeMapleAsciiString(msg);
        return mplew.getPacket();
    }
    
    public static byte[] getStatusMsg(final int itemid) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SHOW_STATUS_INFO.getValue());
        mplew.write(7);
        mplew.writeInt(itemid);
        return mplew.getPacket();
    }
    
    public static final byte[] MapEff(final String path) {
        return MaplePacketCreator.environmentChange(path, 3);
    }
    
    public static final byte[] MapNameDisplay(final int mapid) {
        return MaplePacketCreator.environmentChange("maplemap/enter/" + mapid, 3);
    }
    
    public static final byte[] Aran_Start() {
        return MaplePacketCreator.environmentChange("Aran/balloon", 4);
    }
    
    public static final byte[] AranTutInstructionalBalloon(final String data) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SHOW_ITEM_GAIN_INCHAT.getValue());
        mplew.write(23);
        mplew.writeMapleAsciiString(data);
        mplew.writeInt(1);
        return mplew.getPacket();
    }
    
    public static final byte[] ShowWZEffect(final String data) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SHOW_ITEM_GAIN_INCHAT.getValue());
        mplew.write(20);
        mplew.writeMapleAsciiString(data);
        return mplew.getPacket();
    }
    
    public static byte[] summonHelper(final boolean summon) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SUMMON_HINT.getValue());
        mplew.write((int)(summon ? 1 : 0));
        return mplew.getPacket();
    }
    
    public static byte[] summonMessage(final int type) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SUMMON_HINT_MSG.getValue());
        mplew.write(1);
        mplew.writeInt(type);
        mplew.writeInt(7000);
        return mplew.getPacket();
    }
    
    public static byte[] summonMessage(final String message) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SUMMON_HINT_MSG.getValue());
        mplew.write(0);
        mplew.writeMapleAsciiString(message);
        mplew.writeInt(200);
        mplew.writeShort(0);
        mplew.writeInt(10000);
        return mplew.getPacket();
    }
    
    public static byte[] IntroLock(final boolean enable) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CYGNUS_INTRO_LOCK.getValue());
        mplew.write((int)(enable ? 1 : 0));
        return mplew.getPacket();
    }
    
    public static byte[] IntroDisableUI(final boolean enable) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CYGNUS_INTRO_DISABLE_UI.getValue());
        mplew.write((int)(enable ? 1 : 0));
        return mplew.getPacket();
    }
    
    public static byte[] fishingUpdate(final byte type, final int id) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.FISHING_BOARD_UPDATE.getValue());
        mplew.write(type);
        mplew.writeInt(id);
        return mplew.getPacket();
    }
    
    public static byte[] fishingCaught(final int chrid) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.FISHING_CAUGHT.getValue());
        mplew.writeInt(chrid);
        return mplew.getPacket();
    }
}
