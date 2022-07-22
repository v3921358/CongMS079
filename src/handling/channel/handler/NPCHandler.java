package handling.channel.handler;

import client.MapleQuestStatus;
import tools.Pair;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import client.inventory.Equip;
import tools.ArrayMap;
import scripting.NPCConversationManager;
import client.inventory.IItem;
import server.MapleStorage;
import client.inventory.ItemFlag;
import server.AutobanManager;
import server.MapleItemInformationProvider;
import handling.world.World.Broadcast;
import tools.FileoutputUtil;
import server.MapleInventoryManipulator;
import client.inventory.MapleInventoryType;
import server.quest.MapleQuest;
import scripting.NPCScriptManager;
import tools.MaplePacketCreator;
import server.MapleShop;
import client.MapleCharacter;
import constants.GameConstants;
import server.life.MapleNPC;
import server.maps.MapleMap;
import handling.SendPacketOpcode;
import tools.data.MaplePacketLittleEndianWriter;
import server.maps.MapleMapObject;
import client.MapleClient;
import tools.data.LittleEndianAccessor;

public class NPCHandler
{
    public static final void handleNPCAnimation(final LittleEndianAccessor slea, final MapleClient c) {
        final int length = (int)slea.available();
        if (length < 4) {
            return;
        }
        final MapleMap map = c.getPlayer().getMap();
        if (map == null) {
            return;
        }
        final int oid = slea.readInt();
        final MapleNPC npc = map.getNPCByOid(oid);
        if (npc == null) {
            if (c.getPlayer().isAdmin()) {
                c.getPlayer().dropMessage("NPC OID =" + oid);
            }
            return;
        }
        switch (npc.getId()) {
            default: {
                if (!c.getPlayer().isMapObjectVisible((MapleMapObject)npc)) {
                    return;
                }
                final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
                mplew.writeShort((int)SendPacketOpcode.NPC_ACTION.getValue());
                mplew.writeInt(oid);
                if (length == 6) {
                    mplew.writeShort((int)slea.readShort());
                }
                else {
                    if (length <= 9) {
                        if (c.getPlayer().isAdmin()) {
                            c.getPlayer().dropMessage("NPC, Packet:" + slea.toString());
                        }
                        return;
                    }
                    mplew.write(slea.read(length - 13));
                }
                c.sendPacket(mplew.getPacket());
            }
        }
    }
    
    public static final void handleNPCShop(final LittleEndianAccessor slea, final MapleClient c) {
        final MapleCharacter player = c.getPlayer();
        final byte bmode = slea.readByte();
        if (player == null) {
            return;
        }
        switch (bmode) {
            case 0: {
                final MapleShop shop = player.getShop();
                if (shop == null) {
                    return;
                }
                slea.skip(2);
                final int itemId = slea.readInt();
                final short quantity = slea.readShort();
                shop.buy(c, itemId, quantity);
                break;
            }
            case 1: {
                final MapleShop shop = player.getShop();
                if (shop == null) {
                    return;
                }
                final byte slot = (byte)slea.readShort();
                final int itemId2 = slea.readInt();
                final short quantity2 = slea.readShort();
                shop.sell(c, GameConstants.getInventoryType(itemId2), slot, quantity2);
                break;
            }
            case 2: {
                final MapleShop shop = player.getShop();
                if (shop == null) {
                    return;
                }
                final byte slot = (byte)slea.readShort();
                shop.recharge(c, slot);
                break;
            }
            default: {
                player.setConversation(0);
                break;
            }
        }
    }
    
