package server;

import client.inventory.MapleInventoryIdentifier;
import client.inventory.MaplePet;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import tools.FileoutputUtil;
import database.DBConPool;
import client.inventory.MaplePet.PetFlag;
import tools.StringUtil;
import client.inventory.ItemFlag;
import client.MapleCharacter;
import client.inventory.IItem;
import java.util.Map.Entry;
import java.util.LinkedHashMap;
import constants.GameConstants;
import client.MapleClient;
import provider.MapleDataFileEntry;
import provider.MapleDataDirectoryEntry;
import client.inventory.MapleWeaponType;
import java.util.Iterator;
import provider.MapleDataTool;
import java.util.ArrayList;
import java.util.HashMap;
import provider.MapleDataProviderFactory;
import client.inventory.MapleInventoryType;
import tools.Pair;
import client.inventory.Equip;
import java.util.List;
import java.util.Map;
import provider.MapleData;
import provider.MapleDataProvider;

public class MapleItemInformationProvider
{
    private static final MapleItemInformationProvider instance;
    protected final MapleDataProvider etcData;
    protected final MapleDataProvider itemData;
    protected final MapleDataProvider equipData;
    protected final MapleDataProvider stringData;
    protected final MapleData cashStringData;
    protected final MapleData consumeStringData;
    protected final MapleData eqpStringData;
    protected final MapleData etcStringData;
    protected final MapleData insStringData;
    protected final MapleData petStringData;
    protected Map<Integer, Boolean> onEquipUntradableCache;
    protected final Map<Integer, List<Integer>> scrollReqCache;
    protected final Map<Integer, Short> slotMaxCache;
    protected final Map<Integer, List<StructPotentialItem>> potentialCache;
    protected final Map<Integer, MapleStatEffect> itemEffects;
    protected final Map<Integer, Map<String, Integer>> equipStatsCache;
    protected final Map<Integer, Map<String, Byte>> itemMakeStatsCache;
    protected final Map<Integer, Short> itemMakeLevel;
    protected final Map<Integer, Equip> equipCache;
    protected final Map<Integer, Double> priceCache;
    protected final Map<Integer, Integer> wholePriceCache;
    protected final Map<Integer, Integer> projectileWatkCache;
    protected final Map<Integer, Integer> monsterBookID;
    protected final Map<Integer, String> nameCache;
    protected final Map<Integer, String> descCache;
    protected final Map<Integer, Short> petFlagInfo;
    protected final Map<Integer, Integer> petLimitLifeInfo;
    protected final Map<Integer, Integer> petLifeInfo;
    protected final Map<Integer, String> msgCache;
    protected final Map<Integer, Map<String, Integer>> SkillStatsCache;
    protected final Map<Integer, Byte> consumeOnPickupCache;
    protected final Map<Integer, Boolean> dropRestrictionCache;
    protected final Map<Integer, Boolean> accCache;
    protected final Map<Integer, Boolean> pickupRestrictionCache;
    protected final Map<Integer, Integer> stateChangeCache;
    protected final Map<Integer, Integer> mesoCache;
    protected final Map<Integer, Boolean> notSaleCache;
    protected final Map<Integer, Integer> karmaEnabledCache;
    protected final Map<Integer, Boolean> isQuestItemCache;
    protected final Map<Integer, Boolean> blockPickupCache;
    protected final Map<Integer, List<Integer>> petsCanConsumeCache;
    protected final Map<Integer, Boolean> logoutExpireCache;
    protected final Map<Integer, List<Pair<Integer, Integer>>> summonMobCache;
    protected final List<Pair<Integer, String>> itemNameCache;
    protected final Map<Integer, Map<Integer, Map<String, Integer>>> equipIncsCache;
    protected final Map<Integer, Map<Integer, List<Integer>>> equipSkillsCache;
    protected final Map<Integer, Pair<Integer, List<StructRewardItem>>> RewardItem;
    protected final Map<Byte, StructSetItem> setItems;
    protected final Map<Integer, Pair<Integer, List<Integer>>> questItems;
    protected final Map<Integer, String> faceList;
    public static final Map<Integer, String> faceLists;
    public static final Map<Integer, String> hairList;
    protected Map<Integer, MapleInventoryType> inventoryTypeCache;
    protected final Map<Integer, Integer> chairMountId;
    
    protected MapleItemInformationProvider() {
        this.etcData = MapleDataProviderFactory.getDataProvider("Etc.wz");
        this.itemData = MapleDataProviderFactory.getDataProvider("Item.wz");
        this.equipData = MapleDataProviderFactory.getDataProvider("Character.wz");
        this.stringData = MapleDataProviderFactory.getDataProvider("String.wz");
        this.cashStringData = this.stringData.getData("Cash.img");
        this.consumeStringData = this.stringData.getData("Consume.img");
        this.eqpStringData = this.stringData.getData("Eqp.img");
        this.etcStringData = this.stringData.getData("Etc.img");
        this.insStringData = this.stringData.getData("Ins.img");
        this.petStringData = this.stringData.getData("Pet.img");
        this.onEquipUntradableCache = new HashMap<Integer, Boolean>();
        this.scrollReqCache = new HashMap<Integer, List<Integer>>();
        this.slotMaxCache = new HashMap<Integer, Short>();
        this.potentialCache = new HashMap<Integer, List<StructPotentialItem>>();
        this.itemEffects = new HashMap<Integer, MapleStatEffect>();
        this.equipStatsCache = new HashMap<Integer, Map<String, Integer>>();
        this.itemMakeStatsCache = new HashMap<Integer, Map<String, Byte>>();
        this.itemMakeLevel = new HashMap<Integer, Short>();
        this.equipCache = new HashMap<Integer, Equip>();
        this.priceCache = new HashMap<Integer, Double>();
        this.wholePriceCache = new HashMap<Integer, Integer>();
        this.projectileWatkCache = new HashMap<Integer, Integer>();
        this.monsterBookID = new HashMap<Integer, Integer>();
        this.nameCache = new HashMap<Integer, String>();
        this.descCache = new HashMap<Integer, String>();
        this.petFlagInfo = new HashMap<Integer, Short>();
        this.petLimitLifeInfo = new HashMap<Integer, Integer>();
        this.petLifeInfo = new HashMap<Integer, Integer>();
        this.msgCache = new HashMap<Integer, String>();
        this.SkillStatsCache = new HashMap<Integer, Map<String, Integer>>();
        this.consumeOnPickupCache = new HashMap<Integer, Byte>();
        this.dropRestrictionCache = new HashMap<Integer, Boolean>();
        this.accCache = new HashMap<Integer, Boolean>();
        this.pickupRestrictionCache = new HashMap<Integer, Boolean>();
        this.stateChangeCache = new HashMap<Integer, Integer>();
        this.mesoCache = new HashMap<Integer, Integer>();
        this.notSaleCache = new HashMap<Integer, Boolean>();
        this.karmaEnabledCache = new HashMap<Integer, Integer>();
        this.isQuestItemCache = new HashMap<Integer, Boolean>();
        this.blockPickupCache = new HashMap<Integer, Boolean>();
        this.petsCanConsumeCache = new HashMap<Integer, List<Integer>>();
        this.logoutExpireCache = new HashMap<Integer, Boolean>();
        this.summonMobCache = new HashMap<Integer, List<Pair<Integer, Integer>>>();
        this.itemNameCache = new ArrayList<Pair<Integer, String>>();
        this.equipIncsCache = new HashMap<Integer, Map<Integer, Map<String, Integer>>>();
        this.equipSkillsCache = new HashMap<Integer, Map<Integer, List<Integer>>>();
        this.RewardItem = new HashMap<Integer, Pair<Integer, List<StructRewardItem>>>();
        this.setItems = new HashMap<Byte, StructSetItem>();
        this.questItems = new HashMap<Integer, Pair<Integer, List<Integer>>>();
        this.faceList = new HashMap<Integer, String>();
        this.inventoryTypeCache = new HashMap<Integer, MapleInventoryType>();
        this.chairMountId = new HashMap<Integer, Integer>();
        System.out.println("[正在加载] -> WZ物品信息");
    }
    
    public final void load() {
        if (!this.setItems.isEmpty() || !this.potentialCache.isEmpty()) {
            return;
        }
        this.getAllItems();
    }
    
    public final List<StructPotentialItem> getPotentialInfo(final int potId) {
        return (List<StructPotentialItem>)this.potentialCache.get((Object)Integer.valueOf(potId));
    }
    
    public final Map<Integer, List<StructPotentialItem>> getAllPotentialInfo() {
        return this.potentialCache;
    }
    
    public static final MapleItemInformationProvider getInstance() {
        return MapleItemInformationProvider.instance;
    }
    
    public final List<Pair<Integer, String>> getAllItems() {
        if (!this.itemNameCache.isEmpty()) {
            return this.itemNameCache;
        }
        final List<Pair<Integer, String>> itemPairs = new ArrayList<Pair<Integer, String>>();
        MapleData itemsData = this.stringData.getData("Cash.img");
        for (final MapleData itemFolder : itemsData.getChildren()) {
            itemPairs.add(new Pair<Integer, String>(Integer.valueOf(Integer.parseInt(itemFolder.getName())), MapleDataTool.getString("name", itemFolder, "NO-NAME")));
        }
        itemsData = this.stringData.getData("Consume.img");
        for (final MapleData itemFolder : itemsData.getChildren()) {
            itemPairs.add(new Pair<Integer, String>(Integer.valueOf(Integer.parseInt(itemFolder.getName())), MapleDataTool.getString("name", itemFolder, "NO-NAME")));
        }
        itemsData = this.stringData.getData("Eqp.img").getChildByPath("Eqp");
        for (final MapleData eqpType : itemsData.getChildren()) {
            for (final MapleData itemFolder2 : eqpType.getChildren()) {
                try {
                    itemPairs.add(new Pair<Integer, String>(Integer.valueOf(Integer.parseInt(itemFolder2.getName())), MapleDataTool.getString("name", itemFolder2, "NO-NAME")));
                }
                catch (Exception Ex) {
                    System.out.println("錯誤：" + itemFolder2.getName());
                }
            }
        }
        itemsData = this.stringData.getData("Etc.img").getChildByPath("Etc");
        for (final MapleData itemFolder : itemsData.getChildren()) {
            itemPairs.add(new Pair<Integer, String>(Integer.valueOf(Integer.parseInt(itemFolder.getName())), MapleDataTool.getString("name", itemFolder, "NO-NAME")));
        }
        itemsData = this.stringData.getData("Ins.img");
        for (final MapleData itemFolder : itemsData.getChildren()) {
            itemPairs.add(new Pair<Integer, String>(Integer.valueOf(Integer.parseInt(itemFolder.getName())), MapleDataTool.getString("name", itemFolder, "NO-NAME")));
        }
        itemsData = this.stringData.getData("Pet.img");
        for (final MapleData itemFolder : itemsData.getChildren()) {
            itemPairs.add(new Pair<Integer, String>(Integer.valueOf(Integer.parseInt(itemFolder.getName())), MapleDataTool.getString("name", itemFolder, "NO-NAME")));
        }
        return itemPairs;
    }
    
