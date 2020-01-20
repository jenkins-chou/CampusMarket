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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.LoginActivity;
import com.jenking.spandroid.activity.common.WishlistActivity;
import com.jenking.spandroid.api.BaseAPI;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.CommodityModel;
import com.jenking.spandroid.models.base.PlateModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.models.base.WishListModel;
import com.jenking.spandroid.presenter.PlatePresenter;
import com.jenking.spandroid.presenter.WishListPresenter;
import com.jenking.spandroid.tools.AccountTool;
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

public class MainFragment2 extends Fragment {

    private Context context;
    private Unbinder unbinder;

    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    BaseRecyclerAdapter baseRecyclerAdapter;
    List data;
    private WishListPresenter wishListPresenter;

    @OnClick(R.id.mine_wishlist)
    void mine_wishlist(){
        Intent intent = new Intent(this.getContext(), WishlistActivity.class);
        startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_part2,container,false);
        unbinder = ButterKnife.bind(this,view);
        init();
        return view;
    }

    void init(){
        context = this.getContext();

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                wishListPresenter.getAllMobile(RS.getBaseParams(context));
            }
        });
        smartRefreshLayout.setRefreshHeader(new PhoenixHeader(context));

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));

        data = new ArrayList();
        baseRecyclerAdapter = new BaseRecyclerAdapter<WishListModel>(this.getActivity(),data,R.layout.activity_wishlist_item) {
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
        recyclerView.setAdapter(baseRecyclerAdapter);

        wishListPresenter = new WishListPresenter(this.getContext());
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
                smartRefreshLayout.finishRefresh();
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
            public void getAllByProviderMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void failed(Object object) {

            }
        });

        wishListPresenter.getAllMobile(RS.getBaseParams(context));

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
