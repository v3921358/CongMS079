package client.inventory;

import java.util.ArrayList;
import server.movement.StaticLifeMovement;
import server.movement.LifeMovementFragment;
import java.util.List;
import java.util.Iterator;
import server.MapleItemInformationProvider;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import tools.FileoutputUtil;
import tools.FilePrinter;
import database.DBConPool;
import java.awt.Point;
import java.io.Serializable;

public class MaplePet implements Serializable
{
    private static final long serialVersionUID = 9179541993413738569L;
    private String name;
    private final int uniqueid;
    private final int petitemid;
    private int Fh;
    private int stance;
    private int limitedLife;
    private Point pos;
    private byte fullness;
    private byte level;
    private byte summoned;
    private short inventorypos;
    private short closeness;
    private short flags;
    private boolean changed;
    private int[] excluded;
    
    private MaplePet(final int petitemid, final int uniqueid) {
        this.Fh = 0;
        this.stance = 0;
        this.limitedLife = 0;
        this.fullness = 100;
        this.level = 1;
        this.summoned = 0;
        this.inventorypos = 0;
        this.closeness = 0;
        this.flags = 0;
        this.changed = false;
        this.excluded = new int[10];
        this.petitemid = petitemid;
        this.uniqueid = uniqueid;
        for (int i = 0; i < this.excluded.length; ++i) {
            this.excluded[i] = 0;
        }
    }
    
    private MaplePet(final int petitemid, final int uniqueid, final short inventorypos) {
        this.Fh = 0;
        this.stance = 0;
        this.limitedLife = 0;
        this.fullness = 100;
        this.level = 1;
        this.summoned = 0;
        this.inventorypos = 0;
        this.closeness = 0;
        this.flags = 0;
        this.changed = false;
        this.excluded = new int[10];
        this.petitemid = petitemid;
        this.uniqueid = uniqueid;
        this.inventorypos = inventorypos;
        for (int i = 0; i < this.excluded.length; ++i) {
            this.excluded[i] = 0;
        }
    }
    
