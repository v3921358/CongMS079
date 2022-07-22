package server;

public class MapleShopItem
{
    private final short buyable;
    private final int itemId;
    private final int price;
    private final int reqItem;
    private final int reqItemQ;
    
    public MapleShopItem(final short buyable, final int itemId, final int price, final int reqItem, final int reqItemQ) {
        this.buyable = buyable;
        this.itemId = itemId;
        this.price = price;
        this.reqItem = reqItem;
        this.reqItemQ = reqItemQ;
    }
    
    public short getBuyable() {
        return this.buyable;
    }
    
    public int getItemId() {
        return this.itemId;
    }
    
    public int getPrice() {
        return this.price;
    }
    
    public int getReqItem() {
        return this.reqItem;
    }
    
    public int getReqItemQ() {
        return this.reqItemQ;
    }
}
