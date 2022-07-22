package handling.channel.handler;

import java.util.concurrent.locks.Lock;
import server.movement.LifeMovementFragment;
import java.util.List;
import server.life.MapleMonster;
import client.inventory.MaplePet.PetFlag;
import handling.world.MaplePartyCharacter;
import java.util.LinkedList;
import server.maps.MapleMapItem;
import java.util.Iterator;
import client.inventory.PetCommand;
import client.inventory.MaplePet;
import constants.GameConstants;
import server.Randomizer;
import client.inventory.PetDataFactory;
import tools.packet.PetPacket;
import client.inventory.IItem;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.maps.FieldLimitType;
import tools.MaplePacketCreator;
import client.inventory.MapleInventoryType;
import client.MapleDisease;
import client.MapleCharacter;
import client.MapleClient;
import tools.data.LittleEndianAccessor;

public class PetHandler
{
    public static final void SpawnPet(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        chr.updateTick(slea.readInt());
        chr.spawnPet((byte)slea.readShort(), slea.readByte() > 0);
    }
    
    public static final void Pet_AutoPotion(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        slea.skip(13);
        final byte slot = slea.readByte();
        if (chr == null || !chr.isAlive() || chr.getMapId() == 749040100 || chr.getMap() == null || chr.hasDisease(MapleDisease.POTION)) {
            return;
        }
        final IItem toUse = chr.getInventory(MapleInventoryType.USE).getItem((short)slot);
        if (toUse == null || toUse.getQuantity() < 1) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        final long time = System.currentTimeMillis();
        if (chr.getNextConsume() > time) {
            chr.dropMessage(5, "You may not use this item yet.");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (!FieldLimitType.PotionUse.check(chr.getMap().getFieldLimit()) || chr.getMapId() == 610030600) {
            if (MapleItemInformationProvider.getInstance().getItemEffect(toUse.getItemId()).applyTo(chr)) {
                MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, (short)slot, (short)1, false);
                if (chr.getMap().getConsumeItemCoolTime() > 0) {
                    chr.setNextConsume(time + (long)(chr.getMap().getConsumeItemCoolTime() * 1000));
                }
            }
        }
        else {
            c.sendPacket(MaplePacketCreator.enableActions());
        }
    }
    
