package scripting;

import java.io.InputStream;
import javax.script.ScriptEngine;
import java.io.IOException;
import tools.FilePrinter;
import javax.script.ScriptException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import tools.EncodingDetect;
import java.io.FileInputStream;
import java.io.File;
import javax.script.Invocable;
import client.MapleClient;
import javax.script.ScriptEngineManager;

public abstract class AbstractScriptManager
{
    private static final ScriptEngineManager sem;
    
    protected Invocable getInvocable(final String path, final MapleClient c) {
        return this.getInvocable(path, c, false);
    }
    
    protected Invocable getInvocable(String path, final MapleClient c, final boolean npc) {
        path = "脚本/" + path;
        ScriptEngine engine = null;
        if (c != null) {
            engine = c.getScriptEngine(path);
        }
        if (engine == null) {
            final File scriptFile = new File(path);
            if (!scriptFile.exists()) {
                return null;
            }
            if (c != null && c.getPlayer() != null && c.getPlayer().getDebugMessage()) {
                c.getPlayer().dropMessage("getInvocable - Part1");
            }
            engine = AbstractScriptManager.sem.getEngineByName("javascript");
            if (c != null && c.getPlayer() != null && c.getPlayer().getDebugMessage()) {
                c.getPlayer().dropMessage("getInvocable - Part2");
            }
            if (c != null) {
                c.setScriptEngine(path, engine);
                if (c != null && c.getPlayer() != null && c.getPlayer().getDebugMessage()) {
                    c.getPlayer().dropMessage("getInvocable - Part3");
                }
            }
            InputStream in = null;
            try {
                in = new FileInputStream(scriptFile);
                if (c != null && c.getPlayer() != null && c.getPlayer().getDebugMessage()) {
                    c.getPlayer().dropMessage("getInvocable - Part4");
                }
                final BufferedReader bf = new BufferedReader((Reader)new InputStreamReader(in, EncodingDetect.getJavaEncode(scriptFile)));
                final String lines = "load('nashorn:mozilla_compat.js');" + (String)bf.lines().collect((Collector<? super String, ?, String>)Collectors.joining((CharSequence)System.lineSeparator()));
                engine.eval(lines);
                if (c != null && c.getPlayer() != null && c.getPlayer().getDebugMessage()) {
                    c.getPlayer().dropMessage("getInvocable - Part5");
                }
            }
            catch (ScriptException ex) {}
            catch (IOException e) {
                FilePrinter.printError("AbstractScriptManager.txt", "Error executing script. Path: " + path + "\nException " + (Object)e);
                return null;
            }
            finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                }
                catch (IOException ex2) {}
            }
        }
        else if (c != null && npc) {
            c.getPlayer().dropMessage(5, "你现在不能攻击或不能跟npc对话,请在对话框打 @解卡 來解除异常状态");
        }
        return (Invocable)engine;
    }
    
    static {
        sem = new ScriptEngineManager();
    }
}
