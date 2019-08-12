package com.emmanuel.mojidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private IndexHorizontalScrollView indexHorizontalScrollView;
    private Today24HourView today24HourView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private int maxTemp = 88;
    private int minTemp = 11;

    private void initViews() {
        indexHorizontalScrollView = (IndexHorizontalScrollView) findViewById(R.id.indexHorizontalScrollView);
        today24HourView = (Today24HourView) findViewById(R.id.today24HourView);
        indexHorizontalScrollView.setToday24HourView(today24HourView);
        Materials30View materials30View = (Materials30View) findViewById(R.id.materials);
        Random random = new Random();

        List<Materials30Item> itemList = new ArrayList<>();
        Materials30Item materials30Item;
        for (int i = 0; i < 20; i++) {
            materials30Item = new Materials30Item();
            int s = random.nextInt(maxTemp) % (maxTemp - minTemp + 1) + minTemp;
            materials30Item.setCountTon(s);
            materials30Item.setCountMaterial(i);
            materials30Item.setTime("07-" + i);
            itemList.add(materials30Item);
        }
        materials30View.setData(itemList);
    }
}
