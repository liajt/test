package com.aispeech.test;

import android.util.Log;
import android.widget.Toast;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by liangjiatang on 2017/4/28.
 */

public class WS {
    private static String TAG = "websocket";
    public static WebSocket mWebSocket;
    static boolean isRecording = true;

    public static WebSocket connect() {
        //新建client
        OkHttpClient client = new OkHttpClient.Builder().build();
//        String url = "ws://s-test.api.aispeech.com:10000/cn.asr.rec/aihome?" + getAuthParam("14713318288595da", "fa7817c91a116832213ceb7f615077f5");
        String url = "ws://58.210.96.236:8888/cn.asr.rec/aihome?" + getAuthParam("14713318288595da", "fa7817c91a116832213ceb7f615077f5");
        Log.i(TAG, "url:" + url);
        //构造request对象
        Request request = new Request.Builder()
                .url(url)
                .build();
        WebSocket webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                //保存引用，用于后续操作
                mWebSocket = webSocket;
                //打印一些内容
                Log.i(TAG, "client onOpen");
                Log.i(TAG, "client request header:" + response.request().headers());
                Log.i(TAG, "client response header:" + response.headers());
                Log.i(TAG, "client response:" + response);
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                Log.i(TAG, "onMessage:" + text);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
                Log.i(TAG, "onMessage:" + bytes);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
                Log.i(TAG, "onClosing:" + reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
                Log.i(TAG, "onClosed:" + reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                super.onFailure(webSocket, t, response);
                Log.i(TAG, "onFailure:" + Log.getStackTraceString(t));
            }
        });
        return webSocket;
    }

    private static String getAuthParam(String appkey, String secretkey) {
        String authIdStr = UUID.randomUUID().toString();
        long timestamp = System.currentTimeMillis() / 1000;
        String sigStr = null;
        try {
            sigStr = HMACSHA1.getSignature(appkey + "\n" + timestamp + "\n" + secretkey + "\n" + authIdStr, secretkey);
        } catch (InvalidKeyException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        // applicationId=$applicationId\&timestamp=$timestamp\&authId=$authId\&sig=$sig
        return "applicationId=" + appkey + "&" + "timestamp=" + timestamp + "&" + "authId=" + authIdStr + "&" + "sig="
                + sigStr;
    }
}
