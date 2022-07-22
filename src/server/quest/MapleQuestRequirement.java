package server.quest;

import client.inventory.MapleInventoryType;
import client.MapleQuestStatus;
import client.ISkill;
import java.util.Iterator;
import client.inventory.MaplePet;
import java.util.Calendar;
import client.inventory.IItem;
import constants.GameConstants;
import client.SkillFactory;
import client.MapleCharacter;
import java.sql.SQLException;
import java.util.LinkedList;
import java.sql.ResultSet;
import tools.Pair;
import java.util.List;
import java.io.Serializable;

public class MapleQuestRequirement implements Serializable
{
    private static final long serialVersionUID = 9179541993413738569L;
    private MapleQuest quest;
    private MapleQuestRequirementType type;
    private int intStore;
    private String stringStore;
    private List<Pair<Integer, Integer>> dataStore;
    
    public MapleQuestRequirement(final MapleQuest quest, final MapleQuestRequirementType type, final ResultSet rse) throws SQLException {
        this.type = type;
        this.quest = quest;
        switch (type) {
            case pet:
            case mbcard:
            case mob:
            case item:
            case quest:
            case skill:
            case job: {
                this.dataStore = new LinkedList<Pair<Integer, Integer>>();
                final String[] first = rse.getString("intStoresFirst").split(", ");
                final String[] second = rse.getString("intStoresSecond").split(", ");
                if (first.length <= 0 && rse.getString("intStoresFirst").length() > 0) {
                    this.dataStore.add(new Pair<Integer, Integer>(Integer.valueOf(Integer.parseInt(rse.getString("intStoresFirst"))), Integer.valueOf(Integer.parseInt(rse.getString("intStoresSecond")))));
                }
                for (int i = 0; i < first.length; ++i) {
                    if (first[i].length() > 0 && second[i].length() > 0) {
                        this.dataStore.add(new Pair<Integer, Integer>(Integer.valueOf(Integer.parseInt(first[i])), Integer.valueOf(Integer.parseInt(second[i]))));
                    }
                }
                break;
            }
            case partyQuest_S:
            case dayByDay:
            case normalAutoStart:
            case subJobFlags:
            case fieldEnter:
            case pettamenessmin:
            case npc:
            case questComplete:
            case pop:
            case interval:
            case mbmin:
            case lvmax:
            case lvmin: {
                this.intStore = Integer.parseInt(rse.getString("stringStore"));
                break;
            }
            case end: {
                this.stringStore = rse.getString("stringStore");
                break;
            }
        }
    }
    
