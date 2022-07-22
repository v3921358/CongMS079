package constants;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;
import server.MapleItemInformationProvider;
import client.MapleCharacter;
import client.inventory.MapleInventoryType;
import client.inventory.Equip;
import client.MapleClient;
import client.inventory.MapleWeaponType;

public class ItemConstants
{
    public static MapleWeaponType 武器類型(final int itemid) {
        if (類型.閃亮克魯(itemid)) {
            return MapleWeaponType.閃亮克魯;
        }
        if (類型.靈魂射手(itemid)) {
            return MapleWeaponType.靈魂射手;
        }
        if (類型.魔劍(itemid)) {
            return MapleWeaponType.魔劍;
        }
        if (類型.能量劍(itemid)) {
            return MapleWeaponType.能量劍;
        }
        if (類型.幻獸棍棒(itemid)) {
            return MapleWeaponType.幻獸棍棒;
        }
        if (類型.ESP限制器(itemid)) {
            return MapleWeaponType.ESP限制器;
        }
        if (類型.單手劍(itemid)) {
            return MapleWeaponType.單手劍;
        }
        if (類型.單手斧(itemid)) {
            return MapleWeaponType.單手斧;
        }
        if (類型.單手棍(itemid)) {
            return MapleWeaponType.單手棍;
        }
        if (類型.短劍(itemid)) {
            return MapleWeaponType.短劍;
        }
        if (類型.雙刀(itemid)) {
            return MapleWeaponType.雙刀;
        }
        if (類型.手杖(itemid)) {
            return MapleWeaponType.手杖;
        }
        if (類型.短杖(itemid)) {
            return MapleWeaponType.短杖;
        }
        if (類型.長杖(itemid)) {
            return MapleWeaponType.長杖;
        }
        if (類型.雙手劍(itemid)) {
            return MapleWeaponType.雙手劍;
        }
        if (類型.雙手斧(itemid)) {
            return MapleWeaponType.雙手斧;
        }
        if (類型.雙手棍(itemid)) {
            return MapleWeaponType.雙手棍;
        }
        if (類型.槍(itemid)) {
            return MapleWeaponType.槍;
        }
        if (類型.矛(itemid)) {
            return MapleWeaponType.矛;
        }
        if (類型.弓(itemid)) {
            return MapleWeaponType.弓;
        }
        if (類型.弩(itemid)) {
            return MapleWeaponType.弩;
        }
        if (類型.拳套(itemid)) {
            return MapleWeaponType.拳套;
        }
        if (類型.指虎(itemid)) {
            return MapleWeaponType.指虎;
        }
        if (類型.火槍(itemid)) {
            return MapleWeaponType.火槍;
        }
        if (類型.雙弩槍(itemid)) {
            return MapleWeaponType.雙弩槍;
        }
        if (類型.加農炮(itemid)) {
            return MapleWeaponType.加農炮;
        }
        if (類型.太刀(itemid)) {
            return MapleWeaponType.太刀;
        }
        if (類型.扇子(itemid)) {
            return MapleWeaponType.扇子;
        }
        if (類型.琉(itemid)) {
            return MapleWeaponType.琉;
        }
        if (類型.璃(itemid)) {
            return MapleWeaponType.璃;
        }
        return MapleWeaponType.未知;
    }
    
    public static byte gachaponRareItem(final int itemid) {
        switch (itemid) {
            case 1022082:
            case 1072238:
            case 1102041:
            case 1102042:
            case 1302147:
            case 1312062:
            case 1322090:
            case 1332120:
            case 1332125:
            case 1342033:
            case 1372078:
            case 1382099:
            case 1402090:
            case 1412062:
            case 1422063:
            case 1432081:
            case 1442111:
            case 1452106:
            case 1462091:
            case 1472117:
            case 1482079:
            case 1492079:
            case 2040834:
            case 2049100:
            case 2340000:
            case 3010014:
            case 3010043:
            case 3010068:
            case 3010072:
            case 3010073:
            case 3010085:
            case 3010118:
            case 3010124:
            case 3010125:
            case 3010131:
            case 3010137:
            case 3010156:
            case 3010592:
            case 3010602:
            case 3010615:
            case 3010670:
            case 3010728:
            case 5062002:
            case 5062003:
            case 5062005: {
                return 2;
            }
            default: {
                return 0;
            }
        }
    }
    
    public static boolean isOverPoweredEquip(final MapleClient c, final int itemId, final short slot) {
        final Equip source = (Equip)c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem(slot);
        return source.getAcc() > 1000 || source.getAvoid() > 1000 || source.getStr() > 500 || source.getDex() > 500 || source.getInt() > 500 || source.getLuk() > 500 || source.getEnhance() > 25 || source.getHands() > 100 || source.getHp() > 5000 || source.getMp() > 5000 || source.getJump() > 100 || source.getSpeed() > 100 || source.getMatk() > 1000 || source.getMdef() > 1500 || source.getUpgradeSlots() > 32 || source.getViciousHammer() > 1 || source.getWatk() > 1000 || source.getWdef() > 1500;
    }
    
    public static boolean isForGM(final int itemid) {
        return (itemid >= 2049335 && itemid <= 2049349) || itemid == 2430011 || itemid == 2430012 || itemid == 2430124 || itemid == 2002085;
    }
    
