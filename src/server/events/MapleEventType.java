package server.events;

public enum MapleEventType
{
    爬繩子("爬繩子", new int[] { 109030001, 109030002, 109030003 }), 
    終極忍耐("終極忍耐", new int[] { 109040000, 109040001, 109040002, 109040003, 109040004 }), 
    滾雪球("滾雪球", new int[] { 109060000 }), 
    尋寶("尋寶", new int[] { 109010000, 109010100, 109010102, 109010103, 109010104, 109010105, 109010106, 109010107, 109010108, 109010109, 109010110, 109010200, 109010201, 109010202, 109010203, 109010204, 109010205, 109010206 });
    
    public String command;
    public int[] mapids;
    
    private MapleEventType(final String comm, final int[] mapids) {
        this.command = comm;
        this.mapids = mapids;
    }
    
    public static final MapleEventType getByString(final String splitted) {
        for (final MapleEventType t : values()) {
            if (t.command.equalsIgnoreCase(splitted)) {
                return t;
            }
        }
        return null;
    }
}
