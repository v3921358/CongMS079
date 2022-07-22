package server.events;

import handling.world.World.Broadcast;
import server.Timer.EventTimer;
import tools.MaplePacketCreator;
import server.maps.SavedLocationType;
import server.MapleItemInformationProvider;
import server.MapleInventoryManipulator;
import server.Randomizer;
import server.RandomRewards;
import client.MapleCharacter;
import handling.channel.ChannelServer;
import server.maps.MapleMap;

public abstract class MapleEvent
{
    protected int[] mapid;
    protected int channel;
    protected boolean isRunning;
    
    public MapleEvent(final int channel, final int[] mapid) {
        this.isRunning = false;
        this.channel = channel;
        this.mapid = mapid;
    }
    
    public boolean isRunning() {
        return this.isRunning;
    }
    
    public MapleMap getMap(final int i) {
        return this.getChannelServer().getMapFactory().getMap(this.mapid[i]);
    }
    
    public ChannelServer getChannelServer() {
        return ChannelServer.getInstance(this.channel);
    }
    
    public void broadcast(final byte[] packet) {
        for (int i = 0; i < this.mapid.length; ++i) {
            this.getMap(i).broadcastMessage(packet);
        }
    }
    
    public void givePrize(final MapleCharacter chr) {
        final int reward = RandomRewards.getInstance().getEventReward();
        if (reward == 0) {
            chr.gainMeso(66666, true, false, false);
            chr.dropMessage(5, "你获得 66666 金币");
        }
        else if (reward == 1) {
            chr.gainMeso(399999, true, false, false);
            chr.dropMessage(5, "你获得 399999 金币");
        }
        else if (reward == 2) {
            chr.gainMeso(666666, true, false, false);
            chr.dropMessage(5, "你获得 666666 金币");
        }
        else if (reward == 3) {
            chr.addFame(10);
            chr.dropMessage(5, "你获得 10 名聲");
        }
        else {
            int max_quantity = 1;
            switch (reward) {
                case 5062000: {
                    max_quantity = 3;
                    break;
                }
                case 5220000: {
                    max_quantity = 25;
                    break;
                }
                case 4031307:
                case 5050000: {
                    max_quantity = 5;
                    break;
                }
                case 2022121: {
                    max_quantity = 10;
                    break;
                }
            }
            final int quantity = ((max_quantity > 1) ? Randomizer.nextInt(max_quantity) : 0) + 1;
            if (MapleInventoryManipulator.checkSpace(chr.getClient(), reward, quantity, "")) {
                MapleInventoryManipulator.addById(chr.getClient(), reward, (short)quantity);
                chr.dropMessage(5, "恭喜获得" + MapleItemInformationProvider.getInstance().getName(reward));
            }
            else {
                chr.gainMeso(100000, true, false, false);
                chr.dropMessage(5, "參加獎 100000 金币");
            }
        }
    }
    
    public void finished(final MapleCharacter chr) {
    }
    
    public void onMapLoad(final MapleCharacter chr) {
    }
    
    public void startEvent() {
    }
    
    public void warpBack(final MapleCharacter chr) {
        int map = chr.getSavedLocation(SavedLocationType.EVENT);
        if (map <= -1) {
            map = 104000000;
        }
        final MapleMap mapp = chr.getClient().getChannelServer().getMapFactory().getMap(map);
        chr.changeMap(mapp, mapp.getPortal(0));
    }
    
    public void reset() {
        this.isRunning = true;
    }
    
    public void unreset() {
        this.isRunning = false;
    }
    
    public static final void setEvent(final ChannelServer cserv, final boolean auto) {
        if (auto) {
            for (final MapleEventType t : MapleEventType.values()) {
                final MapleEvent e = cserv.getEvent(t);
                if (e.isRunning) {
                    for (final int i : e.mapid) {
                        if (cserv.getEvent() == i) {
                            e.broadcast(MaplePacketCreator.serverNotice(0, "距離活動開始只剩一分鐘!"));
                            e.broadcast(MaplePacketCreator.getClock(60));
                            EventTimer.getInstance().schedule((Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    e.startEvent();
                                }
                            }, 60000L);
                            break;
                        }
                    }
                }
            }
        }
        cserv.setEvent(-1);
    }
    
    public static final void mapLoad(final MapleCharacter chr, final int channel) {
        if (chr == null) {
            return;
        }
        for (final MapleEventType t : MapleEventType.values()) {
            final MapleEvent e = ChannelServer.getInstance(channel).getEvent(t);
            if (e.isRunning) {
                if (chr.getMapId() == 109050000) {
                    e.finished(chr);
                }
                for (final int i : e.mapid) {
                    if (chr.getMapId() == i) {
                        e.onMapLoad(chr);
                    }
                }
            }
        }
    }
    
    public static final void onStartEvent(final MapleCharacter chr) {
        for (final MapleEventType t : MapleEventType.values()) {
            final MapleEvent e = chr.getClient().getChannelServer().getEvent(t);
            if (e.isRunning) {
                for (final int i : e.mapid) {
                    if (chr.getMapId() == i) {
                        e.startEvent();
                        chr.getMap().broadcastMessage(MaplePacketCreator.serverNotice(5, String.valueOf((Object)t) + " 活動開始。"));
                    }
                }
            }
        }
    }
    
    public static final String scheduleEvent(final MapleEventType event, final ChannelServer cserv) {
        if (cserv.getEvent() != -1 || cserv.getEvent(event) == null) {
            return "該活動已經被禁止安排了.";
        }
        for (final int i : cserv.getEvent(event).mapid) {
            if (cserv.getMapFactory().getMap(i).getCharactersSize() > 0) {
                return "該活動已經在執行中.";
            }
        }
        cserv.setEvent(cserv.getEvent(event).mapid[0]);
        cserv.getEvent(event).reset();
        Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, "活動 " + String.valueOf((Object)event) + " 即將在頻道 " + cserv.getChannel() + " 舉行 , 參加指令@event 要參加的玩家請到頻道 " + cserv.getChannel()));
        return "";
    }
}
