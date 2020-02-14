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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.CommodityDetailActivity;
import com.jenking.spandroid.activity.common.CommodityEditActivity;
import com.jenking.spandroid.activity.common.LoginActivity;
import com.jenking.spandroid.activity.common.PlateSelectActivity;
import com.jenking.spandroid.activity.common.SearchCommodityActivity;
import com.jenking.spandroid.api.BaseAPI;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.CommodityModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.presenter.CommodityPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainFragment1 extends Fragment {

    private String plate_id;
    private String plate_name;

    private Context context;
    private Unbinder unbinder;

    @BindView(R.id.wish_plate)
    TextView commodity_plate;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.login_name)
    TextView login_name;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    BaseRecyclerAdapter baseRecyclerAdapter;
    List data;

    private CommodityPresenter commodityPresenter;


    @OnClick(R.id.login_name)
    void login_name(){
        if (!AccountTool.isLogin(getContext())){
            Intent intent = new Intent(getContext(),LoginActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.select_plate)
    void select_plate(){
        Intent intent = new Intent(getContext(), PlateSelectActivity.class);
        startActivityForResult(intent,1200);
    }

    @OnClick(R.id.search)
    void search(){
        Intent intent = new Intent(getContext(), SearchCommodityActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.add)
    void add(){
        if (!AccountTool.isLogin(getContext())){
            Intent intent = new Intent(getContext(),LoginActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getContext(), CommodityEditActivity.class);
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
        context = this.getContext();

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                commodityPresenter.getAll(RS.getBaseParams(context));
            }
        });
        smartRefreshLayout.setRefreshHeader(new PhoenixHeader(context));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        data = new ArrayList();
        baseRecyclerAdapter = new BaseRecyclerAdapter<CommodityModel>(this.getActivity(),data,R.layout.fragment1_item) {
            @Override
            protected void convert(BaseViewHolder helper, CommodityModel item) {
                ImageView commodity_img = helper.getView(R.id.commodity_img);
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.error(R.mipmap.commondity_default);
                Glide.with(context).load(BaseAPI.base_url+item.getCommodity_img()).apply(requestOptions).into(commodity_img);

                helper.setText(R.id.wish_name,item.getCommodity_name());
                helper.setText(R.id.wish_describe,item.getCommodity_describe());
                helper.setText(R.id.commodity_create_time,"创建时间:"+StringUtil.getStrTime(item.getCreate_time()));
                helper.setText(R.id.wish_price,"¥"+item.getCommodity_price());
            }
        };
        baseRecyclerAdapter.openLoadAnimation(false);
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (data.get(position) != null){
                    Intent intent = new Intent(context, CommodityDetailActivity.class);
                    intent.putExtra("model",new Gson().toJson(data.get(position)));
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(baseRecyclerAdapter);

        commodityPresenter = new CommodityPresenter(context);
        commodityPresenter.setOnCallBack(new CommodityPresenter.OnCallBack() {
            @Override
            public void add(boolean isSuccess, Object object) {

            }

            @Override
            public void update(boolean isSuccess, Object object) {

            }

            @Override
            public void delete(boolean isSuccess, Object object) {

            }

            @Override
            public void getAll(boolean isSuccess, Object object) {
                smartRefreshLayout.finishRefresh();
                if (isSuccess) {
                    if (object != null) {
                        ResultModel resultModel = (ResultModel) object;
                        if (resultModel != null && StringUtil.isEquals(resultModel.getCode(), "200")) {
                            if (resultModel.getList() != null && resultModel.getList().size() > 0) {
                                Log.e("platePresenter", resultModel.getList().toString());
                                List<CommodityModel> list = resultModel.getList();
                                data = list;
                                baseRecyclerAdapter.setData(data);
                                baseRecyclerAdapter.notifyDataSetChanged();

                                // Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                                //AccountTool.saveUser(context,userModel);
                            } else {
                                // Toast.makeText(context, "账号不存在或登录信息有误", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void searchAllMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllByPlateMobile(boolean isSuccess, Object object) {
                smartRefreshLayout.finishRefresh();
                if (isSuccess) {
                    if (object != null) {
                        ResultModel resultModel = (ResultModel) object;
                        if (resultModel != null && StringUtil.isEquals(resultModel.getCode(), "200")) {
                            if (resultModel.getList() != null && resultModel.getList().size() > 0) {
                                Log.e("platePresenter", resultModel.getList().toString());
                                List<CommodityModel> list = resultModel.getList();
                                data = list;
                                baseRecyclerAdapter.setData(data);
                                baseRecyclerAdapter.notifyDataSetChanged();

                                // Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                                //AccountTool.saveUser(context,userModel);
                            } else {
                                // Toast.makeText(context, "账号不存在或登录信息有误", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void getAllByProvider(boolean isSuccess, Object object) {

            }

            @Override
            public void purchaseCommodity(boolean isSuccess, Object object) {

            }

            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void failed(Object object) {

            }
        });

        commodityPresenter.getAll(RS.getBaseParams(context));

    }

    @Override
    public void onResume() {
        super.onResume();

        if (AccountTool.isLogin(getContext())){

            String username = "";
            UserModel userModel = AccountTool.getLoginUser(getContext());
            if (userModel!=null){
                username = AccountTool.getLoginUser(getContext()).getUsername();
                login_name.setText("欢迎"+username+"");
            }
        }else{
            login_name.setText("请登录");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 1200:
                if (StringUtil.isNotEmpty(data.getStringExtra("plate_id")+"")){
                    plate_id = data.getStringExtra("plate_id");
                    plate_name = data.getStringExtra("plate_name");
                    commodity_plate.setText(plate_name);

                    Map<String,String> params = RS.getBaseParams(context);
                    params.put("plate_id",plate_id);
                    commodityPresenter.getAllByPlateMobile(params);
                }
                break;
        }
    }
}
