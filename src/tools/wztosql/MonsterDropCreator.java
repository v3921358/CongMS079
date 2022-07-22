package tools.wztosql;

import tools.StringUtil;
import provider.MapleDataProvider;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.MBeanRegistrationException;
import javax.management.InstanceAlreadyExistsException;
import java.rmi.NotBoundException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Iterator;
import provider.MapleDataTool;
import provider.MapleData;
import java.util.Map.Entry;
import java.io.FileOutputStream;
import provider.MapleDataProviderFactory;
import java.util.Map;
import tools.Pair;
import java.util.List;

public class MonsterDropCreator
{
    private static final int COMMON_ETC_RATE = 600000;
    private static final int SUPER_BOSS_ITEM_RATE = 300000;
    private static final int POTION_RATE = 20000;
    private static final int ARROWS_RATE = 25000;
    private static int lastmonstercardid;
    private static boolean addFlagData;
    protected static String monsterQueryData;
    protected static List<Pair<Integer, String>> itemNameCache;
    protected static List<Pair<Integer, MobInfo>> mobCache;
    protected static Map<Integer, Boolean> bossCache;
    
    public static void main(final String[] args) throws FileNotFoundException, IOException, NotBoundException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, MalformedObjectNameException {
        final MapleData data = MapleDataProviderFactory.getDataProvider("String.wz").getData("MonsterBook.img");
        System.out.println("准备提取数据!");
        System.out.println("按任意鍵繼續...");
        final long currtime = System.currentTimeMillis();
        MonsterDropCreator.addFlagData = false;
        System.out.println("載入: 物品名稱.");
        getAllItems();
        System.out.println("載入: 怪物数据.");
        getAllMobs();
        final StringBuilder sb = new StringBuilder();
        final FileOutputStream out = new FileOutputStream("mobDrop.sql", true);
        for (final Entry<Integer, List<Integer>> e : getDropsNotInMonsterBook().entrySet()) {
            boolean first = true;
            sb.append("INSERT INTO ").append(MonsterDropCreator.monsterQueryData).append(" VALUES ");
            for (final Integer monsterdrop : (List<Integer>)e.getValue()) {
                final int itemid = (int)monsterdrop;
                final int monsterId = (int)Integer.valueOf(e.getKey());
                int rate = getChance(itemid, monsterId, MonsterDropCreator.bossCache.containsKey((Object)Integer.valueOf(monsterId)));
                if (rate <= 100000) {
                    switch (monsterId) {
                        case 9400121: {
                            rate *= 5;
                            break;
                        }
                        case 9400112:
                        case 9400113:
                        case 9400300: {
                            rate *= 10;
                            break;
                        }
                    }
                }
                for (int i = 0; i < multipleDropsIncrement(itemid, monsterId); ++i) {
                    if (first) {
                        sb.append("(DEFAULT, ");
                        first = false;
                    }
                    else {
                        sb.append(", (DEFAULT, ");
                    }
                    sb.append(monsterId).append(", ");
                    if (MonsterDropCreator.addFlagData) {
                        sb.append("'', ");
                    }
                    sb.append(itemid).append(", ");
                    sb.append("1, 1,");
                    sb.append("0, ");
                    final int num = IncrementRate(itemid, i);
                    sb.append((num == -1) ? rate : num);
                    sb.append(")");
                    first = false;
                }
                sb.append("\n");
                sb.append("-- Name : ");
                retriveNLogItemName(sb, itemid);
                sb.append("\n");
            }
            sb.append(";");
            sb.append("\n");
            out.write(sb.toString().getBytes());
            sb.delete(0, Integer.MAX_VALUE);
        }
        System.out.println("載入: Drops from String.wz/MonsterBook.img.");
        for (final MapleData dataz : data.getChildren()) {
            int idtoLog;
            final int monsterId2 = idtoLog = Integer.parseInt(dataz.getName());
            boolean first2 = true;
            if (monsterId2 == 9400408) {
                idtoLog = 9400409;
            }
            if (dataz.getChildByPath("reward").getChildren().size() > 0) {
                sb.append("INSERT INTO ").append(MonsterDropCreator.monsterQueryData).append(" VALUES ");
                for (final MapleData drop : dataz.getChildByPath("reward")) {
                    final int itemid2 = MapleDataTool.getInt(drop);
                    int rate2 = getChance(itemid2, idtoLog, MonsterDropCreator.bossCache.containsKey((Object)Integer.valueOf(idtoLog)));
                    for (final Pair<Integer, MobInfo> Pair : MonsterDropCreator.mobCache) {
                        if ((int)Integer.valueOf(Pair.getLeft()) == monsterId2) {
                            if (((MobInfo)Pair.getRight()).getBoss() <= 0) {
                                break;
                            }
                            if (rate2 > 100000) {
                                break;
                            }
                            if (((MobInfo)Pair.getRight()).rateItemDropLevel() == 2) {
                                rate2 *= 10;
                                break;
                            }
                            if (((MobInfo)Pair.getRight()).rateItemDropLevel() == 3) {
                                switch (monsterId2) {
                                    case 8810018: {
                                        rate2 *= 48;
                                    }
                                    case 8800002: {
                                        rate2 *= 45;
                                        break;
                                    }
                                    default: {
                                        rate2 *= 30;
                                        break;
                                    }
                                }
                            }
                            switch (monsterId2) {
                                case 8860010:
                                case 9400265:
                                case 9400270:
                                case 9400273: {
                                    rate2 *= 10;
                                    continue;
                                }
                                case 9400294: {
                                    rate2 *= 24;
                                    continue;
                                }
                                case 9420522: {
                                    rate2 *= 29;
                                    continue;
                                }
                                case 9400409: {
                                    rate2 *= 35;
                                    continue;
                                }
                                case 9400287: {
                                    rate2 *= 60;
                                    continue;
                                }
                                default: {
                                    rate2 *= 10;
                                    continue;
                                }
                            }
                        }
                    }
                    for (int j = 0; j < multipleDropsIncrement(itemid2, idtoLog); ++j) {
                        if (first2) {
                            sb.append("(DEFAULT, ");
                            first2 = false;
                        }
                        else {
                            sb.append(", (DEFAULT, ");
                        }
                        sb.append(idtoLog).append(", ");
                        if (MonsterDropCreator.addFlagData) {
                            sb.append("'', ");
                        }
                        sb.append(itemid2).append(", ");
                        sb.append("1, 1,");
                        sb.append("0, ");
                        final int num2 = IncrementRate(itemid2, j);
                        sb.append((num2 == -1) ? rate2 : num2);
                        sb.append(")");
                        first2 = false;
                    }
                    sb.append("\n");
                    sb.append("-- Name : ");
                    retriveNLogItemName(sb, itemid2);
                    sb.append("\n");
                }
                sb.append(";");
            }
            sb.append("\n");
            out.write(sb.toString().getBytes());
            sb.delete(0, Integer.MAX_VALUE);
        }
        System.out.println("載入: 怪物書数据.");
        final StringBuilder SQL = new StringBuilder();
        final StringBuilder bookName = new StringBuilder();
        for (final Pair<Integer, String> Pair2 : MonsterDropCreator.itemNameCache) {
            if ((int)Integer.valueOf(Pair2.getLeft()) >= 2380000 && (int)Integer.valueOf(Pair2.getLeft()) <= MonsterDropCreator.lastmonstercardid) {
                bookName.append((String)Pair2.getRight());
                if (bookName.toString().contains((CharSequence)" Card")) {
                    bookName.delete(bookName.length() - 5, bookName.length());
                }
                for (final Pair Pair_ : MonsterDropCreator.mobCache) {
                    if (((MobInfo)Pair_.getRight()).getName().equalsIgnoreCase(bookName.toString())) {
                        int rate3 = 1000;
                        if (((MobInfo)Pair_.getRight()).getBoss() > 0) {
                            rate3 *= 25;
                        }
                        SQL.append("INSERT INTO ").append(MonsterDropCreator.monsterQueryData).append(" VALUES ");
                        SQL.append("(DEFAULT, ");
                        SQL.append(Pair_.getLeft()).append(", ");
                        if (MonsterDropCreator.addFlagData) {
                            sb.append("'', ");
                        }
                        SQL.append(Pair2.getLeft()).append(", ");
                        SQL.append("1, 1,");
                        SQL.append("0, ");
                        SQL.append(rate3);
                        SQL.append(");\n");
                        SQL.append("-- 物品名 : ").append((String)Pair2.getRight()).append("\n");
                        break;
                    }
                }
                bookName.delete(0, Integer.MAX_VALUE);
            }
        }
        System.out.println("載入: 怪物卡数据.");
        SQL.append("\n");
        int k = 1;
        int lastmonsterbookid = 0;
        for (final Pair<Integer, String> Pair3 : MonsterDropCreator.itemNameCache) {
            if ((int)Integer.valueOf(Pair3.getLeft()) >= 2380000 && (int)Integer.valueOf(Pair3.getLeft()) <= MonsterDropCreator.lastmonstercardid) {
                bookName.append((String)Pair3.getRight());
                if (bookName.toString().contains((CharSequence)" Card")) {
                    bookName.delete(bookName.length() - 5, bookName.length());
                }
                if ((int)Integer.valueOf(Pair3.getLeft()) == lastmonsterbookid) {
                    continue;
                }
                for (final Pair Pair_2 : MonsterDropCreator.mobCache) {
                    if (((MobInfo)Pair_2.getRight()).getName().equalsIgnoreCase(bookName.toString())) {
                        SQL.append("INSERT INTO ").append("monstercarddata").append(" VALUES (");
                        SQL.append(k).append(", ");
                        SQL.append(Pair3.getLeft());
                        SQL.append(", ");
                        SQL.append(Pair_2.getLeft()).append(");\n");
                        lastmonsterbookid = (int)Integer.valueOf(Pair3.getLeft());
                        ++k;
                        break;
                    }
                }
                bookName.delete(0, Integer.MAX_VALUE);
            }
        }
        out.write(SQL.toString().getBytes());
        out.close();
        long time = System.currentTimeMillis() - currtime;
        time /= 1000L;
        System.out.println("Time taken : " + time);
    }
    
