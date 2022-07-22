package handling.channel.handler;

import tools.data.LittleEndianAccessor;
import scripting.NPCScriptManager;
import client.MapleClient;

public class UserInterfaceHandler
{
    public static final void CygnusSummonNPCRequest(final MapleClient c) {
        if (c.getPlayer().getJob() == 2000) {
            NPCScriptManager.getInstance().start(c, 1202000);
        }
        else if (c.getPlayer().getJob() == 1000) {
            NPCScriptManager.getInstance().start(c, 1101008);
        }
    }
    
    public static final void InGamePoll(final LittleEndianAccessor slea, final MapleClient c) {
    }
}
