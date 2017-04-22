package com.example.xia.myled;

/**
 * Created by liangjiatang on 2017/3/28.
 */

public class myJNI {
    static {
        System.loadLibrary("JniTest");
    }

    public static native String sayHello();
}