    private static void retriveNLogItemName(final StringBuilder sb, final int id) {
        for (final Pair<Integer, String> Pair : MonsterDropCreator.itemNameCache) {
            if ((int)Integer.valueOf(Pair.getLeft()) == id) {
                sb.append((String)Pair.getRight());
                return;
            }
        }
        sb.append("MISSING STRING, ID : ");
        sb.append(id);
    }
    
    private static int IncrementRate(final int itemid, final int times) {
        if (times == 0) {
            if (itemid == 1002357 || itemid == 1002926 || itemid == 1002927) {
                return 999999;
            }
            if (itemid == 1122000) {
                return 999999;
            }
            if (itemid == 1002972) {
                return 999999;
            }
        }
        else if (times == 1) {
            if (itemid == 1002357 || itemid == 1002926 || itemid == 1002927) {
                return 999999;
            }
            if (itemid == 1122000) {
                return 999999;
            }
            if (itemid == 1002972) {
                return 300000;
            }
        }
        else if (times == 2) {
            if (itemid == 1002357 || itemid == 1002926 || itemid == 1002927) {
                return 300000;
            }
            if (itemid == 1122000) {
                return 300000;
            }
        }
        else if (times == 3) {
            if (itemid == 1002357 || itemid == 1002926 || itemid == 1002927) {
                return 300000;
            }
        }
        else if (times == 4 && (itemid == 1002357 || itemid == 1002926 || itemid == 1002927)) {
            return 300000;
        }
        return -1;
    }
    
