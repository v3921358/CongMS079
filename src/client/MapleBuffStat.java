package client;

import java.io.Serializable;

public enum MapleBuffStat implements Serializable
{
    WATK(0), 
    WDEF(1), 
    MATK(2), 
    MDEF(3), 
    ACC(4), 
    AVOID(5), 
    HANDS(6), 
    SPEED(7), 
    JUMP(8), 
    MAGIC_GUARD(9), 
    DARKSIGHT(10), 
    BOOSTER(11), 
    POWERGUARD(12), 
    MAXHP(13), 
    MAXMP(14), 
    INVINCIBLE(15), 
    SOULARROW(16), 
    STUN(17), 
    POISON(18), 
    SEAL(19), 
    DARKNESS(20), 
    COMBO(21), 
    SUMMON(21), 
    WK_CHARGE(22), 
    DRAGONBLOOD(23), 
    HOLY_SYMBOL(24), 
    MESOUP(25), 
    SHADOWPARTNER(26), 
    PICKPOCKET(27), 
    PUPPET(28), 
    MESOGUARD(29), 
    WEAKEN(30), 
    CURSE(31), 
    SLOW(32), 
    MORPH(33), 
    RECOVERY(34), 
    HP_LOSS_GUARD(34), 
    MAPLE_WARRIOR(35), 
    STANCE(36), 
    SHARP_EYES(37), 
    MANA_REFLECTION(38), 
    DRAGON_ROAR(39), 
    SPIRIT_CLAW(40), 
    INFINITY(41), 
    HOLY_SHIELD(42), 
    HAMSTRING(43), 
    BLIND(44), 
    CONCENTRATE(45), 
    ZOMBIFY(46), 
    ECHO_OF_HERO(47), 
    UNKNOWN3(48), 
    MESO_RATE(48), 
    GHOST_MORPH(49), 
    ARIANT_COSS_IMU(50), 
    DROP_RATE(52), 
    EXPRATE(54), 
    ACASH_RATE(55), 
    GM_HIDE(56), 
    UNKNOWN7(57), 
    ILLUSION(58), 
    BERSERK_FURY(57), 
    DIVINE_BODY(60), 
    SPARK(59), 
    ARIANT_COSS_IMU2(62), 
    FINALATTACK(61), 
    ELEMENT_RESET(63), 
    WIND_WALK(64), 
    ARAN_COMBO(66), 
    COMBO_DRAIN(67), 
    COMBO_BARRIER(68), 
    BODY_PRESSURE(69), 
    SMART_KNOCKBACK(70), 
    SOUL_STONE(73), 
    ENERGY_CHARGE(77), 
    DASH_SPEED(78), 
    DASH_JUMP(79), 
    MONSTER_RIDING(80), 
    SPEED_INFUSION(81), 
    HOMING_BEACON(82), 
    SOARING(82), 
    FREEZE(83), 
    LIGHTNING_CHARGE(84), 
    MIRROR_IMAGE(85), 
    OWL_SPIRIT(86), 
    召唤玩家1(77), 
    召唤玩家2(78), 
    召唤玩家3(79), 
    召唤玩家4(80), 
    召唤玩家5(81), 
    召唤玩家6(82), 
    召唤玩家7(83), 
    召唤玩家8(84);
    
    private static final long serialVersionUID = 0L;
    private final int buffstat;
    private final int first;
    private final long oldvalue;
    
    private MapleBuffStat(final int buffstat) {
        this.buffstat = 1 << buffstat % 32;
        this.first = 3 - (int)Math.floor((double)(buffstat / 32));
        this.oldvalue = (long)new Long((long)this.buffstat) << 32 * (this.first % 2 + 1);
    }
    
    private MapleBuffStat(final int buffstat, final boolean stacked) {
        this.buffstat = 1 << buffstat % 32;
        this.first = (int)Math.floor((double)(buffstat / 32));
        this.oldvalue = (long)new Long((long)this.buffstat) << 32 * (this.first % 2 + 1);
    }
    
    public final long getOldValue() {
        return this.oldvalue;
    }
    
    public final int getPosition() {
        return this.first;
    }
    
    public final int getPosition(final boolean fromZero) {
        if (!fromZero) {
            return this.first;
        }
        switch (this.first) {
            case 4: {
                return 0;
            }
            case 3: {
                return 1;
            }
            case 2: {
                return 2;
            }
            case 1: {
                return 3;
            }
            default: {
                return 0;
            }
        }
    }
    
    public final int getValue() {
        return this.buffstat;
    }
}
