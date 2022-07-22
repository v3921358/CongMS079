package handling.world.guild;

import handling.world.World.Guild;
import tools.MaplePacketCreator;
import handling.world.World.Alliance;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import tools.FileoutputUtil;
import database.DBConPool;
import java.io.Serializable;

public class MapleGuildAlliance implements Serializable
{
    public static final long serialVersionUID = 24081985245L;
    public static final int CHANGE_CAPACITY_COST = 10000000;
    private final int[] guilds;
    private int allianceid;
    private int leaderid;
    private int capacity;
    private String name;
    private String notice;
    private String[] ranks;
    
    public MapleGuildAlliance(final int id) {
        this.guilds = new int[5];
        this.ranks = new String[5];
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM alliances WHERE id = ?")) {
            ps.setInt(1, id);
            final ResultSet rs = ps.executeQuery();
            if (!rs.first()) {
                rs.close();
                ps.close();
                this.allianceid = -1;
                return;
            }
            this.allianceid = id;
            this.name = rs.getString("name");
            this.capacity = rs.getInt("capacity");
            for (int i = 1; i < 6; ++i) {
                this.guilds[i - 1] = rs.getInt("guild" + i);
                this.ranks[i - 1] = rs.getString("rank" + i);
            }
            this.leaderid = rs.getInt("leaderid");
            this.notice = rs.getString("notice");
            rs.close();
        }
        catch (SQLException se) {
            System.err.println("unable to read guild information from sql");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)se);
        }
    }
    
    public static final Collection<MapleGuildAlliance> loadAll() {
        final Collection<MapleGuildAlliance> ret = new ArrayList<MapleGuildAlliance>();
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT id FROM alliances");
             final ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                final MapleGuildAlliance g = new MapleGuildAlliance(rs.getInt("id"));
                if (g.getId() > 0) {
                    ret.add(g);
                }
            }
        }
        catch (SQLException se) {
            System.err.println("unable to read guild information from sql");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)se);
        }
        return ret;
    }
    
    public int getNoGuilds() {
        int ret = 0;
        for (int i = 0; i < this.capacity; ++i) {
            if (this.guilds[i] > 0) {
                ++ret;
            }
        }
        return ret;
    }
    
    public static final int createToDb(final int leaderId, final String name, final int guild1, final int guild2) {
        int ret = -1;
        if (name.length() > 12) {
            return ret;
        }
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id FROM alliances WHERE name = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                rs.close();
                ps.close();
                return ret;
            }
            ps.close();
            rs.close();
            ps = con.prepareStatement("insert into alliances (name, guild1, guild2, leaderid) VALUES (?, ?, ?, ?)", 1);
            ps.setString(1, name);
            ps.setInt(2, guild1);
            ps.setInt(3, guild2);
            ps.setInt(4, leaderId);
            ps.execute();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ret = rs.getInt(1);
            }
            rs.close();
            ps.close();
        }
        catch (SQLException SE) {
            System.err.println("SQL THROW");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)SE);
        }
        return ret;
    }
    
    public final boolean deleteAlliance() {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            for (int i = 0; i < this.getNoGuilds(); ++i) {
                final PreparedStatement ps = con.prepareStatement("UPDATE characters SET alliancerank = 5 WHERE guildid = ?");
                ps.setInt(1, this.guilds[i]);
                ps.execute();
                ps.close();
            }
            final PreparedStatement ps = con.prepareStatement("delete from alliances where id = ?");
            ps.setInt(1, this.allianceid);
            ps.execute();
            ps.close();
        }
        catch (SQLException SE) {
            System.err.println("SQL THROW" + (Object)SE);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)SE);
            return false;
        }
        return true;
    }
    
    public final void broadcast(final byte[] packet) {
        this.broadcast(packet, -1, GAOp.NONE, false);
    }
    
    public final void broadcast(final byte[] packet, final int exception) {
        this.broadcast(packet, exception, GAOp.NONE, false);
    }
    
    public final void broadcast(final byte[] packet, final int exceptionId, final GAOp op, final boolean expelled) {
        if (op == GAOp.DISBAND) {
            Alliance.setOldAlliance(exceptionId, expelled, this.allianceid);
        }
        else if (op == GAOp.NEWGUILD) {
            Alliance.setNewAlliance(exceptionId, this.allianceid);
        }
        else {
            Alliance.sendGuild(packet, exceptionId, this.allianceid);
        }
    }
    
    public final boolean disband() {
        final boolean ret = this.deleteAlliance();
        if (ret) {
            this.broadcast(null, -1, GAOp.DISBAND, false);
        }
        return ret;
    }
    
    public final void saveToDb() {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("UPDATE alliances set guild1 = ?, guild2 = ?, guild3 = ?, guild4 = ?, guild5 = ?, rank1 = ?, rank2 = ?, rank3 = ?, rank4 = ?, rank5 = ?, capacity = ?, leaderid = ?, notice = ? where id = ?")) {
            for (int i = 0; i < 5; ++i) {
                ps.setInt(i + 1, (this.guilds[i] < 0) ? 0 : this.guilds[i]);
                ps.setString(i + 6, this.ranks[i]);
            }
            ps.setInt(11, this.capacity);
            ps.setInt(12, this.leaderid);
            ps.setString(13, this.notice);
            ps.setInt(14, this.allianceid);
            ps.executeUpdate();
        }
        catch (SQLException SE) {
            System.err.println("SQL THROW");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)SE);
        }
    }
    
    public void setRank(final String[] ranks) {
        this.ranks = ranks;
        this.broadcast(MaplePacketCreator.getAllianceUpdate(this));
        this.saveToDb();
    }
    
    public String getRank(final int rank) {
        return this.ranks[rank - 1];
    }
    
    public String[] getRanks() {
        return this.ranks;
    }
    
    public String getNotice() {
        return this.notice;
    }
    
    public void setNotice(final String newNotice) {
        this.notice = newNotice;
        this.broadcast(MaplePacketCreator.getAllianceUpdate(this));
        this.broadcast(MaplePacketCreator.serverNotice(5, "联盟公告事項 : " + newNotice));
        this.saveToDb();
    }
    
    public int getGuildId(final int i) {
        return this.guilds[i];
    }
    
    public int getId() {
        return this.allianceid;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getCapacity() {
        return this.capacity;
    }
    
    public boolean setCapacity() {
        if (this.capacity >= 5) {
            return false;
        }
        ++this.capacity;
        this.broadcast(MaplePacketCreator.getAllianceUpdate(this));
        this.saveToDb();
        return true;
    }
    
    public boolean addGuild(final int guildid) {
        if (this.getNoGuilds() >= this.getCapacity()) {
            return false;
        }
        this.guilds[this.getNoGuilds()] = guildid;
        this.saveToDb();
        this.broadcast(null, guildid, GAOp.NEWGUILD, false);
        return true;
    }
    
    public boolean removeGuild(final int guildid, final boolean expelled) {
        int i = 0;
        while (i < this.getNoGuilds()) {
            if (this.guilds[i] == guildid) {
                this.broadcast(null, guildid, GAOp.DISBAND, expelled);
                if (i > 0 && i != this.getNoGuilds() - 1) {
                    for (int x = i + 1; x < this.getNoGuilds(); ++x) {
                        if (this.guilds[x] > 0) {
                            this.guilds[x - 1] = this.guilds[x];
                            if (x == this.getNoGuilds() - 1) {
                                this.guilds[x] = -1;
                            }
                        }
                    }
                }
                else {
                    this.guilds[i] = -1;
                }
                if (i == 0) {
                    return this.disband();
                }
                this.broadcast(MaplePacketCreator.getAllianceUpdate(this));
                this.broadcast(MaplePacketCreator.getGuildAlliance(this));
                this.saveToDb();
                return true;
            }
            else {
                ++i;
            }
        }
        return false;
    }
    
    public int getLeaderId() {
        return this.leaderid;
    }
    
    public boolean setLeaderId(final int c) {
        if (this.leaderid == c) {
            return false;
        }
        int g = -1;
        String leaderName = null;
        for (int i = 0; i < this.getNoGuilds(); ++i) {
            final MapleGuild g_ = Guild.getGuild(this.guilds[i]);
            if (g_ != null) {
                final MapleGuildCharacter newLead = g_.getMGC(c);
                final MapleGuildCharacter oldLead = g_.getMGC(this.leaderid);
                if (newLead != null && oldLead != null) {
                    return false;
                }
                if (newLead != null && newLead.getGuildRank() == 1 && newLead.getAllianceRank() == 2) {
                    g_.changeARank(c, 1);
                    g = i;
                    leaderName = newLead.getName();
                }
                else if (oldLead != null && oldLead.getGuildRank() == 1 && oldLead.getAllianceRank() == 1) {
                    g_.changeARank(this.leaderid, 2);
                }
                else if (oldLead != null || newLead != null) {
                    return false;
                }
            }
        }
        if (g == -1) {
            return false;
        }
        final int oldGuild = this.guilds[g];
        this.guilds[g] = this.guilds[0];
        this.guilds[0] = oldGuild;
        if (leaderName != null) {
            this.broadcast(MaplePacketCreator.serverNotice(5, leaderName + " has become the leader of the alliance."));
        }
        this.broadcast(MaplePacketCreator.changeAllianceLeader(this.allianceid, this.leaderid, c));
        this.broadcast(MaplePacketCreator.updateAllianceLeader(this.allianceid, this.leaderid, c));
        this.broadcast(MaplePacketCreator.getAllianceUpdate(this));
        this.broadcast(MaplePacketCreator.getGuildAlliance(this));
        this.leaderid = c;
        this.saveToDb();
        return true;
    }
    
    public boolean changeAllianceRank(final int cid, final int change) {
        if (this.leaderid == cid || change < 0 || change > 1) {
            return false;
        }
        for (int i = 0; i < this.getNoGuilds(); ++i) {
            final MapleGuild g_ = Guild.getGuild(this.guilds[i]);
            if (g_ != null) {
                final MapleGuildCharacter chr = g_.getMGC(cid);
                if (chr != null && chr.getAllianceRank() > 2) {
                    if ((change == 0 && chr.getAllianceRank() >= 5) || (change == 1 && chr.getAllianceRank() <= 3)) {
                        return false;
                    }
                    g_.changeARank(cid, chr.getAllianceRank() + ((change == 0) ? 1 : -1));
                    return true;
                }
            }
        }
        return false;
    }
    
    private enum GAOp
    {
        NONE, 
        DISBAND, 
        NEWGUILD;
    }
}
