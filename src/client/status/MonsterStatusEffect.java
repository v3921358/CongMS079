package client.status;

import java.util.TimerTask;
import java.util.Timer;
import server.life.MapleMonster;
import client.MapleCharacter;
import java.lang.ref.WeakReference;
import server.life.MobSkill;

public class MonsterStatusEffect
{
    private MonsterStatus stati;
    private final int skill;
    private final MobSkill mobskill;
    private final boolean monsterSkill;
    private WeakReference<MapleCharacter> weakChr;
    private Integer x;
    private int poisonDamage;
    private boolean reflect;
    private long cancelTime;
    private long dotTime;
    private boolean newpoison;
    
    public MonsterStatusEffect(final MonsterStatus stat, final Integer x, final int skillId, final MobSkill mobskill, final boolean monsterSkill) {
        this.weakChr = null;
        this.poisonDamage = 0;
        this.reflect = false;
        this.cancelTime = 0L;
        this.dotTime = 0L;
        this.newpoison = true;
        this.stati = stat;
        this.skill = skillId;
        this.monsterSkill = monsterSkill;
        this.mobskill = mobskill;
        this.x = x;
    }
    
    public MonsterStatusEffect(final MonsterStatus stat, final Integer x, final int skillId, final MobSkill mobskill, final boolean monsterSkill, final boolean reflect) {
        this.weakChr = null;
        this.poisonDamage = 0;
        this.reflect = false;
        this.cancelTime = 0L;
        this.dotTime = 0L;
        this.newpoison = true;
        this.stati = stat;
        this.skill = skillId;
        this.monsterSkill = monsterSkill;
        this.mobskill = mobskill;
        this.x = x;
        this.reflect = reflect;
    }
    
    public final MonsterStatus getStati() {
        return this.stati;
    }
    
    public final MonsterStatus getStatus() {
        return this.stati;
    }
    
    public WeakReference<MapleCharacter> getchr() {
        return this.weakChr;
    }
    
    public void setX(final int x) {
        this.x = Integer.valueOf(x);
    }
    
    public final Integer getX() {
        return this.x;
    }
    
    public final void setValue(final MonsterStatus status, final Integer newVal) {
        this.stati = status;
        this.x = newVal;
    }
    
    public final int getSkill() {
        return this.skill;
    }
    
    public final MobSkill getMobSkill() {
        return this.mobskill;
    }
    
    public final boolean isMonsterSkill() {
        return this.monsterSkill;
    }
    
    public final void setCancelTask(final long cancelTask) {
        this.cancelTime = System.currentTimeMillis() + cancelTask;
    }
    
    public final long getCancelTask() {
        return this.cancelTime;
    }
    
    public void setnewpoison(final boolean s) {
        this.newpoison = s;
    }
    
    public void setDotTime(final long duration) {
        this.dotTime = duration;
    }
    
    public long getDotTime() {
        return this.dotTime;
    }
    
    public final void setPoisonDamage(final int poisonDamage, final MapleCharacter chrr) {
        this.poisonDamage = poisonDamage;
        this.weakChr = new WeakReference<MapleCharacter>(chrr);
    }
    
    public final int getPoisonDamage() {
        return this.poisonDamage;
    }
    
    public final boolean shouldCancel() {
        return this.cancelTime > 0L && this.cancelTime <= System.currentTimeMillis();
    }
    
    public final void cancelTask() {
        this.cancelTime = 0L;
    }
    
    public final boolean isReflect() {
        return this.reflect;
    }
    
    public final int getFromID() {
        return (this.weakChr == null || this.weakChr.get() == null) ? 0 : ((MapleCharacter)this.weakChr.get()).getId();
    }
    
    public final void cancelPoisonSchedule(final MapleMonster mm) {
        mm.doPoison(this, this.weakChr);
        this.poisonDamage = 0;
        this.weakChr = null;
    }
    
    public void scheduledoPoison(final MapleMonster mon) {
        final Timer timer = new Timer(true);
        final long time = System.currentTimeMillis();
        final MonsterStatusEffect eff = this;
        if (this.newpoison) {
            final TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (time + MonsterStatusEffect.this.getDotTime() > System.currentTimeMillis() && mon.isAlive()) {
                        MonsterStatusEffect.this.setnewpoison(false);
                        mon.doPoison(eff, (WeakReference<MapleCharacter>)weakChr);
                    }
                    else {
                        MonsterStatusEffect.this.setnewpoison(true);
                        timer.cancel();
                    }
                }
            };
            timer.schedule(task, 0L, 1000L);
        }
    }
    
    public static int genericSkill(final MonsterStatus stat) {
        switch (stat) {
            case STUN: {
                return 90001001;
            }
            case SPEED: {
                return 90001002;
            }
            case POISON: {
                return 90001003;
            }
            case DARKNESS: {
                return 90001004;
            }
            case SEAL: {
                return 90001005;
            }
            case FREEZE: {
                return 90001006;
            }
            case MAGIC_CRASH: {
                return 1111007;
            }
            case SHOWDOWN: {
                return 4121003;
            }
            case IMPRINT: {
                return 22161002;
            }
            case SHADOW_WEB: {
                return 4111003;
            }
            case VENOMOUS_WEAPON: {
                return 5211004;
            }
            case DOOM: {
                return 2311005;
            }
            case NINJA_AMBUSH: {
                return 4121004;
            }
            default: {
                return 0;
            }
        }
    }
}
