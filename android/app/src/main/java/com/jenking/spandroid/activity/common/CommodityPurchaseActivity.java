package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.BaseAPI;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonInputDialog;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.CommodityModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.presenter.CommodityPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;
import com.jenking.spandroid.ui.CommonLoading;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CommodityPurchaseActivity extends BaseActivity {

    private CommodityModel commodityModel;
    private String commodity_provider;
    private String commodity_provider_username;

    private String shipping_address_id;
    private String shipping_address_detail;

    private CommodityPresenter commodityPresenter;

    @BindView(R.id.commodity_img)
    ImageView commodity_img;

    @BindView(R.id.commodity_name)
    TextView commodity_name;
    @BindView(R.id.commodity_describe)
    TextView commodity_describe;
    @BindView(R.id.commodity_create_time)
    TextView commodity_create_time;
    @BindView(R.id.commodity_provider)
    TextView commodity_provider_text;
    @BindView(R.id.shipping_address)
    TextView shipping_address;
    @BindView(R.id.price)
    TextView price;

    @BindView(R.id.loading)
    CommonLoading loading;


    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.shipping_address_bar)
    void shipping_address_bar(){
        Intent intent = new Intent(this,MineShippingAddressSelectActivity.class);
        startActivityForResult(intent,2200);
    }

    @OnClick(R.id.purchase)
    void purchase(){
        if (shipping_address_id == null || shipping_address_id.equals("")){
            Toast.makeText(this, "请选择收货地址", Toast.LENGTH_SHORT).show();
            return;
        }

        if (commodityModel != null){
            if (commodityModel.getCommodity_provider().equals(AccountTool.getLoginUser(this).getId())){
                Toast.makeText(this, "不能购买自己出售的交易品", Toast.LENGTH_SHORT).show();
            }else{
                showInputPayPwd();
            }
        }else{
            Toast.makeText(this, "页面加载失败，请重新进入", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_purchase);

        String modelJson = getIntent().getStringExtra("model");
        commodity_provider = getIntent().getStringExtra("commodity_provider");
        commodity_provider_username = getIntent().getStringExtra("commodity_provider_username");
        if (StringUtil.isNotEmpty(modelJson)){
            commodityModel = new Gson().fromJson(modelJson,CommodityModel.class);
            if (commodityModel != null){
                showCommodity();
            }
        }
    }

    @Override
    public void initData() {
        super.initData();
        commodityPresenter = new CommodityPresenter(this);
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

            }

            @Override
            public void searchAllMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllByPlateMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllByProvider(boolean isSuccess, Object object) {

            }

            @Override
            public void purchaseCommodity(boolean isSuccess, Object object) {
                setLoadingEnable(false);
                if (isSuccess) {
                    if (object != null) {
                        ResultModel resultModel = (ResultModel) object;
                        if (resultModel != null && StringUtil.isEquals(resultModel.getCode(), "200")) {
                            Toast.makeText(CommodityPurchaseActivity.this, "购买成功，请刷新数据", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            CommonTipsDialog.show(CommodityPurchaseActivity.this,"温馨提示",resultModel.getMessage(),false);
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

    void showCommodity(){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.mipmap.commondity_default);
        Glide.with(this).load(BaseAPI.base_url+commodityModel.getCommodity_img()).apply(requestOptions).into(commodity_img);
        commodity_name.setText(commodityModel.getCommodity_name());
        commodity_describe.setText(commodityModel.getCommodity_describe());
        commodity_create_time.setText(commodityModel.getCreate_time());
        commodity_provider_text.setText("发布者："+commodity_provider_username);

        price.setText("总价:"+commodityModel.getCommodity_price()+"元");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(resultCode){
            case 2200:
                shipping_address_id = data.getStringExtra("id");
                shipping_address_detail = data.getStringExtra("province") + data.getStringExtra("city") + data.getStringExtra("address");
                shipping_address.setText(shipping_address_detail);
                break;
        }
    }

    public void showInputPayPwd(){
        //开始校验支付密码
        final UserModel userModel = AccountTool.getLoginUser(this);
        if (userModel != null){
            Log.e("UserModel",userModel.toString());
            if (TextUtils.isEmpty(userModel.getPay_pwd()) ||
                    TextUtils.equals(userModel.getPay_pwd(),"null")||
                    TextUtils.equals(userModel.getPay_pwd(),"NULL")){
                CommonTipsDialog.show(CommodityPurchaseActivity.this,"温馨提示","您还没有设置支付密码,请点进入我的钱包进行设置",false);
            }else{
                CommonInputDialog.create(CommodityPurchaseActivity.this, "支付密码", "请输入支付密码", false, new CommonInputDialog.OnClickListener() {
                    @Override
                    public void cancel() {

                    }
                    @Override
                    public void getInput(String inputStr) {
                        if (userModel != null && TextUtils.equals(userModel.getPay_pwd(),inputStr)){
                            //开始购买
                            purchaseSave();
                        }else{
                            Toast.makeText(CommodityPurchaseActivity.this, "验证失败,请重试", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();
            }
        }else{
            Toast.makeText(CommodityPurchaseActivity.this, "数据出错，请重新登录", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void purchaseSave(){
        setLoadingEnable(true);
        Map<String,String> params = new HashMap<>();
        params.put("party_a", commodityModel.getCommodity_provider());
        params.put("party_b", AccountTool.getLoginUser(this).getId());
        params.put("commodity_id", commodityModel.getId());
        params.put("status", "create");
        params.put("money", commodityModel.getCommodity_price());
        params.put("shipping_address_id", shipping_address_id);
        params.put("shipping_address_detail", shipping_address_detail);
        commodityPresenter.purchaseCommodity(params);
    }

    void setLoadingEnable(boolean enable){
        if (loading==null)return;
        if (enable){
            loading.setVisibility(View.VISIBLE);
        }else{
            loading.setVisibility(View.GONE);
        }
    }
}
