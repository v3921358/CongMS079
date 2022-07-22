package constants;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.FileReader;
import java.util.Properties;

public class BeansConstants
{
    private static BeansConstants instance;
    private static boolean CANLOG;
    private Properties itempb_cfg;
    private final int 海洋帽子几率;
    private final String[] 黃金狗幾率;
    private final String[] 小白怪;
    private final String[] 大白怪;
    private final String[] 紫色怪;
    private final String[] 粉色怪;
    private final String[] 飛俠;
    private final String[] 海盜;
    private final String[] 法師;
    private final String[] 戰士;
    private final String[] 弓箭手;
    private final String[] 女皇;
    private final String[] 白怪獎勵;
    private final String[] 色怪獎勵;
    private final String[] 五职业獎勵;
    private final String[] 女皇獎勵;
    private final int 力度搞假;
    private final int 豆豆獎勵範圍;
    
    public BeansConstants() {
        this.itempb_cfg = new Properties();
        try (final InputStreamReader is = new FileReader("beans.properties")) {
            this.itempb_cfg.load((Reader)is);
        }
        catch (Exception e) {
            System.err.println((Object)e);
        }
        this.海洋帽子几率 = Integer.parseInt(this.itempb_cfg.getProperty("hymzjl"));
        this.黃金狗幾率 = this.itempb_cfg.getProperty("hjgjl").split(",");
        this.大白怪 = this.itempb_cfg.getProperty("dbg").split(",");
        this.小白怪 = this.itempb_cfg.getProperty("xbg").split(",");
        this.紫色怪 = this.itempb_cfg.getProperty("zsg").split(",");
        this.粉色怪 = this.itempb_cfg.getProperty("fsg").split(",");
        this.飛俠 = this.itempb_cfg.getProperty("fx").split(",");
        this.海盜 = this.itempb_cfg.getProperty("hd").split(",");
        this.法師 = this.itempb_cfg.getProperty("fs").split(",");
        this.戰士 = this.itempb_cfg.getProperty("zs").split(",");
        this.弓箭手 = this.itempb_cfg.getProperty("gjs").split(",");
        this.女皇 = this.itempb_cfg.getProperty("nh").split(",");
        this.白怪獎勵 = this.itempb_cfg.getProperty("bgjl").split(",");
        this.色怪獎勵 = this.itempb_cfg.getProperty("sgjl").split(",");
        this.五职业獎勵 = this.itempb_cfg.getProperty("wzyjl").split(",");
        this.女皇獎勵 = this.itempb_cfg.getProperty("nhjl").split(",");
        this.力度搞假 = Integer.parseInt(this.itempb_cfg.getProperty("ldgj"));
        this.豆豆獎勵範圍 = Integer.parseInt(this.itempb_cfg.getProperty("ddjlfw"));
    }
    
    public int get豆豆獎勵範圍() {
        return this.豆豆獎勵範圍;
    }
    
    public int get力度搞假() {
        return this.力度搞假;
    }
    
    public String[] get白怪獎勵() {
        return this.白怪獎勵;
    }
    
    public String[] get色怪獎勵() {
        return this.色怪獎勵;
    }
    
    public String[] get五职业獎勵() {
        return this.五职业獎勵;
    }
    
    public String[] get女皇獎勵() {
        return this.女皇獎勵;
    }
    
    public String[] get大白怪() {
        return this.大白怪;
    }
    
    public String[] get小白怪() {
        return this.小白怪;
    }
    
    public String[] get紫色怪() {
        return this.紫色怪;
    }
    
    public String[] get粉色怪() {
        return this.粉色怪;
    }
    
    public String[] get飛俠() {
        return this.飛俠;
    }
    
    public String[] get海盜() {
        return this.海盜;
    }
    
    public String[] get法師() {
        return this.法師;
    }
    
    public String[] get戰士() {
        return this.戰士;
    }
    
    public String[] get弓箭手() {
        return this.弓箭手;
    }
    
    public String[] get女皇() {
        return this.女皇;
    }
    
    public int get海洋帽子几率() {
        return this.海洋帽子几率;
    }
    
    public String[] get黄金狗几率() {
        return this.黃金狗幾率;
    }
    
    public boolean isCANLOG() {
        return BeansConstants.CANLOG;
    }
    
    public void setCANLOG(final boolean CANLOG) {
        BeansConstants.CANLOG = CANLOG;
    }
    
    public static BeansConstants getInstance() {
        if (BeansConstants.instance == null) {
            BeansConstants.instance = new BeansConstants();
        }
        return BeansConstants.instance;
    }
    
    static {
        BeansConstants.instance = null;
    }
}
