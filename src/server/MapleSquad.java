package server;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ScheduledFuture;

import client.MapleCharacter;
import client.MapleClient;
import handling.channel.ChannelServer;
import handling.world.World.Find;
import server.Timer.EtcTimer;
import server.maps.MapleMap;
import tools.MaplePacketCreator;
import tools.Pair;

public class MapleSquad
{
    private WeakReference<MapleCharacter> leader;
    private final String leaderName;
    private final String toSay;
    private final Map<String, String> members;
    private final Map<String, String> bannedMembers;
    private final int ch;
    private final long startTime;
    private final int expiration;
    private final int beginMapId;
    private final MapleSquadType type;
    private byte status;
    private ScheduledFuture<?> removal;
    private MapleClient c;
    
    public MapleSquad(final int ch, final String type, final MapleCharacter leader, final int expiration, final String toSay) {
        this.members = new LinkedHashMap<String, String>();
        this.bannedMembers = new LinkedHashMap<String, String>();
        this.status = 0;
        this.leader = new WeakReference<MapleCharacter>(leader);
        this.members.put(leader.getName(), MapleCarnivalChallenge.getJobNameById((int)leader.getJob()));
        this.leaderName = leader.getName();
        this.ch = ch;
        this.toSay = toSay;
        this.type = MapleSquadType.valueOf(type.toLowerCase());
        this.status = 1;
        this.beginMapId = leader.getMapId();
        leader.getMap().setSquad(this.type);
        if (this.type.queue.get((Object)Integer.valueOf(ch)) == null) {
            this.type.queue.put(Integer.valueOf(ch), new ArrayList<Pair<String, Long>>());
            this.type.queuedPlayers.put(Integer.valueOf(ch), new ArrayList<Pair<String, String>>());
        }
        this.startTime = System.currentTimeMillis();
        this.expiration = expiration;
    }
    
    public void copy() {
        while (((ArrayList<Pair<String, Long>>)this.type.queue.get((Object)Integer.valueOf(this.ch))).size() > 0 && ChannelServer.getInstance(this.ch).getMapleSquad(this.type) == null) {
            int index = 0;
            long lowest = 0L;
            for (int i = 0; i < ((ArrayList<Pair<String, Long>>)this.type.queue.get((Object)Integer.valueOf(this.ch))).size(); ++i) {
                if (lowest == 0L || (long)Long.valueOf(((Pair<String, Long>)((ArrayList<Pair<String, Long>>)this.type.queue.get((Object)Integer.valueOf(this.ch))).get(i)).right) < lowest) {
                    index = i;
                    lowest = (long)Long.valueOf(((Pair<String, Long>)((ArrayList<Pair<String, Long>>)this.type.queue.get((Object)Integer.valueOf(this.ch))).get(i)).right);
                }
            }
            final String nextPlayerId = (String)((Pair<String, Long>)((ArrayList<Pair<String, Long>>)this.type.queue.get((Object)Integer.valueOf(this.ch))).remove(index)).left;
            final int theirCh = Find.findChannel(nextPlayerId);
            if (theirCh > 0) {
                final MapleCharacter lead = ChannelServer.getInstance(theirCh).getPlayerStorage().getCharacterByName(nextPlayerId);
                if (lead != null && lead.getMapId() == this.beginMapId && lead.getClient().getChannel() == this.ch) {
                    final MapleSquad squad = new MapleSquad(this.ch, this.type.name(), lead, this.expiration, this.toSay);
                    if (ChannelServer.getInstance(this.ch).addMapleSquad(squad, this.type.name())) {
                        this.getBeginMap().broadcastMessage(MaplePacketCreator.getClock(this.expiration / 1000));
                        this.getBeginMap().broadcastMessage(MaplePacketCreator.serverNotice(6, nextPlayerId + this.toSay));
                        ((ArrayList<Pair<String, String>>)this.type.queuedPlayers.get((Object)Integer.valueOf(this.ch))).add(new Pair<String, String>(nextPlayerId, "成功"));
                        break;
                    }
                    squad.clear();
                    ((ArrayList<Pair<String, String>>)this.type.queuedPlayers.get((Object)Integer.valueOf(this.ch))).add(new Pair<String, String>(nextPlayerId, "跳過"));
                    break;
                }
                else {
                    if (lead != null) {
                        lead.dropMessage(6, "遠征队已經結束了，由於沒有在正確的頻道裡。");
                    }
                    this.getBeginMap().broadcastMessage(MaplePacketCreator.serverNotice(6, nextPlayerId + "遠征队已經結束了，由於有成員沒有在地图內"));
                    ((ArrayList<Pair<String, String>>)this.type.queuedPlayers.get((Object)Integer.valueOf(this.ch))).add(new Pair<String, String>(nextPlayerId, "不在地图內"));
                }
            }
            else {
                this.getBeginMap().broadcastMessage(MaplePacketCreator.serverNotice(6, nextPlayerId + "'遠征队已經結束了，由於有成員沒有在線上"));
                ((ArrayList<Pair<String, String>>)this.type.queuedPlayers.get((Object)Integer.valueOf(this.ch))).add(new Pair<String, String>(nextPlayerId, "沒有上線"));
            }
        }
    }
    
