package com.example.question_rank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class QueActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView question;
    private Button option1;
    private Button option2;
    private Button option3;
    private Button last;
    private Button next;
    private Button goParing;

    private ReadTxt readtxt = new ReadTxt();
    private ArrayList<String> datas;
    private String[] data;
    private int index;
    private int score;
    private int flag;

    //Lint静态检查器，围绕Android项目的正确性、安全性、性能、可用性以及可访问性进行分析
    //@SuppressLint("HandlerLeak")将在主线程用Handler处理消息出现时警告，提示有内存泄露的危险
    //Handler用于异步消息的处理
//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg){
//            String s = (String) msg.obj;
//            question.setText(s);
//        }
//    };

    //data[0]=id; data[1]=question; data[2]=opitons; data[3]=answer; data[4]=parsing; data[5]=score
    public void getViewText(int index){
        String line = datas.get(index);
        data = line.split("\t");
        for(int i = 0; i < data.length; ++i){
            data[i] = data[i].replace('\"', ' ').trim();
        }
        flag = 0;
    }

    public void setViewText(){
        int progress = progressBar.getProgress();
        progress = progress + 100 / datas.size();
        progressBar.setProgress(progress);

        question.setText(data[1]);
        String[] ops = data[2].split(";");
        option1.setText(ops[0]);
        option2.setText(ops[1]);
        if(ops.length == 3){
            option3.setText(ops[2]);
            option3.setVisibility(View.VISIBLE);
        }
        else {
            option3.setVisibility(View.GONE);
            option3.setVisibility(View.GONE);
        }

        if(index == datas.size() - 1){
            next.setText("交卷");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        question = (TextView) findViewById(R.id.question);
        option1 = (Button) findViewById(R.id.option1);
        option1.setOnClickListener(this::onClick);
        option2 = (Button) findViewById(R.id.option2);
        option2.setOnClickListener(this::onClick);
        option3 = (Button) findViewById(R.id.option3);
        option3.setOnClickListener(this::onClick);
        option3.setVisibility(View.GONE);
        last = (Button) findViewById(R.id.last);
        last.setOnClickListener(this::onClick);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this::onClick);
        goParing = (Button) findViewById(R.id.goParing);
        goParing.setOnClickListener(this::onClick);

        datas = readtxt.getFromAssets(this, "qrank.txt");
        data = new String[5];
        index = 0;
        score = 0;
        getViewText(index);
        setViewText();

        //在Android4.0之后，不允许在主线程中进行比较耗时的操作（如连接数据库），需要开一个新线程
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HashMap<String, Object> map = DBUtils.getInfoById(1);//获取数据库表数据
//                Message message = handler.obtainMessage();
//                if(map != null){
//                    String s = "";
//                    for(String key : map.keySet()){
//                        s += key +":"+map.get(key)+"\n";
//                        message.what = 0x12;
//                        message.obj = s;
//                    }
//                }
//                else {
//                    message.what = 0x11;
//                    message.obj = "查询结果为空";
//                }
//                //发消息通知主线程更新UI
//                handler.sendMessage(message);
//            }
//        });
    }


    public void onClick(View v){
        switch(v.getId()){
            case R.id.option1:
                if(flag != 0){
                    Toast.makeText(QueActivity.this, "已经回答过了，快去下一题吧", Toast.LENGTH_SHORT).show();
                }
                else{
                    ++flag;
                    if(data[3].equals("A")){
                        score += Integer.parseInt(data[5]);
                        Toast.makeText(QueActivity.this, "回答正确", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(QueActivity.this, "回答错误", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.option2:
                if(flag != 0){
                    Toast.makeText(QueActivity.this, "已经回答过了，快去下一题吧", Toast.LENGTH_SHORT).show();
                }
                else{
                    ++flag;
                    if(data[3].equals("B")){
                        score += Integer.parseInt(data[5]);
                        Toast.makeText(QueActivity.this, "回答正确", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(QueActivity.this, "回答错误", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.option3:
                if(flag != 0){
                    Toast.makeText(QueActivity.this, "已经回答过了，快去下一题吧", Toast.LENGTH_SHORT).show();
                }
                else{
                    ++flag;
                    if(data[3].equals("C")){
                        score += Integer.parseInt(data[5]);
                        Toast.makeText(QueActivity.this, "回答正确", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(QueActivity.this, "回答错误", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.last:
                if(index == 0){
                    Toast.makeText(QueActivity.this, "已经是第一题了", Toast.LENGTH_SHORT).show();
                }
                else{
                    getViewText(--index);
                    setViewText();
                }
                break;
            case R.id.next:
                if(index == datas.size() - 1){
                    Intent intent = new Intent(QueActivity.this, EndActicity.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                }
                else{
                    getViewText(++index);
                    setViewText();
                }
                break;
            case R.id.goParing:
                Intent intent = new Intent(QueActivity.this, ParingActivity.class);
                intent.putExtra("index", index);
                startActivityForResult(intent, 1);//请求码用于在回调函数中判断数据来源
                //startActivity(intent);//请求码用于在回调函数中判断数据来源
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String msg_return = data.getStringExtra("msg_return");
                    Toast.makeText(QueActivity.this, msg_return, Toast.LENGTH_SHORT).show();
                }
        }
    }
}