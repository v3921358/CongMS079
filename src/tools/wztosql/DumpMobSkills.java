package tools.wztosql;

import java.util.List;
import java.util.Iterator;
import java.awt.Point;
import java.util.ArrayList;
import provider.MapleDataTool;
import provider.MapleData;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import tools.FileoutputUtil;
import database.DBConPool;
import provider.MapleDataProviderFactory;
import provider.MapleDataProvider;

public class DumpMobSkills
{
    private final MapleDataProvider skill;
    protected boolean hadError;
    protected boolean update;
    protected int id;
    
    public DumpMobSkills(final boolean update) throws Exception {
        this.hadError = false;
        this.update = false;
        this.id = 0;
        this.update = update;
        this.skill = MapleDataProviderFactory.getDataProvider("Skill.wz");
        if (this.skill == null) {
            this.hadError = true;
        }
    }
    
    public boolean isHadError() {
        return this.hadError;
    }
    
    public void dumpMobSkills() throws Exception {
        if (!this.hadError) {
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
                final PreparedStatement ps = con.prepareStatement("INSERT INTO wz_mobskilldata(skillid, `level`, hp, mpcon, x, y, time, prop, `limit`, spawneffect,`interval`, summons, ltx, lty, rbx, rby, once) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                try {
                    this.dumpMobSkills(ps);
                }
                catch (Exception e) {
                    System.out.println(this.id + " skill.");
                    System.out.println((Object)e);
                    this.hadError = true;
                }
                finally {
                    ps.executeBatch();
                    ps.close();
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
        }
    }
    
    public void delete(final String sql) throws Exception {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement(sql)) {
            ps.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
    }
    
    public boolean doesExist(final String sql) throws Exception {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement(sql)) {
            final ResultSet rs = ps.executeQuery();
            final boolean ret = rs.next();
            rs.close();
            return ret;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            return false;
        }
    }
    
    public void dumpMobSkills(final PreparedStatement ps) throws Exception {
        if (!this.update) {
            this.delete("DELETE FROM wz_mobskilldata");
            System.out.println("Deleted wz_mobskilldata successfully.");
        }
        final MapleData skillz = this.skill.getData("MobSkill.img");
        System.out.println("Adding into wz_mobskilldata.....");
        for (final MapleData ids : skillz.getChildren()) {
            for (final MapleData lvlz : ids.getChildByPath("level").getChildren()) {
                this.id = Integer.parseInt(ids.getName());
                final int lvl = Integer.parseInt(lvlz.getName());
                if (this.update && this.doesExist("SELECT * FROM wz_mobskilldata WHERE skillid = " + this.id + " AND level = " + lvl)) {
                    continue;
                }
                ps.setInt(1, this.id);
                ps.setInt(2, lvl);
                ps.setInt(3, MapleDataTool.getInt("hp", lvlz, 100));
                ps.setInt(4, MapleDataTool.getInt("mpCon", lvlz, 0));
                ps.setInt(5, MapleDataTool.getInt("x", lvlz, 1));
                ps.setInt(6, MapleDataTool.getInt("y", lvlz, 1));
                ps.setInt(7, MapleDataTool.getInt("time", lvlz, 0));
                ps.setInt(8, MapleDataTool.getInt("prop", lvlz, 100));
                ps.setInt(9, MapleDataTool.getInt("limit", lvlz, 0));
                ps.setInt(10, MapleDataTool.getInt("summonEffect", lvlz, 0));
                ps.setInt(11, MapleDataTool.getInt("interval", lvlz, 0));
                final StringBuilder summ = new StringBuilder();
                final List<Integer> toSummon = new ArrayList<Integer>();
                for (int i = 0; i > -1 && lvlz.getChildByPath(String.valueOf(i)) != null; ++i) {
                    toSummon.add(Integer.valueOf(MapleDataTool.getInt(lvlz.getChildByPath(String.valueOf(i)), 0)));
                }
                for (final Integer summon : toSummon) {
                    if (summ.length() > 0) {
                        summ.append(", ");
                    }
                    summ.append(String.valueOf((Object)summon));
                }
                ps.setString(12, summ.toString());
                if (lvlz.getChildByPath("lt") != null) {
                    final Point lt = (Point)lvlz.getChildByPath("lt").getData();
                    ps.setInt(13, lt.x);
                    ps.setInt(14, lt.y);
                }
                else {
                    ps.setInt(13, 0);
                    ps.setInt(14, 0);
                }
                if (lvlz.getChildByPath("rb") != null) {
                    final Point rb = (Point)lvlz.getChildByPath("rb").getData();
                    ps.setInt(15, rb.x);
                    ps.setInt(16, rb.y);
                }
                else {
                    ps.setInt(15, 0);
                    ps.setInt(16, 0);
                }
                ps.setByte(17, (byte)(byte)((MapleDataTool.getInt("summonOnce", lvlz, 0) > 0) ? 1 : 0));
                System.out.println("Added skill: " + this.id + " level " + lvl);
                ps.addBatch();
            }
        }
        System.out.println("Done wz_mobskilldata...");
    }
    
    public int currentId() {
        return this.id;
    }
    
    public static void main(final String[] args) {
        boolean hadError = false;
        boolean update = false;
        final long startTime = System.currentTimeMillis();
        for (final String file : args) {
            if (file.equalsIgnoreCase("-update")) {
                update = true;
            }
        }
        int currentQuest = 0;
        try {
            final DumpMobSkills dq = new DumpMobSkills(update);
            System.out.println("Dumping mobskills");
            dq.dumpMobSkills();
            hadError |= dq.isHadError();
            currentQuest = dq.currentId();
        }
        catch (Exception e) {
            hadError = true;
            System.out.println((Object)e);
            System.out.println(currentQuest + " skill.");
        }
        final long endTime = System.currentTimeMillis();
        final double elapsedSeconds = (double)(endTime - startTime) / 1000.0;
        final int elapsedSecs = (int)elapsedSeconds % 60;
        final int elapsedMinutes = (int)(elapsedSeconds / 60.0);
        String withErrors = "";
        if (hadError) {
            withErrors = " with errors";
        }
        System.out.println("Finished" + withErrors + " in " + elapsedMinutes + " minutes " + elapsedSecs + " seconds");
    }
}
