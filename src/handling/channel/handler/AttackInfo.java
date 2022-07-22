package handling.channel.handler;

import tools.FileoutputUtil;
import server.AutobanManager;
import client.SkillFactory;
import constants.GameConstants;
import server.MapleStatEffect;
import client.ISkill;
import client.MapleCharacter;
import java.awt.Point;
import tools.AttackPair;
import java.util.List;

public class AttackInfo
{
    public int skill;
    public int charge;
    public int lastAttackTickCount;
    public List<AttackPair> allDamage;
    public Point position;
    public Point positionxy;
    public byte hits;
    public byte targets;
    public byte tbyte;
    public byte display;
    public byte animation;
    public byte speed;
    public byte csstar;
    public byte AOE;
    public byte slot;
    public byte unk;
    public boolean real;
    
    public AttackInfo() {
        this.real = true;
    }
    
    public final MapleStatEffect getAttackEffect(final MapleCharacter chr, int skillLevel, final ISkill skill_) {
        if (GameConstants.isMulungSkill(this.skill) || GameConstants.isPyramidSkill(this.skill)) {
            skillLevel = 1;
        }
        else if (skillLevel <= 0) {
            return null;
        }
        if (GameConstants.isLinkedSkill(this.skill)) {
            final ISkill skillLink = SkillFactory.getSkill(this.skill);
            if (this.display > 80 && !skillLink.hasAction()) {
                AutobanManager.getInstance().autoban(chr.getClient(), "攻击无延迟，技能ID： : " + this.skill);
                return null;
            }
            return skillLink.getEffect(skillLevel);
        }
        else {
            if (this.skill != skill_.getId()) {
                FileoutputUtil.logToFile("logs/Data/AttackEffect.txt", "" + FileoutputUtil.NowTime() + " 連結技能[" + this.skill + "](" + skill_.getId() + "傳承) 連結技能等級:" + skillLevel + " 不在getLinkedkill清單內卻被觸發, 觸發者: " + chr.getName() + " 职业: " + (int)chr.getJob() + " 等級: " + (int)chr.getLevel() + "\r\n");
            }
            if (this.display > 80 && !skill_.hasAction()) {
                AutobanManager.getInstance().autoban(chr.getClient(), "攻击無延遲，技能ID： " + this.skill);
                return null;
            }
            return skill_.getEffect(skillLevel);
        }
    }
}
