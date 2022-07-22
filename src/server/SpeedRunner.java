package server;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import tools.FileoutputUtil;
import java.util.LinkedHashMap;
import tools.StringUtil;
import database.DBConPool;
import java.sql.SQLException;
import tools.FilePrinter;
import java.util.EnumMap;
import tools.Pair;
import server.maps.SpeedRunType;
import java.util.Map;

public class SpeedRunner
{
    private static final SpeedRunner instance;
    private final Map<SpeedRunType, Pair<String, Map<Integer, String>>> speedRunData;
    
    private SpeedRunner() {
        this.speedRunData = new EnumMap<SpeedRunType, Pair<String, Map<Integer, String>>>(SpeedRunType.class);
    }
    
    public static final SpeedRunner getInstance() {
        return SpeedRunner.instance;
    }
    
    public final Pair<String, Map<Integer, String>> getSpeedRunData(final SpeedRunType type) {
        return (Pair<String, Map<Integer, String>>)this.speedRunData.get((Object)type);
    }
    
    public final void addSpeedRunData(final SpeedRunType type, final Pair<StringBuilder, Map<Integer, String>> mib) {
        this.speedRunData.put(type, new Pair<String, Map<Integer, String>>(((StringBuilder)mib.getLeft()).toString(), mib.getRight()));
    }
    
    public final void removeSpeedRunData(final SpeedRunType type) {
        this.speedRunData.remove((Object)type);
    }
    
    public final void loadSpeedRuns() {
        if (this.speedRunData.size() > 0) {
            return;
        }
        for (final SpeedRunType type : SpeedRunType.values()) {
            try {
                this.loadSpeedRunData(type);
            }
            catch (SQLException ex) {
                FilePrinter.printError("loadSpeedRuns.txt", (Throwable)ex);
            }
        }
    }
    
    public final void loadSpeedRunData(final SpeedRunType type) throws SQLException {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM speedruns WHERE type = ? ORDER BY time LIMIT 25")) {
            ps.setString(1, type.name());
            final StringBuilder ret = new StringBuilder("#rThese are the speedrun times for " + StringUtil.makeEnumHumanReadable(type.name()) + ".#k\r\n\r\n");
            final Map<Integer, String> rett = new LinkedHashMap<Integer, String>();
            boolean changed;
            try (final ResultSet rs = ps.executeQuery()) {
                int rank = 1;
                for (boolean cont = changed = rs.first(); cont; cont = rs.next()) {
                    this.addSpeedRunData(ret, rett, rs.getString("members"), rs.getString("leader"), rank, rs.getString("timestring"));
                    ++rank;
                }
            }
            if (changed) {
                this.speedRunData.put(type, new Pair<String, Map<Integer, String>>(ret.toString(), rett));
            }
        }
        catch (SQLException ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
    }
    
    public final Pair<StringBuilder, Map<Integer, String>> addSpeedRunData(final StringBuilder ret, final Map<Integer, String> rett, final String members, final String leader, final int rank, final String timestring) {
        final StringBuilder rettt = new StringBuilder();
        final String[] membrz = members.split(",");
        rettt.append("#b該遠征队 ").append(leader).append("'成功挑戰排名為 ").append(rank).append(".#k\r\n\r\n");
        for (int i = 0; i < membrz.length; ++i) {
            rettt.append("#r#e");
            rettt.append(i + 1);
            rettt.append(".#n ");
            rettt.append(membrz[i]);
            rettt.append("#k\r\n");
        }
        rett.put(Integer.valueOf(rank), rettt.toString());
        ret.append("#b");
        if (membrz.length > 1) {
            ret.append("#L");
            ret.append(rank);
            ret.append("#");
        }
        ret.append("Rank #e");
        ret.append(rank);
        ret.append("#n#k : ");
        ret.append(leader);
        ret.append(", in ");
        ret.append(timestring);
        if (membrz.length > 1) {
            ret.append("#l");
        }
        ret.append("\r\n");
        return new Pair<StringBuilder, Map<Integer, String>>(ret, rett);
    }
    
    static {
        instance = new SpeedRunner();
    }
}
