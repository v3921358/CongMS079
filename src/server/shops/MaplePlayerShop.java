package server.shops;

import java.util.Iterator;
import server.maps.MapleMapObject;
import client.inventory.IItem;
import tools.packet.PlayerShopPacket;
import constants.GameConstants;
import server.MapleInventoryManipulator;
import tools.MaplePacketCreator;
import client.inventory.ItemFlag;
import client.MapleClient;
import java.util.ArrayList;
import client.MapleCharacter;
import java.util.List;

public class MaplePlayerShop extends AbstractPlayerStore
{
    private int boughtnumber;
    private final List<String> bannedList;
    
    public MaplePlayerShop(final MapleCharacter owner, final int itemId, final String desc) {
        super(owner, itemId, desc, "", 3);
        this.boughtnumber = 0;
        this.bannedList = new ArrayList<String>();
    }
    
    @Override
    public void buy(final MapleClient c, final int item, final short quantity) {
        final MaplePlayerShopItem pItem = (MaplePlayerShopItem)this.items.get(item);
        if (pItem.bundles > 0) {
            final IItem newItem = pItem.item.copy();
            newItem.setQuantity((short)(quantity * newItem.getQuantity()));
            final byte flag = newItem.getFlag();
            if (ItemFlag.KARMA_EQ.check((int)flag)) {
                newItem.setFlag((byte)(flag - ItemFlag.KARMA_EQ.getValue()));
            }
            else if (ItemFlag.KARMA_USE.check((int)flag)) {
                newItem.setFlag((byte)(flag - ItemFlag.KARMA_USE.getValue()));
            }
            final int gainmeso = pItem.price * quantity;
            if (c.getPlayer().getMeso() >= gainmeso) {
                if (!c.getPlayer().canHold(newItem.getItemId())) {
                    c.getPlayer().dropMessage(1, "您的背包滿了.");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (this.getMCOwner().getMeso() + gainmeso > 0 && MapleInventoryManipulator.checkSpace(c, newItem.getItemId(), (int)newItem.getQuantity(), newItem.getOwner()) && MapleInventoryManipulator.addFromDrop(c, newItem, false)) {
                    final MaplePlayerShopItem maplePlayerShopItem = pItem;
                    maplePlayerShopItem.bundles -= quantity;
                    this.bought.add(new BoughtItem(newItem.getItemId(), (int)quantity, gainmeso, c.getPlayer().getName()));
                    c.getPlayer().gainMeso(-gainmeso, false);
                    this.getMCOwner().gainMeso(gainmeso - GameConstants.EntrustedStoreTax(gainmeso), false);
                    if (pItem.bundles <= 0) {
                        ++this.boughtnumber;
                        if (this.boughtnumber == this.items.size()) {
                            this.closeShop(false, true);
                            return;
                        }
                    }
                }
                else {
                    c.getPlayer().dropMessage(1, "你的装备栏已經滿了。");
                }
            }
            else {
                c.getPlayer().dropMessage(1, "你沒有足夠的金币。");
            }
            this.getMCOwner().getClient().sendPacket(PlayerShopPacket.shopItemUpdate((IMaplePlayerShop)this));
        }
    }
    
    @Override
    public byte getShopType() {
        return 2;
    }
    
    @Override
    public void closeShop(final boolean saveItems, final boolean remove) {
        final MapleCharacter owner = this.getMCOwner();
        if (owner != null && owner.getClient() != null) {
            this.removeAllVisitors(3, 1);
            this.setCanShop(false);
            this.getMap().removeMapObject((MapleMapObject)this);
            for (final MaplePlayerShopItem items : this.getItems()) {
                if (items.bundles > 0) {
                    final IItem newItem = items.item.copy();
                    newItem.setQuantity((short)(items.bundles * newItem.getQuantity()));
                    if (!MapleInventoryManipulator.addFromDrop(owner.getClient(), newItem, false)) {
                        this.saveItems();
                        break;
                    }
                    items.bundles = 0;
                }
            }
            owner.setPlayerShop(null);
            this.update();
        }
    }
    
    public void banPlayer(final String name) {
        if (!this.bannedList.contains((Object)name)) {
            this.bannedList.add(name);
        }
        for (int i = 0; i < 3; ++i) {
            final MapleCharacter chr = this.getVisitor(i);
            if (chr.getName().equals((Object)name)) {
                chr.getClient().sendPacket(PlayerShopPacket.shopErrorMessage(5, 1));
                chr.setPlayerShop(null);
                this.removeVisitor(chr);
            }
        }
    }
    
    public boolean isBanned(final String name) {
        return this.bannedList.contains((Object)name);
    }
}
