package client;

import tools.data.MaplePacketLittleEndianWriter;
import client.inventory.MapleWeaponType;
import client.inventory.ModifyInventory;
import server.MapleInventoryManipulator;
import tools.MaplePacketCreator;
import java.util.Collection;
import server.MapleStatEffect;
import server.StructSetItem;
import java.util.Iterator;
import constants.ServerConfig;
import java.util.Calendar;
import server.StructSetItem.SetItem;
import java.util.Map.Entry;
import server.StructPotentialItem;
import client.inventory.IEquip;
import client.inventory.IItem;
import client.inventory.MapleInventoryType;
import constants.GameConstants;
import server.MapleItemInformationProvider;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import client.inventory.Equip;
import java.util.List;
import java.util.Map;
import java.lang.ref.WeakReference;
import java.io.Serializable;

public class PlayerStats implements Serializable
{
    private static final long serialVersionUID = -679541993413738569L;
    private final transient WeakReference<MapleCharacter> chr;
    private final Map<Integer, Integer> setHandling;
    private final List<Equip> durabilityHandling;
    private final List<Equip> equipLevelHandling;
    private transient float shouldHealHP;
    private transient float shouldHealMP;
    public short str;
    public short dex;
    public short luk;
    public short int_;
    public short hp;
    public short maxhp;
    public short mp;
    public short maxmp;
    private transient short passive_sharpeye_percent;
    private transient short localmaxhp;
    private transient short localmaxmp;
    private transient byte passive_mastery;
    private transient byte passive_sharpeye_rate;
    private transient int localstr;
    private transient int localdex;
    private transient int localluk;
    private transient int localint_;
    private transient int magic;
    private transient int watk;
    private transient int hands;
    private transient int accuracy;
    public transient boolean equippedWelcomeBackRing;
    public transient boolean equippedFairy;
    public transient boolean hasMeso;
    public transient boolean hasItem;
    public transient boolean hasVac;
    public transient boolean hasClone;
    public transient boolean hasPartyBonus;
    public transient boolean Berserk;
    public transient boolean isRecalc;
    public transient boolean equippedRing;
    public transient int equipmentBonusExp;
    public transient int expMod;
    public transient int dropMod;
    public transient int cashMod;
    public transient int levelBonus;
    public transient int expMod_H;
    public transient double expBuff;
    public transient double dropBuff;
    public transient double mesoBuff;
    public transient double cashBuff;
    public transient double realExpBuff;
    public transient double realDropBuff;
    public transient double realMesoBuff;
    public transient double realCashBuff;
    public transient double dam_r;
    public transient double bossdam_r;
    public transient double dropm;
    public transient double expm;
    public transient int recoverHP;
    public transient int recoverMP;
    public transient int mpconReduce;
    public transient int incMesoProp;
    public transient int incRewardProp;
    public transient int DAMreflect;
    public transient int DAMreflect_rate;
    public transient int mpRestore;
    public transient int hpRecover;
    public transient int hpRecoverProp;
    public transient int mpRecover;
    public transient int mpRecoverProp;
    public transient int RecoveryUP;
    public transient int incAllskill;
    private transient float speedMod;
    private transient float jumpMod;
    private transient float localmaxbasedamage;
    public transient int def;
    public transient int element_ice;
    public transient int element_fire;
    public transient int element_light;
    public transient int element_psn;
    public static final short maxStr = 999;
    public ReentrantLock lock;
    public short pickRate;
    public int defRange;
    public transient int dotTime;
    public transient boolean 精灵吊坠;
    
    public PlayerStats(final MapleCharacter chr) {
        this.equipLevelHandling = new ArrayList<Equip>();
        this.Berserk = false;
        this.isRecalc = false;
        this.expMod_H = 0;
        this.lock = new ReentrantLock();
        this.setHandling = new HashMap<Integer, Integer>();
        this.durabilityHandling = new ArrayList<Equip>();
        this.chr = new WeakReference<MapleCharacter>(chr);
    }
    
    public final void init() {
        this.recalcLocalStats();
        this.relocHeal();
    }
    
    public final short getStr() {
        return this.str;
    }
    
    public final short getDex() {
        return this.dex;
    }
    
    public final short getLuk() {
        return this.luk;
    }
    
    public final short getInt() {
        return this.int_;
    }
    
    public final void setStr(final short str) {
        this.str = str;
        this.recalcLocalStats();
    }
    
    public final void setDex(final short dex) {
        this.dex = dex;
        this.recalcLocalStats();
    }
    
    public final void setLuk(final short luk) {
        this.luk = luk;
        this.recalcLocalStats();
    }
    
    public final void setInt(final short int_) {
        this.int_ = int_;
        this.recalcLocalStats();
    }
    
    public final boolean setHp(final int newhp) {
        return this.setHp(newhp, false);
    }
    
    public final boolean setHp(final int newhp, final boolean silent) {
        final short oldHp = this.hp;
        int thp = newhp;
        if (thp < 0) {
            thp = 0;
        }
        if (thp > this.localmaxhp) {
            thp = this.localmaxhp;
        }
        this.hp = (short)thp;
        final MapleCharacter chra = (MapleCharacter)this.chr.get();
        if (chra != null) {
            if (!silent) {
                chra.updatePartyMemberHP();
            }
            if (oldHp > this.hp && !chra.isAlive()) {
                chra.playerDead();
            }
        }
        return this.hp != oldHp;
    }
    
    public final boolean setMp(final int newmp) {
        final short oldMp = this.mp;
        int tmp = newmp;
        if (tmp < 0) {
            tmp = 0;
        }
        if (tmp > this.localmaxmp) {
            tmp = this.localmaxmp;
        }
        this.mp = (short)tmp;
        return this.mp != oldMp;
    }
    
    public final void setMaxHp(final short hp) {
        this.maxhp = hp;
        this.recalcLocalStats();
    }
    
    public final void setMaxMp(final short mp) {
        this.maxmp = mp;
        this.recalcLocalStats();
    }
    
    public final short getHp() {
        return this.hp;
    }
    
    public final short getMaxHp() {
        return this.maxhp;
    }
    
    public final short getMp() {
        return this.mp;
    }
    
    public final short getMaxMp() {
        return this.maxmp;
    }
    
    public final int getTotalDex() {
        return this.localdex;
    }
    
    public final int getTotalInt() {
        return this.localint_;
    }
    
    public final int getTotalStr() {
        return this.localstr;
    }
    
    public final int getTotalLuk() {
        return this.localluk;
    }
    
    public final int getTotalMagic() {
        return this.magic;
    }
    
    public final double getSpeedMod() {
        return (double)this.speedMod;
    }
    
    public final double getJumpMod() {
        return (double)this.jumpMod;
    }
    
    public final int getTotalWatk() {
        return this.watk;
    }
    
    public final short getCurrentMaxHp() {
        return this.localmaxhp;
    }
    
    public final short getCurrentMaxMp() {
        return this.localmaxmp;
    }
    
    public final int getHands() {
        return this.hands;
    }
    
    public final float getCurrentMaxBaseDamage() {
        return this.localmaxbasedamage;
    }
    
