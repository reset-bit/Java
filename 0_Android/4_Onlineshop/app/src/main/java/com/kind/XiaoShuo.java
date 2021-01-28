package com.kind;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhang.onlineshop.R;

public class XiaoShuo extends AppCompatActivity {

    private ListView listxiaoshuo;
    private String[] names={"流星之绊",
            "小王子",
            "了不起的盖茨比","我是猫",
            "罪与罚","曾国藩","大明王朝","围城"};
    private String[] prices={"￥22.5","￥17.5","￥19.2","￥20.3","￥33.7","￥49.5","￥49","￥17.5"};
    private int[] pics={R.drawable.lxzb_0, R.drawable.xwz_0,R.drawable.lbqdgcb_0,R.drawable.wsm_0,R.drawable.zyf_0,R.drawable.zgf_0,R.drawable.dmwc_0,R.drawable.wc_0};





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_shuo);
        listxiaoshuo=(ListView)findViewById(R.id.listxiaoshuo);

        XiaoShuo.MyBaseAdapter adapter = new XiaoShuo.MyBaseAdapter();
        listxiaoshuo.setAdapter (adapter);


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
                convertView=View.inflate(XiaoShuo.this,R.layout.listitem,null);
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