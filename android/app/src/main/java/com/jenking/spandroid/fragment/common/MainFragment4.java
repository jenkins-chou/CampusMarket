package com.jenking.spandroid.fragment.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SegmentTabLayout;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.WishlistActivity;
import com.jenking.spandroid.api.BaseAPI;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.fragment.transaction.PurchaseFragment;
import com.jenking.spandroid.fragment.transaction.SellFragment;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.WishListModel;
import com.jenking.spandroid.presenter.WishListPresenter;
import com.jenking.spandroid.tools.StringUtil;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainFragment4 extends Fragment {

    private Context context;
    private Unbinder unbinder;

    private String[] mTitles = { "我出售的","我购买的"};

    @BindView(R.id.segmentTabLayout)
    SegmentTabLayout segmentTabLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_part4,container,false);
        unbinder = ButterKnife.bind(this,view);
        initData();
        return view;
    }

    public void initData() {
        context = this.getActivity();
        segmentTabLayout.setTabData(mTitles);
        segmentTabLayout.setTabData(mTitles,this.getActivity(),R.id.frameLayout,getFragments());
    }

    public ArrayList<Fragment> getFragments(){
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new SellFragment());
        list.add(new PurchaseFragment());
        return list;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
