package configs;

import commons.configuration.Property;

public class ServerConfig
{
    @Property(key = "server.maple.version", defaultValue = "123")
    public static short MAPLE_VERSION;
    @Property(key = "server.maple.patch", defaultValue = "1")
    public static String MAPLE_PATCH;
    @Property(key = "server.maple.type", defaultValue = "5")
    public static byte MAPLE_TYPE;
    @Property(key = "world.beginner.map", defaultValue = "50000")
    public static int BEGINNER_SPAWN_MAP;
    @Property(key = "world.autoban.enable", defaultValue = "false")
    public static boolean AUTO_BAN_ENABLE;
    @Property(key = "server.market.chalk.board", defaultValue = "true")
    public static boolean chalkboard;
    @Property(key = "server.apply.debuff", defaultValue = "false")
    public static boolean applyDebuff;
    @Property(key = "server.apply.monster.status", defaultValue = "false")
    public static boolean applyMonsterStatus;
}
