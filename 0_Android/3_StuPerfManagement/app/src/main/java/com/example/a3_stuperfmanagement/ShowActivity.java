package com.example.a3_stuperfmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a3_stuperfmanagement.entity.Sc;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {
    private List<Sc> scList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        scList.clear();
        int size = getIntent().getIntExtra("size", 0);
        for(int i = 0; i < size; ++i){
            String cname = getIntent().getStringExtra("cname" + i);
            int score = getIntent().getIntExtra("score" + i, 0);
            Sc sc = new Sc(cname, score);
            scList.add(sc);
        }
        //卡片式布局传参
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_View);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        ScAdapter adapter = new ScAdapter(scList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class ScAdapter extends RecyclerView.Adapter<ScAdapter.ViewHolder>{
        private Context mContext;
        private List<Sc> mscList;
        public ScAdapter(List<Sc> scList){
            this.mscList = scList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if(mContext == null){
                mContext = parent.getContext();
            }
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Sc sc = mscList.get(position);
            holder.cname.setText(sc.getCname());
            holder.score.setText(sc.getScore() + "分");
        }

        @Override
        public int getItemCount() {
            return mscList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView cname;
            TextView score;
            public ViewHolder(View view){
                super(view);
                cname = (TextView) view.findViewById(R.id.cname);
                score = (TextView) view.findViewById(R.id.score);
            }
        }

    }
}