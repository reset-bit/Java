package com.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.entity.SysApplication;
import com.pages.MainActivity;
import com.zhang.onlineshop.R;
public class QuickLoginActivity extends AppCompatActivity {

    private Button btn_quick_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_quick_login);
        SysApplication.getInstance().addActivity(this);

        btn_quick_login = (Button) findViewById (R.id.btn_quick_login);


        btn_quick_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(QuickLoginActivity.this,"支付宝登录成功！",Toast.LENGTH_LONG).show();
                Intent it = new Intent();
                it.setClass(QuickLoginActivity.this, MainActivity.class);
                startActivity(it);
            }
        });


    }
}
