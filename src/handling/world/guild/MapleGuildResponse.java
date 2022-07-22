package handling.world.guild;

import tools.MaplePacketCreator;

public enum MapleGuildResponse
{
    NOT_IN_CHANNEL(42), 
    ALREADY_IN_GUILD(40), 
    NOT_IN_GUILD(45);
    
    private final int value;
    
    private MapleGuildResponse(final int val) {
        this.value = val;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public byte[] getPacket() {
        return MaplePacketCreator.genericGuildMessage((byte)this.value);
    }
}
