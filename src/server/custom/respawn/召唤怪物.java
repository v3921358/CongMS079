package server.custom.respawn;

import java.awt.Point;

public class 召唤怪物
{
    private int mapId;
    private int mobId;
    private int mobCount;
    private Point spawnPoint;
    private int channelId;
    private long hp;
    private int exp;
    private int s_day;
    private int s_hour;
    private int s_min;
    
    public 召唤怪物(final int mapId, final int mobId, final int mobCount, final Point spawnPoint, final long hp, final int exp) {
        this.mapId = mapId;
        this.mobId = mobId;
        this.mobCount = mobCount;
        this.spawnPoint = spawnPoint;
        this.hp = hp;
        this.exp = exp;
    }
    
    public int getMapId() {
        return this.mapId;
    }
    
    public void setMapId(final int mapId) {
        this.mapId = mapId;
    }
    
    public int getMobId() {
        return this.mobId;
    }
    
    public void setMobId(final int mobId) {
        this.mobId = mobId;
    }
    
    public int getMobCount() {
        return this.mobCount;
    }
    
    public void setMobCount(final int mobCount) {
        this.mobCount = mobCount;
    }
    
    public Point getSpawnPoint() {
        return this.spawnPoint;
    }
    
    public void setSpawnPoint(final Point spawnPoint) {
        this.spawnPoint = spawnPoint;
    }
    
    public int getChannelId() {
        return this.channelId;
    }
    
    public void setChannelId(final int channelId) {
        this.channelId = channelId;
    }
    
    public long getHp() {
        return this.hp;
    }
    
    public void setHp(final long hp) {
        this.hp = hp;
    }
    
    public int getExp() {
        return this.exp;
    }
    
    public void setExp(final int exp) {
        this.exp = exp;
    }
    
    public int getS_day() {
        return this.s_day;
    }
    
    public void setS_day(final int s_day) {
        this.s_day = s_day;
    }
    
    public int getS_hour() {
        return this.s_hour;
    }
    
    public void setS_hour(final int s_hour) {
        this.s_hour = s_hour;
    }
    
    public int getS_min() {
        return this.s_min;
    }
    
    public void setS_min(final int s_min) {
        this.s_min = s_min;
    }
}
