package configs;

import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;
import java.util.Properties;

public class Config
{
    private static Properties dbProps;
    
    public Config() {
        try {
            final FileReader fR = new FileReader("配置.ini");
            Config.dbProps.load((Reader)fR);
            fR.close();
        }
        catch (IOException ex) {
            System.err.println("加载数据库配置出错，请检查" + (Object)ex);
        }
    }
    
    public String getConfig(final String configSetName) {
        return Config.dbProps.getProperty(configSetName);
    }
    
    static {
        Config.dbProps = new Properties();
    }
}
