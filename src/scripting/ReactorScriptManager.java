package scripting;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tools.FileoutputUtil;
import database.DBConPool;
import java.util.LinkedList;
import javax.script.Invocable;
import javax.script.ScriptException;
import tools.FilePrinter;
import javax.script.ScriptEngine;
import server.maps.MapleReactor;
import client.MapleClient;
import java.util.HashMap;
import server.maps.ReactorDropEntry;
import java.util.List;
import java.util.Map;

public class ReactorScriptManager extends AbstractScriptManager
{
    private static final ReactorScriptManager instance;
    private final Map<Integer, List<ReactorDropEntry>> drops;
    
    public ReactorScriptManager() {
        this.drops = new HashMap<Integer, List<ReactorDropEntry>>();
    }
    
    public static final ReactorScriptManager getInstance() {
        return ReactorScriptManager.instance;
    }
    
    public final void act(final MapleClient c, final MapleReactor reactor) {
        try {
            final Invocable iv = this.getInvocable("反应堆/" + reactor.getReactorId() + ".js", c);
            if (iv == null) {
                return;
            }
            if (c.getPlayer().isGM()) {
                c.getPlayer().dropMessage(6, "执行反应堆ID：" + reactor.getReactorId());
            }
            final ScriptEngine scriptengine = (ScriptEngine)iv;
            final ReactorActionManager rm = new ReactorActionManager(c, reactor);
            scriptengine.put("rm", (Object)rm);
            iv.invokeFunction("act", new Object[0]);
        }
        catch (ScriptException | NoSuchMethodException ex2) {
            System.err.println("Error executing reactor script. ReactorID: " + reactor.getReactorId() + ", ReactorName: " + reactor.getName() + ":" + (Object)ex2);
            FilePrinter.printError("ReactorScriptManager.txt", "Error executing reactor script. ReactorID: " + reactor.getReactorId() + ", ReactorName: " + reactor.getName() + ":" + (Object)ex2);
        }
    }
    
    public final List<ReactorDropEntry> getDrops(final int rid) {
        List<ReactorDropEntry> ret = (List<ReactorDropEntry>)this.drops.get((Object)Integer.valueOf(rid));
        if (ret != null) {
            return ret;
        }
        ret = new LinkedList<ReactorDropEntry>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
                ps = con.prepareStatement("SELECT * FROM reactordrops WHERE reactorid = ?");
                ps.setInt(1, rid);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ret.add(new ReactorDropEntry(rs.getInt("itemid"), rs.getInt("chance"), rs.getInt("questid")));
                }
                rs.close();
                ps.close();
            }
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            catch (SQLException ignore) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ignore);
                return ret;
            }
        }
        catch (SQLException e) {
            System.err.println("Could not retrieve drops for reactor " + rid + (Object)e);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            return ret;
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            catch (SQLException ignore2) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ignore2);
                return ret;
            }
        }
        this.drops.put(Integer.valueOf(rid), ret);
        return ret;
    }
    
    public final void clearDrops() {
        this.drops.clear();
    }
    
    static {
        instance = new ReactorScriptManager();
    }
}
