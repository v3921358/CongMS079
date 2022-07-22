package tools.data;

import constants.ServerConstants;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.awt.Rectangle;
import java.awt.Point;
import tools.HexTool;
import java.nio.charset.Charset;
import java.io.ByteArrayOutputStream;

public class MaplePacketLittleEndianWriter
{
    private final ByteArrayOutputStream baos;
    private static final Charset ASCII;
    
    public MaplePacketLittleEndianWriter() {
        this(32);
    }
    
    public MaplePacketLittleEndianWriter(final int size) {
        this.baos = new ByteArrayOutputStream(size);
    }
    
    private void baosWrite(final byte b) {
        this.baos.write((int)b);
    }
    
    public final byte[] getPacket() {
        return this.baos.toByteArray();
    }
    
    @Override
    public final String toString() {
        return HexTool.toString(this.baos.toByteArray());
    }
    
    public final void writeZeroBytes(final int i) {
        for (int x = 0; x < i; ++x) {
            this.baosWrite((byte)0);
        }
    }
    
    public final void write(final byte[] b) {
        for (int x = 0; x < b.length; ++x) {
            this.baosWrite(b[x]);
        }
    }
    
    public final void write(final byte b) {
        this.baosWrite(b);
    }
    
    public final void write(final int b) {
        this.baosWrite((byte)b);
    }
    
    public final void write(final boolean b) {
        this.baosWrite((byte)(byte)(b ? 1 : 0));
    }
    
    public final void writeShort(final int i) {
        this.baosWrite((byte)(i & 0xFF));
        this.baosWrite((byte)(i >>> 8 & 0xFF));
    }
    
    public final void writeInt(final int i) {
        this.baosWrite((byte)(i & 0xFF));
        this.baosWrite((byte)(i >>> 8 & 0xFF));
        this.baosWrite((byte)(i >>> 16 & 0xFF));
        this.baosWrite((byte)(i >>> 24 & 0xFF));
    }
    
    public void writeReversedInt(final long l) {
        this.baosWrite((byte)(int)(l >>> 32 & 0xFFL));
        this.baosWrite((byte)(int)(l >>> 40 & 0xFFL));
        this.baosWrite((byte)(int)(l >>> 48 & 0xFFL));
        this.baosWrite((byte)(int)(l >>> 56 & 0xFFL));
    }
    
    public final void writeAsciiString(final String s) {
        this.write(s.getBytes(MaplePacketLittleEndianWriter.ASCII));
    }
    
    public final void writeAsciiString(String s, final int max) {
        if (s.getBytes(MaplePacketLittleEndianWriter.ASCII).length > max) {
            s = s.substring(0, max);
        }
        this.write(s.getBytes(MaplePacketLittleEndianWriter.ASCII));
        for (int i = s.getBytes(MaplePacketLittleEndianWriter.ASCII).length; i < max; ++i) {
            this.baosWrite((byte)0);
        }
    }
    
    public final void writeMapleAsciiString(final String s) {
        this.writeShort((int)(short)s.getBytes(MaplePacketLittleEndianWriter.ASCII).length);
        this.writeAsciiString(s);
    }
    
    public final void writeMapleAsciiString(String s, final int max) {
        if (s.getBytes(MaplePacketLittleEndianWriter.ASCII).length > max) {
            s = s.substring(0, max);
        }
        this.writeShort((int)(short)s.getBytes(MaplePacketLittleEndianWriter.ASCII).length);
        this.write(s.getBytes(MaplePacketLittleEndianWriter.ASCII));
        for (int i = s.getBytes(MaplePacketLittleEndianWriter.ASCII).length; i < max; ++i) {
            this.baosWrite((byte)0);
        }
    }
    
    public final void writeNullTerminatedCharString(final String s) {
        final byte[] bytes;
        final byte[] strBytes = bytes = s.getBytes(MaplePacketLittleEndianWriter.ASCII);
        for (final byte b : bytes) {
            this.baosWrite(b);
            this.baosWrite((byte)0);
        }
    }
    
    public final void writeBoolean(final boolean b) {
        this.baosWrite((byte)(byte)(b ? 1 : 0));
    }
    
    public final void writePos(final Point s) {
        this.writeShort(s.x);
        this.writeShort(s.y);
    }
    
    public final void writeRect(final Rectangle s) {
        this.writeInt(s.x);
        this.writeInt(s.y);
        this.writeInt(s.x + s.width);
        this.writeInt(s.y + s.height);
    }
    
    public final void writeLong(final long l) {
        this.baosWrite((byte)(int)(l & 0xFFL));
        this.baosWrite((byte)(int)(l >>> 8 & 0xFFL));
        this.baosWrite((byte)(int)(l >>> 16 & 0xFFL));
        this.baosWrite((byte)(int)(l >>> 24 & 0xFFL));
        this.baosWrite((byte)(int)(l >>> 32 & 0xFFL));
        this.baosWrite((byte)(int)(l >>> 40 & 0xFFL));
        this.baosWrite((byte)(int)(l >>> 48 & 0xFFL));
        this.baosWrite((byte)(int)(l >>> 56 & 0xFFL));
    }
    
    public final void writeReversedLong(final long l) {
        this.baosWrite((byte)(int)(l >>> 32 & 0xFFL));
        this.baosWrite((byte)(int)(l >>> 40 & 0xFFL));
        this.baosWrite((byte)(int)(l >>> 48 & 0xFFL));
        this.baosWrite((byte)(int)(l >>> 56 & 0xFFL));
        this.baosWrite((byte)(int)(l & 0xFFL));
        this.baosWrite((byte)(int)(l >>> 8 & 0xFFL));
        this.baosWrite((byte)(int)(l >>> 16 & 0xFFL));
        this.baosWrite((byte)(int)(l >>> 24 & 0xFFL));
    }
    
    public final void writeFile(final File file) {
        try {
            final InputStream is = new FileInputStream(file);
            final long length = file.length();
            if (length > 2147483647L) {
                System.err.println("檔案太大");
                return;
            }
            final byte[] bytes = new byte[(int)length];
            int offset = 0;
            for (int numRead = 0; offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0; offset += numRead) {}
            is.close();
            if (offset < bytes.length) {
                System.err.println("無法完整讀取檔案:" + file.getName());
                return;
            }
            this.writeInt(bytes.length);
            this.write(bytes);
        }
        catch (IOException e) {
            System.err.println("讀取檔案失敗:" + (Object)e);
        }
    }
    
    static {
        ASCII = Charset.forName(ServerConstants.MAPLE_TYPE.getANSI());
    }
}
