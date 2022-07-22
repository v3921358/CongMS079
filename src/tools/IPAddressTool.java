package tools;

public class IPAddressTool
{
    public static final long dottedQuadToLong(final String dottedQuad) throws RuntimeException {
        final String[] quads = dottedQuad.split("\\.");
        if (quads.length != 4) {
            throw new RuntimeException("Invalid IP Address format.");
        }
        long ipAddress = 0L;
        for (int i = 0; i < 4; ++i) {
            ipAddress += (long)(Integer.parseInt(quads[i]) % 256) * (long)Math.pow(256.0, (double)(4 - i));
        }
        return ipAddress;
    }
    
    public static final String longToDottedQuad(long longIP) throws RuntimeException {
        final StringBuilder ipAddress = new StringBuilder();
        for (int i = 0; i < 4; ++i) {
            final int quad = (int)(longIP / (long)Math.pow(256.0, (double)(4 - i)));
            longIP -= (long)quad * (long)Math.pow(256.0, (double)(4 - i));
            if (i > 0) {
                ipAddress.append(".");
            }
            if (quad > 255) {
                throw new RuntimeException("Invalid long IP address.");
            }
            ipAddress.append(quad);
        }
        return ipAddress.toString();
    }
}
