package scripting;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import server.quest.MapleQuest;
import javax.script.Invocable;
import java.util.concurrent.locks.Lock;
import tools.FilePrinter;
import javax.script.ScriptException;
import javax.script.ScriptEngine;
import java.util.WeakHashMap;
import client.MapleClient;
import java.util.Map;

public class NPCScriptManager extends AbstractScriptManager
{
    private final Map<MapleClient, NPCConversationManager> cms;
    private static final NPCScriptManager instance;
    
    public NPCScriptManager() {
        this.cms = new WeakHashMap<MapleClient, NPCConversationManager>();
    }
    
    public static final NPCScriptManager getInstance() {
        return NPCScriptManager.instance;
    }
    
    void start(final MapleClient client, final int id, final int wh) {
        this.start(client, id, wh);
    }
    
    public final void start(final MapleClient c, final int npc) {
        this.start(c, npc, null);
    }
    
    public final void start(final MapleClient c, final int npc, final String script) {
        this.start(c, npc, 0, script);
    }
    
    public final void start(final MapleClient c, final int npc, final int mode, final String script) {
        final Lock lock = c.getNPCLock();
        lock.lock();
        try {
            if (c.getPlayer().isGM()) {
                c.getPlayer().dropMessage("[GM提示]您已经连接到脚本:" + npc + ((script == null) ? "" : ("(" + script + ")")) + ((mode == 0) ? "" : ("型号: " + mode)) + "的功能，如需修改此内容可前往服务端。");
            }
            if (!this.cms.containsKey((Object)c) && c.canClickNPC()) {
                if (c.getPlayer() != null && c.getPlayer().getDebugMessage()) {
                    c.getPlayer().dropMessage("start - !cms.containsKey(c) && c.canClickNPC()");
                }
                if (c.getPlayer() != null && c.getPlayer().getDebugMessage()) {
                    c.getPlayer().dropMessage("start - setInvocable");
                }
                Invocable iv;
                if (script == null) {
                    if (mode != 0) {
                        iv = this.getInvocable("npc/" + npc + "_" + mode + ".js", c, true);
                    }
                    else {
                        iv = this.getInvocable("npc/" + npc + ".js", c, true);
                    }
                }
                else {
                    iv = this.getInvocable("special/" + script + ".js", c, true);
                }
                if (iv == null) {
                    iv = this.getInvocable("special/notcoded.js", c, true);
                    if (iv == null) {
                        this.dispose(c);
                        return;
                    }
                }
                final ScriptEngine scriptengine = (ScriptEngine)iv;
                if (c.getPlayer() != null && c.getPlayer().getDebugMessage()) {
                    c.getPlayer().dropMessage("start - iv");
                }
                final NPCConversationManager cm = new NPCConversationManager(c, npc, -1, mode, script, (byte)(-1), iv);
                if (c.getPlayer() != null && c.getPlayer().getDebugMessage()) {
                    c.getPlayer().dropMessage("start - cm");
                }
                if (getInstance() == null) {
                    if (c.getPlayer() != null && c.getPlayer().getDebugMessage()) {
                        c.getPlayer().dropMessage("start = null");
                    }
                    this.dispose(c);
                    return;
                }
                this.cms.put(c, cm);
                if (c.getPlayer() != null && c.getPlayer().getDebugMessage()) {
                    c.getPlayer().dropMessage("start - cms");
                }
                scriptengine.put("cm", (Object)cm);
                if (c.getPlayer() != null) {
                    c.getPlayer().setConversation(1);
                }
                if (c.getPlayer() != null && c.getPlayer().getDebugMessage()) {
                    c.getPlayer().dropMessage("start - setConversation");
                }
                c.setClickedNPC();
                if (c.getPlayer() != null && c.getPlayer().getDebugMessage()) {
                    c.getPlayer().dropMessage("start - setClickNPC");
                }
                try {
                    iv.invokeFunction("start", new Object[0]);
                    if (c.getPlayer() != null && c.getPlayer().getDebugMessage()) {
                        c.getPlayer().dropMessage("start - cms");
                    }
                }
                catch (NoSuchMethodException nsme) {
                    iv.invokeFunction("action", Byte.valueOf((byte)1), Byte.valueOf((byte)0), Integer.valueOf(0));
                }
            }
            else if (c.getPlayer() != null) {
                c.getPlayer().dropMessage(5, "你现在不能攻击或不能跟npc对话,请在对话框打 @解卡/@ea 来解除异常状态");
            }
        }
        catch (ScriptException ex) {}
        catch (NoSuchMethodException e) {
            System.err.println("NPC 脚本错误, 它ID为 : " + npc + "." + (Object)e);
            if (c.getPlayer() != null && c.getPlayer().isGM()) {
                c.getPlayer().dropMessage("[系统提示] NPC " + npc + "脚本错误 " + (Object)e + "");
            }
            FilePrinter.printError("NPCScriptManager.txt", "Error executing NPC script, NPC ID : " + npc + "." + (Object)e);
            this.dispose(c);
        }
        finally {
            lock.unlock();
        }
    }
    
