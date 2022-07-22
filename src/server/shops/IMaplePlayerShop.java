package server.shops;

import java.util.List;

import client.MapleCharacter;
import client.MapleClient;
import server.shops.AbstractPlayerStore.BoughtItem;
import tools.Pair;

public interface IMaplePlayerShop
{
    public static final byte HIRED_FISHING = -1;
    public static final byte HIRED_MERCHANT = 1;
    public static final byte PLAYER_SHOP = 2;
    public static final byte OMOK = 3;
    public static final byte MATCH_CARD = 4;
    
    String getOwnerName();
    
    String getDescription();
    
    List<Pair<Byte, MapleCharacter>> getVisitors();
    
    List<MaplePlayerShopItem> getItems();
    
    List<Pair<String, Byte>> getMessages();
    
    boolean isOpen();
    
    boolean removeItem(final int p0);
    
    boolean isOwner(final MapleCharacter p0);
    
    byte getShopType();
    
    byte getVisitorSlot(final MapleCharacter p0);
    
    byte getFreeSlot();
    
    int getItemId();
    
    int getMeso();
    
    int getOwnerId();
    
    int getOwnerAccId();
    
    void setOpen(final boolean p0);
    
    void setMeso(final int p0);
    
    void addItem(final MaplePlayerShopItem p0);
    
    void removeFromSlot(final int p0);
    
    void broadcastToVisitors(final byte[] p0);
    
    void addVisitor(final MapleCharacter p0);
    
    void removeVisitor(final MapleCharacter p0);
    
    void removeAllVisitors(final int p0, final int p1);
    
    void buy(final MapleClient p0, final int p1, final short p2);
    
    void closeShop(final boolean p0, final boolean p1);
    
    String getPassword();
    
    int getMaxSize();
    
    int getSize();
    
    int getGameType();
    
    void update();
    
    void setAvailable(final boolean p0);
    
    boolean isAvailable();
    
    List<BoughtItem> getBoughtItems();
    
    boolean getCanShop();
    
    void setCanShop(final boolean p0);
}
