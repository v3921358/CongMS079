package server.life;

public enum Element
{
    NEUTRAL(0), 
    PHYSICAL(1), 
    FIRE(2, true), 
    ICE(3, true), 
    LIGHTING(4), 
    POISON(5), 
    HOLY(6, true), 
    DARKNESS(7);
    
    private int value;
    private boolean special;
    
    private Element(final int v) {
        this.special = false;
        this.value = v;
    }
    
    private Element(final int v, final boolean special) {
        this.special = false;
        this.value = v;
        this.special = special;
    }
    
    public boolean isSpecial() {
        return this.special;
    }
    
    public static Element getFromChar(final char c) {
        switch (Character.toUpperCase(c)) {
            case 'F': {
                return Element.FIRE;
            }
            case 'I': {
                return Element.ICE;
            }
            case 'L': {
                return Element.LIGHTING;
            }
            case 'S': {
                return Element.POISON;
            }
            case 'H': {
                return Element.HOLY;
            }
            case 'P': {
                return Element.PHYSICAL;
            }
            case 'D': {
                return Element.DARKNESS;
            }
            default: {
                throw new IllegalArgumentException("unknown elemnt char " + c);
            }
        }
    }
    
    public static Element getFromId(final int c) {
        for (final Element e : values()) {
            if (e.value == c) {
                return e;
            }
        }
        throw new IllegalArgumentException("unknown elemnt id " + c);
    }
    
    public int getValue() {
        return this.value;
    }
}
