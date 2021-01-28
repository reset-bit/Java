package com.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.entity.Book;
import com.entity.DBHelper;
import com.entity.Detail;
import com.entity.PublicData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zhang.onlineshop.R;

public class DetailActivity extends AppCompatActivity {
    private List<Detail> detailList = new ArrayList<>();
    private Book book = new Book();
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dbHelper = new DBHelper(this, "bookshop.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String bookname = getIntent().getStringExtra("bname");
        getDetails(bookname);
        TextView bookPriceContent = (TextView) findViewById(R.id.book_Price_Content);
        String princeText = "¥"+book.getBprice();
        bookPriceContent.setText(princeText);
        TextView bookNameContent = (TextView) findViewById(R.id.book_Name_Content);
        bookNameContent.setText(book.getBname());
        TextView bookDetailContent = (TextView) findViewById(R.id.book_detail);
        String detail = "作者\t";
        detail += book.getAuthor() + "\n类别\t";
        detail += book.getBtype() + "\n出版社\t";
        detail += book.getPublish() + "\n";
        bookDetailContent.setText(detail);

        FloatingActionButton back = (FloatingActionButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button addShopping = (Button) findViewById(R.id.add_to_shoppingcart);
        addShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder updateBuilder = new AlertDialog.Builder(DetailActivity.this);
                updateBuilder.setCancelable(true);
                updateBuilder.setMessage("请输入购买数量：");
                EditText numText = new EditText(DetailActivity.this);
                updateBuilder.setView(numText);
                updateBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PublicData publicData = (PublicData) getApplication();
                        int id = publicData.getId();
                        String num = numText.getText().toString();
                        db.execSQL("insert into shopping(id, bid, num) values (?, ?, ?)", new String[]{String.valueOf(id), String.valueOf(book.getId()), num});
                        Toast.makeText(DetailActivity.this, "加入购物车成功", Toast.LENGTH_SHORT).show();
                    }
                });
                updateBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {return;}
                });
                updateBuilder.show();
            }
        });
        Button goToPay = (Button) findViewById(R.id.go_to_pay);
        goToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PublicData publicData = (PublicData) getApplication();
                int id = publicData.getId();
                //插入订单情况表
                db.execSQL("insert into shopping(id, bid, num) values (?, ?, ?)", new String[]{String.valueOf(id), String.valueOf(book.getId()), 1+""});
                Cursor cursor = db.rawQuery("select sid from shopping where id = ? and bid = ? and num = ?", new String[]{String.valueOf(id), String.valueOf(book.getId()), 1+""});
                if(cursor.moveToFirst()){
                    do {
                        int sid = cursor.getInt(cursor.getColumnIndex("sid"));
                        Log.d("sid", sid+"");
                        //保存当前订单情况到全局变量
                        ArrayList<Integer> sids = new ArrayList<Integer>();
                        sids.add(sid);
                        publicData.setSids(sids);
                    }while (cursor.moveToNext());
                }
                cursor.close();

                ArrayList<Integer> t = publicData.getSids();
                Log.d("sid", "sids.size="+t.size());

                Intent intent = new Intent(DetailActivity.this, ConfirmActivity.class);
                startActivity(intent);
            }
        });

        //加载组件
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.book_RecyclerView_Detail);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//横向滚动
        recyclerView.setLayoutManager(layoutManager);
        //加载适配器
        DetailAdapter detailAdapter = new DetailAdapter(detailList);
        recyclerView.setAdapter(detailAdapter);
    }

    private void getDetails(String bookname){
        DBHelper dbHelper = new DBHelper(this, "bookshop.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from book where bname = ?", new String[]{String.valueOf(bookname)});
        if(cursor.moveToFirst()){
            do{
                int bid = cursor.getInt(cursor.getColumnIndex("bid"));
                String bname = cursor.getString(cursor.getColumnIndex("bname"));
                String label = cursor.getString(cursor.getColumnIndex("label"));
                double bprice = cursor.getDouble(cursor.getColumnIndex("bprice"));
                String btype = cursor.getString(cursor.getColumnIndex("btype"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                String publish = cursor.getString(cursor.getColumnIndex("publish"));
                book = new Book(bid, bname, label, bprice, btype, author, publish);
            }while (cursor.moveToNext());
        }
        cursor.close();

        detailList.clear();
        for(int i = 1; i <= 3; ++i){
            String label = book.getLabel()+"_"+i;
            int icon = getResources().getIdentifier(label, "drawable", getPackageName());
            if(icon > 0){
                Detail detail = new Detail(icon);
                detailList.add(detail);
            }
        }
    }

    //内部类适配器
    class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder>{
        private List<Detail> mDetailList;

        public DetailAdapter(List<Detail> detailList){ mDetailList = detailList; }

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView detailImage;
            public ViewHolder(View view){
                super(view);
                detailImage = (ImageView) view.findViewById(R.id.detail_Image);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //加载recyclerview子项布局对应view
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_detail, parent, false);
            //获得要返回的viewholder对象
            final ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Detail detail = mDetailList.get(position);
            holder.detailImage.setImageResource(detail.getImageId());
        }

        @Override
        public int getItemCount() {
            return mDetailList.size();
        }
    }

}