    private static int multipleDropsIncrement(final int itemid, final int mobid) {
        switch (itemid) {
            case 1002357:
            case 1002390:
            case 1002430:
            case 1002926:
            case 1002927: {
                return 5;
            }
            case 1122000: {
                return 4;
            }
            case 4021010: {
                return 7;
            }
            case 1002972: {
                return 2;
            }
            case 4000172: {
                if (mobid == 7220001) {
                    return 8;
                }
                return 1;
            }
            case 4000000:
            case 4000003:
            case 4000005:
            case 4000016:
            case 4000018:
            case 4000019:
            case 4000021:
            case 4000026:
            case 4000029:
            case 4000031:
            case 4000032:
            case 4000033:
            case 4000043:
            case 4000044:
            case 4000073:
            case 4000074:
            case 4000113:
            case 4000114:
            case 4000115:
            case 4000117:
            case 4000118:
            case 4000119:
            case 4000166:
            case 4000167:
            case 4000195:
            case 4000268:
            case 4000269:
            case 4000270:
            case 4000283:
            case 4000284:
            case 4000285:
            case 4000289:
            case 4000298:
            case 4000329:
            case 4000330:
            case 4000331:
            case 4000356:
            case 4000364:
            case 4000365: {
                if (mobid == 2220000 || mobid == 3220000 || mobid == 3220001 || mobid == 4220000 || mobid == 5220000 || mobid == 5220002 || mobid == 5220003 || mobid == 6220000 || mobid == 4000119 || mobid == 7220000 || mobid == 7220002 || mobid == 8220000 || mobid == 8220002 || mobid == 8220003) {
                    return 3;
                }
                return 1;
            }
            default: {
                return 1;
            }
        }
    }
    
