package server.life;

import server.maps.MapleMapObjectType;
import tools.MaplePacketCreator;
import client.MapleClient;
import server.MapleShopFactory;

public class MapleNPC extends AbstractLoadedMapleLife
{
    private String name;
    private boolean custom;
    
    public MapleNPC(final int id, final String name, final boolean custom) {
        super(id);
        this.name = "MISSINGNO";
        this.custom = false;
        this.name = name;
        this.custom = custom;
    }
    
    public MapleNPC(final int id, final String name) {
        super(id);
        this.name = "MISSINGNO";
        this.custom = false;
        this.name = name;
    }
    
    public final boolean hasShop() {
        return MapleShopFactory.getInstance().getShopForNPC(this.getId()) != null;
    }
    
    public final void sendShop(final MapleClient c) {
        MapleShopFactory.getInstance().getShopForNPC(this.getId()).sendShop(c);
    }
    
    @Override
    public void sendSpawnData(final MapleClient client) {
        if (this.getId() < 9901000) {
            client.sendPacket(MaplePacketCreator.spawnNPC(this, true));
            client.sendPacket(MaplePacketCreator.spawnNPCRequestController(this, true));
        }
    }
    
    @Override
    public final void sendDestroyData(final MapleClient client) {
        client.getSession().writeAndFlush((Object)MaplePacketCreator.removeNPCController(this.getObjectId()));
        client.sendPacket(MaplePacketCreator.removeNPC(this.getObjectId()));
    }
    
    @Override
    public final MapleMapObjectType getType() {
        return MapleMapObjectType.NPC;
    }
    
    public final String getName() {
        return this.name;
    }
    
    public void setName(final String n) {
        this.name = n;
    }
}
