package handling.channel;

import java.util.Map.Entry;
import client.MapleCharacterUtil;
import handling.world.CheaterData;
import java.util.Iterator;
import handling.world.World.Find;
import java.util.ConcurrentModificationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import server.Timer.PingTimer;
import java.util.HashMap;
import client.MapleClient;
import handling.world.CharacterTransfer;
import client.MapleCharacter;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PlayerStorage
{
    private final ReentrantReadWriteLock mutex;
    private final Lock readLock;
    private final Lock writeLock;
    private final ReentrantReadWriteLock mutex2;
    private final Lock readLock2;
    private final Lock writeLock2;
    private final ReentrantReadWriteLock mutex3;
    private final Lock readLock3;
    private final Lock writeLock3;
    private final Map<Integer, MapleCharacter> idToChar;
    private final Map<Integer, CharacterTransfer> PendingCharacter;
    private final Map<Integer, MapleClient> PendingClient;
    private final int channel;
    
    public PlayerStorage(final int channel) {
        this.mutex = new ReentrantReadWriteLock();
        this.readLock = this.mutex.readLock();
        this.writeLock = this.mutex.writeLock();
        this.mutex2 = new ReentrantReadWriteLock();
        this.readLock2 = this.mutex2.readLock();
        this.writeLock2 = this.mutex2.writeLock();
        this.mutex3 = new ReentrantReadWriteLock();
        this.readLock3 = this.mutex3.readLock();
        this.writeLock3 = this.mutex3.writeLock();
        this.idToChar = new HashMap<Integer, MapleCharacter>();
        this.PendingCharacter = new HashMap<Integer, CharacterTransfer>();
        this.PendingClient = new HashMap<Integer, MapleClient>();
        this.channel = channel;
        PingTimer.getInstance().register((Runnable)new PersistingTask(), 60000L);
    }
    
    public final Collection<MapleClient> getAllPendingClients() {
        this.readLock.lock();
        try {
            return this.PendingClient.values();
        }
        finally {
            this.readLock.unlock();
        }
    }
    
    public final Collection<MapleCharacter> getAllCharacters() {
        this.readLock.lock();
        try {
            return Collections.unmodifiableCollection((Collection<? extends MapleCharacter>)this.idToChar.values());
        }
        finally {
            this.readLock.unlock();
        }
    }
    
    public final List<MapleCharacter> getAllCharactersThreadSafe() {
        final List<MapleCharacter> ret = new ArrayList<MapleCharacter>();
        try {
            ret.addAll((Collection<? extends MapleCharacter>)this.getAllCharacters());
        }
        catch (ConcurrentModificationException ex) {}
        return ret;
    }
    
    public final void registerPlayer(final MapleCharacter chr) {
        this.writeLock.lock();
        try {
            this.idToChar.put(Integer.valueOf(chr.getId()), chr);
        }
        finally {
            this.writeLock.unlock();
        }
        Find.register(chr.getId(), chr.getName(), this.channel);
    }
    
    public final void registerPendingPlayer(final CharacterTransfer chr, final int playerid) {
        this.writeLock2.lock();
        try {
            this.PendingCharacter.put(Integer.valueOf(playerid), chr);
        }
        finally {
            this.writeLock2.unlock();
        }
    }
    
    public final void deregisterPlayer(final MapleCharacter chr) {
        this.writeLock.lock();
        try {
            this.idToChar.remove((Object)Integer.valueOf(chr.getId()));
        }
        finally {
            this.writeLock.unlock();
        }
        Find.forceDeregister(chr.getId(), chr.getName());
    }
    
    public final int pendingCharacterSize(final int world) {
        final Map<Integer, MapleCharacter> chars = new HashMap<Integer, MapleCharacter>();
        for (final MapleCharacter chr : this.idToChar.values()) {
            if (chr.getWorld() == world) {
                chars.put(Integer.valueOf(chr.getId()), chr);
            }
        }
        return chars.size();
    }
    
    public final int pendingCharacterSize() {
        return this.PendingCharacter.size();
    }
    
    public final void deregisterPlayer(final int idz, final String namez) {
        this.writeLock.lock();
        try {
            this.idToChar.remove((Object)Integer.valueOf(idz));
        }
        finally {
            this.writeLock.unlock();
        }
        Find.forceDeregister(idz, namez);
    }
    
    public final void deregisterPendingPlayer(final int charid) {
        this.writeLock2.lock();
        try {
            this.PendingCharacter.remove((Object)Integer.valueOf(charid));
        }
        finally {
            this.writeLock2.unlock();
        }
    }
    
    public final CharacterTransfer getPendingCharacter(final int charid) {
        this.readLock2.lock();
        CharacterTransfer toreturn;
        try {
            toreturn = (CharacterTransfer)this.PendingCharacter.get((Object)Integer.valueOf(charid));
        }
        finally {
            this.readLock2.unlock();
        }
        if (toreturn != null) {
            this.deregisterPendingPlayer(charid);
        }
        return toreturn;
    }
    
    public final MapleCharacter getCharacterByName(final String name) {
        MapleCharacter rchr = null;
        this.readLock.lock();
        try {
            for (final MapleCharacter chr : this.idToChar.values()) {
                if (chr.getName().equalsIgnoreCase(name)) {
                    rchr = chr;
                }
            }
        }
        finally {
            this.readLock.unlock();
        }
        return rchr;
    }
    
    public final MapleCharacter getCharacterById(final int id) {
        this.readLock.lock();
        try {
            return (MapleCharacter)this.idToChar.get((Object)Integer.valueOf(id));
        }
        finally {
            this.readLock.unlock();
        }
    }
    
    public final int getConnectedClients() {
        return this.idToChar.size();
    }
    
    public final List<CheaterData> getCheaters() {
        final List<CheaterData> cheaters = new ArrayList<CheaterData>();
        this.readLock.lock();
        try {
            for (final MapleCharacter chr : this.idToChar.values()) {
                if (chr.getCheatTracker().getPoints() > 0) {
                    cheaters.add(new CheaterData(chr.getCheatTracker().getPoints(), MapleCharacterUtil.makeMapleReadable(chr.getName()) + "(編號:" + chr.getId() + ") 檢測次數(" + chr.getCheatTracker().getPoints() + ") " + chr.getCheatTracker().getSummary() + " 地图:" + chr.getMap().getMapName()));
                }
            }
        }
        finally {
            this.readLock.unlock();
        }
        return cheaters;
    }
    
    public final void disconnectAll() {
        this.disconnectAll(false);
    }
    
    public final void disconnectAll(final boolean checkGM) {
        this.writeLock.lock();
        try {
            final Iterator<MapleCharacter> itr = this.idToChar.values().iterator();
            while (itr.hasNext()) {
                final MapleCharacter chr = (MapleCharacter)itr.next();
                if (!chr.isGM() || !checkGM) {
                    chr.getClient().disconnect(false, false, true);
                    chr.getClient().getSession().close();
                    Find.forceDeregister(chr.getId(), chr.getName());
                    itr.remove();
                }
            }
        }
        finally {
            this.writeLock.unlock();
        }
    }
    
    public final void disconnectAll(final MapleCharacter ch) {
        this.writeLock.lock();
        try {
            final Iterator<MapleCharacter> itr = this.idToChar.values().iterator();
            while (itr.hasNext()) {
                final MapleCharacter chr = (MapleCharacter)itr.next();
                if (chr.getGMLevel() < ch.getGMLevel()) {
                    chr.getClient().disconnect(false, false, true);
                    chr.getClient().getSession().close();
                    Find.forceDeregister(chr.getId(), chr.getName());
                    itr.remove();
                }
            }
        }
        finally {
            this.writeLock.unlock();
        }
    }
    
    public final String getOnlinePlayers(final boolean byGM) {
        final StringBuilder sb = new StringBuilder();
        if (byGM) {
            this.readLock.lock();
            try {
                final Iterator<MapleCharacter> itr = this.idToChar.values().iterator();
                while (itr.hasNext()) {
                    sb.append(MapleCharacterUtil.makeMapleReadable(((MapleCharacter)itr.next()).getName()));
                    sb.append(", ");
                }
            }
            finally {
                this.readLock.unlock();
            }
        }
        else {
            this.readLock.lock();
            try {
                for (final MapleCharacter chr : this.idToChar.values()) {
                    if (!chr.isGM()) {
                        sb.append(MapleCharacterUtil.makeMapleReadable(chr.getName()));
                        sb.append(", ");
                    }
                }
            }
            finally {
                this.readLock.unlock();
            }
        }
        return sb.toString();
    }
    
    public final void broadcastPacket(final byte[] data) {
        this.readLock.lock();
        try {
            final Iterator<MapleCharacter> itr = this.idToChar.values().iterator();
            while (itr.hasNext()) {
                ((MapleCharacter)itr.next()).getClient().sendPacket(data);
            }
        }
        finally {
            this.readLock.unlock();
        }
    }
    
    public final void broadcastSmegaPacket(final byte[] data) {
        this.readLock.lock();
        try {
            for (final MapleCharacter chr : this.idToChar.values()) {
                if (chr.getClient().isLoggedIn() && chr.getSmega()) {
                    chr.getClient().sendPacket(data);
                }
            }
        }
        finally {
            this.readLock.unlock();
        }
    }
    
    public final void broadcastGashponmegaPacket(final byte[] data) {
        this.readLock.lock();
        try {
            for (final MapleCharacter chr : this.idToChar.values()) {
                if (chr.getClient().isLoggedIn() && chr.getGashponmega()) {
                    chr.getClient().sendPacket(data);
                }
            }
        }
        finally {
            this.readLock.unlock();
        }
    }
    
    public final void broadcastGMPacket(final byte[] data) {
        this.readLock.lock();
        try {
            for (final MapleCharacter chr : this.idToChar.values()) {
                if (chr.getClient().isLoggedIn() && chr.isGM()) {
                    chr.getClient().sendPacket(data);
                }
            }
        }
        finally {
            this.readLock.unlock();
        }
    }
    
    public final void broadcastGMPacket(final byte[] data, final boolean 吸怪) {
        this.readLock.lock();
        try {
            for (final MapleCharacter chr : this.idToChar.values()) {
                if (chr.getClient().isLoggedIn() && chr.isGM() && chr.get_control_吸怪信息()) {
                    chr.getClient().sendPacket(data);
                }
            }
        }
        finally {
            this.readLock.unlock();
        }
    }
    
    public final MapleClient getPendingClient(final int charid) {
        this.readLock3.lock();
        MapleClient toreturn;
        try {
            toreturn = (MapleClient)this.PendingClient.get((Object)Integer.valueOf(charid));
        }
        finally {
            this.readLock3.unlock();
        }
        if (toreturn != null) {
            this.deregisterPendingClient(charid);
        }
        return toreturn;
    }
    
    public final void registerPendingClient(final MapleClient c, final int playerid) {
        this.writeLock3.lock();
        try {
            this.PendingClient.put(Integer.valueOf(playerid), c);
        }
        finally {
            this.writeLock3.unlock();
        }
    }
    
    public final void deregisterPendingClient(final int charid) {
        this.writeLock3.lock();
        try {
            this.PendingClient.remove((Object)Integer.valueOf(charid));
        }
        finally {
            this.writeLock3.unlock();
        }
    }
    
    public final void deregisterPendingPlayerByAccountId(final int accountId) {
        this.writeLock2.lock();
        try {
            for (final CharacterTransfer transfer : this.PendingCharacter.values()) {
                if (transfer.accountid == accountId) {
                    this.PendingCharacter.remove((Object)Integer.valueOf(transfer.characterid));
                }
            }
        }
        finally {
            this.writeLock2.unlock();
        }
    }
    
    public class PersistingTask implements Runnable
    {
        @Override
        public void run() {
            writeLock2.lock();
            try {
                final long currenttime = System.currentTimeMillis();
                final Iterator<Entry<Integer, CharacterTransfer>> itr = PendingCharacter.entrySet().iterator();
                while (itr.hasNext()) {
                    if (currenttime - ((CharacterTransfer)((Entry<Integer, CharacterTransfer>)itr.next()).getValue()).TranferTime > 40000L) {
                        itr.remove();
                    }
                }
            }
            finally {
                writeLock2.unlock();
            }
        }
    }
}
