package client;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import tools.FileoutputUtil;
import database.DBConPool;

public class BuddyEntry
{
    private final String name;
    private String group;
    private final int characterId;
    private final int level;
    private final int job;
    private boolean visible;
    private int channel;
    
    public BuddyEntry(final String name, final int characterId, final String group, final int channel, final boolean visible, final int level, final int job) {
        this.name = name;
        this.characterId = characterId;
        this.group = group;
        this.channel = channel;
        this.visible = visible;
        this.level = level;
        this.job = job;
    }
    
    public int getChannel() {
        return this.channel;
    }
    
    public void setChannel(final int channel) {
        this.channel = channel;
    }
    
    public boolean isOnline() {
        return this.channel >= 0;
    }
    
    public void setOffline() {
        this.channel = -1;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getCharacterId() {
        return this.characterId;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public int getJob() {
        return this.job;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public String getGroup() {
        return this.group;
    }
    
    public void setGroup(final String newGroup) {
        this.group = newGroup;
    }
    
    public static BuddyEntry getByNameFromDB(final String buddyName) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("SELECT id, name, level, job FROM characters WHERE name = ?");
            ps.setString(1, buddyName);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new BuddyEntry(rs.getString("name"), rs.getInt("id"), "其他", -1, false, rs.getInt("level"), rs.getInt("job"));
            }
            return null;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            return null;
        }
    }
    
    public static BuddyEntry getByIdfFromDB(final int buddyCharId) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("SELECT id, name, level, job FROM characters WHERE id = ?");
            ps.setInt(1, buddyCharId);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new BuddyEntry(rs.getString("name"), rs.getInt("id"), "其他", -1, true, rs.getInt("level"), rs.getInt("job"));
            }
            return null;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + this.characterId;
        return result;
    }
    
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        final BuddyEntry other = (BuddyEntry)obj;
        return this.characterId == other.characterId;
    }
}
