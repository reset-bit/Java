package com.example.a3_stuperfmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.a3_stuperfmanagement.activity.BaseActivity;
import com.example.a3_stuperfmanagement.entity.Sc;
import com.example.a3_stuperfmanagement.sqlite.MyDataBaseHelper;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private MyDataBaseHelper dbHelper;
    private List<Sc> scList = new ArrayList<>();
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_Layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);//设置导航按钮
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);//加载导航按钮图片
        }

        int id = Integer.parseInt(getIntent().getStringExtra("account"));//用户职工号/学号
        int identify = getIntent().getIntExtra("identify", 0);//用户身份类型
        String user = "";
        //构建数据库，指定版本号为1(>1则升级)。第一次点击按钮发现没有数据库，则会创建该数据库并调用OnCreate()方法创建表
        dbHelper = new MyDataBaseHelper(this, "StuPerfManagement.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Student where sno = ?", new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()){
            do {
                int userId = cursor.getInt(cursor.getColumnIndex("sno"));
                userName = cursor.getString(cursor.getColumnIndex("sname"));
                String userSex = cursor.getString(cursor.getColumnIndex("ssex"));
                String userDept = cursor.getString(cursor.getColumnIndex("sdept"));
                user = "您的学号/职工号: "+userId+"\n姓名: "+userName+"\n性别: "+userSex+"\n系别: "+userDept;
            }while (cursor.moveToNext());
        }
        cursor.close();

        //滑动菜单点击设置
        navigationView.setItemIconTintList(null);
        String finalUser = user;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_personal:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setCancelable(true);//设置用户可取消
                        builder.setMessage(finalUser);
                        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {return;}
                        });
                        builder.show();
                        break;
                    case R.id.nav_exit:
                        Intent intent = new Intent("com.example.a3_stuperfmanagement.FORCE_OFFLINE");//发送强制下线广播
                        sendBroadcast(intent);
                        break;
                }
                return true;
            }
        });

        Button initData = (Button) findViewById(R.id.init_Data);
        initData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into Student (sname, ssex, sdept) values(?, ?, ?)", new String[]{"Bob", "male", "CS"});
                db.execSQL("insert into Student (sname, ssex, sdept) values(?, ?, ?)", new String[]{"Marry", "female", "AI"});
                db.execSQL("insert into Student (sname, ssex, sdept) values(?, ?, ?)", new String[]{"Tom", "male", "AI"});
                db.execSQL("insert into Course (cname) values(?)", new String[]{"Java"});
                db.execSQL("insert into Course (cname) values(?)", new String[]{"C++"});
                db.execSQL("insert into Course (cname) values(?)", new String[]{"Python"});
                Toast.makeText(MainActivity.this, "初始化数据成功", Toast.LENGTH_SHORT).show();
            }
        });
        Button selectData = (Button) findViewById(R.id.select_Data);
        selectData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scList.clear();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //(使用SQL操作数据库)根据当前用户名查询成绩
                Cursor cursor_sc = db.rawQuery("select * from SC where sno = ?", new String[]{String.valueOf(id)});
                Log.d("Main", "size="+cursor_sc.getCount());
                Log.d("Main", "id="+id);
                if(cursor_sc.moveToFirst()){
                    do{
                        //遍历cursor对象
                        int score = cursor_sc.getInt(cursor_sc.getColumnIndex("score"));
                        int cno = cursor_sc.getInt(cursor_sc.getColumnIndex("cno"));
                        Cursor cursor_course = db.rawQuery("select * from Course where cno = ?", new String[]{String.valueOf(cno)});
                        if (cursor_course.moveToFirst()){
                            do {
                                String cname = cursor_course.getString(cursor_course.getColumnIndex("cname"));
                                scList.add(new Sc(cname, score));
                                Log.d("Main", "cname="+cname);
                                Log.d("Main", "score="+score);
                            }while (cursor_course.moveToNext());
                        }
                        cursor_course.close();
                    }while(cursor_sc.moveToNext());
                }
                cursor_sc.close();

                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                intent.putExtra("size", scList.size());
                for(int i = 0; i < scList.size(); ++i){
                    intent.putExtra("cname" + i, scList.get(i).getCname());
                    intent.putExtra("score" + i, scList.get(i).getScore());
                }
                startActivity(intent);
            }
        });

        if(identify == 0){
            Button addData = (Button) findViewById(R.id.add_Data);
            addData.setVisibility(View.VISIBLE);
            addData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    intent.putExtra("op_Type", "add");
                    startActivity(intent);
                }
            });
            Button updateData = (Button) findViewById(R.id.update_Data);
            updateData.setVisibility(View.VISIBLE);
            updateData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    intent.putExtra("op_Type", "update");
                    startActivity(intent);
                }
            });
            Button deleteData = (Button) findViewById(R.id.delete_Data);
            deleteData.setVisibility(View.VISIBLE);
            deleteData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    intent.putExtra("op_Type", "delete");
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                TextView username = (TextView) findViewById(R.id.username);
                username.setText("Hi,   "+userName);
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}