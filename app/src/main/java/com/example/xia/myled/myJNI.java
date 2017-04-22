package com.example.xia.myled;

/**
 * Created by xia on 17-1-4.
 */

public class myJNI {

    static {
        System.loadLibrary("JniTest");
    }

    public synchronized static native String sayHello();
}
