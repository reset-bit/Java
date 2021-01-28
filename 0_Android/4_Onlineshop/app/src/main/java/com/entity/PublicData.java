package com.entity;

import android.app.Application;

import java.util.ArrayList;

public class PublicData extends Application {
    private static final int ID = 1;
    private static final ArrayList<Integer> SIDS = new ArrayList<Integer>();
    private int id;
    private ArrayList<Integer> sids;

    @Override
    public void onCreate()
    {
        super.onCreate();
        // 初始化全局变量
        setId(ID);
        setSids(SIDS);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getSids() {
        return sids;
    }

    public void setSids(ArrayList<Integer> sids) {
        this.sids = sids;
    }
}
