package tools.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessByteStream
{
    private final RandomAccessFile raf;
    private long read;
    
    public RandomAccessByteStream(final RandomAccessFile raf) {
        this.read = 0L;
        this.raf = raf;
    }
    
    public final int readByte() {
        try {
            final int temp = this.raf.read();
            if (temp == -1) {
                throw new RuntimeException("EOF");
            }
            ++this.read;
            return temp;
        }
        catch (IOException e) {
            throw new RuntimeException((Throwable)e);
        }
    }
    
    public final void seek(final long offset) throws IOException {
        this.raf.seek(offset);
    }
    
    public final long getPosition() throws IOException {
        return this.raf.getFilePointer();
    }
    
    public final long getBytesRead() {
        return this.read;
    }
    
    public final long available() {
        try {
            return this.raf.length() - this.raf.getFilePointer();
        }
        catch (IOException e) {
            System.err.println("ERROR" + (Object)e);
            return 0L;
        }
    }
    
    public final String toString(final boolean b) {
        return this.toString();
    }
    
    public final byte[] toByteArray() throws IOException {
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        final byte[] data = new byte[16384];
        int nRead;
        while ((nRead = this.raf.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
}
