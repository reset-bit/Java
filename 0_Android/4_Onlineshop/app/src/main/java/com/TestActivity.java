package com;

import android.content.Intent;
import android.os.Bundle;

import com.entity.SysApplication;
import com.pages.DetailActivity;
import com.zhang.onlineshop.R;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SysApplication.getInstance().addActivity(this);


        Intent intent = new Intent(TestActivity.this, DetailActivity.class);
        startActivity(intent);
    }

}