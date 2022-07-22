package tools.packet;

import server.shops.AbstractPlayerStore;
import server.shops.IMaplePlayerShop;
import server.movement.LifeMovementFragment;
import client.inventory.MaplePet;
import client.inventory.Equip;
import constants.GameConstants;
import server.MapleItemInformationProvider;
import java.util.LinkedHashMap;
import java.util.Collection;
import client.inventory.MapleInventory;
import java.util.Collections;
import client.inventory.IItem;
import client.inventory.Item;
import java.util.ArrayList;
import client.inventory.MapleInventoryType;
import tools.Pair;
import client.inventory.MapleRing;
import client.MapleCoolDownValueHolder;
import java.util.Map;
import client.SkillEntry;
import client.ISkill;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.List;
import tools.KoreanDateUtil;
import client.MapleQuestStatus;
import client.MapleCharacter;
import tools.data.MaplePacketLittleEndianWriter;
import java.util.Date;
import java.util.TimeZone;

public class PacketHelper
{
    private static final long FT_UT_OFFSET = 116444592000000000L;
    public static final long MAX_TIME = 150842304000000000L;
    public static final byte[] unk1;
    public static final byte[] unk2;
    
    public static final long getKoreanTimestamp(final long realTimestamp) {
        if (realTimestamp == -1L) {
            return 150842304000000000L;
        }
        final long time = realTimestamp / 1000L / 60L;
        return time * 600000000L + 116444592000000000L;
    }
    
    public static final long getTime(final long realTimestamp) {
        if (realTimestamp == -1L) {
            return 150842304000000000L;
        }
        final long time = realTimestamp / 1000L;
        return time * 10000000L + 116444592000000000L;
    }
    
    public static long getFileTimestamp(long timeStampinMillis, final boolean roundToMinutes) {
        if (TimeZone.getDefault().inDaylightTime(new Date())) {
            timeStampinMillis -= 3600000L;
        }
        long time;
        if (roundToMinutes) {
            time = timeStampinMillis / 1000L / 60L * 600000000L;
        }
        else {
            time = timeStampinMillis * 10000L;
        }
        return time + 116444592000000000L;
    }
    
    public static void addImageInfo(final MaplePacketLittleEndianWriter mplew, final byte[] image) {
        mplew.writeInt(image.length);
        mplew.write(image);
    }
    
    public static void addQuestInfo(final MaplePacketLittleEndianWriter mplew, final MapleCharacter chr) {
        final List<MapleQuestStatus> started = chr.getStartedQuests();
        mplew.writeShort(started.size());
        for (final MapleQuestStatus q : started) {
            mplew.writeShort(q.getQuest().getId());
            mplew.writeMapleAsciiString((q.getCustomData() != null) ? q.getCustomData() : "");
        }
        final List<MapleQuestStatus> completed = chr.getCompletedQuests();
        mplew.writeShort(completed.size());
        for (final MapleQuestStatus q2 : completed) {
            mplew.writeShort(q2.getQuest().getId());
            final int time = KoreanDateUtil.getQuestTimestamp(q2.getCompletionTime());
            mplew.writeInt(time);
            mplew.writeInt(time);
        }
    }
    
    public static final void addSkillInfo(final MaplePacketLittleEndianWriter mplew, final MapleCharacter chr) {
        final Map<ISkill, SkillEntry> skills = chr.getSkills();
        mplew.writeShort(skills.size());
        for (final Entry<ISkill, SkillEntry> skill : skills.entrySet()) {
            mplew.writeInt(((ISkill)skill.getKey()).getId());
            mplew.writeInt((int)((SkillEntry)skill.getValue()).skillevel);
            if (((ISkill)skill.getKey()).isFourthJob()) {
                mplew.writeInt((int)((SkillEntry)skill.getValue()).masterlevel);
            }
        }
    }
    