    public static boolean isMadeByGM(final MapleClient c, final int itemId, final short slot) {
        final Equip source = (Equip)c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem(slot);
        final MapleCharacter gm = c.getChannelServer().getPlayerStorage().getCharacterByName(source.getOwner());
        return source.getOwner() != null && !source.getOwner().isEmpty() && gm != null && gm.isStaff();
    }
    
    public static int getEffectItemID(final int itemId) {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final Map<String, Integer> stats = ii.getEquipStats(itemId);
        if (stats.containsKey((Object)"effectItemID")) {
            return (int)Integer.valueOf(stats.get((Object)"effectItemID"));
        }
        return 0;
    }
    
    public static short[] getEquipedSlot(final int itemId) {
        final boolean isCash = MapleItemInformationProvider.getInstance().isCash(itemId);
        if (類型.帽子(itemId)) {
            if (isCash) {
                return new short[] { -101 };
            }
            return new short[] { -1 };
        }
        else if (類型.臉飾(itemId)) {
            if (isCash) {
                return new short[] { -102 };
            }
            return new short[] { -2 };
        }
        else if (類型.眼飾(itemId)) {
            if (isCash) {
                return new short[] { -103 };
            }
            return new short[] { -3 };
        }
        else if (類型.耳環(itemId)) {
            if (isCash) {
                return new short[] { -104 };
            }
            return new short[] { -4 };
        }
        else if (類型.上衣(itemId) || 類型.套服(itemId)) {
            if (isCash) {
                return new short[] { -105 };
            }
            return new short[] { -5 };
        }
        else if (類型.褲裙(itemId)) {
            if (isCash) {
                return new short[] { -106 };
            }
            return new short[] { -6 };
        }
        else if (類型.鞋子(itemId)) {
            if (isCash) {
                return new short[] { -107 };
            }
            return new short[] { -7 };
        }
        else if (類型.手套(itemId)) {
            if (isCash) {
                return new short[] { -108 };
            }
            return new short[] { -8 };
        }
        else if (類型.披風(itemId)) {
            if (isCash) {
                return new short[] { -109 };
            }
            return new short[] { -9 };
        }
        else if (類型.副手(itemId)) {
            if (isCash) {
                return new short[] { -110 };
            }
            return new short[] { -10 };
        }
        else if (類型.武器(itemId)) {
            if (isCash) {
                return new short[] { -111 };
            }
            return new short[] { -11 };
        }
        else if (類型.戒指(itemId)) {
            if (isCash) {
                return new short[] { -112, -113, -115, -116 };
            }
            return new short[] { -12, -13, -15, -16 };
        }
        else {
            if (類型.墜飾(itemId)) {
                return new short[] { -17, -36 };
            }
            if (類型.騎寵(itemId)) {
                return new short[] { -18 };
            }
            if (類型.馬鞍(itemId)) {
                return new short[] { -19 };
            }
            if (類型.勳章(itemId)) {
                return new short[] { -21 };
            }
            if (類型.腰帶(itemId)) {
                return new short[] { -22 };
            }
            if (類型.肩飾(itemId)) {
                return new short[] { -28 };
            }
            if (類型.口袋道具(itemId)) {
                return new short[] { -31 };
            }
            if (類型.機器人(itemId)) {
                return new short[] { -32 };
            }
            if (類型.心臟(itemId)) {
                return new short[] { -33 };
            }
            if (類型.胸章(itemId)) {
                return new short[] { -34 };
            }
            if (類型.能源(itemId)) {
                return new short[] { -35 };
            }
            if (類型.寵物装备(itemId)) {
                return new short[] { -114, -124, -126 };
            }
            if (類型.龍面具(itemId)) {
                return new short[] { -1000 };
            }
            if (類型.龍墜飾(itemId)) {
                return new short[] { -1001 };
            }
            if (類型.龍之翼(itemId)) {
                return new short[] { -1002 };
            }
            if (類型.龍尾巴(itemId)) {
                return new short[] { -1003 };
            }
            if (類型.引擎(itemId)) {
                return new short[] { -1100 };
            }
            if (類型.手臂(itemId)) {
                return new short[] { -1101 };
            }
            if (類型.腿(itemId)) {
                return new short[] { -1102 };
            }
            if (類型.機殼(itemId)) {
                return new short[] { -1103 };
            }
            if (類型.晶體管(itemId)) {
                return new short[] { -1104 };
            }
            if (類型.图騰(itemId)) {
                return new short[] { -5000, -5001, -5002 };
            }
            return new short[0];
        }
    }
    
    public static boolean sub_609CDE(final int slot, final int type) {
        return type - 3 <= 1 && slot >= 0 && slot < sub_5015E5(type);
    }
    
    public static int sub_5015E5(final int type) {
        if (type == 3) {
            return 2;
        }
        if (type == 4) {
            return 6;
        }
        return 0;
    }
    
