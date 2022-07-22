package abc;

public class 离线人偶
{
    public int charId;
    public int AccId;
    public int x;
    public int y;
    public int chairId;
    public int channel;
    public long liftTime;
    
    public 离线人偶() {
        this.AccId = 0;
        this.charId = 0;
        this.x = 0;
        this.y = 0;
        this.chairId = 0;
        this.channel = 0;
        this.liftTime = 0L;
    }
    
    public 离线人偶(final int accid, final int cid, final int dx, final int dy, final int chair, final int nowchannel) {
        this.AccId = accid;
        this.charId = cid;
        this.x = dx;
        this.y = dy;
        this.chairId = chair;
        this.channel = nowchannel;
        this.liftTime = System.currentTimeMillis();
    }
    
    public 离线人偶(final int accid, final int cid, final int dx, final int dy, final int chair, final int nowchannel, final long nowtime) {
        this.AccId = accid;
        this.charId = cid;
        this.x = dx;
        this.y = dy;
        this.chairId = chair;
        this.channel = nowchannel;
        this.liftTime = nowtime;
    }
    
    public int getChannel() {
        return this.channel;
    }
    
    public void setChannel(final int nowchannel) {
        this.channel = nowchannel;
    }
    
    public void setLiftTime() {
        this.liftTime = -1L;
    }
    
    public long getLiftTime() {
        return this.liftTime;
    }
    
    public int getAccId() {
        return this.AccId;
    }
    
    public int getCharId() {
        return this.charId;
    }
    
    public int getChairId() {
        return this.chairId;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setCharId(final int id) {
        this.charId = id;
    }
    
    public void setAccId(final int id) {
        this.AccId = id;
    }
    
    public void setX(final int dx) {
        this.x = dx;
    }
    
    public void setY(final int dy) {
        this.y = dy;
    }
    
    public void setChairId(final int chair) {
        this.chairId = chair;
    }
}
