package server.life;

public enum ElementalEffectiveness
{
    NORMAL, 
    IMMUNE, 
    STRONG, 
    WEAK;
    
    public static ElementalEffectiveness getByNumber(final int num) {
        switch (num) {
            case 1: {
                return ElementalEffectiveness.IMMUNE;
            }
            case 2: {
                return ElementalEffectiveness.STRONG;
            }
            case 3: {
                return ElementalEffectiveness.WEAK;
            }
            default: {
                throw new IllegalArgumentException("Unkown effectiveness: " + num);
            }
        }
    }
}
