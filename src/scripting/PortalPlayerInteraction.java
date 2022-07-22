package scripting;

import client.MapleClient;
import server.MaplePortal;

public class PortalPlayerInteraction extends AbstractPlayerInteraction
{
    private final MaplePortal portal;
    
    public PortalPlayerInteraction(final MapleClient c, final MaplePortal portal) {
        super(c);
        this.portal = portal;
    }
    
    public final MaplePortal getPortal() {
        return this.portal;
    }
    
    public final void inFreeMarket() {
        if (this.getPlayer().getLevel() >= 10) {
            this.saveLocation("FREE_MARKET");
            this.playPortalSE();
            this.warp(910000000, "st00");
        }
        else {
            this.playerMessage(5, "你需要10級才可以進入自由市場");
        }
    }
    
    @Override
    public void spawnMonster(final int id) {
        this.spawnMonster(id, 1, this.portal.getPosition());
    }
    
    @Override
    public void spawnMonster(final int id, final int qty) {
        this.spawnMonster(id, qty, this.portal.getPosition());
    }
}
