package server;

import server.maps.MapleMapObject;
import java.awt.Point;
import constants.WorldConstants;
import gui.CongMS;
import java.util.Collections;
import handling.world.World.Broadcast;
import tools.FileoutputUtil;
import client.MapleBuffStat;
import java.util.ArrayList;
import client.inventory.Equip;
import client.inventory.ItemFlag;
import java.util.Iterator;
import java.util.List;
import client.inventory.InventoryException;
import client.inventory.Item;
import client.inventory.MapleInventoryIdentifier;
import client.inventory.MaplePet;
import client.inventory.MapleInventoryType;
import client.inventory.ModifyInventory;
import client.MapleEquipOnlyId;
import tools.MaplePacketCreator;
import constants.GameConstants;
import client.MapleClient;
import client.inventory.IItem;
import tools.packet.MTSCSPacket;
import client.MapleCharacter;

public class MapleInventoryManipulator
{
    public static void addRing(final MapleCharacter chr, final int itemId, final int ringId, final int sn) {
        final CashItemInfo csi = CashItemFactory.getInstance().getItem(sn);
        if (csi == null) {
            return;
        }
        final IItem ring = chr.getCashInventory().toItem(csi, ringId);
        if (ring == null || ring.getUniqueId() != ringId || ring.getUniqueId() <= 0 || ring.getItemId() != itemId) {
            return;
        }
        chr.getCashInventory().addToInventory(ring);
        chr.getClient().sendPacket(MTSCSPacket.showBoughtCashItem(ring, sn, chr.getClient().getAccID()));
    }
    
    public static boolean addbyItem(final MapleClient c, final IItem item) {
        return addbyItem(c, item, false) >= 0;
    }
    
    public static short addbyItem(final MapleClient c, final IItem item, final boolean fromcs) {
        final MapleInventoryType type = GameConstants.getInventoryType(item.getItemId());
        final short newSlot = c.getPlayer().getInventory(type).addItem(item);
        if (newSlot == -1) {
            if (!fromcs) {
                c.sendPacket(MaplePacketCreator.getInventoryFull());
                c.sendPacket(MaplePacketCreator.getShowInventoryFull());
            }
            return newSlot;
        }
        if (item.hasSetOnlyId()) {
            item.setEquipOnlyId(MapleEquipOnlyId.getInstance().getNextEquipOnlyId());
        }
        if (!fromcs) {
            c.sendPacket(MaplePacketCreator.modifyInventory(true, new ModifyInventory(0, item)));
        }
        c.getPlayer().havePartyQuest(item.getItemId());
        return newSlot;
    }
    
    public static int getUniqueId(final int itemId, final MaplePet pet) {
        int uniqueid = -1;
        if (GameConstants.isPet(itemId)) {
            if (pet != null) {
                uniqueid = pet.getUniqueId();
            }
            else {
                uniqueid = MapleInventoryIdentifier.getInstance();
            }
        }
        else if (GameConstants.getInventoryType(itemId) == MapleInventoryType.CASH || MapleItemInformationProvider.getInstance().isCash(itemId)) {
            uniqueid = MapleInventoryIdentifier.getInstance();
        }
        return uniqueid;
    }
    
    public static boolean addById(final MapleClient c, final int itemId, final short quantity) {
        return addById(c, itemId, quantity, null, null, 0L);
    }
    
    public static boolean addById(final MapleClient c, final int itemId, final short quantity, final String owner) {
        return addById(c, itemId, quantity, owner, null, 0L);
    }
    
    public static byte addId(final MapleClient c, final int itemId, final short quantity, final String owner) {
        return addId(c, itemId, quantity, owner, null, 0L);
    }
    
    public static boolean addById(final MapleClient c, final int itemId, final short quantity, final String owner, final MaplePet pet) {
        return addById(c, itemId, quantity, owner, pet, 0L);
    }
    
    public static boolean addById(final MapleClient c, final int itemId, final short quantity, final String owner, final MaplePet pet, final long period) {
        return addId(c, itemId, quantity, owner, pet, period) >= 0;
    }
    
