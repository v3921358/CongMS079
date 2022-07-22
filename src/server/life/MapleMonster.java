package server.life;

import handling.world.MaplePartyCharacter;
import client.inventory.IItem;
import client.inventory.Item;
import server.MapleItemInformationProvider;
import client.inventory.Equip;
import client.inventory.MapleInventoryType;
import server.Randomizer;
import java.util.Collections;
import tools.Pair;
import server.Timer.MobTimer;
import client.ISkill;
import constants.GameConstants;
import client.SkillFactory;
import server.MapleStatEffect;
import java.util.Map.Entry;
import server.maps.MapleMapObjectType;
import server.maps.MapScriptMethods;
import client.MapleClient;
import java.util.List;
import tools.MaplePacketCreator;
import java.util.ArrayList;
import server.maps.MapleMapObject;
import handling.channel.ChannelServer;
import constants.ServerConfig;
import client.MapleDisease;
import handling.world.MapleParty;
import client.MapleBuffStat;
import gui.CongMS;
import java.util.Iterator;
import tools.packet.MobPacket;
import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.LinkedList;
import client.status.MonsterStatusEffect;
import client.status.MonsterStatus;
import java.util.EnumMap;
import scripting.EventInstanceManager;
import java.util.Collection;
import client.MapleCharacter;
import java.lang.ref.WeakReference;
import server.maps.MapleMap;

public class MapleMonster extends AbstractLoadedMapleLife
{
    MapleMonsterStats stats;
    private OverrideMonsterStats ostats;
    private long hp;
    private long nextKill;
    private int mp;
    private byte venom_counter;
    private byte carnivalTeam;
    private MapleMap map;
    private WeakReference<MapleMonster> sponge;
    private int linkoid;
    private int lastNode;
    private int lastNodeController;
    private int highestDamageChar;
    private WeakReference<MapleCharacter> controller;
    private boolean fake;
    private boolean dropsDisabled;
    private boolean controllerHasAggro;
    private boolean controllerKnowsAboutAggro;
    private final Collection<AttackerEntry> attackers;
    private EventInstanceManager eventInstance;
    private MonsterListener listener;
    private byte[] reflectpack;
    private byte[] nodepack;
    private final EnumMap<MonsterStatus, MonsterStatusEffect> stati;
    private final LinkedList<MonsterStatusEffect> poisons;
    private final ReentrantReadWriteLock poisonsLock;
    private Map<Integer, Long> usedSkills;
    private int stolen;
    private ScheduledFuture<?> dropItemSchedule;
    private boolean shouldDropItem;
    private long lastAbsorbMP;
    
    public MapleMonster(final int id, final MapleMonsterStats stats) {
        super(id);
        this.ostats = null;
        this.sponge = new WeakReference<MapleMonster>(null);
        this.linkoid = 0;
        this.lastNode = -1;
        this.lastNodeController = -1;
        this.highestDamageChar = 0;
        this.controller = new WeakReference<MapleCharacter>(null);
        this.attackers = new LinkedList<AttackerEntry>();
        this.listener = null;
        this.reflectpack = null;
        this.nodepack = null;
        this.stati = new EnumMap<MonsterStatus, MonsterStatusEffect>(MonsterStatus.class);
        this.poisons = new LinkedList<MonsterStatusEffect>();
        this.poisonsLock = new ReentrantReadWriteLock();
        this.stolen = -1;
        this.shouldDropItem = false;
        this.initWithStats(stats);
    }
    
    public MapleMonster(final MapleMonster monster) {
        super((AbstractLoadedMapleLife)monster);
        this.ostats = null;
        this.sponge = new WeakReference<MapleMonster>(null);
        this.linkoid = 0;
        this.lastNode = -1;
        this.lastNodeController = -1;
        this.highestDamageChar = 0;
        this.controller = new WeakReference<MapleCharacter>(null);
        this.attackers = new LinkedList<AttackerEntry>();
        this.listener = null;
        this.reflectpack = null;
        this.nodepack = null;
        this.stati = new EnumMap<MonsterStatus, MonsterStatusEffect>(MonsterStatus.class);
        this.poisons = new LinkedList<MonsterStatusEffect>();
        this.poisonsLock = new ReentrantReadWriteLock();
        this.stolen = -1;
        this.shouldDropItem = false;
        this.initWithStats(monster.stats);
    }
    
    public final MapleMonsterStats getStats() {
        return this.stats;
    }
    
    private void initWithStats(final MapleMonsterStats stats) {
        this.setStance(5);
        this.stats = stats;
        this.hp = stats.getHp();
        this.mp = stats.getMp();
        this.venom_counter = 0;
        this.carnivalTeam = -1;
        this.fake = false;
        this.dropsDisabled = false;
        if (stats.getNoSkills() > 0) {
            this.usedSkills = new HashMap<Integer, Long>();
        }
    }
    
    public final void disableDrops() {
        this.dropsDisabled = true;
    }
    
    public final boolean dropsDisabled() {
        return this.dropsDisabled;
    }
    
    public final void setMap(final MapleMap map) {
        this.map = map;
        this.startDropItemSchedule();
    }
    
    public final MapleMap getMap() {
        return this.map;
    }
    
    public final void setSponge(final MapleMonster mob) {
        this.sponge = new WeakReference<MapleMonster>(mob);
    }
    
    public final MapleMonster getSponge() {
        return (MapleMonster)this.sponge.get();
    }
    
    public final void setHp(final long hp) {
        this.hp = hp;
    }
    
    public final long getHp() {
        return this.hp;
    }
    
    public final long getMobMaxHp() {
        if (this.ostats != null) {
            return this.ostats.getHp();
        }
        return this.stats.getHp();
    }
    
    public final void setMp(int mp) {
        if (mp < 0) {
            mp = 0;
        }
        this.mp = mp;
    }
    
    public final int getMp() {
        return this.mp;
    }
    
    public final int getMobMaxMp() {
        if (this.ostats != null) {
            return this.ostats.getMp();
        }
        return this.stats.getMp();
    }
    
    public final int getMobExp() {
        if (this.ostats != null) {
            return this.ostats.getExp();
        }
        return this.stats.getExp();
    }
    
    public final int getMobLevel() {
        if (this.ostats != null) {
            return this.ostats.getlevel();
        }
        return this.stats.getLevel();
    }
    
    public final void setOverrideStats(final OverrideMonsterStats ostats) {
        this.ostats = ostats;
        this.hp = ostats.getHp();
        this.mp = ostats.getMp();
    }
    
    public final byte getVenomMulti() {
        return this.venom_counter;
    }
    
    public final void setVenomMulti(final byte venom_counter) {
        this.venom_counter = venom_counter;
    }
    
    public final void absorbMP(final int amount) {
        if (!this.canAbsorbMP()) {
            return;
        }
        if (this.getMp() >= amount) {
            this.setMp(this.getMp() - amount);
        }
        else {
            this.setMp(0);
        }
        this.lastAbsorbMP = System.currentTimeMillis();
    }
    
    public final long getLastAbsorbMP() {
        return this.lastAbsorbMP;
    }
    
    public final boolean canAbsorbMP() {
        return System.currentTimeMillis() - this.lastAbsorbMP > 10000L;
    }
    
    public final void damage(final MapleCharacter from, final long damage, final boolean updateAttackTime) {
        this.damage(from, damage, updateAttackTime, 0);
    }
    
