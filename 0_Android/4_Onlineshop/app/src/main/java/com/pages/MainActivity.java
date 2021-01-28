package com.pages;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.entity.DBHelper;
import com.entity.ReadTxt;
import com.kind.DongMan;
import com.kind.JiSuanji;
import com.kind.WenXue;
import com.kind.XiaoShuo;
import com.search.SearchMain;
import com.zhang.onlineshop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.entity.Book;

public class MainActivity extends Activity {

    private EditText search_jingpin = null;
    private ListView mListView ;
    private Button btn_shopping;
    private Button btn_me;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Button btn_xiaoshuo;
    private Button btn_wenxue;
    private Button btn_dongman;
    private Button btn_jisuanji;

    private ArrayList<Book> books;
    private DBHelper dbHelper = new DBHelper(this, "bookshop.db", null, 1);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        books = initBooks();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.book_recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        BookAdapter adapter = new BookAdapter(books);
        recyclerView.setAdapter(adapter);

        search_jingpin = (EditText) findViewById(R.id.search_jingpin);
        search_jingpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish ();
                Intent ie = new Intent();
                ie.setClass ( MainActivity.this, SearchMain.class);
                MainActivity.this.startActivity ( ie );
            }
        });
        btn_shopping = (Button) findViewById(R.id.btn_shopping);
        btn_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish ();
                Intent ie = new Intent();
                ie.setClass ( MainActivity.this, ShoppingCartActivity.class);
                MainActivity.this.startActivity ( ie );
            }
        });
        btn_me = (Button) findViewById(R.id.btn_me);
        btn_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish ();
                Intent ie = new Intent();
                ie.setClass ( MainActivity.this, MeActivity.class);
                MainActivity.this.startActivity ( ie );
            }
        });

        btn_xiaoshuo = (Button)findViewById (R.id.btn_xiaoshuo);
        btn_wenxue = (Button)findViewById (R.id.btn_wenxue);
        btn_dongman = (Button)findViewById (R.id.btn_dongman);
        btn_jisuanji = (Button)findViewById (R.id.btn_jisuanji);

        btn_xiaoshuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ie = new Intent();
                ie.setClass ( MainActivity.this, XiaoShuo.class);
                MainActivity.this.startActivity ( ie );
            }
        });

        btn_wenxue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ie = new Intent();
                ie.setClass ( MainActivity.this, WenXue.class);
                MainActivity.this.startActivity ( ie );
            }
        });

        btn_dongman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ie = new Intent();
                ie.setClass ( MainActivity.this, DongMan.class);
                MainActivity.this.startActivity ( ie );
            }
        });

        btn_jisuanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ie = new Intent();
                ie.setClass ( MainActivity.this, JiSuanji.class);
                MainActivity.this.startActivity ( ie );
            }
        });
        //下拉刷新
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshBooks();
            }
        });
    }

    public ArrayList<Book> initBooks(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from book", null);
        ArrayList<Book> books = new ArrayList<>();
        if(cursor.getCount() == 0){//初始化数据库
            ReadTxt read = new ReadTxt();
            ArrayList<String> all = read.getFromAssets(this, "book.txt");
            for(int i = 0; i < all.size(); ++i){
                String[] col = all.get(i).split("，");
                String bname;
                if(col[1].contains(" ")){//英文名
                    bname = col[1].substring(col[1].indexOf(" ")).trim();
                }
                else{
                    bname = col[1].split(" ")[1];
                }
                String label = col[2].split(" ")[1];
                double bprice = Double.parseDouble(col[3].split(" ")[1]);
                String btype = col[4].split(" ")[1];
                String author = col[5].split(" ")[1];
                String publish = col[6].split(" ")[1];
//                Log.d("database", "col:"+bname+label+bprice+btype+author+publish);
                db.execSQL("insert into book (bname, label, bprice, btype, author, publish) values(?, ?, ?, ?, ?, ?)", new String[]{bname, label, String.valueOf(bprice), btype, author, publish});

                Book book = new Book(bname, label, bprice, btype, author, publish);
                books.add(book);
            }
        }
        else{
            ArrayList<Book> bs = new ArrayList<>();
            for(int i = 1; i <= 32; ++i){
                Random random = new Random();
                Cursor col = db.rawQuery("select * from book where bid = ?", new String[]{String.valueOf(random.nextInt(32))});
                if(col.moveToFirst()){
                    do{
                        String bname = col.getString(col.getColumnIndex("bname"));
                        String label = col.getString(col.getColumnIndex("label"));
                        double bprice = col.getDouble(col.getColumnIndex("bprice"));
                        String btype = col.getString(col.getColumnIndex("btype"));
                        String author = col.getString(col.getColumnIndex("author"));
                        String publish = col.getString(col.getColumnIndex("publish"));
                        Book book = new Book(bname, label, bprice, btype, author, publish);
                        bs.add(book);
                    }while (col.moveToNext());
                }
                col.close();
            }
            books = bs;
        }
        cursor.close();
        return books;
    }

    private void refreshBooks(){
        //新建线程，下拉刷新
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<Book> bs = new ArrayList<>();
//                        books.clear();
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        //生成随机数读取book表
                        for(int i = 1; i <= 32; ++i){
                            Random random = new Random();
                            Cursor cursor = db.rawQuery("select * from book where bid = ?", new String[]{String.valueOf(random.nextInt(32))});
                            if(cursor.moveToFirst()){
                                do{
                                    String bname = cursor.getString(cursor.getColumnIndex("bname"));
                                    String label = cursor.getString(cursor.getColumnIndex("label"));
                                    double bprice = cursor.getDouble(cursor.getColumnIndex("bprice"));
                                    String btype = cursor.getString(cursor.getColumnIndex("btype"));
                                    String author = cursor.getString(cursor.getColumnIndex("author"));
                                    String publish = cursor.getString(cursor.getColumnIndex("publish"));
                                    Book book = new Book(bname, label, bprice, btype, author, publish);
                                    bs.add(book);
                                }while (cursor.moveToNext());
                            }
                            cursor.close();
                        }
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.book_recyclerview);
                        BookAdapter adapter = new BookAdapter(bs);
                        recyclerView.setAdapter(adapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.book_recyclerview);
        BookAdapter adapter = new BookAdapter(books);
        recyclerView.setAdapter(adapter);
    }

    //内部类适配器
    class BookAdapter extends RecyclerView.Adapter<MainActivity.BookAdapter.ViewHolder>{
        private Context mContext;
        private List<Book> mBookList;

        public BookAdapter(List<Book> bookList){ mBookList = bookList; }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if(mContext == null){
                mContext = parent.getContext();
            }
            //加载recyclerview子项布局对应view
            View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_main, parent, false);
            final ViewHolder holder = new ViewHolder(view);//获得要返回的viewholder对象
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Book book = mBookList.get(position);
//                    Log.d("print", "booklist.size="+mBookList.size());
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("bname", book.getBname());
//                    Log.d("print", "bname="+book.getBname());
                    startActivity(intent);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Book book = mBookList.get(position);
            String label = book.getLabel()+"_0";
            int icon = getResources().getIdentifier(label, "drawable", getPackageName());
            holder.iv.setImageResource(icon);
            holder.bnameText.setText(book.getBname());
            holder.priceText.setText(book.getBprice() + "元/本");
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView iv;
            TextView bnameText;
            TextView priceText;
            public ViewHolder(View view){
                super(view);
                iv = (ImageView) view.findViewById(R.id.iv);
                bnameText = (TextView) view.findViewById(R.id.bname_text);
                priceText = (TextView) view.findViewById(R.id.price_text);
            }
        }
    }
}


