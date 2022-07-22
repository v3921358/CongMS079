package client.inventory;

public class InventoryException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    
    public InventoryException() {
    }
    
    public InventoryException(final String msg) {
        super(msg);
    }
}
