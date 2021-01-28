package com.example.a3_stuperfmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.a3_stuperfmanagement.activity.BaseActivity;
import com.example.a3_stuperfmanagement.sqlite.MyDataBaseHelper;

public class LoginActivity extends BaseActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private int identify = 0;//用户身份类型
    private MyDataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();// 隐藏ActionBar
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);//获取SharedPreference对象
        dbHelper = new MyDataBaseHelper(this, "StuPerfManagement.db", null, 1);

        EditText accountText = (EditText) findViewById(R.id.account);
        EditText passwordText = (EditText) findViewById(R.id.password);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        //获取单选按钮序号
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                identify = checkedId;
            }
        });

        CheckBox rememberPass = (CheckBox) findViewById(R.id.remember_Pass);
        boolean isRemember = pref.getBoolean("remember_pass", false);
        if(isRemember){//若选择记住密码，则获得文本
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountText.setText(account);
            passwordText.setText(password);
            rememberPass.setChecked(true);
        }

        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String account = accountText.getText().toString();
                String password = passwordText.getText().toString();
                Log.d("Main", "account="+account);
                if(checkUser(Integer.parseInt(account), password)){
                    editor = pref.edit();
                    if(rememberPass.isChecked()){//存入文件
                        editor.putBoolean("remember_pass", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    }else{//清除文件内容
                        editor.clear();
                    }
                    editor.apply();
                    //登录成功，跳转主页面
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("account", account);
                    intent.putExtra("identify", identify);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkUser(int account, String password){
        SQLiteDatabase db = dbHelper.getReadableDatabase();//以只读方式打开数据库
        Cursor cursor = db.rawQuery("select * from Users", null);
        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String pwd = cursor.getString(cursor.getColumnIndex("pwd"));
                if(id == account && pwd.equals(password)){
                    return true;
                }
            }while (cursor.moveToNext());
        }
        return false;
    }
}