package com.example.a2_commoditybrowsing_x.entity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.example.a2_commoditybrowsing_x.R;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {
    private List<Detail> mDetailList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView detailImage;
        public ViewHolder(View view){
            super(view);
            detailImage = (ImageView) view.findViewById(R.id.detail_Image);
        }
    }

    public DetailAdapter(List<Detail> detailList){
        mDetailList = detailList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_detail, parent, false);
        return new ViewHolder(view);
    }

    //对recyclerview子项数据赋值，在子项被滚动到屏幕内时执行
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Detail detail = mDetailList.get(position);
        viewHolder.detailImage.setImageResource(detail.getImageId());
    }

    //获取recyclerview子项总数
    @Override
    public int getItemCount() {
        return mDetailList.size();
    }
}
