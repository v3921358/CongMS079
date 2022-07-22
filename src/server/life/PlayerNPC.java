package server.life;

import tools.MaplePacketCreator;
import client.MapleClient;
import client.inventory.MaplePet;
import java.util.Map.Entry;
import client.inventory.IItem;
import client.inventory.MapleInventoryType;
import handling.channel.ChannelServer;
import handling.world.World.Find;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;
import server.maps.MapleMap;
import client.MapleCharacter;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import tools.FileoutputUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import database.DBConPool;
import java.util.HashMap;
import java.sql.ResultSet;
import java.util.Map;

public final class PlayerNPC extends MapleNPC
{
    private Map<Byte, Integer> equips;
    private final int mapid;
    private final int charId;
    private int face;
    private int hair;
    private byte skin;
    private byte gender;
    private final int[] pets;
    
    public PlayerNPC(final ResultSet rs) throws SQLException {
        super(rs.getInt("ScriptId"), rs.getString("name"));
        this.equips = new HashMap<Byte, Integer>();
        this.pets = new int[] { 0, 0, 0 };
        this.hair = rs.getInt("hair");
        this.face = rs.getInt("face");
        this.mapid = rs.getInt("map");
        this.skin = rs.getByte("skin");
        this.charId = rs.getInt("charid");
        this.gender = rs.getByte("gender");
        this.setCoords(rs.getInt("x"), rs.getInt("y"), rs.getInt("dir"), rs.getInt("Foothold"));
        final String[] pet = rs.getString("pets").split(",");
        for (int i = 0; i < 3; ++i) {
            if (pet[i] != null) {
                this.pets[i] = Integer.parseInt(pet[i]);
            }
            else {
                this.pets[i] = 0;
            }
        }
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM playernpcs_equip WHERE NpcId = ?")) {
            ps.setInt(1, this.getId());
            try (final ResultSet rs2 = ps.executeQuery()) {
                while (rs2.next()) {
                    this.equips.put(Byte.valueOf(rs2.getByte("equippos")), Integer.valueOf(rs2.getInt("equipid")));
                }
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(PlayerNPC.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
    }
    
    public PlayerNPC(final MapleCharacter cid, final int npc, final MapleMap map, final MapleCharacter base) {
        super(npc, cid.getName());
        this.equips = new HashMap<Byte, Integer>();
        this.pets = new int[] { 0, 0, 0 };
        this.charId = cid.getId();
        this.mapid = map.getId();
        this.setCoords(base.getPosition().x, base.getPosition().y, 0, base.getFH());
        this.update(cid);
    }
    
    public void setCoords(final int x, final int y, final int f, final int fh) {
        this.setPosition(new Point(x, y));
        this.setCy(y);
        this.setRx0(x - 50);
        this.setRx1(x + 50);
        this.setF(f);
        this.setFh(fh);
    }
    
    public static void loadAll() {
        final List<PlayerNPC> toAdd = new ArrayList<PlayerNPC>();
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM playernpcs");
             final ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                toAdd.add(new PlayerNPC(rs));
            }
        }
        catch (Exception se) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)se);
        }
        for (final PlayerNPC npc : toAdd) {
            npc.addToServer();
        }
    }
    
    public static void updateByCharId(final MapleCharacter chr, final Connection con) {
        if (Find.findChannel(chr.getId()) > 0) {
            for (final PlayerNPC npc : ChannelServer.getInstance(Find.findChannel(chr.getId())).getAllPlayerNPC()) {
                npc.update(chr, con);
            }
        }
    }
    
    public void addToServer() {
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            cserv.addPlayerNPC(this);
        }
    }
    
    public void removeFromServer() {
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            cserv.removePlayerNPC(this);
        }
    }
    
    public void update(final MapleCharacter chr) {
        this.update(chr, null);
    }
    
    public void update(final MapleCharacter chr, final Connection con) {
        if (chr == null || this.charId != chr.getId()) {
            return;
        }
        this.setName(chr.getName());
        this.setHair(chr.getHair());
        this.setFace(chr.getFace());
        this.setSkin(chr.getSkinColor());
        this.setGender((int)chr.getGender());
        this.equips = new HashMap<Byte, Integer>();
        for (final IItem item : chr.getInventory(MapleInventoryType.EQUIPPED).list()) {
            if (item.getPosition() < -128) {
                continue;
            }
            this.equips.put(Byte.valueOf((byte)item.getPosition()), Integer.valueOf(item.getItemId()));
        }
        if (con != null) {
            this.saveToDB(con);
        }
        else {
            this.saveToDB();
        }
    }
    
    public void destroy() {
        this.destroy(false);
    }
    
    public void destroy(final boolean remove) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM playernpcs WHERE scriptid = ?");
            ps.setInt(1, this.getId());
            ps.executeUpdate();
            ps.close();
            ps = con.prepareStatement("DELETE FROM playernpcs_equip WHERE npcid = ?");
            ps.setInt(1, this.getId());
            ps.executeUpdate();
            ps.close();
            if (remove) {
                this.removeFromServer();
            }
        }
        catch (Exception se) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)se);
        }
    }
    
    public void saveToDB() {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            this.saveToDB(con);
        }
        catch (Exception se) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)se);
        }
    }
    
    public void saveToDB(final Connection con) {
        try {
            if (this.getNPCFromWZ() == null) {
                this.destroy(true);
                return;
            }
            this.destroy();
            PreparedStatement ps = con.prepareStatement("INSERT INTO playernpcs(name, hair, face, skin, x, y, map, charid, scriptid, foothold, dir, gender, pets) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, this.getName());
            ps.setInt(2, this.getHair());
            ps.setInt(3, this.getFace());
            ps.setInt(4, (int)this.getSkin());
            ps.setInt(5, this.getPosition().x);
            ps.setInt(6, this.getPosition().y);
            ps.setInt(7, this.getMapId());
            ps.setInt(8, this.getCharId());
            ps.setInt(9, this.getId());
            ps.setInt(10, this.getFh());
            ps.setInt(11, this.getF());
            ps.setInt(12, this.getGender());
            final String[] pet = { "0", "0", "0" };
            for (int i = 0; i < 3; ++i) {
                if (this.pets[i] > 0) {
                    pet[i] = String.valueOf(this.pets[i]);
                }
            }
            ps.setString(13, pet[0] + "," + pet[1] + "," + pet[2]);
            ps.executeUpdate();
            ps.close();
            ps = con.prepareStatement("INSERT INTO playernpcs_equip(npcid, charid, equipid, equippos) VALUES (?, ?, ?, ?)");
            ps.setInt(1, this.getId());
            ps.setInt(2, this.getCharId());
            for (final Entry<Byte, Integer> equip : this.equips.entrySet()) {
                ps.setInt(3, (int)Integer.valueOf(equip.getValue()));
                ps.setInt(4, (int)(byte)Byte.valueOf(equip.getKey()));
                ps.executeUpdate();
            }
            ps.close();
        }
        catch (Exception se) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)se);
        }
    }
    
    public Map<Byte, Integer> getEquips() {
        return this.equips;
    }
    
    public byte getSkin() {
        return this.skin;
    }
    
    public int getGender() {
        return this.gender;
    }
    
    public int getFace() {
        return this.face;
    }
    
    public int getHair() {
        return this.hair;
    }
    
    public int getCharId() {
        return this.charId;
    }
    
    public int getMapId() {
        return this.mapid;
    }
    
    public void setSkin(final byte s) {
        this.skin = s;
    }
    
    public void setFace(final int f) {
        this.face = f;
    }
    
    public void setHair(final int h) {
        this.hair = h;
    }
    
    public void setGender(final int g) {
        this.gender = (byte)g;
    }
    
    public int getPet(final int i) {
        return (this.pets[i] > 0) ? this.pets[i] : 0;
    }
    
    public void setPets(final List<MaplePet> p) {
        for (final MaplePet pet : p) {
            if (pet.getSummoned()) {
                this.pets[pet.getSummonedValue() - 1] = pet.getPetItemId();
            }
        }
    }
    
    @Override
    public void sendSpawnData(final MapleClient client) {
        client.sendPacket(MaplePacketCreator.spawnNPC((MapleNPC)this, false));
        client.sendPacket(MaplePacketCreator.spawnPlayerNPC(this));
        client.sendPacket(MaplePacketCreator.spawnNPCRequestController((MapleNPC)this, true));
    }
    
    public MapleNPC getNPCFromWZ() {
        final MapleNPC npc = MapleLifeFactory.getNPC(this.getId());
        if (npc != null) {
            npc.setName(this.getName());
        }
        return npc;
    }
}
