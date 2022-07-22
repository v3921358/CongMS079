package server.maps;

import java.util.HashMap;
import provider.MapleDataProviderFactory;
import provider.MapleData;
import tools.Pair;
import provider.MapleDataTool;
import tools.StringUtil;
import java.util.Map;
import provider.MapleDataProvider;

public class MapleReactorFactory
{
    private static final MapleDataProvider data;
    private static final Map<Integer, MapleReactorStats> reactorStats;
    
    public static final MapleReactorStats getReactor(final int rid) {
        MapleReactorStats stats = (MapleReactorStats)MapleReactorFactory.reactorStats.get((Object)Integer.valueOf(rid));
        if (stats == null) {
            int infoId = rid;
            MapleData reactorData = MapleReactorFactory.data.getData(StringUtil.getLeftPaddedStr(Integer.toString(infoId) + ".img", '0', 11));
            final MapleData link = reactorData.getChildByPath("info/link");
            if (link != null) {
                infoId = MapleDataTool.getIntConvert("info/link", reactorData);
                stats = (MapleReactorStats)MapleReactorFactory.reactorStats.get((Object)Integer.valueOf(infoId));
            }
            if (stats == null) {
                stats = new MapleReactorStats();
                reactorData = MapleReactorFactory.data.getData(StringUtil.getLeftPaddedStr(Integer.toString(infoId) + ".img", '0', 11));
                if (reactorData == null) {
                    return stats;
                }
                boolean areaSet = false;
                boolean foundState = false;
                byte i = 0;
                while (true) {
                    final MapleData reactorD = reactorData.getChildByPath(String.valueOf((int)i));
                    if (reactorD == null) {
                        break;
                    }
                    final MapleData reactorInfoData_ = reactorD.getChildByPath("event");
                    if (reactorInfoData_ != null && reactorInfoData_.getChildByPath("0") != null) {
                        final MapleData reactorInfoData = reactorInfoData_.getChildByPath("0");
                        Pair<Integer, Integer> reactItem = null;
                        final int type = MapleDataTool.getIntConvert("type", reactorInfoData);
                        if (type == 100) {
                            reactItem = new Pair<Integer, Integer>(Integer.valueOf(MapleDataTool.getIntConvert("0", reactorInfoData)), Integer.valueOf(MapleDataTool.getIntConvert("1", reactorInfoData, 1)));
                            if (!areaSet) {
                                stats.setTL(MapleDataTool.getPoint("lt", reactorInfoData));
                                stats.setBR(MapleDataTool.getPoint("rb", reactorInfoData));
                                areaSet = true;
                            }
                        }
                        foundState = true;
                        stats.addState(i, type, reactItem, (byte)MapleDataTool.getIntConvert("state", reactorInfoData), MapleDataTool.getIntConvert("timeOut", reactorInfoData_, -1));
                    }
                    else {
                        stats.addState(i, 999, null, (byte)(foundState ? -1 : (i + 1)), 0);
                    }
                    ++i;
                }
                MapleReactorFactory.reactorStats.put(Integer.valueOf(infoId), stats);
                if (rid != infoId) {
                    MapleReactorFactory.reactorStats.put(Integer.valueOf(rid), stats);
                }
            }
            else {
                MapleReactorFactory.reactorStats.put(Integer.valueOf(rid), stats);
            }
        }
        return stats;
    }
    
    static {
        data = MapleDataProviderFactory.getDataProvider("Reactor.wz");
        reactorStats = new HashMap<Integer, MapleReactorStats>();
    }
}
