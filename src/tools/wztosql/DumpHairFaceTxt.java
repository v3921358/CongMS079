package tools.wztosql;

import java.util.Iterator;
import java.util.List;
import provider.MapleDataProvider;
import tools.FileoutputUtil;
import java.util.Collections;
import provider.MapleDataTool;
import tools.StringUtil;
import provider.MapleData;
import java.util.ArrayList;
import provider.MapleDataProviderFactory;
import java.io.File;

public class DumpHairFaceTxt
{
    public static void main(final String[] args) {
        final DumpHairFaceTxt dump = new DumpHairFaceTxt();
        System.out.println("HairMySQL......");
        dump.dumpHairFaceData("Hair");
        System.out.println("FaceMySQL......");
        dump.dumpHairFaceData("Face");
        System.out.println("結束。");
    }
    
    public void dumpHairFaceData(final String type) {
        final File dataFile = new File(((System.getProperty("path") != null) ? System.getProperty("path") : "") + "wz/Character.wz/" + type);
        final File strDataFile = new File(((System.getProperty("path") != null) ? System.getProperty("path") : "") + "wz/String.wz");
        final MapleDataProvider chrData = MapleDataProviderFactory.getDataProvider(dataFile);
        final MapleDataProvider stringDataWZ = MapleDataProviderFactory.getDataProvider(strDataFile);
        final MapleData chrStringData = stringDataWZ.getData("Eqp.img").getChildByPath("Eqp/" + type);
        final List<Integer> list = new ArrayList<Integer>();
        for (final MapleData c : chrStringData) {
            final int chrid = Integer.parseInt(c.getName());
            final String n = StringUtil.getLeftPaddedStr(chrid + ".img", '0', 12);
            final String name = MapleDataTool.getString("name", c, "無");
            list.add(Integer.valueOf(chrid));
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); ++i) {
            if (type.contains((CharSequence)"Hair") && Integer.parseInt(Integer.valueOf(list.get(i)).toString().substring(4, 5)) == 0) {
                FileoutputUtil.logToFile("logs/Hair.txt", Integer.valueOf(list.get(i)).toString() + ",");
            }
            if (type.contains((CharSequence)"Face") && Integer.parseInt(Integer.valueOf(list.get(i)).toString().substring(2, 3)) == 0) {
                FileoutputUtil.logToFile("logs/Face.txt", Integer.valueOf(list.get(i)).toString() + ",");
            }
        }
    }
}
