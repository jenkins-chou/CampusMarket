package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.presenter.UserPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;
import com.jenking.spandroid.ui.CommonLoading;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    private UserPresenter userPresenter;

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pass)
    EditText pass;

    @BindView(R.id.loading)
    CommonLoading loading;

    @OnClick(R.id.forget_password)
    void forget_password(){
        CommonTipsDialog.showTip(this,"温馨提示","忘记登录密码或账号请联系管理员找回",false);
    }

    @OnClick(R.id.goto_register)
    void goto_register(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.goto_login)
    void goto_login(){
        String name_str = name.getText().toString();
        String pass_str = pass.getText().toString();
        if (!StringUtil.isNotEmpty(name_str)
                &&!StringUtil.isNotEmpty(pass_str)){
            CommonTipsDialog.showTip(this,"温馨提示","请完善信息",false);
            return;
        }else{
            Map<String,String> params = RS.getBaseParams(this);
            params.put("name",name_str);
            params.put("pass",pass_str);
            userPresenter.login(params);
            setLoadingEnable(true);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    void setLoadingEnable(boolean enable){
        if (loading==null)return;
        if (enable){
            loading.setVisibility(View.VISIBLE);
        }else{
            loading.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {
        super.initData();
        userPresenter = new UserPresenter(this);
        userPresenter.setOnCallBack(new UserPresenter.OnCallBack() {

            @Override
            public void login(boolean isSuccess, Object object) {
                setLoadingEnable(false);
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                            if (resultModel.getData()!=null&&resultModel.getData().size()>0){
                                List<UserModel> userModels = resultModel.getData();
                                UserModel userModel = userModels.get(0);
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                AccountTool.saveUser(LoginActivity.this,userModel);
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this, "账号不存在或登录信息有误", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void addUser(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUser(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUser(boolean isSuccess, Object object) {

            }

            @Override
            public void getTeachers(boolean isSuccess, Object object) {

            }

            @Override
            public void getTeachersByCollege(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllStudent(boolean isSuccess, Object object) {

            }

            @Override
            public void getStudentByClass(boolean isSuccess, Object object) {

            }
        });
    }
}