    public static final void handleNPCTalk(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (c == null || chr == null || chr.getMap() == null) {
            return;
        }
        final MapleNPC npc = chr.getMap().getNPCByOid(slea.readInt());
        if (npc == null) {
            return;
        }
        if (chr.getConversation() != 0) {
            chr.dropMessage(5, "你现在不能攻击或不能跟npc对话,请在对话框打 @解卡 來解除异常状态");
            return;
        }
        if (chr.getAntiMacro().inProgress()) {
            chr.dropMessage(5, "被使用測謊儀時無法操作。");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (npc.hasShop()) {
            chr.setConversation(1);
            npc.sendShop(c);
        }
        else {
            NPCScriptManager.getInstance().start(c, npc.getId());
        }
    }
    
    public static final void QuestAction(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        final byte action = slea.readByte();
        final int quest = slea.readUShort();
        if (chr == null) {
            return;
        }
        if (chr.getAntiMacro().inProgress()) {
            chr.dropMessage(5, "被使用測謊儀時無法操作。");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        final MapleQuest q = MapleQuest.getInstance(quest);
        switch (action) {
            case 0: {
                chr.updateTick(slea.readInt());
                final int itemid = slea.readInt();
                MapleQuest.getInstance(quest).RestoreLostItem(chr, itemid);
                break;
            }
            case 1: {
                final int npc = slea.readInt();
                if (quest == 8663) {
                    if (!chr.canHold()) {
                        chr.dropMessage(5, "您的背包空格不足。");
                        return;
                    }
                    if (chr.getAcLogS("Q8663") < 1) {
                        chr.setAcLog("Q8663");
                        chr.modifyCSPoints(2, 520, true);
                        chr.gainItem(1112004);
                    }
                    else {
                        chr.dropMessage(5, "您已領取過該任務的獎勵。");
                    }
                }
                if (npc == 0 && quest > 0) {
                    q.forceStart(chr, npc, null);
                }
                else if (quest == 2001 || quest == 8511 || quest == 21301 || quest == 21302 || quest == 3083) {
                    q.forceStart(chr, npc, null);
                }
                else if (quest == 8512) {
                    q.start(chr, npc);
                }
                else if (!q.hasStartScript()) {
                    q.start(chr, npc);
                }
                if (c.getPlayer().isAdmin()) {
                    chr.dropMessage(6, "开始系统任务 NPC: " + npc + " Quest：" + quest);
                    break;
                }
                break;
            }
            case 2: {
                final int npc = slea.readInt();
                chr.updateTick(slea.readInt());
                if (slea.available() >= 4L) {
                    q.complete(chr, npc, Integer.valueOf(slea.readInt()));
                }
                else {
                    q.complete(chr, npc);
                }
                if (c.getPlayer().isAdmin()) {
                    chr.dropMessage(6, "完成系统任务 NPC: " + npc + " Quest: " + quest);
                    break;
                }
                break;
            }
            case 3: {
                if (!GameConstants.canForfeit(q.getId())) {
                    chr.dropMessage(1, "你不能放弃这个任务。");
                    break;
                }
                q.forfeit(chr);
                if (c.getPlayer().isAdmin()) {
                    chr.dropMessage(6, "放弃系统任务 Quest: " + quest);
                    break;
                }
                break;
            }
            case 4: {
                final int npc = slea.readInt();
                if (c.getPlayer().isAdmin()) {
                    chr.dropMessage(6, "执行腳本任务 NPC：" + npc + " Quest: " + quest);
                }
                NPCScriptManager.getInstance().startQuest(c, npc, quest);
                break;
            }
            case 5: {
                final int npc = slea.readInt();
                NPCScriptManager.getInstance().endQuest(c, npc, quest, false);
                c.sendPacket(MaplePacketCreator.showSpecialEffect(10));
                chr.getMap().broadcastMessage(chr, MaplePacketCreator.showSpecialEffect(chr.getId(), 10), false);
                if (c.getPlayer().isAdmin()) {
                    chr.dropMessage(6, "完成腳本任务 NPC：" + npc + " Quest: " + quest);
                    break;
                }
                break;
            }
        }
    }
    
    public static final void Storage(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        final byte mode = slea.readByte();
        if (chr == null) {
            return;
        }
        if (chr.getAntiMacro().inProgress()) {
            chr.dropMessage(5, "被使用測謊儀時無法操作。");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (chr.hasBlockedInventory2(true)) {
            c.getPlayer().dropMessage(1, "系统错误，请联系管理员。");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        final MapleStorage storage = chr.getStorage();
        switch (mode) {
            case 4: {
                final byte type = slea.readByte();
                final byte slot = storage.getSlot(MapleInventoryType.getByType(type), slea.readByte());
                final IItem item = storage.takeOut(slot);
                if (!c.getPlayer().CanStorage()) {
                    c.getPlayer().dropMessage(1, "操作过快请稍后在尝试。");
                    storage.store(item);
                    break;
                }
                if (item == null) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    break;
                }
                if (!MapleInventoryManipulator.checkSpace(c, item.getItemId(), (int)item.getQuantity(), item.getOwner())) {
                    storage.store(item);
                    chr.saveToDB(false, false);
                    chr.dropMessage(1, "你的物品栏已经满了..");
                    break;
                }
                if (chr.getMapId() == 910000000 && chr.getMeso() < 1000) {
                    storage.store(item);
                    chr.saveToDB(false, false);
                    chr.dropMessage(1, "你沒有足夠的金币放仓库道具。");
                    break;
                }
                if (item.getQuantity() < 1) {
                    return;
                }
                if (chr.getMapId() == 910000000) {
                    chr.gainMeso(-1000, false, true, false);
                }
                MapleInventoryManipulator.addFromDrop(c, item, false);
                storage.saveToDB();
                storage.sendTakenOut(c, GameConstants.getInventoryType(item.getItemId()));
                FileoutputUtil.logToFile("logs/Data/仓库操作.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号: " + c.getAccountName() + " 玩家: " + c.getPlayer().getName() + " 使用了仓库操作拿出物品:" + item.getItemId() + " 数量：" + (int)item.getQuantity());
                Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语]  账号: " + c.getAccountName() + " 玩家: " + c.getPlayer().getName() + " 使用了仓库操作拿出物品:" + item.getItemId() + " 数量：" + (int)item.getQuantity()));
                break;
            }
            case 5: {
                final byte slot2 = (byte)slea.readShort();
                final int itemId = slea.readInt();
                short quantity = slea.readShort();
                final MapleInventoryType type2 = GameConstants.getInventoryType(itemId);
                final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                if (itemId == 4110010) {
                    c.getPlayer().dropMessage(1, "无法存入该物品。");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (!c.getPlayer().CanStorage()) {
                    c.getPlayer().dropMessage(1, "操作过快请稍后在尝试。");
                    storage.sendStored(c, GameConstants.getInventoryType(itemId));
                    break;
                }
                if (quantity < 1) {
                    return;
                }
                if (storage.isFull()) {
                    c.sendPacket(MaplePacketCreator.getStorageFull());
                    return;
                }
                if (ii.isCash(itemId)) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (chr.getInventory(type2).getItem((short)slot2) == null) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (chr.getMeso() < 100 || (chr.getMapId() == 910000000 && chr.getMeso() < 500)) {
                    chr.dropMessage(1, "你沒有足夠的金币放仓库道具。");
                    break;
                }
                final IItem item2 = chr.getInventory(type2).getItem((short)slot2).copy();
                if (GameConstants.isPet(item2.getItemId())) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                final byte flag = item2.getFlag();
                if (ii.isPickupRestricted(item2.getItemId()) && storage.findById(item2.getItemId()) != null) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (item2.getItemId() != itemId || (item2.getQuantity() < quantity && !GameConstants.isThrowingStar(itemId) && !GameConstants.isBullet(itemId))) {
                    AutobanManager.getInstance().addPoints(c, 1000, 0L, "Trying to store non-matching itemid (" + itemId + "/" + item2.getItemId() + ") or quantity not in posession (" + (int)quantity + "/" + (int)item2.getQuantity() + ")");
                    return;
                }
                if (ii.isDropRestricted(item2.getItemId())) {
                    if (ItemFlag.KARMA_EQ.check((int)flag)) {
                        item2.setFlag((byte)(flag - ItemFlag.KARMA_EQ.getValue()));
                    }
                    else {
                        if (!ItemFlag.KARMA_USE.check((int)flag)) {
                            c.sendPacket(MaplePacketCreator.enableActions());
                            return;
                        }
                        item2.setFlag((byte)(flag - ItemFlag.KARMA_USE.getValue()));
                    }
                }
                if ((GameConstants.isThrowingStar(itemId) || GameConstants.isBullet(itemId)) && item2.getQuantity() < 1) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (GameConstants.isThrowingStar(itemId) || GameConstants.isBullet(itemId)) {
                    quantity = item2.getQuantity();
                }
                final int cost = (chr.getMapId() == 910000000) ? 500 : 100;
                chr.gainMeso(-cost, false, true, false);
                MapleInventoryManipulator.removeFromSlot(c, type2, (short)slot2, quantity, false);
                item2.setQuantity(quantity);
                storage.store(item2);
                storage.sendStored(c, GameConstants.getInventoryType(itemId));
                FileoutputUtil.logToFile("logs/Data/仓库操作.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号: " + c.getAccountName() + " 玩家: " + c.getPlayer().getName() + " 使用了仓库操作存入物品:" + item2.getItemId() + " 數量：" + (int)item2.getQuantity());
                Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语]  账号: " + c.getAccountName() + " 玩家: " + c.getPlayer().getName() + " 使用了仓库操作存入物品:" + item2.getItemId() + " 數量：" + (int)item2.getQuantity()));
                break;
            }
            case 6: {
                storage.arrange();
                storage.update(c);
                break;
            }
            case 7: {
                int meso = slea.readInt();
                final int storageMesos = storage.getMeso();
                final int playerMesos = chr.getMeso();
                if (!c.getPlayer().CanStorage()) {
                    c.getPlayer().dropMessage(1, "操作过快请稍后在尝试。");
                    storage.sendMeso(c);
                    break;
                }
                if ((meso > 0 && storageMesos >= meso) || (meso < 0 && playerMesos >= -meso)) {
                    if (meso < 0 && storageMesos - meso < 0) {
                        meso = -(Integer.MAX_VALUE - storageMesos);
                        if (-meso > playerMesos) {
                            return;
                        }
                    }
                    else if (meso > 0 && playerMesos + meso < 0) {
                        meso = Integer.MAX_VALUE - playerMesos;
                        if (meso > storageMesos) {
                            return;
                        }
                    }
                    storage.setMeso(storageMesos - meso);
                    storage.saveToDB();
                    chr.gainMeso(meso, false, true, false);
                    FileoutputUtil.logToFile("logs/Data/仓库操作.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号: " + c.getAccountName() + " 玩家: " + c.getPlayer().getName() + " 使用了仓库操作金币数量:" + meso);
                    Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语]  账号: " + c.getAccountName() + " 玩家: " + c.getPlayer().getName() + " 使用了仓库操作金币数量:" + meso));
                    if (meso >= 50000000 || meso <= -50000000) {
                        FileoutputUtil.logToFile("logs/Data/仓库大额金币操作.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号: " + c.getAccountName() + " 玩家: " + c.getPlayer().getName() + " 使用了仓库大额金币操作数量:" + meso);
                        Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语]  账号: " + c.getAccountName() + " 玩家: " + c.getPlayer().getName() + " 使用了仓库大额金币操作数量:" + meso));
                    }
                    storage.sendMeso(c);
                    break;
                }
                AutobanManager.getInstance().addPoints(c, 1000, 0L, "Trying to store or take out unavailable amount of mesos (" + meso + "/" + storage.getMeso() + "/" + c.getPlayer().getMeso() + ")");
            }
            case 8: {
                storage.close();
                chr.setConversation(0);
                break;
            }
            default: {
                System.err.println("Unhandled Storage mode : " + (int)mode);
                break;
            }
        }
    }
    
