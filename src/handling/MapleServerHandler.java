package handling;

import java.util.Collection;
import java.util.Arrays;
import handling.channel.handler.BeanGame;
import handling.channel.handler.FamilyHandler;
import handling.channel.handler.HiredMerchantHandler;
import handling.channel.handler.DueyHandler;
import handling.channel.handler.MonsterCarnivalHandler;
import handling.channel.handler.PetHandler;
import handling.channel.handler.SummonHandler;
import handling.cashshop.handler.MTSOperation;
import server.MTSStorage;
import handling.channel.handler.UserInterfaceHandler;
import handling.channel.handler.BuddyListHandler;
import handling.channel.handler.PartyHandler;
import handling.channel.handler.BBSHandler;
import handling.channel.handler.AllianceHandler;
import handling.channel.handler.GuildHandler;
import handling.channel.handler.PlayerInteractionHandler;
import handling.channel.handler.StatsHandling;
import handling.channel.handler.ChatHandler;
import handling.channel.handler.NPCHandler;
import handling.channel.handler.MobHandler;
import handling.channel.handler.InventoryHandler;
import handling.channel.handler.ItemMakerHandler;
import handling.channel.handler.PlayersHandler;
import handling.channel.handler.PlayerHandler;
import scripting.NPCScriptManager;
import tools.MaplePacketCreator;
import handling.cashshop.handler.CashShopOperation;
import handling.channel.handler.InterServerHandler;
import handling.login.handler.CharLoginHandler;
import tools.HexTool;
import constants.ServerConfig;
import java.util.concurrent.RejectedExecutionException;
import tools.FilePrinter;
import tools.data.LittleEndianAccessor;
import tools.data.ByteArrayByteStream;
import tools.packet.LoginPacket;
import io.netty.util.AttributeKey;
import handling.mina.MaplePacketDecoder;
import handling.mina.MaplePacketDecoder.DecoderState;
import client.MapleClient;
import tools.MapleAESOFB;
import server.Randomizer;
import handling.channel.ChannelServer;
import handling.login.LoginServer;
import handling.cashshop.CashShopServer;
import tools.FileoutputUtil;
import constants.WorldConstants;
import io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.EnumSet;
import tools.Pair;
import java.util.Map;
import java.util.List;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MapleServerHandler extends ChannelInboundHandlerAdapter
{
    private final int world;
    private final int channel;
    public static final int CASH_SHOP_SERVER = -10;
    public static final int LOGIN_SERVER = 0;
    private final List<String> BlockedIP;
    private final Map<String, Pair<Long, Byte>> tracker;
    private static final EnumSet<RecvPacketOpcode> blocked;
    
    public MapleServerHandler(final int world, final int channel) {
        this.BlockedIP = new ArrayList<String>();
        this.tracker = new ConcurrentHashMap<String, Pair<Long, Byte>>();
        this.world = world;
        this.channel = channel;
    }
    
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
    }
    
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        final String address = ctx.channel().remoteAddress().toString().split(":")[0];
        if (WorldConstants.ADMIN_ONLY) {
            System.out.println("[登錄服務] " + address + " 已連接");
        }
        if (this.BlockedIP.contains((Object)address)) {
            FileoutputUtil.logToFile("logs/Data/連接斷線.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + address + " 連接斷線1");
            ctx.channel().close();
            return;
        }
        final Pair<Long, Byte> track = (Pair<Long, Byte>)this.tracker.get((Object)address);
        byte count;
        if (track == null) {
            count = 1;
        }
        else {
            count = (byte)Byte.valueOf(track.right);
            final long difference = System.currentTimeMillis() - (long)Long.valueOf(track.left);
            if (difference < 2000L) {
                ++count;
            }
            else if (difference > 20000L) {
                count = 1;
            }
            if (count >= 10) {
                this.BlockedIP.add(address);
                this.tracker.remove((Object)address);
                FileoutputUtil.logToFile("logs/Data/連接斷線.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + address + " 連接斷線2");
                ctx.channel().close();
                return;
            }
        }
        this.tracker.put(address, new Pair<Long, Byte>(Long.valueOf(System.currentTimeMillis()), Byte.valueOf(count)));
        if (this.channel == -10) {
            if (CashShopServer.isShutdown()) {
                ctx.channel().close();
                return;
            }
        }
        else if (this.channel == 0) {
            if (LoginServer.isShutdown()) {
                ctx.channel().close();
                return;
            }
        }
        else {
            if (this.channel <= 0) {
                System.out.println("[連結錯誤] 未知類型: " + this.channel);
                FileoutputUtil.logToFile("logs/Data/連接斷線.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + address + " 連接斷線3");
                ctx.channel().close();
                return;
            }
            if (ChannelServer.getInstance(this.channel).isShutdown()) {
                ctx.channel().close();
                return;
            }
        }
        final byte[] ivRecv = { (byte)Randomizer.nextInt(255), (byte)Randomizer.nextInt(255), (byte)Randomizer.nextInt(255), (byte)Randomizer.nextInt(255) };
        final byte[] ivSend = { (byte)Randomizer.nextInt(255), (byte)Randomizer.nextInt(255), (byte)Randomizer.nextInt(255), (byte)Randomizer.nextInt(255) };
        final MapleClient client = new MapleClient(new MapleAESOFB(ivSend, (short)(-80)), new MapleAESOFB(ivRecv, (short)79), ctx.channel());
        client.setChannel(this.channel);
        final DecoderState decoderState = new DecoderState();
        ctx.channel().attr((AttributeKey)MaplePacketDecoder.DECODER_STATE_KEY).set((Object)decoderState);
        ctx.writeAndFlush((Object)LoginPacket.getHello((short)79, ivSend, ivRecv));
        ctx.channel().attr((AttributeKey)MapleClient.CLIENT_KEY).set((Object)client);
    }
    
    public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
        try {
            final MapleClient client = ctx.channel().attr(MapleClient.CLIENT_KEY).get();
            if (client != null) {
                try {
                    client.disconnect(true, this.channel == -10);
                    if (this.channel == 0) {
                        client.setCanloginpw(false);
                        LoginServer.removeClient(client);
                    }
                }
                finally {
                    ctx.channel().close();
                    ctx.channel().attr(MapleClient.CLIENT_KEY).set(null);
                }
            }
        }
        catch (Exception e) {
            FileoutputUtil.outError("logs/斷開連接異常.txt", (Throwable)e);
        }
        super.channelInactive(ctx);
    }
    
    public void channelRead(final ChannelHandlerContext ctx, final Object message) {
        final LittleEndianAccessor slea = new LittleEndianAccessor(new ByteArrayByteStream((byte[])(byte[])message));
        if (slea.available() < 2L) {
            return;
        }
        final MapleClient c = (MapleClient)ctx.channel().attr((AttributeKey)MapleClient.CLIENT_KEY).get();
        if (c == null || !c.isReceiving()) {
            return;
        }
        final short header_num = slea.readShort();
        final RecvPacketOpcode[] values = RecvPacketOpcode.values();
        final int length = values.length;
        int i = 0;
        while (i < length) {
            final RecvPacketOpcode recv = values[i];
            if (recv.getValue() == header_num) {
                if (recv.NeedsChecking() && !c.isLoggedIn()) {
                    return;
                }
                if (c.getPlayer() != null && c.isMonitored() && !MapleServerHandler.blocked.contains((Object)recv)) {
                    FilePrinter.print("Monitored/" + c.getPlayer().getName() + ".txt", String.valueOf((Object)recv) + " (" + Integer.toHexString((int)header_num) + ") Handled: \r\n" + slea.toString() + "\r\n");
                }
                try {
                    handlePacket(recv, slea, c, this.channel == -10);
                }
                catch (RejectedExecutionException ex) {}
                catch (Exception e) {
                    if (c.getPlayer() != null && c.getPlayer().isShowErr()) {
                        c.getPlayer().showInfo("数据包異常", true, "包頭:" + recv.name() + "(0x" + Integer.toHexString((int)header_num).toUpperCase() + ")");
                    }
                    FileoutputUtil.outputFileError("logs/Except/Log_Code_Except.txt", (Throwable)e, false);
                    FileoutputUtil.outputFileError("logs/Except/Log_Packet_Except.txt", (Throwable)e);
                    FileoutputUtil.log("logs/Except/Log_Packet_Except.txt", "Packet: " + (int)header_num + "\r\n" + slea.toString(true));
                }
                return;
            }
            else {
                ++i;
            }
        }
        if (ServerConfig.LOG_PACKETS) {
            final byte[] packet = slea.read((int)slea.available());
            final StringBuilder sb = new StringBuilder("發現未知用戶端数据包 - (包頭:0x" + Integer.toHexString((int)header_num) + ")");
            System.err.println(sb.toString());
            sb.append(":\r\n").append(HexTool.toString(packet)).append("\r\n").append(HexTool.toStringFromAscii(packet));
            FileoutputUtil.log("logs\\数据包_未知.txt", sb.toString());
        }
    }
    
    public void userEventTriggered(final ChannelHandlerContext ctx, final Object status) throws Exception {
        final MapleClient client = (MapleClient)ctx.channel().attr((AttributeKey)MapleClient.CLIENT_KEY).get();
        if (client != null) {
            client.sendPing();
        }
        else {
            ctx.channel().close();
            System.out.println("netty檢測心跳掉線。");
        }
        super.userEventTriggered(ctx, status);
    }
    
    public static final void handlePacket(final RecvPacketOpcode header, final LittleEndianAccessor slea, final MapleClient c, final boolean cs) throws Exception {
        switch (header) {
            case PONG: {
                c.pongReceived();
            }
            case HELLO_LOGIN: {
                if (slea.available() >= 5L) {
                    final Long avaible = Long.valueOf(slea.available());
                    String debug = "";
                    if (c != null) {
                        debug = debug + c.getAccountName() + "_";
                    }
                    FilePrinter.print(debug + "38Logs.txt", HexTool.toStringFromAscii(slea.read(avaible.intValue())), true);
                    break;
                }
                break;
            }
            case LOGIN_PASSWORD: {
                CharLoginHandler.handleLogin(slea, c);
                break;
            }
            case SERVERLIST_REQUEST: {
                CharLoginHandler.ServerListRequest(c);
                break;
            }
            case CHARLIST_REQUEST: {
                CharLoginHandler.CharlistRequest(slea, c);
                break;
            }
            case SERVERSTATUS_REQUEST: {
                CharLoginHandler.ServerStatusRequest(c);
                break;
            }
            case CHECK_CHAR_NAME: {
                CharLoginHandler.checkCharName(slea.readMapleAsciiString(), c);
                break;
            }
            case CREATE_CHAR: {
                CharLoginHandler.handleCreateCharacter(slea, c);
                break;
            }
            case DELETE_CHAR: {
                CharLoginHandler.handleDeleteCharacter(slea, c);
                break;
            }
            case CHAR_SELECT: {
                CharLoginHandler.handleSecectCharacter(slea, c);
                break;
            }
            case SET_GENDER: {
                CharLoginHandler.SetGenderRequest(slea, c);
                break;
            }
            case CHANGE_CHANNEL: {
                InterServerHandler.ChangeChannel(slea, c, c.getPlayer());
                break;
            }
            case PLAYER_LOGGEDIN: {
                final int playerid = slea.readInt();
                if (cs) {
                    CashShopOperation.EnterCashShop(playerid, c);
                    break;
                }
                InterServerHandler.LoggedIn(playerid, c);
                break;
            }
            case ENTER_CASH_SHOP: {
                if (c.getPlayer().getMapId() != 980000010 & c.getPlayer().getMapId() != 980000020 & c.getPlayer().getMapId() != 980000100 & c.getPlayer().getMapId() != 980000101 & c.getPlayer().getMapId() != 980000102 & c.getPlayer().getMapId() != 980000103 & c.getPlayer().getMapId() != 980000104 & c.getPlayer().getMapId() != 980000200 & c.getPlayer().getMapId() != 980000201 & c.getPlayer().getMapId() != 980000202 & c.getPlayer().getMapId() != 980000203 & c.getPlayer().getMapId() != 980000204 & c.getPlayer().getMapId() != 980000300 & c.getPlayer().getMapId() != 980000301 & c.getPlayer().getMapId() != 980000302 & c.getPlayer().getMapId() != 980000303 & c.getPlayer().getMapId() != 980000304 & c.getPlayer().getMapId() != 980000400 & c.getPlayer().getMapId() != 980000401 & c.getPlayer().getMapId() != 980000402 & c.getPlayer().getMapId() != 980000403 & c.getPlayer().getMapId() != 980000404 & c.getPlayer().getMapId() != 980000400 & c.getPlayer().getMapId() != 980000501 & c.getPlayer().getMapId() != 980000502 & c.getPlayer().getMapId() != 980000503 & c.getPlayer().getMapId() != 980000504 & c.getPlayer().getMapId() != 980000600 & c.getPlayer().getMapId() != 980000601 & c.getPlayer().getMapId() != 980000602 & c.getPlayer().getMapId() != 980000603 & c.getPlayer().getMapId() != 180000001 & c.getPlayer().getMapId() != 980000604) {
                    InterServerHandler.EnterCashShop(c, c.getPlayer(), false);
                    c.getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
                    break;
                }
                c.getPlayer().dropMessage(5, "抱歉，该地图无法进入商城。");
                break;
            }
            case ENTER_MTS: {
                if (c.getPlayer().getMapId() == 180000001 || c.getPlayer().getMapId() == 910010300 || c.getPlayer().getMapId() == 910010100 || (c.getPlayer().getMapId() > 211060000 && c.getPlayer().getMapId() <= 211061000)) {
                    c.getPlayer().dropMessage(5, "抱歉，该地图无法使用拍卖功能。");
                    break;
                }
                c.getSession().writeAndFlush((Object)MaplePacketCreator.enableActions());
                NPCScriptManager.getInstance().start(c, 9900004, "拍卖功能");
                break;
            }
            case MOVE_PLAYER: {
                PlayerHandler.MovePlayer(slea, c, c.getPlayer());
                break;
            }
            case CHAR_INFO_REQUEST: {
                c.getPlayer().updateTick(slea.readInt());
                PlayerHandler.CharInfoRequest(slea.readInt(), c, c.getPlayer());
                break;
            }
            case CLOSE_RANGE_ATTACK: {
                PlayerHandler.closeRangeAttack(slea, c, c.getPlayer(), false);
                break;
            }
            case RANGED_ATTACK: {
                PlayerHandler.rangedAttack(slea, c, c.getPlayer());
                break;
            }
            case MAGIC_ATTACK: {
                PlayerHandler.MagicDamage(slea, c, c.getPlayer());
                break;
            }
            case SPECIAL_MOVE: {
                PlayerHandler.SpecialMove(slea, c, c.getPlayer());
                break;
            }
            case PASSIVE_ENERGY: {
                PlayerHandler.closeRangeAttack(slea, c, c.getPlayer(), true);
                break;
            }
            case FACE_EXPRESSION: {
                PlayerHandler.ChangeEmotion(slea.readInt(), c.getPlayer());
                break;
            }
            case TAKE_DAMAGE: {
                PlayerHandler.TakeDamage(slea, c, c.getPlayer());
                break;
            }
            case HEAL_OVER_TIME: {
                PlayerHandler.Heal(slea, c.getPlayer());
                break;
            }
            case CANCEL_BUFF: {
                PlayerHandler.CancelBuffHandler(slea.readInt(), c.getPlayer());
                break;
            }
            case CANCEL_ITEM_EFFECT: {
                PlayerHandler.CancelItemEffect(slea.readInt(), c.getPlayer());
                break;
            }
            case USE_CHAIR: {
                PlayerHandler.UseChair(slea.readInt(), c, c.getPlayer());
                break;
            }
            case SHOW_EXP_CHAIR: {
                PlayerHandler.ShowExpChair(slea, c);
                break;
            }
            case CANCEL_CHAIR: {
                PlayerHandler.CancelChair(slea.readShort(), c, c.getPlayer());
                break;
            }
            case USE_ITEMEFFECT:
            case WHEEL_OF_FORTUNE: {
                PlayerHandler.UseItemEffect(slea.readInt(), c, c.getPlayer());
                break;
            }
            case SKILL_EFFECT: {
                PlayerHandler.SkillEffect(slea, c.getPlayer());
                break;
            }
            case MESO_DROP: {
                c.getPlayer().updateTick(slea.readInt());
                PlayerHandler.DropMeso(slea.readInt(), c.getPlayer());
                break;
            }
            case MONSTER_BOOK_COVER: {
                PlayerHandler.ChangeMonsterBookCover(slea.readInt(), c, c.getPlayer());
                break;
            }
            case CHANGE_KEYMAP: {
                PlayerHandler.ChangeKeymap(slea, c.getPlayer());
                break;
            }
            case CHANGE_MAP: {
                if (cs) {
                    CashShopOperation.LeaveCashShop(slea, c, c.getPlayer());
                    break;
                }
                PlayerHandler.ChangeMap(slea, c, c.getPlayer());
                break;
            }
            case CHANGE_MAP_SPECIAL: {
                slea.skip(1);
                PlayerHandler.ChangeMapSpecial(slea.readMapleAsciiString(), c, c.getPlayer());
                break;
            }
            case USE_INNER_PORTAL: {
                slea.skip(1);
                PlayerHandler.InnerPortal(slea, c, c.getPlayer());
                break;
            }
            case TROCK_ADD_MAP: {
                PlayerHandler.TrockAddMap(slea, c, c.getPlayer());
                break;
            }
            case LIE_DETECTOR_SKILL: {
                PlayersHandler.LieDetector(slea, c, c.getPlayer(), false);
                break;
            }
            case LIE_DETECTOR: {
                PlayersHandler.LieDetector(slea, c, c.getPlayer(), true);
                break;
            }
            case LIE_DETECTOR_RESPONSE: {
                PlayersHandler.LieDetectorResponse(slea, c);
            }
            case ARAN_COMBO: {
                PlayerHandler.AranCombo(c, c.getPlayer(), 1);
                break;
            }
            case SKILL_MACRO: {
                PlayerHandler.ChangeSkillMacro(slea, c.getPlayer());
                break;
            }
            case GIVE_FAME: {
                PlayersHandler.GiveFame(slea, c, c.getPlayer());
                break;
            }
            case TRANSFORM_PLAYER: {
                PlayersHandler.TransformPlayer(slea, c, c.getPlayer());
                break;
            }
            case NOTE_ACTION: {
                PlayersHandler.Note(slea, c.getPlayer());
                break;
            }
            case USE_DOOR: {
                PlayersHandler.UseDoor(slea, c.getPlayer());
                break;
            }
            case DAMAGE_REACTOR: {
                PlayersHandler.HitReactor(slea, c);
                break;
            }
            case TOUCH_REACTOR: {
                PlayersHandler.TouchReactor(slea, c);
                break;
            }
            case CLOSE_CHALKBOARD: {
                c.getPlayer().setChalkboard(null);
                break;
            }
            case ITEM_MAKER: {
                ItemMakerHandler.ItemMaker(slea, c);
                break;
            }
            case ITEM_SORT: {
                InventoryHandler.ItemSort(slea, c);
                break;
            }
            case ITEM_GATHER: {
                InventoryHandler.ItemGather(slea, c);
                break;
            }
            case ITEM_MOVE: {
                InventoryHandler.ItemMove(slea, c);
                break;
            }
            case ITEM_PICKUP: {
                InventoryHandler.PlayerPickup(slea, c, c.getPlayer());
                break;
            }
            case USE_CASH_ITEM: {
                InventoryHandler.UseCashItem(slea, c);
                break;
            }
            case USE_ITEM: {
                InventoryHandler.UseItem(slea, c, c.getPlayer());
                break;
            }
            case USE_MAGNIFY_GLASS: {
                InventoryHandler.UseMagnify(slea, c);
                break;
            }
            case USE_SCRIPTED_NPC_ITEM: {
                InventoryHandler.UseScriptedNPCItem(slea, c, c.getPlayer());
                break;
            }
            case USE_RETURN_SCROLL: {
                InventoryHandler.UseReturnScroll(slea, c, c.getPlayer());
                break;
            }
            case USE_UPGRADE_SCROLL: {
                c.getPlayer().updateTick(slea.readInt());
                InventoryHandler.UseUpgradeScroll((byte)slea.readShort(), (byte)slea.readShort(), (byte)slea.readShort(), c, c.getPlayer());
                break;
            }
            case USE_POTENTIAL_SCROLL: {
                c.getPlayer().updateTick(slea.readInt());
                InventoryHandler.UseUpgradeScroll((byte)slea.readShort(), (byte)slea.readShort(), (byte)0, c, c.getPlayer());
                break;
            }
            case USE_EQUIP_SCROLL: {
                c.getPlayer().updateTick(slea.readInt());
                InventoryHandler.UseUpgradeScroll((byte)slea.readShort(), (byte)slea.readShort(), (byte)0, c, c.getPlayer());
                break;
            }
            case USE_SUMMON_BAG: {
                InventoryHandler.UseSummonBag(slea, c, c.getPlayer());
                break;
            }
            case USE_TREASUER_CHEST: {
                InventoryHandler.UseTreasureChest(slea, c, c.getPlayer());
                break;
            }
            case USE_SKILL_BOOK: {
                InventoryHandler.UseSkillBook(slea, c, c.getPlayer());
                break;
            }
            case USE_CATCH_ITEM: {
                InventoryHandler.UseCatchItem(slea, c, c.getPlayer());
                break;
            }
            case USE_MOUNT_FOOD: {
                InventoryHandler.UseMountFood(slea, c, c.getPlayer());
                break;
            }
            case REWARD_ITEM: {
                InventoryHandler.UseRewardItem((byte)slea.readShort(), slea.readInt(), c, c.getPlayer());
                break;
            }
            case HYPNOTIZE_DMG: {
                MobHandler.HypnotizeDmg(slea, c);
                break;
            }
            case MOB_NODE: {
                MobHandler.handleMobNode(slea, c);
                break;
            }
            case DISPLAY_NODE: {
                MobHandler.handleDisplayNode(slea, c);
                break;
            }
            case MOVE_LIFE: {
                MobHandler.MoveMonster(slea, c);
                break;
            }
            case AUTO_AGGRO: {
                MobHandler.handleAutoAggro(slea, c);
                break;
            }
            case FRIENDLY_DAMAGE: {
                MobHandler.handleFriendlyDamage(slea, c);
                break;
            }
            case MONSTER_BOMB: {
                MobHandler.handleMonsterBomb(slea, c);
                break;
            }
            case QUEST_ACTION: {
                NPCHandler.QuestAction(slea, c, c.getPlayer());
                break;
            }
            case NPC_SHOP: {
                NPCHandler.handleNPCShop(slea, c);
                break;
            }
            case NPC_TALK: {
                NPCHandler.handleNPCTalk(slea, c, c.getPlayer());
                break;
            }
            case NPC_TALK_MORE: {
                NPCHandler.NPCMoreTalk(slea, c);
                break;
            }
            case NPC_ACTION: {
                NPCHandler.handleNPCAnimation(slea, c);
                break;
            }
            case STORAGE: {
                NPCHandler.Storage(slea, c, c.getPlayer());
                break;
            }
            case GENERAL_CHAT: {
                ChatHandler.GeneralChat(slea.readMapleAsciiString(), slea.readByte(), c, c.getPlayer());
                break;
            }
            case PARTYCHAT: {
                ChatHandler.Others(slea, c, c.getPlayer());
                break;
            }
            case WHISPER: {
                ChatHandler.WhisperFind(slea, c);
                break;
            }
            case MESSENGER: {
                ChatHandler.Messenger(slea, c);
                break;
            }
            case AUTO_ASSIGN_AP: {
                StatsHandling.AutoAssignAP(slea, c, c.getPlayer());
                break;
            }
            case DISTRIBUTE_AP: {
                StatsHandling.DistributeAP(slea, c, c.getPlayer());
                break;
            }
            case DISTRIBUTE_SP: {
                c.getPlayer().updateTick(slea.readInt());
                StatsHandling.DistributeSP(slea.readInt(), c, c.getPlayer());
                break;
            }
            case PLAYER_INTERACTION: {
                PlayerInteractionHandler.PlayerInteraction(slea, c, c.getPlayer());
                break;
            }
            case GUILD_OPERATION: {
                GuildHandler.HandleGuild(slea, c);
                break;
            }
            case UPDATE_CHAR_INFO: {
                PlayersHandler.UpdateCharInfo(slea, c, c.getPlayer());
                break;
            }
            case DENY_GUILD_REQUEST: {
                slea.skip(1);
                GuildHandler.denyGuildRequest(slea.readMapleAsciiString(), c);
                break;
            }
            case ALLIANCE_OPERATION: {
                AllianceHandler.HandleAlliance(slea, c, false);
                break;
            }
            case DENY_ALLIANCE_REQUEST: {
                AllianceHandler.HandleAlliance(slea, c, true);
                break;
            }
            case BBS_OPERATION: {
                BBSHandler.HandleBBS(slea, c);
                break;
            }
            case PARTY_OPERATION: {
                PartyHandler.PartyOperatopn(slea, c);
                break;
            }
            case DENY_PARTY_REQUEST: {
                PartyHandler.DenyPartyRequest(slea, c);
                break;
            }
            case BUDDYLIST_MODIFY: {
                BuddyListHandler.BuddyOperationHandler(slea, c);
                break;
            }
            case CYGNUS_SUMMON: {
                UserInterfaceHandler.CygnusSummonNPCRequest(c);
                break;
            }
            case CASHSHOP_OPERATION: {
                CashShopOperation.BuyCashItem(slea, c, c.getPlayer());
                break;
            }
            case COUPON_CODE: {
                slea.skip(2);
                CashShopOperation.CouponCode(slea.readMapleAsciiString(), c);
                break;
            }
            case CS_UPDATE: {
                CashShopOperation.sendCashShopUpdate(c);
                break;
            }
            case TOUCHING_MTS: {
                MTSOperation.MTSUpdate(MTSStorage.getInstance().getCart(c.getPlayer().getId()), c);
                break;
            }
            case MTS_TAB: {
                MTSOperation.MTSOperation(slea, c);
                break;
            }
            case DAMAGE_SUMMON: {
                slea.skip(4);
                SummonHandler.DamageSummon(slea, c.getPlayer());
                break;
            }
            case MOVE_SUMMON: {
                SummonHandler.MoveSummon(slea, c.getPlayer());
                break;
            }
            case SUMMON_ATTACK: {
                SummonHandler.SummonAttack(slea, c, c.getPlayer());
                break;
            }
            case SPAWN_PET: {
                PetHandler.SpawnPet(slea, c, c.getPlayer());
                break;
            }
            case MOVE_PET: {
                PetHandler.MovePet(slea, c.getPlayer());
                break;
            }
            case PET_CHAT: {
                if (slea.available() < 12L) {
                    break;
                }
                PetHandler.PetChat((int)slea.readLong(), slea.readShort(), slea.readMapleAsciiString(), c.getPlayer());
                break;
            }
            case PET_COMMAND: {
                PetHandler.PetCommand(slea, c, c.getPlayer());
                break;
            }
            case PET_FOOD: {
                PetHandler.PetFood(slea, c, c.getPlayer());
                break;
            }
            case PET_LOOT: {
                InventoryHandler.PetPickup(slea, c, c.getPlayer());
                break;
            }
            case PET_AUTO_POT: {
                PetHandler.Pet_AutoPotion(slea, c, c.getPlayer());
                break;
            }
            case MONSTER_CARNIVAL: {
                MonsterCarnivalHandler.MonsterCarnival(slea, c);
                break;
            }
            case DUEY_ACTION: {
                DueyHandler.DueyOperation(slea, c);
                break;
            }
            case USE_HIRED_MERCHANT: {
                HiredMerchantHandler.UseHiredMerchant(slea, c);
                break;
            }
            case MERCH_ITEM_STORE: {
                HiredMerchantHandler.MerchantItemStore(slea, c);
            }
            case LEFT_KNOCK_BACK: {
                PlayerHandler.leftKnockBack(slea, c);
                break;
            }
            case SNOWBALL: {
                PlayerHandler.snowBall(slea, c);
            }
            case REPAIR: {
                NPCHandler.repair(slea, c);
                break;
            }
            case REPAIR_ALL: {
                NPCHandler.repairAll(c);
                break;
            }
            case GAME_POLL: {
                UserInterfaceHandler.InGamePoll(slea, c);
                break;
            }
            case OWL: {
                InventoryHandler.Owl(slea, c);
                break;
            }
            case OWL_WARP: {
                InventoryHandler.OwlWarp(slea, c);
                break;
            }
            case USE_OWL_MINERVA: {
                InventoryHandler.OwlMinerva(slea, c);
                break;
            }
            case UPDATE_QUEST: {
                NPCHandler.UpdateQuest(slea, c);
                break;
            }
            case USE_ITEM_QUEST: {
                NPCHandler.UseItemQuest(slea, c);
                break;
            }
            case FOLLOW_REQUEST: {
                PlayersHandler.FollowRequest(slea, c);
                break;
            }
            case FOLLOW_REPLY: {
                PlayersHandler.FollowReply(slea, c);
                break;
            }
            case RING_ACTION: {
                PlayersHandler.RingAction(slea, c);
                break;
            }
            case ITEM_UNLOCK: {
                PlayersHandler.UnlockItem(slea, c);
                break;
            }
            case SOLOMON: {
                PlayersHandler.Solomon(slea, c);
                break;
            }
            case GACH_EXP: {
                PlayersHandler.GachExp(slea, c);
                break;
            }
            case REQUEST_FAMILY: {
                FamilyHandler.RequestFamily(slea, c);
                break;
            }
            case OPEN_FAMILY: {
                FamilyHandler.OpenFamily(slea, c);
                break;
            }
            case FAMILY_OPERATION: {
                FamilyHandler.FamilyOperation(slea, c);
                break;
            }
            case PET_IGNORE: {
                PetHandler.PetIgnoreTag(slea, c, c.getPlayer());
                break;
            }
            case DELETE_JUNIOR: {
                FamilyHandler.DeleteJunior(slea, c);
                break;
            }
            case DELETE_SENIOR: {
                FamilyHandler.DeleteSenior(slea, c);
                break;
            }
            case USE_FAMILY: {
                FamilyHandler.UseFamily(slea, c);
                break;
            }
            case FAMILY_PRECEPT: {
                FamilyHandler.FamilyPrecept(slea, c);
                break;
            }
            case FAMILY_SUMMON: {
                FamilyHandler.FamilySummon(slea, c);
                break;
            }
            case ACCEPT_FAMILY: {
                FamilyHandler.AcceptFamily(slea, c);
                break;
            }
            case ITEM_SUNZI: {
                InventoryHandler.SunziBF(slea, c);
            }
            case CLIENT_ERROR: {
                final short type = slea.readShort();
                String type_str = "Unknown?!";
                if (type == 1) {
                    type_str = "SendBackupPacket";
                }
                else if (type == 2) {
                    type_str = "Crash Report";
                }
                else if (type == 3) {
                    type_str = "Exception";
                }
                final int errortype = slea.readInt();
                final short data_length = slea.readShort();
                slea.skip(4);
                final short opcodeheader = slea.readShort();
                final byte[] opcode = slea.read((int)slea.available());
                final int packetLen = (int)slea.available() + 2;
                if (errortype == 38) {}
                String AccountName = "null";
                String charName = "null";
                String charLevel = "null";
                String charJob = "null";
                String Map = "null";
                String charId = "null";
                try {
                    AccountName = c.getAccountName();
                }
                catch (Throwable t2) {}
                try {
                    charName = c.getPlayer().getName();
                }
                catch (Throwable t3) {}
                try {
                    charId = String.valueOf(c.getPlayer().getId());
                }
                catch (Throwable t4) {}
                try {
                    charLevel = String.valueOf((int)c.getPlayer().getLevel());
                }
                catch (Throwable t5) {}
                try {
                    charJob = String.valueOf((int)c.getPlayer().getJob());
                }
                catch (Throwable t6) {}
                try {
                    Map = String.valueOf(c.getPlayer().getMap().getId());
                }
                catch (Throwable t7) {}
                String tab = "";
                for (int i = 4; i > SendPacketOpcode.nameOf((int)opcodeheader).length() / 8; --i) {
                    tab += "\t";
                }
                final String t = (packetLen >= 10) ? ((packetLen >= 100) ? ((packetLen >= 1000) ? "" : " ") : "  ") : "   ";
                if (errortype == 38) {
                    break;
                }
                break;
            }
            case SPECIAL_ATTACK: {
                PlayerHandler.SpecialAttack(slea, c, c.getPlayer());
                break;
            }
            case BEANS_OPERATION: {
                BeanGame.BeansGameAction(slea, c);
                break;
            }
            case BEANS_UPDATE: {
                BeanGame.updateBeans(slea, c);
                break;
            }
            case LICENSE_REQUEST: {
                CharLoginHandler.LicenseRequest(slea, c);
                break;
            }
        }
    }
    
    static {
        blocked = EnumSet.noneOf(RecvPacketOpcode.class);
        final RecvPacketOpcode[] block = { RecvPacketOpcode.NPC_ACTION, RecvPacketOpcode.MOVE_PLAYER, RecvPacketOpcode.MOVE_PET, RecvPacketOpcode.MOVE_SUMMON, RecvPacketOpcode.MOVE_LIFE, RecvPacketOpcode.HEAL_OVER_TIME, RecvPacketOpcode.STRANGE_DATA };
        MapleServerHandler.blocked.addAll(Arrays.asList(block));
    }
}
