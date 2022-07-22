package server.maps;

import tools.MaplePacketCreator;
import client.MapleClient;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.awt.Point;
import server.MaplePortal;
import client.MapleCharacter;
import java.lang.ref.WeakReference;

public class MapleDoor extends AbstractMapleMapObject
{
    private final WeakReference<MapleCharacter> owner;
    private final MapleMap town;
    private final MaplePortal townPortal;
    private final MapleMap target;
    private final int skillId;
    private final int ownerId;
    private final Point targetPosition;
    public boolean first;
    
    public MapleDoor(final MapleCharacter owner, final Point targetPosition, final int skillId) {
        this.first = true;
        this.owner = new WeakReference<MapleCharacter>(owner);
        this.ownerId = owner.getId();
        this.target = owner.getMap();
        this.setPosition(this.targetPosition = targetPosition);
        this.town = this.target.getReturnMap();
        this.townPortal = this.getFreePortal();
        this.skillId = skillId;
    }
    
    public MapleDoor(final MapleDoor origDoor) {
        this.first = true;
        this.owner = new WeakReference<MapleCharacter>(origDoor.owner.get());
        this.town = origDoor.town;
        this.townPortal = origDoor.townPortal;
        this.target = origDoor.target;
        this.targetPosition = origDoor.targetPosition;
        this.skillId = origDoor.skillId;
        this.ownerId = origDoor.ownerId;
        this.setPosition(this.townPortal.getPosition());
    }
    
    public final int getSkill() {
        return this.skillId;
    }
    
    public final int getOwnerId() {
        return this.ownerId;
    }
    
    private MaplePortal getFreePortal() {
        final List<MaplePortal> freePortals = new ArrayList<MaplePortal>();
        for (final MaplePortal port : this.town.getPortals()) {
            if (port.getType() == 6) {
                freePortals.add(port);
            }
        }
        Collections.sort(freePortals, (Comparator<? super MaplePortal>)new Comparator<MaplePortal>() {
            @Override
            public final int compare(final MaplePortal o1, final MaplePortal o2) {
                if (o1.getId() < o2.getId()) {
                    return -1;
                }
                if (o1.getId() == o2.getId()) {
                    return 0;
                }
                return 1;
            }
        });
        for (final MapleMapObject obj : this.town.getAllDoorsThreadsafe()) {
            final MapleDoor door = (MapleDoor)obj;
            if (door.getOwner() != null && door.getOwner().getParty() != null && this.getOwner() != null && this.getOwner().getParty() != null && this.getOwner().getParty().getId() == door.getOwner().getParty().getId()) {
                return null;
            }
            freePortals.remove((Object)door.getTownPortal());
        }
        if (freePortals.size() <= 0) {
            return null;
        }
        return (MaplePortal)freePortals.iterator().next();
    }
    
    @Override
    public final void sendSpawnData(final MapleClient client) {
        if (this.getOwner() == null || this.target == null || client.getPlayer() == null) {
            return;
        }
        if (this.target.getId() == client.getPlayer().getMapId() || this.getOwnerId() == client.getPlayer().getId() || (this.getOwner() != null && this.getOwner().getParty() != null && client.getPlayer().getParty() != null && this.getOwner().getParty().getId() == client.getPlayer().getParty().getId())) {
            client.sendPacket(MaplePacketCreator.spawnDoor(this.getOwnerId(), (this.target.getId() == client.getPlayer().getMapId()) ? this.targetPosition : this.townPortal.getPosition(), true));
            if (this.getOwner() != null && this.getOwner().getParty() != null && client.getPlayer().getParty() != null && (this.getOwnerId() == client.getPlayer().getId() || this.getOwner().getParty().getId() == client.getPlayer().getParty().getId())) {
                client.sendPacket(MaplePacketCreator.partyPortal(this.town.getId(), this.target.getId(), this.skillId, (this.target.getId() == client.getPlayer().getMapId()) ? this.targetPosition : this.townPortal.getPosition(), true));
            }
            client.sendPacket(MaplePacketCreator.spawnPortal(this.town.getId(), this.target.getId(), this.skillId, (this.target.getId() == client.getPlayer().getMapId()) ? this.targetPosition : this.townPortal.getPosition()));
        }
    }
    
    @Override
    public final void sendDestroyData(final MapleClient client) {
        if (client.getPlayer() == null || this.getOwner() == null || this.target == null) {
            return;
        }
        if (this.target.getId() == client.getPlayer().getMapId() || this.getOwnerId() == client.getPlayer().getId() || (this.getOwner() != null && this.getOwner().getParty() != null && client.getPlayer().getParty() != null && this.getOwner().getParty().getId() == client.getPlayer().getParty().getId())) {
            client.sendPacket(MaplePacketCreator.removeDoor(this.getOwnerId(), false));
            if (this.getOwner() != null && this.getOwner().getParty() != null && client.getPlayer().getParty() != null && (this.getOwnerId() == client.getPlayer().getId() || this.getOwner().getParty().getId() == client.getPlayer().getParty().getId())) {
                client.sendPacket(MaplePacketCreator.partyPortal(999999999, 999999999, 0, new Point(-1, -1), false));
            }
            client.sendPacket(MaplePacketCreator.spawnPortal(999999999, 999999999, 0, null));
        }
    }
    
    public final void warp(final MapleCharacter chr, final boolean toTown) {
        if (chr.getId() == this.getOwnerId() || (this.getOwner() != null && this.getOwner().getParty() != null && chr.getParty() != null && this.getOwner().getParty().getId() == chr.getParty().getId())) {
            if (!toTown) {
                chr.changeMap(this.target, this.target.findClosestPortal(this.targetPosition));
            }
            else {
                chr.changeMap(this.town, this.townPortal);
            }
        }
        else {
            chr.getClient().getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
        }
    }
    
    public final MapleCharacter getOwner() {
        return (MapleCharacter)this.owner.get();
    }
    
    public final MapleMap getTown() {
        return this.town;
    }
    
    public final MaplePortal getTownPortal() {
        return this.townPortal;
    }
    
    public final MapleMap getTarget() {
        return this.target;
    }
    
    public final Point getTargetPosition() {
        return this.targetPosition;
    }
    
    @Override
    public final MapleMapObjectType getType() {
        return MapleMapObjectType.DOOR;
    }
}
