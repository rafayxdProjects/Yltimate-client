package com.yltimate.client;

public final class Combo {
    private static int count = 0;
    private static long lastHit = 0;
    private Combo() {}

    public static void hit() {
        count++;
        lastHit = System.currentTimeMillis();
    }

    /** Resets the combo when you get hurt or stop hitting for 2 seconds. */
    public static void tick(boolean hurt) {
        if (hurt || System.currentTimeMillis() - lastHit > 2000L) count = 0;
    }

    public static int get() { return count; }
}
