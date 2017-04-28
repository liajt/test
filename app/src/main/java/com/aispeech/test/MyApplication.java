package com.aispeech.test;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import okhttp3.WebSocket;

/**
 * Created by liangjiatang on 2017/4/28.
 */

public class MyApplication extends Application {
    private String TAG = "MyApplication";

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        // 建立连接
        WebSocket webSocket = WS.connect();
        if (webSocket != null) {
            Log.i(TAG, "webSocket连接成功");
        }
    }
}
