package server.life;

import java.awt.Rectangle;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import constants.GameConstants;
import server.maps.MapleMist;
import client.MapleDisease;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import tools.MaplePacketCreator;
import gui.CongMS;
import java.util.LinkedList;
import java.util.EnumMap;
import client.status.MonsterStatus;
import client.MapleCharacter;
import java.util.ArrayList;
import java.awt.Point;
import java.util.List;

public class MobSkill
{
    private final int skillId;
    private final int skillLevel;
    private int mpCon;
    private int spawnEffect;
    private int hp;
    private int x;
    private int y;
    private long duration;
    private long cooltime;
    private float prop;
    private short limit;
    private List<Integer> toSummon;
    private Point lt;
    private Point rb;
    private boolean summonOnce;
    
    public MobSkill(final int skillId, final int level) {
        this.toSummon = new ArrayList<Integer>();
        this.skillId = skillId;
        this.skillLevel = level;
    }
    
    public void setMpCon(final int mpCon) {
        this.mpCon = mpCon;
    }
    
    public void addSummons(final List<Integer> toSummon) {
        this.toSummon = toSummon;
    }
    
    public void setSpawnEffect(final int spawnEffect) {
        this.spawnEffect = spawnEffect;
    }
    
    public void setHp(final int hp) {
        this.hp = hp;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public void setDuration(final long duration) {
        this.duration = duration;
    }
    
    public void setCoolTime(final long cooltime) {
        this.cooltime = cooltime;
    }
    
    public void setProp(final float prop) {
        this.prop = prop;
    }
    
    public void setLtRb(final Point lt, final Point rb) {
        this.lt = lt;
        this.rb = rb;
    }
    
    public void setLimit(final short limit) {
        this.limit = limit;
    }
    
    public boolean checkCurrentBuff(final MapleCharacter player, final MapleMonster monster) {
        boolean stop = false;
        switch (this.skillId) {
            case 100:
            case 110:
            case 150: {
                stop = monster.isBuffed(MonsterStatus.WEAPON_ATTACK_UP);
                break;
            }
            case 101:
            case 111:
            case 151: {
                stop = monster.isBuffed(MonsterStatus.WEAPON_DEFENSE_UP);
                break;
            }
            case 102:
            case 112:
            case 152: {
                stop = monster.isBuffed(MonsterStatus.MAGIC_ATTACK_UP);
                break;
            }
            case 103:
            case 113:
            case 153: {
                stop = monster.isBuffed(MonsterStatus.MAGIC_DEFENSE_UP);
                break;
            }
            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145: {
                stop = (monster.isBuffed(MonsterStatus.DAMAGE_IMMUNITY) || monster.isBuffed(MonsterStatus.MAGIC_IMMUNITY) || monster.isBuffed(MonsterStatus.WEAPON_IMMUNITY));
                break;
            }
            case 200: {
                stop = (player.getMap().getNumMonsters() >= this.limit);
                break;
            }
        }
        return stop;
    }
    
    public void applyEffect(final MapleCharacter player, final MapleMonster monster, final boolean skill) {
        MapleDisease disease = null;
        final Map<MonsterStatus, Integer> stats = new EnumMap<MonsterStatus, Integer>(MonsterStatus.class);
        final List<Integer> reflection = new LinkedList<Integer>();
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"怪物状态开关")) <= 0) {
            switch (this.skillId) {
                case 102:
                case 112:
                case 152: {
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [防御增幅]"));
                    break;
                }
                case 131: {
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [召唤毒雾]"));
                    break;
                }
                case 128: {
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [诱导]"));
                    break;
                }
                case 103:
                case 113:
                case 153: {
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [魔法防御增幅]"));
                    break;
                }
                case 110:
                case 150: {
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [物理攻击增幅]"));
                    break;
                }
                case 101:
                case 111:
                case 151: {
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [魔法攻击增幅]"));
                    break;
                }
                case 120: {
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [封印]"));
                    break;
                }
                case 121: {
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [黑暗]"));
                    break;
                }
                case 122: {
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [弱化]"));
                    break;
                }
                case 124: {
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [诅咒]"));
                    break;
                }
                case 114: {
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [治愈]"));
                    break;
                }
                case 140: {
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [物理无效]"));
                    break;
                }
                case 141: {
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [魔法无效]"));
                    break;
                }
                case 200: {
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [召唤小弟]"));
                    break;
                }
                default: {
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 " + this.skillId));
                    break;
                }
            }
        }
        switch (this.skillId) {
            case 100:
            case 110:
            case 150: {
                stats.put(MonsterStatus.WEAPON_ATTACK_UP, Integer.valueOf(this.x));
                break;
            }
            case 101:
            case 111:
            case 151: {
                stats.put(MonsterStatus.MAGIC_ATTACK_UP, Integer.valueOf(this.x));
                break;
            }
            case 102:
            case 112:
            case 152: {
                stats.put(MonsterStatus.WEAPON_DEFENSE_UP, Integer.valueOf(this.x));
                break;
            }
            case 103:
            case 113:
            case 153: {
                stats.put(MonsterStatus.MAGIC_DEFENSE_UP, Integer.valueOf(this.x));
                break;
            }
            case 154: {
                stats.put(MonsterStatus.ACC, Integer.valueOf(this.x));
                break;
            }
            case 155: {
                stats.put(MonsterStatus.AVOID, Integer.valueOf(this.x));
                break;
            }
            case 156: {
                stats.put(MonsterStatus.SPEED, Integer.valueOf(this.x));
                break;
            }
            case 157: {
                stats.put(MonsterStatus.SEAL, Integer.valueOf(this.x));
                break;
            }
            case 114: {
                if (this.lt != null && this.rb != null && skill && monster != null) {
                    final List<MapleMapObject> objects = this.getObjectsInRange(monster, MapleMapObjectType.MONSTER);
                    final int healHP = this.getHP();
                    for (final MapleMapObject mons : objects) {
                        ((MapleMonster)mons).heal(healHP, 0, true);
                    }
                    break;
                }
                if (monster != null) {
                    monster.heal(this.getHP(), 0, true);
                    break;
                }
                break;
            }
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 128:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137: {
                disease = MapleDisease.getByMobSkill(this.skillId);
                break;
            }
            case 127: {
                if (this.lt != null && this.rb != null && skill && monster != null && player != null) {
                    for (final MapleCharacter character : this.getPlayersInRange(monster, player)) {
                        character.dispel();
                    }
                    break;
                }
                if (player != null) {
                    player.dispel();
                    break;
                }
                break;
            }
            case 129: {
                if (monster == null) {
                    break;
                }
                if (monster.getEventInstance() != null && monster.getEventInstance().getName().contains((CharSequence)"BossQuest")) {
                    break;
                }
                final BanishInfo info = monster.getStats().getBanishInfo();
                if (info != null) {
                    if (this.lt != null && this.rb != null && skill && player != null) {
                        for (final MapleCharacter chr : this.getPlayersInRange(monster, player)) {
                            chr.changeMapBanish(info.getMap(), info.getPortal(), info.getMsg());
                        }
                    }
                    else if (player != null) {
                        player.changeMapBanish(info.getMap(), info.getPortal(), info.getMsg());
                    }
                }
                break;
            }
            case 131: {
                if (monster != null) {
                    monster.getMap().spawnMist(new MapleMist(this.calculateBoundingBox(monster.getPosition(), true), monster, this), this.x * 10, false);
                    break;
                }
                break;
            }
            case 140: {
                stats.put(MonsterStatus.WEAPON_IMMUNITY, Integer.valueOf(this.x));
                break;
            }
            case 141: {
                stats.put(MonsterStatus.MAGIC_IMMUNITY, Integer.valueOf(this.x));
                break;
            }
            case 142: {
                stats.put(MonsterStatus.DAMAGE_IMMUNITY, Integer.valueOf(this.x));
                break;
            }
            case 143: {
                stats.put(MonsterStatus.WEAPON_DAMAGE_REFLECT, Integer.valueOf(this.x));
                stats.put(MonsterStatus.WEAPON_IMMUNITY, Integer.valueOf(this.x));
                reflection.add(Integer.valueOf(this.x));
                if (monster == null) {
                    break;
                }
                monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("[系統提示] 注意 " + monster.getStats().getName() + " 开启了物理反射状态。"));
                break;
            }
            case 144: {
                stats.put(MonsterStatus.MAGIC_DAMAGE_REFLECT, Integer.valueOf(this.x));
                stats.put(MonsterStatus.MAGIC_IMMUNITY, Integer.valueOf(this.x));
                reflection.add(Integer.valueOf(this.x));
                if (monster == null) {
                    break;
                }
                monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("[系統提示] 注意 " + monster.getStats().getName() + " 开启了魔法反射状态。"));
                break;
            }
            case 145: {
                stats.put(MonsterStatus.WEAPON_DAMAGE_REFLECT, Integer.valueOf(this.x));
                stats.put(MonsterStatus.WEAPON_IMMUNITY, Integer.valueOf(this.x));
                stats.put(MonsterStatus.MAGIC_DAMAGE_REFLECT, Integer.valueOf(this.x));
                stats.put(MonsterStatus.MAGIC_IMMUNITY, Integer.valueOf(this.x));
                reflection.add(Integer.valueOf(this.x));
                if (monster == null) {
                    break;
                }
                monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("[系統提示] 注意 " + monster.getStats().getName() + " 开启了物理和魔法反射状态。"));
                break;
            }
            case 200: {
                if (monster == null) {
                    return;
                }
                for (final Integer mobId : this.getSummons()) {
                    MapleMonster toSpawn;
                    try {
                        toSpawn = MapleLifeFactory.getMonster(GameConstants.getCustomSpawnID(monster.getId(), (int)mobId));
                    }
                    catch (RuntimeException e) {
                        continue;
                    }
                    if (toSpawn == null) {
                        continue;
                    }
                    toSpawn.setPosition(monster.getPosition());
                    int ypos = (int)monster.getPosition().getY();
                    int xpos = (int)monster.getPosition().getX();
                    switch ((int)mobId) {
                        case 8500003: {
                            toSpawn.setFh((int)Math.ceil(Math.random() * 19.0));
                            ypos = -590;
                            break;
                        }
                        case 8500004: {
                            xpos = (int)(monster.getPosition().getX() + Math.ceil(Math.random() * 1000.0) - 500.0);
                            ypos = (int)monster.getPosition().getY();
                            break;
                        }
                        case 8510100: {
                            if (Math.ceil(Math.random() * 5.0) == 1.0) {
                                ypos = 78;
                                xpos = (int)(0.0 + Math.ceil(Math.random() * 5.0)) + ((Math.ceil(Math.random() * 2.0) == 1.0) ? 180 : 0);
                                break;
                            }
                            xpos = (int)(monster.getPosition().getX() + Math.ceil(Math.random() * 1000.0) - 500.0);
                            break;
                        }
                        case 8820007: {
                            continue;
                        }
                    }
                    switch (monster.getMap().getId()) {
                        case 220080001: {
                            if (xpos < -890) {
                                xpos = (int)(-890.0 + Math.ceil(Math.random() * 150.0));
                                break;
                            }
                            if (xpos > 230) {
                                xpos = (int)(230.0 - Math.ceil(Math.random() * 150.0));
                                break;
                            }
                            break;
                        }
                        case 230040420: {
                            if (xpos < -239) {
                                xpos = (int)(-239.0 + Math.ceil(Math.random() * 150.0));
                                break;
                            }
                            if (xpos > 371) {
                                xpos = (int)(371.0 - Math.ceil(Math.random() * 150.0));
                                break;
                            }
                            break;
                        }
                    }
                    monster.getMap().spawnMonsterWithEffect(toSpawn, this.getSpawnEffect(), monster.getMap().calcPointBelow(new Point(xpos, ypos - 1)));
                }
                break;
            }
            default: {
                if (disease != null) {}
                break;
            }
        }
        if (stats.size() > 0 && monster != null) {
            if (this.lt != null && this.rb != null && skill) {
                for (final MapleMapObject mons2 : this.getObjectsInRange(monster, MapleMapObjectType.MONSTER)) {
                    ((MapleMonster)mons2).applyMonsterBuff(stats, this.getX(), this.getSkillId(), this.getDuration(), this, reflection);
                }
            }
            else {
                monster.applyMonsterBuff(stats, this.getX(), this.getSkillId(), this.getDuration(), this, reflection);
            }
        }
        if (disease != null && player != null) {
            if (this.lt != null && this.rb != null && skill && monster != null) {
                for (final MapleCharacter chr2 : this.getPlayersInRange(monster, player)) {
                    chr2.getDiseaseBuff(disease, this);
                }
            }
            else {
                player.getDiseaseBuff(disease, this);
            }
        }
        if (monster != null) {
            monster.setMp(monster.getMp() - this.getMpCon());
        }
    }
    
    public int getSkillId() {
        return this.skillId;
    }
    
    public int getSkillLevel() {
        return this.skillLevel;
    }
    
    public int getMpCon() {
        return this.mpCon;
    }
    
    public List<Integer> getSummons() {
        return Collections.unmodifiableList((List<? extends Integer>)this.toSummon);
    }
    
    public int getSpawnEffect() {
        return this.spawnEffect;
    }
    
    public int getHP() {
        return this.hp;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public long getDuration() {
        return this.duration;
    }
    
    public long getCoolTime() {
        return this.cooltime;
    }
    
    public Point getLt() {
        return this.lt;
    }
    
    public Point getRb() {
        return this.rb;
    }
    
    public int getLimit() {
        return this.limit;
    }
    
    public boolean makeChanceResult() {
        return (double)this.prop >= 1.0 || Math.random() < (double)this.prop;
    }
    
    private Rectangle calculateBoundingBox(final Point posFrom, final boolean facingLeft) {
        Point mylt;
        Point myrb;
        if (facingLeft) {
            mylt = new Point(this.lt.x + posFrom.x, this.lt.y + posFrom.y);
            myrb = new Point(this.rb.x + posFrom.x, this.rb.y + posFrom.y);
        }
        else {
            myrb = new Point(this.lt.x * -1 + posFrom.x, this.rb.y + posFrom.y);
            mylt = new Point(this.rb.x * -1 + posFrom.x, this.lt.y + posFrom.y);
        }
        final Rectangle bounds = new Rectangle(mylt.x, mylt.y, myrb.x - mylt.x, myrb.y - mylt.y);
        return bounds;
    }
    
    private List<MapleCharacter> getPlayersInRange(final MapleMonster monster, final MapleCharacter player) {
        final Rectangle bounds = this.calculateBoundingBox(monster.getPosition(), monster.isFacingLeft());
        final List<MapleCharacter> players = new ArrayList<MapleCharacter>();
        players.add(player);
        return monster.getMap().getPlayersInRectThreadsafe(bounds, players);
    }
    
    private List<MapleMapObject> getObjectsInRange(final MapleMonster monster, final MapleMapObjectType objectType) {
        final Rectangle bounds = this.calculateBoundingBox(monster.getPosition(), monster.isFacingLeft());
        final List<MapleMapObjectType> objectTypes = new ArrayList<MapleMapObjectType>();
        objectTypes.add(objectType);
        return monster.getMap().getMapObjectsInRect(bounds, objectTypes);
    }
    
    public void setOnce(final boolean o) {
        this.summonOnce = o;
    }
    
    public boolean onlyOnce() {
        return this.summonOnce;
    }
}
