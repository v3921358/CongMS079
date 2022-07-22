package handling.channel.handler;

import tools.packet.PlayerShopPacket;
import server.shops.IMaplePlayerShop;
import java.util.concurrent.locks.Lock;
import server.maps.MapleMapObject;
import java.awt.Point;
import handling.world.MaplePartyCharacter;
import client.anticheat.CheatingOffense;
import java.awt.geom.Point2D;
import server.maps.MapleMapItem;
import server.maps.MapleMapObjectType;
import handling.world.World;
import server.PredictCardFactory.PredictCardComment;
import server.PredictCardFactory.PredictCard;
import server.shops.HiredMerchant;
import client.inventory.MaplePet;
import client.ISkill;
import client.PlayerStats;
import java.util.Map;
import handling.channel.ChannelServer;
import server.maps.MapleKite;
import server.MapleShopFactory;
import server.PredictCardFactory;
import server.maps.MapleMist;
import java.awt.Rectangle;
import client.inventory.MaplePet.PetFlag;
import tools.packet.PetPacket;
import tools.packet.MTSCSPacket;
import tools.FileoutputUtil;
import constants.ServerConfig;
import gui.CongMS;
import client.inventory.ItemFlag;
import java.util.EnumMap;
import client.MapleStat;
import server.quest.MapleQuest;
import server.life.MapleLifeFactory;
import server.maps.SavedLocationType;
import client.inventory.MapleMount;
import server.maps.MapleMap;
import server.life.MapleMonster;
import client.inventory.IEquip.ScrollResult;
import server.AutobanManager;
import client.SkillFactory;
import client.inventory.IEquip;
import client.inventory.ModifyInventory;
import java.util.Collection;
import server.StructPotentialItem;
import client.inventory.Equip;
import server.maps.FieldLimitType;
import tools.Pair;
import handling.world.World.Broadcast;
import server.RandomRewards;
import server.Randomizer;
import server.StructRewardItem;
import server.MapleItemInformationProvider;
import scripting.NPCScriptManager;
import constants.GameConstants;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import client.inventory.IItem;
import java.util.LinkedList;
import client.inventory.MapleInventory;
import client.MapleCharacter;
import server.MapleInventoryManipulator;
import client.inventory.MapleInventoryType;
import tools.MaplePacketCreator;
import client.MapleClient;
import tools.data.LittleEndianAccessor;

public class InventoryHandler
{
    public static final int OWL_ID = 2;
    
    public static final void ItemMove(final LittleEndianAccessor slea, final MapleClient c) {
        final MapleCharacter player = c.getPlayer();
        if (c.getPlayer().hasBlockedInventory(true)) {
            return;
        }
        if (c.getPlayer().getPlayerShop() != null || c.getPlayer().getConversation() > 0 || c.getPlayer().getTrade() != null) {
            c.getSession().write((Object)MaplePacketCreator.enableActions());
            return;
        }
        player.setCurrenttime(System.currentTimeMillis());
        if (player.getCurrenttime() - player.getLasttime() < player.get防止复制时间()) {
            c.getPlayer().dropMessage(5, "请慢点使用.不然会掉线哟！");
            c.getSession().write((Object)MaplePacketCreator.enableActions());
            return;
        }
        c.getPlayer().updateTick(slea.readInt());
        final MapleInventoryType type = MapleInventoryType.getByType(slea.readByte());
        final short src = slea.readShort();
        final short dst = slea.readShort();
        final short quantity = slea.readShort();
        if (src < 0 && dst > 0) {
            MapleInventoryManipulator.unequip(c, src, dst);
        }
        else if (dst < 0) {
            MapleInventoryManipulator.equip(c, src, dst);
        }
        else if (dst == 0) {
            if (quantity < 0) {
                c.getPlayer().dropMessage(1, "请不要以任何方式破解。");
                return;
            }
            MapleInventoryManipulator.drop(c, type, src, quantity);
        }
        else {
            MapleInventoryManipulator.move(c, type, src, dst);
        }
    }
    
    public static final void ItemSort(final LittleEndianAccessor slea, final MapleClient c) {
        c.getPlayer().updateTick(slea.readInt());
        final MapleInventoryType pInvType = MapleInventoryType.getByType(slea.readByte());
        if (pInvType == MapleInventoryType.UNDEFINED) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        final MapleInventory pInv = c.getPlayer().getInventory(pInvType);
        boolean sorted = false;
        while (!sorted) {
            final byte freeSlot = (byte)pInv.getNextFreeSlot();
            if (freeSlot != -1) {
                byte itemSlot = -1;
                for (byte i = (byte)(freeSlot + 1); i <= pInv.getSlotLimit(); ++i) {
                    if (pInv.getItem((short)i) != null) {
                        itemSlot = i;
                        break;
                    }
                }
                if (itemSlot > 0) {
                    MapleInventoryManipulator.move(c, pInvType, (short)itemSlot, (short)freeSlot);
                }
                else {
                    sorted = true;
                }
            }
            else {
                sorted = true;
            }
        }
        c.sendPacket(MaplePacketCreator.finishedSort((int)pInvType.getType()));
        c.sendPacket(MaplePacketCreator.enableActions());
    }
    
    public static final void ItemGather(final LittleEndianAccessor slea, final MapleClient c) {
        if (c == null || c.getPlayer() == null) {
            return;
        }
        c.getPlayer().updateTick(slea.readInt());
        final byte mode = slea.readByte();
        final MapleInventoryType invType = MapleInventoryType.getByType(mode);
        if (invType != null) {
            final MapleInventory Inv = c.getPlayer().getInventory(invType);
            final List<IItem> itemMap = new LinkedList<IItem>();
            for (final IItem item : Inv.list()) {
                itemMap.add(item.copy());
            }
            for (final IItem itemStats : itemMap) {
                MapleInventoryManipulator.removeById(c, invType, itemStats.getItemId(), (int)itemStats.getQuantity(), true, false);
            }
            final List<IItem> sortedItems = sortItems(itemMap);
            for (final IItem item2 : sortedItems) {
                MapleInventoryManipulator.addFromDrop(c, item2, false);
            }
            c.sendPacket(MaplePacketCreator.finishedGather((int)mode));
            c.sendPacket(MaplePacketCreator.enableActions());
            itemMap.clear();
            sortedItems.clear();
        }
    }
    
    private static List<IItem> sortItems(final List<IItem> passedMap) {
        final List<Integer> itemIds = new ArrayList<Integer>();
        for (final IItem item : passedMap) {
            itemIds.add(Integer.valueOf(item.getItemId()));
        }
        Collections.sort(itemIds);
        final List<IItem> sortedList = new LinkedList<IItem>();
        for (final Integer val : itemIds) {
            for (final IItem item2 : passedMap) {
                if ((int)val == item2.getItemId()) {
                    sortedList.add(item2);
                    passedMap.remove((Object)item2);
                    break;
                }
            }
        }
        return sortedList;
    }
    
    public static final boolean UseRewardItem(final byte slot, final int itemId, final MapleClient c, final MapleCharacter chr) {
        final IItem toUse = c.getPlayer().getInventory(GameConstants.getInventoryType(itemId)).getItem((short)slot);
        if (toUse.getItemId() == 2022552) {
            c.sendPacket(MaplePacketCreator.enableActions());
            NPCScriptManager.getInstance().start(c, 9900004, "拍卖功能");
            return true;
        }
        if (toUse.getItemId() == 2022466) {//2021-8-14开始
            c.sendPacket(MaplePacketCreator.enableActions());
            NPCScriptManager.getInstance().start(c, 9900004, "自定义箱子1");
            return true;
        }
         if (toUse.getItemId() == 2022336) {
            c.sendPacket(MaplePacketCreator.enableActions());
            NPCScriptManager.getInstance().start(c, 9900004, "自定义箱子2");
            return true;
        }//2021-8-14结尾
        if (toUse.getItemId() == 5530000) {
            c.sendPacket(MaplePacketCreator.enableActions());
            NPCScriptManager.getInstance().start(c, 9900004, "新人礼包");
        }
        if (toUse != null && toUse.getQuantity() >= 1 && toUse.getItemId() == itemId) {
            if (chr.getInventory(MapleInventoryType.EQUIP).getNextFreeSlot() > -1 && chr.getInventory(MapleInventoryType.USE).getNextFreeSlot() > -1 && chr.getInventory(MapleInventoryType.SETUP).getNextFreeSlot() > -1 && chr.getInventory(MapleInventoryType.ETC).getNextFreeSlot() > -1) {
                if (toUse.getItemId() != 2022428) {
                    final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                    final Pair<Integer, List<StructRewardItem>> rewards = ii.getRewardItem(itemId);
                    if (rewards != null && (int)Integer.valueOf(rewards.getLeft()) > 0) {
                        boolean rewarded = false;
                        while (!rewarded) {
                            for (final StructRewardItem reward : (List<StructRewardItem>)rewards.getRight()) {
                                if (reward.getProb() > 0 && Randomizer.nextInt((int)Integer.valueOf(rewards.getLeft())) < reward.getProb()) {
                                    if (GameConstants.getInventoryType(reward.getItemid()) == MapleInventoryType.EQUIP) {
                                        final IItem item = ii.getEquipById(reward.getItemid());
                                        if (reward.getPeriod() > 0L) {
                                            item.setExpiration(System.currentTimeMillis() + reward.getPeriod() * 60L * 60L * 10L);
                                        }
                                        MapleInventoryManipulator.addbyItem(c, item);
                                    }
                                    else {
                                        MapleInventoryManipulator.addById(c, reward.getItemid(), reward.getQuantity());
                                    }
                                    MapleInventoryManipulator.removeById(c, GameConstants.getInventoryType(itemId), itemId, 1, false, false);
                                    c.sendPacket(MaplePacketCreator.showRewardItemAnimation(reward.getItemid(), reward.getEffect()));
                                    chr.getMap().broadcastMessage(chr, MaplePacketCreator.showRewardItemAnimation(reward.getItemid(), reward.getEffect(), chr.getId()), false);
                                    rewarded = true;
                                    return rewarded;
                                }
                            }
                        }
                    }
                    else {
                        chr.dropMessage(6, "未知的錯誤.");
                    }
                }
                else {
                    switch (toUse.getItemId()) {
                        case 2022428: {
                            final int reward2 = RandomRewards.getInstance().getJxBoxReward();
                            final String box = "新年禮盒";
                            int amount = 1;
                            switch (reward2) {
                                case 2000004: {
                                    amount = 200;
                                    break;
                                }
                                case 2000005: {
                                    amount = 100;
                                    break;
                                }
                            }
                            if (chr.getInventory(MapleInventoryType.EQUIP).getNextFreeSlot() > -1 && chr.getInventory(MapleInventoryType.USE).getNextFreeSlot() > -1 && chr.getInventory(MapleInventoryType.SETUP).getNextFreeSlot() > -1 && chr.getInventory(MapleInventoryType.ETC).getNextFreeSlot() > -1) {
                                final IItem item2 = MapleInventoryManipulator.addbyId_Gachapon(c, reward2, (short)amount);
                                final byte rareness = GameConstants.gachaponRareItem(item2.getItemId());
                                MapleInventoryManipulator.removeById(c, GameConstants.getInventoryType(itemId), itemId, 1, false, false);
                                c.sendPacket(MaplePacketCreator.getShowItemGain(reward2, (short)amount, true));
                                if (rareness > 0) {
                                    Broadcast.broadcastMessage(MaplePacketCreator.getGachaponMega("\t恭喜\u0010 " + c.getPlayer().getName(), " : 從" + box + "抽到！", item2, rareness));
                                }
                            }
                            else {
                                chr.dropMessage(5, "你有一個栏位滿了，請空出來再打開" + box + "！");
                                c.sendPacket(MaplePacketCreator.enableActions());
                            }
                            break;
                        }
                        default: {
                            return false;
                        }
                    }
                }
            }
            else {
                chr.dropMessage(6, "你有一個栏位滿了 請空出來再打開");
            }
        }
        return false;
    }
    
