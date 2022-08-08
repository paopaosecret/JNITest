package com.example.jnitest;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.utils.JniUtils;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    JniUtils jniUtils = new JniUtils();
    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvShow = findViewById(R.id.tv_show);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
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
                    bytes[0] = 97;
                    bytes[1] = 98;
                    bytes[2] = 99;
                    bytes[3] = 100;
                    tvShow.setText(new String(jniUtils.convertByte(bytes)));
                    break;
                case R.id.btn5:
                    jniUtils.testJNIListenerStaticVoid();
                    break;
                case R.id.btn6:
                    jniUtils.testJNIListenerVoid();
                    break;
                case R.id.btn7:
                    jniUtils.testJNIListenerStaticInt();
                    break;
                case R.id.btn8:
                    jniUtils.testJNIListenerInt();
                    break;
            }
        }catch (Exception e){

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
