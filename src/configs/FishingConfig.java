package configs;

import commons.configuration.Property;

public class FishingConfig
{
    @Property(key = "Fishing.Enable", defaultValue = "true")
    public static boolean FISHING_ENABLE;
    @Property(key = "Fishing.Check.MAP", defaultValue = "749050500,749050501,749050502,970020000,970020005")
    public static String FISHING_MAP;
    @Property(key = "Fishing.Check.Chair", defaultValue = "true")
    public static boolean FISHING_CHECK_CHAIR;
    @Property(key = "Fishing.Chair", defaultValue = "3011000")
    public static int FISHING_CHAIR;
    @Property(key = "Fishing.Time", defaultValue = "60000")
    public static int FISHING_TIME;
    @Property(key = "Fishing.Time.Vip", defaultValue = "30000")
    public static int FISHING_TIME_VIP;
    @Property(key = "Fishing.Time.GM", defaultValue = "10000")
    public static int FISHING_TIME_GM;
    @Property(key = "Fishing.Chance", defaultValue = "70")
    public static int FISHING_CHANCE;
    @Property(key = "Fishing.Chance.Vip", defaultValue = "90")
    public static int FISHING_CHANCE_VIP;
    @Property(key = "Fishing.Chance.GM", defaultValue = "100")
    public static int FISHING_CHANCE_GM;
}