    public static final void addCoolDownInfo(final MaplePacketLittleEndianWriter mplew, final MapleCharacter chr) {
        final List<MapleCoolDownValueHolder> cd = chr.getCooldowns();
        mplew.writeShort(cd.size());
        for (final MapleCoolDownValueHolder cooling : cd) {
            mplew.writeInt(cooling.skillId);
            mplew.writeShort((int)(cooling.length + cooling.startTime - System.currentTimeMillis()) / 1000);
        }
    }
    
    public static final void addRocksInfo(final MaplePacketLittleEndianWriter mplew, final MapleCharacter chr) {
        final int[] mapz = chr.getRegRocks();
        for (int i = 0; i < 5; ++i) {
            mplew.writeInt(mapz[i]);
        }
        final int[] map = chr.getRocks();
        for (int j = 0; j < 10; ++j) {
            mplew.writeInt(map[j]);
        }
    }
    
    public static final void addMonsterBookInfo(final MaplePacketLittleEndianWriter mplew, final MapleCharacter chr) {
        mplew.writeInt(chr.getMonsterBookCover());
        mplew.write(0);
        chr.getMonsterBook().addCardPacket(mplew);
    }
    
    public static final void addRingInfo(final MaplePacketLittleEndianWriter mplew, final MapleCharacter chr) {
        mplew.writeShort(0);
        final Pair<List<MapleRing>, List<MapleRing>> aRing = chr.getRings(true);
        final List<MapleRing> cRing = (List<MapleRing>)aRing.getLeft();
        mplew.writeShort(cRing.size());
        for (final MapleRing ring : cRing) {
            mplew.writeInt(ring.getPartnerChrId());
            mplew.writeAsciiString(ring.getPartnerName(), 13);
            mplew.writeLong((long)ring.getRingId());
            mplew.writeLong((long)ring.getPartnerRingId());
        }
        final List<MapleRing> fRing = (List<MapleRing>)aRing.getRight();
        mplew.writeShort(fRing.size());
        for (final MapleRing ring2 : fRing) {
            mplew.writeInt(ring2.getPartnerChrId());
            mplew.writeAsciiString(ring2.getPartnerName(), 13);
            mplew.writeLong((long)ring2.getRingId());
            mplew.writeLong((long)ring2.getPartnerRingId());
            mplew.writeInt(ring2.getItemId());
        }
        mplew.writeShort((int)(short)(short)((chr.getMarriageRing(false) != null) ? 1 : 0));
        final int marriageId = 30000;
        if (chr.getMarriageRing(false) != null) {
            mplew.writeInt(0);
            mplew.writeAsciiString("", 13);
            mplew.writeInt(chr.getId());
            mplew.writeInt(chr.getMarriageRing(false).getPartnerRingId());
        }
    }
    
    public static void addInventoryInfo(final MaplePacketLittleEndianWriter mplew, final MapleCharacter chr) {
        mplew.writeInt(chr.getMeso());
        mplew.writeInt(chr.getId());
        mplew.writeInt(chr.getBeans());
        mplew.writeInt(0);
        mplew.write(chr.getInventory(MapleInventoryType.EQUIP).getSlotLimit());
        mplew.write(chr.getInventory(MapleInventoryType.USE).getSlotLimit());
        mplew.write(chr.getInventory(MapleInventoryType.SETUP).getSlotLimit());
        mplew.write(chr.getInventory(MapleInventoryType.ETC).getSlotLimit());
        mplew.write(chr.getInventory(MapleInventoryType.CASH).getSlotLimit());
        mplew.writeLong(getTime(-2L));
        MapleInventory iv = chr.getInventory(MapleInventoryType.EQUIPPED);
        final Collection<IItem> equippedC = iv.list();
        final List<Item> equipped = new ArrayList<Item>(equippedC.size());
        for (final IItem item : equippedC) {
            equipped.add((Item)item);
        }
        Collections.sort(equipped);
        for (final Item item2 : equipped) {
            if (item2.getPosition() < 0 && item2.getPosition() > -100) {
                addItemInfo(mplew, (IItem)item2, false, false);
            }
        }
        mplew.write(0);
        for (final Item item2 : equipped) {
            if (item2.getPosition() <= -100 && item2.getPosition() > -1000) {
                addItemInfo(mplew, (IItem)item2, false, false);
            }
        }
        mplew.write(0);
        iv = chr.getInventory(MapleInventoryType.EQUIP);
        for (final IItem item : iv.list()) {
            addItemInfo(mplew, item, false, false);
        }
        mplew.write(0);
        iv = chr.getInventory(MapleInventoryType.USE);
        for (final IItem item : iv.list()) {
            addItemInfo(mplew, item, false, false);
        }
        mplew.write(0);
        iv = chr.getInventory(MapleInventoryType.SETUP);
        for (final IItem item : iv.list()) {
            addItemInfo(mplew, item, false, false);
        }
        mplew.write(0);
        iv = chr.getInventory(MapleInventoryType.ETC);
        for (final IItem item : iv.list()) {
            addItemInfo(mplew, item, false, false);
        }
        mplew.write(0);
        iv = chr.getInventory(MapleInventoryType.CASH);
        for (final IItem item : iv.list()) {
            addItemInfo(mplew, item, false, false);
        }
        mplew.write(0);
    }
    