    private static int getChance(final int id, final int mobid, final boolean boss) {
    	
    	switch (id / 10000) {
        case 100:
          switch (id) {
            case 1002357:
            case 1002390:
            case 1002430:
            case 1002905:
            case 1002906:
            case 1002926:
            case 1002927:
            case 1002972:
              return 300000;
          } 
          return 900;
        case 103:
          switch (id) {
            case 1032062:
              return 100;
          } 
          return 1000;
        case 105:
        case 109:
          switch (id) {
            case 1092049:
              return 100;
          } 
          return 700;
        case 104:
        case 106:
        case 107:
          switch (id) {
            case 1072369:
              return 300000;
          } 
          return 800;
        case 108:
        case 110:
          return 1000;
        case 112:
          switch (id) {
            case 1122000:
              return 300000;
            case 1122011:
            case 1122012:
              return 800000;
          } 
        case 130:
        case 131:
        case 132:
        case 137:
          switch (id) {
            case 1372049:
              return 999999;
          } 
          return 700;
        case 138:
        case 140:
        case 141:
        case 142:
        case 144:
          return 700;
        case 133:
        case 143:
        case 145:
        case 146:
        case 147:
        case 148:
        case 149:
          return 500;
        case 204:
          switch (id) {
            case 2049000:
              return 150;
          } 
          return 300;
        case 205:
          return 50000;
        case 206:
          return 30000;
        case 228:
          return 30000;
        case 229:
          switch (id) {
            case 2290096:
              return 800000;
            case 2290125:
              return 100000;
          } 
          return 500;
        case 233:
          switch (id) {
            case 2330007:
              return 50;
          } 
          return 500;
        case 400:
          switch (id) {
            case 4000021:
              return 50000;
            case 4001094:
              return 999999;
            case 4001000:
              return 5000;
            case 4000157:
              return 100000;
            case 4001023:
            case 4001024:
              return 999999;
            case 4000244:
            case 4000245:
              return 2000;
            case 4001005:
              return 5000;
            case 4001006:
              return 10000;
            case 4000017:
            case 4000082:
              return 40000;
            case 4000446:
            case 4000451:
            case 4000456:
              return 10000;
            case 4000459:
              return 20000;
            case 4000030:
              return 60000;
            case 4000339:
              return 70000;
            case 4000313:
            case 4007000:
            case 4007001:
            case 4007002:
            case 4007003:
            case 4007004:
            case 4007005:
            case 4007006:
            case 4007007:
            case 4031456:
              return 100000;
            case 4001126:
              return 500000;
          } 
          switch (id / 1000) {
            case 4000:
            case 4001:
              return 600000;
            case 4003:
              return 200000;
            case 4004:
            case 4006:
              return 10000;
            case 4005:
              return 1000;
          } 
        case 401:
        case 402:
          switch (id) {
            case 4020009:
              return 5000;
            case 4021010:
              return 300000;
          } 
          return 9000;
        case 403:
          switch (id) {
            case 4032024:
              return 50000;
            case 4032181:
              return boss ? 999999 : 300000;
            case 4032025:
            case 4032155:
            case 4032156:
            case 4032159:
            case 4032161:
            case 4032163:
              return 600000;
            case 4032166:
            case 4032167:
            case 4032168:
              return 10000;
            case 4032151:
            case 4032158:
            case 4032164:
            case 4032180:
              return 2000;
            case 4032152:
            case 4032153:
            case 4032154:
              return 4000;
          } 
          return 300;
        case 413:
          return 6000;
        case 416:
          return 6000;
      } 
      switch (id / 1000000) {
        case 1:
          return 999999;
        case 2:
          switch (id) {
            case 2000004:
            case 2000005:
              return boss ? 999999 : 20000;
            case 2000006:
              return (mobid == 9420540) ? 50000 : (boss ? 999999 : 20000);
            case 2022345:
              return boss ? 999999 : 3000;
            case 2012002:
              return 6000;
            case 2020013:
            case 2020015:
              return boss ? 999999 : 20000;
            case 2060000:
            case 2060001:
            case 2061000:
            case 2061001:
              return 25000;
            case 2070000:
            case 2070001:
            case 2070002:
            case 2070003:
            case 2070004:
            case 2070008:
            case 2070009:
            case 2070010:
              return 500;
            case 2070005:
              return 400;
            case 2070006:
            case 2070007:
              return 200;
            case 2070012:
            case 2070013:
              return 1500;
            case 2070019:
              return 100;
            case 2210006:
              return 999999;
          } 
          return 20000;
        case 3:
          switch (id) {
            case 3010007:
            case 3010008:
              return 500;
          } 
          return 2000;
      } 
      System.out.println("ID : " + id);
      return 999999;
    }
    
