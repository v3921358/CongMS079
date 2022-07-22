package handling;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import tools.StringUtil;
import java.io.FileInputStream;
import java.util.Properties;

public enum SendPacketOpcode implements WritableIntValueHolder
{
    SPAWN_KITE_ERROR(265), 
    SPAWN_KITE(266), 
    DESTROY_KITE(267), 
    LOGIN_STATUS(0), 
    SERVERLIST(2), 
    CHARLIST(3), 
    SERVER_IP(4), 
    CHAR_NAME_RESPONSE(5), 
    LICENSE_RESULT, 
    ADD_NEW_CHAR_ENTRY(6), 
    DELETE_CHAR_RESPONSE(7), 
    CHANGE_CHANNEL(8), 
    PING(9), 
    CS_USE(10), 
    CHANNEL_SELECTED(13), 
    RELOG_RESPONSE(15), 
    SECONDPW_ERROR(16), 
    CHOOSE_GENDER(20), 
    GENDER_SET(21), 
    SERVERSTATUS(22), 
    MODIFY_INVENTORY_ITEM(27), 
    UPDATE_INVENTORY_SLOT(28), 
    UPDATE_STATS(29), 
    GIVE_BUFF(30), 
    CANCEL_BUFF(31), 
    TEMP_STATS(32), 
    TEMP_STATS_RESET(33), 
    UPDATE_SKILLS(34), 
    SKILL_USE_RESULT(35), 
    FAME_RESPONSE(36), 
    SHOW_STATUS_INFO(37), 
    SHOW_NOTES(38), 
    MAP_TRANSFER_RESULT(39), 
    LIE_DETECTOR(40), 
    CLAIM_RESULT(42), 
    CLAIM_STATUS_CHANGED(46), 
    SET_TAMING_MOB_INFO(45), 
    SHOW_QUEST_COMPLETION(46), 
    ENTRUSTED_SHOP_CHECK_RESULT(47), 
    USE_SKILL_BOOK(49), 
    GATHER_ITEM_RESULT(50), 
    SORT_ITEM_RESULT(51), 
    CHAR_INFO(54), 
    PARTY_OPERATION(55), 
    BUDDYLIST(56), 
    GUILD_OPERATION(58), 
    ALLIANCE_OPERATION(59), 
    SPAWN_PORTAL(60), 
    SERVERMESSAGE(61), 
    INCUBATOR_RESULT(62), 
    PIGMI_REWARD(65534), 
    SHOP_SCANNER_RESULT(63), 
    SHOP_LINK_RESULT(64), 
    MARRIAGE_REQUEST(65), 
    MARRIAGE_RESULT(66), 
    WEDDING_GIFT_RESULT(67), 
    NOTIFY_MARRIED_PARTNER_MAP_TRANSFER(68), 
    CASH_PET_FOOD_RESULT(69), 
    SET_WEEK_EVENT_MESSAGE(70), 
    SET_POTION_DISCOUNT_RATE(71), 
    BRIDE_MOB_CATCH_FAIL(72), 
    IMITATED_NPC_RESULT(74), 
    IMITATED_NPC_DATA(75), 
    LIMITED_NPC_DISABLE_INFO(76), 
    MONSTERBOOK_ADD(77), 
    MONSTERBOOK_CHANGE_COVER(78), 
    HOUR_CHANGED(79), 
    MINIMAP_ON_OFF(80), 
    CONSULT_AUTHKEY_UPDATE(81), 
    CLASS_COMPETITION_AUTHKEY_UPDATE(82), 
    WEB_BOARD_AUTHKEY_UPDATE(83), 
    SESSION_VALUE(84), 
    BONUS_EXP_CHANGED(85), 
    FAMILY_CHART_RESULT(86), 
    FAMILY_INFO_RESULT(87), 
    FAMILY_RESULT(88), 
    FAMILY_JOIN_REQUEST(89), 
    FAMILY_JOIN_REQUEST_RESULT(90), 
    FAMILY_JOIN_ACCEPTED(91), 
    FAMILY_PRIVILEGE_LIST(92), 
    FAMILY_FAMOUS_POINT_INC_RESULT(93), 
    FAMILY_NOTIFY_LOGIN_OR_LOGOUT(94), 
    FAMILY_SET_PRIVILEGE(95), 
    FAMILY_SUMMON_REQUEST(96), 
    LEVEL_UPDATE(97), 
    MARRIAGE_UPDATE(98), 
    JOB_UPDATE(99), 
    SET_BUY_EQUIP_EXT(100), 
    SCRIPT_PROGRESS_MESSAGE(101), 
    DATA_CRC_CHECK_FAILED(102), 
    BBS_OPERATION(104), 
    FISHING_BOARD_UPDATE(105), 
    AVATAR_MEGA(109), 
    SKILL_MACRO(122), 
    SET_FIELD(123), 
    SET_ITC(124), 
    SET_CASH_SHOP(125), 
    SET_MAP_OBJECT_VISIBLE(127), 
    CLEAR_BACK_EFFECT(128), 
    MAP_BLOCKED(129), 
    SERVER_BLOCKED(130), 
    SHOW_EQUIP_EFFECT(131), 
    MULTICHAT(132), 
    WHISPER(133), 
    SPOUSE_CHAT(134), 
    BOSS_ENV(135), 
    MOVE_ENV(136), 
    CASH_SONG(137), 
    GM_EFFECT(138), 
    OX_QUIZ(139), 
    GMEVENT_INSTRUCTIONS(140), 
    CLOCK(141), 
    BOAT_EFFECT(142), 
    BOAT_PACKET(143), 
    STOP_CLOCK(147), 
    PYRAMID_UPDATE(148), 
    PYRAMID_RESULT(149), 
    MOVE_PLATFORM(150), 
    SPAWN_PLAYER(153), 
    REMOVE_PLAYER_FROM_MAP(154), 
    CHATTEXT(155), 
    CHALKBOARD(156), 
    UPDATE_CHAR_BOX(157), 
    SHOW_CONSUME_EFFECT(158), 
    SHOW_SCROLL_EFFECT(159), 
    FISHING_CAUGHT(160), 
    HIT_BY_USER(161), 
    SPAWN_PET(162), 
    MOVE_PET(165), 
    PET_CHAT(166), 
    PET_NAMECHANGE(167), 
    PET_EXCEPTION_LIST(168), 
    PET_COMMAND(169), 
    SPAWN_SUMMON(170), 
    REMOVE_SUMMON(171), 
    SUMMON_ATTACK(173), 
    MOVE_SUMMON(174), 
    DAMAGE_SUMMON(175), 
    MOVE_PLAYER(177), 
    CLOSE_RANGE_ATTACK(178), 
    RANGED_ATTACK(179), 
    MAGIC_ATTACK(180), 
    ENERGY_ATTACK(181), 
    SKILL_EFFECT(182), 
    CANCEL_SKILL_EFFECT(183), 
    DAMAGE_PLAYER(184), 
    FACIAL_EXPRESSION(185), 
    SHOW_ITEM_EFFECT(186), 
    SHOW_CHAIR(189), 
    UPDATE_CHAR_LOOK(190), 
    ACTIVE_PORTABLE_CHAIR(189), 
    AVARTAR_MODFIED(190), 
    SHOW_FOREIGN_EFFECT(191), 
    GIVE_FOREIGN_BUFF(192), 
    CANCEL_FOREIGN_BUFF(193), 
    UPDATE_PARTYMEMBER_HP(194), 
    GUILD_NAME_CHANGED(195), 
    GUILD_MARK_CHANGED(196), 
    THROW_GRENADE(197), 
    CANCEL_CHAIR(198), 
    SHOW_ITEM_GAIN_INCHAT(199), 
    CURRENT_MAP_WARP(200), 
    MESOBAG_SUCCESS(202), 
    MESOBAG_FAILURE(203), 
    UPDATE_QUEST_INFO(204), 
    PET_FLAG_CHANGE(206), 
    PLAYER_HINT(207), 
    REPAIR_WINDOW(213), 
    CYGNUS_INTRO_LOCK(214), 
    CYGNUS_INTRO_DISABLE_UI(215), 
    CS_UPDATE, 
    CS_OPERATION, 
    SPAWN_NPC(249), 
    REMOVE_NPC(250), 
    SPAWN_NPC_REQUEST_CONTROLLER(251), 
    SPAWN_MONSTER, 
    SPAWN_MONSTER_CONTROL, 
    MOVE_MONSTER_RESPONSE, 
    SHOW_MESO_GAIN, 
    ANNOUNCE_PLAYER_SHOP, 
    KILL_MONSTER, 
    DROP_ITEM_FROM_MAPOBJECT, 
    MOVE_MONSTER, 
    OPEN_NPC_SHOP, 
    CONFIRM_SHOP_TRANSACTION, 
    OPEN_STORAGE, 
    REMOVE_ITEM_FROM_MAP, 
    PLAYER_INTERACTION, 
    NPC_TALK, 
    KEYMAP, 
    SHOW_MONSTER_HP, 
    APPLY_MONSTER_STATUS, 
    CANCEL_MONSTER_STATUS, 
    SPAWN_DOOR, 
    REMOVE_DOOR, 
    SPAWN_MIST, 
    REMOVE_MIST, 
    DAMAGE_MONSTER, 
    REACTOR_SPAWN, 
    REACTOR_HIT, 
    REACTOR_DESTROY, 
    EARN_TITLE_MSG, 
    SHOW_MAGNET, 
    MERCH_ITEM_MSG, 
    MERCH_ITEM_STORE, 
    MESSENGER, 
    NPC_ACTION, 
    COOLDOWN, 
    SUMMON_HINT, 
    SUMMON_HINT_MSG, 
    SUMMON_SKILL, 
    ARIANT_PQ_START, 
    CATCH_MONSTER, 
    ARIANT_SCOREBOARD, 
    ZAKUM_SHRINE, 
    DUEY, 
    MONSTER_CARNIVAL_START, 
    MONSTER_CARNIVAL_OBTAINED_CP, 
    MONSTER_CARNIVAL_PARTY_CP, 
    MONSTER_CARNIVAL_SUMMON, 
    MONSTER_CARNIVAL_DIED, 
    SPAWN_HIRED_MERCHANT, 
    UPDATE_HIRED_MERCHANT, 
    DESTROY_HIRED_MERCHANT, 
    FAIRY_PEND_MSG, 
    VICIOUS_HAMMER, 
    ROLL_SNOWBALL, 
    HIT_SNOWBALL, 
    SNOWBALL_MESSAGE, 
    LEFT_KNOCK_BACK, 
    HIT_COCONUT, 
    COCONUT_SCORE, 
    HORNTAIL_SHRINE, 
    DRAGON_MOVE, 
    DRAGON_REMOVE, 
    DRAGON_SPAWN, 
    ARAN_COMBO, 
    GET_MTS_TOKENS, 
    MTS_OPERATION, 
    SHOW_POTENTIAL_EFFECT, 
    SHOW_POTENTIAL_RESET, 
    CHAOS_ZAKUM_SHRINE, 
    CHAOS_HORNTAIL_SHRINE, 
    GAME_POLL_QUESTION, 
    GAME_POLL_REPLY, 
    XMAS_SURPRISE, 
    FOLLOW_REQUEST, 
    FOLLOW_EFFECT, 
    FOLLOW_MOVE, 
    FOLLOW_MSG, 
    FOLLOW_MESSAGE, 
    TALK_MONSTER, 
    REMOVE_TALK_MONSTER, 
    MONSTER_PROPERTIES, 
    GHOST_POINT, 
    GHOST_STATUS, 
    ENGLISH_QUIZ, 
    RPS_GAME, 
    UPDATE_BEANS, 
    TIP_BEANS, 
    OPEN_BEANS, 
    SHOOT_BEANS, 
    SHOW_SPECIAL_ATTACK, 
    PET_AUTO_HP, 
    PET_AUTO_MP, 
    TOP_MSG, 
    CHAR_CASH, 
    OPEN_WEB, 
    CS_WEB;
    
