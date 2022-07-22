package handling.login;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.HashMap;
import client.MapleClient;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccountStorage
{
    private final ReentrantReadWriteLock mutex;
    private final Lock readLock;
    private final Lock writeLock;
    private final Map<Integer, MapleClient> idToClient;
    
    public AccountStorage() {
        this.mutex = new ReentrantReadWriteLock();
        this.readLock = this.mutex.readLock();
        this.writeLock = this.mutex.writeLock();
        this.idToClient = new HashMap<Integer, MapleClient>();
    }
    
    public final Collection<MapleClient> getAllClients() {
        this.readLock.lock();
        try {
            return Collections.unmodifiableCollection((Collection<? extends MapleClient>)this.idToClient.values());
        }
        finally {
            this.readLock.unlock();
        }
    }
    
    public final List<MapleClient> getAllClientsThreadSafe() {
        final List<MapleClient> ret = new ArrayList<MapleClient>();
        ret.addAll((Collection<? extends MapleClient>)this.getAllClients());
        return ret;
    }
    
    public final void registerAccount(final MapleClient c) {
        this.writeLock.lock();
        try {
            this.idToClient.put(Integer.valueOf(c.getAccID()), c);
        }
        finally {
            this.writeLock.unlock();
        }
    }
    
    public final void deregisterAccount(final MapleClient c) {
        this.writeLock.lock();
        try {
            final List<Integer> clients = new ArrayList<Integer>();
            for (final Entry<Integer, MapleClient> entry : this.idToClient.entrySet()) {
                if (entry.getValue() == c) {
                    clients.add(entry.getKey());
                }
            }
            final Iterator<Integer> iterator2 = clients.iterator();
            while (iterator2.hasNext()) {
                final int id = (int)Integer.valueOf(iterator2.next());
                this.idToClient.remove((Object)Integer.valueOf(id));
            }
        }
        finally {
            this.writeLock.unlock();
        }
    }
    
    public final void deregisterAccountById(final int id) {
        this.writeLock.lock();
        try {
            this.idToClient.remove((Object)Integer.valueOf(id));
        }
        finally {
            this.writeLock.unlock();
        }
    }
    
    public final int pendingClientSize(final int world) {
        final Map<Integer, MapleClient> clients = new HashMap<Integer, MapleClient>();
        for (final MapleClient c : this.idToClient.values()) {
            if (c.getWorld() == world) {
                clients.put(Integer.valueOf(c.getAccID()), c);
            }
        }
        return clients.size();
    }
    
    public final MapleClient getClientByName(final String name) {
        MapleClient client = null;
        this.readLock.lock();
        try {
            for (final MapleClient c : this.idToClient.values()) {
                if (c.getAccountName().equalsIgnoreCase(name)) {
                    client = c;
                }
            }
        }
        finally {
            this.readLock.unlock();
        }
        return client;
    }
    
    public final MapleClient getCharacterById(final int id) {
        this.readLock.lock();
        try {
            return (MapleClient)this.idToClient.get((Object)Integer.valueOf(id));
        }
        finally {
            this.readLock.unlock();
        }
    }
    
    public final int getConnectedClients() {
        return this.idToClient.size();
    }
    
    public final void disconnectAll() {
        this.disconnectAll(false);
    }
    
    public final void disconnectAll(final boolean checkGM) {
        this.writeLock.lock();
        try {
            final Iterator<MapleClient> itr = this.idToClient.values().iterator();
            while (itr.hasNext()) {
                final MapleClient c = (MapleClient)itr.next();
                if (!c.isGm() || !checkGM) {
                    c.disconnect(false, false, true);
                    c.getSession().close();
                    itr.remove();
                }
            }
        }
        finally {
            this.writeLock.unlock();
        }
    }
    
    public final void disconnectAll(final MapleClient cl) {
        this.writeLock.lock();
        try {
            final Iterator<MapleClient> itr = this.idToClient.values().iterator();
            while (itr.hasNext()) {
                final MapleClient c = (MapleClient)itr.next();
                if (c.getGmLevel() < cl.getGmLevel()) {
                    c.disconnect(false, false, true);
                    c.getSession().close();
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
                final Iterator<MapleClient> itr = this.idToClient.values().iterator();
                while (itr.hasNext()) {
                    sb.append(((MapleClient)itr.next()).getAccountName());
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
                for (final MapleClient c : this.idToClient.values()) {
                    if (!c.isGm()) {
                        sb.append(c.getAccountName());
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
            final Iterator<MapleClient> itr = this.idToClient.values().iterator();
            while (itr.hasNext()) {
                ((MapleClient)itr.next()).sendPacket(data);
            }
        }
        finally {
            this.readLock.unlock();
        }
    }
    
    public final void broadcastGMPacket(final byte[] data) {
        this.readLock.lock();
        try {
            for (final MapleClient c : this.idToClient.values()) {
                if (c.isLoggedIn() && c.isGm()) {
                    c.sendPacket(data);
                }
            }
        }
        finally {
            this.readLock.unlock();
        }
    }
}
