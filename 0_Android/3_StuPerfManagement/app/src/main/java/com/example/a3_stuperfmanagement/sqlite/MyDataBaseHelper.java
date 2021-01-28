package com.example.a3_stuperfmanagement.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_STUDENT = "create table Student(" +
            "sno integer primary key autoincrement, " +
            "sname text, " +
            "ssex text, " +
            "sdept text)";
    public static final String CREATE_COURSE = "create table Course(" +
            "cno integer primary key autoincrement, " +
            "cname text)";
    public static final String CREATE_SC = "create table SC(" +
            "sno integer, " +
            "cno integer, " +
            "score integer)";
    public static final String CREATE_USERS = "create table Users(" +
            "id integer, " +
            "pwd text)";

    private Context context;

    public MyDataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //执行SQL语句
        db.execSQL(CREATE_STUDENT);
        db.execSQL(CREATE_COURSE);
        db.execSQL(CREATE_SC);
        db.execSQL(CREATE_USERS);
        db.execSQL("insert into Users (id, pwd) values(?, ?)", new String[]{"1", "123"});
        db.execSQL("insert into Users (id, pwd) values(?, ?)", new String[]{"2", "123"});
        Toast.makeText(context, "创建表成功", Toast.LENGTH_SHORT).show();
    }

    //升级数据库，同时创建多张表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if exists Student");
//        db.execSQL("drop table if exists Course");
//        onCreate(db);
    }
}