    public MapleMap getBeginMap() {
        return ChannelServer.getInstance(this.ch).getMapFactory().getMap(this.beginMapId);
    }
    
    public void clear() {
        if (this.removal != null) {
            this.getBeginMap().broadcastMessage(MaplePacketCreator.stopClock());
            this.removal.cancel(false);
            this.removal = null;
        }
        this.members.clear();
        this.bannedMembers.clear();
        this.leader = null;
        ChannelServer.getInstance(this.ch).removeMapleSquad(this.type);
        this.status = 0;
    }
    
    public MapleCharacter getChar(final String name) {
        return ChannelServer.getInstance(this.ch).getPlayerStorage().getCharacterByName(name);
    }
    
    public long getTimeLeft() {
        return (long)this.expiration - (System.currentTimeMillis() - this.startTime);
    }
    
    public void scheduleRemoval() {
        this.removal = EtcTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                if (status != 0 && leader != null && (MapleSquad.this.getLeader() == null || status == 1)) {
                    MapleSquad.this.clear();
                    MapleSquad.this.copy();
                }
            }
        }, (long)this.expiration);
    }
    
    public String getLeaderName() {
        return this.leaderName;
    }
    
    public List<Pair<String, Long>> getAllNextPlayer() {
        return (ArrayList<Pair<String, Long>>)this.type.queue.get((Object)Integer.valueOf(this.ch));
    }
    
    public String getNextPlayer() {
        final StringBuilder sb = new StringBuilder("\n排队成員 : ");
        sb.append("#b").append(((ArrayList<Pair<String, Long>>)this.type.queue.get((Object)Integer.valueOf(this.ch))).size()).append(" #k ").append("與遠征队名單 : \n\r ");
        int i = 0;
        for (final Pair<String, Long> chr : (ArrayList<Pair<String, Long>>)this.type.queue.get((Object)Integer.valueOf(this.ch))) {
            ++i;
            sb.append(i).append(" : ").append((String)chr.left);
            sb.append(" \n\r ");
        }
        sb.append("你是否想要 #e當下一個#n 在遠征队排队中\u3000或者 #e移除#n 在遠征队? 如果你想的話...");
        return sb.toString();
    }
    
    public void setNextPlayer(final String i) {
        Pair<String, Long> toRemove = null;
        for (final Pair<String, Long> s : (ArrayList<Pair<String, Long>>)this.type.queue.get((Object)Integer.valueOf(this.ch))) {
            if (((String)s.left).equals((Object)i)) {
                toRemove = s;
                break;
            }
        }
        if (toRemove != null) {
            ((ArrayList<Pair<String, Long>>)this.type.queue.get((Object)Integer.valueOf(this.ch))).remove((Object)toRemove);
            return;
        }
        for (final ArrayList<Pair<String, Long>> v : this.type.queue.values()) {
            for (final Pair<String, Long> s2 : v) {
                if (((String)s2.left).equals((Object)i)) {
                    return;
                }
            }
        }
        ((ArrayList<Pair<String, Long>>)this.type.queue.get((Object)Integer.valueOf(this.ch))).add(new Pair<String, Long>(i, Long.valueOf(System.currentTimeMillis())));
    }
    
    public MapleCharacter getLeader() {
        if (this.leader == null || this.leader.get() == null) {
            if (this.members.size() <= 0 || this.getChar(this.leaderName) == null) {
                if (this.status != 0) {
                    this.clear();
                }
                return null;
            }
            this.leader = new WeakReference<MapleCharacter>(this.getChar(this.leaderName));
        }
        return (MapleCharacter)this.leader.get();
    }
    
    public boolean containsMember(final MapleCharacter member) {
        for (final String mmbr : this.members.keySet()) {
            if (mmbr.equalsIgnoreCase(member.getName())) {
                return true;
            }
        }
        return false;
    }
    
    public List<String> getMembers() {
        return new LinkedList<String>((Collection<? extends String>)this.members.keySet());
    }
    
    public List<String> getBannedMembers() {
        return new LinkedList<String>((Collection<? extends String>)this.bannedMembers.keySet());
    }
    
    public int getSquadSize() {
        return this.members.size();
    }
    
    public boolean isBanned(final MapleCharacter member) {
        return this.bannedMembers.containsKey((Object)member.getName());
    }
    
    public int addMember(final MapleCharacter member, final boolean join) {
        if (this.getLeader() == null) {
            return -1;
        }
        final String job = MapleCarnivalChallenge.getJobNameById((int)member.getJob());
        if (join) {
            if (this.containsMember(member) || this.getAllNextPlayer().contains((Object)member.getName())) {
                return -1;
            }
            if (this.members.size() <= 30) {
                this.members.put(member.getName(), job);
                this.getLeader().dropMessage(6, member.getName() + " (" + job + ") 加入了遠征队!");
                return 1;
            }
            return 2;
        }
        else {
            if (this.containsMember(member)) {
                this.members.remove((Object)member.getName());
                this.getLeader().dropMessage(6, member.getName() + " (" + job + ") 離開了遠征队.");
                return 1;
            }
            return -1;
        }
    }
    
    public void acceptMember(final int pos) {
        if (pos < 0 || pos >= this.bannedMembers.size()) {
            return;
        }
        final List<String> membersAsList = this.getBannedMembers();
        final String toadd = (String)membersAsList.get(pos);
        if (toadd != null && this.getChar(toadd) != null) {
            this.members.put(toadd, this.bannedMembers.get((Object)toadd));
            this.bannedMembers.remove((Object)toadd);
            this.getChar(toadd).dropMessage(5, this.getLeaderName() + " 允許你重新回來遠征队");
        }
    }
    
    public void reAddMember(final MapleCharacter chr) {
        this.removeMember(chr);
        this.members.put(chr.getName(), MapleCarnivalChallenge.getJobNameById((int)chr.getJob()));
    }
    
    public void removeMember(final MapleCharacter chr) {
        if (this.members.containsKey((Object)chr.getName())) {
            this.members.remove((Object)chr.getName());
        }
    }
    
    public void removeMember(final String chr) {
        if (this.members.containsKey((Object)chr)) {
            this.members.remove((Object)chr);
        }
    }
    
    public void banMember(final int pos) {
        if (pos <= 0 || pos >= this.members.size()) {
            return;
        }
        final List<String> membersAsList = this.getMembers();
        final String toban = (String)membersAsList.get(pos);
        if (toban != null && this.getChar(toban) != null) {
            this.bannedMembers.put(toban, this.members.get((Object)toban));
            this.members.remove((Object)toban);
            this.getChar(toban).dropMessage(5, this.getLeaderName() + " 從遠征队中刪除您.");
        }
    }
    
    public void setStatus(final byte status) {
        this.status = status;
        if (status == 2 && this.removal != null) {
            this.removal.cancel(false);
            this.removal = null;
        }
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public int getBannedMemberSize() {
        return this.bannedMembers.size();
    }
    
    public String getSquadMemberString(final byte type) {
        switch (type) {
            case 0: {
                final StringBuilder sb = new StringBuilder("目前遠征队總人數 : ");
                sb.append("#b").append(this.members.size()).append(" #k ").append("\r\n遠征队名單 : \n\r ");
                int i = 0;
                for (final Entry<String, String> chr : this.members.entrySet()) {
                    ++i;
                    sb.append(i).append(" : ").append((String)chr.getKey()).append(" (").append((String)chr.getValue()).append(") ");
                    if (i == 1) {
                        sb.append("(遠征队領袖)");
                    }
                    sb.append(" \n\r ");
                }
                while (i < 30) {
                    ++i;
                    sb.append(i).append(" : ").append(" \n\r ");
                }
                return sb.toString();
            }
            case 1: {
                final StringBuilder sb = new StringBuilder("目前遠征队總人數 : ");
                sb.append("#b").append(this.members.size()).append(" #k ").append("\r\n遠征队名單 : \n\r ");
                int i = 0;
                int selection = 0;
                for (final Entry<String, String> chr2 : this.members.entrySet()) {
                    ++i;
                    sb.append("#b#L").append(selection).append("#");
                    ++selection;
                    sb.append(i).append(" : ").append((String)chr2.getKey()).append(" (").append((String)chr2.getValue()).append(") ");
                    if (i == 1) {
                        sb.append("(遠征队領袖)");
                    }
                    sb.append("#l").append(" \n\r ");
                }
                while (i < 30) {
                    ++i;
                    sb.append(i).append(" : ").append(" \n\r ");
                }
                return sb.toString();
            }
            case 2: {
                final StringBuilder sb = new StringBuilder("目前遠征队總人數 : ");
                sb.append("#b").append(this.members.size()).append(" #k ").append("\r\n遠征队名單 : \n\r ");
                int i = 0;
                int selection = 0;
                for (final Entry<String, String> chr2 : this.bannedMembers.entrySet()) {
                    ++i;
                    sb.append("#b#L").append(selection).append("#");
                    ++selection;
                    sb.append(i).append(" : ").append((String)chr2.getKey()).append(" (").append((String)chr2.getValue()).append(") ");
                    sb.append("#l").append(" \n\r ");
                }
                while (i < 30) {
                    ++i;
                    sb.append(i).append(" : ").append(" \n\r ");
                }
                return sb.toString();
            }
            case 3: {
                final StringBuilder sb = new StringBuilder("职业 : ");
                final Map<String, Integer> jobs = this.getJobs();
                for (final Entry<String, Integer> chr3 : jobs.entrySet()) {
                    sb.append("\r\n").append((String)chr3.getKey()).append(" : ").append((Object)chr3.getValue());
                }
                return sb.toString();
            }
            default: {
                return null;
            }
        }
    }
    
    public final MapleSquadType getType() {
        return this.type;
    }
    
    public final Map<String, Integer> getJobs() {
        final Map<String, Integer> jobs = new LinkedHashMap<String, Integer>();
        for (final Entry<String, String> chr : this.members.entrySet()) {
            if (jobs.containsKey((Object)chr.getValue())) {
                jobs.put(chr.getValue(), Integer.valueOf((int)Integer.valueOf(jobs.get((Object)chr.getValue())) + 1));
            }
            else {
                jobs.put(chr.getValue(), Integer.valueOf(1));
            }
        }
        return jobs;
    }
    
    public enum MapleSquadType
    {
        bossbalrog(2), 
        zak(2), 
        chaoszak(3), 
        horntail(2), 
        chaosht(3), 
        pinkbean(3), 
        nmm_squad(2), 
        vergamot(2), 
        dunas(2), 
        nibergen_squad(2), 
        dunas2(2), 
        core_blaze(2), 
        aufheben(2), 
        cwkpq(10), 
        vonleon(3), 
        scartar(2), 
        cygnus(3);
        
        public int i;
        public HashMap<Integer, ArrayList<Pair<String, String>>> queuedPlayers;
        public HashMap<Integer, ArrayList<Pair<String, Long>>> queue;
        
        private MapleSquadType(final int i) {
            this.queuedPlayers = new HashMap<Integer, ArrayList<Pair<String, String>>>();
            this.queue = new HashMap<Integer, ArrayList<Pair<String, Long>>>();
            this.i = i;
        }
    }
}
