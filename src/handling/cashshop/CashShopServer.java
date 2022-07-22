package handling.cashshop;

import constants.ServerConfig;
import server.ServerProperties;
import handling.channel.PlayerStorage;
import handling.mina.ServerConnection;
import java.net.InetSocketAddress;

public class CashShopServer
{
    private static String ip;
    private static InetSocketAddress InetSocketadd;
    private static int port;
    private static ServerConnection acceptor;
    private static PlayerStorage players;
    private static PlayerStorage playersMTS;
    private static boolean finishedShutdown;
    
    public static final void setup() {
        CashShopServer.port = (short)Short.valueOf(ServerProperties.getProperty("CongMS.cashshop.port", "8600"));
        CashShopServer.ip = ServerConfig.IP + ":" + CashShopServer.port;
        CashShopServer.players = new PlayerStorage(-10);
        CashShopServer.playersMTS = new PlayerStorage(-20);
        (CashShopServer.acceptor = new ServerConnection(CashShopServer.port, 0, -10)).run();
        System.out.println("[正在启动]游戏商城端口:" + CashShopServer.port);
    }
    
    public static final String getIP() {
        return CashShopServer.ip;
    }
    
    public static final PlayerStorage getPlayerStorage() {
        return CashShopServer.players;
    }
    
    public static final PlayerStorage getPlayerStorageMTS() {
        return CashShopServer.playersMTS;
    }
    
    public static final void shutdown() {
        if (CashShopServer.finishedShutdown) {
            return;
        }
        System.out.println("[游戏商城] 准备关闭");
        System.out.println("[游戏商城] 保存资料中");
        CashShopServer.players.disconnectAll();
        CashShopServer.playersMTS.disconnectAll();
        System.out.println("[游戏商城] 解除綁定端口");
        CashShopServer.acceptor.close();
        System.out.println("[游戏商城] 关闭完成");
    }
    
    public static boolean isShutdown() {
        return CashShopServer.finishedShutdown;
    }
    
    static {
        CashShopServer.port = 15555;
        CashShopServer.finishedShutdown = false;
    }
}
