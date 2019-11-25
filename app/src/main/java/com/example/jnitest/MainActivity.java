package com.example.jnitest;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.JniUtils;

import org.fmod.FMOD;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    JniUtils jniUtils = new JniUtils();
    private TextView tvShow;
//    private String path = "file:///android_asset/kill.wav";
    private String path = "file:///android_asset/123.mp3";
    private PlayThread playThread = new PlayThread(0);
    private boolean isPlay = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FMOD.init(this);


        tvShow = findViewById(R.id.tv_show);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btn10).setOnClickListener(this);

        findViewById(R.id.btn11).setOnClickListener(this);
        findViewById(R.id.btn12).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try{
            switch (view.getId()){
                case R.id.btn1:
                    tvShow.setText(jniUtils.stringFromJNI());
                    break;

                case R.id.btn2:
                    tvShow.setText("c计算 10 + 20 = " +jniUtils.add(10,20));
                    break;

                case R.id.btn3:
                    tvShow.setText("c转换之后的数组" + jniUtils.stringToC("JavaString"));
                    break;

                case R.id.btn4:
                    byte[] bytes = new byte[4];
                    bytes[0] = 99;
                    bytes[1] = 100;
                    bytes[2] = 101;
                    bytes[3] = 102;
                    tvShow.setText(new String(jniUtils.convertByte(bytes)));
                    break;

                case R.id.btn5:
                    if(isPlay){
                        isPlay = false;
                        playThread.interrupt();
                        Toast.makeText(this,"停止播放", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"开始播放", Toast.LENGTH_SHORT).show();
                        isPlay = true;
                        playThread.setType(JniUtils.zhengchang);
                        playThread.start();
                    }
                    break;

                case R.id.btn6:
                    if(isPlay){
                        isPlay = false;
                        playThread.interrupt();
                        Toast.makeText(this,"停止播放", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"开始播放", Toast.LENGTH_SHORT).show();
                        isPlay = true;
                        playThread.setType(JniUtils.luoli);
                        playThread.start();
                    }
                    break;

                case R.id.btn7:
                    if(isPlay){
                        isPlay = false;
                        playThread.interrupt();
                        Toast.makeText(this,"停止播放", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"开始播放", Toast.LENGTH_SHORT).show();
                        isPlay = true;
                        playThread.setType(JniUtils.dashu);
                        playThread.start();
                    }
                    break;

                case R.id.btn8:
                    if(isPlay){
                        isPlay = false;
                        playThread.interrupt();
                        Toast.makeText(this,"停止播放", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"开始播放", Toast.LENGTH_SHORT).show();
                        isPlay = true;
                        playThread.setType(JniUtils.gaoguai);
                        playThread.start();
                    }
                    break;

                case R.id.btn9:
                    if(isPlay){
                        isPlay = false;
                        playThread.interrupt();
                        Toast.makeText(this,"停止播放", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"开始播放", Toast.LENGTH_SHORT).show();
                        isPlay = true;
                        playThread.setType(JniUtils.jingsong);
                        playThread.start();
                    }
                    break;

                case R.id.btn10:
                    if(isPlay){
                        isPlay = false;
                        playThread.interrupt();
                        Toast.makeText(this,"停止播放", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"开始播放", Toast.LENGTH_SHORT).show();
                        isPlay = true;
                        playThread.setType(JniUtils.kongling);
                        playThread.start();
                    }
                    break;

            }
        }catch (Exception e){

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FMOD.close();
    }

    class PlayThread extends Thread{
        int type = 0;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public PlayThread(int type){
            this.type = type;
        }

        @Override
        public void run() {
            try{
                jniUtils.playSound(path,type);
            }catch (Exception e){

            }

        }
    }
}
