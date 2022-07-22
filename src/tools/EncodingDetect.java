package tools;

import java.io.File;

public class EncodingDetect
{
    public static void main(final String[] args) {
        final String file = "test.txt";
        final String encode = getJavaEncode(file);
        System.out.println(encode);
    }
    
    public static String getJavaEncode(final String filePath) {
        return getJavaEncode(new File(filePath));
    }
    
    public static String getJavaEncode(final File file) {
        final BytesEncodingDetect s = new BytesEncodingDetect();
        final String fileCode = BytesEncodingDetect.javaname[s.detectEncoding(file)];
        return fileCode;
    }
}
