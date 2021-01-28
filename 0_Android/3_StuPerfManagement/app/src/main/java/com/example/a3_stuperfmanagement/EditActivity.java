package com.example.a3_stuperfmanagement;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3_stuperfmanagement.sqlite.MyDataBaseHelper;
import com.google.android.material.snackbar.Snackbar;

public class EditActivity extends AppCompatActivity {
    private MyDataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        dbHelper = new MyDataBaseHelper(this, "StuPerfManagement.db", null, 1);

        LinearLayout scoreLinearLayout = (LinearLayout) findViewById(R.id.score_LinearLayout);
        String opType = getIntent().getStringExtra("op_Type");
        if (opType.equals("update")){
            scoreLinearLayout.setVisibility(View.GONE);
        }
        else{
            scoreLinearLayout.setVisibility(View.VISIBLE);
        }
        EditText snoText = (EditText) findViewById(R.id.sno_Text);
        EditText cnoText = (EditText) findViewById(R.id.cno_Text);
        EditText scoreText = (EditText) findViewById(R.id.score_Text);

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();//以读写方式打开数据库

                if(!snoText.getText().toString().equals("") && !cnoText.getText().toString().equals("")){
                    int inputSno = Integer.parseInt(snoText.getText().toString());
                    int inputCno = Integer.parseInt(cnoText.getText().toString());
                    //按跳转情况处理不同业务逻辑
                    switch(opType){
                        case "add":
                            int inputScore = Integer.parseInt(scoreText.getText().toString());
                            //遍历查找学号是否存在
                            boolean isSno = false;
                            Cursor cursor_stu = db.rawQuery("select * from Student where sno = ?", new String[]{String.valueOf(inputSno)});
                            if(cursor_stu.getCount() != 0){
                                isSno = true;
                            }
                            //遍历查找课程号是否存在
                            boolean isCno = false;
                            Cursor cursor_course = db.rawQuery("select * from Course where cno = ?", new String[]{String.valueOf(inputCno)});
                            if(cursor_course.getCount() != 0){
                                isCno = true;
                            }
                            //满足插入标准
                            if(!isSno | !isCno){
                                Toast.makeText(EditActivity.this, "插入失败，课程或学生信息不存在", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                db.execSQL("insert into SC (sno, cno, score) values(?, ?, ?)", new String[]{String.valueOf(inputSno), String.valueOf(inputCno), String.valueOf(inputScore)});
                                Toast.makeText(EditActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "update":
                            String updateMsg = "";//弹窗显示信息
                            Cursor cursor_sc_update = db.rawQuery("select score from SC where sno = ? and cno = ?", new String[]{String.valueOf(inputSno), String.valueOf(inputCno)});
                            if(cursor_sc_update.moveToFirst()) {
                                do {
                                    int oldScore = cursor_sc_update.getInt(cursor_sc_update.getColumnIndex("score"));
                                    updateMsg = "原成绩为  " + oldScore + "  ，请输入需要更新的成绩：";
                                } while (cursor_sc_update.moveToNext());
                            }
                            cursor_sc_update.close();
                            AlertDialog.Builder updateBuilder = new AlertDialog.Builder(EditActivity.this);
                            updateBuilder.setCancelable(true);//设置用户可取消
                            if(!updateMsg.equals("")){
                                updateBuilder.setMessage(updateMsg);
                                EditText newScore = new EditText(EditActivity.this);//更新成绩输入框
                                updateBuilder.setView(newScore);
                                //设置确定按钮及其响应函数
                                updateBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        int inputNewScore = Integer.parseInt(newScore.getText().toString());
                                        db.execSQL("update SC set score = ? where sno = ? and cno = ?", new String[]{String.valueOf(inputNewScore), String.valueOf(inputSno), String.valueOf(inputCno)});
                                        Toast.makeText(EditActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                updateBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {return;}
                                });
                            }
                            else{
                                updateMsg = "数据库中没有符合条件的数据";
                                updateBuilder.setMessage(updateMsg);
                                updateBuilder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {return;}
                                });
                            }
                            updateBuilder.show();
                            break;
                        case "delete":
                            int oldScore = Integer.parseInt(scoreText.getText().toString());
                            String deleteMsg = "";//弹窗显示信息
                            Cursor cursor_sc_delete = db.rawQuery("select score from SC where sno = ? and cno = ? and score = ?", new String[]{String.valueOf(inputSno), String.valueOf(inputCno), String.valueOf(oldScore)});
                            if(cursor_sc_delete.getCount() > 0) {
                                Snackbar.make(v, "确定删除这条记录吗？", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        db.execSQL("delete from SC where sno = ? and cno = ? and score = ?", new String[]{String.valueOf(inputSno), String.valueOf(inputCno), String.valueOf(oldScore)});
                                        Toast.makeText(EditActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                            }
                            else{
                                Toast.makeText(EditActivity.this, "数据库中没有符合条件的数据", Toast.LENGTH_SHORT).show();
                            }
                            cursor_sc_delete.close();
                            break;
                    }
                    snoText.setText("");
                    cnoText.setText("");
                    scoreText.setText("");
                }
            }

        });
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
}