package handling.world;

import java.util.Objects;
import java.io.Serializable;

public class CheaterData implements Serializable, Comparable<CheaterData>
{
    private static final long serialVersionUID = -8733673311051249885L;
    private final int points;
    private final String info;
    
    public CheaterData(final int points, final String info) {
        this.points = points;
        this.info = info;
    }
    
    public String getInfo() {
        return this.info;
    }
    
    public int getPoints() {
        return this.points;
    }
    
    @Override
    public int compareTo(final CheaterData o) {
        final int thisVal = this.getPoints();
        final int anotherVal = o.getPoints();
        return (thisVal < anotherVal) ? 1 : ((thisVal == anotherVal) ? 0 : -1);
    }
    
    @Override
    public boolean equals(final Object oth) {
        if (!(oth instanceof CheaterData)) {
            return false;
        }
        final CheaterData obj = (CheaterData)oth;
        return obj.points == this.points && obj.info.equals((Object)this.info);
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.points;
        hash = 83 * hash + Objects.hashCode((Object)this.info);
        return hash;
    }
}
