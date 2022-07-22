package handling;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import tools.StringUtil;
import java.io.FileInputStream;
import java.util.Properties;

public enum RecvPacketOpcode implements WritableIntValueHolder
{
    LOGIN_PASSWORD(1), 
    SERVERLIST_REQUEST(3), 
    CHARLIST_REQUEST(4), 
    CHAR_SELECT(6), 
    PLAYER_LOGGEDIN(7), 
    CHECK_CHAR_NAME(8), 
    CREATE_CHAR(11), 
    CLIENT_FEEDBACK(12), 
    DELETE_CHAR(13), 
    PONG(14), 
    STRANGE_DATA(32767), 
    CLIENT_ERROR(15), 
    HELLO_LOGIN(23), 
    SERVERSTATUS_REQUEST(24), 
    HELLO_CHANNEL(218), 
    SET_GENDER(25), 
    CLIENT_LOGOUT(26), 
    CHANGE_MAP(30), 
    CHANGE_CHANNEL(31), 
    ENTER_CASH_SHOP(32), 
    MOVE_PLAYER(33), 
    CANCEL_CHAIR(34), 
    USE_CHAIR(35), 
    SHOW_EXP_CHAIR(36), 
    CLOSE_RANGE_ATTACK(37), 
    RANGED_ATTACK(38), 
    MAGIC_ATTACK(39), 
    PASSIVE_ENERGY(40), 
    TAKE_DAMAGE(41), 
    GENERAL_CHAT(42), 
    CLOSE_CHALKBOARD(43), 
    FACE_EXPRESSION(44), 
    USE_ITEMEFFECT(45), 
    WHEEL_OF_FORTUNE(46), 
    MONSTER_BOOK_COVER(50), 
    NPC_TALK(51), 
    NPC_TALK_MORE(53), 
    NPC_SHOP(54), 
    STORAGE(55), 
    USE_HIRED_MERCHANT(56), 
    MERCH_ITEM_STORE(58), 
    DUEY_ACTION, 
    ITEM_SORT, 
    ITEM_GATHER, 
    ITEM_MOVE, 
    USE_ITEM, 
    CANCEL_ITEM_EFFECT, 
    USE_SUMMON_BAG, 
    PET_FOOD, 
    USE_MOUNT_FOOD, 
    USE_SCRIPTED_NPC_ITEM, 
    USE_CASH_ITEM, 
    ITEM_UNLOCK, 
    SOLOMON, 
    GACH_EXP, 
    USE_CATCH_ITEM, 
    USE_SKILL_BOOK, 
    USE_RETURN_SCROLL, 
    USE_UPGRADE_SCROLL, 
    DISTRIBUTE_AP, 
    AUTO_ASSIGN_AP, 
    HEAL_OVER_TIME, 
    DISTRIBUTE_SP, 
    SPECIAL_MOVE, 
    CANCEL_BUFF, 
    SKILL_EFFECT, 
    MESO_DROP, 
    GIVE_FAME, 
    CHAR_INFO_REQUEST, 
    SPAWN_PET, 
    CANCEL_DEBUFF, 
    CHANGE_MAP_SPECIAL, 
    USE_INNER_PORTAL, 
    TROCK_ADD_MAP, 
    LIE_DETECTOR, 
    LIE_DETECTOR_SKILL, 
    LIE_DETECTOR_RESPONSE, 
    LIE_DETECTOR_REFRESH, 
    QUEST_ACTION, 
    SKILL_MACRO, 
    REWARD_ITEM(112), 
    ITEM_MAKER, 
    USE_TREASUER_CHEST, 
    PARTYCHAT, 
    WHISPER, 
    MESSENGER, 
    PLAYER_INTERACTION, 
    PARTY_OPERATION, 
    DENY_PARTY_REQUEST, 
    GUILD_OPERATION, 
    DENY_GUILD_REQUEST, 
    BUDDYLIST_MODIFY, 
    NOTE_ACTION, 
    USE_DOOR, 
    CHANGE_KEYMAP, 
    UPDATE_CHAR_INFO, 
    ENTER_MTS, 
    ALLIANCE_OPERATION, 
    DENY_ALLIANCE_REQUEST, 
    REQUEST_FAMILY, 
    OPEN_FAMILY, 
    FAMILY_OPERATION, 
    DELETE_JUNIOR, 
    DELETE_SENIOR, 
    ACCEPT_FAMILY, 
    USE_FAMILY, 
    FAMILY_PRECEPT, 
    FAMILY_SUMMON, 
    CYGNUS_SUMMON, 
    ARAN_COMBO, 
    BBS_OPERATION, 
    TRANSFORM_PLAYER, 
    MOVE_PET, 
    PET_CHAT, 
    PET_COMMAND, 
    PET_LOOT, 
    PET_AUTO_POT, 
    MOVE_SUMMON, 
    SUMMON_ATTACK, 
    DAMAGE_SUMMON, 
    MOVE_LIFE, 
    AUTO_AGGRO, 
    FRIENDLY_DAMAGE, 
    MONSTER_BOMB, 
    HYPNOTIZE_DMG, 
    NPC_ACTION, 
    ITEM_PICKUP, 
    DAMAGE_REACTOR, 
    SNOWBALL, 
    LEFT_KNOCK_BACK, 
    COCONUT, 
    MONSTER_CARNIVAL, 
    CS_UPDATE(229), 
    CASHSHOP_OPERATION(230), 
    COUPON_CODE(231), 
    MAPLETV, 
    REPAIR, 
    REPAIR_ALL, 
    TOUCHING_MTS, 
    USE_MAGNIFY_GLASS, 
    USE_POTENTIAL_SCROLL, 
    USE_EQUIP_SCROLL, 
    GAME_POLL, 
    OWL, 
    OWL_WARP, 
    USE_OWL_MINERVA, 
    RPS_GAME, 
    UPDATE_QUEST, 
    USE_ITEM_QUEST, 
    FOLLOW_REQUEST, 
    FOLLOW_REPLY, 
    MOB_NODE, 
    DISPLAY_NODE, 
    TOUCH_REACTOR, 
    RING_ACTION, 
    MTS_TAB, 
    SPECIAL_ATTACK, 
    PET_IGNORE, 
    BEANS_OPERATION, 
    LICENSE_REQUEST, 
    ITEM_SUNZI, 
    BEANS_UPDATE;
    