    public static byte addId(final MapleClient c, final int itemId, short quantity, final String owner, final MaplePet pet, final long period) {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (ii.isPickupRestricted(itemId) && c.getPlayer().haveItem(itemId, 1, true, false)) {
            c.sendPacket(MaplePacketCreator.getInventoryFull());
            c.sendPacket(MaplePacketCreator.showItemUnavailable());
            return -1;
        }
        final MapleInventoryType type = GameConstants.getInventoryType(itemId);
        final int uniqueid = getUniqueId(itemId, pet);
        short newSlot = -1;
        if (!type.equals((Object)MapleInventoryType.EQUIP)) {
            final short slotMax = ii.getSlotMax(c, itemId);
            final List<IItem> existing = c.getPlayer().getInventory(type).listById(itemId);
            if (!GameConstants.isRechargable(itemId)) {
                if (existing.size() > 0) {
                    final Iterator<IItem> i = existing.iterator();
                    while (quantity > 0 && i.hasNext()) {
                        final Item eItem = (Item)i.next();
                        final short oldQ = eItem.getQuantity();
                        if (oldQ < slotMax && (eItem.getOwner().equals((Object)owner) || owner == null) && eItem.getExpiration() == -1L) {
                            final short newQ = (short)Math.min(oldQ + quantity, (int)slotMax);
                            quantity -= (short)(newQ - oldQ);
                            eItem.setQuantity(newQ);
                            c.sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(1, (IItem)eItem)));
                        }
                    }
                }
                while (quantity > 0) {
                    final short newQ2 = (short)Math.min((int)quantity, (int)slotMax);
                    if (newQ2 == 0) {
                        c.getPlayer().havePartyQuest(itemId);
                        c.sendPacket(MaplePacketCreator.enableActions());
                        return (byte)newSlot;
                    }
                    quantity -= newQ2;
                    final Item nItem = new Item(itemId, (short)0, newQ2, (byte)0, uniqueid);
                    newSlot = c.getPlayer().getInventory(type).addItem((IItem)nItem);
                    if (newSlot == -1) {
                        c.sendPacket(MaplePacketCreator.getInventoryFull());
                        c.sendPacket(MaplePacketCreator.getShowInventoryFull());
                        return -1;
                    }
                    if (owner != null) {
                        nItem.setOwner(owner);
                    }
                    if (period > 0L) {
                        nItem.setExpiration(System.currentTimeMillis() + period * 60L * 60L * 1000L);
                    }
                    if (pet != null) {
                        nItem.setPet(pet);
                        pet.setInventoryPosition(newSlot);
                        c.getPlayer().addPet(pet);
                    }
                    c.sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(0, (IItem)nItem)));
                    if (GameConstants.isRechargable(itemId) && quantity == 0) {
                        break;
                    }
                }
            }
            else {
                final Item nItem = new Item(itemId, (short)0, quantity, (byte)0, uniqueid);
                newSlot = c.getPlayer().getInventory(type).addItem((IItem)nItem);
                if (newSlot == -1) {
                    c.sendPacket(MaplePacketCreator.getInventoryFull());
                    c.sendPacket(MaplePacketCreator.getShowInventoryFull());
                    return -1;
                }
                if (period > 0L) {
                    nItem.setExpiration(System.currentTimeMillis() + period * 24L * 60L * 60L * 1000L);
                }
                c.sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(0, (IItem)nItem)));
                c.sendPacket(MaplePacketCreator.enableActions());
            }
        }
        else {
            if (quantity != 1) {
                throw new InventoryException("Trying to create equip with non-one quantity");
            }
            final IItem nEquip = ii.getEquipById(itemId);
            if (owner != null) {
                nEquip.setOwner(owner);
            }
            nEquip.setUniqueId(uniqueid);
            if (period > 0L) {
                nEquip.setExpiration(System.currentTimeMillis() + period * 24L * 60L * 60L * 1000L);
            }
            if (nEquip.hasSetOnlyId()) {
                nEquip.setEquipOnlyId(MapleEquipOnlyId.getInstance().getNextEquipOnlyId());
            }
            newSlot = c.getPlayer().getInventory(type).addItem(nEquip);
            if (newSlot == -1) {
                c.sendPacket(MaplePacketCreator.getInventoryFull());
                c.sendPacket(MaplePacketCreator.getShowInventoryFull());
                return -1;
            }
            c.sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(0, nEquip)));
        }
        c.getPlayer().havePartyQuest(itemId);
        return (byte)newSlot;
    }
    
    public static boolean addById(final MapleClient c, final int itemId, final short quantity, final byte Flag) {
        return addById(c, itemId, quantity, null, null, 0L, Flag);
    }
    
    public static boolean addById(final MapleClient c, final int itemId, final short quantity, final String owner, final byte Flag) {
        return addById(c, itemId, quantity, owner, null, 0L, Flag);
    }
    
    public static byte addId(final MapleClient c, final int itemId, final short quantity, final String owner, final byte Flag) {
        return addId(c, itemId, quantity, owner, null, 0L, Flag);
    }
    
    public static boolean addById(final MapleClient c, final int itemId, final short quantity, final String owner, final MaplePet pet, final byte Flag) {
        return addById(c, itemId, quantity, owner, pet, 0L, Flag);
    }
    
    public static boolean addById(final MapleClient c, final int itemId, final short quantity, final String owner, final MaplePet pet, final long period, final byte Flag) {
        return addId(c, itemId, quantity, owner, pet, period, Flag) >= 0;
    }
    
    public static byte addId(final MapleClient c, final int itemId, short quantity, final String owner, final MaplePet pet, final long period, final byte Flag) {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (ii.isPickupRestricted(itemId) && c.getPlayer().haveItem(itemId, 1, true, false)) {
            c.getSession().write((Object)MaplePacketCreator.getInventoryFull());
            c.getSession().write((Object)MaplePacketCreator.showItemUnavailable());
            return -1;
        }
        final MapleInventoryType type = GameConstants.getInventoryType(itemId);
        final int uniqueid = getUniqueId(itemId, pet);
        short newSlot = -1;
        if (!type.equals((Object)MapleInventoryType.EQUIP)) {
            final short slotMax = ii.getSlotMax(c, itemId);
            final List<IItem> existing = c.getPlayer().getInventory(type).listById(itemId);
            if (!GameConstants.isRechargable(itemId)) {
                if (existing.size() > 0) {
                    final Iterator<IItem> i = existing.iterator();
                    while (quantity > 0 && i.hasNext()) {
                        final Item eItem = (Item)i.next();
                        final short oldQ = eItem.getQuantity();
                        if (oldQ < slotMax && (eItem.getOwner().equals((Object)owner) || owner == null) && eItem.getExpiration() == -1L) {
                            final short newQ = (short)Math.min(oldQ + quantity, (int)slotMax);
                            quantity -= (short)(newQ - oldQ);
                            eItem.setQuantity(newQ);
                            c.getSession().write((Object)MaplePacketCreator.updateInventorySlot(type, (IItem)eItem, false));
                        }
                    }
                }
                while (quantity > 0) {
                    final short newQ2 = (short)Math.min((int)quantity, (int)slotMax);
                    if (newQ2 == 0) {
                        c.getPlayer().havePartyQuest(itemId);
                        c.getSession().write((Object)MaplePacketCreator.enableActions());
                        return (byte)newSlot;
                    }
                    quantity -= newQ2;
                    final Item nItem = new Item(itemId, (short)0, newQ2, (byte)0, uniqueid);
                    newSlot = c.getPlayer().getInventory(type).addItem((IItem)nItem);
                    if (newSlot == -1) {
                        c.getSession().write((Object)MaplePacketCreator.getInventoryFull());
                        c.getSession().write((Object)MaplePacketCreator.getShowInventoryFull());
                        return -1;
                    }
                    if (owner != null) {
                        nItem.setOwner(owner);
                    }
                    if (Flag > 0 && ii.isCash(nItem.getItemId())) {
                        byte flag = nItem.getFlag();
                        flag |= (byte)ItemFlag.KARMA_EQ.getValue();
                        nItem.setFlag(flag);
                    }
                    if (period > 0L) {
                        nItem.setExpiration(System.currentTimeMillis() + period * 24L * 60L * 60L * 1000L);
                    }
                    if (pet != null) {
                        nItem.setPet(pet);
                        pet.setInventoryPosition(newSlot);
                        c.getPlayer().addPet(pet);
                    }
                    c.getSession().write((Object)MaplePacketCreator.addInventorySlot(type, (IItem)nItem));
                    if (GameConstants.isRechargable(itemId) && quantity == 0) {
                        break;
                    }
                }
            }
            else {
                final Item nItem = new Item(itemId, (short)0, quantity, (byte)0, uniqueid);
                newSlot = c.getPlayer().getInventory(type).addItem((IItem)nItem);
                if (newSlot == -1) {
                    c.getSession().write((Object)MaplePacketCreator.getInventoryFull());
                    c.getSession().write((Object)MaplePacketCreator.getShowInventoryFull());
                    return -1;
                }
                if (period > 0L) {
                    nItem.setExpiration(System.currentTimeMillis() + period * 24L * 60L * 60L * 1000L);
                }
                c.getSession().write((Object)MaplePacketCreator.addInventorySlot(type, (IItem)nItem));
                c.getSession().write((Object)MaplePacketCreator.enableActions());
            }
        }
        else {
            if (quantity != 1) {
                throw new InventoryException("Trying to create equip with non-one quantity");
            }
            final IItem nEquip = ii.getEquipById(itemId);
            if (owner != null) {
                nEquip.setOwner(owner);
            }
            nEquip.setUniqueId(uniqueid);
            if (Flag > 0 && ii.isCash(nEquip.getItemId())) {
                byte flag2 = nEquip.getFlag();
                flag2 |= (byte)ItemFlag.KARMA_USE.getValue();
                nEquip.setFlag(flag2);
            }
            if (period > 0L) {
                nEquip.setExpiration(System.currentTimeMillis() + period * 24L * 60L * 60L * 1000L);
            }
            newSlot = c.getPlayer().getInventory(type).addItem(nEquip);
            if (newSlot == -1) {
                c.getSession().write((Object)MaplePacketCreator.getInventoryFull());
                c.getSession().write((Object)MaplePacketCreator.getShowInventoryFull());
                return -1;
            }
            c.getSession().write((Object)MaplePacketCreator.addInventorySlot(type, nEquip));
        }
        c.getPlayer().havePartyQuest(itemId);
        return (byte)newSlot;
    }
    
    public static IItem addbyId_Gachapon(final MapleClient c, final int itemId, short quantity) {
        if (c.getPlayer().getInventory(MapleInventoryType.EQUIP).getNextFreeSlot() == -1 || c.getPlayer().getInventory(MapleInventoryType.USE).getNextFreeSlot() == -1 || c.getPlayer().getInventory(MapleInventoryType.ETC).getNextFreeSlot() == -1 || c.getPlayer().getInventory(MapleInventoryType.SETUP).getNextFreeSlot() == -1) {
            return null;
        }
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (ii.isPickupRestricted(itemId) && c.getPlayer().haveItem(itemId, 1, true, false)) {
            c.sendPacket(MaplePacketCreator.getInventoryFull());
            c.sendPacket(MaplePacketCreator.showItemUnavailable());
            return null;
        }
        final MapleInventoryType type = GameConstants.getInventoryType(itemId);
        if (!type.equals((Object)MapleInventoryType.EQUIP)) {
            final short slotMax = ii.getSlotMax(c, itemId);
            final List<IItem> existing = c.getPlayer().getInventory(type).listById(itemId);
            if (!GameConstants.isRechargable(itemId)) {
                IItem nItem = null;
                boolean recieved = false;
                if (existing.size() > 0) {
                    final Iterator<IItem> i = existing.iterator();
                    while (quantity > 0 && i.hasNext()) {
                        nItem = (Item)i.next();
                        final short oldQ = nItem.getQuantity();
                        if (oldQ < slotMax) {
                            recieved = true;
                            final short newQ = (short)Math.min(oldQ + quantity, (int)slotMax);
                            quantity -= (short)(newQ - oldQ);
                            nItem.setQuantity(newQ);
                            c.sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(1, nItem)));
                        }
                    }
                }
                while (quantity > 0) {
                    final short newQ2 = (short)Math.min((int)quantity, (int)slotMax);
                    if (newQ2 == 0) {
                        break;
                    }
                    quantity -= newQ2;
                    nItem = new Item(itemId, (short)0, newQ2, (byte)0);
                    final short newSlot = c.getPlayer().getInventory(type).addItem(nItem);
                    if (newSlot == -1 && recieved) {
                        return nItem;
                    }
                    if (newSlot == -1) {
                        return null;
                    }
                    recieved = true;
                    c.sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(0, nItem)));
                    if (GameConstants.isRechargable(itemId) && quantity == 0) {
                        break;
                    }
                }
                if (recieved) {
                    c.getPlayer().havePartyQuest(nItem.getItemId());
                    return nItem;
                }
                return null;
            }
            else {
                final Item nItem2 = new Item(itemId, (short)0, quantity, (byte)0);
                final short newSlot2 = c.getPlayer().getInventory(type).addItem((IItem)nItem2);
                if (newSlot2 == -1) {
                    return null;
                }
                c.sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(0, (IItem)nItem2)));
                c.getPlayer().havePartyQuest(nItem2.getItemId());
                return nItem2;
            }
        }
        else {
            if (quantity != 1) {
                throw new InventoryException("Trying to create equip with non-one quantity");
            }
            IItem item = null;
            switch (itemId) {
                case 1112413: {
                    item = ii.randomizeStats((Equip)ii.getEquipById(itemId), itemId);
                    break;
                }
                case 1112414: {
                    item = ii.randomizeStats((Equip)ii.getEquipById(itemId), itemId);
                    break;
                }
                case 1112405: {
                    item = ii.randomizeStats((Equip)ii.getEquipById(itemId), itemId);
                    break;
                }
                default: {
                    item = ii.randomizeStats((Equip)ii.getEquipById(itemId));
                    break;
                }
            }
            final short newSlot3 = c.getPlayer().getInventory(type).addItem(item);
            if (newSlot3 == -1) {
                return null;
            }
            if (item.hasSetOnlyId()) {
                item.setEquipOnlyId(MapleEquipOnlyId.getInstance().getNextEquipOnlyId());
            }
            c.sendPacket(MaplePacketCreator.modifyInventory(true, new ModifyInventory(0, item)));
            c.getPlayer().havePartyQuest(item.getItemId());
            return item;
        }
    }
    
    public static IItem addbyId_GachaponGM(final MapleClient c, final int itemId, short quantity) {
        if (c.getPlayer().getInventory(MapleInventoryType.EQUIP).getNextFreeSlot() == -1 || c.getPlayer().getInventory(MapleInventoryType.USE).getNextFreeSlot() == -1 || c.getPlayer().getInventory(MapleInventoryType.ETC).getNextFreeSlot() == -1 || c.getPlayer().getInventory(MapleInventoryType.SETUP).getNextFreeSlot() == -1) {
            return null;
        }
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (ii.isPickupRestricted(itemId) && c.getPlayer().haveItem(itemId, 1, true, false)) {
            c.sendPacket(MaplePacketCreator.getInventoryFull());
            c.sendPacket(MaplePacketCreator.showItemUnavailable());
            return null;
        }
        final MapleInventoryType type = GameConstants.getInventoryType(itemId);
        if (!type.equals((Object)MapleInventoryType.EQUIP)) {
            final short slotMax = ii.getSlotMax(c, itemId);
            if (GameConstants.isRechargable(itemId)) {
                final Item nItem = new Item(itemId, (short)0, quantity, (byte)0);
                return nItem;
            }
            IItem nItem2 = null;
            boolean recieved = false;
            while (quantity > 0) {
                final short newQ = (short)Math.min((int)quantity, (int)slotMax);
                if (newQ == 0) {
                    break;
                }
                quantity -= newQ;
                nItem2 = new Item(itemId, (short)0, newQ, (byte)0);
                recieved = true;
                if (GameConstants.isRechargable(itemId) && quantity == 0) {
                    break;
                }
            }
            if (recieved) {
                return nItem2;
            }
            return null;
        }
        else {
            if (quantity == 1) {
                final IItem item = ii.randomizeStats((Equip)ii.getEquipById(itemId));
                return item;
            }
            throw new InventoryException("Trying to create equip with non-one quantity");
        }
    }
    
    public static IItem addbyId_GachaponTime(final MapleClient c, final int itemId, short quantity, final long period) {
        if (c.getPlayer().getInventory(MapleInventoryType.EQUIP).getNextFreeSlot() == -1 || c.getPlayer().getInventory(MapleInventoryType.USE).getNextFreeSlot() == -1 || c.getPlayer().getInventory(MapleInventoryType.ETC).getNextFreeSlot() == -1 || c.getPlayer().getInventory(MapleInventoryType.SETUP).getNextFreeSlot() == -1) {
            return null;
        }
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (ii.isPickupRestricted(itemId) && c.getPlayer().haveItem(itemId, 1, true, false)) {
            c.sendPacket(MaplePacketCreator.getInventoryFull());
            c.sendPacket(MaplePacketCreator.showItemUnavailable());
            return null;
        }
        final MapleInventoryType type = GameConstants.getInventoryType(itemId);
        if (!type.equals((Object)MapleInventoryType.EQUIP)) {
            final short slotMax = ii.getSlotMax(c, itemId);
            final List<IItem> existing = c.getPlayer().getInventory(type).listById(itemId);
            if (!GameConstants.isRechargable(itemId)) {
                IItem nItem = null;
                boolean recieved = false;
                if (existing.size() > 0) {
                    final Iterator<IItem> i = existing.iterator();
                    while (quantity > 0 && i.hasNext()) {
                        nItem = (Item)i.next();
                        final short oldQ = nItem.getQuantity();
                        if (oldQ < slotMax) {
                            recieved = true;
                            final short newQ = (short)Math.min(oldQ + quantity, (int)slotMax);
                            quantity -= (short)(newQ - oldQ);
                            nItem.setQuantity(newQ);
                            c.sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(1, nItem)));
                        }
                    }
                }
                while (quantity > 0) {
                    final short newQ2 = (short)Math.min((int)quantity, (int)slotMax);
                    if (newQ2 == 0) {
                        break;
                    }
                    quantity -= newQ2;
                    nItem = new Item(itemId, (short)0, newQ2, (byte)0);
                    final short newSlot = c.getPlayer().getInventory(type).addItem(nItem);
                    if (newSlot == -1 && recieved) {
                        return nItem;
                    }
                    if (newSlot == -1) {
                        return null;
                    }
                    if (period > 0L) {
                        nItem.setExpiration(System.currentTimeMillis() + period * 24L * 60L * 60L * 1000L);
                    }
                    recieved = true;
                    c.sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(0, nItem)));
                    if (GameConstants.isRechargable(itemId) && quantity == 0) {
                        break;
                    }
                }
                if (recieved) {
                    c.getPlayer().havePartyQuest(nItem.getItemId());
                    return nItem;
                }
                return null;
            }
            else {
                final Item nItem2 = new Item(itemId, (short)0, quantity, (byte)0);
                final short newSlot2 = c.getPlayer().getInventory(type).addItem((IItem)nItem2);
                if (newSlot2 == -1) {
                    return null;
                }
                if (period > 0L) {
                    nItem2.setExpiration(System.currentTimeMillis() + period * 24L * 60L * 60L * 1000L);
                }
                c.sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(0, (IItem)nItem2)));
                c.getPlayer().havePartyQuest(nItem2.getItemId());
                return nItem2;
            }
        }
        else {
            if (quantity != 1) {
                throw new InventoryException("Trying to create equip with non-one quantity");
            }
            final IItem item = ii.randomizeStats((Equip)ii.getEquipById(itemId));
            final short newSlot3 = c.getPlayer().getInventory(type).addItem(item);
            if (newSlot3 == -1) {
                return null;
            }
            if (period > 0L) {
                item.setExpiration(System.currentTimeMillis() + period * 24L * 60L * 60L * 1000L);
            }
            if (item.hasSetOnlyId()) {
                item.setEquipOnlyId(MapleEquipOnlyId.getInstance().getNextEquipOnlyId());
            }
            c.sendPacket(MaplePacketCreator.modifyInventory(true, new ModifyInventory(0, item)));
            c.getPlayer().havePartyQuest(item.getItemId());
            return item;
        }
    }
    
    public static boolean addFromDrop(final MapleClient c, final IItem item, final boolean show) {
        return addFromDrop(c, item, show, false, false);
    }
    
    public static boolean addFromDrop(final MapleClient c, IItem item, final boolean show, final boolean enhance, final boolean isPetPickup) {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (ii.isPickupRestricted(item.getItemId()) && c.getPlayer().haveItem(item.getItemId(), 1, true, false)) {
            c.sendPacket(MaplePacketCreator.getInventoryFull());
            c.sendPacket(MaplePacketCreator.showItemUnavailable());
            return false;
        }
        final int before = c.getPlayer().itemQuantity(item.getItemId());
        short quantity = item.getQuantity();
        final MapleInventoryType type = GameConstants.getInventoryType(item.getItemId());
        if (!type.equals((Object)MapleInventoryType.EQUIP)) {
            final short slotMax = ii.getSlotMax(c, item.getItemId());
            final List<IItem> existing = c.getPlayer().getInventory(type).listById(item.getItemId());
            if (!GameConstants.isRechargable(item.getItemId())) {
                if (quantity <= 0) {
                    c.sendPacket(MaplePacketCreator.getInventoryFull());
                    c.sendPacket(MaplePacketCreator.showItemUnavailable());
                    return false;
                }
                if (existing.size() > 0) {
                    final Iterator<IItem> i = existing.iterator();
                    while (quantity > 0 && i.hasNext()) {
                        final Item eItem = (Item)i.next();
                        final short oldQ = eItem.getQuantity();
                        if (oldQ < slotMax && item.getOwner().equals((Object)eItem.getOwner()) && item.getExpiration() == eItem.getExpiration()) {
                            final short newQ = (short)Math.min(oldQ + quantity, (int)slotMax);
                            quantity -= (short)(newQ - oldQ);
                            eItem.setQuantity(newQ);
                            c.sendPacket(MaplePacketCreator.modifyInventory(!isPetPickup, new ModifyInventory(1, (IItem)eItem)));
                        }
                    }
                }
                while (quantity > 0) {
                    final short newQ2 = (short)Math.min((int)quantity, (int)slotMax);
                    quantity -= newQ2;
                    final Item nItem = new Item(item.getItemId(), (short)0, newQ2, item.getFlag());
                    nItem.setExpiration(item.getExpiration());
                    nItem.setOwner(item.getOwner());
                    nItem.setPet(item.getPet());
                    final short newSlot = c.getPlayer().getInventory(type).addItem((IItem)nItem);
                    if (newSlot == -1) {
                        c.sendPacket(MaplePacketCreator.getInventoryFull());
                        c.sendPacket(MaplePacketCreator.getShowInventoryFull());
                        item.setQuantity((short)(quantity + newQ2));
                        return false;
                    }
                    c.sendPacket(MaplePacketCreator.modifyInventory(!isPetPickup, new ModifyInventory(0, (IItem)nItem)));
                }
            }
            else {
                final Item nItem2 = new Item(item.getItemId(), (short)0, quantity, item.getFlag());
                nItem2.setExpiration(item.getExpiration());
                nItem2.setOwner(item.getOwner());
                nItem2.setPet(item.getPet());
                final short newSlot2 = c.getPlayer().getInventory(type).addItem((IItem)nItem2);
                if (newSlot2 == -1) {
                    c.sendPacket(MaplePacketCreator.getInventoryFull());
                    c.sendPacket(MaplePacketCreator.getShowInventoryFull());
                    return false;
                }
                c.sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(0, (IItem)nItem2)));
                c.sendPacket(MaplePacketCreator.enableActions());
            }
        }
        else {
            if (quantity != 1) {
                throw new RuntimeException("Trying to create equip with non-one quantity");
            }
            if (item.hasSetOnlyId()) {
                item.setEquipOnlyId(MapleEquipOnlyId.getInstance().getNextEquipOnlyId());
            }
            if (enhance) {
                item = checkEnhanced(item, c.getPlayer());
            }
            final short newSlot3 = c.getPlayer().getInventory(type).addItem(item);
            if (newSlot3 == -1) {
                c.sendPacket(MaplePacketCreator.getInventoryFull());
                c.sendPacket(MaplePacketCreator.getShowInventoryFull());
                return false;
            }
            c.sendPacket(MaplePacketCreator.modifyInventory(!isPetPickup, new ModifyInventory(0, item)));
        }
        if (item.getQuantity() < 50 || GameConstants.isUpgradeScroll(item.getItemId())) {}
        if (before == 0) {
            switch (item.getItemId()) {
                case 4000516: {
                    c.getPlayer().dropMessage(5, "你已經获得了一個 香爐， 可以到不夜城尋找龍山寺師父對話。");
                    break;
                }
                case 4031875: {
                    c.getPlayer().dropMessage(5, "You have gained a Powder Keg, you can give this in to Aramia of Henesys.");
                    break;
                }
                case 4001246: {
                    c.getPlayer().dropMessage(5, "You have gained a Warm Sun, you can give this in to Maple Tree Hill through @joyce.");
                    break;
                }
                case 4001473: {
                    c.getPlayer().dropMessage(5, "You have gained a Tree Decoration, you can give this in to White Christmas Hill through @joyce.");
                    break;
                }
            }
        }
        c.getPlayer().havePartyQuest(item.getItemId());
        if (show) {
            c.sendPacket(MaplePacketCreator.getShowItemGain(item.getItemId(), item.getQuantity()));
        }
        return true;
    }
    
    private static final IItem checkEnhanced(final IItem before, final MapleCharacter chr) {
        if (before instanceof Equip) {
            final Equip eq = (Equip)before;
            if (eq.getState() == 0 && (eq.getUpgradeSlots() >= 1 || eq.getLevel() >= 1) && Randomizer.nextInt(100) > 80) {
                eq.resetPotential();
            }
        }
        return before;
    }
    
    private static int rand(final int min, final int max) {
        return Math.abs(Randomizer.rand(min, max));
    }
    
    public static boolean checkSpace(final MapleClient c, final int itemid, int quantity, final String owner) {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (c.getPlayer() == null || (ii.isPickupRestricted(itemid) && c.getPlayer().haveItem(itemid, 1, true, false)) || !ii.itemExists(itemid)) {
            c.getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
            return false;
        }
        if (quantity <= 0 && !GameConstants.isRechargable(itemid)) {
            return false;
        }
        final MapleInventoryType type = GameConstants.getInventoryType(itemid);
        if (c == null || c.getPlayer() == null || c.getPlayer().getInventory(type) == null) {
            return false;
        }
        if (!type.equals((Object)MapleInventoryType.EQUIP)) {
            final short slotMax = ii.getSlotMax(c, itemid);
            final List<IItem> existing = c.getPlayer().getInventory(type).listById(itemid);
            if (!GameConstants.isRechargable(itemid) && existing.size() > 0) {
                for (final IItem eItem : existing) {
                    final short oldQ = eItem.getQuantity();
                    if (oldQ < slotMax && owner != null && owner.equals((Object)eItem.getOwner())) {
                        final short newQ = (short)Math.min(oldQ + quantity, (int)slotMax);
                        quantity -= newQ - oldQ;
                    }
                    if (quantity <= 0) {
                        break;
                    }
                }
            }
            int numSlotsNeeded;
            if (slotMax > 0 && !GameConstants.isRechargable(itemid)) {
                numSlotsNeeded = (int)Math.ceil((double)quantity / (double)slotMax);
            }
            else {
                numSlotsNeeded = 1;
            }
            return !c.getPlayer().getInventory(type).isFull(numSlotsNeeded - 1);
        }
        return !c.getPlayer().getInventory(type).isFull();
    }
    
    public static void removeFromSlot(final MapleClient c, final MapleInventoryType type, final short slot, final short quantity, final boolean fromDrop) {
        removeFromSlot(c, type, slot, quantity, fromDrop, false);
    }
    
    public static void removeFromSlot(final MapleClient c, final MapleInventoryType type, final short slot, final short quantity, final boolean fromDrop, final boolean consume) {
        if (c.getPlayer() == null || c.getPlayer().getInventory(type) == null) {
            return;
        }
        final IItem item = c.getPlayer().getInventory(type).getItem(slot);
        if (item != null) {
            final boolean allowZero = consume && GameConstants.isRechargable(item.getItemId());
            c.getPlayer().getInventory(type).removeItem(slot, quantity, allowZero);
            if (item.getQuantity() == 0 && !allowZero) {
                c.sendPacket(MaplePacketCreator.modifyInventory(fromDrop, new ModifyInventory(3, item)));
            }
            else {
                c.sendPacket(MaplePacketCreator.modifyInventory(fromDrop, new ModifyInventory(1, item)));
            }
        }
    }
    
    public static boolean removeById(final MapleClient c, final MapleInventoryType type, final int itemId, final int quantity, final boolean fromDrop, final boolean consume) {
        int remremove = quantity;
        for (final IItem item : c.getPlayer().getInventory(type).listById(itemId)) {
            if (remremove <= item.getQuantity()) {
                removeFromSlot(c, type, item.getPosition(), (short)remremove, fromDrop, consume);
                remremove = 0;
                break;
            }
            remremove -= item.getQuantity();
            removeFromSlot(c, type, item.getPosition(), item.getQuantity(), fromDrop, consume);
        }
        return remremove <= 0;
    }
    
    public static void move(final MapleClient c, final MapleInventoryType type, final short src, final short dst) {
        if (src < 0 || dst < 0) {
            return;
        }
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final IItem source = c.getPlayer().getInventory(type).getItem(src);
        final IItem initialTarget = c.getPlayer().getInventory(type).getItem(dst);
        if (source == null) {
            return;
        }
        if (c.getPlayer().getGMLevel() > 0) {
            c.getPlayer().dropMessage("移动物品代码ID：" + source.getItemId());
        }
        short olddstQ = -1;
        if (initialTarget != null) {
            olddstQ = initialTarget.getQuantity();
        }
        final short oldsrcQ = source.getQuantity();
        final short slotMax = ii.getSlotMax(c, source.getItemId());
        c.getPlayer().getInventory(type).move(src, dst, slotMax);
        final List<ModifyInventory> mods = new ArrayList<ModifyInventory>();
        if (!type.equals((Object)MapleInventoryType.EQUIP) && !type.equals((Object)MapleInventoryType.CASH) && initialTarget != null && initialTarget.getItemId() == source.getItemId() && !GameConstants.isRechargable(source.getItemId())) {
            if (olddstQ + oldsrcQ > slotMax) {
                mods.add(new ModifyInventory(1, source));
                mods.add(new ModifyInventory(1, initialTarget));
            }
            else {
                mods.add(new ModifyInventory(3, source));
                mods.add(new ModifyInventory(1, initialTarget));
            }
        }
        else {
            mods.add(new ModifyInventory(2, source, src));
        }
        c.sendPacket(MaplePacketCreator.modifyInventory(true, mods));
    }
    
    public static void equip(final MapleClient c, final short src, final short dst) {
        Equip source = (Equip)c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem(src);
        if (source == null) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        boolean itemChanged = false;
        if (MapleItemInformationProvider.getInstance().isUntradeableOnEquip(source.getItemId())) {
            if (!ItemFlag.UNTRADEABLE.check((int)source.getFlag())) {
                source.setFlag((byte)(source.getFlag() + ItemFlag.UNTRADEABLE.getValue()));
            }
            itemChanged = true;
        }
        if (GameConstants.isGMEquip(source.getItemId()) && !c.getPlayer().isGM() && !c.getChannelServer().CanGMItem()) {
            c.getPlayer().dropMessage(1, "只有管理員能装备這件道具。");
            c.getPlayer().removeAll(source.getItemId(), true);
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (c.getPlayer().getDebugMessage()) {
            c.getPlayer().dropMessage("穿装备: src : " + (int)src + " dst : " + (int)dst + " 代码：" + source.getItemId());
        }
        if (dst == -6) {
            final IItem top = c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem((short)(-5));
            if (top != null && isOverall(top.getItemId())) {
                if (c.getPlayer().getInventory(MapleInventoryType.EQUIP).isFull()) {
                    c.sendPacket(MaplePacketCreator.getInventoryFull());
                    c.sendPacket(MaplePacketCreator.getShowInventoryFull());
                    return;
                }
                unequip(c, (short)(-5), c.getPlayer().getInventory(MapleInventoryType.EQUIP).getNextFreeSlot());
            }
        }
        else if (dst == -5) {
            final IItem bottom = c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem((short)(-6));
            if (bottom != null && isOverall(source.getItemId())) {
                if (c.getPlayer().getInventory(MapleInventoryType.EQUIP).isFull()) {
                    c.sendPacket(MaplePacketCreator.getInventoryFull());
                    c.sendPacket(MaplePacketCreator.getShowInventoryFull());
                    return;
                }
                unequip(c, (short)(-6), c.getPlayer().getInventory(MapleInventoryType.EQUIP).getNextFreeSlot());
            }
        }
        else if (dst == -10) {
            final Equip weapon = (Equip)c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem((short)(-11));
            if (weapon != null && MapleItemInformationProvider.getInstance().isTwoHanded(weapon.getItemId())) {
                if (c.getPlayer().getInventory(MapleInventoryType.EQUIP).isFull()) {
                    c.sendPacket(MaplePacketCreator.getInventoryFull());
                    c.sendPacket(MaplePacketCreator.getShowInventoryFull());
                    return;
                }
                unequip(c, (short)(-11), c.getPlayer().getInventory(MapleInventoryType.EQUIP).getNextFreeSlot());
            }
        }
        else if (dst == -11) {
            final IItem shield = c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem((short)(-10));
            if (shield != null && MapleItemInformationProvider.getInstance().isTwoHanded(source.getItemId())) {
                if (c.getPlayer().getInventory(MapleInventoryType.EQUIP).isFull()) {
                    c.sendPacket(MaplePacketCreator.getInventoryFull());
                    c.sendPacket(MaplePacketCreator.getShowInventoryFull());
                    return;
                }
                unequip(c, (short)(-10), c.getPlayer().getInventory(MapleInventoryType.EQUIP).getNextFreeSlot());
            }
        }
        if (dst == -18 && c.getPlayer().getMount() != null) {
            c.getPlayer().getMount().setItemId(source.getItemId());
        }
        if (source.getItemId() == 1122017 || source.getItemId() == 1122086 || source.getItemId() == 1122207 || source.getItemId() == 1122215) {
            c.getPlayer().startFairySchedule(true, true);
        }
        source = (Equip)c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem(src);
        final Equip target = (Equip)c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem(dst);
        c.getPlayer().getInventory(MapleInventoryType.EQUIP).removeSlot(src);
        if (target != null) {
            c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).removeSlot(dst);
        }
        final List<ModifyInventory> mods = new ArrayList<ModifyInventory>();
        if (itemChanged) {
            mods.add(new ModifyInventory(3, (IItem)source));
            mods.add(new ModifyInventory(0, source.copy()));
        }
        source.setPosition(dst);
        c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).addFromDB((IItem)source);
        if (target != null) {
            target.setPosition(src);
            c.getPlayer().getInventory(MapleInventoryType.EQUIP).addFromDB((IItem)target);
        }
        if (c.getPlayer().getBuffedValue(MapleBuffStat.BOOSTER) != null && isWeapon(source.getItemId())) {
            c.getPlayer().cancelBuffStats(MapleBuffStat.BOOSTER);
        }
        mods.add(new ModifyInventory(2, (IItem)source, src));
        c.sendPacket(MaplePacketCreator.modifyInventory(true, mods));
        final int reqlv = MapleItemInformationProvider.getInstance().getReqLevel(source.getItemId());
        if (reqlv > c.getPlayer().getLevel() + c.getPlayer().getStat().levelBonus && !c.getPlayer().isGM()) {
            FileoutputUtil.logToFile("logs/Hack/Ban/修改封包.txt", "\r\n " + FileoutputUtil.NowTime() + " 玩家：" + c.getPlayer().getName() + "(" + c.getPlayer().getId() + ") <等級: " + (int)c.getPlayer().getLevel() + " > 修改装备(" + source.getItemId() + ")封包，穿上装备時封鎖。 該装备需求等級: " + reqlv);
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[封鎖系統] " + c.getPlayer().getName() + " 因為修改封包而被管理員永久停權。"));
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语]  " + c.getPlayer().getName() + "(" + c.getPlayer().getId() + ") <等級: " + (int)c.getPlayer().getLevel() + " > 修改装备(" + source.getItemId() + ")封包，穿上装备時封鎖。 該装备需求等級: " + reqlv));
            c.getPlayer().ban("修改封包", true, true, false);
            c.getSession().close();
            return;
        }
        c.getPlayer().equipChanged();
    }
    
    private static boolean isOverall(final int itemId) {
        return itemId / 10000 == 105;
    }
    
    private static boolean isWeapon(final int itemId) {
        return itemId >= 1302000 && itemId < 1492024;
    }
    
    public static void unequip(final MapleClient c, final short src, final short dst) {
        final Equip source = (Equip)c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem(src);
        final Equip target = (Equip)c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem(dst);
        if (dst < 0) {
            return;
        }
        if (source == null) {
            return;
        }
        if (target != null && src <= 0) {
            c.sendPacket(MaplePacketCreator.getInventoryFull());
            return;
        }
        if (c.getPlayer().getDebugMessage()) {
            c.getPlayer().dropMessage("脫装备: src : " + (int)src + " dst : " + (int)dst + " 代码：" + source.getItemId());
        }
        if (source.getItemId() == 1122017 || source.getItemId() == 1122086 || source.getItemId() == 1122207 || source.getItemId() == 1122215) {
            c.getPlayer().cancelFairySchedule(true);
        }
        c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).removeSlot(src);
        if (target != null) {
            c.getPlayer().getInventory(MapleInventoryType.EQUIP).removeSlot(dst);
        }
        source.setPosition(dst);
        c.getPlayer().getInventory(MapleInventoryType.EQUIP).addFromDB((IItem)source);
        if (target != null) {
            target.setPosition(src);
            c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).addFromDB((IItem)target);
        }
        c.sendPacket(MaplePacketCreator.modifyInventory(true, Collections.singletonList(new ModifyInventory(2, (IItem)source, src))));
        final int reqlv = MapleItemInformationProvider.getInstance().getReqLevel(source.getItemId());
        if (reqlv > c.getPlayer().getLevel() + c.getPlayer().getStat().levelBonus && !c.getPlayer().isGM()) {
            FileoutputUtil.logToFile("logs/Hack/Ban/修改封包.txt", "\r\n " + FileoutputUtil.NowTime() + " 玩家：" + c.getPlayer().getName() + "(" + c.getPlayer().getId() + ") <等級: " + (int)c.getPlayer().getLevel() + " > 修改装备(" + source.getItemId() + ")封包，脫除装备時封鎖。 該装备需求等級: " + reqlv);
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[封鎖系統] " + c.getPlayer().getName() + " 因為修改封包而被管理員永久停權。"));
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语]  " + c.getPlayer().getName() + "(" + c.getPlayer().getId() + ") <等級: " + (int)c.getPlayer().getLevel() + " > 修改装备(" + source.getItemId() + ")封包，脫除装备時封鎖。 該装备需求等級: " + reqlv));
            c.getPlayer().ban("修改封包", true, true, false);
            c.getSession().close();
            return;
        }
        c.getPlayer().equipChanged();
    }
    
    public static boolean dropCs(final MapleClient c, final MapleInventoryType type, final short src, final short quantity) {
        return drop(c, type, src, quantity, false, true);
    }
    
    public static boolean drop(final MapleClient c, final MapleInventoryType type, final short src, final short quantity) {
        return drop(c, type, src, quantity, false);
    }
    
    public static boolean drop(final MapleClient c, final MapleInventoryType type, final short src, final short quantity, final boolean npcInduced) {
        return drop(c, type, src, quantity, npcInduced, false);
    }
    
    public static boolean drop(final MapleClient c, MapleInventoryType type, final short src, final short quantity, final boolean npcInduced, final boolean cs) {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (quantity < 0) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return false;
            }
        final int 丢出物品开关 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"丢出物品开关"));
        if (丢出物品开关 > 0) {
            c.getPlayer().dropMessage(1, "管理员从后台关闭了物品丢出功能。");
            c.sendPacket(MaplePacketCreator.enableActions());
            return false;
        }
        try {
            if (src < 0) {
                type = MapleInventoryType.EQUIPPED;
            }
            if (c.getPlayer() == null) {
                return false;
            }
            final IItem source = c.getPlayer().getInventory(type).getItem(src);
            if (!cs && ii.isCash(source.getItemId())) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return false;
            }
            if (source.getItemId() == 4110010) {
                c.getPlayer().dropMessage(1, "無法丟落該物品。");
                c.sendPacket(MaplePacketCreator.enableActions());
                return false;
            }
            if (source.getItemId() == 2340000 || source.getItemId() == 2049100) {
                if (WorldConstants.DropItem) {
                    Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM 密语系統] 危險貴重物品 账号 " + c.getAccountName() + " 账号ID " + c.getAccID() + " 角色名 " + c.getPlayer().getName() + " 角色ID " + c.getPlayer().getId() + " 類型 " + (Object)type + " src " + (int)src + (int)quantity + " 物品 " + ii.getName(source.getItemId()) + " (" + source.getItemId() + ") x" + (int)quantity));
                }
                FileoutputUtil.logToFile("logs/Data/丟棄貴重物品.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号 " + c.getAccountName() + " 账号ID " + c.getAccID() + " 角色名 " + c.getPlayer().getName() + " 角色ID " + c.getPlayer().getId() + " 類型 " + (Object)type + " src " + (int)src + (int)quantity + " 物品 " + ii.getName(source.getItemId()) + " (" + source.getItemId() + ") x" + (int)quantity);
            }
            if (source == null || (!npcInduced && GameConstants.isPet(source.getItemId()))) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return false;
            }
            if (!cs && ii.isCash(source.getItemId())) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return false;
            }
            final byte flag = source.getFlag();
            if (quantity > source.getQuantity()) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return false;
            }
            if (ItemFlag.LOCK.check((int)flag) || (quantity != 1 && type == MapleInventoryType.EQUIP)) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return false;
            }
            final Point dropPos = new Point(c.getPlayer().getPosition());
            c.getPlayer().getCheatTracker().checkDrop();
            if (quantity < source.getQuantity() && !GameConstants.isRechargable(source.getItemId())) {
                final IItem target = source.copy();
                target.setQuantity(quantity);
                source.setQuantity((short)(source.getQuantity() - quantity));
                c.sendPacket(MaplePacketCreator.dropInventoryItemUpdate(type, source));
                if (ii.isDropRestricted(target.getItemId()) || ii.isAccountShared(target.getItemId())) {
                    if (ItemFlag.KARMA_EQ.check((int)flag)) {
                        target.setFlag((byte)(flag - ItemFlag.KARMA_EQ.getValue()));
                        c.getPlayer().getMap().spawnItemDrop((MapleMapObject)c.getPlayer(), c.getPlayer(), target, dropPos, true, true);
                    }
                    else if (ItemFlag.KARMA_USE.check((int)flag)) {
                        target.setFlag((byte)(flag - ItemFlag.KARMA_USE.getValue()));
                        c.getPlayer().getMap().spawnItemDrop((MapleMapObject)c.getPlayer(), c.getPlayer(), target, dropPos, true, true);
                    }
                    else {
                        c.getPlayer().getMap().disappearingItemDrop((MapleMapObject)c.getPlayer(), c.getPlayer(), target, dropPos);
                    }
                }
                else if (GameConstants.isPet(source.getItemId()) || ItemFlag.UNTRADEABLE.check((int)flag)) {
                    c.getPlayer().getMap().disappearingItemDrop((MapleMapObject)c.getPlayer(), c.getPlayer(), target, dropPos);
                }
                else {
                    c.getPlayer().getMap().spawnItemDrop((MapleMapObject)c.getPlayer(), c.getPlayer(), target, dropPos, true, true);
                }
            }
            else {
                c.getPlayer().getInventory(type).removeSlot(src);
                c.sendPacket(MaplePacketCreator.dropInventoryItem((src < 0) ? MapleInventoryType.EQUIP : type, src));
                if (src < 0) {
                    c.getPlayer().equipChanged();
                }
                if (ii.isDropRestricted(source.getItemId()) || ii.isAccountShared(source.getItemId())) {
                    if (ItemFlag.KARMA_EQ.check((int)flag)) {
                        source.setFlag((byte)(flag - ItemFlag.KARMA_EQ.getValue()));
                        c.getPlayer().getMap().spawnItemDrop((MapleMapObject)c.getPlayer(), c.getPlayer(), source, dropPos, true, true);
                    }
                    else if (ItemFlag.KARMA_USE.check((int)flag)) {
                        source.setFlag((byte)(flag - ItemFlag.KARMA_USE.getValue()));
                        c.getPlayer().getMap().spawnItemDrop((MapleMapObject)c.getPlayer(), c.getPlayer(), source, dropPos, true, true);
                    }
                    else {
                        c.getPlayer().getMap().disappearingItemDrop((MapleMapObject)c.getPlayer(), c.getPlayer(), source, dropPos);
                    }
                }
                else if (GameConstants.isPet(source.getItemId()) || ItemFlag.UNTRADEABLE.check((int)flag)) {
                    c.getPlayer().getMap().disappearingItemDrop((MapleMapObject)c.getPlayer(), c.getPlayer(), source, dropPos);
                }
                else {
                    c.getPlayer().getMap().spawnItemDrop((MapleMapObject)c.getPlayer(), c.getPlayer(), source, dropPos, true, true);
                }
            }
        }
        catch (Exception ex) {
            FileoutputUtil.outError("logs/丟棄道具異常.txt", (Throwable)ex);
        }
        return true;
    }
    
    public static void removeAllByEquipOnlyId(final MapleClient c, final long inventoryitemid) {
        if (c.getPlayer() == null) {
            return;
        }
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final IItem copyEquipItems = c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItemByInventoryItemId(Long.valueOf(inventoryitemid));
        if (copyEquipItems != null) {
            removeFromSlot(c, MapleInventoryType.EQUIP, copyEquipItems.getPosition(), copyEquipItems.getQuantity(), true, false);
            final String msgtext = "玩家" + c.getPlayer().getName() + " ID: " + c.getPlayer().getId() + " (等級" + (int)c.getPlayer().getLevel() + ") 地图: " + c.getPlayer().getMapId() + " 在玩家背包中發現复制装备[" + ii.getName(copyEquipItems.getItemId()) + "]已經將其刪除。";
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语] " + msgtext));
            FileoutputUtil.log("Hack/复制装备_已刪除.txt", msgtext + " 道具唯一ID: " + copyEquipItems.getEquipOnlyId());
        }
        final IItem copyEquipedItems = c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItemByInventoryItemId(Long.valueOf(inventoryitemid));
        if (copyEquipedItems != null) {
            removeFromSlot(c, MapleInventoryType.EQUIPPED, copyEquipedItems.getPosition(), copyEquipedItems.getQuantity(), true, false);
            final String msgtext2 = "玩家" + c.getPlayer().getName() + " ID: " + c.getPlayer().getId() + " (等級" + (int)c.getPlayer().getLevel() + ") 地图: " + c.getPlayer().getMapId() + " 在玩家穿戴中發現复制装备[" + ii.getName(copyEquipedItems.getItemId()) + "]已經將其刪除。";
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语] " + msgtext2));
            FileoutputUtil.logToFile("Hack/复制装备_已刪除.txt", msgtext2 + " 道具唯一ID: " + copyEquipedItems.getEquipOnlyId());
        }
        for (final IItem copyStorageItem : c.getPlayer().getStorage().getItems()) {
            if (copyStorageItem != null && c.getPlayer().getStorage().removeItemByInventoryItemId(inventoryitemid)) {
                final String msgtext3 = "玩家" + c.getPlayer().getName() + " ID: " + c.getPlayer().getId() + " (等級" + (int)c.getPlayer().getLevel() + ") 地图: " + c.getPlayer().getMapId() + " 在玩家穿戴中發現复制装备[" + ii.getName(copyEquipedItems.getItemId()) + "]已經將其刪除。";
                Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语] " + msgtext3));
                FileoutputUtil.logToFile("Hack/复制装备_已刪除.txt", msgtext3 + " 道具唯一ID: " + copyStorageItem.getEquipOnlyId() + "\r\n");
            }
        }
    }
}
