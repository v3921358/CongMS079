package handling.world;

import java.io.ObjectOutput;
import java.io.IOException;
import java.io.ObjectInput;
import client.inventory.MapleMount;
import java.util.Iterator;
import client.inventory.MaplePet;
import client.ISkill;
import client.MapleQuestStatus;
import server.quest.MapleQuest;
import java.util.Map.Entry;
import client.MapleCharacter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import client.SkillEntry;
import client.BuddyEntry;
import java.util.List;
import tools.Pair;
import java.util.Map;
import java.io.Externalizable;

public class CharacterTransfer implements Externalizable
{
    public int characterid;
    public int accountid;
    public int exp;
    public int beans;
    public int meso;
    public int hair;
    public int face;
    public int mapid;
    public int guildid;
    public int partyid;
    public int messengerid;
    public int mBookCover;
    public int dojo;
    public int MaplePoints;
    public int mount_itemid;
    public int mount_exp;
    public int vpoints;
    public int marriageId;
    public int PGSXDJ;
    public int familyid;
    public int seniorid;
    public int junior1;
    public int junior2;
    public int currentrep;
    public int totalrep;
    public int expression;
    public int constellation;
    public int blood;
    public int month;
    public int day;
    public int battleshipHP;
    public int gachexp;
    public int vip;
    public int CsMod;
    public byte channel;
    public byte dojoRecord;
    public byte gender;
    public byte gmLevel;
    public byte guildrank;
    public byte alliancerank;
    public byte clonez;
    public byte fairyExp;
    public byte buddysize;
    public byte world;
    public byte initialSpawnPoint;
    public byte skinColor;
    public byte mount_level;
    public byte mount_Fatigue;
    public byte subcategory;
    public long lastfametime;
    public long TranferTime;
    public long mrqdTime;
    public String name;
    public String accountname;
    public String BlessOfFairy;
    public String chalkboard;
    public String charmessage;
    public String prefix;
    public String nowmacs;
    public String loginkey;
    public String serverkey;
    public String clientkey;
    public String accountsecondPassword;
    public short level;
    public short fame;
    public short str;
    public short dex;
    public short int_;
    public short luk;
    public short maxhp;
    public short maxmp;
    public short hp;
    public short mp;
    public short remainingAp;
    public short hpApUsed;
    public short job;
    public Object inventorys;
    public Object skillmacro;
    public Object storage;
    public Object cs;
    public Object antiMacro;
    public int[] savedlocation;
    public int[] wishlist;
    public int[] rocks;
    public int[] remainingSp;
    public int[] regrocks;
    public int[] savedHairs;
    public int[] savedFaces;
    public byte[] petStore;
    public Map<Integer, Integer> mbook;
    public Map<Integer, Pair<Byte, Integer>> keymap;
    public final List<Integer> finishedAchievements;
    public final List<Integer> famedcharacters;
    public final Map<BuddyEntry, Boolean> buddies;
    public final Map<Integer, Object> Quest;
    public Map<Integer, String> InfoQuest;
    public final Map<Integer, SkillEntry> Skills;
    public boolean 精灵商人购买开关;
    public boolean 玩家私聊开关;
    public boolean 玩家密语开关;
    public boolean 好友聊天开关;
    public boolean 队伍聊天开关;
    public boolean 公会聊天开关;
    public boolean 联盟聊天开关;
    public boolean GM吸怪信息开关;
    public boolean Vip_Medal;
    public boolean auto吸怪;
    public boolean DebugMessage;
    public boolean canTalk;
    public boolean smega;
    public boolean gashponmega;
    public int jf;
    public int zdjf;
    public int rwjf;
    public int zs;
    public int cz;
    public int dy;
    public int rmb;
    public int yb;
    public int playerPoints;
    public int playerEnergy;
    public int jf1;
    public int jf2;
    public int jf3;
    public int jf4;
    public int jf5;
    public int jf6;
    public int jf7;
    public int jf8;
    public int jf9;
    public int jf10;
    
