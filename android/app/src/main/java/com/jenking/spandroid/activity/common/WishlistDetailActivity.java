package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import com.jenking.spandroid.models.base.WishListModel;
import com.jenking.spandroid.presenter.CommodityPresenter;
import com.jenking.spandroid.presenter.WishListPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class WishlistDetailActivity extends BaseActivity{

    @BindView(R.id.footer_edit)
    LinearLayout footer_edit;
    @BindView(R.id.footer_normal)
    LinearLayout footer_normal;


    @BindView(R.id.wish_name)
    TextView wish_name;

    @BindView(R.id.wish_provider)
    TextView wish_provider;

    @BindView(R.id.wish_price)
    TextView wish_price;

    @BindView(R.id.wish_plate)
    TextView wish_plate;

    @BindView(R.id.wish_describe)
    TextView wish_describe;


    @OnClick(R.id.back)
    void back(){
        finish();
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
                    Map<String, String> params = RS.getBaseParams(WishlistDetailActivity.this);
                    params.put("id", model.getId());
                    presenter.deleteWishList(params);
                }
            }
        });
        dialog.show();
    }

    @OnClick(R.id.edit)
    void edit(){
        if (AccountTool.isLogin(this) && model !=null){
            Intent intent = new Intent(this, WishlistEditActivity.class);
            intent.putExtra("model",new Gson().toJson(model));
            startActivity(intent);
        }
    }

    private WishListModel model;
    private WishListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_detail);

        String modelJson = getIntent().getStringExtra("model");
        if (StringUtil.isNotEmpty(modelJson)){
            model = new Gson().fromJson(modelJson, WishListModel.class);
            showCommodity();
        }

        initFooter();
    }

    @Override
    public void initData() {
        super.initData();

        presenter = new WishListPresenter(this);
        presenter.setOnCallBack(new WishListPresenter.OnCallBack() {
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
                            Toast.makeText(WishlistDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }

            @Override
            public void getAllMobile(boolean isSuccess, Object object) {

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
    }

    void showCommodity(){
        wish_provider.setText(model.getWish_provider_username());
        wish_name.setText(model.getWish_name());
        wish_price.setText("¥"+model.getWish_price());
        wish_plate.setText(model.getPlate_name());
        wish_describe.setText(model.getWish_describe());
    }
    void initFooter(){

        footer_edit.setVisibility(View.GONE);
        footer_normal.setVisibility(View.GONE);

        if (getIntent().getBooleanExtra("canEdit",false) == true){
            footer_edit.setVisibility(View.VISIBLE);
        }else{
            footer_normal.setVisibility(View.VISIBLE);
        }
    }
}
