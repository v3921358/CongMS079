package scripting;

import javax.script.ScriptEngineManager;
import client.MapleClient;
import server.MaplePortal;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import java.io.InputStream;
import javax.script.Invocable;
import java.io.IOException;
import javax.script.ScriptException;
import tools.FilePrinter;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import javax.script.Compilable;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import tools.EncodingDetect;
import java.io.FileInputStream;
import java.io.File;
import java.util.HashMap;
import javax.script.ScriptEngineFactory;
import java.util.Map;

public class PortalScriptManager
{
    private static final PortalScriptManager instance;
    private final Map<String, PortalScript> scripts;
    private static final ScriptEngineFactory sef;
    
    public PortalScriptManager() {
        this.scripts = new HashMap<String, PortalScript>();
    }
    
    public static final PortalScriptManager getInstance() {
        return PortalScriptManager.instance;
    }
    
    private final PortalScript getPortalScript(final String scriptName) {
        if (this.scripts.containsKey((Object)scriptName)) {
            return (PortalScript)this.scripts.get((Object)scriptName);
        }
        final File scriptFile = new File("脚本/传送/" + scriptName + ".js");
        if (!scriptFile.exists()) {
            this.scripts.put(scriptName, null);
            return null;
        }
        InputStream in = null;
        final ScriptEngine portal = PortalScriptManager.sef.getScriptEngine();
        try {
            in = new FileInputStream(scriptFile);
            final BufferedReader bf = new BufferedReader((Reader)new InputStreamReader(in, EncodingDetect.getJavaEncode(scriptFile)));
            final String lines = "load('nashorn:mozilla_compat.js');" + (String)bf.lines().collect((Collector<? super String, ?, String>)Collectors.joining((CharSequence)System.lineSeparator()));
            final CompiledScript compiled = ((Compilable)portal).compile(lines);
            compiled.eval();
        }
        catch (FileNotFoundException ex) {}
        catch (UnsupportedEncodingException ex2) {}
        catch (ScriptException e) {
            System.err.println("Error executing Portalscript: " + scriptName + ":" + (Object)e);
            FilePrinter.printError("PortalScriptManager.txt", (Throwable)e);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ex3) {}
        }
        final PortalScript script = (PortalScript)((Invocable)portal).getInterface(PortalScript.class);
        this.scripts.put(scriptName, script);
        return script;
    }
    
    public final void executePortalScript(final MaplePortal portal, final MapleClient c) {
        final PortalScript script = this.getPortalScript(portal.getScriptName());
        if (c != null && c.getPlayer() != null && c.getPlayer().hasGmLevel(2)) {
            c.getPlayer().dropMessage("您已經建立與傳送門腳本: " + portal.getScriptName() + ".js 的关联。");
        }
        if (script != null) {
            try {
                script.enter(new PortalPlayerInteraction(c, portal));
            }
            catch (Exception e) {
                System.err.println("進入傳送腳本失敗: " + portal.getScriptName() + ":" + (Object)e);
            }
        }
        this.clearScripts();
    }
    
    public final void clearScripts() {
        this.scripts.clear();
    }
    
    static {
        instance = new PortalScriptManager();
        sef = new ScriptEngineManager().getEngineByName("nashorn").getFactory();
    }
}
