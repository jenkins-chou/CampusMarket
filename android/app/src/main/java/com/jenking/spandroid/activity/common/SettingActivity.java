package com.jenking.spandroid.activity.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jenking.spandroid.R;
import com.jenking.spandroid.api.BaseAPI;
import com.jenking.spandroid.tools.AccountTool;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity{

    @BindView(R.id.logout)
    TextView logout;
    @OnClick(R.id.back)
    void back(){
        BaseAPI.base_url = input.getText().toString();
        finish();
    }

    @BindView(R.id.input)
    EditText input;

    @OnClick(R.id.logout)
    void logout(){
        AccountTool.logout(this);
        Toast.makeText(this, "退出登录成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @Override
    public void initData() {
        super.initData();
        input.setText(BaseAPI.base_url);
    }

    @Override
    public void initView() {
        super.initView();
        if (AccountTool.isLogin(this)){
            logout.setVisibility(View.VISIBLE);
        }
    }
}
