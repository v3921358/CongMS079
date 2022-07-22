package tools.packet;

import handling.SendPacketOpcode;
import tools.data.MaplePacketLittleEndianWriter;

public class MonsterBookPacket
{
    public static byte[] addCard(final boolean full, final int cardid, final int level) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MONSTERBOOK_ADD.getValue());
        if (!full) {
            mplew.write(1);
            mplew.writeInt(cardid);
            mplew.writeInt(level);
        }
        else {
            mplew.write(0);
        }
        return mplew.getPacket();
    }
    
    public static byte[] showGainCard(final int itemid) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SHOW_STATUS_INFO.getValue());
        mplew.write(0);
        mplew.write(2);
        mplew.writeInt(itemid);
        return mplew.getPacket();
    }
    
    public static byte[] showForeginCardEffect(final int id) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SHOW_FOREIGN_EFFECT.getValue());
        mplew.writeInt(id);
        mplew.write(15);
        return mplew.getPacket();
    }
    
    public static byte[] changeCover(final int cardid) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MONSTERBOOK_CHANGE_COVER.getValue());
        mplew.writeInt(cardid);
        return mplew.getPacket();
    }
}