    public final boolean isTwoHanded(final int itemId) {
        switch (this.getWeaponType(itemId)) {
            case 雙手斧:
            case 雙手棍:
            case 弓:
            case 拳套:
            case 弩:
            case 槍:
            case 矛:
            case 雙手劍:
            case 火槍:
            case 指虎: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean isUntradeableOnEquip(final int itemId) {
        if (this.onEquipUntradableCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (boolean)Boolean.valueOf(this.onEquipUntradableCache.get((Object)Integer.valueOf(itemId)));
        }
        final boolean untradableOnEquip = MapleDataTool.getIntConvert("info/equipTradeBlock", this.getItemData(itemId), 0) > 0;
        this.onEquipUntradableCache.put(Integer.valueOf(itemId), Boolean.valueOf(untradableOnEquip));
        return untradableOnEquip;
    }
    
    public MapleWeaponType getWeaponType(final int itemId) {
        final int cat = itemId / 10000 % 100;
        final MapleWeaponType[] type = { MapleWeaponType.單手劍, MapleWeaponType.單手斧, MapleWeaponType.單手棍, MapleWeaponType.短劍, MapleWeaponType.沒有武器, MapleWeaponType.沒有武器, MapleWeaponType.沒有武器, MapleWeaponType.長杖, MapleWeaponType.短杖, MapleWeaponType.沒有武器, MapleWeaponType.雙手劍, MapleWeaponType.雙手斧, MapleWeaponType.雙手棍, MapleWeaponType.矛, MapleWeaponType.槍, MapleWeaponType.弓, MapleWeaponType.弩, MapleWeaponType.拳套, MapleWeaponType.指虎, MapleWeaponType.火槍 };
        if (cat < 30 || cat > 49) {
            return MapleWeaponType.沒有武器;
        }
        return type[cat - 30];
    }
    
    protected final MapleData getStringData(final int itemId) {
        String cat = null;
        MapleData data;
        if (itemId >= 5010000) {
            data = this.cashStringData;
        }
        else if (itemId >= 2000000 && itemId < 3000000) {
            data = this.consumeStringData;
        }
        else if ((itemId >= 1142000 && itemId < 1143200) || (itemId >= 1010000 && itemId < 1040000) || (itemId >= 1122000 && itemId < 1123000)) {
            data = this.eqpStringData;
            cat = "Accessory";
        }
        else if (itemId >= 1000000 && itemId < 1010000) {
            data = this.eqpStringData;
            cat = "Cap";
        }
        else if (itemId >= 1102000 && itemId < 1103000) {
            data = this.eqpStringData;
            cat = "Cape";
        }
        else if (itemId >= 1040000 && itemId < 1050000) {
            data = this.eqpStringData;
            cat = "Coat";
        }
        else if (itemId >= 20000 && itemId < 22000) {
            data = this.eqpStringData;
            cat = "Face";
        }
        else if (itemId >= 1080000 && itemId < 1090000) {
            data = this.eqpStringData;
            cat = "Glove";
        }
        else if (itemId >= 30000 && itemId < 32000) {
            data = this.eqpStringData;
            cat = "Hair";
        }
        else if (itemId >= 1050000 && itemId < 1060000) {
            data = this.eqpStringData;
            cat = "Longcoat";
        }
        else if (itemId >= 1060000 && itemId < 1070000) {
            data = this.eqpStringData;
            cat = "Pants";
        }
        else if (itemId >= 1610000 && itemId < 1660000) {
            data = this.eqpStringData;
            cat = "Mechanic";
        }
        else if (itemId >= 1802000 && itemId < 1810000) {
            data = this.eqpStringData;
            cat = "PetEquip";
        }
        else if (itemId >= 1920000 && itemId < 2000000) {
            data = this.eqpStringData;
            cat = "Dragon";
        }
        else if (itemId >= 1112000 && itemId < 1120000) {
            data = this.eqpStringData;
            cat = "Ring";
        }
        else if (itemId >= 1092000 && itemId < 1100000) {
            data = this.eqpStringData;
            cat = "Shield";
        }
        else if (itemId >= 1070000 && itemId < 1080000) {
            data = this.eqpStringData;
            cat = "Shoes";
        }
        else if (itemId >= 1900000 && itemId < 1920000) {
            data = this.eqpStringData;
            cat = "Taming";
        }
        else if (itemId >= 1300000 && itemId < 1800000) {
            data = this.eqpStringData;
            cat = "Weapon";
        }
        else if (itemId >= 4000000 && itemId < 5000000) {
            data = this.etcStringData;
        }
        else if (itemId >= 3000000 && itemId < 4000000) {
            data = this.insStringData;
        }
        else {
            if (itemId < 5000000 || itemId >= 5010000) {
                return null;
            }
            data = this.petStringData;
        }
        if (cat == null) {
            return data.getChildByPath(String.valueOf(itemId));
        }
        return data.getChildByPath("Eqp/" + cat + "/" + itemId);
    }
    
    protected final MapleData getItemData(final int itemId) {
        MapleData ret = null;
        final String idStr = "0" + String.valueOf(itemId);
        MapleDataDirectoryEntry root = this.itemData.getRoot();
        for (final MapleDataDirectoryEntry topDir : root.getSubdirectories()) {
            for (final MapleDataFileEntry iFile : topDir.getFiles()) {
                if (iFile.getName().equals((Object)(idStr.substring(0, 4) + ".img"))) {
                    ret = this.itemData.getData(topDir.getName() + "/" + iFile.getName());
                    if (ret == null) {
                        return null;
                    }
                    ret = ret.getChildByPath(idStr);
                    return ret;
                }
                else {
                    if (iFile.getName().equals((Object)(idStr.substring(1) + ".img"))) {
                        return this.itemData.getData(topDir.getName() + "/" + iFile.getName());
                    }
                    continue;
                }
            }
        }
        root = this.equipData.getRoot();
        for (final MapleDataDirectoryEntry topDir : root.getSubdirectories()) {
            for (final MapleDataFileEntry iFile : topDir.getFiles()) {
                if (iFile.getName().equals((Object)(idStr + ".img"))) {
                    return this.equipData.getData(topDir.getName() + "/" + iFile.getName());
                }
            }
        }
        return ret;
    }
    
    public final short getSlotMax(final MapleClient c, final int itemId) {
        if (this.slotMaxCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (short)Short.valueOf(this.slotMaxCache.get((Object)Integer.valueOf(itemId)));
        }
        short ret = 0;
        final MapleData item = this.getItemData(itemId);
        if (item != null) {
            final MapleData smEntry = item.getChildByPath("info/slotMax");
            if (smEntry == null) {
                if (GameConstants.getInventoryType(itemId) == MapleInventoryType.EQUIP) {
                    ret = 1;
                }
                else {
                    ret = 100;
                }
            }
            else {
                ret = (short)MapleDataTool.getInt(smEntry);
            }
        }
        this.slotMaxCache.put(Integer.valueOf(itemId), Short.valueOf(ret));
        return ret;
    }
    
    public final int getWholePrice(final int itemId) {
        if (this.wholePriceCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (int)Integer.valueOf(this.wholePriceCache.get((Object)Integer.valueOf(itemId)));
        }
        final MapleData item = this.getItemData(itemId);
        if (item == null) {
            return -1;
        }
        final MapleData pData = item.getChildByPath("info/price");
        if (pData == null) {
            return -1;
        }
        final int pEntry = MapleDataTool.getInt(pData);
        this.wholePriceCache.put(Integer.valueOf(itemId), Integer.valueOf(pEntry));
        return pEntry;
    }
    
    public final double getPrice(final int itemId) {
        if (this.priceCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (double)Double.valueOf(this.priceCache.get((Object)Integer.valueOf(itemId)));
        }
        final MapleData item = this.getItemData(itemId);
        if (item == null) {
            return -1.0;
        }
        MapleData pData = item.getChildByPath("info/unitPrice");
        double pEntry;
        if (pData != null) {
            try {
                pEntry = MapleDataTool.getDouble(pData);
            }
            catch (Exception e) {
                pEntry = (double)MapleDataTool.getIntConvert(pData);
            }
        }
        else {
            pData = item.getChildByPath("info/price");
            if (pData == null) {
                return -1.0;
            }
            pEntry = (double)MapleDataTool.getIntConvert(pData);
        }
        if (itemId == 2070019 || itemId == 2330007) {
            pEntry = 1.0;
        }
        this.priceCache.put(Integer.valueOf(itemId), Double.valueOf(pEntry));
        return pEntry;
    }
    
    public final Map<String, Byte> getItemMakeStats(final int itemId) {
        if (this.itemMakeStatsCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (Map<String, Byte>)this.itemMakeStatsCache.get((Object)Integer.valueOf(itemId));
        }
        if (itemId / 10000 != 425) {
            return null;
        }
        final Map<String, Byte> ret = new LinkedHashMap<String, Byte>();
        final MapleData item = this.getItemData(itemId);
        if (item == null) {
            return null;
        }
        final MapleData info = item.getChildByPath("info");
        if (info == null) {
            return null;
        }
        ret.put("incPAD", Byte.valueOf((byte)MapleDataTool.getInt("incPAD", info, 0)));
        ret.put("incMAD", Byte.valueOf((byte)MapleDataTool.getInt("incMAD", info, 0)));
        ret.put("incACC", Byte.valueOf((byte)MapleDataTool.getInt("incACC", info, 0)));
        ret.put("incEVA", Byte.valueOf((byte)MapleDataTool.getInt("incEVA", info, 0)));
        ret.put("incSpeed", Byte.valueOf((byte)MapleDataTool.getInt("incSpeed", info, 0)));
        ret.put("incJump", Byte.valueOf((byte)MapleDataTool.getInt("incJump", info, 0)));
        ret.put("incMaxHP", Byte.valueOf((byte)MapleDataTool.getInt("incMaxHP", info, 0)));
        ret.put("incMaxMP", Byte.valueOf((byte)MapleDataTool.getInt("incMaxMP", info, 0)));
        ret.put("incSTR", Byte.valueOf((byte)MapleDataTool.getInt("incSTR", info, 0)));
        ret.put("incINT", Byte.valueOf((byte)MapleDataTool.getInt("incINT", info, 0)));
        ret.put("incLUK", Byte.valueOf((byte)MapleDataTool.getInt("incLUK", info, 0)));
        ret.put("incDEX", Byte.valueOf((byte)MapleDataTool.getInt("incDEX", info, 0)));
        ret.put("randOption", Byte.valueOf((byte)MapleDataTool.getInt("randOption", info, 0)));
        ret.put("randStat", Byte.valueOf((byte)MapleDataTool.getInt("randStat", info, 0)));
        this.itemMakeStatsCache.put(Integer.valueOf(itemId), ret);
        return ret;
    }
    
    private int rand(final int min, final int max) {
        return Math.abs(Randomizer.rand(min, max));
    }
    
    public Equip levelUpEquip(final Equip equip, final Map<String, Integer> sta) {
        final Equip nEquip = (Equip)equip.copy();
        try {
            for (final Entry<String, Integer> stat : sta.entrySet()) {
                final String s = (String)stat.getKey();
                int n = -1;
                switch (s.hashCode()) {
                    case -1838651775: {
                        if (s.equals((Object)"STRMin")) {
                            n = 0;
                            break;
                        }
                        break;
                    }
                    case 2013204187: {
                        if (s.equals((Object)"DEXMin")) {
                            n = 1;
                            break;
                        }
                        break;
                    }
                    case -2130424829: {
                        if (s.equals((Object)"INTMin")) {
                            n = 2;
                            break;
                        }
                        break;
                    }
                    case -2038340848: {
                        if (s.equals((Object)"LUKMin")) {
                            n = 3;
                            break;
                        }
                        break;
                    }
                    case -1942503201: {
                        if (s.equals((Object)"PADMin")) {
                            n = 4;
                            break;
                        }
                        break;
                    }
                    case -1939732638: {
                        if (s.equals((Object)"PDDMin")) {
                            n = 5;
                            break;
                        }
                        break;
                    }
                    case -2028390654: {
                        if (s.equals((Object)"MADMin")) {
                            n = 6;
                            break;
                        }
                        break;
                    }
                    case -2025620091: {
                        if (s.equals((Object)"MDDMin")) {
                            n = 7;
                            break;
                        }
                        break;
                    }
                    case 1924844081: {
                        if (s.equals((Object)"ACCMin")) {
                            n = 8;
                            break;
                        }
                        break;
                    }
                    case 2056848002: {
                        if (s.equals((Object)"EVAMin")) {
                            n = 9;
                            break;
                        }
                        break;
                    }
                    case -2066556757: {
                        if (s.equals((Object)"SpeedMin")) {
                            n = 10;
                            break;
                        }
                        break;
                    }
                    case 409484068: {
                        if (s.equals((Object)"JumpMin")) {
                            n = 11;
                            break;
                        }
                        break;
                    }
                    case -2021568515: {
                        if (s.equals((Object)"MHPMin")) {
                            n = 12;
                            break;
                        }
                        break;
                    }
                    case -2016950910: {
                        if (s.equals((Object)"MMPMin")) {
                            n = 13;
                            break;
                        }
                        break;
                    }
                    case 450405734: {
                        if (s.equals((Object)"MaxHPMin")) {
                            n = 14;
                            break;
                        }
                        break;
                    }
                    case 455023339: {
                        if (s.equals((Object)"MaxMPMin")) {
                            n = 15;
                            break;
                        }
                        break;
                    }
                }
                switch (n) {
                    case 0: {
                        nEquip.setStr((short)(nEquip.getStr() + this.rand((int)Integer.valueOf(stat.getValue()), (int)Integer.valueOf(sta.get((Object)"STRMax")))));
                        continue;
                    }
                    case 1: {
                        nEquip.setDex((short)(nEquip.getDex() + this.rand((int)Integer.valueOf(stat.getValue()), (int)Integer.valueOf(sta.get((Object)"DEXMax")))));
                        continue;
                    }
                    case 2: {
                        nEquip.setInt((short)(nEquip.getInt() + this.rand((int)Integer.valueOf(stat.getValue()), (int)Integer.valueOf(sta.get((Object)"INTMax")))));
                        continue;
                    }
                    case 3: {
                        nEquip.setLuk((short)(nEquip.getLuk() + this.rand((int)Integer.valueOf(stat.getValue()), (int)Integer.valueOf(sta.get((Object)"LUKMax")))));
                        continue;
                    }
                    case 4: {
                        nEquip.setWatk((short)(nEquip.getWatk() + this.rand((int)Integer.valueOf(stat.getValue()), (int)Integer.valueOf(sta.get((Object)"PADMax")))));
                        continue;
                    }
                    case 5: {
                        nEquip.setWdef((short)(nEquip.getWdef() + this.rand((int)Integer.valueOf(stat.getValue()), (int)Integer.valueOf(sta.get((Object)"PDDMax")))));
                        continue;
                    }
                    case 6: {
                        nEquip.setMatk((short)(nEquip.getMatk() + this.rand((int)Integer.valueOf(stat.getValue()), (int)Integer.valueOf(sta.get((Object)"MADMax")))));
                        continue;
                    }
                    case 7: {
                        nEquip.setMdef((short)(nEquip.getMdef() + this.rand((int)Integer.valueOf(stat.getValue()), (int)Integer.valueOf(sta.get((Object)"MDDMax")))));
                        continue;
                    }
                    case 8: {
                        nEquip.setAcc((short)(nEquip.getAcc() + this.rand((int)Integer.valueOf(stat.getValue()), (int)Integer.valueOf(sta.get((Object)"ACCMax")))));
                        continue;
                    }
                    case 9: {
                        nEquip.setAvoid((short)(nEquip.getAvoid() + this.rand((int)Integer.valueOf(stat.getValue()), (int)Integer.valueOf(sta.get((Object)"EVAMax")))));
                        continue;
                    }
                    case 10: {
                        nEquip.setSpeed((short)(nEquip.getSpeed() + this.rand((int)Integer.valueOf(stat.getValue()), (int)Integer.valueOf(sta.get((Object)"SpeedMax")))));
                        continue;
                    }
                    case 11: {
                        nEquip.setJump((short)(nEquip.getJump() + this.rand((int)Integer.valueOf(stat.getValue()), (int)Integer.valueOf(sta.get((Object)"JumpMax")))));
                        continue;
                    }
                    case 12: {
                        nEquip.setHp((short)(nEquip.getHp() + this.rand((int)Integer.valueOf(stat.getValue()), (int)Integer.valueOf(sta.get((Object)"MHPMax")))));
                        continue;
                    }
                    case 13: {
                        nEquip.setMp((short)(nEquip.getMp() + this.rand((int)Integer.valueOf(stat.getValue()), (int)Integer.valueOf(sta.get((Object)"MMPMax")))));
                        continue;
                    }
                    case 14: {
                        nEquip.setHp((short)(nEquip.getHp() + this.rand((int)Integer.valueOf(stat.getValue()), (int)Integer.valueOf(sta.get((Object)"MaxHPMax")))));
                        continue;
                    }
                    case 15: {
                        nEquip.setMp((short)(nEquip.getMp() + this.rand((int)Integer.valueOf(stat.getValue()), (int)Integer.valueOf(sta.get((Object)"MaxMPMax")))));
                        continue;
                    }
                }
            }
        }
        catch (NullPointerException ex) {}
        return nEquip;
    }
    
    public final Map<Integer, Map<String, Integer>> getEquipIncrements(final int itemId) {
        if (this.equipIncsCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (Map<Integer, Map<String, Integer>>)this.equipIncsCache.get((Object)Integer.valueOf(itemId));
        }
        final Map<Integer, Map<String, Integer>> ret = new LinkedHashMap<Integer, Map<String, Integer>>();
        final MapleData item = this.getItemData(itemId);
        if (item == null) {
            return null;
        }
        final MapleData info = item.getChildByPath("info/level/info");
        if (info == null) {
            return null;
        }
        for (final MapleData dat : info.getChildren()) {
            final Map<String, Integer> incs = new HashMap<String, Integer>();
            for (final MapleData data : dat.getChildren()) {
                if (data.getName().length() > 3) {
                    incs.put(data.getName().substring(3), Integer.valueOf(MapleDataTool.getIntConvert(data.getName(), dat, 0)));
                }
            }
            ret.put(Integer.valueOf(Integer.parseInt(dat.getName())), incs);
        }
        this.equipIncsCache.put(Integer.valueOf(itemId), ret);
        return ret;
    }
    
    public final Map<Integer, List<Integer>> getEquipSkills(final int itemId) {
        if (this.equipSkillsCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (Map<Integer, List<Integer>>)this.equipSkillsCache.get((Object)Integer.valueOf(itemId));
        }
        final Map<Integer, List<Integer>> ret = new LinkedHashMap<Integer, List<Integer>>();
        final MapleData item = this.getItemData(itemId);
        if (item == null) {
            return null;
        }
        final MapleData info = item.getChildByPath("info/level/case");
        if (info == null) {
            return null;
        }
        for (final MapleData dat : info.getChildren()) {
            for (final MapleData data : dat.getChildren()) {
                if (data.getName().length() == 1) {
                    final List<Integer> adds = new ArrayList<Integer>();
                    for (final MapleData skil : data.getChildByPath("Skill").getChildren()) {
                        adds.add(Integer.valueOf(MapleDataTool.getIntConvert("id", skil, 0)));
                    }
                    ret.put(Integer.valueOf(Integer.parseInt(data.getName())), adds);
                }
            }
        }
        this.equipSkillsCache.put(Integer.valueOf(itemId), ret);
        return ret;
    }
    
    public final Map<String, Integer> getEquipStats(final int itemId) {
        if (this.equipStatsCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (Map<String, Integer>)this.equipStatsCache.get((Object)Integer.valueOf(itemId));
        }
        final Map<String, Integer> ret = new LinkedHashMap<String, Integer>();
        final MapleData item = this.getItemData(itemId);
        if (item == null) {
            return null;
        }
        final MapleData info = item.getChildByPath("info");
        if (info == null) {
            return null;
        }
        for (final MapleData data : info.getChildren()) {
            if (data.getName().startsWith("inc")) {
                ret.put(data.getName().substring(3), Integer.valueOf(MapleDataTool.getIntConvert(data)));
            }
        }
        ret.put("tuc", Integer.valueOf(MapleDataTool.getInt("tuc", info, 0)));
        ret.put("reqLevel", Integer.valueOf(MapleDataTool.getInt("reqLevel", info, 0)));
        ret.put("reqJob", Integer.valueOf(MapleDataTool.getInt("reqJob", info, 0)));
        ret.put("reqSTR", Integer.valueOf(MapleDataTool.getInt("reqSTR", info, 0)));
        ret.put("reqDEX", Integer.valueOf(MapleDataTool.getInt("reqDEX", info, 0)));
        ret.put("reqINT", Integer.valueOf(MapleDataTool.getInt("reqINT", info, 0)));
        ret.put("reqLUK", Integer.valueOf(MapleDataTool.getInt("reqLUK", info, 0)));
        ret.put("reqPOP", Integer.valueOf(MapleDataTool.getInt("reqPOP", info, 0)));
        ret.put("cash", Integer.valueOf(MapleDataTool.getInt("cash", info, 0)));
        ret.put("canLevel", Integer.valueOf((int)((info.getChildByPath("level") != null) ? 1 : 0)));
        ret.put("cursed", Integer.valueOf(MapleDataTool.getInt("cursed", info, 0)));
        ret.put("success", Integer.valueOf(MapleDataTool.getInt("success", info, 0)));
        ret.put("setItemID", Integer.valueOf(MapleDataTool.getInt("setItemID", info, 0)));
        ret.put("equipTradeBlock", Integer.valueOf(MapleDataTool.getInt("equipTradeBlock", info, 0)));
        ret.put("durability", Integer.valueOf(MapleDataTool.getInt("durability", info, -1)));
        if (GameConstants.isMagicWeapon(itemId)) {
            ret.put("elemDefault", Integer.valueOf(MapleDataTool.getInt("elemDefault", info, 100)));
            ret.put("incRMAS", Integer.valueOf(MapleDataTool.getInt("incRMAS", info, 100)));
            ret.put("incRMAF", Integer.valueOf(MapleDataTool.getInt("incRMAF", info, 100)));
            ret.put("incRMAL", Integer.valueOf(MapleDataTool.getInt("incRMAL", info, 100)));
            ret.put("incRMAI", Integer.valueOf(MapleDataTool.getInt("incRMAI", info, 100)));
        }
        this.equipStatsCache.put(Integer.valueOf(itemId), ret);
        return ret;
    }
    
    public final boolean isCashItem(final int itemId) {
        return this.getEquipStats(itemId) != null && (int)Integer.valueOf(this.getEquipStats(itemId).get((Object)"cash")) == 1;
    }
    
    public final boolean canEquip(final Map<String, Integer> stats, final int itemid, final int level, final int job, final int fame, final int str, final int dex, final int luk, final int int_, final int supremacy) {
        if (level + supremacy >= (int)Integer.valueOf(stats.get((Object)"reqLevel")) && str >= (int)Integer.valueOf(stats.get((Object)"reqSTR")) && dex >= (int)Integer.valueOf(stats.get((Object)"reqDEX")) && luk >= (int)Integer.valueOf(stats.get((Object)"reqLUK")) && int_ >= (int)Integer.valueOf(stats.get((Object)"reqINT"))) {
            final int fameReq = (int)Integer.valueOf(stats.get((Object)"reqPOP"));
            return fameReq == 0 || fame >= fameReq;
        }
        return false;
    }
    
    public final int getReqLevel(final int itemId) {
        if (this.getEquipStats(itemId) == null) {
            return 0;
        }
        return (int)Integer.valueOf(this.getEquipStats(itemId).get((Object)"reqLevel"));
    }
    
    public final int getSlots(final int itemId) {
        if (this.getEquipStats(itemId) == null) {
            return 0;
        }
        return (int)Integer.valueOf(this.getEquipStats(itemId).get((Object)"tuc"));
    }
    
    public final int getSetItemID(final int itemId) {
        if (this.getEquipStats(itemId) == null) {
            return 0;
        }
        return (int)Integer.valueOf(this.getEquipStats(itemId).get((Object)"setItemID"));
    }
    
    public final StructSetItem getSetItem(final int setItemId) {
        return (StructSetItem)this.setItems.get((Object)Byte.valueOf((byte)setItemId));
    }
    
    public final List<Integer> getScrollReqs(final int itemId) {
        if (this.scrollReqCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (List<Integer>)this.scrollReqCache.get((Object)Integer.valueOf(itemId));
        }
        final List<Integer> ret = new ArrayList<Integer>();
        final MapleData data = this.getItemData(itemId).getChildByPath("req");
        if (data == null) {
            return ret;
        }
        for (final MapleData req : data.getChildren()) {
            ret.add(Integer.valueOf(MapleDataTool.getInt(req)));
        }
        this.scrollReqCache.put(Integer.valueOf(itemId), ret);
        return ret;
    }
    
    public final IItem scrollEquipWithId(final IItem equip, final IItem scrollId, final boolean ws, final MapleCharacter chr, final int vegas) {
        if (equip.getType() == 1) {
            final Equip nEquip = (Equip)equip;
            final Map<String, Integer> stats = this.getEquipStats(scrollId.getItemId());
            final Map<String, Integer> eqstats = this.getEquipStats(equip.getItemId());
            final int succ = GameConstants.isTablet(scrollId.getItemId()) ? GameConstants.getSuccessTablet(scrollId.getItemId(), (int)nEquip.getLevel()) : ((GameConstants.isEquipScroll(scrollId.getItemId()) || GameConstants.isPotentialScroll(scrollId.getItemId())) ? 0 : ((int)Integer.valueOf(stats.get((Object)"success"))));
            final int curse = GameConstants.isTablet(scrollId.getItemId()) ? GameConstants.getCurseTablet(scrollId.getItemId(), (int)nEquip.getLevel()) : ((GameConstants.isEquipScroll(scrollId.getItemId()) || GameConstants.isPotentialScroll(scrollId.getItemId())) ? 0 : ((int)Integer.valueOf(stats.get((Object)"cursed"))));
            final int success = succ + ((vegas == 5610000 && succ == 10) ? 20 : ((vegas == 5610001 && succ == 60) ? 30 : 0));
            if (GameConstants.isPotentialScroll(scrollId.getItemId()) || GameConstants.isEquipScroll(scrollId.getItemId()) || Randomizer.nextInt(100) <= success) {
                switch (scrollId.getItemId()) {
                    case 2049000:
                    case 2049001:
                    case 2049002:
                    case 2049003:
                    case 2049004:
                    case 2049005: {
                        if (nEquip.getLevel() + nEquip.getUpgradeSlots() < (int)Integer.valueOf(eqstats.get((Object)"tuc"))) {
                            nEquip.setUpgradeSlots((byte)(nEquip.getUpgradeSlots() + 1));
                            break;
                        }
                        break;
                    }
                    case 2049006:
                    case 2049007:
                    case 2049008: {
                        if (nEquip.getLevel() + nEquip.getUpgradeSlots() < (int)Integer.valueOf(eqstats.get((Object)"tuc"))) {
                            nEquip.setUpgradeSlots((byte)(nEquip.getUpgradeSlots() + 2));
                            break;
                        }
                        break;
                    }
                    case 2040727: {
                        byte flag = nEquip.getFlag();
                        flag |= (byte)ItemFlag.SPIKES.getValue();
                        nEquip.setFlag(flag);
                        break;
                    }
                    case 2041058: {
                        byte flag = nEquip.getFlag();
                        flag |= (byte)ItemFlag.COLD.getValue();
                        nEquip.setFlag(flag);
                        break;
                    }
                    default: {
                        if (GameConstants.isChaosScroll(scrollId.getItemId())) {
                            final int z = GameConstants.getChaosNumber(scrollId.getItemId());
                            if (nEquip.getStr() > 0) {
                                nEquip.setStr((short)(nEquip.getStr() + Randomizer.nextInt(z) * (Randomizer.nextBoolean() ? 1 : -1)));
                            }
                            if (nEquip.getDex() > 0) {
                                nEquip.setDex((short)(nEquip.getDex() + Randomizer.nextInt(z) * (Randomizer.nextBoolean() ? 1 : -1)));
                            }
                            if (nEquip.getInt() > 0) {
                                nEquip.setInt((short)(nEquip.getInt() + Randomizer.nextInt(z) * (Randomizer.nextBoolean() ? 1 : -1)));
                            }
                            if (nEquip.getLuk() > 0) {
                                nEquip.setLuk((short)(nEquip.getLuk() + Randomizer.nextInt(z) * (Randomizer.nextBoolean() ? 1 : -1)));
                            }
                            if (nEquip.getWatk() > 0) {
                                nEquip.setWatk((short)(nEquip.getWatk() + Randomizer.nextInt(z) * (Randomizer.nextBoolean() ? 1 : -1)));
                            }
                            if (nEquip.getWdef() > 0) {
                                nEquip.setWdef((short)(nEquip.getWdef() + Randomizer.nextInt(z) * (Randomizer.nextBoolean() ? 1 : -1)));
                            }
                            if (nEquip.getMatk() > 0) {
                                nEquip.setMatk((short)(nEquip.getMatk() + Randomizer.nextInt(z) * (Randomizer.nextBoolean() ? 1 : -1)));
                            }
                            if (nEquip.getMdef() > 0) {
                                nEquip.setMdef((short)(nEquip.getMdef() + Randomizer.nextInt(z) * (Randomizer.nextBoolean() ? 1 : -1)));
                            }
                            if (nEquip.getAcc() > 0) {
                                nEquip.setAcc((short)(nEquip.getAcc() + Randomizer.nextInt(z) * (Randomizer.nextBoolean() ? 1 : -1)));
                            }
                            if (nEquip.getAvoid() > 0) {
                                nEquip.setAvoid((short)(nEquip.getAvoid() + Randomizer.nextInt(z) * (Randomizer.nextBoolean() ? 1 : -1)));
                            }
                            if (nEquip.getSpeed() > 0) {
                                nEquip.setSpeed((short)(nEquip.getSpeed() + Randomizer.nextInt(z) * (Randomizer.nextBoolean() ? 1 : -1)));
                            }
                            if (nEquip.getJump() > 0) {
                                nEquip.setJump((short)(nEquip.getJump() + Randomizer.nextInt(z) * (Randomizer.nextBoolean() ? 1 : -1)));
                            }
                            if (nEquip.getHp() > 0) {
                                nEquip.setHp((short)(nEquip.getHp() + Randomizer.nextInt(z) * (Randomizer.nextBoolean() ? 1 : -1)));
                            }
                            if (nEquip.getMp() > 0) {
                                nEquip.setMp((short)(nEquip.getMp() + Randomizer.nextInt(z) * (Randomizer.nextBoolean() ? 1 : -1)));
                                break;
                            }
                            break;
                        }
                        else if (GameConstants.isEquipScroll(scrollId.getItemId())) {
                            final int chanc = Math.max(((scrollId.getItemId() == 2049300) ? 100 : 80) - nEquip.getEnhance() * 10, 10);
                            if (Randomizer.nextInt(100) > chanc) {
                                return null;
                            }
                            if (nEquip.getStr() > 0 || Randomizer.nextInt(50) == 1) {
                                nEquip.setStr((short)(nEquip.getStr() + Randomizer.nextInt(5)));
                            }
                            if (nEquip.getDex() > 0 || Randomizer.nextInt(50) == 1) {
                                nEquip.setDex((short)(nEquip.getDex() + Randomizer.nextInt(5)));
                            }
                            if (nEquip.getInt() > 0 || Randomizer.nextInt(50) == 1) {
                                nEquip.setInt((short)(nEquip.getInt() + Randomizer.nextInt(5)));
                            }
                            if (nEquip.getLuk() > 0 || Randomizer.nextInt(50) == 1) {
                                nEquip.setLuk((short)(nEquip.getLuk() + Randomizer.nextInt(5)));
                            }
                            if (nEquip.getWatk() > 0 && GameConstants.isWeapon(nEquip.getItemId())) {
                                nEquip.setWatk((short)(nEquip.getWatk() + Randomizer.nextInt(5)));
                            }
                            if (nEquip.getWdef() > 0 || Randomizer.nextInt(40) == 1) {
                                nEquip.setWdef((short)(nEquip.getWdef() + Randomizer.nextInt(5)));
                            }
                            if (nEquip.getMatk() > 0 && GameConstants.isWeapon(nEquip.getItemId())) {
                                nEquip.setMatk((short)(nEquip.getMatk() + Randomizer.nextInt(5)));
                            }
                            if (nEquip.getMdef() > 0 || Randomizer.nextInt(40) == 1) {
                                nEquip.setMdef((short)(nEquip.getMdef() + Randomizer.nextInt(5)));
                            }
                            if (nEquip.getAcc() > 0 || Randomizer.nextInt(20) == 1) {
                                nEquip.setAcc((short)(nEquip.getAcc() + Randomizer.nextInt(5)));
                            }
                            if (nEquip.getAvoid() > 0 || Randomizer.nextInt(20) == 1) {
                                nEquip.setAvoid((short)(nEquip.getAvoid() + Randomizer.nextInt(5)));
                            }
                            if (nEquip.getSpeed() > 0 || Randomizer.nextInt(10) == 1) {
                                nEquip.setSpeed((short)(nEquip.getSpeed() + Randomizer.nextInt(5)));
                            }
                            if (nEquip.getJump() > 0 || Randomizer.nextInt(10) == 1) {
                                nEquip.setJump((short)(nEquip.getJump() + Randomizer.nextInt(5)));
                            }
                            if (nEquip.getHp() > 0 || Randomizer.nextInt(5) == 1) {
                                nEquip.setHp((short)(nEquip.getHp() + Randomizer.nextInt(5)));
                            }
                            if (nEquip.getMp() > 0 || Randomizer.nextInt(5) == 1) {
                                nEquip.setMp((short)(nEquip.getMp() + Randomizer.nextInt(5)));
                            }
                            nEquip.setEnhance((byte)(nEquip.getEnhance() + 1));
                            break;
                        }
                        else {
                            if (!GameConstants.isPotentialScroll(scrollId.getItemId())) {
                                for (final Entry<String, Integer> stat : stats.entrySet()) {
                                    final String s;
                                    final String key = s = (String)stat.getKey();
                                    int n = -1;
                                    switch (s.hashCode()) {
                                        case 82449: {
                                            if (s.equals((Object)"STR")) {
                                                n = 0;
                                                break;
                                            }
                                            break;
                                        }
                                        case 67575: {
                                            if (s.equals((Object)"DEX")) {
                                                n = 1;
                                                break;
                                            }
                                            break;
                                        }
                                        case 72655: {
                                            if (s.equals((Object)"INT")) {
                                                n = 2;
                                                break;
                                            }
                                            break;
                                        }
                                        case 75746: {
                                            if (s.equals((Object)"LUK")) {
                                                n = 3;
                                                break;
                                            }
                                            break;
                                        }
                                        case 78963: {
                                            if (s.equals((Object)"PAD")) {
                                                n = 4;
                                                break;
                                            }
                                            break;
                                        }
                                        case 79056: {
                                            if (s.equals((Object)"PDD")) {
                                                n = 5;
                                                break;
                                            }
                                            break;
                                        }
                                        case 76080: {
                                            if (s.equals((Object)"MAD")) {
                                                n = 6;
                                                break;
                                            }
                                            break;
                                        }
                                        case 76173: {
                                            if (s.equals((Object)"MDD")) {
                                                n = 7;
                                                break;
                                            }
                                            break;
                                        }
                                        case 64609: {
                                            if (s.equals((Object)"ACC")) {
                                                n = 8;
                                                break;
                                            }
                                            break;
                                        }
                                        case 69040: {
                                            if (s.equals((Object)"EVA")) {
                                                n = 9;
                                                break;
                                            }
                                            break;
                                        }
                                        case 80089127: {
                                            if (s.equals((Object)"Speed")) {
                                                n = 10;
                                                break;
                                            }
                                            break;
                                        }
                                        case 2320462: {
                                            if (s.equals((Object)"Jump")) {
                                                n = 11;
                                                break;
                                            }
                                            break;
                                        }
                                        case 76309: {
                                            if (s.equals((Object)"MHP")) {
                                                n = 12;
                                                break;
                                            }
                                            break;
                                        }
                                        case 76464: {
                                            if (s.equals((Object)"MMP")) {
                                                n = 13;
                                                break;
                                            }
                                            break;
                                        }
                                        case 2365693: {
                                            if (s.equals((Object)"MHPr")) {
                                                n = 14;
                                                break;
                                            }
                                            break;
                                        }
                                        case 2370498: {
                                            if (s.equals((Object)"MMPr")) {
                                                n = 15;
                                                break;
                                            }
                                            break;
                                        }
                                    }
                                    switch (n) {
                                        case 0: {
                                            nEquip.setStr((short)(nEquip.getStr() + (int)Integer.valueOf(stat.getValue())));
                                            continue;
                                        }
                                        case 1: {
                                            nEquip.setDex((short)(nEquip.getDex() + (int)Integer.valueOf(stat.getValue())));
                                            continue;
                                        }
                                        case 2: {
                                            nEquip.setInt((short)(nEquip.getInt() + (int)Integer.valueOf(stat.getValue())));
                                            continue;
                                        }
                                        case 3: {
                                            nEquip.setLuk((short)(nEquip.getLuk() + (int)Integer.valueOf(stat.getValue())));
                                            continue;
                                        }
                                        case 4: {
                                            nEquip.setWatk((short)(nEquip.getWatk() + (int)Integer.valueOf(stat.getValue())));
                                            continue;
                                        }
                                        case 5: {
                                            nEquip.setWdef((short)(nEquip.getWdef() + (int)Integer.valueOf(stat.getValue())));
                                            continue;
                                        }
                                        case 6: {
                                            nEquip.setMatk((short)(nEquip.getMatk() + (int)Integer.valueOf(stat.getValue())));
                                            continue;
                                        }
                                        case 7: {
                                            nEquip.setMdef((short)(nEquip.getMdef() + (int)Integer.valueOf(stat.getValue())));
                                            continue;
                                        }
                                        case 8: {
                                            nEquip.setAcc((short)(nEquip.getAcc() + (int)Integer.valueOf(stat.getValue())));
                                            continue;
                                        }
                                        case 9: {
                                            nEquip.setAvoid((short)(nEquip.getAvoid() + (int)Integer.valueOf(stat.getValue())));
                                            continue;
                                        }
                                        case 10: {
                                            nEquip.setSpeed((short)(nEquip.getSpeed() + (int)Integer.valueOf(stat.getValue())));
                                            continue;
                                        }
                                        case 11: {
                                            nEquip.setJump((short)(nEquip.getJump() + (int)Integer.valueOf(stat.getValue())));
                                            continue;
                                        }
                                        case 12: {
                                            nEquip.setHp((short)(nEquip.getHp() + (int)Integer.valueOf(stat.getValue())));
                                            continue;
                                        }
                                        case 13: {
                                            nEquip.setMp((short)(nEquip.getMp() + (int)Integer.valueOf(stat.getValue())));
                                            continue;
                                        }
                                        case 14: {
                                            nEquip.setHpR((short)(nEquip.getHpR() + (int)Integer.valueOf(stat.getValue())));
                                            continue;
                                        }
                                        case 15: {
                                            nEquip.setMpR((short)(nEquip.getMpR() + (int)Integer.valueOf(stat.getValue())));
                                            continue;
                                        }
                                    }
                                }
                                break;
                            }
                            if (nEquip.getState() != 0) {
                                break;
                            }
                            final int chanc = (scrollId.getItemId() == 2049400) ? 90 : 70;
                            if (Randomizer.nextInt(100) > chanc) {
                                return null;
                            }
                            nEquip.resetPotential();
                            break;
                        }
                    }
                }
                if (!GameConstants.isCleanSlate(scrollId.getItemId()) && !GameConstants.isSpecialScroll(scrollId.getItemId()) && !GameConstants.isEquipScroll(scrollId.getItemId()) && !GameConstants.isPotentialScroll(scrollId.getItemId())) {
                    nEquip.setUpgradeSlots((byte)(nEquip.getUpgradeSlots() - 1));
                    nEquip.setLevel((byte)(nEquip.getLevel() + 1));
                }
            }
            else {
                if (!ws && !GameConstants.isCleanSlate(scrollId.getItemId()) && !GameConstants.isSpecialScroll(scrollId.getItemId()) && !GameConstants.isEquipScroll(scrollId.getItemId()) && !GameConstants.isPotentialScroll(scrollId.getItemId())) {
                    nEquip.setUpgradeSlots((byte)(nEquip.getUpgradeSlots() - 1));
                }
                if (Randomizer.nextInt(99) < curse) {
                    return null;
                }
            }
        }
        return equip;
    }
    
    public final IItem getEquipById(final int equipId) {
        return this.getEquipById(equipId, -1);
    }
    
    public final IItem getEquipById(final int equipId, final int ringId) {
        final Equip nEquip = new Equip(equipId, (short)0, ringId, (byte)0);
        nEquip.setQuantity((short)1);
        final Map<String, Integer> stats = this.getEquipStats(equipId);
        if (stats != null) {
            for (final Entry<String, Integer> stat : stats.entrySet()) {
                final String s;
                final String key = s = (String)stat.getKey();
                int n = -1;
                switch (s.hashCode()) {
                    case 82449: {
                        if (s.equals((Object)"STR")) {
                            n = 0;
                            break;
                        }
                        break;
                    }
                    case 67575: {
                        if (s.equals((Object)"DEX")) {
                            n = 1;
                            break;
                        }
                        break;
                    }
                    case 72655: {
                        if (s.equals((Object)"INT")) {
                            n = 2;
                            break;
                        }
                        break;
                    }
                    case 75746: {
                        if (s.equals((Object)"LUK")) {
                            n = 3;
                            break;
                        }
                        break;
                    }
                    case 78963: {
                        if (s.equals((Object)"PAD")) {
                            n = 4;
                            break;
                        }
                        break;
                    }
                    case 79056: {
                        if (s.equals((Object)"PDD")) {
                            n = 5;
                            break;
                        }
                        break;
                    }
                    case 76080: {
                        if (s.equals((Object)"MAD")) {
                            n = 6;
                            break;
                        }
                        break;
                    }
                    case 76173: {
                        if (s.equals((Object)"MDD")) {
                            n = 7;
                            break;
                        }
                        break;
                    }
                    case 64609: {
                        if (s.equals((Object)"ACC")) {
                            n = 8;
                            break;
                        }
                        break;
                    }
                    case 69040: {
                        if (s.equals((Object)"EVA")) {
                            n = 9;
                            break;
                        }
                        break;
                    }
                    case 80089127: {
                        if (s.equals((Object)"Speed")) {
                            n = 10;
                            break;
                        }
                        break;
                    }
                    case 2320462: {
                        if (s.equals((Object)"Jump")) {
                            n = 11;
                            break;
                        }
                        break;
                    }
                    case 76309: {
                        if (s.equals((Object)"MHP")) {
                            n = 12;
                            break;
                        }
                        break;
                    }
                    case 76464: {
                        if (s.equals((Object)"MMP")) {
                            n = 13;
                            break;
                        }
                        break;
                    }
                    case 2365693: {
                        if (s.equals((Object)"MHPr")) {
                            n = 14;
                            break;
                        }
                        break;
                    }
                    case 2370498: {
                        if (s.equals((Object)"MMPr")) {
                            n = 15;
                            break;
                        }
                        break;
                    }
                    case 115202: {
                        if (s.equals((Object)"tuc")) {
                            n = 16;
                            break;
                        }
                        break;
                    }
                    case 65368576: {
                        if (s.equals((Object)"Craft")) {
                            n = 17;
                            break;
                        }
                        break;
                    }
                    case 716086281: {
                        if (s.equals((Object)"durability")) {
                            n = 18;
                            break;
                        }
                        break;
                    }
                }
                switch (n) {
                    case 0: {
                        nEquip.setStr(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 1: {
                        nEquip.setDex(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 2: {
                        nEquip.setInt(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 3: {
                        nEquip.setLuk(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 4: {
                        nEquip.setWatk(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 5: {
                        nEquip.setWdef(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 6: {
                        nEquip.setMatk(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 7: {
                        nEquip.setMdef(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 8: {
                        nEquip.setAcc(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 9: {
                        nEquip.setAvoid(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 10: {
                        nEquip.setSpeed(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 11: {
                        nEquip.setJump(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 12: {
                        nEquip.setHp(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 13: {
                        nEquip.setMp(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 14: {
                        nEquip.setHpR(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 15: {
                        nEquip.setMpR(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 16: {
                        nEquip.setUpgradeSlots(Integer.valueOf(stat.getValue()).byteValue());
                        continue;
                    }
                    case 17: {
                        nEquip.setHands(Integer.valueOf(stat.getValue()).shortValue());
                        continue;
                    }
                    case 18: {
                        nEquip.setDurability((int)Integer.valueOf(stat.getValue()));
                        continue;
                    }
                }
            }
        }
        this.equipCache.put(Integer.valueOf(equipId), nEquip);
        return nEquip.copy();
    }
    
    private short getRandStat(final short defaultValue, final int maxRange) {
        if (defaultValue == 0) {
            return 0;
        }
        final int lMaxRange = (int)Math.min(Math.ceil((double)defaultValue * 0.1), (double)maxRange);
        return (short)(int)((double)(defaultValue - lMaxRange) + Math.floor(Math.random() * (double)(lMaxRange * 2 + 1)));
    }
    
    public final Equip randomizeStats(final Equip equip) {
        equip.setStr(this.getRandStat(equip.getStr(), 5));
        equip.setDex(this.getRandStat(equip.getDex(), 5));
        equip.setInt(this.getRandStat(equip.getInt(), 5));
        equip.setLuk(this.getRandStat(equip.getLuk(), 5));
        equip.setMatk(this.getRandStat(equip.getMatk(), 5));
        equip.setWatk(this.getRandStat(equip.getWatk(), 5));
        equip.setAcc(this.getRandStat(equip.getAcc(), 5));
        equip.setAvoid(this.getRandStat(equip.getAvoid(), 5));
        equip.setJump(this.getRandStat(equip.getJump(), 5));
        equip.setHands(this.getRandStat(equip.getHands(), 5));
        equip.setSpeed(this.getRandStat(equip.getSpeed(), 5));
        equip.setWdef(this.getRandStat(equip.getWdef(), 10));
        equip.setMdef(this.getRandStat(equip.getMdef(), 10));
        equip.setHp(this.getRandStat(equip.getHp(), 10));
        equip.setMp(this.getRandStat(equip.getMp(), 10));
        return equip;
    }
    
    public final Equip randomizeStats(final Equip equip, final int id) {
        switch (id) {
            case 1112413: {
                equip.setStr((short)1);
                equip.setDex((short)1);
                equip.setInt((short)1);
                equip.setLuk((short)1);
                equip.setMatk((short)1);
                equip.setWatk((short)1);
                equip.setHp((short)10);
                equip.setMp((short)10);
                break;
            }
            case 1112414: {
                equip.setStr((short)2);
                equip.setDex((short)2);
                equip.setInt((short)2);
                equip.setLuk((short)2);
                equip.setMatk((short)2);
                equip.setWatk((short)2);
                equip.setHp((short)20);
                equip.setMp((short)20);
                break;
            }
            case 1112405: {
                equip.setStr((short)3);
                equip.setDex((short)3);
                equip.setInt((short)3);
                equip.setLuk((short)3);
                equip.setMatk((short)3);
                equip.setWatk((short)3);
                equip.setHp((short)30);
                equip.setMp((short)30);
                break;
            }
            default: {
                equip.setStr(this.getRandStat(equip.getStr(), 5));
                equip.setDex(this.getRandStat(equip.getDex(), 5));
                equip.setInt(this.getRandStat(equip.getInt(), 5));
                equip.setLuk(this.getRandStat(equip.getLuk(), 5));
                equip.setMatk(this.getRandStat(equip.getMatk(), 5));
                equip.setWatk(this.getRandStat(equip.getWatk(), 5));
                equip.setAcc(this.getRandStat(equip.getAcc(), 5));
                equip.setAvoid(this.getRandStat(equip.getAvoid(), 5));
                equip.setJump(this.getRandStat(equip.getJump(), 5));
                equip.setHands(this.getRandStat(equip.getHands(), 5));
                equip.setSpeed(this.getRandStat(equip.getSpeed(), 5));
                equip.setWdef(this.getRandStat(equip.getWdef(), 10));
                equip.setMdef(this.getRandStat(equip.getMdef(), 10));
                equip.setHp(this.getRandStat(equip.getHp(), 10));
                equip.setMp(this.getRandStat(equip.getMp(), 10));
                break;
            }
        }
        return equip;
    }
    
    public final MapleStatEffect getItemEffect(final int itemId) {
        MapleStatEffect ret = (MapleStatEffect)this.itemEffects.get((Object)Integer.valueOf(itemId));
        if (ret == null) {
            final MapleData item = this.getItemData(itemId);
            if (item == null) {
                return null;
            }
            ret = MapleStatEffect.loadItemEffectFromData(item.getChildByPath("spec"), itemId);
            this.itemEffects.put(Integer.valueOf(itemId), ret);
        }
        return ret;
    }
    
    public final List<Pair<Integer, Integer>> getSummonMobs(final int itemId) {
        if (this.summonMobCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (List<Pair<Integer, Integer>>)this.summonMobCache.get((Object)Integer.valueOf(itemId));
        }
        if (!GameConstants.isSummonSack(itemId)) {
            return null;
        }
        final MapleData data = this.getItemData(itemId).getChildByPath("mob");
        if (data == null) {
            return null;
        }
        final List<Pair<Integer, Integer>> mobPairs = new ArrayList<Pair<Integer, Integer>>();
        for (final MapleData child : data.getChildren()) {
            mobPairs.add(new Pair<Integer, Integer>(Integer.valueOf(MapleDataTool.getIntConvert("id", child)), Integer.valueOf(MapleDataTool.getIntConvert("prob", child))));
        }
        this.summonMobCache.put(Integer.valueOf(itemId), mobPairs);
        return mobPairs;
    }
    
    public final int getCardMobId(final int id) {
        if (id == 0) {
            return 0;
        }
        if (this.monsterBookID.containsKey((Object)Integer.valueOf(id))) {
            return (int)Integer.valueOf(this.monsterBookID.get((Object)Integer.valueOf(id)));
        }
        final MapleData data = this.getItemData(id);
        final int monsterid = MapleDataTool.getIntConvert("info/mob", data, 0);
        if (monsterid == 0) {
            return 0;
        }
        this.monsterBookID.put(Integer.valueOf(id), Integer.valueOf(monsterid));
        return (int)Integer.valueOf(this.monsterBookID.get((Object)Integer.valueOf(id)));
    }
    
    public final int getWatkForProjectile(final int itemId) {
        Integer atk = this.projectileWatkCache.get((Object)Integer.valueOf(itemId));
        if (atk != null) {
            return (int)atk;
        }
        final MapleData data = this.getItemData(itemId);
        atk = Integer.valueOf(MapleDataTool.getInt("info/incPAD", data, 0));
        this.projectileWatkCache.put(Integer.valueOf(itemId), atk);
        return (int)atk;
    }
    
    public final boolean canScroll(final int scrollid, final int itemid) {
        return scrollid / 100 % 100 == itemid / 10000 % 100;
    }
    
    public final String getName(final int itemId) {
        if (this.nameCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (String)this.nameCache.get((Object)Integer.valueOf(itemId));
        }
        final MapleData strings = this.getStringData(itemId);
        if (strings == null) {
            return null;
        }
        final String ret = MapleDataTool.getString("name", strings, "(null)");
        this.nameCache.put(Integer.valueOf(itemId), ret);
        return ret;
    }
    
    public final String getDesc(final int itemId) {
        if (this.descCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (String)this.descCache.get((Object)Integer.valueOf(itemId));
        }
        final MapleData strings = this.getStringData(itemId);
        if (strings == null) {
            return null;
        }
        final String ret = MapleDataTool.getString("desc", strings, null);
        this.descCache.put(Integer.valueOf(itemId), ret);
        return ret;
    }
    
    public final String getMsg(final int itemId) {
        if (this.msgCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (String)this.msgCache.get((Object)Integer.valueOf(itemId));
        }
        final MapleData strings = this.getStringData(itemId);
        if (strings == null) {
            return null;
        }
        final String ret = MapleDataTool.getString("msg", strings, null);
        this.msgCache.put(Integer.valueOf(itemId), ret);
        return ret;
    }
    
    public final short getItemMakeLevel(final int itemId) {
        if (this.itemMakeLevel.containsKey((Object)Integer.valueOf(itemId))) {
            return (short)Short.valueOf(this.itemMakeLevel.get((Object)Integer.valueOf(itemId)));
        }
        if (itemId / 10000 != 400) {
            return 0;
        }
        final short lvl = (short)MapleDataTool.getIntConvert("info/lv", this.getItemData(itemId), 0);
        this.itemMakeLevel.put(Integer.valueOf(itemId), Short.valueOf(lvl));
        return lvl;
    }
    
    public final byte isConsumeOnPickup(final int itemId) {
        if (this.consumeOnPickupCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (byte)Byte.valueOf(this.consumeOnPickupCache.get((Object)Integer.valueOf(itemId)));
        }
        final MapleData data = this.getItemData(itemId);
        byte consume = (byte)MapleDataTool.getIntConvert("spec/consumeOnPickup", data, 0);
        if (consume == 0) {
            consume = (byte)MapleDataTool.getIntConvert("specEx/consumeOnPickup", data, 0);
        }
        if (consume == 1 && MapleDataTool.getIntConvert("spec/party", this.getItemData(itemId), 0) > 0) {
            consume = 2;
        }
        this.consumeOnPickupCache.put(Integer.valueOf(itemId), Byte.valueOf(consume));
        return consume;
    }
    
    public final boolean isDropRestricted(final int itemId) {
        if (this.dropRestrictionCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (boolean)Boolean.valueOf(this.dropRestrictionCache.get((Object)Integer.valueOf(itemId)));
        }
        final MapleData data = this.getItemData(itemId);
        boolean trade = false;
        if (MapleDataTool.getIntConvert("info/tradeBlock", data, 0) == 1 || MapleDataTool.getIntConvert("info/quest", data, 0) == 1) {
            trade = true;
        }
        this.dropRestrictionCache.put(Integer.valueOf(itemId), Boolean.valueOf(trade));
        return trade;
    }
    
    public final boolean isPickupRestricted(final int itemId) {
        if (this.pickupRestrictionCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (boolean)Boolean.valueOf(this.pickupRestrictionCache.get((Object)Integer.valueOf(itemId)));
        }
        final boolean bRestricted = MapleDataTool.getIntConvert("info/only", this.getItemData(itemId), 0) == 1;
        this.pickupRestrictionCache.put(Integer.valueOf(itemId), Boolean.valueOf(bRestricted));
        return bRestricted;
    }
    
    public final boolean isAccountShared(final int itemId) {
        if (this.accCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (boolean)Boolean.valueOf(this.accCache.get((Object)Integer.valueOf(itemId)));
        }
        final boolean bRestricted = MapleDataTool.getIntConvert("info/accountSharable", this.getItemData(itemId), 0) == 1;
        this.accCache.put(Integer.valueOf(itemId), Boolean.valueOf(bRestricted));
        return bRestricted;
    }
    
    public final int getStateChangeItem(final int itemId) {
        if (this.stateChangeCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (int)Integer.valueOf(this.stateChangeCache.get((Object)Integer.valueOf(itemId)));
        }
        final int triggerItem = MapleDataTool.getIntConvert("info/stateChangeItem", this.getItemData(itemId), 0);
        this.stateChangeCache.put(Integer.valueOf(itemId), Integer.valueOf(triggerItem));
        return triggerItem;
    }
    
    public final int getMeso(final int itemId) {
        if (this.mesoCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (int)Integer.valueOf(this.mesoCache.get((Object)Integer.valueOf(itemId)));
        }
        final int triggerItem = MapleDataTool.getIntConvert("info/meso", this.getItemData(itemId), 0);
        this.mesoCache.put(Integer.valueOf(itemId), Integer.valueOf(triggerItem));
        return triggerItem;
    }
    
    public final boolean isKarmaEnabled(final int itemId) {
        if (this.karmaEnabledCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (int)Integer.valueOf(this.karmaEnabledCache.get((Object)Integer.valueOf(itemId))) == 1;
        }
        final int iRestricted = MapleDataTool.getIntConvert("info/tradeAvailable", this.getItemData(itemId), 0);
        this.karmaEnabledCache.put(Integer.valueOf(itemId), Integer.valueOf(iRestricted));
        return iRestricted == 1;
    }
    
    public final boolean isPKarmaEnabled(final int itemId) {
        if (this.karmaEnabledCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (int)Integer.valueOf(this.karmaEnabledCache.get((Object)Integer.valueOf(itemId))) == 2;
        }
        final int iRestricted = MapleDataTool.getIntConvert("info/tradeAvailable", this.getItemData(itemId), 0);
        this.karmaEnabledCache.put(Integer.valueOf(itemId), Integer.valueOf(iRestricted));
        return iRestricted == 2;
    }
    
    public final boolean isPickupBlocked(final int itemId) {
        if (this.blockPickupCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (boolean)Boolean.valueOf(this.blockPickupCache.get((Object)Integer.valueOf(itemId)));
        }
        final boolean iRestricted = MapleDataTool.getIntConvert("info/pickUpBlock", this.getItemData(itemId), 0) == 1;
        this.blockPickupCache.put(Integer.valueOf(itemId), Boolean.valueOf(iRestricted));
        return iRestricted;
    }
    
    public final boolean isLogoutExpire(final int itemId) {
        if (this.logoutExpireCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (boolean)Boolean.valueOf(this.logoutExpireCache.get((Object)Integer.valueOf(itemId)));
        }
        final boolean iRestricted = MapleDataTool.getIntConvert("info/expireOnLogout", this.getItemData(itemId), 0) == 1;
        this.logoutExpireCache.put(Integer.valueOf(itemId), Boolean.valueOf(iRestricted));
        return iRestricted;
    }
    
    public final boolean cantSell(final int itemId) {
        if (this.notSaleCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (boolean)Boolean.valueOf(this.notSaleCache.get((Object)Integer.valueOf(itemId)));
        }
        final boolean bRestricted = MapleDataTool.getIntConvert("info/notSale", this.getItemData(itemId), 0) == 1;
        this.notSaleCache.put(Integer.valueOf(itemId), Boolean.valueOf(bRestricted));
        return bRestricted;
    }
    
    public final void loadStyles(final boolean reload) {
        if (reload) {
            this.faceList.clear();
        }
        if (!this.faceList.isEmpty()) {
            return;
        }
        final String[] array;
        final String[] types = array = new String[] { "Face" };
        for (final String type : array) {
            for (final MapleData c : this.stringData.getData("Eqp.img").getChildByPath("Eqp/" + type)) {
                if (this.equipData.getData(type + "/" + StringUtil.getLeftPaddedStr(c.getName() + ".img", '0', 12)) != null) {
                    final int dataid = Integer.parseInt(c.getName());
                    final String name = MapleDataTool.getString("name", c, "無名稱");
                    if (!type.equals((Object)"Face")) {
                        continue;
                    }
                    this.faceList.put(Integer.valueOf(dataid), name);
                }
            }
        }
    }
    
    public boolean faceExists(final int face) {
        return this.faceList.containsKey((Object)Integer.valueOf(face));
    }
    
    public final Map<Integer, String> getFaceList() {
        final Map<Integer, String> list = new HashMap<Integer, String>();
        list.putAll((Map<? extends Integer, ? extends String>)this.faceList);
        return list;
    }
    
    public final Pair<Integer, List<StructRewardItem>> getRewardItem(final int itemid) {
        if (this.RewardItem.containsKey((Object)Integer.valueOf(itemid))) {
            return (Pair<Integer, List<StructRewardItem>>)this.RewardItem.get((Object)Integer.valueOf(itemid));
        }
        final MapleData data = this.getItemData(itemid);
        if (data == null) {
            return null;
        }
        final MapleData rewards = data.getChildByPath("reward");
        if (rewards == null) {
            return null;
        }
        int totalprob = 0;
        final List<StructRewardItem> all = new ArrayList<StructRewardItem>();
        for (final MapleData reward : rewards) {
            final StructRewardItem struct = new StructRewardItem();
            struct.setItemid(MapleDataTool.getInt("item", reward, 0));
            struct.setProb((short)MapleDataTool.getInt("prob", reward, 0));
            struct.setQuantity((short)MapleDataTool.getInt("count", reward, 0));
            struct.setEffect(MapleDataTool.getString("Effect", reward, ""));
            struct.setWorldmsg(MapleDataTool.getString("worldMsg", reward, null));
            struct.setPeriod((long)MapleDataTool.getInt("period", reward, -1));
            totalprob += struct.getProb();
            all.add(struct);
        }
        final Pair<Integer, List<StructRewardItem>> toreturn = new Pair<Integer, List<StructRewardItem>>(Integer.valueOf(totalprob), all);
        this.RewardItem.put(Integer.valueOf(itemid), toreturn);
        return toreturn;
    }
    
    public final Map<String, Integer> getSkillStats(final int itemId) {
        if (this.SkillStatsCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (Map<String, Integer>)this.SkillStatsCache.get((Object)Integer.valueOf(itemId));
        }
        if (itemId / 10000 != 228 && itemId / 10000 != 229 && itemId / 10000 != 562) {
            return null;
        }
        final MapleData item = this.getItemData(itemId);
        if (item == null) {
            return null;
        }
        final MapleData info = item.getChildByPath("info");
        if (info == null) {
            return null;
        }
        final Map<String, Integer> ret = new LinkedHashMap<String, Integer>();
        for (final MapleData data : info.getChildren()) {
            if (data.getName().startsWith("inc")) {
                ret.put(data.getName().substring(3), Integer.valueOf(MapleDataTool.getIntConvert(data)));
            }
        }
        ret.put("masterLevel", Integer.valueOf(MapleDataTool.getInt("masterLevel", info, 0)));
        ret.put("reqSkillLevel", Integer.valueOf(MapleDataTool.getInt("reqSkillLevel", info, 0)));
        ret.put("success", Integer.valueOf(MapleDataTool.getInt("success", info, 0)));
        final MapleData skill = info.getChildByPath("skill");
        for (int i = 0; i < skill.getChildren().size(); ++i) {
            ret.put("skillid" + i, Integer.valueOf(MapleDataTool.getInt(Integer.toString(i), skill, 0)));
        }
        this.SkillStatsCache.put(Integer.valueOf(itemId), ret);
        return ret;
    }
    
    public final List<Integer> petsCanConsume(final int itemId) {
        if (this.petsCanConsumeCache.get((Object)Integer.valueOf(itemId)) != null) {
            return (List<Integer>)this.petsCanConsumeCache.get((Object)Integer.valueOf(itemId));
        }
        final List<Integer> ret = new ArrayList<Integer>();
        final MapleData data = this.getItemData(itemId);
        if (data == null || data.getChildByPath("spec") == null) {
            return ret;
        }
        for (final MapleData c : data.getChildByPath("spec")) {
            try {
                Integer.parseInt(c.getName());
            }
            catch (NumberFormatException e) {
                continue;
            }
            final int curPetId = MapleDataTool.getInt(c, 0);
            if (curPetId == 0) {
                break;
            }
            ret.add(Integer.valueOf(curPetId));
        }
        this.petsCanConsumeCache.put(Integer.valueOf(itemId), ret);
        return ret;
    }
    
    public final boolean isQuestItem(final int itemId) {
        if (this.isQuestItemCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (boolean)Boolean.valueOf(this.isQuestItemCache.get((Object)Integer.valueOf(itemId)));
        }
        final boolean questItem = MapleDataTool.getIntConvert("info/quest", this.getItemData(itemId), 0) == 1;
        this.isQuestItemCache.put(Integer.valueOf(itemId), Boolean.valueOf(questItem));
        return questItem;
    }
    
    public final Pair<Integer, List<Integer>> questItemInfo(final int itemId) {
        if (this.questItems.containsKey((Object)Integer.valueOf(itemId))) {
            return (Pair<Integer, List<Integer>>)this.questItems.get((Object)Integer.valueOf(itemId));
        }
        if (itemId / 10000 != 422 || this.getItemData(itemId) == null) {
            return null;
        }
        final MapleData itemD = this.getItemData(itemId).getChildByPath("info");
        if (itemD == null || itemD.getChildByPath("consumeItem") == null) {
            return null;
        }
        final List<Integer> consumeItems = new ArrayList<Integer>();
        for (final MapleData consume : itemD.getChildByPath("consumeItem")) {
            consumeItems.add(Integer.valueOf(MapleDataTool.getInt(consume, 0)));
        }
        final Pair<Integer, List<Integer>> questItem = new Pair<Integer, List<Integer>>(Integer.valueOf(MapleDataTool.getIntConvert("questId", itemD, 0)), consumeItems);
        this.questItems.put(Integer.valueOf(itemId), questItem);
        return questItem;
    }
    
    public final boolean itemExists(final int itemId) {
        return GameConstants.getInventoryType(itemId) != MapleInventoryType.UNDEFINED && this.getItemData(itemId) != null;
    }
    
    public final boolean isCash(final int itemId) {
        if (this.getEquipStats(itemId) == null) {
            return GameConstants.getInventoryType(itemId) == MapleInventoryType.CASH;
        }
        return GameConstants.getInventoryType(itemId) == MapleInventoryType.CASH || (int)Integer.valueOf(this.getEquipStats(itemId).get((Object)"cash")) > 0;
    }
    
    public int getPetLimitLife(final int itemid) {
        if (this.petLimitLifeInfo.containsKey((Object)Integer.valueOf(itemid))) {
            return (int)Integer.valueOf(this.petLimitLifeInfo.get((Object)Integer.valueOf(itemid)));
        }
        if (!GameConstants.isPet(itemid)) {
            return 0;
        }
        final MapleData item = this.getItemData(itemid);
        final int limitLife = MapleDataTool.getIntConvert("info/limitedLife", item, 0);
        this.petLimitLifeInfo.put(Integer.valueOf(itemid), Integer.valueOf(limitLife));
        return limitLife;
    }
    
    public int getPetLife(final int itemid) {
        if (this.petLifeInfo.containsKey((Object)Integer.valueOf(itemid))) {
            return (int)Integer.valueOf(this.petLifeInfo.get((Object)Integer.valueOf(itemid)));
        }
        if (!GameConstants.isPet(itemid)) {
            return 0;
        }
        final MapleData item = this.getItemData(itemid);
        final int life = MapleDataTool.getIntConvert("info/life", item, 0);
        this.petLifeInfo.put(Integer.valueOf(itemid), Integer.valueOf(life));
        return life;
    }
    
    public short getPetFlagInfo(final int itemId) {
        if (this.petFlagInfo.containsKey((Object)Integer.valueOf(itemId))) {
            return (short)Short.valueOf(this.petFlagInfo.get((Object)Integer.valueOf(itemId)));
        }
        short flag = 0;
        if (!GameConstants.isPet(itemId)) {
            return flag;
        }
        final MapleData item = this.getItemData(itemId);
        if (item == null) {
            return flag;
        }
        if (MapleDataTool.getIntConvert("info/pickupItem", item, 0) > 0) {
            flag |= (short)PetFlag.ITEM_PICKUP.getValue();
        }
        if (MapleDataTool.getIntConvert("info/longRange", item, 0) > 0) {
            flag |= (short)PetFlag.EXPAND_PICKUP.getValue();
        }
        if (MapleDataTool.getIntConvert("info/pickupAll", item, 0) > 0) {
            flag |= (short)PetFlag.AUTO_PICKUP.getValue();
        }
        if (MapleDataTool.getIntConvert("info/sweepForDrop", item, 0) > 0) {
            flag |= (short)PetFlag.LEFTOVER_PICKUP.getValue();
        }
        if (MapleDataTool.getIntConvert("info/consumeHP", item, 0) > 0) {
            flag |= (short)PetFlag.HP_CHARGE.getValue();
        }
        if (MapleDataTool.getIntConvert("info/consumeMP", item, 0) > 0) {
            flag |= (short)PetFlag.MP_CHARGE.getValue();
        }
        this.petFlagInfo.put(Integer.valueOf(itemId), Short.valueOf(flag));
        return flag;
    }
    
    public final boolean isOnlyTradeBlock(final int itemId) {
        final MapleData data = this.getItemData(itemId);
        boolean tradeblock = false;
        if (MapleDataTool.getIntConvert("info/tradeBlock", data, 0) == 1) {
            tradeblock = true;
        }
        return tradeblock;
    }
    
    public static void loadFaceHair() {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            try (final PreparedStatement ps = con.prepareStatement("SELECT * FROM wz_hairdata ORDER BY `hairid`");
                 final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MapleItemInformationProvider.hairList.put(Integer.valueOf(rs.getInt("hairid")), rs.getString("name"));
                }
                ps.close();
            }
            catch (SQLException ex) {
                System.out.println("读取发型数据失败：" + (Object)ex);
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
            try (final PreparedStatement ps = con.prepareStatement("SELECT * FROM wz_facedata ORDER BY `faceid`");
                 final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MapleItemInformationProvider.faceLists.put(Integer.valueOf(rs.getInt("faceid")), rs.getString("name"));
                }
                ps.close();
            }
            catch (SQLException ex) {
                System.out.println("读取脸型数据失败：" + (Object)ex);
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
        }
        catch (SQLException ex2) {
            System.out.println("读取脸型数据失败：" + (Object)ex2);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex2);
        }
    }
    
    public MapleInventoryType getInventoryTypeCS(final int itemId) {
        if (this.inventoryTypeCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (MapleInventoryType)this.inventoryTypeCache.get((Object)Integer.valueOf(itemId));
        }
        final String idStr = "0" + String.valueOf(itemId);
        MapleDataDirectoryEntry root = this.itemData.getRoot();
        for (final MapleDataDirectoryEntry topDir : root.getSubdirectories()) {
            for (final MapleDataFileEntry iFile : topDir.getFiles()) {
                if (iFile.getName().equals((Object)(idStr.substring(0, 4) + ".img"))) {
                    final MapleInventoryType ret = MapleInventoryType.getByWZName(topDir.getName());
                    this.inventoryTypeCache.put(Integer.valueOf(itemId), ret);
                    return ret;
                }
                if (iFile.getName().equals((Object)(idStr.substring(1) + ".img"))) {
                    final MapleInventoryType ret = MapleInventoryType.getByWZName(topDir.getName());
                    this.inventoryTypeCache.put(Integer.valueOf(itemId), ret);
                    return ret;
                }
            }
        }
        root = this.equipData.getRoot();
        for (final MapleDataDirectoryEntry topDir : root.getSubdirectories()) {
            for (final MapleDataFileEntry iFile : topDir.getFiles()) {
                if (iFile.getName().equals((Object)(idStr + ".img"))) {
                    final MapleInventoryType ret = MapleInventoryType.EQUIP;
                    this.inventoryTypeCache.put(Integer.valueOf(itemId), ret);
                    return ret;
                }
            }
        }
        final MapleInventoryType ret = MapleInventoryType.UNDEFINED;
        this.inventoryTypeCache.put(Integer.valueOf(itemId), ret);
        return ret;
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
        else if (GameConstants.getInventoryType(itemId) == MapleInventoryType.CASH || getInstance().isCash(itemId)) {
            uniqueid = MapleInventoryIdentifier.getInstance();
        }
        return uniqueid;
    }
    
    public final int getChairMountId(final int itemId) {
        if (this.chairMountId.containsKey((Object)Integer.valueOf(itemId))) {
            return (int)Integer.valueOf(this.chairMountId.get((Object)Integer.valueOf(itemId)));
        }
        int ret = 0;
        final MapleData item = this.getItemData(itemId);
        if (item != null) {
            final MapleData smEntry = item.getChildByPath("info/tamingMob");
            if (smEntry == null) {
                ret = 0;
            }
            else {
                ret = MapleDataTool.getInt(smEntry);
            }
        }
        this.chairMountId.put(Integer.valueOf(itemId), Integer.valueOf(ret));
        return ret;
    }
    
    public MapleInventoryType getInventoryType(final int itemId) {
        if (this.inventoryTypeCache.containsKey((Object)Integer.valueOf(itemId))) {
            return (MapleInventoryType)this.inventoryTypeCache.get((Object)Integer.valueOf(itemId));
        }
        final String idStr = "0" + String.valueOf(itemId);
        MapleDataDirectoryEntry root = this.itemData.getRoot();
        for (final MapleDataDirectoryEntry topDir : root.getSubdirectories()) {
            for (final MapleDataFileEntry iFile : topDir.getFiles()) {
                if (iFile.getName().equals((Object)(idStr.substring(0, 4) + ".img"))) {
                    final MapleInventoryType ret = MapleInventoryType.getByWZName(topDir.getName());
                    this.inventoryTypeCache.put(Integer.valueOf(itemId), ret);
                    return ret;
                }
                if (iFile.getName().equals((Object)(idStr.substring(1) + ".img"))) {
                    final MapleInventoryType ret = MapleInventoryType.getByWZName(topDir.getName());
                    this.inventoryTypeCache.put(Integer.valueOf(itemId), ret);
                    return ret;
                }
            }
        }
        root = this.equipData.getRoot();
        for (final MapleDataDirectoryEntry topDir : root.getSubdirectories()) {
            for (final MapleDataFileEntry iFile : topDir.getFiles()) {
                if (iFile.getName().equals((Object)(idStr + ".img"))) {
                    final MapleInventoryType ret = MapleInventoryType.EQUIP;
                    this.inventoryTypeCache.put(Integer.valueOf(itemId), ret);
                    return ret;
                }
            }
        }
        final MapleInventoryType ret = MapleInventoryType.UNDEFINED;
        this.inventoryTypeCache.put(Integer.valueOf(itemId), ret);
        return ret;
    }
    
    public int getExpCache(final int itemId) {
        final MapleData item = this.getItemData(itemId);
        if (item == null) {
            return 0;
        }
        int pEntry = 0;
        final MapleData pData = item.getChildByPath("spec/exp");
        if (pData == null) {
            return 0;
        }
        pEntry = MapleDataTool.getInt(pData);
        return pEntry;
    }
    
    static {
        instance = new MapleItemInformationProvider();
        faceLists = new HashMap<Integer, String>();
        hairList = new HashMap<Integer, String>();
    }
}
