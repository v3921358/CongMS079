package server;

import tools.MaplePacketCreator;
import handling.channel.ChannelServer;
import server.maps.MapleMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import client.MapleCharacter;
import java.lang.ref.WeakReference;
import java.util.List;

public class MapleCarnivalParty
{
    private final List<Integer> members;
    private final WeakReference<MapleCharacter> leader;
    private final byte team;
    private final int channel;
    private short availableCP;
    private short totalCP;
    private boolean winner;
    private final ReentrantReadWriteLock memebersLock;
    
    public MapleCarnivalParty(final MapleCharacter owner, final List<MapleCharacter> members, final byte team1) {
        this.members = new LinkedList<Integer>();
        this.availableCP = 0;
        this.totalCP = 0;
        this.winner = false;
        this.memebersLock = new ReentrantReadWriteLock();
        this.leader = new WeakReference<MapleCharacter>(owner);
        for (final MapleCharacter mem : members) {
            this.addMember(mem.getId());
        }
        this.team = team1;
        this.channel = owner.getClient().getChannel();
    }
    
    public final MapleCharacter getLeader() {
        return (MapleCharacter)this.leader.get();
    }
    
    public void addCP(final MapleCharacter player, final int ammount) {
        this.totalCP += (short)ammount;
        this.availableCP += (short)ammount;
        player.addCP(ammount);
    }
    
    public int getTotalCP() {
        return this.totalCP;
    }
    
    public int getAvailableCP() {
        return this.availableCP;
    }
    
    public void useCP(final MapleCharacter player, final int ammount) {
        if (this.availableCP >= ammount) {
            this.availableCP -= (short)ammount;
        }
        else {
            this.availableCP = 0;
        }
        player.useCP(ammount);
    }
    
    public List<Integer> getMembers() {
        return this.members;
    }
    
    public int getTeam() {
        return this.team;
    }
    
    public void warp(final MapleMap map, final String portalname) {
        this.memebersLock.readLock().lock();
        try {
            final Iterator<Integer> iterator = this.members.iterator();
            while (iterator.hasNext()) {
                final int chr = (int)Integer.valueOf(iterator.next());
                final MapleCharacter c = ChannelServer.getInstance(this.channel).getPlayerStorage().getCharacterById(chr);
                if (c != null) {
                    c.changeMap(map, map.getPortal(portalname));
                }
            }
        }
        finally {
            this.memebersLock.readLock().unlock();
        }
    }
    
    public void warp(final MapleMap map, final int portalid) {
        this.memebersLock.readLock().lock();
        try {
            final Iterator<Integer> iterator = this.members.iterator();
            while (iterator.hasNext()) {
                final int chr = (int)Integer.valueOf(iterator.next());
                final MapleCharacter c = ChannelServer.getInstance(this.channel).getPlayerStorage().getCharacterById(chr);
                if (c != null) {
                    c.changeMap(map, map.getPortal(portalid));
                }
            }
        }
        finally {
            this.memebersLock.readLock().unlock();
        }
    }
    
    public boolean allInMap(final MapleMap map) {
        try {
            this.memebersLock.readLock().lock();
            final Iterator<Integer> iterator = this.members.iterator();
            while (iterator.hasNext()) {
                final int chr = (int)Integer.valueOf(iterator.next());
                if (map.getCharacterById(chr) == null) {
                    return false;
                }
            }
        }
        finally {
            this.memebersLock.readLock().unlock();
        }
        return true;
    }
    
    public void removeMember(final MapleCharacter chr) {
        try {
            this.memebersLock.writeLock().lock();
            int index = -1;
            for (int i = 0; i < this.members.size(); ++i) {
                if ((int)Integer.valueOf(this.members.get(i)) == chr.getId()) {
                    index = i;
                }
            }
            if (index != -1) {
                chr.setCarnivalParty(null);
                this.members.remove(index);
            }
        }
        finally {
            this.memebersLock.writeLock().unlock();
        }
    }
    
    public boolean isWinner() {
        return this.winner;
    }
    
    public void setWinner(final boolean status) {
        this.winner = status;
    }
    
    public void displayMatchResult() {
        final String effect = this.winner ? "quest/carnival/win" : "quest/carnival/lose";
        final String sound = this.winner ? "MobCarnival/Win" : "MobCarnival/Lose";
        boolean done = false;
        final Iterator<Integer> iterator = this.members.iterator();
        while (iterator.hasNext()) {
            final int chr = (int)Integer.valueOf(iterator.next());
            final MapleCharacter c = ChannelServer.getInstance(this.channel).getPlayerStorage().getCharacterById(chr);
            if (c != null) {
                c.getClient().sendPacket(MaplePacketCreator.showEffect(effect));
                c.getClient().sendPacket(MaplePacketCreator.playSound(sound));
                if (done) {
                    continue;
                }
                done = true;
                c.getMap().killAllMonsters(true);
                c.getMap().setSpawns(false);
            }
        }
    }
    
    private void addMember(final int charId) {
        try {
            this.memebersLock.writeLock().lock();
            this.members.add(Integer.valueOf(charId));
        }
        finally {
            this.memebersLock.writeLock().unlock();
        }
    }
}
