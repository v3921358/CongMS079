package server.life;

import server.MapleCarnivalFactory.MCSkill;
import java.util.Iterator;
import tools.MaplePacketCreator;
import client.MapleCharacter;
import server.MapleCarnivalFactory;
import server.maps.MapleReactor;
import server.maps.MapleMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.Point;

public class SpawnPoint extends Spawns
{
    private MapleMonster monster;
    private final Point pos;
    private long nextPossibleSpawn;
    private final int mobTime;
    private int carnival;
    private final AtomicInteger spawnedMonsters;
    private final boolean immobile;
    private final String msg;
    private final byte carnivalTeam;
    
    public SpawnPoint(final MapleMonster monster, final Point pos, final int mobTime, final byte carnivalTeam, final String msg) {
        this.carnival = -1;
        this.spawnedMonsters = new AtomicInteger(0);
        this.monster = monster;
        this.pos = pos;
        this.mobTime = ((mobTime < 0) ? -1 : (mobTime * 1000));
        this.carnivalTeam = carnivalTeam;
        this.msg = msg;
        this.immobile = !monster.getStats().getMobile();
        this.nextPossibleSpawn = System.currentTimeMillis();
    }
    
    public final void setCarnival(final int c) {
        this.carnival = c;
    }
    
    @Override
    public final Point getPosition() {
        return this.pos;
    }
    
    @Override
    public final MapleMonster getMonster() {
        return this.monster;
    }
    
    @Override
    public final byte getCarnivalTeam() {
        return this.carnivalTeam;
    }
    
    @Override
    public final int getCarnivalId() {
        return this.carnival;
    }
    
    @Override
    public final boolean shouldSpawn() {
        if (this.mobTime < 0) {
            return false;
        }
        if (((this.mobTime != 0 || this.immobile) && this.spawnedMonsters.get() > 0) || this.spawnedMonsters.get() > 1) {
            return false;
        }
        final long time = System.currentTimeMillis();
        return this.nextPossibleSpawn <= time;
    }
    
    @Override
    public final boolean shouldSpawn2() {
        return this.mobTime >= 0;
    }
    
    @Override
    public final MapleMonster spawnMonster(final MapleMap map) {
        (this.monster = new MapleMonster(this.monster)).setPosition(this.pos);
        this.monster.setCarnivalTeam(this.carnivalTeam);
        this.spawnedMonsters.incrementAndGet();
        this.monster.setListener((MonsterListener)new MonsterListener() {
            @Override
            public void monsterKilled() {
                nextPossibleSpawn = System.currentTimeMillis();
                if (mobTime > 0) {
                    nextPossibleSpawn += (long)mobTime;
                }
                spawnedMonsters.decrementAndGet();
            }
        });
        map.spawnMonster(this.monster, -2);
        if (this.carnivalTeam > -1) {
            for (final MapleReactor r : map.getAllReactorsThreadsafe()) {
                if (r.getName().startsWith(String.valueOf((int)this.carnivalTeam)) && r.getReactorId() == 9980000 + this.carnivalTeam && r.getState() < 5) {
                    final int num = Integer.parseInt(r.getName().substring(1, 2));
                    final MCSkill skil = MapleCarnivalFactory.getInstance().getGuardian(num);
                    if (skil == null || skil.getMobSkill() == null || this.monster == null) {
                        continue;
                    }
                    skil.getMobSkill().applyEffect(null, this.monster, false);
                }
            }
        }
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
