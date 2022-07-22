package constants;

import handling.login.LoginServer;
import java.util.Collections;
import java.util.Arrays;
import server.ServerProperties;
import server.MapleStatEffect;
import client.inventory.IItem;
import client.MapleCharacter;
import handling.channel.handler.AttackInfo;
import java.util.Calendar;
import server.Randomizer;
import java.util.Random;
import client.status.MonsterStatus;
import client.inventory.MapleWeaponType;
import client.inventory.MapleInventoryType;
import server.maps.MapleMapObjectType;
import java.util.List;

public class GameConstants
{
    public static String 冒险岛名字;
    public static final List<MapleMapObjectType> rangedMapobjectTypes;
    private static final int[] ExpTable;
    private static final int[] ClosenessTable;
    private static final int[] MountExpTable;
    public static final int[] itemBlock;
    public static final int[] cashBlock;
    public static final int OMOK_SCORE = 122200;
    public static final int MATCH_SCORE = 122210;
    public static final int HP_ITEM = 122221;
    public static final int MP_ITEM = 122223;
    public static final int[] blockedSkills;
    public static int[] blockedMaps;
    public static int[] Equipments_Bonus;
    public static final String[] RESERVED;
    public static final String[] stats;
    public static final int[] Jxboxrewards;
    public static final int[] goldrewards;
    public static final int[] silverrewards;
    public static int[] eventCommonReward;
    public static int[] eventUncommonReward;
    public static int[] eventRareReward;
    public static int[] eventSuperReward;
    public static int[] fishingReward;
    public static final int[] normalDrops;
    public static final int[] rareDrops;
    public static final int[] superDrops;
    public static int[] owlItems;
    private static final List<Balloon> lBalloon;
    
    public static boolean isLinkedAttackSkill(final int id) {
        return getLinkedAttackSkill(id) != id;
    }
    
    public static int getLinkedAttackSkill(final int id) {
        switch (id) {
            case 11101220: {
                return 11101120;
            }
            case 11101221: {
                return 11101121;
            }
            case 11111120: {
                return 11111220;
            }
            case 11111121: {
                return 11111221;
            }
            case 11121102:
            case 11121201:
            case 11121202: {
                return 11121101;
            }
            case 11121103: {
                return 11121203;
            }
            case 21110007:
            case 21110008: {
                return 21110002;
            }
            case 21120009:
            case 21120010: {
                return 21120002;
            }
            case 4321001: {
                return 4321000;
            }
            case 5300007: {
                return 5301001;
            }
            case 5320011: {
                return 5321004;
            }
            case 5211015:
            case 5211016: {
                return 5211011;
            }
            case 5001008: {
                return 5200010;
            }
            case 5001009: {
                return 5101004;
            }
            default: {
                return id;
            }
        }
    }
    
    public static int getExpNeededForLevel(final int level) {
        if (level < 0 || level >= GameConstants.ExpTable.length) {
            return Integer.MAX_VALUE;
        }
        return GameConstants.ExpTable[level];
    }
    
    public static boolean isNoDelaySkill(final int skillId) {
        return skillId == 5110001 || skillId == 21101003 || skillId == 15100004 || skillId == 2111007 || skillId == 2211007 || skillId == 2311007 || skillId == 32121003 || skillId == 35121005 || skillId == 35111004 || skillId == 35121013 || skillId == 35121003 || skillId == 22150004 || skillId == 22181004 || skillId == 11101002 || skillId == 51100002 || skillId == 13101002 || skillId == 24121000 || skillId == 112001008 || skillId == 22161005 || skillId == 22161005;
    }
    
