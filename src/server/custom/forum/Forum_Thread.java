package server.custom.forum;

import java.util.Collection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import tools.FileoutputUtil;
import database.DatabaseConnection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Forum_Thread
{
    private int ThreadId;
    private int sectionId;
    private String threadName;
    private int characterId;
    private String characterName;
    private String releaseTime;
    private Long lastReply;
    private int up;
    private int down;
    private static ArrayList<Forum_Thread> allThread;
    
    public Forum_Thread() {
    }
    
    public Forum_Thread(final int threadId, final int sectionId, final String threadName, final int characterId, final String characterName, final String releaseTime, final int up, final int down, final long lastReply) {
        this.ThreadId = threadId;
        this.sectionId = sectionId;
        this.threadName = threadName;
        this.characterId = characterId;
        this.characterName = characterName;
        this.releaseTime = releaseTime;
        this.up = up;
        this.down = down;
        this.lastReply = Long.valueOf(lastReply);
    }
    
    public int getThreadId() {
        return this.ThreadId;
    }
    
    public void setThreadId(final int threadId) {
        this.ThreadId = threadId;
    }
    
    public int getSectionId() {
        return this.sectionId;
    }
    
    public void setSectionId(final int sectionId) {
        this.sectionId = sectionId;
    }
    
    public String getThreadName() {
        return this.threadName;
    }
    
    public void setThreadName(final String threadName) {
        this.threadName = threadName;
    }
    
    public int getCharacterId() {
        return this.characterId;
    }
    
    public void setCharacterId(final int characterId) {
        this.characterId = characterId;
    }
    
    public String getCharacterName() {
        return this.characterName;
    }
    
    public void setCharacterName(final String characterName) {
        this.characterName = characterName;
    }
    
    public String getReleaseTime() {
        return this.releaseTime;
    }
    
    public void setReleaseTime(final String releaseTime) {
        this.releaseTime = releaseTime;
    }
    
    public int getUp() {
        return this.up;
    }
    
    public void setUp(final int up) {
        this.up = up;
    }
    
    public int getDown() {
        return this.down;
    }
    
    public void setDown(final int down) {
        this.down = down;
    }
    
    public static ArrayList<Forum_Thread> getAllThread() {
        return Forum_Thread.allThread;
    }
    
    public static void setAllThread(final ArrayList<Forum_Thread> allThread) {
        Forum_Thread.allThread = allThread;
    }
    
    public static ArrayList<Forum_Thread> getCurrentAllThread(final int sid) {
        final ArrayList<Forum_Thread> CurrentThread = new ArrayList<Forum_Thread>();
        for (final Forum_Thread ft : Forum_Thread.allThread) {
            if (ft.getSectionId() == sid) {
                CurrentThread.add(ft);
            }
        }
        sortDesc((List<Forum_Thread>)CurrentThread);
        return CurrentThread;
    }
    
    public Long getLastReply() {
        return this.lastReply;
    }
    
    public void setLastReply(final Long lastReply) {
        this.lastReply = lastReply;
    }
    
    public static void sortDesc(final List<Forum_Thread> CurrentThread) {
        Collections.sort(CurrentThread, (Comparator<? super Forum_Thread>)new Comparator<Forum_Thread>() {
            @Override
            public int compare(final Forum_Thread o1, final Forum_Thread o2) {
                long value1 = 0L;
                long value2 = 0L;
                try {
                    value1 = (long)o1.getLastReply();
                    value2 = (long)o2.getLastReply();
                }
                catch (Exception e) {
                    value1 = 0L;
                    value2 = 0L;
                }
                return (int)((long)o2.getLastReply() - (long)o1.getLastReply());
            }
        });
    }
    
    public static ArrayList<Forum_Thread> loadAllThread() {
        final Connection con = DatabaseConnection.getConnection();
        try {
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM forum_thread  order by lastreply desc");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Forum_Thread.allThread.add(new Forum_Thread(rs.getInt("tid"), rs.getInt("sid"), rs.getString("tname"), rs.getInt("cid"), rs.getString("cname"), rs.getString("time"), rs.getInt("up"), rs.getInt("down"), rs.getLong("lastReply")));
            }
            rs.close();
            ps.close();
            Forum_Reply.loadAllReply();
            return Forum_Thread.allThread;
        }
        catch (SQLException ex) {
            FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", (Throwable)ex);
            return null;
        }
    }
    
    public static Forum_Thread getThreadById(final int sid, final int tid) {
        for (final Forum_Thread ft : Forum_Thread.allThread) {
            if (ft.getThreadId() == tid) {
                return ft;
            }
        }
        return null;
    }
    
    public static Forum_Thread getThreadByName(final int sid, final String name) {
        final ArrayList<Forum_Thread> allThread = getCurrentAllThread(sid);
        for (final Forum_Thread ft : allThread) {
            if (ft.getThreadName().equals((Object)name)) {
                return ft;
            }
        }
        return null;
    }
    
    public static Forum_Thread getThreadByNameToSql(final int sid, final String name) {
        final Connection con = DatabaseConnection.getConnection();
        try {
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM forum_thread WHERE sid = ? AND tname = ? order by lastReply desc");
            ps.setInt(1, sid);
            ps.setString(2, name);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Forum_Thread(rs.getInt("tid"), rs.getInt("sid"), rs.getString("tname"), rs.getInt("cid"), rs.getString("cname"), rs.getString("time"), rs.getInt("up"), rs.getInt("down"), rs.getLong("lastReply"));
            }
        }
        catch (SQLException ex) {
            FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", (Throwable)ex);
        }
        return null;
    }
    
    public static boolean addThread(final int sid, final String tname, final int cid, final String cname) {
        final Connection con = DatabaseConnection.getConnection();
        try {
            if (getThreadByName(sid, tname) != null) {
                return false;
            }
            final StringBuilder query = new StringBuilder();
            query.append("INSERT INTO forum_thread(sid, tname, cid, cname, lastreply) VALUES (?,?,?,?,?)");
            final PreparedStatement ps = con.prepareStatement(query.toString());
            ps.setInt(1, sid);
            ps.setString(2, tname);
            ps.setInt(3, cid);
            ps.setString(4, cname);
            ps.setLong(5, System.currentTimeMillis());
            ps.executeUpdate();
            ps.close();
            Forum_Thread.allThread.add(getThreadByNameToSql(sid, tname));
            return true;
        }
        catch (SQLException ex) {
            FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", (Throwable)ex);
            return false;
        }
    }
    
    public static int updateThreadReply(final int tid) {
        int ret = -1;
        final Connection con = DatabaseConnection.getConnection();
        try {
            final Forum_Thread thread = getThreadById(0, tid);
            final long now = System.currentTimeMillis();
            if (thread == null) {
                return -1;
            }
            final StringBuilder query = new StringBuilder();
            query.append("UPDATE forum_thread SET lastreply = ? WHERE tid = ?");
            final PreparedStatement ps = con.prepareStatement(query.toString());
            ps.setLong(1, now);
            ps.setInt(2, tid);
            ret = ps.executeUpdate();
            ps.close();
            if (ret > 0) {
                thread.setLastReply(Long.valueOf(now));
            }
            else {
                System.out.println("更新回复时间失败");
            }
            return ret;
        }
        catch (SQLException ex) {
            FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", (Throwable)ex);
            return ret;
        }
    }
    
    public static boolean deleteThread(final int sid, final int tid, final boolean isAll) {
        final Connection con = DatabaseConnection.getConnection();
        try {
            boolean isExist = false;
            if (isAll) {
                if (getCurrentAllThread(sid) != null) {
                    for (final Forum_Thread ft : Forum_Thread.allThread) {
                        if (ft.getSectionId() == sid) {
                            Forum_Reply.deleteReply(ft.getThreadId(), 0, true);
                        }
                    }
                    Forum_Thread.allThread.removeAll((Collection<?>)getCurrentAllThread(sid));
                    isExist = true;
                }
            }
            else if (getThreadById(sid, tid) != null) {
                Forum_Thread.allThread.remove((Object)getThreadById(sid, tid));
                Forum_Reply.deleteReply(tid, 0, true);
                isExist = true;
            }
            if (!isExist) {
                return isExist;
            }
            final StringBuilder query = new StringBuilder();
            if (isAll) {
                query.append("DELETE FROM forum_thread WHERE sid = ?");
            }
            else {
                query.append("DELETE FROM forum_thread WHERE sid = ? AND tid = ?");
            }
            final PreparedStatement ps = con.prepareStatement(query.toString());
            ps.setInt(1, sid);
            if (!isAll) {
                ps.setInt(2, tid);
            }
            ps.executeUpdate();
            ps.close();
            return true;
        }
        catch (SQLException ex) {
            FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", (Throwable)ex);
            return false;
        }
    }
    
    static {
        Forum_Thread.allThread = new ArrayList<Forum_Thread>();
    }
}