    public static final void UseItem(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (chr == null || !chr.isAlive() || chr.getMapId() == 749040100 || chr.getMap() == null) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        final long time = System.currentTimeMillis();
        if (chr.getNextConsume() > time) {
            chr.dropMessage(5, "你不可以使用這個道具。");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        c.getPlayer().updateTick(slea.readInt());
        final byte slot = (byte)slea.readShort();
        final int itemId = slea.readInt();
        final IItem toUse = chr.getInventory(MapleInventoryType.USE).getItem((short)slot);
        if (toUse == null || toUse.getQuantity() < 1 || toUse.getItemId() != itemId) {
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
    
    public static final void UseReturnScroll(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (!chr.isAlive() || chr.getMapId() == 749040100 || GameConstants.isNotToMap(chr.getMapId())) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        c.getPlayer().updateTick(slea.readInt());
        final byte slot = (byte)slea.readShort();
        final int itemId = slea.readInt();
        final IItem toUse = chr.getInventory(MapleInventoryType.USE).getItem((short)slot);
        if (toUse == null || toUse.getQuantity() < 1 || toUse.getItemId() != itemId) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (!FieldLimitType.PotionUse.check(chr.getMap().getFieldLimit())) {
            if (MapleItemInformationProvider.getInstance().getItemEffect(toUse.getItemId()).applyReturnScroll(chr)) {
                MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, (short)slot, (short)1, false);
            }
            else {
                c.sendPacket(MaplePacketCreator.enableActions());
            }
        }
        else {
            c.sendPacket(MaplePacketCreator.enableActions());
        }
    }
    
    public static final void UseMagnify(final LittleEndianAccessor slea, final MapleClient c) {
        c.getPlayer().updateTick(slea.readInt());
        final IItem magnify = c.getPlayer().getInventory(MapleInventoryType.USE).getItem((short)(byte)slea.readShort());
        final IItem toReveal = c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short)(byte)slea.readShort());
        if (magnify == null || toReveal == null) {
            c.sendPacket(MaplePacketCreator.getInventoryFull());
            return;
        }
        final Equip eqq = (Equip)toReveal;
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final int reqLevel = ii.getReqLevel(eqq.getItemId()) / 10;
        if (eqq.getState() == 1 && (magnify.getItemId() == 2460003 || (magnify.getItemId() == 2460002 && reqLevel <= 12) || (magnify.getItemId() == 2460001 && reqLevel <= 7) || (magnify.getItemId() == 2460000 && reqLevel <= 3))) {
            final List<List<StructPotentialItem>> pots = new LinkedList<List<StructPotentialItem>>((Collection<? extends List<StructPotentialItem>>)ii.getAllPotentialInfo().values());
            int new_state = Math.abs((int)eqq.getPotential1());
            if (new_state > 7 || new_state < 5) {
                new_state = 5;
            }
            final int lines = (eqq.getPotential2() != 0) ? 3 : 2;
            while (eqq.getState() != new_state) {
                for (int i = 0; i < lines; ++i) {
                    for (boolean rewarded = false; !rewarded; rewarded = true) {
                        final StructPotentialItem pot = (StructPotentialItem)((List<StructPotentialItem>)pots.get(Randomizer.nextInt(pots.size()))).get(reqLevel);
                        if (pot != null && pot.reqLevel / 10 <= reqLevel && GameConstants.optionTypeFits(pot.optionType, eqq.getItemId()) && GameConstants.potentialIDFits((int)pot.potentialID, new_state, i)) {
                            if (i == 0) {
                                eqq.setPotential1(pot.potentialID);
                            }
                            else if (i == 1) {
                                eqq.setPotential2(pot.potentialID);
                            }
                            else if (i == 2) {
                                eqq.setPotential3(pot.potentialID);
                            }
                        }
                    }
                }
            }
            c.sendPacket(MaplePacketCreator.modifyInventory(true, new ModifyInventory(2, magnify)));
            c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.getPotentialReset(c.getPlayer().getId(), eqq.getPosition()));
            MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, magnify.getPosition(), (short)1, false);
        }
        else {
            c.sendPacket(MaplePacketCreator.getInventoryFull());
        }
    }
    
    public static final boolean UseUpgradeScroll(final byte slot, final byte dst, final byte ws, final MapleClient c, final MapleCharacter chr) {
        return UseUpgradeScroll(slot, dst, ws, c, chr, 0);
    }
    
    public static final boolean UseUpgradeScroll(final byte slot, final byte dst, final byte ws, final MapleClient c, final MapleCharacter chr, final int vegas) {
        boolean whiteScroll = false;
        boolean legendarySpirit = false;
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if ((ws & 0x2) == 0x2) {
            whiteScroll = true;
        }
        IEquip toScroll;
        if (dst < 0) {
            toScroll = (IEquip)chr.getInventory(MapleInventoryType.EQUIPPED).getItem((short)dst);
        }
        else {
            legendarySpirit = true;
            toScroll = (IEquip)chr.getInventory(MapleInventoryType.EQUIP).getItem((short)dst);
        }
        if (toScroll == null) {
            return false;
        }
        final byte oldLevel = toScroll.getLevel();
        final byte oldEnhance = toScroll.getEnhance();
        final byte oldState = toScroll.getState();
        final byte oldFlag = toScroll.getFlag();
        final byte oldSlots = toScroll.getUpgradeSlots();
        final IItem scroll = chr.getInventory(MapleInventoryType.USE).getItem((short)slot);
        if (scroll == null) {
            c.sendPacket(MaplePacketCreator.getInventoryFull());
            return false;
        }
        if (!GameConstants.isSpecialScroll(scroll.getItemId()) && !GameConstants.isCleanSlate(scroll.getItemId()) && !GameConstants.isEquipScroll(scroll.getItemId()) && !GameConstants.isPotentialScroll(scroll.getItemId())) {
            if (toScroll.getUpgradeSlots() < 1) {
                c.sendPacket(MaplePacketCreator.getInventoryFull());
                return false;
            }
        }
        else if (GameConstants.isEquipScroll(scroll.getItemId())) {
            if (toScroll.getUpgradeSlots() >= 1 || toScroll.getEnhance() >= 100 || vegas > 0 || ii.isCash(toScroll.getItemId())) {
                c.sendPacket(MaplePacketCreator.getInventoryFull());
                return false;
            }
        }
        else if (GameConstants.isPotentialScroll(scroll.getItemId()) && (toScroll.getState() >= 1 || (toScroll.getLevel() == 0 && toScroll.getUpgradeSlots() == 0) || vegas > 0 || ii.isCash(toScroll.getItemId()))) {
            c.sendPacket(MaplePacketCreator.getInventoryFull());
            return false;
        }
        if (!GameConstants.canScroll(toScroll.getItemId()) && !GameConstants.isChaosScroll(toScroll.getItemId())) {
            c.sendPacket(MaplePacketCreator.getInventoryFull());
            return false;
        }
        if ((GameConstants.isCleanSlate(scroll.getItemId()) || GameConstants.isTablet(scroll.getItemId()) || GameConstants.isChaosScroll(scroll.getItemId())) && (vegas > 0 || ii.isCash(toScroll.getItemId()))) {
            c.sendPacket(MaplePacketCreator.getInventoryFull());
            return false;
        }
        if (GameConstants.isTablet(scroll.getItemId()) && toScroll.getDurability() < 0) {
            c.sendPacket(MaplePacketCreator.getInventoryFull());
            return false;
        }
        if (!GameConstants.isTablet(scroll.getItemId()) && toScroll.getDurability() >= 0) {
            c.sendPacket(MaplePacketCreator.getInventoryFull());
            return false;
        }
        IItem wscroll = null;
        final List<Integer> scrollReqs = ii.getScrollReqs(scroll.getItemId());
        if (scrollReqs.size() > 0 && !scrollReqs.contains((Object)Integer.valueOf(toScroll.getItemId()))) {
            c.sendPacket(MaplePacketCreator.getInventoryFull());
            return false;
        }
        if (whiteScroll) {
            wscroll = chr.getInventory(MapleInventoryType.USE).findById(2340000);
            if (wscroll == null) {
                whiteScroll = false;
            }
        }
        if (scroll.getItemId() == 2049115 && toScroll.getItemId() != 1003068) {
            return false;
        }
        if (GameConstants.isTablet(scroll.getItemId())) {
            switch (scroll.getItemId() % 1000 / 100) {
                case 0: {
                    if (GameConstants.isTwoHanded(toScroll.getItemId()) || !GameConstants.isWeapon(toScroll.getItemId())) {
                        return false;
                    }
                    break;
                }
                case 1: {
                    if (!GameConstants.isTwoHanded(toScroll.getItemId()) || !GameConstants.isWeapon(toScroll.getItemId())) {
                        return false;
                    }
                    break;
                }
                case 2: {
                    if (GameConstants.isAccessory(toScroll.getItemId()) || GameConstants.isWeapon(toScroll.getItemId())) {
                        return false;
                    }
                    break;
                }
                case 3: {
                    if (!GameConstants.isAccessory(toScroll.getItemId()) || GameConstants.isWeapon(toScroll.getItemId())) {
                        return false;
                    }
                    break;
                }
            }
        }
        else if (!GameConstants.isAccessoryScroll(scroll.getItemId()) && !GameConstants.isChaosScroll(scroll.getItemId()) && !GameConstants.isCleanSlate(scroll.getItemId()) && !GameConstants.isEquipScroll(scroll.getItemId()) && !GameConstants.isPotentialScroll(scroll.getItemId()) && !ii.canScroll(scroll.getItemId(), toScroll.getItemId())) {
            return false;
        }
        if (GameConstants.isAccessoryScroll(scroll.getItemId()) && !GameConstants.isAccessory(toScroll.getItemId())) {
            return false;
        }
        if (scroll.getQuantity() <= 0) {
            return false;
        }
        if (legendarySpirit && vegas == 0 && chr.getSkillLevel(SkillFactory.getSkill(1003)) <= 0 && chr.getSkillLevel(SkillFactory.getSkill(10001003)) <= 0 && chr.getSkillLevel(SkillFactory.getSkill(20001003)) <= 0 && chr.getSkillLevel(SkillFactory.getSkill(20011003)) <= 0 && chr.getSkillLevel(SkillFactory.getSkill(30001003)) <= 0) {
            AutobanManager.getInstance().addPoints(c, 50, 120000L, "Using the Skill 'Legendary Spirit' without having it.");
            return false;
        }
        final IEquip scrolled = (IEquip)ii.scrollEquipWithId((IItem)toScroll, scroll, whiteScroll, chr, vegas);
        ScrollResult scrollSuccess;
        if (scrolled == null) {
            scrollSuccess = ScrollResult.CURSE;
        }
        else if (scrolled.getLevel() > oldLevel || scrolled.getEnhance() > oldEnhance || scrolled.getState() > oldState || scrolled.getFlag() > oldFlag) {
            scrollSuccess = ScrollResult.SUCCESS;
        }
        else if (GameConstants.isCleanSlate(scroll.getItemId()) && scrolled.getUpgradeSlots() > oldSlots) {
            scrollSuccess = ScrollResult.SUCCESS;
        }
        else {
            scrollSuccess = ScrollResult.FAIL;
        }
        MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, scroll.getPosition(), (short)1, false, false);
        if (whiteScroll && wscroll != null) {
            MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, wscroll.getPosition(), (short)1, false, false);
        }
        final List<ModifyInventory> mods = new ArrayList<ModifyInventory>();
        if (scrollSuccess == ScrollResult.CURSE) {
            mods.add(new ModifyInventory(3, (IItem)toScroll));
            if (dst < 0) {
                c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).removeItem(toScroll.getPosition());
            }
            else {
                c.getPlayer().getInventory(MapleInventoryType.EQUIP).removeItem(toScroll.getPosition());
            }
        }
        else {
            mods.add(new ModifyInventory(3, (IItem)scrolled));
            mods.add(new ModifyInventory(0, (IItem)scrolled));
        }
        c.sendPacket(MaplePacketCreator.modifyInventory(true, mods));
        chr.getMap().broadcastMessage(chr, MaplePacketCreator.getScrollEffect(c.getPlayer().getId(), scrollSuccess, legendarySpirit), vegas == 0);
        if (dst < 0 && (scrollSuccess == ScrollResult.SUCCESS || scrollSuccess == ScrollResult.CURSE) && vegas == 0) {
            chr.equipChanged();
        }
        return true;
    }
    
    public static final void UseCatchItem(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        c.getPlayer().updateTick(slea.readInt());
        final byte slot = (byte)slea.readShort();
        final int itemid = slea.readInt();
        final MapleMonster mob = chr.getMap().getMonsterByOid(slea.readInt());
        final IItem toUse = chr.getInventory(MapleInventoryType.USE).getItem((short)slot);
        if (toUse != null && toUse.getQuantity() > 0 && toUse.getItemId() == itemid && mob != null) {
            switch (itemid) {
                case 2270004: {
                    final MapleMap map = chr.getMap();
                    if (mob.getHp() <= mob.getMobMaxHp() / 2L) {
                        map.broadcastMessage(MaplePacketCreator.catchMonster(mob.getId(), itemid, (byte)1));
                        map.killMonster(mob, chr, true, false, (byte)0);
                        MapleInventoryManipulator.removeById(c, MapleInventoryType.USE, itemid, 1, false, false);
                        MapleInventoryManipulator.addById(c, 4001169, (short)1);
                        break;
                    }
                    map.broadcastMessage(MaplePacketCreator.catchMonster(mob.getId(), itemid, (byte)0));
                    chr.dropMessage(5, "因怪物的生命值過高，所以無法捕捉！");
                    break;
                }
                case 2270002: {
                    final MapleMap map = chr.getMap();
                    if (mob.getHp() <= mob.getMobMaxHp() / 2L) {
                        map.broadcastMessage(MaplePacketCreator.catchMonster(mob.getId(), itemid, (byte)1));
                        map.killMonster(mob, chr, true, false, (byte)0);
                        MapleInventoryManipulator.removeById(c, MapleInventoryType.USE, itemid, 1, false, false);
                        break;
                    }
                    map.broadcastMessage(MaplePacketCreator.catchMonster(mob.getId(), itemid, (byte)0));
                    chr.dropMessage(5, "因怪物的生命值過高，所以無法捕捉！");
                    break;
                }
                case 2270000: {
                    if (mob.getId() != 9300101) {
                        break;
                    }
                    final MapleMap map = c.getPlayer().getMap();
                    map.broadcastMessage(MaplePacketCreator.catchMonster(mob.getId(), itemid, (byte)1));
                    map.killMonster(mob, chr, true, false, (byte)0);
                    MapleInventoryManipulator.addById(c, 1902000, (short)1, null);
                    MapleInventoryManipulator.removeById(c, MapleInventoryType.USE, itemid, 1, false, false);
                    break;
                }
                case 2270003: {
                    if (mob.getId() != 9500320) {
                        break;
                    }
                    final MapleMap map = c.getPlayer().getMap();
                    if (mob.getHp() <= mob.getMobMaxHp() / 2L) {
                        map.broadcastMessage(MaplePacketCreator.catchMonster(mob.getId(), itemid, (byte)1));
                        map.killMonster(mob, chr, true, false, (byte)0);
                        MapleInventoryManipulator.removeById(c, MapleInventoryType.USE, itemid, 1, false, false);
                        break;
                    }
                    map.broadcastMessage(MaplePacketCreator.catchMonster(mob.getId(), itemid, (byte)0));
                    chr.dropMessage(5, "因怪物的生命值過高，所以無法捕捉！");
                    break;
                }
            }
        }
        c.sendPacket(MaplePacketCreator.enableActions());
    }
    
    public static final void UseMountFood(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        c.getPlayer().updateTick(slea.readInt());
        final byte slot = (byte)slea.readShort();
        final int itemid = slea.readInt();
        final IItem toUse = chr.getInventory(MapleInventoryType.USE).getItem((short)slot);
        final MapleMount mount = chr.getMount();
        if (toUse != null && toUse.getQuantity() > 0 && toUse.getItemId() == itemid && mount != null) {
            final int fatigue = mount.getFatigue();
            boolean levelup = false;
            mount.setFatigue((byte)(-30));
            if (fatigue > 0) {
                mount.increaseExp();
                final int level = mount.getLevel();
                if (level < 30 && mount.getExp() >= GameConstants.getMountExpNeededForLevel(level + 1)) {
                    mount.setLevel((byte)(level + 1));
                    levelup = true;
                }
            }
            chr.getMap().broadcastMessage(MaplePacketCreator.updateMount(chr, levelup));
            MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, (short)slot, (short)1, false);
        }
        c.sendPacket(MaplePacketCreator.enableActions());
    }
    
    public static final void UseScriptedNPCItem(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        c.getPlayer().updateTick(slea.readInt());
        final byte slot = (byte)slea.readShort();
        final int itemId = slea.readInt();
        final IItem toUse = chr.getInventory(MapleInventoryType.USE).getItem((short)slot);
        long expiration_days = 0L;
        int mountid = 0;
        if (toUse != null && toUse.getQuantity() >= 1 && toUse.getItemId() == itemId) {
            switch (toUse.getItemId()) {
                case 2430007: {
                    final MapleInventory inventory = chr.getInventory(MapleInventoryType.SETUP);
                    MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, (short)slot, (short)1, false);
                    if (inventory.countById(3994102) >= 20 && inventory.countById(3994103) >= 20 && inventory.countById(3994104) >= 20 && inventory.countById(3994105) >= 20) {
                        MapleInventoryManipulator.addById(c, 2430008, (short)1);
                        MapleInventoryManipulator.removeById(c, MapleInventoryType.SETUP, 3994102, 20, false, false);
                        MapleInventoryManipulator.removeById(c, MapleInventoryType.SETUP, 3994103, 20, false, false);
                        MapleInventoryManipulator.removeById(c, MapleInventoryType.SETUP, 3994104, 20, false, false);
                        MapleInventoryManipulator.removeById(c, MapleInventoryType.SETUP, 3994105, 20, false, false);
                    }
                    else {
                        MapleInventoryManipulator.addById(c, 2430007, (short)1);
                    }
                    NPCScriptManager.getInstance().start(c, 2084001);
                    break;
                }
                case 2430008: {
                    chr.saveLocation(SavedLocationType.RICHIE);
                    boolean warped = false;
                    for (int i = 390001000; i <= 390001004; ++i) {
                        final MapleMap map = c.getChannelServer().getMapFactory().getMap(i);
                        if (map.getCharactersSize() == 0) {
                            chr.changeMap(map, map.getPortal(0));
                            warped = true;
                            break;
                        }
                    }
                    if (warped) {
                        MapleInventoryManipulator.removeById(c, MapleInventoryType.USE, 2430008, 1, false, false);
                        break;
                    }
                    c.getPlayer().dropMessage(5, "全部地都在使用中，請稍後再嘗試一次。");
                    break;
                }
                case 2430112: {
                    if (c.getPlayer().getInventory(MapleInventoryType.USE).getNumFreeSlot() < 1) {
                        c.getPlayer().dropMessage(5, "請空出一些栏位。");
                        break;
                    }
                    if (c.getPlayer().getInventory(MapleInventoryType.USE).countById(2430112) >= 25) {
                        if (MapleInventoryManipulator.checkSpace(c, 2049400, 1, "") && MapleInventoryManipulator.removeById(c, MapleInventoryType.USE, 2430112, 25, true, false)) {
                            MapleInventoryManipulator.addById(c, 2049400, (short)1);
                            break;
                        }
                        c.getPlayer().dropMessage(5, "請空出一些栏位。");
                        break;
                    }
                    else {
                        if (c.getPlayer().getInventory(MapleInventoryType.USE).countById(2430112) < 10) {
                            c.getPlayer().dropMessage(5, "There needs to be 10 Fragments for a Potential Scroll, 25 for Advanced Potential Scroll.");
                            break;
                        }
                        if (MapleInventoryManipulator.checkSpace(c, 2049400, 1, "") && MapleInventoryManipulator.removeById(c, MapleInventoryType.USE, 2430112, 10, true, false)) {
                            MapleInventoryManipulator.addById(c, 2049401, (short)1);
                            break;
                        }
                        c.getPlayer().dropMessage(5, "請空出一些栏位。.");
                        break;
                    }
                }
                case 2430036: {
                    mountid = 1027;
                    expiration_days = 1L;
                    break;
                }
                case 2430037: {
                    mountid = 1028;
                    expiration_days = 1L;
                    break;
                }
                case 2430038: {
                    mountid = 1029;
                    expiration_days = 1L;
                    break;
                }
                case 2430039: {
                    mountid = 1030;
                    expiration_days = 1L;
                    break;
                }
                case 2430040: {
                    mountid = 1031;
                    expiration_days = 1L;
                    break;
                }
                case 2430053: {
                    mountid = 1027;
                    expiration_days = 1L;
                    break;
                }
                case 2430054: {
                    mountid = 1028;
                    expiration_days = 30L;
                    break;
                }
                case 2430055: {
                    mountid = 1029;
                    expiration_days = 30L;
                    break;
                }
                case 2430056: {
                    mountid = 1035;
                    expiration_days = 30L;
                    break;
                }
                case 2430072: {
                    mountid = 1034;
                    expiration_days = 7L;
                    break;
                }
                case 2430073: {
                    mountid = 1036;
                    expiration_days = 15L;
                    break;
                }
                case 2430074: {
                    mountid = 1037;
                    expiration_days = 15L;
                    break;
                }
                case 2430075: {
                    mountid = 1038;
                    expiration_days = 15L;
                    break;
                }
                case 2430076: {
                    mountid = 1039;
                    expiration_days = 15L;
                    break;
                }
                case 2430077: {
                    mountid = 1040;
                    expiration_days = 15L;
                    break;
                }
                case 2430080: {
                    mountid = 1042;
                    expiration_days = 20L;
                    break;
                }
                case 2430082: {
                    mountid = 1044;
                    expiration_days = 7L;
                    break;
                }
                case 2430091: {
                    mountid = 1049;
                    expiration_days = 10L;
                    break;
                }
                case 2430092: {
                    mountid = 1050;
                    expiration_days = 10L;
                    break;
                }
                case 2430093: {
                    mountid = 1051;
                    expiration_days = 10L;
                    break;
                }
                case 2430101: {
                    mountid = 1052;
                    expiration_days = 10L;
                    break;
                }
                case 2430102: {
                    mountid = 1053;
                    expiration_days = 10L;
                    break;
                }
                case 2430103: {
                    mountid = 1054;
                    expiration_days = 30L;
                    break;
                }
                case 2430117: {
                    mountid = 1036;
                    expiration_days = 365L;
                    break;
                }
                case 2430118: {
                    mountid = 1039;
                    expiration_days = 365L;
                    break;
                }
                case 2430119: {
                    mountid = 1040;
                    expiration_days = 365L;
                    break;
                }
                case 2430120: {
                    mountid = 1037;
                    expiration_days = 365L;
                    break;
                }
                case 2430136: {
                    mountid = 1069;
                    expiration_days = 30L;
                    break;
                }
                case 2430137: {
                    mountid = 1069;
                    expiration_days = 365L;
                    break;
                }
                case 2430201: {
                    mountid = 1096;
                    expiration_days = 60L;
                    break;
                }
                case 2430228: {
                    mountid = 1101;
                    expiration_days = 60L;
                    break;
                }
                case 2430229: {
                    mountid = 1102;
                    expiration_days = 60L;
                    break;
                }
            }
        }
        if (mountid > 0) {
            mountid += (GameConstants.isAran((int)c.getPlayer().getJob()) ? 20000000 : 0);
            if (c.getPlayer().getSkillLevel(mountid) > 0) {
                c.getPlayer().dropMessage(5, "已經有了這個技能了。");
            }
            else if (expiration_days > 0L) {
                MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, (short)slot, (short)1, false);
                c.getPlayer().changeSkillLevel(SkillFactory.getSkill(mountid), (byte)1, (byte)1, System.currentTimeMillis() + expiration_days * 24L * 60L * 60L * 1000L);
                c.getPlayer().dropMessage(5, "已經學会了這個技能了。");
            }
        }
        c.sendPacket(MaplePacketCreator.enableActions());
    }
    
    public static final void UseSummonBag(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (!chr.isAlive()) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (chr.getMapId() == 749050400) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        c.getPlayer().updateTick(slea.readInt());
        final byte slot = (byte)slea.readShort();
        final int itemId = slea.readInt();
        final IItem toUse = chr.getInventory(MapleInventoryType.USE).getItem((short)slot);
        if (toUse != null && toUse.getQuantity() >= 1 && toUse.getItemId() == itemId) {
            if (toUse.getItemId() == 2100069) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            if (itemId == 2100069) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, (short)slot, (short)1, false);
            if (c.getPlayer().isGM() || !FieldLimitType.SummoningBag.check(chr.getMap().getFieldLimit())) {
                final List<Pair<Integer, Integer>> toSpawn = MapleItemInformationProvider.getInstance().getSummonMobs(itemId);
                if (toSpawn == null) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                final int type = 0;
                for (final Pair<Integer, Integer> toSpawn2 : toSpawn) {
                    if (Randomizer.nextInt(99) <= (int)Integer.valueOf(toSpawn2.getRight())) {
                        final MapleMonster ht = MapleLifeFactory.getMonster((int)Integer.valueOf(toSpawn2.getLeft()));
                        chr.getMap().spawnMonster_sSack(ht, chr.getPosition(), type);
                    }
                }
            }
        }
        c.sendPacket(MaplePacketCreator.enableActions());
    }
    
    public static final void UseTreasureChest(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        final short slot = slea.readShort();
        final int itemid = slea.readInt();
        final IItem toUse = chr.getInventory(MapleInventoryType.ETC).getItem((short)(byte)slot);
        if (toUse == null || toUse.getQuantity() <= 0 || toUse.getItemId() != itemid) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        String keyname = "";
        int reward = 0;
        int keyIDforRemoval = 0;
        String box = null;
        switch (toUse.getItemId()) {
            case 4280000: {
                reward = RandomRewards.getInstance().getGoldBoxReward();
                keyIDforRemoval = 5490000;
                box = "金寶箱";
                break;
            }
            case 4280001: {
                reward = RandomRewards.getInstance().getSilverBoxReward();
                keyIDforRemoval = 5490001;
                box = "銀寶箱";
                break;
            }
            default: {
                return;
            }
        }
        int amount = 1;
        keyname = MapleItemInformationProvider.getInstance().getName(keyIDforRemoval);
        switch (reward) {
            case 2000004: {
                amount = 200;
                break;
            }
            case 2000005: {
                amount = 100;
                break;
            }
        }
        if (chr.getInventory(MapleInventoryType.CASH).countById(keyIDforRemoval) > 0) {
            if (chr.getInventory(MapleInventoryType.EQUIP).getNextFreeSlot() > -1 && chr.getInventory(MapleInventoryType.USE).getNextFreeSlot() > -1 && chr.getInventory(MapleInventoryType.SETUP).getNextFreeSlot() > -1 && chr.getInventory(MapleInventoryType.ETC).getNextFreeSlot() > -1) {
                final IItem item = MapleInventoryManipulator.addbyId_Gachapon(c, reward, (short)amount);
                final byte rareness = GameConstants.gachaponRareItem(item.getItemId());
                MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.ETC, (short)(byte)slot, (short)1, true);
                MapleInventoryManipulator.removeById(c, MapleInventoryType.CASH, keyIDforRemoval, 1, true, false);
                c.sendPacket(MaplePacketCreator.getShowItemGain(reward, (short)amount, true));
                if (rareness > 0) {
                    Broadcast.broadcastMessage(MaplePacketCreator.getGachaponMega("\t恭喜\u0010 " + c.getPlayer().getName(), " : 從" + box + "抽到！", item, rareness));
                }
            }
            else {
                chr.dropMessage(5, "你有一個栏位滿了，請空出來再打開" + box + "！");
                c.sendPacket(MaplePacketCreator.enableActions());
            }
        }
        else {
            chr.dropMessage(5, "請確認是否有" + keyname);
            c.sendPacket(MaplePacketCreator.enableActions());
        }
    }
    
    public static final void UseCashItem(final LittleEndianAccessor slea, final MapleClient c) {
        final byte slot = (byte)slea.readShort();
        final int itemId = slea.readInt();
        final IItem toUse = c.getPlayer().getInventory(MapleInventoryType.CASH).getItem((short)slot);
        if (toUse == null || toUse.getItemId() != itemId || toUse.getQuantity() < 1) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        boolean used = false;
        boolean cc = false;
        switch (itemId) {
            case 5201001:
            case 5201002: {
                int gain = 0;
                switch (itemId) {
                    case 5201001: {
                        gain = 500;
                        break;
                    }
                    case 5201002: {
                        gain = 3000;
                        break;
                    }
                }
                c.getPlayer().setBeans(c.getPlayer().getBeans() + gain);
                c.getPlayer().dropMessage(1, "請更換頻道進入商城刷新小鋼珠數量。");
                used = true;
                break;
            }
            case 5042000: {
                if (GameConstants.isNotToMap(c.getPlayer().getMapId())) {
                    c.getPlayer().dropMessage(1, "當前地图無法使用。");
                    break;
                }
                final MapleMap map = c.getChannelServer().getMapFactory().getMap(701000200);
                c.getPlayer().changeMap(map, map.getPortal(0));
                used = true;
                break;
            }
            case 5042001: {
                if (GameConstants.isNotToMap(c.getPlayer().getMapId())) {
                    c.getPlayer().dropMessage(1, "當前地图無法使用。");
                    break;
                }
                final MapleMap map = c.getChannelServer().getMapFactory().getMap(741000000);
                c.getPlayer().changeMap(map, map.getPortal(0));
                used = true;
                break;
            }
            case 5043000:
            case 5043001: {
                if (c.getPlayer().getMapId() == 180000001) {
                    c.getPlayer().dropMessage(1, "當前地图無法使用。");
                    break;
                }
                final short questid = slea.readShort();
                final int npcid = slea.readInt();
                final MapleQuest quest = MapleQuest.getInstance((int)questid);
                if (c.getPlayer().getQuest(quest).getStatus() == 1 && quest.canComplete(c.getPlayer(), Integer.valueOf(npcid))) {
                    final int mapId = MapleLifeFactory.getNPCLocation(npcid);
                    if (mapId != -1) {
                        final MapleMap map2 = c.getChannelServer().getMapFactory().getMap(mapId);
                        if (map2.containsNPC(npcid) && !FieldLimitType.VipRock.check(c.getPlayer().getMap().getFieldLimit()) && !FieldLimitType.VipRock.check(map2.getFieldLimit()) && c.getPlayer().getEventInstance() == null) {
                            c.getPlayer().changeMap(map2, map2.getPortal(0));
                        }
                        used = true;
                    }
                    else {
                        c.getPlayer().dropMessage(1, "出現未知錯誤。");
                    }
                    break;
                }
                break;
            }
            case 5560000:
            case 5561000: {
                if (slea.readByte() == 0) {
                    final MapleMap target = c.getChannelServer().getMapFactory().getMap(slea.readInt());
                    if ((itemId == 5560000 || itemId == 5561000 || itemId != 5560000 || (itemId != 5561000 && c.getPlayer().isRegRockMap(target.getId()))) && !FieldLimitType.VipRock.check(c.getPlayer().getMap().getFieldLimit()) && !FieldLimitType.VipRock.check(target.getFieldLimit()) && c.getPlayer().getEventInstance() == null) {
                        c.getPlayer().changeMap(target, target.getPortal(0));
                        used = true;
                    }
                    break;
                }
                break;
            }
            case 2320000:
            case 5040000:
            case 5040001:
            case 5041000: {
                if (GameConstants.isNotToMap(c.getPlayer().getMapId())) {
                    c.getPlayer().dropMessage(1, "當前地图無法使用瞬移石。");
                    break;
                }
                used = UseTeleRock(slea, c, itemId);
                break;
            }
            case 5050000: {
                final Map<MapleStat, Integer> statupdate = new EnumMap<MapleStat, Integer>(MapleStat.class);
                final MapleStat apto = MapleStat.getByValue(slea.readInt());
                final MapleStat apfrom = MapleStat.getByValue(slea.readInt());
                final int job = c.getPlayer().getJob();
                final PlayerStats playerst = c.getPlayer().getStat();
                if (apto == apfrom) {
                    break;
                }
                used = true;
                switch (apto) {
                    case STR: {
                        if (playerst.getStr() >= 30000) {
                            used = false;
                            break;
                        }
                        break;
                    }
                    case DEX: {
                        if (playerst.getDex() >= 30000) {
                            used = false;
                            break;
                        }
                        break;
                    }
                    case INT: {
                        if (playerst.getInt() >= 30000) {
                            used = false;
                            break;
                        }
                        break;
                    }
                    case LUK: {
                        if (playerst.getLuk() >= 30000) {
                            used = false;
                            break;
                        }
                        break;
                    }
                    case MAXHP: {
                        if (playerst.getMaxHp() >= 30000 || c.getPlayer().getHpMpApUsed() >= 500) {
                            used = false;
                            break;
                        }
                        break;
                    }
                    case MAXMP: {
                        if (playerst.getMaxMp() >= 30000 || c.getPlayer().getHpMpApUsed() >= 500) {
                            used = false;
                            break;
                        }
                        break;
                    }
                }
                switch (apfrom) {
                    case STR: {
                        if (playerst.getStr() <= 4 || (c.getPlayer().getJob() % 1000 / 100 == 1 && playerst.getStr() <= 35)) {
                            used = false;
                            break;
                        }
                        break;
                    }
                    case DEX: {
                        if (playerst.getDex() <= 4 || (c.getPlayer().getJob() % 1000 / 100 == 3 && playerst.getDex() <= 25) || (c.getPlayer().getJob() % 1000 / 100 == 4 && playerst.getDex() <= 25) || (c.getPlayer().getJob() % 1000 / 100 == 5 && playerst.getDex() <= 20)) {
                            used = false;
                            break;
                        }
                        break;
                    }
                    case INT: {
                        if (playerst.getInt() <= 4 || (c.getPlayer().getJob() % 1000 / 100 == 2 && playerst.getInt() <= 20)) {
                            used = false;
                            break;
                        }
                        break;
                    }
                    case LUK: {
                        if (playerst.getLuk() <= 4) {
                            used = false;
                            break;
                        }
                        break;
                    }
                    case MAXHP: {
                        if (c.getPlayer().getHpMpApUsed() <= 0 || c.getPlayer().getHpMpApUsed() >= 500) {
                            used = false;
                            break;
                        }
                        break;
                    }
                    case MAXMP: {
                        if (c.getPlayer().getHpMpApUsed() <= 0 || c.getPlayer().getHpMpApUsed() >= 500) {
                            used = false;
                            break;
                        }
                        break;
                    }
                }
                if (used) {
                    switch (apto) {
                        case STR: {
                            final int toSet = playerst.getStr() + 1;
                            playerst.setStr((short)toSet);
                            statupdate.put(MapleStat.STR, Integer.valueOf(toSet));
                            break;
                        }
                        case DEX: {
                            final int toSet = playerst.getDex() + 1;
                            playerst.setDex((short)toSet);
                            statupdate.put(MapleStat.DEX, Integer.valueOf(toSet));
                            break;
                        }
                        case INT: {
                            final int toSet = playerst.getInt() + 1;
                            playerst.setInt((short)toSet);
                            statupdate.put(MapleStat.INT, Integer.valueOf(toSet));
                            break;
                        }
                        case LUK: {
                            final int toSet = playerst.getLuk() + 1;
                            playerst.setLuk((short)toSet);
                            statupdate.put(MapleStat.LUK, Integer.valueOf(toSet));
                            break;
                        }
                        case MAXHP: {
                            short maxhp = playerst.getMaxHp();
                            if (GameConstants.is新手职业(job)) {
                                maxhp += 8;
                            }
                            else if ((job >= 100 && job <= 132) || (job >= 1100 && job <= 1112) || (job >= 3200 && job <= 3212)) {
                                final ISkill improvingMaxHP = SkillFactory.getSkill(1000001);
                                final int improvingMaxHPLevel = c.getPlayer().getSkillLevel(improvingMaxHP);
                                maxhp += 20;
                                if (improvingMaxHPLevel >= 1) {
                                    maxhp += (short)improvingMaxHP.getEffect(improvingMaxHPLevel).getY();
                                }
                                final ISkill improvingMaxHP2 = SkillFactory.getSkill(11000000);
                                final int improvingMaxHPLevel2 = c.getPlayer().getSkillLevel(improvingMaxHP2);
                                if (improvingMaxHPLevel2 >= 1) {
                                    maxhp += (short)improvingMaxHP2.getEffect(improvingMaxHPLevel2).getY();
                                }
                            }
                            else if (job >= 2100 && job <= 2112) {
                                maxhp += 30;
                            }
                            else if ((job >= 200 && job <= 232) || (job >= 1200 && job <= 1212)) {
                                maxhp += 6;
                            }
                            else if ((job >= 300 && job <= 332) || (job >= 400 && job <= 434) || (job >= 1300 && job <= 1312) || (job >= 1400 && job <= 1412) || (job >= 3300 && job <= 3312)) {
                                maxhp += 16;
                            }
                            else if ((job >= 500 && job <= 522) || (job >= 1500 && job <= 1512) || (job >= 3500 && job <= 3512)) {
                                maxhp += 18;
                                final ISkill improvingMaxHP = SkillFactory.getSkill(5100000);
                                final int improvingMaxHPLevel = c.getPlayer().getSkillLevel(improvingMaxHP);
                                if (improvingMaxHPLevel >= 1) {
                                    maxhp += (short)improvingMaxHP.getEffect(improvingMaxHPLevel).getY();
                                }
                                final ISkill improvingMaxHP2 = SkillFactory.getSkill(15100000);
                                final int improvingMaxHPLevel2 = c.getPlayer().getSkillLevel(improvingMaxHP2);
                                if (improvingMaxHPLevel2 >= 1) {
                                    maxhp += (short)improvingMaxHP2.getEffect(improvingMaxHPLevel2).getY();
                                }
                            }
                            else if (job >= 2200 && job <= 2218) {
                                maxhp += 12;
                            }
                            else {
                                maxhp += 8;
                            }
                            maxhp = (short)Math.min(30000, Math.abs((int)maxhp));
                            c.getPlayer().setHpMpApUsed((short)(c.getPlayer().getHpMpApUsed() + 1));
                            playerst.setMaxHp(maxhp);
                            statupdate.put(MapleStat.MAXHP, Integer.valueOf((int)maxhp));
                            break;
                        }
                        case MAXMP: {
                            short maxmp = playerst.getMaxMp();
                            if (GameConstants.is新手职业(job)) {
                                maxmp += 6;
                            }
                            else if ((job >= 100 && job <= 132) || (job >= 1100 && job <= 1112) || (job >= 2100 && job <= 2112)) {
                                maxmp += 2;
                            }
                            else if ((job >= 200 && job <= 232) || (job >= 1200 && job <= 1212) || (job >= 2200 && job <= 2218) || (job >= 3200 && job <= 3212)) {
                                maxmp += 18;
                                final ISkill improvingMaxMP = SkillFactory.getSkill(2000001);
                                final int improvingMaxMPLevel = c.getPlayer().getSkillLevel(improvingMaxMP);
                                if (improvingMaxMPLevel >= 1) {
                                    maxmp += (short)(improvingMaxMP.getEffect(improvingMaxMPLevel).getY() * 2);
                                }
                                final ISkill improvingMaxMP2 = SkillFactory.getSkill(12000000);
                                final int improvingMaxMPLevel2 = c.getPlayer().getSkillLevel(improvingMaxMP2);
                                if (improvingMaxMPLevel2 >= 1) {
                                    maxmp += (short)(improvingMaxMP2.getEffect(improvingMaxMPLevel2).getY() * 2);
                                }
                            }
                            else if ((job >= 300 && job <= 332) || (job >= 400 && job <= 434) || (job >= 1300 && job <= 1312) || (job >= 1400 && job <= 1412) || (job >= 3200 && job <= 3212)) {
                                maxmp += 10;
                            }
                            else if ((job >= 500 && job <= 522) || (job >= 1500 && job <= 1512) || (job >= 3500 && job <= 3512)) {
                                maxmp += 14;
                            }
                            else {
                                if (job >= 3100 && job <= 3112) {
                                    break;
                                }
                                maxmp += 6;
                            }
                            maxmp = (short)Math.min(30000, Math.abs((int)maxmp));
                            c.getPlayer().setHpMpApUsed((short)(c.getPlayer().getHpMpApUsed() + 1));
                            playerst.setMaxMp(maxmp);
                            statupdate.put(MapleStat.MAXMP, Integer.valueOf((int)maxmp));
                            break;
                        }
                    }
                    switch (apfrom) {
                        case STR: {
                            final int toSet = playerst.getStr() - 1;
                            playerst.setStr((short)toSet);
                            statupdate.put(MapleStat.STR, Integer.valueOf(toSet));
                            break;
                        }
                        case DEX: {
                            final int toSet = playerst.getDex() - 1;
                            playerst.setDex((short)toSet);
                            statupdate.put(MapleStat.DEX, Integer.valueOf(toSet));
                            break;
                        }
                        case INT: {
                            final int toSet = playerst.getInt() - 1;
                            playerst.setInt((short)toSet);
                            statupdate.put(MapleStat.INT, Integer.valueOf(toSet));
                            break;
                        }
                        case LUK: {
                            final int toSet = playerst.getLuk() - 1;
                            playerst.setLuk((short)toSet);
                            statupdate.put(MapleStat.LUK, Integer.valueOf(toSet));
                            break;
                        }
                        case MAXHP: {
                            short maxhp = playerst.getMaxHp();
                            if (GameConstants.is新手职业(job)) {
                                maxhp -= 12;
                            }
                            else if ((job >= 100 && job <= 132) || (job >= 1100 && job <= 1112) || (job >= 2100 && job <= 2112)) {
                                maxhp -= 54;
                                final ISkill improvingMaxHP = SkillFactory.getSkill(1000001);
                                final int improvingMaxHPLevel = c.getPlayer().getSkillLevel(improvingMaxHP);
                                if (improvingMaxHPLevel >= 1) {
                                    maxhp -= (short)improvingMaxHP.getEffect(improvingMaxHPLevel).getY();
                                }
                                final ISkill improvingMaxHP2 = SkillFactory.getSkill(11000000);
                                final int improvingMaxHPLevel2 = c.getPlayer().getSkillLevel(improvingMaxHP2);
                                if (improvingMaxHPLevel2 >= 1) {
                                    maxhp -= (short)improvingMaxHP2.getEffect(improvingMaxHPLevel2).getY();
                                }
                            }
                            else if ((job >= 200 && job <= 232) || (job >= 1200 && job <= 1212)) {
                                maxhp -= 10;
                            }
                            else if ((job >= 300 && job <= 332) || (job >= 400 && job <= 434) || (job >= 1300 && job <= 1312) || (job >= 1400 && job <= 1412) || (job >= 3200 && job <= 3212)) {
                                maxhp -= 20;
                            }
                            else if ((job >= 500 && job <= 522) || (job >= 1500 && job <= 1512) || (job >= 3500 && job <= 3512)) {
                                maxhp -= 42;
                                final ISkill improvingMaxHP = SkillFactory.getSkill(5100000);
                                final int improvingMaxHPLevel = c.getPlayer().getSkillLevel(improvingMaxHP);
                                if (improvingMaxHPLevel > 0) {
                                    maxhp -= (short)improvingMaxHP.getEffect(improvingMaxHPLevel).getY();
                                }
                                final ISkill improvingMaxHP2 = SkillFactory.getSkill(15100000);
                                final int improvingMaxHPLevel2 = c.getPlayer().getSkillLevel(improvingMaxHP2);
                                if (improvingMaxHPLevel2 > 0) {
                                    maxhp -= (short)improvingMaxHP2.getEffect(improvingMaxHPLevel2).getY();
                                }
                            }
                            else if (job >= 2200 && job <= 2218) {
                                maxhp -= 16;
                            }
                            else {
                                maxhp -= 12;
                            }
                            c.getPlayer().setHpMpApUsed((short)(c.getPlayer().getHpMpApUsed() - 1));
                            if (playerst.getHp() > playerst.getMaxHp()) {
                                playerst.setHp((int)maxhp);
                            }
                            playerst.setMaxHp(maxhp);
                            statupdate.put(MapleStat.MAXHP, Integer.valueOf((int)maxhp));
                            statupdate.put(MapleStat.HP, Integer.valueOf((int)playerst.getHp()));
                            break;
                        }
                        case MAXMP: {
                            short maxmp = playerst.getMaxMp();
                            if (GameConstants.is新手职业(job)) {
                                maxmp -= 8;
                            }
                            else if ((job >= 100 && job <= 132) || (job >= 1100 && job <= 1112) || (job >= 2100 && job <= 2112)) {
                                maxmp -= 4;
                            }
                            else if ((job >= 200 && job <= 232) || (job >= 1200 && job <= 1212) || (job >= 2200 && job <= 2218) || (job >= 3200 && job <= 3212)) {
                                maxmp -= 30;
                                final ISkill improvingMaxMP = SkillFactory.getSkill(2000001);
                                final int improvingMaxMPLevel = c.getPlayer().getSkillLevel(improvingMaxMP);
                                if (improvingMaxMPLevel >= 1) {
                                    maxmp -= (short)improvingMaxMP.getEffect(improvingMaxMPLevel).getY();
                                }
                                final ISkill improvingMaxMP2 = SkillFactory.getSkill(12000000);
                                final int improvingMaxMPLevel2 = c.getPlayer().getSkillLevel(improvingMaxMP2);
                                if (improvingMaxMPLevel2 >= 1) {
                                    maxmp -= (short)improvingMaxMP2.getEffect(improvingMaxMPLevel2).getY();
                                }
                            }
                            else if ((job >= 300 && job <= 332) || (job >= 400 && job <= 434) || (job >= 1300 && job <= 1312) || (job >= 1400 && job <= 1412) || (job >= 3200 && job <= 3212)) {
                                maxmp -= 12;
                            }
                            else if ((job >= 500 && job <= 522) || (job >= 1500 && job <= 1512) || (job >= 3500 && job <= 3512)) {
                                maxmp -= 16;
                            }
                            else {
                                if (job >= 3100 && job <= 3112) {
                                    break;
                                }
                                maxmp -= 8;
                            }
                            c.getPlayer().setHpMpApUsed((short)(c.getPlayer().getHpMpApUsed() - 1));
                            if (playerst.getMp() > playerst.getMaxMp()) {
                                playerst.setMp((int)maxmp);
                            }
                            playerst.setMaxMp(maxmp);
                            statupdate.put(MapleStat.MAXMP, Integer.valueOf((int)maxmp));
                            statupdate.put(MapleStat.MP, Integer.valueOf((int)playerst.getMp()));
                            break;
                        }
                    }
                    c.sendPacket(MaplePacketCreator.updatePlayerStats(statupdate, true, c.getPlayer()));
                    break;
                }
                break;
            }
            case 5050001:
            case 5050002:
            case 5050003:
            case 5050004: {
                final int skill1 = slea.readInt();
                final int skill2 = slea.readInt();
                final ISkill skillSPTo = SkillFactory.getSkill(skill1);
                final ISkill skillSPFrom = SkillFactory.getSkill(skill2);
                if (skillSPTo.isBeginnerSkill()) {
                    break;
                }
                if (skillSPFrom.isBeginnerSkill()) {
                    break;
                }
                if (c.getPlayer().getSkillLevel(skillSPTo) + 1 <= skillSPTo.getMaxLevel() && c.getPlayer().getSkillLevel(skillSPFrom) > 0) {
                    c.getPlayer().changeSkillLevel(skillSPFrom, (byte)(c.getPlayer().getSkillLevel(skillSPFrom) - 1), c.getPlayer().getMasterLevel(skillSPFrom));
                    c.getPlayer().changeSkillLevel(skillSPTo, (byte)(c.getPlayer().getSkillLevel(skillSPTo) + 1), c.getPlayer().getMasterLevel(skillSPTo));
                    used = true;
                    break;
                }
                break;
            }
            case 5060000: {
                final IItem item = c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem((short)slea.readByte());
                if (item != null && item.getOwner().equals((Object)"")) {
                    boolean change = true;
                    for (final String z : GameConstants.RESERVED) {
                        if (c.getPlayer().getName().indexOf(z) != -1 || item.getOwner().indexOf(z) != -1) {
                            change = false;
                        }
                    }
                    if (change) {
                        item.setOwner(c.getPlayer().getName());
                        c.getPlayer().forceReAddItem_Flag(item, MapleInventoryType.EQUIPPED);
                        used = true;
                    }
                    break;
                }
                break;
            }
            case 5520000:
            case 5520001: {
                final MapleInventoryType type = MapleInventoryType.getByType((byte)slea.readInt());
                final IItem item2 = c.getPlayer().getInventory(type).getItem((short)(byte)slea.readInt());
                if (item2 != null && !ItemFlag.KARMA_EQ.check((int)item2.getFlag()) && !ItemFlag.KARMA_USE.check((int)item2.getFlag()) && ((itemId == 5520000 && MapleItemInformationProvider.getInstance().isKarmaEnabled(item2.getItemId())) || (itemId == 5520000 && MapleItemInformationProvider.getInstance().isPKarmaEnabled(item2.getItemId())))) {
                    byte flag = item2.getFlag();
                    if (type == MapleInventoryType.EQUIP) {
                        if (MapleItemInformationProvider.getInstance().isUntradeableOnEquip(item2.getItemId()) && flag != 0) {
                            flag = 0;
                        }
                        else {
                            flag |= (byte)ItemFlag.KARMA_EQ.getValue();
                        }
                    }
                    else {
                        flag |= (byte)ItemFlag.KARMA_USE.getValue();
                    }
                    item2.setFlag(flag);
                    c.getPlayer().forceReAddItem_Flag(item2, type);
                    used = true;
                    break;
                }
                break;
            }
            case 5570000: {
                slea.readInt();
                final Equip item3 = (Equip)c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short)(byte)slea.readInt());
                if (item3 == null) {
                    break;
                }
                if (GameConstants.canHammer(item3.getItemId()) && MapleItemInformationProvider.getInstance().getSlots(item3.getItemId()) > 0 && item3.getViciousHammer() <= 2) {
                    item3.setViciousHammer((byte)(item3.getViciousHammer() + 1));
                    item3.setUpgradeSlots((byte)(item3.getUpgradeSlots() + 1));
                    c.getPlayer().forceReAddItem((IItem)item3, MapleInventoryType.EQUIP);
                    used = true;
                    cc = true;
                    break;
                }
                c.getPlayer().dropMessage(5, "可能不能使用在這個道具上。");
                cc = true;
                break;
            }
            case 5610000:
            case 5610001: {
                slea.readInt();
                final byte dst = (byte)slea.readInt();
                slea.readInt();
                final byte src = (byte)slea.readInt();
                used = (cc = UseUpgradeScroll(src, dst, (byte)2, c, c.getPlayer(), itemId));
                break;
            }
            case 5060001: {
                final MapleInventoryType type = MapleInventoryType.getByType((byte)slea.readInt());
                final IItem item2 = c.getPlayer().getInventory(type).getItem((short)(byte)slea.readInt());
                if (item2 != null && item2.getExpiration() == -1L) {
                    byte flag = item2.getFlag();
                    flag |= (byte)ItemFlag.LOCK.getValue();
                    item2.setFlag(flag);
                    c.getPlayer().forceReAddItem_Flag(item2, type);
                    used = true;
                    break;
                }
                break;
            }
            case 5061000: {
                final MapleInventoryType type = MapleInventoryType.getByType((byte)slea.readInt());
                final IItem item2 = c.getPlayer().getInventory(type).getItem((short)(byte)slea.readInt());
                if (item2 != null && item2.getExpiration() == -1L) {
                    byte flag = item2.getFlag();
                    flag |= (byte)ItemFlag.LOCK.getValue();
                    item2.setFlag(flag);
                    item2.setExpiration(System.currentTimeMillis() + 604800000L);
                    c.getPlayer().forceReAddItem_Flag(item2, type);
                    used = true;
                    break;
                }
                break;
            }
            case 5061001: {
                final MapleInventoryType type = MapleInventoryType.getByType((byte)slea.readInt());
                final IItem item2 = c.getPlayer().getInventory(type).getItem((short)(byte)slea.readInt());
                if (item2 != null && item2.getExpiration() == -1L) {
                    byte flag = item2.getFlag();
                    flag |= (byte)ItemFlag.LOCK.getValue();
                    item2.setFlag(flag);
                    item2.setExpiration(System.currentTimeMillis() - 1702967296L);
                    c.getPlayer().forceReAddItem_Flag(item2, type);
                    used = true;
                    break;
                }
                break;
            }
            case 5061002: {
                final MapleInventoryType type = MapleInventoryType.getByType((byte)slea.readInt());
                final IItem item2 = c.getPlayer().getInventory(type).getItem((short)(byte)slea.readInt());
                if (item2 != null && item2.getExpiration() == -1L) {
                    byte flag = item2.getFlag();
                    flag |= (byte)ItemFlag.LOCK.getValue();
                    item2.setFlag(flag);
                    item2.setExpiration(System.currentTimeMillis() - 813934592L);
                    c.getPlayer().forceReAddItem_Flag(item2, type);
                    used = true;
                    break;
                }
                break;
            }
            case 5060003: {
                final IItem item = c.getPlayer().getInventory(MapleInventoryType.ETC).findById(4170023);
                if (item == null || item.getQuantity() <= 0) {
                    return;
                }
                if (getIncubatedItems(c)) {
                    MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.ETC, item.getPosition(), (short)1, false);
                    used = true;
                }
                break;
            }
            case 5070000: {
                final int 游戏喇叭 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"游戏喇叭开关"));
                if (游戏喇叭 > 0) {
                    c.getPlayer().dropMessage(1, "目前喇叭功能从后台关闭了，无法广播喇叭");
                    break;
                }
                if (c.getPlayer().getLevel() < 10) {
                    c.getPlayer().dropMessage(5, "必須等級10級以上才可以使用.");
                    break;
                }
                if (!c.getPlayer().getCheatTracker().canAvatarSmega2()) {
                    c.getPlayer().dropMessage(6, "很抱歉為了防止刷廣,所以你每10秒只能用一次.");
                    break;
                }
                if ((!c.getPlayer().getCanTalk() || c.getChannelServer().getMegaphoneMuteState()) && !c.getPlayer().isGM()) {
                    c.getPlayer().dropMessage(5, "目前喇叭停止使用.");
                    break;
                }
                final String message = slea.readMapleAsciiString();
                if (message.length() > 65) {
                    break;
                }
                final StringBuilder sb = new StringBuilder();
                addMedalString(c.getPlayer(), sb);
                sb.append(c.getPlayer().getName());
                sb.append(" : ");
                sb.append(message);
                final boolean ear = slea.readByte() != 0;
                if (c.getPlayer().isPlayer()) {
                    c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.serverNotice(2, sb.toString()));
                }
                else if (c.getPlayer().isGM()) {
                    c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.serverNotice(2, sb.toString()));
                }
                if (ServerConfig.LOG_MEGA) {
                    FileoutputUtil.logToFile("logs/聊天/廣播頻道.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 玩家『" + c.getPlayer().getName() + "』頻道『" + c.getChannel() + "』廣播道具『" + itemId + "』：" + message);
                }
                used = true;
                break;
            }
            case 5071000: {
                final int 游戏喇叭 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"游戏喇叭开关"));
                if (游戏喇叭 > 0) {
                    c.getPlayer().dropMessage(1, "目前喇叭功能从后台关闭了，无法广播喇叭");
                    break;
                }
                if (c.getPlayer().getLevel() < 10) {
                    c.getPlayer().dropMessage(5, "必須等級10級以上才可以使用.");
                    break;
                }
                if (!c.getPlayer().getCheatTracker().canAvatarSmega2()) {
                    c.getPlayer().dropMessage(6, "很抱歉為了防止刷廣,所以你每10秒只能用一次.");
                    break;
                }
                if ((!c.getPlayer().getCanTalk() || c.getChannelServer().getMegaphoneMuteState()) && !c.getPlayer().isGM()) {
                    c.getPlayer().dropMessage(5, "目前喇叭停止使用.");
                    break;
                }
                final String message = slea.readMapleAsciiString();
                if (message.length() > 65) {
                    break;
                }
                final boolean ear2 = slea.readByte() != 0;
                final StringBuilder sb2 = new StringBuilder();
                addMedalString(c.getPlayer(), sb2);
                sb2.append(c.getPlayer().getName());
                sb2.append(" : ");
                sb2.append(message);
                if (c.getPlayer().isPlayer()) {
                    c.getChannelServer().broadcastSmega(MaplePacketCreator.serverNotice(2, sb2.toString()));
                }
                else if (c.getPlayer().isGM()) {
                    c.getChannelServer().broadcastSmega(MaplePacketCreator.serverNotice(2, sb2.toString()));
                }
                if (ServerConfig.LOG_MEGA) {
                    FileoutputUtil.logToFile("logs/聊天/廣播頻道.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 玩家『" + c.getPlayer().getName() + "』頻道『" + c.getChannel() + "』廣播道具『" + itemId + "』：" + message);
                }
                used = true;
                break;
            }
            case 5077000: {
                final int 游戏喇叭 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"游戏喇叭开关"));
                if (游戏喇叭 > 0) {
                    c.getPlayer().dropMessage(1, "目前喇叭功能从后台关闭了，无法广播喇叭");
                    break;
                }
                if (c.getPlayer().getLevel() < 10) {
                    c.getPlayer().dropMessage(5, "必須等級10級以上才可以使用.");
                    break;
                }
                if (!c.getPlayer().getCheatTracker().canAvatarSmega2()) {
                    c.getPlayer().dropMessage(6, "很抱歉為了防止刷廣,所以你每10秒只能用一次.");
                    break;
                }
                if ((!c.getPlayer().getCanTalk() || c.getChannelServer().getMegaphoneMuteState()) && !c.getPlayer().isGM()) {
                    c.getPlayer().dropMessage(5, "目前喇叭停止使用.");
                    break;
                }
                final byte numLines = slea.readByte();
                if (numLines > 3) {
                    return;
                }
                final StringBuilder sb = new StringBuilder();
                addMedalString(c.getPlayer(), sb);
                final List<String> messages = new LinkedList<String>();
                for (int i = 0; i < numLines; ++i) {
                    final String message2 = slea.readMapleAsciiString();
                    messages.add((Object)sb + c.getPlayer().getName() + " : " + message2);
                }
                final boolean ear3 = slea.readByte() > 0;
                if (c.getPlayer().isPlayer()) {
                    Broadcast.broadcastSmega(MaplePacketCreator.tripleSmega(messages, ear3, c.getChannel()));
                }
                else if (c.getPlayer().isGM()) {
                    Broadcast.broadcastSmega(MaplePacketCreator.tripleSmega(messages, ear3, c.getChannel()));
                }
                if (ServerConfig.LOG_MEGA) {
                    FileoutputUtil.logToFile("logs/聊天/廣播頻道.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 玩家『" + c.getPlayer().getName() + "』頻道『" + c.getChannel() + "』廣播道具『" + itemId + "』：" + (Object)messages);
                }
                used = true;
                break;
            }
            case 5072000: {
                final int 游戏喇叭 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"游戏喇叭开关"));
                if (游戏喇叭 > 0) {
                    c.getPlayer().dropMessage(1, "目前喇叭功能从后台关闭了，无法广播喇叭");
                    break;
                }
                if (c.getPlayer().getLevel() < 10) {
                    c.getPlayer().dropMessage(5, "必須要10等以上才能使用.");
                    break;
                }
                if (!c.getPlayer().getCheatTracker().canAvatarSmega2()) {
                    c.getPlayer().dropMessage(6, "很抱歉為了防止刷廣,所以你每10秒只能用一次.");
                    break;
                }
                if ((!c.getPlayer().getCanTalk() || c.getChannelServer().getMegaphoneMuteState()) && !c.getPlayer().isGM()) {
                    c.getPlayer().dropMessage(5, "目前喇叭停止使用.");
                    break;
                }
                final String message = slea.readMapleAsciiString();
                if (message.length() > 65) {
                    break;
                }
                final StringBuilder sb = new StringBuilder();
                addMedalString(c.getPlayer(), sb);
                sb.append(c.getPlayer().getName());
                sb.append(" : ");
                sb.append(message);
                final boolean ear = slea.readByte() != 0;
                if (c.getPlayer().isPlayer()) {
                    Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(3, c.getChannel(), sb.toString(), ear));
                }
                else if (c.getPlayer().isGM()) {
                    Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(3, c.getChannel(), sb.toString(), ear));
                }
                if (ServerConfig.LOG_MEGA) {
                    FileoutputUtil.logToFile("logs/聊天/廣播頻道.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 玩家『" + c.getPlayer().getName() + "』頻道『" + c.getChannel() + "』廣播道具『" + itemId + "』：" + message);
                }
                used = true;
                break;
            }
            case 5073000: {
                final int 游戏喇叭 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"游戏喇叭开关"));
                if (游戏喇叭 > 0) {
                    c.getPlayer().dropMessage(1, "目前喇叭功能从后台关闭了，无法广播喇叭");
                    break;
                }
                if (c.getPlayer().getLevel() < 10) {
                    c.getPlayer().dropMessage(5, "必須等級10級以上才可以使用.");
                    break;
                }
                if (!c.getPlayer().getCheatTracker().canAvatarSmega2()) {
                    c.getPlayer().dropMessage(6, "很抱歉為了防止刷廣,所以你每10秒只能用一次.");
                    break;
                }
                if ((!c.getPlayer().getCanTalk() || c.getChannelServer().getMegaphoneMuteState()) && !c.getPlayer().isGM()) {
                    c.getPlayer().dropMessage(5, "目前喇叭停止使用.");
                    break;
                }
                final String message = slea.readMapleAsciiString();
                if (message.length() > 65) {
                    break;
                }
                final StringBuilder sb = new StringBuilder();
                addMedalString(c.getPlayer(), sb);
                final List<String> messages = new LinkedList<String>();
                messages.add((Object)sb + c.getPlayer().getName() + " : " + message);
                final boolean ear4 = slea.readByte() != 0;
                if (c.getPlayer().isPlayer()) {
                    Broadcast.broadcastSmega(MaplePacketCreator.HeartSmega(messages, ear4, c.getChannel()));
                }
                else if (c.getPlayer().isGM()) {
                    Broadcast.broadcastSmega(MaplePacketCreator.HeartSmega(messages, ear4, c.getChannel()));
                }
                if (ServerConfig.LOG_MEGA) {
                    FileoutputUtil.logToFile("logs/聊天/廣播頻道.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 玩家『" + c.getPlayer().getName() + "』頻道『" + c.getChannel() + "』廣播道具『" + itemId + "』：" + message);
                }
                used = true;
                break;
            }
            case 5074000: {
                final int 游戏喇叭 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"游戏喇叭开关"));
                if (游戏喇叭 > 0) {
                    c.getPlayer().dropMessage(1, "目前喇叭功能从后台关闭了，无法广播喇叭");
                    break;
                }
                if (c.getPlayer().getLevel() < 10) {
                    c.getPlayer().dropMessage(5, "必須等級10級以上才可以使用.");
                    break;
                }
                if (!c.getPlayer().getCheatTracker().canAvatarSmega2()) {
                    c.getPlayer().dropMessage(6, "很抱歉為了防止刷廣,所以你每10秒只能用一次.");
                    break;
                }
                if ((!c.getPlayer().getCanTalk() || c.getChannelServer().getMegaphoneMuteState()) && !c.getPlayer().isGM()) {
                    c.getPlayer().dropMessage(5, "目前喇叭停止使用.");
                    break;
                }
                final String message = slea.readMapleAsciiString();
                if (message.length() > 65) {
                    break;
                }
                final StringBuilder sb = new StringBuilder();
                addMedalString(c.getPlayer(), sb);
                final List<String> messages = new LinkedList<String>();
                messages.add((Object)sb + c.getPlayer().getName() + " : " + message);
                final boolean ear4 = slea.readByte() != 0;
                if (c.getPlayer().isPlayer()) {
                    Broadcast.broadcastSmega(MaplePacketCreator.SkullSmega(messages, ear4, c.getChannel()));
                }
                else if (c.getPlayer().isGM()) {
                    Broadcast.broadcastSmega(MaplePacketCreator.SkullSmega(messages, ear4, c.getChannel()));
                }
                if (ServerConfig.LOG_MEGA) {
                    FileoutputUtil.logToFile("logs/聊天/廣播頻道.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 玩家『" + c.getPlayer().getName() + "』頻道『" + c.getChannel() + "』廣播道具『" + itemId + "』：" + message);
                }
                used = true;
                break;
            }
            case 5075000:
            case 5075001:
            case 5075002: {
                c.getPlayer().dropMessage(5, "There are no MapleTVs to broadcast the message to.");
                break;
            }
            case 5075003:
            case 5075004:
            case 5075005: {
                if (c.getPlayer().getLevel() < 10) {
                    c.getPlayer().dropMessage(5, "必須等級10級以上才可以使用.");
                    break;
                }
                final int tvType = itemId % 10;
                if (tvType == 3) {
                    slea.readByte();
                }
                final boolean ear5 = tvType != 1 && tvType != 2 && slea.readByte() > 1;
                final String victimName = slea.readMapleAsciiString();
                MapleCharacter victim = (tvType == 1 || tvType == 4) ? null : c.getChannelServer().getPlayerStorage().getCharacterByName(victimName);
                if (tvType == 0 || tvType == 3) {
                    victim = null;
                }
                else if (victim == null) {
                    c.getPlayer().dropMessage(1, "找不到" + victimName);
                    break;
                }
                final String message2 = slea.readMapleAsciiString();
                Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(3, c.getChannel(), c.getPlayer().getName() + " : " + message2, ear5));
                break;
            }
            case 5076000: {
                final int 游戏喇叭 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"游戏喇叭开关"));
                if (游戏喇叭 > 0) {
                    c.getPlayer().dropMessage(1, "目前喇叭功能从后台关闭了，无法广播喇叭");
                    break;
                }
                if (c.getPlayer().getLevel() < 10) {
                    c.getPlayer().dropMessage(5, "必須等級10級以上才可以使用.");
                    break;
                }
                if (!c.getPlayer().getCheatTracker().canAvatarSmega2()) {
                    c.getPlayer().dropMessage(6, "很抱歉為了防止刷廣,所以你每10秒只能用一次.");
                    break;
                }
                if ((!c.getPlayer().getCanTalk() || c.getChannelServer().getMegaphoneMuteState()) && !c.getPlayer().isGM()) {
                    c.getPlayer().dropMessage(5, "目前喇叭停止使用.");
                    break;
                }
                final String message = slea.readMapleAsciiString();
                if (message.length() > 65) {
                    break;
                }
                final StringBuilder sb = new StringBuilder();
                addMedalString(c.getPlayer(), sb);
                sb.append(c.getPlayer().getName());
                sb.append(" : ");
                sb.append(message);
                final boolean ear = slea.readByte() > 0;
                IItem item4 = null;
                if (slea.readByte() == 1) {
                    final byte invType = (byte)slea.readInt();
                    final byte pos = (byte)slea.readInt();
                    item4 = c.getPlayer().getInventory(MapleInventoryType.getByType(invType)).getItem((short)pos);
                }
                if (c.getPlayer().isPlayer()) {
                    Broadcast.broadcastSmega(MaplePacketCreator.itemMegaphone(sb.toString(), ear, c.getChannel(), item4));
                }
                else if (c.getPlayer().isGM()) {
                    Broadcast.broadcastSmega(MaplePacketCreator.itemMegaphone(sb.toString(), ear, c.getChannel(), item4));
                }
                if (ServerConfig.LOG_MEGA) {
                    FileoutputUtil.logToFile("logs/聊天/廣播頻道.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 玩家『" + c.getPlayer().getName() + "』頻道『" + c.getChannel() + "』廣播道具『" + itemId + "』：" + message);
                }
                used = true;
                break;
            }
            case 5090000:
            case 5090100: {
                final String sendTo = slea.readMapleAsciiString();
                final String msg = slea.readMapleAsciiString();
                c.getPlayer().sendNote(sendTo, msg);
                used = true;
                break;
            }
            case 5100000: {
                c.getPlayer().getMap().broadcastMessage(MTSCSPacket.playCashSong(5100000, c.getPlayer().getName()));
                used = true;
                break;
            }
            case 5152100:
            case 5152101:
            case 5152102:
            case 5152103:
            case 5152104:
            case 5152105:
            case 5152106:
            case 5152107: {
                if (itemId < 5152100 || itemId > 5152107) {
                    break;
                }
                if (c.getPlayer().getLevel() < 30) {
                    c.getPlayer().dropMessage(1, "必須等級30級以上才可以使用.");
                    break;
                }
                final int color = (itemId - 5152100) * 100;
                if (color >= 0) {
                    if (changeFace(c.getPlayer(), color)) {
                        used = true;
                    }
                    else {
                        c.getPlayer().dropMessage(1, "使用日拋隱形眼鏡出現錯誤。");
                    }
                }
                else {
                    c.getPlayer().dropMessage(1, "使用日拋隱形眼鏡出現錯誤。");
                }
                break;
            }
            case 5170000: {
                final MaplePet pet = c.getPlayer().getPet(0);
                final int slo = 0;
                if (pet == null) {
                    break;
                }
                if (c.getPlayer().getSummonedPets().size() != 1) {
                    c.getPlayer().dropMessage(1, "只有开启一隻寵物的時候才能使用。");
                    used = false;
                    break;
                }
                final String nName = slea.readMapleAsciiString();
                pet.setName(nName);
                c.sendPacket(PetPacket.updatePet(pet, c.getPlayer().getInventory(MapleInventoryType.CASH).getItem((short)(byte)pet.getInventoryPosition())));
                c.sendPacket(MaplePacketCreator.enableActions());
                c.getPlayer().getMap().broadcastMessage(MTSCSPacket.changePetName(c.getPlayer(), nName, slo));
                used = true;
                break;
            }
            case 5190000:
            case 5190001:
            case 5190002:
            case 5190003:
            case 5190004:
            case 5190005:
            case 5190006:
            case 5190007:
            case 5190008:
            case 5191000:
            case 5191001:
            case 5191002:
            case 5191003:
            case 5191004: {
                final boolean isAdd = itemId / 1000 % 10 == 0;
                final int uniqueid = (int)slea.readLong();
                MaplePet pet2 = c.getPlayer().getPet(0);
                int slo2 = 0;
                if (pet2 == null) {
                    break;
                }
                if (pet2.getUniqueId() != uniqueid) {
                    pet2 = c.getPlayer().getPet(1);
                    slo2 = 1;
                    if (pet2 == null) {
                        break;
                    }
                    if (pet2.getUniqueId() != uniqueid) {
                        pet2 = c.getPlayer().getPet(2);
                        slo2 = 2;
                        if (pet2 == null) {
                            break;
                        }
                        if (pet2.getUniqueId() != uniqueid) {
                            break;
                        }
                    }
                }
                final PetFlag zz = PetFlag.getByAddId(itemId);
                if (zz != null && !zz.check((int)pet2.getFlags())) {
                    if (isAdd) {
                        pet2.setFlags(pet2.getFlags() | zz.getValue());
                    }
                    else {
                        pet2.setFlags(pet2.getFlags() - zz.getValue());
                    }
                    c.sendPacket(PetPacket.updatePet(pet2, c.getPlayer().getInventory(MapleInventoryType.CASH).getItem((short)(byte)pet2.getInventoryPosition())));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    c.sendPacket(MTSCSPacket.changePetFlag(uniqueid, true, zz.getValue()));
                    used = true;
                    break;
                }
                break;
            }
            case 5230000: {
                final int itemSearch = slea.readInt();
                final List<HiredMerchant> hms = c.getChannelServer().searchMerchant(itemSearch);
                if (hms.size() > 0) {
                    c.sendPacket(MaplePacketCreator.getOwlSearched(itemSearch, hms));
                    used = true;
                    break;
                }
                c.getPlayer().dropMessage(1, "找不到物品.");
                break;
            }
            case 5240000:
            case 5240001:
            case 5240002:
            case 5240003:
            case 5240004:
            case 5240005:
            case 5240006:
            case 5240007:
            case 5240008:
            case 5240009:
            case 5240010:
            case 5240011:
            case 5240012:
            case 5240013:
            case 5240014:
            case 5240015:
            case 5240016:
            case 5240017:
            case 5240018:
            case 5240019:
            case 5240020:
            case 5240021:
            case 5240022:
            case 5240023:
            case 5240024:
            case 5240025:
            case 5240026:
            case 5240027:
            case 5240028: {
                MaplePet pet = c.getPlayer().getPet(0);
                if (pet == null) {
                    break;
                }
                if (!pet.canConsume(itemId)) {
                    pet = c.getPlayer().getPet(1);
                    if (pet == null) {
                        break;
                    }
                    if (!pet.canConsume(itemId)) {
                        pet = c.getPlayer().getPet(2);
                        if (pet == null) {
                            break;
                        }
                        if (!pet.canConsume(itemId)) {
                            break;
                        }
                    }
                }
                final byte petindex = c.getPlayer().getPetIndex(pet);
                pet.setFullness(100);
                if (pet.getCloseness() < 30000) {
                    if (pet.getCloseness() + 100 > 30000) {
                        pet.setCloseness(30000);
                    }
                    else {
                        pet.setCloseness(pet.getCloseness() + 100);
                    }
                    if (pet.getCloseness() >= GameConstants.getClosenessNeededForLevel(pet.getLevel() + 1)) {
                        pet.setLevel(pet.getLevel() + 1);
                        c.sendPacket(PetPacket.showOwnPetLevelUp(c.getPlayer().getPetIndex(pet)));
                        c.getPlayer().getMap().broadcastMessage(PetPacket.showPetLevelUp(c.getPlayer(), petindex));
                    }
                }
                c.sendPacket(PetPacket.updatePet(pet, c.getPlayer().getInventory(MapleInventoryType.CASH).getItem(pet.getInventoryPosition())));
                c.getPlayer().getMap().broadcastMessage(c.getPlayer(), PetPacket.commandResponse(c.getPlayer().getId(), (byte)1, petindex, true, true), true);
                used = true;
                break;
            }
            case 5280001:
            case 5281000:
            case 5281001: {
                final Rectangle bounds = new Rectangle((int)c.getPlayer().getPosition().getX(), (int)c.getPlayer().getPosition().getY(), 1, 1);
                final MapleMist mist = new MapleMist(bounds, c.getPlayer());
                c.getPlayer().getMap().spawnMist(mist, 10000, true);
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.getChatText(c.getPlayer().getId(), "哦，不，我放屁!", false, 1));
                c.sendPacket(MaplePacketCreator.enableActions());
                used = true;
                break;
            }
            case 5320000: {
                final String name = slea.readMapleAsciiString();
                final String otherName = slea.readMapleAsciiString();
                final long unk = (long)slea.readInt();
                final long unk_2 = (long)slea.readInt();
                final int cardId = slea.readByte();
                final short unk_3 = slea.readShort();
                final byte unk_4 = slea.readByte();
                final int comm = Randomizer.rand(1, 6);
                final PredictCardFactory pcf = PredictCardFactory.getInstance();
                final PredictCard Card = pcf.getPredictCard(cardId);
                final PredictCardComment Comment = pcf.getPredictCardComment(comm);
                final MapleCharacter victim2 = MapleCharacter.getOnlineCharacterByName(otherName);
                if (Card == null) {
                    break;
                }
                if (Comment == null) {
                    break;
                }
                if (!name.equals((Object)c.getPlayer().getName())) {
                    c.getPlayer().dropMessage(1, "我的角色名字請填自己。");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (victim2 == null) {
                    c.getPlayer().dropMessage(1, "對方必須上線。");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (c.getPlayer().getGMLevel() < victim2.getGMLevel()) {
                    c.getPlayer().dropMessage(1, "無法對此玩家使用。");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                final int love = Randomizer.rand(1, Comment.score) + 5;
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.startMapEffect("", 0, false));
                c.sendPacket(MTSCSPacket.showPredictCard(name, otherName, love, cardId, Comment.effectType));
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.startMapEffect(name + "和" + otherName + "的速配程度 " + Comment.worldmsg0, 5120026, true));
                used = true;
                break;
            }
            case 5370000: {
                if (c.getPlayer().getMapId() / 1000000 == 109) {
                    c.getPlayer().dropMessage(1, "请勿在活动地图使用黑板");
                    break;
                }
                c.getPlayer().setChalkboard(slea.readMapleAsciiString());
                break;
            }
            case 5370001: {
                if (c.getPlayer().getMapId() / 1000000 == 910) {
                    c.getPlayer().setChalkboard(slea.readMapleAsciiString());
                    break;
                }
                break;
            }
            case 5390000:
            case 5390001:
            case 5390002:
            case 5390003:
            case 5390004:
            case 5390005:
            case 5390006: {
                final int 游戏喇叭 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"游戏喇叭开关"));
                if (游戏喇叭 > 0) {
                    c.getPlayer().dropMessage(1, "目前喇叭功能从后台关闭了，无法广播喇叭");
                    break;
                }
                if (c.getPlayer().getLevel() < 10) {
                    c.getPlayer().dropMessage(5, "必須等級10級以上才可以使用.");
                    break;
                }
                if (!c.getPlayer().getCheatTracker().canAvatarSmega2()) {
                    c.getPlayer().dropMessage(6, "很抱歉為了防止刷廣,所以你每10秒只能用一次.");
                    break;
                }
                if ((!c.getPlayer().getCanTalk() || c.getChannelServer().getMegaphoneMuteState()) && !c.getPlayer().isGM()) {
                    c.getPlayer().dropMessage(5, "目前喇叭停止使用.");
                    break;
                }
                final String text = slea.readMapleAsciiString();
                final StringBuilder sb = new StringBuilder();
                addMedalString(c.getPlayer(), sb);
                sb.append(c.getPlayer().getName());
                sb.append(" : ");
                sb.append(text);
                if (text.length() > 55) {
                    break;
                }
                final boolean ear = slea.readByte() != 0;
                if (c.getPlayer().isPlayer()) {
                    Broadcast.broadcastSmega(MaplePacketCreator.getAvatarMega(c.getPlayer(), c.getChannel(), itemId, text, ear));
                }
                else if (c.getPlayer().isGM()) {
                    Broadcast.broadcastSmega(MaplePacketCreator.getAvatarMega(c.getPlayer(), c.getChannel(), itemId, text, ear));
                }
                if (ServerConfig.LOG_MEGA) {
                    FileoutputUtil.logToFile("logs/聊天/廣播頻道.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 玩家『" + c.getPlayer().getName() + "』頻道『" + c.getChannel() + "』廣播道具『" + itemId + "』：" + text);
                }
                used = true;
                break;
            }
            case 5450000: {
                for (final int j : GameConstants.blockedMaps) {
                    if (c.getPlayer().getMapId() == j) {
                        c.getPlayer().dropMessage(5, "你不能在這張地图裡使用，如果卡住請使用 @ea 來解卡。");
                        c.sendPacket(MaplePacketCreator.enableActions());
                        return;
                    }
                }
                if (c.getPlayer().getLevel() < 10) {
                    c.getPlayer().dropMessage(5, "你還沒有10等以上");
                    break;
                }
                if (c.getPlayer().getMap().getSquadByMap() != null || c.getPlayer().getEventInstance() != null || c.getPlayer().getMap().getEMByMap() != null || c.getPlayer().getMapId() >= 990000000) {
                    c.getPlayer().dropMessage(5, "你不能在這張地图裡使用，如果卡住請使用 @ea 來解卡。");
                    break;
                }
                if ((c.getPlayer().getMapId() >= 680000210 && c.getPlayer().getMapId() <= 680000502) || (c.getPlayer().getMapId() / 1000 == 980000 && c.getPlayer().getMapId() != 980000000) || c.getPlayer().getMapId() / 100 == 1030008 || c.getPlayer().getMapId() / 100 == 922010 || c.getPlayer().getMapId() / 10 == 13003000) {
                    c.getPlayer().dropMessage(5, "你不能在這張地图裡使用，如果卡住請使用 @ea 來解卡。");
                    break;
                }
                MapleShopFactory.getInstance().getShop(57).sendShop(c);
                break;
            }
            default: {
                if (itemId / 10000 == 512) {
                    final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                    String msg = ii.getMsg(itemId);
                    final String ourMsg = slea.readMapleAsciiString();
                    if (!msg.contains((CharSequence)"%s")) {
                        msg = ourMsg;
                    }
                    else {
                        msg = msg.replaceFirst("%s", ourMsg);
                        if (!msg.contains((CharSequence)"%s")) {
                            msg = ii.getMsg(itemId).replaceFirst("%s", ourMsg);
                        }
                        else {
                            try {
                                msg = msg.replaceFirst("%s", ourMsg);
                            }
                            catch (Exception e) {
                                msg = ii.getMsg(itemId).replaceFirst("%s", ourMsg);
                            }
                        }
                    }
                    c.getPlayer().getMap().startMapEffect(ourMsg, itemId);
                    final int buff = ii.getStateChangeItem(itemId);
                    if (buff != 0) {
                        for (final MapleCharacter mChar : c.getPlayer().getMap().getCharactersThreadsafe()) {
                            ii.getItemEffect(buff).applyTo(mChar);
                        }
                    }
                    used = true;
                    break;
                }
                if (itemId / 10000 == 508) {
                    final MapleKite Kite = new MapleKite(c.getPlayer(), c.getPlayer().getPosition(), c.getPlayer().getMap().getFootholds().findBelow(c.getPlayer().getPosition()).getId(), slea.readMapleAsciiString(), itemId);
                    c.getPlayer().getMap().spawnKite(Kite);
                    used = true;
                    break;
                }
                if (itemId / 10000 == 510) {
                    c.getPlayer().getMap().startJukebox(c.getPlayer().getName(), itemId);
                    used = true;
                    break;
                }
                if (itemId / 10000 == 520) {
                    final int mesars = MapleItemInformationProvider.getInstance().getMeso(itemId);
                    if (mesars > 0 && c.getPlayer().getMeso() < Integer.MAX_VALUE - mesars) {
                        used = true;
                        if (Math.random() > 0.1) {
                            final int gainmes = Randomizer.nextInt(mesars);
                            c.getPlayer().gainMeso(gainmes, false);
                            c.sendPacket(MTSCSPacket.sendMesobagSuccess(gainmes));
                        }
                        else {
                            c.sendPacket(MTSCSPacket.sendMesobagFailed());
                        }
                    }
                    break;
                }
                if (itemId / 10000 == 553) {
                    UseRewardItem(slot, itemId, c, c.getPlayer());
                    break;
                }
                System.out.println("未處理的商城道具ID : " + itemId);
                System.out.println(slea.toString(true));
                break;
            }
        }
        if (used) {
            MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.CASH, (short)slot, (short)1, false, true);
        }
        c.sendPacket(MaplePacketCreator.enableActions());
        if (cc) {
            if (!c.getPlayer().isAlive() || c.getPlayer().getEventInstance() != null || FieldLimitType.ChannelSwitch.check(c.getPlayer().getMap().getFieldLimit())) {
                c.getPlayer().dropMessage(1, "Auto change channel failed.");
                return;
            }
            c.getPlayer().dropMessage(5, "Auto changing channels. Please wait.");
            c.getPlayer().changeChannel((c.getChannel() == ChannelServer.getChannelCount()) ? 1 : (c.getChannel() + 1));
        }
    }
    
    public static final void PlayerPickup(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (World.isShutDown) {
            c.getPlayer().dropMessage(1, "目前無法撿物品。");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (chr.getTrade() != null) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (chr.hasBlockedInventory(true)) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        chr.updateTick(slea.readInt());
        slea.skip(1);
        final Point Client_Reportedpos = slea.readPos();
        final int oid = slea.readInt();
        final MapleMapObject ob = chr.getMap().getMapObject(oid, MapleMapObjectType.ITEM);
        if (ob == null) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        final MapleMapItem mapitem = (MapleMapItem)ob;
        final Lock lock = mapitem.getLock();
        lock.lock();
        try {
            if (mapitem.isPickedUp()) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            if (mapitem.getOwner() != chr.getId() && ((!mapitem.isPlayerDrop() && mapitem.getDropType() == 0) || (mapitem.isPlayerDrop() && chr.getMap().getEverlast()))) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            if (!mapitem.isPlayerDrop() && mapitem.getDropType() == 1 && mapitem.getOwner() != chr.getId() && (chr.getParty() == null || chr.getParty().getMemberById(mapitem.getOwner()) == null)) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            final double Distance = Client_Reportedpos.distanceSq((Point2D)mapitem.getPosition());
            if (Distance > 2500.0) {
                chr.getCheatTracker().registerOffense(CheatingOffense.ITEMVAC_CLIENT, String.valueOf(Distance));
            }
            else if (chr.getPosition().distanceSq((Point2D)mapitem.getPosition()) > 640000.0) {
                chr.getCheatTracker().registerOffense(CheatingOffense.ITEMVAC_SERVER, " 範圍: " + chr.getPosition().distanceSq((Point2D)mapitem.getPosition()));
            }
            if (mapitem.getMeso() > 0) {
                final MapleMapObject obd = chr.getMap().getMapObject(oid, MapleMapObjectType.ITEM);
                if (obd == null) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (chr.getParty() != null && mapitem.getOwner() != chr.getId()) {
                    final List<MapleCharacter> toGive = new LinkedList<MapleCharacter>();
                    for (final MaplePartyCharacter z : chr.getParty().getMembers()) {
                        final MapleCharacter m = chr.getMap().getCharacterById(z.getId());
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
                removeItem(chr, mapitem, ob);
            }
            else if (MapleItemInformationProvider.getInstance().isPickupBlocked(mapitem.getItem().getItemId())) {
                c.sendPacket(MaplePacketCreator.enableActions());
                c.getPlayer().dropMessage(5, "此物品無法被撿起.");
            }
            else if (useItem(c, mapitem.getItemId())) {
                final MapleMapObject obd = chr.getMap().getMapObject(oid, MapleMapObjectType.ITEM);
                if (obd == null) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                removeItem(c.getPlayer(), mapitem, ob);
            }
            else if (MapleInventoryManipulator.checkSpace(c, mapitem.getItem().getItemId(), (int)mapitem.getItem().getQuantity(), mapitem.getItem().getOwner())) {
                final MapleMapObject obd = chr.getMap().getMapObject(oid, MapleMapObjectType.ITEM);
                if (obd == null) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (mapitem.getItem().getQuantity() >= 50 && GameConstants.isUpgradeScroll(mapitem.getItem().getItemId())) {
                    c.setMonitored(true);
                }
                if (MapleInventoryManipulator.addFromDrop(c, mapitem.getItem(), true, mapitem.getDropper() instanceof MapleMonster, false)) {
                    removeItem(chr, mapitem, ob);
                }
            }
            else {
                c.sendPacket(MaplePacketCreator.getInventoryFull());
                c.sendPacket(MaplePacketCreator.getShowInventoryFull());
                c.sendPacket(MaplePacketCreator.enableActions());
            }
        }
        catch (Exception ex) {
            FileoutputUtil.outError("logs/撿取道具異常.txt", (Throwable)ex);
        }
        finally {
            lock.unlock();
        }
    }
    
    public static final void PetPickup(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (chr == null) {
            return;
        }
        if (chr.getTrade() != null) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        final byte petz = c.getPlayer().getPetIndex((int)slea.readLong());
        final MaplePet pet = chr.getPet((int)petz);
        slea.skip(1);
        chr.updateTick(slea.readInt());
        final Point Client_Reportedpos = slea.readPos();
        final int oid = slea.readInt();
        final MapleMapObject ob = chr.getMap().getMapObject(oid, MapleMapObjectType.ITEM);
        if (ob == null || pet == null) {
            return;
        }
        final MapleMapItem mapitem = (MapleMapItem)ob;
        final Lock lock = mapitem.getLock();
        lock.lock();
        try {
            if (mapitem.isPickedUp()) {
                c.sendPacket(MaplePacketCreator.getInventoryFull());
                return;
            }
            if (mapitem.getOwner() != chr.getId() && mapitem.isPlayerDrop()) {
                return;
            }
            if (mapitem.getOwner() != chr.getId() && ((!mapitem.isPlayerDrop() && mapitem.getDropType() == 0) || (mapitem.isPlayerDrop() && chr.getMap().getEverlast()))) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            if (!mapitem.isPlayerDrop() && mapitem.getDropType() == 1 && mapitem.getOwner() != chr.getId() && (chr.getParty() == null || chr.getParty().getMemberById(mapitem.getOwner()) == null)) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            if (mapitem.isPlayerDrop() && mapitem.getDropType() == 2 && mapitem.getOwner() == chr.getId()) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            final double count = pet.getPos().distanceSq((Point2D)mapitem.getPosition());
            final double range = 700000.0;
            if (count > range) {}
            if (mapitem.getMeso() > 0) {
                final MapleMapObject obd = chr.getMap().getMapObject(oid, MapleMapObjectType.ITEM);
                if (obd == null) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (chr.getParty() != null && mapitem.getOwner() != chr.getId()) {
                    final List<MapleCharacter> toGive = new LinkedList<MapleCharacter>();
                    final int splitMeso = mapitem.getMeso() * 40 / 100;
                    for (final MaplePartyCharacter z : chr.getParty().getMembers()) {
                        final MapleCharacter m = chr.getMap().getCharacterById(z.getId());
                        if (m != null && m.getId() != chr.getId()) {
                            toGive.add(m);
                        }
                    }
                    for (final MapleCharacter i : toGive) {
                        i.gainMeso(splitMeso / toGive.size() + (i.getStat().hasPartyBonus ? ((int)((double)mapitem.getMeso() / 20.0)) : 0), true);
                    }
                    chr.gainMeso(mapitem.getMeso() - splitMeso, true);
                }
                else {
                    chr.gainMeso(mapitem.getMeso(), true);
                }
                removeItemPet(chr, mapitem, (int)petz);
            }
            else if (MapleItemInformationProvider.getInstance().isPickupBlocked(mapitem.getItemId()) || mapitem.getItemId() / 10000 == 291) {
                c.sendPacket(MaplePacketCreator.enableActions());
            }
            else if (useItem(c, mapitem.getItemId())) {
                final MapleMapObject obd = chr.getMap().getMapObject(oid, MapleMapObjectType.ITEM);
                if (obd == null) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                removeItemPet(chr, mapitem, (int)petz);
            }
            else if (MapleInventoryManipulator.checkSpace(c, mapitem.getItemId(), (int)mapitem.getItem().getQuantity(), mapitem.getItem().getOwner())) {
                final MapleMapObject obd = chr.getMap().getMapObject(oid, MapleMapObjectType.ITEM);
                if (obd == null) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (mapitem.getItem().getQuantity() >= 50 && mapitem.getItemId() == 2340000) {
                    c.setMonitored(true);
                }
                removeItemPet(chr, mapitem, (int)petz);
                MapleInventoryManipulator.addFromDrop(c, mapitem.getItem(), true, mapitem.getDropper() instanceof MapleMonster, true);
            }
        }
        finally {
            lock.unlock();
        }
    }
    
    public static final boolean useItem(final MapleClient c, final int id) {
        if (GameConstants.isUse(id)) {
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final byte consumeval = ii.isConsumeOnPickup(id);
            if (consumeval > 0) {
                if (consumeval == 2) {
                    if (c.getPlayer().getParty() != null) {
                        for (final MaplePartyCharacter pc : c.getPlayer().getParty().getMembers()) {
                            final MapleCharacter chr = c.getPlayer().getMap().getCharacterById(pc.getId());
                            if (chr != null) {
                                ii.getItemEffect(id).applyTo(chr);
                            }
                        }
                    }
                    else {
                        ii.getItemEffect(id).applyTo(c.getPlayer());
                    }
                }
                else {
                    ii.getItemEffect(id).applyTo(c.getPlayer());
                }
                c.sendPacket(MaplePacketCreator.getShowItemGain(id, (short)1));
                return true;
            }
        }
        return false;
    }
    
    public static final void removeItemPet(final MapleCharacter chr, final MapleMapItem mapitem, final int pet) {
        mapitem.setPickedUp(true);
        chr.getMap().broadcastMessage(MaplePacketCreator.removeItemFromMap(mapitem.getObjectId(), 5, chr.getId(), pet), mapitem.getPosition());
        chr.getMap().removeMapObject((MapleMapObject)mapitem);
        if (mapitem.isRandDrop()) {
            chr.getMap().spawnRandDrop();
        }
    }
    
    public static void removeItem(final MapleCharacter chr, final MapleMapItem mapitem, final MapleMapObject ob) {
        mapitem.setPickedUp(true);
        chr.getMap().broadcastMessage(MaplePacketCreator.removeItemFromMap(mapitem.getObjectId(), 2, chr.getId()), mapitem.getPosition());
        chr.getMap().removeMapObject(ob);
        if (mapitem.isRandDrop()) {
            chr.getMap().spawnRandDrop();
        }
    }
    
    private static void addMedalString(final MapleCharacter c, final StringBuilder sb) {
        final IItem medal = c.getInventory(MapleInventoryType.EQUIPPED).getItem((short)(-21));
        if (medal != null) {
            sb.append("<");
            sb.append(MapleItemInformationProvider.getInstance().getName(medal.getItemId()));
            sb.append("> ");
        }
    }
    
    private static boolean getIncubatedItems(final MapleClient c) {
        if (c.getPlayer().getInventory(MapleInventoryType.EQUIP).getNumFreeSlot() < 2 || c.getPlayer().getInventory(MapleInventoryType.USE).getNumFreeSlot() < 2 || c.getPlayer().getInventory(MapleInventoryType.SETUP).getNumFreeSlot() < 2) {
            c.getPlayer().dropMessage(5, "Please make room in your inventory.");
            return false;
        }
        int[] ids;
        int[] chances;
        int z;
        for (ids = new int[] { 2430091, 2430092, 2430093, 2430101, 2430102, 2340000, 1152000, 1152001, 1152004, 1152005, 1152006, 1152007, 1152008, 1000040, 1102246, 1082276, 1050169, 1051210, 1072447, 1442106, 3010019, 1001060, 1002391, 1102004, 1050039, 1102040, 1102041, 1102042, 1102043, 1082145, 1082146, 1082147, 1082148, 1082149, 1082150, 2043704, 2040904, 2040409, 2040307, 2041030, 2040015, 2040109, 2041035, 2041036, 2040009, 2040511, 2040408, 2043804, 2044105, 2044903, 2044804, 2043009, 2043305, 2040610, 2040716, 2041037, 2043005, 2041032, 2040305, 2040211, 2040212, 1022097, 2049000, 2049001, 2049002, 2049003, 1012058, 1012059, 1012060, 1012061, 1332100, 1382058, 1402073, 1432066, 1442090, 1452058, 1462076, 1472069, 1482051, 1492024, 1342009, 2049400, 2049401, 2049301 }, chances = new int[] { 100, 100, 100, 100, 100, 1, 10, 10, 10, 10, 10, 10, 10, 5, 5, 5, 5, 5, 5, 5, 2, 10, 10, 10, 10, 10, 10, 10, 10, 5, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 5, 5, 10, 10, 10, 10, 10, 5, 5, 5, 5, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2 }, z = Randomizer.nextInt(ids.length); chances[z] < Randomizer.nextInt(1000); z = Randomizer.nextInt(ids.length)) {}
        int z_2;
        for (z_2 = Randomizer.nextInt(ids.length); z_2 == z || chances[z_2] < Randomizer.nextInt(1000); z_2 = Randomizer.nextInt(ids.length)) {}
        c.sendPacket(MaplePacketCreator.getPeanutResult(ids[z], (short)1, ids[z_2], (short)1));
        return MapleInventoryManipulator.addById(c, ids[z], (short)1) && MapleInventoryManipulator.addById(c, ids[z_2], (short)1);
    }
    
    public static final void OwlMinerva(final LittleEndianAccessor slea, final MapleClient c) {
        final byte slot = (byte)slea.readShort();
        final int itemid = slea.readInt();
        final IItem toUse = c.getPlayer().getInventory(MapleInventoryType.USE).getItem((short)slot);
        if (toUse != null && toUse.getQuantity() > 0 && toUse.getItemId() == itemid && itemid == 2310000) {
            final int itemSearch = slea.readInt();
            final List<HiredMerchant> hms = c.getChannelServer().searchMerchant(itemSearch);
            if (hms.size() > 0) {
                c.sendPacket(MaplePacketCreator.getOwlSearched(itemSearch, hms));
                MapleInventoryManipulator.removeById(c, MapleInventoryType.USE, itemid, 1, true, false);
            }
            else {
                c.getPlayer().dropMessage(1, "找不到該物品");
            }
        }
        c.sendPacket(MaplePacketCreator.enableActions());
    }
    
    public static final void Owl(final LittleEndianAccessor slea, final MapleClient c) {
        if (c.getPlayer().haveItem(5230000, 1, true, false) || c.getPlayer().haveItem(2310000, 1, true, false)) {
            if (c.getPlayer().getMapId() >= 910000000 && c.getPlayer().getMapId() <= 910000022) {
                c.sendPacket(MaplePacketCreator.getOwlOpen());
            }
            else {
                c.getPlayer().dropMessage(5, "貓頭鷹只能在自由市場內使用");
                c.sendPacket(MaplePacketCreator.enableActions());
            }
        }
    }
    
    public static final void UseSkillBook(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        slea.skip(4);
        final byte slot = (byte)slea.readShort();
        final int itemId = slea.readInt();
        final IItem toUse = chr.getInventory(MapleInventoryType.USE).getItem((short)slot);
        if (toUse == null || toUse.getQuantity() < 1 || toUse.getItemId() != itemId) {
            return;
        }
        final Map<String, Integer> skilldata = MapleItemInformationProvider.getInstance().getSkillStats(toUse.getItemId());
        if (skilldata == null) {
            return;
        }
        boolean canuse = false;
        boolean success = false;
        final int skill = 0;
        final int maxlevel = 0;
        final int SuccessRate = (int)Integer.valueOf(skilldata.get((Object)"success"));
        final int ReqSkillLevel = (int)Integer.valueOf(skilldata.get((Object)"reqSkillLevel"));
        final int MasterLevel = (int)Integer.valueOf(skilldata.get((Object)"masterLevel"));
        byte i = 0;
        while (true) {
            final Integer CurrentLoopedSkillId = skilldata.get((Object)("skillid" + (int)i));
            ++i;
            if (CurrentLoopedSkillId == null) {
                break;
            }
            if (Math.floor((double)((int)CurrentLoopedSkillId / 10000)) != (double)chr.getJob()) {
                continue;
            }
            final ISkill CurrSkillData = SkillFactory.getSkill((int)CurrentLoopedSkillId);
            if (chr.getSkillLevel(CurrSkillData) >= ReqSkillLevel && chr.getMasterLevel(CurrSkillData) < MasterLevel) {
                canuse = true;
                if (Randomizer.nextInt(99) <= SuccessRate && SuccessRate != 0) {
                    success = true;
                    final ISkill skill2 = CurrSkillData;
                    chr.changeSkillLevel(skill2, chr.getSkillLevel(skill2), (byte)MasterLevel);
                }
                else {
                    success = false;
                }
                MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, (short)slot, (short)1, false);
                break;
            }
            canuse = false;
        }
        c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.useSkillBook(chr, skill, maxlevel, canuse, success));
    }
    
    public static final void OwlWarp(final LittleEndianAccessor slea, final MapleClient c) {
        c.sendPacket(MaplePacketCreator.enableActions());
        if (c.getPlayer().getMapId() >= 910000000 && c.getPlayer().getMapId() <= 910000022 && c.getPlayer().getPlayerShop() == null) {
            final int id = slea.readInt();
            final int map = slea.readInt();
            if (map >= 910000001 && map <= 910000022) {
                final MapleMap mapp = c.getChannelServer().getMapFactory().getMap(map);
                c.getPlayer().changeMap(mapp, mapp.getPortal(0));
                HiredMerchant merchant = null;
                switch (2) {
                    case 0: {
                        final List<MapleMapObject> objects = mapp.getAllHiredMerchantsThreadsafe();
                        for (final MapleMapObject ob : objects) {
                            if (ob instanceof IMaplePlayerShop) {
                                final IMaplePlayerShop ips = (IMaplePlayerShop)ob;
                                if (!(ips instanceof HiredMerchant)) {
                                    continue;
                                }
                                final HiredMerchant merch = (HiredMerchant)ips;
                                if (merch.getOwnerId() == id) {
                                    merchant = merch;
                                    break;
                                }
                                continue;
                            }
                        }
                        break;
                    }
                    case 1: {
                        final List<MapleMapObject> objects = mapp.getAllHiredMerchantsThreadsafe();
                        for (final MapleMapObject ob : objects) {
                            if (ob instanceof IMaplePlayerShop) {
                                final IMaplePlayerShop ips = (IMaplePlayerShop)ob;
                                if (!(ips instanceof HiredMerchant)) {
                                    continue;
                                }
                                final HiredMerchant merch = (HiredMerchant)ips;
                                if (merch.getStoreId() == id) {
                                    merchant = merch;
                                    break;
                                }
                                continue;
                            }
                        }
                        break;
                    }
                    default: {
                        final MapleMapObject ob2 = mapp.getMapObject(id, MapleMapObjectType.HIRED_MERCHANT);
                        if (!(ob2 instanceof IMaplePlayerShop)) {
                            break;
                        }
                        final IMaplePlayerShop ips2 = (IMaplePlayerShop)ob2;
                        if (ips2 instanceof HiredMerchant) {
                            merchant = (HiredMerchant)ips2;
                            break;
                        }
                        break;
                    }
                }
                if (merchant != null) {
                    if (merchant.isOwner(c.getPlayer())) {
                        merchant.setOpen(false);
                        merchant.removeAllVisitors(18, 1);
                        c.getPlayer().setPlayerShop((IMaplePlayerShop)merchant);
                        c.sendPacket(PlayerShopPacket.getHiredMerch(c.getPlayer(), merchant, false));
                    }
                    else if (!merchant.isOpen() || !merchant.isAvailable()) {
                        c.getPlayer().dropMessage(1, "這個商店在整理或者是沒在販賣東西。");
                    }
                    else if (merchant.getFreeSlot() == -1) {
                        c.getPlayer().dropMessage(1, "商店人數已經滿了，請稍後再進入。");
                    }
                    else if (merchant.isInBlackList(c.getPlayer().getName())) {
                        c.getPlayer().dropMessage(1, "被加入黑名單了，所以不能進入。");
                    }
                    else {
                        c.getPlayer().setPlayerShop((IMaplePlayerShop)merchant);
                        merchant.addVisitor(c.getPlayer());
                        c.sendPacket(PlayerShopPacket.getHiredMerch(c.getPlayer(), merchant, false));
                    }
                }
                else {
                    c.getPlayer().dropMessage(1, "商店正在整理中，");
                }
            }
        }
    }
    
    public static final boolean UseTeleRock(final LittleEndianAccessor slea, final MapleClient c, final int itemId) {
        boolean used = false;
        if (itemId == 5041001 || itemId == 5040004) {
            slea.readByte();
        }
        if (slea.readByte() == 0) {
            final MapleMap target = c.getChannelServer().getMapFactory().getMap(slea.readInt());
            if (((itemId == 5041000 && c.getPlayer().isRockMap(target.getId())) || (itemId != 5041000 && c.getPlayer().isRegRockMap(target.getId())) || itemId == 5040004 || itemId == 5041001) && !FieldLimitType.VipRock.check(c.getPlayer().getMap().getFieldLimit()) && !FieldLimitType.VipRock.check(target.getFieldLimit()) && c.getPlayer().getEventInstance() == null) {
                c.getPlayer().changeMap(target, target.getPortal(0));
                used = true;
            }
        }
        else {
            final MapleCharacter victim = c.getChannelServer().getPlayerStorage().getCharacterByName(slea.readMapleAsciiString());
            if (victim != null && !victim.isGM() && c.getPlayer().getEventInstance() == null && victim.getEventInstance() == null && !FieldLimitType.VipRock.check(c.getPlayer().getMap().getFieldLimit()) && !FieldLimitType.VipRock.check(c.getChannelServer().getMapFactory().getMap(victim.getMapId()).getFieldLimit()) && (itemId == 5041000 || itemId == 5040004 || itemId == 5041001 || victim.getMapId() / 100000000 == c.getPlayer().getMapId() / 100000000)) {
                c.getPlayer().changeMap(victim.getMap(), victim.getMap().findClosestSpawnpoint(victim.getPosition()));
                used = true;
            }
        }
        return used && itemId != 5041001 && itemId != 5040004;
    }
    
    private static boolean changeFace(final MapleCharacter player, int color) {
        if (player.getFace() % 1000 < 100) {
            color += player.getFace();
        }
        else if (player.getFace() % 1000 >= 100 && player.getFace() % 1000 < 200) {
            color += player.getFace() - 100;
        }
        else if (player.getFace() % 1000 >= 200 && player.getFace() % 1000 < 300) {
            color += player.getFace() - 200;
        }
        else if (player.getFace() % 1000 >= 300 && player.getFace() % 1000 < 400) {
            color += player.getFace() - 300;
        }
        else if (player.getFace() % 1000 >= 400 && player.getFace() % 1000 < 500) {
            color += player.getFace() - 400;
        }
        else if (player.getFace() % 1000 >= 500 && player.getFace() % 1000 < 600) {
            color += player.getFace() - 500;
        }
        else if (player.getFace() % 1000 >= 600 && player.getFace() % 1000 < 700) {
            color += player.getFace() - 600;
        }
        else if (player.getFace() % 1000 >= 700 && player.getFace() % 1000 < 800) {
            color += player.getFace() - 700;
        }
        player.setFace(color);
        player.updateSingleStat(MapleStat.FACE, player.getFace());
        player.equipChanged();
        return true;
    }
    
    public static void SunziBF(final LittleEndianAccessor slea, final MapleClient c) {
        slea.readInt();
        final byte slot = (byte)slea.readShort();
        final int itemid = slea.readInt();
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final IItem item = c.getPlayer().getInventory(MapleInventoryType.USE).getItem((short)slot);
        if (item == null || item.getItemId() != itemid || c.getPlayer().getLevel() > 255) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        final int expGained = ii.getExpCache(itemid) * c.getChannelServer().getExpRate();
        c.getPlayer().gainExp(expGained, true, false, false);
        c.sendPacket(MaplePacketCreator.enableActions());
        MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, (short)slot, (short)1, false);
    }
}