    public final void damage(final MapleCharacter from, final long damage, final boolean updateAttackTime, final int lastSkill) {
        if (from == null || damage <= 0L || !this.isAlive()) {
            return;
        }
        AttackerEntry attacker = (from.getParty() != null) ? new PartyAttackerEntry(from.getParty().getId(), this.map.getChannel()) : new SingleAttackerEntry(from, this.map.getChannel());
        boolean replaced = false;
        for (final AttackerEntry aentry : this.attackers) {
            if (aentry.equals((Object)attacker)) {
                attacker = aentry;
                replaced = true;
                break;
            }
        }
        if (!replaced) {
            this.attackers.add(attacker);
        }
        final long rightDamage = Math.max(0L, Math.min(damage, this.hp));
        attacker.addDamage(from, rightDamage, updateAttackTime);
        if (this.getStats().getSelfD() != -1) {
            final long newHp = this.getHp() - rightDamage;
            this.setHp(newHp);
            if (this.getHp() > 0L) {
                if (this.getHp() < (long)this.getStats().getSelfDHp()) {
                    this.getMap().killMonster(this, from, false, false, this.getStats().getSelfD(), lastSkill);
                }
                else {
                    for (final AttackerEntry mattacker : this.attackers) {
                        for (final AttackingMapleCharacter cattacker : mattacker.getAttackers()) {
                            if (cattacker != null && cattacker.getAttacker().getMap() == from.getMap() && cattacker.getLastAttackTime() >= System.currentTimeMillis() - 4000L) {
                                cattacker.getAttacker().getClient().sendPacket(MobPacket.showMonsterHP(this.getObjectId(), (int)Math.ceil((double)this.hp * 100.0 / (double)this.getMobMaxHp())));
                            }
                        }
                    }
                }
            }
            else {
                this.getMap().killMonster(this, from, true, false, (byte)1, lastSkill);
            }
        }
        else {
            if (this.getSponge() != null && this.getSponge().getHp() > 0L) {
                final long newHp = this.getSponge().getHp() - rightDamage;
                this.getSponge().setHp(newHp);
                if (this.getSponge().getHp() <= 0L) {
                    this.getMap().killMonster((MapleMonster)this.sponge.get(), from, true, false, (byte)1, lastSkill);
                }
                else {
                    this.getMap().broadcastMessage(MobPacket.showBossHP((MapleMonster)this.sponge.get()));
                }
            }
            if (this.getHp() > 0L) {
                final long newHp = this.getHp() - rightDamage;
                this.setHp(newHp);
                if (this.eventInstance != null) {
                    this.eventInstance.monsterDamaged(from, this, (int)rightDamage);
                }
                else {
                    final EventInstanceManager em = from.getEventInstance();
                    if (em != null) {
                        em.monsterDamaged(from, this, (int)rightDamage);
                    }
                }
                if (this.getSponge() == null && this.hp > 0L) {
                    switch (this.getStats().getHPDisplayType()) {
                        case 0: {
                            this.getMap().broadcastMessage(MobPacket.showBossHP(this), this.getPosition());
                            break;
                        }
                        case 1: {
                            this.getMap().broadcastMessage(MobPacket.damageFriendlyMob(this, damage, true));
                            break;
                        }
                        case -1:
                        case 2: {
                            this.getMap().broadcastMessage(MobPacket.showMonsterHP(this.getObjectId(), (int)Math.ceil((double)this.hp * 100.0 / (double)this.getMobMaxHp())));
                            from.mulungEnergyModify(true);
                            break;
                        }
                        case 3: {
                            for (final AttackerEntry mattacker : this.attackers) {
                                for (final AttackingMapleCharacter cattacker : mattacker.getAttackers()) {
                                    if (cattacker != null && cattacker.getAttacker().getMap() == from.getMap() && cattacker.getLastAttackTime() >= System.currentTimeMillis() - 4000L) {
                                        cattacker.getAttacker().getClient().sendPacket(MobPacket.showMonsterHP(this.getObjectId(), (int)Math.ceil((double)this.hp * 100.0 / (double)this.getMobMaxHp())));
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
                if (this.getHp() <= 0L) {
                    if (this.getStats().getHPDisplayType() == -1) {
                        this.getMap().broadcastMessage(MobPacket.showMonsterHP(this.getObjectId(), (int)Math.ceil((double)this.hp * 100.0 / (double)this.getMobMaxHp())));
                    }
                    this.getMap().killMonster(this, from, true, false, (byte)1, lastSkill);
                }
            }
        }
        this.startDropItemSchedule();
    }
    
    public final void heal(final int hp, final int mp, final boolean broadcast) {
        long totalHP = this.getHp() + (long)hp;
        int totalMP = this.getMp() + mp;
        totalHP = ((totalHP > this.getMobMaxHp()) ? this.getMobMaxHp() : totalHP);
        totalMP = ((totalMP > this.getMobMaxMp()) ? this.getMobMaxMp() : totalMP);
        this.setHp(totalHP);
        this.setMp(totalMP);
        if (broadcast) {
            this.getMap().broadcastMessage(MobPacket.healMonster(this.getObjectId(), hp));
        }
        if (this.getSponge() != null) {
            totalHP = this.getSponge().getHp() + (long)hp;
            totalMP = this.getSponge().getMp() + mp;
            totalHP = ((totalHP > this.getSponge().getMobMaxHp()) ? this.getSponge().getMobMaxHp() : totalHP);
            totalMP = ((totalMP > this.getSponge().getMobMaxMp()) ? this.getSponge().getMobMaxMp() : totalMP);
            this.getSponge().setHp(totalHP);
            this.getSponge().setMp(totalMP);
        }
    }
    
    private void giveExpToCharacter(final MapleCharacter attacker, int exp, final boolean highestDamage, final int numExpSharers, final byte pty, final byte classBounsExpPercent, final byte Premium_Bonus_EXP_PERCENT, final int lastskillID) {
        if (highestDamage) {
            if (this.eventInstance != null) {
                this.eventInstance.monsterKilled(attacker, this);
            }
            else {
                final EventInstanceManager em = attacker.getEventInstance();
                if (em != null) {
                    em.monsterKilled(attacker, this);
                }
            }
            this.highestDamageChar = attacker.getId();
        }
        final double 怪物坐标X = this.getPosition().getX();
        final double 怪物坐标Y = this.getPosition().getY();
        final double X坐标误差 = attacker.getPosition().getX() - 怪物坐标X;
        final double Y坐标误差 = attacker.getPosition().getY() - 怪物坐标Y;
        final int 记录地图 = attacker.getMapId();
        if (attacker.打怪地图 == 0) {
            attacker.打怪地图 = 记录地图;
        }
        else if (记录地图 != attacker.打怪地图) {
            attacker.打怪地图 = 0;
            attacker.打怪数量 = 0;
            attacker.X坐标误差 = 0.0;
            attacker.Y坐标误差 = 0.0;
        }
        if (attacker.打怪地图 > 0) {
            if (X坐标误差 > attacker.X坐标误差) {
                attacker.X坐标误差 = X坐标误差;
            }
            if (Y坐标误差 > attacker.Y坐标误差) {
                attacker.Y坐标误差 = Y坐标误差;
            }
        }
        if (exp > 0) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"越级打怪开关")) == 0) {
                final int 怪物 = this.getMobLevel();
                final int 玩家 = attacker.getLevel();
                if (玩家 < 怪物) {
                    final int 相差 = 怪物 - 玩家;
                    if (相差 >= 10 && 相差 <= 20) {
                        exp = (int)((double)exp * 0.8);
                    }
                    else if (相差 >= 21 && 相差 <= 30) {
                        exp = (int)((double)exp * 0.7);
                    }
                    else if (相差 >= 31) {
                        exp = (int)((double)exp * 0.6);
                    }
                }
            }
            if (exp > 0) {
                final MonsterStatusEffect mse = (MonsterStatusEffect)this.stati.get((Object)MonsterStatus.SHOWDOWN);
                if (mse != null) {
                    exp += (int)((double)exp * ((double)(int)mse.getX() / 100.0));
                }
                final Integer holySymbol = attacker.getBuffedValue(MapleBuffStat.HOLY_SYMBOL);
                if (holySymbol != null) {
                    if (numExpSharers == 1) {
                        exp = (int)((double)exp * (1.0 + (double)holySymbol / 500.0));
                    }
                    else {
                        exp = (int)((double)exp * (1.0 + (double)holySymbol / 100.0));
                    }
                }
                final int 职业 = attacker.getJob();
                final int 职业2 = MapleParty.幸运职业;
                if (职业 == 职业2 || 职业 - 职业2 == 1 || 职业2 - 职业 == -1) {
                    exp = (int)((double)exp + (double)exp * 0.5);
                }
                if (attacker.hasDisease(MapleDisease.CURSE)) {
                    if (attacker.getEquippedFuMoMap().get((Object)Integer.valueOf(32)) != null) {
                        exp *= 5;
                    }
                    else {
                        exp /= 2;
                    }
                }
                final double lastexp = (attacker.getStat().realExpBuff - 100.0 <= 0.0) ? 100.0 : (attacker.getStat().realExpBuff - 100.0);
                exp *= attacker.getEXPMod() * (int)(attacker.getStat().expBuff / 100.0);
                if ("情怀冒险岛".equals((Object)ServerConfig.SERVERNAME) || "枫之谷".equals((Object)ServerConfig.SERVERNAME)) {
                    if (attacker.getLevel() < 10) {
                        exp = 1 * exp * ChannelServer.getInstance(this.map.getChannel()).getExpRate();
                    }
                    else if (attacker.getLevel() >= 10 && attacker.getLevel() < 30) {
                        exp = 5 * exp * ChannelServer.getInstance(this.map.getChannel()).getExpRate();
                    }
                    else if (attacker.getLevel() >= 30 && attacker.getLevel() < 60) {
                        exp = 4 * exp * ChannelServer.getInstance(this.map.getChannel()).getExpRate();
                    }
                    else if (attacker.getLevel() >= 60 && attacker.getLevel() < 90) {
                        exp = 3 * exp * ChannelServer.getInstance(this.map.getChannel()).getExpRate();
                    }
                    else if (attacker.getLevel() >= 90 && attacker.getLevel() < 120) {
                        exp = 2 * exp * ChannelServer.getInstance(this.map.getChannel()).getExpRate();
                    }
                    else {
                        exp *= ChannelServer.getInstance(this.map.getChannel()).getExpRate();
                    }
                }
                else {
                    exp *= ChannelServer.getInstance(this.map.getChannel()).getExpRate();
                }
                int classBonusExp = 0;
                if (classBounsExpPercent > 0) {
                    classBonusExp = (int)((double)exp / 100.0 * (double)classBounsExpPercent);
                }
                int Premium_Bonus_EXP = 0;
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"网吧经验加成")) != 0) {
                    final int 网吧经验加成 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"网吧经验加成"));
                    Premium_Bonus_EXP += (int)((double)exp / 100.0 * (double)网吧经验加成);
                }
                int Equipment_Bonus_EXP = (int)((double)exp / 100.0 * (double)attacker.getStat().equipmentBonusExp);
                if (attacker.getStat().精灵吊坠) {
                    Equipment_Bonus_EXP += (int)((double)exp / 100.0 * (double)attacker.getFairyExp());
                }
                int wedding_EXP = 0;
                if (attacker.getMarriageId() > 0 && attacker.getMap().getCharacterById_InMap(attacker.getMarriageId()) != null && (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"结婚经验加成")) != 0) {
                    final int 结婚经验加成 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"结婚经验加成"));
                    wedding_EXP = (int)((double)wedding_EXP + (double)exp / 100.0 * (double)结婚经验加成);
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"人气经验加成")) > 0 && attacker.getFame() > 0) {
                    attacker.人气经验加成();
                }
                int premiumBonusExp = 0;
                if (Premium_Bonus_EXP_PERCENT > 0) {
                    premiumBonusExp = (int)((double)exp / 100.0 * (double)Premium_Bonus_EXP_PERCENT);
                }
                int equpBonusExp = (int)((double)exp / 100.0 * (double)attacker.getStat().equipmentBonusExp);
                if (attacker.getStat().equippedFairy) {
                    equpBonusExp += (int)((double)exp / 100.0 * (double)attacker.getFairyExp());
                }
                if (pty > 1) {
                    exp = (int)((double)exp * 1.2);
                    attacker.gainExpMonster(exp, true, true, pty, classBonusExp, equpBonusExp, premiumBonusExp);
                }
                else {
                    attacker.gainExpMonster(exp, highestDamage, true, pty, classBonusExp, equpBonusExp, premiumBonusExp);
                }
            }
            attacker.mobKilled(this.getId(), lastskillID);
        }
    }
    
    public final int killBy(final MapleCharacter killer, final int lastSkill) {
        final int totalBaseExp = this.getMobExp();
        AttackerEntry highest = null;
        long highdamage = 0L;
        for (final AttackerEntry attackEntry : this.attackers) {
            if (attackEntry.getDamage() > highdamage) {
                highest = attackEntry;
                highdamage = attackEntry.getDamage();
            }
        }
        for (final AttackerEntry attackEntry : this.attackers) {
            final int baseExp = (int)Math.ceil((double)totalBaseExp * ((double)attackEntry.getDamage() / (double)this.getMobMaxHp()));
            attackEntry.killedMob(this.getMap(), baseExp, attackEntry == highest, lastSkill);
        }
        final MapleCharacter controll = this.getController();
        if (controll != null) {
            controll.getClient().sendPacket(MobPacket.stopControllingMonster(this.getObjectId()));
            controll.stopControllingMonster(this);
        }
        switch (this.getId()) {
            default: {
                this.spawnRevives(this.getMap());
                if (this.eventInstance != null) {
                    this.eventInstance.unregisterMonster(this);
                    this.eventInstance = null;
                }
                if (killer != null && killer.getPyramidSubway() != null) {
                    killer.getPyramidSubway().onKill(killer);
                }
                final MapleMonster oldSponge = this.getSponge();
                this.sponge = new WeakReference<MapleMonster>(null);
                if (oldSponge != null && oldSponge.isAlive()) {
                    boolean set = true;
                    for (final MapleMapObject mon : this.map.getAllMonstersThreadsafe()) {
                        final MapleMonster mons = (MapleMonster)mon;
                        if (mons.getObjectId() != oldSponge.getObjectId() && mons.getObjectId() != this.getObjectId() && (mons.getSponge() == oldSponge || mons.getLinkOid() == oldSponge.getObjectId())) {
                            set = false;
                            break;
                        }
                    }
                    if (set) {
                        this.map.killMonster(oldSponge, killer, true, false, (byte)1);
                    }
                }
                this.nodepack = null;
                this.reflectpack = null;
                this.stati.clear();
                this.cancelDropItem();
                if (this.listener != null) {
                    this.listener.monsterKilled();
                }
                final int v1 = this.highestDamageChar;
                this.highestDamageChar = 0;
                return v1;
            }
        }
    }
    
    public final void spawnRevives(final MapleMap map) {
        final List<Integer> toSpawn = this.stats.getRevives();
        if (toSpawn == null) {
            return;
        }
        MapleMonster spongy = null;
        switch (this.getId()) {
            case 8810118:
            case 8810119:
            case 8810120:
            case 8810121: {
                final Iterator<Integer> iterator = toSpawn.iterator();
                while (iterator.hasNext()) {
                    final int i = (int)Integer.valueOf(iterator.next());
                    final MapleMonster mob = MapleLifeFactory.getMonster(i);
                    mob.setPosition(this.getPosition());
                    if (this.eventInstance != null) {
                        this.eventInstance.registerMonster(mob);
                    }
                    if (this.dropsDisabled()) {
                        mob.disableDrops();
                    }
                    switch (mob.getId()) {
                        case 8810119:
                        case 8810120:
                        case 8810121:
                        case 8810122: {
                            spongy = mob;
                            continue;
                        }
                    }
                }
                if (spongy != null) {
                    map.spawnRevives(spongy, this.getObjectId());
                    for (final MapleMapObject mon : map.getAllMonstersThreadsafe()) {
                        final MapleMonster mons = (MapleMonster)mon;
                        if (mons.getObjectId() != spongy.getObjectId() && (mons.getSponge() == this || mons.getLinkOid() == this.getObjectId())) {
                            mons.setSponge(spongy);
                            mons.setLinkOid(spongy.getObjectId());
                        }
                    }
                    break;
                }
                break;
            }
            case 8810026:
            case 8810130:
            case 8820008:
            case 8820009:
            case 8820010:
            case 8820011:
            case 8820012:
            case 8820013: {
                final List<MapleMonster> mobs = new ArrayList<MapleMonster>();
                final Iterator<Integer> iterator3 = toSpawn.iterator();
                while (iterator3.hasNext()) {
                    final int j = (int)Integer.valueOf(iterator3.next());
                    final MapleMonster mob2 = MapleLifeFactory.getMonster(j);
                    mob2.setPosition(this.getPosition());
                    if (this.eventInstance != null) {
                        this.eventInstance.registerMonster(mob2);
                    }
                    if (this.dropsDisabled()) {
                        mob2.disableDrops();
                    }
                    switch (mob2.getId()) {
                        case 8810018:
                        case 8810118:
                        case 8820009:
                        case 8820010:
                        case 8820011:
                        case 8820012:
                        case 8820013:
                        case 8820014: {
                            spongy = mob2;
                            continue;
                        }
                        default: {
                            mobs.add(mob2);
                            continue;
                        }
                    }
                }
                if (spongy != null) {
                    map.spawnRevives(spongy, this.getObjectId());
                    for (final MapleMonster k : mobs) {
                        k.setSponge(spongy);
                        map.spawnRevives(k, this.getObjectId());
                    }
                    break;
                }
                break;
            }
            default: {
                final Iterator<Integer> iterator5 = toSpawn.iterator();
                while (iterator5.hasNext()) {
                    final int i = (int)Integer.valueOf(iterator5.next());
                    final MapleMonster mob = MapleLifeFactory.getMonster(i);
                    if (this.eventInstance != null) {
                        this.eventInstance.registerMonster(mob);
                    }
                    mob.setPosition(this.getPosition());
                    if (this.dropsDisabled()) {
                        mob.disableDrops();
                    }
                    map.spawnRevives(mob, this.getObjectId());
                    if (mob.getId() == 9300216) {
                        map.broadcastMessage(MaplePacketCreator.environmentChange("Dojang/clear", 4));
                        map.broadcastMessage(MaplePacketCreator.environmentChange("dojang/end/clear", 3));
                    }
                }
                break;
            }
        }
    }
    
    public final void setCarnivalTeam(final byte team) {
        this.carnivalTeam = team;
    }
    
    public final byte getCarnivalTeam() {
        return this.carnivalTeam;
    }
    
    public final MapleCharacter getController() {
        return (MapleCharacter)this.controller.get();
    }
    
    public final void setController(final MapleCharacter controller) {
        this.controller = new WeakReference<MapleCharacter>(controller);
    }
    
    public final void switchController(final MapleCharacter newController, final boolean immediateAggro) {
        final MapleCharacter controllers = this.getController();
        if (controllers == newController) {
            return;
        }
        if (controllers != null) {
            controllers.stopControllingMonster(this);
            controllers.getClient().sendPacket(MobPacket.stopControllingMonster(this.getObjectId()));
            this.sendStatus(controllers.getClient());
        }
        newController.controlMonster(this, immediateAggro);
        this.setController(newController);
        if (immediateAggro) {
            this.setControllerHasAggro(true);
        }
        this.setControllerKnowsAboutAggro(false);
        if (this.getId() == 9300275 && this.map.getId() >= 921120100 && this.map.getId() < 921120500) {
            if (this.lastNodeController != -1 && this.lastNodeController != newController.getId()) {
                this.resetShammos(newController.getClient());
            }
            else {
                this.setLastNodeController(newController.getId());
            }
        }
    }
    
    public final void resetShammos(final MapleClient c) {
        this.map.killAllMonsters(true);
        this.map.broadcastMessage(MaplePacketCreator.serverNotice(5, "A player has moved too far from Shammos. Shammos is going back to the start."));
        for (final MapleCharacter chr : this.map.getCharactersThreadsafe()) {
            chr.changeMap(chr.getMap(), chr.getMap().getPortal(0));
        }
        MapScriptMethods.startScript_FirstUser(c, "shammos_Fenter");
    }
    
    public final void setListener(final MonsterListener listener) {
        this.listener = listener;
    }
    
    public final boolean isControllerHasAggro() {
        return this.controllerHasAggro;
    }
    
    public final void setControllerHasAggro(final boolean controllerHasAggro) {
        this.controllerHasAggro = controllerHasAggro;
    }
    
    public final boolean isControllerKnowsAboutAggro() {
        return this.controllerKnowsAboutAggro;
    }
    
    public final void setControllerKnowsAboutAggro(final boolean controllerKnowsAboutAggro) {
        this.controllerKnowsAboutAggro = controllerKnowsAboutAggro;
    }
    
    public final void sendStatus(final MapleClient client) {
        if (this.reflectpack != null) {
            client.getSession().writeAndFlush((Object)this.reflectpack);
        }
        if (this.poisons.size() > 0) {
            this.poisonsLock.readLock().lock();
            try {
                client.getSession().writeAndFlush((Object)MobPacket.applyMonsterStatus(this, (List<MonsterStatusEffect>)this.poisons));
            }
            finally {
                this.poisonsLock.readLock().unlock();
            }
        }
    }
    
    @Override
    public final void sendSpawnData(final MapleClient client) {
        if (!this.isAlive()) {
            return;
        }
        client.sendPacket(MobPacket.spawnMonster(this, (this.lastNode >= 0) ? -2 : -1, this.fake ? 252 : ((this.lastNode >= 0) ? 12 : 0), 0));
        this.sendStatus(client);
        if (this.lastNode >= 0) {
            client.sendPacket(MaplePacketCreator.getNodeProperties(this, this.map));
            if (this.getId() == 9300275 && this.map.getId() >= 921120100 && this.map.getId() < 921120500) {
                if (this.lastNodeController != -1) {
                    this.resetShammos(client);
                }
                else {
                    this.setLastNodeController(client.getPlayer().getId());
                }
            }
        }
    }
    
    @Override
    public final void sendDestroyData(final MapleClient client) {
        if (this.lastNode == -1) {
            client.sendPacket(MobPacket.killMonster(this.getObjectId(), 0));
        }
        if (this.getId() == 9300275 && this.map.getId() >= 921120100 && this.map.getId() < 921120500) {
            this.resetShammos(client);
        }
    }
    
    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.stats.getName());
        sb.append("(");
        sb.append(this.getId());
        sb.append(") (等級 ");
        sb.append((int)this.stats.getLevel());
        sb.append(") 在 (X");
        sb.append(this.getPosition().x);
        sb.append("/ Y");
        sb.append(this.getPosition().y);
        sb.append(") 座標 ");
        sb.append(this.getHp());
        sb.append("/ ");
        sb.append(this.getMobMaxHp());
        sb.append("血量, ");
        sb.append(this.getMp());
        sb.append("/ ");
        sb.append(this.getMobMaxMp());
        sb.append(" 魔力, MobOID: ");
        sb.append(this.getObjectId());
        sb.append(" || 仇恨目標 : ");
        final MapleCharacter chr = (MapleCharacter)this.controller.get();
        sb.append((chr != null) ? chr.getName() : "無");
        return sb.toString();
    }
    
    @Override
    public final MapleMapObjectType getType() {
        return MapleMapObjectType.MONSTER;
    }
    
    public final void setEventInstance(final EventInstanceManager eventInstance) {
        this.eventInstance = eventInstance;
    }
    
    public final EventInstanceManager getEventInstance() {
        return this.eventInstance;
    }
    
    public final int getStatusSourceID(final MonsterStatus status) {
        final MonsterStatusEffect effect = (MonsterStatusEffect)this.stati.get((Object)status);
        if (effect != null) {
            return effect.getSkill();
        }
        return -1;
    }
    
    public final ElementalEffectiveness getEffectiveness(final Element e) {
        if (this.stati.size() > 0 && this.stati.get((Object)MonsterStatus.DOOM) != null) {
            return ElementalEffectiveness.NORMAL;
        }
        return this.stats.getEffectiveness(e);
    }
    
    public void applyMonsterBuff(final Map<MonsterStatus, Integer> effect, final int x, final int skillId, final long duration, final MobSkill skill, final List<Integer> reflection) {
        final MapleCharacter con = this.getController();
        for (final Entry<MonsterStatus, Integer> z : effect.entrySet()) {
            if (this.stati.containsKey((Object)z.getKey())) {
                this.cancelStatus((MonsterStatus)z.getKey());
            }
            final MonsterStatusEffect effectz = new MonsterStatusEffect((MonsterStatus)z.getKey(), Integer.valueOf(z.getValue()), 0, skill, true, reflection.size() > 0);
            effectz.setCancelTask(duration);
            this.stati.put((MonsterStatus)z.getKey(), effectz);
        }
        if (reflection.size() > 0) {
            final List<MonsterStatusEffect> mse = new ArrayList<MonsterStatusEffect>();
            for (final Entry<MonsterStatus, Integer> z2 : effect.entrySet()) {
                mse.add(new MonsterStatusEffect((MonsterStatus)z2.getKey(), Integer.valueOf(z2.getValue()), 0, skill, true, reflection.size() > 0));
            }
            this.reflectpack = MobPacket.applyMonsterStatus(this, mse);
            if (con != null) {
                this.map.broadcastMessage(con, this.reflectpack, this.getTruePosition());
                con.getClient().getSession().writeAndFlush((Object)this.reflectpack);
            }
            else {
                this.map.broadcastMessage(this.reflectpack, this.getTruePosition());
            }
        }
        else {
            for (final Entry<MonsterStatus, Integer> z : effect.entrySet()) {
                final MonsterStatusEffect effectz = new MonsterStatusEffect((MonsterStatus)z.getKey(), Integer.valueOf(z.getValue()), 0, skill, true, reflection.size() > 0);
                if (con != null) {
                    this.map.broadcastMessage(con, MobPacket.applyMonsterStatus(this, effectz), this.getTruePosition());
                    con.getClient().getSession().writeAndFlush((Object)MobPacket.applyMonsterStatus(this, effectz));
                }
                else {
                    this.map.broadcastMessage(MobPacket.applyMonsterStatus(this, effectz), this.getTruePosition());
                }
            }
        }
    }
    
    public final void applyStatus(final MapleCharacter from, final MonsterStatusEffect status, final boolean poison, long duration, final boolean checkboss, final MapleStatEffect eff) {
        if (!this.isAlive()) {
            return;
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"怪物状态开关")) <= 0 && from.hasGmLevel(5)) {
            String 状态 = "";
            if (status.getStatus() != null) {
                final String name = status.getStati().name();
                int n = -1;
                switch (name.hashCode()) {
                    case 2556090: {
                        if (name.equals((Object)"STUN")) {
                            n = 0;
                            break;
                        }
                        break;
                    }
                    case -1929420024: {
                        if (name.equals((Object)"POISON")) {
                            n = 1;
                            break;
                        }
                        break;
                    }
                    case 79104039: {
                        if (name.equals((Object)"SPEED")) {
                            n = 2;
                            break;
                        }
                        break;
                    }
                    case 2104233: {
                        if (name.equals((Object)"DOOM")) {
                            n = 3;
                            break;
                        }
                        break;
                    }
                    case 2541053: {
                        if (name.equals((Object)"SEAL")) {
                            n = 4;
                            break;
                        }
                        break;
                    }
                    case -534226027: {
                        if (name.equals((Object)"SHADOW_WEB")) {
                            n = 5;
                            break;
                        }
                        break;
                    }
                    case 444279071: {
                        if (name.equals((Object)"SHOWDOWN")) {
                            n = 6;
                            break;
                        }
                        break;
                    }
                    case 2361464: {
                        if (name.equals((Object)"MDEF")) {
                            n = 7;
                            break;
                        }
                        break;
                    }
                    case 2659374: {
                        if (name.equals((Object)"WDEF")) {
                            n = 8;
                            break;
                        }
                        break;
                    }
                }
                switch (n) {
                    case 0: {
                        状态 = "怪物无法移动,[昏迷]，[冰冻]";
                        break;
                    }
                    case 1: {
                        状态 = "怪物持续掉血,[中毒]，[灼烧]";
                        break;
                    }
                    case 2: {
                        状态 = "怪物减少移动速度,[缓速]，[束缚]";
                        break;
                    }
                    case 3: {
                        状态 = "怪物改变外观,[巫毒]，[变身]";
                        break;
                    }
                    case 4: {
                        状态 = "怪物无法使用技能,[封印]，[沉默]";
                        break;
                    }
                    case 5: {
                        状态 = "怪物定身，无法移动,[束缚]，[昏迷]，[定身]";
                        break;
                    }
                    case 6: {
                        状态 = "怪物被激怒,[挑衅]，[诱导]";
                        break;
                    }
                    case 7: {
                        状态 = "怪物防御发生变化,[魔防]";
                        break;
                    }
                    case 8: {
                        状态 = "怪物防御发生变化,[物防]";
                        break;
                    }
                    default: {
                        from.dropMessage(5, "怪物状态: " + status.getStati().name() + "");
                        break;
                    }
                }
            }
            from.dropMessage(5, "怪物状态: " + 状态 + "");
        }
        final ISkill skilz = SkillFactory.getSkill(status.getSkill());
        if (skilz != null) {
            switch (this.stats.getEffectiveness(skilz.getElement())) {
                case IMMUNE:
                case STRONG: {
                    return;
                }
                case NORMAL:
                case WEAK: {
                    break;
                }
                default: {
                    return;
                }
            }
        }
        final int statusSkill = status.getSkill();
        Label_0757: {
            switch (statusSkill) {
                case 2111006: {
                    switch (this.stats.getEffectiveness(Element.POISON)) {
                        case IMMUNE:
                        case STRONG: {
                            return;
                        }
                        default: {
                            break Label_0757;
                        }
                    }
                }
                case 2211006: {
                    switch (this.stats.getEffectiveness(Element.ICE)) {
                        case IMMUNE:
                        case STRONG: {
                            return;
                        }
                        default: {
                            break Label_0757;
                        }
                    }
                }
                case 4120005:
                case 4220005:
                case 14110004: {
                    switch (this.stats.getEffectiveness(Element.POISON)) {
                        case WEAK: {
                            return;
                        }
                        default: {
                            break Label_0757;
                        }
                    }
                }
            }
        }
        if (duration >= 2000000000L) {
            duration = 5000L;
        }
        final MonsterStatus stat = status.getStatus();
        if (this.getId() == 5100002 && stat == MonsterStatus.POISON) {
            return;
        }
        if (this.stats.isNoDoom() && stat == MonsterStatus.DOOM) {
            return;
        }
        if (stat == MonsterStatus.FREEZE) {
            switch (this.getId()) {
                case 9400253:
                case 9400254: {
                    return;
                }
            }
        }
        if (this.stats.isBoss()) {
            if (stat == MonsterStatus.POISON) {
                return;
            }
            if (stat == MonsterStatus.STUN) {
                return;
            }
            if (stat != MonsterStatus.SPEED && stat != MonsterStatus.NINJA_AMBUSH && stat != MonsterStatus.WATK) {
                return;
            }
            if (this.getId() == 8850011 && stat == MonsterStatus.MAGIC_CRASH) {
                return;
            }
            if (stat == MonsterStatus.FREEZE) {
                return;
            }
        }
        if ((this.stats.isFriendly() || this.isFake()) && (stat == MonsterStatus.STUN || stat == MonsterStatus.SPEED || stat == MonsterStatus.POISON || stat == MonsterStatus.VENOMOUS_WEAPON)) {
            return;
        }
        if ((stat == MonsterStatus.VENOMOUS_WEAPON || stat == MonsterStatus.POISON) && eff == null) {
            return;
        }
        if (this.stati.containsKey((Object)stat)) {
            return;
        }
        if (stat == MonsterStatus.POISON || stat == MonsterStatus.VENOMOUS_WEAPON) {
            this.poisonsLock.readLock().lock();
            try {
                for (final MonsterStatusEffect mse : this.poisons) {
                    if (mse != null && (mse.getSkill() == eff.getSourceId() || mse.getSkill() == GameConstants.getLinkedAttackSkill(eff.getSourceId()) || GameConstants.getLinkedAttackSkill(mse.getSkill()) == eff.getSourceId())) {
                        return;
                    }
                }
            }
            finally {
                this.poisonsLock.readLock().unlock();
            }
        }
        if (poison && this.getHp() > 1L && eff != null) {
            duration = Math.max(duration, (long)(eff.getDOTTime() * 1000));
        }
        final long aniTime;
        duration = (aniTime = duration + (long)(from.getStat().dotTime * 1000));
        status.setCancelTask(aniTime);
        if (poison && this.getHp() > 1L) {
            if (status.getchr() != null) {
                return;
            }
            status.setDotTime(duration);
            final int dam = (int)Math.min(32767L, (long)((double)this.getMobMaxHp() / (70.0 - (double)from.getSkillLevel(status.getSkill())) + 0.999));
            if (from.hasGmLevel(5)) {
                from.dropMessage(6, "[持續伤害] 開始處理效果 - 技能ID：" + eff.getSourceId());
            }
            status.setValue(status.getStatus(), Integer.valueOf(dam));
            status.setPoisonDamage(dam, from);
            final int poisonDamage = (int)(aniTime / 1000L * (long)(int)status.getX());
            if (from.hasGmLevel(5)) {
                from.dropMessage(6, "[持續伤害] 持續伤害： " + ((this.getHp() > (long)poisonDamage) ? ((long)poisonDamage) : (this.getHp() - 1L)) + " 持續时间：" + aniTime + " 持續掉血：" + (Object)status.getX());
            }
        }
        else if (statusSkill == 5211004 && this.getHp() > 1L) {
            if (status.getchr() != null) {
                return;
            }
            status.setDotTime(duration);
            final int dam = (int)Math.min(32767L, (long)((double)this.getMobMaxHp() / (70.0 - (double)from.getSkillLevel(status.getSkill())) + 0.999));
            if (from.isAdmin()) {
                from.dropMessage(6, "[持續伤害] 開始處理效果 - 技能ID：" + eff.getSourceId());
            }
            status.setValue(status.getStatus(), Integer.valueOf(dam));
            status.setPoisonDamage(dam, from);
            final int poisonDamage = (int)(aniTime / 1000L * (long)(int)status.getX());
            if (from.isAdmin()) {
                from.dropMessage(6, "[持續伤害] 持續伤害： " + ((this.getHp() > (long)poisonDamage) ? ((long)poisonDamage) : (this.getHp() - 1L)) + " 持續时间：" + aniTime + " 持續掉血：" + (Object)status.getX());
            }
        }
        else if (statusSkill == 4111003 || statusSkill == 14111001) {
            status.setValue(status.getStatus(), Integer.valueOf((int)((double)this.getMobMaxHp() / 50.0 + 0.999)));
            status.setPoisonDamage((int)status.getX(), from);
        }
        else if (statusSkill == 4341003) {
            status.setPoisonDamage((int)((double)((float)eff.getDamage() * from.getStat().getCurrentMaxBaseDamage()) / 100.0), from);
        }
        else if (statusSkill == 4121004 || statusSkill == 4221004) {
            status.setValue(status.getStatus(), Integer.valueOf(Math.min(32767, (int)((double)((float)eff.getDamage() * from.getStat().getCurrentMaxBaseDamage()) / 100.0))));
            int dam = (int)(aniTime / 1000L * (long)(int)status.getX() / 2L);
            status.setPoisonDamage(dam, from);
            if (dam > 0) {
                if ((long)dam >= this.hp) {
                    dam = (int)(this.hp - 1L);
                }
                this.damage(from, (long)dam, false);
            }
        }
        final MapleCharacter con = this.getController();
        if (stat == MonsterStatus.POISON || stat == MonsterStatus.VENOMOUS_WEAPON) {
            this.poisonsLock.writeLock().lock();
            try {
                this.poisons.add(status);
                status.scheduledoPoison(this);
            }
            finally {
                this.poisonsLock.writeLock().unlock();
            }
        }
        else {
            this.stati.put(stat, status);
        }
        if (con != null) {
            this.map.broadcastMessage(con, MobPacket.applyMonsterStatus(this, status), this.getTruePosition());
            con.getClient().sendPacket(MobPacket.applyMonsterStatus(this, status));
        }
        else {
            this.map.broadcastMessage(MobPacket.applyMonsterStatus(this, status), this.getTruePosition());
        }
        if (from.getDebugMessage()) {
            from.dropMessage(6, "開始 => 給予怪物状态: 持續时间[" + aniTime + "] 状态效果[" + status.getStatus().name() + "] 開始时间[" + System.currentTimeMillis() + "]");
        }
    }
    
    public final void dispelSkill(final MobSkill skillId) {
        final List<MonsterStatus> toCancel = new ArrayList<MonsterStatus>();
        for (final Entry<MonsterStatus, MonsterStatusEffect> effects : this.stati.entrySet()) {
            final MonsterStatusEffect mse = (MonsterStatusEffect)effects.getValue();
            if (mse != null && mse.getMobSkill() != null && mse.getMobSkill().getSkillId() == skillId.getSkillId()) {
                toCancel.add(effects.getKey());
            }
        }
        for (final MonsterStatus stat : toCancel) {
            this.cancelStatus(stat);
        }
    }
    
    public final void cancelStatus(final MonsterStatus stat) {
        if (stat == MonsterStatus.EMPTY || stat == MonsterStatus.SUMMON) {
            return;
        }
        final MonsterStatusEffect mse = (MonsterStatusEffect)this.stati.get((Object)stat);
        if (mse == null || !this.isAlive()) {
            return;
        }
        if (mse.isReflect()) {
            this.reflectpack = null;
        }
        mse.cancelPoisonSchedule(this);
        final MapleCharacter con = this.getController();
        if (con != null) {
            this.map.broadcastMessage(con, MobPacket.cancelMonsterStatus(this, mse), this.getTruePosition());
            con.getClient().sendPacket(MobPacket.cancelMonsterStatus(this, mse));
        }
        else {
            this.map.broadcastMessage(MobPacket.cancelMonsterStatus(this, mse), this.getTruePosition());
        }
        this.stati.remove((Object)stat);
    }
    
    public final void cancelSingleStatus(final MonsterStatusEffect stat) {
        if (stat == null || stat.getStatus() == MonsterStatus.EMPTY || stat.getStatus() == MonsterStatus.SUMMON || !this.isAlive()) {
            return;
        }
        if (stat.getStatus() != MonsterStatus.POISON && stat.getStatus() != MonsterStatus.VENOMOUS_WEAPON) {
            this.cancelStatus(stat.getStatus());
            return;
        }
        this.poisonsLock.writeLock().lock();
        try {
            if (!this.poisons.contains((Object)stat)) {
                return;
            }
            this.poisons.remove((Object)stat);
            if (stat.isReflect()) {
                this.reflectpack = null;
            }
            stat.cancelPoisonSchedule(this);
            final MapleCharacter con = this.getController();
            if (con != null) {
                this.map.broadcastMessage(con, MobPacket.cancelMonsterStatus(this, stat), this.getTruePosition());
                con.getClient().getSession().writeAndFlush((Object)MobPacket.cancelMonsterStatus(this, stat));
            }
            else {
                this.map.broadcastMessage(MobPacket.cancelMonsterStatus(this, stat), this.getTruePosition());
            }
        }
        finally {
            this.poisonsLock.writeLock().unlock();
        }
    }
    
    public final void doPoison(final MonsterStatusEffect status, final WeakReference<MapleCharacter> weakChr) {
        if ((status.getStatus() == MonsterStatus.VENOMOUS_WEAPON || status.getStatus() == MonsterStatus.POISON || status.getStatus() == MonsterStatus.NEUTRALISE) && this.poisons.size() <= 0) {
            return;
        }
        if (status.getStatus() != MonsterStatus.VENOMOUS_WEAPON && status.getStatus() != MonsterStatus.POISON && status.getStatus() == MonsterStatus.NEUTRALISE && !this.stati.containsKey((Object)status.getStatus())) {
            return;
        }
        if (weakChr == null) {
            return;
        }
        int damage = status.getPoisonDamage();
        final boolean shadowWeb = status.getSkill() == 4111003 || status.getSkill() == 14111001;
        final MapleCharacter chr = (MapleCharacter)weakChr.get();
        boolean cancel = damage <= 0 || chr == null || chr.getMapId() != this.map.getId();
        if ((long)damage >= this.hp) {
            damage = (int)this.hp - 1;
            cancel = (!shadowWeb || cancel);
        }
        if (!cancel) {
            this.damage(chr, (long)damage, false);
            if (shadowWeb) {
                this.map.broadcastMessage(MobPacket.damageMonster(this.getObjectId(), (long)damage), this.getTruePosition());
            }
        }
    }
    
    public final void setTempEffectiveness(final Element e, final long milli) {
        this.stats.setEffectiveness(e, ElementalEffectiveness.WEAK);
        MobTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                stats.removeEffectiveness(e);
            }
        }, milli);
    }
    
    public final boolean isBuffed(final MonsterStatus status) {
        return this.stati.containsKey((Object)status);
    }
    
    public final MonsterStatusEffect getBuff(final MonsterStatus status) {
        return (MonsterStatusEffect)this.stati.get((Object)status);
    }
    
    public final int getStatiSize() {
        return this.stati.size() + ((this.poisons.size() > 0) ? 1 : 0);
    }
    
    public final ArrayList<MonsterStatusEffect> getAllBuffs() {
        final ArrayList<MonsterStatusEffect> ret = new ArrayList<MonsterStatusEffect>();
        for (final MonsterStatusEffect e : this.stati.values()) {
            ret.add(e);
        }
        this.poisonsLock.readLock().lock();
        try {
            for (final MonsterStatusEffect e : this.poisons) {
                ret.add(e);
            }
        }
        finally {
            this.poisonsLock.readLock().unlock();
        }
        return ret;
    }
    
    public final void setFake(final boolean fake) {
        this.fake = fake;
    }
    
    public final boolean isFake() {
        return this.fake;
    }
    
    public final boolean isAlive() {
        return this.hp > 0L;
    }
    
    public boolean isAttackedBy(final MapleCharacter chr) {
        for (final AttackerEntry aentry : this.attackers) {
            if (aentry.contains(chr)) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean isFirstAttack() {
        return this.stats.isFirstAttack();
    }
    
    public final List<Pair<Integer, Integer>> getSkills() {
        return this.stats.getSkills();
    }
    
    public final boolean hasSkill(final int skillId, final int level) {
        return this.stats.hasSkill(skillId, level);
    }
    
    public final long getLastSkillUsed(final int skillId) {
        if (this.usedSkills.containsKey((Object)Integer.valueOf(skillId))) {
            return (long)Long.valueOf(this.usedSkills.get((Object)Integer.valueOf(skillId)));
        }
        return 0L;
    }
    
    public final void setLastSkillUsed(final int skillId, final long now, final long cooltime) {
        switch (skillId) {
            case 140: {
                this.usedSkills.put(Integer.valueOf(skillId), Long.valueOf(now + cooltime * 2L));
                this.usedSkills.put(Integer.valueOf(141), Long.valueOf(now));
                break;
            }
            case 141: {
                this.usedSkills.put(Integer.valueOf(skillId), Long.valueOf(now + cooltime * 2L));
                this.usedSkills.put(Integer.valueOf(140), Long.valueOf(now + cooltime));
                break;
            }
            default: {
                this.usedSkills.put(Integer.valueOf(skillId), Long.valueOf(now + cooltime));
                break;
            }
        }
    }
    
    public final byte getNoSkills() {
        return this.stats.getNoSkills();
    }
    
    public final int getBuffToGive() {
        return this.stats.getBuffToGive();
    }
    
    public int getLevel() {
        return this.stats.getLevel();
    }
    
    public int getLinkOid() {
        return this.linkoid;
    }
    
    public void setLinkOid(final int lo) {
        this.linkoid = lo;
    }
    
    public final Map<MonsterStatus, MonsterStatusEffect> getStati() {
        return this.stati;
    }
    
    public void addEmpty() {
        for (final MonsterStatus stat : MonsterStatus.values()) {
            if (stat.isDefault()) {
                this.stati.put(stat, new MonsterStatusEffect(stat, Integer.valueOf(0), 0, null, false));
            }
        }
    }
    
    public final int getStolen() {
        return this.stolen;
    }
    
    public final void setStolen(final int s) {
        this.stolen = s;
    }
    
    public final void handleSteal(final MapleCharacter chr) {
        double showdown = 100.0;
        final MonsterStatusEffect mse = this.getBuff(MonsterStatus.SHOWDOWN);
        if (mse != null) {
            showdown += (double)(int)mse.getX();
        }
        final ISkill steal = SkillFactory.getSkill(4201004);
        final int level = chr.getSkillLevel(steal);
        final int chServerrate = ChannelServer.getInstance(chr.getClient().getChannel()).getDropRate();
        if (level > 0 && !this.getStats().isBoss() && this.stolen == -1 && steal.getEffect(level).makeChanceResult()) {
            final MapleMonsterInformationProvider mi = MapleMonsterInformationProvider.getInstance();
            final List<MonsterDropEntry> de = mi.retrieveDrop(this.getId());
            if (de == null) {
                this.stolen = 0;
                return;
            }
            final List<MonsterDropEntry> dropEntry = new ArrayList<MonsterDropEntry>((Collection<? extends MonsterDropEntry>)de);
            Collections.shuffle(dropEntry);
            for (final MonsterDropEntry d : dropEntry) {
                if (d.itemId > 0 && d.questid == 0 && d.itemId / 10000 != 238 && Randomizer.nextInt(999999) < (int)((double)(10 * d.chance * chServerrate * chr.getDropMod()) * chr.getDropm() * ((double)chr.getVipExpRate() / 100.0 + 1.0) * (chr.getStat().dropBuff / 100.0) * (showdown / 100.0))) {
                    IItem idrop;
                    if (GameConstants.getInventoryType(d.itemId) == MapleInventoryType.EQUIP) {
                        final Equip eq = (Equip)MapleItemInformationProvider.getInstance().getEquipById(d.itemId);
                        idrop = MapleItemInformationProvider.getInstance().randomizeStats(eq);
                    }
                    else {
                        idrop = new Item(d.itemId, (short)0, (short)((d.Maximum != 1) ? (Randomizer.nextInt(d.Maximum - d.Minimum) + d.Minimum) : 1), (byte)0);
                    }
                    this.stolen = d.itemId;
                    this.map.spawnMobDrop(idrop, this.map.calcDropPos(this.getPosition(), this.getTruePosition()), this, chr, (byte)0, (short)0);
                    break;
                }
            }
        }
        else {
            this.stolen = 0;
        }
    }
    
    public final void setLastNode(final int lastNode) {
        this.lastNode = lastNode;
    }
    
    public final int getLastNode() {
        return this.lastNode;
    }
    
    public final void setLastNodeController(final int lastNode) {
        this.lastNodeController = lastNode;
    }
    
    public final int getLastNodeController() {
        return this.lastNodeController;
    }
    
    public final void cancelDropItem() {
        if (this.dropItemSchedule != null) {
            this.dropItemSchedule.cancel(false);
            this.dropItemSchedule = null;
        }
    }
    
    public final void startDropItemSchedule() {
        this.cancelDropItem();
        if (this.stats.getDropItemPeriod() <= 0 || !this.isAlive()) {
            return;
        }
        int itemId = 0;
        switch (this.getId()) {
            case 9300061: {
                itemId = 4001101;
                break;
            }
            case 9300102: {
                itemId = 4031507;
                break;
            }
            default: {
                return;
            }
        }
        final  int itemId2 = itemId;
        this.shouldDropItem = false;
        this.dropItemSchedule = MobTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                if (MapleMonster.this.isAlive() && map != null) {
                    if (shouldDropItem) {
                        map.spawnAutoDrop(itemId2, MapleMonster.this.getPosition());
                    }
                    else {
                        shouldDropItem = true;
                    }
                }
            }
        }, (long)(this.stats.getDropItemPeriod() * 1000));
    }
    
    public byte[] getNodePacket() {
        return this.nodepack;
    }
    
    public void setNodePacket(final byte[] np) {
        this.nodepack = np;
    }
    
    public final void killed() {
        if (this.listener != null) {
            this.listener.monsterKilled();
        }
        this.listener = null;
    }
    
    private final class PoisonTask implements Runnable
    {
        private final int poisonDamage;
        private final MapleCharacter chr;
        private final MonsterStatusEffect status;
        private final Runnable cancelTask;
        private final boolean shadowWeb;
        private final MapleMap map;
        
        private PoisonTask(final int poisonDamage, final MapleCharacter chr, final MonsterStatusEffect status, final Runnable cancelTask, final boolean shadowWeb) {
            this.poisonDamage = poisonDamage;
            this.chr = chr;
            this.status = status;
            this.cancelTask = cancelTask;
            this.shadowWeb = shadowWeb;
            this.map = chr.getMap();
        }
        
        @Override
        public void run() {
            long damage = (long)this.poisonDamage;
            if (damage >= hp) {
                damage = hp - 1L;
                if (!this.shadowWeb) {
                    this.cancelTask.run();
                    this.status.cancelTask();
                }
            }
            if (hp > 1L && damage > 0L) {
                MapleMonster.this.damage(this.chr, damage, false);
                if (this.shadowWeb) {
                    this.map.broadcastMessage(MobPacket.damageMonster(MapleMonster.this.getObjectId(), damage), MapleMonster.this.getPosition());
                }
            }
        }
    }
    
    private static class AttackingMapleCharacter
    {
        private final WeakReference<MapleCharacter> attacker;
        private long lastAttackTime;
        
        public AttackingMapleCharacter(final MapleCharacter attacker, final long lastAttackTime) {
            this.attacker = new WeakReference<MapleCharacter>(attacker);
            this.lastAttackTime = lastAttackTime;
        }
        
        public final long getLastAttackTime() {
            return this.lastAttackTime;
        }
        
        public final void setLastAttackTime(final long lastAttackTime) {
            this.lastAttackTime = lastAttackTime;
        }
        
        public final MapleCharacter getAttacker() {
            return (MapleCharacter)this.attacker.get();
        }
    }
    
    private final class SingleAttackerEntry implements AttackerEntry
    {
        private long damage;
        private final int chrid;
        private long lastAttackTime;
        private final int channel;
        
        public SingleAttackerEntry(final MapleCharacter from, final int cserv) {
            this.damage = 0L;
            this.chrid = from.getId();
            this.channel = cserv;
        }
        
        @Override
        public void addDamage(final MapleCharacter from, final long damage, final boolean updateAttackTime) {
            if (this.chrid == from.getId()) {
                this.damage += damage;
                if (updateAttackTime) {
                    this.lastAttackTime = System.currentTimeMillis();
                }
            }
        }
        
        @Override
        public final List<AttackingMapleCharacter> getAttackers() {
            final MapleCharacter chr = map.getCharacterById(this.chrid);
            if (chr != null) {
                return Collections.singletonList(new AttackingMapleCharacter(chr, this.lastAttackTime));
            }
            return Collections.emptyList();
        }
        
        @Override
        public boolean contains(final MapleCharacter chr) {
            return this.chrid == chr.getId();
        }
        
        @Override
        public long getDamage() {
            return this.damage;
        }
        
        @Override
        public void killedMob(final MapleMap map, final int baseExp, final boolean mostDamage, final int lastSkill) {
            final MapleCharacter chr = map.getCharacterById(this.chrid);
            if (chr != null && chr.isAlive()) {
                MapleMonster.this.giveExpToCharacter(chr, baseExp, mostDamage, 1, (byte)0, (byte)0, (byte)0, lastSkill);
            }
        }
        
        @Override
        public int hashCode() {
            return this.chrid;
        }
        
        @Override
        public final boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            final SingleAttackerEntry other = (SingleAttackerEntry)obj;
            return this.chrid == other.chrid;
        }
    }
    
    private static final class ExpMap
    {
        public final int exp;
        public final byte ptysize;
        public final byte Class_Bonus_EXP;
        public final byte Premium_Bonus_EXP;
        
        public ExpMap(final int exp, final byte ptysize, final byte Class_Bonus_EXP, final byte Premium_Bonus_EXP) {
            this.exp = exp;
            this.ptysize = ptysize;
            this.Class_Bonus_EXP = Class_Bonus_EXP;
            this.Premium_Bonus_EXP = Premium_Bonus_EXP;
        }
    }
    
    private static final class OnePartyAttacker
    {
        public MapleParty lastKnownParty;
        public long damage;
        public long lastAttackTime;
        
        public OnePartyAttacker(final MapleParty lastKnownParty, final long damage) {
            this.lastKnownParty = lastKnownParty;
            this.damage = damage;
            this.lastAttackTime = System.currentTimeMillis();
        }
    }
    
    private class PartyAttackerEntry implements AttackerEntry
    {
        private long totDamage;
        private final Map<Integer, OnePartyAttacker> attackers;
        private final int partyid;
        private final int channel;
        
        public PartyAttackerEntry(final int partyid, final int cserv) {
            this.attackers = new HashMap<Integer, OnePartyAttacker>(6);
            this.partyid = partyid;
            this.channel = cserv;
        }
        
        @Override
        public List<AttackingMapleCharacter> getAttackers() {
            final List<AttackingMapleCharacter> ret = new ArrayList<AttackingMapleCharacter>(this.attackers.size());
            for (final Entry<Integer, OnePartyAttacker> entry : this.attackers.entrySet()) {
                final MapleCharacter chr = map.getCharacterById((int)Integer.valueOf(entry.getKey()));
                if (chr != null) {
                    ret.add(new AttackingMapleCharacter(chr, ((OnePartyAttacker)entry.getValue()).lastAttackTime));
                }
            }
            return ret;
        }
        
        private Map<MapleCharacter, OnePartyAttacker> resolveAttackers() {
            final Map<MapleCharacter, OnePartyAttacker> ret = new HashMap<MapleCharacter, OnePartyAttacker>(this.attackers.size());
            for (final Entry<Integer, OnePartyAttacker> aentry : this.attackers.entrySet()) {
                final MapleCharacter chr = map.getCharacterById((int)Integer.valueOf(aentry.getKey()));
                if (chr != null) {
                    ret.put(chr, aentry.getValue());
                }
            }
            return ret;
        }
        
        @Override
        public final boolean contains(final MapleCharacter chr) {
            return this.attackers.containsKey((Object)Integer.valueOf(chr.getId()));
        }
        
        @Override
        public final long getDamage() {
            return this.totDamage;
        }
        
        @Override
        public void addDamage(final MapleCharacter from, final long damage, final boolean updateAttackTime) {
            final OnePartyAttacker oldPartyAttacker = (OnePartyAttacker)this.attackers.get((Object)Integer.valueOf(from.getId()));
            if (oldPartyAttacker != null) {
                final OnePartyAttacker onePartyAttacker2 = oldPartyAttacker;
                onePartyAttacker2.damage += damage;
                oldPartyAttacker.lastKnownParty = from.getParty();
                if (updateAttackTime) {
                    oldPartyAttacker.lastAttackTime = System.currentTimeMillis();
                }
            }
            else {
                final OnePartyAttacker onePartyAttacker = new OnePartyAttacker(from.getParty(), damage);
                this.attackers.put(Integer.valueOf(from.getId()), onePartyAttacker);
                if (!updateAttackTime) {
                    onePartyAttacker.lastAttackTime = 0L;
                }
            }
            this.totDamage += damage;
        }
        
        @Override
        public final void killedMob(final MapleMap map, final int baseExp, final boolean mostDamage, final int lastSkill) {
            MapleCharacter highest = null;
            long highestDamage = 0L;
            final Map<MapleCharacter, ExpMap> expMap = new HashMap<MapleCharacter, ExpMap>(6);
            byte added_partyinc = 0;
            for (final Entry<MapleCharacter, OnePartyAttacker> attacker : this.resolveAttackers().entrySet()) {
                final MapleParty party = ((OnePartyAttacker)attacker.getValue()).lastKnownParty;
                double averagePartyLevel = 0.0;
                byte Class_Bonus_EXP = 0;
                byte Premium_Bonus_EXP = 0;
                final List<MapleCharacter> expApplicable = new ArrayList<MapleCharacter>();
                for (final MaplePartyCharacter partychar : party.getMembers()) {
                    if (((MapleCharacter)attacker.getKey()).getLevel() - partychar.getLevel() <= 5 || stats.getLevel() - partychar.getLevel() <= 5) {
                        final MapleCharacter pchr = map.getCharacterById(partychar.getId());
                        if (pchr == null || !pchr.isAlive() || pchr.getMap() != map) {
                            continue;
                        }
                        expApplicable.add(pchr);
                        averagePartyLevel += (double)pchr.getLevel();
                        if (Class_Bonus_EXP == 0) {}
                        if (pchr.getStat().equippedWelcomeBackRing && Premium_Bonus_EXP == 0) {
                            Premium_Bonus_EXP = 80;
                        }
                        if (!pchr.getStat().hasPartyBonus || added_partyinc >= 4) {
                            continue;
                        }
                        ++added_partyinc;
                    }
                }
                if (expApplicable.size() > 1) {
                    averagePartyLevel /= (double)expApplicable.size();
                }
                else {
                    Class_Bonus_EXP = 0;
                }
                final long iDamage = ((OnePartyAttacker)attacker.getValue()).damage;
                if (iDamage > highestDamage) {
                    highest = (MapleCharacter)attacker.getKey();
                    highestDamage = iDamage;
                }
                final double innerBaseExp = (double)baseExp * ((double)iDamage / (double)this.totDamage);
                final double expFraction = innerBaseExp / (double)expApplicable.size();
                for (final MapleCharacter expReceiver : expApplicable) {
                    final int iexp = (int)Math.round(expFraction);
                    expMap.put(expReceiver, new ExpMap(iexp, (byte)(expApplicable.size() + added_partyinc), Class_Bonus_EXP, Premium_Bonus_EXP));
                }
            }
            for (final Entry<MapleCharacter, ExpMap> expReceiver2 : expMap.entrySet()) {
                final ExpMap expmap = (ExpMap)expReceiver2.getValue();
                MapleMonster.this.giveExpToCharacter((MapleCharacter)expReceiver2.getKey(), expmap.exp, mostDamage && expReceiver2.getKey() == highest, expMap.size(), expmap.ptysize, expmap.Class_Bonus_EXP, expmap.Premium_Bonus_EXP, lastSkill);
            }
        }
        
        @Override
        public final int hashCode() {
            final int prime = 31;
            int result = 1;
            result = 31 * result + this.partyid;
            return result;
        }
        
        @Override
        public final boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            final PartyAttackerEntry other = (PartyAttackerEntry)obj;
            return this.partyid == other.partyid;
        }
    }
    
    private interface AttackerEntry
    {
        List<AttackingMapleCharacter> getAttackers();
        
        void addDamage(final MapleCharacter p0, final long p1, final boolean p2);
        
        long getDamage();
        
        boolean contains(final MapleCharacter p0);
        
        void killedMob(final MapleMap p0, final int p1, final boolean p2, final int p3);
    }
}
