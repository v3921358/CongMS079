package client;

import java.util.Iterator;
import java.util.List;
import server.life.MapleMonster;
import server.MapleInventoryManipulator;
import handling.world.MaplePartyCharacter;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import handling.channel.handler.InventoryHandler;
import server.MapleItemInformationProvider;
import server.maps.MapleMapItem;
import server.maps.MapleMapObject;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ItemVac extends Thread
{
    private final ConcurrentLinkedQueue<MapleMapObject> itemVacs;
    private MapleMapItem item;
    private MapleMapObject object;
    private final MapleCharacter chr;
    private final MapleClient c;
    
    public ItemVac(final MapleCharacter chr) {
        this.chr = chr;
        this.itemVacs = new ConcurrentLinkedQueue<MapleMapObject>();
        this.c = chr.getClient();
    }
    
    public synchronized void addObject(final MapleMapObject ob) {
        final MapleMapItem mapitem = (MapleMapItem)ob;
        final Lock lock = mapitem.getLock();
        lock.lock();
        try {
            if (mapitem.isPickedUp() || (mapitem.getQuest() > 0 && this.chr.getQuestStatus(mapitem.getQuest()) != 1) || (mapitem.getOwner() != this.chr.getId() && ((!mapitem.isPlayerDrop() && mapitem.getDropType() == 0) || (mapitem.isPlayerDrop() && this.chr.getMap().getEverlast()))) || (!mapitem.isPlayerDrop() && mapitem.getDropType() == 1 && mapitem.getOwner() != this.chr.getId())) {
                return;
            }
            if (mapitem.getMeso() > 0) {
                this.itemVacs.add(ob);
            }
            else if (!MapleItemInformationProvider.getInstance().isPickupBlocked(mapitem.getItemId()) && !InventoryHandler.useItem(this.c, mapitem.getItemId()) && mapitem.getItemId() / 10000 != 291) {
                this.itemVacs.add(ob);
            }
        }
        finally {
            lock.unlock();
        }
    }
    
    @Override
    public synchronized void run() {
        try {
            while (!Thread.interrupted()) {
                this.wait(5000L);
                final List<MapleMapItem> items = this.chr.getMap().getAllItemsThreadsafe();
                for (final MapleMapItem i : items) {
                    this.addObject((MapleMapObject)i);
                }
                while (this.itemVacs.peek() != null) {
                    this.object = (MapleMapObject)this.itemVacs.poll();
                    this.item = (MapleMapItem)this.object;
                    final Lock lock = this.item.getLock();
                    lock.lock();
                    try {
                        if (this.item.getMeso() > 0) {
                            if (this.chr.getParty() != null && this.item.getOwner() != this.chr.getId()) {
                                final List<MapleCharacter> toGive = new LinkedList<MapleCharacter>();
                                for (final MaplePartyCharacter z : this.chr.getParty().getMembers()) {
                                    final MapleCharacter m = this.chr.getMap().getCharacterById(z.getId());
                                    if (m != null && m.getId() != this.chr.getId()) {
                                        toGive.add(m);
                                    }
                                }
                            }
                            if (interrupted()) {
                                continue;
                            }
                            InventoryHandler.removeItem(this.chr, this.item, this.object);
                            this.wait(20L);
                        }
                        else {
                            if (!MapleInventoryManipulator.checkSpace(this.c, this.item.getItemId(), (int)this.item.getItem().getQuantity(), this.item.getItem().getOwner()) || Thread.interrupted()) {
                                continue;
                            }
                            MapleInventoryManipulator.addFromDrop(this.chr.getClient(), this.item.getItem(), true, this.item.getDropper() instanceof MapleMonster, false);
                            InventoryHandler.removeItem(this.chr, this.item, this.object);
                            this.wait(20L);
                        }
                    }
                    finally {
                        lock.unlock();
                    }
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println("[ItemVac]未知錯誤" + (Object)e);
        }
    }
}
