package tools;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import constants.GameConstants;
import client.inventory.MapleInventoryType;
import handling.RecvPacketOpcode;
import handling.SendPacketOpcode;
import client.MapleJob;
import client.ISkill;
import client.SkillFactory;
import server.quest.MapleQuest;
import java.util.Map.Entry;
import server.life.MapleMonsterInformationProvider;
import provider.MapleDataTool;
import provider.MapleData;
import provider.MapleDataProviderFactory;
import server.life.MapleLifeFactory;
import server.MapleItemInformationProvider;
import java.util.TreeMap;
import java.util.Map;

public class SearchGenerator
{
    public static final int 道具;
    public static final int NPC;
    public static final int 地图;
    public static final int 怪物;
    public static final int 任務;
    public static final int 技能;
    public static final int 职业;
    public static final int 伺服器包頭;
    public static final int 用戶端包頭;
    public static final int 发型;
    public static final int 脸型;
    private static final Map<SearchType, Map<Integer, String>> searchs;
    
    public static Map<Integer, String> getSearchs(final int type) {
        return getSearchs(SearchType.valueOf(SearchType.nameOf(type)));
    }
    
    public static Map<Integer, String> getSearchs(final SearchType type) {
        Map<Integer, String> value = null;
        if (SearchGenerator.searchs.containsKey((Object)type)) {
            return (Map<Integer, String>)SearchGenerator.searchs.get((Object)type);
        }
        if ((type == SearchType.脸型 || type == SearchType.发型) && SearchGenerator.searchs.containsKey((Object)SearchType.道具)) {
            value = (Map<Integer, String>)SearchGenerator.searchs.get((Object)SearchType.道具);
        }
        Map<Integer, String> values = new TreeMap<Integer, String>();
        switch (type) {
            case 道具: {
                for (final Pair<Integer, String> itemInfo : MapleItemInformationProvider.getInstance().getAllItems()) {
                    values.put(itemInfo.left, itemInfo.right);
                }
                break;
            }
            case NPC: {
                values = MapleLifeFactory.getNPCNames();
                break;
            }
            case 地图: {
                final MapleData data = MapleDataProviderFactory.getDataProvider("String.wz").getData("Map.img");
                for (final MapleData mapAreaData : data.getChildren()) {
                    for (final MapleData mapIdData : mapAreaData.getChildren()) {
                        values.put(Integer.valueOf(Integer.parseInt(mapIdData.getName())), "'" + MapleDataTool.getString(mapIdData.getChildByPath("streetName"), "無名稱") + " : " + MapleDataTool.getString(mapIdData.getChildByPath("mapName"), "無名稱") + "'");
                    }
                }
                break;
            }
            case 怪物: {
                for (final Entry<Integer, String> mob : MapleMonsterInformationProvider.getInstance().getAllMonsters().entrySet()) {
                    values.put(mob.getKey(), mob.getValue());
                }
                break;
            }
            case 任務: {
                for (final MapleQuest quest : MapleQuest.getAllInstances()) {
                    values.put(Integer.valueOf(quest.getId()), quest.getName());
                }
                break;
            }
            case 技能: {
                for (final ISkill skill : SkillFactory.getAllSkills()) {
                    values.put(Integer.valueOf(skill.getId()), skill.getName());
                }
                break;
            }
            case 职业: {
                for (final MapleJob job : MapleJob.values()) {
                    values.put(Integer.valueOf(job.getId()), job.name());
                }
                break;
            }
            case 伺服器包頭: {
                for (final SendPacketOpcode send : SendPacketOpcode.values()) {
                    values.put(Integer.valueOf((int)send.getValue()), send.name());
                }
                break;
            }
            case 用戶端包頭: {
                for (final RecvPacketOpcode recv : RecvPacketOpcode.values()) {
                    values.put(Integer.valueOf((int)recv.getValue()), recv.name());
                }
                break;
            }
            case 发型:
            case 脸型: {
                if (value == null) {
                    value = new TreeMap<Integer, String>();
                    for (final Pair<Integer, String> itemInfo2 : MapleItemInformationProvider.getInstance().getAllItems()) {
                        value.put(itemInfo2.left, itemInfo2.right);
                    }
                    SearchGenerator.searchs.put(SearchType.道具, value);
                }
                final MapleInventoryType iType = (type == SearchType.发型) ? MapleInventoryType.HAIR : MapleInventoryType.FACE;
                for (final Entry<Integer, String> entry : value.entrySet()) {
                    if (GameConstants.getInventoryType((int)Integer.valueOf(entry.getKey())) == iType) {
                        values.put(entry.getKey(), entry.getValue());
                    }
                }
                break;
            }
        }
        SearchGenerator.searchs.put(type, values);
        return values;
    }
    
    public static Map<Integer, String> getSearchData(final int type, final String search) {
        return getSearchData(SearchType.valueOf(SearchType.nameOf(type)), search);
    }
    
    public static Map<Integer, String> getSearchData(final SearchType type, final String search) {
        final Map<Integer, String> values = new TreeMap<Integer, String>();
        final Map<Integer, String> ss = getSearchs(type);
        final Iterator<Integer> iterator = ss.keySet().iterator();
        while (iterator.hasNext()) {
            final int i = (int)Integer.valueOf(iterator.next());
            if (String.valueOf(i).toLowerCase().contains((CharSequence)search.toLowerCase()) || ((String)ss.get((Object)Integer.valueOf(i))).toLowerCase().contains((CharSequence)search.toLowerCase())) {
                values.put(Integer.valueOf(i), ss.get((Object)Integer.valueOf(i)));
            }
        }
        return values;
    }
    