    private short code;
    private boolean CheckState;
    
    @Override
    public void setValue(final short code) {
        this.code = code;
    }
    
    @Override
    public final short getValue() {
        return this.code;
    }
    
    private RecvPacketOpcode() {
        this.code = -2;
        this.CheckState = true;
    }
    
    private RecvPacketOpcode(final int code) {
        this.code = -2;
        this.code = (short)code;
        this.CheckState = false;
    }
    
    private RecvPacketOpcode(final short code, final boolean CheckState) {
        this.code = -2;
        this.code = code;
        this.CheckState = CheckState;
    }
    
    private RecvPacketOpcode(final boolean CheckState) {
        this.code = -2;
        this.CheckState = CheckState;
    }
    
    public final boolean NeedsChecking() {
        return this.CheckState;
    }
    
    public static String nameOf(final short value) {
        for (final RecvPacketOpcode header : values()) {
            if (header.getValue() == value) {
                return header.name();
            }
        }
        return "UNKNOWN";
    }
    
    public static boolean isSpamHeader(final RecvPacketOpcode header) {
        final String name = header.name();
        int n = -1;
        switch (name.hashCode()) {
            case 2461688: {
                if (name.equals((Object)"PONG")) {
                    n = 0;
                    break;
                }
                break;
            }
            case 132404596: {
                if (name.equals((Object)"NPC_ACTION")) {
                    n = 1;
                    break;
                }
                break;
            }
        }
        switch (n) {
            case 0:
            case 1: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static final void reloadValues() {
        final String fileName = "recv.ini";
        final Properties props = new Properties();
        try (final FileInputStream fileInputStream = new FileInputStream(fileName);
             final BufferedReader br = new BufferedReader((Reader)new InputStreamReader((InputStream)fileInputStream, StringUtil.codeString(fileName)))) {
            props.load((Reader)br);
        }
        catch (IOException ex) {
            final InputStream in = RecvPacketOpcode.class.getClassLoader().getResourceAsStream("properties/" + fileName);
            if (in == null) {
                System.err.println("錯誤: 未加載 " + fileName + " 檔案");
                return;
            }
            try {
                props.load(in);
                in.close();
            }
            catch (IOException e) {
                throw new RuntimeException("加載 " + fileName + " 檔案出錯", (Throwable)e);
            }
        }
        ExternalCodeTableGetter.populateValues(props, values());
    }
    
    static {
        reloadValues();
    }
}