    public static final void addCharStats(final MaplePacketLittleEndianWriter mplew, final MapleCharacter chr) {
        mplew.writeInt(chr.getId());
        mplew.writeAsciiString(chr.getName(), 13);
        mplew.write(chr.getGender());
        mplew.write(chr.getSkinColor());
        mplew.writeInt(chr.getFace());
        mplew.writeInt(chr.getHair());
        mplew.writeZeroBytes(24);
        mplew.write((int)chr.getLevel());
        mplew.writeShort((int)chr.getJob());
        chr.getStat().connectData(mplew);
        mplew.writeShort((int)chr.getRemainingAp());
        mplew.writeShort(chr.getRemainingSp());
        mplew.writeInt(chr.getExp());
        mplew.writeShort((int)chr.getFame());
        mplew.writeInt(0);
        mplew.writeLong(getTime(System.currentTimeMillis()));
        mplew.writeInt(chr.getMapId());
        mplew.write(chr.getInitialSpawnpoint());
    }
    
    public static final void addCharLook(final MaplePacketLittleEndianWriter mplew, final MapleCharacter chr, final boolean mega) {
        mplew.write(chr.getGender());
        mplew.write(chr.getSkinColor());
        mplew.writeInt(chr.getFace());
        mplew.write((int)(mega ? 0 : 1));
        mplew.writeInt(chr.getHair());
        final Map<Byte, Integer> myEquip = new LinkedHashMap<Byte, Integer>();
        final Map<Byte, Integer> maskedEquip = new LinkedHashMap<Byte, Integer>();
        final MapleInventory equip = chr.getInventory(MapleInventoryType.EQUIPPED);
        for (final IItem item : equip.list()) {
            if (item.getPosition() < -128) {
                continue;
            }
            byte pos = (byte)(item.getPosition() * -1);
            if (pos < 100 && myEquip.get((Object)Byte.valueOf(pos)) == null) {
                myEquip.put(Byte.valueOf(pos), Integer.valueOf(item.getItemId()));
            }
            else if ((pos > 100 || pos == -128) && pos != 111) {
                pos = (byte)((pos == -128) ? 28 : (pos - 100));
                if (myEquip.get((Object)Byte.valueOf(pos)) != null) {
                    maskedEquip.put(Byte.valueOf(pos), myEquip.get((Object)Byte.valueOf(pos)));
                }
                myEquip.put(Byte.valueOf(pos), Integer.valueOf(item.getItemId()));
            }
            else {
                if (myEquip.get((Object)Byte.valueOf(pos)) == null) {
                    continue;
                }
                maskedEquip.put(Byte.valueOf(pos), Integer.valueOf(item.getItemId()));
            }
        }
        for (final Entry<Byte, Integer> entry : myEquip.entrySet()) {
            mplew.write((byte)Byte.valueOf(entry.getKey()));
            mplew.writeInt((int)Integer.valueOf(entry.getValue()));
        }
        mplew.write(255);
        for (final Entry<Byte, Integer> entry : maskedEquip.entrySet()) {
            mplew.write((byte)Byte.valueOf(entry.getKey()));
            mplew.writeInt((int)Integer.valueOf(entry.getValue()));
        }
        mplew.write(255);
        final IItem cWeapon = equip.getItem((short)(-111));
        mplew.writeInt((cWeapon != null) ? cWeapon.getItemId() : 0);
        mplew.writeInt(0);
        mplew.writeLong(0L);
    }
    