    public void recalcLocalStats() {
        this.recalcLocalStats(false);
    }
    
    public void recalcLocalStats(final boolean first_login) {
        final MapleCharacter chra = (MapleCharacter)this.chr.get();
        if (chra == null) {
            return;
        }
        if (this.isRecalc) {
            return;
        }
        this.isRecalc = true;
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final int oldmaxhp = this.localmaxhp;
        int localmaxhp_ = this.getMaxHp();
        int localmaxmp_ = this.getMaxMp();
        this.localdex = this.getDex();
        this.localint_ = this.getInt();
        this.localstr = this.getStr();
        this.localluk = this.getLuk();
        int speed = 100;
        int jump = 100;
        this.dotTime = 0;
        int percent_hp = 0;
        int percent_mp = 0;
        int percent_str = 0;
        int percent_dex = 0;
        int percent_int = 0;
        int percent_luk = 0;
        int percent_acc = 0;
        int percent_atk = 0;
        int percent_matk = 0;
        int added_sharpeye_rate = 0;
        int added_sharpeye_dmg = 0;
        this.magic = this.localint_;
        this.watk = 0;
        if (chra.getJob() == 500 || (chra.getJob() >= 520 && chra.getJob() <= 522)) {
            this.watk = 20;
        }
        else if (chra.getJob() == 400 || (chra.getJob() >= 410 && chra.getJob() <= 412) || (chra.getJob() >= 1400 && chra.getJob() <= 1412)) {
            this.watk = 30;
        }
        this.dam_r = 100.0;
        this.bossdam_r = 100.0;
        this.realExpBuff = 100.0;
        this.realCashBuff = 100.0;
        this.realDropBuff = 100.0;
        this.realMesoBuff = 100.0;
        this.expBuff = 100.0;
        this.cashBuff = 100.0;
        this.dropBuff = 100.0;
        this.mesoBuff = 100.0;
        this.recoverHP = 0;
        this.recoverMP = 0;
        this.mpconReduce = 0;
        this.incMesoProp = 0;
        this.incRewardProp = 0;
        this.DAMreflect = 0;
        this.DAMreflect_rate = 0;
        this.hpRecover = 0;
        this.hpRecoverProp = 0;
        this.mpRecover = 0;
        this.mpRecoverProp = 0;
        this.mpRestore = 0;
        this.equippedWelcomeBackRing = false;
        this.equippedRing = false;
        this.equippedFairy = false;
        this.hasMeso = false;
        this.hasItem = false;
        this.hasPartyBonus = false;
        this.hasVac = false;
        this.hasClone = false;
        final boolean canEquipLevel = chra.getLevel() >= 120 && !GameConstants.isKOC((int)chra.getJob());
        this.equipmentBonusExp = 0;
        this.RecoveryUP = 0;
        this.dropMod = 1;
        this.dropm = 1.0;
        this.expMod = 1;
        this.expm = 1.0;
        this.cashMod = 1;
        this.精灵吊坠 = false;
        this.levelBonus = 0;
        this.incAllskill = 0;
        this.durabilityHandling.clear();
        this.equipLevelHandling.clear();
        this.setHandling.clear();
        this.element_fire = 100;
        this.element_ice = 100;
        this.element_light = 100;
        this.element_psn = 100;
        this.def = 100;
        this.defRange = 0;
        for (final IItem item : chra.getInventory(MapleInventoryType.EQUIPPED)) {
            final IEquip equip = (IEquip)item;
            if (equip.getPosition() == -11 && GameConstants.isMagicWeapon(equip.getItemId())) {
                final Map<String, Integer> eqstat = MapleItemInformationProvider.getInstance().getEquipStats(equip.getItemId());
                this.element_fire = (int)Integer.valueOf(eqstat.get((Object)"incRMAF"));
                this.element_ice = (int)Integer.valueOf(eqstat.get((Object)"incRMAI"));
                this.element_light = (int)Integer.valueOf(eqstat.get((Object)"incRMAL"));
                this.element_psn = (int)Integer.valueOf(eqstat.get((Object)"incRMAS"));
                this.def = (int)Integer.valueOf(eqstat.get((Object)"elemDefault"));
            }
            this.accuracy += equip.getAcc();
            localmaxhp_ += equip.getHp();
            localmaxmp_ += equip.getMp();
            this.localdex += equip.getDex();
            this.localint_ += equip.getInt();
            this.localstr += equip.getStr();
            this.localluk += equip.getLuk();
            this.magic += equip.getMatk() + equip.getInt();
            this.watk += equip.getWatk();
            speed += equip.getSpeed();
            jump += equip.getJump();
            switch (equip.getItemId()) {
                case 1122017: {
                    this.精灵吊坠 = true;
                    this.equippedFairy = true;
                    break;
                }
                case 1112427: {
                    added_sharpeye_rate += 5;
                    added_sharpeye_dmg += 20;
                    break;
                }
                case 1112428: {
                    added_sharpeye_rate += 10;
                    added_sharpeye_dmg += 10;
                    break;
                }
                case 1112429: {
                    added_sharpeye_rate += 5;
                    added_sharpeye_dmg += 20;
                    break;
                }
                case 1112127: {
                    this.equippedWelcomeBackRing = true;
                    break;
                }
                case 1114000: {
                    this.equippedRing = true;
                    break;
                }
                case 1122086:
                case 1122207:
                case 1122215: {
                    this.equippedFairy = true;
                    break;
                }
                case 1812000: {
                    this.hasMeso = true;
                    break;
                }
                case 1812001: {
                    this.hasItem = true;
                    break;
                }
                default: {
                    for (final int eb_bonus : GameConstants.Equipments_Bonus) {
                        if (equip.getItemId() == eb_bonus) {
                            this.equipmentBonusExp += GameConstants.Equipment_Bonus_EXP(eb_bonus);
                            break;
                        }
                    }
                    break;
                }
            }
            percent_hp += equip.getHpR();
            percent_mp += equip.getMpR();
            final int set = ii.getSetItemID(equip.getItemId());
            if (set > 0) {
                int value = 1;
                if (this.setHandling.get((Object)Integer.valueOf(set)) != null) {
                    value += (int)Integer.valueOf(this.setHandling.get((Object)Integer.valueOf(set)));
                }
                this.setHandling.put(Integer.valueOf(set), Integer.valueOf(value));
            }
            if (equip.getState() > 1) {
                final int[] array;
                final int[] potentials = array = new int[] { equip.getPotential1(), equip.getPotential2(), equip.getPotential3() };
                for (final int i : array) {
                    if (i > 0) {
                        final StructPotentialItem pot = (StructPotentialItem)ii.getPotentialInfo(i).get(ii.getReqLevel(equip.getItemId()) / 10);
                        if (pot != null) {
                            this.localstr += pot.incSTR;
                            this.localdex += pot.incDEX;
                            this.localint_ += pot.incINT;
                            this.localluk += pot.incLUK;
                            this.localmaxhp += pot.incMHP;
                            this.localmaxmp += pot.incMMP;
                            this.watk += pot.incPAD;
                            this.magic += pot.incINT + pot.incMAD;
                            speed += pot.incSpeed;
                            jump += pot.incJump;
                            this.accuracy += pot.incACC;
                            this.incAllskill += pot.incAllskill;
                            percent_hp += pot.incMHPr;
                            percent_mp += pot.incMMPr;
                            percent_str += pot.incSTRr;
                            percent_dex += pot.incDEXr;
                            percent_int += pot.incINTr;
                            percent_luk += pot.incLUKr;
                            percent_acc += pot.incACCr;
                            percent_atk += pot.incPADr;
                            percent_matk += pot.incMADr;
                            added_sharpeye_rate += pot.incCr;
                            added_sharpeye_dmg += pot.incCr;
                            if (!pot.boss) {
                                this.dam_r = Math.max((double)pot.incDAMr, this.dam_r);
                            }
                            else {
                                this.bossdam_r = Math.max((double)pot.incDAMr, this.bossdam_r);
                            }
                            this.recoverHP += pot.RecoveryHP;
                            this.recoverMP += pot.RecoveryMP;
                            this.RecoveryUP += pot.RecoveryUP;
                            if (pot.HP > 0) {
                                this.hpRecover += pot.HP;
                                this.hpRecoverProp += pot.prop;
                            }
                            if (pot.MP > 0) {
                                this.mpRecover += pot.MP;
                                this.mpRecoverProp += pot.prop;
                            }
                            this.mpconReduce += pot.mpconReduce;
                            this.incMesoProp += pot.incMesoProp;
                            this.incRewardProp += pot.incRewardProp;
                            if (pot.DAMreflect > 0) {
                                this.DAMreflect += pot.DAMreflect;
                                this.DAMreflect_rate += pot.prop;
                            }
                            this.mpRestore += pot.mpRestore;
                            if (!first_login && pot.skillID > 0) {
                                chra.changeSkillLevel_Skip(SkillFactory.getSkill(this.getSkillByJob((int)pot.skillID, (int)chra.getJob())), (byte)1, (byte)1);
                            }
                        }
                    }
                }
            }
            if (equip.getDurability() > 0) {
                this.durabilityHandling.add((Equip)equip);
            }
            if (canEquipLevel && GameConstants.getMaxLevel(equip.getItemId()) > 0) {
                if (GameConstants.getStatFromWeapon(equip.getItemId()) == null) {
                    if (equip.getEquipLevel() > GameConstants.getMaxLevel(equip.getItemId())) {
                        continue;
                    }
                }
                else if (equip.getEquipLevel() >= GameConstants.getMaxLevel(equip.getItemId())) {
                    continue;
                }
                this.equipLevelHandling.add((Equip)equip);
            }
        }
        for (final Entry<Integer, Integer> entry : this.setHandling.entrySet()) {
            final StructSetItem set2 = ii.getSetItem((int)Integer.valueOf(entry.getKey()));
            if (set2 != null) {
                final Map<Integer, SetItem> itemz = set2.getItems();
                for (final Entry<Integer, SetItem> ent : itemz.entrySet()) {
                    if ((int)Integer.valueOf(ent.getKey()) <= (int)Integer.valueOf(entry.getValue())) {
                        final SetItem se = (SetItem)ent.getValue();
                        this.localstr += se.incSTR;
                        this.localdex += se.incDEX;
                        this.localint_ += se.incINT;
                        this.localluk += se.incLUK;
                        this.watk += se.incPAD;
                        this.magic += se.incINT + se.incMAD;
                        speed += se.incSpeed;
                        this.accuracy += se.incACC;
                        localmaxhp_ += se.incMHP;
                        localmaxmp_ += se.incMMP;
                    }
                }
            }
        }
        final int hour = Calendar.getInstance().get(11);
        final int weekDay = Calendar.getInstance().get(7);
        if (chra.getMarriageId() > 0) {
            this.expm = 1.1;
            this.dropm = 1.1;
        }
        this.expMod = 1;
        this.dropMod = 1;
        for (final IItem item2 : chra.getInventory(MapleInventoryType.CASH)) {
            if (this.expMod < 3 && (item2.getItemId() == 5211060 || item2.getItemId() == 5211050 || item2.getItemId() == 5211051 || item2.getItemId() == 5211052 || item2.getItemId() == 5211053 || item2.getItemId() == 5211054)) {
                this.expMod = 3;
            }
            else if (this.expMod < 2 && (item2.getItemId() == 5211061 || item2.getItemId() == 5211000 || item2.getItemId() == 5211001 || item2.getItemId() == 5211002 || item2.getItemId() == 5211003 || item2.getItemId() == 5211046 || item2.getItemId() == 5211047 || item2.getItemId() == 5211048 || item2.getItemId() == 5211049)) {
                this.expMod = 2;
            }
            else if (this.expMod < 2 && (item2.getItemId() == 5210002 || item2.getItemId() == 5210003) && ((hour >= 6 && hour <= 18 && weekDay >= 2 && weekDay <= 6) || weekDay == 1 || weekDay == 7)) {
                this.expMod = 2;
            }
            else if (this.expMod < 2 && (item2.getItemId() == 5210004 || item2.getItemId() == 5210005 || item2.getItemId() == 521000) && (((hour >= 18 || hour <= 6) && weekDay >= 2 && weekDay <= 6) || weekDay == 1 || weekDay == 7)) {
                this.expMod = 2;
            }
            else if (this.expMod < 2 && (item2.getItemId() == 5210000 || item2.getItemId() == 5210001) && ((hour >= 10 && hour <= 22 && weekDay >= 2 && weekDay <= 6) || weekDay == 1 || weekDay == 7)) {
                this.expMod = 2;
            }
            if (this.dropMod == 1) {
                if (item2.getItemId() == 5360015) {
                    this.dropMod = 2;
                }
                else if (item2.getItemId() == 5360000 && hour >= 0 && hour <= 6) {
                    this.dropMod = 2;
                }
                else if (item2.getItemId() == 5360001 && hour >= 6 && hour <= 12) {
                    this.dropMod = 2;
                }
                else if (item2.getItemId() == 5360002 && hour >= 12 && hour <= 18) {
                    this.dropMod = 2;
                }
                else if (item2.getItemId() == 5360003 && hour >= 18 && hour <= 24) {
                    this.dropMod = 2;
                }
            }
            if (item2.getItemId() == 5650000) {
                this.hasPartyBonus = true;
            }
            else if (item2.getItemId() == 5590001) {
                this.levelBonus = 10;
            }
            else {
                if (this.levelBonus != 0 || item2.getItemId() != 5590000) {
                    continue;
                }
                this.levelBonus = 5;
            }
        }
        if (chra.getHiredChannel() > 0) {
            this.expMod_H = 10;
        }
        if (chra.getLevel() >= 1 && chra.getLevel() <= 120) {
            this.expMod *= ServerConfig.BeiShu1;
        }
        else if (chra.getLevel() > 120 && chra.getLevel() <= 200) {
            this.expMod *= ServerConfig.BeiShu2;
        }
        else if (chra.getLevel() > 200 && chra.getLevel() <= 250) {
            this.expMod *= ServerConfig.BeiShu3;
        }
        for (final IItem item2 : chra.getInventory(MapleInventoryType.ETC)) {
            switch (item2.getItemId()) {
                case 5062000: {
                    this.hasVac = true;
                    continue;
                }
                case 4030004: {
                    this.hasClone = true;
                    continue;
                }
                case 4030005: {
                    this.cashMod = 2;
                    continue;
                }
                case 4101000:
                case 4101002: {
                    this.equippedFairy = true;
                    chra.setFairyExp((byte)30);
                    continue;
                }
            }
        }
        for (final IItem item2 : chra.getInventory(MapleInventoryType.CASH)) {
            switch (item2.getItemId()) {
                case 5062000: {
                    this.hasVac = true;
                    continue;
                }
            }
        }
        this.magic += chra.getSkillLevel(SkillFactory.getSkill(22000000));
        this.localstr = (int)((float)this.localstr + (float)(percent_str * this.localstr) / 100.0f);
        this.localdex = (int)((float)this.localdex + (float)(percent_dex * this.localdex) / 100.0f);
        final int before_ = this.localint_;
        this.localint_ = (int)((float)this.localint_ + (float)(percent_int * this.localint_) / 100.0f);
        this.magic += this.localint_ - before_;
        this.localluk = (int)((float)this.localluk + (float)(percent_luk * this.localluk) / 100.0f);
        this.accuracy = (int)((float)this.accuracy + (float)(percent_acc * this.accuracy) / 100.0f);
        this.watk = (int)((float)this.watk + (float)(percent_atk * this.watk) / 100.0f);
        this.magic = (int)((float)this.magic + (float)(percent_matk * this.magic) / 100.0f);
        localmaxhp_ = (int)((float)localmaxhp_ + (float)(percent_hp * localmaxhp_) / 100.0f);
        localmaxmp_ = (int)((float)localmaxmp_ + (float)(percent_mp * localmaxmp_) / 100.0f);
        this.magic = Math.min(this.magic, 1999);
        Integer buff = chra.getBuffedValue(MapleBuffStat.MAPLE_WARRIOR);
        if (buff != null) {
            final double d = (double)buff / 100.0;
            this.localstr = (int)((double)this.localstr + d * (double)this.str);
            this.localdex = (int)((double)this.localdex + d * (double)this.dex);
            this.localluk = (int)((double)this.localluk + d * (double)this.luk);
            final int before = this.localint_;
            this.localint_ = (int)((double)this.localint_ + d * (double)this.int_);
            this.magic += this.localint_ - before;
        }
        buff = chra.getBuffedValue(MapleBuffStat.ECHO_OF_HERO);
        if (buff != null) {
            final double d = (double)buff / 100.0;
            this.watk += (int)((double)this.watk * d);
            this.magic += (int)((double)this.magic * d);
        }
        buff = chra.getBuffedValue(MapleBuffStat.ARAN_COMBO);
        if (buff != null) {
            this.watk += (int)buff / 10;
        }
        buff = chra.getBuffedValue(MapleBuffStat.MAXHP);
        if (buff != null) {
            localmaxhp_ = (int)((double)localmaxhp_ + (double)buff / 100.0 * (double)localmaxhp_);
        }
        buff = chra.getBuffedValue(MapleBuffStat.MAXMP);
        if (buff != null) {
            localmaxmp_ = (int)((double)localmaxmp_ + (double)buff / 100.0 * (double)localmaxmp_);
        }
        switch (chra.getJob()) {
            case 322: {
                final ISkill expert = SkillFactory.getSkill(3220004);
                final int boostLevel = chra.getSkillLevel(expert);
                if (boostLevel > 0) {
                    this.watk += expert.getEffect(boostLevel).getX();
                    break;
                }
                break;
            }
            case 312: {
                final ISkill expert = SkillFactory.getSkill(3120005);
                final int boostLevel = chra.getSkillLevel(expert);
                if (boostLevel > 0) {
                    this.watk += expert.getEffect(boostLevel).getX();
                    break;
                }
                break;
            }
            case 211:
            case 212: {
                final ISkill amp = SkillFactory.getSkill(2110001);
                final int level = chra.getSkillLevel(amp);
                if (level > 0) {
                    this.dam_r *= (double)amp.getEffect(level).getY() / 100.0;
                    this.bossdam_r *= (double)amp.getEffect(level).getY() / 100.0;
                    break;
                }
                break;
            }
            case 221:
            case 222: {
                final ISkill amp = SkillFactory.getSkill(2210001);
                final int level = chra.getSkillLevel(amp);
                if (level > 0) {
                    this.dam_r *= (double)amp.getEffect(level).getY() / 100.0;
                    this.bossdam_r *= (double)amp.getEffect(level).getY() / 100.0;
                    break;
                }
                break;
            }
            case 1211:
            case 1212: {
                final ISkill amp = SkillFactory.getSkill(12110001);
                final int level = chra.getSkillLevel(amp);
                if (level > 0) {
                    this.dam_r *= (double)amp.getEffect(level).getY() / 100.0;
                    this.bossdam_r *= (double)amp.getEffect(level).getY() / 100.0;
                    break;
                }
                break;
            }
            case 2112: {
                final ISkill expert = SkillFactory.getSkill(21120001);
                final int boostLevel = chra.getSkillLevel(expert);
                if (boostLevel > 0) {
                    this.watk += expert.getEffect(boostLevel).getX();
                    break;
                }
                break;
            }
        }
        final ISkill blessoffairy = SkillFactory.getSkill(GameConstants.getBofForJob((int)chra.getJob()));
        final int boflevel = chra.getSkillLevel(blessoffairy);
        if (boflevel > 0) {
            this.watk += blessoffairy.getEffect(boflevel).getX();
            this.magic += blessoffairy.getEffect(boflevel).getY();
            this.accuracy += blessoffairy.getEffect(boflevel).getX();
        }
        buff = chra.getBuffedValue(MapleBuffStat.EXPRATE);
        if (buff != null) {
            this.expBuff *= (double)buff / 100.0;
            this.realExpBuff += (double)buff;
        }
        if (chra.isBuffedValue(2382046)) {
            this.realMesoBuff += 100.0;
            this.mesoBuff *= 2.0;
            this.realDropBuff += 200.0;
            this.dropBuff *= 3.0;
        }
        else if (chra.isBuffedValue(2382028)) {
            this.realMesoBuff += 100.0;
            this.mesoBuff *= 2.0;
            this.realDropBuff += 200.0;
            this.dropBuff *= 3.0;
        }
        buff = chra.getBuffedValue(MapleBuffStat.DROP_RATE);
        if (buff != null) {
            if (chra.getBuffSource(MapleBuffStat.DROP_RATE) == 2382028) {
                switch (chra.getMapId()) {
                    case 100040101:
                    case 100040102:
                    case 100040103:
                    case 100040104:
                    case 107000401:
                    case 107000402:
                    case 107000403:
                    case 191000000: {
                        this.realDropBuff += (double)buff;
                        this.dropBuff *= (double)buff / 100.0;
                        break;
                    }
                }
            }
            else if (chra.getBuffSource(MapleBuffStat.DROP_RATE) == 2382028) {
                switch (chra.getMapId()) {
                    case 222020100:
                    case 222020200:
                    case 222020300: {
                        this.realDropBuff += (double)buff;
                        this.dropBuff *= (double)buff / 100.0;
                        break;
                    }
                }
            }
            else if (chra.getBuffSource(MapleBuffStat.DROP_RATE) == 2022462) {
                this.realDropBuff += 50.0;
                this.dropBuff *= 1.5;
            }
            else if (chra.getBuffSource(MapleBuffStat.DROP_RATE) == 2382001) {
                this.realMesoBuff += 100.0;
                this.mesoBuff *= 2.0;
                this.realDropBuff += 200.0;
                this.dropBuff *= 3.0;
            }
            else if (chra.getBuffSource(MapleBuffStat.DROP_RATE) == 2382040) {
                this.realMesoBuff += 100.0;
                this.mesoBuff *= 2.0;
                this.realDropBuff += 200.0;
                this.dropBuff *= 3.0;
            }
            else if (chra.getBuffSource(MapleBuffStat.DROP_RATE) == 2383003) {
                this.realMesoBuff += 100.0;
                this.mesoBuff *= 2.0;
                this.realDropBuff += 200.0;
                this.dropBuff *= 3.0;
            }
            else if (chra.getBuffSource(MapleBuffStat.DROP_RATE) == 2383006) {
                this.realDropBuff += 300.0;
                this.dropBuff *= 4.0;
            }
            else {
                this.realDropBuff += (double)buff;
                this.dropBuff *= (double)buff / 100.0;
            }
        }
        buff = chra.getBuffedValue(MapleBuffStat.ACASH_RATE);
        if (buff != null) {
            this.realCashBuff += (double)buff;
            this.cashBuff *= (double)buff / 100.0;
        }
        buff = chra.getBuffedValue(MapleBuffStat.MESO_RATE);
        if (buff != null) {
            if (chra.getBuffSource(MapleBuffStat.MESO_RATE) == 2382005 || chra.getBuffSource(MapleBuffStat.MESO_RATE) == 2382016) {
                if (chra.getMapId() >= 221020000 && chra.getMapId() <= 221024400) {
                    this.mesoBuff *= (double)buff / 100.0;
                    this.realMesoBuff += (double)buff;
                }
            }
            else if (chra.getBuffSource(MapleBuffStat.MESO_RATE) == 2022459) {
                this.realMesoBuff += 30.0;
                this.mesoBuff *= 1.3;
            }
            else if (chra.getBuffSource(MapleBuffStat.MESO_RATE) == 2022460) {
                this.realMesoBuff += 50.0;
                this.mesoBuff *= 1.5;
            }
            else {
                this.realMesoBuff += (double)buff;
                this.mesoBuff *= (double)buff / 100.0;
            }
        }
        buff = chra.getBuffedValue(MapleBuffStat.MESOUP);
        if (buff != null) {
            this.realMesoBuff += (double)buff;
            this.mesoBuff *= (double)buff / 100.0;
        }
        buff = chra.getBuffedValue(MapleBuffStat.ACC);
        if (buff != null) {
            this.accuracy += (int)buff;
        }
        buff = chra.getBuffedValue(MapleBuffStat.WATK);
        if (buff != null) {
            this.watk += (int)buff;
        }
        buff = chra.getBuffedValue(MapleBuffStat.MATK);
        if (buff != null) {
            this.magic += (int)buff;
        }
        buff = chra.getBuffedValue(MapleBuffStat.SPEED);
        if (buff != null) {
            speed += (int)buff;
        }
        buff = chra.getBuffedValue(MapleBuffStat.JUMP);
        if (buff != null) {
            jump += (int)buff;
        }
        buff = chra.getBuffedValue(MapleBuffStat.DASH_SPEED);
        if (buff != null) {
            speed += (int)buff;
        }
        buff = chra.getBuffedValue(MapleBuffStat.DASH_JUMP);
        if (buff != null) {
            jump += (int)buff;
        }
        buff = chra.getBuffedValue(MapleBuffStat.WIND_WALK);
        if (buff != null) {
            final MapleStatEffect eff = chra.getStatForBuff(MapleBuffStat.WIND_WALK);
            this.dam_r *= (double)eff.getDamage() / 100.0;
            this.bossdam_r *= (double)eff.getDamage() / 100.0;
        }
        buff = chra.getBuffedSkill_Y(MapleBuffStat.OWL_SPIRIT);
        if (buff != null) {
            this.dam_r *= (double)buff / 100.0;
            this.bossdam_r *= (double)buff / 100.0;
        }
        buff = chra.getBuffedValue(MapleBuffStat.BERSERK_FURY);
        if (buff != null) {
            this.dam_r *= 2.0;
            this.bossdam_r *= 2.0;
        }
        final ISkill bx = SkillFactory.getSkill(1320006);
        if (chra.getSkillLevel(bx) > 0) {
            this.dam_r *= (double)bx.getEffect((int)chra.getSkillLevel(bx)).getDamage() / 100.0;
            this.bossdam_r *= (double)bx.getEffect((int)chra.getSkillLevel(bx)).getDamage() / 100.0;
        }
        buff = chra.getBuffedValue(MapleBuffStat.WK_CHARGE);
        if (buff != null) {
            final MapleStatEffect eff2 = chra.getStatForBuff(MapleBuffStat.WK_CHARGE);
            this.dam_r *= (double)eff2.getDamage() / 100.0;
            this.bossdam_r *= (double)eff2.getDamage() / 100.0;
        }
        buff = chra.getBuffedValue(MapleBuffStat.MONSTER_RIDING);
        if (buff != null) {
            final MapleStatEffect eff2 = chra.getStatForBuff(MapleBuffStat.MONSTER_RIDING);
            this.pickRate = eff2.getProb();
        }
        buff = chra.getBuffedValue(MapleBuffStat.LIGHTNING_CHARGE);
        if (buff != null) {
            final MapleStatEffect eff2 = chra.getStatForBuff(MapleBuffStat.LIGHTNING_CHARGE);
            this.dam_r *= (double)eff2.getDamage() / 100.0;
            this.bossdam_r *= (double)eff2.getDamage() / 100.0;
        }
        buff = chra.getBuffedSkill_X(MapleBuffStat.SHARP_EYES);
        if (buff != null) {
            added_sharpeye_rate += (int)buff;
        }
        buff = chra.getBuffedSkill_Y(MapleBuffStat.SHARP_EYES);
        if (buff != null) {
            added_sharpeye_dmg += (int)buff - 100;
        }
        if (speed > 140) {
            speed = 140;
        }
        if (jump > 123) {
            jump = 123;
        }
        this.speedMod = (float)speed / 100.0f;
        this.jumpMod = (float)jump / 100.0f;
        final Integer mount = chra.getBuffedValue(MapleBuffStat.MONSTER_RIDING);
        if (mount != null) {
            this.jumpMod = 1.23f;
            switch ((int)mount) {
                case 1: {
                    this.speedMod = 1.5f;
                    break;
                }
                case 2: {
                    this.speedMod = 1.7f;
                    break;
                }
                case 3: {
                    this.speedMod = 1.8f;
                    break;
                }
                default: {
                    System.err.println("Unhandeled monster riding level, Speedmod = " + this.speedMod + "");
                    break;
                }
            }
        }
        this.hands = this.localdex + this.localint_ + this.localluk;
        this.localmaxhp = (short)Math.min(30000, Math.abs(Math.max(-30000, localmaxhp_)));
        this.localmaxmp = (short)Math.min(30000, Math.abs(Math.max(-30000, localmaxmp_)));
        this.CalcPassive_SharpEye(chra, added_sharpeye_rate, added_sharpeye_dmg);
        this.CalcPassive_Mastery(chra);
        this.CalcPassive_Range(chra);
        if (first_login) {
            chra.silentEnforceMaxHpMp();
        }
        else {
            chra.enforceMaxHpMp();
        }
        this.localmaxbasedamage = this.calculateMaxBaseDamage(this.magic, this.watk);
        if (oldmaxhp != 0 && oldmaxhp != this.localmaxhp) {
            chra.updatePartyMemberHP();
        }
        this.isRecalc = false;
    }
    
