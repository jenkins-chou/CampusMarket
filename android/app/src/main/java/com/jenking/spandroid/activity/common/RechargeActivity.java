package com.jenking.spandroid.activity.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.base.BaseEmptyActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonInputDialog;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.intent.IntentManager;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.models.business.account.BankCardModel;
import com.jenking.spandroid.presenter.AccountPresenter;
import com.jenking.spandroid.presenter.BankCardPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.Const;

import java.util.List;
import java.util.Map;

public class RechargeActivity extends BaseEmptyActivity implements View.OnClickListener{

    boolean noBankCard = true;
    BankCardModel bankCardModel;
    AccountPresenter accountPresenter;
    BankCardPresenter bankCardPresenter;
    RelativeLayout tips_bar;

    TextView btn_bind_bankcard;
    EditText input_money;
    Button btn_recharge;
    LinearLayout bankcard_bar;
    TextView bankcard_name;
    TextView bankcard_number;

    @Override
    protected int setContentRes() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("余额充值");
        initPresenter();
        setToolbarRightButtonEnable(true);
        setToolbarRightButtonTitle("支付密码");
    }

    @Override
    public void onToolbarRightButtonClick() {
        super.onToolbarRightButtonClick();
        IntentManager.intentToAccountPayPwd(context);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBankcard();
    }

    @Override
    public void initView() {
        super.initView();
        tips_bar = findViewById(R.id.tips_bar);
        btn_bind_bankcard = findViewById(R.id.btn_bind_bankcard);
        input_money = findViewById(R.id.input_money);
        btn_recharge = findViewById(R.id.btn_recharge);

        bankcard_bar = findViewById(R.id.bankcard_bar);
        bankcard_name = findViewById(R.id.bankcard_name);
        bankcard_number = findViewById(R.id.bankcard_number);

        btn_bind_bankcard.setOnClickListener(this);
        btn_recharge.setOnClickListener(this);
    }

    private void initPresenter(){
        accountPresenter = new AccountPresenter(context);
        accountPresenter.setOnCallBack(new AccountPresenter.OnCallBack() {
            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void getBalanceAndCoupon(boolean isSuccess, Object object) {

            }

            @Override
            public void recharge(boolean isSuccess, Object object) {
                if (isSuccess && object != null) {
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(), Const.KEY_RES_CODE_200)) {
                        //Log.e("recharge","成功");
                        Toast.makeText(context, "充值成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void getRechargeRecord(boolean isSuccess, Object object) {

            }

            @Override
            public void failed(Object object) {

            }
        });

        bankCardPresenter = new BankCardPresenter(context);
        bankCardPresenter.setOnCallBack(new BankCardPresenter.OnCallBack() {
            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void bindBankCard(boolean isSuccess, Object object) {

            }

            @Override
            public void getBankCard(boolean isSuccess, Object object) {
                if (isSuccess && object != null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(),Const.KEY_RES_CODE_200)){
                        List<BankCardModel> bankCardModels = resultModel.getList();
                        if (bankCardModels != null && bankCardModels.size()>=1){
                            tips_bar.setVisibility(View.GONE);
                            noBankCard = false;
                            setBankcardEnable(true);
                            setBankcardInfo(bankCardModels.get(0));
                        }else{
                            noBankCard = true;
                            tips_bar.setVisibility(View.VISIBLE);
                            setBankcardEnable(false);
                        }
                    }
                }
            }

            @Override
            public void deleteBankCard(boolean isSuccess, Object object) {

            }

            @Override
            public void failed(Object object) {

            }
        });
    }

    private void getBankcard(){
        Map<String,String> params = RS.getBaseParams(context);
        params.put("user_id", AccountTool.getLoginUser(context).getId());
        bankCardPresenter.getBankCard(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_bind_bankcard:
                if (noBankCard){
                    IntentManager.intentToBindBankcard(context);
                }
                break;
            case R.id.btn_recharge:
                if (noBankCard){
                    CommonTipsDialog.show(context,"提示","未绑定银行卡，请按照页面提示进行操作",false);
                }else{
                    if (TextUtils.isEmpty(getInputMoney())){
                        CommonTipsDialog.show(context,"提示","请输入金额",false);
                    }else{
                        //开始校验支付密码
                        final UserModel userModel = AccountTool.getLoginUser(RechargeActivity.this);
                        if (userModel != null){
                            Log.e("UserModel",userModel.toString());
                            if (TextUtils.isEmpty(userModel.getPay_pwd()) ||
                                    TextUtils.equals(userModel.getPay_pwd(),"null")||
                                    TextUtils.equals(userModel.getPay_pwd(),"NULL")){
                                CommonTipsDialog.show(context,"温馨提示","您还没有设置支付密码,请点击右上角'支付密码'进行设置",false);
                            }else{
                                CommonInputDialog.create(context, "支付密码", "请输入支付密码", false, new CommonInputDialog.OnClickListener() {
                                    @Override
                                    public void cancel() {

                                    }
                                    @Override
                                    public void getInput(String inputStr) {
                                        if (userModel != null && TextUtils.equals(userModel.getPay_pwd(),inputStr)){
                                            //开始充值
                                            accountPresenter.recharge(getRequestParams());
                                        }else{
                                            Toast.makeText(context, "验证失败,请重试", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }).show();
                            }
                        }else{
                            Toast.makeText(context, "数据出错，请重新登录", Toast.LENGTH_SHORT).show();
                            finish();
                        }


                    }
                }
                break;
        }
    }

    public void setBankcardEnable(boolean enable){
        bankcard_bar.setVisibility(enable?View.VISIBLE:View.GONE);
    }
    public void setBankcardInfo(BankCardModel bankcardInfo){
        if (bankcardInfo == null)return;
        bankCardModel = bankcardInfo;
        bankcard_name.setText(bankcardInfo.getBank_name());
        bankcard_number.setText(bankcardInfo.getBankcard_number());
    }

    public String getInputMoney(){
        return input_money.getText().toString();
    }

    public Map<String,String> getRequestParams(){
        Map<String,String> params = RS.getBaseParams(context);
        params.put("money",getInputMoney());
        params.put("bankcard_id",bankCardModel.getId());
        return params;
    }


}
