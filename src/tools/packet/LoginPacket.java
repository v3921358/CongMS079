package tools.packet;

import java.util.Iterator;
import client.MapleCharacter;
import java.util.List;
import java.util.Set;
import handling.channel.ChannelServer;
import constants.WorldConstants;
import java.util.Map;
import client.MapleClient;
import tools.HexTool;
import handling.SendPacketOpcode;
import tools.data.MaplePacketLittleEndianWriter;

public class LoginPacket
{
    public static final byte[] getHello(final short mapleVersion, final byte[] sendIv, final byte[] recvIv) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(16);
        mplew.writeShort(14);
        mplew.writeShort((int)mapleVersion);
        mplew.writeMapleAsciiString("1");
        mplew.write(recvIv);
        mplew.write(sendIv);
        mplew.write(4);
        return mplew.getPacket();
    }
    
    public static final byte[] getPing() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(16);
        mplew.writeShort((int)SendPacketOpcode.PING.getValue());
        return mplew.getPacket();
    }
    
    public static final byte[] StrangeDATA() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(16);
        mplew.writeShort(18);
        mplew.writeMapleAsciiString("30819F300D06092A864886F70D010101050003818D0030818902818100994F4E66B003A7843C944E67BE4375203DAA203C676908E59839C9BADE95F53E848AAFE61DB9C09E80F48675CA2696F4E897B7F18CCB6398D221C4EC5823D11CA1FB9764A78F84711B8B6FCA9F01B171A51EC66C02CDA9308887CEE8E59C4FF0B146BF71F697EB11EDCEBFCE02FB0101A7076A3FEB64F6F6022C8417EB6B87270203010001");
        return mplew.getPacket();
    }
    
    public static final byte[] getLoginFailed(final int reason) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(16);
        mplew.writeShort((int)SendPacketOpcode.LOGIN_STATUS.getValue());
        mplew.write(reason);
        mplew.writeShort(0);
        return mplew.getPacket();
    }
    
    public static final byte[] getPermBan(final byte reason) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(16);
        mplew.writeShort((int)SendPacketOpcode.LOGIN_STATUS.getValue());
        mplew.writeShort(2);
        mplew.write(0);
        mplew.write(reason);
        mplew.write(HexTool.getByteArrayFromHexString("01 01 01 01 00"));
        return mplew.getPacket();
    }
    
    public static final byte[] getTempBan(final long timestampTill, final byte reason) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(17);
        mplew.writeShort((int)SendPacketOpcode.LOGIN_STATUS.getValue());
        mplew.write(2);
        mplew.write(HexTool.getByteArrayFromHexString("00 00 00 00 00"));
        mplew.write(reason);
        mplew.writeLong(timestampTill);
        return mplew.getPacket();
    }
    
    public static final byte[] getGenderChanged(final MapleClient client) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.GENDER_SET.getValue());
        mplew.write(0);
        mplew.writeMapleAsciiString(client.getAccountName());
        mplew.writeMapleAsciiString(String.valueOf(client.getAccID()));
        return mplew.getPacket();
    }
    
    public static final byte[] getGenderNeeded(final MapleClient client) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CHOOSE_GENDER.getValue());
        mplew.writeMapleAsciiString(client.getAccountName());
        return mplew.getPacket();
    }
    
    public static final byte[] getAuthSuccessRequest(final MapleClient client) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.LOGIN_STATUS.getValue());
        mplew.write(0);
        mplew.writeInt(client.getAccID());
        mplew.write(client.getGender());
        mplew.write(0);
        mplew.write(0);
        mplew.writeMapleAsciiString(client.getAccountName());
        mplew.write(HexTool.getByteArrayFromHexString("00 00 00 03 01 00 00 00 E2 ED A3 7A FA C9 01"));
        mplew.write(0);
        mplew.writeLong(0L);
        mplew.writeShort(0);
        mplew.write(0);
        mplew.writeMapleAsciiString(String.valueOf(client.getAccID()));
        mplew.writeMapleAsciiString(client.getAccountName());
        mplew.write(1);
        return mplew.getPacket();
    }
    
    public static final byte[] deleteCharResponse(final int cid, final int state) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.DELETE_CHAR_RESPONSE.getValue());
        mplew.writeInt(cid);
        mplew.write(state);
        return mplew.getPacket();
    }
    
    public static final byte[] secondPwError(final byte mode) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(3);
        mplew.writeShort((int)SendPacketOpcode.SECONDPW_ERROR.getValue());
        mplew.write(mode);
        return mplew.getPacket();
    }
    
    public static final byte[] getServerList(final int serverId, final String serverName, final Map<Integer, Integer> channelLoad, final int a) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SERVERLIST.getValue());
        mplew.write(serverId);
        mplew.writeMapleAsciiString(serverName);
        mplew.write(a);
        mplew.writeMapleAsciiString(WorldConstants.WORLD_TIP);
        mplew.writeShort(100);
        mplew.writeShort(100);
        int lastChannel = 1;
        final Set<Integer> channels = ChannelServer.getAllChannels();
        for (int i = WorldConstants.CHANNEL_COUNT; i > 0; --i) {
            if (channels.contains((Object)Integer.valueOf(i))) {
                lastChannel = i;
                break;
            }
        }
        mplew.write(lastChannel);
        mplew.writeInt(500);
        for (int j = 1; j <= lastChannel; ++j) {
            int load;
            if (channels.contains((Object)Integer.valueOf(j))) {
                load = (int)Integer.valueOf(channelLoad.get((Object)Integer.valueOf(j)));
            }
            else {
                load = 1200;
            }
            mplew.writeMapleAsciiString(serverName + "-" + j);
            mplew.writeInt(Math.max(load * 55 / WorldConstants.MAX_CHAR_VIEW, 1));
            mplew.write(serverId);
            mplew.writeShort(j - 1);
        }
        mplew.writeShort(0);
        return mplew.getPacket();
    }
    
    public static final byte[] getEndOfServerList() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SERVERLIST.getValue());
        mplew.write(255);
        return mplew.getPacket();
    }
    
    public static final byte[] getServerStatus(final int status) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SERVERSTATUS.getValue());
        mplew.writeShort(status);
        return mplew.getPacket();
    }
    
    public static final byte[] getCharList(final boolean secondpw, final List<MapleCharacter> chars, final int charslots) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CHARLIST.getValue());
        mplew.write(0);
        mplew.writeInt(0);
        mplew.write(chars.size());
        for (final MapleCharacter chr : chars) {
            addCharEntry(mplew, chr, !chr.isGM() && chr.getLevel() >= 10);
        }
        mplew.writeShort(3);
        mplew.writeInt(charslots);
        return mplew.getPacket();
    }
    
    public static final byte[] addNewCharEntry(final MapleCharacter chr, final boolean worked) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.ADD_NEW_CHAR_ENTRY.getValue());
        mplew.write((int)(worked ? 0 : 1));
        addCharEntry(mplew, chr, false);
        return mplew.getPacket();
    }
    
    public static final byte[] charNameResponse(final String charname, final boolean nameUsed) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CHAR_NAME_RESPONSE.getValue());
        mplew.writeMapleAsciiString(charname);
        mplew.write((int)(nameUsed ? 1 : 0));
        return mplew.getPacket();
    }
    
    private static final void addCharEntry(final MaplePacketLittleEndianWriter mplew, final MapleCharacter chr, final boolean ranking) {
        PacketHelper.addCharStats(mplew, chr);
        PacketHelper.addCharLook(mplew, chr, true);
        mplew.write(0);
        if (chr.getJob() == 900) {
            mplew.write(2);
        }
    }
    
    public static final byte[] licenseResult() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.LICENSE_RESULT.getValue());
        mplew.write(1);
        return mplew.getPacket();
    }
}
