package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

public class english extends AppCompatActivity {
    int a;
    //客製化類別Data,包含一張圖片與文字
    class Data{
        int photo;
        String name;
    }
    //繼承BaseAdapter
    public class MyAdapter extends BaseAdapter {
        //保存在MyAdapter之中的資料來源
        private english.Data[] data;
        //保存在MyAdapter之中的畫面
        private int view;
        //透過建構子儲存資料來源與畫面
        public MyAdapter(english.Data[] data, int view) {
            this.data = data;
            this.view = view;
        }



        //回傳資料來源筆數
        @Override
        public int getCount() {
            return data.length;
        }
        //回傳某筆項目
        @Override
        public Object getItem(int position) {
            return data[position];
        }
        //回傳某筆項目id
        @Override
        public long getItemId(int position) {
            return 0;
        }
        //取得畫面元件
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //取得xml畫面
            convertView = getLayoutInflater().inflate(view,parent,false);
            //連接TextView元件
            TextView name = convertView.findViewById(R.id.name);
            //根據position把字串顯示到TextView
            name.setText(data[position].name);
            //連接ImageView 元件
            ImageView imageView = convertView.findViewById(R.id.imageView);
            //根據position把圖片顯示到ImageView
            imageView.setImageResource(data[position].photo);
            return convertView;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);


