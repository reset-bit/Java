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

public class WenXue extends AppCompatActivity {

    private ListView listwenxue;
    private String[] names={"皮囊",
            "目光",
            "心安即是归处","人间告白",
            "撒哈拉的故事","我与地坛","春风桃李一杯酒","鲜衣怒马少年时"};
    private String[] prices={"￥24.7","￥24.9","￥9.9","￥22.4","￥17.5","￥18.5","￥24.5","￥19.7"};
    private int[] pics={R.drawable.pn_0, R.drawable.mg_0, R.drawable.xajsgc_0, R.drawable.rjgb_0, R.drawable.shldgs_0, R.drawable.wydt_0, R.drawable.tlcfybj_0, R.drawable.xynmsns_0};




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wen_xue);
        listwenxue=(ListView)findViewById(R.id.listwenxue);

        MyBaseAdapter adapter = new MyBaseAdapter();
        listwenxue.setAdapter (adapter);


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
                convertView=View.inflate(WenXue.this, R.layout.listitem,null);
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