    public boolean check(final MapleCharacter c, final Integer npcid) {
        int skill;
        IItem item;
        MaplePet pet;
        switch (this.type) {
            case job: {
                for (final Pair<Integer, Integer> a : this.dataStore) {
                    if ((int)Integer.valueOf(a.getRight()) == c.getJob() || c.isGM()) {
                        return true;
                    }
                }
                return false;
            }
            case skill: {
                for (final Pair<Integer, Integer> a : this.dataStore) {
                    final boolean acquire = (int)Integer.valueOf(a.getRight()) > 0;
                    skill = (int)Integer.valueOf(a.getLeft());
                    final ISkill skil = SkillFactory.getSkill(skill);
                    if (acquire) {
                        if (skil.isFourthJob()) {
                            if (c.getMasterLevel(skil) == 0) {
                                return false;
                            }
                            continue;
                        }
                        else {
                            if (c.getSkillLevel(skil) == 0) {
                                return false;
                            }
                            continue;
                        }
                    }
                    else {
                        if (c.getSkillLevel(skil) > 0 || c.getMasterLevel(skil) > 0) {
                            return false;
                        }
                        continue;
                    }
                }
                return true;
            }
            case quest: {
                for (final Pair<Integer, Integer> a : this.dataStore) {
                    final MapleQuestStatus q = c.getQuest(MapleQuest.getInstance((int)Integer.valueOf(a.getLeft())));
                    final int state = (int)Integer.valueOf(a.getRight());
                    if (state != 0) {
                        if (q == null && state == 0) {
                            continue;
                        }
                        if (q == null || q.getStatus() != state) {
                            return false;
                        }
                        continue;
                    }
                }
                return true;
            }
            case item: {
                for (final Pair<Integer, Integer> a2 : this.dataStore) {
                    final int itemId = (int)Integer.valueOf(a2.getLeft());
                    short quantity = 0;
                    final MapleInventoryType iType = GameConstants.getInventoryType(itemId);
                    final Iterator<IItem> iterator5 = c.getInventory(iType).listById(itemId).iterator();
                    while (iterator5.hasNext()) {
                        item = (IItem)iterator5.next();
                        quantity += item.getQuantity();
                    }
                    final int count = (int)Integer.valueOf(a2.getRight());
                    if (quantity < count || (count <= 0 && quantity > 0)) {
                        return false;
                    }
                }
                return true;
            }
            case lvmin: {
                return c.getLevel() >= this.intStore;
            }
            case lvmax: {
                return c.getLevel() <= this.intStore;
            }
            case end: {
                final String timeStr = this.stringStore;
                if (timeStr == null || timeStr.length() <= 0) {
                    return true;
                }
                final Calendar cal = Calendar.getInstance();
                cal.set(Integer.parseInt(timeStr.substring(0, 4)), Integer.parseInt(timeStr.substring(4, 6)), Integer.parseInt(timeStr.substring(6, 8)), Integer.parseInt(timeStr.substring(8, 10)), 0);
                return cal.getTimeInMillis() >= System.currentTimeMillis();
            }
            case mob: {
                for (final Pair<Integer, Integer> a3 : this.dataStore) {
                    final int mobId = (int)Integer.valueOf(a3.getLeft());
                    final int killReq = (int)Integer.valueOf(a3.getRight());
                    if (c.getQuest(this.quest).getMobKills(mobId) < killReq) {
                        return false;
                    }
                }
                return true;
            }
            case npc: {
                return npcid == null || (int)npcid == this.intStore;
            }
            case fieldEnter: {
                return this.intStore <= 0 || this.intStore == c.getMapId();
            }
            case mbmin: {
                return c.getMonsterBook().getTotalCards() >= this.intStore;
            }
            case mbcard: {
                for (final Pair<Integer, Integer> a3 : this.dataStore) {
                    final int cardId = (int)Integer.valueOf(a3.getLeft());
                    final int killReq = (int)Integer.valueOf(a3.getRight());
                    if (c.getMonsterBook().getLevelByCard(cardId) < killReq) {
                        return false;
                    }
                }
                return true;
            }
            case pop: {
                return c.getFame() >= this.intStore;
            }
            case questComplete: {
                return c.getNumQuest() >= this.intStore;
            }
            case interval: {
                return c.getQuest(this.quest).getStatus() != 2 || c.getQuest(this.quest).getCompletionTime() <= System.currentTimeMillis() - (long)(this.intStore * 60) * 1000L;
            }
            case pet: {
                for (final Pair<Integer, Integer> a3 : this.dataStore) {
                    if (c.getPetIndex((int)Integer.valueOf(a3.getRight())) != -1) {
                        return true;
                    }
                }
                return false;
            }
            case pettamenessmin: {
                final Iterator<MaplePet> iterator9 = c.getPets().iterator();
                while (iterator9.hasNext()) {
                    pet = (MaplePet)iterator9.next();
                    if (pet.getSummoned() && pet.getCloseness() >= this.intStore) {
                        return true;
                    }
                }
                return false;
            }
            case partyQuest_S: {
                final int[] partyQuests = { 1200, 1201, 1202, 1203, 1204, 1205, 1206, 1300, 1301, 1302 };
                int sRankings = 0;
                for (final int i : partyQuests) {
                    final String rank = c.getOneInfo(i, "rank");
                    if (rank != null && rank.equals((Object)"S")) {
                        ++sRankings;
                    }
                }
                return sRankings >= 5;
            }
            case subJobFlags: {
                return c.getSubcategory() == this.intStore / 2;
            }
            default: {
                return true;
            }
        }
    }
    
    public MapleQuestRequirementType getType() {
        return this.type;
    }
    
    @Override
    public String toString() {
        return this.type.toString();
    }
    
    public List<Pair<Integer, Integer>> getDataStore() {
        return this.dataStore;
    }
}
