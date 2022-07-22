package scripting;

import server.MapleCarnivalFactory.MCSkill;
import server.MapleCarnivalFactory;
import server.life.MapleMonster;
import server.life.MapleLifeFactory;
import client.inventory.IItem;
import java.awt.Point;
import java.util.Iterator;
import java.util.List;
import client.inventory.Equip;
import client.inventory.Item;
import client.inventory.MapleInventoryType;
import constants.GameConstants;
import server.maps.MapleMapObject;
import handling.channel.ChannelServer;
import server.Randomizer;
import server.MapleItemInformationProvider;
import server.maps.ReactorDropEntry;
import java.util.LinkedList;
import client.MapleClient;
import server.maps.MapleReactor;

public class ReactorActionManager extends AbstractPlayerInteraction
{
    private final MapleReactor reactor;
    
    public ReactorActionManager(final MapleClient c, final MapleReactor reactor) {
        super(c);
        this.reactor = reactor;
    }
    
    public void dropItems() {
        this.dropItems(false, 0, 0, 0, 0);
    }
    
    public void dropItems(final boolean meso, final int mesoChance, final int minMeso, final int maxMeso) {
        this.dropItems(meso, mesoChance, minMeso, maxMeso, 0);
    }
    
    public void dropItems(final boolean meso, final int mesoChance, final int minMeso, final int maxMeso, final int minItems) {
        final List<ReactorDropEntry> chances = ReactorScriptManager.getInstance().getDrops(this.reactor.getReactorId());
        final List<ReactorDropEntry> items = new LinkedList<ReactorDropEntry>();
        if (meso && Math.random() < 1.0 / (double)mesoChance) {
            items.add(new ReactorDropEntry(0, mesoChance, -1));
        }
        int numItems = 0;
        for (final ReactorDropEntry d : chances) {
            if (Math.random() < 1.0 / (double)d.chance && (d.questid <= 0 || this.getPlayer().getQuestStatus(d.questid) == 1)) {
                ++numItems;
                items.add(d);
            }
        }
        while (items.size() < minItems) {
            items.add(new ReactorDropEntry(0, mesoChance, -1));
            ++numItems;
        }
        final Point position;
        final Point dropPos = position = this.reactor.getPosition();
        position.x -= 12 * numItems;
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        for (final ReactorDropEntry d2 : items) {
            if (d2.itemId == 0) {
                final int range = maxMeso - minMeso;
                final int mesoDrop = Randomizer.nextInt(range) + minMeso * ChannelServer.getInstance(this.getClient().getChannel()).getMesoRate();
                this.reactor.getMap().spawnMesoDrop(mesoDrop, dropPos, (MapleMapObject)this.reactor, this.getPlayer(), false, (byte)0);
            }
            else {
                IItem drop;
                if (GameConstants.getInventoryType(d2.itemId) != MapleInventoryType.EQUIP) {
                    drop = new Item(d2.itemId, (short)0, (short)1, (byte)0);
                }
                else {
                    drop = ii.randomizeStats((Equip)ii.getEquipById(d2.itemId));
                }
                this.reactor.getMap().spawnItemDrop((MapleMapObject)this.reactor, this.getPlayer(), drop, dropPos, false, false);
            }
            final Point point = dropPos;
            point.x += 25;
        }
    }
    
    @Override
    public void spawnNpc(final int npcId) {
        this.spawnNpc(npcId, this.getPosition());
    }
    
    public Point getPosition() {
        final Point position;
        final Point pos = position = this.reactor.getPosition();
        position.y -= 10;
        return pos;
    }
    
    public MapleReactor getReactor() {
        return this.reactor;
    }
    
    public void spawnZakum() {
        this.reactor.getMap().spawnZakum(this.getPosition().x, this.getPosition().y);
    }
    
    public void spawnFakeMonster(final int id) {
        this.spawnFakeMonster(id, 1, this.getPosition());
    }
    
    public void spawnFakeMonster(final int id, final int x, final int y) {
        this.spawnFakeMonster(id, 1, new Point(x, y));
    }
    
    public void spawnFakeMonster(final int id, final int qty) {
        this.spawnFakeMonster(id, qty, this.getPosition());
    }
    
    public void spawnFakeMonster(final int id, final int qty, final int x, final int y) {
        this.spawnFakeMonster(id, qty, new Point(x, y));
    }
    
    private void spawnFakeMonster(final int id, final int qty, final Point pos) {
        for (int i = 0; i < qty; ++i) {
            this.reactor.getMap().spawnFakeMonsterOnGroundBelow(MapleLifeFactory.getMonster(id), pos);
        }
    }
    
    public MapleMonster getMonster(final int id) {
        return MapleLifeFactory.getMonster(id);
    }
    
    public void killAll() {
        this.reactor.getMap().killAllMonsters(true);
    }
    
    public void killMonster(final int monsId) {
        this.reactor.getMap().killMonster(monsId);
    }
    
    @Override
    public void spawnMonster(final int id) {
        this.spawnMonster(id, 1, this.getPosition());
    }
    
    @Override
    public void spawnMonster(final int id, final int qty) {
        this.spawnMonster(id, qty, this.getPosition());
    }
    
    public void dispelAllMonsters(final int num) {
        final MCSkill skil = MapleCarnivalFactory.getInstance().getGuardian(num);
        if (skil != null) {
            for (final MapleMonster mons : this.getMap().getAllMonstersThreadsafe()) {
                mons.dispelSkill(skil.getMobSkill());
            }
        }
    }
}