    private static Map<Integer, List<Integer>> getDropsNotInMonsterBook() {
        final Map drops = new HashMap();
        List IndiviualMonsterDrop = new ArrayList();
        IndiviualMonsterDrop.add(Integer.valueOf(4000139));
        IndiviualMonsterDrop.add(Integer.valueOf(2002011));
        IndiviualMonsterDrop.add(Integer.valueOf(2002011));
        IndiviualMonsterDrop.add(Integer.valueOf(2002011));
        IndiviualMonsterDrop.add(Integer.valueOf(2000004));
        IndiviualMonsterDrop.add(Integer.valueOf(2000004));
        drops.put(Integer.valueOf(9400112), IndiviualMonsterDrop);
        IndiviualMonsterDrop = new ArrayList();
        IndiviualMonsterDrop.add(Integer.valueOf(4000140));
        IndiviualMonsterDrop.add(Integer.valueOf(2022027));
        IndiviualMonsterDrop.add(Integer.valueOf(2022027));
        IndiviualMonsterDrop.add(Integer.valueOf(2000004));
        IndiviualMonsterDrop.add(Integer.valueOf(2000004));
        IndiviualMonsterDrop.add(Integer.valueOf(2002008));
        IndiviualMonsterDrop.add(Integer.valueOf(2002008));
        drops.put(Integer.valueOf(9400113), IndiviualMonsterDrop);
        IndiviualMonsterDrop = new ArrayList();
        IndiviualMonsterDrop.add(Integer.valueOf(4000141));
        IndiviualMonsterDrop.add(Integer.valueOf(2000004));
        IndiviualMonsterDrop.add(Integer.valueOf(2040813));
        IndiviualMonsterDrop.add(Integer.valueOf(2041030));
        IndiviualMonsterDrop.add(Integer.valueOf(2041040));
        IndiviualMonsterDrop.add(Integer.valueOf(1072238));
        IndiviualMonsterDrop.add(Integer.valueOf(1032026));
        IndiviualMonsterDrop.add(Integer.valueOf(1372011));
        drops.put(Integer.valueOf(9400300), IndiviualMonsterDrop);
        IndiviualMonsterDrop = new ArrayList();
        IndiviualMonsterDrop.add(Integer.valueOf(4000225));
        IndiviualMonsterDrop.add(Integer.valueOf(2000006));
        IndiviualMonsterDrop.add(Integer.valueOf(2000004));
        IndiviualMonsterDrop.add(Integer.valueOf(2070013));
        IndiviualMonsterDrop.add(Integer.valueOf(2002005));
        IndiviualMonsterDrop.add(Integer.valueOf(2022018));
        IndiviualMonsterDrop.add(Integer.valueOf(2040306));
        IndiviualMonsterDrop.add(Integer.valueOf(2043704));
        IndiviualMonsterDrop.add(Integer.valueOf(2044605));
        IndiviualMonsterDrop.add(Integer.valueOf(2041034));
        IndiviualMonsterDrop.add(Integer.valueOf(1032019));
        IndiviualMonsterDrop.add(Integer.valueOf(1102013));
        IndiviualMonsterDrop.add(Integer.valueOf(1322026));
        IndiviualMonsterDrop.add(Integer.valueOf(1092015));
        IndiviualMonsterDrop.add(Integer.valueOf(1382016));
        IndiviualMonsterDrop.add(Integer.valueOf(1002276));
        IndiviualMonsterDrop.add(Integer.valueOf(1002403));
        IndiviualMonsterDrop.add(Integer.valueOf(1472027));
        drops.put(Integer.valueOf(9400013), IndiviualMonsterDrop);
        IndiviualMonsterDrop = new ArrayList();
        IndiviualMonsterDrop.add(Integer.valueOf(1372049));
        drops.put(Integer.valueOf(8800002), IndiviualMonsterDrop);
        IndiviualMonsterDrop = new ArrayList();
        IndiviualMonsterDrop.add(Integer.valueOf(4001094));
        IndiviualMonsterDrop.add(Integer.valueOf(2290125));
        drops.put(Integer.valueOf(8810018), IndiviualMonsterDrop);
        IndiviualMonsterDrop = new ArrayList();
        IndiviualMonsterDrop.add(Integer.valueOf(4000138));
        IndiviualMonsterDrop.add(Integer.valueOf(4010006));
        IndiviualMonsterDrop.add(Integer.valueOf(2000006));
        IndiviualMonsterDrop.add(Integer.valueOf(2000011));
        IndiviualMonsterDrop.add(Integer.valueOf(2020016));
        IndiviualMonsterDrop.add(Integer.valueOf(2022024));
        IndiviualMonsterDrop.add(Integer.valueOf(2022026));
        IndiviualMonsterDrop.add(Integer.valueOf(2043705));
        IndiviualMonsterDrop.add(Integer.valueOf(2040716));
        IndiviualMonsterDrop.add(Integer.valueOf(2040908));
        IndiviualMonsterDrop.add(Integer.valueOf(2040510));
        IndiviualMonsterDrop.add(Integer.valueOf(1072239));
        IndiviualMonsterDrop.add(Integer.valueOf(1422013));
        IndiviualMonsterDrop.add(Integer.valueOf(1402016));
        IndiviualMonsterDrop.add(Integer.valueOf(1442020));
        IndiviualMonsterDrop.add(Integer.valueOf(1432011));
        IndiviualMonsterDrop.add(Integer.valueOf(1332022));
        IndiviualMonsterDrop.add(Integer.valueOf(1312015));
        IndiviualMonsterDrop.add(Integer.valueOf(1382010));
        IndiviualMonsterDrop.add(Integer.valueOf(1372009));
        IndiviualMonsterDrop.add(Integer.valueOf(1082085));
        IndiviualMonsterDrop.add(Integer.valueOf(1332022));
        IndiviualMonsterDrop.add(Integer.valueOf(1472033));
        drops.put(Integer.valueOf(9400121), IndiviualMonsterDrop);
        IndiviualMonsterDrop = new ArrayList();
        IndiviualMonsterDrop.add(Integer.valueOf(4032024));
        IndiviualMonsterDrop.add(Integer.valueOf(4032025));
        IndiviualMonsterDrop.add(Integer.valueOf(4020006));
        IndiviualMonsterDrop.add(Integer.valueOf(4020008));
        IndiviualMonsterDrop.add(Integer.valueOf(4010001));
        IndiviualMonsterDrop.add(Integer.valueOf(4004001));
        IndiviualMonsterDrop.add(Integer.valueOf(2070006));
        IndiviualMonsterDrop.add(Integer.valueOf(2044404));
        IndiviualMonsterDrop.add(Integer.valueOf(2044702));
        IndiviualMonsterDrop.add(Integer.valueOf(2044305));
        IndiviualMonsterDrop.add(Integer.valueOf(1102029));
        IndiviualMonsterDrop.add(Integer.valueOf(1032023));
        IndiviualMonsterDrop.add(Integer.valueOf(1402004));
        IndiviualMonsterDrop.add(Integer.valueOf(1072210));
        IndiviualMonsterDrop.add(Integer.valueOf(1040104));
        IndiviualMonsterDrop.add(Integer.valueOf(1060092));
        IndiviualMonsterDrop.add(Integer.valueOf(1082129));
        IndiviualMonsterDrop.add(Integer.valueOf(1442008));
        IndiviualMonsterDrop.add(Integer.valueOf(1072178));
        IndiviualMonsterDrop.add(Integer.valueOf(1050092));
        IndiviualMonsterDrop.add(Integer.valueOf(1002271));
        IndiviualMonsterDrop.add(Integer.valueOf(1051053));
        IndiviualMonsterDrop.add(Integer.valueOf(1382008));
        IndiviualMonsterDrop.add(Integer.valueOf(1002275));
        IndiviualMonsterDrop.add(Integer.valueOf(1051082));
        IndiviualMonsterDrop.add(Integer.valueOf(1050064));
        IndiviualMonsterDrop.add(Integer.valueOf(1472028));
        IndiviualMonsterDrop.add(Integer.valueOf(1072193));
        IndiviualMonsterDrop.add(Integer.valueOf(1072172));
        IndiviualMonsterDrop.add(Integer.valueOf(1002285));
        drops.put(Integer.valueOf(9400545), IndiviualMonsterDrop);
        IndiviualMonsterDrop = new ArrayList();
        return (Map<Integer, List<Integer>>)drops;
    }
    
