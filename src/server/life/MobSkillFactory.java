package server.life;

import provider.MapleDataProviderFactory;
import java.util.HashMap;
import java.util.List;
import java.awt.Point;
import provider.MapleDataTool;
import java.util.ArrayList;
import provider.MapleData;
import provider.MapleDataProvider;
import tools.Pair;
import java.util.Map;

public class MobSkillFactory
{
    private static final Map<Pair<Integer, Integer>, MobSkill> mobSkills;
    private static final MapleDataProvider dataSource;
    private static final MapleData skillRoot;
    
    public static MobSkill getMobSkill(final int skillId, final int level) {
        MobSkill ret = (MobSkill)MobSkillFactory.mobSkills.get((Object)new Pair((Object)Integer.valueOf(skillId), (Object)Integer.valueOf(level)));
        if (ret != null) {
            return ret;
        }
        final MapleData skillData = MobSkillFactory.skillRoot.getChildByPath(skillId + "/level/" + level);
        if (skillData != null && skillData.getChildren() != null) {
            final List<Integer> toSummon = new ArrayList<Integer>();
            for (int i = 0; i > -1 && skillData.getChildByPath(String.valueOf(i)) != null; ++i) {
                toSummon.add(Integer.valueOf(MapleDataTool.getInt(skillData.getChildByPath(String.valueOf(i)), 0)));
            }
            final MapleData ltd = skillData.getChildByPath("lt");
            Point lt = null;
            Point rb = null;
            if (ltd != null) {
                lt = (Point)ltd.getData();
                rb = (Point)skillData.getChildByPath("rb").getData();
            }
            ret = new MobSkill(skillId, level);
            ret.addSummons(toSummon);
            ret.setCoolTime((long)(MapleDataTool.getInt("interval", skillData, 0) * 1000));
            ret.setDuration((long)(MapleDataTool.getInt("time", skillData, 1) * 1000));
            ret.setHp(MapleDataTool.getInt("hp", skillData, 100));
            ret.setMpCon(MapleDataTool.getInt(skillData.getChildByPath("mpCon"), 0));
            ret.setSpawnEffect(MapleDataTool.getInt("summonEffect", skillData, 0));
            ret.setX(MapleDataTool.getInt("x", skillData, 1));
            ret.setY(MapleDataTool.getInt("y", skillData, 1));
            ret.setProp((float)MapleDataTool.getInt("prop", skillData, 100) / 100.0f);
            ret.setLimit((short)MapleDataTool.getInt("limit", skillData, 0));
            ret.setLtRb(lt, rb);
            ret.setOnce(MapleDataTool.getInt("once", skillData, 0) > 0);
            MobSkillFactory.mobSkills.put(new Pair<Integer, Integer>(Integer.valueOf(skillId), Integer.valueOf(level)), ret);
        }
        return ret;
    }
    
    static {
        mobSkills = new HashMap<Pair<Integer, Integer>, MobSkill>();
        dataSource = MapleDataProviderFactory.getDataProvider("Skill.wz");
        skillRoot = MobSkillFactory.dataSource.getData("MobSkill.img");
    }
}
