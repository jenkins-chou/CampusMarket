package com.jenking.spandroid.fragment.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.LoginActivity;
import com.jenking.spandroid.models.base.CommodityModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.tools.AccountTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainFragment1 extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.login_name)
    TextView login_name;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    BaseRecyclerAdapter baseRecyclerAdapter;
    List data;


    @OnClick(R.id.login_name)
    void login_name(){
        if (!AccountTool.isLogin(getContext())){
            Intent intent = new Intent(getContext(),LoginActivity.class);
            startActivity(intent);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_part1,container,false);
        unbinder = ButterKnife.bind(this,view);
        init();
        return view;
    }

    void init(){
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));

        data = new ArrayList();
        data.add(new CommodityModel());
        data.add(new CommodityModel());
        data.add(new CommodityModel());
        data.add(new CommodityModel());
        data.add(new CommodityModel());
        data.add(new CommodityModel());
        data.add(new CommodityModel());
        data.add(new CommodityModel());
        baseRecyclerAdapter = new BaseRecyclerAdapter<CommodityModel>(this.getActivity(),data,R.layout.fragment1_item) {
            @Override
            protected void convert(BaseViewHolder helper, CommodityModel item) {

            }
        };
        recyclerView.setAdapter(baseRecyclerAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AccountTool.isLogin(getContext())){

            String username = "";
            UserModel userModel = AccountTool.getLoginUser(getContext());
            if (userModel!=null){
                username = AccountTool.getLoginUser(getContext()).getName();
                login_name.setText("欢迎"+username+"");
            }
        }else{
            login_name.setText("请登录");
        }
    }
}
