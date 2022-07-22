package tools.packet;

import java.util.Iterator;
import client.MapleStat;
import tools.MaplePacketCreator;
import server.movement.LifeMovementFragment;
import java.util.List;
import client.MapleCharacter;
import handling.SendPacketOpcode;
import tools.data.MaplePacketLittleEndianWriter;
import client.inventory.IItem;
import client.inventory.MaplePet;

public class PetPacket
{
    private static final byte[] ITEM_MAGIC;
    
    public static final byte[] updatePet(final MaplePet pet, final IItem item) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MODIFY_INVENTORY_ITEM.getValue());
        mplew.write(0);
        mplew.write(2);
        mplew.write(3);
        mplew.write(5);
        mplew.writeShort((int)pet.getInventoryPosition());
        mplew.write(0);
        mplew.write(5);
        mplew.writeShort((int)pet.getInventoryPosition());
        mplew.write(3);
        mplew.writeInt(pet.getPetItemId());
        mplew.write(1);
        mplew.writeLong((long)pet.getUniqueId());
        PacketHelper.addPetItemInfo(mplew, item, pet);
        return mplew.getPacket();
    }
    
    public static final byte[] removePet(final MapleCharacter chr, final int slot) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SPAWN_PET.getValue());
        mplew.writeInt(chr.getId());
        mplew.writeShort(slot);
        return mplew.getPacket();
    }
    
    public static final byte[] showPet(final MapleCharacter chr, final MaplePet pet, final boolean remove, final boolean hunger) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SPAWN_PET.getValue());
        mplew.writeInt(chr.getId());
        mplew.write(chr.getPetIndex(pet));
        if (remove) {
            mplew.write(0);
            mplew.write((int)(hunger ? 1 : 0));
        }
        else {
            mplew.write(1);
            mplew.write(0);
            mplew.writeInt(pet.getPetItemId());
            String petname = pet.getName();
            if (petname == null) {
                petname = "";
            }
            mplew.writeMapleAsciiString(petname);
            mplew.writeLong((long)pet.getUniqueId());
            mplew.writeShort(pet.getPos().x);
            mplew.writeShort(pet.getPos().y - 20);
            mplew.write(pet.getStance());
            mplew.writeInt(pet.getFh());
        }
        return mplew.getPacket();
    }
    
    public static final byte[] removePet(final int cid, final int index) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SPAWN_PET.getValue());
        mplew.writeInt(cid);
        mplew.write(index);
        mplew.writeShort(0);
        return mplew.getPacket();
    }
    
    public static final byte[] movePet(final int cid, final int pid, final byte slot, final List<LifeMovementFragment> moves) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MOVE_PET.getValue());
        mplew.writeInt(cid);
        mplew.write(slot);
        mplew.writeInt(pid);
        PacketHelper.serializeMovementList(mplew, moves);
        return mplew.getPacket();
    }
    
    public static final byte[] petChat(final int cid, final int un, final String text, final byte slot) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PET_CHAT.getValue());
        mplew.writeInt(cid);
        mplew.write(slot);
        mplew.writeShort(un);
        mplew.writeMapleAsciiString(text);
        mplew.write(0);
        return mplew.getPacket();
    }
    
    public static final byte[] commandResponse(final int cid, final byte command, final byte slot, final boolean success, final boolean food) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PET_COMMAND.getValue());
        mplew.writeInt(cid);
        mplew.write(slot);
        mplew.write((int)((command == 1) ? 1 : 0));
        mplew.write(command);
        if (command == 1) {
            mplew.write(0);
        }
        else {
            mplew.writeShort((int)(success ? 1 : 0));
        }
        return mplew.getPacket();
    }
    
    public static final byte[] showOwnPetLevelUp(final byte index) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SHOW_ITEM_GAIN_INCHAT.getValue());
        mplew.write(4);
        mplew.write(0);
        mplew.write(index);
        return mplew.getPacket();
    }
    
    public static final byte[] showPetLevelUp(final MapleCharacter chr, final byte index) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SHOW_FOREIGN_EFFECT.getValue());
        mplew.writeInt(chr.getId());
        mplew.write(4);
        mplew.write(0);
        mplew.write(index);
        return mplew.getPacket();
    }
    
    public static final byte[] emptyStatUpdate() {
        return MaplePacketCreator.enableActions();
    }
    
    public static final byte[] petStatUpdate_Empty() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.UPDATE_STATS.getValue());
        mplew.write(0);
        mplew.writeInt(MapleStat.PET.getValue());
        mplew.writeZeroBytes(25);
        return mplew.getPacket();
    }
    
    public static final byte[] petStatUpdate(final MapleCharacter chr) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.UPDATE_STATS.getValue());
        mplew.write(0);
        mplew.writeInt(MapleStat.PET.getValue());
        byte count = 0;
        for (final MaplePet pet : chr.getSummonedPets()) {
            if (pet.getSummoned()) {
                mplew.writeLong((long)pet.getUniqueId());
                ++count;
            }
        }
        while (count < 3) {
            mplew.writeZeroBytes(8);
            ++count;
        }
        mplew.write(0);
        return mplew.getPacket();
    }
    
    public static byte[] loadExceptionList(final MapleCharacter chr, final MaplePet pet) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PET_EXCEPTION_LIST.getValue());
        mplew.writeInt(chr.getId());
        mplew.write(chr.getPetIndex(pet));
        mplew.writeLong((long)pet.getUniqueId());
        final List excluded = pet.getExcluded();
        mplew.write(excluded.size());
        for (final Object excluded2 : excluded) {
            mplew.writeInt((int)(Integer)excluded2);
        }
        return mplew.getPacket();
    }
    
    static {
        ITEM_MAGIC = new byte[] { -128, 5 };
    }
}
