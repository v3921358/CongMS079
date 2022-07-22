package handling.world;

import handling.cashshop.CashShopServer;
import java.util.List;
import server.maps.MapleDoor;
import client.MapleCharacter;
import java.awt.Point;
import java.io.Serializable;

public class MaplePartyCharacter implements Serializable
{
    private static final long serialVersionUID = 6215463252132450750L;
    private final String name;
    private int id;
    private int level;
    private int channel;
    private int jobid;
    private int mapid;
    private int doorTown;
    private int doorTarget;
    private int doorSkill;
    private Point doorPosition;
    private boolean online;
    
    public MaplePartyCharacter(final MapleCharacter maplechar) {
        this.doorTown = 999999999;
        this.doorTarget = 999999999;
        this.doorSkill = 0;
        this.doorPosition = new Point(0, 0);
        this.name = maplechar.getName();
        this.level = maplechar.getLevel();
        this.channel = maplechar.getClient().getChannel();
        this.id = maplechar.getId();
        this.jobid = maplechar.getJob();
        this.mapid = maplechar.getMapId();
        this.online = true;
        final List<MapleDoor> doors = maplechar.getDoors();
        if (doors.size() > 0) {
            final MapleDoor door = (MapleDoor)doors.get(0);
            this.doorTown = door.getTown().getId();
            this.doorTarget = door.getTarget().getId();
            this.doorSkill = door.getSkill();
            this.doorPosition = door.getTargetPosition();
        }
        else {
            this.doorPosition = new Point(maplechar.getPosition());
        }
    }
    
    public MaplePartyCharacter() {
        this.doorTown = 999999999;
        this.doorTarget = 999999999;
        this.doorSkill = 0;
        this.doorPosition = new Point(0, 0);
        this.name = "";
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public int getChannel() {
        return this.channel;
    }
    
    public boolean isOnline() {
        return this.online;
    }
    
    public boolean isCs() {
        return CashShopServer.getPlayerStorage().getCharacterByName(this.name) != null;
    }
    
    public boolean isHp0() {
        final MapleCharacter victim = MapleCharacter.getCharacterByName(this.name);
        return victim.getStat().getHp() == 0;
    }
    
    public boolean haveItem(final int itemid, final int quantity) {
        final MapleCharacter victim = MapleCharacter.getCharacterByName(this.name);
        return victim.haveItem(itemid, quantity);
    }
    
    public int getBossLogD(final String bossid) {
        final MapleCharacter victim = MapleCharacter.getCharacterByName(this.name);
        return victim.getBossLogD(bossid);
    }
    
    public void setOnline(final boolean online) {
        this.online = online;
    }
    
    public int getMapid() {
        return this.mapid;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getJobId() {
        return this.jobid;
    }
    
    public int getDoorTown() {
        return this.doorTown;
    }
    
    public int getDoorTarget() {
        return this.doorTarget;
    }
    
    public int getDoorSkill() {
        return this.doorSkill;
    }
    
    public Point getDoorPosition() {
        return this.doorPosition;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final MaplePartyCharacter other = (MaplePartyCharacter)obj;
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        }
        else if (!this.name.equals((Object)other.name)) {
            return false;
        }
        return true;
    }
}
