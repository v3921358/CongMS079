package server;

public class StructRewardItem
{
    private int itemid;
    private long period;
    private short prob;
    private short quantity;
    private String effect;
    private String worldmsg;
    
    public int getItemid() {
        return this.itemid;
    }
    
    public void setItemid(final int itemid) {
        this.itemid = itemid;
    }
    
    public long getPeriod() {
        return this.period;
    }
    
    public void setPeriod(final long period) {
        this.period = period;
    }
    
    public short getProb() {
        return this.prob;
    }
    
    public void setProb(final short prob) {
        this.prob = prob;
    }
    
    public short getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(final short quantity) {
        this.quantity = quantity;
    }
    
    public String getEffect() {
        return this.effect;
    }
    
    public void setEffect(final String effect) {
        this.effect = effect;
    }
    
    public String getWorldmsg() {
        return this.worldmsg;
    }
    
    public void setWorldmsg(final String worldmsg) {
        this.worldmsg = worldmsg;
    }
}
