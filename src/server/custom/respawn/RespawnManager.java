package server.custom.respawn;

import server.Timer.RespawnTimer;
import server.life.MapleMonster;
import server.maps.MapleMap;
import java.util.Iterator;
import handling.world.World.Broadcast;
import tools.MaplePacketCreator;
import server.life.OverrideMonsterStats;
import server.life.MapleLifeFactory;
import handling.channel.ChannelServer;
import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.ScheduledFuture;
import java.util.List;

public class RespawnManager
{
    private List<RespawnInfo> respawnInfolist;
    private ScheduledFuture<?> spawnTime;
    
    public RespawnManager() {
        this.respawnInfolist = new ArrayList<RespawnInfo>();
    }
    
    public static RespawnManager getInstance() {
        return InstanceHolder.instance;
    }
    
    public void run() {
        this.reloadSpawn();
        this.startTime();
    }
    
    private void reloadSpawn() {
        this.respawnInfolist.clear();
        this.respawnInfolist.add(new RespawnInfo(910000000, 100100, 10, new Point(912, -806), 1L, 10000));
    }
    
    public void spawnInTime() {
        for (final RespawnInfo respawnInfo : this.respawnInfolist) {
            final ChannelServer channelServer = ChannelServer.getInstance(respawnInfo.getChannelId());
            if (channelServer == null) {
                continue;
            }
            final MapleMap mapleMap = channelServer.getMapFactory().getMap(respawnInfo.getMapId());
            if (mapleMap == null) {
                continue;
            }
            final MapleMonster mobName = MapleLifeFactory.getMonster(respawnInfo.getMobId());
            if (mobName == null) {
                continue;
            }
            for (int i = 0; i < respawnInfo.getMobCount(); ++i) {
                final MapleMonster mapleMonster = MapleLifeFactory.getMonster(respawnInfo.getMobId());
                if (respawnInfo.getHp() > 0L) {
                    mapleMonster.setHp(respawnInfo.getHp());
                }
                if (respawnInfo.getExp() > 0) {
                    mapleMonster.setOverrideStats(new OverrideMonsterStats(mapleMonster.getHp(), mapleMonster.getMp(), respawnInfo.getExp()));
                }
                mapleMonster.setPosition(respawnInfo.getSpawnPoint());
                mapleMap.spawnMonster(mapleMonster, -2);
            }
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[怪物攻城]：頻道:" + channelServer.getChannel() + "地图:" + mapleMap.getMapName() + " 怪物:" + mobName.getStats().getName() + " 数量:" + respawnInfo.getMobCount()));
        }
    }
    
    public void startTime() {
        if (this.spawnTime != null) {
            this.spawnTime.cancel(true);
        }
        this.spawnTime = RespawnTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                RespawnManager.this.spawnInTime();
            }
        }, 10000L);
    }
    
    public void 关闭startTime() {
        if (this.spawnTime != null) {
            this.spawnTime.cancel(true);
        }
        this.spawnTime = null;
    }
    
    public static void main(final String[] args) {
        RespawnTimer.getInstance().start();
        getInstance().run();
    }
    
    private static class InstanceHolder
    {
        public static final RespawnManager instance;
        
        static {
            instance = new RespawnManager();
        }
    }
}
