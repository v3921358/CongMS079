package server.custom.rank;

import client.BuddyEntry;

public class MiniGamePoints implements Comparable
{
    private int characterid;
    private String name;
    private BuddyEntry buddyEntry;
    private int wins;
    private int ties;
    private int losses;
    
    public int getCharacterid() {
        return this.characterid;
    }
    
    public void setCharacterid(final int characterid) {
        this.characterid = characterid;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public BuddyEntry getBuddyEntry() {
        return this.buddyEntry;
    }
    
    public void setBuddyEntry(final BuddyEntry buddyEntry) {
        this.buddyEntry = buddyEntry;
    }
    
    public int getWins() {
        return this.wins;
    }
    
    public void setWins(final int wins) {
        this.wins = wins;
    }
    
    public int getTies() {
        return this.ties;
    }
    
    public void setTies(final int ties) {
        this.ties = ties;
    }
    
    public int getLosses() {
        return this.losses;
    }
    
    public void setLosses(final int losses) {
        this.losses = losses;
    }
    
    public int getScore() {
        int score = 2000;
        if (this.wins + this.ties + this.losses > 0) {
            score += this.wins * 2;
            score += this.ties;
            score -= this.losses * 2;
        }
        return score;
    }
    
    @Override
    public int compareTo(final Object o) {
        final MiniGamePoints mgp = (MiniGamePoints)o;
        final int otherScore = (mgp == null) ? 0 : mgp.getScore();
        return otherScore - this.getScore();
    }
}
