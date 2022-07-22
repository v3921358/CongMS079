package client;

import server.Randomizer;
import java.io.Serializable;

public enum MapleDisease implements Serializable
{
    POTION(8796093022208L, true), 
    SHADOW(17592186044416L, true), 
    BLIND(35184372088832L, true), 
    FREEZE(2251799813685248L, true), 
    SLOW(1L), 
    MORPH(2L), 
    SEDUCE(128L), 
    ZOMBIFY(16384L), 
    REVERSE_DIRECTION(524288L), 
    WEIRD_FLAME(134217728L), 
    STUN(562949953421312L), 
    POISON(1125899906842624L), 
    SEAL(2251799813685248L), 
    DARKNESS(4503599627370496L), 
    WEAKEN(4611686018427387904L), 
    CURSE(Long.MIN_VALUE);
    
    private static final long serialVersionUID = 0L;
    private final long i;
    private final boolean first;
    
    private MapleDisease(final long i) {
        this.i = i;
        this.first = false;
    }
    
    private MapleDisease(final long i, final boolean first) {
        this.i = i;
        this.first = first;
    }
    
    public boolean isFirst() {
        return this.first;
    }
    
    public long getValue() {
        return this.i;
    }
    
    public static final MapleDisease getRandom() {
        MapleDisease dis = null;
    Block_1:
        while (true) {
            final MapleDisease[] values = values();
            for (int length = values.length, i = 0; i < length; ++i) {
                dis = values[i];
                if (Randomizer.nextInt(values().length) == 0) {
                    break Block_1;
                }
            }
        }
        return dis;
    }
    
    public static final MapleDisease getBySkill(final int skill) {
        switch (skill) {
            case 120: {
                return MapleDisease.SEAL;
            }
            case 121: {
                return MapleDisease.DARKNESS;
            }
            case 122: {
                return MapleDisease.WEAKEN;
            }
            case 123: {
                return MapleDisease.STUN;
            }
            case 124: {
                return MapleDisease.CURSE;
            }
            case 126: {
                return MapleDisease.SLOW;
            }
            case 128: {
                return MapleDisease.SEDUCE;
            }
            case 132: {
                return MapleDisease.REVERSE_DIRECTION;
            }
            case 133: {
                return MapleDisease.ZOMBIFY;
            }
            case 135: {
                return MapleDisease.SHADOW;
            }
            case 136: {
                return MapleDisease.BLIND;
            }
            default: {
                return null;
            }
        }
    }
    
    public static final MapleDisease getByMobSkill(final int skill) {
        switch (skill) {
            case 120: {
                return MapleDisease.SEAL;
            }
            case 121: {
                return MapleDisease.DARKNESS;
            }
            case 122: {
                return MapleDisease.WEAKEN;
            }
            case 123: {
                return MapleDisease.STUN;
            }
            case 124: {
                return MapleDisease.CURSE;
            }
            case 125: {
                return MapleDisease.POISON;
            }
            case 126: {
                return MapleDisease.SLOW;
            }
            case 128: {
                return MapleDisease.SEDUCE;
            }
            case 132: {
                return MapleDisease.REVERSE_DIRECTION;
            }
            case 133: {
                return MapleDisease.ZOMBIFY;
            }
            case 134: {
                return MapleDisease.POTION;
            }
            case 135: {
                return MapleDisease.SHADOW;
            }
            case 136: {
                return MapleDisease.BLIND;
            }
            case 137: {
                return MapleDisease.FREEZE;
            }
            default: {
                return null;
            }
        }
    }
    
    public static final int getByDisease(final MapleDisease skill) {
        switch (skill) {
            case SEAL: {
                return 120;
            }
            case DARKNESS: {
                return 121;
            }
            case WEAKEN: {
                return 122;
            }
            case STUN: {
                return 123;
            }
            case CURSE: {
                return 124;
            }
            case POISON: {
                return 125;
            }
            case SLOW: {
                return 126;
            }
            case SEDUCE: {
                return 128;
            }
            case REVERSE_DIRECTION: {
                return 132;
            }
            case ZOMBIFY: {
                return 133;
            }
            case POTION: {
                return 134;
            }
            case SHADOW: {
                return 135;
            }
            case BLIND: {
                return 136;
            }
            case FREEZE: {
                return 137;
            }
            default: {
                return 0;
            }
        }
    }
}
