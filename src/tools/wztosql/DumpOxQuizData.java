package tools.wztosql;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.sql.PreparedStatement;
import java.sql.Connection;
import provider.MapleDataProvider;
import tools.FileoutputUtil;
import provider.MapleDataTool;
import provider.MapleData;
import database.DBConPool;
import provider.MapleDataProviderFactory;
import java.io.File;
import server.ServerProperties;
import java.sql.SQLException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.charset.CharsetEncoder;

public class DumpOxQuizData
{
    static CharsetEncoder asciiEncoder;
    
    public static void main(final String[] args) throws FileNotFoundException, IOException, SQLException {
        System.out.println("OXQuiz.img");
        final DumpOxQuizData dump = new DumpOxQuizData();
        dump.dumpOxData();
        System.out.println("Ox quiz 讀取資料完成。");
    }
    
    public void dumpOxData() throws SQLException {
        final MapleDataProvider stringProvider = MapleDataProviderFactory.getDataProvider(new File(ServerProperties.getProperty("server.wzpath") + "/Etc.wz"));
        final MapleData ox = stringProvider.getData("OXQuiz.img");
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM `wz_oxdata`");
            ps.execute();
            ps.close();
            for (final MapleData child1 : ox.getChildren()) {
                for (final MapleData child2 : child1.getChildren()) {
                    final MapleData q = child2.getChildByPath("q");
                    final MapleData d = child2.getChildByPath("d");
                    final int a = MapleDataTool.getInt(child2.getChildByPath("a"));
                    String qs = "";
                    String ds = "";
                    String as;
                    if (a == 0) {
                        as = "x";
                    }
                    else {
                        as = "o";
                    }
                    if (q != null) {
                        qs = (String)q.getData();
                    }
                    if (d != null) {
                        ds = (String)d.getData();
                    }
                    if (DumpOxQuizData.asciiEncoder.canEncode((CharSequence)child1.getName()) && DumpOxQuizData.asciiEncoder.canEncode((CharSequence)child2.getName()) && DumpOxQuizData.asciiEncoder.canEncode((CharSequence)qs) && DumpOxQuizData.asciiEncoder.canEncode((CharSequence)ds)) {
                        if (!DumpOxQuizData.asciiEncoder.canEncode((CharSequence)as)) {
                            continue;
                        }
                        ps = con.prepareStatement("INSERT INTO `wz_oxdata` (`questionset`, `questionid`, `question`, `display`, `answer`) VALUES (?, ?, ?, ?, ?)");
                        ps.setString(1, child1.getName());
                        ps.setString(2, child2.getName());
                        ps.setString(3, qs);
                        ps.setString(4, ds);
                        ps.setString(5, as);
                        ps.execute();
                        ps.close();
                    }
                }
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
    }
    
    static {
        DumpOxQuizData.asciiEncoder = Charset.forName("UTF-8").newEncoder();
    }
}
