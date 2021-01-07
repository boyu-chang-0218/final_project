package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class shoppingcar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcar);
        //連接元件
        TextView tv_total = findViewById(R.id.tv_total);
        //宣告字串
        String total = new String();
        ArrayList<String> meal = new ArrayList<String>();
        //取出bundle
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null)
        {
            meal = bundle.getStringArrayList("mealname");
            for (int i = 0; i<meal.size(); i++)
            {
                //把陣列一項一項放入字串
                total += meal.get(i)+"\n";
            }
            //顯示出字串
            tv_total.setText(total);
        }
        //連接元件
        TextView tv_totalprice = findViewById(R.id.tv_toatalprice);
        //宣告字串
        String totalprice = new String();
        int sum = 0;
        int a;
        ArrayList<String> price = new ArrayList<String>();
        //取出bundle
        Bundle bundle2 = this.getIntent().getExtras();
        if (bundle2 != null)
        {
            price = bundle2.getStringArrayList("pricename");
            for (int i = 0; i<price.size(); i++)
            {
                //取出陣列放入字串
                totalprice = price.get(i);
                //將string轉為int
                a = Integer.parseInt(totalprice);
                //累加到sum中
                sum = sum + a;
            }
            //顯示sum
            tv_totalprice.setText("總金額為$" + sum);
        }


    }
}