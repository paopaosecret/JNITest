package com.example.jnitest;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.utils.JniUtils;

import org.fmod.FMOD;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    JniUtils jniUtils = new JniUtils();
    private TextView tvShow;
//    private String path = "file:///android_asset/kill.wav";
    private String path = "file:///android_asset/123.mp3";

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
    }

    @Override
    public void onClick(View view) {
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
                jniUtils.playSound(path, JniUtils.zhengchang);
                break;

            case R.id.btn6:
                jniUtils.playSound(path, JniUtils.luoli);
                break;

            case R.id.btn7:
                jniUtils.playSound(path, JniUtils.dashu);
                break;

            case R.id.btn8:
                jniUtils.playSound(path, JniUtils.gaoguai);
                break;

            case R.id.btn9:
                jniUtils.playSound(path, JniUtils.jingsong);
                break;

            case R.id.btn10:
                jniUtils.playSound(path, JniUtils.kongling);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FMOD.close();
    }
}
