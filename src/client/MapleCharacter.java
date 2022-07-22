package client;

import abc.离线人偶;
import scripting.EventManager;
import server.Timer.EventTimer;
import server.maps.MapleMapEffect;
import handling.world.World.Find;
import constants.MapConstants;
import constants.ServerConfig;
import handling.world.family.MapleFamily;
import handling.world.family.MapleFamilyBuff;
import handling.world.family.MapleFamilyBuff.MapleFamilyBuffEntry;
import scripting.NPCScriptManager;
import constants.ServerConstants.PlayerGMRank;
import handling.cashshop.CashShopServer;
import handling.login.LoginServer;
import handling.world.World;
import handling.world.PlayerBuffStorage;
import handling.world.MapleMessengerCharacter;
import io.netty.channel.Channel;
import tools.MapleAESOFB;
import tools.MockIOSession;
import client.inventory.MapleInventoryIdentifier;
import server.maps.MapleFoothold;
import java.util.Comparator;
import client.inventory.MapleRing.RingComparator;
import client.inventory.Item;
import client.inventory.MapleRing;
import tools.packet.MonsterCarnivalPacket;
import tools.packet.PlayerShopPacket;
import database.DatabaseConnection;
import fumo.FumoSkill;
import server.life.MobSkill;
import handling.world.World.Guild;
import handling.world.guild.MapleGuild;
import server.maps.MapleMapObjectType;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import scripting.NPCConversationManager;
import tools.packet.MobPacket;
import client.inventory.ItemFlag;
import handling.world.PartyOperation;
import client.inventory.ModifyInventory;
import constants.ServerConstants;
import handling.world.World.Family;
import gui.CongMS;
import server.maps.FieldLimitType;
import tools.packet.MTSCSPacket;
import handling.world.MaplePartyCharacter;
import java.util.Collections;
import handling.world.PlayerBuffValueHolder;
import java.util.Arrays;
import tools.packet.PetPacket;
import java.util.EnumMap;
import tools.HexTool;
import java.util.concurrent.RejectedExecutionException;
import server.FishingRewardFactory.FishingReward;
import handling.world.World.Broadcast;
import server.life.MapleLifeFactory;
import tools.packet.UIPacket;
import server.Randomizer;
import server.FishingRewardFactory;
import server.MapleInventoryManipulator;
import server.Timer.EtcTimer;
import server.Timer.MapTimer;
import server.Timer.BuffTimer;
import server.MapleStatEffect;
import server.MapleItemInformationProvider;
import java.util.Collection;
import tools.MaplePacketCreator;
import tools.data.MaplePacketLittleEndianWriter;
import server.life.PlayerNPC;
import database.DatabaseException;
import tools.FilePrinter;
import client.inventory.IItem;
import tools.Pair;
import client.inventory.ItemLoader;
import java.util.Iterator;
import server.MaplePortal;
import server.maps.MapleMapFactory;
import constants.GameConstants;
import java.util.Map.Entry;
import handling.world.World.Party;
import handling.world.World.Messenger;
import handling.channel.ChannelServer;
import handling.world.CharacterTransfer;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import tools.FileoutputUtil;
import database.DBConPool;
import server.maps.SavedLocationType;
import java.util.LinkedList;
import java.util.LinkedHashSet;
import client.inventory.MapleInventoryType;
import java.util.HashMap;
import java.util.ArrayList;
import tools.ConcurrentEnumMap;
import java.util.LinkedHashMap;
import server.maps.Event_PyramidSubway;
import java.util.concurrent.ScheduledFuture;
import client.inventory.MapleInventory;
import scripting.EventInstanceManager;
import handling.world.family.MapleFamilyCharacter;
import handling.world.guild.MapleGuildCharacter;
import handling.world.MapleParty;
import server.shops.IMaplePlayerShop;
import handling.world.MapleMessenger;
import client.inventory.MapleMount;
import server.MapleTrade;
import server.MapleStorage;
import server.MapleShop;
import server.maps.MapleMap;
import client.anticheat.CheatTracker;
import server.MapleCarnivalParty;
import server.MapleCarnivalChallenge;
import java.util.Deque;
import server.CashShop;
import server.maps.MapleSummon;
import server.quest.MapleQuest;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import server.maps.MapleMapObject;
import server.life.MapleMonster;
import java.util.Set;
import java.lang.ref.WeakReference;
import client.inventory.MaplePet;
import server.maps.MapleDoor;
import server.movement.LifeMovementFragment;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.Point;
import java.io.Serializable;
import server.maps.AbstractAnimatedMapleMapObject;

public class MapleCharacter extends AbstractAnimatedMapleMapObject implements Serializable
{
    private static final long serialVersionUID = 845748950829L;
    private String name;
    private String chalktext;
    private String BlessOfFairy_Origin;
    private String charmessage;
    private String prefix;
    private String teleportname;
    private String nowmacs;
    private String loginkey;
    private String serverkey;
    private String clientkey;
    private String accountsecondPassword;
    private long lastCombo;
    private long lastfametime;
    private long keydown_skill;
    private long lastRecoveryTime;
    private long nextConsume;
    private long pqStartTime;
    private long lastHPTime;
    private long lastMPTime;
    private long lastMDTime;
    private long lastStorageTime;
    private long mapChangeTime;
    private long mrqdTime;
    private byte dojoRecord;
    private byte gmLevel;
    private byte gender;
    private byte initialSpawnPoint;
    private byte skinColor;
    private byte guildrank;
    private byte allianceRank;
    private byte world;
    private byte fairyExp;
    private byte numClones;
    private byte subcategory;
    private byte fairyHour;
    public short level;
    public short mulung_energy;
    public short availableCP;
    public short totalCP;
    public short fame;
    public short hpmpApUsed;
    public short job;
    public short remainingAp;
    private int accountid;
    private int id;
    private int meso;
    private int exp;
    private int hair;
    private int face;
    private int mapid;
    private int bookCover;
    private int dojo;
    private int guildid;
    private int fallcounter;
    private int maplepoints;
    private int chair;
    private int itemEffect;
    private int vpoints;
    private int rank;
    private int rankMove;
    private int jobRank;
    private int jobRankMove;
    private int marriageId;
    private int marriageItemId;
    private int currentrep;
    private int totalrep;
    private int linkMid;
    private int coconutteam;
    private int followid;
    private int battleshipHP;
    private int expression;
    private int constellation;
    private int blood;
    private int month;
    private int day;
    private int beans;
    private int beansNum;
    private int beansRange;
    private int PGSXDJ;
    private int gachexp;
    private int combo;
    private int MSG;
    private int 打怪;
    private int 吸怪;
    private int FLY_吸怪;
    private int vip;
    private int CsMod;
    private int 在线时间;
    private Point old;
    private boolean smega;
    private boolean gashponmega;
    private boolean hidden;
    private boolean hasSummon;
    private boolean 精灵商人购买开关;
    private boolean 玩家私聊开关;
    private boolean 玩家密语开关;
    private boolean 好友聊天开关;
    private boolean 队伍聊天开关;
    private boolean 公会聊天开关;
    private boolean 联盟聊天开关;
    private boolean GM吸怪信息开关;
    private boolean canSetBeansNum;
    private boolean Vip_Medal;
    private boolean auto吸怪;
    private boolean DebugMessage;
    private boolean itemVacs;
    private boolean beansStart;
    private int[] wishlist;
    private int[] rocks;
    private int[] savedLocations;
    private int[] regrocks;
    private int[] remainingSp;
    private int[] savedHairs;
    private int[] savedFaces;
    private transient AtomicInteger inst;
    private transient List<LifeMovementFragment> lastres;
    private List<Integer> lastmonthfameids;
    private List<MapleDoor> doors;
    private List<MaplePet> pets;
    private transient WeakReference<MapleCharacter>[] clones;
    private transient Set<MapleMonster> controlled;
    private transient Set<MapleMapObject> visibleMapObjects;
    private transient ReentrantReadWriteLock visibleMapObjectsLock;
    private final Map<MapleQuest, MapleQuestStatus> quests;
    private Map<Integer, String> questinfo;
    private final Map<ISkill, SkillEntry> skills;
    private final transient Map<MapleBuffStat, MapleBuffStatValueHolder> effects;
    private final transient Map<Integer, MapleBuffStatValueHolder> skillID;
    private transient Map<Integer, MapleSummon> summons;
    private final transient Map<Integer, MapleCoolDownValueHolder> coolDowns;
    private final transient Map<MapleDisease, MapleDiseaseValueHolder> diseases;
    private CashShop cs;
    private transient Deque<MapleCarnivalChallenge> pendingCarnivalRequests;
    private transient MapleCarnivalParty carnivalParty;
    private BuddyList buddylist;
    private MonsterBook monsterbook;
    private transient CheatTracker anticheat;
    private transient MapleLieDetector antiMacro;
    private MapleClient client;
    private PlayerStats stats;
    private transient PlayerRandomStream CRand;
    private transient MapleMap map;
    private transient MapleShop shop;
    private transient RockPaperScissors rps;
    private MapleStorage storage;
    private transient MapleTrade trade;
    private MapleMount mount;
    private final List<Integer> finishedAchievements;
    private MapleMessenger messenger;
    private byte[] petStore;
    private transient IMaplePlayerShop playerShop;
    private MapleParty party;
    private boolean invincible;
    private boolean canTalk;
    private boolean clone;
    private boolean followinitiator;
    private boolean followon;
    private MapleGuildCharacter mgc;
    private MapleFamilyCharacter mfc;
    private transient EventInstanceManager eventInstance;
    private MapleInventory[] inventory;
    private SkillMacro[] skillMacros;
    private MapleKeyLayout keylayout;
    private ItemVac ItemVac;
    private transient ScheduledFuture<?> beholderHealingSchedule;
    private transient ScheduledFuture<?> beholderBuffSchedule;
    private transient ScheduledFuture<?> BerserkSchedule;
    private transient ScheduledFuture<?> dragonBloodSchedule;
    private transient ScheduledFuture<?> fairySchedule;
    private transient ScheduledFuture<?> mapTimeLimitTask;
    private transient ScheduledFuture<?> fishing;
    private transient Event_PyramidSubway pyramidSubway;
    private transient List<Integer> pendingExpiration;
    private transient List<Integer> pendingSkills;
    private final transient Map<Integer, Integer> movedMobs;
    private int jf;
    private int zdjf;
    private int rwjf;
    private int cz;
    private int zs;
    private int dy;
    private int rmb;
    private int yb;
    private int APQScore;
    private long lasttime;
    private long currenttime;
    private long deadtime;
    private MapleCharacter chars;
    private long nengl;
    private long nengls;
    private static String[] ariantroomleader;
    private static int[] ariantroomslot;
    public int apprentice;
    public int master;
    public int ariantScore;
    public long lastGainHM;
    private int touzhuNum;
    private int touzhuType;
    private int touzhuNX;
    private long 防止复制时间;
    private boolean isbanned;
    public static int 记录当前血量;
    public static int 角色无敌指数;
    public static int 地图记录;
    public static int 吸怪怪值;
    public static long 记录范围;
    public static int 记录技能;
    public static int 技能检测惩罚;
    public static int 登陆验证;
    public int 鱼来鱼往;
    public long 蓄力一击;
    public long 越战越勇;
    public long prevTimestamp1;
    public long 持续对话NPC;
    public long 宠物捡物冷却;
    public long 怪物移动冷却;
    public long 玩家捡物冷却;
    public long 上线提醒冷却;
    public long 整理背包冷却;
    public long 对话冷却;
    public long 吸怪检测;
    public long 愤怒之火掉血;
    public long 神圣之火回血;
    private static int _tiredMinutes;
    public int 银行账号;
    public int 银行密码;
    public long 最高伤害;
    public long 枫叶数量;
    public long 飞镖数量;
    public int 属性攻击累计伤害次数;
    public long 属性攻击累计伤害;
    public int 龙之献祭攻击次数;
    public long 末日烈焰伤害记录;
    public int 圣光无敌;
    public double 记录坐标X;
    public double 记录坐标Y;
    public int 吸怪指数;
    public int 打怪地图;
    public int 打怪数量;
    public int 被触碰次数;
    public long 累计掉血;
    public double X坐标误差;
    public double Y坐标误差;
    public int 误差次数;
    public int 过图;
    public int 地图缓存1;
    public int 地图缓存2;
    public int 地图缓存3;
    public int 地图缓存4;
    public int 地图缓存5;
    public int 地图缓存6;
    public int 地图缓存7;
    public int 地图缓存8;
    public int 地图缓存9;
    public int 地图缓存10;
    public String 地图缓存时间1;
    public String 地图缓存时间2;
    public String 地图缓存时间3;
    public String 地图缓存时间4;
    public String 地图缓存时间5;
    public String 地图缓存时间6;
    public String 地图缓存时间7;
    public String 地图缓存时间8;
    public String 地图缓存时间9;
    public String 地图缓存时间10;
    public long 攻击加速;
    public int 攻击加速判断;
    public double 记录移动坐标X1;
    public double 记录移动坐标Y1;
    public double 记录移动坐标X2;
    public double 记录移动坐标Y2;
    public Boolean isCheating;
    private int _isCheatingPlayer;
    private int playerPoints;
    private int playerEnergy;
    private int jf1;
    private int jf2;
    private int jf3;
    private int jf4;
    private int jf5;
    private int jf6;
    private int jf7;
    private int jf8;
    private int jf9;
    private int jf10;
    private Map<Integer, Integer> _equippedFuMoMap;
    
    private MapleCharacter(final boolean ChannelServer) {
        this.teleportname = "";
        this.nowmacs = "";
        this.nextConsume = 0L;
        this.pqStartTime = 0L;
        this.guildrank = 5;
        this.allianceRank = 5;
        this.fairyExp = 30;
        this.fairyHour = 1;
        this.guildid = 0;
        this.fallcounter = 0;
        this.rank = 1;
        this.rankMove = 0;
        this.jobRank = 1;
        this.jobRankMove = 0;
        this.marriageItemId = 0;
        this.linkMid = 0;
        this.coconutteam = 0;
        this.followid = 0;
        this.battleshipHP = 0;
        this.MSG = 0;
        this.打怪 = 0;
        this.吸怪 = 0;
        this.FLY_吸怪 = 0;
        this.CsMod = 0;
        this.在线时间 = 0;
        this.old = new Point(0, 0);
        this.smega = true;
        this.gashponmega = true;
        this.hasSummon = false;
        this.精灵商人购买开关 = false;
        this.玩家私聊开关 = false;
        this.玩家密语开关 = false;
        this.好友聊天开关 = false;
        this.队伍聊天开关 = false;
        this.公会聊天开关 = false;
        this.联盟聊天开关 = false;
        this.GM吸怪信息开关 = false;
        this.canSetBeansNum = false;
        this.Vip_Medal = true;
        this.auto吸怪 = false;
        this.DebugMessage = false;
        this.itemVacs = false;
        this.beansStart = false;
        this.remainingSp = new int[10];
        this.savedHairs = new int[6];
        this.savedFaces = new int[6];
        this.skills = new LinkedHashMap<ISkill, SkillEntry>();
        this.effects = new ConcurrentEnumMap<MapleBuffStat, MapleBuffStatValueHolder>(MapleBuffStat.class);
        this.skillID = new LinkedHashMap<Integer, MapleBuffStatValueHolder>();
        this.coolDowns = new LinkedHashMap<Integer, MapleCoolDownValueHolder>();
        this.diseases = new ConcurrentEnumMap<MapleDisease, MapleDiseaseValueHolder>(MapleDisease.class);
        this.finishedAchievements = new ArrayList<Integer>();
        this.invincible = false;
        this.canTalk = true;
        this.clone = false;
        this.followinitiator = false;
        this.followon = false;
        this.skillMacros = new SkillMacro[5];
        this.pyramidSubway = null;
        this.pendingExpiration = null;
        this.pendingSkills = null;
        this.movedMobs = new HashMap<Integer, Integer>();
        this.lasttime = 0L;
        this.currenttime = 0L;
        this.deadtime = 1000L;
        this.nengl = 0L;
        this.nengls = 0L;
        this.apprentice = 0;
        this.master = 0;
        this.ariantScore = 0;
        this.防止复制时间 = 1000L;
        this.isbanned = false;
        this.鱼来鱼往 = 0;
        this.蓄力一击 = 0L;
        this.越战越勇 = 0L;
        this.prevTimestamp1 = 0L;
        this.持续对话NPC = 0L;
        this.宠物捡物冷却 = 0L;
        this.怪物移动冷却 = 0L;
        this.玩家捡物冷却 = 0L;
        this.上线提醒冷却 = 0L;
        this.整理背包冷却 = 0L;
        this.对话冷却 = 0L;
        this.吸怪检测 = 0L;
        this.愤怒之火掉血 = 0L;
        this.神圣之火回血 = 0L;
        this.银行账号 = 0;
        this.银行密码 = 0;
        this.最高伤害 = 0L;
        this.枫叶数量 = 0L;
        this.飞镖数量 = 0L;
        this.属性攻击累计伤害次数 = 0;
        this.属性攻击累计伤害 = 0L;
        this.龙之献祭攻击次数 = 0;
        this.末日烈焰伤害记录 = 0L;
        this.圣光无敌 = 0;
        this.记录坐标X = 0.0;
        this.记录坐标Y = 0.0;
        this.吸怪指数 = 0;
        this.打怪地图 = 0;
        this.打怪数量 = 0;
        this.被触碰次数 = 0;
        this.累计掉血 = 0L;
        this.X坐标误差 = 0.0;
        this.Y坐标误差 = 0.0;
        this.误差次数 = 0;
        this.过图 = 0;
        this.地图缓存1 = 0;
        this.地图缓存2 = 0;
        this.地图缓存3 = 0;
        this.地图缓存4 = 0;
        this.地图缓存5 = 0;
        this.地图缓存6 = 0;
        this.地图缓存7 = 0;
        this.地图缓存8 = 0;
        this.地图缓存9 = 0;
        this.地图缓存10 = 0;
        this.地图缓存时间1 = "";
        this.地图缓存时间2 = "";
        this.地图缓存时间3 = "";
        this.地图缓存时间4 = "";
        this.地图缓存时间5 = "";
        this.地图缓存时间6 = "";
        this.地图缓存时间7 = "";
        this.地图缓存时间8 = "";
        this.地图缓存时间9 = "";
        this.地图缓存时间10 = "";
        this.攻击加速 = 0L;
        this.攻击加速判断 = 0;
        this.记录移动坐标X1 = 0.0;
        this.记录移动坐标Y1 = 0.0;
        this.记录移动坐标X2 = 0.0;
        this.记录移动坐标Y2 = 0.0;
        this.isCheating = Boolean.valueOf(false);
        this._isCheatingPlayer = 0;
        this._equippedFuMoMap = new HashMap<Integer, Integer>();
        this.setStance(0);
        this.setPosition(new Point(0, 0));
        this.inventory = new MapleInventory[MapleInventoryType.values().length];
        for (final MapleInventoryType type : MapleInventoryType.values()) {
            this.inventory[type.ordinal()] = new MapleInventory(type);
        }
        this.quests = new LinkedHashMap<MapleQuest, MapleQuestStatus>();
        this.stats = new PlayerStats(this);
        for (int i = 0; i < this.remainingSp.length; ++i) {
            this.remainingSp[i] = 0;
        }
        for (int i = 0; i < this.savedHairs.length; ++i) {
            this.savedHairs[i] = -1;
        }
        for (int i = 0; i < this.savedFaces.length; ++i) {
            this.savedFaces[i] = -1;
        }
        if (ChannelServer) {
            this.lastCombo = 0L;
            this.mulung_energy = 0;
            this.combo = 0;
            this.keydown_skill = 0L;
            this.lastHPTime = 0L;
            this.lastMPTime = 0L;
            this.mapChangeTime = 0L;
            this.lastRecoveryTime = 0L;
            this.petStore = new byte[3];
            for (int i = 0; i < this.petStore.length; ++i) {
                this.petStore[i] = -1;
            }
            this.wishlist = new int[10];
            this.rocks = new int[10];
            this.regrocks = new int[5];
            this.clones = (WeakReference<MapleCharacter>[])new WeakReference[25];
            for (int i = 0; i < this.clones.length; ++i) {
                this.clones[i] = new WeakReference<MapleCharacter>(null);
            }
            (this.inst = new AtomicInteger()).set(0);
            this.keylayout = new MapleKeyLayout();
            this.doors = new ArrayList<MapleDoor>();
            this.controlled = new LinkedHashSet<MapleMonster>();
            this.summons = new LinkedHashMap<Integer, MapleSummon>();
            this.visibleMapObjects = new LinkedHashSet<MapleMapObject>();
            this.visibleMapObjectsLock = new ReentrantReadWriteLock();
            this.pendingCarnivalRequests = new LinkedList<MapleCarnivalChallenge>();
            this.savedLocations = new int[SavedLocationType.values().length];
            for (int i = 0; i < SavedLocationType.values().length; ++i) {
                this.savedLocations[i] = -1;
            }
            this.questinfo = new LinkedHashMap<Integer, String>();
            this.anticheat = new CheatTracker(this);
            this.pets = new ArrayList<MaplePet>();
        }
    }
    
    public static MapleCharacter getDefault(final MapleClient client, final int type) {
        final MapleCharacter ret = new MapleCharacter(false);
        ret.client = client;
        ret.map = null;
        ret.exp = 0;
        ret.gmLevel = 0;
        ret.job = (short)((type == 1) ? 0 : ((type == 0) ? 1000 : ((type == 3) ? 2001 : ((type == 4) ? 3000 : 2000))));
        ret.beans = 0;
        ret.meso = 0;
        ret.level = 1;
        ret.remainingAp = 0;
        ret.fame = 0;
        ret.accountid = client.getAccID();
        ret.buddylist = new BuddyList((byte)20);
        ret.stats.str = 12;
        ret.stats.dex = 5;
        ret.stats.int_ = 4;
        ret.stats.luk = 4;
        ret.stats.maxhp = 50;
        ret.stats.hp = 50;
        ret.stats.maxmp = 50;
        ret.stats.mp = 50;
        ret.prefix = "";
        ret.PGSXDJ = 0;
        ret.gachexp = 0;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("SELECT name, 2ndpassword, mPoints,  vpoints, VIP, loginkey, serverkey, clientkey FROM accounts WHERE id = ?");
            ps.setInt(1, ret.accountid);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ret.client.setAccountName(rs.getString("name"));
                    ret.accountsecondPassword = rs.getString("2ndpassword");
                    ret.maplepoints = rs.getInt("mPoints");
                    ret.vpoints = rs.getInt("vpoints");
                    ret.vip = rs.getInt("VIP");
                    ret.loginkey = rs.getString("loginkey");
                    ret.serverkey = rs.getString("serverkey");
                    ret.clientkey = rs.getString("clientkey");
                }
            }
            ps.close();
        }
        catch (SQLException e) {
            System.err.println("Error getting character default" + (Object)e);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
        return ret;
    }
    
    public static final MapleCharacter ReconstructChr(final CharacterTransfer ct, final MapleClient client, final boolean isChannel) {
        final MapleCharacter ret = new MapleCharacter(true);
        ret.client = client;
        if (!isChannel) {
            ret.client.setChannel((int)ct.channel);
        }
        ret.nowmacs = ct.nowmacs;
        ret.canTalk = ct.canTalk;
        ret.DebugMessage = ct.DebugMessage;
        ret.auto吸怪 = ct.auto吸怪;
        ret.GM吸怪信息开关 = ct.GM吸怪信息开关;
        ret.Vip_Medal = ct.Vip_Medal;
        ret.精灵商人购买开关 = ct.精灵商人购买开关;
        ret.玩家私聊开关 = ct.玩家私聊开关;
        ret.玩家密语开关 = ct.玩家密语开关;
        ret.好友聊天开关 = ct.好友聊天开关;
        ret.队伍聊天开关 = ct.队伍聊天开关;
        ret.公会聊天开关 = ct.公会聊天开关;
        ret.联盟聊天开关 = ct.联盟聊天开关;
        ret.smega = ct.smega;
        ret.gashponmega = ct.gashponmega;
        ret.id = ct.characterid;
        ret.name = ct.name;
        ret.level = ct.level;
        ret.fame = ct.fame;
        ret.CRand = new PlayerRandomStream();
        ret.stats.str = ct.str;
        ret.stats.dex = ct.dex;
        ret.stats.int_ = ct.int_;
        ret.stats.luk = ct.luk;
        ret.stats.maxhp = ct.maxhp;
        ret.stats.maxmp = ct.maxmp;
        ret.stats.hp = ct.hp;
        ret.stats.mp = ct.mp;
        ret.chalktext = ct.chalkboard;
        ret.exp = ct.exp;
        ret.hpmpApUsed = ct.hpApUsed;
        ret.remainingSp = ct.remainingSp;
        ret.remainingAp = ct.remainingAp;
        ret.savedHairs = ct.savedHairs;
        ret.savedFaces = ct.savedFaces;
        ret.beans = ct.beans;
        ret.meso = ct.meso;
        ret.gmLevel = ct.gmLevel;
        ret.skinColor = ct.skinColor;
        ret.gender = ct.gender;
        ret.job = ct.job;
        ret.hair = ct.hair;
        ret.face = ct.face;
        ret.accountid = ct.accountid;
        ret.mapid = ct.mapid;
        ret.initialSpawnPoint = ct.initialSpawnPoint;
        ret.world = ct.world;
        ret.bookCover = ct.mBookCover;
        ret.dojo = ct.dojo;
        ret.dojoRecord = ct.dojoRecord;
        ret.guildid = ct.guildid;
        ret.guildrank = ct.guildrank;
        ret.allianceRank = ct.alliancerank;
        ret.CsMod = ct.CsMod;
        ret.vpoints = ct.vpoints;
        ret.vip = ct.vip;
        ret.mrqdTime = ct.mrqdTime;
        ret.fairyExp = ct.fairyExp;
        ret.marriageId = ct.marriageId;
        ret.currentrep = ct.currentrep;
        ret.totalrep = ct.totalrep;
        ret.charmessage = ct.charmessage;
        ret.expression = ct.expression;
        ret.constellation = ct.constellation;
        ret.blood = ct.blood;
        ret.month = ct.month;
        ret.jf = ct.jf;
        ret.zdjf = ct.zdjf;
        ret.rwjf = ct.rwjf;
        ret.cz = ct.cz;
        ret.zs = ct.zs;
        ret.dy = ct.dy;
        ret.rmb = ct.rmb;
        ret.yb = ct.yb;
        ret.playerPoints = ct.playerPoints;
        ret.playerEnergy = ct.playerEnergy;
        ret.jf1 = ct.jf1;
        ret.jf2 = ct.jf2;
        ret.jf3 = ct.jf3;
        ret.jf4 = ct.jf4;
        ret.jf5 = ct.jf5;
        ret.jf6 = ct.jf6;
        ret.jf7 = ct.jf7;
        ret.jf8 = ct.jf8;
        ret.jf9 = ct.jf9;
        ret.jf10 = ct.jf10;
        ret.day = ct.day;
        ret.gachexp = ct.gachexp;
        ret.makeMFC(ct.familyid, ct.seniorid, ct.junior1, ct.junior2);
        if (ret.guildid > 0) {
            ret.mgc = new MapleGuildCharacter(ret);
        }
        ret.buddylist = new BuddyList(ct.buddysize);
        ret.subcategory = ct.subcategory;
        ret.prefix = ct.prefix;
        ret.PGSXDJ = ct.PGSXDJ;
        if (isChannel) {
            final MapleMapFactory mapFactory = ChannelServer.getInstance(client.getChannel()).getMapFactory();
            ret.map = mapFactory.getMap(ret.mapid);
            if (ret.map != null) {
                if (ret.mapid == 801000110 || ret.mapid == 801000210) {
                    ret.map = mapFactory.getMap(801000000);
                }
                if (ret.mapid >= 211060000 && ret.mapid <= 211070200) {
                    ret.map = mapFactory.getMap(211060000);
                }
            }
            if (ret.map == null) {
                ret.map = mapFactory.getMap(100000000);
            }
            else if (ret.map.getForcedReturnId() != 999999999) {
                ret.map = ret.map.getForcedReturnMap();
            }
            MaplePortal portal = ret.map.getPortal((int)ret.initialSpawnPoint);
            if (portal == null) {
                portal = ret.map.getPortal(0);
                ret.initialSpawnPoint = 0;
            }
            ret.setPosition(portal.getPosition());
            final int messengerid = ct.messengerid;
            if (messengerid > 0) {
                ret.messenger = Messenger.getMessenger(messengerid);
            }
        }
        else {
            ret.messenger = null;
        }
        final int partyid = ct.partyid;
        if (partyid >= 0) {
            final MapleParty party = Party.getParty(partyid);
            if (party != null && party.getMemberById(ret.id) != null) {
                ret.party = party;
            }
        }
        for (final Entry<Integer, Object> qs : ct.Quest.entrySet()) {
            final MapleQuest quest = MapleQuest.getInstance((int)Integer.valueOf(qs.getKey()));
            final MapleQuestStatus queststatus_from = (MapleQuestStatus)qs.getValue();
            final MapleQuestStatus queststatus = new MapleQuestStatus(quest, (int)queststatus_from.getStatus());
            queststatus.setForfeited(queststatus_from.getForfeited());
            queststatus.setCustomData(queststatus_from.getCustomData());
            queststatus.setCompletionTime(queststatus_from.getCompletionTime());
            if (queststatus_from.getMobKills() != null) {
                for (final Entry<Integer, Integer> mobkills : queststatus_from.getMobKills().entrySet()) {
                    queststatus.setMobKills((int)Integer.valueOf(mobkills.getKey()), (int)Integer.valueOf(mobkills.getValue()));
                }
            }
            ret.quests.put(quest, queststatus);
        }
        for (final Entry<Integer, SkillEntry> qs2 : ct.Skills.entrySet()) {
            ret.skills.put(SkillFactory.getSkill((int)Integer.valueOf(qs2.getKey())), qs2.getValue());
        }
        for (final Integer zz : ct.finishedAchievements) {
            ret.finishedAchievements.add(zz);
        }
        ret.monsterbook = new MonsterBook(ct.mbook);
        ret.inventory = (MapleInventory[])(MapleInventory[])ct.inventorys;
        ret.BlessOfFairy_Origin = ct.BlessOfFairy;
        ret.skillMacros = (SkillMacro[])(SkillMacro[])ct.skillmacro;
        ret.petStore = ct.petStore;
        ret.keylayout = new MapleKeyLayout(ct.keymap);
        ret.questinfo = ct.InfoQuest;
        ret.savedLocations = ct.savedlocation;
        ret.wishlist = ct.wishlist;
        ret.rocks = ct.rocks;
        ret.regrocks = ct.regrocks;
        ret.buddylist.loadFromTransfer(ct.buddies);
        ret.keydown_skill = 0L;
        ret.lastfametime = ct.lastfametime;
        ret.lastmonthfameids = ct.famedcharacters;
        ret.storage = (MapleStorage)ct.storage;
        ret.cs = (CashShop)ct.cs;
        client.setAccountName(ct.accountname);
        ret.maplepoints = ct.MaplePoints;
        ret.accountsecondPassword = ct.accountsecondPassword;
        ret.loginkey = ct.loginkey;
        ret.serverkey = ct.serverkey;
        ret.clientkey = ct.clientkey;
        ret.antiMacro = (MapleLieDetector)ct.antiMacro;
        ret.numClones = ct.clonez;
        ret.mount = new MapleMount(ret, ct.mount_itemid, GameConstants.isKOC((int)ret.job) ? 10001004 : (GameConstants.isAran((int)ret.job) ? 20001004 : 1004), ct.mount_Fatigue, ct.mount_level, ct.mount_exp);
        ret.stats.recalcLocalStats(true);
        return ret;
    }
    
    public static MapleCharacter loadCharFromDB(final int charid, final MapleClient client, final boolean channelserver) {
        final MapleCharacter ret = new MapleCharacter(channelserver);
        ret.client = client;
        ret.id = charid;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement("SELECT * FROM characters WHERE id = ?");
                ps.setInt(1, charid);
                rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new RuntimeException("Loading the Char Failed (char not found)");
                }
                ret.Vip_Medal = (rs.getShort("VipMedal") > 0);
                ret.name = rs.getString("name");
                ret.level = rs.getShort("level");
                ret.fame = rs.getShort("fame");
                ret.stats.str = rs.getShort("str");
                ret.stats.dex = rs.getShort("dex");
                ret.stats.int_ = rs.getShort("int");
                ret.stats.luk = rs.getShort("luk");
                ret.stats.maxhp = rs.getShort("maxhp");
                ret.stats.maxmp = rs.getShort("maxmp");
                ret.stats.hp = rs.getShort("hp");
                ret.stats.mp = rs.getShort("mp");
                ret.exp = rs.getInt("exp");
                ret.hpmpApUsed = rs.getShort("hpApUsed");
                final String[] sp = rs.getString("sp").split(",");
                for (int i = 0; i < ret.remainingSp.length; ++i) {
                    ret.remainingSp[i] = Integer.parseInt(sp[i]);
                }
                final String[] saves_faces = rs.getString("saved_faces").split(",");
                for (int j = 0; j < ret.savedFaces.length; ++j) {
                    ret.savedFaces[j] = Integer.parseInt(saves_faces[j]);
                }
                final String[] saves_hairs = rs.getString("saved_hairs").split(",");
                for (int k = 0; k < ret.savedHairs.length; ++k) {
                    ret.savedHairs[k] = Integer.parseInt(saves_hairs[k]);
                }
                ret.remainingAp = rs.getShort("ap");
                ret.beans = rs.getInt("beans");
                ret.meso = rs.getInt("meso");
                ret.gmLevel = rs.getByte("gm");
                ret.skinColor = rs.getByte("skincolor");
                ret.gender = rs.getByte("gender");
                ret.job = rs.getShort("job");
                ret.hair = rs.getInt("hair");
                ret.face = rs.getInt("face");
                ret.accountid = rs.getInt("accountid");
                ret.mapid = rs.getInt("map");
                ret.initialSpawnPoint = rs.getByte("spawnpoint");
                ret.world = rs.getByte("world");
                ret.guildid = rs.getInt("guildid");
                ret.guildrank = rs.getByte("guildrank");
                ret.allianceRank = rs.getByte("allianceRank");
                ret.currentrep = rs.getInt("currentrep");
                ret.totalrep = rs.getInt("totalrep");
                ret.makeMFC(rs.getInt("familyid"), rs.getInt("seniorid"), rs.getInt("junior1"), rs.getInt("junior2"));
                if (ret.guildid > 0) {
                    ret.mgc = new MapleGuildCharacter(ret);
                }
                ret.buddylist = new BuddyList(rs.getByte("buddyCapacity"));
                ret.subcategory = rs.getByte("subcategory");
                ret.mount = new MapleMount(ret, 0, (ret.job > 1000 && ret.job < 2000) ? 10001004 : ((ret.job >= 2000) ? ((ret.job == 2001 || (ret.job >= 2200 && ret.job <= 2218)) ? 20011004 : ((ret.job >= 3000) ? 30001004 : 20001004)) : 1004), (byte)0, (byte)1, 0);
                ret.rank = rs.getInt("rank");
                ret.rankMove = rs.getInt("rankMove");
                ret.jobRank = rs.getInt("jobRank");
                ret.jobRankMove = rs.getInt("jobRankMove");
                ret.marriageId = rs.getInt("marriageId");
                ret.charmessage = rs.getString("charmessage");
                ret.expression = rs.getInt("expression");
                ret.constellation = rs.getInt("constellation");
                ret.blood = rs.getInt("blood");
                ret.month = rs.getInt("month");
                ret.jf = rs.getInt("jf");
                ret.zdjf = rs.getInt("zdjf");
                ret.rwjf = rs.getInt("rwjf");
                ret.zs = rs.getInt("zs");
                ret.cz = rs.getInt("cz");
                ret.dy = rs.getInt("dy");
                ret.rmb = rs.getInt("rmb");
                ret.yb = rs.getInt("yb");
                ret.playerPoints = rs.getInt("playerPoints");
                ret.playerEnergy = rs.getInt("playerEnergy");
                ret.jf1 = rs.getInt("jf1");
                ret.jf2 = rs.getInt("jf2");
                ret.jf3 = rs.getInt("jf3");
                ret.jf4 = rs.getInt("jf4");
                ret.jf5 = rs.getInt("jf5");
                ret.jf6 = rs.getInt("jf6");
                ret.jf7 = rs.getInt("jf7");
                ret.jf8 = rs.getInt("jf8");
                ret.jf9 = rs.getInt("jf9");
                ret.jf10 = rs.getInt("jf10");
                ret.day = rs.getInt("day");
                ret.prefix = rs.getString("prefix");
                ret.gachexp = rs.getInt("gachexp");
                ret.PGSXDJ = rs.getInt("PGSXDJ");
                if (channelserver) {
                    final MapleMapFactory mapFactory = ChannelServer.getInstance(client.getChannel()).getMapFactory();
                    ret.antiMacro = new MapleLieDetector(ret.id);
                    ret.map = mapFactory.getMap(ret.mapid);
                    if (ret.mapid == 801000210) {
                        ret.map = mapFactory.getMap(801000000);
                    }
                    if (ret.mapid == 801000110) {
                        ret.map = mapFactory.getMap(801000000);
                    }
                    if (ret.map == null) {
                        ret.map = mapFactory.getMap(100000000);
                    }
                    MaplePortal portal = ret.map.getPortal((int)ret.initialSpawnPoint);
                    if (portal == null) {
                        portal = ret.map.getPortal(0);
                        ret.initialSpawnPoint = 0;
                    }
                    ret.setPosition(portal.getPosition());
                    final int partyid = rs.getInt("party");
                    if (partyid >= 0) {
                        final MapleParty party = Party.getParty(partyid);
                        if (party != null && party.getMemberById(ret.id) != null) {
                            ret.party = party;
                        }
                    }
                    ret.bookCover = rs.getInt("monsterbookcover");
                    ret.dojo = rs.getInt("dojo_pts");
                    ret.dojoRecord = rs.getByte("dojoRecord");
                    final String[] pets = rs.getString("pets").split(",");
                    for (int l = 0; l < ret.petStore.length; ++l) {
                        ret.petStore[l] = Byte.parseByte(pets[l]);
                    }
                    rs.close();
                    ps.close();
                    ps = con.prepareStatement("SELECT achievementid FROM achievements WHERE accountid = ?");
                    ps.setInt(1, ret.accountid);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        ret.finishedAchievements.add(Integer.valueOf(rs.getInt("achievementid")));
                    }
                }
                rs.close();
                ps.close();
                boolean compensate_previousEvans = false;
                ps = con.prepareStatement("SELECT * FROM queststatus WHERE characterid = ?");
                ps.setInt(1, charid);
                rs = ps.executeQuery();
                final PreparedStatement pse = con.prepareStatement("SELECT * FROM queststatusmobs WHERE queststatusid = ?");
                while (rs.next()) {
                    final int id = rs.getInt("quest");
                    if (id == 170000) {
                        compensate_previousEvans = true;
                    }
                    final MapleQuest q = MapleQuest.getInstance(id);
                    final MapleQuestStatus status = new MapleQuestStatus(q, (int)rs.getByte("status"));
                    final long cTime = rs.getLong("time");
                    if (cTime > -1L) {
                        status.setCompletionTime(cTime * 1000L);
                    }
                    status.setForfeited(rs.getInt("forfeited"));
                    status.setCustomData(rs.getString("customData"));
                    ret.quests.put(q, status);
                    pse.setLong(1, rs.getLong("queststatusid"));
                    try (final ResultSet rsMobs = pse.executeQuery()) {
                        while (rsMobs.next()) {
                            status.setMobKills(rsMobs.getInt("mob"), rsMobs.getInt("count"));
                        }
                    }
                }
                rs.close();
                ps.close();
                pse.close();
                if (channelserver) {
                    ret.CRand = new PlayerRandomStream();
                    ret.monsterbook = MonsterBook.loadCards(charid);
                    ps = con.prepareStatement("SELECT * FROM inventoryslot where characterid = ?");
                    ps.setInt(1, charid);
                    rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new RuntimeException("No Inventory slot column found in SQL. [inventoryslot]");
                    }
                    ret.getInventory(MapleInventoryType.EQUIP).setSlotLimit(rs.getByte("equip"));
                    ret.getInventory(MapleInventoryType.USE).setSlotLimit(rs.getByte("use"));
                    ret.getInventory(MapleInventoryType.SETUP).setSlotLimit(rs.getByte("setup"));
                    ret.getInventory(MapleInventoryType.ETC).setSlotLimit(rs.getByte("etc"));
                    ret.getInventory(MapleInventoryType.CASH).setSlotLimit(rs.getByte("cash"));
                    ps.close();
                    rs.close();
                    for (final Pair<IItem, MapleInventoryType> mit : ItemLoader.INVENTORY.loadItems(false, Integer.valueOf(charid)).values()) {
                        ret.getInventory((MapleInventoryType)mit.getRight()).addFromDB((IItem)mit.getLeft());
                        if (((IItem)mit.getLeft()).getPet() != null) {
                            ret.pets.add(((IItem)mit.getLeft()).getPet());
                        }
                    }
                    ps = con.prepareStatement("SELECT name, 2ndpassword, mPoints, vpoints, VIP, loginkey, serverkey, clientkey FROM accounts WHERE id = ?");
                    ps.setInt(1, ret.accountid);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        ret.getClient().setAccountName(rs.getString("name"));
                        ret.accountsecondPassword = rs.getString("2ndpassword");
                        ret.maplepoints = rs.getInt("mPoints");
                        ret.vpoints = rs.getInt("vpoints");
                        ret.vip = rs.getInt("VIP");
                        ret.loginkey = rs.getString("loginkey");
                        ret.serverkey = rs.getString("serverkey");
                        ret.clientkey = rs.getString("clientkey");
                    }
                    else {
                        rs.close();
                    }
                    ps.close();
                    ps = con.prepareStatement("SELECT quest, customData FROM questinfo WHERE characterid = ?");
                    ps.setInt(1, charid);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        ret.questinfo.put(Integer.valueOf(rs.getInt("quest")), rs.getString("customData"));
                    }
                    rs.close();
                    ps.close();
                    ps = con.prepareStatement("SELECT skillid, skilllevel, masterlevel, expiration FROM skills WHERE characterid = ?");
                    ps.setInt(1, charid);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        final ISkill skil = SkillFactory.getSkill(rs.getInt("skillid"));
                        if (skil != null && GameConstants.isApplicableSkill(rs.getInt("skillid")) && rs.getByte("skilllevel") >= 0) {
                            ret.skills.put(skil, new SkillEntry(rs.getByte("skilllevel"), rs.getByte("masterlevel"), rs.getLong("expiration")));
                        }
                        else {
                            if (skil != null) {
                                continue;
                            }
                            final int[] remainingSp = ret.remainingSp;
                            final int skillBookForSkill = GameConstants.getSkillBookForSkill(rs.getInt("skillid"));
                            remainingSp[skillBookForSkill] += rs.getByte("skilllevel");
                        }
                    }
                    rs.close();
                    ps.close();
                    ret.expirationTask(false);
                    ps = con.prepareStatement("SELECT id, name, level FROM characters WHERE accountid = ? ORDER BY level DESC");
                    ps.setInt(1, ret.accountid);
                    rs = ps.executeQuery();
                    byte maxlevel_ = 0;
                    while (rs.next()) {
                        if (rs.getInt("id") != charid) {
                            byte maxlevel = (byte)(rs.getShort("level") / 10);
                            if (maxlevel > 20) {
                                maxlevel = 20;
                            }
                            if (maxlevel <= maxlevel_) {
                                continue;
                            }
                            maxlevel_ = maxlevel;
                            ret.BlessOfFairy_Origin = rs.getString("name");
                        }
                        else {
                            if (charid >= 17000 || compensate_previousEvans || ret.job < 2200 || ret.job > 2218) {
                                continue;
                            }
                            for (int m = 0; m <= GameConstants.getSkillBook((int)ret.job); ++m) {
                                final int[] remainingSp2 = ret.remainingSp;
                                final int n = m;
                                remainingSp2[n] += 2;
                            }
                            ret.setQuestAdd(MapleQuest.getInstance(170000), (byte)0, null);
                        }
                    }
                    ret.skills.put(SkillFactory.getSkill(GameConstants.getBofForJob((int)ret.job)), new SkillEntry(maxlevel_, (byte)0, -1L));
                    ps.close();
                    rs.close();
                    ps = con.prepareStatement("SELECT * FROM skillmacros WHERE characterid = ?");
                    ps.setInt(1, charid);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        final int position = rs.getInt("position");
                        final SkillMacro macro = new SkillMacro(rs.getInt("skill1"), rs.getInt("skill2"), rs.getInt("skill3"), rs.getString("name"), rs.getInt("shout"), position);
                        ret.skillMacros[position] = macro;
                    }
                    rs.close();
                    ps.close();
                    ps = con.prepareStatement("SELECT `keye`,`type`,`action` FROM keymap WHERE characterid = ?");
                    ps.setInt(1, charid);
                    rs = ps.executeQuery();
                    final Map<Integer, Pair<Byte, Integer>> keyb = ret.keylayout.Layout();
                    while (rs.next()) {
                        keyb.put(Integer.valueOf(rs.getInt("keye")), new Pair<Byte, Integer>(Byte.valueOf(rs.getByte("type")), Integer.valueOf(rs.getInt("action"))));
                    }
                    rs.close();
                    ps.close();
                    ps = con.prepareStatement("SELECT `locationtype`,`map` FROM savedlocations WHERE characterid = ?");
                    ps.setInt(1, charid);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        ret.savedLocations[rs.getInt("locationtype")] = rs.getInt("map");
                    }
                    rs.close();
                    ps.close();
                    ps = con.prepareStatement("SELECT `characterid_to`,`when` FROM famelog WHERE characterid = ? AND DATEDIFF(NOW(),`when`) < 30");
                    ps.setInt(1, charid);
                    rs = ps.executeQuery();
                    ret.lastfametime = 0L;
                    ret.lastmonthfameids = new ArrayList<Integer>(31);
                    while (rs.next()) {
                        ret.lastfametime = Math.max(ret.lastfametime, rs.getTimestamp("when").getTime());
                        ret.lastmonthfameids.add(Integer.valueOf(rs.getInt("characterid_to")));
                    }
                    rs.close();
                    ps.close();
                    ret.buddylist.loadFromDb(charid);
                    ret.storage = MapleStorage.loadStorage(ret.accountid);
                    ret.cs = new CashShop(ret.accountid, charid, (int)ret.getJob());
                    ps = con.prepareStatement("SELECT sn FROM wishlist WHERE characterid = ?");
                    ps.setInt(1, charid);
                    rs = ps.executeQuery();
                    int i2 = 0;
                    while (rs.next()) {
                        ret.wishlist[i2] = rs.getInt("sn");
                        ++i2;
                    }
                    while (i2 < 10) {
                        ret.wishlist[i2] = 0;
                        ++i2;
                    }
                    rs.close();
                    ps.close();
                    ps = con.prepareStatement("SELECT mapid FROM trocklocations WHERE characterid = ?");
                    ps.setInt(1, charid);
                    rs = ps.executeQuery();
                    int r = 0;
                    while (rs.next()) {
                        ret.rocks[r] = rs.getInt("mapid");
                        ++r;
                    }
                    while (r < 10) {
                        ret.rocks[r] = 999999999;
                        ++r;
                    }
                    rs.close();
                    ps.close();
                    ps = con.prepareStatement("SELECT mapid FROM regrocklocations WHERE characterid = ?");
                    ps.setInt(1, charid);
                    rs = ps.executeQuery();
                    r = 0;
                    while (rs.next()) {
                        ret.regrocks[r] = rs.getInt("mapid");
                        ++r;
                    }
                    while (r < 5) {
                        ret.regrocks[r] = 999999999;
                        ++r;
                    }
                    rs.close();
                    ps.close();
                    ps = con.prepareStatement("SELECT * FROM mountdata WHERE characterid = ?");
                    ps.setInt(1, charid);
                    rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new RuntimeException("No mount data found on SQL column");
                    }
                    final IItem mount = ret.getInventory(MapleInventoryType.EQUIPPED).getItem((short)(-18));
                    ret.mount = new MapleMount(ret, (mount != null) ? mount.getItemId() : 0, (ret.job > 1000 && ret.job < 2000) ? 10001004 : ((ret.job >= 2000) ? ((ret.job == 2001 || ret.job >= 2200) ? 20011004 : ((ret.job >= 3000) ? 30001004 : 20001004)) : 1004), rs.getByte("Fatigue"), rs.getByte("Level"), rs.getInt("Exp"));
                    ps.close();
                    rs.close();
                    ret.stats.recalcLocalStats(true);
                }
                else {
                    for (final Pair<IItem, MapleInventoryType> mit : ItemLoader.INVENTORY.loadItems(true, Integer.valueOf(charid)).values()) {
                        ret.getInventory((MapleInventoryType)mit.getRight()).addFromDB((IItem)mit.getLeft());
                    }
                }
            }
            catch (SQLException ess) {
                FilePrinter.printError("MapleCharacter.txt", (Throwable)ess, "載入角色失敗..");
            }
            finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                }
                catch (SQLException ex) {}
            }
        }
        catch (SQLException exxx) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)exxx);
        }
        return ret;
    }
    
    public static void saveNewCharToDB(final MapleCharacter chr, final int type, final boolean db) {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement pse = null;
        ResultSet rs = null;
        try {
            con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
            con.setTransactionIsolation(1);
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO characters (level, fame, str, dex, luk, `int`, exp, hp, mp, maxhp, maxmp, sp, ap, gm, skincolor, gender, job, hair, face, map, meso, hpApUsed, spawnpoint, party, buddyCapacity, monsterbookcover, dojo_pts, dojoRecord, pets, subcategory, marriageId, currentrep, totalrep, prefix, PGSXDJ, jf, zdjf, rwjf, zs, cz, dy, rmb, yb, playerPoints, playerEnergy, jf1, jf2, jf3, jf4, jf5, jf6, jf7, jf8, jf9, jf10,  accountid, name, world, VipMedal) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 1);
            ps.setInt(1, 1);
            ps.setShort(2, (short)0);
            final PlayerStats stat = chr.stats;
            ps.setShort(3, stat.getStr());
            ps.setShort(4, stat.getDex());
            ps.setShort(5, stat.getInt());
            ps.setShort(6, stat.getLuk());
            ps.setInt(7, 0);
            ps.setShort(8, stat.getHp());
            ps.setShort(9, stat.getMp());
            ps.setShort(10, stat.getMaxHp());
            ps.setShort(11, stat.getMaxMp());
            ps.setString(12, "0,0,0,0,0,0,0,0,0,0");
            ps.setShort(13, (short)0);
            ps.setByte(14, (byte)0);
            ps.setByte(15, chr.skinColor);
            ps.setByte(16, chr.gender);
            ps.setShort(17, chr.job);
            ps.setInt(18, chr.hair);
            ps.setInt(19, chr.face);
            ps.setInt(20, (type == 1) ? 0 : ((type == 0) ? 130030000 : ((type == 3) ? 900090000 : 914000000)));
            ps.setInt(21, chr.meso);
            ps.setShort(22, (short)0);
            ps.setByte(23, (byte)0);
            ps.setInt(24, -1);
            ps.setByte(25, chr.buddylist.getCapacity());
            ps.setInt(26, 0);
            ps.setInt(27, 0);
            ps.setInt(28, 0);
            ps.setString(29, "-1,-1,-1");
            ps.setInt(30, 0);
            ps.setInt(31, 0);
            ps.setInt(32, 0);
            ps.setInt(33, 0);
            ps.setString(34, chr.prefix);
            ps.setInt(35, chr.PGSXDJ);
            ps.setInt(36, chr.jf);
            ps.setInt(37, chr.zdjf);
            ps.setInt(38, chr.rwjf);
            ps.setInt(39, chr.zs);
            ps.setInt(40, chr.cz);
            ps.setInt(41, chr.dy);
            ps.setInt(42, chr.rmb);
            ps.setInt(43, chr.yb);
            ps.setInt(44, chr.playerPoints);
            ps.setInt(45, chr.playerEnergy);
            ps.setInt(46, chr.jf1);
            ps.setInt(47, chr.jf2);
            ps.setInt(48, chr.jf3);
            ps.setInt(49, chr.jf4);
            ps.setInt(50, chr.jf5);
            ps.setInt(51, chr.jf6);
            ps.setInt(52, chr.jf7);
            ps.setInt(53, chr.jf8);
            ps.setInt(54, chr.jf9);
            ps.setInt(55, chr.jf10);
            ps.setInt(56, chr.getAccountID());
            ps.setString(57, chr.name);
            ps.setByte(58, chr.world);
            ps.setInt(59, (int)(chr.Vip_Medal ? 1 : 0));
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (!rs.next()) {
                throw new DatabaseException("Inserting char failed.");
            }
            chr.id = rs.getInt(1);
            ps.close();
            rs.close();
            ps = con.prepareStatement("INSERT INTO queststatus (`queststatusid`, `characterid`, `quest`, `status`, `time`, `forfeited`, `customData`) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)", 1);
            pse = con.prepareStatement("INSERT INTO queststatusmobs VALUES (DEFAULT, ?, ?, ?)");
            ps.setInt(1, chr.id);
            for (final MapleQuestStatus q : chr.quests.values()) {
                ps.setInt(2, q.getQuest().getId());
                ps.setInt(3, (int)q.getStatus());
                ps.setInt(4, (int)(q.getCompletionTime() / 1000L));
                ps.setInt(5, q.getForfeited());
                ps.setString(6, q.getCustomData());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                rs.next();
                if (q.hasMobKills()) {
                    final Iterator<Integer> iterator2 = q.getMobKills().keySet().iterator();
                    while (iterator2.hasNext()) {
                        final int mob = (int)Integer.valueOf(iterator2.next());
                        pse.setLong(1, rs.getLong(1));
                        pse.setInt(2, mob);
                        pse.setInt(3, q.getMobKills(mob));
                        pse.executeUpdate();
                    }
                }
                rs.close();
            }
            ps.close();
            pse.close();
            ps = con.prepareStatement("INSERT INTO inventoryslot (characterid, `equip`, `use`, `setup`, `etc`, `cash`) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, chr.id);
            ps.setByte(2, (byte)32);
            ps.setByte(3, (byte)32);
            ps.setByte(4, (byte)32);
            ps.setByte(5, (byte)32);
            ps.setByte(6, (byte)60);
            ps.execute();
            ps.close();
            ps = con.prepareStatement("INSERT INTO mountdata (characterid, `Level`, `Exp`, `Fatigue`) VALUES (?, ?, ?, ?)");
            ps.setInt(1, chr.id);
            ps.setByte(2, (byte)1);
            ps.setInt(3, 0);
            ps.setByte(4, (byte)0);
            ps.execute();
            ps.close();
            final List<Pair<IItem, MapleInventoryType>> listing = new ArrayList<Pair<IItem, MapleInventoryType>>();
            for (final MapleInventory iv : chr.inventory) {
                for (final IItem item : iv.list()) {
                    listing.add(new Pair<IItem, MapleInventoryType>(item, iv.getType()));
                }
            }
            ItemLoader.INVENTORY.saveItems(listing, con, Integer.valueOf(chr.id));
            final int[] array1 = { 2, 3, 4, 5, 6, 7, 16, 17, 18, 19, 23, 25, 26, 27, 29, 31, 34, 35, 37, 38, 40, 41, 43, 44, 45, 46, 48, 50, 56, 57, 59, 60, 61, 62, 63, 64, 65 };
            final int[] array2 = { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 4, 4, 4, 5, 5, 6, 6, 6, 6, 6, 6, 6 };
            final int[] array3 = { 10, 12, 13, 18, 24, 21, 8, 5, 0, 4, 1, 19, 14, 15, 52, 2, 17, 11, 3, 20, 16, 23, 9, 50, 51, 6, 22, 7, 53, 54, 100, 101, 102, 103, 104, 105, 106 };
            ps = con.prepareStatement("INSERT INTO keymap (characterid, `keye`, `type`, `action`) VALUES (?, ?, ?, ?)");
            ps.setInt(1, chr.id);
            for (int i = 0; i < array1.length; ++i) {
                ps.setInt(2, array1[i]);
                ps.setInt(3, array2[i]);
                ps.setInt(4, array3[i]);
                ps.execute();
            }
            ps.close();
            con.commit();
        }
        catch (SQLException ex2) {}
        catch (DatabaseException e) {
            FilePrinter.printError("MapleCharacter.txt", (Throwable)e, "[角色存檔] 儲存角色資料失敗");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            try {
                con.rollback();
            }
            catch (SQLException ex) {
                FilePrinter.printError("MapleCharacter.txt", (Throwable)ex, "[角色存檔] 儲存失敗，繼續使用暫存檔不儲存資料庫");
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
            try {
                if (pse != null) {
                    pse.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                con.setAutoCommit(true);
                con.setTransactionIsolation(4);
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e2) {
                FilePrinter.printError("MapleCharacter.txt", (Throwable)e2, "[角色存檔] 錯誤自動返回儲存功能");
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e2);
            }
        }
        finally {
            try {
                if (pse != null) {
                    pse.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                con.setAutoCommit(true);
                con.setTransactionIsolation(4);
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e3) {
                FilePrinter.printError("MapleCharacter.txt", (Throwable)e3, "[角色存檔] 錯誤自動返回儲存功能");
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e3);
            }
        }
    }
    
    public int saveToDB(final boolean dc, final boolean fromcs) {
        if (this.isClone()) {
            return -1;
        }
        int retValue = 1;
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement pse = null;
        ResultSet rs = null;
        try {
            con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
            con.setTransactionIsolation(1);
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE characters SET level = ?, fame = ?, str = ?, dex = ?, luk = ?, `int` = ?, exp = ?, hp = ?, mp = ?, maxhp = ?, maxmp = ?, sp = ?, ap = ?, gm = ?, skincolor = ?, gender = ?, job = ?, hair = ?, face = ?, map = ?, meso = ?, hpApUsed = ?, spawnpoint = ?, party = ?, buddyCapacity = ?, monsterbookcover = ?, dojo_pts = ?, dojoRecord = ?, pets = ?, subcategory = ?, marriageId = ?, currentrep = ?, totalrep = ?, charmessage = ?, expression = ?, constellation = ?, blood = ?, month = ?, jf = ?, zdjf = ?, rwjf = ?, zs = ?, cz = ?, dy = ?, rmb = ?, yb =?,  playerPoints = ?, playerEnergy = ?, jf1 = ?, jf2 = ?, jf3 = ?, jf4 = ?, jf5 = ?, jf6 = ?, jf7 = ?, jf8 = ?, jf9 = ?, jf10 = ?, day = ?, beans = ?, prefix = ?, PGSXDJ = ?,gachexp = ?, name = ?, VipMedal = ?, saved_faces = ?, saved_hairs = ? WHERE id = ?");
            ps.setInt(1, (int)this.level);
            ps.setShort(2, this.fame);
            ps.setShort(3, this.stats.getStr());
            ps.setShort(4, this.stats.getDex());
            ps.setShort(5, this.stats.getLuk());
            ps.setShort(6, this.stats.getInt());
            ps.setInt(7, this.exp);
            ps.setShort(8, (short)((this.stats.getHp() < 1) ? 50 : this.stats.getHp()));
            ps.setShort(9, this.stats.getMp());
            ps.setShort(10, this.stats.getMaxHp());
            ps.setShort(11, this.stats.getMaxMp());
            final StringBuilder sps = new StringBuilder();
            for (int i = 0; i < this.remainingSp.length; ++i) {
                sps.append(this.remainingSp[i]);
                sps.append(",");
            }
            final String sp = sps.toString();
            ps.setString(12, sp.substring(0, sp.length() - 1));
            ps.setShort(13, this.remainingAp);
            ps.setByte(14, this.gmLevel);
            ps.setByte(15, this.skinColor);
            ps.setByte(16, this.gender);
            ps.setShort(17, this.job);
            ps.setInt(18, this.hair);
            ps.setInt(19, this.face);
            if (!fromcs && this.map != null) {
                if (this.map.getForcedReturnId() != 999999999) {
                    if (this.map.getId() == 220080001) {
                        ps.setInt(20, 910000000);
                    }
                    else {
                        ps.setInt(20, this.map.getForcedReturnId());
                    }
                }
                else {
                    ps.setInt(20, (this.stats.getHp() < 1) ? this.map.getReturnMapId() : this.map.getId());
                }
            }
            else {
                ps.setInt(20, this.mapid);
            }
            ps.setInt(21, this.meso);
            ps.setShort(22, this.hpmpApUsed);
            if (this.map == null) {
                ps.setByte(23, (byte)0);
            }
            else {
                final MaplePortal closest = this.map.findClosestSpawnpoint(this.getPosition());
                ps.setByte(23, (byte)((closest != null) ? closest.getId() : 0));
            }
            ps.setInt(24, (this.party != null) ? this.party.getId() : -1);
            ps.setShort(25, (short)this.buddylist.getCapacity());
            ps.setInt(26, this.bookCover);
            ps.setInt(27, this.dojo);
            ps.setInt(28, (int)this.dojoRecord);
            final StringBuilder petz = new StringBuilder();
            int petLength = 0;
            for (final MaplePet pet : this.pets) {
                pet.saveToDb();
                if (pet.getSummoned()) {
                    petz.append((int)pet.getInventoryPosition());
                    petz.append(",");
                    ++petLength;
                }
            }
            while (petLength < 3) {
                petz.append("-1,");
                ++petLength;
            }
            final String petstring = petz.toString();
            ps.setString(29, petstring.substring(0, petstring.length() - 1));
            ps.setByte(30, this.subcategory);
            ps.setInt(31, this.marriageId);
            ps.setInt(32, this.currentrep);
            ps.setInt(33, this.totalrep);
            ps.setString(34, this.charmessage);
            ps.setInt(35, this.expression);
            ps.setInt(36, this.constellation);
            ps.setInt(37, this.blood);
            ps.setInt(38, this.month);
            ps.setInt(39, this.jf);
            ps.setInt(40, this.zdjf);
            ps.setInt(41, this.rwjf);
            ps.setInt(42, this.zs);
            ps.setInt(43, this.cz);
            ps.setInt(44, this.dy);
            ps.setInt(45, this.rmb);
            ps.setInt(46, this.yb);
            ps.setInt(47, this.playerPoints);
            ps.setInt(48, this.playerEnergy);
            ps.setInt(49, this.jf1);
            ps.setInt(50, this.jf2);
            ps.setInt(51, this.jf3);
            ps.setInt(52, this.jf4);
            ps.setInt(53, this.jf5);
            ps.setInt(54, this.jf6);
            ps.setInt(55, this.jf7);
            ps.setInt(56, this.jf8);
            ps.setInt(57, this.jf9);
            ps.setInt(58, this.jf10);
            ps.setInt(59, this.day);
            ps.setInt(60, this.beans);
            ps.setString(61, this.prefix);
            ps.setInt(62, this.PGSXDJ);
            ps.setInt(63, this.gachexp);
            ps.setString(64, this.name);
            ps.setInt(65, (int)(this.Vip_Medal ? 1 : 0));
            final StringBuilder faces = new StringBuilder();
            for (int j = 0; j < this.savedFaces.length; ++j) {
                faces.append(this.savedFaces[j]);
                faces.append(",");
            }
            final String saved_faces = faces.toString();
            ps.setString(66, saved_faces.substring(0, saved_faces.length() - 1));
            final StringBuilder hairs = new StringBuilder();
            for (int k = 0; k < this.savedHairs.length; ++k) {
                hairs.append(this.savedHairs[k]);
                hairs.append(",");
            }
            final String saved_hairs = hairs.toString();
            ps.setString(67, saved_hairs.substring(0, saved_hairs.length() - 1));
            ps.setInt(68, this.id);
            if (ps.executeUpdate() < 1) {
                ps.close();
                throw new DatabaseException("Character not in database (" + this.id + ")");
            }
            ps.close();
            this.deleteWhereCharacterId(con, "DELETE FROM skillmacros WHERE characterid = ?");
            for (int l = 0; l < 5; ++l) {
                final SkillMacro macro = this.skillMacros[l];
                if (macro != null) {
                    ps = con.prepareStatement("INSERT INTO skillmacros (characterid, skill1, skill2, skill3, name, shout, position) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    ps.setInt(1, this.id);
                    ps.setInt(2, macro.getSkill1());
                    ps.setInt(3, macro.getSkill2());
                    ps.setInt(4, macro.getSkill3());
                    ps.setString(5, macro.getName());
                    ps.setInt(6, macro.getShout());
                    ps.setInt(7, l);
                    ps.execute();
                    ps.close();
                }
            }
            this.deleteWhereCharacterId(con, "DELETE FROM inventoryslot WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO inventoryslot (characterid, `equip`, `use`, `setup`, `etc`, `cash`) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, this.id);
            ps.setByte(2, this.getInventory(MapleInventoryType.EQUIP).getSlotLimit());
            ps.setByte(3, this.getInventory(MapleInventoryType.USE).getSlotLimit());
            ps.setByte(4, this.getInventory(MapleInventoryType.SETUP).getSlotLimit());
            ps.setByte(5, this.getInventory(MapleInventoryType.ETC).getSlotLimit());
            ps.setByte(6, this.getInventory(MapleInventoryType.CASH).getSlotLimit());
            ps.execute();
            ps.close();
            this.saveInventory(con);
            this.deleteWhereCharacterId(con, "DELETE FROM questinfo WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO questinfo (`characterid`, `quest`, `customData`) VALUES (?, ?, ?)");
            ps.setInt(1, this.id);
            for (final Entry<Integer, String> q : this.questinfo.entrySet()) {
                ps.setInt(2, (int)Integer.valueOf(q.getKey()));
                ps.setString(3, (String)q.getValue());
                ps.execute();
            }
            ps.close();
            this.deleteWhereCharacterId(con, "DELETE FROM queststatus WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO queststatus (`queststatusid`, `characterid`, `quest`, `status`, `time`, `forfeited`, `customData`) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)", 1);
            pse = con.prepareStatement("INSERT INTO queststatusmobs VALUES (DEFAULT, ?, ?, ?)", 1);
            ps.setInt(1, this.id);
            for (final MapleQuestStatus q2 : this.quests.values()) {
                ps.setInt(2, q2.getQuest().getId());
                ps.setInt(3, (int)q2.getStatus());
                ps.setInt(4, (int)(q2.getCompletionTime() / 1000L));
                ps.setInt(5, q2.getForfeited());
                ps.setString(6, q2.getCustomData());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                rs.next();
                if (q2.hasMobKills()) {
                    final Iterator<Integer> iterator4 = q2.getMobKills().keySet().iterator();
                    while (iterator4.hasNext()) {
                        final int mob = (int)Integer.valueOf(iterator4.next());
                        pse.setLong(1, rs.getLong(1));
                        pse.setInt(2, mob);
                        pse.setInt(3, q2.getMobKills(mob));
                        pse.executeUpdate();
                    }
                }
                rs.close();
            }
            ps.close();
            pse.close();
            this.deleteWhereCharacterId(con, "DELETE FROM skills WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO skills (characterid, skillid, skilllevel, masterlevel, expiration) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, this.id);
            for (final Entry<ISkill, SkillEntry> skill : this.skills.entrySet()) {
                if (GameConstants.isApplicableSkill(((ISkill)skill.getKey()).getId())) {
                    ps.setInt(2, ((ISkill)skill.getKey()).getId());
                    ps.setByte(3, ((SkillEntry)skill.getValue()).skillevel);
                    ps.setByte(4, ((SkillEntry)skill.getValue()).masterlevel);
                    ps.setLong(5, ((SkillEntry)skill.getValue()).expiration);
                    ps.execute();
                }
            }
            ps.close();
            final List<MapleCoolDownValueHolder> cd = this.getCooldowns();
            if (dc && cd.size() > 0) {
                ps = con.prepareStatement("INSERT INTO skills_cooldowns (charid, SkillID, StartTime, length) VALUES (?, ?, ?, ?)");
                ps.setInt(1, this.getId());
                for (final MapleCoolDownValueHolder cooling : cd) {
                    ps.setInt(2, cooling.skillId);
                    ps.setLong(3, cooling.startTime);
                    ps.setLong(4, cooling.length);
                    ps.execute();
                }
                ps.close();
            }
            this.deleteWhereCharacterId(con, "DELETE FROM savedlocations WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO savedlocations (characterid, `locationtype`, `map`) VALUES (?, ?, ?)");
            ps.setInt(1, this.id);
            for (final SavedLocationType savedLocationType : SavedLocationType.values()) {
                if (this.savedLocations[savedLocationType.getValue()] != -1) {
                    ps.setInt(2, savedLocationType.getValue());
                    ps.setInt(3, this.savedLocations[savedLocationType.getValue()]);
                    ps.execute();
                }
            }
            ps.close();
            ps = con.prepareStatement("DELETE FROM achievements WHERE accountid = ?");
            ps.setInt(1, this.accountid);
            ps.executeUpdate();
            ps.close();
            ps = con.prepareStatement("INSERT INTO achievements(charid, achievementid, accountid) VALUES(?, ?, ?)");
            for (final Integer achid : this.finishedAchievements) {
                ps.setInt(1, this.id);
                ps.setInt(2, (int)achid);
                ps.setInt(3, this.accountid);
                ps.executeUpdate();
            }
            ps.close();
            this.deleteWhereCharacterId(con, "DELETE FROM buddies WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO buddies (characterid, `buddyid`, `pending`) VALUES (?, ?, ?)");
            ps.setInt(1, this.id);
            for (final BuddyEntry entry : this.buddylist.getBuddies()) {
                if (entry != null) {
                    ps.setInt(2, entry.getCharacterId());
                    ps.setInt(3, (int)(entry.isVisible() ? 0 : 1));
                    ps.execute();
                }
            }
            ps.close();
            ps = con.prepareStatement("UPDATE accounts SET `mPoints` = ?, `vpoints` = ?, `VIP` = ? WHERE id = ?");
            ps.setInt(1, this.maplepoints);
            ps.setInt(2, this.vpoints);
            ps.setInt(3, this.vip);
            ps.setInt(4, this.client.getAccID());
            ps.execute();
            ps.close();
            if (this.storage != null) {
                this.storage.saveToDB(con);
            }
            if (this.cs != null) {
                this.cs.save(con);
            }
            PlayerNPC.updateByCharId(this, con);
            this.keylayout.saveKeys(this.id, con);
            this.mount.saveMount(this.id, con);
            this.monsterbook.saveCards(this.id, con);
            this.deleteWhereCharacterId(con, "DELETE FROM wishlist WHERE characterid = ?");
            for (int m = 0; m < this.getWishlistSize(); ++m) {
                ps = con.prepareStatement("INSERT INTO wishlist(characterid, sn) VALUES(?, ?) ");
                ps.setInt(1, this.getId());
                ps.setInt(2, this.wishlist[m]);
                ps.execute();
                ps.close();
            }
            this.deleteWhereCharacterId(con, "DELETE FROM trocklocations WHERE characterid = ?");
            for (int m = 0; m < this.rocks.length; ++m) {
                if (this.rocks[m] != 999999999) {
                    ps = con.prepareStatement("INSERT INTO trocklocations(characterid, mapid) VALUES(?, ?) ");
                    ps.setInt(1, this.getId());
                    ps.setInt(2, this.rocks[m]);
                    ps.execute();
                    ps.close();
                }
            }
            this.deleteWhereCharacterId(con, "DELETE FROM regrocklocations WHERE characterid = ?");
            for (int m = 0; m < this.regrocks.length; ++m) {
                if (this.regrocks[m] != 999999999) {
                    ps = con.prepareStatement("INSERT INTO regrocklocations(characterid, mapid) VALUES(?, ?) ");
                    ps.setInt(1, this.getId());
                    ps.setInt(2, this.regrocks[m]);
                    ps.execute();
                    ps.close();
                }
            }
            con.commit();
        }
        catch (UnsupportedOperationException ex2) {}
        catch (SQLException ex3) {}
        catch (DatabaseException e) {
            retValue = 0;
            FileoutputUtil.logToFile("logs/保存角色数据出錯.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + this.getClient().getSession().remoteAddress().toString().split(":")[0] + " 账号 " + this.getClient().getAccountName() + " 账号ID " + this.getClient().getAccID() + " 角色名 " + this.getName() + " 角色ID " + this.getId());
            FilePrinter.printError("MapleCharacter.txt", (Throwable)e, "[角色存檔]儲存角色失敗");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            FileoutputUtil.outError("logs/保存角色数据出錯.txt", (Throwable)e);
            try {
                con.rollback();
            }
            catch (SQLException ex) {
                FileoutputUtil.logToFile("logs/保存角色数据出錯.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + this.getClient().getSession().remoteAddress().toString().split(":")[0] + " 账号 " + this.getClient().getAccountName() + " 账号ID " + this.getClient().getAccID() + " 角色名 " + this.getName() + " 角色ID " + this.getId());
                FileoutputUtil.outError("logs/保存角色数据出錯.txt", (Throwable)ex);
                FilePrinter.printError("MapleCharacter.txt", (Throwable)e, "[角色存檔] 儲存失敗，繼續使用暫存檔不儲存資料庫");
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
            try {
                if (ps != null) {
                    ps.close();
                }
                if (pse != null) {
                    pse.close();
                }
                if (rs != null) {
                    rs.close();
                }
                con.setAutoCommit(true);
                con.setTransactionIsolation(4);
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException es) {
                retValue = 0;
                FileoutputUtil.logToFile("logs/保存角色数据出錯.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + this.getClient().getSession().remoteAddress().toString().split(":")[0] + " 账号 " + this.getClient().getAccountName() + " 账号ID " + this.getClient().getAccID() + " 角色名 " + this.getName() + " 角色ID " + this.getId());
                FilePrinter.printError("MapleCharacter.txt", (Throwable)es, "[角色存檔] 錯誤自動返回儲存功能");
                FileoutputUtil.outError("logs/保存角色数据出錯.txt", (Throwable)es);
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)es);
            }
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (pse != null) {
                    pse.close();
                }
                if (rs != null) {
                    rs.close();
                }
                con.setAutoCommit(true);
                con.setTransactionIsolation(4);
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException es2) {
                retValue = 0;
                FileoutputUtil.logToFile("logs/保存角色数据出錯.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + this.getClient().getSession().remoteAddress().toString().split(":")[0] + " 账号 " + this.getClient().getAccountName() + " 账号ID " + this.getClient().getAccID() + " 角色名 " + this.getName() + " 角色ID " + this.getId());
                FilePrinter.printError("MapleCharacter.txt", (Throwable)es2, "[角色存檔] 錯誤自動返回儲存功能");
                FileoutputUtil.outError("logs/保存角色数据出錯.txt", (Throwable)es2);
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)es2);
            }
        }
        return retValue;
    }
    
    private void deleteWhereCharacterId(final Connection con, final String sql) throws SQLException {
        deleteWhereCharacterId(con, sql, this.id);
    }
    
    public static void deleteWhereCharacterId(final Connection con, final String sql, final int id) {
        try {
            final PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception ex) {
            FilePrinter.printError("MapleCharacter.txt", (Throwable)ex, "[deleteWhereCharacterId]");
        }
    }
    
    public void saveInventory(final Connection con) {
        final List<Pair<IItem, MapleInventoryType>> listing = new ArrayList<Pair<IItem, MapleInventoryType>>();
        for (final MapleInventory iv : this.inventory) {
            for (final IItem item : iv.list()) {
                listing.add(new Pair<IItem, MapleInventoryType>(item, iv.getType()));
            }
        }
        if (con != null) {
            try {
                ItemLoader.INVENTORY.saveItems(listing, con, Integer.valueOf(this.id));
            }
            catch (SQLException ex) {
                FilePrinter.printError("MapleCharacter.txt", (Throwable)ex, "[saveInventory]");
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
        }
        else {
            try {
                ItemLoader.INVENTORY.saveItems(listing, Integer.valueOf(this.id));
            }
            catch (SQLException ex) {
                FilePrinter.printError("MapleCharacter.txt", (Throwable)ex, "[saveInventory]");
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
        }
    }
    
    public final PlayerStats getStat() {
        return this.stats;
    }
    
    public final PlayerRandomStream CRand() {
        return this.CRand;
    }
    
    public final void QuestInfoPacket(final MaplePacketLittleEndianWriter mplew) {
        mplew.writeShort(this.questinfo.size());
        for (final Entry<Integer, String> q : this.questinfo.entrySet()) {
            mplew.writeShort((int)Integer.valueOf(q.getKey()));
            mplew.writeMapleAsciiString((q.getValue() == null) ? "" : ((String)q.getValue()));
        }
    }
    
    public final void updateInfoQuest(final int questid, final String data) {
        this.questinfo.put(Integer.valueOf(questid), data);
        this.client.sendPacket(MaplePacketCreator.updateInfoQuest(questid, data));
    }
    
    public final String getInfoQuest(final int questid) {
        if (this.questinfo.containsKey((Object)Integer.valueOf(questid))) {
            return (String)this.questinfo.get((Object)Integer.valueOf(questid));
        }
        return "";
    }
    
    public final int getNumQuest() {
        int i = 0;
        for (final MapleQuestStatus q : this.quests.values()) {
            if (q.getStatus() == 2 && !q.isCustom()) {
                ++i;
            }
        }
        return i;
    }
    
    public final byte getQuestStatus(final int quest) {
        return this.getQuest(MapleQuest.getInstance(quest)).getStatus();
    }
    
    public final MapleQuestStatus getQuest(final MapleQuest quest) {
        if (!this.quests.containsKey((Object)quest)) {
            return new MapleQuestStatus(quest, 0);
        }
        return (MapleQuestStatus)this.quests.get((Object)quest);
    }
    
    public final void setQuestAdd(final MapleQuest quest, final byte status, final String customData) {
        if (!this.quests.containsKey((Object)quest)) {
            final MapleQuestStatus stat = new MapleQuestStatus(quest, (int)status);
            stat.setCustomData(customData);
            this.quests.put(quest, stat);
        }
    }
    
    public final MapleQuestStatus getQuestNAdd(final MapleQuest quest) {
        if (!this.quests.containsKey((Object)quest)) {
            final MapleQuestStatus status = new MapleQuestStatus(quest, 0);
            this.quests.put(quest, status);
            return status;
        }
        return (MapleQuestStatus)this.quests.get((Object)quest);
    }
    
    public final MapleQuestStatus getQuestNoAdd(final MapleQuest quest) {
        return (MapleQuestStatus)this.quests.get((Object)quest);
    }
    
    public final MapleQuestStatus getQuestRemove(final MapleQuest quest) {
        return (MapleQuestStatus)this.quests.remove((Object)quest);
    }
    
    public final void updateQuest(final MapleQuestStatus quest) {
        this.updateQuest(quest, false);
    }
    
    public final void updateQuest(final MapleQuestStatus quest, final boolean update) {
        this.quests.put(quest.getQuest(), quest);
        if (!quest.isCustom()) {
            this.client.sendPacket(MaplePacketCreator.updateQuest(quest));
            if (quest.getStatus() == 1 && !update) {
                this.client.sendPacket(MaplePacketCreator.updateQuestInfo(this, quest.getQuest().getId(), quest.getNpc(), (byte)8));
            }
        }
    }
    
    public final Map<Integer, String> getInfoQuest_Map() {
        return this.questinfo;
    }
    
    public final Map<MapleQuest, MapleQuestStatus> getQuest_Map() {
        return this.quests;
    }
    
    public boolean isActiveBuffedValue(final int skillid) {
        final LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>((Collection<? extends MapleBuffStatValueHolder>)this.effects.values());
        for (final MapleBuffStatValueHolder mbsvh : allBuffs) {
            if (mbsvh.effect.isSkill() && mbsvh.effect.getSourceId() == skillid) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isBuffedValue(final int skillid) {
        final LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>((Collection<? extends MapleBuffStatValueHolder>)this.effects.values());
        for (final MapleBuffStatValueHolder mbsvh : allBuffs) {
            if (mbsvh.effect.getSourceId() == skillid) {
                return true;
            }
        }
        return false;
    }
    
    public Integer getBuffedValue(final MapleBuffStat effect) {
        final MapleBuffStatValueHolder mbsvh = (MapleBuffStatValueHolder)this.effects.get((Object)effect);
        return (mbsvh == null) ? null : Integer.valueOf(mbsvh.value);
    }
    
    public boolean hasBuffedValue(final MapleBuffStat effect) {
        return this.getBuffedValue(effect) != null;
    }
    
    public final Integer getBuffedSkill_X(final MapleBuffStat effect) {
        final MapleBuffStatValueHolder mbsvh = (MapleBuffStatValueHolder)this.effects.get((Object)effect);
        if (mbsvh == null) {
            return null;
        }
        return Integer.valueOf(mbsvh.effect.getX());
    }
    
    public final Integer getBuffedSkill_Y(final MapleBuffStat effect) {
        final MapleBuffStatValueHolder mbsvh = (MapleBuffStatValueHolder)this.effects.get((Object)effect);
        if (mbsvh == null) {
            return null;
        }
        return Integer.valueOf(mbsvh.effect.getY());
    }
    
    public boolean isBuffFrom(final MapleBuffStat stat, final ISkill skill) {
        final MapleBuffStatValueHolder mbsvh = (MapleBuffStatValueHolder)this.effects.get((Object)stat);
        return mbsvh != null && mbsvh.effect.isSkill() && mbsvh.effect.getSourceId() == skill.getId();
    }
    
    public boolean changeFace(final short item, final int color) {
        int newFace = this.face / 1000 * 1000 + color + this.face % 10;
        if (!MapleItemInformationProvider.getInstance().faceExists(newFace)) {
            newFace = this.face;
            this.gainItem((int)item, 1);
        }
        else {
            this.face = newFace;
            this.updateSingleStat(MapleStat.FACE, newFace);
            this.equipChanged();
        }
        return MapleItemInformationProvider.faceLists.containsKey((Object)Integer.valueOf(color));
    }
    
    public int getBuffSource(final MapleBuffStat stat) {
        final MapleBuffStatValueHolder mbsvh = (MapleBuffStatValueHolder)this.effects.get((Object)stat);
        return (mbsvh == null) ? -1 : mbsvh.effect.getSourceId();
    }
    
    public int getItemQuantity(final int itemid, final boolean checkEquipped) {
        int possesed = this.inventory[GameConstants.getInventoryType(itemid).ordinal()].countById(itemid);
        if (checkEquipped) {
            possesed += this.inventory[MapleInventoryType.EQUIPPED.ordinal()].countById(itemid);
        }
        return possesed;
    }
    
    public void setBuffedValue(final MapleBuffStat effect, final int value) {
        final MapleBuffStatValueHolder mbsvh = (MapleBuffStatValueHolder)this.effects.get((Object)effect);
        if (mbsvh == null) {
            return;
        }
        mbsvh.value = value;
    }
    
    public Long getBuffedStarttime(final MapleBuffStat effect) {
        final MapleBuffStatValueHolder mbsvh = (MapleBuffStatValueHolder)this.effects.get((Object)effect);
        return (mbsvh == null) ? null : Long.valueOf(mbsvh.startTime);
    }
    
    public MapleStatEffect getStatForBuff(final MapleBuffStat effect) {
        final MapleBuffStatValueHolder mbsvh = (MapleBuffStatValueHolder)this.effects.get((Object)effect);
        return (mbsvh == null) ? null : mbsvh.effect;
    }
    
    public void doRecovery() {
        final MapleStatEffect bloodEffect = this.getStatForBuff(MapleBuffStat.RECOVERY);
        if (bloodEffect != null) {
            this.prepareRecovery();
            if (this.stats.getHp() >= this.stats.getCurrentMaxHp()) {
                this.cancelEffectFromBuffStat(MapleBuffStat.RECOVERY);
            }
            else {
                this.healHP(bloodEffect.getX(), true);
            }
        }
    }
    
    public final boolean canRecover(final long now) {
        return this.lastRecoveryTime > 0L && this.lastRecoveryTime + 5000L < now;
    }
    
    private void prepareRecovery() {
        this.lastRecoveryTime = System.currentTimeMillis();
    }
    
    private void prepareDragonBlood(final MapleStatEffect bloodEffect) {
        if (this.dragonBloodSchedule != null) {
            this.dragonBloodSchedule.cancel(false);
        }
        this.dragonBloodSchedule = BuffTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                if (stats.getHp() - bloodEffect.getX() > 1) {
                    MapleCharacter.this.cancelBuffStats(MapleBuffStat.DRAGONBLOOD);
                }
                else {
                    MapleCharacter.this.addHP(-bloodEffect.getX());
                    client.sendPacket(MaplePacketCreator.showOwnBuffEffect(bloodEffect.getSourceId(), 5));
                    map.broadcastMessage(MapleCharacter.this, MaplePacketCreator.showBuffeffect(MapleCharacter.this.getId(), bloodEffect.getSourceId(), 5), false);
                }
            }
        }, 4000L, 4000L);
    }
    
    public void startMapTimeLimitTask(int time, final MapleMap to) {
        this.client.sendPacket(MaplePacketCreator.getClock(time));
        time *= 1000;
        this.mapTimeLimitTask = MapTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                MapleCharacter.this.changeMap(to, to.getPortal(0));
            }
        }, (long)time, (long)time);
    }
    
    public void startFishingTask(final boolean VIP) {
        try {
            final int time = GameConstants.getFishingTime(VIP, this.isGM());
            this.dropMessage(5, "开始钓鱼：请注意检查背包空间");
            this.dropMessage(5, "钓鱼时间：" + time + "豪秒");
            this.cancelFishingTask();
            this.fishing = EtcTimer.getInstance().register((Runnable)new Runnable() {
                @Override
                public void run() {
                    final boolean expMulti = MapleCharacter.this.haveItem(2300001, 1, false, true);
                    if (!expMulti && !MapleCharacter.this.haveItem(2300000, 1, false, true)) {
                        MapleCharacter.this.cancelFishingTask();
                        return;
                    }
                    MapleInventoryManipulator.removeById(client, MapleInventoryType.USE, expMulti ? 2300001 : 2300000, 1, false, false);
                    final int rewardType = FishingRewardFactory.getInstance().getNextRewardType();
                    switch (rewardType) {
                        case 0: {
                            final int money = Randomizer.rand(expMulti ? 15 : 10, expMulti ? 2000 : 1000);
                            MapleCharacter.this.gainMeso(money, true);
                            client.sendPacket(UIPacket.fishingUpdate((byte)1, money));
                            break;
                        }
                        case 1: {
                            final int experi = Randomizer.nextInt(Math.abs(GameConstants.getExpNeededForLevel((int)level) / 200) + 1);
                            MapleCharacter.this.gainExp(expMulti ? (experi * 3 / 2) : experi, true, false, true);
                            client.sendPacket(UIPacket.fishingUpdate((byte)2, experi));
                            break;
                        }
                        default: {
                            final int gl = Randomizer.nextInt(2);
                            if (gl == 1) {
                                final FishingReward item = FishingRewardFactory.getInstance().getNextRewardItemId();
                                if (item != null) {
                                    if (!MapleInventoryManipulator.checkSpace(client, item.getItemId(), 1, MapleCharacter.this.getName())) {
                                        client.sendPacket(MaplePacketCreator.serverNotice(5, "温馨提示：要保持背包空位足够哦，满了是不能获得钓鱼物品的哦"));
                                        return;
                                    }
                                    MapleInventoryManipulator.addById(client, item.getItemId(), (short)1, GameConstants.isChair(item.getItemId()) ? MapleCharacter.this.getName() : null, null, (long)item.getExpiration());
                                    client.sendPacket(UIPacket.fishingUpdate((byte)0, item.getItemId()));
                                }
                                break;
                            }
                            final int moneya = Randomizer.rand(expMulti ? 15 : 10, expMulti ? 2000 : 1000);
                            MapleCharacter.this.gainMeso(moneya, true);
                            client.sendPacket(UIPacket.fishingUpdate((byte)1, moneya));
                            break;
                        }
                    }
                    map.broadcastMessage(UIPacket.fishingCaught(id));
                    final int tmp = Randomizer.nextInt(10000);
                    if (tmp == 5000) {
                        map.spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(8510000), new Point(1000, 385));
                        map.spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(8520000), new Point(0, 385));
                        MapleCharacter.this.dropMessage(5, "哇！鱼王出现了！快跑啊！");
                        Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "哇！ " + MapleCharacter.this.getName() + "钓鱼钓出来鱼王了，大家快跑！！！"));
                    }
                }
            }, (long)time, (long)time);
        }
        catch (RejectedExecutionException ex) {}
    }
    
    public void cancelMapTimeLimitTask() {
        if (this.mapTimeLimitTask != null) {
            this.mapTimeLimitTask.cancel(false);
        }
    }
    
    public void cancelFishingTask() {
        if (this.fishing != null && !this.fishing.isCancelled()) {
            this.fishing.cancel(false);
        }
    }
    
    public void registerEffect(final MapleStatEffect effect, final long starttime, final ScheduledFuture<?> schedule, final int from) {
        this.registerEffect(effect, starttime, schedule, effect.getStatups(), false, effect.getDuration(), from);
    }
    
    public void registerEffect(final MapleStatEffect effect, final long starttime, final ScheduledFuture<?> schedule, final List<Pair<MapleBuffStat, Integer>> statups, final boolean silent, final int localDuration, final int cid) {
        if (effect.isHide() && this.isGM()) {
            this.hidden = true;
            this.map.broadcastNONGMMessage(this, MaplePacketCreator.removePlayerFromMap(this.getId()), false);
        }
        else if (effect.isDragonBlood()) {
            this.prepareDragonBlood(effect);
        }
        else if (effect.isBerserk()) {
            this.checkBerserk();
        }
        else if (effect.isMonsterRiding_()) {
            this.getMount().startSchedule();
        }
        else if (effect.isBeholder()) {
            this.prepareBeholderEffect();
        }
        else if (effect.isRecovery()) {
            this.prepareRecovery();
        }
        else if (GameConstants.isAran((int)this.getJob())) {
            final int reduce = this.Aran_ReduceCombo(effect.getSourceId());
            if (reduce > 0) {
                this.setCombo(this.getCombo() - reduce);
            }
        }
        int clonez = 0;
        for (final Pair<MapleBuffStat, Integer> statup : statups) {
            if (statup.getLeft() == MapleBuffStat.ILLUSION) {
                clonez = (int)Integer.valueOf(statup.getRight());
            }
            final int value = (int)Integer.valueOf(statup.getRight());
            if (statup.getLeft() == MapleBuffStat.MONSTER_RIDING && effect.getSourceId() == 5221006 && this.battleshipHP <= 0) {
                this.battleshipHP = value;
            }
            final MapleBuffStatValueHolder mbsvh = new MapleBuffStatValueHolder(effect, starttime, schedule, value, localDuration, cid, effect.getSourceId());
            this.effects.put(statup.getLeft(), mbsvh);
            this.skillID.put(Integer.valueOf(effect.getSourceId()), mbsvh);
        }
        if (clonez > 0) {
            final int cloneSize = Math.max((int)this.getNumClones(), this.getCloneSize());
            if (clonez > cloneSize) {
                for (int i = 0; i < clonez - cloneSize; ++i) {
                    this.cloneLook();
                }
            }
        }
        this.stats.recalcLocalStats();
        if (this.getDebugMessage()) {
            for (final Pair<MapleBuffStat, Integer> buf : statups) {
                this.dropMessage(6, "[系統提示]\u0010" + ((MapleBuffStat)buf.getLeft()).toString() + "(0x" + HexTool.toString(((MapleBuffStat)buf.getLeft()).getValue()) + ")");
            }
        }
    }
    
    public List<MapleBuffStat> getBuffStats(final MapleStatEffect effect, final long startTime) {
        final List<MapleBuffStat> bstats = new ArrayList<MapleBuffStat>();
        final Map<MapleBuffStat, MapleBuffStatValueHolder> allBuffs = new EnumMap<MapleBuffStat, MapleBuffStatValueHolder>((Map<MapleBuffStat, ? extends MapleBuffStatValueHolder>)this.effects);
        for (final Entry<MapleBuffStat, MapleBuffStatValueHolder> stateffect : allBuffs.entrySet()) {
            final MapleBuffStatValueHolder mbsvh = (MapleBuffStatValueHolder)stateffect.getValue();
            if (mbsvh.effect.sameSource(effect) && (startTime == -1L || startTime == mbsvh.startTime)) {
                bstats.add(stateffect.getKey());
            }
        }
        return bstats;
    }
    
    public List<MapleBuffStat> getBuffStatsFromStatEffect(final MapleStatEffect effect, final long startTime) {
        final List<MapleBuffStat> bstats = new ArrayList<MapleBuffStat>();
        final Map<MapleBuffStat, MapleBuffStatValueHolder> allBuffs = new EnumMap<MapleBuffStat, MapleBuffStatValueHolder>((Map<MapleBuffStat, ? extends MapleBuffStatValueHolder>)this.effects);
        for (final Entry<MapleBuffStat, MapleBuffStatValueHolder> stateffect : allBuffs.entrySet()) {
            final MapleBuffStatValueHolder mbsvh = (MapleBuffStatValueHolder)stateffect.getValue();
            if (mbsvh.effect.sameSource(effect) && (startTime == -1L || startTime == mbsvh.startTime)) {
                bstats.add(stateffect.getKey());
                this.skillID.put(Integer.valueOf(effect.getSourceId()), mbsvh);
            }
        }
        return bstats;
    }
    
    private boolean deregisterBuffStats(final List<MapleBuffStat> stats) {
        boolean clonez = false;
        final List<MapleBuffStatValueHolder> effectsToCancel = new ArrayList<MapleBuffStatValueHolder>(stats.size());
        for (final MapleBuffStat stat : stats) {
            final MapleBuffStatValueHolder mbsvh = (MapleBuffStatValueHolder)this.effects.remove((Object)stat);
            if (mbsvh != null) {
                this.skillID.remove((Object)Integer.valueOf(mbsvh.effect.getSourceId()));
                boolean addMbsvh = true;
                for (final MapleBuffStatValueHolder contained : effectsToCancel) {
                    if (mbsvh.startTime == contained.startTime && contained.effect == mbsvh.effect) {
                        addMbsvh = false;
                    }
                }
                if (addMbsvh) {
                    effectsToCancel.add(mbsvh);
                }
                if (stat == MapleBuffStat.SUMMON || stat == MapleBuffStat.PUPPET) {
                    final int summonId = mbsvh.effect.getSourceId();
                    final MapleSummon summon = (MapleSummon)this.summons.get((Object)Integer.valueOf(summonId));
                    if (summon == null) {
                        continue;
                    }
                    this.map.broadcastMessage(MaplePacketCreator.removeSummon(summon, true));
                    this.map.removeMapObject((MapleMapObject)summon);
                    this.removeVisibleMapObject((MapleMapObject)summon);
                    this.summons.remove((Object)Integer.valueOf(summonId));
                    if (summon.getSkill() != 1321007) {
                        continue;
                    }
                    if (this.beholderHealingSchedule != null) {
                        this.beholderHealingSchedule.cancel(false);
                        this.beholderHealingSchedule = null;
                    }
                    if (this.beholderBuffSchedule == null) {
                        continue;
                    }
                    this.beholderBuffSchedule.cancel(false);
                    this.beholderBuffSchedule = null;
                }
                else if (stat == MapleBuffStat.DRAGONBLOOD) {
                    if (this.dragonBloodSchedule == null) {
                        continue;
                    }
                    this.dragonBloodSchedule.cancel(false);
                    this.dragonBloodSchedule = null;
                }
                else if (stat == MapleBuffStat.RECOVERY) {
                    this.lastRecoveryTime = 0L;
                }
                else {
                    if (stat != MapleBuffStat.ILLUSION) {
                        continue;
                    }
                    this.disposeClones();
                    clonez = true;
                }
            }
        }
        for (final MapleBuffStatValueHolder cancelEffectCancelTasks : effectsToCancel) {
            if (this.getBuffStatsFromStatEffect(cancelEffectCancelTasks.effect, cancelEffectCancelTasks.startTime).isEmpty() && cancelEffectCancelTasks.schedule != null) {
                cancelEffectCancelTasks.schedule.cancel(false);
            }
        }
        return clonez;
    }
    
    public void cancelEffect(final MapleStatEffect effect, final boolean overwrite, final long startTime) {
        if (this != null && effect != null) {
            this.cancelEffect(effect, overwrite, startTime, effect.getStatups());
        }
    }
    
    public void cancelEffect(final MapleStatEffect effect, final boolean overwrite, final long startTime, final List<Pair<MapleBuffStat, Integer>> statups) {
        List<MapleBuffStat> buffstats;
        if (!overwrite) {
            buffstats = this.getBuffStats(effect, startTime);
        }
        else {
            buffstats = new ArrayList<MapleBuffStat>(statups.size());
            for (final Pair<MapleBuffStat, Integer> statup : statups) {
                buffstats.add(statup.getLeft());
            }
        }
        if (buffstats.size() <= 0) {
            return;
        }
        final boolean clonez = this.deregisterBuffStats(buffstats);
        if (effect.isMagicDoor()) {
            if (!this.getDoors().isEmpty()) {
                this.removeDoor();
                this.silentPartyUpdate();
            }
        }
        else if (effect.isMonsterRiding_()) {
            this.getMount().cancelSchedule();
        }
        else if (effect.isAranCombo()) {
            this.combo = 0;
        }
        if (!overwrite) {
            this.cancelPlayerBuffs(buffstats);
            if (this.isGM() && effect.isHide() && this.client.getChannelServer().getPlayerStorage().getCharacterById(this.getId()) != null) {
                this.hidden = false;
                if (this.isGod()) {
                    this.map.broadcastGMMessage(this, MaplePacketCreator.spawnPlayerMapobject(this), false);
                }
                else {
                    this.map.broadcastMessage(this, MaplePacketCreator.spawnPlayerMapobject(this), false);
                }
                for (final MaplePet pet : this.pets) {
                    if (pet.getSummoned()) {
                        this.map.broadcastMessage(this, PetPacket.showPet(this, pet, false, false), false);
                    }
                }
                for (final WeakReference<MapleCharacter> chr : this.clones) {
                    if (chr.get() != null) {
                        this.map.broadcastMessage((MapleCharacter)chr.get(), MaplePacketCreator.spawnPlayerMapobject((MapleCharacter)chr.get()), false);
                    }
                }
            }
        }
        if (!clonez) {
            for (final WeakReference<MapleCharacter> chr : this.clones) {
                if (chr.get() != null) {
                    ((MapleCharacter)chr.get()).cancelEffect(effect, overwrite, startTime);
                }
            }
        }
        if (this.getDebugMessage()) {
            this.dropMessage("取消技能 - " + effect.getName() + "(" + effect.getSourceId() + ")");
        }
    }
    
    public void cancelBuffStats(final MapleBuffStat... stat) {
        final List<MapleBuffStat> buffStatList = Arrays.asList(stat);
        this.deregisterBuffStats(buffStatList);
        this.cancelPlayerBuffs(buffStatList);
    }
    
    public void cancelEffectFromBuffStat(final MapleBuffStat stat) {
        if (this.effects.get((Object)stat) != null) {
            this.cancelEffect(((MapleBuffStatValueHolder)this.effects.get((Object)stat)).effect, false, -1L);
        }
    }
    
    private void cancelPlayerBuffs(final List<MapleBuffStat> buffstats) {
        final MapleBuffStatValueHolder mbsvh = (MapleBuffStatValueHolder)this.effects.remove((Object)buffstats);
        final boolean write = this.client.getChannelServer().getPlayerStorage().getCharacterById(this.getId()) != null;
        if (buffstats.contains((Object)MapleBuffStat.HOMING_BEACON)) {
            if (write) {
                this.client.sendPacket(MaplePacketCreator.cancelHoming());
            }
        }
        else {
            if (write) {
                this.stats.recalcLocalStats();
            }
            this.client.sendPacket(MaplePacketCreator.cancelBuff(buffstats));
            this.map.broadcastMessage(this, MaplePacketCreator.cancelForeignBuff(this.getId(), buffstats), false);
        }
    }
    
    public void dispel() {
        if (!this.isHidden()) {
            final LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>((Collection<? extends MapleBuffStatValueHolder>)this.effects.values());
            for (final MapleBuffStatValueHolder mbsvh : allBuffs) {
                if (mbsvh.effect.isSkill() && mbsvh.schedule != null && !mbsvh.effect.isMorph() && !mbsvh.effect.isEnergyCharge()) {
                    this.cancelEffect(mbsvh.effect, false, mbsvh.startTime);
                }
            }
        }
    }
    
    public void dispelSkill(final int skillid) {
        final LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>((Collection<? extends MapleBuffStatValueHolder>)this.effects.values());
        for (final MapleBuffStatValueHolder mbsvh : allBuffs) {
            if (skillid == 0) {
                if (mbsvh.effect.isSkill() && (mbsvh.effect.getSourceId() == 4331003 || mbsvh.effect.getSourceId() == 4331002 || mbsvh.effect.getSourceId() == 4341002 || mbsvh.effect.getSourceId() == 22131001 || mbsvh.effect.getSourceId() == 1321007 || mbsvh.effect.getSourceId() == 2121005 || mbsvh.effect.getSourceId() == 2221005 || mbsvh.effect.getSourceId() == 2311006 || mbsvh.effect.getSourceId() == 2321003 || mbsvh.effect.getSourceId() == 3111002 || mbsvh.effect.getSourceId() == 3111005 || mbsvh.effect.getSourceId() == 3211002 || mbsvh.effect.getSourceId() == 3211005 || mbsvh.effect.getSourceId() == 4111002)) {
                    this.cancelEffect(mbsvh.effect, false, mbsvh.startTime);
                    break;
                }
                continue;
            }
            else {
                if (mbsvh.effect.isSkill() && mbsvh.effect.getSourceId() == skillid) {
                    this.cancelEffect(mbsvh.effect, false, mbsvh.startTime);
                    break;
                }
                continue;
            }
        }
    }
    
    public void dispelBuff(final int skillid) {
        final LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>((Collection<? extends MapleBuffStatValueHolder>)this.effects.values());
        for (final MapleBuffStatValueHolder mbsvh : allBuffs) {
            if (mbsvh.effect.getSourceId() == skillid) {
                this.cancelEffect(mbsvh.effect, false, mbsvh.startTime);
                break;
            }
        }
    }
    
    public void cancelAllBuffs_() {
        this.effects.clear();
    }
    
    public void cancelAllSkillID() {
        this.skillID.clear();
    }
    
    public void cancelAllBuffs() {
        final LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>((Collection<? extends MapleBuffStatValueHolder>)this.effects.values());
        for (final MapleBuffStatValueHolder mbsvh : allBuffs) {
            this.cancelEffect(mbsvh.effect, false, mbsvh.startTime);
        }
    }
    
    public void cancelMorphs() {
        final LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>((Collection<? extends MapleBuffStatValueHolder>)this.effects.values());
        for (final MapleBuffStatValueHolder mbsvh : allBuffs) {
            switch (mbsvh.effect.getSourceId()) {
                case 5111005:
                case 5121003:
                case 13111005:
                case 15111002: {}
                default: {
                    if (!mbsvh.effect.isMorph()) {
                        continue;
                    }
                    this.cancelEffect(mbsvh.effect, false, mbsvh.startTime);
                    continue;
                }
            }
        }
    }
    
    public int getMorphState() {
        final LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>((Collection<? extends MapleBuffStatValueHolder>)this.effects.values());
        for (final MapleBuffStatValueHolder mbsvh : allBuffs) {
            if (mbsvh.effect.isMorph()) {
                return mbsvh.effect.getSourceId();
            }
        }
        return -1;
    }
    
    public void silentGiveBuffs(final List<PlayerBuffValueHolder> buffs) {
        if (buffs == null) {
            return;
        }
        for (final PlayerBuffValueHolder mbsvh : buffs) {
            mbsvh.effect.silentApplyBuff(this, mbsvh.startTime, mbsvh.localDuration, mbsvh.statup, mbsvh.cid);
        }
    }
    
    public List<PlayerBuffValueHolder> getAllBuffs() {
        final List<PlayerBuffValueHolder> ret = new ArrayList<PlayerBuffValueHolder>();
        final Map<Pair<Integer, Byte>, Integer> alreadyDone = new HashMap<Pair<Integer, Byte>, Integer>();
        final LinkedList<Entry<MapleBuffStat, MapleBuffStatValueHolder>> allBuffs = new LinkedList<Entry<MapleBuffStat, MapleBuffStatValueHolder>>((Collection<? extends Entry<MapleBuffStat, MapleBuffStatValueHolder>>)this.effects.entrySet());
        for (final Entry<MapleBuffStat, MapleBuffStatValueHolder> mbsvh : allBuffs) {
            final Pair<Integer, Byte> key = new Pair<Integer, Byte>(Integer.valueOf(((MapleBuffStatValueHolder)mbsvh.getValue()).effect.getSourceId()), Byte.valueOf(((MapleBuffStatValueHolder)mbsvh.getValue()).effect.getLevel()));
            if (alreadyDone.containsKey((Object)key)) {
                ((PlayerBuffValueHolder)ret.get((int)Integer.valueOf(alreadyDone.get((Object)key)))).statup.add(new Pair<MapleBuffStat, Integer>(mbsvh.getKey(), Integer.valueOf(((MapleBuffStatValueHolder)mbsvh.getValue()).value)));
            }
            else {
                alreadyDone.put(key, Integer.valueOf(ret.size()));
                final ArrayList<Pair<MapleBuffStat, Integer>> list = new ArrayList<Pair<MapleBuffStat, Integer>>();
                list.add(new Pair<MapleBuffStat, Integer>(mbsvh.getKey(), Integer.valueOf(((MapleBuffStatValueHolder)mbsvh.getValue()).value)));
                ret.add(new PlayerBuffValueHolder(((MapleBuffStatValueHolder)mbsvh.getValue()).startTime, ((MapleBuffStatValueHolder)mbsvh.getValue()).effect, (List<Pair<MapleBuffStat, Integer>>)list, ((MapleBuffStatValueHolder)mbsvh.getValue()).localDuration, ((MapleBuffStatValueHolder)mbsvh.getValue()).cid));
            }
        }
        return ret;
    }
    
    public void cancelMagicDoor() {
        final LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>((Collection<? extends MapleBuffStatValueHolder>)this.effects.values());
        for (final MapleBuffStatValueHolder mbsvh : allBuffs) {
            if (mbsvh.effect.isMagicDoor()) {
                this.cancelEffect(mbsvh.effect, false, mbsvh.startTime);
                break;
            }
        }
    }
    
    public int getSkillLevel2(final int skillid) {
        return this.getSkillLevel2(SkillFactory.getSkill(skillid));
    }
    
    public int getSkillLevel(final int skillid) {
        return this.getSkillLevel(SkillFactory.getSkill(skillid));
    }
    
    public final void handleEnergyCharge(final int skillid, final int targets) {
        final ISkill echskill = SkillFactory.getSkill(skillid);
        final byte skilllevel = this.getSkillLevel(echskill);
        if (skilllevel > 0) {
            final MapleStatEffect echeff = echskill.getEffect((int)skilllevel);
            if (targets > 0) {
                if (this.getBuffedValue(MapleBuffStat.ENERGY_CHARGE) == null) {
                    echeff.applyEnergyBuff(this, true);
                }
                else {
                    Integer energyLevel = this.getBuffedValue(MapleBuffStat.ENERGY_CHARGE);
                    if ((int)energyLevel < 10000) {
                        energyLevel = Integer.valueOf((int)energyLevel + echeff.getX() * targets);
                        this.client.sendPacket(MaplePacketCreator.showOwnBuffEffect(skillid, 2));
                        this.map.broadcastMessage(this, MaplePacketCreator.showBuffeffect(this.id, skillid, 2), false);
                        if ((int)energyLevel >= 10000) {
                            energyLevel = Integer.valueOf(10000);
                        }
                        this.client.sendPacket(MaplePacketCreator.giveEnergyChargeTest((int)energyLevel, echeff.getDuration() / 1000));
                        this.setBuffedValue(MapleBuffStat.ENERGY_CHARGE, (int)energyLevel);
                    }
                    else if ((int)energyLevel == 10000) {
                        echeff.applyEnergyBuff(this, false);
                        this.setBuffedValue(MapleBuffStat.ENERGY_CHARGE, 10001);
                    }
                }
            }
        }
    }
    
    public final void handleBattleshipHP(final int damage) {
        if (this.isActiveBuffedValue(5221006)) {
            this.battleshipHP -= damage;
            if (this.battleshipHP <= 0) {
                this.battleshipHP = 0;
                final MapleStatEffect effect = this.getStatForBuff(MapleBuffStat.MONSTER_RIDING);
                this.client.sendPacket(MaplePacketCreator.skillCooldown(5221006, effect.getCooldown()));
                this.addCooldown(5221006, System.currentTimeMillis(), (long)(effect.getCooldown() * 1000));
                this.dispelSkill(5221006);
            }
        }
    }
    
    public final void handleOrbgain() {
        final int orbcount = (int)this.getBuffedValue(MapleBuffStat.COMBO);
        ISkill theCombol = null;
        ISkill advcombo = null;
        switch (this.getJob()) {
            case 1110:
            case 1111:
            case 1112: {
                theCombol = SkillFactory.getSkill(11111001);
                advcombo = SkillFactory.getSkill(11110005);
                break;
            }
            default: {
                theCombol = SkillFactory.getSkill(1111002);
                advcombo = SkillFactory.getSkill(1120003);
                break;
            }
        }
        final int advComboSkillLevel = this.getSkillLevel(advcombo);
        MapleStatEffect ceffect;
        if (advComboSkillLevel > 0) {
            ceffect = advcombo.getEffect(advComboSkillLevel);
        }
        else {
            if (this.getSkillLevel(theCombol) <= 0) {
                return;
            }
            ceffect = theCombol.getEffect((int)this.getSkillLevel(theCombol));
        }
        if (orbcount < ceffect.getX() + 1) {
            int neworbcount = orbcount + 1;
            if (advComboSkillLevel > 0 && ceffect.makeChanceResult() && neworbcount < ceffect.getX() + 1) {
                ++neworbcount;
            }
            final List<Pair<MapleBuffStat, Integer>> stat = Collections.singletonList(new Pair<MapleBuffStat, Integer>(MapleBuffStat.COMBO, Integer.valueOf(neworbcount)));
            this.setBuffedValue(MapleBuffStat.COMBO, neworbcount);
            int duration = ceffect.getDuration();
            duration += (int)((long)this.getBuffedStarttime(MapleBuffStat.COMBO) - System.currentTimeMillis());
            this.client.sendPacket(MaplePacketCreator.giveBuff(theCombol.getId(), duration, stat, ceffect));
            this.map.broadcastMessage(this, MaplePacketCreator.giveForeignBuff(this.getId(), stat, ceffect), false);
        }
    }
    
    public void handleOrbconsume() {
        ISkill theCombol = null;
        switch (this.getJob()) {
            case 1110:
            case 1111:
            case 1112: {
                theCombol = SkillFactory.getSkill(11111001);
                break;
            }
            default: {
                theCombol = SkillFactory.getSkill(1111002);
                break;
            }
        }
        if (this.getSkillLevel(theCombol) <= 0) {
            return;
        }
        final MapleStatEffect ceffect = this.getStatForBuff(MapleBuffStat.COMBO);
        if (ceffect == null) {
            return;
        }
        final List<Pair<MapleBuffStat, Integer>> stat = Collections.singletonList(new Pair<MapleBuffStat, Integer>(MapleBuffStat.COMBO, Integer.valueOf(1)));
        this.setBuffedValue(MapleBuffStat.COMBO, 1);
        int duration = ceffect.getDuration();
        duration += (int)((long)this.getBuffedStarttime(MapleBuffStat.COMBO) - System.currentTimeMillis());
        this.client.sendPacket(MaplePacketCreator.giveBuff(theCombol.getId(), duration, stat, ceffect));
        this.map.broadcastMessage(this, MaplePacketCreator.giveForeignBuff(this.getId(), stat, ceffect), false);
    }
    
    public void silentEnforceMaxHpMp() {
        this.stats.setMp((int)this.stats.getMp());
        this.stats.setHp((int)this.stats.getHp(), true);
    }
    
    public void enforceMaxHpMp() {
        final Map<MapleStat, Integer> statups = new EnumMap<MapleStat, Integer>(MapleStat.class);
        if (this.stats.getMp() > this.stats.getCurrentMaxMp()) {
            this.stats.setMp((int)this.stats.getMp());
            statups.put(MapleStat.MP, Integer.valueOf((int)this.stats.getMp()));
        }
        if (this.stats.getHp() > this.stats.getCurrentMaxHp()) {
            this.stats.setHp((int)this.stats.getHp());
            statups.put(MapleStat.HP, Integer.valueOf((int)this.stats.getHp()));
        }
        if (statups.size() > 0) {
            this.client.sendPacket(MaplePacketCreator.updatePlayerStats(statups, this));
        }
    }
    
    public MapleMap getMap() {
        return this.map;
    }
    
    public MonsterBook getMonsterBook() {
        return this.monsterbook;
    }
    
    public void setMap(final MapleMap newmap) {
        this.map = newmap;
    }
    
    public void setMap(final int PmapId) {
        this.mapid = PmapId;
    }
    
    public int getMapId() {
        if (this.map != null) {
            return this.map.getId();
        }
        return this.mapid;
    }
    
    public byte getInitialSpawnpoint() {
        return this.initialSpawnPoint;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getguildid() {
        return this.guildid;
    }
    
    public String getName() {
        return this.name;
    }
    
    public final boolean canHold(final int itemid) {
        return this.getInventory(GameConstants.getInventoryType(itemid)).getNextFreeSlot() > -1;
    }
    
    public final String getBlessOfFairyOrigin() {
        return this.BlessOfFairy_Origin;
    }
    
    public final short getLevel() {
        if (this.level < 1) {
            this.level = 1;
        }
        return this.level;
    }
    
    public final short getFame() {
        return this.fame;
    }
    
    public final int getDojo() {
        return this.dojo;
    }
    
    public final int getDojoRecord() {
        return this.dojoRecord;
    }
    
    public final int getFallCounter() {
        return this.fallcounter;
    }
    
    public final MapleClient getClient() {
        return this.client;
    }
    
    public final void setClient(final MapleClient client) {
        this.client = client;
    }
    
    public int getExp() {
        return this.exp;
    }
    
    public short getRemainingAp() {
        return this.remainingAp;
    }
    
    public int getRemainingSp() {
        return this.remainingSp[GameConstants.getSkillBook((int)this.job)];
    }
    
    public int getRemainingSp(final int skillbook) {
        return this.remainingSp[skillbook];
    }
    
    public int[] getRemainingSps() {
        return this.remainingSp;
    }
    
    public int getRemainingSpSize() {
        int ret = 0;
        for (int i = 0; i < this.remainingSp.length; ++i) {
            if (this.remainingSp[i] > 0) {
                ++ret;
            }
        }
        return ret;
    }
    
    public short getHpMpApUsed() {
        return this.hpmpApUsed;
    }
    
    public boolean isHidden() {
        return this.hidden;
    }
    
    public void setHpMpApUsed(final short hpApUsed) {
        this.hpmpApUsed = hpApUsed;
    }
    
    public byte getSkinColor() {
        return this.skinColor;
    }
    
    public void setSkinColor(final byte skinColor) {
        this.skinColor = skinColor;
    }
    
    public short getJob() {
        return this.job;
    }
    
    public byte getGender() {
        return this.gender;
    }
    
    public int getHair() {
        return this.hair;
    }
    
    public int getFace() {
        return this.face;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setExp(final int exp) {
        this.exp = exp;
    }
    
    public void setHair(final int hair) {
        this.hair = hair;
    }
    
    public void setFace(final int face) {
        this.face = face;
    }
    
    public void setFame(final short fame) {
        this.fame = fame;
    }
    
    public void setDojo(final int dojo) {
        this.dojo = dojo;
    }
    
    public void setDojoRecord(final boolean reset) {
        if (reset) {
            this.dojo = 0;
            this.dojoRecord = 0;
        }
        else {
            ++this.dojoRecord;
        }
    }
    
    public void setFallCounter(final int fallcounter) {
        this.fallcounter = fallcounter;
    }
    
    public Point getOldPosition() {
        return this.old;
    }
    
    public void setOldPosition(final Point x) {
        this.old = x;
    }
    
    public void setRemainingAp(final short remainingAp) {
        this.remainingAp = remainingAp;
    }
    
    public void setRemainingSp(final int remainingSp) {
        this.remainingSp[GameConstants.getSkillBook((int)this.job)] = remainingSp;
    }
    
    public void setRemainingSp(final int remainingSp, final int skillbook) {
        this.remainingSp[skillbook] = remainingSp;
    }
    
    public void setGender(final byte gender) {
        this.gender = gender;
    }
    
    public void setInvincible(final boolean invinc) {
        this.invincible = invinc;
    }
    
    public boolean isInvincible() {
        return this.invincible;
    }
    
    public CheatTracker getCheatTracker() {
        return this.anticheat;
    }
    
    public MapleLieDetector getAntiMacro() {
        return this.antiMacro;
    }
    
    public void startLieDetector(final boolean isItem) {
        if ((this.getMapId() < 910000000 || this.getMapId() > 910000022) && this.getMapId() != 800040410 && !GameConstants.isFishingMap(this.getMapId()) && this.getMap().getReturnMapId() != this.getMapId()) {
            if (this.getAntiMacro().isPassed()) {
                this.getAntiMacro().setPassed(false);
            }
            if (!this.getAntiMacro().inProgress()) {
                this.getAntiMacro().startLieDetector(this.getName(), isItem, false);
            }
        }
    }
    
    public BuddyList getBuddylist() {
        return this.buddylist;
    }
    
    public void addFame(final int famechange) {
        this.fame += (short)famechange;
        this.updateFame();
    }
    
    public void changeMap(final int mapid) {
        final MapleMap target = this.client.getChannelServer().getMapFactory().getMap(mapid);
        this.changeMap(target, target.getPortal(0));
    }
    
    public void changeMap(final int map, final int portal) {
        final MapleMap warpMap = this.client.getChannelServer().getMapFactory().getMap(map);
        this.changeMap(warpMap, warpMap.getPortal(portal));
    }
    
    public void changeMapBanish(final int mapid, final String portal, final String msg) {
        this.dropMessage(5, msg);
        final MapleMap target = this.client.getChannelServer().getMapFactory().getMap(mapid);
        this.changeMap(target, target.getPortal(portal));
    }
    
    public void changeMap(final MapleMap to, final Point pos) {
        this.changeMapInternal(to, pos, MaplePacketCreator.getWarpToMap(to, 129, this), null);
    }
    
    public void changeMap(final MapleMap to, final MaplePortal pto) {
        this.changeMapInternal(to, pto.getPosition(), MaplePacketCreator.getWarpToMap(to, pto.getId(), this), null);
    }
    
    public void changeMapPortal(final MapleMap to, final MaplePortal pto) {
        this.changeMapInternal(to, pto.getPosition(), MaplePacketCreator.getWarpToMap(to, pto.getId(), this), pto);
    }
    
    private void changeMapInternal(final MapleMap to, final Point pos, final byte[] warpPacket, final MaplePortal pto) {
        if (to == null) {
            return;
        }
        if (pto != null && GameConstants.isNotTo(to.getId(), pto.getName())) {
            if (this.getParty() == null || this.getParty().getMembers().size() == 1) {
                this.changeMap(211060000);
                return;
            }
            final int cMap = this.getMapId();
            for (final MaplePartyCharacter chr : this.getParty().getMembers()) {
                final MapleCharacter curChar = this.getClient().getChannelServer().getPlayerStorage().getCharacterById(chr.getId());
                if (curChar != null && (curChar.getMapId() == cMap || curChar.getEventInstance() == this.getEventInstance())) {
                    curChar.changeMap(211060000);
                }
            }
        }
        else {
            if (this.getAntiMacro().inProgress()) {
                this.dropMessage(5, "被使用測謊儀時無法操作。");
                this.client.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            if (to.getId() != 105100300) {
                this.dispelBuff(2022536);
                this.dispelBuff(2022537);
            }
            if (to.getId() == 180000000 && this.getGMLevel() < 4) {
                this.changeMap(100000000);
                return;
            }
            final int nowmapid = this.map.getId();
            if (this.eventInstance != null) {
                this.eventInstance.changedMap(this, to.getId());
            }
            if (this.getTrade() != null) {
                if (this.getTrade().getPartner() != null) {
                    final MapleTrade local = this.getTrade();
                    final MapleTrade partners = local.getPartner();
                    if (local.isLocked() && partners.isLocked()) {
                        this.client.getSession().write((Object)MaplePacketCreator.enableActions());
                    }
                    else {
                        MapleTrade.cancelTrade(this.getTrade(), this.getClient());
                    }
                }
                else {
                    MapleTrade.cancelTrade(this.getTrade(), this.client);
                }
            }
            final boolean pyramid = this.pyramidSubway != null;
            if (this.map.getId() == nowmapid) {
                this.client.sendPacket(warpPacket);
                this.map.removePlayer(this);
                if (!this.isClone() && this.client.getChannelServer().getPlayerStorage().getCharacterById(this.getId()) != null) {
                    this.map = to;
                    this.setPosition(pos);
                    to.addPlayer(this);
                    this.stats.relocHeal();
                }
            }
            if (pyramid && this.pyramidSubway != null) {
                this.pyramidSubway.onChangeMap(this, to.getId());
            }
        }
    }
    
    public void leaveMap() {
        this.visibleMapObjectsLock.writeLock().lock();
        try {
            for (final MapleMonster mons : this.controlled) {
                if (mons != null) {
                    mons.setController(null);
                    mons.setControllerHasAggro(false);
                    mons.setControllerKnowsAboutAggro(false);
                    this.map.updateMonsterController(mons);
                }
            }
            this.controlled.clear();
            this.visibleMapObjects.clear();
        }
        finally {
            this.visibleMapObjectsLock.writeLock().unlock();
        }
        if (this.chair != 0) {
            this.cancelFishingTask();
            this.chair = 0;
        }
        if (this.getTrade() != null) {
            if (this.getTrade().getPartner() != null) {
                final MapleTrade local = this.getTrade();
                final MapleTrade partners = local.getPartner();
                if (local.isLocked() && partners.isLocked()) {
                    this.client.getSession().write((Object)MaplePacketCreator.enableActions());
                }
                else {
                    MapleTrade.cancelTrade(this.getTrade(), this.getClient());
                }
            }
            else {
                MapleTrade.cancelTrade(this.getTrade(), this.client);
            }
        }
        this.cancelMapTimeLimitTask();
    }
    
    public void changeJob(final int newJob) {
        try {
            boolean normal = true;
            switch (this.getLevel()) {
                case 8:
                case 10:
                case 30:
                case 70:
                case 120: {
                    normal = false;
                    break;
                }
            }
            if (normal) {
                this.dropMessage("由於您的轉職等級非普通轉職等級，技能點異常後果自負。");
            }
            this.job = (short)newJob;
            if (newJob == 200 && this.level > 8 && this.getOneTimeLog("九級補點數") < 1) {
                this.setOneTimeLog("九級補點數");
                final int[] remainingSp = this.remainingSp;
                final int skillBook = GameConstants.getSkillBook((int)this.job);
                remainingSp[skillBook] += 3;
            }
            if (newJob != 0 && newJob != 1000 && newJob != 2000 && newJob != 2001) {
                final int[] remainingSp2 = this.remainingSp;
                final int skillBook2 = GameConstants.getSkillBook(newJob);
                ++remainingSp2[skillBook2];
                if (newJob % 10 >= 2) {
                    final int[] remainingSp3 = this.remainingSp;
                    final int skillBook3 = GameConstants.getSkillBook(newJob);
                    remainingSp3[skillBook3] += 2;
                }
            }
            if (newJob > 0 && !this.isGM()) {
                this.resetStatsByJob(true);
            }
            this.client.sendPacket(MaplePacketCreator.updateSp(this, false));
            this.updateSingleStat(MapleStat.JOB, newJob);
            int maxhp = this.stats.getMaxHp();
            int maxmp = this.stats.getMaxMp();
            switch (this.job) {
                case 100:
                case 1100:
                case 2100: {
                    maxhp += Randomizer.rand(200, 250);
                    break;
                }
                case 200: {
                    maxmp += Randomizer.rand(100, 150);
                    break;
                }
                case 300:
                case 400:
                case 500: {
                    maxhp += Randomizer.rand(100, 150);
                    maxmp += Randomizer.rand(25, 50);
                    break;
                }
                case 110: {
                    maxhp += Randomizer.rand(300, 350);
                    break;
                }
                case 120:
                case 130:
                case 510:
                case 512:
                case 1110:
                case 2110: {
                    maxhp += Randomizer.rand(300, 350);
                    break;
                }
                case 210:
                case 220:
                case 230: {
                    maxmp += Randomizer.rand(400, 450);
                    break;
                }
                case 310:
                case 312:
                case 320:
                case 322:
                case 410:
                case 412:
                case 420:
                case 422:
                case 520:
                case 522:
                case 1310:
                case 1410: {
                    maxhp += Randomizer.rand(300, 350);
                    maxhp += Randomizer.rand(150, 200);
                    break;
                }
                case 800:
                case 900: {
                    maxhp += 30000;
                    maxhp += 30000;
                    break;
                }
            }
            if (maxhp >= 30000) {
                maxhp = 30000;
            }
            if (maxmp >= 30000) {
                maxmp = 30000;
            }
            this.stats.setMaxHp((short)maxhp);
            this.stats.setMaxMp((short)maxmp);
            this.stats.setHp((int)(short)maxhp);
            this.stats.setMp((int)(short)maxmp);
            final Map<MapleStat, Integer> statup = new EnumMap<MapleStat, Integer>(MapleStat.class);
            statup.put(MapleStat.MAXHP, Integer.valueOf(maxhp));
            statup.put(MapleStat.MAXMP, Integer.valueOf(maxmp));
            statup.put(MapleStat.HP, Integer.valueOf(maxhp));
            statup.put(MapleStat.MP, Integer.valueOf(maxmp));
            this.stats.recalcLocalStats();
            this.client.sendPacket(MaplePacketCreator.updatePlayerStats(statup, this));
            this.map.broadcastMessage(this, MaplePacketCreator.showForeignEffect(this.getId(), 9), false);
            this.silentPartyUpdate();
            this.guildUpdate();
            this.familyUpdate();
            this.baseSkills();
        }
        catch (Exception e) {
            FilePrinter.printError("MapleCharacter.txt", (Throwable)e);
        }
    }
    
    public void baseSkills() {
        if (GameConstants.getJobNumber((int)this.job) >= 3) {
            final List<Integer> baseSkills = SkillFactory.getSkillsByJob((int)this.job);
            if (baseSkills != null) {
                final Iterator<Integer> iterator = baseSkills.iterator();
                while (iterator.hasNext()) {
                    final int i = (int)Integer.valueOf(iterator.next());
                    final ISkill skil = SkillFactory.getSkill(i);
                    if (skil != null && !skil.isInvisible() && skil.isFourthJob() && this.getSkillLevel(skil) <= 0 && this.getMasterLevel(skil) <= 0 && skil.getMasterLevel() > 0) {
                        this.changeSkillLevel(skil, (byte)0, (byte)skil.getMasterLevel());
                    }
                }
            }
        }
    }
    
    public void gainAp(final short ap) {
        this.remainingAp += ap;
        this.updateSingleStat(MapleStat.AVAILABLEAP, (int)this.remainingAp);
    }
    
    public void gainSP(final int sp) {
        final int[] remainingSp = this.remainingSp;
        final int skillBook = GameConstants.getSkillBook((int)this.job);
        remainingSp[skillBook] += sp;
        this.client.sendPacket(MaplePacketCreator.updateSp(this, false));
        this.client.sendPacket(UIPacket.getSPMsg((byte)sp, this.job));
    }
    
    public void gainSP(final int sp, final int skillbook) {
        final int[] remainingSp = this.remainingSp;
        remainingSp[skillbook] += sp;
        this.client.sendPacket(MaplePacketCreator.updateSp(this, false));
        this.client.sendPacket(UIPacket.getSPMsg((byte)sp, this.job));
    }
    
    public void resetAPSP() {
        for (int i = 0; i < this.remainingSp.length; ++i) {
            this.remainingSp[i] = 0;
        }
        this.client.sendPacket(MaplePacketCreator.updateSp(this, false));
        this.gainAp((short)(-this.remainingAp));
    }
    
    public void changeSkillLevel(final ISkill skill, final byte newLevel, final byte newMasterlevel) {
        if (skill == null) {
            return;
        }
        this.changeSkillLevel(skill, newLevel, newMasterlevel, skill.isTimeLimited() ? (System.currentTimeMillis() + 2592000000L) : -1L);
    }
    
    public void changeSkillLevel(final ISkill skill, final byte newLevel, final byte newMasterlevel, final long expiration) {
        if (skill == null || (!GameConstants.isApplicableSkill(skill.getId()) && !GameConstants.isApplicableSkill_(skill.getId()))) {
            return;
        }
        this.client.sendPacket(MaplePacketCreator.updateSkill(skill.getId(), (int)newLevel, (int)newMasterlevel, expiration));
        if (newLevel == 0 && newMasterlevel == 0) {
            if (!this.skills.containsKey((Object)skill)) {
                return;
            }
            this.skills.remove((Object)skill);
        }
        else {
            this.skills.put(skill, new SkillEntry(newLevel, newMasterlevel, expiration));
        }
        if (GameConstants.isRecoveryIncSkill(skill.getId())) {
            this.stats.relocHeal();
        }
        else if (GameConstants.isElementAmpSkill(skill.getId())) {
            this.stats.recalcLocalStats();
        }
    }
    
    public void changeSkillLevel_Skip(final ISkill skill, final byte newLevel, final byte newMasterlevel) {
        if (skill == null) {
            return;
        }
        this.client.sendPacket(MaplePacketCreator.updateSkill(skill.getId(), (int)newLevel, (int)newMasterlevel, -1L));
        if (newLevel == 0 && newMasterlevel == 0) {
            if (this.skills.containsKey((Object)skill)) {
                this.skills.remove((Object)skill);
            }
        }
        else {
            this.skills.put(skill, new SkillEntry(newLevel, newMasterlevel, -1L));
        }
    }
    
    public void playerDead() {
        if (this.getMapId() != 109020001) {
            final MapleStatEffect statss = this.getStatForBuff(MapleBuffStat.SOUL_STONE);
            if (statss != null) {
                this.dropMessage(5, "你已經透過靈魂之石復活了。");
                this.getStat().setHp(this.getStat().getMaxHp() / 100 * statss.getX());
                this.setStance(0);
                this.changeMap(this.getMap(), this.getMap().getPortal(0));
                return;
            }
        }
        if (this.getEventInstance() != null) {
            this.getEventInstance().playerKilled(this);
        }
        this.client.getSession().write((Object)MaplePacketCreator.enableActions());
        this.dispelSkill(0);
        this.cancelEffectFromBuffStat(MapleBuffStat.MORPH);
        this.cancelEffectFromBuffStat(MapleBuffStat.MONSTER_RIDING);
        this.cancelEffectFromBuffStat(MapleBuffStat.SUMMON);
        this.cancelEffectFromBuffStat(MapleBuffStat.PUPPET);
        this.checkFollow();
        if (this.getMapId() != 109020001) {
            if (this.job != 0 && this.job != 1000 && this.job != 2000 && this.job != 2001 && this.job != 3000) {
                int charms = this.getItemQuantity(5130000, false);
                if (charms > 0) {
                    MapleInventoryManipulator.removeById(this.client, MapleInventoryType.CASH, 5130000, 1, true, false);
                    if (--charms > 255) {
                        charms = 255;
                    }
                    this.client.sendPacket(MTSCSPacket.useCharm((byte)charms, (byte)0));
                }
                else {
                    final int expforlevel = GameConstants.getExpNeededForLevel((int)this.level);
                    float diepercentage;
                    if (this.map.isTown() || FieldLimitType.RegularExpLoss.check(this.map.getFieldLimit())) {
                        diepercentage = 0.01f;
                    }
                    else {
                        float v8;
                        if (this.job / 100 == 3) {
                            v8 = 0.08f;
                        }
                        else {
                            v8 = 0.2f;
                        }
                        diepercentage = (float)((double)(v8 / (float)this.stats.getLuk()) + 0.05);
                    }
                    int v9 = (int)((long)this.exp - (long)((double)expforlevel * (double)diepercentage));
                    if (v9 < 0) {
                        v9 = 0;
                    }
                    this.exp = v9;
                }
            }
            this.updateSingleStat(MapleStat.EXP, this.exp);
            this.client.getSession().write((Object)MaplePacketCreator.enableActions());
            if (!this.stats.checkEquipDurabilitys(this, -100)) {
                this.dropMessage(5, "該装备耐久度已經使用完畢，必須修理才可以繼續使用。");
            }
        }
        if (this.pyramidSubway != null) {
            this.stats.setHp(50);
            this.pyramidSubway.fail(this);
        }
        this.client.getSession().write((Object)MaplePacketCreator.enableActions());
    }
    
    public void updatePartyMemberHP() {
        if (this.party != null) {
            final int channel = this.client.getChannel();
            for (final MaplePartyCharacter partychar : this.party.getMembers()) {
                if (partychar.getMapid() == this.getMapId() && partychar.getChannel() == channel) {
                    final MapleCharacter other = this.client.getChannelServer().getPlayerStorage().getCharacterByName(partychar.getName());
                    if (other == null) {
                        continue;
                    }
                    other.getClient().sendPacket(MaplePacketCreator.updatePartyMemberHP(this.getId(), (int)this.stats.getHp(), (int)this.stats.getCurrentMaxHp()));
                }
            }
        }
    }
    
    public void receivePartyMemberHP() {
        if (this.party == null) {
            return;
        }
        final int channel = this.client.getChannel();
        for (final MaplePartyCharacter partychar : this.party.getMembers()) {
            if (partychar.getMapid() == this.getMapId() && partychar.getChannel() == channel) {
                final MapleCharacter other = this.client.getChannelServer().getPlayerStorage().getCharacterByName(partychar.getName());
                if (other == null) {
                    continue;
                }
                this.client.sendPacket(MaplePacketCreator.updatePartyMemberHP(other.getId(), (int)other.getStat().getHp(), (int)other.getStat().getCurrentMaxHp()));
            }
        }
    }
    
    public void healHP(final int delta) {
        this.healHP(delta, false);
    }
    
    public void healHP(final int delta, final boolean Show) {
        this.addHP(delta);
        if (Show) {
            this.client.sendPacket(MaplePacketCreator.showOwnHpHealed(delta));
            this.getMap().broadcastMessage(this, MaplePacketCreator.showHpHealed(this.getId(), delta), false);
        }
    }
    
    public void healMP(final int delta) {
        this.addMP(delta);
    }
    
    public void addHP(final int delta) {
        if (this.stats.setHp(this.stats.getHp() + delta)) {
            this.updateSingleStat(MapleStat.HP, (int)this.stats.getHp());
        }
    }
    
    public void addMP(final int delta) {
        if (this.stats.setMp(this.stats.getMp() + delta)) {
            this.updateSingleStat(MapleStat.MP, (int)this.stats.getMp());
        }
    }
    
    public void addMPHP(final int hpDiff, final int mpDiff) {
        final Map<MapleStat, Integer> statups = new EnumMap<MapleStat, Integer>(MapleStat.class);
        if (this.stats.setHp(this.stats.getHp() + hpDiff)) {
            statups.put(MapleStat.HP, Integer.valueOf((int)this.stats.getHp()));
        }
        if (this.stats.setMp(this.stats.getMp() + mpDiff)) {
            statups.put(MapleStat.MP, Integer.valueOf((int)this.stats.getMp()));
        }
        if (statups.size() > 0) {
            this.client.sendPacket(MaplePacketCreator.updatePlayerStats(statups, this));
        }
    }
    
    public void updateSingleStat(final MapleStat stat, final int newval) {
        this.updateSingleStat(stat, newval, false);
    }
    
    public void updateSingleStat(final MapleStat stat, final int newval, final boolean itemReaction) {
        if (stat == MapleStat.AVAILABLESP) {
            this.client.sendPacket(MaplePacketCreator.updateSp(this, itemReaction));
            return;
        }
        final Map<MapleStat, Integer> statup = new EnumMap<MapleStat, Integer>(MapleStat.class);
        statup.put(stat, Integer.valueOf(newval));
        this.client.sendPacket(MaplePacketCreator.updatePlayerStats(statup, itemReaction, this));
    }
    
    public void gainExp(final int total, final boolean show, final boolean inChat, final boolean white) {
        try {
            final int prevexp = this.getExp();
            int needed = GameConstants.getExpNeededForLevel((int)this.level);
            final int 骑士团等级上限 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"骑士团等级上限"));
            final int 冒险家等级上限 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"冒险家等级上限"));
            if (total > 0) {
                this.stats.checkEquipLevels(this, total);
            }
            if (this.level >= 冒险家等级上限 || (GameConstants.isKOC((int)this.job) && this.level >= 骑士团等级上限)) {
                if (this.exp + total > needed) {
                    this.setExp(needed);
                }
                else {
                    this.exp += total;
                }
            }
            else {
                boolean leveled = false;
                this.exp += total;
                long oexp = (long)this.exp;
                short olevel = this.level;
                while (oexp >= (long)needed) {
                    this.levelUp();
                    leveled = true;
                    oexp -= (long)needed;
                    ++olevel;
                    needed = GameConstants.getExpNeededForLevel((int)olevel);
                    if (olevel >= 冒险家等级上限 || (GameConstants.isKOC((int)this.job) && olevel >= 骑士团等级上限) || oexp < (long)needed) {
                        break;
                    }
                }
                if (total > 0) {
                    this.familyRep(prevexp, needed, leveled);
                }
            }
            if (total != 0) {
                if (this.exp < 0) {
                    if (total > 0) {
                        this.setExp(needed);
                    }
                    else if (total < 0) {
                        this.setExp(0);
                    }
                }
                this.updateSingleStat(MapleStat.EXP, this.getExp());
                if (show) {
                    this.client.sendPacket(MaplePacketCreator.GainEXP_Others(total, inChat, white));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            FilePrinter.printError("MapleCharacter.txt", (Throwable)e);
        }
    }
    
    public void familyRep(final int prevexp, final int needed, final boolean leveled) {
        if (this.mfc != null) {
            final int onepercent = needed / 100;
            int percentrep = prevexp / onepercent + this.getExp() / onepercent;
            if (leveled) {
                percentrep = 100 - percentrep + this.level / 2;
            }
            if (percentrep > 0) {
                final int sensen = Family.setRep(this.mfc.getFamilyId(), this.mfc.getSeniorId(), percentrep, (int)this.level);
                if (sensen > 0) {
                    Family.setRep(this.mfc.getFamilyId(), sensen, percentrep / 2, (int)this.level);
                }
            }
        }
    }
    
    public boolean isShowInfo() {
        return this.isAdmin();
    }
    
    public boolean isShowErr() {
        return this.isShowInfo();
    }
    
    public void gainExpMonster(int gain, final boolean show, final boolean white, final byte pty, final int Class_Bonus_EXP, final int Equipment_Bonus_EXP, final int Premium_Bonus_EXP) {
        if (this == null) {
            return;
        }
        this.expirationTask(true, false);
        if (this.getExpm() > 1.0) {
            gain = (int)((double)gain * this.getExpm());
        }
        if (this.isVip()) {
            gain = (int)((double)gain * (1.0 + (double)this.getVipExpRate() / 100.0));
        }
        if (this.getStat().equippedRing) {
            if (pty > 1) {
                if (pty > 5) {
                    gain = (int)((double)gain * 1.3);
                }
                else {
                    gain = (int)((double)gain * (1.0 + (0.1 + 0.05 * (double)(pty - 1))));
                }
            }
            else {
                gain = (int)((double)gain * 1.1);
            }
        }
        int H_EXP = 0;
        if (ServerConstants.getENABLE_H_EXP().get() && this.getStat().getExpModH() > 0) {
            H_EXP = gain * this.getStat().getExpModH() / 100;
        }
        int total = gain + Class_Bonus_EXP + Equipment_Bonus_EXP + Premium_Bonus_EXP + H_EXP;
        int partyinc = 0;
        final int prevexp = this.getExp();
        if (pty == 2) {
            partyinc = (int)((double)gain / 100.0 * 50.0);
            total += partyinc;
        }
        else if (pty == 3) {
            partyinc = (int)((double)gain / 100.0 * 40.0);
            total += partyinc;
        }
        else if (pty == 4) {
            partyinc = (int)((double)gain / 100.0 * 35.0);
            total += partyinc;
        }
        else if (pty == 5) {
            partyinc = (int)((double)gain / 100.0 * 30.0);
            total += partyinc;
        }
        else if (pty == 6) {
            partyinc = (int)((double)gain / 100.0 * 20.0);
            total += partyinc;
        }
        if (gain > 0 && total < gain) {
            total = Integer.MAX_VALUE;
        }
        if (total > 0) {
            this.stats.checkEquipLevels(this, total);
        }
        final int 冒险家等级上限 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"冒险家等级上限"));
        final int 骑士团等级上限 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"骑士团等级上限"));
        int needed = GameConstants.getExpNeededForLevel((int)this.level);
        if (this.level >= 冒险家等级上限 || (GameConstants.isKOC((int)this.job) && this.level >= 骑士团等级上限)) {
            if (this.exp + total > needed) {
                this.setExp(needed);
            }
            else {
                this.exp += total;
            }
        }
        else if (this.level >= 冒险家等级上限) {
            this.setExp(0);
        }
        else {
            boolean leveled = false;
            this.exp += total;
            long oexp = (long)this.exp;
            short olevel = this.level;
            while (oexp >= (long)needed) {
                this.levelUp();
                leveled = true;
                oexp -= (long)needed;
                ++olevel;
                needed = GameConstants.getExpNeededForLevel((int)olevel);
                if (olevel >= 冒险家等级上限 || (GameConstants.isKOC((int)this.job) && olevel >= 骑士团等级上限) || oexp < (long)needed) {
                    break;
                }
            }
            if (total > 0) {
                this.familyRep(prevexp, needed, leveled);
            }
        }
        if (gain != 0) {
            if (this.exp < 0) {
                if (gain > 0) {
                    this.setExp(GameConstants.getExpNeededForLevel((int)this.level));
                }
                else if (gain < 0) {
                    this.setExp(0);
                }
            }
            this.updateSingleStat(MapleStat.EXP, this.getExp());
            if (show) {
                this.client.sendPacket(MaplePacketCreator.GainEXP_Monster(gain, white, partyinc, Class_Bonus_EXP, Equipment_Bonus_EXP, Premium_Bonus_EXP, H_EXP));
            }
        }
    }
    
    public void reloadC() {
        this.client.getPlayer().getClient().sendPacket(MaplePacketCreator.getCharInfo(this.client.getPlayer()));
        this.client.getPlayer().getMap().removePlayer(this.client.getPlayer());
        this.client.getPlayer().getMap().addPlayer(this.client.getPlayer());
    }
    
    public void forceReAddItem_NoUpdate(final IItem item, final MapleInventoryType type) {
        this.getInventory(type).removeSlot(item.getPosition());
        this.getInventory(type).addFromDB(item);
    }
    
    public void forceReAddItem(final IItem item, final MapleInventoryType type) {
        this.forceReAddItem_NoUpdate(item, type);
        if (type != MapleInventoryType.UNDEFINED) {
            this.client.sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(1, item)));
        }
    }
    
    public void forceReAddItem_Flag(final IItem item, final MapleInventoryType type) {
        this.forceReAddItem_NoUpdate(item, type);
        if (type != MapleInventoryType.UNDEFINED) {
            this.client.sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(0, item)));
        }
    }
    
    public void silentPartyUpdate() {
        if (this.party != null) {
            Party.updateParty(this.party.getId(), PartyOperation.SILENT_UPDATE, new MaplePartyCharacter(this));
        }
    }
    
    public boolean isGM() {
        return this.gmLevel > 0;
    }
    
    public boolean isAdmin() {
        return this.gmLevel >= 5;
    }
    
    public int getGMLevel() {
        return this.gmLevel;
    }
    
    public boolean isPlayer() {
        return this.gmLevel == 0;
    }
    
    public boolean hasGmLevel(final int level) {
        return this.gmLevel >= level;
    }
    
    public void setGmLevelHM(final byte level) {
        this.gmLevel = level;
    }
    
    public final MapleInventory getInventory(final MapleInventoryType type) {
        return this.inventory[type.ordinal()];
    }
    
    public final MapleInventory[] getInventorys() {
        return this.inventory;
    }
    
    public final void expirationTask() {
        this.expirationTask(false);
    }
    
    public final void expirationTask(final boolean pending) {
        this.expirationTask(false, pending);
    }
    
    public final void expirationTask(final boolean packet, final boolean pending) {
        if (pending) {
            if (this.pendingExpiration != null) {
                for (final Integer z : this.pendingExpiration) {
                    this.client.sendPacket(MTSCSPacket.itemExpired((int)z));
                }
            }
            this.pendingExpiration = null;
            if (this.pendingSkills != null) {
                for (final Integer z : this.pendingSkills) {
                    this.client.sendPacket(MaplePacketCreator.updateSkill((int)z, 0, 0, -1L));
                    this.client.sendPacket(MaplePacketCreator.serverNotice(5, "[" + SkillFactory.getSkillName((int)z) + "] 技能已經過期，系統自動從技能栏位移除。"));
                }
            }
            this.pendingSkills = null;
            return;
        }
        final List<Integer> ret = new ArrayList<Integer>();
        final long currenttime = System.currentTimeMillis();
        final List<Pair<MapleInventoryType, IItem>> toberemove = new ArrayList<Pair<MapleInventoryType, IItem>>();
        final List<IItem> tobeunlock = new ArrayList<IItem>();
        for (final MapleInventoryType inv : MapleInventoryType.values()) {
            for (final IItem item : this.getInventory(inv)) {
                final long expiration = item.getExpiration();
                if (expiration != -1L && !GameConstants.isPet(item.getItemId()) && currenttime > expiration) {
                    if (ItemFlag.LOCK.check((int)item.getFlag())) {
                        tobeunlock.add(item);
                    }
                    else {
                        if (currenttime <= expiration) {
                            continue;
                        }
                        toberemove.add(new Pair<MapleInventoryType, IItem>(inv, item));
                    }
                }
                else {
                    if (item.getItemId() != 5000054 || item.getPet() == null || item.getPet().getSecondsLeft() > 0) {
                        continue;
                    }
                    toberemove.add(new Pair<MapleInventoryType, IItem>(inv, item));
                }
            }
        }
        for (final Pair<MapleInventoryType, IItem> itemz : toberemove) {
            final IItem item2 = (IItem)itemz.getRight();
            ret.add(Integer.valueOf(item2.getItemId()));
            if (packet) {
                this.getInventory((MapleInventoryType)itemz.getLeft()).removeItem(item2.getPosition(), item2.getQuantity(), false, this);
            }
            else {
                this.getInventory((MapleInventoryType)itemz.getLeft()).removeItem(item2.getPosition(), item2.getQuantity(), false);
            }
        }
        for (final IItem itemz2 : tobeunlock) {
            itemz2.setExpiration(-1L);
            itemz2.setFlag((byte)(itemz2.getFlag() - ItemFlag.LOCK.getValue()));
        }
        this.pendingExpiration = ret;
        final List<Integer> skilz = new ArrayList<Integer>();
        final List<ISkill> toberem = new ArrayList<ISkill>();
        for (final Entry<ISkill, SkillEntry> skil : this.skills.entrySet()) {
            if (((SkillEntry)skil.getValue()).expiration != -1L && currenttime > ((SkillEntry)skil.getValue()).expiration) {
                toberem.add(skil.getKey());
            }
        }
        for (final ISkill skil2 : toberem) {
            skilz.add(Integer.valueOf(skil2.getId()));
            this.skills.remove((Object)skil2);
        }
        this.pendingSkills = skilz;
    }
    
    public MapleShop getShop() {
        return this.shop;
    }
    
    public void setShop(final MapleShop shop) {
        this.shop = shop;
    }
    
    public int getMeso() {
        return this.meso;
    }
    
    public final int[] getSavedLocations() {
        return this.savedLocations;
    }
    
    public int getSavedLocation(final SavedLocationType type) {
        return this.savedLocations[type.getValue()];
    }
    
    public void saveLocation(final SavedLocationType type) {
        this.savedLocations[type.getValue()] = this.getMapId();
    }
    
    public void saveLocation(final SavedLocationType type, final int mapz) {
        this.savedLocations[type.getValue()] = mapz;
    }
    
    public void clearSavedLocation(final SavedLocationType type) {
        this.savedLocations[type.getValue()] = -1;
    }
    
    public void gainMeso(final int gain, final boolean show) {
        this.gainMeso(gain, show, false, false);
    }
    
    public void gainMeso(final int gain, final boolean show, final boolean enableActions) {
        this.gainMeso(gain, show, enableActions, false);
    }
    
    public void gainMeso(final int gain, final boolean show, final boolean enableActions, final boolean inChat) {
        if (this.meso + gain < 0) {
            this.client.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        this.meso += gain;
        this.updateSingleStat(MapleStat.MESO, this.meso, enableActions);
        if (show) {
            this.client.sendPacket(MaplePacketCreator.showMesoGain(gain, inChat));
        }
    }
    
    public void controlMonster(final MapleMonster monster, final boolean aggro) {
        if (this.clone) {
            return;
        }
        monster.setController(this);
        this.controlled.add(monster);
        this.client.sendPacket(MobPacket.controlMonster(monster, false, aggro));
        monster.sendStatus(this.client);
    }
    
    public void stopControllingMonster(final MapleMonster monster) {
        if (this.clone) {
            return;
        }
        if (monster != null && this.controlled.contains((Object)monster)) {
            this.controlled.remove((Object)monster);
        }
    }
    
    public void checkMonsterAggro(final MapleMonster monster) {
        if (this.clone || monster == null) {
            return;
        }
        if (monster.getController() == this) {
            monster.setControllerHasAggro(true);
        }
        else {
            monster.switchController(this, true);
        }
    }
    
    public Set<MapleMonster> getControlled() {
        return this.controlled;
    }
    
    public int getControlledSize() {
        return this.controlled.size();
    }
    
    public int getAccountID() {
        return this.accountid;
    }
    
    public void mobKilled(final int id, final int skillID) {
        try {
            for (final MapleQuestStatus q : this.quests.values()) {
                if (q.getStatus() == 1) {
                    if (!q.hasMobKills()) {
                        continue;
                    }
                    if (!q.mobKilled(id, skillID)) {
                        continue;
                    }
                    this.client.sendPacket(MaplePacketCreator.updateQuestMobKills(q));
                    if (!q.getQuest().canComplete(this, null)) {
                        continue;
                    }
                    this.client.sendPacket(MaplePacketCreator.getShowQuestCompletion(q.getQuest().getId()));
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            FileoutputUtil.outError("logs/殺死怪物計次異常.txt", (Throwable)ex);
        }
    }
    
    public final List<MapleQuestStatus> getStartedQuests() {
        final List<MapleQuestStatus> ret = new LinkedList<MapleQuestStatus>();
        for (final MapleQuestStatus q : this.quests.values()) {
            if (q.getStatus() == 1 && !q.isCustom()) {
                ret.add(q);
            }
        }
        return ret;
    }
    
    public final List<MapleQuestStatus> getCompletedQuests() {
        final List<MapleQuestStatus> ret = new LinkedList<MapleQuestStatus>();
        for (final MapleQuestStatus q : this.quests.values()) {
            if (q.getStatus() == 2 && !q.isCustom()) {
                ret.add(q);
            }
        }
        return ret;
    }
    
    public Map<ISkill, SkillEntry> getSkills() {
        return Collections.unmodifiableMap((Map<? extends ISkill, ? extends SkillEntry>)this.skills);
    }
    
    public byte getSkillLevel2(final ISkill skill) {
        final SkillEntry ret = (SkillEntry)this.skills.get((Object)skill);
        if (ret == null || ret.skillevel < 0) {
            return -1;
        }
        return (byte)Math.min((int)skill.getMaxLevel(), ret.skillevel + (skill.isBeginnerSkill() ? 0 : this.stats.incAllskill));
    }
    
    public byte getSkillLevel(final ISkill skill) {
        final SkillEntry ret = (SkillEntry)this.skills.get((Object)skill);
        if (ret == null || ret.skillevel <= 0) {
            return 0;
        }
        return (byte)Math.min((int)skill.getMaxLevel(), ret.skillevel + (skill.isBeginnerSkill() ? 0 : this.stats.incAllskill));
    }
    
    public byte getMasterLevel(final int skill) {
        return this.getMasterLevel(SkillFactory.getSkill(skill));
    }
    
    public byte getMasterLevel(final ISkill skill) {
        final SkillEntry ret = (SkillEntry)this.skills.get((Object)skill);
        if (ret == null) {
            return 0;
        }
        return ret.masterlevel;
    }
    
    public void levelUp() {
        if (this.level >= this.取限制等级()) {
            this.client.sendPacket(MaplePacketCreator.serverNotice(5, "当前等级已经超过世界限制等级，暂时无法升级，请突破世界等级之后才能继续升级！"));
            return;
        }
        if (GameConstants.isKOC((int)this.job)) {
            if (this.level <= 70) {
                this.remainingAp += 6;
            }
            else {
                this.remainingAp += 5;
            }
        }
        else {
            this.remainingAp += 5;
        }
        int maxhp = this.stats.getMaxHp();
        int maxmp = this.stats.getMaxMp();
        if (this.job == 0 || this.job == 1000 || this.job == 2000) {
            maxhp += Randomizer.rand(12, 16);
            maxmp += Randomizer.rand(10, 12);
        }
        else if (this.job >= 100 && this.job <= 132) {
            final ISkill improvingMaxHP = SkillFactory.getSkill(1000001);
            final int slevel = this.getSkillLevel(improvingMaxHP);
            if (slevel > 0) {
                maxhp += improvingMaxHP.getEffect(slevel).getX();
            }
            maxhp += Randomizer.rand(24, 28);
            maxmp += Randomizer.rand(4, 6);
        }
        else if (this.job >= 200 && this.job <= 232) {
            final ISkill improvingMaxMP = SkillFactory.getSkill(2000001);
            final int slevel = this.getSkillLevel(improvingMaxMP);
            if (slevel > 0) {
                maxmp += improvingMaxMP.getEffect(slevel).getX() * 2;
            }
            maxhp += Randomizer.rand(10, 14);
            maxmp += Randomizer.rand(22, 24);
        }
        else if ((this.job >= 300 && this.job <= 322) || (this.job >= 400 && this.job <= 422) || (this.job >= 1300 && this.job <= 1311) || (this.job >= 1400 && this.job <= 1411)) {
            maxhp += Randomizer.rand(20, 24);
            maxmp += Randomizer.rand(14, 16);
        }
        else if (this.job >= 500 && this.job <= 522) {
            final ISkill improvingMaxHP = SkillFactory.getSkill(5100000);
            final int slevel = this.getSkillLevel(improvingMaxHP);
            if (slevel > 0) {
                maxhp += improvingMaxHP.getEffect(slevel).getX();
            }
            maxhp += Randomizer.rand(22, 26);
            maxmp += Randomizer.rand(18, 22);
        }
        else if (this.job >= 1100 && this.job <= 1111) {
            final ISkill improvingMaxHP = SkillFactory.getSkill(11000000);
            final int slevel = this.getSkillLevel(improvingMaxHP);
            if (slevel > 0) {
                maxhp += improvingMaxHP.getEffect(slevel).getX();
            }
            maxhp += Randomizer.rand(24, 28);
            maxmp += Randomizer.rand(4, 6);
        }
        else if (this.job >= 1200 && this.job <= 1212) {
            final ISkill improvingMaxMP = SkillFactory.getSkill(12000000);
            final int slevel = this.getSkillLevel(improvingMaxMP);
            if (slevel > 0) {
                maxmp += improvingMaxMP.getEffect(slevel).getX() * 2;
            }
            maxhp += Randomizer.rand(10, 14);
            maxmp += Randomizer.rand(22, 24);
        }
        else if (this.job >= 1500 && this.job <= 1512) {
            final ISkill improvingMaxHP = SkillFactory.getSkill(15100000);
            final int slevel = this.getSkillLevel(improvingMaxHP);
            if (slevel > 0) {
                maxhp += improvingMaxHP.getEffect(slevel).getX();
            }
            maxhp += Randomizer.rand(22, 26);
            maxmp += Randomizer.rand(18, 22);
        }
        else if (this.job >= 2100 && this.job <= 2112) {
            maxhp += Randomizer.rand(50, 52);
            maxmp += Randomizer.rand(4, 6);
        }
        else {
            maxhp += Randomizer.rand(50, 100);
            maxmp += Randomizer.rand(50, 100);
        }
        maxmp += this.stats.getTotalInt() / 10;
        this.exp -= GameConstants.getExpNeededForLevel((int)this.level);
        if (this.exp < 0) {
            this.exp = 0;
        }
        ++this.level;
        maxhp = (short)Math.min(30000, Math.abs(maxhp));
        maxmp = (short)Math.min(30000, Math.abs(maxmp));
        final Map<MapleStat, Integer> statup = new EnumMap<MapleStat, Integer>(MapleStat.class);
        statup.put(MapleStat.MAXHP, Integer.valueOf(maxhp));
        statup.put(MapleStat.MAXMP, Integer.valueOf(maxmp));
        statup.put(MapleStat.HP, Integer.valueOf(maxhp));
        statup.put(MapleStat.MP, Integer.valueOf(maxmp));
        statup.put(MapleStat.EXP, Integer.valueOf(this.exp));
        statup.put(MapleStat.LEVEL, Integer.valueOf((int)this.level));
        if (this.isGM() || (this.job != 0 && this.job != 1000 && this.job != 2000 && this.job != 2001 && this.job != 3000) || this.level > 9) {
            final int[] remainingSp = this.remainingSp;
            final int skillBook = GameConstants.getSkillBook((int)this.job);
            remainingSp[skillBook] += 3;
            this.client.sendPacket(MaplePacketCreator.updateSp(this, false));
        }
        else if (this.level <= 10) {
            this.stats.setStr((short)(this.stats.getStr() + this.remainingAp));
            this.remainingAp = 0;
            statup.put(MapleStat.STR, Integer.valueOf((int)this.stats.getStr()));
        }
        statup.put(MapleStat.AVAILABLEAP, Integer.valueOf((int)this.remainingAp));
        this.stats.setMaxHp((short)maxhp);
        this.stats.setMaxMp((short)maxmp);
        this.stats.setHp((int)(short)maxhp);
        this.stats.setMp((int)(short)maxmp);
        this.client.sendPacket(MaplePacketCreator.updatePlayerStats(statup, this));
        this.map.broadcastMessage(this, MaplePacketCreator.showForeignEffect(this.getId(), 0), false);
        this.stats.recalcLocalStats();
        this.silentPartyUpdate();
        this.guildUpdate();
        this.familyUpdate();
        this.DoLevelMsg();
        if (this.level >= 20 && this.level <= 25 && !this.isGM()) {
            this.DoLevelMap();
        }
        if ((this.job == 1000 || (this.job >= 1100 && this.job <= 1111) || (this.job >= 1200 && this.job <= 1212) || (this.job >= 1300 && this.job <= 1312) || (this.job >= 1400 && this.job <= 1412) || (this.job >= 1500 && this.job <= 1512)) && this.level == 120) {
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[恭賀] 玩家" + this.getName() + " 皇家騎士團到達120級。"));
        }
        if (this.level == 200) {
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[恭賀] 玩家" + this.getName() + " 到達200級。"));
        }
        if (this.level == 100 && this.getStLog() >= 1) {
            final int stjfid = this.getStLogid(this.id);
            if (this.getStjfLog(stjfid) >= 1) {
                this.updateStjfLog(stjfid, this.getStjf(stjfid) + 1);
            }
            else {
                this.setStjfLog(stjfid, 1);
            }
        }
        FileoutputUtil.logToFile("logs/Data/升級日誌.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + this.getClient().getSession().remoteAddress().toString().split(":")[0] + " 账号: " + this.getClient().getAccountName() + " 玩家: " + this.getName() + " 升級到" + (int)this.level);
    }
    
    public void DoLevelMsg() {
        final int 升级快讯 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"升级快讯开关"));
        if (升级快讯 <= 0 && !this.isGM() && this.level >= 1) {
            final String 最高等级玩家 = NPCConversationManager.获取最高等级玩家名字();
            final StringBuilder sb = new StringBuilder("[升级快讯]: 恭喜玩家【");
            final IItem medal = this.getInventory(MapleInventoryType.EQUIPPED).getItem((short)(-26));
            if (this.guildid > 0) {}
            sb.append(this.getName());
            sb.append("】在地图 【 " + this.map.getMapName() + "】 目前等级达到 ").append((int)this.level).append(" 级 ");
            if (medal != null) {
                sb.append("<");
                sb.append(MapleItemInformationProvider.getInstance().getName(medal.getItemId()));
                sb.append(">");
            }
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, sb.toString()));
        }
        if (GameConstants.isAran((int)this.job)) {
            switch (this.level) {
                case 30: {
                    this.client.sendPacket(MaplePacketCreator.startMapEffect("恭喜达到30级请回金银岛二转吧。", 5120000, true));
                    break;
                }
                case 70: {
                    this.client.sendPacket(MaplePacketCreator.startMapEffect("恭喜达到70级请到冰原雪域长老公馆三转吧。", 5120000, true));
                    break;
                }
                case 120: {
                    this.client.sendPacket(MaplePacketCreator.startMapEffect("恭喜达到120级请回神木村祭司森林四转吧。", 5120000, true));
                    break;
                }
            }
        }
        if (GameConstants.isKOC((int)this.job) && this.level == 70) {
            this.client.sendPacket(MaplePacketCreator.startMapEffect("恭喜达到70等请到冰封雪域三转吧。", 5120000, true));
        }
    }
    
    public void DoLevelMap() {
        boolean warp = false;
        int Return_Map = 0;
        switch (this.getMapId()) {
            case 910060000: {
                warp = true;
                Return_Map = 100010000;
                break;
            }
            case 910120000: {
                warp = true;
                Return_Map = 100040000;
                break;
            }
            case 910220000: {
                warp = true;
                Return_Map = 101040000;
                break;
            }
            case 910310000: {
                warp = true;
                Return_Map = 103010000;
                break;
            }
            case 912030000: {
                warp = true;
                Return_Map = 120010000;
                break;
            }
        }
        if (warp) {
            final MapleMap warpMap = this.client.getChannelServer().getMapFactory().getMap(Return_Map);
            if (warpMap != null) {
                this.changeMap(warpMap, warpMap.getPortal(0));
                this.dropMessage("由於你的等級超過20，已經不符合新手需求，將把您傳出訓練場。");
            }
        }
    }
    
    public void changeKeybinding(final int key, final byte type, final int action) {
        if (type != 0) {
            this.keylayout.Layout().put(Integer.valueOf(key), new Pair<Byte, Integer>(Byte.valueOf(type), Integer.valueOf(action)));
        }
        else {
            this.keylayout.Layout().remove((Object)Integer.valueOf(key));
        }
    }
    
    public void sendMacros() {
        for (int i = 0; i < 5; ++i) {
            if (this.skillMacros[i] != null) {
                this.client.sendPacket(MaplePacketCreator.getMacros(this.skillMacros));
                break;
            }
        }
    }
    
    public void updateMacros(final int position, final SkillMacro updateMacro) {
        this.skillMacros[position] = updateMacro;
    }
    
    public final SkillMacro[] getMacros() {
        return this.skillMacros;
    }
    
    public void tempban(final String reason, final Calendar duration, final int greason, final boolean bandIp) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            FileoutputUtil.logToFile("logs/Hack/Ban/MySql_input.txt", "\r\n[tempBan] " + FileoutputUtil.NowTime() + " IP: " + this.client.getSession().remoteAddress().toString().split(":")[0] + " MAC: " + (Object)this.getClient().getMacs() + " 理由: " + reason, false, false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO ipbans (ip) VALUES (?)");
            ps.setString(1, this.client.getSession().remoteAddress().toString().split(":")[0]);
            ps.executeUpdate();
            ps.close();
            ps = con.prepareStatement("UPDATE accounts SET tempban = ?, banreason = ?, greason = ? WHERE id = ?");
            final Timestamp TS = new Timestamp(duration.getTimeInMillis());
            ps.setTimestamp(1, TS);
            ps.setString(2, reason);
            ps.setInt(3, greason);
            ps.setInt(4, this.accountid);
            ps.execute();
            ps.close();
            this.client.disconnect(true, false);
        }
        catch (SQLException ex) {
            System.err.println("Error while tempbanning" + (Object)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
    }
    
    public static boolean ban(final String ip, final String id, final String reason, final boolean accountId, final int gmlevel, final boolean hellban) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            if (!isVpn(ip) && id.matches("/[0-9]{1,3}\\..*")) {
                FileoutputUtil.logToFile("logs/Hack/Ban/MySql_input.txt", "\r\n[Ban-1] " + FileoutputUtil.NowTime() + " IP: " + ip + " 理由: " + reason, false, false);
                final PreparedStatement ps = con.prepareStatement("INSERT INTO ipbans (ip) VALUES (?)");
                ps.setString(1, id);
                ps.executeUpdate();
                ps.close();
                return true;
            }
            PreparedStatement ps;
            if (accountId) {
                ps = con.prepareStatement("SELECT id FROM accounts WHERE name = ?");
            }
            else {
                ps = con.prepareStatement("SELECT accountid FROM characters WHERE name = ?");
            }
            boolean ret = false;
            ps.setString(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    final int z = rs.getInt(1);
                    try (final PreparedStatement psb = con.prepareStatement("UPDATE accounts SET banned = 1, banreason = ? WHERE id = ? AND gm < ?")) {
                        psb.setString(1, reason);
                        psb.setInt(2, z);
                        psb.setInt(3, gmlevel);
                        psb.execute();
                    }
                    if (gmlevel > 100) {
                        try (final PreparedStatement psa = con.prepareStatement("SELECT * FROM accounts WHERE id = ?")) {
                            psa.setInt(1, z);
                            try (final ResultSet rsa = psa.executeQuery()) {
                                if (rsa.next()) {
                                    final String sessionIP = rsa.getString("sessionIP");
                                    if (sessionIP != null && sessionIP.matches("/[0-9]{1,3}\\..*")) {
                                        FileoutputUtil.logToFile("logs/Hack/Ban/MySql_input.txt", "\r\n[Ban-2] " + FileoutputUtil.NowTime() + " IP: " + ip + " 理由: " + reason, false, false);
                                    }
                                    final String macData = rsa.getString("macs");
                                    if (macData != null) {
                                        MapleClient.banMacs(macData);
                                    }
                                    if (hellban) {
                                        try (final PreparedStatement pss = con.prepareStatement("UPDATE accounts SET banned = 1, banreason = ? WHERE email = ?" + ((sessionIP == null) ? "" : " OR SessionIP = ?"))) {
                                            pss.setString(1, reason);
                                            pss.setString(2, rsa.getString("email"));
                                            if (sessionIP != null) {
                                                pss.setString(3, sessionIP);
                                            }
                                            pss.execute();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    ret = true;
                }
            }
            ps.close();
            return ret;
        }
        catch (SQLException ex) {
            System.err.println("Error while banning" + (Object)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            return false;
        }
    }
    
    public final boolean ban(final String reason, final boolean banIP, final boolean autoban, final boolean hellban) {
        if (this.lastmonthfameids == null) {
            throw new RuntimeException("Trying to ban a non-loaded character (testhack)");
        }
        final String ip = this.client.getSessionIPAddress();
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE accounts SET banned = ?, banreason = ? WHERE id = ?");
            ps.setInt(1, autoban ? 2 : 1);
            ps.setString(2, reason);
            ps.setInt(3, this.accountid);
            ps.execute();
            ps.close();
            if (!isVpn(ip)) {
                FileoutputUtil.logToFile("logs/Hack/Ban/MySql_input.txt", "\r\n" + FileoutputUtil.NowTime() + " IP: " + ip + " MAC: " + (Object)this.getClient().getMacs() + " 理由: " + reason, false, false);
                ps = con.prepareStatement("INSERT INTO ipbans (ip) VALUES (?)");
                ps.setString(1, ip);
                ps.executeUpdate();
                ps.close();
                try {
                    for (final ChannelServer cs : ChannelServer.getAllInstances()) {
                        for (final MapleCharacter chr : cs.getPlayerStorage().getAllCharactersThreadSafe()) {
                            if (chr.getClient().getSessionIPAddress().equals((Object)this.client.getSessionIPAddress()) && !chr.getClient().isGm()) {
                                chr.getClient().disconnect(true, false);
                                chr.getClient().getSession().close();
                            }
                        }
                    }
                }
                catch (Exception ex3) {}
            }
            this.client.banMacs();
            if (hellban) {
                try (final PreparedStatement psa = con.prepareStatement("SELECT * FROM accounts WHERE id = ?")) {
                    psa.setInt(1, this.accountid);
                    try (final ResultSet rsa = psa.executeQuery()) {
                        if (rsa.next()) {
                            try (final PreparedStatement pss = con.prepareStatement("UPDATE accounts SET banned = ?, banreason = ? WHERE email = ? OR SessionIP = ?")) {
                                pss.setInt(1, autoban ? 2 : 1);
                                pss.setString(2, reason);
                                pss.setString(3, rsa.getString("email"));
                                pss.setString(4, ip);
                                pss.execute();
                            }
                        }
                    }
                }
            }
        }
        catch (SQLException ex) {
            System.err.println("Error while banning" + (Object)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            return false;
        }
        try {
            this.client.disconnect(true, false);
        }
        catch (Exception ex2) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex2);
        }
        return true;
    }
    
    public boolean OfflineBanByName(final String name, final String reason) {
        int id = 0;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = null;
            final Statement stmt = con.createStatement();
            ps = con.prepareStatement("select id from characters where name = ?");
            ps.setString(1, name);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        }
        catch (Exception ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        return id != 0 && this.OfflineBanById(id, reason);
    }
    
    public boolean OfflineBanById(final int id, final String reason) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final Statement stmt = con.createStatement();
            final int z = id;
            int acid = 0;
            String ip = "";
            String mac = "";
            ResultSet rs = stmt.executeQuery("select accountid from characters where id = " + id);
            while (rs.next()) {
                acid = rs.getInt("accountid");
            }
            if (acid == 0) {
                return false;
            }
            try (final PreparedStatement psb = con.prepareStatement("UPDATE accounts SET banned = 1, banreason = ? WHERE id = ?")) {
                psb.setString(1, reason);
                psb.setInt(2, acid);
                psb.execute();
                psb.close();
            }
            rs = stmt.executeQuery("select SessionIP, macs from accounts where id = " + acid);
            while (rs.next()) {
                ip = rs.getString("SessionIP");
                mac = rs.getString("macs");
            }
            if (!isVpn(ip)) {
                FileoutputUtil.logToFile("logs/Hack/Ban/MySql_input.txt", "\r\n[offlineBan] " + FileoutputUtil.NowTime() + " IP: " + ip + " MAC: " + (Object)this.getClient().getMacs() + " 理由: " + reason, false, false);
                final PreparedStatement ps = con.prepareStatement("INSERT INTO ipbans (ip) VALUES (?)");
                ps.setString(1, ip);
                ps.executeUpdate();
                ps.close();
                try {
                    for (final ChannelServer cs : ChannelServer.getAllInstances()) {
                        for (final MapleCharacter chr : cs.getPlayerStorage().getAllCharactersThreadSafe()) {
                            if (chr.getClient().getSessionIPAddress().equals((Object)ip) && !chr.getClient().isGm()) {
                                chr.getClient().disconnect(true, false);
                                chr.getClient().getSession().close();
                            }
                        }
                    }
                }
                catch (Exception ex2) {}
            }
            final MapleClient client = this.client;
            MapleClient.banMacs(mac);
            rs.close();
            stmt.close();
            return true;
        }
        catch (Exception ex) {
            System.err.println("封鎖出現錯誤 " + (Object)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            return false;
        }
    }
    
    @Override
    public int getObjectId() {
        return this.getId();
    }
    
    @Override
    public void setObjectId(final int id) {
        throw new UnsupportedOperationException();
    }
    
    public MapleStorage getStorage() {
        return this.storage;
    }
    
    public void addVisibleMapObject(final MapleMapObject mo) {
        if (this.clone) {
            return;
        }
        this.visibleMapObjectsLock.writeLock().lock();
        try {
            this.visibleMapObjects.add(mo);
        }
        finally {
            this.visibleMapObjectsLock.writeLock().unlock();
        }
    }
    
    public void removeVisibleMapObject(final MapleMapObject mo) {
        if (this.clone) {
            return;
        }
        this.visibleMapObjectsLock.writeLock().lock();
        try {
            this.visibleMapObjects.remove((Object)mo);
        }
        finally {
            this.visibleMapObjectsLock.writeLock().unlock();
        }
    }
    
    public boolean isMapObjectVisible(final MapleMapObject mo) {
        this.visibleMapObjectsLock.readLock().lock();
        try {
            return !this.clone && this.visibleMapObjects.contains((Object)mo);
        }
        finally {
            this.visibleMapObjectsLock.readLock().unlock();
        }
    }
    
    public Collection<MapleMapObject> getAndWriteLockVisibleMapObjects() {
        this.visibleMapObjectsLock.writeLock().lock();
        return this.visibleMapObjects;
    }
    
    public void unlockWriteVisibleMapObjects() {
        this.visibleMapObjectsLock.writeLock().unlock();
    }
    
    public boolean isAlive() {
        return this.stats.getHp() > 0;
    }
    
    @Override
    public void sendDestroyData(final MapleClient client) {
        client.sendPacket(MaplePacketCreator.removePlayerFromMap(this.getObjectId()));
        for (final WeakReference<MapleCharacter> chr : this.clones) {
            if (chr.get() != null) {
                ((MapleCharacter)chr.get()).sendDestroyData(client);
            }
        }
    }
    
    @Override
    public void sendSpawnData(final MapleClient client) {
        if (client.getPlayer().allowedToTarget(this)) {
            client.sendPacket(MaplePacketCreator.spawnPlayerMapobject(this));
            if (this.getParty() != null && !this.isClone()) {
                this.updatePartyMemberHP();
                this.receivePartyMemberHP();
            }
            for (final MaplePet pet : this.getSummonedPets()) {
                if (this.getId() != client.getPlayer().getId()) {
                    client.sendPacket(PetPacket.showPet(this, pet, false, false));
                }
            }
            for (final WeakReference<MapleCharacter> chr : this.clones) {
                if (chr.get() != null) {
                    ((MapleCharacter)chr.get()).sendSpawnData(client);
                }
            }
            if (this.summons != null) {
                for (final MapleSummon summon : this.summons.values()) {
                    client.sendPacket(MaplePacketCreator.spawnSummon(summon, false));
                }
            }
            if (this.followid > 0) {
                client.sendPacket(MaplePacketCreator.followEffect(this.followinitiator ? this.id : this.followid, this.followinitiator ? this.followid : this.id, null));
            }
        }
    }
    
    public final void equipChanged() {
        if (this.map == null) {
            return;
        }
        this.map.broadcastMessage(this, MaplePacketCreator.updateCharLook(this), false);
        this.stats.recalcLocalStats();
        if (this.getMessenger() != null) {
            Messenger.updateMessenger(this.getMessenger().getId(), this.getName(), this.client.getChannel());
        }
    }
    
    public final MaplePet getPet(final int index) {
        byte count = 0;
        for (final MaplePet pet : this.pets) {
            if (pet.getSummoned()) {
                if (count == index) {
                    return pet;
                }
                ++count;
            }
        }
        return null;
    }
    
    public void addPet(final MaplePet pet) {
        if (this.pets.contains((Object)pet)) {
            this.pets.remove((Object)pet);
        }
        this.pets.add(pet);
    }
    
    public void removePet(final MaplePet pet) {
        pet.setSummoned(0);
        this.pets.remove((Object)pet);
    }
    
    public final List<MaplePet> getSummonedPets() {
        final List<MaplePet> ret = new ArrayList<MaplePet>();
        for (int i = 0; i < 3; ++i) {
            ret.add(null);
        }
        for (int i = 0; i < 3; ++i) {
            for (final MaplePet pet : this.pets) {
                if (pet != null && pet.getSummoned()) {
                    final int index = pet.getSummonedValue() - 1;
                    if (index == i) {
                        ret.remove(index);
                        ret.add(index, pet);
                        break;
                    }
                    continue;
                }
            }
        }
        final List<Integer> nullArr = new ArrayList<Integer>();
        nullArr.add(null);
        ret.removeAll((Collection<?>)nullArr);
        return ret;
    }
    
    public final MaplePet getSummonedPet(final int index) {
        for (final MaplePet pet : this.getSummonedPets()) {
            if (pet.getSummonedValue() - 1 == index) {
                return pet;
            }
        }
        return null;
    }
    
    public final void shiftPetsRight() {
        final List<MaplePet> petsz = this.getSummonedPets();
        if (petsz.size() >= 3 || petsz.size() < 1) {
            return;
        }
        final boolean[] indexBool = { false, false, false };
        for (int i = 0; i < 3; ++i) {
            for (final MaplePet p : petsz) {
                if (p.getSummonedValue() == i + 1) {
                    indexBool[i] = true;
                }
            }
        }
        if (petsz.size() > 1) {
            if (!indexBool[2]) {
                ((MaplePet)petsz.get(0)).setSummoned(2);
                ((MaplePet)petsz.get(1)).setSummoned(3);
            }
            else if (!indexBool[1]) {
                ((MaplePet)petsz.get(0)).setSummoned(2);
            }
        }
        else if (indexBool[0]) {
            ((MaplePet)petsz.get(0)).setSummoned(2);
        }
    }
    
    public final int getPetSlotNext() {
        final List<MaplePet> petsz = this.getSummonedPets();
        int index = 0;
        if (petsz.size() >= 3) {
            this.unequipPet(this.getSummonedPet(0), false);
        }
        else {
            final boolean[] indexBool = { false, false, false };
            for (int i = 0; i < 3; ++i) {
                for (final MaplePet p : petsz) {
                    if (p.getSummonedValue() == i + 1) {
                        indexBool[i] = true;
                        break;
                    }
                }
            }
            for (final boolean b : indexBool) {
                if (!b) {
                    break;
                }
                ++index;
            }
            index = Math.min(index, 2);
            for (final MaplePet p2 : petsz) {
                if (p2.getSummonedValue() == index + 1) {
                    this.unequipPet(p2, false);
                }
            }
        }
        return index;
    }
    
    public final byte getPetIndex(final MaplePet petz) {
        return (byte)Math.max(-1, petz.getSummonedValue() - 1);
    }
    
    public final byte getPetIndex(final int petId) {
        for (final MaplePet pet : this.getSummonedPets()) {
            if (pet.getUniqueId() == petId) {
                return (byte)Math.max(-1, pet.getSummonedValue() - 1);
            }
        }
        return -1;
    }
    
    public final List<MaplePet> getPets() {
        return this.pets;
    }
    
    public final void unequipAllPets() {
        for (final MaplePet pet : this.getSummonedPets()) {
            this.unequipPet(pet, false);
        }
    }
    
    public void unequipPet(final MaplePet pet, final boolean hunger) {
        if (pet.getSummoned()) {
            pet.saveToDb();
            final List<MaplePet> summonedPets = this.getSummonedPets();
            if (summonedPets.contains((Object)pet)) {
                summonedPets.remove((Object)pet);
                int i = 1;
                for (final MaplePet p : summonedPets) {
                    if (p == null) {
                        continue;
                    }
                    p.setSummoned(i);
                    ++i;
                }
            }
            if (this.map != null) {
                this.map.broadcastMessage(this, PetPacket.showPet(this, pet, true, hunger), true);
            }
            pet.setSummoned(0);
            this.client.sendPacket(PetPacket.petStatUpdate(this));
            this.client.sendPacket(MaplePacketCreator.enableActions());
        }
    }
    
    public final long getLastFameTime() {
        return this.lastfametime;
    }
    
    public final List<Integer> getFamedCharacters() {
        return this.lastmonthfameids;
    }
    
    public FameStatus canGiveFame(final MapleCharacter from) {
        if (this.lastfametime >= System.currentTimeMillis() - 86400000L) {
            return FameStatus.NOT_TODAY;
        }
        if (from == null || this.lastmonthfameids == null || this.lastmonthfameids.contains((Object)Integer.valueOf(from.getId()))) {
            return FameStatus.NOT_THIS_MONTH;
        }
        return FameStatus.OK;
    }
    
    public void hasGivenFame(final MapleCharacter to) {
        this.lastfametime = System.currentTimeMillis();
        this.lastmonthfameids.add(Integer.valueOf(to.getId()));
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("INSERT INTO famelog (characterid, characterid_to) VALUES (?, ?)")) {
            ps.setInt(1, this.getId());
            ps.setInt(2, to.getId());
            ps.execute();
        }
        catch (SQLException e) {
            System.err.println("ERROR writing famelog for char " + this.getName() + " to " + to.getName() + (Object)e);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
    }
    
    public final MapleKeyLayout getKeyLayout() {
        return this.keylayout;
    }
    
    public MapleParty getParty() {
        return this.party;
    }
    
    public int getPartyId() {
        return (this.party != null) ? this.party.getId() : -1;
    }
    
    public byte getWorld() {
        return this.world;
    }
    
    public void setWorld(final byte world) {
        this.world = world;
    }
    
    public void setParty(final MapleParty party) {
        this.party = party;
    }
    
    public MapleTrade getTrade() {
        return this.trade;
    }
    
    public void setTrade(final MapleTrade trade) {
        this.trade = trade;
    }
    
    public EventInstanceManager getEventInstance() {
        return this.eventInstance;
    }
    
    public void setEventInstance(final EventInstanceManager eventInstance) {
        this.eventInstance = eventInstance;
    }
    
    public void addDoor(final MapleDoor door) {
        this.doors.add(door);
    }
    
    public void clearDoors() {
        this.doors.clear();
    }
    
    public List<MapleDoor> getDoors() {
        return new ArrayList<MapleDoor>((Collection<? extends MapleDoor>)this.doors);
    }
    
    public void setSmega() {
        if (this.smega) {
            this.smega = false;
            this.dropMessage(5, "由於您关闭了顯示廣播，所以您看不見任何的廣播，如果要打開請打@TSmega。");
        }
        else {
            this.smega = true;
            this.dropMessage(5, "目前已經打開顯示廣播，若要再次关闭請打@TSmega。");
        }
    }
    
    public boolean getSmega() {
        return this.smega;
    }
    
    public void setGashponmega() {
        if (this.gashponmega) {
            this.gashponmega = false;
            this.dropMessage(5, "由於您关闭了轉蛋廣播，所以您看不見任何的轉蛋廣播，如果要打開請打@Gashponmega。");
        }
        else {
            this.gashponmega = true;
            this.dropMessage(5, "目前已經打開顯示轉蛋廣播，若要再次关闭請打@Gashponmega。");
        }
    }
    
    public long getLasttime() {
        return this.lasttime;
    }
    
    public void setLasttime(final long lasttime) {
        this.lasttime = lasttime;
    }
    
    public long getCurrenttime() {
        return this.currenttime;
    }
    
    public void setCurrenttime(final long currenttime) {
        this.currenttime = currenttime;
    }
    
    public long get防止复制时间() {
        return this.防止复制时间;
    }
    
    public void set防止复制时间(final long 防止复制时间) {
        this.防止复制时间 = 防止复制时间;
    }
    
    public void startCheck() {
        if (!this.client.hasCheck(this.getAccountID())) {
            System.err.println("[作弊] 检测到玩家 " + this.getName() + " 登录器关闭，系统对其进行断开连接处理。");
            this.dropMessage(5, "检测到登录器关闭，游戏即将断开。");
        }
    }
    
    public boolean getGashponmega() {
        return this.gashponmega;
    }
    
    public Map<Integer, MapleSummon> getSummons() {
        return this.summons;
    }
    
    public int getChair() {
        return this.chair;
    }
    
    public int getItemEffect() {
        return this.itemEffect;
    }
    
    public void setChair(final int chair) {
        this.chair = chair;
        this.stats.relocHeal();
    }
    
    public void setItemEffect(final int itemEffect) {
        this.itemEffect = itemEffect;
    }
    
    @Override
    public MapleMapObjectType getType() {
        return MapleMapObjectType.PLAYER;
    }
    
    public int getFamilyId() {
        if (this.mfc == null) {
            return 0;
        }
        return this.mfc.getFamilyId();
    }
    
    public int getSeniorId() {
        if (this.mfc == null) {
            return 0;
        }
        return this.mfc.getSeniorId();
    }
    
    public int getJunior1() {
        if (this.mfc == null) {
            return 0;
        }
        return this.mfc.getJunior1();
    }
    
    public int getJunior2() {
        if (this.mfc == null) {
            return 0;
        }
        return this.mfc.getJunior2();
    }
    
    public int getCurrentRep() {
        return this.currentrep;
    }
    
    public int getTotalRep() {
        return this.totalrep;
    }
    
    public void setCurrentRep(final int _rank) {
        this.currentrep = _rank;
        if (this.mfc != null) {
            this.mfc.setCurrentRep(_rank);
        }
    }
    
    public void setTotalRep(final int _rank) {
        this.totalrep = _rank;
        if (this.mfc != null) {
            this.mfc.setTotalRep(_rank);
        }
    }
    
    public int getGuildId() {
        return this.guildid;
    }
    
    public byte getGuildRank() {
        return this.guildrank;
    }
    
    public void setGuildId(final int _id) {
        this.guildid = _id;
        if (this.guildid > 0) {
            if (this.mgc == null) {
                this.mgc = new MapleGuildCharacter(this);
            }
            else {
                this.mgc.setGuildId(this.guildid);
            }
        }
        else {
            this.mgc = null;
        }
    }
    
    public void setGuildRank(final byte _rank) {
        this.guildrank = _rank;
        if (this.mgc != null) {
            this.mgc.setGuildRank(_rank);
        }
    }
    
    public MapleGuildCharacter getMGC() {
        return this.mgc;
    }
    
    public void setAllianceRank(final byte rank) {
        this.allianceRank = rank;
        if (this.mgc != null) {
            this.mgc.setAllianceRank(rank);
        }
    }
    
    public byte getAllianceRank() {
        return this.allianceRank;
    }
    
    public MapleGuild getGuild() {
        if (this.getGuildId() <= 0) {
            return null;
        }
        return Guild.getGuild(this.getGuildId());
    }
    
    public void guildUpdate() {
        if (this.guildid <= 0) {
            return;
        }
        this.mgc.setLevel(this.level);
        this.mgc.setJobId((int)this.job);
        Guild.memberLevelJobUpdate(this.mgc);
    }
    
    public void saveGuildStatus() {
        MapleGuild.saveCharacterGuildInfo(this.guildid, this.guildrank, this.allianceRank, this.id);
    }
    
    public void familyUpdate() {
        if (this.mfc == null) {
            return;
        }
        Family.memberFamilyUpdate(this.mfc, this);
    }
    
    public void saveFamilyStatus() {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("UPDATE characters SET familyid = ?, seniorid = ?, junior1 = ?, junior2 = ? WHERE id = ?")) {
            if (this.mfc == null) {
                ps.setInt(1, 0);
                ps.setInt(2, 0);
                ps.setInt(3, 0);
                ps.setInt(4, 0);
            }
            else {
                ps.setInt(1, this.mfc.getFamilyId());
                ps.setInt(2, this.mfc.getSeniorId());
                ps.setInt(3, this.mfc.getJunior1());
                ps.setInt(4, this.mfc.getJunior2());
            }
            ps.setInt(5, this.id);
            ps.execute();
        }
        catch (SQLException se) {
            FilePrinter.printError("MapleCharacter.txt", (Throwable)se, "saveFamilyStatus");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)se);
        }
    }
    
    public void modifyCSPoints(final int type, final int quantity) {
        this.modifyCSPoints(type, quantity, false);
    }
    
    public void dropMessage(final String message) {
        this.dropMessage(6, message);
    }
    
    public void modifyCSPoints(final int type, final int quantity, final boolean show) {
        switch (type) {
            case 1: {
                final int Acash = this.getAcash();
                if (Acash + quantity < 0) {
                    if (show) {
                        this.dropMessage(-1, "目前点卷已滿，無法获得更多的点卷");
                    }
                    return;
                }
                this.setAcash(Acash + quantity);
                break;
            }
            case 2: {
                if (this.maplepoints + quantity < 0) {
                    if (show) {
                        this.dropMessage(-1, "目前抵用卷已滿，無法获得更多的抵用卷.");
                    }
                    return;
                }
                this.maplepoints += quantity;
                break;
            }
            case 3: {
                final int Points = this.getPoints();
                if (Points + quantity < 0) {
                    if (show) {
                        this.dropMessage(-1, "目前元宝已滿，無法获得更多的元宝");
                    }
                    return;
                }
                this.setPoints(Points + quantity);
                break;
            }
        }
        if (show && quantity != 0) {
            this.dropMessage(-1, "您已经 " + ((quantity > 0) ? "获得 " : "消费 ") + quantity + ((type == 1) ? " 点卷." : ((type == 2) ? " 抵用卷." : "元宝")));
        }
    }
    
    public int getCSPoints(final int type) {
        switch (type) {
            case 1: {
                return this.getAcash();
            }
            case 2: {
                return this.maplepoints;
            }
            case 3: {
                return this.getPoints();
            }
            default: {
                return 0;
            }
        }
    }
    
    public int getOfflineAcash(final MapleCharacter victim) {
        return this.getAcash(victim);
    }
    
    public final boolean hasEquipped(final int itemid) {
        return this.inventory[MapleInventoryType.EQUIPPED.ordinal()].countById(itemid) >= 1;
    }
    
    public final boolean haveItem(final int itemid, final int quantity, final boolean checkEquipped, final boolean greaterOrEquals) {
        final MapleInventoryType type = GameConstants.getInventoryType(itemid);
        int possesed = this.inventory[type.ordinal()].countById(itemid);
        if (checkEquipped && type == MapleInventoryType.EQUIP) {
            possesed += this.inventory[MapleInventoryType.EQUIPPED.ordinal()].countById(itemid);
        }
        if (greaterOrEquals) {
            return possesed >= quantity;
        }
        return possesed == quantity;
    }
    
    public final boolean haveItem(final int itemid, final int quantity) {
        return this.haveItem(itemid, quantity, true, true);
    }
    
    public final boolean haveItem(final int itemid) {
        return this.haveItem(itemid, 1, true, true);
    }
    
    public final short haveItemPos(final int itemid) {
        final MapleInventoryType type = GameConstants.getInventoryType(itemid);
        final IItem findById = this.inventory[type.ordinal()].findById(itemid);
        short possesed;
        if (findById != null) {
            possesed = findById.getPosition();
        }
        else {
            possesed = 100;
        }
        return possesed;
    }
    
    public void dropNPC(final String message) {
        this.client.sendPacket(MaplePacketCreator.getNPCTalk(9010000, (byte)0, message, "00 00", (byte)0));
    }
    
    public void dropNPC(final int npc, final String message) {
        this.client.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte)0, message, "00 00", (byte)0));
    }
    
    public boolean getItemVac() {
        return this.itemVacs;
    }
    
    public void startItemVac() {
        (this.ItemVac = new ItemVac(this)).start();
        this.itemVacs = true;
    }
    
    public void stopItemVac() {
        if (this.itemVacs) {
            this.ItemVac.interrupt();
            this.itemVacs = false;
        }
    }
    
    public final int getCombo() {
        return this.combo;
    }
    
    public void setCombo(final int combo) {
        this.combo = combo;
        this.lastCombo = System.currentTimeMillis();
        this.getClient().getSession().writeAndFlush((Object)MaplePacketCreator.updateCombo(combo));
        if (combo % 10 == 0 && combo >= 10 && combo <= 100) {
            if (this.getSkillLevel(21000000) < combo / 10) {
                return;
            }
            if (combo == 9 && this.getQuestStatus(10370) == 0) {
                this.giftMedal(1142134);
                MapleQuest.getInstance(10370).forceComplete(this, 0);
                this.dropMessage(5, "您剛才拿到了連續技高手勳章。");
            }
            if (combo == 4999 && this.getQuestStatus(10371) == 0) {
                this.giftMedal(1142135);
                MapleQuest.getInstance(10371).forceComplete(this, 0);
                this.dropMessage(5, "您剛才拿到了連續技達人勳章。");
            }
            if (combo == 14999 && this.getQuestStatus(10372) == 0) {
                this.giftMedal(1142136);
                MapleQuest.getInstance(10372).forceComplete(this, 0);
                this.dropMessage(5, "您剛才拿到了連續技之王勳章。");
            }
            SkillFactory.getSkill(21000000).getEffect(combo / 10).applyComboBuff(this, combo);
        }
        else if (combo < 10) {
            SkillFactory.getSkill(21000000).getEffect(combo / 10).applyComboBuff(this, 0);
        }
    }
    
    public final long getLastCombo() {
        return this.lastCombo;
    }
    
    public void setLastCombo(final long combo) {
        this.lastCombo = combo;
    }
    
    public void dropTopMsg(final String message) {
        this.client.sendPacket(UIPacket.getTopMsg(message));
    }
    
    public void dropTopMsg2(final String message) {
        this.client.sendPacket(UIPacket.getTopMsg1(message));
    }
    
    public byte getBuddyCapacity() {
        return this.buddylist.getCapacity();
    }
    
    public void setBuddyCapacity(final byte capacity) {
        this.buddylist.setCapacity(capacity);
        this.client.sendPacket(MaplePacketCreator.updateBuddyCapacity((int)capacity));
    }
    
    public MapleMessenger getMessenger() {
        return this.messenger;
    }
    
    public void setMessenger(final MapleMessenger messenger) {
        this.messenger = messenger;
    }
    
    public void addCooldown(final int skillId, final long startTime, final long length) {
        this.coolDowns.put(Integer.valueOf(skillId), new MapleCoolDownValueHolder(skillId, startTime, length));
    }
    
    public void removeCooldown(final int skillId) {
        if (this.coolDowns.containsKey((Object)Integer.valueOf(skillId))) {
            this.coolDowns.remove((Object)Integer.valueOf(skillId));
        }
    }
    
    public boolean skillisCooling(final int skillId) {
        return this.coolDowns.containsKey((Object)Integer.valueOf(skillId));
    }
    
    public void giveCoolDowns(final int skillid, final long starttime, final long length) {
        this.addCooldown(skillid, starttime, length);
    }
    
    public void giveCoolDowns(final List<MapleCoolDownValueHolder> cooldowns) {
        if (cooldowns != null) {
            for (final MapleCoolDownValueHolder cooldown : cooldowns) {
                this.coolDowns.put(Integer.valueOf(cooldown.skillId), cooldown);
                this.client.sendPacket(MaplePacketCreator.skillCooldown(cooldown.skillId, (int)((cooldown.length - ((System.currentTimeMillis() - cooldown.startTime > cooldown.length) ? 0L : (System.currentTimeMillis() - cooldown.startTime))) / 1000L)));
            }
        }
        else {
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
                 final PreparedStatement ps = con.prepareStatement("SELECT SkillID,StartTime,length FROM skills_cooldowns WHERE charid = ?")) {
                ps.setInt(1, this.getId());
                final ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getLong("length") + rs.getLong("StartTime") - System.currentTimeMillis() <= 0L) {
                        continue;
                    }
                    this.giveCoolDowns(rs.getInt("SkillID"), rs.getLong("StartTime"), rs.getLong("length"));
                }
                rs.close();
                this.deleteWhereCharacterId(con, "DELETE FROM skills_cooldowns WHERE charid = ?");
            }
            catch (SQLException e) {
                FilePrinter.printError("MapleCharcter.txt", (Throwable)e, "Error while retriving cooldown from SQL storage");
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            }
        }
    }
    
    public List<MapleCoolDownValueHolder> getCooldowns() {
        return new ArrayList<MapleCoolDownValueHolder>((Collection<? extends MapleCoolDownValueHolder>)this.coolDowns.values());
    }
    
    public final List<MapleDiseaseValueHolder> getAllDiseases() {
        return new ArrayList<MapleDiseaseValueHolder>((Collection<? extends MapleDiseaseValueHolder>)this.diseases.values());
    }
    
    public final boolean hasDisease(final MapleDisease dis) {
        return this.diseases.keySet().contains((Object)dis);
    }
    
    public void giveDebuff(final MapleDisease disease, final MobSkill skill) {
        this.giveDebuff(disease, skill.getX(), skill.getDuration(), skill.getSkillId(), skill.getSkillLevel());
    }
    
    public void giveDebuff(final MapleDisease disease, final int x, final long duration, final int skillid, final int level) {
        final List<Pair<MapleDisease, Integer>> debuff = Collections.singletonList(new Pair<MapleDisease, Integer>(disease, Integer.valueOf(x)));
        if (!this.hasDisease(disease) && this.diseases.size() < 2) {
            if (disease != MapleDisease.SEDUCE && disease != MapleDisease.STUN && this.isActiveBuffedValue(2321005)) {
                return;
            }
            this.client.sendPacket(MaplePacketCreator.giveDebuff(debuff, skillid, level, (int)duration));
            this.map.broadcastMessage(this, MaplePacketCreator.giveForeignDebuff(this.id, debuff, skillid, level), false);
            if (this.F().get((Object)Integer.valueOf(FumoSkill.FM("异常抗性"))) != null) {
                final double r1 = Math.ceil(Math.random() * 500.0);
                final int 免疫 = (int)Integer.valueOf(this.F().get((Object)Integer.valueOf(FumoSkill.FM("异常抗性"))));
                if (r1 <= (double)免疫) {
                    this.diseases.put(disease, new MapleDiseaseValueHolder(disease, System.currentTimeMillis(), duration / 100L));
                    this.dropMessage(5, "异常免疫");
                    return;
                }
            }
            if (this.F().get((Object)Integer.valueOf(FumoSkill.FM("异常抗性"))) != null) {
                int 抗性 = (int)Integer.valueOf(this.F().get((Object)Integer.valueOf(FumoSkill.FM("异常抗性"))));
                if (抗性 > 65) {
                    抗性 = 65;
                }
                this.diseases.put(disease, new MapleDiseaseValueHolder(disease, System.currentTimeMillis(), duration / 100L * (long)抗性));
            }
            else {
                this.diseases.put(disease, new MapleDiseaseValueHolder(disease, System.currentTimeMillis(), duration));
            }
        }
    }
    
    public void getDiseaseBuff(final MapleDisease disease, final MobSkill skill) {
        this.getDiseaseBuff(disease, skill.getX(), skill.getDuration(), skill.getSkillId(), skill.getSkillLevel());
    }
    
    public void getDiseaseBuff(final MapleDisease disease, final int x, final long duration, final int skillid, final int level) {
        final List<Pair<MapleDisease, Integer>> debuff = Collections.singletonList(new Pair<MapleDisease, Integer>(disease, Integer.valueOf(x)));
        if (!this.hasDisease(disease) && this.diseases.size() < 2) {
            if (disease != MapleDisease.SEDUCE && disease != MapleDisease.STUN && this.isActiveBuffedValue(2321005)) {
                return;
            }
            this.diseases.put(disease, new MapleDiseaseValueHolder(disease, System.currentTimeMillis(), duration));
            this.client.sendPacket(MaplePacketCreator.giveDebuff(debuff, skillid, level, (int)duration));
            this.map.broadcastMessage(this, MaplePacketCreator.giveForeignDebuff(this.id, debuff, skillid, level), false);
        }
    }
    
    public final void giveSilentDebuff(final List<MapleDiseaseValueHolder> ld) {
        if (ld != null) {
            for (final MapleDiseaseValueHolder disease : ld) {
                this.diseases.put(disease.disease, disease);
            }
        }
    }
    
    public void dispelDebuff(final MapleDisease debuff) {
        if (this.hasDisease(debuff)) {
            final long mask = debuff.getValue();
            final boolean first = debuff.isFirst();
            this.diseases.remove((Object)debuff);
            this.client.sendPacket(MaplePacketCreator.cancelDebuff(mask, first));
            this.map.broadcastMessage(this, MaplePacketCreator.cancelForeignDebuff(this.id, mask, first), false);
        }
    }
    
    public void dispelDebuffs() {
        this.dispelDebuff(MapleDisease.CURSE);
        this.dispelDebuff(MapleDisease.DARKNESS);
        this.dispelDebuff(MapleDisease.POISON);
        this.dispelDebuff(MapleDisease.SEAL);
        this.dispelDebuff(MapleDisease.STUN);
        this.dispelDebuff(MapleDisease.WEAKEN);
    }
    
    public void cancelAllDebuffs() {
        this.diseases.clear();
    }
    
    public void setLevel(final short level) {
        this.level = level;
    }
    
    public void sendNote(final String to, final String msg) {
        this.sendNote(to, msg, 0);
    }
    
    public void sendNote(final String to, final String msg, final int fame) {
        MapleCharacterUtil.sendNote(to, this.getName(), msg, fame);
    }
    
    public void showNote() {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM notes WHERE `to`=?", 1005, 1008)) {
            ps.setString(1, this.getName());
            try (final ResultSet rs = ps.executeQuery()) {
                rs.last();
                final int count = rs.getRow();
                rs.first();
                this.client.sendPacket(MTSCSPacket.showNotes(rs, count));
            }
        }
        catch (SQLException e) {
            FilePrinter.printError("MapleCharacter.txt", (Throwable)e, "Unable to show note");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
    }
    
    public void deleteNote(final int id, final int fame) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT gift FROM notes WHERE `id`=?");
            ps.setInt(1, id);
            final ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt("gift") == fame && fame > 0) {
                this.addFame(fame);
                this.updateSingleStat(MapleStat.FAME, (int)this.getFame());
                this.client.sendPacket(MaplePacketCreator.getShowFameGain(fame));
            }
            rs.close();
            ps.close();
            ps = con.prepareStatement("DELETE FROM notes WHERE `id`=?");
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        }
        catch (SQLException e) {
            System.err.println("Unable to delete note" + (Object)e);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
    }
    
    public int getmulungEnergy() {
        return this.mulung_energy;
    }
    
    public void mulungEnergyModify(final boolean inc) {
        if (inc) {
            if (this.mulung_energy + 100 > 10000) {
                this.mulung_energy = 10000;
            }
            else {
                this.mulung_energy += 100;
            }
        }
        else {
            this.mulung_energy = 0;
        }
        if (this.isAdmin()) {
            this.mulung_energy = 10000;
        }
        this.client.sendPacket(MaplePacketCreator.MulungEnergy((int)this.mulung_energy));
    }
    
    public void writeMulungEnergy() {
        this.client.sendPacket(MaplePacketCreator.MulungEnergy((int)this.mulung_energy));
    }
    
    public void writeEnergy(final String type, final String inc) {
        this.client.sendPacket(MaplePacketCreator.sendPyramidEnergy(type, inc));
    }
    
    public void writeStatus(final String type, final String inc) {
        this.client.sendPacket(MaplePacketCreator.sendGhostStatus(type, inc));
    }
    
    public void writePoint(final String type, final String inc) {
        this.client.sendPacket(MaplePacketCreator.sendGhostPoint(type, inc));
    }
    
    public final long getKeyDownSkill_Time() {
        return this.keydown_skill;
    }
    
    public void setKeyDownSkill_Time(final long keydown_skill) {
        this.keydown_skill = keydown_skill;
    }
    
    public void checkBerserk() {
        if (this.BerserkSchedule != null) {
            this.BerserkSchedule.cancel(false);
            this.BerserkSchedule = null;
        }
        final ISkill BerserkX = SkillFactory.getSkill(1320006);
        final int skilllevel = this.getSkillLevel(BerserkX);
        if (skilllevel >= 1) {
            final MapleStatEffect ampStat = BerserkX.getEffect(skilllevel);
            this.stats.Berserk = (this.stats.getHp() * 100 / this.stats.getMaxHp() <= ampStat.getX());
            try {
                this.BerserkSchedule = BuffTimer.getInstance().schedule((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        MapleCharacter.this.checkBerserk();
                    }
                }, 10000L);
            }
            catch (RejectedExecutionException ex) {}
        }
    }
    
    private void prepareBeholderEffect() {
        if (this.beholderHealingSchedule != null) {
            this.beholderHealingSchedule.cancel(false);
        }
        if (this.beholderBuffSchedule != null) {
            this.beholderBuffSchedule.cancel(false);
        }
        final ISkill bHealing = SkillFactory.getSkill(1320008);
        final int bHealingLvl = this.getSkillLevel(bHealing);
        final int berserkLvl = this.getSkillLevel(SkillFactory.getSkill(1320006));
        if (bHealingLvl > 0) {
            final MapleStatEffect healEffect = bHealing.getEffect(bHealingLvl);
            final int healInterval = healEffect.getX() * 1000;
            this.beholderHealingSchedule = BuffTimer.getInstance().register((Runnable)new Runnable() {
                @Override
                public void run() {
                    final int remhppercentage = (int)Math.ceil((double)MapleCharacter.this.getStat().getHp() * 100.0 / (double)MapleCharacter.this.getStat().getMaxHp());
                    if (berserkLvl == 0 || remhppercentage >= berserkLvl + 10) {
                        MapleCharacter.this.addHP((int)healEffect.getHp());
                    }
                }
            }, (long)healInterval, (long)healInterval);
        }
        final ISkill bBuff = SkillFactory.getSkill(1320009);
        final int bBuffLvl = this.getSkillLevel(bBuff);
        if (bBuffLvl > 0) {
            final MapleStatEffect buffEffect = bBuff.getEffect(bBuffLvl);
            final int buffInterval = buffEffect.getX() * 1000;
            this.beholderBuffSchedule = BuffTimer.getInstance().register((Runnable)new Runnable() {
                @Override
                public void run() {
                    buffEffect.applyTo(MapleCharacter.this);
                    client.sendPacket(MaplePacketCreator.showOwnBuffEffect(1321007, 2));
                    map.broadcastMessage(MaplePacketCreator.summonSkill(MapleCharacter.this.getId(), 1321007, Randomizer.nextInt(3) + 6));
                    map.broadcastMessage(MapleCharacter.this, MaplePacketCreator.showBuffeffect(MapleCharacter.this.getId(), 1321007, 2), false);
                }
            }, (long)buffInterval, (long)buffInterval);
        }
    }
    
    public void setChalkboard(final String text) {
        this.chalktext = text;
        this.map.broadcastMessage(MTSCSPacket.useChalkboard(this.getId(), text));
    }
    
    public String getChalkboard() {
        return this.chalktext;
    }
    
    public MapleMount getMount() {
        return this.mount;
    }
    
    public int gmLevel() {
        return this.gmLevel;
    }
    
    public int[] getWishlist() {
        return this.wishlist;
    }
    
    public void clearWishlist() {
        for (int i = 0; i < 10; ++i) {
            this.wishlist[i] = 0;
        }
    }
    
    public int getWishlistSize() {
        int ret = 0;
        for (int i = 0; i < 10; ++i) {
            if (this.wishlist[i] > 0) {
                ++ret;
            }
        }
        return ret;
    }
    
    public void setWishlist(final int[] wl) {
        this.wishlist = wl;
    }
    
    public int[] getRocks() {
        return this.rocks;
    }
    
    public int getRockSize() {
        int ret = 0;
        for (int i = 0; i < 10; ++i) {
            if (this.rocks[i] != 999999999) {
                ++ret;
            }
        }
        return ret;
    }
    
    public void deleteFromRocks(final int map) {
        for (int i = 0; i < 10; ++i) {
            if (this.rocks[i] == map) {
                this.rocks[i] = 999999999;
                break;
            }
        }
    }
    
    public void addRockMap() {
        if (this.getRockSize() >= 10) {
            return;
        }
        this.rocks[this.getRockSize()] = this.getMapId();
    }
    
    public boolean isRockMap(final int id) {
        for (int i = 0; i < 10; ++i) {
            if (this.rocks[i] == id) {
                return true;
            }
        }
        return false;
    }
    
    public int[] getRegRocks() {
        return this.regrocks;
    }
    
    public int getRegRockSize() {
        int ret = 0;
        for (int i = 0; i < 5; ++i) {
            if (this.regrocks[i] != 999999999) {
                ++ret;
            }
        }
        return ret;
    }
    
    public void deleteFromRegRocks(final int map) {
        for (int i = 0; i < 5; ++i) {
            if (this.regrocks[i] == map) {
                this.regrocks[i] = 999999999;
                break;
            }
        }
    }
    
    public void addRegRockMap() {
        if (this.getRegRockSize() >= 5) {
            return;
        }
        this.regrocks[this.getRegRockSize()] = this.getMapId();
    }
    
    public boolean isRegRockMap(final int id) {
        for (int i = 0; i < 5; ++i) {
            if (this.regrocks[i] == id) {
                return true;
            }
        }
        return false;
    }
    
    public List<LifeMovementFragment> getLastRes() {
        return this.lastres;
    }
    
    public void setLastRes(final List<LifeMovementFragment> lastres) {
        this.lastres = lastres;
    }
    
    public void setMonsterBookCover(final int bookCover) {
        this.bookCover = bookCover;
    }
    
    public int getMonsterBookCover() {
        return this.bookCover;
    }
    
    public String getAccountSecondPassword() {
        return this.accountsecondPassword;
    }
    
    public int 获得破功() {
        final Connection con = DatabaseConnection.getConnection();
        try {
            int count = 0;
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM z_pg WHERE CharID = ? ");
            ps.setInt(1, this.accountid);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(3);
            }
            else {
                this.创建破功();
            }
            rs.close();
            ps.close();
            return count;
        }
        catch (Exception Ex) {
            System.err.println("Error while read bosslog." + (Object)Ex);
            return 0;
        }
    }
    
    public int 创建破功() {
        final Connection con = DatabaseConnection.getConnection();
        try {
            final int count = 0;
            final PreparedStatement ps = con.prepareStatement("Insert into z_pg(CharID,PG) values (?,?)");
            ps.setInt(1, this.accountid);
            ps.setInt(2, 0);
            ps.executeUpdate();
            ps.close();
            return count;
        }
        catch (Exception Ex) {
            System.err.println("Error while read bosslog." + (Object)Ex);
            return -1;
        }
    }
    
    public void 添加破功(final int pg) {
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("UPDATE z_pg SET PG = ? WHERE CharID = ?");
            ps.setInt(1, pg + this.获得破功());
            ps.setInt(2, this.accountid);
            ps.execute();
            ps.close();
        }
        catch (Exception Ex) {
            System.err.println("错误" + (Object)Ex);
        }
    }
    
    public int getBossLog(final String boss) {
        return this.getBossLog(boss, 0);
    }
    
    public int getBossLog(final String boss, final int type) {
        try {
            int count = 0;
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM bosslog WHERE characterid = ? AND bossid = ?");
            ps.setInt(1, this.id);
            ps.setString(2, boss);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
                if (count < 0) {
                    return count;
                }
                final Timestamp bossTime = rs.getTimestamp("time");
                rs.close();
                ps.close();
                if (type == 0) {
                    final Calendar sqlcal = Calendar.getInstance();
                    if (bossTime != null) {
                        sqlcal.setTimeInMillis(bossTime.getTime());
                    }
                    if (sqlcal.get(5) + 1 <= Calendar.getInstance().get(5) || sqlcal.get(2) + 1 <= Calendar.getInstance().get(2) || sqlcal.get(1) + 1 <= Calendar.getInstance().get(1)) {
                        count = 0;
                        ps = con.prepareStatement("UPDATE bosslog SET count = 0, time = CURRENT_TIMESTAMP() WHERE characterid = ? AND bossid = ?");
                        ps.setInt(1, this.id);
                        ps.setString(2, boss);
                        ps.executeUpdate();
                    }
                }
            }
            else {
                final PreparedStatement psu = con.prepareStatement("INSERT INTO bosslog (characterid, bossid, count, type) VALUES (?, ?, ?, ?)");
                psu.setInt(1, this.id);
                psu.setString(2, boss);
                psu.setInt(3, 0);
                psu.setInt(4, type);
                psu.executeUpdate();
                psu.close();
            }
            rs.close();
            ps.close();
            return count;
        }
        catch (Exception Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
            return -1;
        }
    }
    
    public void setBossLog(final String boss) {
        this.setBossLog(boss, 0);
    }
    
    public void setBossLog(final String boss, final int type) {
        this.setBossLog(boss, type, 1);
    }
    
    public void setBossLog(final String boss, final int type, final int count) {
        final int bossCount = this.getBossLog(boss, type);
        try {
            final PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE bosslog SET count = ?, type = ?, time = CURRENT_TIMESTAMP() WHERE characterid = ? AND bossid = ?");
            ps.setInt(1, bossCount + count);
            ps.setInt(2, type);
            ps.setInt(3, this.id);
            ps.setString(4, boss);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
        }
    }
    
    public void resetBossLog(final String boss) {
        this.resetBossLog(boss, 0);
    }
    
    public void resetBossLog(final String boss, final int type) {
        try {
            final PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE bosslog SET count = ?, type = ?, time = CURRENT_TIMESTAMP() WHERE characterid = ? AND bossid = ?");
            ps.setInt(1, 0);
            ps.setInt(2, type);
            ps.setInt(3, this.id);
            ps.setString(4, boss);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
        }
    }
    
    public int getBossLogAcc(final String boss) {
        try {
            int count = 0;
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM bosslog WHERE accountid = ? AND bossid = ?");
            ps.setInt(1, this.accountid);
            ps.setString(2, boss);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
                if (count < 0) {
                    return count;
                }
                final Timestamp bossTime = rs.getTimestamp("time");
                rs.close();
                ps.close();
                final Calendar sqlcal = Calendar.getInstance();
                if (bossTime != null) {
                    sqlcal.setTimeInMillis(bossTime.getTime());
                }
                if (sqlcal.get(5) + 1 <= Calendar.getInstance().get(5) || sqlcal.get(2) + 1 <= Calendar.getInstance().get(2) || sqlcal.get(1) + 1 <= Calendar.getInstance().get(1)) {
                    count = 0;
                    ps = con.prepareStatement("UPDATE bosslog SET count = 0, time = CURRENT_TIMESTAMP() WHERE accountid = ? AND bossid = ?");
                    ps.setInt(1, this.accountid);
                    ps.setString(2, boss);
                    ps.executeUpdate();
                }
            }
            else {
                final PreparedStatement psu = con.prepareStatement("INSERT INTO bosslog (accountid, characterid, bossid, count) VALUES (?, ?, ?, ?)");
                psu.setInt(1, this.accountid);
                psu.setInt(2, 0);
                psu.setString(3, boss);
                psu.setInt(4, 0);
                psu.executeUpdate();
                psu.close();
            }
            rs.close();
            ps.close();
            return count;
        }
        catch (Exception Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
            return -1;
        }
    }
    
    public void setBossLogAcc(final String bossid) {
        this.setBossLogAcc(bossid, 0);
    }
    
    public void setBossLogAcc(final String bossid, int bossCount) {
        bossCount += this.getBossLogAcc(bossid);
        try {
            final PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE bosslog SET count = ?, characterid = ?, time = CURRENT_TIMESTAMP() WHERE accountid = ? AND bossid = ?");
            ps.setInt(1, bossCount + 1);
            ps.setInt(2, this.id);
            ps.setInt(3, this.accountid);
            ps.setString(4, bossid);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
        }
    }
    
    public int getAccNewTime(final String time) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("select count(*) from accounts where id = ? and createdat <= '" + time + "'");
            ps.setInt(1, this.accountid);
            int ret_count;
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ret_count = rs.getInt(1);
                }
                else {
                    ret_count = -1;
                }
            }
            ps.close();
            return ret_count;
        }
        catch (Exception Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
            return -1;
        }
    }
    
    public int getOneTimeLog(final String log) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            int ret_count = 0;
            final PreparedStatement ps = con.prepareStatement("select count(*) from OneTimelog where characterid = ? and log = ?");
            ps.setInt(1, this.id);
            ps.setString(2, log);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ret_count = rs.getInt(1);
            }
            else {
                ret_count = -1;
            }
            rs.close();
            ps.close();
            return ret_count;
        }
        catch (Exception Wx) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Wx);
            return -1;
        }
    }
    
    public int getAddLog() {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            int money = 0;
            final PreparedStatement ps = con.prepareStatement("SELECT money FROM addlog WHERE accid = ?");
            ps.setInt(1, this.getClient().getAccID());
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                money += rs.getInt("money");
            }
            rs.close();
            ps.close();
            return money;
        }
        catch (SQLException e) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            return -1;
        }
    }
    
    public void setOneTimeLog(final String log) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("insert into OneTimelog (characterid, log) values (?,?)");
            ps.setInt(1, this.id);
            ps.setString(2, log);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception Wx) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Wx);
        }
    }
    
    public void deleteOneTimeLog(final String log) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("DELETE FROM onetimelog WHERE characterid = ? and log = ?");
            ps.setInt(1, this.id);
            ps.setString(2, log);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException Wx) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Wx);
        }
    }
    
    public void setPrizeLog(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("insert into Prizelog (accid, bossid) values (?,?)");
            ps.setInt(1, this.getClient().getAccID());
            ps.setString(2, bossid);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception Wx) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Wx);
        }
    }
    
    public int getPrizeLog(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            int ret_count = 0;
            final PreparedStatement ps = con.prepareStatement("select count(*) from Prizelog where accid = ? and bossid = ?");
            ps.setInt(1, this.getClient().getAccID());
            ps.setString(2, bossid);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ret_count = rs.getInt(1);
            }
            else {
                ret_count = -1;
            }
            rs.close();
            ps.close();
            return ret_count;
        }
        catch (Exception Wx) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Wx);
            return -1;
        }
    }
    
    public void setAcLog(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("insert into Aclog (accid, bossid) values (?,?)");
            ps.setInt(1, this.getClient().getAccID());
            ps.setString(2, bossid);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception Wx) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Wx);
        }
    }
    
    public void setAcLogS(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("insert into Aclog (accid, bossid) values (?,?)");
            ps.setInt(1, this.getAccountID());
            ps.setString(2, bossid);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception Wx) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Wx);
        }
    }
    
    public int getAcLog(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            int ret_count = 0;
            final PreparedStatement ps = con.prepareStatement("select count(*) from Aclog where accid = ? and bossid = ? and lastattempt >= subtime(current_timestamp, '1 0:0:0.0')");
            ps.setInt(1, this.getClient().getAccID());
            ps.setString(2, bossid);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ret_count = rs.getInt(1);
            }
            else {
                ret_count = -1;
            }
            rs.close();
            ps.close();
            return ret_count;
        }
        catch (Exception Wx) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Wx);
            return -1;
        }
    }
    
    public int getAcLogS(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            int ret_count = 0;
            final PreparedStatement ps = con.prepareStatement("select count(*) from Aclog where accid = ? and bossid = ?");
            ps.setInt(1, this.getClient().getAccID());
            ps.setString(2, bossid);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ret_count = rs.getInt(1);
            }
            else {
                ret_count = -1;
            }
            rs.close();
            ps.close();
            return ret_count;
        }
        catch (Exception Wx) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Wx);
            return -1;
        }
    }
    
    public void dropMessage(final int type, final String message) {
        if (type == -1) {
            this.client.sendPacket(UIPacket.getTopMsg(message));
        }
        else if (type == -2) {
            this.client.sendPacket(PlayerShopPacket.shopChat(message, 0));
        }
        else if (type == -11) {
            this.client.getSession().writeAndFlush((Object)MaplePacketCreator.yellowChat(message));
        }
        else {
            this.client.sendPacket(MaplePacketCreator.serverNotice(type, message));
        }
    }
    
    public void showInfo(final String caption, final boolean pink, String msg) {
        final short type = (short)(pink ? 5 : 6);
        if (caption != null && !caption.isEmpty()) {
            msg = "[" + caption + "] " + msg;
        }
        this.dropMessage((int)type, msg);
        this.dropMessage(-1, msg);
    }
    
    public IMaplePlayerShop getPlayerShop() {
        return this.playerShop;
    }
    
    public void setPlayerShop(final IMaplePlayerShop playerShop) {
        this.playerShop = playerShop;
    }
    
    public int getConversation() {
        return this.inst.get();
    }
    
    public void setConversation(final int inst) {
        this.inst.set(inst);
    }
    
    public MapleCarnivalParty getCarnivalParty() {
        return this.carnivalParty;
    }
    
    public void setCarnivalParty(final MapleCarnivalParty party) {
        this.carnivalParty = party;
    }
    
    public void addCP(final int ammount) {
        this.totalCP += (short)ammount;
        this.availableCP += (short)ammount;
    }
    
    public void useCP(final int ammount) {
        if (this.availableCP >= ammount) {
            this.availableCP -= (short)ammount;
        }
    }
    
    public int getAvailableCP() {
        return this.availableCP;
    }
    
    public int getTotalCP() {
        return this.totalCP;
    }
    
    public void resetCP() {
        this.totalCP = 0;
        this.availableCP = 0;
    }
    
    public static int getIdByName(final String name) {
        try {
            int id;
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
                 final PreparedStatement ps = con.prepareStatement("SELECT id FROM characters WHERE name = ?")) {
                ps.setString(1, name);
                try (final ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        rs.close();
                        ps.close();
                        return -1;
                    }
                    id = rs.getInt("id");
                }
            }
            return id;
        }
        catch (Exception e) {
            System.err.println("錯誤 'getIdByName' " + (Object)e);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            return -1;
        }
    }
    
    public void addCarnivalRequest(final MapleCarnivalChallenge request) {
        this.pendingCarnivalRequests.add(request);
    }
    
    public final MapleCarnivalChallenge getNextCarnivalRequest() {
        return (MapleCarnivalChallenge)this.pendingCarnivalRequests.pollLast();
    }
    
    public void clearCarnivalRequests() {
        this.pendingCarnivalRequests = new LinkedList<MapleCarnivalChallenge>();
    }
    
    public void startMonsterCarnival(final int enemyavailable, final int enemytotal) {
        this.client.sendPacket(MonsterCarnivalPacket.startMonsterCarnival(this, enemyavailable, enemytotal));
    }
    
    public void CPUpdate(final boolean party, final int available, final int total, final int team) {
        this.client.sendPacket(MonsterCarnivalPacket.CPUpdate(party, available, total, team));
    }
    
    public void playerDiedCPQ(final String name, final int lostCP, final int team) {
        this.client.sendPacket(MonsterCarnivalPacket.playerDiedMessage(name, lostCP, team));
    }
    
    public boolean getCanTalk() {
        return this.canTalk;
    }
    
    public void canTalk(final boolean talk) {
        this.canTalk = talk;
    }
    
    public int getEXPMod() {
        return this.stats.expMod;
    }
    
    public int getDropMod() {
        return this.stats.dropMod;
    }
    
    public double getDropm() {
        return this.stats.dropm;
    }
    
    public double getExpm() {
        return this.stats.expm;
    }
    
    public int getCashMod() {
        return this.stats.cashMod;
    }
    
    public void setVPoints(final int p) {
        this.vpoints = p;
    }
    
    public int getVPoints() {
        return this.vpoints;
    }
    
    public CashShop getCashInventory() {
        return this.cs;
    }
    
    public void removeAll(final int id) {
        this.removeAll(id, false);
    }
    
    public void removeAll(final int id, final boolean show) {
        this.removeAll(id, false, false);
    }
    
    public void removeAll(final int id, final boolean show, final boolean equip) {
        MapleInventoryType type = GameConstants.getInventoryType(id);
        int possessed = this.getInventory(type).countById(id);
        if (possessed > 0) {
            MapleInventoryManipulator.removeById(this.getClient(), type, id, possessed, true, false);
            if (show) {
                this.getClient().sendPacket(MaplePacketCreator.getShowItemGain(id, (short)(-possessed), true));
            }
        }
        if (equip && type == MapleInventoryType.EQUIP) {
            type = MapleInventoryType.EQUIPPED;
            possessed = this.getInventory(type).countById(id);
            if (possessed > 0) {
                MapleInventoryManipulator.removeById(this.getClient(), type, id, possessed, true, false);
                this.getClient().sendPacket(MaplePacketCreator.getShowItemGain(id, (short)(-possessed), true));
            }
        }
    }
    
    public MapleRing getMarriageRing(final boolean incluedEquip) {
        MapleInventory iv = this.getInventory(MapleInventoryType.EQUIPPED);
        final Collection<IItem> equippedC = iv.list();
        final List<Item> equipped = new ArrayList<Item>(equippedC.size());
        for (final IItem item : equippedC) {
            equipped.add((Item)item);
        }
        for (final Item item2 : equipped) {
            if (item2.getRing() != null) {
                final MapleRing ring = item2.getRing();
                ring.setEquipped(true);
                if (GameConstants.isMarriageRing(item2.getItemId())) {
                    return ring;
                }
                continue;
            }
        }
        if (incluedEquip) {
            iv = this.getInventory(MapleInventoryType.EQUIP);
            for (final IItem item : iv.list()) {
                if (item.getRing() != null && GameConstants.isMarriageRing(item.getItemId())) {
                    final MapleRing ring = item.getRing();
                    ring.setEquipped(false);
                    return ring;
                }
            }
        }
        return null;
    }
    
    public Pair<List<MapleRing>, List<MapleRing>> getRings(final boolean equip) {
        MapleInventory iv = this.getInventory(MapleInventoryType.EQUIPPED);
        final Collection<IItem> equippedC = iv.list();
        final List<Item> equipped = new ArrayList<Item>(equippedC.size());
        for (final IItem item : equippedC) {
            equipped.add((Item)item);
        }
        Collections.sort(equipped);
        final List<MapleRing> crings = new ArrayList<MapleRing>();
        final List<MapleRing> frings = new ArrayList<MapleRing>();
        for (final Item item2 : equipped) {
            if (item2.getRing() != null) {
                final MapleRing ring = item2.getRing();
                ring.setEquipped(true);
                if (!GameConstants.isEffectRing(item2.getItemId())) {
                    continue;
                }
                if (equip) {
                    if (GameConstants.isCrushRing(item2.getItemId())) {
                        crings.add(ring);
                    }
                    else {
                        if (!GameConstants.isFriendshipRing(item2.getItemId())) {
                            continue;
                        }
                        frings.add(ring);
                    }
                }
                else if (crings.isEmpty() && GameConstants.isCrushRing(item2.getItemId())) {
                    crings.add(ring);
                }
                else {
                    if (!frings.isEmpty() || !GameConstants.isFriendshipRing(item2.getItemId())) {
                        continue;
                    }
                    frings.add(ring);
                }
            }
        }
        if (equip) {
            iv = this.getInventory(MapleInventoryType.EQUIP);
            for (final IItem item3 : iv.list()) {
                if (item3.getRing() != null && GameConstants.isEffectRing(item3.getItemId())) {
                    final MapleRing ring = item3.getRing();
                    ring.setEquipped(false);
                    if (GameConstants.isFriendshipRing(item3.getItemId())) {
                        frings.add(ring);
                    }
                    else {
                        if (!GameConstants.isCrushRing(item3.getItemId())) {
                            continue;
                        }
                        crings.add(ring);
                    }
                }
            }
        }
        Collections.sort(frings, (Comparator<? super MapleRing>)new RingComparator());
        Collections.sort(crings, (Comparator<? super MapleRing>)new RingComparator());
        return new Pair<List<MapleRing>, List<MapleRing>>(crings, frings);
    }
    
    public int getFH() {
        final MapleFoothold fh = this.getMap().getFootholds().findBelow(this.getPosition());
        if (fh != null) {
            return fh.getId();
        }
        return 0;
    }
    
    public void startFairySchedule(final boolean exp) {
        this.startFairySchedule(exp, false);
    }
    
    public void startFairySchedule(final boolean exp, final boolean equipped) {
        try {
            this.cancelFairySchedule(exp);
            if (this.fairyExp < 30 && this.stats.equippedFairy) {
                if (equipped) {
                    this.dropMessage(5, "您装备了精灵吊坠在1小时后经验获取将增加到 " + (this.fairyExp + 30) + "%");
                }
                this.fairySchedule = EtcTimer.getInstance().schedule((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (fairyExp < 30 && stats.equippedFairy) {
                            fairyExp = (byte)30;
                            ++fairyHour;
                            MapleCharacter.this.dropMessage(5, "因装备精灵吊坠超过了" + (int)fairyHour + "小时，打怪時可以额外获得紅利经验值" + (int)fairyExp + "%.");
                            MapleCharacter.this.startFairySchedule(false, true);
                        }
                        else {
                            MapleCharacter.this.cancelFairySchedule(!stats.equippedFairy);
                        }
                    }
                }, 3600000L);
            }
            else {
                this.cancelFairySchedule(!this.stats.equippedFairy);
            }
        }
        catch (RejectedExecutionException ex) {}
    }
    
    public void cancelFairySchedule(final boolean exp) {
        if (this.fairySchedule != null) {
            this.fairySchedule.cancel(false);
            this.fairySchedule = null;
        }
        if (exp) {
            final int 精灵吊坠 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"精灵吊坠经验加成"));
            this.fairyExp = (byte)精灵吊坠;
        }
    }
    
    public void 人气经验加成() {
        final int 人气 = this.getFame() * (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"人气经验加成"));
        this.gainExp(人气, true, false, false);
    }
    
    public void 白银VIP经验加成(final boolean exp, final boolean equipped) {
        this.白银VIP经验加成(exp);
    }
    
    public void 白银VIP经验加成(final boolean exp) {
        if (exp) {
            this.fairyExp += 30;
        }
    }
    
    public byte getFairyExp() {
        return this.fairyExp;
    }
    
    public void setFairyExp(final byte Exp) {
        this.fairyExp = Exp;
    }
    
    public int getCoconutTeam() {
        return this.coconutteam;
    }
    
    public void setCoconutTeam(final int team) {
        this.coconutteam = team;
    }
    
    public void spawnPet(final byte slot) {
        this.spawnPet(slot, false, true);
    }
    
    public void spawnPet(final byte slot, final boolean lead) {
        this.spawnPet(slot, lead, true);
    }
    
    public void spawnPet(final byte slot, final boolean lead, final boolean broadcast) {
        final IItem item = this.getInventory(MapleInventoryType.CASH).getItem((short)slot);
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (item == null || item.getItemId() > 5010000 || item.getItemId() < 5000000) {
            return;
        }
        switch (item.getItemId()) {
            case 5000028:
            case 5000047: {
                final MaplePet pet = MaplePet.createPet(item.getItemId() + 1, MapleInventoryIdentifier.getInstance());
                if (pet != null) {
                    MapleInventoryManipulator.addById(this.client, item.getItemId() + 1, (short)1, item.getOwner(), pet, 45L);
                    MapleInventoryManipulator.removeFromSlot(this.client, MapleInventoryType.CASH, (short)slot, (short)1, false);
                    break;
                }
                break;
            }
            default: {
                final MaplePet pet = item.getPet();
                if (pet == null || (item.getItemId() == 5000054 && pet.getSecondsLeft() <= 0) || (item.getExpiration() != -1L && item.getExpiration() <= System.currentTimeMillis())) {
                    break;
                }
                if (pet.getSummoned()) {
                    this.unequipPet(pet, false);
                    break;
                }
                int leadid = 8;
                if (GameConstants.isKOC((int)this.getJob())) {
                    leadid = 10000018;
                }
                else if (GameConstants.isAran((int)this.getJob())) {
                    leadid = 20000024;
                }
                if (this.getSkillLevel(SkillFactory.getSkill(leadid)) == 0 && this.getSummonedPet(0) != null) {
                    this.unequipPet(this.getSummonedPet(0), false);
                }
                else if (lead) {
                    this.shiftPetsRight();
                }
                final Point pos = this.getPosition();
                pet.setPos(pos);
                try {
                    pet.setFh(this.getMap().getFootholds().findBelow(pos).getId());
                }
                catch (NullPointerException e) {
                    pet.setFh(0);
                }
                pet.setStance(0);
                pet.setSummoned(this.getPetSlotNext() + 1);
                this.addPet(pet);
                if (this.getMap() != null) {
                    this.getMap().broadcastMessage(this, PetPacket.showPet(this, pet, false, false), true);
                    this.client.sendPacket(PetPacket.loadExceptionList(this, pet));
                    this.client.sendPacket(PetPacket.petStatUpdate(this));
                }
                break;
            }
        }
        this.client.sendPacket(PetPacket.emptyStatUpdate());
    }
    
    public void addMoveMob(final int mobid) {
        if (this.movedMobs.containsKey((Object)Integer.valueOf(mobid))) {
            this.movedMobs.put(Integer.valueOf(mobid), Integer.valueOf((int)Integer.valueOf(this.movedMobs.get((Object)Integer.valueOf(mobid))) + 1));
            if ((int)Integer.valueOf(this.movedMobs.get((Object)Integer.valueOf(mobid))) > 30) {
                for (final MapleCharacter chr : this.getMap().getCharactersThreadsafe()) {
                    if (chr.getMoveMobs().containsKey((Object)Integer.valueOf(mobid))) {
                        chr.getClient().sendPacket(MobPacket.killMonster(mobid, 1));
                        chr.getMoveMobs().remove((Object)Integer.valueOf(mobid));
                    }
                }
            }
        }
        else {
            this.movedMobs.put(Integer.valueOf(mobid), Integer.valueOf(1));
        }
    }
    
    public Map<Integer, Integer> getMoveMobs() {
        return this.movedMobs;
    }
    
    public int getLinkMid() {
        return this.linkMid;
    }
    
    public void setLinkMid(final int lm) {
        this.linkMid = lm;
    }
    
    public boolean isClone() {
        return this.clone;
    }
    
    public void setClone(final boolean c) {
        this.clone = c;
    }
    
    public WeakReference<MapleCharacter>[] getClones() {
        return this.clones;
    }
    
    public MapleCharacter cloneLooks() {
        final MapleClient cloneClient = new MapleClient(null, null, (Channel)new MockIOSession());
        final int minus = this.getId() + Randomizer.nextInt(this.getId());
        final MapleCharacter ret = new MapleCharacter(true);
        ret.id = minus;
        ret.client = cloneClient;
        ret.exp = 0;
        ret.meso = 0;
        ret.beans = this.beans;
        ret.blood = this.blood;
        ret.month = this.month;
        ret.jf = this.jf;
        ret.zdjf = this.zdjf;
        ret.rwjf = this.rwjf;
        ret.zs = this.zs;
        ret.cz = this.cz;
        ret.dy = this.dy;
        ret.rmb = this.rmb;
        ret.yb = this.yb;
        ret.playerPoints = this.playerPoints;
        ret.playerEnergy = this.playerEnergy;
        ret.jf1 = this.jf1;
        ret.jf2 = this.jf2;
        ret.jf3 = this.jf3;
        ret.jf4 = this.jf4;
        ret.jf5 = this.jf5;
        ret.jf6 = this.jf6;
        ret.jf7 = this.jf7;
        ret.jf8 = this.jf8;
        ret.jf9 = this.jf9;
        ret.jf10 = this.jf10;
        ret.day = this.day;
        ret.charmessage = this.charmessage;
        ret.expression = this.expression;
        ret.constellation = this.constellation;
        ret.remainingAp = 0;
        ret.fame = 0;
        ret.accountid = this.client.getAccID();
        ret.name = this.name;
        ret.level = this.level;
        ret.fame = this.fame;
        ret.job = this.job;
        ret.hair = this.hair;
        ret.face = this.face;
        ret.skinColor = this.skinColor;
        ret.bookCover = this.bookCover;
        ret.monsterbook = this.monsterbook;
        ret.mount = this.mount;
        ret.CRand = new PlayerRandomStream();
        ret.gmLevel = this.gmLevel;
        ret.gender = this.gender;
        ret.mapid = this.map.getId();
        ret.map = this.map;
        ret.setStance(this.getStance());
        ret.chair = this.chair;
        ret.itemEffect = this.itemEffect;
        ret.guildid = this.guildid;
        ret.currentrep = this.currentrep;
        ret.totalrep = this.totalrep;
        ret.stats = this.stats;
        ret.effects.putAll((Map<? extends MapleBuffStat, ? extends MapleBuffStatValueHolder>)this.effects);
        if (ret.effects.get((Object)MapleBuffStat.ILLUSION) != null) {
            ret.effects.remove((Object)MapleBuffStat.ILLUSION);
        }
        if (ret.effects.get((Object)MapleBuffStat.SUMMON) != null) {
            ret.effects.remove((Object)MapleBuffStat.SUMMON);
        }
        if (ret.effects.get((Object)MapleBuffStat.PUPPET) != null) {
            ret.effects.remove((Object)MapleBuffStat.PUPPET);
        }
        ret.guildrank = this.guildrank;
        ret.allianceRank = this.allianceRank;
        ret.hidden = this.hidden;
        ret.setPosition(new Point(this.getPosition()));
        for (final IItem equip : this.getInventory(MapleInventoryType.EQUIPPED)) {
            ret.getInventory(MapleInventoryType.EQUIPPED).addFromDB(equip);
        }
        ret.skillMacros = this.skillMacros;
        ret.keylayout = this.keylayout;
        ret.questinfo = this.questinfo;
        ret.savedLocations = this.savedLocations;
        ret.wishlist = this.wishlist;
        ret.rocks = this.rocks;
        ret.regrocks = this.regrocks;
        ret.buddylist = this.buddylist;
        ret.keydown_skill = 0L;
        ret.lastmonthfameids = this.lastmonthfameids;
        ret.lastfametime = this.lastfametime;
        ret.storage = this.storage;
        ret.cs = this.cs;
        ret.client.setAccountName(this.client.getAccountName());
        ret.maplepoints = this.maplepoints;
        ret.clone = true;
        ret.client.setChannel(this.client.getChannel());
        while (this.map.getCharacterById(ret.id) != null || this.client.getChannelServer().getPlayerStorage().getCharacterById(ret.id) != null) {
            final MapleCharacter mapleCharacter = ret;
            ++mapleCharacter.id;
        }
        ret.client.setPlayer(ret);
        return ret;
    }
    
    public final void cloneLook() {
        if (this.clone) {
            return;
        }
        for (int i = 0; i < this.clones.length; ++i) {
            if (this.clones[i].get() == null) {
                final MapleCharacter newp = this.cloneLooks();
                this.map.addPlayer(newp);
                this.map.broadcastMessage(MaplePacketCreator.updateCharLook(newp));
                this.map.movePlayer(newp, this.getPosition());
                this.clones[i] = new WeakReference<MapleCharacter>(newp);
                return;
            }
        }
    }
    
    public final void disposeClones() {
        this.numClones = 0;
        for (int i = 0; i < this.clones.length; ++i) {
            if (this.clones[i].get() != null) {
                this.map.removePlayer((MapleCharacter)this.clones[i].get());
                ((MapleCharacter)this.clones[i].get()).getClient().disconnect(false, false);
                this.clones[i] = new WeakReference<MapleCharacter>(null);
                ++this.numClones;
            }
        }
    }
    
    public final int getCloneSize() {
        int z = 0;
        for (final WeakReference<MapleCharacter> clone1 : this.clones) {
            if (clone1.get() != null) {
                ++z;
            }
        }
        return z;
    }
    
    public void spawnClones() {
        if (this.numClones == 0 && this.stats.hasClone) {
            this.cloneLook();
        }
        for (int i = 0; i < this.numClones; ++i) {
            this.cloneLook();
        }
        this.numClones = 0;
    }
    
    public byte getNumClones() {
        return this.numClones;
    }
    
    public final void spawnSavedPets() {
        for (int i = 0; i < this.petStore.length; ++i) {
            if (this.petStore[i] > -1) {
                this.spawnPet(this.petStore[i], false, false);
            }
        }
        this.client.sendPacket(PetPacket.petStatUpdate(this));
        this.petStore = new byte[] { -1, -1, -1 };
    }
    
    public final byte[] getPetStores() {
        return this.petStore;
    }
    
    public void resetStats(final int str, final int dex, final int int_, final int luk) {
        final Map<MapleStat, Integer> statup = new EnumMap<MapleStat, Integer>(MapleStat.class);
        int total = this.stats.getStr() + this.stats.getDex() + this.stats.getLuk() + this.stats.getInt() + this.getRemainingAp();
        total -= str;
        this.stats.setStr((short)str);
        total -= dex;
        this.stats.setDex((short)dex);
        total -= int_;
        this.stats.setInt((short)int_);
        total -= luk;
        this.stats.setLuk((short)luk);
        this.setRemainingAp((short)total);
        statup.put(MapleStat.STR, Integer.valueOf(str));
        statup.put(MapleStat.DEX, Integer.valueOf(dex));
        statup.put(MapleStat.INT, Integer.valueOf(int_));
        statup.put(MapleStat.LUK, Integer.valueOf(luk));
        statup.put(MapleStat.AVAILABLEAP, Integer.valueOf(total));
        this.client.sendPacket(MaplePacketCreator.updatePlayerStats(statup, false, this));
    }
    
    public Event_PyramidSubway getPyramidSubway() {
        return this.pyramidSubway;
    }
    
    public void setPyramidSubway(final Event_PyramidSubway ps) {
        this.pyramidSubway = ps;
    }
    
    public byte getSubcategory() {
        if (this.job >= 430 && this.job <= 434) {
            return 1;
        }
        return this.subcategory;
    }
    
    public int itemQuantity(final int itemid) {
        return this.getInventory(GameConstants.getInventoryType(itemid)).countById(itemid);
    }
    
    public void setRPS(final RockPaperScissors rps) {
        this.rps = rps;
    }
    
    public RockPaperScissors getRPS() {
        return this.rps;
    }
    
    public long getNextConsume() {
        return this.nextConsume;
    }
    
    public void setNextConsume(final long nc) {
        this.nextConsume = nc;
    }
    
    public int getRank() {
        return this.rank;
    }
    
    public int getRankMove() {
        return this.rankMove;
    }
    
    public int getJobRank() {
        return this.jobRank;
    }
    
    public int getJobRankMove() {
        return this.jobRankMove;
    }
    
    public void dispelBuff() {
        final LinkedList<Entry<MapleBuffStat, MapleBuffStatValueHolder>> allBuffs = new LinkedList<Entry<MapleBuffStat, MapleBuffStatValueHolder>>((Collection<? extends Entry<MapleBuffStat, MapleBuffStatValueHolder>>)this.effects.entrySet());
        for (final Entry<MapleBuffStat, MapleBuffStatValueHolder> mbsvh : allBuffs) {
            final long startTime = ((MapleBuffStatValueHolder)mbsvh.getValue()).startTime;
            final long localDuration = (long)((MapleBuffStatValueHolder)mbsvh.getValue()).localDuration;
            final long nowtime = System.currentTimeMillis();
            if (startTime + localDuration - nowtime < 8000L) {
                this.dispelBuff(((MapleBuffStatValueHolder)mbsvh.getValue()).skillid);
            }
        }
    }
    
    public void ForcechangeChannel(final int channel) {
        final ChannelServer toch = ChannelServer.getInstance(channel);
        try {
            this.saveToDB(false, false);
        }
        catch (Exception ex) {
            FileoutputUtil.logToFile("logs/ForcechangeChannel保存数据異常.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + this.getClient().getSession().remoteAddress().toString().split(":")[0] + " 账号 " + this.getClient().getAccountName() + " 账号ID " + this.getClient().getAccID() + " 角色名 " + this.getName() + " 角色ID " + this.getId());
            FileoutputUtil.outError("logs/ForcechangeChannel保存数据異常.txt", (Throwable)ex);
        }
        if (toch == null || toch.isShutdown()) {
            this.client.sendPacket(MaplePacketCreator.serverBlocked(1));
            return;
        }
        this.changeRemoval();
        this.dispelBuff();
        final ChannelServer ch = ChannelServer.getInstance(this.client.getChannel());
        if (this.getMessenger() != null) {
            Messenger.silentLeaveMessenger(this.getMessenger().getId(), new MapleMessengerCharacter(this));
        }
        PlayerBuffStorage.addBuffsToStorage(this.getId(), this.getAllBuffs());
        PlayerBuffStorage.addCooldownsToStorage(this.getId(), this.getCooldowns());
        PlayerBuffStorage.addDiseaseToStorage(this.getId(), this.getAllDiseases());
        World.channelChangeData(new CharacterTransfer(this), this.getId(), channel);
        ch.removePlayer(this);
        this.client.updateLoginState(6, this.client.getSessionIPAddress());
        this.client.sendPacket(MaplePacketCreator.getChannelChange(this.client, Integer.parseInt(toch.getSocket().split(":")[1])));
        this.getMap().removePlayer(this);
        this.client.setPlayer(null);
        this.client.setReceiving(false);
    }
    
    public void changeChannel(final int channel) {
        final ChannelServer toch = ChannelServer.getInstance(channel);
        try {
            this.saveToDB(false, false);
        }
        catch (Exception ex) {
            FileoutputUtil.logToFile("logs/更換頻道保存数据異常.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + this.getClient().getSession().remoteAddress().toString().split(":")[0] + " 账号 " + this.getClient().getAccountName() + " 账号ID " + this.getClient().getAccID() + " 角色名 " + this.getName() + " 角色ID " + this.getId());
            FileoutputUtil.outError("logs/更換頻道保存数据異常.txt", (Throwable)ex);
        }
        if (channel == this.client.getChannel() || toch == null || toch.isShutdown()) {
            this.client.sendPacket(MaplePacketCreator.serverBlocked(1));
            return;
        }
        this.dispelBuff();
        this.changeRemoval();
        final ChannelServer ch = ChannelServer.getInstance(this.client.getChannel());
        if (this.getMessenger() != null) {
            Messenger.silentLeaveMessenger(this.getMessenger().getId(), new MapleMessengerCharacter(this));
        }
        PlayerBuffStorage.addBuffsToStorage(this.getId(), this.getAllBuffs());
        PlayerBuffStorage.addCooldownsToStorage(this.getId(), this.getCooldowns());
        PlayerBuffStorage.addDiseaseToStorage(this.getId(), this.getAllDiseases());
        World.channelChangeData(new CharacterTransfer(this), this.getId(), channel);
        if (ch != null) {
            ch.removePlayer(this);
        }
        this.client.updateLoginState(6, this.client.getSessionIPAddress());
        this.client.sendPacket(MaplePacketCreator.getChannelChange(this.client, Integer.parseInt(toch.getSocket().split(":")[1])));
        this.getMap().removePlayer(this);
        if (!LoginServer.CanLoginKey(this.getLoginKey(), this.getAccountID()) || (LoginServer.getLoginKey(this.getAccountID()) == null && !this.getLoginKey().isEmpty())) {
            FileoutputUtil.logToFile("logs/Data/客戶端登錄KEY異常.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + this.client.getSession().remoteAddress().toString().split(":")[0] + " 账号: " + this.client.getAccountName() + " 客戶端key：" + LoginServer.getLoginKey(this.getAccountID()) + " 伺服端key：" + this.getLoginKey() + " 更換頻道10");
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM 密语系統] 非法更換頻道 账号 " + this.client.getAccountName()));
            this.client.getSession().close();
            return;
        }
        if (!LoginServer.CanServerKey(this.getServerKey(), this.getAccountID()) || (LoginServer.getServerKey(this.getAccountID()) == null && !this.getServerKey().isEmpty())) {
            FileoutputUtil.logToFile("logs/Data/客戶端頻道KEY異常.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + this.client.getSession().remoteAddress().toString().split(":")[0] + " 账号: " + this.client.getAccountName() + " 客戶端key：" + LoginServer.getServerKey(this.getAccountID()) + " 伺服端key：" + this.getServerKey() + " 更換頻道11");
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM 密语系統] 非法更換頻道 账号 " + this.client.getAccountName()));
            this.client.getSession().close();
            return;
        }
        if (!LoginServer.CanClientKey(this.getClientKey(), this.getAccountID()) || (LoginServer.getClientKey(this.getAccountID()) == null && !this.getClientKey().isEmpty())) {
            FileoutputUtil.logToFile("logs/Data/客戶端進入KEY異常.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + this.client.getSession().remoteAddress().toString().split(":")[0] + " 账号: " + this.client.getAccountName() + " 客戶端key：" + LoginServer.getClientKey(this.getAccountID()) + " 伺服端key：" + this.getClientKey() + " 更換頻道12");
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM 密语系統] 非法更換頻道 账号 " + this.client.getAccountName()));
            this.client.getSession().close();
            return;
        }
        final List<String> charNamesa = this.client.loadCharacterNamesByCharId(this.getId());
        for (final ChannelServer cs : ChannelServer.getAllInstances()) {
            for (final String name : charNamesa) {
                if (cs.getPlayerStorage().getCharacterByName(name) != null) {
                    FileoutputUtil.logToFile("logs/Data/非法登錄.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + this.client.getSession().remoteAddress().toString().split(":")[0] + " 账号 " + this.client.getAccountName() + "更換頻道1");
                    Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM 密语系統] 非法更換頻道 账号 " + this.client.getAccountName()));
                    this.client.getSession().close();
                    return;
                }
            }
        }
        for (final String name2 : charNamesa) {
            if (CashShopServer.getPlayerStorage().getCharacterByName(name2) != null) {
                FileoutputUtil.logToFile("logs/Data/非法登錄.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + this.client.getSession().remoteAddress().toString().split(":")[0] + " 账号 " + this.client.getAccountName() + "更換頻道2");
                Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM 密语系統] 非法更換頻道 账号 " + this.client.getAccountName()));
                this.client.getSession().close();
                return;
            }
        }
        final List<String> charNames = this.client.loadCharacterNamesByCharId(this.getId());
        for (final ChannelServer cs2 : ChannelServer.getAllInstances()) {
            for (final String name3 : charNames) {
                final MapleCharacter character = cs2.getPlayerStorage().getCharacterByName(name3);
                if (character != null) {
                    FileoutputUtil.logToFile("logs/Data/非法登錄.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + this.client.getSession().remoteAddress().toString().split(":")[0] + " 账号 " + this.client.getAccountName() + "更換頻道3");
                    Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM 密语系統] 非法更換頻道 账号 " + this.client.getAccountName()));
                    this.client.getSession().close();
                    character.getClient().getSession().close();
                }
            }
        }
        for (final String name4 : charNames) {
            final MapleCharacter charactercs = CashShopServer.getPlayerStorage().getCharacterByName(name4);
            if (charactercs != null) {
                FileoutputUtil.logToFile("logs/Data/非法登錄.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + this.client.getSession().remoteAddress().toString().split(":")[0] + " 账号 " + this.client.getAccountName() + "更換頻道4");
                Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM 密语系統] 非法更換頻道 账号 " + this.client.getAccountName()));
                this.client.getSession().close();
                charactercs.getClient().getSession().close();
            }
        }
        this.client.setPlayer(null);
        this.client.setReceiving(false);
        this.expirationTask(true, false);
    }
    
    public void expandInventory(final byte type, final int amount) {
        final MapleInventory inv = this.getInventory(MapleInventoryType.getByType(type));
        inv.addSlot((byte)amount);
        this.client.sendPacket(MaplePacketCreator.getSlotUpdate(type, inv.getSlotLimit()));
    }
    
    public boolean allowedToTarget(final MapleCharacter other) {
        return other != null && (!other.isHidden() || this.getGMLevel() >= other.getGMLevel());
    }
    
    public int getFollowId() {
        return this.followid;
    }
    
    public void setFollowId(final int fi) {
        this.followid = fi;
        if (fi == 0) {
            this.followinitiator = false;
            this.followon = false;
        }
    }
    
    public void setFollowInitiator(final boolean fi) {
        this.followinitiator = fi;
    }
    
    public void setFollowOn(final boolean fi) {
        this.followon = fi;
    }
    
    public boolean isFollowOn() {
        return this.followon;
    }
    
    public boolean isFollowInitiator() {
        return this.followinitiator;
    }
    
    public void checkFollow() {
        if (this.followon) {
            this.map.broadcastMessage(MaplePacketCreator.followEffect(this.id, 0, null));
            this.map.broadcastMessage(MaplePacketCreator.followEffect(this.followid, 0, null));
            final MapleCharacter tt = this.map.getCharacterById(this.followid);
            this.client.sendPacket(MaplePacketCreator.getFollowMessage("Follow canceled."));
            if (tt != null) {
                tt.setFollowId(0);
                tt.getClient().sendPacket(MaplePacketCreator.getFollowMessage("Follow canceled."));
            }
            this.setFollowId(0);
        }
    }
    
    public int getMarriageId() {
        return this.marriageId;
    }
    
    public void setMarriageId(final int mi) {
        this.marriageId = mi;
    }
    
    public int getMarriageItemId() {
        return this.marriageItemId;
    }
    
    public void setMarriageItemId(final int mi) {
        this.marriageItemId = mi;
    }
    
    public boolean isStaff() {
        return this.gmLevel > PlayerGMRank.普通玩家.getLevel();
    }
    
    public boolean startPartyQuest(final int questid) {
        boolean ret = false;
        if (!this.quests.containsKey((Object)MapleQuest.getInstance(questid)) || !this.questinfo.containsKey((Object)Integer.valueOf(questid))) {
            final MapleQuestStatus status = this.getQuestNAdd(MapleQuest.getInstance(questid));
            status.setStatus((byte)1);
            this.updateQuest(status);
            switch (questid) {
                case 1300:
                case 1301:
                case 1302: {
                    this.updateInfoQuest(questid, "min=0;sec=0;date=0000-00-00;have=0;rank=F;try=0;cmp=0;CR=0;VR=0;gvup=0;vic=0;lose=0;draw=0");
                    break;
                }
                case 1204: {
                    this.updateInfoQuest(questid, "min=0;sec=0;date=0000-00-00;have0=0;have1=0;have2=0;have3=0;rank=F;try=0;cmp=0;CR=0;VR=0");
                    break;
                }
                case 1206: {
                    this.updateInfoQuest(questid, "min=0;sec=0;date=0000-00-00;have0=0;have1=0;rank=F;try=0;cmp=0;CR=0;VR=0");
                    break;
                }
                default: {
                    this.updateInfoQuest(questid, "min=0;sec=0;date=0000-00-00;have=0;rank=F;try=0;cmp=0;CR=0;VR=0");
                    break;
                }
            }
            ret = true;
        }
        return ret;
    }
    
    public String getOneInfo(final int questid, final String key) {
        if (!this.questinfo.containsKey((Object)Integer.valueOf(questid)) || key == null) {
            return null;
        }
        final String[] split3;
        final String[] split = split3 = ((String)this.questinfo.get((Object)Integer.valueOf(questid))).split(";");
        for (final String x : split3) {
            final String[] split2 = x.split("=");
            if (split2.length == 2 && split2[0].equals((Object)key)) {
                return split2[1];
            }
        }
        return null;
    }
    
    public void updateOneInfo(final int questid, final String key, final String value) {
        if (!this.questinfo.containsKey((Object)Integer.valueOf(questid)) || key == null || value == null) {
            return;
        }
        final String[] split = ((String)this.questinfo.get((Object)Integer.valueOf(questid))).split(";");
        boolean changed = false;
        final StringBuilder newQuest = new StringBuilder();
        for (final String x : split) {
            final String[] split2 = x.split("=");
            if (split2.length == 2) {
                if (split2[0].equals((Object)key)) {
                    newQuest.append(key).append("=").append(value);
                }
                else {
                    newQuest.append(x);
                }
                newQuest.append(";");
                changed = true;
            }
        }
        this.updateInfoQuest(questid, changed ? newQuest.toString().substring(0, newQuest.toString().length() - 1) : newQuest.toString());
    }
    
    public void recalcPartyQuestRank(final int questid) {
        if (!this.startPartyQuest(questid)) {
            final String oldRank = this.getOneInfo(questid, "rank");
            if (oldRank == null || oldRank.equals((Object)"S")) {
                return;
            }
            final String s = oldRank;
            int n = -1;
            switch (s.hashCode()) {
                case 65: {
                    if (s.equals((Object)"A")) {
                        n = 0;
                        break;
                    }
                    break;
                }
                case 66: {
                    if (s.equals((Object)"B")) {
                        n = 1;
                        break;
                    }
                    break;
                }
                case 67: {
                    if (s.equals((Object)"C")) {
                        n = 2;
                        break;
                    }
                    break;
                }
                case 68: {
                    if (s.equals((Object)"D")) {
                        n = 3;
                        break;
                    }
                    break;
                }
                case 70: {
                    if (s.equals((Object)"F")) {
                        n = 4;
                        break;
                    }
                    break;
                }
            }
            String newRank = null;
            switch (n) {
                case 0: {
                    newRank = "S";
                    break;
                }
                case 1: {
                    newRank = "A";
                    break;
                }
                case 2: {
                    newRank = "B";
                    break;
                }
                case 3: {
                    newRank = "C";
                    break;
                }
                case 4: {
                    newRank = "D";
                    break;
                }
                default: {
                    return;
                }
            }
            final List<Pair<String, Pair<String, Integer>>> questInfo = MapleQuest.getInstance(questid).getInfoByRank(newRank);
            for (final Pair<String, Pair<String, Integer>> q : questInfo) {
                boolean found = false;
                final String val = this.getOneInfo(questid, (String)((Pair<String, Integer>)q.right).left);
                if (val == null) {
                    return;
                }
                int vall;
                try {
                    vall = Integer.parseInt(val);
                }
                catch (NumberFormatException e) {
                    return;
                }
                final String s2 = (String)q.left;
                int n2 = -1;
                switch (s2.hashCode()) {
                    case 3318169: {
                        if (s2.equals((Object)"less")) {
                            n2 = 0;
                            break;
                        }
                        break;
                    }
                    case 3357525: {
                        if (s2.equals((Object)"more")) {
                            n2 = 1;
                            break;
                        }
                        break;
                    }
                    case 96757556: {
                        if (s2.equals((Object)"equal")) {
                            n2 = 2;
                            break;
                        }
                        break;
                    }
                }
                switch (n2) {
                    case 0: {
                        found = (vall < (int)Integer.valueOf(((Pair<String, Integer>)q.right).right));
                        break;
                    }
                    case 1: {
                        found = (vall > (int)Integer.valueOf(((Pair<String, Integer>)q.right).right));
                        break;
                    }
                    case 2: {
                        found = (vall == (int)Integer.valueOf(((Pair<String, Integer>)q.right).right));
                        break;
                    }
                }
                if (!found) {
                    return;
                }
            }
            this.updateOneInfo(questid, "rank", newRank);
        }
    }
    
    public void tryPartyQuest(final int questid) {
        try {
            this.startPartyQuest(questid);
            this.pqStartTime = System.currentTimeMillis();
            this.updateOneInfo(questid, "try", String.valueOf(Integer.parseInt(this.getOneInfo(questid, "try")) + 1));
        }
        catch (Exception e) {
            FilePrinter.printError("MapleCharacter.txt", (Throwable)e, "tryPartyQuest error");
        }
    }
    
    public void cmpPartyQuest(final int questid, final int sl) {
        this.updateOneInfo(questid, "cmp", String.valueOf(Integer.parseInt(this.getOneInfo(questid, "cmp")) - sl));
    }
    
    public void endPartyQuest(final int questid) {
        try {
            this.startPartyQuest(questid);
            if (this.pqStartTime > 0L) {
                final long changeTime = System.currentTimeMillis() - this.pqStartTime;
                final int mins = (int)(changeTime / 1000L / 60L);
                final int secs = (int)(changeTime / 1000L % 60L);
                final int mins2 = Integer.parseInt(this.getOneInfo(questid, "min"));
                final int secs2 = Integer.parseInt(this.getOneInfo(questid, "sec"));
                if (mins2 <= 0 || mins < mins2) {
                    this.updateOneInfo(questid, "min", String.valueOf(mins));
                    this.updateOneInfo(questid, "sec", String.valueOf(secs));
                    this.updateOneInfo(questid, "date", FilePrinter.getLocalDateString());
                }
                final int newCmp = Integer.parseInt(this.getOneInfo(questid, "cmp")) + 1;
                this.updateOneInfo(questid, "cmp", String.valueOf(newCmp));
                this.updateOneInfo(questid, "CR", String.valueOf((int)Math.ceil((double)newCmp * 100.0 / (double)Integer.parseInt(this.getOneInfo(questid, "try")))));
                this.recalcPartyQuestRank(questid);
                this.pqStartTime = 0L;
            }
        }
        catch (Exception e) {
            FilePrinter.printError("MapleCharacter.txt", (Throwable)e, "endPartyQuest error");
        }
    }
    
    public void havePartyQuest(final int itemId) {
        int index = -1;
        int questid = 0;
        switch (itemId) {
            case 1002798: {
                questid = 1200;
                break;
            }
            case 1072369: {
                questid = 1201;
                break;
            }
            case 1022073: {
                questid = 1202;
                break;
            }
            case 1082232: {
                questid = 1203;
                break;
            }
            case 1002571:
            case 1002572:
            case 1002573:
            case 1002574: {
                questid = 1204;
                index = itemId - 1002571;
                break;
            }
            case 1122010: {
                questid = 1205;
                break;
            }
            case 1032060:
            case 1032061: {
                questid = 1206;
                index = itemId - 1032060;
                break;
            }
            case 3010018: {
                questid = 1300;
                break;
            }
            case 1122007: {
                questid = 1301;
                break;
            }
            case 1122058: {
                questid = 1302;
                break;
            }
            default: {
                return;
            }
        }
        this.startPartyQuest(questid);
        this.updateOneInfo(questid, "have" + (Object)((index == -1) ? "" : Integer.valueOf(index)), "1");
    }
    
    public void resetStatsByJob(final boolean beginnerJob) {
        final int baseJob = beginnerJob ? (this.job % 1000) : (this.job % 1000 / 100 * 100);
        if (baseJob == 100) {
            this.resetStats(25, 4, 4, 4);
        }
        else if (baseJob == 200) {
            this.resetStats(4, 4, 20, 4);
        }
        else if (baseJob == 300 || baseJob == 400) {
            this.resetStats(4, 25, 4, 4);
        }
        else if (baseJob == 500) {
            this.resetStats(4, 20, 4, 4);
        }
    }
    
    public boolean hasSummon() {
        return this.hasSummon;
    }
    
    public void setHasSummon(final boolean summ) {
        this.hasSummon = summ;
    }
    
    public void removeDoor() {
        final MapleDoor door = (MapleDoor)this.getDoors().iterator().next();
        for (final MapleCharacter chr : door.getTarget().getCharactersThreadsafe()) {
            door.sendDestroyData(chr.getClient());
        }
        for (final MapleCharacter chr : door.getTown().getCharactersThreadsafe()) {
            door.sendDestroyData(chr.getClient());
        }
        for (final MapleDoor destroyDoor : this.getDoors()) {
            door.getTarget().removeMapObject((MapleMapObject)destroyDoor);
            door.getTown().removeMapObject((MapleMapObject)destroyDoor);
        }
        this.clearDoors();
    }
    
    public void changeRemoval() {
        this.changeRemoval(false);
    }
    
    public void changeRemoval(final boolean dc) {
        if (this.getTrade() != null) {
            if (this.getTrade().getPartner() != null) {
                final MapleTrade local = this.getTrade();
                final MapleTrade partners = local.getPartner();
                if (local.isLocked() && partners.isLocked()) {
                    this.client.getSession().write((Object)MaplePacketCreator.enableActions());
                }
                else {
                    MapleTrade.cancelTrade(this.getTrade(), this.getClient());
                }
            }
            else {
                MapleTrade.cancelTrade(this.getTrade(), this.client);
            }
        }
        if (this.getCheatTracker() != null) {
            this.getCheatTracker().dispose();
        }
        if (!dc) {
            this.cancelEffectFromBuffStat(MapleBuffStat.MONSTER_RIDING);
            this.cancelEffectFromBuffStat(MapleBuffStat.PUPPET);
        }
        if (this.getPyramidSubway() != null) {
            this.getPyramidSubway().dispose(this);
        }
        if (this.playerShop != null && !dc) {
            this.playerShop.removeVisitor(this);
            if (this.playerShop.isOwner(this)) {
                this.playerShop.setOpen(true);
            }
        }
        if (!this.getDoors().isEmpty()) {
            this.removeDoor();
        }
        this.disposeClones();
        NPCScriptManager.getInstance().dispose(this.client);
    }
    
    public void updateTick(final int newTick) {
        if (this.anticheat != null) {
            this.anticheat.updateTick(newTick);
        }
    }
    
    public boolean canUseFamilyBuff(final MapleFamilyBuffEntry buff) {
        final MapleQuestStatus stat = this.getQuestNAdd(MapleQuest.getInstance(buff.questID));
        if (stat.getCustomData() == null) {
            stat.setCustomData("0");
        }
        return Long.parseLong(stat.getCustomData()) + 86400000L < System.currentTimeMillis();
    }
    
    public void useFamilyBuff(final MapleFamilyBuffEntry buff) {
        final MapleQuestStatus stat = this.getQuestNAdd(MapleQuest.getInstance(buff.questID));
        stat.setCustomData(String.valueOf(System.currentTimeMillis()));
    }
    
    public List<Pair<Integer, Integer>> usedBuffs() {
        final List<Pair<Integer, Integer>> used = new ArrayList<Pair<Integer, Integer>>();
        for (final MapleFamilyBuffEntry buff : MapleFamilyBuff.getBuffEntry()) {
            if (!this.canUseFamilyBuff(buff)) {
                used.add(new Pair<Integer, Integer>(Integer.valueOf(buff.index), Integer.valueOf(buff.count)));
            }
        }
        return used;
    }
    
    public String getTeleportName() {
        return this.teleportname;
    }
    
    public void setTeleportName(final String tname) {
        this.teleportname = tname;
    }
    
    public int getNoJuniors() {
        if (this.mfc == null) {
            return 0;
        }
        return this.mfc.getNoJuniors();
    }
    
    public MapleFamilyCharacter getMFC() {
        return this.mfc;
    }
    
    public void makeMFC(final int familyid, final int seniorid, final int junior1, final int junior2) {
        if (familyid > 0) {
            final MapleFamily f = Family.getFamily(familyid);
            if (f == null) {
                this.mfc = null;
            }
            else {
                this.mfc = f.getMFC(this.id);
                if (this.mfc == null) {
                    this.mfc = f.addFamilyMemberInfo(this, seniorid, junior1, junior2);
                }
                if (this.mfc.getSeniorId() != seniorid) {
                    this.mfc.setSeniorId(seniorid);
                }
                if (this.mfc.getJunior1() != junior1) {
                    this.mfc.setJunior1(junior1);
                }
                if (this.mfc.getJunior2() != junior2) {
                    this.mfc.setJunior2(junior2);
                }
            }
        }
        else {
            this.mfc = null;
        }
    }
    
    public int 取破攻等级() {
        int 破功等级 = 0;
        try {
            final int cid = this.getId();
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement limitCheck = con.prepareStatement("SELECT * FROM characters WHERE id=" + cid + "");
            final ResultSet rs = limitCheck.executeQuery();
            if (rs.next()) {
                破功等级 = rs.getInt("PGSXDJ");
            }
            limitCheck.close();
            rs.close();
        }
        catch (SQLException ex) {}
        return 破功等级;
    }
    
    public void setFamily(final int newf, final int news, final int newj1, final int newj2) {
        if (this.mfc == null || newf != this.mfc.getFamilyId() || news != this.mfc.getSeniorId() || newj1 != this.mfc.getJunior1() || newj2 != this.mfc.getJunior2()) {
            this.makeMFC(newf, news, newj1, newj2);
        }
    }
    
    public int maxBattleshipHP(final int skillid) {
        return this.getSkillLevel(skillid) * 5000 + (this.getLevel() - 120) * 3000;
    }
    
    public int currentBattleshipHP() {
        return this.battleshipHP;
    }
    
    public int getGachExp() {
        return this.gachexp;
    }
    
    public void setGachExp(final int ge) {
        this.gachexp = ge;
    }
    
    public void sendEnglishQuiz(final String msg) {
        this.client.sendPacket(MaplePacketCreator.englishQuizMsg(msg));
    }
    
    public void fakeRelog() {
        final int chan = this.client.getChannel();
        this.client.sendPacket(MaplePacketCreator.getCharInfo(this));
        final MapleMap mapp = this.getMap();
        mapp.removePlayer(this);
        mapp.addPlayer(this);
        this.ForcechangeChannel(chan);
    }
    
    public String getcharmessage() {
        return this.charmessage;
    }
    
    public void setcharmessage(String s) {
        if (s.getBytes().length >= 24) {
            s = s.substring(0, 24);
        }
        this.charmessage = s;
    }
    
    public int getexpression() {
        return this.expression;
    }
    
    public void setexpression(final int s) {
        this.expression = s;
    }
    
    public int getconstellation() {
        return this.constellation;
    }
    
    public void setconstellation(final int s) {
        this.constellation = s;
    }
    
    public int getblood() {
        return this.blood;
    }
    
    public void setblood(final int s) {
        this.blood = s;
    }
    
    public int getmonth() {
        return this.month;
    }
    
    public void setmonth(final int s) {
        this.month = s;
    }
    
    public int getday() {
        return this.day;
    }
    
    public void setday(final int s) {
        this.day = s;
    }
    
    public int getTeam() {
        return this.coconutteam;
    }
    
    public int getBeans() {
        return this.beans;
    }
    
    public void gainBeans(final int s) {
        this.beans += s;
    }
    
    public void setBeans(final int s) {
        this.beans = s;
    }
    
    public int getBeansNum() {
        return this.beansNum;
    }
    
    public void setBeansNum(final int beansNum) {
        this.beansNum = beansNum;
    }
    
    public int getBeansRange() {
        return this.beansRange;
    }
    
    public void setBeansRange(final int beansRange) {
        this.beansRange = beansRange;
    }
    
    public boolean isCanSetBeansNum() {
        return this.canSetBeansNum;
    }
    
    public void setCanSetBeansNum(final boolean canSetBeansNum) {
        this.canSetBeansNum = canSetBeansNum;
    }
    
    public boolean getBeansStart() {
        return this.beansStart;
    }
    
    public void setBeansStart(final boolean beansStart) {
        this.beansStart = beansStart;
    }
    
    public boolean haveGM() {
        return this.gmLevel >= 2 && this.gmLevel <= 3;
    }
    
    public void setprefix(final String prefix) {
        this.prefix = prefix;
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public void setPGSXDJ(final int PGSXDJ) {
        this.PGSXDJ = PGSXDJ;
    }
    
    public void gainPGSXDJ(final int PGSXDJ) {
        this.PGSXDJ += PGSXDJ;
    }
    
    public int getPGSXDJ() {
        return this.PGSXDJ;
    }
    
    public void gainItem(final int code, final int amount) {
        MapleInventoryManipulator.addById(this.client, code, (short)amount, null);
    }
    
    public void gainItem(final int code) {
        MapleInventoryManipulator.addById(this.client, code, (short)1, null);
    }
    
    public void giftMedal(final int id) {
        if (!this.getInventory(MapleInventoryType.EQUIP).isFull() && this.getInventory(MapleInventoryType.EQUIP).countById(id) == 0 && this.getInventory(MapleInventoryType.EQUIPPED).countById(id) == 0) {
            MapleInventoryManipulator.addById(this.client, id, (short)1);
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[恭喜]" + this.getName() + "剛才得到了 " + MapleItemInformationProvider.getInstance().getName(id) + "！"));
        }
        else if (this.getInventory(MapleInventoryType.EQUIP).countById(id) == 0 && this.getInventory(MapleInventoryType.EQUIPPED).countById(id) == 0) {
            MapleInventoryManipulator.drop(this.client, MapleInventoryType.EQUIP, (short)1, (short)1);
            MapleInventoryManipulator.addById(this.client, id, (short)1);
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[恭喜]" + this.getName() + "剛才得到了 " + MapleItemInformationProvider.getInstance().getName(id) + "！"));
        }
    }
    
    public final void showInstruction(final String msg, final int width, final int height) {
        this.client.getSession().writeAndFlush((Object)MaplePacketCreator.sendHint(msg, width, height));
    }
    
    public String getVipName() {
        String name = "";
        if (this.getVip() > 0) {
            name = ServerConfig.getVipMedalName(this.getVip());
        }
        return name;
    }
    
    public String getNick() {
        String name = "";
        if (this.getOneTimeLog("关闭VIP星星數顯示") < 1 && this.getVipMedal() && this.getVip() > 0) {
            name += this.getVipName();
        }
        if (this.getGMLevel() > 0) {
            name += "";
        }
        return name;
    }
    
    public boolean getVipMedal() {
        return this.Vip_Medal;
    }
    
    public void setVipMedat(final boolean x) {
        this.Vip_Medal = x;
    }
    
    public void setVipMedal(final boolean x) {
        this.Vip_Medal = x;
    }
    
    public int getVipExpRate() {
        if (this.getVip() <= 5) {
            return (this.getVip() == 0) ? 0 : ((this.getVip() + 1) * 5);
        }
        return (this.getVip() == 0) ? 0 : ((this.getVip() + 1) * 5);
    }
    
    public void control_精灵商人(final boolean control) {
        this.精灵商人购买开关 = control;
    }
    
    public void control_玩家私聊(final boolean control) {
        this.玩家私聊开关 = control;
    }
    
    public void control_玩家密语(final boolean control) {
        this.玩家密语开关 = control;
    }
    
    public void control_好友聊天(final boolean control) {
        this.好友聊天开关 = control;
    }
    
    public void control_队伍聊天(final boolean control) {
        this.队伍聊天开关 = control;
    }
    
    public void control_公会聊天(final boolean control) {
        this.公会聊天开关 = control;
    }
    
    public void control_联盟聊天(final boolean control) {
        this.联盟聊天开关 = control;
    }
    
    public void control_吸怪信息(final boolean control) {
        this.GM吸怪信息开关 = control;
    }
    
    public boolean get_control_精灵商人() {
        return this.精灵商人购买开关;
    }
    
    public boolean get_control_玩家私聊() {
        return this.玩家私聊开关;
    }
    
    public boolean get_control_玩家密语() {
        return this.玩家密语开关;
    }
    
    public boolean get_control_好友聊天() {
        return this.好友聊天开关;
    }
    
    public boolean get_control_队伍聊天() {
        return this.队伍聊天开关;
    }
    
    public boolean get_control_公会聊天() {
        return this.公会聊天开关;
    }
    
    public boolean get_control_联盟聊天() {
        return this.联盟聊天开关;
    }
    
    public boolean get_control_吸怪信息() {
        return this.GM吸怪信息开关;
    }
    
    public int getDiseaseSize() {
        return this.diseases.size();
    }
    
    public int getMSG() {
        return this.MSG;
    }
    
    public void setMSG(final int x) {
        this.MSG = x;
    }
    
    public void addMSG() {
        ++this.MSG;
    }
    
    public void fly() {
    }
    
    public final boolean CanStorage() {
        if (this.lastStorageTime + 5000L > System.currentTimeMillis()) {
            return false;
        }
        this.lastStorageTime = System.currentTimeMillis();
        return true;
    }
    
    public final boolean canHP() {
        if (this.lastHPTime + 5000L > System.currentTimeMillis()) {
            return false;
        }
        this.lastHPTime = System.currentTimeMillis();
        return true;
    }
    
    public final boolean canMP() {
        if (this.lastMPTime + 5000L > System.currentTimeMillis()) {
            return false;
        }
        this.lastMPTime = System.currentTimeMillis();
        return true;
    }
    
    public final boolean canUseMD() {
        if (this.lastMDTime + 5000L > System.currentTimeMillis()) {
            return false;
        }
        this.lastMDTime = System.currentTimeMillis();
        return true;
    }
    
    public void add打怪() {
        ++this.打怪;
    }
    
    public int get打怪() {
        return this.打怪;
    }
    
    public void add吸怪() {
        ++this.吸怪;
    }
    
    public int get吸怪() {
        return this.吸怪;
    }
    
    public void addFly_吸怪() {
        ++this.FLY_吸怪;
    }
    
    public int getFly_吸怪() {
        return this.FLY_吸怪;
    }
    
    public int Aran_ReduceCombo(final int skill) {
        int reduce = 0;
        switch (skill) {
            case 21100004:
            case 21100005: {
                reduce = 30;
                break;
            }
            case 21110004: {
                reduce = 100;
                break;
            }
            case 21120006:
            case 21120007: {
                reduce = 200;
                break;
            }
        }
        return reduce;
    }
    
    public int getAcash() {
        return this.getAcash(this);
    }
    
    public int getAcash(final MapleCharacter chr) {
        final int maxtimes = 10;
        int nowtime = 0;
        final int delay = 500;
        boolean error = false;
        int x = 0;
        do {
            ++nowtime;
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
                final Statement stmt = con.createStatement();
                final ResultSet rs = stmt.executeQuery("Select Acash from Accounts Where id = " + chr.getClient().getAccID());
                while (rs.next()) {
                    int debug = -1;
                    try {
                        debug = rs.getInt("Acash");
                    }
                    catch (Exception ex2) {}
                    if (debug != -1) {
                        x = rs.getInt("Acash");
                        error = false;
                    }
                    else {
                        error = true;
                    }
                }
                rs.close();
            }
            catch (SQLException SQL) {
                System.err.println("[getAcash]無法連接資料庫");
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)SQL);
            }
            catch (Exception ex) {
                FilePrinter.printError("MapleCharacter.txt", (Throwable)ex, "getAcash");
                System.err.println("[getAcash]" + (Object)ex);
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
            if (error) {
                try {
                    Thread.sleep((long)delay);
                }
                catch (Exception ex) {
                    FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
                }
            }
        } while (error && nowtime < maxtimes);
        return x;
    }
    
    public void setAcash(final int x) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("Update Accounts set Acash = ? Where id = ?");
            ps.setInt(1, x);
            ps.setInt(2, this.getClient().getAccID());
            ps.execute();
            ps.close();
        }
        catch (SQLException ex) {
            System.err.println("[Acash]無法連接資料庫");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        catch (Exception ex2) {
            FilePrinter.printError("MapleCharacter.txt", (Throwable)ex2, "SetAcash");
            System.err.println("[setAcash]" + (Object)ex2);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex2);
        }
    }
    
    public int getCZJF() {
        return this.getCZJF(this);
    }
    
    public int getCZJF(final MapleCharacter chr) {
        final int maxtimes = 10;
        int nowtime = 0;
        final int delay = 500;
        boolean error = false;
        int x = 0;
        do {
            ++nowtime;
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
                final Statement stmt = con.createStatement();
                final ResultSet rs = stmt.executeQuery("Select CZJF from Accounts Where id = " + chr.getClient().getAccID());
                while (rs.next()) {
                    int debug = -1;
                    try {
                        debug = rs.getInt("CZJF");
                    }
                    catch (Exception ex2) {}
                    if (debug != -1) {
                        x = rs.getInt("CZJF");
                        error = false;
                    }
                    else {
                        error = true;
                    }
                }
                rs.close();
            }
            catch (SQLException SQL) {
                System.err.println("[getCZJF]無法連接資料庫");
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)SQL);
            }
            catch (Exception ex) {
                FilePrinter.printError("MapleCharacter.txt", (Throwable)ex, "getCZJF");
                System.err.println("[getCZJF]" + (Object)ex);
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
            if (error) {
                try {
                    Thread.sleep((long)delay);
                }
                catch (Exception ex) {
                    FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
                }
            }
        } while (error && nowtime < maxtimes);
        return x;
    }
    
    public void setCZJF(final int x) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("Update Accounts set CZJF = ? Where id = ?");
            ps.setInt(1, x);
            ps.setInt(2, this.getClient().getAccID());
            ps.execute();
            ps.close();
        }
        catch (SQLException ex) {
            System.err.println("[CZJF]無法連接資料庫");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        catch (Exception ex2) {
            FilePrinter.printError("MapleCharacter.txt", (Throwable)ex2, "SetCZJF");
            System.err.println("[setCZJF]" + (Object)ex2);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex2);
        }
    }
    
    public int getTGJF() {
        return this.getTGJF(this);
    }
    
    public int getTGJF(final MapleCharacter chr) {
        final int maxtimes = 10;
        int nowtime = 0;
        final int delay = 500;
        boolean error = false;
        int x = 0;
        do {
            ++nowtime;
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
                final Statement stmt = con.createStatement();
                final ResultSet rs = stmt.executeQuery("Select TGJF from Accounts Where id = " + chr.getClient().getAccID());
                while (rs.next()) {
                    int debug = -1;
                    try {
                        debug = rs.getInt("TGJF");
                    }
                    catch (Exception ex2) {}
                    if (debug != -1) {
                        x = rs.getInt("TGJF");
                        error = false;
                    }
                    else {
                        error = true;
                    }
                }
                rs.close();
            }
            catch (SQLException SQL) {
                System.err.println("[getTGJF]無法連接資料庫");
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)SQL);
            }
            catch (Exception ex) {
                FilePrinter.printError("MapleCharacter.txt", (Throwable)ex, "getTGJF");
                System.err.println("[getTGJF]" + (Object)ex);
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
            if (error) {
                try {
                    Thread.sleep((long)delay);
                }
                catch (Exception ex) {
                    FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
                }
            }
        } while (error && nowtime < maxtimes);
        return x;
    }
    
    public void setTGJF(final int x) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("Update Accounts set TGJF = ? Where id = ?");
            ps.setInt(1, x);
            ps.setInt(2, this.getClient().getAccID());
            ps.execute();
            ps.close();
        }
        catch (SQLException ex) {
            System.err.println("[TGJF]無法連接資料庫");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        catch (Exception ex2) {
            FilePrinter.printError("MapleCharacter.txt", (Throwable)ex2, "SetTGJFF");
            System.err.println("[setTGJF]" + (Object)ex2);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex2);
        }
    }
    
    public int getTJJF() {
        return this.getTJJF(this);
    }
    
    public int getTJJF(final MapleCharacter chr) {
        final int maxtimes = 10;
        int nowtime = 0;
        final int delay = 500;
        boolean error = false;
        int x = 0;
        do {
            ++nowtime;
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
                final Statement stmt = con.createStatement();
                final ResultSet rs = stmt.executeQuery("Select TJJF from Accounts Where id = " + chr.getClient().getAccID());
                while (rs.next()) {
                    int debug = -1;
                    try {
                        debug = rs.getInt("TJJF");
                    }
                    catch (Exception ex2) {}
                    if (debug != -1) {
                        x = rs.getInt("TJJF");
                        error = false;
                    }
                    else {
                        error = true;
                    }
                }
                rs.close();
            }
            catch (SQLException SQL) {
                System.err.println("[getTJJF]無法連接資料庫");
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)SQL);
            }
            catch (Exception ex) {
                FilePrinter.printError("MapleCharacter.txt", (Throwable)ex, "getTJJF");
                System.err.println("[getTJJF]" + (Object)ex);
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
            if (error) {
                try {
                    Thread.sleep((long)delay);
                }
                catch (Exception ex) {
                    FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
                }
            }
        } while (error && nowtime < maxtimes);
        return x;
    }
    
    public void setTJJF(final int x) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("Update Accounts set TJJF = ? Where id = ?");
            ps.setInt(1, x);
            ps.setInt(2, this.getClient().getAccID());
            ps.execute();
            ps.close();
        }
        catch (SQLException ex) {
            System.err.println("[TJJF]無法連接資料庫");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        catch (Exception ex2) {
            FilePrinter.printError("MapleCharacter.txt", (Throwable)ex2, "SetTJJFF");
            System.err.println("[setTJJF]" + (Object)ex2);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex2);
        }
    }
    
    public void modifyJF(final int type, final int quantity) {
        switch (type) {
            case 1: {
                final int CZJF = this.getCZJF();
                if (CZJF + quantity < 0) {
                    return;
                }
                this.setCZJF(CZJF + quantity);
                break;
            }
            case 2: {
                final int TGJF = this.getTGJF();
                if (TGJF + quantity < 0) {
                    return;
                }
                this.setTGJF(TGJF + quantity);
                break;
            }
            case 3: {
                final int TJJF = this.getTJJF();
                if (TJJF + quantity < 0) {
                    return;
                }
                this.setTJJF(TJJF + quantity);
                break;
            }
            case 4: {
                final int DDJF = this.getDDJF();
                if (DDJF + quantity < 0) {
                    return;
                }
                this.setDDJF(DDJF + quantity);
                break;
            }
        }
    }
    
    public boolean getAuto吸怪() {
        return this.auto吸怪;
    }
    
    public void setAuto吸怪(final boolean x) {
        this.auto吸怪 = x;
    }
    
    public void warpAuto吸怪(final MapleCharacter chr_) {
        final MapleCharacter chr = this;
        try {
            if (chr.getMapId() != chr_.getMapId()) {
                chr.changeMap(chr_.getMapId());
                chr.changeMap(chr_.getMap(), chr_.getMap().findClosestSpawnpoint(chr_.getPosition()));
            }
            if (chr.getClient().getChannel() != chr_.getClient().getChannel()) {
                chr.changeChannel(chr_.getClient().getChannel());
            }
        }
        catch (Exception ex) {}
    }
    
    public void setDebugMessage(final boolean control) {
        this.DebugMessage = control;
    }
    
    public boolean getDebugMessage() {
        return this.DebugMessage;
    }
    
    public void RemoveHired() {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("Delete From hiredmerch Where characterid = ?");
            ps.setInt(1, this.id);
            ps.execute();
            ps = con.prepareStatement("Delete From hiredmerchitems Where characterid = ?");
            ps.setInt(1, this.id);
            ps.execute();
            ps.close();
        }
        catch (Exception ex) {
            FilePrinter.printError("MapleCharacter.txt", (Throwable)ex, "RemoveHired");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
    }
    
    public final void maxSkills() {
        for (final ISkill skil : SkillFactory.getAllSkills()) {
            this.changeSkillLevel(skil, skil.getMaxLevel(), skil.getMaxLevel());
        }
    }
    
    public final void clearSkills() {
        for (final ISkill skil : SkillFactory.getAllSkills()) {
            this.changeSkillLevel(skil, (byte)0, (byte)0);
        }
    }
    
    public final void LearnSameSkill(final MapleCharacter victim) {
        for (final ISkill skil : SkillFactory.getAllSkills()) {
            if (victim.getSkillLevel(skil) > 0) {
                this.changeSkillLevel(skil, victim.getSkillLevel(skil), victim.getMasterLevel(skil));
            }
        }
    }
    
    public int getStr() {
        return this.getStat().getStr();
    }
    
    public int getInt() {
        return this.getStat().getInt();
    }
    
    public int getLuk() {
        return this.getStat().getLuk();
    }
    
    public int getDex() {
        return this.getStat().getDex();
    }
    
    public int getHp() {
        return this.getStat().getHp();
    }
    
    public int getMp() {
        return this.getStat().getMp();
    }
    
    public int getMaxHp() {
        return this.getStat().getMaxHp();
    }
    
    public int getMaxMp() {
        return this.getStat().getMaxMp();
    }
    
    public void setHp(final int amount) {
        this.getStat().setHp(amount);
    }
    
    public void setMp(final int amount) {
        this.getStat().setMp(amount);
    }
    
    public void setMaxHp(final int amount) {
        this.getStat().setMaxHp((short)amount);
    }
    
    public void setMaxMp(final int amount) {
        this.getStat().setMaxMp((short)amount);
    }
    
    public void setStr(final int str) {
        this.stats.str = (short)str;
        this.stats.recalcLocalStats(false);
    }
    
    public void setLuk(final int luk) {
        this.stats.luk = (short)luk;
        this.stats.recalcLocalStats(false);
    }
    
    public void setDex(final int dex) {
        this.stats.dex = (short)dex;
        this.stats.recalcLocalStats(false);
    }
    
    public void setInt(final int int_) {
        this.stats.int_ = (short)int_;
        this.stats.recalcLocalStats(false);
    }
    
    public void setMeso(final int mesos) {
        this.meso = mesos;
    }
    
    public void updateFame() {
        this.updateSingleStat(MapleStat.FAME, (int)this.getFame());
    }
    
    public boolean inBossMap() {
        return MapConstants.inBossMap(this.getMapId());
    }
    
    public static boolean isVpn(final String ip) {
        int n = -1;
        switch (ip.hashCode()) {
            case -218956429: {
                if (ip.equals((Object)"/1.34.145.220")) {
                    n = 0;
                    break;
                }
                break;
            }
            case -641759509: {
                if (ip.equals((Object)"/59.125.5.52")) {
                    n = 1;
                    break;
                }
                break;
            }
            case -685134090: {
                if (ip.equals((Object)"/59.126.97.123")) {
                    n = 2;
                    break;
                }
                break;
            }
            case 645706596: {
                if (ip.equals((Object)"/60.251.73.100")) {
                    n = 3;
                    break;
                }
                break;
            }
            case 1562443102: {
                if (ip.equals((Object)"/61.219.216.173")) {
                    n = 4;
                    break;
                }
                break;
            }
            case 1562443103: {
                if (ip.equals((Object)"/61.219.216.174")) {
                    n = 5;
                    break;
                }
                break;
            }
            case 796693722: {
                if (ip.equals((Object)"/61.227.252.169")) {
                    n = 6;
                    break;
                }
                break;
            }
            case -1091107193: {
                if (ip.equals((Object)"/61.228.228.128")) {
                    n = 7;
                    break;
                }
                break;
            }
        }
        switch (n) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean isKOC() {
        return this.job >= 1000 && this.job < 2000;
    }
    
    public boolean isAran() {
        return this.job >= 2000 && this.job <= 2112 && this.job != 2001;
    }
    
    public boolean isAdventurer() {
        return this.job >= 0 && this.job < 1000;
    }
    
    public boolean isCygnus() {
        return this.job >= 1000 && this.job <= 1512;
    }
    
    public boolean isGod() {
        return this.gmLevel >= 100;
    }
    
    public static String getCharacterNameById(final int id) {
        String name = null;
        final MapleCharacter chr = getOnlineCharacterById(id);
        if (chr != null) {
            return chr.getName();
        }
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = null;
            final Statement stmt = con.createStatement();
            ps = con.prepareStatement("select name from characters where id = ?");
            ps.setInt(1, id);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
            }
            ps.close();
            rs.close();
        }
        catch (Exception ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        return name;
    }
    
    public static String getCharacterNameById2(final int id) {
        String name = null;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = null;
            final Statement stmt = con.createStatement();
            ps = con.prepareStatement("select name from characters where id = ?");
            ps.setInt(1, id);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
            }
            ps.close();
            rs.close();
        }
        catch (Exception ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        return name;
    }
    
    public static int getCharacterIdByName(final String name) {
        int id = -1;
        final MapleCharacter chr = getOnlineCharacterByName(name);
        if (chr != null) {
            return chr.getId();
        }
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = null;
            final Statement stmt = con.createStatement();
            ps = con.prepareStatement("select id from characters where name = ?");
            ps.setString(1, name);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
            ps.close();
            rs.close();
        }
        catch (Exception ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        return id;
    }
    
    public static MapleCharacter getOnlineCharacterById(final int cid) {
        MapleCharacter chr = null;
        if (Find.findChannel(cid) >= 1) {
            chr = ChannelServer.getInstance(Find.findChannel(cid)).getPlayerStorage().getCharacterById(cid);
            if (chr != null) {
                return chr;
            }
        }
        return null;
    }
    
    public static MapleCharacter getOnlineCharacterByName(final String name) {
        MapleCharacter chr = null;
        if (Find.findChannel(name) >= 1) {
            chr = ChannelServer.getInstance(Find.findChannel(name)).getPlayerStorage().getCharacterByName(name);
            if (chr != null) {
                return chr;
            }
        }
        return null;
    }
    
    public static MapleCharacter getCharacterById(final int cid) {
        final MapleCharacter chr = getOnlineCharacterById(cid);
        if (chr != null) {
            return chr;
        }
        final String name = getCharacterNameById(cid);
        return (name == null) ? null : loadCharFromDB(cid, new MapleClient(null, null, (Channel)new MockIOSession()), true);
    }
    
    public static MapleCharacter getCharacterByName(final String name) {
        final MapleCharacter chr = getOnlineCharacterByName(name);
        if (chr != null) {
            return chr;
        }
        final int cid = getCharacterIdByName(name);
        return (cid == -1) ? null : loadCharFromDB(cid, new MapleClient(null, null, (Channel)new MockIOSession()), true);
    }
    
    public static void setMP(final Map<MapleCharacter, Integer> GiveList, final boolean showMessage) {
        final Iterator<MapleCharacter> iter = GiveList.keySet().iterator();
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = null;
            while (iter.hasNext()) {
                final StringBuilder sql = new StringBuilder();
                final MapleCharacter chr = (MapleCharacter)iter.next();
                final int MP = (int)Integer.valueOf(GiveList.get((Object)chr));
                sql.append("Update Accounts set MP = ");
                sql.append(chr.getMP() + MP);
                sql.append(" Where id = ");
                sql.append(chr.getAccountID());
                ps = con.prepareStatement(sql.toString());
                ps.execute();
                if (showMessage) {
                    final String MSG = "「在線獎勵」获得在線點數 " + MP + " 若要領取請輸入 @在線點數/@jcds";
                    chr.dropMessage(MSG);
                }
            }
            if (ps != null) {
                ps.close();
            }
        }
        catch (SQLException ex) {
            System.err.println("[setMP]無法連接資料庫 " + (Object)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        catch (Exception ex2) {
            FilePrinter.printError("MapleCharacter.txt", (Throwable)ex2, "setMP");
            System.err.println("[setMP]" + (Object)ex2);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex2);
        }
    }
    
    public void setMP(final int x) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("Update Accounts set MP = ? Where id = ?");
            ps.setInt(1, x);
            ps.setInt(2, this.getClient().getAccID());
            ps.execute();
            ps.close();
        }
        catch (SQLException ex) {
            System.err.println("[setMP]無法連接資料庫 " + (Object)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        catch (Exception ex2) {
            FilePrinter.printError("MapleCharacter.txt", (Throwable)ex2, "setMP");
            System.err.println("[setMP]" + (Object)ex2);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex2);
        }
    }
    
    public int getMP() {
        final int maxtimes = 10;
        int nowtime = 0;
        final int delay = 500;
        boolean error = false;
        int mp = 0;
        do {
            ++nowtime;
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
                final Statement stmt = con.createStatement();
                final ResultSet rs = stmt.executeQuery("Select MP from Accounts Where id = " + this.getClient().getAccID());
                while (rs.next()) {
                    int debug = -1;
                    try {
                        debug = rs.getInt("MP");
                    }
                    catch (Exception ex2) {}
                    if (debug != -1) {
                        mp = rs.getInt("MP");
                        error = false;
                    }
                    else {
                        error = true;
                    }
                }
                rs.close();
            }
            catch (SQLException SQL) {
                System.err.println("[getMP] 無法連接資料庫" + (Object)SQL);
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)SQL);
            }
            catch (Exception ex) {
                FilePrinter.printError("MapleCharacter.txt", (Throwable)ex, "getMP");
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
            if (error) {
                try {
                    Thread.sleep((long)delay);
                }
                catch (Exception ex) {
                    FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
                }
            }
        } while (error && nowtime < maxtimes);
        return mp;
    }
    
    public boolean hasBlockedInventory(final boolean dead) {
        boolean has = false;
        if (dead) {
            has = (!this.isAlive() || this.getTrade() != null || this.getConversation() > 0 || this.getPlayerShop() != null);
        }
        else {
            has = (this.getTrade() != null || this.getConversation() > 0 || this.getPlayerShop() != null);
        }
        return has;
    }
    
    public boolean hasBlockedInventory2(final boolean dead) {
        boolean has = false;
        if (dead) {
            has = (!this.isAlive() || this.getTrade() != null || this.getPlayerShop() != null);
        }
        else {
            has = (this.getTrade() != null || this.getPlayerShop() != null);
        }
        return has;
    }
    
    public String getNowMacs() {
        return this.nowmacs;
    }
    
    public void setNowMacs(final String macs) {
        this.nowmacs = macs;
    }
    
    public int loadVip(final int accountID) {
        int vip = 0;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("SELECT vip FROM accounts WHERE id = ?");
            ps.setInt(1, accountID);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                vip = rs.getShort("vip");
                ps.close();
                rs.close();
            }
        }
        catch (SQLException e) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
        return vip;
    }
    
    public Map<Integer, MapleBuffStatValueHolder> getSkillID() {
        return this.skillID;
    }
    
    public void startMapEffect(final String msg, final int itemId) {
        this.startMapEffect(msg, itemId, 10000);
    }
    
    public void startMapEffect(final String msg, final int itemId, final int duration) {
        final MapleMapEffect mapEffect = new MapleMapEffect(msg, itemId);
        this.getClient().getSession().writeAndFlush((Object)mapEffect.makeStartData());
        EventTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                MapleCharacter.this.getClient().getSession().writeAndFlush((Object)mapEffect.makeDestroyData());
            }
        }, (long)duration);
    }
    
    public void forceCompleteQuest(final int id) {
        MapleQuest.getInstance(id).forceComplete(this, 0);
    }
    
    public String getLoginKey() {
        return this.loginkey;
    }
    
    public String getServerKey() {
        return this.serverkey;
    }
    
    public String getClientKey() {
        return this.clientkey;
    }
    
    public boolean chrdangerousIp(final String lip) {
        final String ip = lip.substring(1, lip.lastIndexOf(58));
        boolean ret = false;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM dangerousip WHERE ? LIKE CONCAT(ip, '%')")) {
            ps.setString(1, ip);
            try (final ResultSet rs = ps.executeQuery()) {
                rs.next();
                if (rs.getInt(1) > 0) {
                    ret = true;
                }
            }
        }
        catch (SQLException ex) {
            System.err.println("Error dangerousIp " + (Object)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        return ret;
    }
    
    public void setChrDangerousIp(final String lip) {
        final String ip = lip.substring(1, lip.lastIndexOf(58));
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("INSERT INTO dangerousip (ip) VALUES (?)");
            ps.setString(1, ip);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException Wx) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Wx);
        }
    }
    
    public final void updateNewState(final int newstate, final int accountId) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("UPDATE `accounts` SET `loggedin` = ? WHERE id = ?")) {
            ps.setInt(1, newstate);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        }
        catch (SQLException ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
    }
    
    public int getIntNoRecord(final int questID) {
        final MapleQuestStatus stat = this.getQuestNoAdd(MapleQuest.getInstance(questID));
        if (stat == null || stat.getCustomData() == null) {
            return 0;
        }
        return Integer.parseInt(stat.getCustomData());
    }
    
    public int getIntRecord(final int questID) {
        final MapleQuestStatus stat = this.getQuestNAdd(MapleQuest.getInstance(questID));
        if (stat.getCustomData() == null) {
            stat.setCustomData("0");
        }
        return Integer.parseInt(stat.getCustomData());
    }
    
    public void updatePetAuto() {
        if (this.getIntNoRecord(122221) > 0) {
            this.client.getSession().writeAndFlush((Object)MaplePacketCreator.petAutoHP(this.getIntRecord(122221)));
        }
        if (this.getIntNoRecord(122223) > 0) {
            this.client.getSession().writeAndFlush((Object)MaplePacketCreator.petAutoMP(this.getIntRecord(122223)));
        }
    }
    
    public long getChrMeso() {
        long meso = 0L;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM characters")) {
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                meso += (long)rs.getInt("meso");
            }
            rs.close();
        }
        catch (Exception e) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            e.printStackTrace();
        }
        return meso;
    }
    
    public long getStorageMeso() {
        long meso = 0L;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM storages")) {
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                meso += (long)rs.getInt("meso");
            }
            rs.close();
        }
        catch (Exception e) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            e.printStackTrace();
        }
        return meso;
    }
    
    public long getHiredMerchMeso() {
        long meso = 0L;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM hiredmerch")) {
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                meso += (long)rs.getInt("Mesos");
            }
            rs.close();
        }
        catch (Exception e) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            e.printStackTrace();
        }
        return meso;
    }
    
    public int getQianDaoTime(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("select count(*) from bosslog where characterid = ? and bossid = ? and lastattempt >= DATE_SUB(curdate(),INTERVAL 0 DAY)");
            ps.setInt(1, this.id);
            ps.setString(2, bossid);
            int ret_count;
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ret_count = rs.getInt(1);
                }
                else {
                    ret_count = -1;
                }
            }
            ps.close();
            return ret_count;
        }
        catch (Exception Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
            return -1;
        }
    }
    
    public int getQianDaoAcLog(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            int ret_count = 0;
            final PreparedStatement ps = con.prepareStatement("select count(*) from Aclog where accid = ? and bossid = ? and lastattempt >= DATE_SUB(curdate(),INTERVAL 0 DAY)");
            ps.setInt(1, this.getClient().getAccID());
            ps.setString(2, bossid);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ret_count = rs.getInt(1);
            }
            else {
                ret_count = -1;
            }
            rs.close();
            ps.close();
            return ret_count;
        }
        catch (Exception Wx) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Wx);
            return -1;
        }
    }
    
    public boolean ChrDangerousAcc(final String acc) {
        boolean ret = false;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM dangerousacc WHERE ? LIKE CONCAT(acc, '%')")) {
            ps.setString(1, acc);
            try (final ResultSet rs = ps.executeQuery()) {
                rs.next();
                if (rs.getInt(1) > 0) {
                    ret = true;
                }
            }
        }
        catch (SQLException ex) {
            System.err.println("Error dangerousname " + (Object)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        return ret;
    }
    
    public void setChrDangerousAcc(final String acc) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("INSERT INTO dangerousacc (acc) VALUES (?)");
            ps.setString(1, acc);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException Wx) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Wx);
        }
    }
    
    public long getChangeTime() {
        return this.mapChangeTime;
    }
    
    public void setChangeTime(final boolean changeMap) {
        this.mapChangeTime = System.currentTimeMillis();
        if (changeMap) {
            this.getCheatTracker().resetInMapIimeCount();
        }
    }
    
    public int getDDJF() {
        return this.getDDJF(this);
    }
    
    public int getDDJF(final MapleCharacter chr) {
        final int maxtimes = 10;
        int nowtime = 0;
        final int delay = 500;
        boolean error = false;
        int x = 0;
        do {
            ++nowtime;
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
                final Statement stmt = con.createStatement();
                final ResultSet rs = stmt.executeQuery("Select DDJF from Accounts Where id = " + chr.getClient().getAccID());
                while (rs.next()) {
                    int debug = -1;
                    try {
                        debug = rs.getInt("DDJF");
                    }
                    catch (Exception ex2) {}
                    if (debug != -1) {
                        x = rs.getInt("DDJF");
                        error = false;
                    }
                    else {
                        error = true;
                    }
                }
                rs.close();
            }
            catch (SQLException SQL) {
                System.err.println("[getDDJF]無法連接資料庫");
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)SQL);
            }
            catch (Exception ex) {
                FilePrinter.printError("MapleCharacter.txt", (Throwable)ex, "getDDJF");
                System.err.println("[getDDJF]" + (Object)ex);
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
            if (error) {
                try {
                    Thread.sleep((long)delay);
                }
                catch (Exception ex) {
                    FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
                }
            }
        } while (error && nowtime < maxtimes);
        return x;
    }
    
    public void setDDJF(final int x) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("Update Accounts set DDJF = ? Where id = ?");
            ps.setInt(1, x);
            ps.setInt(2, this.getClient().getAccID());
            ps.execute();
            ps.close();
        }
        catch (SQLException ex) {
            System.err.println("[DDJF]無法連接資料庫");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        catch (Exception ex2) {
            FilePrinter.printError("MapleCharacter.txt", (Throwable)ex2, "SetDDJFF");
            System.err.println("[setDDJF]" + (Object)ex2);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex2);
        }
    }
    
    public final boolean canHold() {
        for (int i = 1; i <= 5; ++i) {
            if (this.getInventory(MapleInventoryType.getByType((byte)i)).getNextFreeSlot() <= -1) {
                return false;
            }
        }
        return true;
    }
    
    public void deleteAcLog(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("DELETE FROM Aclog WHERE accid = ? and bossid = ?");
            ps.setInt(1, this.getClient().getAccID());
            ps.setString(2, bossid);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException Wx) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Wx);
        }
    }
    
    public int getAcLogD(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("select count(*) from Aclog where accid = ? and bossid = ? and lastattempt >= DATE_SUB(curdate(),INTERVAL 0 DAY)");
            ps.setInt(1, this.getClient().getAccID());
            ps.setString(2, bossid);
            int ret_count;
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ret_count = rs.getInt(1);
                }
                else {
                    ret_count = -1;
                }
            }
            ps.close();
            return ret_count;
        }
        catch (SQLException Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
            return -1;
        }
    }
    
    public int getAclogY(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("select count(*) from Aclog where accid = ? and bossid = ? and DATE_FORMAT(lastattempt, '%Y%m') = DATE_FORMAT(CURDATE( ), '%Y%m')");
            ps.setInt(1, this.getClient().getAccID());
            ps.setString(2, bossid);
            int ret_count;
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ret_count = rs.getInt(1);
                }
                else {
                    ret_count = -1;
                }
            }
            ps.close();
            return ret_count;
        }
        catch (SQLException Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
            return -1;
        }
    }
    
    public int getBossLogS(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("select count(*) from bosslog where characterid = ? and bossid = ?");
            ps.setInt(1, this.id);
            ps.setString(2, bossid);
            int ret_count;
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ret_count = rs.getInt(1);
                }
                else {
                    ret_count = -1;
                }
            }
            ps.close();
            return ret_count;
        }
        catch (SQLException Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
            return -1;
        }
    }
    
    public int getBossLogC(final String bossid) {
        int ret_count = 0;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("select count(*) from bosslog where characterid = ? and bossid = ?");
            ps.setInt(1, this.id);
            ps.setString(2, bossid);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ret_count += rs.getInt(1);
            }
            ps.close();
            rs.close();
            return ret_count;
        }
        catch (SQLException Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
            return -1;
        }
    }
    
    public int getAcLogC(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            int ret_count = 0;
            final PreparedStatement ps = con.prepareStatement("select count(*) from Aclog where accid = ? and bossid = ?");
            ps.setInt(1, this.getClient().getAccID());
            ps.setString(2, bossid);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ret_count += rs.getInt(1);
            }
            rs.close();
            ps.close();
            return ret_count;
        }
        catch (SQLException Wx) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Wx);
            return -1;
        }
    }
    
    public int getBossLogD(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("select count(*) from bosslog where characterid = ? and bossid = ? and lastattempt >= DATE_SUB(curdate(),INTERVAL 0 DAY)");
            ps.setInt(1, this.id);
            ps.setString(2, bossid);
            int ret_count;
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ret_count = rs.getInt(1);
                }
                else {
                    ret_count = -1;
                }
            }
            ps.close();
            return ret_count;
        }
        catch (SQLException Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
            return -1;
        }
    }
    
    public int getBossLogY(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("select count(*) from bosslog where characterid = ? and bossid = ? and DATE_FORMAT(lastattempt, '%Y%m') = DATE_FORMAT(CURDATE( ), '%Y%m')");
            ps.setInt(1, this.id);
            ps.setString(2, bossid);
            int ret_count;
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ret_count = rs.getInt(1);
                }
                else {
                    ret_count = -1;
                }
            }
            ps.close();
            return ret_count;
        }
        catch (SQLException Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
            return -1;
        }
    }
    
    public long getMrqdTime() {
        return this.mrqdTime;
    }
    
    public void setMrqdTime(final long r) {
        this.mrqdTime = r;
    }
    
    public int getStChrLog() {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("select count(*) from characterid where stlog = ?");
            ps.setInt(1, this.id);
            int ret_count;
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ret_count = rs.getInt(1);
                }
                else {
                    ret_count = -1;
                }
            }
            ps.close();
            return ret_count;
        }
        catch (SQLException Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
            return -1;
        }
    }
    
    public String getStChrNameLog(final int id) {
        final int maxtimes = 10;
        int nowtime = 0;
        final int delay = 500;
        boolean error = false;
        int x = 0;
        String name = "";
        do {
            ++nowtime;
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
                final Statement stmt = con.createStatement();
                final ResultSet rs = stmt.executeQuery("Select characterid from stlog Where stid = " + id);
                while (rs.next()) {
                    int debug = -1;
                    try {
                        debug = rs.getInt("characterid");
                    }
                    catch (SQLException ex2) {}
                    if (debug != -1) {
                        x = rs.getInt("characterid");
                        name = name + getCharacterNameById(x) + ",";
                        error = false;
                    }
                    else {
                        error = true;
                    }
                }
                rs.close();
            }
            catch (SQLException SQL) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)SQL);
            }
            catch (Exception ex) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
            if (error) {
                try {
                    Thread.sleep((long)delay);
                }
                catch (Exception ex) {
                    FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
                }
            }
        } while (error && nowtime < maxtimes);
        return name;
    }
    
    public int getStLog() {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("select count(*) from stlog where characterid = ?");
            ps.setInt(1, this.id);
            int ret_count;
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ret_count = rs.getInt(1);
                }
                else {
                    ret_count = -1;
                }
            }
            ps.close();
            return ret_count;
        }
        catch (SQLException Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
            return -1;
        }
    }
    
    public int getStjfLog(final int id) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("select count(*) from stjflog where characterid = ?");
            ps.setInt(1, id);
            int ret_count;
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ret_count = rs.getInt(1);
                }
                else {
                    ret_count = -1;
                }
            }
            ps.close();
            return ret_count;
        }
        catch (SQLException Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
            return -1;
        }
    }
    
    public int getStLogid(final int id) {
        final int maxtimes = 10;
        int nowtime = 0;
        final int delay = 500;
        boolean error = false;
        int x = 0;
        do {
            ++nowtime;
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
                final Statement stmt = con.createStatement();
                final ResultSet rs = stmt.executeQuery("Select stid from stlog Where characterid = " + id);
                while (rs.next()) {
                    int debug = -1;
                    try {
                        debug = rs.getInt("stid");
                    }
                    catch (SQLException ex2) {}
                    if (debug != -1) {
                        x = rs.getInt("stid");
                        error = false;
                    }
                    else {
                        error = true;
                    }
                }
                rs.close();
            }
            catch (SQLException SQL) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)SQL);
            }
            catch (Exception ex) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
            if (error) {
                try {
                    Thread.sleep((long)delay);
                }
                catch (Exception ex) {
                    FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
                }
            }
        } while (error && nowtime < maxtimes);
        return x;
    }
    
    public void setStLog(final int stid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("insert into stlog (characterid, stid) values (?,?)");
            ps.setInt(1, this.id);
            ps.setInt(2, stid);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
        }
    }
    
    public void setStjfLog(final int id, final int stid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("insert into stjflog (characterid, stjf) values (?,?)");
            ps.setInt(1, id);
            ps.setInt(2, stid);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
        }
    }
    
    public void updateStjfLog(final int id, final int stid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("Update stjflog SET stjf = ? WHERE characterid = ?");
            ps.setInt(1, stid);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
        }
    }
    
    public int getStjf(final int id) {
        final int maxtimes = 10;
        int nowtime = 0;
        final int delay = 500;
        boolean error = false;
        int x = 0;
        do {
            ++nowtime;
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
                final Statement stmt = con.createStatement();
                final ResultSet rs = stmt.executeQuery("Select stjf from stjflog Where characterid = " + id);
                while (rs.next()) {
                    int debug = -1;
                    try {
                        debug = rs.getInt("stjf");
                    }
                    catch (SQLException ex2) {}
                    if (debug != -1) {
                        x = rs.getInt("stjf");
                        error = false;
                    }
                    else {
                        error = true;
                    }
                }
                rs.close();
            }
            catch (SQLException SQL) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)SQL);
            }
            catch (Exception ex) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
            if (error) {
                try {
                    Thread.sleep((long)delay);
                }
                catch (Exception ex) {
                    FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
                }
            }
        } while (error && nowtime < maxtimes);
        return x;
    }
    
    public int getVip() {
        return this.vip;
    }
    
    public void setVip(final int r) {
        this.vip = r;
    }
    
    public boolean isVip() {
        return this.getVip() > 0;
    }
    
    public int getOfflinePoints(final MapleCharacter victim) {
        return this.getPoints(victim);
    }
    
    public int getPoints() {
        return this.getPoints(this);
    }
    
    public int getPoints(final MapleCharacter chr) {
        final int maxtimes = 10;
        int nowtime = 0;
        final int delay = 500;
        boolean error = false;
        int x = 0;
        do {
            ++nowtime;
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
                final Statement stmt = con.createStatement();
                final ResultSet rs = stmt.executeQuery("Select points from Accounts Where id = " + chr.getClient().getAccID());
                while (rs.next()) {
                    int debug = -1;
                    try {
                        debug = rs.getInt("points");
                    }
                    catch (SQLException ex2) {}
                    if (debug != -1) {
                        x = rs.getInt("points");
                        error = false;
                    }
                    else {
                        error = true;
                    }
                }
                rs.close();
            }
            catch (SQLException SQL) {
                System.err.println("[getPoints]無法連接資料庫");
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)SQL);
            }
            catch (Exception ex) {
                System.err.println("[getPoints]" + (Object)ex);
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            }
            if (error) {
                try {
                    Thread.sleep((long)delay);
                }
                catch (Exception ex) {
                    FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
                }
            }
        } while (error && nowtime < maxtimes);
        return x;
    }
    
    public void setPoints(final int x) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("Update Accounts set points = ? Where id = ?");
            ps.setInt(1, x);
            ps.setInt(2, this.getClient().getAccID());
            ps.execute();
            ps.close();
        }
        catch (SQLException ex) {
            System.err.println("[Points]無法連接資料庫");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        catch (Exception ex2) {
            System.err.println("[setPoints]" + (Object)ex2);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex2);
        }
    }
    
    public void gainVip() {
        final int rmb = this.getMoneyAll();
        if (rmb >= 2000 && rmb < 4000) {
            this.setVip(1);
        }
        else if (rmb >= 4000 && rmb < 6000) {
            this.setVip(2);
        }
        else if (rmb >= 6000 && rmb < 8000) {
            this.setVip(3);
        }
        else if (rmb >= 8000 && rmb < 10000) {
            this.setVip(4);
        }
        else if (rmb >= 10000) {
            this.setVip(5);
        }
    }
    
    public int getMoneyAll() {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            int money = 0;
            final PreparedStatement ps = con.prepareStatement("SELECT amount FROM donate WHERE username = ?");
            ps.setString(1, this.getClient().getAccountName());
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                money += rs.getInt("amount");
            }
            rs.close();
            ps.close();
            return money;
        }
        catch (SQLException e) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            return -1;
        }
    }
    
    public void setBuLingZanZu(final int bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("insert into donate (username, amount, paymentMethod, date) values (?,?,?,?)");
            ps.setString(1, this.getClient().getAccountName());
            ps.setString(2, String.valueOf(bossid));
            ps.setString(3, "補領贊助");
            ps.setString(4, FileoutputUtil.NowTime());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException Wx) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Wx);
        }
    }
    
    public void isSquadPlayerID() {
        if (this.getMapId() == 280030000) {
            final EventManager em = this.getClient().getChannelServer().getEventSM().getEventManager("ZakumBattle");
            final EventInstanceManager eim = em.getInstance("ZakumBattle");
            final String propsa = eim.getProperty("isSquadPlayerID_" + this.getId());
            if (eim != null && propsa != null && propsa.equals((Object)"0")) {
                eim.setProperty("isSquadPlayerID_" + this.getId(), "1");
            }
        }
        if (this.getMapId() == 551030200) {
            final EventManager em = this.getClient().getChannelServer().getEventSM().getEventManager("ScarTarBattle");
            final EventInstanceManager eim = em.getInstance("ScarTarBattle");
            final String propsa = eim.getProperty("isSquadPlayerID_" + this.getId());
            if (eim != null && propsa != null && propsa.equals((Object)"0")) {
                eim.setProperty("isSquadPlayerID_" + this.getId(), "1");
            }
        }
    }
    
    public void setCsMod(final int mod) {
        this.CsMod = mod;
    }
    
    public int getCsMod() {
        return this.CsMod;
    }
    
    public void setFxName(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("insert into fxlog (bossid, characterid) values (?,?)");
            ps.setString(1, bossid);
            ps.setInt(2, this.id);
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
        }
    }
    
    public int getFxName(final String bossid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("select count(*) from fxlog where bossid = ?");
            ps.setString(1, bossid);
            int ret_count;
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ret_count = rs.getInt(1);
                }
                else {
                    ret_count = -1;
                }
            }
            ps.close();
            return ret_count;
        }
        catch (SQLException Ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)Ex);
            return -1;
        }
    }
    
    public int[] getSavedFaces() {
        return this.savedFaces;
    }
    
    public void setSavedFace(final int sel, final int id) {
        this.savedFaces[sel] = id;
    }
    
    public int getSavedFace(final int sel) {
        if (sel < this.savedFaces.length) {
            return this.savedFaces[sel];
        }
        return -1;
    }
    
    public int[] getSavedHairs() {
        return this.savedHairs;
    }
    
    public void setSavedHair(final int sel, final int id) {
        this.savedHairs[sel] = id;
    }
    
    public int getSavedHair(final int sel) {
        if (sel < this.savedHairs.length) {
            return this.savedHairs[sel];
        }
        return -1;
    }
    
    public final void maxSkillsByJob() {
        for (final ISkill skil : SkillFactory.getAllSkills()) {
            if (skil.canBeLearnedBy((int)this.job) && skil.getId() >= 1000000) {
                this.changeSkillLevel(skil, skil.getMaxLevel(), skil.getMaxLevel());
            }
        }
    }
    
    public String getServerName() {
        return ServerConfig.SERVERNAME;
    }
    
    public int getDY() {
        return this.maplepoints;
    }
    
    public void setDY(final int set) {
        this.maplepoints = set;
    }
    
    public void gainDY(final int gain) {
        this.maplepoints += gain;
    }
    
    public int getItemQuantity(final int itemid) {
        final MapleInventoryType type = GameConstants.getInventoryType(itemid);
        return this.getInventory(type).countById(itemid);
    }
    
    public int getNX() {
        return this.getCSPoints(1);
    }
    
    public int getGamePoints() {
        try {
            int gamePoints = 0;
            final Connection con = DatabaseConnection.getConnection();
            try (final PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts_info WHERE accId = ? AND worldId = ?")) {
                ps.setInt(1, this.getClient().getAccID());
                ps.setInt(2, (int)this.getWorld());
                try (final ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        gamePoints = rs.getInt("gamePoints");
                        final Timestamp updateTime = rs.getTimestamp("updateTime");
                        final Calendar sqlcal = Calendar.getInstance();
                        if (updateTime != null) {
                            sqlcal.setTimeInMillis(updateTime.getTime());
                        }
                        if (sqlcal.get(5) + 1 <= Calendar.getInstance().get(5) || sqlcal.get(2) + 1 <= Calendar.getInstance().get(2) || sqlcal.get(1) + 1 <= Calendar.getInstance().get(1)) {
                            gamePoints = 0;
                            try (final PreparedStatement psu = con.prepareStatement("UPDATE accounts_info SET gamePoints = 0, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND worldId = ?")) {
                                psu.setInt(1, this.getClient().getAccID());
                                psu.setInt(2, (int)this.getWorld());
                                psu.executeUpdate();
                                psu.close();
                            }
                        }
                    }
                    else {
                        try (final PreparedStatement psu2 = con.prepareStatement("INSERT INTO accounts_info (accId, worldId, gamePoints) VALUES (?, ?, ?)")) {
                            psu2.setInt(1, this.getClient().getAccID());
                            psu2.setInt(2, (int)this.getWorld());
                            psu2.setInt(3, 0);
                            psu2.executeUpdate();
                            psu2.close();
                        }
                    }
                    rs.close();
                }
                ps.close();
            }
            return gamePoints;
        }
        catch (SQLException Ex) {
            System.err.println("获取角色帐号的在线时间点出现错误 - 数据库查询失败" + (Object)Ex);
            return -1;
        }
    }
    
    public int getGamePointsPD() {
        try {
            int gamePointsPD = 0;
            final Connection con = DatabaseConnection.getConnection();
            try (final PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts_info WHERE accId = ? AND worldId = ?")) {
                ps.setInt(1, this.getClient().getAccID());
                ps.setInt(2, (int)this.getWorld());
                try (final ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        gamePointsPD = rs.getInt("gamePointspd");
                        final Timestamp updateTime = rs.getTimestamp("updateTime");
                        final Calendar sqlcal = Calendar.getInstance();
                        if (updateTime != null) {
                            sqlcal.setTimeInMillis(updateTime.getTime());
                        }
                        if (sqlcal.get(5) + 1 <= Calendar.getInstance().get(5) || sqlcal.get(2) + 1 <= Calendar.getInstance().get(2) || sqlcal.get(1) + 1 <= Calendar.getInstance().get(1)) {
                            gamePointsPD = 0;
                            try (final PreparedStatement psu = con.prepareStatement("UPDATE accounts_info SET gamePointspd = 0, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND worldId = ?")) {
                                psu.setInt(1, this.getClient().getAccID());
                                psu.setInt(2, (int)this.getWorld());
                                psu.executeUpdate();
                                psu.close();
                            }
                        }
                    }
                    else {
                        try (final PreparedStatement psu2 = con.prepareStatement("INSERT INTO accounts_info (accId, worldId, gamePointspd) VALUES (?, ?, ?)")) {
                            psu2.setInt(1, this.getClient().getAccID());
                            psu2.setInt(2, (int)this.getWorld());
                            psu2.setInt(3, 0);
                            psu2.executeUpdate();
                            psu2.close();
                        }
                    }
                    rs.close();
                }
                ps.close();
            }
            return gamePointsPD;
        }
        catch (SQLException Ex) {
            System.err.println("获取角色帐号的在线时间点出现错误 - 数据库查询失败2" + (Object)Ex);
            return -1;
        }
    }
    
    public int get在线时间() {
        return this.在线时间;
    }
    
    public void set在线时间(final int 在线时间) {
        this.在线时间 = 在线时间;
    }
    
    public void gainGamePoints(final int amount) {
        this.set在线时间(this.在线时间 + amount);
        this.updateGamePoints(this.get在线时间());
    }
    
    public void gainGamePointsPD(final int amount) {
        final int gamePointsPD = this.getGamePointsPD() + amount;
        this.updateGamePointsPD(gamePointsPD);
    }
    
    public void resetGamePointsPD() {
        this.updateGamePointsPD(0);
    }
    
    public void updateGamePointsPD(final int amount) {
        try {
            final Connection con = DatabaseConnection.getConnection();
            try (final PreparedStatement ps = con.prepareStatement("UPDATE accounts_info SET gamePointspd = ?, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND worldId = ?")) {
                ps.setInt(1, amount);
                ps.setInt(2, this.getClient().getAccID());
                ps.setInt(3, (int)this.getWorld());
                ps.executeUpdate();
                ps.close();
            }
        }
        catch (SQLException Ex) {
            System.err.println("更新角色帐号的在线时间出现错误 - 数据库更新失败." + (Object)Ex);
        }
    }
    
    public void updateGamePoints(final int amount) {
        try {
            final Connection con = DatabaseConnection.getConnection();
            try (final PreparedStatement ps = con.prepareStatement("UPDATE accounts_info SET gamePoints = ?, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND worldId = ?")) {
                ps.setInt(1, amount);
                ps.setInt(2, this.getClient().getAccID());
                ps.setInt(3, (int)this.getWorld());
                ps.executeUpdate();
                ps.close();
            }
        }
        catch (SQLException Ex) {
            System.err.println("更新角色帐号的在线时间出现错误 - 数据库更新失败." + (Object)Ex);
        }
    }
    
    public int getGamePointsRQ() {
        try {
            int gamePointsRQ = 0;
            final Connection con = DatabaseConnection.getConnection();
            try (final PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts_info WHERE accId = ? AND worldId = ?")) {
                ps.setInt(1, this.getClient().getAccID());
                ps.setInt(2, (int)this.getWorld());
                try (final ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        gamePointsRQ = rs.getInt("gamePointsrq");
                        final Timestamp updateTime = rs.getTimestamp("updateTime");
                        final Calendar sqlcal = Calendar.getInstance();
                        if (updateTime != null) {
                            sqlcal.setTimeInMillis(updateTime.getTime());
                        }
                        if (sqlcal.get(5) + 1 <= Calendar.getInstance().get(5) || sqlcal.get(2) + 1 <= Calendar.getInstance().get(2) || sqlcal.get(1) + 1 <= Calendar.getInstance().get(1)) {
                            gamePointsRQ = 0;
                            try (final PreparedStatement psu = con.prepareStatement("UPDATE accounts_info SET gamePointsrq = 0, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND worldId = ?")) {
                                psu.setInt(1, this.getClient().getAccID());
                                psu.setInt(2, (int)this.getWorld());
                                psu.executeUpdate();
                            }
                        }
                    }
                    else {
                        try (final PreparedStatement psu2 = con.prepareStatement("INSERT INTO accounts_info (accId, worldId, gamePointsrq) VALUES (?, ?, ?)")) {
                            psu2.setInt(1, this.getClient().getAccID());
                            psu2.setInt(2, (int)this.getWorld());
                            psu2.setInt(3, 0);
                            psu2.executeUpdate();
                        }
                    }
                }
            }
            return gamePointsRQ;
        }
        catch (SQLException Ex) {
            System.err.println("获取角色帐号的在线时间点出现错误 - 数据库查询失败3" + (Object)Ex);
            return -1;
        }
    }
    
    public void gainGamePointsRQ(final int amount) {
        final int gamePointsRQ = this.getGamePointsRQ() + amount;
        this.updateGamePointsRQ(gamePointsRQ);
    }
    
    public void resetGamePointsRQ() {
        this.updateGamePointsRQ(0);
    }
    
    public void updateGamePointsRQ(final int amount) {
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("UPDATE accounts_info SET gamePointsrq = ?, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND worldId = ?");
            ps.setInt(1, amount);
            ps.setInt(2, this.getClient().getAccID());
            ps.setInt(3, (int)this.getWorld());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("更新角色帐号的在线时间出现错误 - 数据库更新失败." + (Object)Ex);
        }
    }
    
    public int getGamePointsPS() {
        try {
            int gamePointsRQ = 0;
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts_info WHERE accId = ? AND worldId = ?");
            ps.setInt(1, this.getClient().getAccID());
            ps.setInt(2, (int)this.getWorld());
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                gamePointsRQ = rs.getInt("gamePointsps");
                final Timestamp updateTime = rs.getTimestamp("updateTime");
                final Calendar sqlcal = Calendar.getInstance();
                if (updateTime != null) {
                    sqlcal.setTimeInMillis(updateTime.getTime());
                }
                if (sqlcal.get(5) + 1 <= Calendar.getInstance().get(5) || sqlcal.get(2) + 1 <= Calendar.getInstance().get(2) || sqlcal.get(1) + 1 <= Calendar.getInstance().get(1)) {
                    gamePointsRQ = 0;
                    final PreparedStatement psu = con.prepareStatement("UPDATE accounts_info SET gamePointsps = 0, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND worldId = ?");
                    psu.setInt(1, this.getClient().getAccID());
                    psu.setInt(2, (int)this.getWorld());
                    psu.executeUpdate();
                    psu.close();
                }
            }
            else {
                final PreparedStatement psu2 = con.prepareStatement("INSERT INTO accounts_info (accId, worldId, gamePointsps) VALUES (?, ?, ?)");
                psu2.setInt(1, this.getClient().getAccID());
                psu2.setInt(2, (int)this.getWorld());
                psu2.setInt(3, 0);
                psu2.executeUpdate();
                psu2.close();
            }
            rs.close();
            ps.close();
            return gamePointsRQ;
        }
        catch (SQLException Ex) {
            System.err.println("获取角色帐号的跑商数据出现错误 - 数据库查询失败" + (Object)Ex);
            return -1;
        }
    }
    
    public void gainGamePointsPS(final int amount) {
        final int gamePointsPS = this.getGamePointsPS() + amount;
        this.updateGamePointsPS(gamePointsPS);
    }
    
    public void resetGamePointsPS() {
        this.updateGamePointsPS(0);
    }
    
    public void updateGamePointsPS(final int amount) {
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("UPDATE accounts_info SET gamePointsps = ?, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND worldId = ?");
            ps.setInt(1, amount);
            ps.setInt(2, this.getClient().getAccID());
            ps.setInt(3, (int)this.getWorld());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("更新" + this.name + "帐号的跑商数据出现错误 - 数据库更新失败." + (Object)Ex);
        }
    }
    
    public void Gaincharactera(final String Name, final int Channale, final int Piot) {
        try {
            int ret = this.Getcharactera(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO charactera (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setString(2, Name);
                    ps.setInt(3, ret);
                    ps.execute();
                }
                catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + (Object)e);
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    }
                    catch (SQLException e2) {
                        System.out.println("xxxxxxxxzzzzzzz:" + (Object)e2);
                    }
                }
                finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    }
                    catch (SQLException e2) {
                        System.out.println("xxxxxxxxzzzzzzz:" + (Object)e2);
                    }
                }
            }
            ret += Piot;
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps2 = con.prepareStatement("UPDATE charactera SET `Point` = ? WHERE Name = ? and channel = ?");
            ps2.setInt(1, ret);
            ps2.setString(2, Name);
            ps2.setInt(3, Channale);
            ps2.execute();
            ps2.close();
        }
        catch (SQLException sql) {
            System.err.println("Getcharactera!!55" + (Object)sql);
        }
    }
    
    public int Getcharactera(final String Name, final int Channale) {
        int ret = -1;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM charactera WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            final ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        }
        catch (SQLException ex) {}
        return ret;
    }
    
    public void Gainpersonal(final String Name, final int Channale, final int Piot) {
        try {
            int ret = this.Getpersonal(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO personal (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setString(2, Name);
                    ps.setInt(3, ret);
                    ps.execute();
                }
                catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + (Object)e);
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    }
                    catch (SQLException e2) {
                        System.out.println("xxxxxxxxzzzzzzz:" + (Object)e2);
                    }
                }
                finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    }
                    catch (SQLException e2) {
                        System.out.println("xxxxxxxxzzzzzzz:" + (Object)e2);
                    }
                }
            }
            ret += Piot;
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps2 = con.prepareStatement("UPDATE personal SET `Point` = ? WHERE Name = ? and channel = ?");
            ps2.setInt(1, ret);
            ps2.setString(2, Name);
            ps2.setInt(3, Channale);
            ps2.execute();
            ps2.close();
        }
        catch (SQLException sql) {
            System.err.println("personal!!55" + (Object)sql);
        }
    }
    
    public int Getpersonal(final String Name, final int Channale) {
        int ret = -1;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM personal WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            final ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        }
        catch (SQLException ex) {}
        return ret;
    }
    
    public int getzs() {
        return this.zs;
    }
    
    public void gainzs(final int gain) {
        this.zs += gain;
    }
    
    public void setzs(final int set) {
        this.zs = set;
    }
    
    public int getjf() {
        return this.jf;
    }
    
    public void gainjf(final int gain) {
        this.jf += gain;
    }
    
    public void setjf(final int set) {
        this.jf = set;
    }
    
    public int getzdjf() {
        return this.zdjf;
    }
    
    public void gainzdjf(final int gain) {
        this.zdjf += gain;
    }
    
    public void setzdjf(final int set) {
        this.zdjf = set;
    }
    
    public int getrwjf() {
        return this.rwjf;
    }
    
    public void gainrwjf(final int gain) {
        this.rwjf += gain;
    }
    
    public void setrwjf(final int set) {
        this.rwjf = set;
    }
    
    public int getcz() {
        return this.cz;
    }
    
    public void gaincz(final int gain) {
        this.cz += gain;
    }
    
    public void setcz(final int set) {
        this.cz = set;
    }
    
    public int getdy() {
        return this.dy;
    }
    
    public void gaindy(final int gain) {
        this.dy += gain;
    }
    
    public void setdy(final int set) {
        this.dy = set;
    }
    
    public int getrmb() {
        return this.rmb;
    }
    
    public void gainrmb(final int gain) {
        this.rmb += gain;
    }
    
    public void setrmb(final int set) {
        this.rmb = set;
    }
    
    public int getyb() {
        return this.yb;
    }
    
    public void gainyb(final int gain) {
        this.yb += gain;
    }
    
    public void setyb(final int set) {
        this.yb = set;
    }
    
    public int getplayerPoints() {
        return this.playerPoints;
    }
    
    public void gainplayerPoints(final int gain) {
        this.playerPoints += gain;
    }
    
    public void setplayerPoints(final int set) {
        this.playerPoints = set;
    }
    
    public int getplayerEnergy() {
        return this.playerEnergy;
    }
    
    public void gainplayerEnergy(final int gain) {
        this.playerEnergy += gain;
    }
    
    public void setplayerEnergy(final int set) {
        this.playerEnergy = set;
    }
    
    public int getjf1() {
        return this.jf1;
    }
    
    public void gainjf1(final int gain) {
        this.jf1 += gain;
    }
    
    public void setjf1(final int set) {
        this.jf1 = set;
    }
    
    public int getjf2() {
        return this.jf2;
    }
    
    public void gainjf2(final int gain) {
        this.jf2 += gain;
    }
    
    public void setjf2(final int set) {
        this.jf2 = set;
    }
    
    public int getjf3() {
        return this.jf3;
    }
    
    public void gainjf3(final int gain) {
        this.jf3 += gain;
    }
    
    public void setjf3(final int set) {
        this.jf3 = set;
    }
    
    public int getjf4() {
        return this.jf4;
    }
    
    public void gainjf4(final int gain) {
        this.jf4 += gain;
    }
    
    public void setjf4(final int set) {
        this.jf4 = set;
    }
    
    public int getjf5() {
        return this.jf5;
    }
    
    public void gainjf5(final int gain) {
        this.jf5 += gain;
    }
    
    public void setjf5(final int set) {
        this.jf5 = set;
    }
    
    public int getjf6() {
        return this.jf6;
    }
    
    public void gainjf6(final int gain) {
        this.jf6 += gain;
    }
    
    public void setjf6(final int set) {
        this.jf6 = set;
    }
    
    public int getjf7() {
        return this.jf7;
    }
    
    public void gainjf7(final int gain) {
        this.jf7 += gain;
    }
    
    public void setjf7(final int set) {
        this.jf7 = set;
    }
    
    public int getjf8() {
        return this.jf8;
    }
    
    public void gainjf8(final int gain) {
        this.jf8 += gain;
    }
    
    public void setjf8(final int set) {
        this.jf8 = set;
    }
    
    public int getjf9() {
        return this.jf9;
    }
    
    public void gainjf9(final int gain) {
        this.jf9 += gain;
    }
    
    public void setjf9(final int set) {
        this.jf9 = set;
    }
    
    public int getjf10() {
        return this.jf10;
    }
    
    public void gainjf10(final int gain) {
        this.jf10 += gain;
    }
    
    public void setjf10(final int set) {
        this.jf10 = set;
    }
    
    public void 打开奖励() {
        NPCScriptManager.getInstance().dispose(this.client);
        NPCScriptManager.getInstance().start(this.client, 9000011, "5");
    }
    
    public void 击杀野外BOSS特效2() {
        this.map.broadcastMessage(MaplePacketCreator.environmentChange("dojang/end/clear", 3));
    }
    
    public void 击杀野外BOSS特效() {
    }
    
    public void 时间到() {
        this.map.broadcastMessage(MaplePacketCreator.environmentChange("summerboating/timeout", 3));
    }
    
    public Map<Integer, Integer> getEquippedFuMoMap() {
        return this._equippedFuMoMap;
    }
    
    public Map<Integer, Integer> F() {
        return this._equippedFuMoMap;
    }
    
    public int getTotalRMB() {
        int rmb = 0;
        try {
            final PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT SUM(rmb) FROM paylog WHERE account = ?");
            ps.setString(1, this.getClient().getAccountName());
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rmb = rs.getInt(1);
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取账号充值总数失败." + (Object)Ex);
        }
        return rmb;
    }
    
    public int getRMB() {
        int point = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            final Connection con = DatabaseConnection.getConnection();
            ps = con.prepareStatement("SELECT rmb FROM accounts WHERE name = ?");
            ps.setString(1, this.getClient().getAccountName());
            rs = ps.executeQuery();
            if (rs.next()) {
                point = rs.getInt("rmb");
            }
            ps.close();
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("获取角色rmb失败。" + (Object)ex);
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException ex2) {
                System.err.println("获取角色rmb失败。" + (Object)ex2);
            }
        }
        return point;
    }
    
    public void setRMB(final int point) {
        PreparedStatement ps = null;
        try {
            final Connection con = DatabaseConnection.getConnection();
            ps = con.prepareStatement("UPDATE accounts SET rmb = ? WHERE name = ?");
            ps.setInt(1, point);
            ps.setString(2, this.getClient().getAccountName());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex) {
            System.err.println("获取角色rmb失败。" + (Object)ex);
            try {
                if (ps != null) {
                    ps.close();
                }
            }
            catch (SQLException ex2) {
                System.err.println("获取角色rmb失败。" + (Object)ex2);
            }
        }
    }
    
    public void gainRMB(final int point) {
        PreparedStatement ps = null;
        try {
            final Connection con = DatabaseConnection.getConnection();
            ps = con.prepareStatement("UPDATE accounts SET rmb = rmb + ? WHERE name = ?");
            ps.setInt(1, point);
            ps.setString(2, this.getClient().getAccountName());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex) {
            System.err.println("获取角色rmb失败。" + (Object)ex);
            try {
                if (ps != null) {
                    ps.close();
                }
            }
            catch (SQLException ex2) {
                System.err.println("获取角色rmb失败。" + (Object)ex2);
            }
        }
    }
    
    public int getPQLog(final String pqName) {
        return this.getPQLog(pqName, 0);
    }
    
    public int getPQLog(final String pqName, final int times) {
        return this.getPQLog(pqName, times, 1);
    }
    
    public int getDaysPQLog(final String pqName, final int times) {
        return this.getDaysPQLog(pqName, 0, times);
    }
    
    public int getDaysPQLog(final String pqName, final int times, final int day) {
        return this.getPQLog(pqName, times, day);
    }
    
    public int getPQLog(final String pqName, final int times, final int day) {
        try (final Connection con = DatabaseConnection.getConnection()) {
            int n4 = 0;
            try (final PreparedStatement ps = con.prepareStatement("SELECT `count`,`time` FROM pqlog WHERE characterid = ? AND pqname = ?")) {
                ps.setInt(1, this.id);
                ps.setString(2, pqName);
                try (final ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        n4 = rs.getInt("count");
                        final Timestamp timestamp = rs.getTimestamp("time");
                        rs.close();
                        ps.close();
                        if (times == 0) {
                            final Calendar calendar = Calendar.getInstance();
                            final Calendar calendar2 = Calendar.getInstance();
                            if (timestamp != null) {
                                calendar2.setTimeInMillis(timestamp.getTime());
                                calendar2.add(6, day);
                            }
                            int n5;
                            if (calendar.get(1) - calendar2.get(1) > 1) {
                                n5 = 0;
                            }
                            else if (calendar.get(1) - calendar2.get(1) >= 0) {
                                if (calendar.get(1) - calendar2.get(1) > 0) {
                                    calendar2.add(1, 1);
                                }
                                n5 = calendar.get(6) - calendar2.get(6);
                            }
                            else {
                                n5 = -1;
                            }
                            if (n5 >= 0) {
                                n4 = 0;
                                try (final PreparedStatement psi = con.prepareStatement("UPDATE pqlog SET count = 0, time = CURRENT_TIMESTAMP() WHERE characterid = ? AND pqname = ?")) {
                                    psi.setInt(1, this.id);
                                    psi.setString(2, pqName);
                                    psi.executeUpdate();
                                }
                            }
                        }
                    }
                    else {
                        try (final PreparedStatement pss = con.prepareStatement("INSERT INTO pqlog (characterid, pqname, count, type) VALUES (?, ?, ?, ?)")) {
                            pss.setInt(1, this.id);
                            pss.setString(2, pqName);
                            pss.setInt(3, 0);
                            pss.setInt(4, times);
                            pss.executeUpdate();
                        }
                    }
                }
            }
            return n4;
        }
        catch (SQLException sQLException) {
            System.err.println("Error while get pqlog: " + (Object)sQLException);
            return -1;
        }
    }
    
    public void setPQLog(final String pqname) {
        this.setPQLog(pqname, 0);
    }
    
    public void setPQLog(final String pqname, final int type) {
        this.setPQLog(pqname, type, 1);
    }
    
    public void setPQLog(final String pqname, final int type, final int count) {
        final int times = this.getPQLog(pqname, type);
        try (final Connection con = DatabaseConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement("UPDATE pqlog SET count = ?, type = ?, time = CURRENT_TIMESTAMP() WHERE characterid = ? AND pqname = ?")) {
            ps.setInt(1, times + count);
            ps.setInt(2, type);
            ps.setInt(3, this.id);
            ps.setString(4, pqname);
            ps.executeUpdate();
        }
        catch (SQLException sQLException) {
            System.err.println("Error while set pqlog: " + (Object)sQLException);
        }
    }
    
    public void resetPQLog(final String pqname) {
        this.resetPQLog(pqname, 0);
    }
    
    public void resetPQLog(final String pqname, final int type) {
        try (final Connection con = DatabaseConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement("UPDATE pqlog SET count = ?, type = ?, time = CURRENT_TIMESTAMP() WHERE characterid = ? AND pqname = ?")) {
            ps.setInt(1, 0);
            ps.setInt(2, type);
            ps.setInt(3, this.id);
            ps.setString(4, pqname);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Error while reset pqlog: " + (Object)e);
        }
    }
    
    public int getEventCount(final String eventId) {
        return this.getEventCount(eventId, 0);
    }
    
    public int getEventCount(final String eventId, final int type) {
        try {
            int count = 0;
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts_event WHERE accId = ? AND eventId = ?");
            ps.setInt(1, this.getClient().getAccID());
            ps.setString(2, eventId);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
                final Timestamp updateTime = rs.getTimestamp("updateTime");
                if (type == 0) {
                    final Calendar sqlcal = Calendar.getInstance();
                    if (updateTime != null) {
                        sqlcal.setTimeInMillis(updateTime.getTime());
                    }
                    if (sqlcal.get(5) + 1 <= Calendar.getInstance().get(5) || sqlcal.get(2) + 1 <= Calendar.getInstance().get(2) || sqlcal.get(1) + 1 <= Calendar.getInstance().get(1)) {
                        count = 0;
                        final PreparedStatement psu = con.prepareStatement("UPDATE accounts_event SET count = 0, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND eventId = ?");
                        psu.setInt(1, this.getClient().getAccID());
                        psu.setString(2, eventId);
                        psu.executeUpdate();
                        psu.close();
                    }
                }
            }
            else {
                final PreparedStatement psu2 = con.prepareStatement("INSERT INTO accounts_event (accId, eventId, count, type) VALUES (?, ?, ?, ?)");
                psu2.setInt(1, this.getClient().getAccID());
                psu2.setString(2, eventId);
                psu2.setInt(3, 0);
                psu2.setInt(4, type);
                psu2.executeUpdate();
                psu2.close();
            }
            rs.close();
            ps.close();
            return count;
        }
        catch (SQLException e) {
            System.err.println("获取 EventCount 次数 " + (Object)e);
            return -1;
        }
    }
    
    public void setEventCount(final String eventId) {
        this.setEventCount(eventId, 0);
    }
    
    public void setEventCount(final String eventId, final int type) {
        this.setEventCount(eventId, type, 1);
    }
    
    public void setEventCount(final String eventId, final int type, final int count) {
        final int eventCount = this.getEventCount(eventId, type);
        try {
            final PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE accounts_event SET count = ?, type = ?, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND eventId = ?");
            ps.setInt(1, eventCount + count);
            ps.setInt(2, type);
            ps.setInt(3, this.getClient().getAccID());
            ps.setString(4, eventId);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            System.err.println("增加 EventCount 次数失败 " + (Object)e);
        }
    }
    
    public void resetEventCount(final String eventId) {
        this.resetEventCount(eventId, 0);
    }
    
    public void resetEventCount(final String eventId, final int type) {
        try {
            final PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE accounts_event SET count = 0, type = ?, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND eventId = ?");
            ps.setInt(1, type);
            ps.setInt(2, this.getClient().getAccID());
            ps.setString(3, eventId);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            System.err.println("重置 EventCount 次数失败 " + (Object)e);
        }
    }
    
    public int getHiredChannel() {
        return (this.getMap() == null) ? -1 : MapleMap.getMerchantChannel(this);
    }
    
    public List<Integer> getiApprentice(final int charaid) {
        final Connection con1 = DatabaseConnection.getConnection();
        try {
            final List<Integer> charid = new ArrayList<Integer>();
            final PreparedStatement ps = con1.prepareStatement("select * from Learnteacher where worker = ? ");
            ps.setInt(1, charaid);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                charid.add(Integer.valueOf(rs.getInt("characterid")));
            }
            rs.close();
            ps.close();
            return charid;
        }
        catch (Exception Ex) {
            return null;
        }
    }
    
    public int getiLearnTeacher(final int charaid, final int tybe) {
        final Connection con1 = DatabaseConnection.getConnection();
        try {
            int charid = 0;
            final PreparedStatement ps = con1.prepareStatement("select * from Learnteacher where characterid = ? ");
            ps.setInt(1, charaid);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                switch (tybe) {
                    case 0: {
                        charid = rs.getInt("worker");
                        break;
                    }
                    case 1: {
                        charid = rs.getInt("masterworker");
                        break;
                    }
                }
            }
            rs.close();
            ps.close();
            return charid;
        }
        catch (Exception Ex) {
            return -1;
        }
    }
    
    public void setLearnteacher(final int workid) {
        final Connection con1 = DatabaseConnection.getConnection();
        try {
            final PreparedStatement ps = con1.prepareStatement("select * from Learnteacher where  characterid = ?");
            ps.setInt(1, workid);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                final PreparedStatement pss = con1.prepareStatement("update Learnteacher set masterworker=? where  characterid = ?");
                pss.setInt(1, 3);
                pss.setInt(2, workid);
                pss.execute();
                pss.close();
            }
            else {
                final PreparedStatement psu = con1.prepareStatement("insert into Learnteacher (characterid, worker,masterworker) values (?,?,?)");
                psu.setInt(1, workid);
                psu.setInt(2, 0);
                psu.setInt(3, 3);
                psu.executeUpdate();
                psu.close();
            }
            rs.close();
            ps.close();
        }
        catch (Exception ex) {}
    }
    
    public void setApprentice(final int workid, final int apprentid) {
        final Connection con1 = DatabaseConnection.getConnection();
        try {
            String caozuo = "";
            final PreparedStatement ps = con1.prepareStatement("select * from Learnteacher where  characterid = ?");
            ps.setInt(1, apprentid);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                caozuo = "Update Learnteacher set worker = '" + workid + "' , masterworker = 1 Where characterid = '" + apprentid + "'";
                final PreparedStatement pss = con1.prepareStatement(caozuo);
                pss.execute();
                pss.close();
            }
            else {
                final PreparedStatement psu = con1.prepareStatement("insert into Learnteacher (characterid, worker,masterworker) values (?,?,1)");
                psu.setInt(1, apprentid);
                psu.setInt(2, workid);
                psu.executeUpdate();
                psu.close();
            }
            rs.close();
            ps.close();
        }
        catch (Exception ex) {}
    }
    
    public void updateApprentice(final int workerid, final int apprentid, final int tybe) {
        try (final Connection con = DatabaseConnection.getConnection()) {
            String caozuo = "";
            switch (tybe) {
                case 0: {
                    caozuo = "Update Learnteacher set worker = '" + workerid + "' , masterworker = 2 Where characterid = '" + apprentid + "'";
                    break;
                }
                case 1: {
                    caozuo = "Update Learnteacher set worker = 0 , masterworker = 0 Where characterid = '" + apprentid + "'";
                    break;
                }
                case 2: {
                    caozuo = "Update Learnteacher set worker =0 , masterworker = 0 Where characterid = '" + apprentid + "'";
                    break;
                }
            }
            final PreparedStatement ps = con.prepareStatement(caozuo);
            ps.execute();
            ps.close();
        }
        catch (Exception ex) {}
    }
    
    public String getCharaName(final int id) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE id = ?");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("name");
                }
                rs.close();
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取角色ID取名字出错 - 数据库查询失败：" + (Object)Ex);
        }
        if (data == null) {
            data = "";
        }
        return data;
    }
    
    public int getNowdaylog() {
        final Connection con1 = DatabaseConnection.getConnection();
        try {
            int ret_count = 0;
            final PreparedStatement ps = con1.prepareStatement("select * from nowdaylog where characterid = ? ");
            ps.setInt(1, this.id);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ret_count = rs.getInt("Point");
            }
            else {
                ret_count = -1;
            }
            rs.close();
            ps.close();
            return ret_count;
        }
        catch (Exception Ex) {
            return -1;
        }
    }
    
    public void setNowdaylog() {
        final Connection con1 = DatabaseConnection.getConnection();
        try {
            int nownuber = 0;
            String caozuo = "";
            final PreparedStatement ps = con1.prepareStatement("select * from nowdaylog where  characterid = ?");
            ps.setInt(1, this.id);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nownuber = rs.getInt("Point") + 1;
                caozuo = "Update nowdaylog set Point = '" + nownuber + "' Where characterid = '" + this.id + "'";
                final PreparedStatement pss = con1.prepareStatement(caozuo);
                pss.execute();
                pss.close();
            }
            else {
                final PreparedStatement psu = con1.prepareStatement("insert into nowdaylog (characterid,Point) values (?,1)");
                psu.setInt(1, this.id);
                psu.executeUpdate();
                psu.close();
            }
            rs.close();
            ps.close();
        }
        catch (Exception ex) {}
    }
    
    public void RestNowdaylog() {
        final Connection con1 = DatabaseConnection.getConnection();
        try {
            String caozuo = "";
            final PreparedStatement ps = con1.prepareStatement("select * from nowdaylog where  characterid = ?");
            ps.setInt(1, this.id);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                caozuo = "Update nowdaylog set Point = 0 Where characterid = '" + this.id + "'";
                final PreparedStatement pss = con1.prepareStatement(caozuo);
                pss.execute();
                pss.close();
            }
            rs.close();
            ps.close();
        }
        catch (Exception ex) {}
    }
    
    public int 取限制等级() {
        int 限制等级 = 0;
        try {
            final int cid = 4001128;
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement limitCheck = con.prepareStatement("SELECT * FROM shijiexianzhidengji WHERE huoyaotongid=" + cid + "");
            final ResultSet rs = limitCheck.executeQuery();
            if (rs.next()) {
                限制等级 = rs.getInt("xianzhidengji");
            }
            limitCheck.close();
            rs.close();
        }
        catch (SQLException ex) {}
        return 限制等级;
    }
    
    public void updateOfflineTime1() {
        try (final Connection con = DatabaseConnection.getConnection()) {
            final PreparedStatement ps = con.prepareStatement("Update lefttime set lefttime = ? Where accid = ?");
            ps.setLong(1, System.currentTimeMillis());
            ps.setInt(2, this.getClient().getAccID());
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception Ex) {
            System.out.println("离线挂机异常" + (Object)Ex);
        }
    }
    
    public void updateOfflineTime() {
        try (final Connection con = DatabaseConnection.getConnection()) {
            final PreparedStatement ps = con.prepareStatement("Update lefttime set lefttime = ? Where accid = ?");
            ps.setLong(1, System.currentTimeMillis());
            ps.setInt(2, this.getClient().getAccID());
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception Ex) {
            System.out.println("离线挂机异常" + (Object)Ex);
        }
    }
    
    public void updateOfflineTime3(final int x, final int y, final int chairid, final long lefttime) {
        Connection con1 = null;
        try {
            con1 = DatabaseConnection.getConnection();
            final PreparedStatement ps = con1.prepareStatement("select * from lefttime where  accid = ?");
            ps.setInt(1, this.getClient().getAccID());
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                final PreparedStatement pss = con1.prepareStatement("update lefttime set (charid=?,x=?,y=?,chairid=?,lefttime=?) where accid = ?");
                pss.setInt(1, this.id);
                pss.setInt(2, x);
                pss.setInt(3, y);
                pss.setInt(4, chairid);
                pss.setLong(5, lefttime);
                pss.setInt(6, this.getClient().getAccID());
                pss.execute();
                pss.close();
            }
            else {
                final PreparedStatement psu = con1.prepareStatement("insert into lefttime (accid, charid,x,y,chairid,lefttime) values (?,?,?,?,?,?)");
                psu.setInt(1, this.getClient().getAccID());
                psu.setInt(2, this.id);
                psu.setInt(3, x);
                psu.setInt(4, y);
                psu.setInt(5, chairid);
                psu.setLong(6, lefttime);
                psu.executeUpdate();
                psu.close();
            }
            rs.close();
            ps.close();
        }
        catch (Exception Ex) {
            System.out.println("结束客户端保存离线数据错误!" + (Object)Ex);
            try {
                if (con1 != null) {
                    con1.close();
                }
            }
            catch (Exception Ex2) {
                System.out.println("结束客户端保存离线数据自动关闭数据库异常" + (Object)Ex2);
            }
        }
        finally {
            try {
                if (con1 != null) {
                    con1.close();
                }
            }
            catch (Exception Ex2) {
                System.out.println("结束客户端保存离线数据自动关闭数据库异常" + (Object)Ex2);
            }
        }
    }
    
    public void updateOfflineTime2() {
        final 离线人偶 clones = null;
        ArrayList<离线人偶> clone = new ArrayList<离线人偶>();
        this.client.getChannelServer();
        clone = ChannelServer.clones;
        for (final 离线人偶 jr : clone) {
            this.updateOfflineTime3(jr.getX(), jr.getY(), jr.getChairId(), jr.getLiftTime());
        }
    }
    
    public 离线人偶 getPlayerclones() {
        离线人偶 clones = null;
        ArrayList<离线人偶> clone = new ArrayList<离线人偶>();
        this.client.getChannelServer();
        clone = ChannelServer.clones;
        for (final 离线人偶 jr : clone) {
            if (jr.AccId == this.getClient().getAccID()) {
                clones = jr;
                break;
            }
        }
        return clones;
    }
    
    public int getLastOfflineTime2() {
        Connection conn = null;
        int retnumber = -1;
        try {
            conn = DatabaseConnection.getConnection();
            final PreparedStatement ps = conn.prepareStatement("TRUNCATE TABLE lefttime");
            ps.executeUpdate();
            this.client.getChannelServer();
            final ArrayList<离线人偶> clone = ChannelServer.clones;
            for (final 离线人偶 jr : clone) {
                final PreparedStatement psu = conn.prepareStatement("insert into lefttime (accid,charid,x,y,chairid,lefttime,channel) values (?,?,?,?,?,?,?)");
                psu.setInt(1, jr.AccId);
                psu.setInt(2, jr.charId);
                psu.setInt(3, jr.x);
                psu.setInt(4, jr.y);
                psu.setInt(5, jr.chairId);
                psu.setLong(6, jr.liftTime);
                psu.setInt(7, jr.channel);
                psu.executeUpdate();
                psu.close();
            }
            ps.close();
            conn.close();
            retnumber = 1;
        }
        catch (Exception Ex) {
            System.out.println("离线挂机数据保存异常" + (Object)Ex);
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (Exception Ex2) {
                System.out.println("结束客户端保存离线数据自动关闭数据库异常" + (Object)Ex2);
            }
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (Exception Ex2) {
                System.out.println("结束客户端保存离线数据自动关闭数据库异常" + (Object)Ex2);
            }
        }
        return retnumber;
    }
    
    public long getLastOfflineTime() {
        long 离线时间 = 0L;
        try {
            final Connection con1 = DatabaseConnection.getConnection();
            final PreparedStatement ps = con1.prepareStatement("select lefttime from lefttime WHERE accid = ?");
            ps.setInt(1, this.getClient().getAccID());
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                离线时间 = rs.getLong("lefttime");
            }
            else {
                离线时间 = System.currentTimeMillis();
                final PreparedStatement psu = con1.prepareStatement("insert into lefttime (accid, lefttime) values (?,?)");
                psu.setInt(1, this.getClient().getAccID());
                psu.setLong(2, System.currentTimeMillis());
                psu.executeUpdate();
                psu.close();
            }
            rs.close();
            ps.close();
            return 离线时间;
        }
        catch (Exception Ex) {
            System.out.println("离线挂机异常" + (Object)Ex);
            return -1L;
        }
    }
    
    static {
        MapleCharacter.ariantroomleader = new String[3];
        MapleCharacter.ariantroomslot = new int[3];
        MapleCharacter.记录当前血量 = 0;
        MapleCharacter.角色无敌指数 = 0;
        MapleCharacter.地图记录 = 0;
        MapleCharacter.吸怪怪值 = 0;
        MapleCharacter.记录范围 = 0L;
        MapleCharacter.记录技能 = 0;
        MapleCharacter.技能检测惩罚 = 0;
        MapleCharacter.登陆验证 = 0;
        MapleCharacter._tiredMinutes = 0;
    }
    
    public enum FameStatus
    {
        OK, 
        NOT_TODAY, 
        NOT_THIS_MONTH;
    }
}
