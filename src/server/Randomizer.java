package server;

import java.util.Random;

public class Randomizer
{
    private static final Random rand;
    
    public static final int nextInt() {
        return Randomizer.rand.nextInt();
    }
    
    public static final int nextInt(final int arg0) {
        return Randomizer.rand.nextInt(arg0);
    }
    
    public static final void nextBytes(final byte[] bytes) {
        Randomizer.rand.nextBytes(bytes);
    }
    
    public static final boolean nextBoolean() {
        return Randomizer.rand.nextBoolean();
    }
    
    public static final double nextDouble() {
        return Randomizer.rand.nextDouble();
    }
    
    public static final float nextFloat() {
        return Randomizer.rand.nextFloat();
    }
    
    public static final long nextLong() {
        return Randomizer.rand.nextLong();
    }
    
    public static final int rand(final int lbound, final int ubound) {
        return (int)(Randomizer.rand.nextDouble() * (double)(ubound - lbound + 1) + (double)lbound);
    }
    
    static {
        rand = new Random();
    }
}
