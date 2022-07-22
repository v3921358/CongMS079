package abc;

import org.slf4j.LoggerFactory;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.FileReader;
import org.slf4j.Logger;
import java.util.Properties;

public class 吸怪检测
{
    private static 吸怪检测 instance;
    private static boolean CANLOG;
    private Properties itempb_cfg;
    private String[] jc1;
    private String[] jc2;
    private String[] jc3;
    private String[] jc4;
    private String[] jc5;
    private String[] jc6;
    private String[] jc7;
    private String[] jc8;
    private static Logger log;
    
    public 吸怪检测() {
        this.itempb_cfg = new Properties();
        try {
            final InputStreamReader is = new FileReader("Load\\Bin\\天谴检测A.ini");
            this.itempb_cfg.load((Reader)is);
            is.close();
            this.jc1 = this.itempb_cfg.getProperty("jc1").split(",");
            this.jc2 = this.itempb_cfg.getProperty("jc2").split(",");
            this.jc3 = this.itempb_cfg.getProperty("jc3").split(",");
            this.jc4 = this.itempb_cfg.getProperty("jc4").split(",");
            this.jc5 = this.itempb_cfg.getProperty("jc5").split(",");
            this.jc6 = this.itempb_cfg.getProperty("jc6").split(",");
            this.jc7 = this.itempb_cfg.getProperty("jc7").split(",");
            this.jc8 = this.itempb_cfg.getProperty("jc8").split(",");
        }
        catch (Exception e) {
            吸怪检测.log.error("Could not configuration", (Throwable)e);
        }
    }
    
    public String[] getjc1() {
        return this.jc1;
    }
    
    public String[] getjc2() {
        return this.jc2;
    }
    
    public String[] getjc3() {
        return this.jc3;
    }
    
    public String[] getjc4() {
        return this.jc4;
    }
    
    public String[] getjc5() {
        return this.jc5;
    }
    
    public String[] getjc6() {
        return this.jc6;
    }
    
    public String[] getjc7() {
        return this.jc7;
    }
    
    public String[] getjc8() {
        return this.jc8;
    }
    
    public boolean isCANLOG() {
        return 吸怪检测.CANLOG;
    }
    
    public void setCANLOG(final boolean CANLOG) {
        吸怪检测.CANLOG = CANLOG;
    }
    
    public static 吸怪检测 getInstance() {
        if (吸怪检测.instance == null) {
            吸怪检测.instance = new 吸怪检测();
        }
        return 吸怪检测.instance;
    }
    
    static {
        吸怪检测.instance = null;
        吸怪检测.log = LoggerFactory.getLogger((Class)吸怪检测.class);
    }
}
