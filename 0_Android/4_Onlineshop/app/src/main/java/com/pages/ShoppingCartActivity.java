package com.pages;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.entity.DBHelper;
import com.entity.SysApplication;
import com.zhang.onlineshop.R;
import com.entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ShoppingCartActivity extends AppCompatActivity implements ShoppingCartAdapter.RefreshPriceInterface, View.OnClickListener {
    private ListView listView;
    private CheckBox cb_check_all;
    private TextView tv_total_price;
    private TextView tv_delete;
    private TextView tv_go_to_pay;
    private Button btn_main;
    private Button btn_me;
    private DBHelper dbHelper;
    private ShoppingCartAdapter adapter;
    private PublicData publicData;
    private Integer[] selectBook;//购物车选择的书本
    private ArrayList<Integer> select_book;//要传给公共变量的sid（订单编号）
    private Book book = new Book();

    private double totalPrice = 0.00;
    private int totalCount = 0;
    private List<HashMap<String, String>> goodsList;
    private String[] names={"我与地坛","目光","心安即是归处","春风桃李一杯酒","流星之绊","算法导论","看漫画学python",
            "小王子","了不起的盖茨比","我是猫","罪与罚","曾国藩","大明王朝","围城","皮囊", "人间告白",
            "撒哈拉的故事","数码宝贝","你今天真好看", "龙猫","窝在角落好安心","名侦探柯南","我的心中每天开出一朵花",
            "我不是完美小孩","鲜衣怒马少年时","假如历史是一群喵","高性能mySQl","鸟哥的Linux私房菜","SQL必知必会",
            "深度学习推荐系统", "Head First Java","程序员修炼之道"};

    private String[] prices={"18.5","24.9","9.9","24.5","22.5","83.2","44.1", "17.5","19.2",
            "20.3","33.7","49.5","49","17.5","24.7", "22.4", "17.5","133","93.4", "49",
            "16","84","23", "24","19.7","190","63.4","81.4","33.8", "53.5", "39.1","44.5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        SysApplication.getInstance().addActivity(this);

        btn_main = (Button) findViewById (R.id.btn_main);
        btn_me = (Button)findViewById (R.id.btn_me);

        initDate();

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent ie = new Intent(ShoppingCartActivity.this, MainActivity.class);
                startActivity ( ie );
            }
        });

        btn_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent ie= new Intent();
                ie.setClass ( ShoppingCartActivity.this, MeActivity.class);
                ShoppingCartActivity.this.startActivity ( ie );
            }
        });
       /* listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ShoppingCartActivity.this, DetailActivity.class);
            startActivity(intent);
        });*/
    }

    //控制价格展示
    private void priceControl(Map<String, Integer> pitchOnMap) {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < goodsList.size(); i++) {
            if (pitchOnMap.get(goodsList.get(i).get("id")) == 1) {
                totalCount = totalCount + Integer.valueOf(goodsList.get(i).get("count"));
                double goodsPrice = Integer.valueOf(goodsList.get(i).get("count")) * Double.valueOf(goodsList.get(i).get("price"));
                totalPrice = totalPrice + goodsPrice;
            }
        }
        tv_total_price.setText("￥ " + totalPrice);
        tv_go_to_pay.setText("付款(" + totalCount + ")");
    }
    @Override
    public void refreshPrice(Map<String, Integer> pitchOnMap) {
        priceControl(pitchOnMap);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_chekbox:
                AllTheSelected();
                break;
            case R.id.tv_go_to_pay:
                if (totalCount <= 0) {
                    Toast.makeText(this, "请选择要付款的商品~", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Toast.makeText(this, "钱就是另一回事了~", Toast.LENGTH_SHORT).show();
                else{
                    checkSelect(adapter.getPitchOnMap());
                    Intent intent = new Intent(ShoppingCartActivity.this, ConfirmActivity.class);
                    intent.putExtra("bname", book.getBname());
                    intent.putExtra("bprice", book.getBprice());
                    intent.putExtra("author", book.getAuthor());
                    intent.putExtra("publish", book.getPublish());
                    startActivity(intent);
                    break;
                }
            case R.id.tv_delete:
                if (totalCount <= 0) {
                    Toast.makeText(this, "请选择要删除的商品~", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    AlertDialog.Builder delete_info = new AlertDialog.Builder(ShoppingCartActivity.this);
                    delete_info.setTitle("提示!!!");
                    delete_info.setMessage("确定要删除商品么？");
                    delete_info.setNegativeButton("取消",null);
                    delete_info.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            checkDelete(adapter.getPitchOnMap());
                        }
                    });
                    delete_info.show();
                }
                break;
        }
    }
    //删除操作
    private void checkDelete(Map<String, Integer> map) {
        List<HashMap<String, String>> waitDeleteList = new ArrayList<>();
        Map<String, Integer> waitDeleteMap = new HashMap<>();
        for (int i = 0; i < goodsList.size(); i++) {
            if (map.get(goodsList.get(i).get("id")) == 1) {
                waitDeleteList.add(goodsList.get(i));
                waitDeleteMap.put(goodsList.get(i).get("id"), map.get(goodsList.get(i).get("id")));
            }
        }
        goodsList.removeAll(waitDeleteList);
        map.remove(waitDeleteMap);
        priceControl(map);
        adapter.notifyDataSetChanged();
    }
    //选择结算的订单
    private void checkSelect(Map<String, Integer> map) {
        List<HashMap<String, String>> waitDeleteList = new ArrayList<>();
        Map<String, Integer> waitDeleteMap = new HashMap<>();
        for (int i = 0; i < goodsList.size(); i++) {
            if (map.get(goodsList.get(i).get("id")) == 1) {
                for(int j = 0;j <= 32;j++){
                    selectBook[j] = i;
                }
                select_book = new ArrayList<Integer>(Arrays.asList(selectBook));
                publicData.setSids(select_book);
            }
        }
        goodsList.removeAll(waitDeleteList);
        map.remove(waitDeleteMap);
        priceControl(map);
        adapter.notifyDataSetChanged();
    }
    //全选或反选
    private void AllTheSelected() {
        Map<String, Integer> map = adapter.getPitchOnMap();
        boolean isCheck = false;
        boolean isUnCheck = false;
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if (Integer.valueOf(entry.getValue().toString()) == 1) isCheck = true;
            else isUnCheck = true;
        }
        if (isCheck == true && isUnCheck == false) {//已经全选,做反选
            for (int i = 0; i < goodsList.size(); i++) {
                map.put(goodsList.get(i).get("id"), 0);
            }
            cb_check_all.setChecked(false);
        } else if (isCheck == true && isUnCheck == true) {//部分选择,做全选
            for (int i = 0; i < goodsList.size(); i++) {
                map.put(goodsList.get(i).get("id"), 1);
            }
            cb_check_all.setChecked(true);
        } else if (isCheck == false && isUnCheck == true) {//一个没选,做全选
            for (int i = 0; i < goodsList.size(); i++) {
                map.put(goodsList.get(i).get("id"), 1);
            }
            cb_check_all.setChecked(true);
        }
        priceControl(map);
        adapter.setPitchOnMap(map);
        adapter.notifyDataSetChanged();
    }
    private void initView() {
        listView = (ListView) findViewById(R.id.listview);
        cb_check_all = (CheckBox) findViewById(R.id.all_chekbox);
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        tv_go_to_pay = (TextView) findViewById(R.id.tv_go_to_pay);
        tv_go_to_pay.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        cb_check_all.setOnClickListener(this);
        adapter = new ShoppingCartAdapter(this, goodsList);
        adapter.setRefreshPriceInterface(this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void initDate() {
        goodsList = new ArrayList<>();

        for (int i = 0; i < 32; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", (i+1)+"");
            map.put("name", names[i]);
            map.put("type", "有货");
            map.put("price", prices[i]);
            map.put("count", "1");
            goodsList.add(map);
        }
        initView();

    }


}



