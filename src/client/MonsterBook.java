package client;

import tools.MaplePacketCreator;
import tools.packet.MonsterBookPacket;
import server.MapleItemInformationProvider;
import tools.data.MaplePacketLittleEndianWriter;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import tools.FileoutputUtil;
import java.util.LinkedHashMap;
import database.DBConPool;
import java.util.Iterator;
import constants.GameConstants;
import java.util.Map.Entry;
import java.util.Map;
import java.io.Serializable;

public class MonsterBook implements Serializable
{
    private static final long serialVersionUID = 7179541993413738569L;
    private boolean changed;
    private int SpecialCard;
    private int NormalCard;
    private int BookLevel;
    private final Map<Integer, Integer> cards;
    
    public MonsterBook(final Map<Integer, Integer> cards) {
        this.changed = false;
        this.SpecialCard = 0;
        this.NormalCard = 0;
        this.BookLevel = 1;
        this.cards = cards;
        for (final Entry<Integer, Integer> card : cards.entrySet()) {
            if (GameConstants.isSpecialCard((int)Integer.valueOf(card.getKey()))) {
                this.SpecialCard += (int)Integer.valueOf(card.getValue());
            }
            else {
                this.NormalCard += (int)Integer.valueOf(card.getValue());
            }
        }
        this.calculateLevel();
    }
    
    public Map<Integer, Integer> getCards() {
        return this.cards;
    }
    
    public final int getTotalCards() {
        return this.SpecialCard + this.NormalCard;
    }
    
    public final int getLevelByCard(final int cardid) {
        return (this.cards.get((Object)Integer.valueOf(cardid)) == null) ? 0 : ((int)Integer.valueOf(this.cards.get((Object)Integer.valueOf(cardid))));
    }
    
    public static final MonsterBook loadCards(final int charid) throws SQLException {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM monsterbook WHERE charid = ? ORDER BY cardid ASC")) {
            ps.setInt(1, charid);
            Map<Integer, Integer> cards;
            try (final ResultSet rs = ps.executeQuery()) {
                cards = new LinkedHashMap<Integer, Integer>();
                while (rs.next()) {
                    cards.put(Integer.valueOf(rs.getInt("cardid")), Integer.valueOf(rs.getInt("level")));
                }
            }
            return new MonsterBook(cards);
        }
        catch (SQLException ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            return null;
        }
    }
    
    public final void saveCards(final int charid, final Connection con) throws SQLException {
        if (!this.changed || this.cards.isEmpty()) {
            return;
        }
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM monsterbook WHERE charid = ?");
            ps.setInt(1, charid);
            ps.execute();
            ps.close();
            boolean first = true;
            final StringBuilder query = new StringBuilder();
            for (final Entry<Integer, Integer> all : this.cards.entrySet()) {
                if (first) {
                    first = false;
                    query.append("INSERT INTO monsterbook VALUES (DEFAULT,");
                }
                else {
                    query.append(",(DEFAULT,");
                }
                query.append(charid);
                query.append(",");
                query.append((Object)all.getKey());
                query.append(",");
                query.append((Object)all.getValue());
                query.append(")");
            }
            ps = con.prepareStatement(query.toString());
            ps.execute();
            ps.close();
        }
        catch (Exception se) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)se);
        }
    }
    
    private void calculateLevel() {
        final int Size = this.NormalCard + this.SpecialCard;
        this.BookLevel = 8;
        for (int i = 0; i < 8; ++i) {
            if (Size <= GameConstants.getBookLevel(i)) {
                this.BookLevel = i + 1;
                break;
            }
        }
    }
    
    public final void addCardPacket(final MaplePacketLittleEndianWriter mplew) {
        mplew.writeShort(this.cards.size());
        for (final Entry<Integer, Integer> all : this.cards.entrySet()) {
            mplew.writeShort(GameConstants.getCardShortId((int)Integer.valueOf(all.getKey())));
            mplew.write((int)Integer.valueOf(all.getValue()));
        }
    }
    
    public final void addCharInfoPacket(final int bookcover, final MaplePacketLittleEndianWriter mplew) {
        mplew.writeInt(this.BookLevel);
        mplew.writeInt(this.NormalCard);
        mplew.writeInt(this.SpecialCard);
        mplew.writeInt(this.NormalCard + this.SpecialCard);
        mplew.writeInt(MapleItemInformationProvider.getInstance().getCardMobId(bookcover));
    }
    
    public final void updateCard(final MapleClient c, final int cardid) {
        c.sendPacket(MonsterBookPacket.changeCover(cardid));
    }
    
    public final void addCard(final MapleClient c, final int cardid) {
        this.changed = true;
        c.getPlayer().getMap().broadcastMessage(c.getPlayer(), MonsterBookPacket.showForeginCardEffect(c.getPlayer().getId()), false);
        if (this.cards.containsKey((Object)Integer.valueOf(cardid))) {
            final int levels = (int)Integer.valueOf(this.cards.get((Object)Integer.valueOf(cardid)));
            if (levels >= 5) {
                c.sendPacket(MonsterBookPacket.addCard(true, cardid, levels));
            }
            else {
                if (GameConstants.isSpecialCard(cardid)) {
                    ++this.SpecialCard;
                }
                else {
                    ++this.NormalCard;
                }
                c.sendPacket(MonsterBookPacket.addCard(false, cardid, levels));
                c.sendPacket(MonsterBookPacket.showGainCard(cardid));
                c.sendPacket(MaplePacketCreator.showSpecialEffect(15));
                this.cards.put(Integer.valueOf(cardid), Integer.valueOf(levels + 1));
                this.calculateLevel();
            }
            return;
        }
        if (GameConstants.isSpecialCard(cardid)) {
            ++this.SpecialCard;
        }
        else {
            ++this.NormalCard;
        }
        this.cards.put(Integer.valueOf(cardid), Integer.valueOf(1));
        c.sendPacket(MonsterBookPacket.addCard(false, cardid, 1));
        c.sendPacket(MonsterBookPacket.showGainCard(cardid));
        c.sendPacket(MaplePacketCreator.showSpecialEffect(15));
        this.calculateLevel();
    }
}