    public static String searchData(final int type, final String search) {
        return searchData(SearchType.valueOf(SearchType.nameOf(type)), search);
    }
    
    public static String searchData(final SearchType type, final String search) {
        final Map<Integer, String> ss = getSearchData(type, search);
        final List<String> ret = new ArrayList<String>();
        final StringBuilder sb = new StringBuilder();
        switch (type) {
            case 道具: {
                for (final Integer i : ss.keySet()) {
                    if (MapleItemInformationProvider.getInstance().itemExists((int)i)) {
                        ret.add("\r\n#L" + (Object)i + "##z" + (Object)i + "# (" + (Object)i + ")#l");
                    }
                }
                break;
            }
            case NPC: {
                for (final Entry<Integer, String> j : ss.entrySet()) {
                    ret.add("\r\n#L" + (Object)j.getKey() + "##p" + (Object)j.getKey() + "#(" + (Object)j.getKey() + ")#l");
                }
                break;
            }
            case 地图: {
                for (final Integer i : ss.keySet()) {
                    ret.add("\r\n#L" + (Object)i + "##m" + (Object)i + "#(" + (Object)i + ")#l");
                }
                break;
            }
            case 怪物: {
                for (final Integer i : ss.keySet()) {
                    ret.add("\r\n#L" + (Object)i + "##o" + (Object)i + "#(" + (Object)i + ")#l");
                }
                break;
            }
            case 任務: {
                for (final Entry<Integer, String> j : ss.entrySet()) {
                    ret.add("\r\n#L" + (Object)j.getKey() + "#" + (String)j.getValue() + "(" + (Object)j.getKey() + ")#l");
                }
                break;
            }
            case 技能: {
                for (final Entry<Integer, String> j : ss.entrySet()) {
                    ret.add("\r\n#L" + (Object)j.getKey() + "##s" + (Object)j.getKey() + "#" + (String)j.getValue() + "(" + (Object)j.getKey() + ")#l");
                }
                break;
            }
            case 职业: {
                for (final Entry<Integer, String> j : ss.entrySet()) {
                    ret.add("\r\n#L" + (Object)j.getKey() + "#" + (String)j.getValue() + "(" + (Object)j.getKey() + ")#l");
                }
                break;
            }
            case 伺服器包頭:
            case 用戶端包頭: {
                for (final Entry<Integer, String> j : ss.entrySet()) {
                    ret.add("\r\n" + (String)j.getValue() + " 值: " + (Object)j.getKey() + " 16進制: " + HexTool.getOpcodeToString((int)Integer.valueOf(j.getKey())));
                }
                break;
            }
            default: {
                sb.append("對不起, 這個檢索類型不被支援");
                break;
            }
        }
        if (ret.size() > 0) {
            for (final String singleRetItem : ret) {
                if (sb.length() > 3500) {
                    sb.append("\r\n後面還有很多搜尋結果, 但已經無法顯示更多");
                    break;
                }
                sb.append(singleRetItem);
            }
        }
        final StringBuilder sbs = new StringBuilder();
        if (!sb.toString().isEmpty() && !sb.toString().equalsIgnoreCase("對不起, 這個檢索指令不被支援")) {
            sbs.append("<<類型: ").append(type.name()).append(" | 搜尋信息: ").append(search).append(">>");
        }
        sbs.append((CharSequence)sb);
        if (sbs.toString().isEmpty()) {
            sbs.append("搜尋不到此").append(type.name());
        }
        return sbs.toString();
    }
    
    public static boolean foundData(final int type, final String search) {
        return !getSearchData(type, search).isEmpty();
    }
    
    static {
        道具 = SearchType.道具.getValue();
        NPC = SearchType.NPC.getValue();
        地图 = SearchType.地图.getValue();
        怪物 = SearchType.怪物.getValue();
        任務 = SearchType.任務.getValue();
        技能 = SearchType.技能.getValue();
        职业 = SearchType.职业.getValue();
        伺服器包頭 = SearchType.伺服器包頭.getValue();
        用戶端包頭 = SearchType.用戶端包頭.getValue();
        发型 = SearchType.发型.getValue();
        脸型 = SearchType.脸型.getValue();
        searchs = new HashMap<SearchType, Map<Integer, String>>();
    }
    
    public enum SearchType
    {
        道具(1), 
        NPC(2), 
        地图(3), 
        怪物(4), 
        任務(5), 
        技能(6), 
        职业(7), 
        伺服器包頭(8), 
        用戶端包頭(9), 
        发型(10), 
        脸型(11), 
        未知;
        
        private int value;
        
        private SearchType() {
            this.value = 0;
        }
        
        private SearchType(final int value) {
            this.value = value;
        }
        
        public final int getValue() {
            return this.value;
        }
        
        public static String nameOf(final int value) {
            for (final SearchType type : values()) {
                if (type.getValue() == value) {
                    return type.name();
                }
            }
            return "未知";
        }
    }
}
