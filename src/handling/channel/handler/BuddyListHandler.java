package handling.channel.handler;

import client.MapleCharacter;
import tools.FilePrinter;
import client.BuddyList.BuddyOperation;
import client.BuddyList.BuddyAddResult;
import client.BuddyList;
import handling.world.World.Buddy;
import handling.channel.ChannelServer;
import handling.world.World.Find;
import tools.data.LittleEndianAccessor;
import client.BuddyEntry;
import tools.MaplePacketCreator;
import client.MapleClient;

public class BuddyListHandler
{
    private static void nextPendingRequest(final MapleClient c) {
        final BuddyEntry pendingBuddyRequest = c.getPlayer().getBuddylist().pollPendingRequest();
        if (pendingBuddyRequest != null) {
            c.sendPacket(MaplePacketCreator.requestBuddylistAdd(pendingBuddyRequest.getCharacterId(), pendingBuddyRequest.getName(), pendingBuddyRequest.getLevel(), pendingBuddyRequest.getJob()));
        }
    }
    
    public static final void BuddyOperationHandler(final LittleEndianAccessor slea, final MapleClient client) {
        final MapleCharacter player = client.getPlayer();
        final int mode = slea.readByte();
        final BuddyList buddyList = player.getBuddylist();
        switch (mode) {
            case 0: {
                final int unknow1 = slea.readInt();
                final int unknow2 = slea.readInt();
                client.sendPacket(MaplePacketCreator.updateBuddylist(player.getBuddylist().getBuddies()));
                break;
            }
            case 1: {
                final String buddyName = slea.readMapleAsciiString();
                final String buddyGroup = slea.readMapleAsciiString();
                final BuddyEntry oldBuddy = buddyList.get(buddyName);
                if (buddyName.length() > 13 || buddyGroup.length() > 16) {
                    nextPendingRequest(client);
                    return;
                }
                if (oldBuddy != null && oldBuddy.getGroup().equals((Object)buddyGroup)) {
                    client.sendPacket(MaplePacketCreator.buddylistMessage((byte)11));
                    nextPendingRequest(client);
                    return;
                }
                if (oldBuddy != null) {
                    oldBuddy.setGroup(buddyGroup);
                    client.sendPacket(MaplePacketCreator.updateBuddylist(buddyList.getBuddies()));
                    nextPendingRequest(client);
                    return;
                }
                if (buddyList.isFull()) {
                    client.sendPacket(MaplePacketCreator.buddylistMessage((byte)11));
                    return;
                }
                final int buddyChannel = Find.findChannel(buddyName);
                BuddyEntry buddy = null;
                BuddyAddResult reqRes = null;
                if (buddyChannel > 0) {
                    final MapleCharacter buddyChar = ChannelServer.getInstance(buddyChannel).getPlayerStorage().getCharacterByName(buddyName);
                    if (!buddyChar.isGM() || player.isGM()) {
                        buddy = new BuddyEntry(buddyChar.getName(), buddyChar.getId(), buddyGroup, buddyChannel, false, (int)buddyChar.getLevel(), (int)buddyChar.getJob());
                    }
                }
                else {
                    buddy = BuddyEntry.getByNameFromDB(buddyName);
                }
                if (buddy == null) {
                    client.sendPacket(MaplePacketCreator.buddylistMessage((byte)15));
                    nextPendingRequest(client);
                    return;
                }
                if (buddyChannel > 0) {
                    reqRes = Buddy.requestBuddyAdd(buddyName, client.getChannel(), client.getPlayer().getId(), client.getPlayer().getName(), (int)client.getPlayer().getLevel(), (int)client.getPlayer().getJob());
                }
                else {
                    final int buddyCount = BuddyList.getBuddyCount(buddy.getCharacterId(), 0);
                    if (buddyCount == -1) {
                        throw new RuntimeException("Result set expected");
                    }
                    if (buddyCount >= BuddyList.getBuddyCapacity(buddy.getCharacterId())) {
                        reqRes = BuddyAddResult.BUDDYLIST_FULL;
                    }
                    final int pending = BuddyList.getBuddyPending(buddy.getCharacterId(), player.getId());
                    if (pending > -1) {
                        reqRes = BuddyAddResult.ALREADY_ON_LIST;
                    }
                }
                if (reqRes == BuddyAddResult.BUDDYLIST_FULL) {
                    client.sendPacket(MaplePacketCreator.buddylistMessage((byte)12));
                    break;
                }
                if (reqRes == BuddyAddResult.ALREADY_ON_LIST && buddyChannel > 0) {
                    final MapleCharacter buddyChara = ChannelServer.getInstance(buddyChannel).getPlayerStorage().getCharacterByName(buddyName);
                    notifyRemoteChannel(client, buddyChannel, buddy.getCharacterId(), buddyGroup, BuddyOperation.ADDED);
                    buddyList.put(new BuddyEntry(buddyName, buddyChara.getId(), buddyGroup, buddyChannel, true, (int)buddyChara.getLevel(), (int)buddyChara.getJob()));
                    client.sendPacket(MaplePacketCreator.updateBuddylist(buddyList.getBuddies()));
                    client.sendPacket(MaplePacketCreator.updateBuddyChannel(buddyChara.getId(), buddyChannel - 1));
                    break;
                }
                BuddyList.addBuddyToDB(player, buddy);
                buddyList.put(buddy);
                client.sendPacket(MaplePacketCreator.updateBuddylist(buddyList.getBuddies()));
                nextPendingRequest(client);
                break;
            }
            case 2: {
                final int buddyCharId = slea.readInt();
                if (buddyList.isFull()) {
                    client.sendPacket(MaplePacketCreator.buddylistMessage((byte)11));
                    nextPendingRequest(client);
                    return;
                }
                final int buddyChannel2 = Find.findChannel(buddyCharId);
                BuddyEntry buddy2;
                if (buddyChannel2 < 0) {
                    buddy2 = BuddyEntry.getByIdfFromDB(buddyCharId);
                }
                else {
                    final MapleCharacter buddyChar2 = ChannelServer.getInstance(buddyChannel2).getPlayerStorage().getCharacterById(buddyCharId);
                    buddy2 = new BuddyEntry(buddyChar2.getName(), buddyChar2.getId(), "其他", buddyChannel2, true, (int)buddyChar2.getLevel(), (int)buddyChar2.getJob());
                }
                if (buddy2 == null) {
                    client.sendPacket(MaplePacketCreator.buddylistMessage((byte)11));
                }
                else {
                    buddyList.put(buddy2);
                    client.sendPacket(MaplePacketCreator.updateBuddylist(buddyList.getBuddies()));
                    notifyRemoteChannel(client, buddyChannel2, buddyCharId, "其他", BuddyOperation.ADDED);
                }
                nextPendingRequest(client);
                break;
            }
            case 3: {
                final int buddyCharId = slea.readInt();
                final BuddyEntry buddy3 = buddyList.get(buddyCharId);
                if (buddy3 != null && buddy3.isVisible()) {
                    notifyRemoteChannel(client, Find.findChannel(buddyCharId), buddyCharId, buddy3.getGroup(), BuddyOperation.DELETED);
                }
                buddyList.remove(buddyCharId);
                client.sendPacket(MaplePacketCreator.updateBuddylist(player.getBuddylist().getBuddies()));
                nextPendingRequest(client);
                break;
            }
            case 82: {
                final int unknow1 = slea.readShort();
                final int unknow2 = slea.readByte();
                client.sendPacket(MaplePacketCreator.updateBuddylist(player.getBuddylist().getBuddies()));
                break;
            }
            case 125: {
                break;
            }
            default: {
                FilePrinter.printError("BuddyListHandler.txt", "Unknown Buddylist Operation " + String.valueOf(mode) + " " + slea.toString());
                break;
            }
        }
    }
    
    private static void notifyRemoteChannel(final MapleClient c, final int remoteChannel, final int otherCid, final String group, final BuddyOperation operation) {
        final MapleCharacter player = c.getPlayer();
        if (remoteChannel > 0) {
            Buddy.buddyChanged(otherCid, player.getId(), player.getName(), c.getChannel(), operation, (int)player.getLevel(), (int)player.getJob(), group);
        }
    }
}