    public static final MaplePet loadFromDb(final int itemid, final int petid, final short inventorypos) {
        final MaplePet ret = new MaplePet(itemid, petid, inventorypos);
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM pets WHERE petid = ?")) {
            ps.setInt(1, petid);
            try (final ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    rs.close();
                    ps.close();
                    return null;
                }
                ret.setName(rs.getString("name"));
                ret.setCloseness((int)rs.getShort("closeness"));
                ret.setLevel((int)rs.getByte("level"));
                ret.setFullness((int)rs.getByte("fullness"));
                ret.setLimitedLife(rs.getInt("seconds"));
                ret.setFlags((int)rs.getShort("flags"));
                final String[] list = rs.getString("excluded").split(",");
                for (int i = 0; i < ret.excluded.length; ++i) {
                    ret.excluded[i] = Integer.parseInt(list[i]);
                }
                ret.changed = false;
            }
            return ret;
        }
        catch (SQLException ex) {
            FilePrinter.printError("MaplePet.txt", (Throwable)ex, "loadFromDb");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            return null;
        }
    }
    
    public final void saveToDb() {
        if (!this.changed) {
            return;
        }
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("UPDATE pets SET name = ?, level = ?, closeness = ?, fullness = ?, seconds = ?, flags = ?, excluded = ?  WHERE petid = ?")) {
            ps.setString(1, this.name);
            ps.setByte(2, this.level);
            ps.setShort(3, this.closeness);
            ps.setByte(4, this.fullness);
            ps.setInt(5, this.limitedLife);
            ps.setShort(6, this.flags);
            final StringBuilder list = new StringBuilder();
            for (int i = 0; i < this.excluded.length; ++i) {
                list.append(this.excluded[i]);
                list.append(",");
            }
            final String newlist = list.toString();
            ps.setString(7, newlist.substring(0, newlist.length() - 1));
            ps.setInt(8, this.uniqueid);
            ps.executeUpdate();
            this.changed = false;
        }
        catch (SQLException ex) {
            FilePrinter.printError("MaplePet.txt", (Throwable)ex, "saveToDb");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
    }
    
    public static final MaplePet createPet(final int itemid, final int uniqueid) {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        return createPet(itemid, ii.getName(itemid), 1, 0, 100, uniqueid, ii.getPetLimitLife(itemid), ii.getPetFlagInfo(itemid));
    }
    
    public static final MaplePet createPet(final int itemid, final String name, final int level, final int closeness, final int fullness, int uniqueid, final int limitedLife, final short flag) {
        if (uniqueid <= -1) {
            uniqueid = MapleInventoryIdentifier.getInstance();
        }
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement pse = con.prepareStatement("INSERT INTO pets (petid, name, level, closeness, fullness, seconds, flags) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            pse.setInt(1, uniqueid);
            pse.setString(2, name);
            pse.setByte(3, (byte)level);
            pse.setShort(4, (short)closeness);
            pse.setByte(5, (byte)fullness);
            pse.setInt(6, limitedLife);
            pse.setShort(7, flag);
            pse.executeUpdate();
        }
        catch (SQLException ex) {
            FilePrinter.printError("MaplePet.txt", (Throwable)ex, "createPet");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            return null;
        }
        final MaplePet pet = new MaplePet(itemid, uniqueid);
        pet.setName(name);
        pet.setLevel(level);
        pet.setFullness(fullness);
        pet.setCloseness(closeness);
        pet.setFlags((int)flag);
        pet.setLimitedLife(limitedLife);
        return pet;
    }
    
    public final String getName() {
        if (this.name == null) {
            this.setName(MapleItemInformationProvider.getInstance().getName(this.petitemid));
        }
        return this.name;
    }
    
    public final void setName(final String name) {
        this.name = name;
        this.changed = true;
    }
    
    public final boolean getSummoned() {
        return this.summoned > 0;
    }
    
    public final byte getSummonedValue() {
        return this.summoned;
    }
    
    public final void setSummoned(final int summoned) {
        this.summoned = (byte)summoned;
    }
    
    public final short getInventoryPosition() {
        return this.inventorypos;
    }
    
    public final void setInventoryPosition(final short inventorypos) {
        this.inventorypos = inventorypos;
    }
    
    public int getUniqueId() {
        return this.uniqueid;
    }
    
    public final short getCloseness() {
        return this.closeness;
    }
    
    public final void setCloseness(final int closeness) {
        this.closeness = (short)closeness;
        this.changed = true;
    }
    
    public final byte getLevel() {
        return this.level;
    }
    
    public final void setLevel(final int level) {
        this.level = (byte)level;
        this.changed = true;
    }
    
    public final byte getFullness() {
        return this.fullness;
    }
    
    public final void setFullness(final int fullness) {
        this.fullness = (byte)fullness;
        this.changed = true;
    }
    
    public final short getFlags() {
        return this.flags;
    }
    
    public final void setFlags(final int fffh) {
        this.flags = (short)fffh;
        this.changed = true;
    }
    
    public final int getFh() {
        return this.Fh;
    }
    
    public final void setFh(final int Fh) {
        this.Fh = Fh;
    }
    
    public final Point getPos() {
        return this.pos;
    }
    
    public final void setPos(final Point pos) {
        this.pos = pos;
    }
    
    public final int getStance() {
        return this.stance;
    }
    
    public final void setStance(final int stance) {
        this.stance = stance;
    }
    
    public final int getPetItemId() {
        return this.petitemid;
    }
    
    public final boolean canConsume(final int itemId) {
        final MapleItemInformationProvider mii = MapleItemInformationProvider.getInstance();
        final Iterator<Integer> iterator = mii.petsCanConsume(itemId).iterator();
        while (iterator.hasNext()) {
            final int petId = (int)Integer.valueOf(iterator.next());
            if (petId == this.petitemid) {
                return true;
            }
        }
        return false;
    }
    
    public final void updatePosition(final List<LifeMovementFragment> movement) {
        for (final LifeMovementFragment move : movement) {
            if (move instanceof StaticLifeMovement) {
                final Point newPos = move.getPosition();
                if (newPos == null) {
                    continue;
                }
                this.setPos(newPos);
                this.setStance(((StaticLifeMovement)move).getNewstate());
            }
        }
    }
    
    public final int getSecondsLeft() {
        return this.limitedLife;
    }
    
    public final void setLimitedLife(final int sl) {
        this.limitedLife = sl;
        this.changed = true;
    }
    
    public static void main(final String[] args) {
        clearPet();
    }
    
    public static void clearPet() {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pets");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final int uid = rs.getInt("petid");
                if (!ItemLoader.isExistsByUniqueid(uid)) {
                    ps = con.prepareStatement("DELETE FROM pets WHERE petid = ?");
                    ps.setInt(1, uid);
                    ps.executeUpdate();
                    System.err.println("寵物：" + rs.getString("name") + " petid: " + uid + " 不存在, 清理。");
                }
            }
            ps.close();
            rs.close();
        }
        catch (SQLException se) {
            System.err.println("[MaplePet] 從数据庫中加載寵物信息出錯");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)se);
        }
    }
    
    public void clearExcluded() {
        for (int i = 0; i < this.excluded.length; ++i) {
            this.excluded[i] = 0;
        }
        this.changed = true;
    }
    
    public List<Integer> getExcluded() {
        final List list = new ArrayList();
        for (int i = 0; i < this.excluded.length; ++i) {
            if (this.excluded[i] > 0 && PetFlag.UNPICKABLE.check((int)this.flags)) {
                list.add(Integer.valueOf(this.excluded[i]));
            }
        }
        return (List<Integer>)list;
    }
    
    public void addExcluded(final int i, final int itemId) {
        if (i < this.excluded.length) {
            this.excluded[i] = itemId;
            this.changed = true;
        }
    }
    
    public enum PetFlag
    {
        ITEM_PICKUP(1, 5190000, 5191000), 
        EXPAND_PICKUP(2, 5190002, 5191002), 
        AUTO_PICKUP(4, 5190003, 5191003), 
        UNPICKABLE(8, 5190005, -1), 
        LEFTOVER_PICKUP(16, 5190004, 5191004), 
        HP_CHARGE(32, 5190001, 5191001), 
        MP_CHARGE(64, 5190006, -1), 
        PET_BUFF(128, -1, -1), 
        PET_DRAW(256, 5190007, -1), 
        PET_DIALOGUE(512, 5190008, -1);
        
        private final int i;
        private final int item;
        private final int remove;
        
        private PetFlag(final int i, final int item, final int remove) {
            this.i = i;
            this.item = item;
            this.remove = remove;
        }
        
        public final int getValue() {
            return this.i;
        }
        
        public final boolean check(final int flag) {
            return (flag & this.i) == this.i;
        }
        
        public static final PetFlag getByAddId(final int itemId) {
            for (final PetFlag flag : values()) {
                if (flag.item == itemId) {
                    return flag;
                }
            }
            return null;
        }
        
        public static final PetFlag getByDelId(final int itemId) {
            for (final PetFlag flag : values()) {
                if (flag.remove == itemId) {
                    return flag;
                }
            }
            return null;
        }
    }
}
