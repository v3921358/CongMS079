package server.shops;

import client.inventory.IItem;

public class MaplePlayerShopItem
{
    public IItem item;
    public short bundles;
    public int price;
    
    public MaplePlayerShopItem(final IItem item, final short bundles, final int price) {
        this.item = item;
        this.bundles = bundles;
        this.price = price;
    }
}
