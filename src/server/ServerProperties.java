package server;

import java.util.Iterator;
import java.util.Map;
import tools.FileoutputUtil;
import constants.WorldConstants;
import java.util.Map.Entry;
import constants.WorldConstants.TespiaWorldOption;
import constants.WorldConstants.WorldOption;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import tools.StringUtil;
import java.io.FileInputStream;
import java.util.Properties;

public class ServerProperties
{
    private static Properties props;
    
    public static String getPath() {
        return System.getProperty("path", "") + "配置.ini";
    }
    
    public static void loadProperties() {
        try {
            final InputStream in = new FileInputStream(getPath());
            final BufferedReader bf = new BufferedReader((Reader)new InputStreamReader(in, StringUtil.codeString(getPath())));
            ServerProperties.props.load((Reader)bf);
            bf.close();
        }
        catch (IOException ex) {
            System.err.println("讀取\"" + getPath() + "\"檔案失敗 " + (Object)ex);
        }
    }
    
    public static void relaodProperties() {
        BufferedReader bf = null;
        try {
            final InputStream in = new FileInputStream(getPath());
            bf = new BufferedReader((Reader)new InputStreamReader(in, StringUtil.codeString(getPath())));
            final Properties p = new Properties();
            p.load((Reader)bf);
            ServerProperties.props = p;
        }
        catch (IOException ex) {
            System.err.println("读取\"" + getPath() + "\"档案失败 " + (Object)ex);
            if (null != bf) {
                try {
                    bf.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            if (null != bf) {
                try {
                    bf.close();
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
    
    public static void saveProperties() {
        final File outputFile = new File(getPath());
        if (outputFile.exists()) {
            outputFile.delete();
        }
        final ArrayList<String> setting = new ArrayList<String>();
        final ArrayList<String> job = new ArrayList<String>();
        final Map<String, ArrayList<String>> world = new HashMap<String, ArrayList<String>>();
        final Map<String, ArrayList<String>> tespia = new HashMap<String, ArrayList<String>>();
        for (final WorldOption e : WorldOption.values()) {
            world.put(e.name(), new ArrayList<String>());
        }
        for (final TespiaWorldOption e2 : TespiaWorldOption.values()) {
            tespia.put(e2.name(), new ArrayList<String>());
        }
        for (final Entry i : ServerProperties.props.entrySet()) {
            final String info = i.getKey() + " = " + i.getValue().toString().replace((CharSequence)"\\", (CharSequence)"\\\\") + "\r\n";
            if (((String)i.getKey()).contains((CharSequence)"World")) {
                final int worldId = Integer.parseInt(((String)i.getKey()).substring(((String)i.getKey()).lastIndexOf(100) + 1));
                ((ArrayList<String>)world.get((Object)WorldConstants.getNameById(worldId))).add(info);
            }
            else if (((String)i.getKey()).contains((CharSequence)"Worldt")) {
                final int worldId = Integer.parseInt(((String)i.getKey()).substring(((String)i.getKey()).lastIndexOf(116) + 1));
                ((ArrayList<String>)tespia.get((Object)WorldConstants.getNameById(worldId))).add(info);
            }
            else if (((String)i.getKey()).contains((CharSequence)"Job")) {
                job.add(info);
            }
            else {
                setting.add(info);
            }
        }
        FileoutputUtil.logToFile(getPath(), "# [配置]\r\n");
        for (final String s : setting) {
            FileoutputUtil.logToFile(getPath(), s);
        }
        FileoutputUtil.logToFile(getPath(), "\r\n# [伺服器]\r\n");
        for (final Entry<String, ArrayList<String>> j : world.entrySet()) {
            if (((ArrayList<String>)j.getValue()).isEmpty()) {
                continue;
            }
            FileoutputUtil.logToFile(getPath(), "# " + (String)j.getKey() + "\r\n");
            for (final String s2 : (ArrayList<String>)j.getValue()) {
                FileoutputUtil.logToFile(getPath(), s2);
            }
        }
        FileoutputUtil.logToFile(getPath(), "\r\n# [測試機]\r\n");
        for (final Entry<String, ArrayList<String>> j : tespia.entrySet()) {
            if (((ArrayList<String>)j.getValue()).isEmpty()) {
                continue;
            }
            FileoutputUtil.logToFile(getPath(), "# " + (String)j.getKey() + "\r\n");
            for (final String s2 : (ArrayList<String>)j.getValue()) {
                FileoutputUtil.logToFile(getPath(), s2);
            }
        }
        FileoutputUtil.logToFile(getPath(), "\r\n# [职业創建开关]\r\n");
        for (final String s : job) {
            FileoutputUtil.logToFile(getPath(), s);
        }
    }
    
    public static void setProperty(final String prop, final String newInf) {
        ServerProperties.props.setProperty(prop, newInf);
    }
    
    public static void setProperty(final String prop, final boolean newInf) {
        ServerProperties.props.setProperty(prop, String.valueOf(newInf));
    }
    
    public static void setProperty(final String prop, final byte newInf) {
        ServerProperties.props.setProperty(prop, String.valueOf((int)newInf));
    }
    
    public static void setProperty(final String prop, final short newInf) {
        ServerProperties.props.setProperty(prop, String.valueOf((int)newInf));
    }
    
    public static void setProperty(final String prop, final int newInf) {
        ServerProperties.props.setProperty(prop, String.valueOf(newInf));
    }
    
    public static void setProperty(final String prop, final long newInf) {
        ServerProperties.props.setProperty(prop, String.valueOf(newInf));
    }
    
    public static void removeProperty(final String prop) {
        ServerProperties.props.remove((Object)prop);
    }
    
    public static String getProperty(final String s) {
        return ServerProperties.props.getProperty(s);
    }
    
    public static String getProperty(final String s, final String def) {
        return ServerProperties.props.getProperty(s, def);
    }
    
    public static boolean getProperty(final String s, final boolean def) {
        return getProperty(s, def ? "true" : "false").equalsIgnoreCase("true");
    }
    
    public static byte getProperty(final String s, final byte def) {
        final String property = ServerProperties.props.getProperty(s);
        if (property != null) {
            return Byte.parseByte(property);
        }
        return def;
    }
    
    public static short getProperty(final String s, final short def) {
        final String property = ServerProperties.props.getProperty(s);
        if (property != null) {
            return Short.parseShort(property);
        }
        return def;
    }
    
    public static int getProperty(final String s, final int def) {
        final String property = ServerProperties.props.getProperty(s);
        if (property != null) {
            return Integer.parseInt(property);
        }
        return def;
    }
    
    public static long getProperty(final String s, final long def) {
        final String property = ServerProperties.props.getProperty(s);
        if (property != null) {
            return Long.parseLong(property);
        }
        return def;
    }
    
    static {
        ServerProperties.props = new Properties();
        loadProperties();
    }
}
