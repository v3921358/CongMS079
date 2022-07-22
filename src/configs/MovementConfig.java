package configs;

import commons.configuration.Property;

public class MovementConfig
{
    @Property(key = "Bounce.Movement", defaultValue = "-1")
    public static String bounceMovement;
    @Property(key = "Absolute.Life.Movement", defaultValue = "0x00,0x08")
    public static String absoluteLifeMovement;
    @Property(key = "Relative.Life.Movement", defaultValue = "0x01,0x02,0x17")
    public static String relativeLifeMovement;
    @Property(key = "Teleport.Movement", defaultValue = "0x04,0x05")
    public static String teleportMovement;
    @Property(key = "Change.Equip.Movement", defaultValue = "0x0C")
    public static String changeEquipMovement;
    @Property(key = "Chair.Movement", defaultValue = "0x03")
    public static String chairMovement;
    @Property(key = "Aran.Movement", defaultValue = "0x13")
    public static String aranMovement;
    @Property(key = "Jump.Down.Movement", defaultValue = "0x0F")
    public static String jumpDownMovement;
    @Property(key = "Static.Life.Movement", defaultValue = "0x37,0x42")
    public static String staticLifeMovement;
}
