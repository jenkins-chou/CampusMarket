package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.BaseAPI;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.CommodityModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.CollectPresenter;
import com.jenking.spandroid.presenter.CommodityPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CommodityDetailActivity extends BaseActivity {

    @BindView(R.id.footer_edit)
    LinearLayout footer_edit;
    @BindView(R.id.footer_normal)
    LinearLayout footer_normal;

    @BindView(R.id.collect)
    TextView collect;

    @BindView(R.id.commodity_name)
    TextView commodity_name;
    @BindView(R.id.commodity_price)
    TextView commodity_price;
    @BindView(R.id.commodity_provider)
    TextView commodity_provider;
    @BindView(R.id.commodity_plate)
    TextView commodity_plate;
    @BindView(R.id.commodity_old_price)
    TextView commodity_old_price;
    @BindView(R.id.commodity_describe)
    TextView commodity_describe;
    @BindView(R.id.commodity_produce_time)
    TextView commodity_produce_time;
    @BindView(R.id.commodity_validity)
    TextView commodity_validity;
    @BindView(R.id.commodity_img)
    ImageView commodity_img;


    private CommodityModel model;
    private CommodityPresenter presenter;
    private CollectPresenter collectPresenter;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.collect)
    void collect(){
        CommonTipsDialog dialog = new CommonTipsDialog(this,"提示","确认要收藏吗",false);
        dialog.setOnClickListener(new CommonTipsDialog.OnClickListener() {
            @Override
            public void cancel() {

            }

            @Override
            public void confirm() {
                if (AccountTool.isLogin(CommodityDetailActivity.this) && model != null) {
                    Map<String, String> params = RS.getBaseParams(CommodityDetailActivity.this);
                    params.put("commodity_id", model.getId());
                    params.put("user_id", AccountTool.getLoginUser(CommodityDetailActivity.this).getId());
                    collectPresenter.collect(params);
                }
            }
        });
        dialog.show();
    }

    @OnClick(R.id.delete)
    void delete(){
        CommonTipsDialog dialog = new CommonTipsDialog(this,"提示","确认要删除吗",false);
        dialog.setOnClickListener(new CommonTipsDialog.OnClickListener() {
            @Override
            public void cancel() {

            }

            @Override
            public void confirm() {
                if (model != null) {
                    Map<String, String> params = RS.getBaseParams(CommodityDetailActivity.this);
                    params.put("id", model.getId());
                    presenter.delete(params);
                }
            }
        });
        dialog.show();
    }

    @OnClick(R.id.edit)
    void edit(){
        if (AccountTool.isLogin(this) && model !=null){
            Intent intent = new Intent(this, CommodityEditActivity.class);
            intent.putExtra("model",new Gson().toJson(model));
            startActivity(intent);
        }
    }

    @OnClick(R.id.purchase)
    void purchase(){
        if (AccountTool.isLogin(this) && model !=null){
            Intent intent = new Intent(this, CommodityPurchaseActivity.class);
            intent.putExtra("model",new Gson().toJson(model));
            intent.putExtra("commodity_provider",model.getCommodity_provider());
            intent.putExtra("commodity_provider_username",model.getCommodity_provider_username());
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_detail);

        initFooter();
        String modelJson = getIntent().getStringExtra("model");
        if (StringUtil.isNotEmpty(modelJson)){
            model = new Gson().fromJson(modelJson,CommodityModel.class);
            showCommodity();
        }

        if (getIntent().getBooleanExtra("canEdit",false) == true){
            footer_edit.setVisibility(View.VISIBLE);
            collect.setVisibility(View.GONE);
        }else{
            footer_normal.setVisibility(View.VISIBLE);
            collect.setVisibility(View.VISIBLE);
        }

        presenter = new CommodityPresenter(this);
        presenter.setOnCallBack(new CommodityPresenter.OnCallBack() {
            @Override
            public void add(boolean isSuccess, Object object) {

            }

            @Override
            public void update(boolean isSuccess, Object object) {

            }

            @Override
            public void delete(boolean isSuccess, Object object) {
                if (isSuccess) {
                    if (object != null) {
                        ResultModel resultModel = (ResultModel) object;
                        if (resultModel != null && StringUtil.isEquals(resultModel.getCode(), "200")) {
                            Toast.makeText(CommodityDetailActivity.this, "删除成功，请刷新数据", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
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

            }

            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void failed(Object object) {

            }
        });

        collectPresenter = new CollectPresenter(this);
        collectPresenter.setOnCallBack(new CollectPresenter.OnCallBack() {
            @Override
            public void collect(boolean isSuccess, Object object) {
                if (isSuccess) {
                    if (object != null) {
                        ResultModel resultModel = (ResultModel) object;
                        if (resultModel != null && StringUtil.isEquals(resultModel.getCode(), "200")) {
                            Toast.makeText(CommodityDetailActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(CommodityDetailActivity.this, "已经收藏过啦", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void getAllCollectByProvider(boolean isSuccess, Object object) {

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
        Glide.with(this).load(BaseAPI.base_url+model.getCommodity_img()).apply(requestOptions).into(commodity_img);
        commodity_name.setText(model.getCommodity_name());
        commodity_price.setText(model.getCommodity_price());
        commodity_provider.setText("卖家："+model.getCommodity_provider_username());
        commodity_plate.setText(model.getPlate_name());
        commodity_old_price.setText("¥ "+model.getCommodity_old_price());
        commodity_describe.setText(model.getCommodity_describe());
        commodity_produce_time.setText(model.getCommodity_produce_time());
        commodity_validity.setText(model.getCommodity_validity());
    }

    void initFooter(){
        footer_edit.setVisibility(View.GONE);
        footer_normal.setVisibility(View.GONE);
    }
}
