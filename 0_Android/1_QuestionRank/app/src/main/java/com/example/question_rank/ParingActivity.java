package com.example.question_rank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ParingActivity extends AppCompatActivity {

    private TextView parsing;
    private Button back;

    private ReadTxt readtxt = new ReadTxt();
    private ArrayList<String> parsings;

    public void getParing(int index){
        parsing.setText(parsings.get(index));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paring);

        parsing = (TextView) findViewById(R.id.paring);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("msg_return", "作弊是不对的");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        ArrayList<String> datas = readtxt.getFromAssets(this, "qrank.txt");
        parsings = new ArrayList<>();
        for(int i = 0; i < datas.size(); ++i){
            String data = datas.get(i);
            String[] line = data.split("\t");
            line[4] = line[4].replace("\"", " ").trim();
            parsings.add(line[4]);
        }

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 0);
        getParing(index);
    }

    //按下Back键依旧返回数据
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("msg_return", "作弊是不对的");
        setResult(RESULT_OK, intent);
        finish();
    }
}