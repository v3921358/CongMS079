package client.inventory;

import server.MapleItemInformationProvider;
import tools.MaplePacketCreator;
import client.MapleCharacter;
import constants.GameConstants;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.Serializable;

public class MapleInventory implements Iterable<IItem>, Serializable
{
    private Map<Short, IItem> inventory;
    private byte slotLimit;
    private MapleInventoryType type;
    
    public MapleInventory(final MapleInventoryType type) {
        this.slotLimit = 0;
        this.inventory = new LinkedHashMap<Short, IItem>();
        this.type = type;
    }
    
    public void addSlot(final byte slot) {
        this.slotLimit += slot;
        if (this.slotLimit > 96) {
            this.slotLimit = 96;
        }
    }
    
    public byte getSlotLimit() {
        return this.slotLimit;
    }
    
    public void setSlotLimit(byte slot) {
        if (slot > 96) {
            slot = 96;
        }
        this.slotLimit = slot;
    }
    
    public IItem findById(final int itemId) {
        for (final IItem item : this.inventory.values()) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }
        return null;
    }
    
    public IItem findByUniqueId(final int itemId) {
        for (final IItem item : this.inventory.values()) {
            if (item.getUniqueId() == itemId) {
                return item;
            }
        }
        return null;
    }
    
    public int countById(final int itemId) {
        int possesed = 0;
        for (final IItem item : this.inventory.values()) {
            if (item.getItemId() == itemId) {
                possesed += item.getQuantity();
            }
        }
        return possesed;
    }
    
    public List<IItem> listById(final int itemId) {
        final List<IItem> ret = new ArrayList<IItem>();
        for (final IItem item : this.inventory.values()) {
            if (item.getItemId() == itemId) {
                ret.add(item);
            }
        }
        if (ret.size() > 1) {
            Collections.sort(ret);
        }
        return ret;
    }
    
    public Collection<IItem> list() {
        return this.inventory.values();
    }
    
    public short addItem(final IItem item) {
        final short slotId = this.getNextFreeSlot();
        if (slotId < 0) {
            return -1;
        }
        this.inventory.put(Short.valueOf(slotId), item);
        item.setPosition(slotId);
        return slotId;
    }
    
    public short addItem(final Item item, final int slotId) {
        this.inventory.put(Short.valueOf((short)slotId), item);
        item.setPosition((short)slotId);
        return (short)slotId;
    }
    
    public short addItem(final IItem item, final int slotId) {
        this.inventory.put(Short.valueOf((short)slotId), item);
        item.setPosition((short)slotId);
        return (short)slotId;
    }
    
    public void addFromDB(final IItem item) {
        if (item.getPosition() < 0 && !this.type.equals((Object)MapleInventoryType.EQUIPPED)) {
            return;
        }
        this.inventory.put(Short.valueOf(item.getPosition()), item);
    }
    
    public void move(final short sSlot, final short dSlot, final short slotMax) {
        if (dSlot > this.slotLimit) {
            return;
        }
        final Item source = (Item)this.inventory.get((Object)Short.valueOf(sSlot));
        final Item target = (Item)this.inventory.get((Object)Short.valueOf(dSlot));
        if (source == null) {
            throw new InventoryException("Trying to move empty slot");
        }
        if (target == null) {
            source.setPosition(dSlot);
            this.inventory.put(Short.valueOf(dSlot), source);
            this.inventory.remove((Object)Short.valueOf(sSlot));
        }
        else if (target.getItemId() == source.getItemId() && !GameConstants.isThrowingStar(source.getItemId()) && !GameConstants.isBullet(source.getItemId()) && target.getOwner().equals((Object)source.getOwner()) && target.getExpiration() == source.getExpiration()) {
            if (this.type.getType() == MapleInventoryType.EQUIP.getType() || this.type.getType() == MapleInventoryType.CASH.getType()) {
                this.swap((IItem)target, (IItem)source);
            }
            else if (source.getQuantity() + target.getQuantity() > slotMax) {
                source.setQuantity((short)(source.getQuantity() + target.getQuantity() - slotMax));
                target.setQuantity(slotMax);
            }
            else {
                target.setQuantity((short)(source.getQuantity() + target.getQuantity()));
                this.inventory.remove((Object)Short.valueOf(sSlot));
            }
        }
        else {
            this.swap((IItem)target, (IItem)source);
        }
    }
    
    private void swap(final IItem source, final IItem target) {
        this.inventory.remove((Object)Short.valueOf(source.getPosition()));
        this.inventory.remove((Object)Short.valueOf(target.getPosition()));
        final short swapPos = source.getPosition();
        source.setPosition(target.getPosition());
        target.setPosition(swapPos);
        this.inventory.put(Short.valueOf(source.getPosition()), source);
        this.inventory.put(Short.valueOf(target.getPosition()), target);
    }
    
    public IItem getItem(final short slot) {
        return (IItem)this.inventory.get((Object)Short.valueOf(slot));
    }
    
    public void removeItem(final short slot) {
        this.removeItem(slot, (short)1, false);
    }
    
    public void removeItem(final short slot, final short quantity, final boolean allowZero) {
        this.removeItem(slot, quantity, allowZero, null);
    }
    
    public void removeItem(final short slot, final short quantity, final boolean allowZero, final MapleCharacter chr) {
        final IItem item = (IItem)this.inventory.get((Object)Short.valueOf(slot));
        if (item == null) {
            return;
        }
        item.setQuantity((short)(item.getQuantity() - quantity));
        if (item.getQuantity() < 0) {
            item.setQuantity((short)0);
        }
        if (item.getQuantity() == 0 && !allowZero) {
            this.removeSlot(slot);
        }
        if (chr != null) {
            chr.getClient().sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(3, item)));
            chr.dropMessage(5, "加值道具[" + MapleItemInformationProvider.getInstance().getName(item.getItemId()) + "]因到期而消失了。");
        }
    }
    
    public void removeSlot(final short slot) {
        this.inventory.remove((Object)Short.valueOf(slot));
    }
    
    public boolean isFull() {
        return this.inventory.size() >= this.slotLimit;
    }
    
    public boolean isFull(final int margin) {
        return this.inventory.size() + margin >= this.slotLimit;
    }
    
    public short getNextFreeSlot() {
        if (this.isFull()) {
            return -1;
        }
        for (short i = 1; i <= this.slotLimit; ++i) {
            if (!this.inventory.keySet().contains((Object)Short.valueOf(i))) {
                return i;
            }
        }
        return -1;
    }
    
    public int getNumSlotLimit() {
        return this.inventory.size();
    }
    
    public short getNumFreeSlot() {
        if (this.isFull()) {
            return 0;
        }
        byte free = 0;
        for (short i = 1; i <= this.slotLimit; ++i) {
            if (!this.inventory.keySet().contains((Object)Short.valueOf(i))) {
                ++free;
            }
        }
        return (short)free;
    }
    
    public MapleInventoryType getType() {
        return this.type;
    }
    
    @Override
    public Iterator<IItem> iterator() {
        return Collections.unmodifiableCollection(this.inventory.values()).iterator();
    }
    
    public IItem getItemByInventoryItemId(final Long inventoryitemid) {
        for (final IItem item : this.inventory.values()) {
            if (item.getInventoryId() == (long)inventoryitemid) {
                return item;
            }
        }
        return null;
    }
}
