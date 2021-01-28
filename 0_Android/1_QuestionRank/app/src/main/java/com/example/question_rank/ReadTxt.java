package com.example.question_rank;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadTxt {

    public ArrayList<String> getFromAssets(Context context, String fileName) {
        InputStreamReader inputReader = null;
        try {
            inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            ArrayList<String> datas = new ArrayList<>();
            int i = 0;
            String data;
            while((data = bufReader.readLine()) != null) {
                datas.add(i++, data);
            }
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != inputReader) {
                try {
                    inputReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
