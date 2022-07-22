package server.life;

import tools.MaplePacketCreator;
import server.maps.MapleMap;
import server.Randomizer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.awt.Point;

public class SpawnPointAreaBoss extends Spawns
{
    private MapleMonster monster;
    private final Point pos1;
    private final Point pos2;
    private final Point pos3;
    private long nextPossibleSpawn;
    private final int mobTime;
    private final AtomicBoolean spawned;
    private final String msg;
    
    public SpawnPointAreaBoss(final MapleMonster monster, final Point pos1, final Point pos2, final Point pos3, final int mobTime, final String msg) {
        this.spawned = new AtomicBoolean(false);
        this.monster = monster;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.pos3 = pos3;
        this.mobTime = ((mobTime < 0) ? -1 : (mobTime * 1000));
        this.msg = msg;
        this.nextPossibleSpawn = System.currentTimeMillis();
    }
    
    @Override
    public final MapleMonster getMonster() {
        return this.monster;
    }
    
    @Override
    public final byte getCarnivalTeam() {
        return -1;
    }
    
    @Override
    public final int getCarnivalId() {
        return -1;
    }
    
    @Override
    public final boolean shouldSpawn() {
        return this.mobTime >= 0 && !this.spawned.get() && this.nextPossibleSpawn <= System.currentTimeMillis();
    }
    
    @Override
    public final boolean shouldSpawn2() {
        return this.mobTime >= 0;
    }
    
    @Override
    public final Point getPosition() {
        final int rand = Randomizer.nextInt(3);
        return (rand == 0) ? this.pos1 : ((rand == 1) ? this.pos2 : this.pos3);
    }
    
    @Override
    public final MapleMonster spawnMonster(final MapleMap map) {
        (this.monster = new MapleMonster(this.monster)).setPosition(this.getPosition());
        this.spawned.set(true);
        this.monster.setListener((MonsterListener)new MonsterListener() {
            @Override
            public void monsterKilled() {
                nextPossibleSpawn = System.currentTimeMillis();
                if (mobTime > 0) {
                    nextPossibleSpawn += (long)mobTime;
                }
                spawned.set(false);
            }
        });
        map.spawnMonster(this.monster, -2);
        if (this.msg != null) {
            map.broadcastMessage(MaplePacketCreator.serverNotice(6, this.msg));
        }
        return this.monster;
    }
    
    @Override
    public final int getMobTime() {
        return this.mobTime;
    }
}
