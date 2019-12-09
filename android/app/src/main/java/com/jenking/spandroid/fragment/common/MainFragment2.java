package com.jenking.spandroid.fragment.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.jenking.spandroid.R;
import com.jenking.spandroid.models.base.CommodityModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainFragment2 extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    BaseRecyclerAdapter baseRecyclerAdapter;
    List data;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_part2,container,false);
        unbinder = ButterKnife.bind(this,view);
        init();
        return view;
    }

    void init(){
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));

        data = new ArrayList();
        data.add(new CommodityModel());
        data.add(new CommodityModel());
        data.add(new CommodityModel());
        data.add(new CommodityModel());
        data.add(new CommodityModel());
        data.add(new CommodityModel());
        data.add(new CommodityModel());
        data.add(new CommodityModel());
        baseRecyclerAdapter = new BaseRecyclerAdapter<CommodityModel>(this.getActivity(),data,R.layout.fragment2_item) {
            @Override
            protected void convert(BaseViewHolder helper, CommodityModel item) {

            }
        };
        recyclerView.setAdapter(baseRecyclerAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
