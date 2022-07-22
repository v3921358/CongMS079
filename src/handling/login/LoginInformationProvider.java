package handling.login;

import java.util.Iterator;
import provider.MapleDataTool;
import provider.MapleData;
import provider.MapleDataProviderFactory;
import java.util.ArrayList;
import java.util.List;

public class LoginInformationProvider
{
    private static final LoginInformationProvider instance;
    protected final List<String> ForbiddenName;
    
    public static LoginInformationProvider getInstance() {
        return LoginInformationProvider.instance;
    }
    
    protected LoginInformationProvider() {
        this.ForbiddenName = new ArrayList<String>();
        System.out.println("[正在加载] -> WZ內禁止使用的道具与名称");
        final MapleData nameData = MapleDataProviderFactory.getDataProvider("Etc.wz").getData("ForbiddenName.img");
        for (final MapleData data : nameData.getChildren()) {
            this.ForbiddenName.add(MapleDataTool.getString(data));
        }
    }
    
    public final boolean isForbiddenName(final String in) {
        for (final String name : this.ForbiddenName) {
            if (in.contains((CharSequence)name)) {
                return true;
            }
        }
        return false;
    }
    
    static {
        instance = new LoginInformationProvider();
    }
}
