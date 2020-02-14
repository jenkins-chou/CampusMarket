package com.jenking.spandroid.activity.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.BaseAPI;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonInputDialog;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.OrderModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.business.account.OrderStateModel;
import com.jenking.spandroid.presenter.OrderPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity {

    private OrderModel orderModel;

    @BindView(R.id.commodity_img)
    ImageView commodity_img;
    @BindView(R.id.commodity_name)
    TextView commodity_name;
    @BindView(R.id.commodity_describe)
    TextView commodity_describe;
    @BindView(R.id.create_time)
    TextView create_time;
    @BindView(R.id.order_state)
    TextView order_state;
    @BindView(R.id.add_state)
    TextView add_state;
    @BindView(R.id.confirm)
    Button confirm;

    private String operate_type;

    private OrderPresenter orderPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    BaseRecyclerAdapter baseRecyclerAdapter;
    List data;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.add_state)
    void add_state(){
        addState();
    }

    @OnClick(R.id.confirm)
    void confirm(){
        confirmOrder();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
    }

    @Override
    public void initData() {
        super.initData();
        operate_type = getIntent().getStringExtra("operate_type");
        String modelJson = getIntent().getStringExtra("model");

        if (!TextUtils.isEmpty(modelJson)){
            orderModel = new Gson().fromJson(modelJson,OrderModel.class);
            if (orderModel != null){
                showOrderDetail();

            }
        }

        orderPresenter = new OrderPresenter(this);
        orderPresenter.setOnCallBack(new OrderPresenter.OnCallBack() {
            @Override
            public void getMineSellList(boolean isSuccess, Object object) {

            }

            @Override
            public void getMinePurchaseList(boolean isSuccess, Object object) {

            }

            @Override
            public void getOrderStateList(boolean isSuccess, Object object) {
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                            if (resultModel.getList()!=null && resultModel.getList().size()>0){
                                Log.e("platePresenter",resultModel.getList().toString());
                                List<OrderStateModel> list = resultModel.getList();
                                data = list;
                                baseRecyclerAdapter.setData(data);
                                baseRecyclerAdapter.notifyDataSetChanged();

                                // Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                                //AccountTool.saveUser(context,userModel);
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
            public void updateOrder(boolean isSuccess, Object object) {
                if (isSuccess) {
                    if (object != null) {
                        ResultModel resultModel = (ResultModel) object;
                        if (resultModel != null && StringUtil.isEquals(resultModel.getCode(), "200")) {
                            Toast.makeText(OrderDetailActivity.this, "确认成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }

            @Override
            public void deleteOrderState(boolean isSuccess, Object object) {
                if (isSuccess) {
                    if (object != null) {
                        ResultModel resultModel = (ResultModel) object;
                        if (resultModel != null && StringUtil.isEquals(resultModel.getCode(), "200")) {
                            Toast.makeText(OrderDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            OrderDetailActivity.this.getOrderStateList();
                        }
                    }
                }
            }

            @Override
            public void addOrderState(boolean isSuccess, Object object) {
                if (isSuccess) {
                    if (object != null) {
                        ResultModel resultModel = (ResultModel) object;
                        if (resultModel != null && StringUtil.isEquals(resultModel.getCode(), "200")) {
                            Toast.makeText(OrderDetailActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            OrderDetailActivity.this.getOrderStateList();
                        }
                    }
                }
            }
        });

        data = new ArrayList();
        baseRecyclerAdapter = new BaseRecyclerAdapter<OrderStateModel>(this,data,R.layout.layout_order_state_item) {
            @Override
            protected void convert(BaseViewHolder helper, final OrderStateModel item) {
                helper.setText(R.id.state,item.getState());
                helper.setText(R.id.create_time,"更新时间:"+StringUtil.getStrTime(item.getCreate_time()+""));

                final ImageView deleteIcon = helper.getView(R.id.item_delete);
                if (isSell()){
                    deleteIcon.setVisibility(View.VISIBLE);
                }else{
                    deleteIcon.setVisibility(View.GONE);
                }

                deleteIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteState(item.getId());
                    }
                });
            }
        };

        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(baseRecyclerAdapter);

        getOrderStateList();

        showView();

    }

    void showOrderDetail(){

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.mipmap.commondity_default);
        Glide.with(this).load(BaseAPI.base_url+orderModel.getCommodity_img()).apply(requestOptions).into(commodity_img);

        commodity_name.setText(orderModel.getCommodity_name());
        commodity_describe.setText(orderModel.getCommodity_describe());
        create_time.setText(StringUtil.getStrTime(orderModel.getCreate_time()+""));

        order_state.setText(getStatusTitle(orderModel.getStatus()));

    }

    void addState(){
        CommonInputDialog.create(this, "添加", "请输入状态", false, new CommonInputDialog.OnClickListener() {
            @Override
            public void cancel() {

            }
            @Override
            public void getInput(String inputStr) {
                if (!TextUtils.isEmpty(inputStr)){
                    Map<String,String> params = RS.getBaseParams(OrderDetailActivity.this);
                    params.put("order_id",orderModel.getId());
                    params.put("state",inputStr);
                    orderPresenter.addOrderState(params);
                }else{
                    Toast.makeText(OrderDetailActivity.this, "请输入内容 ", Toast.LENGTH_SHORT).show();
                }
            }
        }).show();

    }

    void deleteState(final String state_id){
        CommonTipsDialog commonTipsDialog = CommonTipsDialog.create(this,"提示","确认要删除吗?",false);
        commonTipsDialog.setOnClickListener(new CommonTipsDialog.OnClickListener() {
            @Override
            public void cancel() {

            }

            @Override
            public void confirm() {
                Map<String,String> params = RS.getBaseParams(OrderDetailActivity.this);
                params.put("id",state_id);
                orderPresenter.deleteOrderState(params);
            }
        });

        commonTipsDialog.show();
    }
    void showView(){
        if (isSell()){
            add_state.setVisibility(View.VISIBLE);
            confirm.setEnabled(false);
            confirm.setText("对方未确认收货");
        }else{
            add_state.setVisibility(View.GONE);

            if (orderModel != null && TextUtils.equals(orderModel.getStatus(),"complete")){
                confirm.setEnabled(false);
                confirm.setText("已确认收货");
            }else{
                confirm.setEnabled(true);
                confirm.setText("确认收货");
            }

        }
    }

    void getOrderStateList(){
        if (orderModel != null){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("order_id",orderModel.getId());
            orderPresenter.getOrderStateList(params);
        } else {
            Toast.makeText(this, "数据初始化失败，请重新进入", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    void confirmOrder(){
        CommonTipsDialog commonTipsDialog = CommonTipsDialog.create(this,"提示","确认收到交易品了吗?",false);
        commonTipsDialog.setOnClickListener(new CommonTipsDialog.OnClickListener() {
            @Override
            public void cancel() {

            }

            @Override
            public void confirm() {
                Map<String,String> params = RS.getBaseParams(OrderDetailActivity.this);
                params.put("id",orderModel.getId());
                params.put("status","complete");
                orderPresenter.updateOrder(params);
            }
        });

        commonTipsDialog.show();
    }

    public boolean isSell(){
        return TextUtils.equals(operate_type,"sell")?true:false;
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
