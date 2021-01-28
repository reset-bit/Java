package com.kind;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhang.onlineshop.R;

public class JiSuanji extends AppCompatActivity {

    private ListView listjisuanji;
    private String[] names={"高性能mySQl",
            "鸟哥的Linux私房菜",
            "SQL必知必会","深度学习推荐系统",
            "Head First Java","算法导论","看漫画学python","程序员修炼之道"};
    private String[] prices={"￥63.4","￥81.4","￥33.8","￥53.5","￥39.1","￥83.2","￥44.1","￥44.5"};
    private int[] pics={R.drawable.mysql_0, R.drawable.linux_0, R.drawable.sql_0, R.drawable.sdxx_0, R.drawable.headfirstjava_0, R.drawable.sfdl_0, R.drawable.python_0, R.drawable.cxy_0};




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_suanji);
        listjisuanji=(ListView)findViewById(R.id.listjisuanji);

        MyBaseAdapter adapter = new MyBaseAdapter();
        listjisuanji.setAdapter (adapter);


    }
    static class ViewHolder
    {

        TextView txtName,price,place,paysum,linhquan;
        ImageView img;
        Button btn_car1;

    }
    class MyBaseAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            XiaoShuo.ViewHolder holder=null;
            if(convertView==null) {
                convertView=View.inflate(JiSuanji.this, R.layout.listitem,null);
                holder= new XiaoShuo.ViewHolder();
                holder.txtName=(TextView) convertView.findViewById(R.id.txtName);
                holder.price=(TextView) convertView.findViewById(R.id.price);
                holder.img=(ImageView) convertView.findViewById(R.id.iv);
                convertView.setTag(holder);
            }
            else
            {
                holder=(XiaoShuo.ViewHolder) convertView.getTag();

            }
            holder.txtName.setText(names[position]);
            holder.price.setText(prices[position]);
            holder.img.setBackgroundResource(pics[position]);
            return convertView;
        }


    }

}