    public CharacterTransfer() {
        this.mbook = new LinkedHashMap<Integer, Integer>();
        this.keymap = new LinkedHashMap<Integer, Pair<Byte, Integer>>();
        this.finishedAchievements = new ArrayList<Integer>();
        this.famedcharacters = new ArrayList<Integer>();
        this.buddies = new LinkedHashMap<BuddyEntry, Boolean>();
        this.Quest = new LinkedHashMap<Integer, Object>();
        this.InfoQuest = new LinkedHashMap<Integer, String>();
        this.Skills = new LinkedHashMap<Integer, SkillEntry>();
    }
    
    public CharacterTransfer(final MapleCharacter chr) {
        this.mbook = new LinkedHashMap<Integer, Integer>();
        this.keymap = new LinkedHashMap<Integer, Pair<Byte, Integer>>();
        this.finishedAchievements = new ArrayList<Integer>();
        this.famedcharacters = new ArrayList<Integer>();
        this.buddies = new LinkedHashMap<BuddyEntry, Boolean>();
        this.Quest = new LinkedHashMap<Integer, Object>();
        this.InfoQuest = new LinkedHashMap<Integer, String>();
        this.Skills = new LinkedHashMap<Integer, SkillEntry>();
        this.nowmacs = chr.getNowMacs();
        this.canTalk = chr.getCanTalk();
        this.DebugMessage = chr.getDebugMessage();
        this.auto吸怪 = chr.getAuto吸怪();
        this.GM吸怪信息开关 = chr.get_control_吸怪信息();
        this.Vip_Medal = chr.getVipMedal();
        this.玩家私聊开关 = chr.get_control_玩家私聊();
        this.玩家密语开关 = chr.get_control_玩家密语();
        this.好友聊天开关 = chr.get_control_好友聊天();
        this.队伍聊天开关 = chr.get_control_队伍聊天();
        this.公会聊天开关 = chr.get_control_公会聊天();
        this.联盟聊天开关 = chr.get_control_联盟聊天();
        this.精灵商人购买开关 = chr.get_control_精灵商人();
        this.smega = chr.getSmega();
        this.gashponmega = chr.getGashponmega();
        this.characterid = chr.getId();
        this.accountid = chr.getAccountID();
        this.accountname = chr.getClient().getAccountName();
        this.accountsecondPassword = chr.getAccountSecondPassword();
        this.channel = (byte)chr.getClient().getChannel();
        this.MaplePoints = chr.getCSPoints(2);
        this.loginkey = chr.getLoginKey();
        this.serverkey = chr.getServerKey();
        this.clientkey = chr.getClientKey();
        this.antiMacro = chr.getAntiMacro();
        this.vpoints = chr.getVPoints();
        this.vip = chr.getVip();
        this.mrqdTime = chr.getMrqdTime();
        this.name = chr.getName();
        this.fame = chr.getFame();
        this.gender = chr.getGender();
        this.level = chr.getLevel();
        this.str = chr.getStat().getStr();
        this.dex = chr.getStat().getDex();
        this.int_ = chr.getStat().getInt();
        this.luk = chr.getStat().getLuk();
        this.hp = chr.getStat().getHp();
        this.mp = chr.getStat().getMp();
        this.maxhp = chr.getStat().getMaxHp();
        this.maxmp = chr.getStat().getMaxMp();
        this.exp = chr.getExp();
        this.hpApUsed = chr.getHpMpApUsed();
        this.remainingAp = chr.getRemainingAp();
        this.remainingSp = chr.getRemainingSps();
        this.savedFaces = chr.getSavedFaces();
        this.savedHairs = chr.getSavedHairs();
        this.beans = chr.getBeans();
        this.meso = chr.getMeso();
        this.skinColor = chr.getSkinColor();
        this.job = chr.getJob();
        this.hair = chr.getHair();
        this.face = chr.getFace();
        this.mapid = chr.getMapId();
        this.initialSpawnPoint = chr.getInitialSpawnpoint();
        this.marriageId = chr.getMarriageId();
        this.world = chr.getWorld();
        this.guildid = chr.getGuildId();
        this.guildrank = chr.getGuildRank();
        this.alliancerank = chr.getAllianceRank();
        this.gmLevel = (byte)chr.getGMLevel();
        this.CsMod = chr.getCsMod();
        this.fairyExp = chr.getFairyExp();
        this.clonez = chr.getNumClones();
        this.petStore = chr.getPetStores();
        this.subcategory = chr.getSubcategory();
        this.currentrep = chr.getCurrentRep();
        this.totalrep = chr.getTotalRep();
        this.familyid = chr.getFamilyId();
        this.seniorid = chr.getSeniorId();
        this.junior1 = chr.getJunior1();
        this.junior2 = chr.getJunior2();
        this.charmessage = chr.getcharmessage();
        this.expression = chr.getexpression();
        this.constellation = chr.getconstellation();
        this.blood = chr.getblood();
        this.month = chr.getmonth();
        this.jf = chr.getjf();
        this.zdjf = chr.getzdjf();
        this.rwjf = chr.getrwjf();
        this.zs = chr.getzs();
        this.cz = chr.getcz();
        this.dy = chr.getdy();
        this.rmb = chr.getrmb();
        this.yb = chr.getyb();
        this.playerPoints = chr.getplayerPoints();
        this.playerEnergy = chr.getplayerEnergy();
        this.jf1 = chr.getjf1();
        this.jf2 = chr.getjf2();
        this.jf3 = chr.getjf3();
        this.jf4 = chr.getjf4();
        this.jf5 = chr.getjf5();
        this.jf6 = chr.getjf6();
        this.jf7 = chr.getjf7();
        this.jf8 = chr.getjf8();
        this.jf9 = chr.getjf9();
        this.jf10 = chr.getjf10();
        this.day = chr.getday();
        this.battleshipHP = chr.currentBattleshipHP();
        this.prefix = chr.getPrefix();
        this.gachexp = chr.getGachExp();
        this.PGSXDJ = chr.getPGSXDJ();
        boolean uneq = false;
        for (int i = 0; i < this.petStore.length; ++i) {
            final MaplePet pet = chr.getPet(i);
            if (this.petStore[i] == 0) {
                this.petStore[i] = -1;
            }
            if (pet != null) {
                uneq = true;
                this.petStore[i] = (byte)Math.max((int)this.petStore[i], (int)pet.getInventoryPosition());
            }
        }
        if (uneq) {
            chr.unequipAllPets();
        }
        for (final BuddyEntry qs : chr.getBuddylist().getBuddies()) {
            this.buddies.put(qs, Boolean.valueOf(qs.isVisible()));
        }
        this.buddysize = chr.getBuddyCapacity();
        this.partyid = chr.getPartyId();
        if (chr.getMessenger() != null) {
            this.messengerid = chr.getMessenger().getId();
        }
        else {
            this.messengerid = 0;
        }
        this.mBookCover = chr.getMonsterBookCover();
        this.dojo = chr.getDojo();
        this.dojoRecord = (byte)chr.getDojoRecord();
        this.InfoQuest = chr.getInfoQuest_Map();
        for (final Entry<MapleQuest, MapleQuestStatus> qs2 : chr.getQuest_Map().entrySet()) {
            this.Quest.put(Integer.valueOf(((MapleQuest)qs2.getKey()).getId()), qs2.getValue());
        }
        this.mbook = chr.getMonsterBook().getCards();
        this.inventorys = chr.getInventorys();
        for (final Entry<ISkill, SkillEntry> qs3 : chr.getSkills().entrySet()) {
            this.Skills.put(Integer.valueOf(((ISkill)qs3.getKey()).getId()), qs3.getValue());
        }
        this.BlessOfFairy = chr.getBlessOfFairyOrigin();
        this.chalkboard = chr.getChalkboard();
        this.skillmacro = chr.getMacros();
        this.keymap = chr.getKeyLayout().Layout();
        this.savedlocation = chr.getSavedLocations();
        this.wishlist = chr.getWishlist();
        this.rocks = chr.getRocks();
        this.regrocks = chr.getRegRocks();
        for (final Integer zz : chr.getFamedCharacters()) {
            this.famedcharacters.add(zz);
        }
        this.lastfametime = chr.getLastFameTime();
        this.storage = chr.getStorage();
        this.cs = chr.getCashInventory();
        final MapleMount mount = chr.getMount();
        this.mount_itemid = mount.getItemId();
        this.mount_Fatigue = mount.getFatigue();
        this.mount_level = mount.getLevel();
        this.mount_exp = mount.getExp();
        this.TranferTime = System.currentTimeMillis();
    }
    
    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        this.nowmacs = in.readUTF();
        this.canTalk = in.readBoolean();
        this.DebugMessage = in.readBoolean();
        this.auto吸怪 = in.readBoolean();
        this.GM吸怪信息开关 = in.readBoolean();
        this.Vip_Medal = in.readBoolean();
        this.玩家私聊开关 = in.readBoolean();
        this.玩家密语开关 = in.readBoolean();
        this.好友聊天开关 = in.readBoolean();
        this.精灵商人购买开关 = in.readBoolean();
        this.smega = in.readBoolean();
        this.gashponmega = in.readBoolean();
        this.characterid = in.readInt();
        this.accountid = in.readInt();
        this.accountname = in.readUTF();
        this.accountsecondPassword = in.readUTF();
        this.channel = in.readByte();
        this.MaplePoints = in.readInt();
        this.loginkey = in.readUTF();
        this.serverkey = in.readUTF();
        this.clientkey = in.readUTF();
        this.antiMacro = in.readObject();
        this.name = in.readUTF();
        this.fame = in.readShort();
        this.gender = in.readByte();
        this.level = in.readShort();
        this.str = in.readShort();
        this.dex = in.readShort();
        this.int_ = in.readShort();
        this.luk = in.readShort();
        this.hp = in.readShort();
        this.mp = in.readShort();
        this.maxhp = in.readShort();
        this.maxmp = in.readShort();
        this.exp = in.readInt();
        this.hpApUsed = in.readShort();
        this.remainingAp = in.readShort();
        this.remainingSp = new int[in.readByte()];
        for (int i = 0; i < this.remainingSp.length; ++i) {
            this.remainingSp[i] = in.readInt();
        }
        this.savedHairs = new int[in.readByte()];
        for (int i = 0; i < this.savedHairs.length; ++i) {
            this.savedHairs[i] = in.readInt();
        }
        this.savedFaces = new int[in.readByte()];
        for (int i = 0; i < this.savedFaces.length; ++i) {
            this.savedFaces[i] = in.readInt();
        }
        this.beans = in.readInt();
        this.meso = in.readInt();
        this.skinColor = in.readByte();
        this.job = in.readShort();
        this.hair = in.readInt();
        this.face = in.readInt();
        this.mapid = in.readInt();
        this.initialSpawnPoint = in.readByte();
        this.world = in.readByte();
        this.guildid = in.readInt();
        this.guildrank = in.readByte();
        this.alliancerank = in.readByte();
        this.gmLevel = in.readByte();
        this.CsMod = in.readInt();
        this.vpoints = in.readInt();
        this.vip = in.readInt();
        this.mrqdTime = in.readLong();
        if (in.readByte() == 1) {
            this.BlessOfFairy = in.readUTF();
        }
        else {
            this.BlessOfFairy = null;
        }
        if (in.readByte() == 1) {
            this.chalkboard = in.readUTF();
        }
        else {
            this.chalkboard = null;
        }
        this.clonez = in.readByte();
        this.skillmacro = in.readObject();
        this.lastfametime = in.readLong();
        this.storage = in.readObject();
        this.cs = in.readObject();
        this.mount_itemid = in.readInt();
        this.mount_Fatigue = in.readByte();
        this.mount_level = in.readByte();
        this.mount_exp = in.readInt();
        this.partyid = in.readInt();
        this.messengerid = in.readInt();
        this.mBookCover = in.readInt();
        this.dojo = in.readInt();
        this.dojoRecord = in.readByte();
        this.inventorys = in.readObject();
        this.fairyExp = in.readByte();
        this.subcategory = in.readByte();
        this.marriageId = in.readInt();
        this.familyid = in.readInt();
        this.seniorid = in.readInt();
        this.junior1 = in.readInt();
        this.junior2 = in.readInt();
        this.currentrep = in.readInt();
        this.totalrep = in.readInt();
        this.charmessage = in.readUTF();
        this.expression = in.readByte();
        this.constellation = in.readInt();
        this.blood = in.readInt();
        this.month = in.readInt();
        this.jf = in.readInt();
        this.zdjf = in.readInt();
        this.rwjf = in.readInt();
        this.zs = in.readInt();
        this.cz = in.readInt();
        this.dy = in.readInt();
        this.rmb = in.readInt();
        this.yb = in.readInt();
        this.playerPoints = in.readInt();
        this.playerEnergy = in.readInt();
        this.jf1 = in.readInt();
        this.jf2 = in.readInt();
        this.jf3 = in.readInt();
        this.jf4 = in.readInt();
        this.jf5 = in.readInt();
        this.jf6 = in.readInt();
        this.jf7 = in.readInt();
        this.jf8 = in.readInt();
        this.jf9 = in.readInt();
        this.jf10 = in.readInt();
        this.day = in.readInt();
        this.battleshipHP = in.readInt();
        this.prefix = in.readUTF();
        this.gachexp = in.readInt();
        for (int mbooksize = in.readShort(), j = 0; j < mbooksize; ++j) {
            this.mbook.put(Integer.valueOf(in.readInt()), Integer.valueOf(in.readInt()));
        }
        for (int skillsize = in.readShort(), k = 0; k < skillsize; ++k) {
            this.Skills.put(Integer.valueOf(in.readInt()), new SkillEntry(in.readByte(), in.readByte(), in.readLong()));
        }
        this.buddysize = in.readByte();
        final short addedbuddysize = in.readShort();
        for (int l = 0; l < addedbuddysize; ++l) {
            this.buddies.put(new BuddyEntry(in.readUTF(), in.readInt(), in.readUTF(), in.readInt(), in.readBoolean(), in.readInt(), in.readInt()), Boolean.valueOf(in.readBoolean()));
        }
        for (int questsize = in.readShort(), m = 0; m < questsize; ++m) {
            this.Quest.put(Integer.valueOf(in.readInt()), in.readObject());
        }
        for (int achievesize = in.readShort(), i2 = 0; i2 < achievesize; ++i2) {
            this.finishedAchievements.add(Integer.valueOf(in.readInt()));
        }
        for (int famesize = in.readInt(), i3 = 0; i3 < famesize; ++i3) {
            this.famedcharacters.add(Integer.valueOf(in.readInt()));
        }
        final int savesize = in.readShort();
        this.savedlocation = new int[savesize];
        for (int i4 = 0; i4 < savesize; ++i4) {
            this.savedlocation[i4] = in.readInt();
        }
        final int wsize = in.readShort();
        this.wishlist = new int[wsize];
        for (int i5 = 0; i5 < wsize; ++i5) {
            this.wishlist[i5] = in.readInt();
        }
        final int rsize = in.readShort();
        this.rocks = new int[rsize];
        for (int i6 = 0; i6 < rsize; ++i6) {
            this.rocks[i6] = in.readInt();
        }
        final int resize = in.readShort();
        this.regrocks = new int[resize];
        for (int i7 = 0; i7 < resize; ++i7) {
            this.regrocks[i7] = in.readInt();
        }
        for (int infosize = in.readShort(), i8 = 0; i8 < infosize; ++i8) {
            this.InfoQuest.put(Integer.valueOf(in.readInt()), in.readUTF());
        }
        for (int keysize = in.readInt(), i9 = 0; i9 < keysize; ++i9) {
            this.keymap.put(Integer.valueOf(in.readInt()), new Pair<Byte, Integer>(Byte.valueOf(in.readByte()), Integer.valueOf(in.readInt())));
        }
        this.petStore = new byte[in.readByte()];
        for (int i9 = 0; i9 < 3; ++i9) {
            this.petStore[i9] = in.readByte();
        }
        this.TranferTime = System.currentTimeMillis();
    }
    
    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeUTF(this.nowmacs);
        out.writeBoolean(this.canTalk);
        out.writeBoolean(this.DebugMessage);
        out.writeBoolean(this.auto吸怪);
        out.writeBoolean(this.GM吸怪信息开关);
        out.writeBoolean(this.Vip_Medal);
        out.writeBoolean(this.精灵商人购买开关);
        out.writeBoolean(this.玩家私聊开关);
        out.writeBoolean(this.玩家密语开关);
        out.writeBoolean(this.好友聊天开关);
        out.writeBoolean(this.队伍聊天开关);
        out.writeBoolean(this.公会聊天开关);
        out.writeBoolean(this.联盟聊天开关);
        out.writeBoolean(this.smega);
        out.writeBoolean(this.gashponmega);
        out.writeInt(this.characterid);
        out.writeInt(this.accountid);
        out.writeUTF(this.accountname);
        out.writeUTF(this.accountsecondPassword);
        out.writeByte((int)this.channel);
        out.writeInt(this.MaplePoints);
        out.writeUTF(this.loginkey);
        out.writeUTF(this.serverkey);
        out.writeUTF(this.clientkey);
        out.writeObject(this.antiMacro);
        out.writeUTF(this.name);
        out.writeShort((int)this.fame);
        out.writeByte((int)this.gender);
        out.writeShort((int)this.level);
        out.writeShort((int)this.str);
        out.writeShort((int)this.dex);
        out.writeShort((int)this.int_);
        out.writeShort((int)this.luk);
        out.writeShort((int)this.hp);
        out.writeShort((int)this.mp);
        out.writeShort((int)this.maxhp);
        out.writeShort((int)this.maxmp);
        out.writeInt(this.exp);
        out.writeShort((int)this.hpApUsed);
        out.writeShort((int)this.remainingAp);
        out.writeByte(this.remainingSp.length);
        for (int i = 0; i < this.remainingSp.length; ++i) {
            out.writeInt(this.remainingSp[i]);
        }
        out.writeByte(this.savedHairs.length);
        for (int i = 0; i < this.savedHairs.length; ++i) {
            out.writeInt(this.savedHairs[i]);
        }
        out.writeByte(this.savedFaces.length);
        for (int i = 0; i < this.savedFaces.length; ++i) {
            out.writeInt(this.savedFaces[i]);
        }
        out.writeInt(this.beans);
        out.writeInt(this.meso);
        out.writeByte((int)this.skinColor);
        out.writeShort((int)this.job);
        out.writeInt(this.hair);
        out.writeInt(this.face);
        out.writeInt(this.mapid);
        out.writeByte((int)this.initialSpawnPoint);
        out.writeByte((int)this.world);
        out.writeInt(this.guildid);
        out.writeByte((int)this.guildrank);
        out.writeByte((int)this.alliancerank);
        out.writeByte((int)this.gmLevel);
        out.writeInt(this.CsMod);
        out.writeInt(this.vpoints);
        out.writeInt(this.vip);
        out.writeLong(this.mrqdTime);
        out.writeByte((int)((this.BlessOfFairy != null) ? 1 : 0));
        if (this.BlessOfFairy != null) {
            out.writeUTF(this.BlessOfFairy);
        }
        out.writeByte((int)((this.chalkboard != null) ? 1 : 0));
        if (this.chalkboard != null) {
            out.writeUTF(this.chalkboard);
        }
        out.writeByte((int)this.clonez);
        out.writeObject(this.skillmacro);
        out.writeLong(this.lastfametime);
        out.writeObject(this.storage);
        out.writeObject(this.cs);
        out.writeInt(this.mount_itemid);
        out.writeByte((int)this.mount_Fatigue);
        out.writeByte((int)this.mount_level);
        out.writeInt(this.mount_exp);
        out.writeInt(this.partyid);
        out.writeInt(this.messengerid);
        out.writeInt(this.mBookCover);
        out.writeInt(this.dojo);
        out.writeByte((int)this.dojoRecord);
        out.writeObject(this.inventorys);
        out.writeByte((int)this.fairyExp);
        out.writeByte((int)this.subcategory);
        out.writeInt(this.marriageId);
        out.writeInt(this.familyid);
        out.writeInt(this.seniorid);
        out.writeInt(this.junior1);
        out.writeInt(this.junior2);
        out.writeInt(this.currentrep);
        out.writeInt(this.totalrep);
        out.writeInt(this.battleshipHP);
        out.writeUTF(this.charmessage);
        out.writeInt(this.expression);
        out.writeInt(this.constellation);
        out.writeInt(this.blood);
        out.writeInt(this.month);
        out.writeInt(this.jf);
        out.writeInt(this.zdjf);
        out.writeInt(this.rwjf);
        out.writeInt(this.zs);
        out.writeInt(this.cz);
        out.writeInt(this.dy);
        out.writeInt(this.rmb);
        out.writeInt(this.yb);
        out.writeInt(this.playerPoints);
        out.writeInt(this.playerEnergy);
        out.writeInt(this.jf1);
        out.writeInt(this.jf2);
        out.writeInt(this.jf3);
        out.writeInt(this.jf4);
        out.writeInt(this.jf5);
        out.writeInt(this.jf6);
        out.writeInt(this.jf7);
        out.writeInt(this.jf8);
        out.writeInt(this.jf9);
        out.writeInt(this.jf10);
        out.writeInt(this.day);
        out.writeUTF(this.prefix);
        out.writeInt(this.gachexp);
        out.writeShort(this.mbook.size());
        for (final Entry<Integer, Integer> ms : this.mbook.entrySet()) {
            out.writeInt((int)Integer.valueOf(ms.getKey()));
            out.writeInt((int)Integer.valueOf(ms.getValue()));
        }
        out.writeShort(this.Skills.size());
        for (final Entry<Integer, SkillEntry> qs : this.Skills.entrySet()) {
            out.writeInt((int)Integer.valueOf(qs.getKey()));
            out.writeByte((int)((SkillEntry)qs.getValue()).skillevel);
            out.writeByte((int)((SkillEntry)qs.getValue()).masterlevel);
            out.writeLong(((SkillEntry)qs.getValue()).expiration);
        }
        out.writeByte((int)this.buddysize);
        out.writeShort(this.buddies.size());
        for (final Entry<BuddyEntry, Boolean> qs2 : this.buddies.entrySet()) {
            out.writeUTF(((BuddyEntry)qs2.getKey()).getName());
            out.writeInt(((BuddyEntry)qs2.getKey()).getCharacterId());
            out.writeUTF(((BuddyEntry)qs2.getKey()).getGroup());
            out.writeInt(((BuddyEntry)qs2.getKey()).getChannel());
            out.writeBoolean((boolean)Boolean.valueOf(qs2.getValue()));
            out.writeInt(((BuddyEntry)qs2.getKey()).getLevel());
            out.writeInt(((BuddyEntry)qs2.getKey()).getJob());
            out.writeBoolean((boolean)Boolean.valueOf(qs2.getValue()));
        }
        out.writeShort(this.Quest.size());
        for (final Entry<Integer, Object> qs3 : this.Quest.entrySet()) {
            out.writeInt((int)Integer.valueOf(qs3.getKey()));
            out.writeObject(qs3.getValue());
        }
        out.writeShort(this.finishedAchievements.size());
        for (final Integer zz : this.finishedAchievements) {
            out.writeInt((int)zz);
        }
        out.writeInt(this.famedcharacters.size());
        for (final Integer zz : this.famedcharacters) {
            out.writeInt((int)zz);
        }
        out.writeShort(this.savedlocation.length);
        for (final int zz2 : this.savedlocation) {
            out.writeInt(zz2);
        }
        out.writeShort(this.wishlist.length);
        for (final int zz2 : this.wishlist) {
            out.writeInt(zz2);
        }
        out.writeShort(this.rocks.length);
        for (final int zz2 : this.rocks) {
            out.writeInt(zz2);
        }
        out.writeShort(this.regrocks.length);
        for (final int zz2 : this.regrocks) {
            out.writeInt(zz2);
        }
        out.writeShort(this.InfoQuest.size());
        for (final Entry<Integer, String> qs4 : this.InfoQuest.entrySet()) {
            out.writeInt((int)Integer.valueOf(qs4.getKey()));
            out.writeUTF((String)qs4.getValue());
        }
        out.writeInt(this.keymap.size());
        for (final Entry<Integer, Pair<Byte, Integer>> qs5 : this.keymap.entrySet()) {
            out.writeInt((int)Integer.valueOf(qs5.getKey()));
            out.writeByte((int)(byte)Byte.valueOf(((Pair<Byte, Integer>)qs5.getValue()).left));
            out.writeInt((int)Integer.valueOf(((Pair<Byte, Integer>)qs5.getValue()).right));
        }
        out.writeByte(this.petStore.length);
        for (int i = 0; i < this.petStore.length; ++i) {
            out.writeByte((int)this.petStore[i]);
        }
    }
}
