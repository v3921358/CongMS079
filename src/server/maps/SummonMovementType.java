package server.maps;

public enum SummonMovementType
{
    STATIONARY(0), 
    FOLLOW(1), 
    WALK_STATIONARY(2), 
    CIRCLE_FOLLOW(3), 
    CIRCLE_STATIONARY(4);
    
    private final int val;
    
    private SummonMovementType(final int val) {
        this.val = val;
    }
    
    public int getValue() {
        return this.val;
    }
}
