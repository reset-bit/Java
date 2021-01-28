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

public class DongMan extends AppCompatActivity {

    private ListView listdongman;
    private String[] names={"假如历史是一群喵",
            "数码宝贝",
            "你今天真好看","龙猫",
            "窝在角落好安心","名侦探柯南","我的心中每天开出一朵花","我不是完美小孩"};
    private String[] prices={"￥190","￥133","￥93.4","￥49","￥16","￥84","￥24","￥24"};
    private int[] pics={R.drawable.rulssyqm_0, R.drawable.smbb_0, R.drawable.njtzhk_0, R.drawable.lm_0, R.drawable.wzjlhax_0, R.drawable.mztkn_0, R.drawable.wdxlmtkcydh_0, R.drawable.wc_0};




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_man);
        listdongman=(ListView)findViewById(R.id.listdongman);

        MyBaseAdapter adapter = new MyBaseAdapter();
        listdongman.setAdapter (adapter);


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
                convertView=View.inflate(DongMan.this, R.layout.listitem,null);
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