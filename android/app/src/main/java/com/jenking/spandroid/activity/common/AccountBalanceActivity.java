package com.jenking.spandroid.activity.common;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.base.BaseEmptyActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.intent.IntentManager;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.business.account.BalanceAndCoupon;
import com.jenking.spandroid.presenter.AccountPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.Const;
import com.jenking.spandroid.tools.StringUtil;

import java.text.DecimalFormat;
import java.util.Map;

public class AccountBalanceActivity extends BaseEmptyActivity implements View.OnClickListener {

    //@BindView(R.id.balance)
    TextView balance;
    Button goto_recharge;
    Button goto_bankcard;
    AccountPresenter accountPresenter;

    @Override
    protected int setContentRes() {
        return R.layout.activity_account_balance;
    }

    @Override
    public void initData() {
        super.initData();
        setTitle("");//去除标题
        initPresenter();

        balance = findViewById(R.id.balance);
        goto_recharge = findViewById(R.id.goto_recharge);
        goto_bankcard = findViewById(R.id.goto_bankcard);

        goto_recharge.setOnClickListener(this);
        goto_bankcard.setOnClickListener(this);
    }

    @Override
    public void initView() {
        super.initView();
        setToolbarRightButtonEnable(true);
        setToolbarRightButtonTitle("充值记录");
    }

    @Override
    public void onToolbarRightButtonClick() {
        super.onToolbarRightButtonClick();
        IntentManager.intentToRechargeRecord(context);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBalanceAndCoupon();
    }

    private void initPresenter(){
        accountPresenter = new AccountPresenter(context);
        accountPresenter.setOnCallBack(new AccountPresenter.OnCallBack() {
            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void getBalanceAndCoupon(boolean isSuccess, Object object) {
                if (isSuccess && object != null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(), Const.KEY_RES_CODE_200)){
                        BalanceAndCoupon balanceAndCoupon = new Gson().fromJson(resultModel.getModelJson(),BalanceAndCoupon.class);
                        if (balanceAndCoupon != null){

                            if (StringUtil.isNumber(balanceAndCoupon.getBalance())){
                                DecimalFormat df = new DecimalFormat();
                                df.setMaximumFractionDigits(2);
                                df.setMinimumFractionDigits(2);
                                balance.setText(df.format(Double.parseDouble(balanceAndCoupon.getBalance())));//控制两位小数
                            }else{
                                balance.setText(balanceAndCoupon.getBalance());
                            }
                        }
                    }
                }
            }

            @Override
            public void recharge(boolean isSuccess, Object object) {

            }

            @Override
            public void getRechargeRecord(boolean isSuccess, Object object) {

            }

            @Override
            public void failed(Object object) {

            }
        });
    }

    private void getBalanceAndCoupon(){
        if (accountPresenter ==null)return;
        resetBalanceAndCoupon();
        Map<String,String> params = RS.getBaseParams(context);
        params.put("user_id", AccountTool.getLoginUser(context).getId());
        accountPresenter.getBalanceAndCoupon(params);
    }

    private void resetBalanceAndCoupon(){
        balance.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goto_recharge:
                IntentManager.intentToRecharge(context);
                break;
            case R.id.goto_bankcard:
                IntentManager.intentToBindBankcard(context);
                break;
        }

    }
}
