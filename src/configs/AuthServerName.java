package configs;

public enum AuthServerName
{
    奇幻冒险岛("00-26-6C-F1-8B-D4"), 
    输了爱("9C-B7-0D-33-1E-B2"), 
    输了爱2("02-00-4C-4F-4F-50"), 
    输了爱3("1C-78-39-00-B4-23"), 
    菠萝("24-0A-64-A0-81-C9"), 
    奇乐冒险岛("00-26-6C-F0-18-FD"), 
    奇乐冒险岛2("F4-CE-46-BF-5D-CC"), 
    奇乐冒险岛3("74-D4-35-BB-0B-AA"), 
    羁绊冒险岛("94-DE-80-F8-60-FD"), 
    安妮冒险岛("08-60-6E-C4-D4-C6"), 
    暖冬冒险岛("AC-22-0B-80-28-89"), 
    时光冒险岛("FC-AA-14-0B-2A-00"), 
    猫咪冒险岛("94-DE-80-04-26-6F"), 
    猫咪冒险岛2("FC-AA-14-79-21-26"), 
    漂漂猪冒险岛A("78-2B-CB-73-65-40"), 
    匆匆冒险岛("94-DE-80-AE-B5-54"), 
    北岸冒险岛("00-1E-C9-D3-34-A9"), 
    魅力冒险岛("00-1D-09-71-8C-EA"), 
    魅力冒险岛2("14-DA-E9-39-20-60"), 
    迷你冒险岛("18-A9-05-3D-34-A2"), 
    小灿客户A("00-26-6C-F4-49-2C"), 
    小灿冒险岛("D8-50-E6-B9-F3-CA"), 
    筱希冒险岛("00-1E-C9-B2-3B-E5"), 
    蘑菇冒险岛("00-22-19-5C-72-6A"), 
    一起冒险岛("00-1E-C9-B6-12-F9"), 
    倾城冒险岛("00-1E-C9-AB-52-43"), 
    酷暑冒险岛("00-A0-D1-EC-2B-8C"), 
    冰封冒险岛("00-16-3E-00-01-D2"), 
    无双冒险岛("00-16-3E-00-38-32");
    
    String mac;
    
    private AuthServerName(final String mac) {
        this.mac = mac;
    }
    
    public String getMac() {
        return this.mac;
    }
    
    public static AuthServerName getName(final String mac) {
        for (final AuthServerName n : values()) {
            if (n.getMac().equals((Object)mac)) {
                return n;
            }
        }
        return null;
    }
}
