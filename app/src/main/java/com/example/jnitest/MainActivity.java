package com.example.jnitest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.utils.JniUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    JniUtils jniUtils = new JniUtils();
    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvShow = findViewById(R.id.tv_show);

        findViewById(R.id.btn_string).setOnClickListener(this);
        findViewById(R.id.btn_add).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_string:
                tvShow.setText(jniUtils.stringFromJNI());
                break;

            case R.id.btn_add:
                tvShow.setText("c计算 10 + 20 = " +jniUtils.add(10,20));
                break;
        }
    }
}
