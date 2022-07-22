package handling.channel.handler;

import client.SkillFactory;
import java.util.Iterator;
import java.util.ArrayList;
import tools.Pair;
import java.util.List;
import server.Randomizer;
import java.util.Map;
import server.ItemMakerFactory.ItemMakerCreateEntry;
import server.ItemMakerFactory.GemCreateEntry;
import client.inventory.IItem;
import client.inventory.Equip;
import server.MapleItemInformationProvider;
import client.inventory.MapleInventoryType;
import tools.MaplePacketCreator;
import server.MapleInventoryManipulator;
import server.ItemMakerFactory;
import constants.GameConstants;
import client.MapleClient;
import tools.data.LittleEndianAccessor;

public class ItemMakerHandler
{
    public static final void ItemMaker(final LittleEndianAccessor slea, final MapleClient c) {
        final int makerType = slea.readInt();
        switch (makerType) {
            case 1: {
                final int toCreate = slea.readInt();
                if (GameConstants.isGem(toCreate)) {
                    final GemCreateEntry gem = ItemMakerFactory.getInstance().getGemInfo(toCreate);
                    if (gem == null) {
                        return;
                    }
                    if (!hasSkill(c, gem.getReqSkillLevel())) {
                        return;
                    }
                    if (c.getPlayer().getMeso() < gem.getCost()) {
                        return;
                    }
                    final int randGemGiven = getRandomGem(gem.getRandomReward());
                    if (c.getPlayer().getInventory(GameConstants.getInventoryType(randGemGiven)).isFull()) {
                        return;
                    }
                    final int taken = checkRequiredNRemove(c, gem.getReqRecipes());
                    if (taken == 0) {
                        return;
                    }
                    c.getPlayer().gainMeso(-gem.getCost(), false);
                    MapleInventoryManipulator.addById(c, randGemGiven, (short)(byte)((taken == randGemGiven) ? 9 : 1));
                    c.sendPacket(MaplePacketCreator.ItemMaker_Success());
                    c.getPlayer().getMap().broadcastMessage(c.getPlayer(), MaplePacketCreator.ItemMaker_Success_3rdParty(c.getPlayer().getId()), false);
                    break;
                }
                else if (GameConstants.isOtherGem(toCreate)) {
                    final GemCreateEntry gem = ItemMakerFactory.getInstance().getGemInfo(toCreate);
                    if (gem == null) {
                        return;
                    }
                    if (!hasSkill(c, gem.getReqSkillLevel())) {
                        return;
                    }
                    if (c.getPlayer().getMeso() < gem.getCost()) {
                        return;
                    }
                    if (c.getPlayer().getInventory(GameConstants.getInventoryType(toCreate)).isFull()) {
                        return;
                    }
                    if (checkRequiredNRemove(c, gem.getReqRecipes()) == 0) {
                        return;
                    }
                    c.getPlayer().gainMeso(-gem.getCost(), false);
                    if (GameConstants.getInventoryType(toCreate) == MapleInventoryType.EQUIP) {
                        MapleInventoryManipulator.addbyItem(c, MapleItemInformationProvider.getInstance().getEquipById(toCreate));
                    }
                    else {
                        MapleInventoryManipulator.addById(c, toCreate, (short)1);
                    }
                    c.sendPacket(MaplePacketCreator.ItemMaker_Success());
                    c.getPlayer().getMap().broadcastMessage(c.getPlayer(), MaplePacketCreator.ItemMaker_Success_3rdParty(c.getPlayer().getId()), false);
                    break;
                }
                else {
                    final boolean stimulator = slea.readByte() > 0;
                    final int numEnchanter = slea.readInt();
                    final ItemMakerCreateEntry create = ItemMakerFactory.getInstance().getCreateInfo(toCreate);
                    if (create == null) {
                        return;
                    }
                    if (numEnchanter > create.getTUC()) {
                        return;
                    }
                    if (!hasSkill(c, (int)create.getReqSkillLevel())) {
                        return;
                    }
                    if (c.getPlayer().getMeso() < create.getCost()) {
                        return;
                    }
                    if (c.getPlayer().getInventory(GameConstants.getInventoryType(toCreate)).isFull()) {
                        return;
                    }
                    if (checkRequiredNRemove(c, create.getReqItems()) == 0) {
                        return;
                    }
                    c.getPlayer().gainMeso(-create.getCost(), false);
                    final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                    final Equip toGive = (Equip)ii.getEquipById(toCreate);
                    if (stimulator || numEnchanter > 0) {
                        if (c.getPlayer().haveItem(create.getStimulator(), 1, false, true)) {
                            ii.randomizeStats(toGive);
                            MapleInventoryManipulator.removeById(c, MapleInventoryType.ETC, create.getStimulator(), 1, false, false);
                        }
                        for (int i = 0; i < numEnchanter; ++i) {
                            final int enchant = slea.readInt();
                            if (c.getPlayer().haveItem(enchant, 1, false, true)) {
                                final Map<String, Byte> stats = ii.getItemMakeStats(enchant);
                                if (stats != null) {
                                    addEnchantStats(stats, toGive);
                                    MapleInventoryManipulator.removeById(c, MapleInventoryType.ETC, enchant, 1, false, false);
                                }
                            }
                        }
                    }
                    MapleInventoryManipulator.addbyItem(c, (IItem)toGive);
                    c.sendPacket(MaplePacketCreator.ItemMaker_Success());
                    c.getPlayer().getMap().broadcastMessage(c.getPlayer(), MaplePacketCreator.ItemMaker_Success_3rdParty(c.getPlayer().getId()), false);
                    break;
                }
            }
            case 3: {
                final int etc = slea.readInt();
                if (c.getPlayer().haveItem(etc, 100, false, true)) {
                    MapleInventoryManipulator.addById(c, getCreateCrystal(etc), (short)1);
                    MapleInventoryManipulator.removeById(c, MapleInventoryType.ETC, etc, 100, false, false);
                    c.sendPacket(MaplePacketCreator.ItemMaker_Success());
                    c.getPlayer().getMap().broadcastMessage(c.getPlayer(), MaplePacketCreator.ItemMaker_Success_3rdParty(c.getPlayer().getId()), false);
                    break;
                }
                break;
            }
            case 4: {
                final int itemId = slea.readInt();
                c.getPlayer().updateTick(slea.readInt());
                final byte slot = (byte)slea.readInt();
                final IItem toUse = c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short)slot);
                if (toUse == null || toUse.getItemId() != itemId || toUse.getQuantity() < 1) {
                    return;
                }
                final MapleItemInformationProvider ii2 = MapleItemInformationProvider.getInstance();
                if (!ii2.isDropRestricted(itemId) && !ii2.isAccountShared(itemId)) {
                    final int[] toGive2 = getCrystal(itemId, ii2.getReqLevel(itemId));
                    MapleInventoryManipulator.addById(c, toGive2[0], (short)(byte)toGive2[1]);
                    MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.EQUIP, (short)slot, (short)1, false);
                }
                c.sendPacket(MaplePacketCreator.ItemMaker_Success());
                c.getPlayer().getMap().broadcastMessage(c.getPlayer(), MaplePacketCreator.ItemMaker_Success_3rdParty(c.getPlayer().getId()), false);
                break;
            }
        }
    }
    
    private static int getCreateCrystal(final int etc) {
        final short level = MapleItemInformationProvider.getInstance().getItemMakeLevel(etc);
        int itemid;
        if (level >= 31 && level <= 50) {
            itemid = 4260000;
        }
        else if (level >= 51 && level <= 60) {
            itemid = 4260001;
        }
        else if (level >= 61 && level <= 70) {
            itemid = 4260002;
        }
        else if (level >= 71 && level <= 80) {
            itemid = 4260003;
        }
        else if (level >= 81 && level <= 90) {
            itemid = 4260004;
        }
        else if (level >= 91 && level <= 100) {
            itemid = 4260005;
        }
        else if (level >= 101 && level <= 110) {
            itemid = 4260006;
        }
        else if (level >= 111 && level <= 120) {
            itemid = 4260007;
        }
        else {
            if (level < 121) {
                throw new RuntimeException("Invalid Item Maker id");
            }
            itemid = 4260008;
        }
        return itemid;
    }
    
    private static int[] getCrystal(final int itemid, final int level) {
        final int[] all = { -1, 0 };
        if (level >= 31 && level <= 50) {
            all[0] = 4260000;
        }
        else if (level >= 51 && level <= 60) {
            all[0] = 4260001;
        }
        else if (level >= 61 && level <= 70) {
            all[0] = 4260002;
        }
        else if (level >= 71 && level <= 80) {
            all[0] = 4260003;
        }
        else if (level >= 81 && level <= 90) {
            all[0] = 4260004;
        }
        else if (level >= 91 && level <= 100) {
            all[0] = 4260005;
        }
        else if (level >= 101 && level <= 110) {
            all[0] = 4260006;
        }
        else if (level >= 111 && level <= 120) {
            all[0] = 4260007;
        }
        else {
            if (level < 121 || level > 200) {
                throw new RuntimeException("Invalid Item Maker type" + level);
            }
            all[0] = 4260008;
        }
        if (GameConstants.isWeapon(itemid) || GameConstants.isOverall(itemid)) {
            all[1] = Randomizer.rand(5, 11);
        }
        else {
            all[1] = Randomizer.rand(3, 7);
        }
        return all;
    }
    
    private static void addEnchantStats(final Map<String, Byte> stats, final Equip item) {
        int s = (byte)Byte.valueOf(stats.get((Object)"incPAD"));
        if (s != 0) {
            item.setWatk((short)(item.getWatk() + s));
        }
        s = (byte)Byte.valueOf(stats.get((Object)"incMAD"));
        if (s != 0) {
            item.setMatk((short)(item.getMatk() + s));
        }
        s = (byte)Byte.valueOf(stats.get((Object)"incACC"));
        if (s != 0) {
            item.setAcc((short)(item.getAcc() + s));
        }
        s = (byte)Byte.valueOf(stats.get((Object)"incEVA"));
        if (s != 0) {
            item.setAvoid((short)(item.getAvoid() + s));
        }
        s = (byte)Byte.valueOf(stats.get((Object)"incSpeed"));
        if (s != 0) {
            item.setSpeed((short)(item.getSpeed() + s));
        }
        s = (byte)Byte.valueOf(stats.get((Object)"incJump"));
        if (s != 0) {
            item.setJump((short)(item.getJump() + s));
        }
        s = (byte)Byte.valueOf(stats.get((Object)"incMaxHP"));
        if (s != 0) {
            item.setHp((short)(item.getHp() + s));
        }
        s = (byte)Byte.valueOf(stats.get((Object)"incMaxMP"));
        if (s != 0) {
            item.setMp((short)(item.getMp() + s));
        }
        s = (byte)Byte.valueOf(stats.get((Object)"incSTR"));
        if (s != 0) {
            item.setStr((short)(item.getStr() + s));
        }
        s = (byte)Byte.valueOf(stats.get((Object)"incDEX"));
        if (s != 0) {
            item.setDex((short)(item.getDex() + s));
        }
        s = (byte)Byte.valueOf(stats.get((Object)"incINT"));
        if (s != 0) {
            item.setInt((short)(item.getInt() + s));
        }
        s = (byte)Byte.valueOf(stats.get((Object)"incLUK"));
        if (s != 0) {
            item.setLuk((short)(item.getLuk() + s));
        }
        s = (byte)Byte.valueOf(stats.get((Object)"randOption"));
        if (s > 0) {
            final boolean success = Randomizer.nextBoolean();
            final int ma = item.getMatk();
            final int wa = item.getWatk();
            if (wa > 0) {
                item.setWatk((short)(success ? (wa + s) : (wa - s)));
            }
            if (ma > 0) {
                item.setMatk((short)(success ? (ma + s) : (ma - s)));
            }
        }
        s = (byte)Byte.valueOf(stats.get((Object)"randStat"));
        if (s > 0) {
            final boolean success = Randomizer.nextBoolean();
            final int str = item.getStr();
            final int dex = item.getDex();
            final int luk = item.getLuk();
            final int int_ = item.getInt();
            if (str > 0) {
                item.setStr((short)(success ? (str + s) : (str - s)));
            }
            if (dex > 0) {
                item.setDex((short)(success ? (dex + s) : (dex - s)));
            }
            if (int_ > 0) {
                item.setInt((short)(success ? (int_ + s) : (int_ - s)));
            }
            if (luk > 0) {
                item.setLuk((short)(success ? (luk + s) : (luk - s)));
            }
        }
    }
    
    private static int getRandomGem(final List<Pair<Integer, Integer>> rewards) {
        final List<Integer> items = new ArrayList<Integer>();
        for (final Pair p : rewards) {
            final int itemid = (int)Integer.valueOf((String)p.getLeft());
            for (int i = 0; i < (int)Integer.valueOf((String)p.getRight()); ++i) {
                items.add(Integer.valueOf(itemid));
            }
        }
        return (int)Integer.valueOf(items.get(Randomizer.nextInt(items.size())));
    }
    
    private static int checkRequiredNRemove(final MapleClient c, final List<Pair<Integer, Integer>> recipe) {
        int itemid = 0;
        for (final Pair<Integer, Integer> p : recipe) {
            if (!c.getPlayer().haveItem((int)Integer.valueOf(p.getLeft()), (int)Integer.valueOf(p.getRight()), false, true)) {
                return 0;
            }
        }
        for (final Pair<Integer, Integer> p : recipe) {
            itemid = (int)Integer.valueOf(p.getLeft());
            MapleInventoryManipulator.removeById(c, GameConstants.getInventoryType(itemid), itemid, (int)Integer.valueOf(p.getRight()), false, false);
        }
        return itemid;
    }
    
    private static boolean hasSkill(final MapleClient c, final int reqlvl) {
        if (GameConstants.isKOC((int)c.getPlayer().getJob())) {
            return c.getPlayer().getSkillLevel(SkillFactory.getSkill(10001007)) >= reqlvl;
        }
        if (GameConstants.isAran((int)c.getPlayer().getJob())) {
            return c.getPlayer().getSkillLevel(SkillFactory.getSkill(20001007)) >= reqlvl;
        }
        return c.getPlayer().getSkillLevel(SkillFactory.getSkill(1007)) >= reqlvl;
    }
}
