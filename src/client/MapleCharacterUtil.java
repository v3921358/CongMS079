package client;

import tools.FilePrinter;
import tools.Pair;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import tools.FileoutputUtil;
import database.DBConPool;
import constants.GameConstants;
import java.util.regex.Pattern;

public class MapleCharacterUtil
{
    private static final Pattern namePattern;
    private static final Pattern petPattern;
    
    public static final boolean canCreateChar(final String name) {
        return getIdByName(name) == -1 && isEligibleCharName(name);
    }
    
    public static final boolean isEligibleCharName(final String name) {
        if (name.length() > 15) {
            return false;
        }
        if (name.length() < 2) {
            return false;
        }
        for (final String z : GameConstants.RESERVED) {
            if (name.contains((CharSequence)z)) {
                return false;
            }
        }
        return true;
    }
    
    public static final boolean canChangePetName(final String name) {
        if (MapleCharacterUtil.petPattern.matcher((CharSequence)name).matches()) {
            for (final String z : GameConstants.RESERVED) {
                if (name.contains((CharSequence)z)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static final String makeMapleReadable(final String in) {
        String wui = in.replace('I', 'i');
        wui = wui.replace('l', 'L');
        wui = wui.replace((CharSequence)"rn", (CharSequence)"Rn");
        wui = wui.replace((CharSequence)"vv", (CharSequence)"Vv");
        wui = wui.replace((CharSequence)"VV", (CharSequence)"Vv");
        return wui;
    }
    
    public static final int getIdByName(final String name) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT id FROM characters WHERE name = ?")) {
            ps.setString(1, name);
            int id;
            try (final ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    rs.close();
                    ps.close();
                    return -1;
                }
                id = rs.getInt("id");
            }
            return id;
        }
        catch (SQLException e) {
            System.err.println("error 'getIdByName' " + (Object)e);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            return -1;
        }
    }
    
    public static final boolean PromptPoll(final int accountid) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean prompt = false;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            ps = con.prepareStatement("SELECT * from game_poll_reply where AccountId = ?");
            ps.setInt(1, accountid);
            rs = ps.executeQuery();
            prompt = !rs.next();
        }
        catch (SQLException e) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException e2) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e2);
            }
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException e2) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e2);
            }
        }
        return prompt;
    }
    
    public static final boolean SetPoll(final int accountid, final int selection) {
        if (!PromptPoll(accountid)) {
            return false;
        }
        PreparedStatement ps = null;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            ps = con.prepareStatement("INSERT INTO game_poll_reply (AccountId, SelectAns) VALUES (?, ?)");
            ps.setInt(1, accountid);
            ps.setInt(2, selection);
            ps.execute();
        }
        catch (SQLException e) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            try {
                if (ps != null) {
                    ps.close();
                }
            }
            catch (SQLException e2) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e2);
            }
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            }
            catch (SQLException e2) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e2);
            }
        }
        return true;
    }
    
    private static boolean check_ifPasswordEquals(final String passhash, final String pwd, final String salt) {
        return (LoginCryptoLegacy.isLegacyPassword(passhash) && LoginCryptoLegacy.checkPassword(pwd, passhash)) || (salt == null && LoginCrypto.checkSha1Hash(passhash, pwd)) || LoginCrypto.checkSaltedSha512Hash(passhash, pwd, salt);
    }
    
    public static Pair<Integer, Pair<Integer, Integer>> getInfoByName(final String name, final int world) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE name = ? AND world = ?")) {
            ps.setString(1, name);
            ps.setInt(2, world);
            Pair<Integer, Pair<Integer, Integer>> id;
            try (final ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    rs.close();
                    ps.close();
                    return null;
                }
                id = new Pair<Integer, Pair<Integer, Integer>>(Integer.valueOf(rs.getInt("id")), new Pair<Integer, Integer>(Integer.valueOf(rs.getInt("accountid")), Integer.valueOf(rs.getInt("gender"))));
            }
            return id;
        }
        catch (Exception e) {
            FilePrinter.printError("MapleCharacterUtil.txt", (Throwable)e);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            return null;
        }
    }
    
    public static void setNXCodeUsed(final String name, final String code) throws SQLException {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("UPDATE nxcode SET `user` = ?, `valid` = 0 WHERE code = ?")) {
            ps.setString(1, name);
            ps.setString(2, code);
            ps.execute();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
    }
    
    public static void sendNote(final String to, final String name, final String msg, final int fame) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("INSERT INTO notes (`to`, `from`, `message`, `timestamp`, `gift`) VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, to);
            ps.setString(2, name);
            ps.setString(3, msg);
            ps.setLong(4, System.currentTimeMillis());
            ps.setInt(5, fame);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            FilePrinter.printError("MapleCharacterUtil.txt", (Throwable)e);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
    }
    
    public static boolean getNXCodeValid(final String code, boolean validcode) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT `valid` FROM nxcode WHERE code = ?")) {
            ps.setString(1, code);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    validcode = (rs.getInt("valid") > 0);
                }
            }
        }
        catch (SQLException ex) {
            FilePrinter.printError("MapleCharacterUtil.txt", (Throwable)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        return validcode;
    }
    
    public static int getNXCodeType(final String code) {
        int type = -1;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT `type` FROM nxcode WHERE code = ?")) {
            ps.setString(1, code);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    type = rs.getInt("type");
                }
            }
        }
        catch (SQLException ex) {
            FilePrinter.printError("MapleCharacterUtil.txt", (Throwable)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        return type;
    }
    
    public static int getNXCodeItem(final String code) {
        int item = -1;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT `item` FROM nxcode WHERE code = ?")) {
            ps.setString(1, code);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    item = rs.getInt("item");
                }
            }
        }
        catch (SQLException ex) {
            FilePrinter.printError("MapleCharacterUtil.txt", (Throwable)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        return item;
    }
    
    public static int getNXCodeSize(final String code) {
        int item = -1;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT `size` FROM nxcode WHERE code = ?")) {
            ps.setString(1, code);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    item = rs.getInt("size");
                }
            }
        }
        catch (SQLException ex) {
            FilePrinter.printError("MapleCharacterUtil.txt", (Throwable)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        return item;
    }
    
    public static int getNXCodeTime(final String code) {
        int item = -1;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT `time` FROM nxcode WHERE code = ?")) {
            ps.setString(1, code);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    item = rs.getInt("time");
                }
            }
        }
        catch (SQLException ex) {
            FilePrinter.printError("MapleCharacterUtil.txt", (Throwable)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        return item;
    }
    
    static {
        namePattern = Pattern.compile("[a-zA-Z0-9_-]{3,12}");
        petPattern = Pattern.compile("[a-zA-Z0-9_-]{4,12}");
    }
}
