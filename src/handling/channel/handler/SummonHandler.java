package handling.channel.handler;

import server.MapleStatEffect;
import client.ISkill;
import server.life.MapleMonster;
import client.SummonSkillEntry;
import server.maps.MapleMap;
import server.maps.MapleMapObject;
import server.AutobanManager;
import tools.packet.MobPacket;
import server.life.MobSkill;
import client.status.MonsterStatusEffect;
import client.status.MonsterStatus;
import java.util.Map.Entry;
import java.awt.geom.Point2D;
import server.life.SummonAttackEntry;
import java.util.ArrayList;
import client.anticheat.CheatingOffense;
import client.SkillFactory;
import server.maps.MapleMapObjectType;
import client.MapleClient;
import client.MapleBuffStat;
import java.util.Iterator;
import server.movement.LifeMovementFragment;
import java.util.List;
import tools.MaplePacketCreator;
import server.maps.AnimatedMapleMapObject;
import server.maps.SummonMovementType;
import server.maps.MapleSummon;
import java.awt.Point;
import client.MapleCharacter;
import tools.data.LittleEndianAccessor;

public class SummonHandler
{
    public static final void MoveSummon(final LittleEndianAccessor slea, final MapleCharacter chr) {
        final int oid = slea.readInt();
        final Point startPos = new Point((int)slea.readShort(), (int)slea.readShort());
        final List<LifeMovementFragment> res = MovementParse.parseMovement(slea, 4);
        if (chr == null) {
            return;
        }
        for (final MapleSummon sum : chr.getSummons().values()) {
            if (sum.getObjectId() == oid && sum.getMovementType() != SummonMovementType.STATIONARY) {
                final Point pos = sum.getPosition();
                MovementParse.updatePosition(res, (AnimatedMapleMapObject)sum, 0);
                if (!sum.isChangedMap()) {
                    chr.getMap().broadcastMessage(chr, MaplePacketCreator.moveSummon(chr.getId(), oid, startPos, res), sum.getPosition());
                    break;
                }
                break;
            }
        }
    }
    
    public static final void DamageSummon(final LittleEndianAccessor slea, final MapleCharacter chr) {
        final int unkByte = slea.readByte();
        final int damage = slea.readInt();
        final int monsterIdFrom = slea.readInt();
        for (final MapleSummon summon : chr.getSummons().values()) {
            if (summon.isPuppet() && summon.getOwnerId() == chr.getId()) {
                summon.addHP((short)(-damage));
                if (summon.getHP() <= 0) {
                    chr.cancelEffectFromBuffStat(MapleBuffStat.PUPPET);
                }
                chr.getMap().broadcastMessage(chr, MaplePacketCreator.damageSummon(chr.getId(), summon.getSkill(), damage, unkByte, monsterIdFrom), summon.getPosition());
                break;
            }
        }
    }
    
    public static void SummonAttack(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (chr == null || !chr.isAlive()) {
            return;
        }
        final MapleMap map = chr.getMap();
        final MapleMapObject obj = map.getMapObject(slea.readInt(), MapleMapObjectType.SUMMON);
        if (obj == null) {
            return;
        }
        final MapleSummon summon = (MapleSummon)obj;
        if (summon.getOwnerId() != chr.getId() || summon.getSkillLevel() <= 0) {
            return;
        }
        final SummonSkillEntry sse = SkillFactory.getSummonData(summon.getSkill());
        if (sse == null) {
            return;
        }
        slea.skip(8);
        final int tick = slea.readInt();
        chr.updateTick(tick);
        summon.CheckSummonAttackFrequency(chr, tick);
        slea.skip(8);
        final byte animation = slea.readByte();
        slea.skip(8);
        final byte numAttacked = slea.readByte();
        if (numAttacked > sse.mobCount) {
            chr.getCheatTracker().registerOffense(CheatingOffense.SUMMON_HACK_MOBS);
            return;
        }
        final List<SummonAttackEntry> allDamage = new ArrayList<SummonAttackEntry>();
        chr.getCheatTracker().checkSummonAttack();
        for (int i = 0; i < numAttacked; ++i) {
            final MapleMonster mob = map.getMonsterByOid(slea.readInt());
            if (mob != null) {
                if (chr.getPosition().distanceSq((Point2D)mob.getPosition()) > 400000.0) {
                    chr.getCheatTracker().registerOffense(CheatingOffense.ATTACK_FARAWAY_MONSTER_SUMMON);
                }
                slea.skip(14);
                final int damage = slea.readInt();
                allDamage.add(new SummonAttackEntry(mob, damage));
            }
        }
        if (!summon.isChangedMap()) {
            map.broadcastMessage(chr, MaplePacketCreator.summonAttack(summon.getOwnerId(), summon.getObjectId(), animation, allDamage, (int)chr.getLevel()), summon.getPosition());
        }
        final ISkill summonSkill = SkillFactory.getSkill(summon.getSkill());
        final MapleStatEffect summonEffect = summonSkill.getEffect(summon.getSkillLevel());
        if (summonEffect == null) {
            return;
        }
        for (final SummonAttackEntry attackEntry : allDamage) {
            final int toDamage = attackEntry.getDamage();
            final MapleMonster mob2 = attackEntry.getMonster();
            if (toDamage > 0 && summonEffect.getMonsterStati().size() > 0 && summonEffect.makeChanceResult()) {
                for (final Entry<MonsterStatus, Integer> z : summonEffect.getMonsterStati().entrySet()) {
                    mob2.applyStatus(chr, new MonsterStatusEffect((MonsterStatus)z.getKey(), Integer.valueOf(z.getValue()), summonSkill.getId(), null, false), summonEffect.isPoison(), 4000L, true, summonEffect);
                }
            }
            if (chr.isGM() || toDamage < 120000) {
                mob2.damage(chr, (long)toDamage, true);
                chr.checkMonsterAggro(mob2);
                if (mob2.isAlive()) {
                    continue;
                }
                chr.getClient().sendPacket(MobPacket.killMonster(mob2.getObjectId(), 1));
            }
            else {
                AutobanManager.getInstance().autoban(c, "High Summon Damage (" + toDamage + " to " + attackEntry.getMonster().getId() + ")");
            }
        }
        if (summon.isGaviota()) {
            chr.getMap().broadcastMessage(MaplePacketCreator.removeSummon(summon, true));
            chr.getMap().removeMapObject((MapleMapObject)summon);
            chr.removeVisibleMapObject((MapleMapObject)summon);
            chr.cancelEffectFromBuffStat(MapleBuffStat.SUMMON);
        }
    }
}
