package handling.login;

import java.util.Iterator;
import java.util.Map;
import server.Timer.PingTimer;
import server.ServerProperties;
import handling.world.World;
import java.util.Map.Entry;
import constants.WorldConstants;
import handling.channel.ChannelServer;
import tools.packet.LoginPacket;
import tools.MaplePacketCreator;
import gui.CongMS;
import client.MapleClient;

public class LoginWorker
{
    private static long lastUpdate;
    
    public static void registerClient(final MapleClient c) {
        final int 禁止登陆开关 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"禁止登陆开关"));
        if (!c.isGm()) {
            if (禁止登陆开关 <= 0) {
                c.sendPacket(MaplePacketCreator.serverNotice(1, "服务器目前正在维修中."));
                c.sendPacket(LoginPacket.getLoginFailed(1));
                return;
            }
            if (!c.isGm() && (c.hasBannedMac() || c.hasBannedIP())) {
                c.sendPacket(LoginPacket.getLoginFailed(3));
                return;
            }
            if (System.currentTimeMillis() - LoginWorker.lastUpdate > 600000L) {
                LoginWorker.lastUpdate = System.currentTimeMillis();
                final Map<Integer, Integer> load = ChannelServer.getChannelLoad();
                int usersOn = 0;
                if (load == null || load.size() <= 0) {
                    LoginWorker.lastUpdate = 0L;
                    c.sendPacket(LoginPacket.getLoginFailed(7));
                    return;
                }
                final double loadFactor = 1200.0 / ((double)WorldConstants.USER_LIMIT / (double)load.size());
                for (final Entry<Integer, Integer> entry : load.entrySet()) {
                    usersOn += (int)Integer.valueOf(entry.getValue());
                    load.put(entry.getKey(), Integer.valueOf(Math.min(1200, (int)((double)(int)Integer.valueOf(entry.getValue()) * loadFactor))));
                }
                LoginServer.setLoad(load, usersOn);
                LoginWorker.lastUpdate = System.currentTimeMillis();
            }
            if (c.finishLogin() == 0) {
                if (c.getSecondPassword() == null) {
                    c.sendPacket(LoginPacket.getGenderNeeded(c));
                }
                else {
                    World.clearChannelChangeDataByAccountId(c.getAccID());
                    LoginServer.forceRemoveClient(c);
                    ChannelServer.forceRemovePlayerByAccId(c, c.getAccID());
                    LoginServer.getClientStorage().registerAccount(c);
                    c.sendPacket(LoginPacket.getAuthSuccessRequest(c));
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"蓝蜗牛开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(0, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"蘑菇仔开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(1, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"绿水灵开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(2, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"漂漂猪开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(3, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"小青蛇开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(4, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"红螃蟹开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(5, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"大海龟开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(6, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"章鱼怪开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(7, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"顽皮猴开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(8, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"星精灵开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(9, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"胖企鹅开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(10, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"白雪人开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(11, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"石头人开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(12, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"紫色猫开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(13, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"大灰狼开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(14, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"小白兔开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(15, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"喷火龙开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(16, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"火野猪开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(17, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"青鳄鱼开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(18, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"花蘑菇开关")) == 0) {
                        c.sendPacket(LoginPacket.getServerList(19, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                    }
                    c.sendPacket(LoginPacket.getEndOfServerList());
                }
                c.setIdleTask(PingTimer.getInstance().schedule((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        c.getSession().close();
                    }
                }, 6000000L));
            }
            else if (c.getGender() == 10) {
                c.sendPacket(LoginPacket.getGenderNeeded(c));
            }
            else {
                c.sendPacket(LoginPacket.getAuthSuccessRequest(c));
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"蓝蜗牛开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(0, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"蘑菇仔开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(1, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"绿水灵开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(2, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"漂漂猪开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(3, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"小青蛇开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(4, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"红螃蟹开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(5, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"大海龟开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(6, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"章鱼怪开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(7, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"顽皮猴开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(8, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"星精灵开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(9, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"胖企鹅开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(10, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"白雪人开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(11, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"石头人开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(12, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"紫色猫开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(13, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"大灰狼开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(14, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"小白兔开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(15, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"喷火龙开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(16, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"火野猪开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(17, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"青鳄鱼开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(18, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"花蘑菇开关")) == 0) {
                    c.sendPacket(LoginPacket.getServerList(19, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("CongMS.flag"))));
                }
                c.sendPacket(LoginPacket.getEndOfServerList());
            }
        }
    }
    
    static {
        LoginWorker.lastUpdate = 0L;
    }
}
