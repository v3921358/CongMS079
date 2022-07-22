package client.inventory;

public enum ItemFlag
{
    LOCK(1), 
    SPIKES(2), 
    COLD(4), 
    UNTRADEABLE(8), 
    KARMA_EQ(16), 
    KARMA_USE(2);
    
    private final int i;
    
    private ItemFlag(final int i) {
        this.i = i;
    }
    
    public final int getValue() {
        return this.i;
    }
    
    public final boolean check(final int flag) {
        return (flag & this.i) == this.i;
    }
}