    public static final void NPCMoreTalk(final LittleEndianAccessor slea, final MapleClient c) {
        final byte lastMsg = slea.readByte();
        final byte action = slea.readByte();
        final NPCConversationManager cm = NPCScriptManager.getInstance().getCM(c);
        if (c == null || c.getPlayer() == null || cm == null || c.getPlayer().getConversation() == 0 || cm.getLastMsg() != lastMsg) {
            return;
        }
        cm.setLastMsg((byte)(-1));
        if (lastMsg == 2) {
            if (action != 0) {
                cm.setGetText(slea.readMapleAsciiString());
                if (cm.getType() == 0) {
                    NPCScriptManager.getInstance().startQuest(c, action, lastMsg, -1);
                }
                else if (cm.getType() == 1) {
                    NPCScriptManager.getInstance().endQuest(c, action, lastMsg, -1);
                }
                else {
                    NPCScriptManager.getInstance().action(c, action, lastMsg, -1);
                }
            }
            else {
                cm.dispose();
            }
        }
        else {
            int selection = -1;
            if (slea.available() >= 4L) {
                selection = slea.readInt();
            }
            else if (slea.available() > 0L) {
                selection = slea.readByte();
            }
            if (lastMsg == 4 && selection == -1) {
                cm.dispose();
                return;
            }
            if (selection >= -1 && action != -1) {
                switch (cm.getType()) {
                    case 0: {
                        NPCScriptManager.getInstance().startQuest(c, action, lastMsg, selection);
                        break;
                    }
                    case 1: {
                        NPCScriptManager.getInstance().endQuest(c, action, lastMsg, selection);
                        break;
                    }
                    default: {
                        NPCScriptManager.getInstance().action(c, action, lastMsg, selection);
                        break;
                    }
                }
            }
            else {
                cm.dispose();
            }
        }
    }
    
