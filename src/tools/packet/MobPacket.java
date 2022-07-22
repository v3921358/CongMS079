package tools.packet;

import java.util.ArrayList;
import tools.MaplePacketCreator;
import java.util.Set;
import java.util.Collections;
import java.util.Comparator;
import client.status.MonsterStatus;
import java.util.HashSet;
import java.util.LinkedList;
import client.status.MonsterStatusEffect;
import java.util.Collection;
import java.util.Iterator;
import server.movement.LifeMovementFragment;
import java.util.List;
import java.awt.Point;
import server.life.MapleMonster;
import handling.SendPacketOpcode;
import tools.data.MaplePacketLittleEndianWriter;

public class MobPacket
{
    public static byte[] damageMonster(final int oid, final long damage) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.DAMAGE_MONSTER.getValue());
        mplew.writeInt(oid);
        mplew.write(0);
        if (damage > 2147483647L) {
            mplew.writeInt(Integer.MAX_VALUE);
        }
        else {
            mplew.writeInt((int)damage);
        }
        return mplew.getPacket();
    }
    
    public static byte[] damageFriendlyMob(final MapleMonster mob, final long damage, final boolean display) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.DAMAGE_MONSTER.getValue());
        mplew.writeInt(mob.getObjectId());
        mplew.write(display ? 1 : 2);
        if (damage > 2147483647L) {
            mplew.writeInt(Integer.MAX_VALUE);
        }
        else {
            mplew.writeInt((int)damage);
        }
        if (mob.getHp() > 2147483647L) {
            mplew.writeInt((int)((double)mob.getHp() / (double)mob.getMobMaxHp() * 2.147483647E9));
        }
        else {
            mplew.writeInt((int)mob.getHp());
        }
        if (mob.getMobMaxHp() > 2147483647L) {
            mplew.writeInt(Integer.MAX_VALUE);
        }
        else {
            mplew.writeInt((int)mob.getMobMaxHp());
        }
        return mplew.getPacket();
    }
    
    public static byte[] killMonster(final int oid, final int animation) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.KILL_MONSTER.getValue());
        mplew.writeInt(oid);
        mplew.write(animation);
        return mplew.getPacket();
    }
    
    public static byte[] healMonster(final int oid, final int heal) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.DAMAGE_MONSTER.getValue());
        mplew.writeInt(oid);
        mplew.write(0);
        mplew.writeInt(-heal);
        return mplew.getPacket();
    }
    
    public static byte[] showMonsterHP(final int oid, final int remhppercentage) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SHOW_MONSTER_HP.getValue());
        mplew.writeInt(oid);
        mplew.write(remhppercentage);
        return mplew.getPacket();
    }
    
    public static byte[] showBossHP(final MapleMonster mob) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.BOSS_ENV.getValue());
        mplew.write(5);
        mplew.writeInt(mob.getId());
        if (mob.getHp() > 2147483647L) {
            mplew.writeInt((int)((double)mob.getHp() / (double)mob.getMobMaxHp() * 2.147483647E9));
        }
        else {
            mplew.writeInt((int)mob.getHp());
        }
        if (mob.getMobMaxHp() > 2147483647L) {
            mplew.writeInt(Integer.MAX_VALUE);
        }
        else {
            mplew.writeInt((int)mob.getMobMaxHp());
        }
        mplew.write(mob.getStats().getTagColor());
        mplew.write(mob.getStats().getTagBgColor());
        return mplew.getPacket();
    }
    
    public static byte[] showBossHP(final int monsterId, final long currentHp, final long maxHp) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.BOSS_ENV.getValue());
        mplew.write(5);
        mplew.writeInt(monsterId);
        if (currentHp > 2147483647L) {
            mplew.writeInt((int)((double)currentHp / (double)maxHp * 2.147483647E9));
        }
        else {
            mplew.writeInt((int)((currentHp <= 0L) ? -1L : currentHp));
        }
        if (maxHp > 2147483647L) {
            mplew.writeInt(Integer.MAX_VALUE);
        }
        else {
            mplew.writeInt((int)maxHp);
        }
        mplew.write(6);
        mplew.write(5);
        return mplew.getPacket();
    }
    
    public static byte[] moveMonster(final boolean useskill, final int skill, final int unk, final int oid, final Point startPos, final Point endPos, final List<LifeMovementFragment> moves) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MOVE_MONSTER.getValue());
        mplew.writeInt(oid);
        mplew.write(0);
        mplew.write((int)(useskill ? 1 : 0));
        mplew.write(skill);
        mplew.writeInt(unk);
        mplew.writePos(startPos);
        serializeMovementList(mplew, moves);
        return mplew.getPacket();
    }
    
    private static void serializeMovementList(final MaplePacketLittleEndianWriter lew, final List<LifeMovementFragment> moves) {
        lew.write(moves.size());
        for (final LifeMovementFragment move : moves) {
            move.serialize(lew);
        }
    }
    
    public static byte[] spawnMonster(final MapleMonster life, final int spawnType, final int effect, final int link) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SPAWN_MONSTER.getValue());
        mplew.writeInt(life.getObjectId());
        mplew.write(1);
        mplew.writeInt(life.getId());
        addMonsterStatus(mplew, life);
        mplew.writeShort(life.getPosition().x);
        mplew.writeShort(life.getPosition().y);
        mplew.write(life.getStance());
        mplew.writeShort(0);
        mplew.writeShort(life.getFh());
        if (effect != 0 || link != 0) {
            mplew.write((effect != 0) ? effect : -3);
            mplew.writeInt(link);
        }
        else {
            if (spawnType == 0) {
                mplew.writeInt(effect);
            }
            mplew.write(spawnType);
        }
        mplew.write(life.getCarnivalTeam());
        mplew.writeInt(0);
        return mplew.getPacket();
    }
    
    private static void writeMaskFromList(final MaplePacketLittleEndianWriter mplew, final Collection<MonsterStatusEffect> ss) {
        final int[] mask = new int[4];
        for (final MonsterStatusEffect statup : ss) {
            final int[] array = mask;
            final int position = statup.getStatus().getPosition();
            array[position] |= statup.getStatus().getValue();
        }
        for (int i = 0; i < mask.length; ++i) {
            mplew.writeInt(mask[i]);
        }
    }
    
    public static void addMonsterStatus(final MaplePacketLittleEndianWriter mplew, final MapleMonster life) {
        if (life.getStati().size() <= 0) {
            life.addEmpty();
        }
        final LinkedList<MonsterStatusEffect> buffs = new LinkedList<MonsterStatusEffect>((Collection<? extends MonsterStatusEffect>)life.getStati().values());
        EncodeTemporary(mplew, (List<MonsterStatusEffect>)buffs);
    }
    
    public static byte[] controlMonster(final MapleMonster life, final boolean newSpawn, final boolean aggro) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SPAWN_MONSTER_CONTROL.getValue());
        mplew.write(aggro ? 2 : 1);
        mplew.writeInt(life.getObjectId());
        mplew.write(1);
        mplew.writeInt(life.getId());
        addMonsterStatus(mplew, life);
        mplew.writeShort(life.getPosition().x);
        mplew.writeShort(life.getPosition().y);
        mplew.write(life.getStance());
        mplew.writeShort(life.getFh());
        mplew.writeShort(life.getFh());
        mplew.write(life.isFake() ? 252 : (newSpawn ? -2 : -1));
        mplew.write(life.getCarnivalTeam());
        mplew.writeInt(0);
        if (life.getId() / 10000 == 961) {
            mplew.writeAsciiString("");
        }
        return mplew.getPacket();
    }
    
    public static byte[] stopControllingMonster(final int oid) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SPAWN_MONSTER_CONTROL.getValue());
        mplew.write(0);
        mplew.writeInt(oid);
        return mplew.getPacket();
    }
    
    public static byte[] makeMonsterInvisible(final MapleMonster life) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SPAWN_MONSTER_CONTROL.getValue());
        mplew.write(0);
        mplew.writeInt(life.getObjectId());
        return mplew.getPacket();
    }
    
    public static byte[] makeMonsterReal(final MapleMonster life) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SPAWN_MONSTER.getValue());
        mplew.writeInt(life.getObjectId());
        mplew.write(1);
        mplew.writeInt(life.getId());
        addMonsterStatus(mplew, life);
        mplew.writeShort(life.getPosition().x);
        mplew.writeShort(life.getPosition().y);
        mplew.write(life.getStance());
        mplew.writeShort(0);
        mplew.writeShort(life.getFh());
        mplew.writeShort(-1);
        mplew.writeInt(0);
        return mplew.getPacket();
    }
    
    public static byte[] moveMonsterResponse(final int objectid, final short moveid, final int currentMp, final boolean useSkills, final int skillId, final int skillLevel) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MOVE_MONSTER_RESPONSE.getValue());
        mplew.writeInt(objectid);
        mplew.writeShort((int)moveid);
        mplew.write((int)(useSkills ? 1 : 0));
        mplew.writeShort(currentMp);
        mplew.write(skillId);
        mplew.write(skillLevel);
        return mplew.getPacket();
    }
    
    public static void SingleProcessStatSet(final MaplePacketLittleEndianWriter mplew, final MonsterStatusEffect buff) {
        final List<MonsterStatusEffect> ss = new LinkedList<MonsterStatusEffect>();
        ss.add(buff);
        ProcessStatSet(mplew, ss);
    }
    
    public static void EncodeTemporary(final MaplePacketLittleEndianWriter mplew, final List<MonsterStatusEffect> buffs) {
        final Set<MonsterStatus> mobstat = new HashSet<MonsterStatus>();
        writeMaskFromList(mplew, (Collection<MonsterStatusEffect>)buffs);
        Collections.sort(buffs, (Comparator<? super MonsterStatusEffect>)new Comparator<MonsterStatusEffect>() {
            @Override
            public int compare(final MonsterStatusEffect o1, final MonsterStatusEffect o2) {
                final int val1 = o1.getStatus().getOrder();
                final int val2 = o2.getStatus().getOrder();
                return (val1 < val2) ? -1 : ((val1 == val2) ? 0 : 1);
            }
        });
        final Collection<MonsterStatus> buffstatus = new LinkedList<MonsterStatus>();
        for (final MonsterStatusEffect buff : buffs) {
            buffstatus.add(buff.getStatus());
            if (buff.getStatus() != MonsterStatus.EMPTY) {
                mplew.writeShort((int)buff.getX());
                if (buff.getMobSkill() != null) {
                    mplew.writeShort(buff.getMobSkill().getSkillId());
                    mplew.writeShort(buff.getMobSkill().getSkillLevel());
                }
                else if (buff.getSkill() > 0) {
                    mplew.writeInt((buff.getSkill() > 0) ? buff.getSkill() : 0);
                }
                mplew.writeShort((int)(short)(int)((buff.getCancelTask() - System.currentTimeMillis()) / 1000L));
            }
        }
        if (buffstatus.contains((Object)MonsterStatus.EMPTY)) {
            final int result = 0;
            mplew.writeInt(result);
            for (int i = 0; i < result; ++i) {
                mplew.writeInt(0);
                mplew.writeInt(0);
                mplew.writeInt(0);
            }
        }
        if (buffstatus.contains((Object)MonsterStatus.WEAPON_DAMAGE_REFLECT)) {
            mplew.writeInt(0);
        }
        if (buffstatus.contains((Object)MonsterStatus.MAGIC_DAMAGE_REFLECT)) {
            mplew.writeInt(0);
        }
        if (buffstatus.contains((Object)MonsterStatus.WEAPON_DAMAGE_REFLECT)) {
            mplew.writeInt(0);
        }
        if (buffstatus.contains((Object)MonsterStatus.SUMMON)) {
            mplew.write(0);
            mplew.write(0);
        }
    }
    
    public static void ProcessStatSet(final MaplePacketLittleEndianWriter mplew, final List<MonsterStatusEffect> buffs) {
        EncodeTemporary(mplew, buffs);
        mplew.writeShort(2);
        mplew.write(1);
        mplew.write(1);
    }
    
    public static byte[] applyMonsterStatus(final MapleMonster mons, final MonsterStatusEffect ms) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.APPLY_MONSTER_STATUS.getValue());
        mplew.writeInt(mons.getObjectId());
        SingleProcessStatSet(mplew, ms);
        return mplew.getPacket();
    }
    
    public static byte[] applyMonsterStatus(final MapleMonster mons, final List<MonsterStatusEffect> mse) {
        if (mse.size() <= 0 || mse.get(0) == null) {
            return MaplePacketCreator.enableActions();
        }
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.APPLY_MONSTER_STATUS.getValue());
        mplew.writeInt(mons.getObjectId());
        ProcessStatSet(mplew, mse);
        return mplew.getPacket();
    }
    
    public static byte[] cancelMonsterStatus(final MapleMonster mons, final MonsterStatusEffect ms) {
        final List<MonsterStatusEffect> mse = new ArrayList<MonsterStatusEffect>();
        mse.add(ms);
        return cancelMonsterStatus(mons, mse);
    }
    
    public static byte[] cancelMonsterStatus(final MapleMonster mons, final List<MonsterStatusEffect> mse) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CANCEL_MONSTER_STATUS.getValue());
        mplew.writeInt(mons.getObjectId());
        writeMaskFromList(mplew, (Collection<MonsterStatusEffect>)mse);
        final boolean cond = false;
        if (cond) {
            final int v6 = 0;
            mplew.writeInt(v6);
            for (int i = 0; i < v6; ++i) {
                mplew.writeInt(0);
            }
        }
        mplew.writeInt(0);
        return mplew.getPacket();
    }
    
    public static byte[] talkMonster(final int oid, final int itemId, final String msg) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.TALK_MONSTER.getValue());
        mplew.writeInt(oid);
        mplew.writeInt(500);
        mplew.writeInt(itemId);
        mplew.write((int)((itemId > 0) ? 1 : 0));
        mplew.write((int)((msg != null && msg.length() > 0) ? 1 : 0));
        if (msg != null && msg.length() > 0) {
            mplew.writeMapleAsciiString(msg);
        }
        mplew.writeInt(1);
        return mplew.getPacket();
    }
    
    public static byte[] removeTalkMonster(final int oid) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.REMOVE_TALK_MONSTER.getValue());
        mplew.writeInt(oid);
        return mplew.getPacket();
    }
}