    public static void addExpirationTime(final MaplePacketLittleEndianWriter mplew, final long time) {
        mplew.writeLong(getTime(time));
    }
    
    public static final void addItemInfo(final MaplePacketLittleEndianWriter mplew, final IItem item, final boolean zeroPosition) {
        addItemInfo(mplew, item, zeroPosition);
    }
    
    public static final void addItemInfo(final MaplePacketLittleEndianWriter mplew, final IItem item, final boolean zeroPosition, final boolean leaveOut) {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final boolean isPet = item.getPet() != null && item.getPet().getUniqueId() > -1;
        boolean isRing = false;
        final boolean hasUniqueId = item.getUniqueId() > 0 && !GameConstants.isMarrigeRing(item.getItemId()) && item.getItemId() / 10000 != 166;
        Equip equip = null;
        short pos = item.getPosition();
        if (item.getType() == 1) {
            equip = (Equip)item;
            isRing = (equip.getRing() != null && equip.getRing().getRingId() > -1);
        }
        if (!zeroPosition) {
            if (equip != null) {
                if (pos < 0) {
                    pos *= -1;
                }
                mplew.write((pos > 100) ? (pos - 100) : pos);
            }
            else {
                mplew.write((int)pos);
            }
        }
        mplew.write((byte)((item.getPet() != null) ? 3 : item.getType()));
        mplew.writeInt(item.getItemId());
        if (ii.isCash(item.getItemId()) && !isPet && item.getUniqueId() < 0) {
            final int uniqueid = MapleItemInformationProvider.getUniqueId(item.getItemId(), null);
            item.setUniqueId(uniqueid);
        }
        mplew.write((int)(hasUniqueId ? 1 : 0));
        if (hasUniqueId) {
            if (isPet) {
                mplew.writeLong((long)item.getPet().getUniqueId());
            }
            else if (isRing) {
                mplew.writeLong((long)item.getRing().getRingId());
            }
            else {
                mplew.writeLong((long)item.getUniqueId());
            }
        }
        if (item.getPet() != null) {
            addPetItemInfo(mplew, item, item.getPet());
        }
        else {
            addExpirationTime(mplew, item.getExpiration());
            if (item.getType() == 1 && equip != null) {
                mplew.write(equip.getUpgradeSlots());
                mplew.write(equip.getLevel());
                mplew.writeShort((int)equip.getStr());
                mplew.writeShort((int)equip.getDex());
                mplew.writeShort((int)equip.getInt());
                mplew.writeShort((int)equip.getLuk());
                mplew.writeShort((int)equip.getHp());
                mplew.writeShort((int)equip.getMp());
                mplew.writeShort((int)equip.getWatk());
                mplew.writeShort((int)equip.getMatk());
                mplew.writeShort((int)equip.getWdef());
                mplew.writeShort((int)equip.getMdef());
                mplew.writeShort((int)equip.getAcc());
                mplew.writeShort((int)equip.getAvoid());
                mplew.writeShort((int)equip.getHands());
                mplew.writeShort((int)equip.getSpeed());
                mplew.writeShort((int)equip.getJump());
                mplew.writeMapleAsciiString(equip.getOwner());
                mplew.writeShort((int)equip.getFlag());
                mplew.write(0);
                mplew.write(Math.max(equip.getBaseLevel(), equip.getEquipLevel()));
                mplew.writeInt(equip.getExpPercentage() * 100000);
                mplew.writeInt((int)equip.getViciousHammer());
                if (!hasUniqueId) {
                    mplew.writeLong((long)item.getUniqueId());
                }
                mplew.writeLong(getTime(-2L));
                mplew.writeInt(-1);
            }
            else {
                mplew.writeShort((int)item.getQuantity());
                mplew.writeMapleAsciiString(item.getOwner());
                mplew.writeShort((int)item.getFlag());
                if (GameConstants.isExpChair(item.getItemId())) {
                    mplew.writeInt(0);
                    mplew.writeInt(0);
                }
                if (GameConstants.isRechargable(item.getItemId())) {
                    mplew.writeInt(2);
                    mplew.writeShort(84);
                    mplew.write(0);
                    mplew.write(52);
                }
            }
        }
    }
    
