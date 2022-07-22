package scripting;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import server.Randomizer;
import java.io.File;
import tools.HexTool;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import tools.Pair;

public class LieDetectorScript
{
    private static final String IMG_DIRECTORY = "脚本/验证码/";
    private static final String CAPTCHA_VERIFIER = "98818D40B83AECCFB7AFD7FD9653E1037519AC61";
    private static final String CAPTCHA_SERVER = "http://localhost/captcha.php?verify=98818D40B83AECCFB7AFD7FD9653E1037519AC61";
    
    public static Pair<String, String> getImageBytes() {
        try {
            final URL url = new URL("http://localhost/captcha.php?verify=98818D40B83AECCFB7AFD7FD9653E1037519AC61");
            final InputStream inputStream = url.openStream();
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            final byte[] buffer = new byte[1024];
            int n = 0;
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
            final String imgByte = HexTool.toString(output.toByteArray());
            return new Pair<String, String>(imgByte.substring(39, imgByte.length()), output.toString().split("CAPTCHA")[0]);
        }
        catch (IOException ex) {
            final File directory = new File("脚本/验证码/");
            if (!directory.exists()) {
                System.err.println("lieDetector folder does not exist!");
                return null;
            }
            final String[] filename = directory.list();
            String answer = filename[Randomizer.nextInt(filename.length)];
            answer = answer.substring(0, answer.length() - 4);
            try {
                return new Pair<String, String>(HexTool.toString(getBytesFromFile(new File("脚本/验证码/" + answer + ".jpg"))), answer);
            }
            catch (IOException ex2) {
                return null;
            }
        }
    }
    
    public static byte[] getBytesFromFile(final File file) throws IOException {
        byte[] bytes;
        try {
            final InputStream is = new FileInputStream(file);
            final long length = file.length();
            if (length > 2147483647L) {
                return null;
            }
            bytes = new byte[(int)length];
            int offset = 0;
            for (int numRead = 0; offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0; offset += numRead) {}
            if (offset < bytes.length) {
                System.err.println("[Lie Detector Script] Could not completely read file " + file.getName());
                return null;
            }
        }
        catch (IOException e) {
            return null;
        }
        return bytes;
    }
}