    private short code;
    
    private SendPacketOpcode() {
        this.code = -2;
        this.code = -2;
    }
    
    private SendPacketOpcode(final int code) {
        this.code = -2;
        this.code = (short)code;
    }
    
    public static String nameOf(final int value) {
        for (final SendPacketOpcode opcode : values()) {
            if (opcode.getValue() == value) {
                return opcode.name();
            }
        }
        return "UNKNOWN";
    }
    
    @Override
    public void setValue(final short code) {
        this.code = code;
    }
    
    @Override
    public short getValue() {
        return this.code;
    }
    
    public static boolean isSpamHeader(final SendPacketOpcode opcode) {
        final String name = opcode.name();
        int n = -1;
        switch (name.hashCode()) {
            case -906709233: {
                if (name.equals((Object)"WARP_TO_MAP")) {
                    n = 0;
                    break;
                }
                break;
            }
            case 2455922: {
                if (name.equals((Object)"PING")) {
                    n = 1;
                    break;
                }
                break;
            }
            case 132404596: {
                if (name.equals((Object)"NPC_ACTION")) {
                    n = 2;
                    break;
                }
                break;
            }
        }
        switch (n) {
            case 0:
            case 1:
            case 2: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static final void reloadValues() {
        final String fileName = "send.ini";
        final Properties props = new Properties();
        try (final FileInputStream fileInputStream = new FileInputStream(fileName);
             final BufferedReader br = new BufferedReader((Reader)new InputStreamReader((InputStream)fileInputStream, StringUtil.codeString(fileName)))) {
            props.load((Reader)br);
        }
        catch (IOException ex) {
            final InputStream in = SendPacketOpcode.class.getClassLoader().getResourceAsStream("properties/" + fileName);
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
