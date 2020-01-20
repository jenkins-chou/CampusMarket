package com.jenking.spandroid.activity.common;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.BaseAPI;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.CommodityModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.WishListModel;
import com.jenking.spandroid.presenter.WishListPresenter;
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
import butterknife.OnClick;

public class WishlistActivity extends BaseActivity {

    private Context context;

    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    BaseRecyclerAdapter baseRecyclerAdapter;
    List data;

    private WishListPresenter wishListPresenter;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.edit)
    void edit(){
        Intent intent = new Intent(this,WishlistEditActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
    }

    public void initData(){
        context = this;

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });
        smartRefreshLayout.setRefreshHeader(new PhoenixHeader(context));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        data = new ArrayList();
        baseRecyclerAdapter = new BaseRecyclerAdapter<WishListModel>(this,data,R.layout.activity_wishlist_item) {
            @Override
            protected void convert(BaseViewHolder helper, WishListModel item) {

                helper.setText(R.id.wish_name,item.getWish_name());
                helper.setText(R.id.wish_describe,item.getWish_describe());
                helper.setText(R.id.wish_create_time,StringUtil.getStrTime(item.getCreate_time()+""));

                helper.setText(R.id.wish_provider_username,item.getWish_provider_username());
                helper.setText(R.id.wish_price,"¥"+item.getWish_price());

                ImageView imageView = helper.getView(R.id.wish_provider_avatar);
                Glide.with(context).load(BaseAPI.base_url+item.getWish_provider_avatar()).into(imageView);
            }
        };
        baseRecyclerAdapter.openLoadAnimation(false);
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (data.get(position) != null){
                    Intent intent = new Intent(context, WishlistDetailActivity.class);
                    intent.putExtra("canEdit",true);
                    intent.putExtra("model",new Gson().toJson(data.get(position)));
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(baseRecyclerAdapter);

        wishListPresenter = new WishListPresenter(this);
        wishListPresenter.setOnCallBack(new WishListPresenter.OnCallBack() {
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
            public void getAllMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllByProviderMobile(boolean isSuccess, Object object) {
                smartRefreshLayout.finishRefresh();
                data.clear();
                baseRecyclerAdapter.setData(data);
                baseRecyclerAdapter.notifyDataSetChanged();
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                            if (resultModel.getList()!=null && resultModel.getList().size()>0){
                                Log.e("platePresenter",resultModel.getList().toString());
                                List<WishListModel> list = resultModel.getList();
                                data = list;
                                baseRecyclerAdapter.setData(data);
                                baseRecyclerAdapter.notifyDataSetChanged();

                                // Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                                //AccountTool.saveUser(context,userModel);
                            }else{
                                // Toast.makeText(context, "账号不存在或登录信息有误", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            // Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void failed(Object object) {

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    void getData(){
        if (AccountTool.isLogin(this)){
            Map<String,String> params = RS.getBaseParams(context);
            params.put("user_id",AccountTool.getLoginUser(this).getId());
            wishListPresenter.getAllByProviderMobile(params);
        }
    }
}
