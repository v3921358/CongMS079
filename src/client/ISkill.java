package client;

import server.life.Element;
import server.MapleStatEffect;

public interface ISkill
{
    int getId();
    
    MapleStatEffect getEffect(final int p0);
    
    byte getMaxLevel();
    
    int getAnimationTime();
    
    boolean canBeLearnedBy(final int p0);
    
    boolean isFourthJob();
    
    boolean hasAction();
    
    boolean isTimeLimited();
    
    int getMasterLevel();
    
    Element getElement();
    
    boolean isBeginnerSkill();
    
    boolean hasRequiredSkill();
    
    boolean isInvisible();
    
    boolean isChargeSkill();
    
    int getRequiredSkillLevel();
    
    int getRequiredSkillId();
    
    String getName();
}