    public static final void PetChat(final int petid, final short command, final String text, final MapleCharacter chr) {
        if (chr == null || chr.getMap() == null || chr.getPetIndex(petid) < 0) {
            return;
        }
        if (!chr.getCanTalk()) {
            chr.getClient().sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        chr.getMap().broadcastMessage(chr, PetPacket.petChat(chr.getId(), (int)command, text, chr.getPetIndex(petid)), true);
    }
    
    public static final void PetCommand(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        final byte petIndex = chr.getPetIndex(slea.readInt());
        if (petIndex == -1) {
            return;
        }
        final MaplePet pet = chr.getPet((int)petIndex);
        if (pet == null) {
            return;
        }
        slea.skip(5);
        final byte command = slea.readByte();
        final PetCommand petCommand = PetDataFactory.getPetCommand(pet.getPetItemId(), (int)command);
        boolean success = false;
        if (Randomizer.nextInt(99) <= petCommand.getProbability()) {
            success = true;
            if (pet.getCloseness() < 30000) {
                int newCloseness = pet.getCloseness() + petCommand.getIncrease();
                if (newCloseness > 30000) {
                    newCloseness = 30000;
                }
                pet.setCloseness(newCloseness);
                if (newCloseness >= GameConstants.getClosenessNeededForLevel(pet.getLevel() + 1)) {
                    pet.setLevel(pet.getLevel() + 1);
                    c.sendPacket(PetPacket.showOwnPetLevelUp(petIndex));
                    chr.getMap().broadcastMessage(PetPacket.showPetLevelUp(chr, petIndex));
                }
                c.sendPacket(PetPacket.updatePet(pet, chr.getInventory(MapleInventoryType.CASH).getItem((short)(byte)pet.getInventoryPosition())));
            }
        }
        chr.getMap().broadcastMessage(chr, PetPacket.commandResponse(chr.getId(), command, petIndex, success, false), true);
    }
    
    public static final void PetFood(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        int previousFullness = 100;
        MaplePet pet = null;
        if (chr == null) {
            return;
        }
        for (final MaplePet pets : chr.getSummonedPets()) {
            if (pets.getSummoned() && pets.getFullness() < previousFullness) {
                previousFullness = pets.getFullness();
                pet = pets;
            }
        }
        if (pet == null) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        slea.skip(4);
        final short slot = slea.readShort();
        final int itemId = slea.readInt();
        final IItem petFood = c.getPlayer().getInventory(MapleInventoryType.USE).getItem(slot);
        if (petFood == null || petFood.getItemId() != itemId || petFood.getQuantity() <= 0 || itemId / 10000 != 212) {
            c.getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
            return;
        }
        boolean gainCloseness = false;
        if (Randomizer.nextInt(99) <= 50) {
            gainCloseness = true;
        }
        if (pet.getFullness() < 100) {
            int newFullness = pet.getFullness() + 30;
            if (newFullness > 100) {
                newFullness = 100;
            }
            pet.setFullness(newFullness);
            final byte index = chr.getPetIndex(pet);
            if (gainCloseness && pet.getCloseness() < 30000) {
                int newCloseness = pet.getCloseness() + 1;
                if (newCloseness > 30000) {
                    newCloseness = 30000;
                }
                pet.setCloseness(newCloseness);
                if (newCloseness >= GameConstants.getClosenessNeededForLevel(pet.getLevel() + 1)) {
                    pet.setLevel(pet.getLevel() + 1);
                    c.sendPacket(PetPacket.showOwnPetLevelUp(index));
                    chr.getMap().broadcastMessage(PetPacket.showPetLevelUp(chr, index));
                }
            }
            c.sendPacket(PetPacket.updatePet(pet, chr.getInventory(MapleInventoryType.CASH).getItem((short)(byte)pet.getInventoryPosition())));
            chr.getMap().broadcastMessage(c.getPlayer(), PetPacket.commandResponse(chr.getId(), (byte)1, index, true, true), true);
        }
        else {
            if (gainCloseness) {
                int newCloseness2 = pet.getCloseness() - 1;
                if (newCloseness2 < 0) {
                    newCloseness2 = 0;
                }
                pet.setCloseness(newCloseness2);
                if (newCloseness2 < GameConstants.getClosenessNeededForLevel((int)pet.getLevel())) {
                    pet.setLevel(pet.getLevel() - 1);
                }
            }
            c.sendPacket(PetPacket.updatePet(pet, chr.getInventory(MapleInventoryType.CASH).getItem((short)(byte)pet.getInventoryPosition())));
            chr.getMap().broadcastMessage(chr, PetPacket.commandResponse(chr.getId(), (byte)1, chr.getPetIndex(pet), false, true), true);
        }
        MapleInventoryManipulator.removeById(c, MapleInventoryType.USE, itemId, 1, true, false);
        c.sendPacket(MaplePacketCreator.enableActions());
    }
    
    public static final void MovePet(final LittleEndianAccessor slea, final MapleCharacter chr) {
        final int petId = slea.readInt();
        slea.skip(8);
        final List<LifeMovementFragment> res = MovementParse.parseMovement(slea, 3);
        if (res != null && chr != null && !res.isEmpty()) {
            final byte slot = chr.getPetIndex(petId);
            if (slot == -1) {
                return;
            }
            final MaplePet pet = chr.getPet((int)slot);
            if (pet == null) {
                return;
            }
            pet.updatePosition(res);
            chr.getMap().broadcastMessage(chr, PetPacket.movePet(chr.getId(), petId, slot, res), false);
            if (chr.hasBlockedInventory(false)) {
                return;
            }
            if (chr.getStat().hasVac) {
                final List<MapleMapItem> objects = chr.getMap().getAllItems();
                for (final MapleMapItem mapitem : objects) {
                    final Lock lock = mapitem.getLock();
                    lock.lock();
                    try {
                        if (mapitem.isPickedUp()) {
                            continue;
                        }
                        if (mapitem.getOwner() != chr.getId() && mapitem.isPlayerDrop()) {
                            continue;
                        }
                        if (mapitem.getOwner() != chr.getId() && ((!mapitem.isPlayerDrop() && mapitem.getDropType() == 0) || (mapitem.isPlayerDrop() && chr.getMap().getEverlast()))) {
                            continue;
                        }
                        if (!mapitem.isPlayerDrop() && mapitem.getDropType() == 1 && mapitem.getOwner() != chr.getId() && (chr.getParty() == null || chr.getParty().getMemberById(mapitem.getOwner()) == null)) {
                            continue;
                        }
                        if (mapitem.getMeso() > 0 && chr.getStat().hasVac) {
                            if (chr.getParty() != null && mapitem.getOwner() != chr.getId()) {
                                final List<MapleCharacter> toGive = new LinkedList<MapleCharacter>();
                                for (final MaplePartyCharacter mem : chr.getParty().getMembers()) {
                                    final MapleCharacter m = chr.getMap().getCharacterById(mem.getId());
                                    if (m != null) {
                                        toGive.add(m);
                                    }
                                }
                                for (final MapleCharacter i : toGive) {
                                    i.gainMeso(mapitem.getMeso() / toGive.size() + (i.getStat().hasPartyBonus ? ((int)((double)mapitem.getMeso() / 20.0)) : 0), true, true);
                                }
                            }
                            else {
                                chr.gainMeso(mapitem.getMeso(), true, true);
                            }
                            InventoryHandler.removeItemPet(chr, mapitem, (int)slot);
                        }
                        else {
                            if (!chr.getStat().hasVac || !PetFlag.ITEM_PICKUP.check((int)pet.getFlags())) {
                                continue;
                            }
                            boolean exc = false;
                            final List excluded = pet.getExcluded();
                            if (excluded.size() > 0) {
                                for (final Object excluded2 : excluded) {
                                    if ((int)(Integer)excluded2 == mapitem.getItemId()) {
                                        exc = true;
                                    }
                                }
                            }
                            if (exc) {
                                continue;
                            }
                            if (InventoryHandler.useItem(chr.getClient(), mapitem.getItemId())) {
                                InventoryHandler.removeItemPet(chr, mapitem, (int)slot);
                            }
                            else {
                                if (!MapleInventoryManipulator.checkSpace(chr.getClient(), mapitem.getItem().getItemId(), (int)mapitem.getItem().getQuantity(), mapitem.getItem().getOwner())) {
                                    continue;
                                }
                                if (mapitem.getItem().getQuantity() < 50 || GameConstants.isUpgradeScroll(mapitem.getItem().getItemId())) {}
                                if (!MapleInventoryManipulator.addFromDrop(chr.getClient(), mapitem.getItem(), true, mapitem.getDropper() instanceof MapleMonster, false)) {
                                    continue;
                                }
                                InventoryHandler.removeItemPet(chr, mapitem, (int)slot);
                            }
                        }
                    }
                    finally {
                        lock.unlock();
                    }
                }
            }
        }
    }
    
    public static void PetIgnoreTag(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        final int petSlot = (int)slea.readLong();
        final MaplePet pet = chr.getPet((int)chr.getPetIndex(petSlot));
        if (pet == null || !PetFlag.UNPICKABLE.check((int)pet.getFlags())) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        pet.clearExcluded();
        final byte amount = slea.readByte();
        for (int i = 0; i < amount; ++i) {
            pet.addExcluded(i, slea.readInt());
        }
    }
}
