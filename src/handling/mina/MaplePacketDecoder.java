package handling.mina;

import tools.FilePrinter;
import tools.FileoutputUtil;
import tools.HexTool;
import constants.ServerConfig;
import handling.RecvPacketOpcode;
import tools.data.LittleEndianAccessor;
import tools.data.ByteArrayByteStream;
import tools.MapleCustomEncryption;
import tools.MapleAESOFB;
import client.MapleClient;
import java.util.List;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MaplePacketDecoder extends ByteToMessageDecoder
{
    public static final AttributeKey<DecoderState> DECODER_STATE_KEY;
    
    protected void decode(final ChannelHandlerContext chc, final ByteBuf in, final List<Object> message) throws Exception {
        final MapleClient client = (MapleClient)chc.channel().attr((AttributeKey)MapleClient.CLIENT_KEY).get();
        final DecoderState decoderState = (DecoderState)chc.channel().attr((AttributeKey)MaplePacketDecoder.DECODER_STATE_KEY).get();
        if (in.readableBytes() >= 4 && decoderState.packetlength == -1) {
            final int packetHeader = in.readInt();
            if (!client.getReceiveCrypto().checkPacket(packetHeader)) {
                chc.channel().close();
                return;
            }
            decoderState.packetlength = MapleAESOFB.getPacketLength(packetHeader);
        }
        else if (in.readableBytes() < 4 && decoderState.packetlength == -1) {
            return;
        }
        if (in.readableBytes() >= decoderState.packetlength) {
            final byte[] decryptedPacket = new byte[decoderState.packetlength];
            in.readBytes(decryptedPacket);
            decoderState.packetlength = -1;
            client.getReceiveCrypto().crypt(decryptedPacket);
            MapleCustomEncryption.decryptData(decryptedPacket);
            message.add(decryptedPacket);
            final int packetLen = decryptedPacket.length;
            final short pHeader = new LittleEndianAccessor(new ByteArrayByteStream(decryptedPacket)).readShort();
            final String op = RecvPacketOpcode.nameOf(pHeader);
            if ((ServerConfig.LOG_PACKETS || ServerConfig.CHRLOG_PACKETS) && !RecvPacketOpcode.isSpamHeader(RecvPacketOpcode.valueOf(op))) {
                String tab = "";
                for (int i = 4; i > op.length() / 8; --i) {
                    tab += "\t";
                }
                final String t = (packetLen >= 10) ? ((packetLen >= 100) ? ((packetLen >= 1000) ? "" : " ") : "  ") : "   ";
                final StringBuilder sb = new StringBuilder("[接收]\t" + op + tab + "\t包頭:" + HexTool.getOpcodeToString((int)pHeader) + t + "[" + packetLen + "字元]");
                if (ServerConfig.LOG_PACKETS) {
                    System.out.println(sb.toString());
                }
                sb.append("\r\n\r\n").append(HexTool.toString(decryptedPacket)).append("\r\n").append(HexTool.toStringFromAscii(decryptedPacket));
                if (ServerConfig.LOG_PACKETS) {
                    FileoutputUtil.log("logs\\数据包收發\\Log.txt", "\r\n\r\n" + sb.toString() + "\r\n\r\n");
                }
                else if (ServerConfig.CHRLOG_PACKETS && client.getPlayer() != null) {
                    FilePrinter.print("封包記錄/" + client.getPlayer().getName() + ".txt", "\r\n\r\n" + sb.toString() + "\r\n\r\n");
                }
            }
        }
    }
    
    static {
        DECODER_STATE_KEY = AttributeKey.valueOf(MaplePacketDecoder.class.getName() + ".STATE");
    }
    
    public static class DecoderState
    {
        public int packetlength;
        
        public DecoderState() {
            this.packetlength = -1;
        }
    }
}
