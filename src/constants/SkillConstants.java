package constants;

import client.MapleJob;

public class SkillConstants
{
    public static boolean isSkill92XX0000(final int skillId) {
        return skillId / 1000000 == 92 && skillId % 10000 == 0;
    }
    
    public static boolean isSkill92XX____(final int skillId) {
        return !isSkill92XX0000(skillId) && isSkill92XX0000(10000 * (skillId / 10000));
    }
    
    public static boolean is4thNotNeedMasterLevel(final int skillId) {
        if (skillId > 5220014) {
            if (skillId > 23120011) {
                return skillId == 23120013 || skillId == 23121008 || skillId == 33120010 || skillId == 35120014 || skillId == 51120000;
            }
            return skillId == 23120011 || skillId == 5320007 || skillId == 5321004 || skillId == 5321006 || skillId == 21120011 || skillId == 21120014 || skillId == 22181004;
        }
        else {
            if (skillId == 5220014) {
                return true;
            }
            if (skillId <= 4110012) {
                return skillId == 4110012 || skillId == 1120012 || skillId == 1320011 || skillId == 2121009 || skillId == 2221009 || skillId == 2321010 || skillId == 3210015;
            }
            if (skillId != 4210012 && skillId != 4340010 && skillId != 4340012) {
                if (skillId <= 5120010) {
                    return false;
                }
                if (skillId > 5120012) {
                    return skillId == 5220012;
                }
            }
            return true;
        }
    }
    
    public static boolean isNot4thNeedMasterLevel(final int skillId) {
        if (skillId > 101100101) {
            return skillId == 101100201 || skillId == 101110102 || skillId == 101110200 || skillId == 101110203 || skillId == 101120104 || skillId == 101120204;
        }
        return skillId == 101100101 || skillId == 4311003 || skillId == 4321006 || skillId == 4330009 || skillId == 4331002 || skillId == 4341004 || skillId == 4341007 || skillId == 101000101;
    }
    
    public static boolean isSkillNeedMasterLevel(final int skillId) {
        if (is4thNotNeedMasterLevel(skillId)) {
            return false;
        }
        if (isSkill92XX0000(skillId)) {
            return false;
        }
        if (isSkill92XX____(skillId)) {
            return false;
        }
        if (MapleJob.isJob8000(skillId)) {
            return false;
        }
        final int jobid = getJobBySkill(skillId);
        if (MapleJob.is初心者(jobid) || MapleJob.isJob9500(skillId) || skillId == 42120024) {
            return false;
        }
        final int jobTimes = MapleJob.get轉數(jobid);
        return isNot4thNeedMasterLevel(skillId) || jobTimes == 4;
    }
    
    public static int get紫扇傳授UnknownValue(final int skillId) {
        int result;
        if (skillId == 40020002 || skillId == 80000004) {
            result = 100;
        }
        else {
            result = 0;
        }
        return result;
    }
    
    public static int getJobBySkill(final int skillId) {
        int result = skillId / 10000;
        if (skillId / 10000 == 8000) {
            result = skillId / 100;
        }
        return result;
    }
    
    public static boolean isApplicableSkill(final int skil) {
        return ((skil < 80000000 || skil >= 100000000) && (skil % 10000 < 8000 || skil % 10000 > 8006) && !isAngel(skil)) || skil >= 92000000 || (skil >= 80000000 && skil < 80010000);
    }
    
    public static boolean isRidingSKill(final int skil) {
        return skil >= 80001000 && skil < 80010000;
    }
    
