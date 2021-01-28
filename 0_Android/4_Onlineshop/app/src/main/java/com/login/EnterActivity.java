package com.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.entity.SysApplication;
import com.pages.MainActivity;
import com.zhang.onlineshop.R;

import java.util.Timer;
import java.util.TimerTask;

public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView ( R.layout.activity_enter);
        SysApplication.getInstance().addActivity(this);

        final Timer timer = new Timer ();
        final TimerTask task = new TimerTask () {
            @Override
            public void run() {
                finish();
                Intent it = new Intent(EnterActivity.this, SelectLogin.class);
                startActivity(it);
            }
        };
        timer.schedule (task,1500);
    }
}