package server.maps;

import tools.MaplePacketCreator;
import client.MapleClient;
import client.MapleCharacter;
import java.awt.Point;

public class MapleKite extends AbstractMapleMapObject
{
    private final Point pos;
    private final MapleCharacter owner;
    private final String text;
    private final int ft;
    private final int itemid;
    
    public MapleKite(final MapleCharacter owner, final Point pos, final int ft, final String text, final int itemid) {
        this.owner = owner;
        this.pos = pos;
        this.text = text;
        this.ft = ft;
        this.itemid = itemid;
    }
    
    @Override
    public MapleMapObjectType getType() {
        return MapleMapObjectType.Kite;
    }
    
    @Override
    public Point getPosition() {
        return this.pos.getLocation();
    }
    
    public MapleCharacter getOwner() {
        return this.owner;
    }
    
    @Override
    public void setPosition(final Point position) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void sendDestroyData(final MapleClient client) {
        client.getSession().writeAndFlush((Object)this.makeDestroyData());
    }
    
    @Override
    public void sendSpawnData(final MapleClient client) {
        client.getSession().writeAndFlush((Object)this.makeSpawnData());
    }
    
    public byte[] makeSpawnData() {
        return MaplePacketCreator.spawnKite(this.getObjectId(), this.itemid, this.owner.getName(), this.text, this.pos, this.ft);
    }
    
    public byte[] makeDestroyData() {
        return MaplePacketCreator.destroyKite(this.getObjectId());
    }
}
