package com.pages;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.entity.DBHelper;
import com.entity.Detail;
import com.entity.Order;
import com.entity.PublicData;
import com.zhang.onlineshop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    ArrayList<Order> orderList;
    private int sum = 0;//总价

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        dbHelper = new DBHelper(this, "bookshop.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        PublicData publicData = (PublicData) getApplication();
        int id = publicData.getId();
        ArrayList<Integer> sids = publicData.getSids();


        orderList = new ArrayList<>();
        sum = 0;
        if(orderList.size() > 0){
            orderList.clear();
        }
        for(int i = 0; i < sids.size(); ++i){
            Cursor cursorShopping = db.rawQuery("select * from shopping where sid = ?", new String[]{String.valueOf(sids.get(i))});
            if(cursorShopping.moveToFirst()){
                do {
                    String bname = "";
                    String label = "";
                    int bprice = 0;
                    Cursor cursorBook = db.rawQuery("select bname, label, bprice from book where bid = ?", new String[]{cursorShopping.getString(cursorShopping.getColumnIndex("bid"))});
                    if(cursorBook.moveToFirst()){
                        do {
                            bname = cursorBook.getString(cursorBook.getColumnIndex("bname"));
                            label = cursorBook.getString(cursorBook.getColumnIndex("label"));
                            bprice = cursorBook.getInt(cursorBook.getColumnIndex("bprice"));
                        }while (cursorBook.moveToNext());
                    }
                    cursorBook.close();
                    int num = cursorShopping.getInt(cursorShopping.getColumnIndex("num"));
                    Order order = new Order(bname, label, bprice, num);
                    orderList.add(order);
                    sum += bprice * num;
                }while (cursorShopping.moveToNext());
            }
            cursorShopping.close();
        }
        Log.d("sid", "orderList.size="+orderList.size());

        ListView orderListView = (ListView) findViewById(R.id.orderlist);
        OrderAdapter orderAdapter = new OrderAdapter(ConfirmActivity.this, R.layout.listview_order, orderList);
        orderListView.setAdapter(orderAdapter);

        TextView totalPriceText = (TextView) findViewById(R.id.tv_total_price);
        totalPriceText.setText(sum+"元");
        TextView submit = (TextView) findViewById(R.id.bt);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从订单情况表中删除信息
                for(int i = 0; i < sids.size(); ++i){
                    db.execSQL("delete from shopping where sid = ?", new String[]{String.valueOf(sids.get(i))});
                }
                //提示并跳转
                Toast.makeText(ConfirmActivity.this, "结算成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ConfirmActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
    }

    class OrderAdapter extends ArrayAdapter<Order>{
        private int resourceId;//子项布局文件id

        public OrderAdapter(Context context, int textViewResourceId, ArrayList<Order> orderList){
            super(context, textViewResourceId, orderList);
            resourceId = textViewResourceId;
        }

        //每个子项在被滚动到屏幕内时调用
        //ListView中的每一个Item显示都需要Adapter调用一次getView方法，这个方法会传入一个convertView的参数，返回的View就是这个Item显示的View
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

            Order order = getItem(position);//获取当前Order实例
            String label = order.getLabel()+"_0";
            int icon = getResources().getIdentifier(label, "drawable", getPackageName());
            ImageView img = (ImageView) view.findViewById(R.id.iv_adapter_list_pic);
            img.setImageResource(icon);
            TextView bname = (TextView) view.findViewById(R.id.tv_goods_name);
            bname.setText(order.getBname());
            TextView bprice = (TextView) view.findViewById(R.id.tv_goods_price);
            bprice.setText("¥"+order.getBprice());
            TextView num = (TextView) view.findViewById(R.id.tv_num);
            num.setText("*"+order.getNum());
            return view;
        }
    }
}

