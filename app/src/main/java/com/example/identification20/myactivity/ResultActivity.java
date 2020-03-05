package com.example.identification20.myactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.identification20.R;
import com.example.identification20.myserver.MyResult;

/**
 * 获取识别结果后的界面
 */
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView textView = (TextView)findViewById(R.id.result_text);
        textView.setText("这是一个："+MyResult.getResult());
    }
}
