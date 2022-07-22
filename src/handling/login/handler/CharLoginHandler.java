package handling.login.handler;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import database.DBConPool;
import java.util.Iterator;
import handling.cashshop.CashShopServer;
import client.inventory.IItem;
import client.inventory.MapleInventory;
import client.inventory.Item;
import server.quest.MapleQuest;
import server.MapleItemInformationProvider;
import client.inventory.MapleInventoryType;
import handling.login.LoginInformationProvider;
import client.MapleCharacterUtil;
import client.MapleCharacter;
import java.util.List;
import constants.WorldConstants;
import handling.channel.ChannelServer;
import server.ServerProperties;
import gui.CongMS;
import java.util.Calendar;
import handling.login.LoginWorker;
import tools.KoreanDateUtil;
import tools.MaplePacketCreator;
import handling.login.LoginServer;
import tools.FileoutputUtil;
import tools.StringUtil;
import java.util.Random;
import tools.packet.LoginPacket;
import tools.data.LittleEndianAccessor;
import client.MapleClient;

public class CharLoginHandler
{
    private static boolean loginFailCount(final MapleClient c) {
        ++c.loginAttempt;
        return c.loginAttempt > 5;
    }
    
    public static final void handleWelcome(final MapleClient c) {
        c.sendPing();
    }
    
    public static final void LicenseRequest(final LittleEndianAccessor slea, final MapleClient c) {
        if (slea.readByte() == 1) {
            c.sendPacket(LoginPacket.licenseResult());
            c.updateLoginState(0, c.getSessionIPAddress());
        }
        else {
            c.getSession().close();
        }
    }
    
    public static String RandomString() {
        final Random random = new Random();
        String sRand = "";
        for (int i = 0; i < 6; ++i) {
            final String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }
        return sRand;
    }
    
