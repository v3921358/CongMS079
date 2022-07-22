package handling.mina;

import java.util.concurrent.locks.Lock;
import tools.MapleAESOFB;
import tools.MapleCustomEncryption;
import tools.FilePrinter;
import tools.FileoutputUtil;
import tools.HexTool;
import tools.StringUtil;
import constants.ServerConfig;
import handling.SendPacketOpcode;
import io.netty.util.AttributeKey;
import client.MapleClient;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MaplePacketEncoder extends MessageToByteEncoder<Object>
{
    protected void encode(final ChannelHandlerContext chc, final Object message, final ByteBuf buffer) throws Exception {
        final MapleClient client = (MapleClient)chc.channel().attr((AttributeKey)MapleClient.CLIENT_KEY).get();
        if (client != null) {
            final MapleAESOFB send_crypto = client.getSendCrypto();
            final byte[] input = (byte[])(byte[])message;
            final int pHeader = (input[0] & 0xFF) + ((input[1] & 0xFF) << 8);
            final String op = SendPacketOpcode.nameOf(pHeader);
            if ((ServerConfig.LOG_PACKETS || ServerConfig.CHRLOG_PACKETS) && !SendPacketOpcode.isSpamHeader(SendPacketOpcode.valueOf(op))) {
                final int packetLen = input.length;
                String pHeaderStr = Integer.toHexString(pHeader).toUpperCase();
                pHeaderStr = "0x" + StringUtil.getLeftPaddedStr(pHeaderStr, '0', 4);
                String tab = "";
                for (int i = 4; i > op.length() / 8; --i) {
                    tab += "\t";
                }
                final String t = (packetLen >= 10) ? ((packetLen >= 100) ? ((packetLen >= 1000) ? "" : " ") : "  ") : "   ";
                final StringBuilder sb = new StringBuilder("[發送]\t" + op + tab + "\t包頭:" + pHeaderStr + t + "[" + packetLen + "字元]");
                if (ServerConfig.LOG_PACKETS) {
                    System.out.println(sb.toString());
                }
                sb.append("\r\n\r\n").append(HexTool.toString((byte[])(byte[])message)).append("\r\n").append(HexTool.toStringFromAscii((byte[])(byte[])message));
                if (ServerConfig.LOG_PACKETS) {
                    FileoutputUtil.log("logs\\数据包收發\\Log.txt", "\r\n\r\n" + sb.toString() + "\r\n\r\n");
                }
                else if (ServerConfig.CHRLOG_PACKETS && client.getPlayer() != null) {
                    FilePrinter.print("封包記錄/" + client.getPlayer().getName() + ".txt", "\r\n\r\n" + sb.toString() + "\r\n\r\n");
                }
            }
            final byte[] unencrypted = new byte[input.length];
            System.arraycopy((Object)input, 0, (Object)unencrypted, 0, input.length);
            final byte[] ret = new byte[unencrypted.length + 4];
            final Lock mutex = client.getLock();
            mutex.lock();
            try {
                final byte[] header = send_crypto.getPacketHeader(unencrypted.length);
                MapleCustomEncryption.encryptData(unencrypted);
                send_crypto.crypt(unencrypted);
                System.arraycopy((Object)header, 0, (Object)ret, 0, 4);
                System.arraycopy((Object)unencrypted, 0, (Object)ret, 4, unencrypted.length);
                buffer.writeBytes(ret);
            }
            finally {
                mutex.unlock();
            }
        }
        else {
            final byte[] input2 = (byte[])(byte[])message;
            buffer.writeBytes(input2);
        }
    }
}
