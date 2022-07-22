package tools;

import java.io.Serializable;

public class Quadra<F, S, T, Fo> implements Serializable
{
    private static final long serialVersionUID = 9179541993413739999L;
    public F first;
    public S second;
    public T third;
    public Fo forth;
    
    public Quadra(final F first, final S second, final T third, final Fo forth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.forth = forth;
    }
    
    public F getFirst() {
        return this.first;
    }
    
    public S getSecond() {
        return this.second;
    }
    
    public T getThird() {
        return this.third;
    }
    
    public Fo getForth() {
        return this.forth;
    }
    
    @Override
    public String toString() {
        return this.first.toString() + ":" + this.second.toString() + ":" + this.third.toString() + ":" + this.forth.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + ((this.first == null) ? 0 : this.first.hashCode());
        result = 31 * result + ((this.second == null) ? 0 : this.second.hashCode());
        result = 31 * result + ((this.third == null) ? 0 : this.third.hashCode());
        result = 31 * result + ((this.forth == null) ? 0 : this.forth.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Quadra other = (Quadra)obj;
        if (this.first == null) {
            if (other.first != null) {
                return false;
            }
        }
        else if (!this.first.equals((Object)other.first)) {
            return false;
        }
        if (this.second == null) {
            if (other.second != null) {
                return false;
            }
        }
        else if (!this.second.equals((Object)other.second)) {
            return false;
        }
        if (this.third == null) {
            if (other.third != null) {
                return false;
            }
        }
        else if (!this.third.equals((Object)other.third)) {
            return false;
        }
        if (this.forth == null) {
            if (other.forth != null) {
                return false;
            }
        }
        else if (!this.forth.equals((Object)other.forth)) {
            return false;
        }
        return true;
    }
}