    public static final void addItemInfo2(final MaplePacketLittleEndianWriter mplew, final IItem item, final boolean zeroPosition, final boolean leaveOut, final boolean trade) {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final boolean isCash = ii.isCash(item.getItemId());
        final boolean isPet = item.getPet() != null && item.getPet().getUniqueId() > -1;
        boolean isRing = false;
        Equip equip = null;
        short pos = item.getPosition();
        if (item.getType() == 1) {
            equip = (Equip)item;
            isRing = (equip.getRing() != null && equip.getRing().getRingId() > -1);
        }
        if (!zeroPosition) {
            if (equip != null) {
                if (pos < 0) {
                    pos *= -1;
                }
                mplew.write((pos > 100) ? (pos - 100) : pos);
            }
            else {
                mplew.write((int)pos);
            }
        }
        mplew.write(item.getType());
        mplew.writeInt(item.getItemId());
        mplew.write((int)(isCash ? 1 : 0));
        if (isCash) {
            mplew.writeLong(isPet ? ((long)item.getPet().getUniqueId()) : (isRing ? ((long)equip.getRing().getRingId()) : ((long)item.getUniqueId())));
        }
        if (isPet) {
            addPetItemInfo(mplew, item, item.getPet());
        }
        else {
            addExpirationTime(mplew, item.getExpiration());
            if (equip == null) {
                mplew.writeShort((int)item.getQuantity());
                mplew.writeMapleAsciiString(item.getOwner());
                mplew.writeShort((int)item.getFlag());
                if (GameConstants.isRechargable(item.getItemId())) {
                    mplew.writeInt(2);
                    mplew.writeShort(84);
                    mplew.write(0);
                    mplew.write(52);
                }
                return;
            }
            mplew.write(equip.getUpgradeSlots());
            mplew.write(equip.getLevel());
            mplew.writeShort((int)equip.getStr());
            mplew.writeShort((int)equip.getDex());
            mplew.writeShort((int)equip.getInt());
            mplew.writeShort((int)equip.getLuk());
            mplew.writeShort((int)equip.getHp());
            mplew.writeShort((int)equip.getMp());
            mplew.writeShort((int)equip.getWatk());
            mplew.writeShort((int)equip.getMatk());
            mplew.writeShort((int)equip.getWdef());
            mplew.writeShort((int)equip.getMdef());
            mplew.writeShort((int)equip.getAcc());
            mplew.writeShort((int)equip.getAvoid());
            mplew.writeShort((int)equip.getHands());
            mplew.writeShort((int)equip.getSpeed());
            mplew.writeShort((int)equip.getJump());
            mplew.writeMapleAsciiString(equip.getOwner());
            mplew.writeShort((int)equip.getFlag());
            mplew.write(equip.getLevel());
            mplew.write(equip.getExpPercentage());
            mplew.writeInt(0);
            if (!isCash) {
                mplew.writeLong((long)item.getUniqueId());
            }
            mplew.writeLong(getTime(-2L));
            mplew.writeInt(-1);
        }
    }
    
    public static final void serializeMovementList(final MaplePacketLittleEndianWriter lew, final List<LifeMovementFragment> moves) {
        lew.write(moves.size());
        for (final LifeMovementFragment move : moves) {
            move.serialize(lew);
        }
    }
    
