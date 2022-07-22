package server.movement;

import tools.data.MaplePacketLittleEndianWriter;
import java.awt.Point;

public class StaticLifeMovement extends AbstractLifeMovement
{
    private Point pixelsPerSecond;
    private short unk;
    private short fh;
    private int wui;
    
    public StaticLifeMovement(final int type, final Point position, final int duration, final int newstate, final int newfh) {
        super(type, position, duration, newstate, newfh);
    }
    
    public void setPixelsPerSecond(final Point wobble) {
        this.pixelsPerSecond = wobble;
    }
    
    public void setFh(final short fh) {
        this.fh = fh;
    }
    
    public void setUnk(final short unk) {
        this.unk = unk;
    }
    
    public short getUnk() {
        return this.unk;
    }
    
    public void setWui(final int wui) {
        this.wui = wui;
    }
    
    public void defaulted() {
        this.unk = 0;
        this.fh = 0;
        this.pixelsPerSecond = new Point(0, 0);
        this.wui = 0;
    }
    
    @Override
    public void serialize(final MaplePacketLittleEndianWriter lew) {
        lew.write(this.getType());
        switch (this.getType()) {
            case 0:
            case 5:
            case 15:
            case 17: {
                lew.writePos(this.getPosition());
                lew.writePos(this.pixelsPerSecond);
                lew.writeShort((int)this.unk);
                if (this.getType() == 15) {
                    lew.writeShort((int)this.fh);
                    break;
                }
                break;
            }
            case 1:
            case 2:
            case 6:
            case 12:
            case 13:
            case 16:
            case 18:
            case 19:
            case 22: {
                lew.writePos(this.pixelsPerSecond);
                break;
            }
            case 3:
            case 4:
            case 7:
            case 8:
            case 9:
            case 11: {
                lew.writePos(this.getPosition());
                lew.writeShort((int)this.unk);
                break;
            }
            case 14: {
                lew.writePos(this.pixelsPerSecond);
                lew.writeShort((int)this.fh);
                break;
            }
        }
        if (this.getType() != 10) {
            lew.write(this.getNewstate());
            lew.writeShort(this.getDuration());
        }
        else {
            lew.write(this.wui);
        }
    }
}