    private static void getAllItems() {
        final MapleDataProvider data = MapleDataProviderFactory.getDataProvider("String.wz");
        final List itemPairs = new ArrayList();
        MapleData itemsData = data.getData("Cash.img");
        for (final MapleData itemFolder : itemsData.getChildren()) {
            final int itemId = Integer.parseInt(itemFolder.getName());
            final String itemName = MapleDataTool.getString("name", itemFolder, "NO-NAME");
            itemPairs.add(new Pair<Integer, String>(Integer.valueOf(itemId), itemName));
        }
        itemsData = data.getData("Consume.img");
        for (final MapleData itemFolder : itemsData.getChildren()) {
            final int itemId = Integer.parseInt(itemFolder.getName());
            final String itemName = MapleDataTool.getString("name", itemFolder, "NO-NAME");
            itemPairs.add(new Pair<Integer, String>(Integer.valueOf(itemId), itemName));
        }
        itemsData = data.getData("Eqp.img").getChildByPath("Eqp");
        for (final MapleData eqpType : itemsData.getChildren()) {
            for (final MapleData itemFolder2 : eqpType.getChildren()) {
                final int itemId2 = Integer.parseInt(itemFolder2.getName());
                final String itemName2 = MapleDataTool.getString("name", itemFolder2, "NO-NAME");
                itemPairs.add(new Pair<Integer, String>(Integer.valueOf(itemId2), itemName2));
            }
        }
        itemsData = data.getData("Etc.img").getChildByPath("Etc");
        for (final MapleData itemFolder : itemsData.getChildren()) {
            final int itemId = Integer.parseInt(itemFolder.getName());
            final String itemName = MapleDataTool.getString("name", itemFolder, "NO-NAME");
            itemPairs.add(new Pair<Integer, String>(Integer.valueOf(itemId), itemName));
        }
        itemsData = data.getData("Ins.img");
        for (final MapleData itemFolder : itemsData.getChildren()) {
            final int itemId = Integer.parseInt(itemFolder.getName());
            final String itemName = MapleDataTool.getString("name", itemFolder, "NO-NAME");
            itemPairs.add(new Pair<Integer, String>(Integer.valueOf(itemId), itemName));
        }
        itemsData = data.getData("Pet.img");
        for (final MapleData itemFolder : itemsData.getChildren()) {
            final int itemId = Integer.parseInt(itemFolder.getName());
            final String itemName = MapleDataTool.getString("name", itemFolder, "NO-NAME");
            itemPairs.add(new Pair<Integer, String>(Integer.valueOf(itemId), itemName));
        }
        MonsterDropCreator.itemNameCache.addAll((Collection<? extends Pair<Integer, String>>)itemPairs);
    }
    
