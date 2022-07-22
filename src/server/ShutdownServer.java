package server;

import java.util.Set;

import handling.cashshop.CashShopServer;
import handling.channel.ChannelServer;
import handling.login.LoginServer;
import handling.world.World;
import handling.world.World.Alliance;
import handling.world.World.Family;
import handling.world.World.Guild;
import merchant.merchant_main;
import server.Timer.BuffTimer;
import server.Timer.CloneTimer;
import server.Timer.EtcTimer;
import server.Timer.EventTimer;
import server.Timer.MapTimer;
import server.Timer.MobTimer;
import server.Timer.PingTimer;
import server.Timer.WorldTimer;

public class ShutdownServer implements Runnable, ShutdownServerMBean
{
    private static final ShutdownServer instance;
    public static boolean running;
    
    public static ShutdownServer getInstance() {
        return ShutdownServer.instance;
    }
    
    @Override
    public void run() {
        synchronized (this) {
            if (ShutdownServer.running) {
                return;
            }
            ShutdownServer.running = true;
        }
        World.isShutDown = true;
        int ret = 0;
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            ret += cserv.closeAllMerchant();
        }
        System.out.println("共储存了 " + ret + " 个精灵商人");
        ret = 0;
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            ret += cserv.closeAllPlayerShop();
        }
        merchant_main.getInstance().save_data();
        System.out.println("共储存了 " + ret + " 个人雇佣商店");
        Guild.save();
        System.out.println("公会资料储存完毕");
        Alliance.save();
        System.out.println("联盟资料储存完毕");
        Family.save();
        System.out.println("家族资料储存完毕");
        EventTimer.getInstance().stop();
        WorldTimer.getInstance().stop();
        MapTimer.getInstance().stop();
        MobTimer.getInstance().stop();
        BuffTimer.getInstance().stop();
        CloneTimer.getInstance().stop();
        EtcTimer.getInstance().stop();
        PingTimer.getInstance().stop();
        System.out.println("Timer 关闭完成");
        final Set<Integer> channels = ChannelServer.getAllChannels();
        for (final Integer channel : channels) {
            try {
                final ChannelServer cs = ChannelServer.getInstance((int)channel);
                cs.saveAll();
                cs.setPrepareShutdown();
                cs.shutdown();
            }
            catch (Exception e) {
                System.out.println("频道" + String.valueOf((Object)channel) + " 关闭失败");
            }
        }
        try {
            LoginServer.shutdown();
            System.out.println("[登陆本地服务器关闭完成]");
        }
        catch (Exception e2) {
            System.out.println("[登陆本地服务器关闭失败]");
        }
        try {
            CashShopServer.shutdown();
            System.out.println("[购物商城关闭完成]\r\n\r\n本地服务器关闭完成，感谢使用@CongMS服务端！！！");
        }
        catch (Exception e2) {
            System.out.println("[购物商城关闭失败]");
        }
    }
    
    @Override
    public void shutdown() {
        this.run();
    }
    
    static {
        instance = new ShutdownServer();
        ShutdownServer.running = false;
    }
}
