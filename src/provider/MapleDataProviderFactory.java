package provider;

import server.ServerProperties;
import provider.WzXML.XMLWZFile;
import java.io.File;

public class MapleDataProviderFactory
{
    private static final String wzPath;
    
    private static MapleDataProvider getWZ(final Object in) {
        return getWZ(in, false);
    }
    
    private static MapleDataProvider getWZ(final Object in, final boolean provideImages) {
        if (in instanceof File) {
            final File fileIn = (File)in;
            return new XMLWZFile(fileIn);
        }
        throw new IllegalArgumentException("Can't create data provider for input " + in);
    }
    
    public static MapleDataProvider getDataProvider(final File in) {
        return getWZ((Object)in);
    }
    
    public static MapleDataProvider getDataProvider(final String path) {
        return getWZ((Object)fileInWZPath(path));
    }
    
    public static File fileInWZPath(final String filename) {
        return new File(MapleDataProviderFactory.wzPath, filename);
    }
    
    static {
        wzPath = ServerProperties.getProperty("server.wzpath", "wz");
    }
}