    public boolean checkEquipLevels(final MapleCharacter chr, final int gain) {
        boolean changed = false;
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final List<Equip> all = new ArrayList<Equip>((Collection<? extends Equip>)this.equipLevelHandling);
        for (Equip eq : all) {
            final int lvlz = eq.getEquipLevel();
            eq.setItemEXP(Math.min(eq.getItemEXP() + gain, Integer.MAX_VALUE));
            if (eq.getEquipLevel() > lvlz) {
                for (int i = eq.getEquipLevel() - lvlz; i > 0; --i) {
                    final Map<Integer, Map<String, Integer>> inc = ii.getEquipIncrements(eq.getItemId());
                    if (inc != null && inc.containsKey((Object)Integer.valueOf(lvlz + i))) {
                        eq = ii.levelUpEquip(eq, (Map<String, Integer>)inc.get((Object)Integer.valueOf(lvlz + i)));
                    }
                    if (GameConstants.getStatFromWeapon(eq.getItemId()) == null) {
                        final Map<Integer, List<Integer>> ins = ii.getEquipSkills(eq.getItemId());
                        if (ins != null && ins.containsKey((Object)Integer.valueOf(lvlz + i))) {
                            for (final Integer z : (List<Integer>)ins.get((Object)Integer.valueOf(lvlz + i))) {
                                if (Math.random() < 0.1) {
                                    final ISkill skil = SkillFactory.getSkill((int)z);
                                    if (skil == null || !skil.canBeLearnedBy((int)chr.getJob()) || chr.getSkillLevel(skil) >= chr.getMasterLevel(skil)) {
                                        continue;
                                    }
                                    chr.changeSkillLevel(skil, (byte)(chr.getSkillLevel(skil) + 1), chr.getMasterLevel(skil));
                                }
                            }
                        }
                    }
                }
                changed = true;
            }
            chr.forceReAddItem_Flag(eq.copy(), MapleInventoryType.EQUIPPED);
        }
        if (changed) {
            chr.equipChanged();
            chr.getClient().sendPacket(MaplePacketCreator.showItemLevelupEffect());
            chr.getMap().broadcastMessage(chr, MaplePacketCreator.showForeignItemLevelupEffect(chr.getId()), false);
        }
        return changed;
    }
    