    public static boolean isAngel(final int skillId) {
        if (MapleJob.isBeginner(skillId / 10000) || skillId / 100000 == 800) {
            switch (skillId % 10000) {
                case 86:
                case 1085:
                case 1087:
                case 1090:
                case 1179: {
                    return true;
                }
            }
        }
        switch (skillId) {
            case 80000052:
            case 80000053:
            case 80000054:
            case 80000086:
            case 80001154:
            case 80001262:
            case 80001518:
            case 80001519:
            case 80001520:
            case 80001521:
            case 80001522:
            case 80001523:
            case 80001524:
            case 80001525:
            case 80001526:
            case 80001527:
            case 80001528:
            case 80001529:
            case 80001530:
            case 80001715:
            case 80001716:
            case 80001717:
            case 80001718:
            case 80001719:
            case 80001720:
            case 80001721:
            case 80001722:
            case 80001723:
            case 80001724:
            case 80001725:
            case 80001726:
            case 80001727: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean is紫扇仰波(final int id) {
        return id == 42001000 || (id > 42001004 && id <= 42001006);
    }
    
    public static boolean is初心者紫扇仰波(final int id) {
        return id == 40021185 || id == 42001006 || id == 80011067;
    }
    
    public static boolean sub_9F5282(final int id) {
        return id == 4221052 || id == 65121052;
    }
    
    public static boolean sub_9F529C(final int id) {
        return id == 13121052 || id - 13121052 == 1000000 || id - 13121052 == 2000000 || id - 13121052 == 66880377 || id - 13121052 == 66880379 || id - 13121052 - 66880379 == 19999852;
    }
    
    public static boolean isKeyDownSkillWithPos(final int id) {
        return id == 13111020 || id == 112111016;
    }
    
    public static int getHyperAddBullet(final int id) {
        if (id == 4121013) {
            return 4120051;
        }
        if (id == 5321012) {
            return 5320051;
        }
        return 0;
    }
    
    public static int getHyperAddAttack(final int id) {
        if (id > 12120011) {
            if (id > 41121001) {
                if (id > 61121100) {
                    if (id > 112101009) {
                        if (id == 112111004) {
                            return 112120050;
                        }
                        if (id > 112119999 && id <= 112120003) {
                            return 112120053;
                        }
                        return 0;
                    }
                    else {
                        if (id == 112101009) {
                            return 112120048;
                        }
                        if (id != 61121201) {
                            if (id > 65121006 && (id <= 65121008 || id == 65121101)) {
                                return 65120051;
                            }
                            return 0;
                        }
                    }
                }
                else if (id != 61121100) {
                    switch (id) {
                        case 41121002: {
                            return 41120050;
                        }
                        case 41121018:
                        case 41121021: {
                            return 41120048;
                        }
                        case 42121000: {
                            return 42120045;
                        }
                        case 51121007: {
                            return 51120051;
                        }
                        case 51121008: {
                            return 51120048;
                        }
                        default: {
                            return 0;
                        }
                    }
                }
                return 61120045;
            }
            if (id == 41121001) {
                return 41120044;
            }
            if (id > 21121013) {
                if (id == 22181002) {
                    return 0;
                }
                if (id == 25121005) {
                    return 25120148;
                }
                if (id == 31111005) {
                    return 31120044;
                }
                if (id == 31121001) {
                    return 31120050;
                }
                if (id == 32111003) {
                    return 0;
                }
                if (id == 35121016) {
                    return 35120051;
                }
            }
            else {
                if (id == 21121013) {}
                if (id == 13121002) {
                    return 13120048;
                }
                if (id - 13121002 == 1000000) {
                    return 14120045;
                }
                if (id - 13121002 == 1990020 || id - 13121002 == 1999001) {
                    return 15120045;
                }
                if (id - 13121002 == 2000000) {
                    return 15120048;
                }
                if (id - 13121002 - 2000000 == 5999003) {
                    return 21120047;
                }
                if (id - 13121002 - 2000000 - 5999003 == 1) {
                    return 21120049;
                }
            }
        }
        else {
            if (id == 12120011) {
                return 12120046;
            }
            if (id <= 5121017) {
                if (id >= 5121016) {
                    return 5120051;
                }
                if (id <= 3121015) {
                    switch (id) {
                        case 3121015: {
                            return 3120048;
                        }
                        case 1120017:
                        case 1121008: {
                            return 1120051;
                        }
                        case 1221009: {
                            return 1220048;
                        }
                        case 1221011: {
                            return 1220050;
                        }
                        case 2121003: {
                            return 2120049;
                        }
                        case 2121006: {
                            return 2120048;
                        }
                        case 2221006: {
                            return 2220048;
                        }
                        default: {
                            return 0;
                        }
                    }
                }
                else {
                    if (id == 3121020) {
                        return 3120051;
                    }
                    if (id == 3221017) {
                        return 3220048;
                    }
                    if (id == 4221007) {
                        return 4220048;
                    }
                    if (id == 4331000) {
                        return 4340045;
                    }
                    if (id == 4341009) {
                        return 4340048;
                    }
                    if (id != 5121007) {
                        return 0;
                    }
                    return 5120048;
                }
            }
            else if (id > 5721064) {
                if (id == 11121103 || id - 11121103 == 100) {
                    return 11120048;
                }
                if (id - 11121103 == 878923 || id - 11121103 == 978925 || id - 11121103 == 988925 || id - 11121103 == 998907) {
                    return 12120045;
                }
            }
            else {
                if (id == 5721064) {
                    return 5720048;
                }
                if (id == 5121020) {
                    return 5120048;
                }
                if (id - 5121020 == 99996) {
                    return 5220047;
                }
                if (id - 5121020 == 198991) {}
                if (id - 5121020 == 199980) {
                    return 5320048;
                }
                if (id - 5121020 == 199984) {
                    return 5320043;
                }
                if (id - 5121020 == 600041) {
                    return 5720045;
                }
            }
        }
        return 0;
    }
    
    public static int SkillIncreaseMobCount(final int sk) {
        int inc = 0;
        switch (sk) {
            case 1121008:
            case 1211008:
            case 2211007:
            case 2221006:
            case 2221012:
            case 3121015:
            case 3221017:
            case 4121017:
            case 4221007:
            case 4331000:
            case 4341004:
            case 5121016:
            case 5321000:
            case 5721007:
            case 11121103:
            case 11121203:
            case 12120011:
            case 13121002:
            case 15121002:
            case 24121000:
            case 24121005:
            case 27121202:
            case 27121303:
            case 32121003:
            case 33121002:
            case 35121015:
            case 36121000:
            case 36121011:
            case 36121012:
            case 41121009:
            case 41121018:
            case 41121021:
            case 42121000:
            case 51121008:
            case 61111100:
            case 112001008:
            case 112101009:
            case 112111004: {
                inc = 2;
                break;
            }
            case 1201011:
            case 1201012:
            case 1221004: {
                inc = 3;
                break;
            }
        }
        return inc;
    }
}
