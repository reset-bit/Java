package com.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.entity.SysApplication;
import com.login.LoginActivity;
import com.zhang.onlineshop.R;

public class MeActivity extends AppCompatActivity {

    private Button btn_back;
    private Button btn_main;
    private Button btn_member;
    private Button btn_shopping;
    private Button btn_car;
    private Button btn_me;
    private Button my_quit;
    private Button aboutme_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_me);
        SysApplication.getInstance().addActivity(this);


//        btn_back=(Button) findViewById(R.id.btn_main);
//        btn_member=(Button) findViewById(R.id.btn_member);
//        btn_car = (Button)findViewById (R.id.btn_addcar);
        my_quit = findViewById (R.id.my_quit);
        aboutme_more = findViewById(R.id.aboutme_more);
        btn_main = (Button) findViewById (R.id.btn_main);
        btn_shopping=(Button) findViewById( R.id.btn_shopping);
        btn_me = (Button)findViewById (R.id.btn_me);

//        btn_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish ();
//                Intent ie = new Intent();
//                ie.setClass ( MeActivity.this, MainActivity.class);
//              MeActivity.this.startActivity ( ie );
//            }
//        });

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent ie = new Intent(MeActivity.this, MainActivity.class);
                startActivity ( ie );
            }
        });

        btn_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish ();
                Intent ie = new Intent(MeActivity.this, ShoppingCartActivity.class);
                startActivity ( ie );
            }

        });
        aboutme_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ie = new Intent();
                ie.setClass ( MeActivity.this, MeMore.class);
                MeActivity.this.startActivity ( ie );
            }
        });
//        btn_me.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent ie = new Intent(MeActivity.this, MeActivity.class);
//                startActivity ( ie );
//            }
//        });
        my_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder delete_builder = new AlertDialog.Builder(MeActivity.this);
                delete_builder.setTitle("提示！！！！");
                delete_builder.setMessage("确定要退出吗？");

                delete_builder.setNegativeButton("取消", null);
                delete_builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SysApplication.getInstance().exit();
                    }
                });
                delete_builder.create().show();

            }
        });
/*
        //强制退出
        my_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SysApplication.getInstance().exit();
            }
        });
*/

    }
}
