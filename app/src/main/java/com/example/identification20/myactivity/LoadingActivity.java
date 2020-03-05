package com.example.identification20.myactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import com.example.identification20.R;

/**
 * 加载页
 */
public class LoadingActivity extends AppCompatActivity {
    private static int TIME = 5;
    private Button loading;
    private CountDownTimer timer;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        loading = (Button)findViewById(R.id.button_loading);
        loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                timer.cancel();//关闭倒计时，不然还是会执行倒计时完成时的操作


            }
        });
         timer = new CountDownTimer(1000*5, 1000) {
            @Override
            //每次达到对应的时间会执行一次这个函数
            public void onTick(long millisUntilFinished) {
                loading.setText(String.format("%ds跳过",millisUntilFinished/1000));
            }
            @Override
            //倒计时完成会执行这个函数
            public void onFinish() {
                //跳转到主界面
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.start();
    }
}

