package com.example.question_rank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndActicity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_acticity);

        TextView result = (TextView) findViewById(R.id.result);
        Button rebegin = (Button) findViewById(R.id.rebegin);
        rebegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndActicity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);

        String msg = "您的成绩是：\t" + score;
        switch (score / 10){
            case 10:
            case 9:
                msg += "\n太棒了！";
                break;
            case 8:
            case 7:
            case 6:
                msg += "\n还不错！";
                break;
            default:
                msg += "\n加油吧！";
                break;
        }
        result.setText(msg);
    }
}