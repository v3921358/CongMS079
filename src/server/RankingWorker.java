package server;

import java.util.Iterator;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import tools.FileoutputUtil;
import database.DBConPool;
import java.util.HashMap;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class RankingWorker
{
    private final Map<Integer, List<RankingInformation>> rankings;
    private final Map<String, Integer> jobCommands;
    private static RankingWorker instance;
    private Connection con;
    
    public RankingWorker() {
        this.rankings = new HashMap<Integer, List<RankingInformation>>();
        this.jobCommands = new HashMap<String, Integer>();
    }
    
    public static final RankingWorker getInstance() {
        if (RankingWorker.instance == null) {
            RankingWorker.instance = new RankingWorker();
        }
        return RankingWorker.instance;
    }
    
    public final Integer getJobCommand(final String job) {
        return Integer.valueOf(this.jobCommands.get((Object)job));
    }
    
    public final Map<String, Integer> getJobCommands() {
        return this.jobCommands;
    }
    
    public final List<RankingInformation> getRankingInfo(final int job) {
        return (List<RankingInformation>)this.rankings.get((Object)Integer.valueOf(job));
    }
    
    public final void run() {
        System.out.println("[正在加载] -> 游戏徘行榜系统");
        this.loadJobCommands();
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            this.updateRanking(con);
        }
        catch (Exception ex) {
            System.err.println("Could not update rankings");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
    }
    
    private void updateRanking(final Connection con) throws Exception {
        final StringBuilder sb = new StringBuilder("SELECT c.id, c.job, c.exp, c.level, c.name, c.jobRank, c.jobRankMove, c.rank, c.rankMove");
        sb.append(", a.lastlogin AS lastlogin, a.loggedin FROM characters AS c LEFT JOIN accounts AS a ON c.accountid = a.id WHERE c.gm = 0 AND a.banned = 0 ");
        sb.append("ORDER BY c.level DESC , c.exp DESC , c.fame DESC , c.meso DESC , c.rank ASC");
        PreparedStatement ps;
        try (final PreparedStatement charSelect = con.prepareStatement(sb.toString());
             final ResultSet rs = charSelect.executeQuery()) {
            ps = con.prepareStatement("UPDATE characters SET jobRank = ?, jobRankMove = ?, rank = ?, rankMove = ? WHERE id = ?");
            int rank = 0;
            final Map<Integer, Integer> rankMap = new LinkedHashMap<Integer, Integer>();
            final Iterator<Integer> iterator = this.jobCommands.values().iterator();
            while (iterator.hasNext()) {
                final int i = (int)Integer.valueOf(iterator.next());
                rankMap.put(Integer.valueOf(i), Integer.valueOf(0));
                this.rankings.put(Integer.valueOf(i), new ArrayList<RankingInformation>());
            }
            while (rs.next()) {
                final int job = rs.getInt("job");
                if (!rankMap.containsKey((Object)Integer.valueOf(job / 100))) {
                    continue;
                }
                final int jobRank = (int)Integer.valueOf(rankMap.get((Object)Integer.valueOf(job / 100))) + 1;
                rankMap.put(Integer.valueOf(job / 100), Integer.valueOf(jobRank));
                ++rank;
                ((List<RankingInformation>)this.rankings.get((Object)Integer.valueOf(-1))).add(new RankingInformation(rs.getString("name"), job, rs.getInt("level"), rs.getInt("exp"), rank));
                ((List<RankingInformation>)this.rankings.get((Object)Integer.valueOf(job / 100))).add(new RankingInformation(rs.getString("name"), job, rs.getInt("level"), rs.getInt("exp"), jobRank));
                ps.setInt(1, jobRank);
                ps.setInt(2, rs.getInt("jobRank") - jobRank);
                ps.setInt(3, rank);
                ps.setInt(4, rs.getInt("rank") - rank);
                ps.setInt(5, rs.getInt("id"));
                ps.addBatch();
            }
            ps.executeBatch();
        }
        ps.close();
    }
    
    public final void loadJobCommands() {
        this.jobCommands.put("all", Integer.valueOf(-1));
        this.jobCommands.put("beginner", Integer.valueOf(0));
        this.jobCommands.put("warrior", Integer.valueOf(1));
        this.jobCommands.put("magician", Integer.valueOf(2));
        this.jobCommands.put("bowman", Integer.valueOf(3));
        this.jobCommands.put("thief", Integer.valueOf(4));
        this.jobCommands.put("pirate", Integer.valueOf(5));
    }
    
    public static class RankingInformation
    {
        public int job;
        public int level;
        public int exp;
        public int rank;
        public String name;
        public String toString;
        
        public RankingInformation(final String name, final int job, final int level, final int exp, final int rank) {
            this.name = name;
            this.job = job;
            this.level = level;
            this.exp = exp;
            this.rank = rank;
            this.loadToString();
        }
        
        public final void loadToString() {
            final StringBuilder builder = new StringBuilder("Rank ");
            builder.append(this.rank);
            builder.append(" : ");
            builder.append(this.name);
            builder.append(" - Level ");
            builder.append(this.level);
            builder.append(" ");
            builder.append(MapleCarnivalChallenge.getJobNameById(this.job));
            builder.append(" | ");
            builder.append(this.exp);
            builder.append(" EXP");
            this.toString = builder.toString();
        }
        
        @Override
        public String toString() {
            return this.toString;
        }
    }
}
