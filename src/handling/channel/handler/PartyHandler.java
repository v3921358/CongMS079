package handling.channel.handler;

import client.MapleJob;
import client.MapleCharacter;
import handling.world.MapleParty;
import tools.MaplePacketCreator;
import handling.world.MaplePartyCharacter;
import handling.world.PartyOperation;
import handling.world.World.Party;
import client.MapleClient;
import tools.data.LittleEndianAccessor;

public class PartyHandler
{
    public static final void DenyPartyRequest(final LittleEndianAccessor slea, final MapleClient c) {
        final int action = slea.readByte();
        final int partyid = slea.readInt();
        if (c.getPlayer().getParty() == null) {
            final MapleParty party = Party.getParty(partyid);
            if (party != null) {
                if (action == 27) {
                    if (party.getMembers().size() < 6) {
                        Party.updateParty(partyid, PartyOperation.JOIN, new MaplePartyCharacter(c.getPlayer()));
                        c.getPlayer().receivePartyMemberHP();
                        c.getPlayer().updatePartyMemberHP();
                    }
                    else {
                        c.sendPacket(MaplePacketCreator.partyStatusMessage(17));
                    }
                }
                else if (action != 22) {
                    final MapleCharacter cfrom = c.getChannelServer().getPlayerStorage().getCharacterById(party.getLeader().getId());
                    if (cfrom != null) {
                        cfrom.getClient().sendPacket(MaplePacketCreator.partyStatusMessage(23, c.getPlayer().getName()));
                    }
                }
            }
            else {
                c.getPlayer().dropMessage(5, "已拒絕組队邀請。");
            }
        }
        else {
            c.getPlayer().dropMessage(5, "不能加入組队，因為已經有組队了。");
        }
    }
    
    public static final void PartyOperatopn(final LittleEndianAccessor slea, final MapleClient c) {
        final int operation = slea.readByte();
        MapleParty party = c.getPlayer().getParty();
        final MaplePartyCharacter partyplayer = new MaplePartyCharacter(c.getPlayer());
        switch (operation) {
            case 1: {
                if (c.getPlayer().getParty() == null) {
                    if (!MapleJob.isBeginner((int)c.getPlayer().getJob()) || c.getPlayer().getLevel() >= 10) {
                        party = Party.createParty(partyplayer);
                        c.getPlayer().setParty(party);
                        c.sendPacket(MaplePacketCreator.partyCreated(party.getId()));
                        break;
                    }
                    c.sendPacket(MaplePacketCreator.partyStatusMessage(10));
                    break;
                }
                else {
                    if (partyplayer.equals((Object)party.getLeader()) && party.getMembers().size() == 1) {
                        c.sendPacket(MaplePacketCreator.partyCreated(party.getId()));
                        break;
                    }
                    c.getPlayer().dropMessage(5, "不能創建組队，因為已經有組队了。");
                    break;
                }
            }
            case 2: {
                if (party != null) {
                    if (partyplayer.equals((Object)party.getLeader())) {
                        Party.updateParty(party.getId(), PartyOperation.DISBAND, partyplayer);
                        if (c.getPlayer().getEventInstance() != null) {
                            c.getPlayer().getEventInstance().disbandParty();
                        }
                        if (c.getPlayer().getPyramidSubway() != null) {
                            c.getPlayer().getPyramidSubway().fail(c.getPlayer());
                        }
                    }
                    else {
                        Party.updateParty(party.getId(), PartyOperation.LEAVE, partyplayer);
                        if (c.getPlayer().getEventInstance() != null) {
                            c.getPlayer().getEventInstance().leftParty(c.getPlayer());
                        }
                        if (c.getPlayer().getPyramidSubway() != null) {
                            c.getPlayer().getPyramidSubway().fail(c.getPlayer());
                        }
                    }
                    c.getPlayer().setParty(null);
                    break;
                }
                break;
            }
            case 3: {
                final int partyid = slea.readInt();
                if (c.getPlayer().getMapId() == 914000300 && c.getPlayer().getMapId() == 914000220 && c.getPlayer().getMapId() == 914000210 && c.getPlayer().getMapId() == 914000200 && c.getPlayer().getMapId() == 914000100 && c.getPlayer().getMapId() == 914000000) {
                    c.getPlayer().dropMessage(5, "該地图不能加入組队。");
                    break;
                }
                if (c.getPlayer().getParty() != null) {
                    c.getPlayer().dropMessage(5, "不能加入組队，因為已經有組队了。");
                    break;
                }
                party = Party.getParty(partyid);
                if (party == null) {
                    c.getPlayer().dropMessage(5, "已拒絕組队邀請。");
                    break;
                }
                if (party.getMembers().size() < 6) {
                    Party.updateParty(party.getId(), PartyOperation.JOIN, partyplayer);
                    c.getPlayer().receivePartyMemberHP();
                    c.getPlayer().updatePartyMemberHP();
                    break;
                }
                c.sendPacket(MaplePacketCreator.partyStatusMessage(17));
                break;
            }
            case 4: {
                final MapleCharacter invited = c.getChannelServer().getPlayerStorage().getCharacterByName(slea.readMapleAsciiString());
                if (invited == null) {
                    c.sendPacket(MaplePacketCreator.partyStatusMessage(19));
                    break;
                }
                if (invited.getParty() != null || party == null) {
                    c.sendPacket(MaplePacketCreator.partyStatusMessage(16));
                    break;
                }
                if (party.getMembers().size() < 6) {
                    invited.getClient().sendPacket(MaplePacketCreator.partyInvite(c.getPlayer()));
                    break;
                }
                c.sendPacket(MaplePacketCreator.partyStatusMessage(17));
                break;
            }
            case 5: {
                if (partyplayer.equals((Object)party.getLeader())) {
                    final MaplePartyCharacter expelled = party.getMemberById(slea.readInt());
                    if (expelled != null) {
                        Party.updateParty(party.getId(), PartyOperation.EXPEL, expelled);
                        if (c.getPlayer().getEventInstance() != null && expelled.isOnline()) {
                            c.getPlayer().getEventInstance().disbandParty();
                        }
                        if (c.getPlayer().getPyramidSubway() != null && expelled.isOnline()) {
                            c.getPlayer().getPyramidSubway().fail(c.getPlayer());
                        }
                    }
                    break;
                }
                break;
            }
            case 6: {
                if (party != null) {
                    final MaplePartyCharacter newleader = party.getMemberById(slea.readInt());
                    if (partyplayer.getMapid() != 910010000) {
                        if (newleader != null && partyplayer.equals((Object)party.getLeader())) {
                            Party.updateParty(party.getId(), PartyOperation.CHANGE_LEADER, newleader);
                        }
                    }
                    else {
                        c.getPlayer().dropMessage(5, "當前地图無法變更队長");
                    }
                    break;
                }
                break;
            }
            default: {
                System.err.println("Unhandled Party function." + operation);
                break;
            }
        }
    }
}
