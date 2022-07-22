package gui;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PipedOutputStream;

public class LoopedStreams
{
    private PipedOutputStream pipedOS;
    private boolean keepRunning;
    private ByteArrayOutputStream byteArrayOS;
    private PipedInputStream pipedIS;
    
    public LoopedStreams() throws IOException {
        this.pipedOS = new PipedOutputStream();
        this.keepRunning = true;
        this.byteArrayOS = new ByteArrayOutputStream() {
            @Override
            public void close() {
                keepRunning = false;
                try {
                    super.close();
                    pipedOS.close();
                }
                catch (IOException e) {
                    System.exit(1);
                }
            }
        };
        this.pipedIS = new PipedInputStream() {
            @Override
            public void close() {
                keepRunning = false;
                try {
                    super.close();
                }
                catch (IOException e) {
                    System.exit(1);
                }
            }
        };
        this.pipedOS.connect(this.pipedIS);
        this.startByteArrayReaderThread();
    }
    
    public InputStream getInputStream() {
        return this.pipedIS;
    }
    
    public OutputStream getOutputStream() {
        return this.byteArrayOS;
    }
    
    private void startByteArrayReaderThread() {
        new Thread((Runnable)new Runnable() {
            @Override
            public void run() {
                while (keepRunning) {
                    if (byteArrayOS.size() > 0) {
                        byte[] buffer = null;
                        synchronized (byteArrayOS) {
                            buffer = byteArrayOS.toByteArray();
                            byteArrayOS.reset();
                        }
                        try {
                            pipedOS.write(buffer, 0, buffer.length);
                        }
                        catch (IOException e) {
                            System.exit(1);
                        }
                    }
                    else {
                        try {
                            Thread.sleep(1000L);
                        }
                        catch (InterruptedException ex) {}
                    }
                }
            }
        }).start();
    }
}
