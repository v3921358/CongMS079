package tools.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.awt.Point;
import java.io.UnsupportedEncodingException;
import constants.ServerConstants;

public class LittleEndianAccessor
{
    private final ByteArrayByteStream bs;
    
    public LittleEndianAccessor(final ByteArrayByteStream bs) {
        this.bs = bs;
    }
    
    public int readByteAsInt() {
        return this.bs.readByte();
    }
    
    public final byte readByte() {
        return (byte)this.bs.readByte();
    }
    
    public final int readInt() {
        final int byte1 = this.bs.readByte();
        final int byte2 = this.bs.readByte();
        final int byte3 = this.bs.readByte();
        final int byte4 = this.bs.readByte();
        return (byte4 << 24) + (byte3 << 16) + (byte2 << 8) + byte1;
    }
    
    public final short readShort() {
        final int byte1 = this.bs.readByte();
        final int byte2 = this.bs.readByte();
        return (short)((byte2 << 8) + byte1);
    }
    
    public final int readUShort() {
        int quest = this.readShort();
        if (quest < 0) {
            quest += 65536;
        }
        return quest;
    }
    
    public final char readChar() {
        return (char)this.readShort();
    }
    
    public final long readLong() {
        final long byte1 = (long)this.bs.readByte();
        final long byte2 = (long)this.bs.readByte();
        final long byte3 = (long)this.bs.readByte();
        final long byte4 = (long)this.bs.readByte();
        final long byte5 = (long)this.bs.readByte();
        final long byte6 = (long)this.bs.readByte();
        final long byte7 = (long)this.bs.readByte();
        final long byte8 = (long)this.bs.readByte();
        return (byte8 << 56) + (byte7 << 48) + (byte6 << 40) + (byte5 << 32) + (byte4 << 24) + (byte3 << 16) + (byte2 << 8) + byte1;
    }
    
    public final float readFloat() {
        return Float.intBitsToFloat(this.readInt());
    }
    
    public final double readDouble() {
        return Double.longBitsToDouble(this.readLong());
    }
    
    public final String readAsciiString(final int n) {
        try {
            final byte[] ret = new byte[n];
            for (int x = 0; x < n; ++x) {
                ret[x] = this.readByte();
            }
            return new String(ret, ServerConstants.MAPLE_TYPE.getANSI());
        }
        catch (UnsupportedEncodingException ex) {
            System.err.println((Object)ex);
            return "";
        }
    }
    
    public final long getBytesRead() {
        return this.bs.getBytesRead();
    }
    
    public final String readMapleAsciiString() {
        return this.readAsciiString((int)this.readShort());
    }
    
    public final Point readPos() {
        final int x = this.readShort();
        final int y = this.readShort();
        return new Point(x, y);
    }
    
    public final byte[] read(final int num) {
        final byte[] ret = new byte[num];
        for (int x = 0; x < num; ++x) {
            ret[x] = this.readByte();
        }
        return ret;
    }
    
    public final void unReadByte() {
        this.bs.unReadByte();
    }
    
    public final void unReadInt() {
        for (int byte_ = 0; byte_ < 4; ++byte_) {
            this.bs.unReadByte();
        }
    }
    
    public final void unReadShort() {
        for (int byte_ = 0; byte_ < 2; ++byte_) {
            this.bs.unReadByte();
        }
    }
    
    public final void unReadLong() {
        for (int byte_ = 0; byte_ < 8; ++byte_) {
            this.bs.unReadByte();
        }
    }
    
    public final void unReadAsciiString(final int n) {
        for (int byte_ = 0; byte_ < n; ++byte_) {
            this.bs.unReadByte();
        }
    }
    
    public final void unReadPos() {
        for (int byte_ = 0; byte_ < 4; ++byte_) {
            this.bs.unReadByte();
        }
    }
    
    public final void unRead(final int num) {
        for (int byte_ = 0; byte_ < num; ++byte_) {
            this.bs.unReadByte();
        }
    }
    
    public final byte readLastByte() {
        return (byte)this.bs.readLastByte();
    }
    
    public final int readLastInt() {
        this.unReadInt();
        final int byte1 = this.bs.readByte();
        final int byte2 = this.bs.readByte();
        final int byte3 = this.bs.readByte();
        final int byte4 = this.bs.readByte();
        return (byte4 << 24) + (byte3 << 16) + (byte2 << 8) + byte1;
    }
    
    public final short readLastShort() {
        this.unReadShort();
        final int byte1 = this.bs.readByte();
        final int byte2 = this.bs.readByte();
        return (short)((byte2 << 8) + byte1);
    }
    
    public final long readLastLong() {
        this.unReadLong();
        final int byte1 = this.bs.readByte();
        final int byte2 = this.bs.readByte();
        final int byte3 = this.bs.readByte();
        final int byte4 = this.bs.readByte();
        final long byte5 = (long)this.bs.readByte();
        final long byte6 = (long)this.bs.readByte();
        final long byte7 = (long)this.bs.readByte();
        final long byte8 = (long)this.bs.readByte();
        return (byte8 << 56) + (byte7 << 48) + (byte6 << 40) + (byte5 << 32) + (long)(byte4 << 24) + (long)(byte3 << 16) + (long)(byte2 << 8) + (long)byte1;
    }
    
    public final String readLastAsciiString(final int n) {
        try {
            for (int y = 0; y < n; ++y) {
                this.unReadByte();
            }
            final byte[] ret = new byte[n];
            for (int x = 0; x < n; ++x) {
                ret[x] = this.readByte();
            }
            return new String(ret, ServerConstants.MAPLE_TYPE.getANSI());
        }
        catch (UnsupportedEncodingException ex) {
            System.err.println((Object)ex);
            return "";
        }
    }
    
    public final Point readLastPos() {
        this.unReadInt();
        final short x = this.readShort();
        final short y = this.readShort();
        return new Point((int)x, (int)y);
    }
    
    public final byte[] readLastBytes(final int num) {
        for (int byte_ = 0; byte_ < num; ++byte_) {
            this.bs.unReadByte();
        }
        final byte[] ret = new byte[num];
        for (int x = 0; x < num; ++x) {
            ret[x] = this.readByte();
        }
        return ret;
    }
    
    public final long available() {
        return this.bs.available();
    }
    
    @Override
    public final String toString() {
        return this.bs.toString();
    }
    
    public final String toString(final boolean b) {
        return this.bs.toString(b);
    }
    
    public final void seek(final long offset) {
        try {
            this.bs.seek(offset);
        }
        catch (IOException e) {
            System.err.println("Seek failed" + (Object)e);
        }
    }
    
    public final long getPosition() {
        return this.bs.getPosition();
    }
    
    public final void skip(final int num) {
        this.seek(this.getPosition() + (long)num);
    }
    
    public final String readNullTerminatedAsciiString() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while (true) {
            final byte b = this.readByte();
            if (b == 0) {
                break;
            }
            baos.write((int)b);
        }
        final byte[] buf = baos.toByteArray();
        final char[] chrBuf = new char[buf.length];
        for (int x = 0; x < buf.length; ++x) {
            chrBuf[x] = (char)buf[x];
        }
        return String.valueOf(chrBuf);
    }
}
