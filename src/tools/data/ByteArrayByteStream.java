package tools.data;

import tools.HexTool;
import java.io.IOException;

public class ByteArrayByteStream
{
    private int pos;
    private long bytesRead;
    private final byte[] arr;
    
    public ByteArrayByteStream(final byte[] arr) {
        this.pos = 0;
        this.bytesRead = 0L;
        this.arr = arr;
    }
    
    public long getPosition() {
        return (long)this.pos;
    }
    
    public void seek(final long offset) throws IOException {
        this.pos = (int)offset;
    }
    
    public long getBytesRead() {
        return this.bytesRead;
    }
    
    public int readByte() {
        ++this.bytesRead;
        return this.arr[this.pos++] & 0xFF;
    }
    
    public void unReadByte() {
        --this.bytesRead;
    }
    
    public int readLastByte() {
        return this.arr[this.pos] & 0xFF;
    }
    
    public int[] readLastBytes(int bytes) {
        while (this.pos - bytes < 1) {
            --bytes;
        }
        final int[] a = null;
        for (int b = 0; bytes > 0; --bytes, ++b) {
            final int[] array = a;
            final int n = b;
            array[n] += this.arr[this.pos - bytes];
        }
        return a;
    }
    
    @Override
    public String toString() {
        return this.toString(false);
    }
    
    public String toString(final boolean b) {
        String nows = "";
        if (this.arr.length - this.pos > 0) {
            final byte[] now = new byte[this.arr.length - this.pos];
            System.arraycopy((Object)this.arr, this.pos, (Object)now, 0, this.arr.length - this.pos);
            nows = HexTool.toString(now);
        }
        if (b) {
            return "全部: " + HexTool.toString(this.arr) + " 現在: " + nows;
        }
        return "数据: " + nows;
    }
    
    public long available() {
        return (long)(this.arr.length - this.pos);
    }
}