    public static boolean isMarrigeRing(final int itemid) {
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
    
    public static int getClosenessNeededForLevel(final int level) {
        return GameConstants.ClosenessTable[level - 1];
    }
    
    public static int getMountExpNeededForLevel(final int level) {
        return GameConstants.MountExpTable[level - 1];
    }
    
    public static int getBookLevel(final int level) {
        return 5 * level * (level + 1);
    }
    
    public static int getTimelessRequiredEXP(final int level) {
        return 70 + level * 10;
    }
    
    public static int getReverseRequiredEXP(final int level) {
        return 60 + level * 5;
    }
    
    public static int maxViewRangeSq() {
        return Integer.MAX_VALUE;
    }
    
    public static boolean isRecoveryIncSkill(final int id) {
        switch (id) {
            case 1110000:
            case 1210000:
            case 2000000:
            case 4100002:
            case 4200001:
            case 11110000: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isLinkedSkill(final int id) {
        return getLinkedSkill(id) != id;
    }
    
    public static int getLinkedSkill(final int id) {
        switch (id) {
            case 21110007:
            case 21110008: {
                return 21110002;
            }
            case 21120009:
            case 21120010: {
                return 21120002;
            }
            case 4321001: {
                return 4321000;
            }
            default: {
                return id;
            }
        }
    }
    
    public static boolean isElementAmpSkill(final int skill) {
        switch (skill) {
            case 2110001:
            case 2210001:
            case 12110001: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static int getMPEaterForJob(final int job) {
        switch (job) {
            case 210:
            case 211:
            case 212: {
                return 2100000;
            }
            case 220:
            case 221:
            case 222: {
                return 2200000;
            }
            case 230:
            case 231:
            case 232: {
                return 2300000;
            }
            default: {
                return 2100000;
            }
        }
    }
    
    public static int getJobShortValue(int job) {
        if (job >= 1000) {
            job -= job / 1000 * 1000;
        }
        job /= 100;
        if (job == 4) {
            job *= 2;
        }
        else if (job == 3) {
            ++job;
        }
        else if (job == 5) {
            job += 11;
        }
        return job;
    }
    
    public static boolean isPyramidSkill(final int skill) {
        switch (skill) {
            case 1020:
            case 10001020:
            case 20001020:
            case 20011020: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isMulungSkill(final int skill) {
        switch (skill) {
            case 1009:
            case 1010:
            case 1011:
            case 10001009:
            case 10001010:
            case 10001011:
            case 20001009:
            case 20001010:
            case 20001011:
            case 20011009:
            case 20011010:
            case 20011011: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isThrowingStar(final int itemId) {
        return itemId / 10000 == 207;
    }
    
    public static boolean isBullet(final int itemId) {
        return itemId / 10000 == 233;
    }
    
    public static boolean isRechargable(final int itemId) {
        return isThrowingStar(itemId) || isBullet(itemId);
    }
    
    public static boolean isOverall(final int itemId) {
        return itemId / 10000 == 105;
    }
    
    public static boolean isPet(final int itemId) {
        return itemId / 10000 == 500;
    }
    
    public static boolean isArrowForCrossBow(final int itemId) {
        return itemId >= 2061000 && itemId < 2062000;
    }
    
    public static boolean isArrowForBow(final int itemId) {
        return itemId >= 2060000 && itemId < 2061000;
    }
    
    public static boolean isMagicWeapon(final int itemId) {
        final int s = itemId / 10000;
        return s == 137 || s == 138;
    }
    
    public static boolean isWeapon(final int itemId) {
        return itemId >= 1300000 && itemId < 1500000;
    }
    
    public static MapleInventoryType getInventoryType(final int itemId) {
        final byte type = (byte)(itemId / 1000000);
        if (type < 1 || type > 5) {
            return MapleInventoryType.UNDEFINED;
        }
        return MapleInventoryType.getByType(type);
    }
    
    public static MapleWeaponType getWeaponType(final int itemId) {
        int cat = itemId / 10000;
        cat %= 100;
        switch (cat) {
            case 30: {
                return MapleWeaponType.單手劍;
            }
            case 31: {
                return MapleWeaponType.單手斧;
            }
            case 32: {
                return MapleWeaponType.單手棍;
            }
            case 33: {
                return MapleWeaponType.短劍;
            }
            case 34: {
                return MapleWeaponType.雙刀;
            }
            case 37: {
                return MapleWeaponType.長杖;
            }
            case 38: {
                return MapleWeaponType.短杖;
            }
            case 40: {
                return MapleWeaponType.雙手劍;
            }
            case 41: {
                return MapleWeaponType.雙手斧;
            }
            case 42: {
                return MapleWeaponType.雙手棍;
            }
            case 43: {
                return MapleWeaponType.矛;
            }
            case 44: {
                return MapleWeaponType.槍;
            }
            case 45: {
                return MapleWeaponType.弓;
            }
            case 46: {
                return MapleWeaponType.弩;
            }
            case 47: {
                return MapleWeaponType.拳套;
            }
            case 48: {
                return MapleWeaponType.指虎;
            }
            case 49: {
                return MapleWeaponType.火槍;
            }
            default: {
                return MapleWeaponType.沒有武器;
            }
        }
    }
    
    public static boolean isShield(final int itemId) {
        int cat = itemId / 10000;
        cat %= 100;
        return cat == 9;
    }
    
    public static boolean isEquip(final int itemId) {
        return itemId / 1000000 == 1;
    }
    
    public static boolean isCleanSlate(final int itemId) {
        return itemId / 100 == 20490;
    }
    
    public static boolean isAccessoryScroll(final int itemId) {
        return itemId / 100 == 20492;
    }
    
    public static boolean isChaosScroll(final int itemId) {
        return (itemId < 2049105 || itemId > 2049110) && itemId / 100 == 20491;
    }
    
    public static int getChaosNumber(final int itemId) {
        return (itemId == 2049116) ? 10 : 5;
    }
    
    public static boolean isEquipScroll(final int scrollId) {
        return scrollId / 100 == 20493;
    }
    
    public static boolean isPotentialScroll(final int scrollId) {
        return scrollId / 100 == 20494;
    }
    
    public static boolean isSpecialScroll(final int scrollId) {
        switch (scrollId) {
            case 2040727:
            case 2041058: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isTwoHanded(final int itemId) {
        switch (getWeaponType(itemId)) {
            case 雙手斧:
            case 火槍:
            case 指虎:
            case 雙手棍:
            case 弓:
            case 拳套:
            case 弩:
            case 槍:
            case 矛:
            case 雙手劍: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isTownScroll(final int id) {
        return id >= 2030000 && id < 2040000;
    }
    
    public static boolean isUpgradeScroll(final int id) {
        return id >= 2040000 && id < 2050000;
    }
    
    public static boolean isGun(final int id) {
        return id >= 1492000 && id < 1500000;
    }
    
    public static boolean isUse(final int id) {
        return id >= 2000000 && id <= 2490000;
    }
    
    public static boolean isSummonSack(final int id) {
        return id / 10000 == 210;
    }
    
    public static boolean isMonsterCard(final int id) {
        return id / 10000 == 238;
    }
    
    public static boolean isSpecialCard(final int id) {
        return id / 1000 >= 2388;
    }
    
    public static int getCardShortId(final int id) {
        return id % 10000;
    }
    
    public static boolean isGem(final int id) {
        return id >= 4250000 && id <= 4251402;
    }
    
    public static boolean isOtherGem(final int id) {
        switch (id) {
            case 1032062:
            case 1142156:
            case 1142157:
            case 2040727:
            case 2041058:
            case 4001174:
            case 4001175:
            case 4001176:
            case 4001177:
            case 4001178:
            case 4001179:
            case 4001180:
            case 4001181:
            case 4001182:
            case 4001183:
            case 4001184:
            case 4001185:
            case 4001186:
            case 4031980:
            case 4032312:
            case 4032334: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isCustomQuest(final int id) {
        return id > 99999;
    }
    
    public static int getTaxAmount(final int meso) {
        if (meso >= 100000000) {
            return (int)Math.round(0.06 * (double)meso);
        }
        if (meso >= 25000000) {
            return (int)Math.round(0.05 * (double)meso);
        }
        if (meso >= 10000000) {
            return (int)Math.round(0.04 * (double)meso);
        }
        if (meso >= 5000000) {
            return (int)Math.round(0.03 * (double)meso);
        }
        if (meso >= 1000000) {
            return (int)Math.round(0.018 * (double)meso);
        }
        if (meso >= 100000) {
            return (int)Math.round(0.008 * (double)meso);
        }
        return 0;
    }
    
    public static int EntrustedStoreTax(final int meso) {
        if (meso >= 100000000) {
            return (int)Math.round(0.03 * (double)meso);
        }
        if (meso >= 25000000) {
            return (int)Math.round(0.025 * (double)meso);
        }
        if (meso >= 10000000) {
            return (int)Math.round(0.02 * (double)meso);
        }
        if (meso >= 5000000) {
            return (int)Math.round(0.015 * (double)meso);
        }
        if (meso >= 1000000) {
            return (int)Math.round(0.009 * (double)meso);
        }
        if (meso >= 100000) {
            return (int)Math.round(0.004 * (double)meso);
        }
        return 0;
    }
    
    public static short getSummonAttackDelay(final int id) {
        switch (id) {
            case 2121005:
            case 2221005:
            case 2311006:
            case 2321003:
            case 3111005:
            case 3121006:
            case 3211005:
            case 3221005:
            case 11001004:
            case 12001004:
            case 13001004:
            case 14001005:
            case 15001004: {
                return 3030;
            }
            case 5211001:
            case 5211002:
            case 5220002: {
                return 1230;
            }
            case 1321007:
            case 3111002:
            case 3211002:
            case 4341006:
            case 35111002:
            case 35111011:
            case 35121009:
            case 35121010: {
                return 0;
            }
            default: {
                return 0;
            }
        }
    }
    
    public static short getAttackDelay(final int id) {
        switch (id) {
            case 5201001: {
                return 200;
            }
            case 3110001: {
                return 120;
            }
            case 5001002: {
                return 30;
            }
            case 4321001: {
                return 40;
            }
            case 3121004:
            case 4221001:
            case 5201006:
            case 5221004:
            case 13111002:
            case 33121009: {
                return 120;
            }
            case 13101005: {
                return 360;
            }
            case 5001003: {
                return 390;
            }
            case 1121006:
            case 1221007:
            case 1321003:
            case 5001001:
            case 15001001: {
                return 450;
            }
            case 4201005:
            case 5211004:
            case 5211005: {
                return 480;
            }
            case 0:
            case 1001004:
            case 1001005:
            case 1311005:
            case 5111002:
            case 11001002:
            case 11001003:
            case 15101005: {
                return 570;
            }
            case 311004:
            case 1121008:
            case 1211002:
            case 1221009:
            case 1311003:
            case 1311004:
            case 2001005:
            case 2101005:
            case 2121003:
            case 2121006:
            case 2221003:
            case 2301002:
            case 3101005:
            case 3111003:
            case 3111006:
            case 3201005:
            case 3211003:
            case 3211004:
            case 3211006:
            case 3221001:
            case 4001334:
            case 4001344:
            case 4101005:
            case 4111004:
            case 4111005:
            case 4121007:
            case 4201004:
            case 4211004:
            case 5101004:
            case 5221007:
            case 11111004:
            case 12101002:
            case 13111000:
            case 14001004:
            case 14111002:
            case 14111005:
            case 15101003: {
                return 600;
            }
            case 1311001:
            case 1311002:
            case 2221006:
            case 4221007:
            case 5201004:
            case 5211000:
            case 15001002: {
                return 660;
            }
            case 2001004:
            case 2121001:
            case 2201004:
            case 2201005:
            case 2211002:
            case 2221001:
            case 2301005:
            case 2321001:
            case 2321007:
            case 4121008:
            case 4211006:
            case 5101002:
            case 5121005:
            case 5211006:
            case 5221008:
            case 11101004:
            case 12001003:
            case 12111006: {
                return 750;
            }
            case 2111006:
            case 2211006:
            case 15111007: {
                return 810;
            }
            case 2111002:
            case 4211002:
            case 5101003:
            case 13111006: {
                return 900;
            }
            case 2311004: {
                return 500;
            }
            case 5121003: {
                return 930;
            }
            case 13111007: {
                return 960;
            }
            case 4121003:
            case 4221003:
            case 14101006: {
                return 1020;
            }
            case 12101006: {
                return 1050;
            }
            case 5121001: {
                return 1060;
            }
            case 1311006:
            case 2211003: {
                return 1140;
            }
            case 11111006: {
                return 1230;
            }
            case 12111005: {
                return 1260;
            }
            case 2111003: {
                return 800;
            }
            case 5111006:
            case 15111003: {
                return 1500;
            }
            case 5121007:
            case 15111004: {
                return 1830;
            }
            case 5121004:
            case 5221003: {
                return 2160;
            }
            case 2121007:
            case 2221007:
            case 2321008: {
                return 700;
            }
            case 10001011: {
                return 3060;
            }
            default: {
                return 330;
            }
        }
    }
    
    public static boolean getWuYanChi(final int id) {
        switch (id) {
            case 15001002:
            case 15111006: {
                return false;
            }
            default: {
                return true;
            }
        }
    }
    
    public static byte gachaponRareItem(final int id) {
        switch (id) {
            case 1002596:
            case 1002723:
            case 1002799:
            case 1002931:
            case 1002932:
            case 1002933:
            case 1002934:
            case 1003114:
            case 1003439:
            case 1004096:
            case 1004379:
            case 1004380:
            case 1004381:
            case 1004382:
            case 1004579:
            case 1012056:
            case 1012070:
            case 1012164:
            case 1012167:
            case 1012168:
            case 1012169:
            case 1012170:
            case 1012171:
            case 1012310:
            case 1012484:
            case 1022047:
            case 1022060:
            case 1022162:
            case 1032028:
            case 1032077:
            case 1032078:
            case 1032079:
            case 1032127:
            case 1032128:
            case 1032129:
            case 1032194:
            case 1052191:
            case 1052350:
            case 1072447:
            case 1072798:
            case 1082149:
            case 1082179:
            case 1082276:
            case 1082345:
            case 1082514:
            case 1082533:
            case 1092022:
            case 1092035:
            case 1092049:
            case 1102041:
            case 1102042:
            case 1102234:
            case 1102246:
            case 1102248:
            case 1102370:
            case 1102371:
            case 1102372:
            case 1102590:
            case 1112405:
            case 1112413:
            case 1112414:
            case 1112596:
            case 1112672:
            case 1112723:
            case 1112915:
            case 1112922:
            case 1113076:
            case 1122019:
            case 1122109:
            case 1122209:
            case 1122297:
            case 1132049:
            case 1132059:
            case 1132069:
            case 1132079:
            case 1132088:
            case 1132103:
            case 1132140:
            case 1142073:
            case 1142207:
            case 1142260:
            case 1142374:
            case 1142375:
            case 1142376:
            case 1142399:
            case 1142681:
            case 1142698:
            case 1142713:
            case 1142739:
            case 1302001:
            case 1302021:
            case 1302024:
            case 1302037:
            case 1302061:
            case 1302067:
            case 1302080:
            case 1302087:
            case 1302105:
            case 1302132:
            case 1302145:
            case 1302150:
            case 1302201:
            case 1302293:
            case 1322001:
            case 1322006:
            case 1322023:
            case 1322024:
            case 1322025:
            case 1322034:
            case 1322070:
            case 1322225:
            case 1332030:
            case 1332032:
            case 1332101:
            case 1332123:
            case 1332242:
            case 1372035:
            case 1372036:
            case 1372037:
            case 1372038:
            case 1372076:
            case 1382045:
            case 1382046:
            case 1382047:
            case 1382048:
            case 1382050:
            case 1382097:
            case 1382226:
            case 1402044:
            case 1402088:
            case 1402214:
            case 1422036:
            case 1422068:
            case 1422156:
            case 1422160:
            case 1432079:
            case 1432182:
            case 1442109:
            case 1452104:
            case 1452167:
            case 1452220:
            case 1462089:
            case 1462208:
            case 1472115:
            case 1472230:
            case 1482077:
            case 1482183:
            case 1492077:
            case 1492194:
            case 1932171:
            case 1932200:
            case 2022463:
            case 2040509:
            case 2040519:
            case 2040521:
            case 2040533:
            case 2043005:
            case 2043105:
            case 2043205:
            case 2043305:
            case 2043705:
            case 2043805:
            case 2044005:
            case 2044105:
            case 2044205:
            case 2044305:
            case 2044405:
            case 2044505:
            case 2044605:
            case 2044705:
            case 2044804:
            case 2044805:
            case 2044904:
            case 2044905:
            case 2048014:
            case 2048015:
            case 2048016:
            case 2048017:
            case 2049003:
            case 2049100:
            case 2100000:
            case 2100009:
            case 2101013:
            case 2290017:
            case 2290020:
            case 2290022:
            case 2290040:
            case 2290041:
            case 2290046:
            case 2290047:
            case 2290048:
            case 2290049:
            case 2290056:
            case 2290064:
            case 2290066:
            case 2290074:
            case 2290075:
            case 2290084:
            case 2290085:
            case 2290094:
            case 2290095:
            case 2290096:
            case 2290116:
            case 2340000:
            case 3010002:
            case 3010003:
            case 3010006:
            case 3010007:
            case 3010010:
            case 3010013:
            case 3010014:
            case 3010016:
            case 3010017:
            case 3010020:
            case 3010021:
            case 3010024:
            case 3010025:
            case 3010026:
            case 3010027:
            case 3010029:
            case 3010030:
            case 3010031:
            case 3010032:
            case 3010033:
            case 3010037:
            case 3010038:
            case 3010043:
            case 3010044:
            case 3010045:
            case 3010046:
            case 3010047:
            case 3010048:
            case 3010049:
            case 3010051:
            case 3010052:
            case 3010053:
            case 3010061:
            case 3010068:
            case 3010069:
            case 3010070:
            case 3010071:
            case 3010075:
            case 3010077:
            case 3010094:
            case 3010095:
            case 3010096:
            case 3010098:
            case 3010106:
            case 3010107:
            case 3010120:
            case 3010123:
            case 3010135:
            case 3010139:
            case 3010140:
            case 3010144:
            case 3010149:
            case 3010161:
            case 3010170:
            case 3010172:
            case 3010174:
            case 3010175:
            case 3010187:
            case 3010196:
            case 3010210:
            case 3010223:
            case 3010224:
            case 3010288:
            case 3010298:
            case 3010316:
            case 3010403:
            case 3010433:
            case 3010439:
            case 3010440:
            case 3010447:
            case 3010449:
            case 3010453:
            case 3010454:
            case 3010584:
            case 3010593:
            case 3010600:
            case 3010601:
            case 3010608:
            case 3010609:
            case 3010622:
            case 3010624:
            case 3010642:
            case 3010664:
            case 3010675:
            case 3010682:
            case 3010688:
            case 3010705:
            case 3010716:
            case 3010717:
            case 3010721:
            case 3010722:
            case 3010754:
            case 3010755:
            case 3010756:
            case 3010757:
            case 3010766:
            case 3010799:
            case 3010802:
            case 3010804:
            case 3010810:
            case 3010863:
            case 3010866:
            case 3010876:
            case 3010877:
            case 3010879:
            case 3010976:
            case 3010978:
            case 3010979:
            case 3010980:
            case 3012001:
            case 3012002:
            case 3015331:
            case 3015369:
            case 3015378:
            case 3015379:
            case 3015429:
            case 4280000:
            case 4280001:
            case 5490000: {
                return 1;
            }
            case 2022483:
            case 2040305:
            case 2040407:
            case 2040411:
            case 2040626:
            case 2040811:
            case 2040815:
            case 2210029:
            case 2370005:
            case 2370006:
            case 2370007:
            case 3010054: {
                return 2;
            }
            case 1382037:
            case 2040006:
            case 2040007:
            case 2040303:
            case 2040403:
            case 2040506:
            case 2040507:
            case 2040603:
            case 2040709:
            case 2040710:
            case 2040711:
            case 2040806:
            case 2040903:
            case 2041024:
            case 2041025:
            case 2043003:
            case 2043103:
            case 2043203:
            case 2043303:
            case 2043703:
            case 2043803:
            case 2044003:
            case 2044019:
            case 2044103:
            case 2044203:
            case 2044303:
            case 2044403:
            case 2044503:
            case 2044603:
            case 2044703:
            case 2044815:
            case 2044908:
            case 2049000:
            case 2049001:
            case 2049002: {
                return 3;
            }
            case 1102084:
            case 1102086:
            case 3010063:
            case 3010064:
            case 3010065: {
                return 3;
            }
            default: {
                return 0;
            }
        }
    }
    
    public static boolean isDragonItem(final int itemId) {
        switch (itemId) {
            case 1302059:
            case 1312031:
            case 1322052:
            case 1332049:
            case 1332050:
            case 1342010:
            case 1372032:
            case 1382036:
            case 1402036:
            case 1412026:
            case 1422028:
            case 1432038:
            case 1442045:
            case 1452044:
            case 1462039:
            case 1472051:
            case 1472052: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isReverseItem(final int itemId) {
        switch (itemId) {
            case 1002790:
            case 1002791:
            case 1002792:
            case 1002793:
            case 1002794:
            case 1052160:
            case 1052161:
            case 1052162:
            case 1052163:
            case 1052164:
            case 1072361:
            case 1072362:
            case 1072363:
            case 1072364:
            case 1072365:
            case 1082239:
            case 1082240:
            case 1082241:
            case 1082242:
            case 1082243:
            case 1302086:
            case 1312038:
            case 1322061:
            case 1332075:
            case 1332076:
            case 1342012:
            case 1372045:
            case 1382059:
            case 1402047:
            case 1412034:
            case 1422038:
            case 1432049:
            case 1442067:
            case 1452059:
            case 1462051:
            case 1472071:
            case 1482024:
            case 1492025: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isTimelessItem(final int itemId) {
        switch (itemId) {
            case 1002776:
            case 1002777:
            case 1002778:
            case 1002779:
            case 1002780:
            case 1032031:
            case 1052155:
            case 1052156:
            case 1052157:
            case 1052158:
            case 1052159:
            case 1072355:
            case 1072356:
            case 1072357:
            case 1072358:
            case 1072359:
            case 1082234:
            case 1082235:
            case 1082236:
            case 1082237:
            case 1082238:
            case 1092057:
            case 1092058:
            case 1092059:
            case 1102172:
            case 1122011:
            case 1122012:
            case 1302081:
            case 1312037:
            case 1322060:
            case 1332073:
            case 1332074:
            case 1342011:
            case 1372044:
            case 1382057:
            case 1402046:
            case 1412033:
            case 1422037:
            case 1432047:
            case 1442063:
            case 1452057:
            case 1462050:
            case 1472068:
            case 1482023:
            case 1492023: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isRing(final int itemId) {
        return itemId >= 1112000 && itemId < 1113000;
    }
    
    public static boolean isEffectRing(final int itemid) {
        return isFriendshipRing(itemid) || isCrushRing(itemid) || isMarriageRing(itemid);
    }
    
    public static boolean isMarriageRing(final int itemId) {
        switch (itemId) {
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
    
    public static boolean isFriendshipRing(final int itemId) {
        switch (itemId) {
            case 1049000:
            case 1112015:
            case 1112016:
            case 1112800:
            case 1112801:
            case 1112802:
            case 1112804:
            case 1112810:
            case 1112811:
            case 1112812:
            case 1112816:
            case 1112817:
            case 1112822: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isCrushRing(final int itemId) {
        switch (itemId) {
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
            case 1112013:
            case 1112015: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static int Equipment_Bonus_EXP(final int itemid) {
        switch (itemid) {
            case 1122017:
            case 1122086:
            case 1122207:
            case 1122215: {
                return 30;
            }
            default: {
                return 0;
            }
        }
    }
    
    public static int getExpForLevel(final int i, final int itemId) {
        if (isReverseItem(itemId)) {
            return getReverseRequiredEXP(i);
        }
        if (getMaxLevel(itemId) > 0) {
            return getTimelessRequiredEXP(i);
        }
        return 0;
    }
    
    public static int getMaxLevel(final int itemId) {
        if (isTimelessItem(itemId)) {
            return 5;
        }
        if (isReverseItem(itemId)) {
            return 3;
        }
        switch (itemId) {
            case 1302108:
            case 1302109:
            case 1312040:
            case 1312041:
            case 1322066:
            case 1322067:
            case 1332082:
            case 1332083:
            case 1372047:
            case 1372048:
            case 1382063:
            case 1382064:
            case 1402054:
            case 1402055:
            case 1412036:
            case 1412037:
            case 1422040:
            case 1422041:
            case 1432051:
            case 1432052:
            case 1442072:
            case 1442073:
            case 1452063:
            case 1452064:
            case 1462057:
            case 1462058:
            case 1472078:
            case 1472079:
            case 1482035:
            case 1482036: {
                return 1;
            }
            case 1072376: {
                return 2;
            }
            default: {
                return 0;
            }
        }
    }
    
    public static int getStatChance() {
        return 25;
    }
    
    public static MonsterStatus getStatFromWeapon(final int itemid) {
        switch (itemid) {
            case 1302109:
            case 1312041:
            case 1322067:
            case 1332083:
            case 1372048:
            case 1382064:
            case 1402055:
            case 1412037:
            case 1422041:
            case 1432052:
            case 1442073:
            case 1452064:
            case 1462058:
            case 1472079:
            case 1482035: {
                return MonsterStatus.ACC;
            }
            case 1302108:
            case 1312040:
            case 1322066:
            case 1332082:
            case 1372047:
            case 1382063:
            case 1402054:
            case 1412036:
            case 1422040:
            case 1432051:
            case 1442072:
            case 1452063:
            case 1462057:
            case 1472078:
            case 1482036: {
                return MonsterStatus.SPEED;
            }
            default: {
                return null;
            }
        }
    }
    
    public static int getXForStat(final MonsterStatus stat) {
        switch (stat) {
            case ACC: {
                return -70;
            }
            case SPEED: {
                return -50;
            }
            default: {
                return 0;
            }
        }
    }
    
    public static int getSkillForStat(final MonsterStatus stat) {
        switch (stat) {
            case ACC: {
                return 3221006;
            }
            case SPEED: {
                return 3121007;
            }
            default: {
                return 0;
            }
        }
    }
    
    public static int getSkillBook(final int job) {
        return 0;
    }
    
    public static int getSkillBookForSkill(final int skillid) {
        return getSkillBook(skillid / 10000);
    }
    
    public static int getMountItem(final int sourceid) {
        switch (sourceid) {
            case 5221006: {
                return 1932000;
            }
            case 1013:
            case 10001014: {
                return 1932001;
            }
            case 1014:
            case 10001015: {
                return 1932002;
            }
            case 1015:
            case 10001016: {
                return 1932007;
            }
            case 1017:
            case 10001019:
            case 20001019: {
                return 1932003;
            }
            case 3015998: {
                return 1933391;
            }
            case 3010075: {
                return 1933019;
            }
            case 3010086: {
                return 1933025;
            }
            case 3010093: {
                return 1933024;
            }
            case 3010117: {
                return 1933006;
            }
            case 3010118: {
                return 1933005;
            }
            case 3010123: {
                return 1933021;
            }
            case 3010125: {
                return 1933017;
            }
            case 3010141: {
                return 1933002;
            }
            case 3010142: {
                return 1933003;
            }
            case 3010145: {
                return 1933022;
            }
            case 3010146: {
                return 1933022;
            }
            case 3010151: {
                return 1933007;
            }
            case 3010153: {
                return 1933009;
            }
            case 3010156: {
                return 1933008;
            }
            case 3010162: {
                return 1933010;
            }
            case 3010163: {
                return 1933013;
            }
            case 3010164: {
                return 1933013;
            }
            case 3010166: {
                return 1933011;
            }
            case 3010167: {
                return 1933002;
            }
            case 3010183: {
                return 1933012;
            }
            case 3010204: {
                return 1933026;
            }
            case 3010217: {
                return 1933027;
            }
            case 3010219: {
                return 1933028;
            }
            case 3010220: {
                return 1933029;
            }
            case 3010221: {
                return 1933030;
            }
            case 3010226: {
                return 1933031;
            }
            case 3010230: {
                return 1933002;
            }
            case 3010241: {
                return 1933019;
            }
            case 3010242: {
                return 1933007;
            }
            case 3010253: {
                return 1933013;
            }
            case 3010255: {
                return 1933022;
            }
            case 3010266: {
                return 1933933;
            }
            case 3010279: {
                return 1933352;
            }
            case 3010282: {
                return 1933035;
            }
            case 3010283: {
                return 1933036;
            }
            case 3010285: {
                return 1933099;
            }
            case 3010286: {
                return 1933038;
            }
            case 3010287: {
                return 1933039;
            }
            case 3010299: {
                return 1933006;
            }
            case 3010304: {
                return 1933028;
            }
            case 3010305: {
                return 1933032;
            }
            case 3010306: {
                return 1933021;
            }
            case 3010312: {
                return 1933040;
            }
            case 3010323: {
                return 1933000;
            }
            case 3010338: {
                return 1933008;
            }
            case 3010350: {
                return 1933006;
            }
            case 3010355: {
                return 1933042;
            }
            case 3010359: {
                return 1933035;
            }
            case 3010362: {
                return 1933043;
            }
            case 3010366: {
                return 1933028;
            }
            case 3010367: {
                return 1933004;
            }
            case 3010400: {
                return 1933042;
            }
            case 3010401: {
                return 1933041;
            }
            case 3010412: {
                return 1933028;
            }
            case 3010423: {
                return 1933371;
            }
            case 3010424: {
                return 1933046;
            }
            case 3010462: {
                return 1933047;
            }
            case 3010473: {
                return 1933003;
            }
            case 3010480: {
                return 1933006;
            }
            case 3010483: {
                return 1933020;
            }
            case 3010485: {
                return 1933012;
            }
            case 3010489: {
                return 1933023;
            }
            case 3010495: {
                return 1933049;
            }
            case 3010501: {
                return 1933352;
            }
            case 3010506: {
                return 1933352;
            }
            case 3010527: {
                return 1933004;
            }
            case 3010528: {
                return 1933007;
            }
            case 3010571: {
                return 1933371;
            }
            case 3010583: {
                return 1933050;
            }
            case 3010590: {
                return 1933051;
            }
            case 3010592: {
                return 1933099;
            }
            case 3010595: {
                return 1933052;
            }
            case 3010599: {
                return 1933053;
            }
            case 3010610: {
                return 1933054;
            }
            case 3010651: {
                return 1933056;
            }
            case 3010652: {
                return 1933057;
            }
            case 3010653: {
                return 1933058;
            }
            case 3010654: {
                return 1933059;
            }
            case 3010655: {
                return 1933060;
            }
            case 3010656: {
                return 1933061;
            }
            case 3010682: {
                return 1933047;
            }
            case 3010700: {
                return 1933064;
            }
            case 3010703: {
                return 1933043;
            }
            case 3010704: {
                return 1933065;
            }
            case 3010705: {
                return 1933352;
            }
            case 3010706: {
                return 1933005;
            }
            case 3010708: {
                return 1933066;
            }
            case 3010719: {
                return 1933069;
            }
            case 3010742: {
                return 1933072;
            }
            case 3010743: {
                return 1933073;
            }
            case 3010747: {
                return 1933075;
            }
            case 3010748: {
                return 1933076;
            }
            case 3010749: {
                return 1933077;
            }
            case 3010750: {
                return 1933078;
            }
            case 3010751: {
                return 1933079;
            }
            case 3010761: {
                return 1933080;
            }
            case 3010779: {
                return 1933082;
            }
            case 3010780: {
                return 1933081;
            }
            case 3010783: {
                return 1933083;
            }
            case 3010794: {
                return 1933084;
            }
            case 3010811: {
                return 1933087;
            }
            case 3010812: {
                return 1933085;
            }
            case 3010813: {
                return 1933086;
            }
            case 3010824: {
                return 1933089;
            }
            case 3010825: {
                return 1933090;
            }
            case 3010826: {
                return 1933091;
            }
            case 3010827: {
                return 1933092;
            }
            case 3010828: {
                return 1933093;
            }
            case 3010829: {
                return 1933094;
            }
            case 3010830: {
                return 1933095;
            }
            case 3010831: {
                return 1933096;
            }
            case 3010832: {
                return 1933097;
            }
            case 3010835: {
                return 1933098;
            }
            case 3010837: {
                return 1933103;
            }
            case 3010838: {
                return 1933102;
            }
            case 3010839: {
                return 1933067;
            }
            case 3010842: {
                return 1933099;
            }
            case 3010843: {
                return 1933100;
            }
            case 3010844: {
                return 1933101;
            }
            case 3010855: {
                return 1933028;
            }
            case 3010876: {
                return 1933105;
            }
            case 3010878: {
                return 1933106;
            }
            case 3010883: {
                return 1933036;
            }
            case 3010889: {
                return 1933010;
            }
            case 3010890: {
                return 1933011;
            }
            case 3010925: {
                return 1933080;
            }
            case 3010930: {
                return 1933038;
            }
            case 3010936: {
                return 1933107;
            }
            case 3010955: {
                final Random rand = new Random();
                final int luck = rand.nextInt(100);
                if (luck <= 50) {
                    return 1933047;
                }
                if (luck > 50) {
                    return 1933049;
                }
                return 1933105;
            }
            case 3010964: {
                return 1933105;
            }
            case 3010969: {
                return 1933069;
            }
            case 3010980: {
                return 1933110;
            }
            case 3010988: {
                return 1933113;
            }
            case 3010989: {
                return 1933114;
            }
            case 3010990: {
                return 1933115;
            }
            case 3010991: {
                return 1933116;
            }
            case 3010992: {
                return 1933117;
            }
            case 3010993: {
                return 1933118;
            }
            case 3010994: {
                return 1933119;
            }
            case 3010995: {
                return 1933120;
            }
            case 3010996: {
                return 1933121;
            }
            case 3010997: {
                return 1933122;
            }
            case 3010998: {
                return 1933123;
            }
            case 3015000: {
                return 1933111;
            }
            case 3015002: {
                return 1933112;
            }
            case 3015008: {
                return 1933138;
            }
            case 3015013: {
                return 1933137;
            }
            case 3015014: {
                return 1933139;
            }
            case 3015015: {
                return 1933124;
            }
            case 3015016: {
                return 1933125;
            }
            case 3015017: {
                return 1933126;
            }
            case 3015018: {
                return 1933127;
            }
            case 3015019: {
                return 1933128;
            }
            case 3015020: {
                return 1933129;
            }
            case 3015021: {
                return 1933130;
            }
            case 3015022: {
                return 1933131;
            }
            case 3015023: {
                return 1933132;
            }
            case 3015024: {
                return 1933133;
            }
            case 3015025: {
                return 1933134;
            }
            case 3015026: {
                return 1933135;
            }
            case 3015027: {
                return 1933136;
            }
            case 3015031: {
                return 1933141;
            }
            case 3015035: {
                return 1933147;
            }
            case 3015048: {
                return 1933144;
            }
            case 3015049: {
                return 1933145;
            }
            case 3015050: {
                return 1933146;
            }
            case 3015999: {
                return 1933388;
            }
            default: {
                return 0;
            }
        }
    }
    
    public static boolean isKatara(final int itemId) {
        return itemId / 10000 == 134;
    }
    
    public static boolean isDagger(final int itemId) {
        return itemId / 10000 == 133;
    }
    
    public static boolean isApplicableSkill(final int skil) {
        return skil < 40000000 && (skil % 10000 < 8000 || skil % 10000 > 8003);
    }
    
    public static boolean isApplicableSkill_(final int skil) {
        return skil >= 90000000 || (skil % 10000 >= 8000 && skil % 10000 <= 8003);
    }
    
    public static boolean isTablet(final int itemId) {
        return itemId / 1000 == 2047;
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
    
    public static boolean isAccessory(final int itemId) {
        return (itemId >= 1010000 && itemId < 1040000) || (itemId >= 1122000 && itemId < 1153000) || (itemId >= 1112000 && itemId < 1113000);
    }
    
    public static boolean potentialIDFits(final int potentialID, final int newstate, final int i) {
        if (newstate == 7) {
            return (i == 0 || Randomizer.nextInt(10) == 0) ? (potentialID >= 30000) : (potentialID >= 20000 && potentialID < 30000);
        }
        if (newstate == 6) {
            return (i == 0 || Randomizer.nextInt(10) == 0) ? (potentialID >= 20000 && potentialID < 30000) : (potentialID >= 10000 && potentialID < 20000);
        }
        return newstate == 5 && ((i == 0 || Randomizer.nextInt(10) == 0) ? (potentialID >= 10000 && potentialID < 20000) : (potentialID < 10000));
    }
    
    public static boolean optionTypeFits(final int optionType, final int itemId) {
        switch (optionType) {
            case 10: {
                return isWeapon(itemId);
            }
            case 11: {
                return !isWeapon(itemId);
            }
            case 20: {
                return itemId / 10000 == 109;
            }
            case 21: {
                return itemId / 10000 == 180;
            }
            case 40: {
                return isAccessory(itemId);
            }
            case 51: {
                return itemId / 10000 == 100;
            }
            case 52: {
                return itemId / 10000 == 110;
            }
            case 53: {
                return itemId / 10000 == 104 || itemId / 10000 == 105 || itemId / 10000 == 106;
            }
            case 54: {
                return itemId / 10000 == 108;
            }
            case 55: {
                return itemId / 10000 == 107;
            }
            case 90: {
                return false;
            }
            default: {
                return true;
            }
        }
    }
    
    public static boolean isJobFamily(final int baseJob, final int currentJob) {
        return currentJob >= baseJob && currentJob / 100 == baseJob / 100;
    }
    
    public static boolean isKOC(final int job) {
        return job >= 1000 && job < 2000;
    }
    
    public static boolean isAran(final int job) {
        return job >= 2000 && job <= 2112 && job != 2001;
    }
    
    public static boolean isAdventurer(final int job) {
        return job >= 0 && job < 1000;
    }
    
    public static boolean isCygnus(final int job) {
        return job >= 1000 && job <= 1512;
    }
    
    public static int getBofForJob(final int job) {
        if (isAdventurer(job)) {
            return 12;
        }
        if (isKOC(job)) {
            return 10000012;
        }
        return 20000012;
    }
    
    public static final boolean isMountItemAvailable(final int mountid, final int jobid) {
        if (jobid != 900 && mountid / 10000 == 190) {
            if (isKOC(jobid)) {
                if (mountid < 1902005 || mountid > 1902007) {
                    return false;
                }
            }
            else if (isAdventurer(jobid)) {
                if (mountid < 1902000 || mountid > 1902002) {
                    return false;
                }
            }
            else if (isAran(jobid) && (mountid < 1902015 || mountid > 1902018)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isEvanDragonItem(final int itemId) {
        return itemId >= 1940000 && itemId < 1980000;
    }
    
    public static boolean canScroll(final int itemId) {
        return itemId / 100000 != 19 && itemId / 100000 != 16;
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
    
    public static int getMasterySkill(final int job) {
        if (job >= 1410 && job <= 1412) {
            return 14100000;
        }
        if (job >= 410 && job <= 412) {
            return 4100000;
        }
        if (job >= 520 && job <= 522) {
            return 5200000;
        }
        return 0;
    }
    
    public static int getExpRate_Below10(final int job) {
        if (isAran(job) || isKOC(job)) {
            return 5;
        }
        return 1;
    }
    
    public static int getExpRate_Quest(final int level) {
        return 1;
    }
    
    public static String getCashBlockedMsg(final int id) {
        switch (id) {
            case 5062000: {
                return "这个东西只能通过自由市场玩家NPC";
            }
            default: {
                return "这个道具无法购买\r\n未来有机会开放购买。";
            }
        }
    }
    
    public static boolean isCustomReactItem(final int rid, final int iid, final int original) {
        if (rid == 2008006) {
            return iid == Calendar.getInstance().get(7) + 4001055;
        }
        return iid == original;
    }
    
    public static List<Balloon> getBalloons() {
        return GameConstants.lBalloon;
    }
    
    public static int getJobNumber(final int jobz) {
        final int job = jobz % 1000;
        if (job / 100 == 0) {
            return 0;
        }
        if (job / 10 == 0) {
            return 1;
        }
        return 2 + job % 10;
    }
    
    public static boolean is新手职业(final int job) {
        switch (job) {
            case 0:
            case 1000:
            case 2000:
            case 2001:
            case 2002:
            case 2003:
            case 2004:
            case 2005:
            case 3000:
            case 3001:
            case 3002:
            case 5000:
            case 6000:
            case 6001:
            case 10000:
            case 11000: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isCarnivalMaps(final int mapid) {
        return mapid / 100000 == 9800 && (mapid % 10 == 1 || mapid % 1000 == 100);
    }
    
    public static boolean isForceRespawn(final int mapid) {
        switch (mapid) {
            case 100010000:
            case 103000800:
            case 925100100: {
                return true;
            }
            default: {
                return mapid / 100000 == 9800 && (mapid % 10 == 1 || mapid % 1000 == 100);
            }
        }
    }
    
    public static int getFishingTime(final boolean vip, final boolean gm) {
        return gm ? 1000 : (vip ? 12000 : 12000);
    }
    
    public static int getCustomSpawnID(final int summoner, final int def) {
        switch (summoner) {
            case 9400589:
            case 9400748: {
                return 9400706;
            }
            default: {
                return def;
            }
        }
    }
    
    public static boolean canForfeit(final int questid) {
        switch (questid) {
            case 2312:
            case 20000:
            case 20010:
            case 20015:
            case 20020: {
                return false;
            }
            default: {
                return true;
            }
        }
    }
    
    public static boolean isGMEquip(final int itemId) {
        switch (itemId) {
            case 1002140:
            case 1042003:
            case 1062007:
            case 1322013: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isEventMap(final int mapid) {
        return (mapid >= 109010000 && mapid < 109050000) || (mapid > 109050001 && mapid < 109090000) || (mapid >= 809040000 && mapid <= 809040100);
    }
    
    public static boolean isExpChair(final int itemid) {
        switch (itemid / 10000) {
            case 302: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isFishingMap(final int mapId) {
        switch (mapId) {
            case 741000200:
            case 741000201:
            case 741000202:
            case 741000203:
            case 741000204:
            case 741000205:
            case 741000206:
            case 741000207:
            case 741000208:
            case 749050500:
            case 749050501:
            case 749050502: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isChair(final int itemid) {
        return itemid / 10000 == 302;
    }
    
    public static int getMaxDamage(final int level, final int jobid, final int skillid) {
        int max = 0;
        if (level < 20) {
            max += 900;
        }
        else if (level < 30) {
            max += 1800;
        }
        else if (level < 40) {
            max += 5000;
        }
        else if (level < 50) {
            max += 7000;
        }
        else if (level < 60) {
            max += 8000;
        }
        else if (level < 70) {
            max += 9000;
        }
        else if (level < 80) {
            max += 10000;
        }
        else if (level < 90) {
            max += 11000;
        }
        else if (level < 100) {
            max += 12000;
        }
        else if (level < 110) {
            max += 13000;
        }
        else {
            max = 500000;
        }
        if (isCygnus(jobid)) {
            max += 1000;
        }
        if (skillid == 21110004) {
            max *= 3;
        }
        else if (skillid == 1111005) {
            max *= 2;
        }
        else if (skillid == 21100004 || skillid == 4211006) {
            max = (int)((double)max * 1.5);
        }
        return max;
    }
    
    public static boolean isElseSkill(final int id) {
        switch (id) {
            case 1009:
            case 1020:
            case 1221011:
            case 3221001:
            case 3221007:
            case 4211006:
            case 10001009:
            case 10001020:
            case 20001009:
            case 20001020: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean Novice_Skill(final int skill) {
        switch (skill) {
            case 1000:
            case 10001000:
            case 20001000: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private static double getAttackRangeBySkill(final AttackInfo attack) {
        double defRange = 0.0;
        switch (attack.skill) {
            case 21120006: {
                defRange = 800000.0;
                break;
            }
            case 2121007:
            case 2221007:
            case 2321008: {
                defRange = 750000.0;
                break;
            }
            case 2221006:
            case 3101005:
            case 15111006:
            case 15111007:
            case 21101003: {
                defRange = 600000.0;
                break;
            }
            case 2111003: {
                defRange = 400000.0;
                break;
            }
            case 1121008:
            case 4001344: {
                defRange = 350000.0;
                break;
            }
            case 2211002: {
                defRange = 300000.0;
                break;
            }
            case 2001005:
            case 2211003:
            case 2311004:
            case 5110001: {
                defRange = 250000.0;
                break;
            }
            case 2321007:
            case 5221004: {
                defRange = 200000.0;
                break;
            }
            case 1000:
            case 20001000: {
                defRange = 120000.0;
                break;
            }
            case 12111005:
            case 12111006: {
                defRange = 400000.0;
                break;
            }
        }
        return defRange;
    }
    
    private static double getAttackRangeByWeapon(final MapleCharacter chr) {
        final IItem weapon_item = chr.getInventory(MapleInventoryType.EQUIPPED).getItem((short)(-11));
        final MapleWeaponType weapon = (weapon_item == null) ? MapleWeaponType.沒有武器 : getWeaponType(weapon_item.getItemId());
        switch (weapon) {
            case 槍: {
                return 200000.0;
            }
            case 拳套: {
                return 250000.0;
            }
            case 火槍:
            case 弓:
            case 弩: {
                return 180000.0;
            }
            default: {
                return 100000.0;
            }
        }
    }
    
    public static double getAttackRange(final MapleCharacter chr, final MapleStatEffect def, final AttackInfo attack) {
        final int rangeInc = chr.getStat().defRange;
        final double base = 450.0;
        double defRange = (base + (double)rangeInc) * (base + (double)rangeInc);
        if (def != null) {
            defRange += def.getMaxDistanceSq() + (double)(def.getRange() * def.getRange());
            if (getAttackRangeBySkill(attack) != 0.0) {
                defRange = getAttackRangeBySkill(attack);
            }
        }
        else {
            defRange = getAttackRangeByWeapon(chr);
        }
        return defRange;
    }
    
    public static boolean isMonsterSpawn(final int id) {
        switch (id) {
            case 220060000:
            case 220060100:
            case 220060200:
            case 220060201:
            case 220060300:
            case 220060301:
            case 220070000:
            case 220070100:
            case 220070200:
            case 220070201:
            case 220070300:
            case 220070301:
            case 270010100:
            case 270010200:
            case 270010300:
            case 270010400:
            case 270010500:
            case 270020100:
            case 270020200:
            case 270020300:
            case 270020400:
            case 270020500:
            case 270030100:
            case 270030200:
            case 270030300:
            case 270030400:
            case 270030500:
            case 741020101:
            case 741020102: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isBossMap(final int id) {
        switch (id) {
            case 220080001:
            case 240060000:
            case 240060100:
            case 240060200:
            case 280030000:
            case 551030200:
            case 800040208:
            case 801040003:
            case 801040100: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isNotToMap(final int id) {
        return (id >= 211060000 && id <= 211061000) || id == 180000001;
    }
    
    public static boolean isNotTo(final int id) {
        switch (id) {
            case 211060010:
            case 211060100:
            case 211060200:
            case 211060300:
            case 211060400:
            case 211060500:
            case 211060600:
            case 211060610:
            case 211060620:
            case 211060700:
            case 211060800:
            case 211060810:
            case 211060820:
            case 211060830:
            case 211060900: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isNotTo(final int id, final String portal) {
        return (id == 211060010 && portal.equals((Object)"east00")) || (id == 211060100 && portal.equals((Object)"west00")) || (id == 211060100 && portal.equals((Object)"east00")) || (id == 211060200 && portal.equals((Object)"west00")) || (id == 211060200 && portal.equals((Object)"east00")) || (id == 211060300 && portal.equals((Object)"east00")) || (id == 211060400 && portal.equals((Object)"west00")) || (id == 211060400 && portal.equals((Object)"out00")) || (id == 211060400 && portal.equals((Object)"east00")) || (id == 211060410 && portal.equals((Object)"in01")) || (id == 211060500 && portal.equals((Object)"east00")) || (id == 211060600 && portal.equals((Object)"out00")) || (id == 211060600 && portal.equals((Object)"out01")) || (id == 211060600 && portal.equals((Object)"east00")) || (id == 211060600 && portal.equals((Object)"west00")) || (id == 211060610 && portal.equals((Object)"in01")) || (id == 211060620 && portal.equals((Object)"in01")) || (id == 211060700 && portal.equals((Object)"east00")) || (id == 211060800 && portal.equals((Object)"out00")) || (id == 211060800 && portal.equals((Object)"out01")) || (id == 211060800 && portal.equals((Object)"out10")) || (id == 211060800 && portal.equals((Object)"out11")) || (id == 211060800 && portal.equals((Object)"out20")) || (id == 211060800 && portal.equals((Object)"east00")) || (id == 211060800 && portal.equals((Object)"up00")) || (id == 211060801 && portal.equals((Object)"east00")) || (id == 211060801 && portal.equals((Object)"down00")) || (id == 211060810 && portal.equals((Object)"in01")) || (id == 211060810 && portal.equals((Object)"in00")) || (id == 211060820 && portal.equals((Object)"in00")) || (id == 211060820 && portal.equals((Object)"in01")) || (id == 211060830 && portal.equals((Object)"in00")) || (id == 211060830 && portal.equals((Object)"in01")) || (id == 211060900 && portal.equals((Object)"west00")) || (id == 211061000 && portal.equals((Object)"west00")) || (id == 211061000 && portal.equals((Object)"out00")) || (id == 211061000 && portal.equals((Object)"out10")) || (id == 211061000 && portal.equals((Object)"out20")) || (id == 211061000 && portal.equals((Object)"up00")) || (id == 211061001 && portal.equals((Object)"down00")) || (id == 211061001 && portal.equals((Object)"st00"));
    }
    
    public static short getSlotMax(final int itemId) {
        switch (itemId) {
            case 4030003:
            case 4030004:
            case 4030005: {
                return 1;
            }
            case 3993000:
            case 3993002:
            case 3993003:
            case 4001168:
            case 4031306:
            case 4031307: {
                return 100;
            }
            case 5220010:
            case 5220013: {
                return 1000;
            }
            case 5220020: {
                return 2000;
            }
            case 2000005:
            case 2000019:
            case 2001505: {
                return 1000;
            }
            default: {
                return 0;
            }
        }
    }
    
    public static short getStat(final int itemId, final int def) {
        switch (itemId) {
            case 1002419: {
                return 5;
            }
            case 1002959: {
                return 25;
            }
            case 1142002: {
                return 10;
            }
            case 1122121: {
                return 7;
            }
            default: {
                return (short)def;
            }
        }
    }
    
    public static short getHpMp(final int itemId, final int def) {
        switch (itemId) {
            case 1122121: {
                return 500;
            }
            case 1002959:
            case 1142002: {
                return 1000;
            }
            default: {
                return (short)def;
            }
        }
    }
    
    public static short getATK(final int itemId, final int def) {
        switch (itemId) {
            case 1122121: {
                return 3;
            }
            case 1002959: {
                return 4;
            }
            case 1142002: {
                return 9;
            }
            default: {
                return (short)def;
            }
        }
    }
    
    public static short getDEF(final int itemId, final int def) {
        switch (itemId) {
            case 1122121: {
                return 250;
            }
            case 1002959: {
                return 500;
            }
            default: {
                return (short)def;
            }
        }
    }
    
    public static boolean isPickupRestricted(final int itemId) {
        return itemId == 4030003 || itemId == 4030004;
    }
    
    public static boolean isDropRestricted(final int itemId) {
        return itemId == 3012000 || itemId == 4030004 || itemId == 1052098 || itemId == 1052202;
    }
    
    static {
        GameConstants.冒险岛名字 = ServerProperties.getProperty("CongMS.serverName");
        rangedMapobjectTypes = Collections.unmodifiableList((List<? extends MapleMapObjectType>)Arrays.asList(MapleMapObjectType.ITEM, MapleMapObjectType.MONSTER, MapleMapObjectType.DOOR, MapleMapObjectType.REACTOR, MapleMapObjectType.SUMMON, MapleMapObjectType.NPC, MapleMapObjectType.MIST));
        ExpTable = new int[] { 0, 15, 34, 57, 92, 135, 372, 560, 840, 1242, 1716, 2360, 3216, 4200, 5460, 7050, 8840, 11040, 13716, 16680, 20216, 24402, 28980, 34320, 40512, 47216, 54900, 63666, 73080, 83720, 95700, 108480, 122760, 138666, 155540, 174216, 194832, 216600, 240500, 266682, 294216, 324240, 356916, 391160, 428280, 468450, 510420, 555680, 604416, 655200, 709716, 748608, 789631, 832902, 878545, 926689, 977471, 1031036, 1087536, 1147132, 1209994, 1276301, 1346242, 1420016, 1497832, 1579913, 1666492, 1757815, 1854143, 1955750, 2062925, 2175973, 2295216, 2410993, 2553663, 2693603, 2841212, 2996910, 3161140, 3334370, 3517093, 3709829, 3913127, 4127566, 4353756, 4592341, 4844001, 5109452, 5389449, 5684790, 5996316, 6324914, 6671519, 7037118, 7422752, 7829518, 8258575, 8711144, 9188514, 9692044, 10223168, 10783397, 11374327, 11997640, 12655110, 13348610, 14080113, 14851703, 15665576, 16524049, 17429566, 18384706, 19392187, 20454878, 21575805, 22758159, 24005306, 25320796, 26708375, 28171993, 29715818, 31344244, 33061908, 34873700, 36784778, 38800583, 40926854, 43169645, 45535341, 48030677, 50662758, 53439077, 56367538, 59456479, 62714694, 66151459, 69776558, 73600313, 77633610, 81887931, 86375389, 91108760, 96101520, 101367883, 106922842, 112782213, 118962678, 125481832, 132358236, 139611467, 147262175, 155332142, 163844343, 172823012, 182293713, 192283408, 202820538, 213935103, 225658746, 238024845, 251068606, 264827165, 279339693, 294647508, 310794191, 327825712, 345790561, 364739883, 384727628, 405810702, 428049128, 451506220, 476248760, 502347192, 529875818, 558913012, 589541445, 621848316, 655925603, 691870326, 729784819, 769777027, 811960808, 856456260, 903390063, 952895838, 1005114529, 1060194805, 1118293480, 1179575962, 1244216724, 1312399800, 1384319309, 1460180007, 1540197871, 1624600714, 1713628833, 1807535693, 1906588648, 2011069705, 2121276324, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323, 2121276323 };
        ClosenessTable = new int[] { 0, 1, 3, 6, 14, 31, 60, 108, 181, 287, 434, 632, 891, 1224, 1642, 2161, 2793, 3557, 4467, 5542, 6801, 8263, 9950, 11882, 14084, 16578, 19391, 22547, 26074, 30000 };
        MountExpTable = new int[] { 0, 6, 25, 50, 105, 134, 196, 254, 263, 315, 367, 430, 543, 587, 679, 725, 897, 1146, 1394, 1701, 2247, 2543, 2898, 3156, 3313, 3584, 3923, 4150, 4305, 4550 };
        itemBlock = new int[] { 2340000, 2049100, 4001129, 2040037, 2040006, 2040007, 2040303, 2040403, 2040506, 2040507, 2040603, 2040709, 2040710, 2040711, 2040806, 2040903, 2041024, 2041025, 2043003, 2043103, 2043203, 2043303, 2043703, 2043803, 2044003, 2044103, 2044203, 2044303, 2044403, 2044503, 2044603, 2044908, 2044815, 2044019, 2044703, 1004001, 4007008, 1004002, 5152053, 5150040 };
        cashBlock = new int[] { 5500001, 5500002, 5600001, 5350003, 5401000, 5490000, 5490001, 5500000, 5252001, 5252003, 5220001, 5220002, 5200000, 5200001, 5200002, 5320000, 5440000 };
        blockedSkills = new int[] { 4341003 };
        GameConstants.blockedMaps = new int[] { 109050000, 200000112, 200090020, 240060200, 280030000, 280090000, 280030001, 240060201, 900090021, 950101100, 950101010 };
        GameConstants.Equipments_Bonus = new int[] { 1122017, 1122086, 1122207, 1122215 };
        RESERVED = new String[] { "Rental" };
        stats = new String[] { "tuc", "reqLevel", "reqJob", "reqSTR", "reqDEX", "reqINT", "reqLUK", "reqPOP", "cash", "cursed", "success", "setItemID", "equipTradeBlock", "durability", "randOption", "randStat", "masterLevel", "reqSkillLevel", "elemDefault", "incRMAS", "incRMAF", "incRMAI", "incRMAL", "canLevel", "skill", "charmEXP" };
        Jxboxrewards = new int[] { 1112413, 1, 2040313, 1, 2040522, 1, 2040526, 1, 2040821, 1, 2041052, 1, 2043011, 1, 2043306, 1, 2043706, 1, 2043806, 1, 2044006, 1, 2041050, 3, 2044406, 1, 2044811, 1, 2044906, 1, 2040528, 1, 2040819, 3, 2040718, 3, 2044306, 1, 5120015, 1, 5121020, 1, 2022133, 3, 2022455, 3, 2002023, 3, 4001038, 3, 4001039, 3, 4001040, 3, 4001041, 3, 4001042, 3, 4001043, 3, 2022216, 3, 2022220, 3, 2022223, 3, 2000005, 3, 3010798, 1, 2012005, 1, 5220000, 1, 2043206, 1, 2044106, 1, 2044206, 1, 2043106, 1, 2044813, 1, 2022251, 4, 2022245, 3, 2210025, 4, 2210026, 4, 2210027, 4, 2022217, 5, 4001137, 3, 2210028, 4, 2022133, 10, 5121020, 10, 4001188, 4 };
        goldrewards = new int[] { 2340000, 1, 1402037, 1, 2290096, 1, 2290049, 1, 2290041, 1, 2290047, 1, 2290095, 1, 2290017, 1, 2290075, 1, 2290085, 1, 2290116, 1, 1302059, 3, 2049100, 1, 2340000, 1, 1092049, 1, 1102041, 1, 1432018, 3, 1022047, 3, 3010051, 1, 3010020, 1, 2040914, 1, 1432011, 3, 1442020, 3, 1382035, 3, 1372010, 3, 1332027, 3, 1302056, 3, 1402005, 3, 1472053, 3, 1462018, 3, 1452017, 3, 1422013, 3, 1322029, 3, 1412010, 3, 1472051, 1, 1482013, 1, 1492013, 1, 1382050, 1, 1382045, 1, 1382047, 1, 1382048, 1, 1382046, 1, 1332032, 4, 1302293, 3, 4001040, 4, 4001039, 4, 4001038, 4, 2030008, 5, 1442018, 3, 2040900, 4, 2000005, 10, 2000004, 10, 4280000, 4 };
        silverrewards = new int[] { 3010041, 1, 1002452, 3, 1002455, 3, 2290084, 1, 2290048, 1, 2290040, 1, 2290046, 1, 2290074, 1, 2290064, 1, 2290094, 1, 2290022, 1, 2290056, 1, 2290066, 1, 2290020, 1, 1102082, 1, 1302049, 1, 2340000, 1, 1102041, 1, 1452019, 2, 4001116, 3, 4001041, 3, 1022060, 2, 1432011, 3, 1442020, 3, 1382035, 3, 1372010, 3, 1332027, 3, 1302056, 3, 1402005, 3, 1472053, 3, 1462018, 3, 1452017, 3, 1422013, 3, 1322029, 3, 1412010, 3, 1002587, 3, 1402044, 1, 2101013, 4, 1442046, 1, 1422031, 1, 1332054, 3, 1012056, 3, 1022047, 3, 3012002, 1, 1442012, 3, 1442018, 3, 1432010, 3, 2000005, 10, 2000004, 10, 4280001, 4 };
        GameConstants.eventCommonReward = new int[] { 0, 40, 1, 10, 4031019, 5, 4280000, 3, 4280001, 4, 5490000, 3, 5490001, 4 };
        GameConstants.eventUncommonReward = new int[] { 2, 4, 3, 4, 5160000, 5, 5160001, 5, 5160002, 5, 5160003, 5, 5160004, 5, 5160005, 5, 5160006, 5, 5160007, 5, 5160008, 5, 5160009, 5, 5160010, 5, 5160011, 5, 5160012, 5, 5160013, 5, 4001137, 5, 4001137, 5, 4080000, 5, 4080001, 5, 4080002, 5, 4080003, 5, 4080004, 5, 4080005, 5, 4080006, 5, 4080007, 5, 4080008, 5, 4080009, 5, 4080010, 5, 4080011, 5, 4080100, 5, 4031019, 5, 5121003, 5, 5150042, 5, 2022463, 5, 2022463, 5, 2450000, 2 };
        GameConstants.eventRareReward = new int[] { 4031019, 5, 2049100, 5, 4001137, 10, 2049301, 20, 2049400, 3, 2340000, 1, 3010130, 5, 3010131, 5, 3010132, 5, 3010133, 5, 3010136, 5, 3010116, 5, 3010117, 5, 3010118, 5, 1112405, 1, 1112413, 1, 1112414, 1, 2040211, 1, 2040212, 1, 2049000, 2, 2049001, 2, 2049002, 2, 2049003, 2, 1012058, 2, 1012059, 2, 1012060, 2, 1012061, 2 };
        GameConstants.eventSuperReward = new int[] { 4031019, 5, 4031307, 50, 3010127, 10, 3010128, 10, 3010137, 10, 4001137, 10, 1012139, 10, 1012140, 10, 1012141, 10 };
        GameConstants.fishingReward = new int[] { 0, 40, 1, 40, 1302021, 5, 1072238, 1, 1072239, 1, 2049100, 1, 1302000, 3, 1442011, 1, 4031627, 2, 4031628, 1, 4031630, 1, 4031631, 1, 4031632, 1, 4031633, 2, 4031634, 1, 4031635, 1, 4031636, 1, 4031637, 2, 4031638, 2, 4031639, 1, 4031640, 1, 4031641, 2, 4031642, 2, 4031643, 1, 4031644, 1, 4031645, 2, 4031646, 2, 4031647, 1, 4031648, 1, 4031629, 1, 1102041, 1, 1102042, 1, 2101120, 1 };
        normalDrops = new int[] { 4001009, 4001010, 4001011, 4001012, 4001013, 4001014, 4001021, 4001038, 4001039, 4001040, 4001041, 4001042, 4001043, 4001038, 4001039, 4001040, 4001041, 4001042, 4001043, 4001038, 4001039, 4001040, 4001041, 4001042, 4001043, 4000164, 2000000, 2000003, 2000004, 2000005, 4000019, 4000000, 4000016, 4000006, 2100121, 4000029, 4000064, 5110000, 4000306, 4032181, 4006001, 4006000, 2050004, 3994102, 3994103, 3994104, 3994105, 2430007, 4000164, 2000000, 2000003, 2000004, 2000005, 4000019, 4000000, 4000016, 4000006, 2100121, 4000029, 4000064, 5110000, 4000306, 4032181, 4006001, 4006000, 2050004, 3994102, 3994103, 3994104, 3994105, 2430007, 4000164, 2000000, 2000003, 2000004, 2000005, 4000019, 4000000, 4000016, 4000006, 2100121, 4000029, 4000064, 5110000, 4000306, 4032181, 4006001, 4006000, 2050004, 3994102, 3994103, 3994104, 3994105, 2430007 };
        rareDrops = new int[] { 2049100, 2049301, 2049401, 2022326, 2022193, 2049000, 2049001, 2049002 };
        superDrops = new int[] { 2040804, 2049400, 2049100 };
        GameConstants.owlItems = new int[] { 1082002, 2070005, 2070006, 1022047, 1102041, 2044705, 2340000, 2040017, 1092030, 2040804 };
        lBalloon = Arrays.asList(new Balloon("欢迎來到" + LoginServer.getServerName(), 236, 122), new Balloon("禁止开外挂", 0, 276), new Balloon("开服联系唯一QQ：327321366", 196, 263));
    }
}
