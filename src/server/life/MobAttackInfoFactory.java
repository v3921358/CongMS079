package server.life;

import java.util.HashMap;
import provider.MapleDataProviderFactory;
import provider.MapleData;
import provider.MapleDataTool;
import tools.StringUtil;
import tools.Pair;
import java.util.Map;
import provider.MapleDataProvider;

public class MobAttackInfoFactory
{
    private static final MobAttackInfoFactory instance;
    private static final MapleDataProvider dataSource;
    private static final Map<Pair<Integer, Integer>, MobAttackInfo> mobAttacks;
    
    public static MobAttackInfoFactory getInstance() {
        return MobAttackInfoFactory.instance;
    }
    
    public MobAttackInfo getMobAttackInfo(final MapleMonster mob, final int attack) {
        MobAttackInfo ret = (MobAttackInfo)MobAttackInfoFactory.mobAttacks.get((Object)new Pair((Object)Integer.valueOf(mob.getId()), (Object)Integer.valueOf(attack)));
        if (ret != null) {
            return ret;
        }
        MapleData mobData = MobAttackInfoFactory.dataSource.getData(StringUtil.getLeftPaddedStr(Integer.toString(mob.getId()) + ".img", '0', 11));
        if (mobData != null) {
            final MapleData infoData = mobData.getChildByPath("info/link");
            if (infoData != null) {
                final String linkedmob = MapleDataTool.getString("info/link", mobData);
                mobData = MobAttackInfoFactory.dataSource.getData(StringUtil.getLeftPaddedStr(linkedmob + ".img", '0', 11));
            }
            final MapleData attackData = mobData.getChildByPath("attack" + (attack + 1) + "/info");
            if (attackData != null) {
                ret = new MobAttackInfo();
                ret.setDeadlyAttack(attackData.getChildByPath("deadlyAttack") != null);
                ret.setMpBurn(MapleDataTool.getInt("mpBurn", attackData, 0));
                ret.setDiseaseSkill(MapleDataTool.getInt("disease", attackData, 0));
                ret.setDiseaseLevel(MapleDataTool.getInt("level", attackData, 0));
                ret.setMpCon(MapleDataTool.getInt("conMP", attackData, 0));
            }
        }
        MobAttackInfoFactory.mobAttacks.put(new Pair<Integer, Integer>(Integer.valueOf(mob.getId()), Integer.valueOf(attack)), ret);
        return ret;
    }
    
    static {
        instance = new MobAttackInfoFactory();
        dataSource = MapleDataProviderFactory.getDataProvider("Mob.wz");
        mobAttacks = new HashMap<Pair<Integer, Integer>, MobAttackInfo>();
    }
}