    public final void action(final MapleClient c, final byte mode, final byte type, final int selection) {
        if (mode != -1) {
            final NPCConversationManager cm = (NPCConversationManager)this.cms.get((Object)c);
            if (cm == null || cm.getLastMsg() > -1) {
                return;
            }
            final Lock lock = c.getNPCLock();
            lock.lock();
            try {
                if (cm.pendingDisposal) {
                    this.dispose(c);
                }
                else {
                    c.setClickedNPC();
                    cm.getIv().invokeFunction("action", Byte.valueOf(mode), Byte.valueOf(type), Integer.valueOf(selection));
                }
            }
            catch (ScriptException ex) {}
            catch (NoSuchMethodException e) {
                if (c.getPlayer() != null && c.getPlayer().isGM()) {
                    c.getPlayer().dropMessage("[系统提示] NPC " + cm.getNpc() + "脚本错误 " + (Object)e + "");
                }
                System.err.println("NPC 脚本错误. 它ID为 : " + cm.getNpc() + ":" + (Object)e);
                FilePrinter.printError("NPCScriptManager.txt", "Error executing NPC script, NPC ID : " + cm.getNpc() + "." + (Object)e);
                this.dispose(c);
            }
            finally {
                lock.unlock();
            }
        }
    }
    
    public final void startQuest(final MapleClient c, final int npc, final int quest) {
        if (!MapleQuest.getInstance(quest).canStart(c.getPlayer(), null)) {
            return;
        }
        final Lock lock = c.getNPCLock();
        lock.lock();
        try {
            if (!this.cms.containsKey((Object)c) && c.canClickNPC()) {
                final Invocable iv = this.getInvocable("任务/" + quest + ".js", c, true);
                if (iv == null) {
                    c.getPlayer().dropMessage(1, "此任务尚未建置，请通知管理员。\r\n任务编号: " + quest);
                    this.dispose(c);
                    return;
                }
                final ScriptEngine scriptengine = (ScriptEngine)iv;
                final NPCConversationManager cm = new NPCConversationManager(c, npc, quest, 0, null, (byte)0, iv);
                this.cms.put(c, cm);
                scriptengine.put("qm", (Object)cm);
                c.getPlayer().setConversation(1);
                c.setClickedNPC();
                if (c.getPlayer().isGM()) {
                    c.getPlayer().dropMessage("[系统提示]你已经建立起任务脚本:" + quest + "的往来。");
                }
                iv.invokeFunction("start", Byte.valueOf((byte)1), Byte.valueOf((byte)0), Integer.valueOf(0));
            }
        }
        catch (ScriptException ex) {}
        catch (NoSuchMethodException e) {
            System.err.println("Error executing Quest script. (" + quest + ")..NPCID: " + npc + ":" + (Object)e);
            FilePrinter.printError("NPCScriptManager.txt", "Error executing Quest script. (" + quest + ")..NPCID: " + npc + ":" + (Object)e);
            this.dispose(c);
        }
        finally {
            lock.unlock();
        }
    }
    
    public final void startQuest(final MapleClient c, final byte mode, final byte type, final int selection) {
        final Lock lock = c.getNPCLock();
        final NPCConversationManager cm = (NPCConversationManager)this.cms.get((Object)c);
        if (cm == null || cm.getLastMsg() > -1) {
            return;
        }
        lock.lock();
        try {
            if (cm.pendingDisposal) {
                this.dispose(c);
            }
            else {
                c.setClickedNPC();
                cm.getIv().invokeFunction("start", Byte.valueOf(mode), Byte.valueOf(type), Integer.valueOf(selection));
            }
        }
        catch (ScriptException ex) {}
        catch (NoSuchMethodException e) {
            if (c.getPlayer().isGM()) {
                c.getPlayer().dropMessage("[系统提示]任务脚本:" + cm.getQuest() + "错误NPC: " + cm.getNpc() + ":" + (Object)e);
            }
            System.err.println("Error executing Quest script. (" + cm.getQuest() + ")...NPC: " + cm.getNpc() + ":" + (Object)e);
            FilePrinter.printError("NPCScriptManager.txt", "Error executing Quest script. (" + cm.getQuest() + ")..NPCID: " + cm.getNpc() + ":" + (Object)e);
            this.dispose(c);
        }
        finally {
            lock.unlock();
        }
    }
    
