package com.jenking.spandroid.activity.common;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.intent.IntentManager;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.ShippingAddressModel;
import com.jenking.spandroid.presenter.ShippingAddressPresenter;
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

public class MineShippingAddressSelectActivity extends BaseActivity {

    private Context context;

    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    BaseRecyclerAdapter baseRecyclerAdapter;
    List<ShippingAddressModel> data;

    private ShippingAddressPresenter shippingAddressPresenter;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.add)
    void add(){
        IntentManager.intentToMineShippingAddressEdit(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_shipping_address_select);
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
        baseRecyclerAdapter = new BaseRecyclerAdapter<ShippingAddressModel>(this,data,R.layout.activity_shipping_address_select_item) {
            @Override
            protected void convert(BaseViewHolder helper, final ShippingAddressModel item) {

                ImageView is_dafult = helper.getView(R.id.is_dafult);
                if (TextUtils.equals(item.getIs_dafault(),"true")){
                    Glide.with(context).load(R.mipmap.is_default_true).into(is_dafult);
                }else{
                    Glide.with(context).load(R.mipmap.is_default_false).into(is_dafult);
                }
                helper.setText(R.id.province,item.getProvince());
                helper.setText(R.id.city,item.getCity());
                helper.setText(R.id.address,item.getAddress());
                TextView delete = helper.getView(R.id.delete);
//                helper.setText(R.id.wish_price,"¥"+item.getCommodity_price());
            }
        };
        baseRecyclerAdapter.openLoadAnimation(false);
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (data.get(position) != null){
                    Intent intent = new Intent();
                    intent.putExtra("id",data.get(position).getId());
                    intent.putExtra("province",data.get(position).getProvince());
                    intent.putExtra("city",data.get(position).getCity());
                    intent.putExtra("address",data.get(position).getAddress());
                    setResult(2200,intent);
                    finish();
                }
            }
        });
        recyclerView.setAdapter(baseRecyclerAdapter);

        shippingAddressPresenter = new ShippingAddressPresenter(this);
        shippingAddressPresenter.setOnCallBack(new ShippingAddressPresenter.OnCallBack() {
            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void failed(Object object) {

            }

            @Override
            public void addShippingAddress(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteShippingAddress(boolean isSuccess, Object object) {
                if (isSuccess) {
                    if (object != null) {
                        ResultModel resultModel = (ResultModel) object;
                        if (resultModel != null && StringUtil.isEquals(resultModel.getCode(), "200")) {
                            Toast.makeText(context, "、删除成功", Toast.LENGTH_SHORT).show();
                            getData();
                        }

                    }
                }
            }

            @Override
            public void setDefaultShippingAddress(boolean isSuccess, Object object) {

            }

            @Override
            public void getShippingAddressByUserId(boolean isSuccess, Object object) {
                if (isSuccess) {
                    if (object != null) {
                        ResultModel resultModel = (ResultModel) object;
                        if (resultModel != null && StringUtil.isEquals(resultModel.getCode(), "200")) {
                            if (resultModel.getList() != null && resultModel.getList().size() > 0) {
                                Log.e("platePresenter", resultModel.getList().toString());
                                List<ShippingAddressModel> list = resultModel.getList();
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
        });
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
            shippingAddressPresenter.getShippingAddressByUserId(params);
        }
    }

    void deleteAddress(final String id){
        CommonTipsDialog dialog = CommonTipsDialog.create(this,"温馨提示","确认要删除吗",false);
        dialog.setOnClickListener(new CommonTipsDialog.OnClickListener() {
            @Override
            public void cancel() {

            }

            @Override
            public void confirm() {
                Map<String,String> params = RS.getBaseParams(context);
                params.put("id",id);
                shippingAddressPresenter.deleteShippingAddress(params);
            }
        });
        dialog.show();

    }
}
