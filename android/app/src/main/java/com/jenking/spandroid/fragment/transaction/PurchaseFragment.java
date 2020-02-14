package com.jenking.spandroid.fragment.transaction;

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
import com.bumptech.glide.request.RequestOptions;
import com.flyco.tablayout.SegmentTabLayout;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.OrderDetailActivity;
import com.jenking.spandroid.api.BaseAPI;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.OrderModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.WishListModel;
import com.jenking.spandroid.presenter.OrderPresenter;
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
import butterknife.Unbinder;

public class PurchaseFragment extends Fragment {
    private Context context;
    private Unbinder unbinder;

    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    BaseRecyclerAdapter baseRecyclerAdapter;
    List data;

    private OrderPresenter orderPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_purchase,container,false);
        unbinder = ButterKnife.bind(this,view);
        context = this.getActivity();
        init();
        return view;
    }

    void init(){
        context = this.getActivity();

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                orderPresenter.getMinePurchaseList(RS.getBaseParams(context));
            }
        });
        smartRefreshLayout.setRefreshHeader(new PhoenixHeader(context));

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));

        data = new ArrayList();
        baseRecyclerAdapter = new BaseRecyclerAdapter<OrderModel>(this.getActivity(),data,R.layout.layout_order_item) {
            @Override
            protected void convert(BaseViewHolder helper, OrderModel item) {
                helper.setText(R.id.commodity_name,item.getCommodity_name());
                helper.setText(R.id.commodity_describe,item.getCommodity_describe());
                helper.setText(R.id.create_time, StringUtil.getStrTime(item.getCreate_time()+""));

                helper.setText(R.id.order_state,getStatusTitle(item.getStatus()));

                ImageView imageView = helper.getView(R.id.commodity_img);
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.error(R.mipmap.commondity_default);
                Glide.with(context).load(BaseAPI.base_url+item.getCommodity_img()).apply(requestOptions).into(imageView);
            }
        };
        baseRecyclerAdapter.openLoadAnimation(false);
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (data.get(position) != null){
                    Intent intent = new Intent(context, OrderDetailActivity.class);
                    intent.putExtra("operate_type","purchase");
                    intent.putExtra("model",new Gson().toJson(data.get(position)));
                    context.startActivity(intent);
                }

            }
        });
        recyclerView.setAdapter(baseRecyclerAdapter);

        orderPresenter = new OrderPresenter(this.getContext());
        orderPresenter.setOnCallBack(new OrderPresenter.OnCallBack() {
            @Override
            public void getMineSellList(boolean isSuccess, Object object) {

            }

            @Override
            public void getMinePurchaseList(boolean isSuccess, Object object) {
                smartRefreshLayout.finishRefresh();
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                            if (resultModel.getList()!=null && resultModel.getList().size()>0){
                                Log.e("platePresenter",resultModel.getList().toString());
                                List<OrderModel> list = resultModel.getList();
                                data = list;
                                baseRecyclerAdapter.setData(data);
                                baseRecyclerAdapter.notifyDataSetChanged();
                            }else{

                            }
                        }else{
                            data = new ArrayList();
                            baseRecyclerAdapter.setData(data);
                            baseRecyclerAdapter.notifyDataSetChanged();

                        }
                    }
                }
            }

            @Override
            public void getOrderStateList(boolean isSuccess, Object object) {

            }

            @Override
            public void updateOrder(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteOrderState(boolean isSuccess, Object object) {

            }

            @Override
            public void addOrderState(boolean isSuccess, Object object) {

            }
        });
        orderPresenter.getMinePurchaseList(RS.getBaseParams(context));
    }

    public String getStatusTitle(String status){
        switch(status){
            case "create":
                return "未完成";
            case "complete":
                return "已完成";
            default:
                return "未知状态";
        }
    }
}
