package client.inventory;

import constants.GameConstants;

public class ModifyInventory
{
    private final int mode;
    private IItem item;
    private short oldPos;
    
    public ModifyInventory(final int mode, final IItem item) {
        this.mode = mode;
        this.item = item.copy();
    }
    
    public ModifyInventory(final int mode, final IItem item, final short oldPos) {
        this.mode = mode;
        this.item = item.copy();
        this.oldPos = oldPos;
    }
    
    public final int getMode() {
        return this.mode;
    }
    
    public final int getInventoryType() {
        return GameConstants.getInventoryType(this.item.getItemId()).getType();
    }
    
    public final short getPosition() {
        return this.item.getPosition();
    }
    
    public final short getOldPosition() {
        return this.oldPos;
    }
    
    public final short getQuantity() {
        return this.item.getQuantity();
    }
    
    public final IItem getItem() {
        return this.item;
    }
    
    public final void clear() {
        this.item = null;
    }
    
    public static class Types
    {
        public static final int ADD = 0;
        public static final int UPDATE = 1;
        public static final int MOVE = 2;
        public static final int REMOVE = 3;
    }
}
