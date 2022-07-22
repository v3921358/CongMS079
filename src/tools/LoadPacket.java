package tools;

import java.io.InputStreamReader;
import tools.data.MaplePacketLittleEndianWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;
import java.util.Properties;

public class LoadPacket
{
    public static byte[] getPacket() {
        final Properties packetProps = new Properties();
        try {
            final InputStreamReader is = new FileReader("test.txt");
            packetProps.load((Reader)is);
            is.close();
        }
        catch (IOException ex) {
            System.err.println("test.txt");
        }
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.write(HexTool.getByteArrayFromHexString(packetProps.getProperty("packet")));
        return mplew.getPacket();
    }
}