    public static final void handleLogin(final LittleEndianAccessor slea, final MapleClient c) {
        final String account = slea.readMapleAsciiString();
        final String password = slea.readMapleAsciiString();
        final String loginkey = RandomString();
        final int loginkeya = (int)((Math.random() * 9.0 + 1.0) * 100000.0);
        c.setAccountName(account);
        final int[] bytes = new int[6];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = slea.readByteAsInt();
        }
        final StringBuilder sps = new StringBuilder();
        for (int j = 0; j < bytes.length; ++j) {
            sps.append(StringUtil.getLeftPaddedStr(Integer.toHexString(bytes[j]).toUpperCase(), '0', 2));
            sps.append("-");
        }
        String macData = sps.toString();
        macData = macData.substring(0, macData.length() - 1);
        final boolean ipBan = c.hasBannedIP();
        final boolean macBan = c.hasBannedMac();
        final boolean ban = ipBan || macBan;
        int loginok = c.login(account, password, ban);
        final Calendar tempbannedTill = c.getTempBanCalendar();
        String errorInfo = null;
        if (c.getLastLoginTime() != 0L && c.getLastLoginTime() + 5000L < System.currentTimeMillis()) {
            errorInfo = "您登入的速度過快!\r\n請重新輸入.";
            loginok = 1;
        }
        else if (loginok == 0 && ban && !c.isGm()) {
            loginok = 3;
            FileoutputUtil.logToFile("logs/data/" + (macBan ? "MAC" : "IP") + "封鎖_登入账号.txt", "\r\n 时间\u3000[" + FileoutputUtil.NowTime() + "]  所有MAC位址: " + (Object)c.getMacs() + " IP地址: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号：\u3000" + account + " 密碼：" + password);
        }
        else {
            if (loginok == 0 && (c.getGender() == 10 || c.getSecondPassword() == null)) {
                c.sendPacket(LoginPacket.getGenderNeeded(c));
                return;
            }
            if (loginok == 5) {
                if (LoginServer.getAutoReg()) {
                    if (password.equalsIgnoreCase("fixlogged")) {
                        errorInfo = "這個密碼是解卡密碼，請換其他密碼。";
                    }
                    else if (account.length() >= 12) {
                        errorInfo = "您的账号長度太長了唷!\r\n請重新輸入.";
                    }
                    else {
                        AutoRegister.createAccount(account, password, c.getSession().remoteAddress().toString());
                        if (AutoRegister.success && AutoRegister.mac) {
                            errorInfo = "账号創建成功,請重新登入!";
                            FileoutputUtil.logToFile("logs/data/創建账号.txt", "\r\n 时间\u3000[" + FileoutputUtil.NowTime() + "] IP 地址 : " + c.getSession().remoteAddress().toString().split(":")[0] + " MAC: " + macData + " 账号：\u3000" + account + " 密碼：" + password);
                        }
                        else if (!AutoRegister.mac) {
                            errorInfo = "無法註冊過多的账号密碼唷!";
                            AutoRegister.success = false;
                            AutoRegister.mac = true;
                        }
                    }
                    loginok = 1;
                }
            }
            else if (!LoginServer.canLoginAgain(c.getAccID())) {
                final int sec = (int)((LoginServer.getLoginAgainTime(c.getAccID()) + 50000L - System.currentTimeMillis()) / 1000L);
                c.loginAttempt = 0;
                errorInfo = "游戏账号將於" + sec + "秒后可以登入， 請耐心等候。";
                loginok = 1;
            }
            else if (!LoginServer.canEnterGameAgain(c.getAccID())) {
                final int sec = (int)((LoginServer.getEnterGameAgainTime(c.getAccID()) + 60000L - System.currentTimeMillis()) / 1000L);
                c.loginAttempt = 0;
                errorInfo = "游戏账号將於" + sec + "秒后可以登入， 請耐心等候。";
                loginok = 1;
            }
        }
        if (loginok != 0) {
            if (!loginFailCount(c)) {
                c.sendPacket(LoginPacket.getLoginFailed(loginok));
                if (errorInfo != null) {
                    c.getSession().writeAndFlush((Object)MaplePacketCreator.serverNotice(1, errorInfo));
                }
            }
            else {
                c.getSession().close();
            }
        }
        else if (tempbannedTill.getTimeInMillis() != 0L) {
            if (!loginFailCount(c)) {
                c.sendPacket(LoginPacket.getTempBan(KoreanDateUtil.getTempBanTimestamp(tempbannedTill.getTimeInMillis()), c.getBanReason()));
            }
            else {
                c.getSession().close();
            }
        }
        else {
            c.loginAttempt = 0;
            LoginServer.RemoveLoginKey(c.getAccID());
            c.updateMacs(macData);
            c.setLoginKey(loginkey);
            c.updateLoginKey(loginkey);
            LoginServer.addLoginKey(loginkey, c.getAccID());
            FileoutputUtil.logToFile("logs/data/登入账号.txt", "\r\n 时间\u3000[" + FileoutputUtil.NowTime() + "] IP 地址 : " + c.getSession().remoteAddress().toString().split(":")[0] + " MAC: " + macData + " 账号：\u3000" + account + " 密碼：" + password);
            c.setLoginKeya(loginkeya);
            LoginWorker.registerClient(c);
        }
    }
    
    public static final void SetGenderRequest(final LittleEndianAccessor slea, final MapleClient c) {
        final byte gender = slea.readByte();
        final String username = slea.readMapleAsciiString();
        if (gender != 0 && gender != 1) {
            c.getSession().close();
            return;
        }
        if (c.getAccountName().equals((Object)username)) {
            c.setGender(gender);
            c.updateGender();
            c.setSecondPassword("123456");
            c.updateSecondPassword();
            c.sendPacket(LoginPacket.getGenderChanged(c));
            c.updateLoginState(0, c.getSessionIPAddress());
        }
        else {
            c.getSession().close();
        }
    }
    
    public static final void ServerListRequest(final MapleClient c) {
        if (c.getLoginKeya() == 0) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "請不要通過非法手段\r\n進入游戏。"));
            return;
        }
        if (!c.isCanloginpw()) {
            c.getSession().close();
            return;
        }
        if (c.loadLogGedin(c.getAccID()) == 1 || c.loadLogGedin(c.getAccID()) > 2) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "請不要通過非法手段\r\n進入游戏。"));
            return;
        }
        LoginServer.forceRemoveClient(c, false);
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"蓝蜗牛开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(0, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"蘑菇仔开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(1, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"绿水灵开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(2, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"漂漂猪开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(3, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"小青蛇开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(4, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"红螃蟹开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(5, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"大海龟开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(6, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"章鱼怪开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(7, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"顽皮猴开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(8, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"星精灵开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(9, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"胖企鹅开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(10, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"白雪人开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(11, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"石头人开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(12, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"紫色猫开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(13, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"大灰狼开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(14, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"小白兔开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(15, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"喷火龙开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(16, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"火野猪开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(17, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"青鳄鱼开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(18, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"花蘑菇开关")) == 0) {
            c.sendPacket(LoginPacket.getServerList(19, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
        }
        c.sendPacket(LoginPacket.getEndOfServerList());
    }
    
    public static final void ServerStatusRequest(final MapleClient c) {
        if (!c.isCanloginpw()) {
            c.getSession().close();
            return;
        }
        LoginServer.forceRemoveClient(c, false);
        ChannelServer.forceRemovePlayerByCharNameFromDataBase(c, c.loadCharacterNamesByAccId(c.getAccID()));
        final int numPlayer = LoginServer.getUsersOn();
        final int userLimit = WorldConstants.USER_LIMIT;
        if (numPlayer >= userLimit) {
            c.sendPacket(LoginPacket.getServerStatus(2));
        }
        else if (numPlayer * 2 >= userLimit) {
            c.sendPacket(LoginPacket.getServerStatus(1));
        }
        else {
            c.sendPacket(LoginPacket.getServerStatus(0));
        }
    }
    
    public static final void CharlistRequest(final LittleEndianAccessor slea, final MapleClient c) {
        if (!c.isCanloginpw()) {
            c.getSession().close();
            return;
        }
        if (c.getCloseSession()) {
            return;
        }
        if (c.getLoginKeya() == 0) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "請不要通過非法手段\r\n進入游戏。"));
            return;
        }
        ChannelServer.forceRemovePlayerByCharNameFromDataBase(c, c.loadCharacterNamesByAccId(c.getAccID()));
        LoginServer.forceRemoveClient(c, false);
        final String serverkey = RandomString();
        if (!LoginServer.CanLoginKey(c.getLoginKey(), c.getAccID()) || (LoginServer.getLoginKey(c.getAccID()) == null && !c.getLoginKey().isEmpty())) {
            FileoutputUtil.logToFile("logs/Data/客戶端登錄KEY異常.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号: " + c.getAccountName() + " 客戶端key：" + LoginServer.getLoginKey(c.getAccID()) + " 伺服端key：" + c.getLoginKey() + " 角色列表");
            return;
        }
        final int server = slea.readByte();
        final int channel = slea.readByte() + 1;
        LoginServer.RemoveServerKey(c.getAccID());
        c.setServerKey(serverkey);
        c.updateServerKey(serverkey);
        LoginServer.addServerKey(serverkey, c.getAccID());
        c.setWorld(server + 1);
        c.setChannel(channel);
        final List<MapleCharacter> chars = c.loadCharacters(server);
        if (chars != null) {
            c.sendPacket(LoginPacket.getCharList(c.getSecondPassword() != null, chars, c.getCharacterSlots()));
        }
        else {
            c.getSession().close();
        }
    }
    
    public static final void checkCharName(final String name, final MapleClient c) {
        c.sendPacket(LoginPacket.charNameResponse(name, !MapleCharacterUtil.canCreateChar(name) || LoginInformationProvider.getInstance().isForbiddenName(name)));
    }
    
    public static final void handleCreateCharacter(final LittleEndianAccessor slea, final MapleClient c) {
        final int 冒险家 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"冒险家职业开关"));
        final int 战神 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"战神职业开关"));
        final int 骑士团 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"骑士团职业开关"));
        final String name = slea.readMapleAsciiString();
        final int JobType = slea.readInt();
        if (骑士团 == 1 && JobType == 0) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "骑士团职业群已被关闭！无法创建。"));
            c.sendPacket(LoginPacket.getLoginFailed(1));
            return;
        }
        if (冒险家 == 1 && JobType == 1) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "冒险家职业群已被关闭！无法创建。"));
            c.sendPacket(LoginPacket.getLoginFailed(1));
            return;
        }
        if (战神 == 1 && JobType == 2) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "战神职业群已被关闭！无法创建。"));
            c.sendPacket(LoginPacket.getLoginFailed(1));
            return;
        }
        final short db = 0;
        final int face = slea.readInt();
        final int hair = slea.readInt();
        final int hairColor = 0;
        final byte skinColor = 0;
        final int top = slea.readInt();
        final int bottom = slea.readInt();
        final int shoes = slea.readInt();
        final int weapon = slea.readInt();
        final byte gender = c.getGender();
        final List<MapleCharacter> chars = c.loadCharacters(0);
        if (chars.size() > c.getCharacterSlots()) {
            FileoutputUtil.logToFile("logs/Hack/Ban/角色數量異常.txt", "\r\n " + FileoutputUtil.NowTime() + " 账号 " + c.getAccountName());
            c.getSession().close();
            return;
        }
        if (gender != 0 && gender != 1) {
            FileoutputUtil.logToFile("logs/Hack/Ban/修改封包.txt", "\r\n " + FileoutputUtil.NowTime() + " 账号 " + c.getAccountName() + " 性別類型 " + (int)gender);
            c.getSession().close();
            return;
        }
        if (JobType != 0 && JobType != 1 && JobType != 2) {
            FileoutputUtil.logToFile("logs/Data/非法創建.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号 " + c.getAccountName() + " 职业類型 " + JobType);
            return;
        }
        if (gender == 0 && (JobType == 1 || JobType == 0)) {
            if (face != 20100 && face != 20401 && face != 20402) {
                return;
            }
            if (hair != 30030 && hair != 30027 && hair != 30000) {
                return;
            }
            if (top != 1040002 && top != 1040006 && top != 1040010) {
                return;
            }
            if (bottom != 1060002 && bottom != 1060006) {
                return;
            }
            if (shoes != 1072001 && shoes != 1072005 && shoes != 1072037 && shoes != 1072038) {
                return;
            }
            if (weapon != 1302000 && weapon != 1322005 && weapon != 1312004) {
                return;
            }
        }
        else if (gender == 1 && (JobType == 1 || JobType == 0)) {
            if (face != 21002 && face != 21700 && face != 21201) {
                return;
            }
            if (hair != 31002 && hair != 31047 && hair != 31057) {
                return;
            }
            if (top != 1041002 && top != 1041006 && top != 1041010 && top != 1041011) {
                return;
            }
            if (bottom != 1061002 && bottom != 1061008) {
                return;
            }
            if (shoes != 1072001 && shoes != 1072005 && shoes != 1072037 && shoes != 1072038) {
                return;
            }
            if (weapon != 1302000 && weapon != 1322005 && weapon != 1312004) {
                return;
            }
        }
        else if (JobType == 2) {
            if (gender == 0) {
                if (face != 20100 && face != 20401 && face != 20402) {
                    return;
                }
                if (hair != 30030 && hair != 30027 && hair != 30000) {
                    return;
                }
            }
            else if (gender == 1) {
                if (face != 21002 && face != 21700 && face != 21201) {
                    return;
                }
                if (hair != 31002 && hair != 31047 && hair != 31057) {
                    return;
                }
            }
            if (top != 1042167) {
                return;
            }
            if (bottom != 1062115) {
                return;
            }
            if (shoes != 1072383) {
                return;
            }
            if (weapon != 1442079) {
                return;
            }
        }
        final MapleCharacter newchar = MapleCharacter.getDefault(c, JobType);
        newchar.setWorld((byte)(c.getWorld() - 1));
        newchar.setFace(face);
        newchar.setHair(hair + 0);
        newchar.setGender(gender);
        newchar.setName(name);
        newchar.setSkinColor((byte)0);
        final MapleInventory equip = newchar.getInventory(MapleInventoryType.EQUIPPED);
        final MapleItemInformationProvider li = MapleItemInformationProvider.getInstance();
        IItem item = li.getEquipById(top);
        item.setPosition((short)(-5));
        equip.addFromDB(item);
        item = li.getEquipById(bottom);
        item.setPosition((short)(-6));
        equip.addFromDB(item);
        item = li.getEquipById(shoes);
        item.setPosition((short)(-7));
        equip.addFromDB(item);
        item = li.getEquipById(weapon);
        item.setPosition((short)(-11));
        equip.addFromDB(item);
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        switch (JobType) {
            case 0: {
                newchar.setQuestAdd(MapleQuest.getInstance(20022), (byte)1, "1");
                newchar.setQuestAdd(MapleQuest.getInstance(20010), (byte)1, null);
                newchar.setQuestAdd(MapleQuest.getInstance(20000), (byte)1, null);
                newchar.setQuestAdd(MapleQuest.getInstance(20015), (byte)1, null);
                newchar.setQuestAdd(MapleQuest.getInstance(20020), (byte)1, null);
                newchar.getInventory(MapleInventoryType.ETC).addItem(new Item(4161047, (short)0, (short)1, (byte)0), 1);
                newchar.getInventory(MapleInventoryType.CASH).addItem(new Item(5530000, (short)0, (short)1, (byte)0), 1);
                break;
            }
            case 1: {
                newchar.getInventory(MapleInventoryType.ETC).addItem(new Item(4161001, (short)0, (short)1, (byte)0), 1);
                newchar.getInventory(MapleInventoryType.CASH).addItem(new Item(5530000, (short)0, (short)1, (byte)0), 1);
                break;
            }
            case 2: {
                newchar.setSkinColor((byte)11);
                newchar.getInventory(MapleInventoryType.ETC).addItem(new Item(4161048, (short)0, (short)1, (byte)0), 1);
                newchar.getInventory(MapleInventoryType.CASH).addItem(new Item(5530000, (short)0, (short)1, (byte)0), 1);
                break;
            }
            case 3: {
                newchar.getInventory(MapleInventoryType.ETC).addItem(new Item(4161052, (short)0, (short)1, (byte)0), 1);
                newchar.getInventory(MapleInventoryType.CASH).addItem(new Item(5530000, (short)0, (short)1, (byte)0), 1);
                break;
            }
        }
        if (MapleCharacterUtil.canCreateChar(name) && !LoginInformationProvider.getInstance().isForbiddenName(name)) {
            MapleCharacter.saveNewCharToDB(newchar, JobType, JobType == 1);
            c.sendPacket(LoginPacket.addNewCharEntry(newchar, true));
            c.createdChar(newchar.getId());
        }
        else {
            c.sendPacket(LoginPacket.addNewCharEntry(newchar, false));
        }
    }
    
    public static final void handleDeleteCharacter(final LittleEndianAccessor slea, final MapleClient c) {
        if (!LoginServer.CanLoginKey(c.getLoginKey(), c.getAccID()) || (LoginServer.getLoginKey(c.getAccID()) == null && !c.getLoginKey().isEmpty())) {
            FileoutputUtil.logToFile("logs/Data/客戶端登錄KEY異常.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号: " + c.getAccountName() + " 客戶端key：" + LoginServer.getLoginKey(c.getAccID()) + " 伺服端key：" + c.getLoginKey() + " 刪除角色");
            return;
        }
        if (!LoginServer.CanServerKey(c.getServerKey(), c.getAccID()) || (LoginServer.getServerKey(c.getAccID()) == null && !c.getServerKey().isEmpty())) {
            FileoutputUtil.logToFile("logs/Data/客戶端頻道KEY異常.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号: " + c.getAccountName() + " 客戶端key：" + LoginServer.getServerKey(c.getAccID()) + " 伺服端key：" + c.getServerKey() + " 刪除角色");
            return;
        }
        if (slea.available() < 7L) {
            return;
        }
        slea.readByte();
        final String _2ndPassword = slea.readMapleAsciiString();
        final int characterId = slea.readInt();
        final List<String> charNames = c.loadCharacterNamesByCharId(characterId);
        for (final ChannelServer cs : ChannelServer.getAllInstances()) {
            for (final String name : charNames) {
                final MapleCharacter character = cs.getPlayerStorage().getCharacterByName(name);
                if (character != null) {
                    FileoutputUtil.logToFile("logs/Data/非法刪除角色.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号 " + c.getAccountName());
                    c.getSession().close();
                    character.getClient().getSession().close();
                }
            }
        }
        for (final String name2 : charNames) {
            final MapleCharacter charactercs = CashShopServer.getPlayerStorage().getCharacterByName(name2);
            if (charactercs != null) {
                FileoutputUtil.logToFile("logs/Data/非法刪除角色.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号 " + c.getAccountName());
                c.getSession().close();
                charactercs.getClient().getSession().close();
            }
        }
        if (!c.login_Auth(characterId)) {
            c.sendPacket(LoginPacket.secondPwError((byte)20));
            return;
        }
        byte state = 0;
        if (c.getSecondPassword() != null) {
            if (_2ndPassword == null) {
                c.getSession().close();
                return;
            }
            if (!c.getCheckSecondPassword(_2ndPassword)) {
                state = 16;
            }
        }
        if (state == 0) {
            state = (byte)c.deleteCharacter(characterId);
        }
        c.sendPacket(LoginPacket.deleteCharResponse(characterId, (int)state));
    }
    
    public static final void handleSecectCharacter(final LittleEndianAccessor slea, final MapleClient c) {
        if (c.getCloseSession()) {
            return;
        }
        if (c.getLoginKeya() == 0) {
            return;
        }
        if (!c.isCanloginpw()) {
            return;
        }
        if (!LoginServer.CanLoginKey(c.getLoginKey(), c.getAccID()) || (LoginServer.getLoginKey(c.getAccID()) == null && !c.getLoginKey().isEmpty())) {
            FileoutputUtil.logToFile("logs/Data/客戶端登錄KEY異常.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号: " + c.getAccountName() + " 客戶端key：" + LoginServer.getLoginKey(c.getAccID()) + " 伺服端key：" + c.getLoginKey() + " 開始游戏");
            return;
        }
        if (!LoginServer.CanServerKey(c.getServerKey(), c.getAccID()) || (LoginServer.getServerKey(c.getAccID()) == null && !c.getServerKey().isEmpty())) {
            FileoutputUtil.logToFile("logs/Data/客戶端頻道KEY異常.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号: " + c.getAccountName() + " 客戶端key：" + LoginServer.getServerKey(c.getAccID()) + " 伺服端key：" + c.getServerKey() + " 開始游戏");
            return;
        }
        LoginServer.RemoveClientKey(c.getAccID());
        final String clientkey = RandomString();
        c.updateClientKey(clientkey);
        LoginServer.addClientKey(clientkey, c.getAccID());
        final int charId = slea.readInt();
        final List<String> charNamesa = c.loadCharacterNamesByCharId(charId);
        for (final ChannelServer cs : ChannelServer.getAllInstances()) {
            for (final String name : charNamesa) {
                if (cs.getPlayerStorage().getCharacterByName(name) != null) {
                    FileoutputUtil.logToFile("logs/Data/非法登錄.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号 " + c.getAccountName() + "開始游戏1");
                    c.getSession().close();
                    return;
                }
            }
        }
        for (final String name2 : charNamesa) {
            if (CashShopServer.getPlayerStorage().getCharacterByName(name2) != null) {
                final MapleCharacter victim = CashShopServer.getPlayerStorage().getCharacterByName(name2);
                CashShopServer.getPlayerStorage().deregisterPlayer(victim.getId(), victim.getName());
                FileoutputUtil.logToFile("logs/Data/非法登錄.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号 " + c.getAccountName() + "開始游戏2");
                c.getSession().close();
                return;
            }
        }
        final List<String> charNames = c.loadCharacterNamesByCharId(charId);
        for (final ChannelServer cs2 : ChannelServer.getAllInstances()) {
            for (final String name3 : charNames) {
                final MapleCharacter character = cs2.getPlayerStorage().getCharacterByName(name3);
                if (character != null) {
                    FileoutputUtil.logToFile("logs/Data/非法登錄.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号 " + c.getAccountName() + "開始游戏3");
                    c.getSession().close();
                    character.getClient().getSession().close();
                }
            }
        }
        for (final String name4 : charNames) {
            final MapleCharacter charactercs = CashShopServer.getPlayerStorage().getCharacterByName(name4);
            if (charactercs != null) {
                FileoutputUtil.logToFile("logs/Data/非法登錄.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号 " + c.getAccountName() + "開始游戏4");
                c.getSession().close();
                charactercs.getClient().getSession().close();
            }
        }
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = null;
            ps = con.prepareStatement("select accountid from characters where id = ?");
            ps.setInt(1, charId);
            final ResultSet rs = ps.executeQuery();
            if (!rs.next() || rs.getInt("accountid") != c.getAccID()) {
                rs.close();
                ps.close();
                con.close();
                return;
            }
            rs.close();
            ps.close();
            con.close();
        }
        catch (Exception ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        if (c.getIdleTask() != null) {
            c.getIdleTask().cancel(true);
        }
        c.updateLoginState(1, c.getSessionIPAddress());
        c.sendPacket(MaplePacketCreator.getServerIP(c, Integer.parseInt(ChannelServer.getInstance(c.getChannel()).getSocket().split(":")[1]), charId));
        System.setProperty(String.valueOf(charId), "1");
    }
}
