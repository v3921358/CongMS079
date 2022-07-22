package server.movement;

import java.awt.Point;

public abstract class AbstractLifeMovement implements LifeMovement
{
    private final Point position;
    private final int duration;
    private final int newstate;
    private final int newfh;
    private final int type;
    
    public AbstractLifeMovement(final int type, final Point position, final int duration, final int newstate, final int newfh) {
        this.type = type;
        this.position = position;
        this.duration = duration;
        this.newstate = newstate;
        this.newfh = newfh;
    }
    
    @Override
    public int getType() {
        return this.type;
    }
    
    @Override
    public int getDuration() {
        return this.duration;
    }
    
    @Override
    public int getNewstate() {
        return this.newstate;
    }
    
    @Override
    public int getNewFh() {
        return this.newfh;
    }
    
    @Override
    public Point getPosition() {
        return this.position;
    }
}
