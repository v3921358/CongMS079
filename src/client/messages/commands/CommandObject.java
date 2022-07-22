package client.messages.commands;

import constants.ServerConstants.CommandType;
import client.MapleClient;

public class CommandObject
{
    private final String command;
    private final int gmLevelReq;
    private final CommandExecute exe;
    
    public CommandObject(final String com, final CommandExecute c, final int gmLevel) {
        this.command = com;
        this.exe = c;
        this.gmLevelReq = gmLevel;
    }
    
    public boolean execute(final MapleClient c, final String[] splitted) {
        return this.exe.execute(c, splitted);
    }
    
    public CommandType getType() {
        return this.exe.getType();
    }
    
    public int getReqGMLevel() {
        return this.gmLevelReq;
    }
    
    public String getMessage() {
        return (this.command != null) ? this.exe.getMessage() : "";
    }
}
