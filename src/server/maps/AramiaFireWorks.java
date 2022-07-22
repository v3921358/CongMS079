package server.maps;

import server.Randomizer;
import server.life.MapleLifeFactory;
import java.awt.Point;
import server.Timer.EventTimer;
import handling.world.World.Broadcast;
import tools.MaplePacketCreator;
import client.MapleCharacter;

public class AramiaFireWorks
{
    public static final int KEG_ID = 4031875;
    public static final int SUN_ID = 4001246;
    public static final int DEC_ID = 4001473;
    public static final int XIANG_ID = 4000516;
    public static final int MAX_KEGS = 5000;
    public static final int MAX_SUN = 14000;
    public static final int MAX_DEC = 18000;
    private short kegs;
    private short sunshines;
    private short decorations;
    private static final AramiaFireWorks instance;
    private static final int[] arrayMob;
    private static final int[] arrayX;
    private static final int[] arrayY;
    private static final int[] array_X;
    private static final int[] array_Y;
    private static final int flake_Y = 149;
    
    public AramiaFireWorks() {
        this.kegs = 0;
        this.sunshines = 2333;
        this.decorations = 3000;
    }
    
    public static final AramiaFireWorks getInstance() {
        return AramiaFireWorks.instance;
    }
    
    public final void giveKegs(final MapleCharacter c, final int kegs) {
        switch (this.kegs += (short)kegs) {
            case 1000: {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "<頻道 " + c.getClient().getChannel() + "> 不夜城新年活動進度目前是5000/" + (int)this.kegs + "！！"));
                break;
            }
            case 2000: {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "<頻道 " + c.getClient().getChannel() + "> 不夜城新年活動進度目前是5000/" + (int)this.kegs + "！！"));
                break;
            }
            case 3000: {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "<頻道 " + c.getClient().getChannel() + "> 不夜城新年活動進度目前是5000/" + (int)this.kegs + "！！"));
                break;
            }
            case 4000: {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "<頻道 " + c.getClient().getChannel() + "> 不夜城新年活動進度目前是5000/" + (int)this.kegs + "！！"));
                break;
            }
            case 4999: {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "<頻道 " + c.getClient().getChannel() + "> 不夜城新年活動進度目前是5000/" + (int)this.kegs + "！！"));
                break;
            }
        }
        if (this.kegs >= 5000) {
            this.kegs = 0;
            this.broadcastEvent(c);
        }
    }
    
    private void broadcastServer(final MapleCharacter c, final int itemid) {
        Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, itemid, "<頻道 " + c.getClient().getChannel() + "> 不夜城新年活動即將開始舉辦怪物大遊行！！"));
    }
    
    public final short getKegsPercentage() {
        final double per = (double)this.kegs * 1.0 / 5000.0;
        return (short)(int)(per * 100.0);
    }
    
    private void broadcastEvent(final MapleCharacter c) {
        this.broadcastServer(c, 4000516);
        EventTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public final void run() {
                AramiaFireWorks.this.startEvent(c.getClient().getChannelServer().getMapFactory().getMap(741000000));
            }
        }, 10000L);
    }
    
    private void startEvent(final MapleMap map) {
        map.startMapEffect("可以進行新年活動的表演了！！", 5121020);
        EventTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public final void run() {
                AramiaFireWorks.this.spawnMonster(map);
            }
        }, 5000L);
    }
    
    private void spawnMonster(final MapleMap map) {
        for (int i = 0; i < AramiaFireWorks.arrayMob.length; ++i) {
            final Point pos = new Point(AramiaFireWorks.arrayX[i], AramiaFireWorks.arrayY[i]);
            map.spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(AramiaFireWorks.arrayMob[i]), pos);
        }
    }
    
    public final void giveSuns(final MapleCharacter c, final int kegs) {
        this.sunshines += (short)kegs;
        final MapleMap map = c.getClient().getChannelServer().getMapFactory().getMap(555000000);
        final MapleReactor reactor = map.getReactorByName("XmasTree");
        for (int gogo = kegs + 2333; gogo > 0; gogo -= 2333) {
            switch (reactor.getState()) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4: {
                    if (this.sunshines >= 2333 * (2 + reactor.getState())) {
                        reactor.setState((byte)(reactor.getState() + 1));
                        reactor.setTimerActive(false);
                        map.broadcastMessage(MaplePacketCreator.triggerReactor(reactor, (int)reactor.getState()));
                        break;
                    }
                    break;
                }
                default: {
                    if (this.sunshines >= 2333) {
                        map.resetReactors();
                        break;
                    }
                    break;
                }
            }
        }
        if (this.sunshines >= 14000) {
            this.sunshines = 0;
            this.broadcastSun(c);
        }
    }
    
    public final short getSunsPercentage() {
        return (short)(this.sunshines / 14000 * 10000);
    }
    
    private void broadcastSun(final MapleCharacter c) {
        this.broadcastServer(c, 4001246);
        EventTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public final void run() {
                AramiaFireWorks.this.startSun(c.getClient().getChannelServer().getMapFactory().getMap(970010000));
            }
        }, 10000L);
    }
    
    private void startSun(final MapleMap map) {
        map.startMapEffect("陽光正在綻放！", 5121010);
        for (int i = 0; i < 3; ++i) {
            EventTimer.getInstance().schedule((Runnable)new Runnable() {
                @Override
                public final void run() {
                    AramiaFireWorks.this.spawnItem(map);
                }
            }, (long)(5000 + i * 10000));
        }
    }
    
    private void spawnItem(final MapleMap map) {
        for (int i = 0; i < Randomizer.nextInt(5) + 10; ++i) {
            final Point pos = new Point(AramiaFireWorks.array_X[i], AramiaFireWorks.array_Y[i]);
            map.spawnAutoDrop((Randomizer.nextInt(3) == 1) ? 3010025 : 4001246, pos);
        }
    }
    
    public final void giveDecs(final MapleCharacter c, final int kegs) {
        this.decorations += (short)kegs;
        final MapleMap map = c.getClient().getChannelServer().getMapFactory().getMap(555000000);
        final MapleReactor reactor = map.getReactorByName("XmasTree");
        for (int gogo = kegs + 3000; gogo > 0; gogo -= 3000) {
            switch (reactor.getState()) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4: {
                    if (this.decorations >= 3000 * (2 + reactor.getState())) {
                        reactor.setState((byte)(reactor.getState() + 1));
                        reactor.setTimerActive(false);
                        map.broadcastMessage(MaplePacketCreator.triggerReactor(reactor, (int)reactor.getState()));
                        break;
                    }
                    break;
                }
                default: {
                    if (this.decorations >= 3000) {
                        map.resetReactors();
                        break;
                    }
                    break;
                }
            }
        }
        if (this.decorations >= 18000) {
            this.decorations = 0;
            this.broadcastDec(c);
        }
    }
    
    public final short getDecsPercentage() {
        return (short)(this.decorations / 18000 * 10000);
    }
    
    private final void broadcastDec(final MapleCharacter c) {
        this.broadcastServer(c, 4001473);
        EventTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public final void run() {
                AramiaFireWorks.this.startDec(c.getClient().getChannelServer().getMapFactory().getMap(555000000));
            }
        }, 10000L);
    }
    
    private final void startDec(final MapleMap map) {
        map.startMapEffect("聖誕樹正在綻放！", 5120000);
        for (int i = 0; i < 3; ++i) {
            EventTimer.getInstance().schedule((Runnable)new Runnable() {
                @Override
                public final void run() {
                    AramiaFireWorks.this.spawnDec(map);
                }
            }, (long)(5000 + i * 10000));
        }
    }
    
    private final void spawnDec(final MapleMap map) {
        for (int i = 0; i < Randomizer.nextInt(10) + 40; ++i) {
            final Point pos = new Point(Randomizer.nextInt(800) - 400, 149);
            map.spawnAutoDrop((Randomizer.nextInt(15) == 1) ? 2060006 : 2060006, pos);
        }
    }
    
    
    static {
        instance = new AramiaFireWorks();
        arrayMob = new int[] { 9410066 };
        arrayX = new int[] { -255 };
        arrayY = new int[] { 340 };
        array_X = new int[] { 720, 180, 630, 270, 360, 540, 450, 142, 142, 218, 772, 810, 848, 232, 308, 142 };
        array_Y = new int[] { 1234, 1234, 1174, 1234, 1174, 1174, 1174, 1260, 1234, 1234, 1234, 1234, 1234, 1114, 1114, 1140 };
    }
}
