package server.events;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import tools.FileoutputUtil;
import tools.FilePrinter;
import database.DBConPool;
import java.util.Iterator;
import server.Randomizer;
import java.util.Map.Entry;
import java.util.HashMap;
import tools.Pair;
import java.util.Map;

public class MapleOxQuizFactory
{
    private boolean initialized;
    private final Map<Pair<Integer, Integer>, MapleOxQuizEntry> questionCache;
    private static final MapleOxQuizFactory instance;
    
    public MapleOxQuizFactory() {
        this.initialized = false;
        this.questionCache = new HashMap<Pair<Integer, Integer>, MapleOxQuizEntry>();
    }
    
    public static MapleOxQuizFactory getInstance() {
        return MapleOxQuizFactory.instance;
    }
    
    public boolean hasInitialized() {
        return this.initialized;
    }
    
    public Entry<Pair<Integer, Integer>, MapleOxQuizEntry> grabRandomQuestion() {
        final int size = this.questionCache.size();
        Entry<Pair<Integer, Integer>, MapleOxQuizEntry> oxquiz = null;
    Block_2:
        while (true) {
            final Iterator<Entry<Pair<Integer, Integer>, MapleOxQuizEntry>> iterator = this.questionCache.entrySet().iterator();
            while (iterator.hasNext()) {
                oxquiz = (Entry<Pair<Integer, Integer>, MapleOxQuizEntry>)iterator.next();
                if (Randomizer.nextInt(size) == 0) {
                    break Block_2;
                }
            }
        }
        return oxquiz;
    }
    
    public void initialize() {
        if (this.initialized) {
            return;
        }
        System.out.println("[正在加载] -> 游戏QX问答系统");
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM wz_oxdata");
             final ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                this.questionCache.put(new Pair<Integer, Integer>(Integer.valueOf(rs.getInt("questionset")), Integer.valueOf(rs.getInt("questionid"))), this.get(rs));
            }
        }
        catch (Exception e) {
            FilePrinter.printError("MapleOxQuizEntry.txt", (Throwable)e);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
        this.initialized = true;
    }
    
    public MapleOxQuizEntry getFromSQL(final String sql) {
        MapleOxQuizEntry ret = null;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement(sql);
             final ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                ret = this.get(rs);
            }
        }
        catch (Exception e) {
            FilePrinter.printError("MapleOxQuizEntry.txt", (Throwable)e);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
        return ret;
    }
    
    public static MapleOxQuizEntry getOxEntry(final int questionSet, final int questionId) {
        return getInstance().getOxQuizEntry(new Pair<Integer, Integer>(Integer.valueOf(questionSet), Integer.valueOf(questionId)));
    }
    
    public static MapleOxQuizEntry getOxEntry(final Pair<Integer, Integer> pair) {
        return getInstance().getOxQuizEntry(pair);
    }
    
    public MapleOxQuizEntry getOxQuizEntry(final Pair<Integer, Integer> pair) {
        MapleOxQuizEntry mooe = (MapleOxQuizEntry)this.questionCache.get((Object)pair);
        if (mooe == null) {
            if (this.initialized) {
                return null;
            }
            mooe = this.getFromSQL("SELECT * FROM wz_oxdata WHERE questionset = " + (Object)pair.getLeft() + " AND questionid = " + (Object)pair.getRight());
            this.questionCache.put(pair, mooe);
        }
        return mooe;
    }
    
    private MapleOxQuizEntry get(final ResultSet rs) throws SQLException {
        return new MapleOxQuizEntry(rs.getString("question"), rs.getString("display"), this.getAnswerByText(rs.getString("answer")), rs.getInt("questionset"), rs.getInt("questionid"));
    }
    
    private int getAnswerByText(final String text) {
        if (text.equalsIgnoreCase("x")) {
            return 0;
        }
        if (text.equalsIgnoreCase("o")) {
            return 1;
        }
        return -1;
    }
    
    public void reloadOX() {
        this.questionCache.clear();
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM wz_oxdata");
             final ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                this.questionCache.put(new Pair<Integer, Integer>(Integer.valueOf(rs.getInt("questionset")), Integer.valueOf(rs.getInt("questionid"))), this.get(rs));
            }
        }
        catch (Exception e) {
            FilePrinter.printError("MapleOxQuizEntry.txt", (Throwable)e);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
    }
    
    static {
        instance = new MapleOxQuizFactory();
    }
    
    public static class MapleOxQuizEntry
    {
        private final String question;
        private final String answerText;
        private final int answer;
        private final int questionset;
        private final int questionid;
        
        public MapleOxQuizEntry(final String question, final String answerText, final int answer, final int questionset, final int questionid) {
            this.question = question;
            this.answerText = answerText;
            this.answer = answer;
            this.questionset = questionset;
            this.questionid = questionid;
        }
        
        public String getQuestion() {
            return this.question;
        }
        
        public String getAnswerText() {
            return this.answerText;
        }
        
        public int getAnswer() {
            return this.answer;
        }
        
        public int getQuestionSet() {
            return this.questionset;
        }
        
        public int getQuestionId() {
            return this.questionid;
        }
    }
}
