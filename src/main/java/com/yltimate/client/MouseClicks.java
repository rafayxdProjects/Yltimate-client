package com.yltimate.client;

import java.util.ArrayDeque;
import java.util.Deque;

public final class MouseClicks {
    private static final Deque<Long> LEFT_CLICKS = new ArrayDeque<>();
    private MouseClicks() {}

    public static synchronized void registerLeftClick() {
        long now = System.currentTimeMillis();
        LEFT_CLICKS.addLast(now);
        trim(now);
    }

    public static synchronized int getCps() {
        trim(System.currentTimeMillis());
        return LEFT_CLICKS.size();
    }

    private static void trim(long now) {
        while (!LEFT_CLICKS.isEmpty() && now - LEFT_CLICKS.peekFirst() > 1000L) LEFT_CLICKS.removeFirst();
    }
}
