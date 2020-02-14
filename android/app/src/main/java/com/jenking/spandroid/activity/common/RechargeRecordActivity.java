package com.jenking.spandroid.activity.common;

import android.accounts.Account;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.github.library.BaseViewHolder;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.base.BaseListActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.business.account.AccountRechargeModel;
import com.jenking.spandroid.presenter.AccountPresenter;
import com.jenking.spandroid.tools.Const;
import com.jenking.spandroid.tools.StringUtil;

import java.util.List;

public class RechargeRecordActivity extends BaseListActivity {

    AccountPresenter accountPresenter;

    @Override
    protected int getItemLayoutRes() {
        return R.layout.activity_recharge_record_item;
    }

    @Override
    protected void convertData(BaseViewHolder helper, Object item) {
        //
        AccountRechargeModel accountRechargeModel = (AccountRechargeModel)item;
        helper.setText(R.id.recharge_money,"充值："+accountRechargeModel.getMoney()+" 元");
        helper.setText(R.id.recharge_time, StringUtil.getStrTime(accountRechargeModel.getCreate_time(),"yyyy年MM月dd日 HH:mm:ss")+"");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("充值记录");
        setPullRefreshEnable(false);
        setLoadRefreshEnable(false);
    }

    @Override
    public void initData() {
        super.initData();
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

            }

            @Override
            public void getRechargeRecord(boolean isSuccess, Object object) {
                if (isSuccess && object != null) {
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(), Const.KEY_RES_CODE_200)) {
                        List<AccountRechargeModel> accountRechargeModels = (List<AccountRechargeModel>) resultModel.getData();
                        if (accountRechargeModels != null){
                            setData(accountRechargeModels);
                        }
                    }
                }

            }

            @Override
            public void failed(Object object) {

            }
        });

        accountPresenter.getRechargeRecord(RS.getBaseParams(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
