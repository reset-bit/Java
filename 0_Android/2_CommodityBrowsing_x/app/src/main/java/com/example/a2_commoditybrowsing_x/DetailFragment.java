package com.example.a2_commoditybrowsing_x;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2_commoditybrowsing_x.entity.Detail;
import com.example.a2_commoditybrowsing_x.entity.DetailAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {
    private View view;
    private List<Detail> detailList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail, container, false);

        //加载组件
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.food_RecyclerView_Detail);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //加载适配器
        DetailAdapter detailAdapter = new DetailAdapter(detailList);
        recyclerView.setAdapter(detailAdapter);
        return view;
    }

    //获取内容布局并加载文字
    public void refresh(String foodName, String foodDetail, int position){
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);

        getDetails(position);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.food_RecyclerView_Detail);
        DetailAdapter detailAdapter = new DetailAdapter(detailList);
        recyclerView.setAdapter(detailAdapter);

        TextView foodNameText = (TextView) view.findViewById(R.id.food_Name_Content);
        TextView foodDetailText = (TextView) view.findViewById(R.id.food_Detail_Content);
        foodNameText.setText(foodName);
        foodDetailText.setText(foodDetail);
    }

    private void getDetails(int index){
        detailList.clear();
        switch (index){
            case 0:
                Detail beef_stew_with_potatoes_min = new Detail(R.drawable.beef_stew_with_potatoes_min);
                detailList.add(beef_stew_with_potatoes_min);
                Detail beef_stew_with_potatoes = new Detail(R.drawable.beef_stew_with_potatoes);
                detailList.add(beef_stew_with_potatoes);
                break;
            case 1:
                Detail casserole_min = new Detail(R.drawable.casserole_min);
                detailList.add(casserole_min);
                Detail casserole = new Detail(R.drawable.casserole);
                detailList.add(casserole);
                break;
            case 2:
                Detail dumplings_min = new Detail(R.drawable.dumplings_min);
                detailList.add(dumplings_min);
                Detail dumplings = new Detail(R.drawable.dumplings);
                detailList.add(dumplings);
                break;
            case 3:
                Detail gold_shrimp_min = new Detail(R.drawable.gold_shrimp_min);
                detailList.add(gold_shrimp_min);
                Detail gold_shrimp = new Detail(R.drawable.gold_shrimp);
                detailList.add(gold_shrimp);
                break;
            case 4:
                Detail laba_rice_porridge_min = new Detail(R.drawable.laba_rice_porridge_min);
                detailList.add(laba_rice_porridge_min);
                Detail laba_rice_porridge = new Detail(R.drawable.laba_rice_porridge);
                detailList.add(laba_rice_porridge);
                break;
            case 5:
                Detail moon_cake_min = new Detail(R.drawable.moon_cake_min);
                detailList.add(moon_cake_min);
                Detail moon_cake = new Detail(R.drawable.moon_cake);
                detailList.add(moon_cake);
                break;
            case 6:
                Detail mutton_hotpot_min = new Detail(R.drawable.mutton_hotpot_min);
                detailList.add(mutton_hotpot_min);
                Detail mutton_hotpot = new Detail(R.drawable.mutton_hotpot);
                detailList.add(mutton_hotpot);
                break;
            case 7:
                Detail mutton_soup_min = new Detail(R.drawable.mutton_soup_min);
                detailList.add(mutton_soup_min);
                Detail mutton_soup = new Detail(R.drawable.mutton_soup);
                detailList.add(mutton_soup);
                break;
            case 8:
                Detail octopus_balls_min = new Detail(R.drawable.octopus_balls_min);
                detailList.add(octopus_balls_min);
                Detail octopus_balls = new Detail(R.drawable.octopus_balls);
                detailList.add(octopus_balls);
                break;
            case 9:
                Detail rice_balls_min = new Detail(R.drawable.rice_balls_min);
                detailList.add(rice_balls_min);
                Detail rice_balls = new Detail(R.drawable.rice_balls);
                detailList.add(rice_balls);
                break;
            case 10:
                Detail sugar_fried_chestnut_min = new Detail(R.drawable.sugar_fried_chestnut_min);
                detailList.add(sugar_fried_chestnut_min);
                Detail sugar_fried_chestnut = new Detail(R.drawable.sugar_fried_chestnut);
                detailList.add(sugar_fried_chestnut);
                break;
            case 11:
                Detail vegetables_min = new Detail(R.drawable.vegetables_min);
                detailList.add(vegetables_min);
                Detail vegetables = new Detail(R.drawable.vegetables);
                detailList.add(vegetables);
                break;
            case 12:
                Detail zong_zi_min = new Detail(R.drawable.zong_zi_min);
                detailList.add(zong_zi_min);
                Detail zong_zi = new Detail(R.drawable.zong_zi);
                detailList.add(zong_zi);
                break;
        }
    }
}