    public static void getAllMobs() {
        final List itemPairs = new ArrayList();
        final MapleDataProvider data = MapleDataProviderFactory.getDataProvider("String.wz");
        final MapleDataProvider mobData = MapleDataProviderFactory.getDataProvider("Mob.wz");
        final MapleData mob = data.getData("Mob.img");
        for (final MapleData itemFolder : mob.getChildren()) {
            final int id = Integer.parseInt(itemFolder.getName());
            try {
                final MapleData monsterData = mobData.getData(StringUtil.getLeftPaddedStr(Integer.toString(id) + ".img", '0', 11));
                final int boss = (id == 8810018) ? 1 : MapleDataTool.getIntConvert("boss", monsterData.getChildByPath("info"), 0);
                if (boss > 0) {
                    MonsterDropCreator.bossCache.put(Integer.valueOf(id), Boolean.valueOf(true));
                }
                final MobInfo mobInfo = new MobInfo(boss, MapleDataTool.getIntConvert("rareItemDropLevel", monsterData.getChildByPath("info"), 0), MapleDataTool.getString("name", itemFolder, "NO-NAME"));
                itemPairs.add(new Pair<Integer, MobInfo>(Integer.valueOf(id), mobInfo));
            }
            catch (Exception ex) {}
        }
        MonsterDropCreator.mobCache.addAll((Collection<? extends Pair<Integer, MobInfo>>)itemPairs);
    }
    
    static {
        MonsterDropCreator.lastmonstercardid = 2388070;
        MonsterDropCreator.addFlagData = false;
        MonsterDropCreator.monsterQueryData = "drop_data";
        MonsterDropCreator.itemNameCache = new ArrayList<Pair<Integer, String>>();
        MonsterDropCreator.mobCache = new ArrayList<Pair<Integer, MobInfo>>();
        MonsterDropCreator.bossCache = new HashMap<Integer, Boolean>();
    }
    
    public static class MobInfo
    {
        public int boss;
        public int rareItemDropLevel;
        public String name;
        
        public MobInfo(final int boss, final int rareItemDropLevel, final String name) {
            this.boss = boss;
            this.rareItemDropLevel = rareItemDropLevel;
            this.name = name;
        }
        
        public int getBoss() {
            return this.boss;
        }
        
        public int rateItemDropLevel() {
            return this.rareItemDropLevel;
        }
        
        public String getName() {
            return this.name;
        }
    }
}
