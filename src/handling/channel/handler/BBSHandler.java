package handling.channel.handler;

import java.util.Iterator;
import java.util.List;
import handling.world.guild.MapleBBSThread;
import tools.MaplePacketCreator;
import handling.world.World.Guild;
import client.MapleClient;
import tools.data.LittleEndianAccessor;

public class BBSHandler
{
    private static String correctLength(final String in, final int maxSize) {
        if (in.length() > maxSize) {
            return in.substring(0, maxSize);
        }
        return in;
    }
    
    public static final void HandleBBS(final LittleEndianAccessor slea, final MapleClient c) {
        if (c.getPlayer().getGuildId() <= 0) {
            return;
        }
        try {
            int localThreadId = 0;
            final int read = slea.readByte();
            final BBSOperation action = BBSOperation.getByValue((byte)read);
            if (action != null) {
                Label_0222: {
                    switch (action) {
                        case ADD_THREAD: {
                            final boolean isEdit = slea.readByte() > 0;
                            if (isEdit) {
                                localThreadId = slea.readInt();
                            }
                            final boolean isNotice = slea.readByte() > 0;
                            final String title = correctLength(slea.readMapleAsciiString(), 25);
                            final String content = correctLength(slea.readMapleAsciiString(), 600);
                            final int icon = slea.readInt();
                            if (icon >= 100 && icon <= 106) {
                                if (!c.getPlayer().haveItem(5290000 + icon - 100, 1, false, true)) {
                                    return;
                                }
                            }
                            else if (icon < 0 || icon > 2) {
                                return;
                            }
                            if (!isEdit) {
                                addNewBBSThread(c, title, content, icon, isNotice);
                                break Label_0222;
                            }
                            editBBSThread(c, title, content, icon, localThreadId);
                            break Label_0222;
                        }
                        case DELETE_THREAD: {
                            localThreadId = slea.readInt();
                            deleteBBSThread(c, localThreadId);
                        }
                        case LIST_THREAD: {
                            try {
                                final int start = slea.readInt();
                                listBBSThreads(c, start * 10);
                            }
                            catch (ArrayIndexOutOfBoundsException ex) {}
                        }
                        case DISPLAY_THREAD: {
                            try {
                                localThreadId = slea.readInt();
                                displayThread(c, localThreadId);
                            }
                            catch (ArrayIndexOutOfBoundsException ex2) {}
                        }
                        case ADD_REPLY: {
                            localThreadId = slea.readInt();
                            final String text = correctLength(slea.readMapleAsciiString(), 25);
                            newBBSReply(c, localThreadId, text);
                        }
                        case DELETE_REPLY: {
                            localThreadId = slea.readInt();
                            final int replyid = slea.readInt();
                            deleteBBSReply(c, localThreadId, replyid);
                            break;
                        }
                        default: {
                            System.err.println("未處理的BBS動作: " + read);
                            break;
                        }
                    }
                }
            }
            else {
                System.err.println("未處理的BBS動作: " + read);
            }
        }
        catch (ArrayIndexOutOfBoundsException ex3) {}
    }
    
    private static void listBBSThreads(final MapleClient c, final int start) {
        c.sendPacket(MaplePacketCreator.BBSThreadList(Guild.getBBS(c.getPlayer().getGuildId()), start));
    }
    
    private static void newBBSReply(final MapleClient c, final int localthreadid, final String text) {
        Guild.addBBSReply(c.getPlayer().getGuildId(), localthreadid, text, c.getPlayer().getId());
        displayThread(c, localthreadid);
    }
    
    private static void editBBSThread(final MapleClient c, final String title, final String text, final int icon, final int localthreadid) {
        Guild.editBBSThread(c.getPlayer().getGuildId(), localthreadid, title, text, icon, c.getPlayer().getId(), (int)c.getPlayer().getGuildRank());
        displayThread(c, localthreadid);
    }
    
    private static void addNewBBSThread(final MapleClient c, final String title, final String text, final int icon, final boolean bNotice) {
        displayThread(c, Guild.addBBSThread(c.getPlayer().getGuildId(), title, text, icon, bNotice, c.getPlayer().getId()));
    }
    
    private static void deleteBBSThread(final MapleClient c, final int localthreadid) {
        Guild.deleteBBSThread(c.getPlayer().getGuildId(), localthreadid, c.getPlayer().getId(), (int)c.getPlayer().getGuildRank());
    }
    
    private static void deleteBBSReply(final MapleClient c, final int localthreadid, final int replyid) {
        Guild.deleteBBSReply(c.getPlayer().getGuildId(), localthreadid, replyid, c.getPlayer().getId(), (int)c.getPlayer().getGuildRank());
        displayThread(c, localthreadid);
    }
    
    private static void displayThread(final MapleClient c, final int localthreadid) {
        final List<MapleBBSThread> bbsList = Guild.getBBS(c.getPlayer().getGuildId());
        if (bbsList != null) {
            for (final MapleBBSThread t : bbsList) {
                if (t != null && t.localthreadID == localthreadid) {
                    c.sendPacket(MaplePacketCreator.showThread(t));
                }
            }
        }
    }
    
    enum BBSOperation
    {
        ADD_THREAD((byte)0), 
        DELETE_THREAD((byte)1), 
        LIST_THREAD((byte)2), 
        DISPLAY_THREAD((byte)3), 
        ADD_REPLY((byte)4), 
        DELETE_REPLY((byte)5);
        
        byte value;
        
        private BBSOperation(final byte value) {
            this.value = -1;
            this.value = value;
        }
        
        public int getValue() {
            return this.value;
        }
        
        public static BBSOperation getByValue(final byte value) {
            for (final BBSOperation o : values()) {
                if (o.getValue() == value) {
                    return o;
                }
            }
            return null;
        }
    }
}
