package client.status;

import client.MapleDisease;

public enum MonsterStatus
{
    WATK(0), 
    WDEF(1), 
    MATK(2), 
    MDEF(3), 
    ACC(4), 
    AVOID(5), 
    SPEED(6), 
    STUN(7), 
    FREEZE(8), 
    POISON(9), 
    SEAL(10), 
    SHOWDOWN(11), 
    WEAPON_ATTACK_UP(12), 
    WEAPON_DEFENSE_UP(13), 
    MAGIC_ATTACK_UP(14), 
    MAGIC_DEFENSE_UP(15), 
    DOOM(16, 18), 
    SHADOW_WEB(17, 19), 
    WEAPON_IMMUNITY(18, 16), 
    MAGIC_IMMUNITY(19, 17), 
    DAMAGE_IMMUNITY(21, 20), 
    NINJA_AMBUSH(22, 21), 
    DANAGED_ELEM_ATTR(23), 
    VENOMOUS_WEAPON(24, 22), 
    DARKNESS(25, 23), 
    BLIND(25, 23), 
    SEAL_SKILL(26, 24), 
    EMPTY(27, true, 33), 
    HYPNOTIZE(28, 25), 
    WEAPON_DAMAGE_REFLECT(29, 26), 
    MAGIC_DAMAGE_REFLECT(32, 27), 
    SUMMON(33, 34), 
    MBS_32(32, 28), 
    NEUTRALISE(33, 29), 
    IMPRINT(34, 30), 
    MONSTER_BOMB(35, 31), 
    MAGIC_CRASH(36);
    
    private final int i;
    private final int pos;
    private final boolean isDefault;
    private final int order;
    
    private MonsterStatus(final int i) {
        this.i = 1 << i % 32;
        this.pos = 3 - (int)Math.floor((double)(i / 32));
        this.order = this.pos;
        this.isDefault = false;
    }
    
    private MonsterStatus(final int i, final int order) {
        this.i = 1 << i % 32;
        this.pos = 3 - (int)Math.floor((double)(i / 32));
        this.order = order;
        this.isDefault = false;
    }
    
    private MonsterStatus(final int i, final boolean isDefault) {
        this.i = 1 << i % 32;
        this.pos = 3 - (int)Math.floor((double)(i / 32));
        this.isDefault = isDefault;
        this.order = i;
    }
    
    private MonsterStatus(final int i, final boolean isDefault, final int order) {
        this.i = 1 << i % 32;
        this.pos = 3 - (int)Math.floor((double)(i / 32));
        this.isDefault = isDefault;
        this.order = order;
    }
    
    public int getPosition() {
        return this.pos;
    }
    
    public boolean isDefault() {
        return this.isDefault;
    }
    
    public int getValue() {
        return this.i;
    }
    
    public int getOrder() {
        return this.order;
    }
    
    public static final MapleDisease getLinkedDisease(final MonsterStatus skill) {
        switch (skill) {
            case STUN:
            case SHADOW_WEB: {
                return MapleDisease.STUN;
            }
            case POISON:
            case VENOMOUS_WEAPON: {
                return MapleDisease.POISON;
            }
            case SEAL:
            case MAGIC_CRASH: {
                return MapleDisease.SEAL;
            }
            case FREEZE: {
                return MapleDisease.FREEZE;
            }
            case DARKNESS: {
                return MapleDisease.DARKNESS;
            }
            case SPEED: {
                return MapleDisease.SLOW;
            }
            default: {
                return null;
            }
        }
    }
}
