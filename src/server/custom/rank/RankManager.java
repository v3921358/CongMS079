package server.custom.rank;

import client.MapleQuestStatus;
import server.quest.MapleQuest;
import client.MapleCharacter;
import java.util.Iterator;
import client.BuddyEntry;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collections;
import java.util.ArrayList;
import database.DatabaseConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankManager
{
    private Map<Integer, List<MiniGamePoints>> miniGamePointsMap;
    private Map<Integer, List<MiniGamePoints>> topMap;
    private long updateTime;
    private long lastUpdateTime;
    
    public static RankManager getInstance() {
        return InstanceHolder.instance;
    }
    
    private RankManager() {
        this.miniGamePointsMap = new HashMap<Integer, List<MiniGamePoints>>();
        this.topMap = new HashMap<Integer, List<MiniGamePoints>>();
        this.updateTime = 3600000L;
    }
    
    public void loadRank(final int quest) {
        final Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        final List<MiniGamePoints> miniGamePoints_list = new ArrayList<MiniGamePoints>();
        try {
            ps = con.prepareStatement("SELECT * FROM queststatus WHERE quest = ?");
            ps.setInt(1, quest);
            rs = ps.executeQuery();
            while (rs.next()) {
                final int characterid = rs.getInt("characterid");
                String customData = rs.getString("customData");
                if (customData == null || customData.length() < 5 || customData.indexOf(",") == -1) {
                    customData = "0,0,0";
                }
                final MiniGamePoints ngp = new MiniGamePoints();
                ngp.setCharacterid(characterid);
                ngp.setWins(Integer.parseInt(customData.split(",")[2]));
                ngp.setTies(Integer.parseInt(customData.split(",")[1]));
                ngp.setLosses(Integer.parseInt(customData.split(",")[0]));
                miniGamePoints_list.add(ngp);
            }
            Collections.sort(miniGamePoints_list);
            this.miniGamePointsMap.put(Integer.valueOf(quest), miniGamePoints_list);
        }
        catch (SQLException ex) {
            Logger.getLogger(RankManager.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            catch (SQLException ex2) {
                Logger.getLogger(RankManager.class.getName()).log(Level.SEVERE, null, (Throwable)ex2);
            }
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
            catch (SQLException ex2) {
                Logger.getLogger(RankManager.class.getName()).log(Level.SEVERE, null, (Throwable)ex2);
            }
        }
    }
    
    public static void main(final String[] args) {
    }
    
    public List<MiniGamePoints> getTop(final int gameType, final int top) {
        List<MiniGamePoints> ret = null;
        if (System.currentTimeMillis() - this.lastUpdateTime > this.updateTime) {
            this.topMap.clear();
            this.miniGamePointsMap.clear();
        }
        if (!this.miniGamePointsMap.containsKey((Object)Integer.valueOf(gameType))) {
            this.loadRank(gameType);
        }
        if (!this.miniGamePointsMap.containsKey((Object)Integer.valueOf(gameType))) {
            return null;
        }
        if (this.topMap.containsKey((Object)Integer.valueOf(gameType))) {
            return (List<MiniGamePoints>)this.topMap.get((Object)Integer.valueOf(gameType));
        }
        ret = new ArrayList<MiniGamePoints>();
        int topIndex = 0;
        for (final MiniGamePoints mgp : (List<MiniGamePoints>)this.miniGamePointsMap.get((Object)Integer.valueOf(gameType))) {
            if (topIndex >= 10) {
                break;
            }
            mgp.setBuddyEntry(BuddyEntry.getByIdfFromDB(mgp.getCharacterid()));
            ret.add(mgp);
            ++topIndex;
        }
        this.topMap.put(Integer.valueOf(gameType), ret);
        this.lastUpdateTime = System.currentTimeMillis();
        return ret;
    }
    
    public String getData(final MapleCharacter chr, final int GameType) {
        final MapleQuest quest = MapleQuest.getInstance(GameType);
        MapleQuestStatus record;
        if (chr.getQuestNoAdd(quest) == null) {
            record = chr.getQuestNAdd(quest);
            record.setCustomData("0,0,0");
        }
        else {
            record = chr.getQuestNoAdd(quest);
            if (record.getCustomData() == null || record.getCustomData().length() < 5 || record.getCustomData().indexOf(",") == -1) {
                record.setCustomData("0,0,0");
            }
        }
        return record.getCustomData();
    }
    
    public Map<Integer, List<MiniGamePoints>> getMiniGamePointsMap() {
        return this.miniGamePointsMap;
    }
    
    public void setMiniGamePointsMap(final Map<Integer, List<MiniGamePoints>> miniGamePointsMap) {
        this.miniGamePointsMap = miniGamePointsMap;
    }
    
    private static class InstanceHolder
    {
        public static final RankManager instance;
        
        static {
            instance = new RankManager();
        }
    }
}