    public boolean checkEquipDurabilitys(final MapleCharacter chr, final int gain) {
        for (final Equip item : this.durabilityHandling) {
            item.setDurability(item.getDurability() + gain);
            if (item.getDurability() < 0) {
                item.setDurability(0);
            }
        }
        final List<Equip> all = new ArrayList<Equip>((Collection<? extends Equip>)this.durabilityHandling);
        for (final Equip eqq : all) {
            if (eqq.getDurability() == 0) {
                if (chr.getInventory(MapleInventoryType.EQUIP).isFull()) {
                    chr.getClient().sendPacket(MaplePacketCreator.getInventoryFull());
                    chr.getClient().sendPacket(MaplePacketCreator.getShowInventoryFull());
                    return false;
                }
                this.durabilityHandling.remove((Object)eqq);
                final short pos = chr.getInventory(MapleInventoryType.EQUIP).getNextFreeSlot();
                MapleInventoryManipulator.unequip(chr.getClient(), eqq.getPosition(), pos);
                chr.getClient().sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(1, (IItem)eqq, pos)));
            }
            else {
                chr.forceReAddItem(eqq.copy(), MapleInventoryType.EQUIPPED);
            }
        }
        return true;
    }
    
    private void CalcPassive_Mastery(final MapleCharacter player) {
        if (player.getInventory(MapleInventoryType.EQUIPPED).getItem((short)(-11)) == null) {
            this.passive_mastery = 0;
            return;
        }
        int skil = 0;
        switch (GameConstants.getWeaponType(player.getInventory(MapleInventoryType.EQUIPPED).getItem((short)(-11)).getItemId())) {
            case 弓: {
                skil = 3100000;
                break;
            }
            case 拳套: {
                skil = 4100000;
                break;
            }
            case 雙刀:
            case 短劍: {
                skil = ((player.getJob() >= 430 && player.getJob() <= 434) ? 4300000 : 4200000);
                break;
            }
            case 弩: {
                skil = 3200000;
                break;
            }
            case 單手斧:
            case 雙手斧: {
                skil = 1100001;
                break;
            }
            case 單手劍:
            case 雙手劍: {
                skil = (GameConstants.isKOC((int)player.getJob()) ? 11100000 : ((player.getJob() > 112) ? 1200000 : 1100000));
                break;
            }
            case 單手棍:
            case 雙手棍: {
                skil = 1200001;
                break;
            }
            case 槍: {
                skil = (GameConstants.isAran((int)player.getJob()) ? 21100000 : 1300001);
                break;
            }
            case 矛: {
                skil = 1300000;
                break;
            }
            case 指虎: {
                skil = (GameConstants.isKOC((int)player.getJob()) ? 15100001 : 5100001);
                break;
            }
            case 火槍: {
                skil = 5200000;
                break;
            }
            case 短杖: {
                skil = 32100006;
                break;
            }
            default: {
                this.passive_mastery = 0;
                return;
            }
        }
        if (player.getSkillLevel(skil) <= 0) {
            this.passive_mastery = 0;
            return;
        }
        this.passive_mastery = (byte)(player.getSkillLevel(skil) / 2 + player.getSkillLevel(skil) % 2);
    }
    
    private void CalcPassive_SharpEye(final MapleCharacter player, final int added_sharpeye_rate, final int added_sharpeye_dmg) {
        switch (player.getJob()) {
            case 410:
            case 411:
            case 412: {
                final ISkill critSkill = SkillFactory.getSkill(4100001);
                final int critlevel = player.getSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.passive_sharpeye_percent = (short)(critSkill.getEffect(critlevel).getDamage() - 100 + added_sharpeye_dmg);
                    this.passive_sharpeye_rate = (byte)(critSkill.getEffect(critlevel).getProb() + added_sharpeye_rate);
                    return;
                }
                break;
            }
            case 1410:
            case 1411:
            case 1412: {
                final ISkill critSkill = SkillFactory.getSkill(14100001);
                final int critlevel = player.getSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.passive_sharpeye_percent = (short)(critSkill.getEffect(critlevel).getDamage() - 100 + added_sharpeye_dmg);
                    this.passive_sharpeye_rate = (byte)(critSkill.getEffect(critlevel).getProb() + added_sharpeye_rate);
                    return;
                }
                break;
            }
            case 511:
            case 512: {
                final ISkill critSkill = SkillFactory.getSkill(5110000);
                final int critlevel = player.getSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.passive_sharpeye_percent = (short)(critSkill.getEffect(critlevel).getDamage() - 100 + added_sharpeye_dmg);
                    this.passive_sharpeye_rate = (byte)(critSkill.getEffect(critlevel).getProb() + added_sharpeye_rate);
                    return;
                }
                break;
            }
            case 1511:
            case 1512: {
                final ISkill critSkill = SkillFactory.getSkill(15110000);
                final int critlevel = player.getSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.passive_sharpeye_percent = (short)(critSkill.getEffect(critlevel).getDamage() - 100 + added_sharpeye_dmg);
                    this.passive_sharpeye_rate = (byte)(critSkill.getEffect(critlevel).getProb() + added_sharpeye_rate);
                    return;
                }
                break;
            }
            case 2111:
            case 2112: {
                final ISkill critSkill = SkillFactory.getSkill(21110000);
                final int critlevel = player.getSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.passive_sharpeye_percent = (short)(critSkill.getEffect(critlevel).getX() * critSkill.getEffect(critlevel).getDamage() + added_sharpeye_dmg);
                    this.passive_sharpeye_rate = (byte)(critSkill.getEffect(critlevel).getX() * critSkill.getEffect(critlevel).getY() + added_sharpeye_rate);
                    return;
                }
                break;
            }
            case 300:
            case 310:
            case 311:
            case 312:
            case 320:
            case 321:
            case 322: {
                final ISkill critSkill = SkillFactory.getSkill(3000001);
                final int critlevel = player.getSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.passive_sharpeye_percent = (short)(critSkill.getEffect(critlevel).getDamage() - 100 + added_sharpeye_dmg);
                    this.passive_sharpeye_rate = (byte)(critSkill.getEffect(critlevel).getProb() + added_sharpeye_rate);
                    return;
                }
                break;
            }
            case 1300:
            case 1310:
            case 1311:
            case 1312: {
                final ISkill critSkill = SkillFactory.getSkill(13000000);
                final int critlevel = player.getSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.passive_sharpeye_percent = (short)(critSkill.getEffect(critlevel).getDamage() - 100 + added_sharpeye_dmg);
                    this.passive_sharpeye_rate = (byte)(critSkill.getEffect(critlevel).getProb() + added_sharpeye_rate);
                    return;
                }
                break;
            }
            case 2214:
            case 2215:
            case 2216:
            case 2217:
            case 2218: {
                final ISkill critSkill = SkillFactory.getSkill(22140000);
                final int critlevel = player.getSkillLevel(critSkill);
                if (critlevel > 0) {
                    this.passive_sharpeye_percent = (short)(critSkill.getEffect(critlevel).getDamage() - 100 + added_sharpeye_dmg);
                    this.passive_sharpeye_rate = (byte)(critSkill.getEffect(critlevel).getProb() + added_sharpeye_rate);
                    return;
                }
                break;
            }
        }
        this.passive_sharpeye_percent = (short)added_sharpeye_dmg;
        this.passive_sharpeye_rate = (byte)added_sharpeye_rate;
    }
    
    private void CalcPassive_Range(final MapleCharacter chra) {
        switch (chra.getJob()) {
            case 300:
            case 310:
            case 311:
            case 312:
            case 320:
            case 321:
            case 322: {
                this.defRange = 100;
                final ISkill bx = SkillFactory.getSkill(3000002);
                final int bof = chra.getSkillLevel(bx);
                if (bof > 0) {
                    this.defRange += bx.getEffect(bof).getRange();
                    break;
                }
                break;
            }
            case 410:
            case 411:
            case 412:
            case 420:
            case 421:
            case 422: {
                this.defRange = 100;
                final ISkill bx = SkillFactory.getSkill(4000001);
                final int bof = chra.getSkillLevel(bx);
                if (bof > 0) {
                    this.defRange += bx.getEffect(bof).getRange();
                    break;
                }
                break;
            }
            case 520:
            case 521:
            case 522: {
                this.defRange = 100;
                break;
            }
            case 1300:
            case 1310:
            case 1311:
            case 1312: {
                this.defRange = 100;
                final ISkill bx = SkillFactory.getSkill(13000001);
                final int bof = chra.getSkillLevel(bx);
                if (bof > 0) {
                    this.defRange += bx.getEffect(bof).getRange();
                    break;
                }
                break;
            }
            case 1400:
            case 1410:
            case 1411:
            case 1412: {
                this.defRange = 100;
                final ISkill bx = SkillFactory.getSkill(14000001);
                final int bof = chra.getSkillLevel(bx);
                if (bof > 0) {
                    this.defRange += bx.getEffect(bof).getRange();
                    break;
                }
                break;
            }
            case 2100:
            case 2110:
            case 2111:
            case 2112: {
                this.defRange = 80;
                break;
            }
        }
    }
    
    public final short passive_sharpeye_percent() {
        return this.passive_sharpeye_percent;
    }
    
    public final byte passive_sharpeye_rate() {
        return this.passive_sharpeye_rate;
    }
    
    public final byte passive_mastery() {
        return this.passive_mastery;
    }
    
    public final float calculateMaxBaseDamage(final int matk, final int watk) {
        final MapleCharacter chra = (MapleCharacter)this.chr.get();
        if (chra == null) {
            return 0.0f;
        }
        float maxbasedamage;
        if (watk == 0) {
            maxbasedamage = 1.0f;
        }
        else {
            final IItem weapon_item = chra.getInventory(MapleInventoryType.EQUIPPED).getItem((short)(-11));
            final int job = chra.getJob();
            final MapleWeaponType weapon = (weapon_item == null) ? MapleWeaponType.沒有武器 : GameConstants.getWeaponType(weapon_item.getItemId());
            final boolean magican = (job >= 200 && job <= 232) || (job >= 1200 && job <= 1212);
            int mainstat = 0;
            int secondarystat = 0;
            switch (weapon) {
                case 矛: {
                    mainstat = (int)((double)this.localstr * 1.25);
                    secondarystat = (int)((double)this.localdex * 1.25);
                    break;
                }
                case 弓:
                case 弩: {
                    mainstat = this.localdex * 2;
                    secondarystat = this.localstr * 2;
                    break;
                }
                case 拳套:
                case 雙刀:
                case 短劍: {
                    if ((job >= 400 && job <= 434) || (job >= 1400 && job <= 1412)) {
                        mainstat = this.localluk;
                        secondarystat = this.localdex + this.localstr;
                        break;
                    }
                    mainstat = this.localstr;
                    secondarystat = this.localdex;
                    break;
                }
                case 指虎: {
                    mainstat = this.localstr;
                    secondarystat = this.localdex;
                    break;
                }
                case 火槍: {
                    mainstat = this.localdex;
                    secondarystat = this.localstr;
                    break;
                }
                case 沒有武器: {
                    if ((job >= 500 && job <= 522) || (job >= 1500 && job <= 1512)) {
                        mainstat = this.localstr;
                        secondarystat = this.localdex;
                        break;
                    }
                    mainstat = 0;
                    secondarystat = 0;
                    break;
                }
                default: {
                    if (magican) {
                        mainstat = this.localint_;
                        secondarystat = this.localluk;
                        break;
                    }
                    mainstat = this.localstr;
                    secondarystat = this.localdex;
                    break;
                }
            }
            maxbasedamage = (weapon.getMaxDamageMultiplier() * (float)mainstat + (float)secondarystat) * (float)(magican ? matk : watk) / 100.0f;
        }
        return maxbasedamage;
    }
    
    public final float getHealHP() {
        int shouldHealHP = 10;
        Skill bx = (Skill)SkillFactory.getSkill(1000000);
        int bof = ((MapleCharacter)this.chr.get()).getSkillLevel((ISkill)bx);
        if (bof > 0) {
            final MapleStatEffect eff = bx.getEffect(bof);
            shouldHealHP += eff.getHp();
        }
        bx = (Skill)SkillFactory.getSkill(1320008);
        bof = ((MapleCharacter)this.chr.get()).getSkillLevel((ISkill)bx);
        if (bof > 0) {
            final MapleStatEffect eff = bx.getEffect(bof);
            shouldHealHP += eff.getHp();
        }
        bx = (Skill)SkillFactory.getSkill(4100002);
        bof = ((MapleCharacter)this.chr.get()).getSkillLevel((ISkill)bx);
        if (bof > 0) {
            final MapleStatEffect eff = bx.getEffect(bof);
            shouldHealHP += eff.getHp();
        }
        bx = (Skill)SkillFactory.getSkill(4200001);
        bof = ((MapleCharacter)this.chr.get()).getSkillLevel((ISkill)bx);
        if (bof > 0) {
            final MapleStatEffect eff = bx.getEffect(bof);
            shouldHealHP += eff.getHp();
        }
        return (float)shouldHealHP;
    }
    
    public final float getHealMP() {
        int shouldHealMP = 3;
        Skill bx = (Skill)SkillFactory.getSkill(2000000);
        int bof = ((MapleCharacter)this.chr.get()).getSkillLevel((ISkill)bx);
        if (bof > 0) {
            shouldHealMP += bof * 5;
        }
        bx = (Skill)SkillFactory.getSkill(4100002);
        bof = ((MapleCharacter)this.chr.get()).getSkillLevel((ISkill)bx);
        if (bof > 0) {
            final MapleStatEffect eff = bx.getEffect(bof);
            shouldHealMP += eff.getMp();
        }
        bx = (Skill)SkillFactory.getSkill(4200001);
        bof = ((MapleCharacter)this.chr.get()).getSkillLevel((ISkill)bx);
        if (bof > 0) {
            final MapleStatEffect eff = bx.getEffect(bof);
            shouldHealMP += eff.getMp();
        }
        return (float)shouldHealMP;
    }
    
    public final void relocHeal() {
        final MapleCharacter chra = (MapleCharacter)this.chr.get();
        if (chra == null) {
            return;
        }
        final int playerjob = chra.getJob();
        this.shouldHealHP = (float)(10 + this.recoverHP);
        this.shouldHealMP = (float)(3 + this.mpRestore + this.recoverMP);
        if (GameConstants.isJobFamily(200, playerjob)) {
            this.shouldHealMP += (float)chra.getSkillLevel(SkillFactory.getSkill(2000000)) / 10.0f * (float)chra.getLevel();
        }
        else if (GameConstants.isJobFamily(111, playerjob)) {
            final ISkill effect = SkillFactory.getSkill(1110000);
            final int lvl = chra.getSkillLevel(effect);
            if (lvl > 0) {
                this.shouldHealMP += (float)effect.getEffect(lvl).getMp();
            }
        }
        else if (GameConstants.isJobFamily(121, playerjob)) {
            final ISkill effect = SkillFactory.getSkill(1210000);
            final int lvl = chra.getSkillLevel(effect);
            if (lvl > 0) {
                this.shouldHealMP += (float)effect.getEffect(lvl).getMp();
            }
        }
        else if (GameConstants.isJobFamily(1111, playerjob)) {
            final ISkill effect = SkillFactory.getSkill(11110000);
            final int lvl = chra.getSkillLevel(effect);
            if (lvl > 0) {
                this.shouldHealMP += (float)effect.getEffect(lvl).getMp();
            }
        }
        else if (GameConstants.isJobFamily(410, playerjob)) {
            final ISkill effect = SkillFactory.getSkill(4100002);
            final int lvl = chra.getSkillLevel(effect);
            if (lvl > 0) {
                this.shouldHealHP += (float)effect.getEffect(lvl).getHp();
                this.shouldHealMP += (float)effect.getEffect(lvl).getMp();
            }
        }
        else if (GameConstants.isJobFamily(420, playerjob)) {
            final ISkill effect = SkillFactory.getSkill(4200001);
            final int lvl = chra.getSkillLevel(effect);
            if (lvl > 0) {
                this.shouldHealHP += (float)effect.getEffect(lvl).getHp();
                this.shouldHealMP += (float)effect.getEffect(lvl).getMp();
            }
        }
        if (chra.isGM()) {
            this.shouldHealHP += 1000.0f;
            this.shouldHealMP += 1000.0f;
        }
        if (chra.getChair() != 0) {
            this.shouldHealHP += 99.0f;
            this.shouldHealMP += 99.0f;
        }
        else {
            final float recvRate = chra.getMap().getRecoveryRate();
            this.shouldHealHP *= recvRate;
            this.shouldHealMP *= recvRate;
        }
        this.shouldHealHP *= 2.0f;
        this.shouldHealMP *= 2.0f;
    }
    
    public final void connectData(final MaplePacketLittleEndianWriter mplew) {
        mplew.writeShort((int)this.str);
        mplew.writeShort((int)this.dex);
        mplew.writeShort((int)this.int_);
        mplew.writeShort((int)this.luk);
        mplew.writeShort((int)this.hp);
        mplew.writeShort((int)this.maxhp);
        mplew.writeShort((int)this.mp);
        mplew.writeShort((int)this.maxmp);
    }
    
    public final int getSkillByJob(final int skillID, final int job) {
        if (GameConstants.isKOC(job)) {
            return skillID + 10000000;
        }
        if (GameConstants.isAran(job)) {
            return skillID + 20000000;
        }
        return skillID;
    }
    
    public int getExpModH() {
        return this.expMod_H;
    }
}
