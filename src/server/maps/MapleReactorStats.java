package server.maps;

import tools.Pair;
import java.util.HashMap;
import java.util.Map;
import java.awt.Point;

public class MapleReactorStats
{
    private byte facingDirection;
    private Point tl;
    private Point br;
    private Map<Byte, StateData> stateInfo;
    
    public MapleReactorStats() {
        this.stateInfo = new HashMap<Byte, StateData>();
    }
    
    public final void setFacingDirection(final byte facingDirection) {
        this.facingDirection = facingDirection;
    }
    
    public final byte getFacingDirection() {
        return this.facingDirection;
    }
    
    public void setTL(final Point tl) {
        this.tl = tl;
    }
    
    public void setBR(final Point br) {
        this.br = br;
    }
    
    public Point getTL() {
        return this.tl;
    }
    
    public Point getBR() {
        return this.br;
    }
    
    public void addState(final byte state, final int type, final Pair<Integer, Integer> reactItem, final byte nextState, final int timeOut) {
        final StateData newState = new StateData(type, (Pair)reactItem, nextState, timeOut);
        this.stateInfo.put(Byte.valueOf(state), newState);
    }
    
    public byte getNextState(final byte state) {
        final StateData nextState = (StateData)this.stateInfo.get((Object)Byte.valueOf(state));
        if (nextState != null) {
            return nextState.getNextState();
        }
        return -1;
    }
    
    public int getType(final byte state) {
        final StateData nextState = (StateData)this.stateInfo.get((Object)Byte.valueOf(state));
        if (nextState != null) {
            return nextState.getType();
        }
        return -1;
    }
    
    public Pair<Integer, Integer> getReactItem(final byte state) {
        final StateData nextState = (StateData)this.stateInfo.get((Object)Byte.valueOf(state));
        if (nextState != null) {
            return nextState.getReactItem();
        }
        return null;
    }
    
    public int getTimeOut(final byte state) {
        final StateData nextState = (StateData)this.stateInfo.get((Object)Byte.valueOf(state));
        if (nextState != null) {
            return nextState.getTimeOut();
        }
        return -1;
    }
    
    private static class StateData
    {
        private int type;
        private int timeOut;
        private Pair<Integer, Integer> reactItem;
        private byte nextState;
        
        private StateData(final int type, final Pair<Integer, Integer> reactItem, final byte nextState, final int timeOut) {
            this.type = type;
            this.reactItem = reactItem;
            this.nextState = nextState;
            this.timeOut = timeOut;
        }
        
        private int getType() {
            return this.type;
        }
        
        private byte getNextState() {
            return this.nextState;
        }
        
        private Pair<Integer, Integer> getReactItem() {
            return this.reactItem;
        }
        
        private int getTimeOut() {
            return this.timeOut;
        }
    }
}
