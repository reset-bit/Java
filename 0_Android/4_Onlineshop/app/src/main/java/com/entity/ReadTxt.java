package com.entity;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadTxt {
	//传入文件地址
    public ArrayList<String> getFromAssets(Context context, String fileName) {
        InputStreamReader inputReader = null;
        ArrayList<String> datas = new ArrayList<>();
        Log.d("database", "this is test\n");
        try {
            inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            int i = 0;
            String data;
            while((data = bufReader.readLine()) != null) {
                datas.add(i++, data);
                Log.d("database", data+"\n");
            }
            bufReader.close();
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
        return datas;
    }
}
