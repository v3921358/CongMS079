package client.anticheat;

import server.Timer.CheatTimer;
import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedHashSet;
import java.util.concurrent.locks.Lock;
import java.util.Set;

public class CheatingOffensePersister
{
    private static final CheatingOffensePersister instance;
    private final Set<CheatingOffenseEntry> toPersist;
    private final Lock mutex;
    
    private CheatingOffensePersister() {
        this.toPersist = new LinkedHashSet<CheatingOffenseEntry>();
        this.mutex = new ReentrantLock();
        CheatTimer.getInstance().register((Runnable)new PersistingTask(), 61000L);
    }
    
    public static CheatingOffensePersister getInstance() {
        return CheatingOffensePersister.instance;
    }
    
    public void persistEntry(final CheatingOffenseEntry coe) {
        this.mutex.lock();
        try {
            this.toPersist.remove((Object)coe);
            this.toPersist.add(coe);
        }
        finally {
            this.mutex.unlock();
        }
    }
    
    static {
        instance = new CheatingOffensePersister();
    }
    
    public class PersistingTask implements Runnable
    {
        @Override
        public void run() {
            mutex.lock();
            try {
                toPersist.clear();
            }
            finally {
                mutex.unlock();
            }
        }
    }
}