    public static boolean is透明短刀(final int itemID) {
        switch (itemID) {
            case 1342069: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static class 伤害字型
    {
        private static Map<Integer, Integer> damageSkin;
        
        public static Map<Integer, Integer> getDamageSkin() {
            if (伤害字型.damageSkin.isEmpty()) {
                伤害字型.damageSkin.put(Integer.valueOf(2431965), Integer.valueOf(0));
                伤害字型.damageSkin.put(Integer.valueOf(2431966), Integer.valueOf(1));
                伤害字型.damageSkin.put(Integer.valueOf(2432084), Integer.valueOf(1));
                伤害字型.damageSkin.put(Integer.valueOf(2431967), Integer.valueOf(2));
                伤害字型.damageSkin.put(Integer.valueOf(2432131), Integer.valueOf(3));
                伤害字型.damageSkin.put(Integer.valueOf(2432153), Integer.valueOf(4));
                伤害字型.damageSkin.put(Integer.valueOf(2432638), Integer.valueOf(4));
                伤害字型.damageSkin.put(Integer.valueOf(2432659), Integer.valueOf(4));
                伤害字型.damageSkin.put(Integer.valueOf(2432154), Integer.valueOf(5));
                伤害字型.damageSkin.put(Integer.valueOf(2432637), Integer.valueOf(5));
                伤害字型.damageSkin.put(Integer.valueOf(2432658), Integer.valueOf(5));
                伤害字型.damageSkin.put(Integer.valueOf(2432207), Integer.valueOf(6));
                伤害字型.damageSkin.put(Integer.valueOf(2432354), Integer.valueOf(7));
                伤害字型.damageSkin.put(Integer.valueOf(2432355), Integer.valueOf(8));
                伤害字型.damageSkin.put(Integer.valueOf(2432972), Integer.valueOf(8));
                伤害字型.damageSkin.put(Integer.valueOf(2432465), Integer.valueOf(9));
                伤害字型.damageSkin.put(Integer.valueOf(2432479), Integer.valueOf(10));
                伤害字型.damageSkin.put(Integer.valueOf(2432526), Integer.valueOf(11));
                伤害字型.damageSkin.put(Integer.valueOf(2432639), Integer.valueOf(11));
                伤害字型.damageSkin.put(Integer.valueOf(2432660), Integer.valueOf(11));
                伤害字型.damageSkin.put(Integer.valueOf(2432532), Integer.valueOf(12));
                伤害字型.damageSkin.put(Integer.valueOf(2432592), Integer.valueOf(13));
                伤害字型.damageSkin.put(Integer.valueOf(2432640), Integer.valueOf(14));
                伤害字型.damageSkin.put(Integer.valueOf(2432661), Integer.valueOf(14));
                伤害字型.damageSkin.put(Integer.valueOf(2432710), Integer.valueOf(15));
                伤害字型.damageSkin.put(Integer.valueOf(2432836), Integer.valueOf(16));
                伤害字型.damageSkin.put(Integer.valueOf(2432973), Integer.valueOf(17));
                伤害字型.damageSkin.put(Integer.valueOf(2433063), Integer.valueOf(18));
                伤害字型.damageSkin.put(Integer.valueOf(2433456), Integer.valueOf(19));
                伤害字型.damageSkin.put(Integer.valueOf(2433178), Integer.valueOf(20));
                伤害字型.damageSkin.put(Integer.valueOf(2433631), Integer.valueOf(22));
                伤害字型.damageSkin.put(Integer.valueOf(2433655), Integer.valueOf(22));
                伤害字型.damageSkin.put(Integer.valueOf(2433981), Integer.valueOf(28));
                伤害字型.damageSkin.put(Integer.valueOf(2432591), Integer.valueOf(1000));
                伤害字型.damageSkin.put(Integer.valueOf(2432803), Integer.valueOf(1004));
                伤害字型.damageSkin.put(Integer.valueOf(2432804), Integer.valueOf(1004));
                伤害字型.damageSkin.put(Integer.valueOf(2432846), Integer.valueOf(1005));
                伤害字型.damageSkin.put(Integer.valueOf(2433049), Integer.valueOf(1009));
                伤害字型.damageSkin.put(Integer.valueOf(2433038), Integer.valueOf(1010));
                伤害字型.damageSkin.put(Integer.valueOf(2433165), Integer.valueOf(1011));
                伤害字型.damageSkin.put(Integer.valueOf(2433197), Integer.valueOf(1012));
                伤害字型.damageSkin.put(Integer.valueOf(2433195), Integer.valueOf(1013));
                伤害字型.damageSkin.put(Integer.valueOf(2433182), Integer.valueOf(1014));
                伤害字型.damageSkin.put(Integer.valueOf(2433183), Integer.valueOf(1015));
                伤害字型.damageSkin.put(Integer.valueOf(2433184), Integer.valueOf(1016));
                伤害字型.damageSkin.put(Integer.valueOf(2433775), Integer.valueOf(1032));
                伤害字型.damageSkin.put(Integer.valueOf(2433776), Integer.valueOf(1033));
                伤害字型.damageSkin.put(Integer.valueOf(2433828), Integer.valueOf(1034));
                伤害字型.damageSkin.put(Integer.valueOf(2433829), Integer.valueOf(1035));
                伤害字型.damageSkin.put(Integer.valueOf(2433830), Integer.valueOf(1036));
                伤害字型.damageSkin.put(Integer.valueOf(2433831), Integer.valueOf(1037));
                伤害字型.damageSkin.put(Integer.valueOf(2433832), Integer.valueOf(1038));
                伤害字型.damageSkin.put(Integer.valueOf(2433833), Integer.valueOf(1039));
                伤害字型.damageSkin.put(Integer.valueOf(2434004), Integer.valueOf(1041));
                伤害字型.damageSkin.put(Integer.valueOf(2434499), Integer.valueOf(1049));
            }
            final Map<Integer, Integer> value = new TreeMap<Integer, Integer>();
            value.putAll((Map<? extends Integer, ? extends Integer>)伤害字型.damageSkin);
            return value;
        }
        
        public static int getDamageSkinNumberByItem(final int itemid) {
            final Map<Integer, Integer> skin = getDamageSkin();
            if (skin.containsKey((Object)Integer.valueOf(itemid))) {
                return (int)Integer.valueOf(skin.get((Object)Integer.valueOf(itemid)));
            }
            return -1;
        }
        
        public static Integer[] getDamageSkinsTradeBlock() {
            final Map<Integer, Integer> skin = getDamageSkin();
            final ArrayList<Integer> skins = new ArrayList<Integer>();
            final Iterator<Integer> iterator = skin.keySet().iterator();
            while (iterator.hasNext()) {
                final int s = (int)Integer.valueOf(iterator.next());
                if (MapleItemInformationProvider.getInstance().isOnlyTradeBlock(s)) {
                    skins.add(Integer.valueOf(s));
                }
            }
            final Integer[] list = new Integer[skins.size()];
            return (Integer[])skins.toArray(list);
        }
        
        public static boolean isDamageSkin(final int itemid) {
            final Map<Integer, Integer> skin = getDamageSkin();
            return skin.containsKey((Object)Integer.valueOf(itemid));
        }
        
        static {
            伤害字型.damageSkin = new TreeMap<Integer, Integer>();
        }
    }
    
    public static class 卷軸
    {
        public static boolean canScroll(final int itemId) {
            return (itemId / 100000 != 19 && itemId / 100000 != 16) || 類型.心臟(itemId);
        }
        
        public static boolean canHammer(final int itemId) {
            switch (itemId) {
                case 1122000:
                case 1122076: {
                    return false;
                }
                default: {
                    return canScroll(itemId);
                }
            }
        }
        
        public static int getChaosNumber(final int itemId) {
            switch (itemId) {
                case 2049116:
                case 2049132:
                case 2049133:
                case 2049134:
                case 2049135:
                case 2049136:
                case 2049137:
                case 2049140:
                case 2049142:
                case 2049145:
                case 2049152:
                case 2049153:
                case 2049156:
                case 2049159:
                case 2049165: {
                    return 10;
                }
                default: {
                    return 5;
                }
            }
        }
        
        public static int getBonusPotentialScrollSucc(final int scrollId) {
            switch (scrollId) {
                case 2048306:
                case 2048307:
                case 2048315:
                case 2048316: {
                    return 100;
                }
                case 2048313: {
                    return 80;
                }
                case 2048305: {
                    return 70;
                }
                case 2048309:
                case 2048310:
                case 2048314: {
                    return 60;
                }
                case 2048308:
                case 2048311: {
                    return 50;
                }
                case 2048312: {
                    return 1;
                }
                default: {
                    return 0;
                }
            }
        }
        
        public static int getBonusPotentialScrollCurse(final int scrollId) {
            switch (scrollId) {
                case 2048305:
                case 2048310: {
                    return 100;
                }
                case 2048308:
                case 2048311: {
                    return 50;
                }
                default: {
                    return 0;
                }
            }
        }
        
        public static int getSuccessTablet(final int scrollId, final int level) {
            if (scrollId % 1000 / 100 == 2) {
                switch (level) {
                    case 0: {
                        return 70;
                    }
                    case 1: {
                        return 55;
                    }
                    case 2: {
                        return 43;
                    }
                    case 3: {
                        return 33;
                    }
                    case 4: {
                        return 26;
                    }
                    case 5: {
                        return 20;
                    }
                    case 6: {
                        return 16;
                    }
                    case 7: {
                        return 12;
                    }
                    case 8: {
                        return 10;
                    }
                    default: {
                        return 7;
                    }
                }
            }
            else if (scrollId % 1000 / 100 == 3) {
                switch (level) {
                    case 0: {
                        return 70;
                    }
                    case 1: {
                        return 35;
                    }
                    case 2: {
                        return 18;
                    }
                    case 3: {
                        return 12;
                    }
                    default: {
                        return 7;
                    }
                }
            }
            else {
                switch (level) {
                    case 0: {
                        return 70;
                    }
                    case 1: {
                        return 50;
                    }
                    case 2: {
                        return 36;
                    }
                    case 3: {
                        return 26;
                    }
                    case 4: {
                        return 19;
                    }
                    case 5: {
                        return 14;
                    }
                    case 6: {
                        return 10;
                    }
                    default: {
                        return 7;
                    }
                }
            }
        }
        
        public static int getCurseTablet(final int scrollId, final int level) {
            if (scrollId % 1000 / 100 == 2) {
                switch (level) {
                    case 0: {
                        return 10;
                    }
                    case 1: {
                        return 12;
                    }
                    case 2: {
                        return 16;
                    }
                    case 3: {
                        return 20;
                    }
                    case 4: {
                        return 26;
                    }
                    case 5: {
                        return 33;
                    }
                    case 6: {
                        return 43;
                    }
                    case 7: {
                        return 55;
                    }
                    case 8: {
                        return 70;
                    }
                    default: {
                        return 100;
                    }
                }
            }
            else if (scrollId % 1000 / 100 == 3) {
                switch (level) {
                    case 0: {
                        return 12;
                    }
                    case 1: {
                        return 18;
                    }
                    case 2: {
                        return 35;
                    }
                    case 3: {
                        return 70;
                    }
                    default: {
                        return 100;
                    }
                }
            }
            else {
                switch (level) {
                    case 0: {
                        return 10;
                    }
                    case 1: {
                        return 14;
                    }
                    case 2: {
                        return 19;
                    }
                    case 3: {
                        return 26;
                    }
                    case 4: {
                        return 36;
                    }
                    case 5: {
                        return 50;
                    }
                    case 6: {
                        return 70;
                    }
                    default: {
                        return 100;
                    }
                }
            }
        }
        
        public static int getEnhanceTimes(final int itemId) {
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final int level = ii.getReqLevel(itemId);
            final boolean isSuperiorEquip = false;
            int enhanceTimes = 0;
            if (level >= 0 && level < 95) {
                enhanceTimes = (isSuperiorEquip ? 3 : 5);
            }
            else if (level >= 95 && level < 108) {
                enhanceTimes = (isSuperiorEquip ? 5 : 8);
            }
            else if (level >= 108 && level < 118) {
                enhanceTimes = (isSuperiorEquip ? 8 : 10);
            }
            else if (level >= 118 && level < 128) {
                enhanceTimes = (isSuperiorEquip ? 10 : 15);
            }
            else if (level >= 128 && level < 138) {
                enhanceTimes = (isSuperiorEquip ? 12 : 20);
            }
            else if (level >= 138) {
                enhanceTimes = (isSuperiorEquip ? 15 : 25);
            }
            return enhanceTimes;
        }
    }
    
    public static class 套裝
    {
        public static ArrayList<Integer> get6YearSet() {
            final int[] set = { 1462116, 1342039, 1402109, 1472139, 1332147, 1322105, 1442135, 1452128, 1312071, 1382123, 1492100, 1372099, 1432098, 1422072, 1302172, 1482101, 1412070 };
            final ArrayList<Integer> list = new ArrayList<Integer>();
            for (final int i : set) {
                list.add(Integer.valueOf(i));
            }
            return list;
        }
        
        public static ArrayList<Integer> get7YearSet() {
            final int[] set = { 1003243, 1052358, 1072522, 1082315, 1102295, 1132093, 1152061, 1332145, 1402107, 1442133, 1462114, 1472137, 1532070, 1522066, 1452126, 1312069, 1382121, 1492098, 1372097, 1362058, 1432096, 1422070, 1302170, 1482099, 1412068 };
            final ArrayList<Integer> list = new ArrayList<Integer>();
            for (final int i : set) {
                list.add(Integer.valueOf(i));
            }
            return list;
        }
        
        public static ArrayList<Integer> get8YearSet() {
            final int[] set = { 1462159, 1462156, 1402145, 1402151, 1052461, 1052457, 1532073, 1532074, 1472177, 1472179, 1332186, 1332193, 1322154, 1322162, 1442173, 1442182, 1522068, 1522071, 1452165, 1312114, 1312116, 1382160, 1132154, 1132151, 1072666, 1072660, 1212069, 1212068, 1492152, 1492138, 1372139, 1372131, 1222063, 1222064, 1082433, 1082430, 1362060, 1362067, 1432138, 1432135, 1152088, 1152089, 1003529, 1003552, 1422107, 1422105, 1232070, 1232063, 1302227, 1302212, 1113036, 1113035, 1112743, 1112742, 1482140, 1482138, 1242048, 1242075, 1412102, 1412014, 1102394, 1102441 };
            final ArrayList<Integer> list = new ArrayList<Integer>();
            for (final int i : set) {
                list.add(Integer.valueOf(i));
            }
            return list;
        }
        
        public static ArrayList<Integer> get10YearSet() {
            final int[] set = { 1004172, 1012471, 1052758, 1102691, 1122280, 1212095, 1222089, 1232089, 1242095, 1302304, 1312179, 1322230, 1332254, 1342094, 1362115, 1372201, 1382239, 1402229, 1412158, 1422165, 1432194, 1442248, 1452232, 1462219, 1472241, 1482196, 1492205, 1522118, 1532124 };
            final ArrayList<Integer> list = new ArrayList<Integer>();
            for (final int i : set) {
                list.add(Integer.valueOf(i));
            }
            return list;
        }
    }
    
    public static class 類型
    {
        public static boolean 帽子(final int itemid) {
            return itemid / 10000 == 100;
        }
        
        public static boolean 臉飾(final int itemid) {
            return itemid / 10000 == 101;
        }
        
        public static boolean 眼飾(final int itemid) {
            return itemid / 10000 == 102;
        }
        
        public static boolean 耳環(final int itemid) {
            return itemid / 10000 == 103;
        }
        
        public static boolean 上衣(final int itemid) {
            return itemid / 10000 == 104;
        }
        
        public static boolean 套服(final int itemId) {
            return itemId / 10000 == 105;
        }
        
        public static boolean 褲裙(final int itemid) {
            return itemid / 10000 == 106;
        }
        
        public static boolean 鞋子(final int itemid) {
            return itemid / 10000 == 107;
        }
        
        public static boolean 手套(final int itemid) {
            return itemid / 10000 == 108;
        }
        
        public static boolean 盾牌(final int itemid) {
            return itemid / 10000 == 109;
        }
        
        public static boolean 披風(final int itemid) {
            return itemid / 10000 == 110;
        }
        
        public static boolean 戒指(final int itemid) {
            return itemid / 10000 == 111;
        }
        
        public static boolean 墜飾(final int itemid) {
            return itemid / 10000 == 112;
        }
        
        public static boolean 腰帶(final int itemid) {
            return itemid / 10000 == 113;
        }
        
        public static boolean 勳章(final int itemid) {
            return itemid / 10000 == 114;
        }
        
        public static boolean 肩飾(final int itemid) {
            return itemid / 10000 == 115;
        }
        
        public static boolean 口袋道具(final int itemid) {
            return itemid / 10000 == 116;
        }
        
        public static boolean 胸章(final int itemId) {
            return itemId / 10000 == 118;
        }
        
        public static boolean 能源(final int itemid) {
            return itemid / 10000 == 119;
        }
        
        public static boolean 图騰(final int itemid) {
            return itemid / 10000 == 120;
        }
        
        public static boolean 閃亮克魯(final int itemid) {
            return itemid / 10000 == 121;
        }
        
        public static boolean 靈魂射手(final int itemid) {
            return itemid / 10000 == 122;
        }
        
        public static boolean 魔劍(final int itemid) {
            return itemid / 10000 == 123;
        }
        
        public static boolean 能量劍(final int itemid) {
            return itemid / 10000 == 124;
        }
        
        public static boolean 幻獸棍棒(final int itemid) {
            return itemid / 10000 == 125;
        }
        
        public static boolean ESP限制器(final int itemid) {
            return itemid / 10000 == 126;
        }
        
        public static boolean 單手劍(final int itemid) {
            return itemid / 10000 == 130;
        }
        
        public static boolean 單手斧(final int itemid) {
            return itemid / 10000 == 131;
        }
        
        public static boolean 單手棍(final int itemid) {
            return itemid / 10000 == 132;
        }
        
        public static boolean 短劍(final int itemid) {
            return itemid / 10000 == 133;
        }
        
        public static boolean 雙刀(final int itemid) {
            return itemid / 10000 == 134;
        }
        
        public static boolean 特殊副手(final int itemid) {
            return itemid / 10000 == 135;
        }
        
        public static boolean 手杖(final int itemid) {
            return itemid / 10000 == 136;
        }
        
        public static boolean 短杖(final int itemid) {
            return itemid / 10000 == 137;
        }
        
        public static boolean 長杖(final int itemid) {
            return itemid / 10000 == 138;
        }
        
        public static boolean 雙手劍(final int itemid) {
            return itemid / 10000 == 140;
        }
        
        public static boolean 雙手斧(final int itemid) {
            return itemid / 10000 == 141;
        }
        
        public static boolean 雙手棍(final int itemid) {
            return itemid / 10000 == 142;
        }
        
        public static boolean 槍(final int itemid) {
            return itemid / 10000 == 143;
        }
        
        public static boolean 矛(final int itemid) {
            return itemid / 10000 == 144;
        }
        
        public static boolean 弓(final int itemid) {
            return itemid / 10000 == 145;
        }
        
        public static boolean 弩(final int itemid) {
            return itemid / 10000 == 146;
        }
        
        public static boolean 拳套(final int itemid) {
            return itemid / 10000 == 147;
        }
        
        public static boolean 指虎(final int itemid) {
            return itemid / 10000 == 148;
        }
        
        public static boolean 火槍(final int itemid) {
            return itemid / 10000 == 149;
        }
        
        public static boolean 雙弩槍(final int itemid) {
            return itemid / 10000 == 152;
        }
        
        public static boolean 加農炮(final int itemid) {
            return itemid / 10000 == 153;
        }
        
        public static boolean 太刀(final int itemid) {
            return itemid / 10000 == 154;
        }
        
        public static boolean 扇子(final int itemid) {
            return itemid / 10000 == 155;
        }
        
        public static boolean 琉(final int itemid) {
            return itemid / 10000 == 156;
        }
        
        public static boolean 璃(final int itemid) {
            return itemid / 10000 == 157;
        }
        
        public static boolean 引擎(final int itemid) {
            return itemid / 10000 == 161;
        }
        
        public static boolean 手臂(final int itemid) {
            return itemid / 10000 == 162;
        }
        
        public static boolean 腿(final int itemid) {
            return itemid / 10000 == 163;
        }
        
        public static boolean 機殼(final int itemid) {
            return itemid / 10000 == 164;
        }
        
        public static boolean 晶體管(final int itemid) {
            return itemid / 10000 == 165;
        }
        
        public static boolean 機器人(final int itemid) {
            return itemid / 10000 == 166;
        }
        
        public static boolean 心臟(final int itemId) {
            return itemId / 10000 == 167;
        }
        
        public static boolean 寵物装备(final int itemid) {
            return itemid / 10000 >= 180 && itemid / 10000 <= 183;
        }
        
        public static boolean 騎寵(final int itemid) {
            return itemid / 10000 == 190 || itemid / 10000 == 193;
        }
        
        public static boolean 馬鞍(final int itemid) {
            return itemid / 10000 == 191;
        }
        
        public static boolean 龍面具(final int itemid) {
            return itemid / 10000 == 194;
        }
        
        public static boolean 龍墜飾(final int itemid) {
            return itemid / 10000 == 195;
        }
        
        public static boolean 龍之翼(final int itemid) {
            return itemid / 10000 == 196;
        }
        
        public static boolean 龍尾巴(final int itemid) {
            return itemid / 10000 == 197;
        }
        
        public static boolean 飛鏢(final int itemid) {
            return itemid / 10000 == 207;
        }
        
        public static boolean 子彈(final int itemid) {
            return itemid / 10000 == 233;
        }
        
        public static boolean 寵物(final int id) {
            return id / 10000 == 500;
        }
        
        public static boolean 防具(final int itemid) {
            return 帽子(itemid) || 上衣(itemid) || 套服(itemid) || 褲裙(itemid) || 鞋子(itemid) || 手套(itemid) || 披風(itemid);
        }
        
        public static boolean 飾品(final int itemid) {
            return 臉飾(itemid) || 眼飾(itemid) || 耳環(itemid) || 戒指(itemid) || 墜飾(itemid) || 腰帶(itemid) || 勳章(itemid) || 肩飾(itemid) || 口袋道具(itemid) || 胸章(itemid) || 能源(itemid) || 图騰(itemid);
        }
        
        public static boolean 副手(final int itemid) {
            return 盾牌(itemid) || 雙刀(itemid) || 特殊副手(itemid);
        }
        
        public static boolean 武器(final int itemid) {
            return 閃亮克魯(itemid) || 靈魂射手(itemid) || 魔劍(itemid) || 能量劍(itemid) || 幻獸棍棒(itemid) || ESP限制器(itemid) || 單手劍(itemid) || 單手斧(itemid) || 單手棍(itemid) || 短劍(itemid) || 手杖(itemid) || 短杖(itemid) || 長杖(itemid) || 雙手劍(itemid) || 雙手斧(itemid) || 雙手棍(itemid) || 槍(itemid) || 矛(itemid) || 弓(itemid) || 弩(itemid) || 拳套(itemid) || 指虎(itemid) || 火槍(itemid) || 雙弩槍(itemid) || 加農炮(itemid) || 太刀(itemid) || 扇子(itemid) || 琉(itemid) || 璃(itemid);
        }
        
        public static boolean 機械(final int itemid) {
            return 引擎(itemid) || 手臂(itemid) || 腿(itemid) || 機殼(itemid) || 晶體管(itemid);
        }
        
        public static boolean 龍装备(final int itemid) {
            return 龍面具(itemid) || 龍墜飾(itemid) || 龍之翼(itemid) || 龍尾巴(itemid);
        }
        
        public static boolean 可充值道具(final int itemid) {
            return 飛鏢(itemid) || 子彈(itemid);
        }
        
        public static boolean 單手武器(final int itemid) {
            return 武器(itemid) && !雙手武器(itemid);
        }
        
        public static boolean 雙手武器(final int itemid) {
            return 雙手劍(itemid) || 雙手斧(itemid) || 雙手棍(itemid) || 槍(itemid) || 矛(itemid) || 弓(itemid) || 弩(itemid) || 拳套(itemid) || 指虎(itemid) || 火槍(itemid) || 雙弩槍(itemid) || 加農炮(itemid) || 太刀(itemid) || 扇子(itemid) || 琉(itemid) || 璃(itemid);
        }
        
        public static boolean 物理武器(final int itemid) {
            return 武器(itemid) && !魔法武器(itemid);
        }
        
        public static boolean 魔法武器(final int itemid) {
            return 短杖(itemid) || 長杖(itemid) || 扇子(itemid) || 幻獸棍棒(itemid) || ESP限制器(itemid);
        }
        
        public static boolean 騎寵道具(final int itemid) {
            return 騎寵(itemid) || 馬鞍(itemid);
        }
        
        public static boolean 装备(final int itemid) {
            return itemid / 10000 >= 100 && itemid / 10000 < 200;
        }
        
        public static boolean 消耗(final int itemid) {
            return itemid / 10000 >= 200 && itemid / 10000 < 300;
        }
        
        public static boolean 裝飾(final int itemid) {
            return itemid / 10000 >= 300 && itemid / 10000 < 400;
        }
        
        public static boolean 其他(final int itemid) {
            return itemid / 10000 >= 400 && itemid / 10000 < 500;
        }
        
        public static boolean 特殊(final int itemid) {
            return itemid / 1000 >= 500;
        }
        
        public static boolean 友誼戒指(final int itemid) {
            switch (itemid) {
                case 1112800:
                case 1112801:
                case 1112802:
                case 1112810:
                case 1112811:
                case 1112816:
                case 1112817: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        
        public static boolean 戀人戒指(final int itemid) {
            switch (itemid) {
                case 1048000:
                case 1048001:
                case 1048002:
                case 1112001:
                case 1112002:
                case 1112003:
                case 1112005:
                case 1112006:
                case 1112007:
                case 1112012:
                case 1112015: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        
        public static boolean 結婚戒指(final int itemid) {
            switch (itemid) {
                case 1112300:
                case 1112301:
                case 1112302:
                case 1112303:
                case 1112304:
                case 1112305:
                case 1112306:
                case 1112307:
                case 1112308:
                case 1112309:
                case 1112310:
                case 1112311:
                case 1112315:
                case 1112316:
                case 1112317:
                case 1112318:
                case 1112319:
                case 1112320:
                case 1112803:
                case 1112806:
                case 1112807:
                case 1112808:
                case 1112809: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        
        public static boolean 特效戒指(final int itemid) {
            return 友誼戒指(itemid) || 戀人戒指(itemid) || 結婚戒指(itemid);
        }
        
        public static boolean 管理員装备(final int itemid) {
            switch (itemid) {
                case 1002140:
                case 1002959:
                case 1003142:
                case 1003274:
                case 1042003:
                case 1042223:
                case 1062007:
                case 1062140:
                case 1322013:
                case 1322106: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        
        public static boolean 城鎮傳送卷軸(final int itemid) {
            return itemid >= 2030000 && itemid < 2040000;
        }
        
        public static boolean 普通升級卷軸(final int itemid) {
            return itemid >= 2040000 && itemid <= 2048100 && !阿斯旺卷軸(itemid);
        }
        
        public static boolean 阿斯旺卷軸(final int itemid) {
            return (itemid >= 2046060 && itemid <= 2046069) || (itemid >= 2046141 && itemid <= 2046145) || (itemid >= 2046519 && itemid <= 2046530) || (itemid >= 2046701 && itemid <= 2046712);
        }
        
        public static boolean 提升卷(final int itemid) {
            return itemid >= 2047000 && itemid < 2047310;
        }
        
        public static boolean 附加潜能卷軸(final int itemid) {
            return itemid / 100 == 20483 && (itemid < 2048200 || itemid > 2048304);
        }
        
        public static boolean 白醫卷軸(final int itemid) {
            return itemid / 100 == 20490;
        }
        
        public static boolean 混沌卷軸(final int itemid) {
            return (itemid < 2049105 || itemid > 2049110) && (itemid / 100 == 20491 || itemid == 2040126);
        }
        
        public static boolean 樂觀混沌卷軸(final int itemid) {
            if (!混沌卷軸(itemid)) {
                return false;
            }
            switch (itemid) {
                case 2049122:
                case 2049129:
                case 2049130:
                case 2049131:
                case 2049135:
                case 2049136:
                case 2049137:
                case 2049141:
                case 2049153:
                case 2049155: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        
        public static boolean 飾品卷軸(final int itemid) {
            return itemid / 100 == 20492;
        }
        
        public static boolean 装备強化卷軸(final int itemid) {
            return itemid / 100 == 20493;
        }
        
        public static boolean 鐵鎚(final int itemid) {
            return itemid / 10000 == 247;
        }
        
        public static boolean 潜能卷軸(final int itemid) {
            return itemid / 100 == 20494 || itemid / 100 == 20497 || itemid == 5534000;
        }
        
        public static boolean 回真卷軸(final int itemid) {
            switch (itemid) {
                case 5064200:
                case 5064201: {
                    return true;
                }
                default: {
                    return itemid / 100 == 20496;
                }
            }
        }
        
        public static boolean 幸運日卷軸(final int itemid) {
            switch (itemid) {
                case 5063100:
                case 5068000: {
                    return true;
                }
                default: {
                    return itemid / 1000 == 2530;
                }
            }
        }
        
        public static boolean 保護卷軸(final int itemid) {
            switch (itemid) {
                case 5063100:
                case 5064000:
                case 5064002:
                case 5064003:
                case 5064004: {
                    return true;
                }
                default: {
                    return itemid / 1000 == 2531;
                }
            }
        }
        
        public static boolean 安全卷軸(final int itemid) {
            switch (itemid) {
                case 5064100:
                case 5064101:
                case 5068100: {
                    return true;
                }
                default: {
                    return itemid / 1000 == 2532;
                }
            }
        }
        
        public static boolean 卷軸保護卡(final int itemid) {
            switch (itemid) {
                case 5064300:
                case 5064301:
                case 5068200: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        
        public static boolean 靈魂卷軸_附魔器(final int itemid) {
            return itemid / 1000 == 2590;
        }
        
        public static boolean 靈魂寶珠(final int itemid) {
            return itemid / 1000 == 2591;
        }
        
        public static boolean TMS特殊卷軸(final int itemid) {
            return itemid / 10000 == 261;
        }
        
        public static boolean 特殊卷軸(final int itemid) {
            return 幸運日卷軸(itemid) || 保護卷軸(itemid) || 安全卷軸(itemid) || 卷軸保護卡(itemid);
        }
        
        public static boolean 特殊潜能道具(final int itemid) {
            return (itemid / 100 == 10121 && itemid % 100 >= 64 && itemid % 100 <= 74 && itemid % 100 != 65 && itemid % 100 != 66) || (itemid / 10 == 112212 && itemid % 10 >= 2 && itemid % 10 <= 6) || (itemid >= 1122224 && itemid <= 1122245) || itemid / 10 == 101244;
        }
        
        public static boolean 無法潜能道具(final int itemid) {
            return false;
        }
        
        public static boolean 脸型(final int itemid) {
            return itemid / 10000 == 2;
        }
        
        public static boolean 发型(final int itemid) {
            return itemid / 10000 == 3;
        }
        
        public static boolean 男脸型(final int id) {
            return id / 1000 == 20;
        }
        
        public static boolean 女脸型(final int id) {
            return id / 1000 == 21;
        }
        
        public static boolean 男发型(final int id) {
            if (id == 33030 || id == 33160 || id == 33590) {
                return false;
            }
            if (id / 1000 == 30 || id / 1000 == 33 || (id / 1000 == 32 && id >= 32370) || id / 1000 == 36 || (id / 1000 == 37 && id >= 37160 && id <= 37170)) {
                return true;
            }
            switch (id) {
                case 32160:
                case 32330:
                case 34740: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        
        public static boolean 女发型(final int id) {
            if (id == 32160 || id == 32330 || id == 34740) {
                return false;
            }
            if (id / 1000 == 31 || id / 1000 == 34 || (id / 1000 == 32 && id < 32370) || (id / 1000 == 37 && id < 37160)) {
                return true;
            }
            switch (id) {
                case 33030:
                case 33160:
                case 33590: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
    }
}