    public static final void repairAll(final MapleClient c) {
        if (c.getPlayer().getMapId() != 240000000) {
            return;
        }
        int price = 0;
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final Map<Equip, Integer> eqs = new ArrayMap<Equip, Integer>();
        final MapleInventoryType[] array;
        final MapleInventoryType[] types = array = new MapleInventoryType[] { MapleInventoryType.EQUIP, MapleInventoryType.EQUIPPED };
        for (final MapleInventoryType type : array) {
            for (final IItem item : c.getPlayer().getInventory(type)) {
                if (item instanceof Equip) {
                    final Equip eq = (Equip)item;
                    if (eq.getDurability() < 0) {
                        continue;
                    }
                    final Map<String, Integer> eqStats = ii.getEquipStats(eq.getItemId());
                    if ((int)Integer.valueOf(eqStats.get((Object)"durability")) <= 0 || eq.getDurability() >= (int)Integer.valueOf(eqStats.get((Object)"durability"))) {
                        continue;
                    }
                    final double rPercentage = 100.0 - Math.ceil((double)eq.getDurability() * 1000.0 / ((double)(int)Integer.valueOf(eqStats.get((Object)"durability")) * 10.0));
                    eqs.put(eq, eqStats.get((Object)"durability"));
                    price += (int)Math.ceil(rPercentage * ii.getPrice(eq.getItemId()) / ((ii.getReqLevel(eq.getItemId()) < 70) ? 100.0 : 1.0));
                }
            }
        }
        if (eqs.size() <= 0 || c.getPlayer().getMeso() < price) {
            return;
        }
        c.getPlayer().gainMeso(-price, true);
        for (final Entry<Equip, Integer> eqqz : eqs.entrySet()) {
            final Equip ez = (Equip)eqqz.getKey();
            ez.setDurability((int)Integer.valueOf(eqqz.getValue()));
            c.getPlayer().forceReAddItem(ez.copy(), (ez.getPosition() < 0) ? MapleInventoryType.EQUIPPED : MapleInventoryType.EQUIP);
        }
    }
    
