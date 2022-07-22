package tools.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamByteStream
{
    private final InputStream is;
    private long read;
    
    public InputStreamByteStream(final InputStream is) {
        this.read = 0L;
        this.is = is;
    }
    
    public final int readByte() {
        try {
            final int temp = this.is.read();
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
    
    public final long getBytesRead() {
        return this.read;
    }
    
    public final long available() {
        try {
            return (long)this.is.available();
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
        while ((nRead = this.is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
}
