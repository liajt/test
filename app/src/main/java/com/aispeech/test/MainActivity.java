package com.aispeech.test;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;

import io.vov.vitamio.widget.VideoView;
import okio.ByteString;

import static android.hardware.usb.UsbManager.ACTION_USB_DEVICE_ATTACHED;
import static android.hardware.usb.UsbManager.ACTION_USB_DEVICE_DETACHED;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "websocket";
    private Button btn_adapter;
    private VideoView mVvv;
    private AudioRecord mAudioRecord;

    private Button btn_start;
    private Button btn_stop;
    private AudioRecord audioRecord;

    private UsbManager usbManager;

    private boolean isRecording;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.i("onCreate", myJNI.sayHello());

        // ~~~ 检测Vitamio是否解压解码包
//        if (!LibsChecker.checkVitamioLibs(this))
//            Log.e("Vitamio", "checkVitamioLibs false");
//        else
//            Log.i("Vitamio", "checkVitamioLibs true");
//
//        Vitamio.initialize(this);
//
//        mVvv = (VideoView) findViewById(R.id.vv);
//        // 硬解码
//        mVvv.setHardwareDecoder(false);
////        mVvv.setVideoURI(Uri.parse("http://qiubai-video.qiushibaike.com/91B2TEYP9D300XXH_3g.mp4"));
//        mVvv.setVideoPath(Environment.getExternalStorageDirectory()
//                + "/pijiu.avi");
//        mVvv.setMediaController(new MediaController(this));
//
//        //设置监听
//        mVvv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mVvv.start();
//            }
//        });
//        mVvv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//            @Override
//            public boolean onError(MediaPlayer mp, int what, int extra) {
//                Log.e("Vitamio", "onError");
//                return true;
//            }
//        });
//        mVvv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//            }
//        });
//
//        btn_adapter = (Button) findViewById(R.id.btn_adapter);
//        btn_adapter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendBroadcast(new Intent("Action_start_adapter"));
//            }
//        });

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_USB_PERMISSION);
        filter.addAction(ACTION_USB_DEVICE_DETACHED);
        filter.addAction(ACTION_USB_DEVICE_ATTACHED);
        registerReceiver(mUsbReceiver, filter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
//            File file = new File("/mnt/sdcard/xxx.pcm");
//            FileWriter writer = new FileWriter(file);
//            while (true) {
//                byte[] buffer = new byte[320 * 2 * 5];//100ms
//                int size = mAudioRecord.read(buffer, 0, buffer.length);
//                if (size > 0) {
////                bc.publish("recorder.pcm", size == buffer.length ? buffer : Arrays.copyOfRange(buffer, 0, size));
//                    for (int i = 0; i <size ; i++) {
//                        writer.write(buffer[i]);
//                    }
//                    writer.flush();
//                }
//            }

//            OutputStream os = new FileOutputStream(dir);
//            BufferedOutputStream bos = new BufferedOutputStream(os);
//            DataOutputStream dos = new DataOutputStream(bos);
//
//            short[] buffer = new short[recBufSize];
//            while (isRecording) {
//                // 从MIC保存数据到缓冲区
//                int bufferReadResult = mAudioRecord.read(buffer, 0, recBufSize);
//                short[] tmpBuf = new short[bufferReadResult / rateX];
//                for (int i = 0, j = 0; i < tmpBuf.length; i++, j = i * rateX) {
//                    tmpBuf[i] = buffer[j];
//                    //第三步：写入数据
//                    dos.writeShort(tmpBuf[i]);
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 开始录音
//        StartRecord();

//        usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
//        HashMap<String, UsbDevice> map = usbManager.getDeviceList();
//        for (UsbDevice device : map.values()) {
//            Log.i(TAG, "dName: " + device.getDeviceName());
//            Log.i(TAG, "vid: " + device.getVendorId() + "\t pid: " + device.getProductId());
//            usbDevice = device;
//        }
//
//        PendingIntent pi = PendingIntent.getBroadcast(this, new Random().nextInt(1000), new Intent(ACTION_USB_PERMISSION), PendingIntent.FLAG_CANCEL_CURRENT);
//        if (usbManager.hasPermission(usbDevice)) {
//            //Other code
//            Log.i(TAG, "有权限");
//        } else {
//            Log.i(TAG, "需要授权");
//            //没有权限询问用户是否授予权限
//            //该代码执行后，系统弹出一个对话框，
//            //询问用户是否授予程序操作USB设备的权限
//            usbManager.requestPermission(usbDevice, pi);

//            // Register receiver for USB permission
//            PendingIntent mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(
//                    ACTION_USB_PERMISSION), 0);
//            Intent intent = new Intent();
//            intent.setAction(ACTION_USB_PERMISSION);
//            // Request permission
//            intent.putExtra(UsbManager.EXTRA_DEVICE, usbDevice);
//            intent.putExtra(UsbManager.EXTRA_PERMISSION_GRANTED, true);
//
//            final PackageManager pm = getPackageManager();
//            try {
//                ApplicationInfo aInfo = pm.getApplicationInfo(getPackageName(), 0);
//                try {
//                    IBinder b = ServiceManager.getService(USB_SERVICE);
//                    IUsbManager service = IUsbManager.Stub.asInterface(b);
//                    service.grantDevicePermission(usbDevice, aInfo.uid);
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            getApplicationContext().sendBroadcast(intent);
////			mManager.requestPermission(device, mPermissionIntent);
//            Log.i(TAG, "UsbManager.EXTRA_DEVICE 11111111111111======" + usbManager.openDevice(usbDevice));
//        }

    }

    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private UsbDevice usbDevice;
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.e(TAG, action);
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    usbDevice = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    //允许权限申请
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if (usbDevice != null) {
                            //call method to set up device communication
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    startUSBRecord();
                                }
                            }).start();
                        }
                    } else {
                        Log.d(TAG, "permission denied for device " + usbDevice);
                    }
                }
            } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                if (device != null) {
                    // call your method that cleans up and closes communication with the device
                }

            } else if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, new Random().nextInt(1000), new Intent(ACTION_USB_PERMISSION), PendingIntent.FLAG_CANCEL_CURRENT);
                if (device != null) {
                    if (usbManager.hasPermission(device)) {
                        //Other code
                        Log.i(TAG, "有权限");
                    } else {
                        Log.i(TAG, "需要授权");
                        //没有权限询问用户是否授予权限
                        //该代码执行后，系统弹出一个对话框，
                        //询问用户是否授予程序操作USB设备的权限
                        usbManager.requestPermission(device, pi);
                    }

                }
            }
            }
    };
    private byte[] bytes;
    private static int TIMEOUT = 3000;
    private boolean forceClaim = true;

    private void startUSBRecord() {
        Log.i(TAG, "starUsbRecord: list the usb devices");
        UsbInterface usbInterface = usbDevice.getInterface(0);
        //USBEndpoint为读写数据所需的节点
        UsbEndpoint inEndpoint = usbInterface.getEndpoint(0);  //读数据节点
//        UsbEndpoint outEndpoint = usbInterface.getEndpoint(1); //写数据节点
        UsbDeviceConnection connection = usbManager.openDevice(usbDevice);
        connection.claimInterface(usbInterface, true);
        //发送数据
        byte[] byte2 = new byte[64];    //发送与接收字节数与设备outputreport有关
//        int out = connection.bulkTransfer(outEndpoint, bytes, bytes.length, TIMEOUT);
//        Log.e(TAG, "out:" + out);
        //读取数据1   两种方法读取数据
        int ret = connection.bulkTransfer(inEndpoint, byte2, byte2.length, TIMEOUT);
        Log.e(TAG, "ret:" + ret);
        //还有第二种读取方式，未验证。
//        int outMax = outEndpoint.getMaxPacketSize();
        int inMax = inEndpoint.getMaxPacketSize();
        ByteBuffer byteBuffer = ByteBuffer.allocate(inMax);
        UsbRequest usbRequest = new UsbRequest();
        usbRequest.initialize(connection, inEndpoint);
        usbRequest.queue(byteBuffer, inMax);
        if (connection.requestWait() == usbRequest) {
            byte[] retData = byteBuffer.array();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Toast.makeText(MainActivity.this, "keyCode：" + keyCode + "  action：" + event.getAction(), Toast.LENGTH_SHORT).show();
        Log.i("onKeyDown", "keyCode：" + keyCode + "  action：" + event.getAction());
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                isRecording = true;
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        startRecord();
                    }
                });
                break;
            case R.id.btn_stop:
                isRecording = false;
                break;
        }
    }


    private static final String config = "{" +
            "    \"app\": {" +
            "        \"userId\": \"%s\"," +
            "        \"applicationId\":\"%s\"," +
            "        \"deviceId\":\"%s\"" +
            "    }," +
            "    \"audio\": {" +
            "        \"audioType\": \"wav\",   " +
            "        \"sampleBytes\": 2,     " +
            "        \"sampleRate\": 16000,  " +
            "        \"channel\": 1,         " +
            "        \"compress\": \"raw\"   " +
            "    }," +
            "    \"request\": {" +
            "        \"coreType\": \"%s\", " +
            "        \"res\": \"%s\"            " +
