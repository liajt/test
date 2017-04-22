package com.aispeech.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.xia.myled.myJNI;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.VitamioLicense;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class MainActivity extends AppCompatActivity {

    private Button btn_adapter;
    private VideoView mVvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("onCreate", myJNI.sayHello());

        // ~~~ 检测Vitamio是否解压解码包
        if (!LibsChecker.checkVitamioLibs(this))
            Log.e("Vitamio", "checkVitamioLibs false");
        else
            Log.i("Vitamio", "checkVitamioLibs true");

        Vitamio.initialize(this);

        mVvv = (VideoView) findViewById(R.id.vv);
        // 硬解码
        mVvv.setHardwareDecoder(false);
//        mVvv.setVideoURI(Uri.parse("http://qiubai-video.qiushibaike.com/91B2TEYP9D300XXH_3g.mp4"));
        mVvv.setVideoPath(Environment.getExternalStorageDirectory()
                + "/pijiu.avi");
        mVvv.setMediaController(new MediaController(this));

        //设置监听
        mVvv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVvv.start();
            }
        });
        mVvv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e("Vitamio", "onError");
                return true;
            }
        });
        mVvv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
            }
        });

        btn_adapter = (Button) findViewById(R.id.btn_adapter);
        btn_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new Intent("Action_start_adapter"));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}
