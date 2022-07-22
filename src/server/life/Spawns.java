package server.life;

import java.awt.Point;
import server.maps.MapleMap;

public abstract class Spawns
{
    public abstract MapleMonster getMonster();
    
    public abstract byte getCarnivalTeam();
    
    public abstract boolean shouldSpawn();
    
    public abstract boolean shouldSpawn2();
    
    public abstract int getCarnivalId();
    
    public abstract MapleMonster spawnMonster(final MapleMap p0);
    
    public abstract int getMobTime();
    
    public abstract Point getPosition();
}
