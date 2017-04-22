package com.xia.led;

/**
 * Created by xia on 3/28/17.
 */

public class LedRedJni {
    static {
        System.loadLibrary("ledred");
    }

    public synchronized static native int ledredOpen(int index);
    public synchronized static native int ledredRelease(int index);
    public synchronized static native int ledredRead(int index);
    public synchronized static native int ledredWrite(int index, int value);
}
