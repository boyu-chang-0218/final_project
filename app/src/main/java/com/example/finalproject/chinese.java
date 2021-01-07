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

public class chinese extends AppCompatActivity {
    int a;
    //客製化類別Data,包含一張圖片與文字
    class Data{
        int photo;
        String name;
    }
    //繼承BaseAdapter
    public class MyAdapter extends BaseAdapter {
        //保存在MyAdapter之中的資料來源
        private chinese.Data[] data;
        //保存在MyAdapter之中的畫面
        private int view;
        //透過建構子儲存資料來源與畫面
        public MyAdapter (chinese.Data[] data, int view) {
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
        setContentView(R.layout.activity_chinese);


        //連接button元件
        Button btn_backtomain = findViewById(R.id.btn_backtomain);
        //對btn_backtomain設置監聽
        btn_backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(chinese.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //建立資料來源,用陣列方式宣告自行設計的類別,並為陣列的每個內容填入要顯示的資料
        String[] MealNameArray = new String[]{"大麥克", "雙層牛肉吉事堡", "嫩煎雞腿堡", "麥香雞", "麥克雞塊(6塊)", "麥克雞塊(10塊)", "勁辣雞腿堡", "麥脆雞翅",
                "麥脆雞腿", "黃金起司豬排堡", "麥香魚", "煙燻雞肉長堡", "醬燒豬肉長堡", "蕈菇安格斯黑牛堡", "BLT安格斯黑牛堡", "BLT辣脆雞腿堡", "BLT嫩煎雞腿堡", "凱撒辣脆雞沙拉", "義式烤雞沙拉"};
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

                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            tv_ShowMeal.setText("大麥克單點$72");

                            imv.setImageResource(R.drawable.a_01_big_bac);

                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    meal.add("大麥克單點$72");

                                    price.add("72");

                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    //以下18項以此類推
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("大麥克套餐$127");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("大麥克套餐$127");
                                    price.add("127");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 1) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("雙層牛肉吉事堡單點$62");
                            imv.setImageResource(R.drawable.a_02_cheeseburger);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("雙層牛肉吉事堡單點$62");
                                    price.add("62");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("雙層牛肉吉事堡套餐$117");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("雙層牛肉吉事堡套餐$117");
                                    price.add("117");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 2) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("嫩煎雞腿堡單點$82");
                            imv.setImageResource(R.drawable.a_03_gbc);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("嫩煎雞腿堡單點$82");
                                    price.add("82");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("嫩煎雞腿堡套餐$137");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("嫩煎雞腿堡套餐$137");
                                    price.add("137");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 3) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("麥香雞單點$44");
                            imv.setImageResource(R.drawable.a_04_mcchicken);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("麥香雞單點$44");
                                    price.add("44");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("麥香雞套餐$99");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("麥香雞套餐$99");
                                    price.add("99");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 4) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("麥克雞塊(6塊)單點$60");
                            imv.setImageResource(R.drawable.a_05_ngt6);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("麥克雞塊(6塊)單點$60");
                                    price.add("60");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("麥克雞塊(6塊)套餐$115");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("麥克雞塊(6塊)套餐$115");
                                    price.add("115");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 5) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("麥克雞塊(10塊)單點$100");
                            imv.setImageResource(R.drawable.a_06_ngt10);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("麥克雞塊(10塊)單點$100");
                                    price.add("100");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("麥克雞塊(10塊)套餐$155");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("麥克雞塊(10塊)套餐$155");
                                    price.add("155");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 6) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("勁辣雞腿堡單點$72");
                            imv.setImageResource(R.drawable.a_07_scf);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("勁辣雞腿堡單點$72");
                                    price.add("72");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("勁辣雞腿堡套餐$127");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("勁辣雞腿堡套餐$127");
                                    price.add("127");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 7) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("麥脆雞翅單點$90");
                            imv.setImageResource(R.drawable.a_08_mfc);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("麥脆雞翅單點$90");
                                    price.add("90");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("麥脆雞翅套餐$145");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("麥脆雞翅套餐$145");
                                    price.add("145");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 8) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("麥脆雞腿單點$110");
                            imv.setImageResource(R.drawable.a_09_mfc);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("麥脆雞腿單點$110");
                                    price.add("110");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("麥脆雞腿套餐$165");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("麥脆雞腿套餐$165");
                                    price.add("165");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 9) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("黃金起司豬排堡單點$52");
                            imv.setImageResource(R.drawable.a_10_pork_burger);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("黃金起司豬排堡單點$52");
                                    price.add("52");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("黃金起司豬排堡套餐$107");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("黃金起司豬排堡套餐$107");
                                    price.add("107");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 10) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("麥香魚單點$44");
                            imv.setImageResource(R.drawable.a_11_fof);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("麥香魚單點$44");
                                    price.add("44");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("麥香魚套餐$99");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("麥香魚套餐$99");
                                    price.add("99");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 11) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("煙燻雞肉長堡單點$74");
                            imv.setImageResource(R.drawable.a_12_smoked_chicken);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("煙燻雞肉長堡單點$74");
                                    price.add("74");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("煙燻雞肉長堡套餐$129");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("煙燻雞肉長堡套餐$129");
                                    price.add("129");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 12) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("醬燒豬肉長堡單點$74");
                            imv.setImageResource(R.drawable.a_13_ginger_pork);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("醬燒豬肉長堡單點$74");
                                    price.add("74");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("醬燒豬肉長堡套餐$129");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("醬燒豬肉長堡套餐$129");
                                    price.add("129");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 13) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("蕈菇安格斯黑牛堡單點$119");
                            imv.setImageResource(R.drawable.a_14_mushroom);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("蕈菇安格斯黑牛堡單點$119");
                                    price.add("119");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("蕈菇安格斯黑牛堡套餐$174");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("蕈菇安格斯黑牛堡套餐$174");
                                    price.add("174");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 14) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("BLT安格斯黑牛堡單點$109");
                            imv.setImageResource(R.drawable.a_15_beef);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("BLT安格斯黑牛堡單點$109");
                                    price.add("109");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("BLT安格斯黑牛堡套餐$164");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("BLT安格斯黑牛堡套餐$164");
                                    price.add("164");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 15) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("BLT辣脆雞腿堡單點$109");
                            imv.setImageResource(R.drawable.a_16_fried_chicken);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("BLT辣脆雞腿堡單點$109");
                                    price.add("109");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("BLT辣脆雞腿堡套餐$164");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("BLT辣脆雞腿堡套餐$164");
                                    price.add("164");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 16) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("BLT嫩煎雞腿堡單點$109");
                            imv.setImageResource(R.drawable.a_17_grilled_chicken);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("BLT嫩煎雞腿堡單點$109");
                                    price.add("109");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("BLT嫩煎雞腿堡套餐$164");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("BLT嫩煎雞腿堡套餐$164");
                                    price.add("164");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 17) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("凱撒辣脆雞沙拉單點$99");
                            imv.setImageResource(R.drawable.a_18_spicy_fried_chicken);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("凱撒辣脆雞沙拉單點$99");
                                    price.add("99");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("凱撒辣脆雞沙拉套餐$154");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("凱撒辣脆雞沙拉套餐$154");
                                    price.add("154");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else if(position == 18) {
                    btn_single.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("義式烤雞沙拉單點$99");
                            imv.setImageResource(R.drawable.a_19_grilled_chicken);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("義式烤雞沙拉單點$99");
                                    price.add("99");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    btn_set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_ShowMeal.setText("義式烤雞沙拉套餐$154");
                            imv.setImageResource(R.drawable.set01);
                            btn_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    meal.add("義式烤雞沙拉套餐$154");
                                    price.add("154");
                                    Toast.makeText(chinese.this, "餐點已新增至購物車", Toast.LENGTH_SHORT).show();
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

                Intent intent = new Intent();
                intent.setClass(chinese.this, shoppingcar.class);

                Bundle bundle= new Bundle();
                bundle.putStringArrayList("mealname", meal);
                intent.putExtras(bundle);

                Bundle bundle2 = new Bundle();
                bundle2.putStringArrayList("pricename", price);
                intent.putExtras(bundle2);
                startActivity(intent);



            }
        });



    }
}