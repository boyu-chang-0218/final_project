package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_title;
    private Button btn_chinese, btn_english;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //連接元件
        tv_title = findViewById(R.id.tv_title);
        btn_chinese = findViewById(R.id.btn_chinese);
        btn_english = findViewById(R.id.btn_english);
        //設置監聽器
        btn_chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //透過intent切換至chinese
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, chinese.class);
                startActivity(intent);
            }
        });
        btn_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //透過intent切換至english
                Intent intent1 = new Intent();
                intent1.setClass(MainActivity.this, english.class);
                startActivity(intent1);
            }
        });
    }
}