        //連接button元件
        Button btn_backtomain = findViewById(R.id.btn_backtomain);
        //對btn_backtomain設置監聽
        btn_backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(english.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //建立資料來源,用陣列方式宣告自行設計的類別,並為陣列的每個內容填入藥顯示的資料
        String[] MealNameArray = new String[]{"Big Mac", "Double Cheeseburger", "Grilled BBQ Chicken Burger", "McChicken", "Chicken McNuggets(6pcs)", "Chicken McNuggets(10pcs)", "Spicy Chicken Filet Burger", "Chicken McCrispy Wing",
                "Chicken McCrispy", "Pork Burger with Cheese Sauce", "Filet-O-Fish", "Chicken Long Burger", "Pork Long Burger", "Mushroom Angus Beef Burger", "BLT Angus Beef Burger", "BLT Spicy Chicken Burger", "BLT Grilled Chicken Burger", "Caeser Salad with Fried Chicken Filet", "Italien Salad with Grilled Chicken Filet"};
        int[] MealPhotoArray = new int[]{R.drawable.a_01_big_bac, R.drawable.a_02_cheeseburger, R.drawable.a_03_gbc, R.drawable.a_04_mcchicken, R.drawable.a_05_ngt6, R.drawable.a_06_ngt10,
                R.drawable.a_07_scf, R.drawable.a_08_mfc, R.drawable.a_09_mfc, R.drawable.a_10_pork_burger, R.drawable.a_11_fof, R.drawable.a_12_smoked_chicken, R.drawable.a_13_ginger_pork,
                R.drawable.a_14_mushroom, R.drawable.a_15_beef, R.drawable.a_16_fried_chicken, R.drawable.a_17_grilled_chicken, R.drawable.a_18_spicy_fried_chicken, R.drawable.a_19_grilled_chicken};
        Data[] mealData = new Data[MealNameArray.length];
        for(int i=0 ; i<mealData.length ; i++){
            mealData[i] = new Data();
            mealData[i].name = MealNameArray[i];
            mealData[i].photo = MealPhotoArray[i];
        }
        //建立MyAdapter物件,並放入mealData與custom
        MyAdapter mealAdapter = new MyAdapter(mealData,R.layout.custom);
        //連接Spinner元件,並連結MyAdapter
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(mealAdapter);


        //連接物件
        ImageView imv = findViewById(R.id.imv);
        TextView tv_ShowMeal = findViewById(R.id.tv_ShowMeal);
        Button btn_set = findViewById(R.id.btn_set);
        Button btn_send = findViewById(R.id.btn_send);
        Button btn_single = findViewById(R.id.btn_single);
        Button btn_gotocar = findViewById(R.id.btn_gotocar);
        //宣告陣列
        ArrayList<String> meal = new ArrayList<String>();
        ArrayList<String> price = new ArrayList<String>();
        //對spinner項目設置監聽
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //按下第一項
                if(position == 0) {
                    //設置single監聽
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //顯示textview
                            tv_ShowMeal.setText("Big Mac Single $72");
                            //顯示image
                            imv.setImageResource(R.drawable.a_01_big_bac);
                            //設置send監聽
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //把字串放入meal矩陣
                                    meal.add("Big Mac Single $72");
                                    //把字串放入price矩陣
                                    price.add("72");
                                    //顯示Toast
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    //以下18項以此類推
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Big Mac Set $127");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Big Mac Set $127");
                                    price.add("127");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 1) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Double Cheeseburger Single $62");
                            imv.setImageResource(R.drawable.a_02_cheeseburger);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Double Cheeseburger Single $62");
                                    price.add("62");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Double Cheeseburger Set $117");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Double Cheeseburger Set $117");
                                    price.add("117");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 2) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Grilled BBQ Chicken Burger Single $82");
                            imv.setImageResource(R.drawable.a_03_gbc);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Grilled BBQ Chicken Burger Single $82");
                                    price.add("82");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Grilled BBQ Chicken Burger Set $137");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Grilled BBQ Chicken Burger Set $137");
                                    price.add("137");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 3) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("McChicken Single $44");
                            imv.setImageResource(R.drawable.a_04_mcchicken);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("McChicken Single $44");
                                    price.add("44");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("McChicken Set $99");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("McChicken Set $99");
                                    price.add("99");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 4) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Chicken McNuggets(6pcs) Single $60");
                            imv.setImageResource(R.drawable.a_05_ngt6);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Chicken McNuggets(6pcs) Single $60");
                                    price.add("60");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Chicken McNuggets(6pcs) Set $115");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Chicken McNuggets(6pcs) Set $115");
                                    price.add("115");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 5) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Chicken McNuggets(10pcs) Single $100");
                            imv.setImageResource(R.drawable.a_06_ngt10);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Chicken McNuggets(10pcs) Single $100");
                                    price.add("100");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Chicken McNuggets(10pcs) Set $155");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Chicken McNuggets(10pcs) Set $155");
                                    price.add("155");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 6) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Spicy Chicken Filet Burger Single $72");
                            imv.setImageResource(R.drawable.a_07_scf);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Spicy Chicken Filet Burger Single $72");
                                    price.add("72");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Spicy Chicken Filet Burger Set $127");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Spicy Chicken Filet Burger Set $127");
                                    price.add("127");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 7) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Chicken McCrispy Wing Single $90");
                            imv.setImageResource(R.drawable.a_08_mfc);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Chicken McCrispy Wing Single $90");
                                    price.add("90");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Chicken McCrispy Wing Set $145");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Chicken McCrispy Wing Set $145");
                                    price.add("145");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 8) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Chicken McCrispy Single $110");
                            imv.setImageResource(R.drawable.a_09_mfc);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Chicken McCrispy Single $110");
                                    price.add("110");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Chicken McCrispy Set $165");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Chicken McCrispy Set $165");
                                    price.add("165");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 9) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Pork Burger with Cheese Sauce Single $52");
                            imv.setImageResource(R.drawable.a_10_pork_burger);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Pork Burger with Cheese Sauce Single $52");
                                    price.add("52");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Pork Burger with Cheese Sauce Single Set $107");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Pork Burger with Cheese Sauce Set $107");
                                    price.add("107");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 10) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Filet-O-Fish Single $44");
                            imv.setImageResource(R.drawable.a_11_fof);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Filet-O-Fish Single $44");
                                    price.add("44");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Filet-O-Fish Set $99");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Filet-O-Fish Set $99");
                                    price.add("99");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 11) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Chicken Long Burger Single $74");
                            imv.setImageResource(R.drawable.a_12_smoked_chicken);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Chicken Long Burger Single $74");
                                    price.add("74");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Chicken Long Burger Set $129");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Chicken Long Burger Set $129");
                                    price.add("129");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 12) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Pork Long Burger Single $74");
                            imv.setImageResource(R.drawable.a_13_ginger_pork);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Pork Long Burger Single $74");
                                    price.add("74");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Pork Long Burger Set $129");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Pork Long Burger Set $129");
                                    price.add("129");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 13) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Mushroom Angus Beef Burger Single $119");
                            imv.setImageResource(R.drawable.a_14_mushroom);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Mushroom Angus Beef Burger Single $119");
                                    price.add("119");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Mushroom Angus Beef Burger Set $174");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Mushroom Angus Beef Burger Set $174");
                                    price.add("174");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 14) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("BLT Angus Beef Burger Single $109");
                            imv.setImageResource(R.drawable.a_15_beef);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("BLT Angus Beef Burger Single $109");
                                    price.add("109");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("BLT Angus Beef Burger Set $164");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("BLT Angus Beef Burger Set $164");
                                    price.add("164");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 15) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("BLT Spicy Chicken Burger Single $109");
                            imv.setImageResource(R.drawable.a_16_fried_chicken);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("BLT Spicy Chicken Burger Single $109");
                                    price.add("109");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("BLT Spicy Chicken Burger Set $164");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("BLT Spicy Chicken Burger Set $164");
                                    price.add("164");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 16) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("BLT Grilled Chicken Burger Set $109");
                            imv.setImageResource(R.drawable.a_17_grilled_chicken);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("BLT Grilled Chicken Burger Set $109");
                                    price.add("109");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("BLT Grilled Chicken Burger Single $164");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("BLT Grilled Chicken Burger Single $164");
                                    price.add("164");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 17) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Caeser Salad with Fried Chicken Filet Single $99");
                            imv.setImageResource(R.drawable.a_18_spicy_fried_chicken);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Caeser Salad with Fried Chicken Filet Single $99");
                                    price.add("99");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Caeser Salad with Fried Chicken Filet Set $154");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Caeser Salad with Fried Chicken Filet Set $154");
                                    price.add("154");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 18) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Italien Salad with Grilled Chicken Filet Single $99");
                            imv.setImageResource(R.drawable.a_19_grilled_chicken);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Italien Salad with Grilled Chicken Filet Single $99");
                                    price.add("99");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("Italien Salad with Grilled Chicken Filet Set $154");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("Italien Salad with Grilled Chicken Filet Set $154");
                                    price.add("154");
                                    Toast.makeText(english.this, "Meal has already been add to your shopping car", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //設置gotocar監聽
        btn_gotocar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //透過Intent切換至shoppingcar
                Intent intent = new Intent();
                intent.setClass(english.this, shoppingcareng.class);
                //利用bundle傳值
                Bundle bundle= new Bundle();
                bundle.putStringArrayList("mealname", meal);
                intent.putExtras(bundle);
                //利用bundle傳值
                Bundle bundle2 = new Bundle();
                bundle2.putStringArrayList("pricename", price);
                intent.putExtras(bundle2);
                startActivity(intent);



            }
        });



    }
}