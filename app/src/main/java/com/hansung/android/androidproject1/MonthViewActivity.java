package com.hansung.android.androidproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MonthViewActivity extends AppCompatActivity {
    int year;
    int month;
    Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent intent = getIntent();
        year = intent.getIntExtra("year", -1);
        month = intent.getIntExtra("month", -1);

        if(year == -1 || month == -1) {
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH)+1;
        }



        cal.set(year, month-1, 1);



        TextView dat = (TextView)findViewById(R.id.cal_date);
        dat.setText(year +"년 "+ month +"월");

        


        Button btn_pre = findViewById(R.id.cal_previous);
        btn_pre.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                if(month != 1) {
                    month -= 1;
                    Intent intent = new Intent(getApplicationContext(),
                            MonthViewActivity.class);
                    intent.putExtra("year", year);
                    intent.putExtra("month", month);
                    startActivity(intent);
                    finish();
                }


                else {
                    year -= 1;
                    month = 12;
                    Intent intent = new Intent(getApplicationContext(),
                            MonthViewActivity.class);
                    intent.putExtra("year", year);
                    intent.putExtra("month", month);
                    startActivity(intent);
                    finish();
                }
            }
        });

        Button btn_next = findViewById(R.id.cal_next);
        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(month != 12) {
                    month += 1;
                    Intent intent = new Intent(getApplicationContext(),
                            MonthViewActivity.class);
                    intent.putExtra("year", year);
                    intent.putExtra("month", month);
                    startActivity(intent);
                    finish();
                }



                else {
                    year += 1;
                    month = 1;
                    Intent intent = new Intent(getApplicationContext(),
                            MonthViewActivity.class);
                    intent.putExtra("year", year);
                    intent.putExtra("month", month);
                    startActivity(intent);
                    finish();
                }
            }
        });

        ArrayList<String> items = new ArrayList<String>();
        for(int i = 1; i < cal.get(Calendar.DAY_OF_WEEK); i++) {
            items.add(" ");
        }

        for(int i = 0; i < finddaynum(year, month); i++) {
            if(i>=0&&i<=8)
                items.add("  "+(i+1));
            else
                items.add(Integer.toString(i+1));
        }


        ArrayAdapter<String> adapt
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                items);


        GridView gridview = (GridView) findViewById(R.id.calendar_gridview);
        gridview.setAdapter(adapt);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = (Object)adapterView.getAdapter().getItem(i);
                Toast.makeText(getApplicationContext(),year+"."+month+"."+o,Toast.LENGTH_SHORT).show();
            }
        });




    }

    public int finddaynum(int year, int month) {
        int day_num = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day_num = 31;
                break;
            case 2:
                if((year%4==0 && year%100 != 0) || year%400 == 0)
                    day_num = 29;
                else
                    day_num = 28;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day_num = 30;
                break;
        }
        return day_num;
    }



}