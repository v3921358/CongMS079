package tools.wangzhan;

import java.net.URISyntaxException;
import java.io.IOException;
import java.awt.Desktop;
import java.net.URI;

public class BareBonesBrowserLaunch
{
    public static void OpenWeb1() {
        try {
            final URI uri = new URI("http://www.baidu.com");
            Desktop.getDesktop().browse(uri);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (URISyntaxException e2) {
            e2.printStackTrace();
        }
    }
}
