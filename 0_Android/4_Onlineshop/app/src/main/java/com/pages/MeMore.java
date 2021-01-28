package com.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.entity.SysApplication;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zhang.onlineshop.R;

public class MeMore extends AppCompatActivity {

    FloatingActionButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_me_more);
        SysApplication.getInstance().addActivity(this);

        back = (FloatingActionButton)(FloatingActionButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}