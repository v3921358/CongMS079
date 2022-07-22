package server.maps;

import client.anticheat.CheatingOffense;
import constants.GameConstants;
import tools.MaplePacketCreator;
import client.MapleClient;
import java.awt.Point;
import server.MapleStatEffect;
import client.MapleCharacter;

public class MapleSummon extends AbstractAnimatedMapleMapObject
{
    private final int ownerid;
    private final int skillLevel;
    private final int ownerLevel;
    private final int skill;
    private int fh;
    private MapleMap map;
    private short hp;
    private boolean changedMap;
    private SummonMovementType movementType;
    private int lastSummonTickCount;
    private byte Summon_tickResetCount;
    private long Server_ClientSummonTickDiff;
    
    public MapleSummon(final MapleCharacter owner, final MapleStatEffect skill, final Point pos, final SummonMovementType movementType) {
        this.changedMap = false;
        this.ownerid = owner.getId();
        this.ownerLevel = owner.getLevel();
        this.skill = skill.getSourceId();
        this.map = owner.getMap();
        this.skillLevel = skill.getLevel();
        this.movementType = movementType;
        this.setPosition(pos);
        this.setStance(owner.getStance());
        try {
            this.fh = owner.getMap().getFootholds().findBelow(pos).getId();
        }
        catch (NullPointerException e) {
            this.fh = 0;
        }
        if (!this.isPuppet()) {
            this.lastSummonTickCount = 0;
            this.Summon_tickResetCount = 0;
            this.Server_ClientSummonTickDiff = 0L;
        }
    }
    
    @Override
    public final void sendSpawnData(final MapleClient client) {
    }
    
    @Override
    public final void sendDestroyData(final MapleClient client) {
        client.sendPacket(MaplePacketCreator.removeSummon(this, false));
    }
    
    public final void updateMap(final MapleMap map) {
        this.map = map;
    }
    
    public final MapleCharacter getOwner() {
        return this.map.getCharacterById(this.ownerid);
    }
    
    public final int getFh() {
        return this.fh;
    }
    
    public final void setFh(final int fh) {
        this.fh = fh;
    }
    
    public final int getOwnerId() {
        return this.ownerid;
    }
    
    public final int getOwnerLevel() {
        return this.ownerLevel;
    }
    
    public final int getSkill() {
        return this.skill;
    }
    
    public final short getHP() {
        return this.hp;
    }
    
    public final void addHP(final short delta) {
        this.hp += delta;
    }
    
    public final SummonMovementType getMovementType() {
        return this.movementType;
    }
    
    public final boolean isPuppet() {
        switch (this.skill) {
            case 3111002:
            case 3211002:
            case 4341006:
            case 13111004:
            case 33111003: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public final boolean isGaviota() {
        return this.skill == 5211002;
    }
    
    public final boolean isBeholder() {
        return this.skill == 1321007;
    }
    
    public final boolean isMultiSummon() {
        return this.skill == 5211002 || this.skill == 5211001 || this.skill == 5220002 || this.skill == 32111006;
    }
    
    public final boolean isSummon() {
        switch (this.skill) {
            case 1321007:
            case 2121005:
            case 2221005:
            case 2311006:
            case 2321003:
            case 5211001:
            case 5211002:
            case 5220002:
            case 11001004:
            case 12001004:
            case 12111004:
            case 13001004:
            case 13111004:
            case 14001005:
            case 15001004:
            case 32111006:
            case 33111005:
            case 35111001:
            case 35111002:
            case 35111004:
            case 35111005:
            case 35111009:
            case 35111010:
            case 35121009:
            case 35121011: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public final int getSkillLevel() {
        return this.skillLevel;
    }
    
    public final int getSummonType() {
        if ((this.skill != 33111003 && this.skill != 3120012 && this.skill != 3220012 && this.isPuppet()) || this.skill == 33101008 || this.skill == 35111002) {
            return 0;
        }
        switch (this.skill) {
            case 1321007: {
                return 2;
            }
            case 35111001:
            case 35111009:
            case 35111010:
            case 42111003: {
                return 3;
            }
            case 35121009: {
                return 5;
            }
            case 35121003: {
                return 6;
            }
            case 4111007:
            case 4211007:
            case 14111010: {
                return 7;
            }
            case 42101001: {
                return 8;
            }
            default: {
                return 1;
            }
        }
    }
    
    @Override
    public final MapleMapObjectType getType() {
        return MapleMapObjectType.SUMMON;
    }
    
    public final void CheckSummonAttackFrequency(final MapleCharacter chr, final int tickcount) {
        final int tickdifference = tickcount - this.lastSummonTickCount;
        if (tickdifference < GameConstants.getSummonAttackDelay(this.skill)) {
            chr.getCheatTracker().registerOffense(CheatingOffense.FAST_SUMMON_ATTACK, chr.getName() + "快速召喚獸攻击");
        }
        final long STime_TC = System.currentTimeMillis() - (long)tickcount;
        final long S_C_Difference = this.Server_ClientSummonTickDiff - STime_TC;
        if (S_C_Difference > 200L) {}
        ++this.Summon_tickResetCount;
        if (this.Summon_tickResetCount > 4) {
            this.Summon_tickResetCount = 0;
            this.Server_ClientSummonTickDiff = STime_TC;
        }
        this.lastSummonTickCount = tickcount;
    }
    
    public final boolean isChangedMap() {
        return this.changedMap;
    }
    
    public final void setChangedMap(final boolean cm) {
        this.changedMap = cm;
    }
}
