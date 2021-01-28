package com.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.entity.SysApplication;
import com.zhang.onlineshop.R;


public class SelectLogin extends AppCompatActivity {
    private Button btn_other_login;
    private Button btn_up_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login);
        SysApplication.getInstance().addActivity(this);

        btn_other_login = (Button)findViewById(R.id.btn_other_login);
        btn_up_login = (Button)findViewById(R.id.btn_up_login);
        btn_other_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(SelectLogin.this, QuickLoginActivity.class);
                startActivity(it);
            }
        });

        btn_up_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SelectLogin.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}