    public static final void repair(final LittleEndianAccessor slea, final MapleClient c) {
        if (c.getPlayer().getMapId() != 240000000 || slea.available() < 4L) {
            return;
        }
        final int position = slea.readInt();
        final MapleInventoryType type = (position < 0) ? MapleInventoryType.EQUIPPED : MapleInventoryType.EQUIP;
        final IItem item = c.getPlayer().getInventory(type).getItem((short)(byte)position);
        if (item == null) {
            return;
        }
        final Equip eq = (Equip)item;
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final Map<String, Integer> eqStats = ii.getEquipStats(item.getItemId());
        if (eq.getDurability() < 0 || (int)Integer.valueOf(eqStats.get((Object)"durability")) <= 0 || eq.getDurability() >= (int)Integer.valueOf(eqStats.get((Object)"durability"))) {
            return;
        }
        final double rPercentage = 100.0 - Math.ceil((double)eq.getDurability() * 1000.0 / ((double)(int)Integer.valueOf(eqStats.get((Object)"durability")) * 10.0));
        final int price = (int)Math.ceil(rPercentage * ii.getPrice(eq.getItemId()) / ((ii.getReqLevel(eq.getItemId()) < 70) ? 100.0 : 1.0));
        if (c.getPlayer().getMeso() < price) {
            return;
        }
        c.getPlayer().gainMeso(-price, false);
        eq.setDurability((int)Integer.valueOf(eqStats.get((Object)"durability")));
        c.getPlayer().forceReAddItem(eq.copy(), type);
    }
    
    public static final void UpdateQuest(final LittleEndianAccessor slea, final MapleClient c) {
        final MapleQuest quest = MapleQuest.getInstance((int)slea.readShort());
        if (quest != null) {
            c.getPlayer().updateQuest(c.getPlayer().getQuest(quest), true);
        }
    }
    
    public static final void UseItemQuest(final LittleEndianAccessor slea, final MapleClient c) {
        final short slot = slea.readShort();
        final int itemId = slea.readInt();
        final IItem item = c.getPlayer().getInventory(MapleInventoryType.ETC).getItem(slot);
        final short qid = slea.readShort();
        slea.readShort();
        final MapleQuest quest = MapleQuest.getInstance((int)qid);
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        boolean found = false;
        for (final IItem i : c.getPlayer().getInventory(MapleInventoryType.ETC)) {
            if (i.getItemId() / 10000 == 422) {
                final Pair<Integer, List<Integer>> questItemInfo = ii.questItemInfo(i.getItemId());
                if (questItemInfo != null && (int)Integer.valueOf(questItemInfo.getLeft()) == qid && ((List<Integer>)questItemInfo.getRight()).contains((Object)Integer.valueOf(itemId))) {
                    found = true;
                    break;
                }
                continue;
            }
        }
        if (quest != null && found && item != null && item.getQuantity() > 0 && item.getItemId() == itemId) {
            final int newData = slea.readInt();
            final MapleQuestStatus stats = c.getPlayer().getQuestNoAdd(quest);
            if (stats != null && stats.getStatus() == 1) {
                stats.setCustomData(String.valueOf(newData));
                c.getPlayer().updateQuest(stats, true);
                MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.ETC, slot, (short)1, false);
            }
        }
    }
}
