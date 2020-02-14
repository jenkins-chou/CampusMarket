package com.jenking.spandroid.activity.common;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.ShippingAddressModel;
import com.jenking.spandroid.presenter.ShippingAddressPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;
import com.jenking.spandroid.ui.CommonLoading;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MineShippingAddressEditActivity extends BaseActivity{

    private ShippingAddressModel model;
    private ShippingAddressPresenter presenter;

    @BindView(R.id.title)
    TextView title;


    @BindView(R.id.province)
    EditText province;

    @BindView(R.id.city)
    TextView city;

    @BindView(R.id.address)
    EditText address;

    @BindView(R.id.loading)
    CommonLoading loading;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.submit)
    void submit(){
        doSubmit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_shippint_address_edit);

        String modelJson = getIntent().getStringExtra("model");
        if (StringUtil.isNotEmpty(modelJson)){
            model = new Gson().fromJson(modelJson, ShippingAddressModel.class);
            title.setText("修改");
            showCommodity();
        }else{
            title.setText(" 新增地址");
        }
    }

    @Override
    public void initData() {
        super.initData();
        presenter = new ShippingAddressPresenter(this);
        presenter.setOnCallBack(new ShippingAddressPresenter.OnCallBack() {

            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void failed(Object object) {

            }

            @Override
            public void addShippingAddress(boolean isSuccess, Object object) {
                setLoadingEnable(false);
                if (isSuccess) {
                    if (object != null) {
                        ResultModel resultModel = (ResultModel) object;
                        if (resultModel != null && StringUtil.isEquals(resultModel.getCode(), "200")) {
                            Toast.makeText(MineShippingAddressEditActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }

            @Override
            public void deleteShippingAddress(boolean isSuccess, Object object) {

            }

            @Override
            public void setDefaultShippingAddress(boolean isSuccess, Object object) {

            }

            @Override
            public void getShippingAddressByUserId(boolean isSuccess, Object object) {

            }
        });
    }

    void showCommodity(){
        province.setText(model.getProvince());
        city.setText(model.getCity());
        address.setText(model.getAddress());
    }

    void doSubmit(){
        if (!AccountTool.isLogin(this)){
            CommonTipsDialog.show(this,"提示","请登录后重试",true);
            finish();
        }
        setLoadingEnable(true);
        String provinceStr =  province.getText().toString();
        String cityStr =  city.getText().toString();
        String addressStr =  address.getText().toString();

        if (StringUtil.isNotEmpty(provinceStr)
                && StringUtil.isNotEmpty(cityStr)
                && StringUtil.isNotEmpty(addressStr)){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("province",provinceStr);
            params.put("city",cityStr);
            params.put("address",addressStr);
            if (model==null){
                presenter.addShippingAddress(params);
            }else{
//                params.put("id",model.getId());
//                presenter.(params);
            }
        }else{
            setLoadingEnable(false);
            CommonTipsDialog.show(this,"提示","请完善信息",true);
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