    public final void endQuest(final MapleClient c, final int npc, final int quest, final boolean customEnd) {
        if (!customEnd && !MapleQuest.getInstance(quest).canComplete(c.getPlayer(), null)) {
            return;
        }
        final Lock lock = c.getNPCLock();
        lock.lock();
        try {
            if (!this.cms.containsKey((Object)c) && c.canClickNPC()) {
                final Invocable iv = this.getInvocable("任务/" + quest + ".js", c, true);
                if (iv == null) {
                    this.dispose(c);
                    return;
                }
                final ScriptEngine scriptengine = (ScriptEngine)iv;
                final NPCConversationManager cm = new NPCConversationManager(c, npc, quest, 0, null, (byte)1, iv);
                this.cms.put(c, cm);
                scriptengine.put("qm", (Object)cm);
                c.getPlayer().setConversation(1);
                c.setClickedNPC();
                iv.invokeFunction("end", Byte.valueOf((byte)1), Byte.valueOf((byte)0), Integer.valueOf(0));
            }
        }
        catch (ScriptException ex) {}
        catch (NoSuchMethodException e) {
            if (c.getPlayer().isGM()) {
                c.getPlayer().dropMessage("[系统提示]任务脚本:" + quest + "错误NPC: " + quest + ":" + (Object)e);
            }
            System.err.println("Error executing Quest script. (" + quest + ")..NPCID: " + npc + ":" + (Object)e);
            FilePrinter.printError("NPCScriptManager.txt", "Error executing Quest script. (" + quest + ")..NPCID: " + npc + ":" + (Object)e);
            this.dispose(c);
        }
        finally {
            lock.unlock();
        }
    }
    
    public final void endQuest(final MapleClient c, final byte mode, final byte type, final int selection) {
        final Lock lock = c.getNPCLock();
        final NPCConversationManager cm = (NPCConversationManager)this.cms.get((Object)c);
        if (cm == null || cm.getLastMsg() > -1) {
            return;
        }
        lock.lock();
        try {
            if (cm.pendingDisposal) {
                this.dispose(c);
            }
            else {
                c.setClickedNPC();
                cm.getIv().invokeFunction("end", Byte.valueOf(mode), Byte.valueOf(type), Integer.valueOf(selection));
            }
        }
        catch (ScriptException ex) {}
        catch (NoSuchMethodException e) {
            if (c.getPlayer().isGM()) {
                c.getPlayer().dropMessage("[系统提示]任务脚本:" + cm.getQuest() + "错误NPC: " + cm.getNpc() + ":" + (Object)e);
            }
            System.err.println("Error executing Quest script. (" + cm.getQuest() + ")...NPC: " + cm.getNpc() + ":" + (Object)e);
            FilePrinter.printError("NPCScriptManager.txt", "Error executing Quest script. (" + cm.getQuest() + ")..NPCID: " + cm.getNpc() + ":" + (Object)e);
            this.dispose(c);
        }
        finally {
            lock.unlock();
        }
    }
    
    public final void dispose(final MapleClient c) {
        final NPCConversationManager npccm = (NPCConversationManager)this.cms.get((Object)c);
        if (npccm != null) {
            this.cms.remove((Object)c);
            if (npccm.getType() == -1) {
                c.removeScriptEngine("脚本/npc/" + npccm.getNpc() + ".js");
                if (npccm.getMode() != 0) {
                    c.removeScriptEngine("脚本/npc/" + npccm.getNpc() + "_" + npccm.getMode() + ".js");
                }
                c.removeScriptEngine("脚本/special/" + npccm.getScript() + ".js");
                c.removeScriptEngine("脚本/special/notcoded.js");
            }
            else {
                c.removeScriptEngine("脚本/任务/" + npccm.getQuest() + ".js");
            }
        }
        if (c.getPlayer() != null && c.getPlayer().getConversation() == 1) {
            c.getPlayer().setConversation(0);
        }
    }
    
    public final NPCConversationManager getCM(final MapleClient c) {
        return (NPCConversationManager)this.cms.get((Object)c);
    }
    
    public final void cleanCMS() {
        final List<MapleClient> clients = new ArrayList<MapleClient>();
        for (final MapleClient c : this.cms.keySet()) {
            if (c == null || c.getSession() == null || !c.getSession().isActive()) {
                clients.add(c);
            }
        }
        for (final MapleClient c : clients) {
            this.cms.remove((Object)c);
        }
    }
    
    static {
        instance = new NPCScriptManager();
    }
}
