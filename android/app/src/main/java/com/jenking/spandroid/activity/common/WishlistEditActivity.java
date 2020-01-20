package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.jenking.spandroid.presenter.WishListPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;
import com.jenking.spandroid.ui.CommonLoading;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class WishlistEditActivity extends BaseActivity {

    private String plate_id;
    private String plate_name;

    private WishListModel model;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.loading)
    CommonLoading loading;

    @BindView(R.id.wish_name)
    EditText wish_name;

    @BindView(R.id.wish_plate)
    TextView wish_plate;

    @BindView(R.id.wish_price)
    EditText wish_price;

    @BindView(R.id.wish_describe)
    EditText wish_describe;


    private WishListPresenter presenter;


    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.wish_plate)
    void wish_plate(){
        Intent intent = new Intent(this,PlateSelectActivity.class);
        startActivityForResult(intent,1200);
    }

    @OnClick(R.id.submit)
    void submit(){
        doSubmit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_edit);

        String modelJson = getIntent().getStringExtra("model");
        if (StringUtil.isNotEmpty(modelJson)){
            model = new Gson().fromJson(modelJson, WishListModel.class);
            title.setText("修改");
            showDetail();
        }else{
            title.setText("发布");
        }

    }

    @Override
    public void initData() {
        super.initData();
        presenter = new WishListPresenter(this);
        presenter.setOnCallBack(new WishListPresenter.OnCallBack() {
            @Override
            public void add(boolean isSuccess, Object object) {
                setLoadingEnable(false);
                if (isSuccess) {
                    if (object != null) {
                        ResultModel resultModel = (ResultModel) object;
                        if (resultModel != null && StringUtil.isEquals(resultModel.getCode(), "200")) {
                            Toast.makeText(WishlistEditActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }

            @Override
            public void update(boolean isSuccess, Object object) {
                setLoadingEnable(false);
                if (isSuccess) {
                    if (object != null) {
                        ResultModel resultModel = (ResultModel) object;
                        if (resultModel != null && StringUtil.isEquals(resultModel.getCode(), "200")) {
                            Toast.makeText(WishlistEditActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }

            }

            @Override
            public void delete(boolean isSuccess, Object object) {

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

    void doSubmit(){
        if (!AccountTool.isLogin(this)){
            CommonTipsDialog.show(this,"提示","请登录后重试",true);
            finish();
        }
        String wish_name_text =  wish_name.getText().toString();
        String wish_price_text =  wish_price.getText().toString();
        String wish_describe_text =  wish_describe.getText().toString();

        if (StringUtil.isNotEmpty(wish_name_text)
                && StringUtil.isNotEmpty(wish_price_text)
                && StringUtil.isNotEmpty(wish_describe_text)
                && StringUtil.isNotEmpty(plate_id)){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("plate_id",plate_id);
            params.put("plate_name",plate_name);
            params.put("wish_name",wish_name_text);
            params.put("wish_price",wish_price_text);
            params.put("wish_describe",wish_describe_text);
            params.put("wish_provider",AccountTool.getLoginUser(this).getId()+"");
            params.put("wish_provider_avatar",AccountTool.getLoginUser(this).getAvatar()+"");
            params.put("wish_provider_username",AccountTool.getLoginUser(this).getUsername()+"");
            if (model==null){
                presenter.addWishList(params);
            }else{
                params.put("id",model.getId());
                presenter.updateWishList(params);
            }

        }else{
            setLoadingEnable(false);
            CommonTipsDialog.show(this,"提示","请完善交易品信息",true);
        }
    }

    void showDetail(){
        plate_id = model.getPlate_id();
        plate_name = model.getPlate_name();

        wish_name.setText(model.getWish_name());
        wish_plate.setText(model.getPlate_name());
        wish_price.setText(model.getWish_price());
        wish_describe.setText(model.getWish_describe());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i("wish_plate",resultCode+"");
        if (resultCode == 1200){
            if (StringUtil.isNotEmpty(data.getStringExtra("plate_id")+"")){
                plate_id = data.getStringExtra("plate_id");
                plate_name = data.getStringExtra("plate_name");
                wish_plate.setText(plate_name);
            }
        }
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