//            "        \"env\":\"use_cn_conf=1;nbest=3;\"" +
            "    }" +
            "}";

    //开始录音
    private void startRecord() {

        // 发送config
        String s = String.format(config, "aihome", "14713318288595da", "123456789012", "cn.asr.rec", "aihome");
        Log.i(TAG, "发送config:" + s);
        boolean isok = WS.mWebSocket.send(s);
        Log.i(TAG, "结果：" + isok);

        Log.i(TAG, "开始录音");
        //16K采集率
        int frequency = 16000;
        //格式
        int channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;
        //16Bit
        int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
        //生成PCM文件
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/zzzzzz.pcm");
        Log.i(TAG, "生成文件");
        //如果存在，就先删除再创建
        if (file.exists())
            file.delete();
        Log.i(TAG, "删除文件");
        try {
            file.createNewFile();
            Log.i(TAG, "创建文件");
        } catch (IOException e) {
            Log.i(TAG, "未能创建");
            throw new IllegalStateException("未能创建" + file.toString());
        }
        try {
            //输出流
            OutputStream os = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);
            int bufferSize = AudioRecord.getMinBufferSize(frequency, channelConfiguration, audioEncoding);
            AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, frequency, channelConfiguration, audioEncoding, bufferSize);

            byte[] buffer = new byte[3200];
            audioRecord.startRecording();
            Log.i(TAG, "开始录音");
            int j = 1;
            while (isRecording) {
                int bufferReadResult = audioRecord.read(buffer, 0, bufferSize);
                for (int i = 0; i < bufferReadResult; i++) {
                    dos.writeShort(buffer[i]);
                }
                // 发送报文
                byte[] tmp = bufferReadResult == buffer.length ? buffer : Arrays.copyOfRange(buffer, 0, bufferReadResult);
                Log.i(TAG, "send:" + ByteString.of(tmp));
                isok = WS.mWebSocket.send(ByteString.of(tmp));
                Log.i(TAG, "结果：" + isok);
//                Thread.sleep(100);

            }
            // 结束报文
            Log.i(TAG, "send:" + ByteString.EMPTY);
            isok = WS.mWebSocket.send(ByteString.EMPTY);
            Log.i(TAG, "结果：" + isok);
            os.flush();
            dos.close();
            bos.close();
            os.close();
            audioRecord.stop();
        } catch (Throwable t) {
            Log.e(TAG, "录音失败" + Log.getStackTraceString(t));
        } finally {
        }
    }

}