    public static final void addAnnounceBox(final MaplePacketLittleEndianWriter mplew, final MapleCharacter chr) {
        if (chr.getPlayerShop() != null && chr.getPlayerShop().isOwner(chr) && chr.getPlayerShop().getShopType() != 1 && chr.getPlayerShop().isAvailable()) {
            addInteraction(mplew, chr.getPlayerShop());
        }
        else {
            mplew.write(0);
        }
    }
    
    public static final void addInteraction(final MaplePacketLittleEndianWriter mplew, final IMaplePlayerShop shop) {
        mplew.write(shop.getGameType());
        mplew.writeInt(((AbstractPlayerStore)shop).getObjectId());
        mplew.writeMapleAsciiString(shop.getDescription());
        if (shop.getShopType() != 1) {
            mplew.write((int)((shop.getPassword().length() > 0) ? 1 : 0));
        }
        mplew.write(shop.getItemId() % 10);
        mplew.write(shop.getSize());
        mplew.write(shop.getMaxSize());
        if (shop.getShopType() != 1) {
            mplew.write((int)(shop.isOpen() ? 0 : 1));
        }
    }
    
    public static final void addCharacterInfo(final MaplePacketLittleEndianWriter mplew, final MapleCharacter chr, final boolean isCs) {
        mplew.writeLong(-1L);
        mplew.write(0);
        addCharStats(mplew, chr);
        mplew.write(chr.getBuddylist().getCapacity());
        if (!isCs) {
            if (chr.getBlessOfFairyOrigin() != null) {
                mplew.write(1);
                mplew.writeMapleAsciiString(chr.getBlessOfFairyOrigin());
            }
            else {
                mplew.write(0);
            }
        }
        else {
            mplew.write(0);
        }
        addInventoryInfo(mplew, chr);
        if (!isCs) {
            addSkillInfo(mplew, chr);
        }
        else {
            mplew.writeShort(0);
        }
        if (!isCs) {
            addCoolDownInfo(mplew, chr);
        }
        else {
            mplew.writeShort(0);
        }
        if (!isCs) {
            addQuestInfo(mplew, chr);
        }
        else {
            mplew.writeShort(0);
            mplew.writeShort(0);
        }
        addRingInfo(mplew, chr);
        addRocksInfo(mplew, chr);
        if (!isCs) {
            addMonsterBookInfo(mplew, chr);
        }
        else {
            mplew.writeInt(1);
            mplew.write(0);
            mplew.writeShort(0);
        }
        if (!isCs) {
            chr.QuestInfoPacket(mplew);
        }
        else {
            mplew.writeShort(0);
        }
        mplew.writeShort(0);
        mplew.writeShort(0);
        mplew.writeShort(0);
    }
    
    public static final void addPetItemInfo(final MaplePacketLittleEndianWriter mplew, final IItem item, final MaplePet pet) {
        addExpirationTime(mplew, (item != null) ? item.getExpiration() : -1L);
        String petname = pet.getName();
        if (petname == null) {
            petname = "";
        }
        mplew.writeAsciiString(petname, 13);
        mplew.write(pet.getLevel());
        mplew.writeShort((int)pet.getCloseness());
        mplew.write(pet.getFullness());
        if (item == null) {
            mplew.writeLong(getKoreanTimestamp((long)((double)System.currentTimeMillis() * 1.5)));
        }
        else {
            addExpirationTime(mplew, (item.getExpiration() <= System.currentTimeMillis()) ? -1L : item.getExpiration());
        }
        mplew.writeShort(0);
        mplew.writeShort((int)pet.getFlags());
        mplew.writeShort(0);
        for (int i = 0; i < 4; ++i) {
            mplew.write(0);
        }
    }
    
    static {
        unk1 = new byte[] { 0, 64, -32, -3 };
        unk2 = new byte[] { 59, 55, 79, 1 };
    }
}
