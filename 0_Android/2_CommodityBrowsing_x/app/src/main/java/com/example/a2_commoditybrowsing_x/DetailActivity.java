package com.example.a2_commoditybrowsing_x;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.a2_commoditybrowsing_x.entity.Detail;
import com.example.a2_commoditybrowsing_x.entity.DetailAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private List<Detail> detailList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //-------------actionbar---------------------
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //-------------actionbar---------------------

        //-------------recyclerview----------------
        int position = getIntent().getIntExtra("position", 0);
        getDetails(position);
        //加载组件
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.food_RecyclerView_Detail);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//横向滚动
        recyclerView.setLayoutManager(layoutManager);
        //加载适配器
        DetailAdapter detailAdapter = new DetailAdapter(detailList);
        recyclerView.setAdapter(detailAdapter);
        //-------------recyclerview----------------

        //获取参数内容
        String foodName = getIntent().getStringExtra("foodName");
        String foodDetail = getIntent().getStringExtra("foodDetail");
        //获取布局实例并加载
        DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_Fragment_Activity);
        detailFragment.refresh(foodName, foodDetail, position);
    }

    public static void actionStart(Context context, String foodName, String foodDetail, int position){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("foodName", foodName);
        intent.putExtra("foodDetail", foodDetail);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDetails(int index){
        detailList.clear();
        switch (index){
            case 0:
                Detail beef_stew_with_potatoes_min = new Detail(R.drawable.beef_stew_with_potatoes_min);
                detailList.add(beef_stew_with_potatoes_min);
                Detail beef_stew_with_potatoes = new Detail(R.drawable.beef_stew_with_potatoes);
                detailList.add(beef_stew_with_potatoes);
                break;
            case 1:
                Detail casserole_min = new Detail(R.drawable.casserole_min);
                detailList.add(casserole_min);
                Detail casserole = new Detail( R.drawable.casserole);
                detailList.add(casserole);
                break;
            case 2:
                Detail dumplings_min = new Detail( R.drawable.dumplings_min);
                detailList.add(dumplings_min);
                Detail dumplings = new Detail( R.drawable.dumplings);
                detailList.add(dumplings);
                break;
            case 3:
                Detail gold_shrimp_min = new Detail( R.drawable.gold_shrimp_min);
                detailList.add(gold_shrimp_min);
                Detail gold_shrimp = new Detail( R.drawable.gold_shrimp);
                detailList.add(gold_shrimp);
                break;
            case 4:
                Detail laba_rice_porridge_min = new Detail(R.drawable.laba_rice_porridge_min);
                detailList.add(laba_rice_porridge_min);
                Detail laba_rice_porridge = new Detail( R.drawable.laba_rice_porridge);
                detailList.add(laba_rice_porridge);
                break;
            case 5:
                Detail moon_cake_min = new Detail( R.drawable.moon_cake_min);
                detailList.add(moon_cake_min);
                Detail moon_cake = new Detail( R.drawable.moon_cake);
                detailList.add(moon_cake);
                break;
            case 6:
                Detail mutton_hotpot_min = new Detail( R.drawable.mutton_hotpot_min);
                detailList.add(mutton_hotpot_min);
                Detail mutton_hotpot = new Detail( R.drawable.mutton_hotpot);
                detailList.add(mutton_hotpot);
                break;
            case 7:
                Detail mutton_soup_min = new Detail( R.drawable.mutton_soup_min);
                detailList.add(mutton_soup_min);
                Detail mutton_soup = new Detail(R.drawable.mutton_soup);
                detailList.add(mutton_soup);
                break;
            case 8:
                Detail octopus_balls_min = new Detail( R.drawable.octopus_balls_min);
                detailList.add(octopus_balls_min);
                Detail octopus_balls = new Detail( R.drawable.octopus_balls);
                detailList.add(octopus_balls);
                break;
            case 9:
                Detail rice_balls_min = new Detail(R.drawable.rice_balls_min);
                detailList.add(rice_balls_min);
                Detail rice_balls = new Detail( R.drawable.rice_balls);
                detailList.add(rice_balls);
                break;
            case 10:
                Detail sugar_fried_chestnut_min = new Detail(R.drawable.sugar_fried_chestnut_min);
                detailList.add(sugar_fried_chestnut_min);
                Detail sugar_fried_chestnut = new Detail( R.drawable.sugar_fried_chestnut);
                detailList.add(sugar_fried_chestnut);
                break;
            case 11:
                Detail vegetables_min = new Detail( R.drawable.vegetables_min);
                detailList.add(vegetables_min);
                Detail vegetables = new Detail( R.drawable.vegetables);
                detailList.add(vegetables);
                break;
            case 12:
                Detail zong_zi_min = new Detail( R.drawable.zong_zi_min);
                detailList.add(zong_zi_min);
                Detail zong_zi = new Detail( R.drawable.zong_zi);
                detailList.add(zong_zi);
                break;
        }
    }
}