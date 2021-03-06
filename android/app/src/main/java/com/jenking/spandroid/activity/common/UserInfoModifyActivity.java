package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.presenter.UserPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class UserInfoModifyActivity extends BaseActivity {

    private UserModel userModel;

    private String select_school_id;
    private String select_school_name;
    private String select_college_id;
    private String select_college_name;
    private String select_class_id;
    private String select_class_name;

    private UserPresenter userPresenter;

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.realname)
    EditText realname;
    @BindView(R.id.slogan)
    EditText slogan;
    @BindView(R.id.sex)
    EditText sex;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.idnum)
    EditText idnum;
    @BindView(R.id.nation)
    EditText nation;
    @BindView(R.id.registered_residence)
    EditText registered_residence;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.useridentify)
    EditText useridentify;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.health)
    EditText health;
    @BindView(R.id.entrance_time)
    EditText entrance_time;
    @BindView(R.id.college_name)
    TextView college_name;

    @BindView(R.id.class_bar)
    LinearLayout class_bar;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.submit)
    void submit(){
        String name_str = name.getText().toString();
        String pass_str = pass.getText().toString();
        if (!StringUtil.isNotEmpty(name_str)
                ||!StringUtil.isNotEmpty(pass_str)){
            Toast.makeText(this, "登录名和密码必填", Toast.LENGTH_SHORT).show();
            return;
        }else{
            Map<String,String> params = RS.getBaseParams(this);
            params.put("name",name_str);
            params.put("pass",pass_str);
            params.put("realname",realname.getText().toString());
            params.put("avatar","");
            params.put("slogan",slogan.getText().toString());
            params.put("sex",sex.getText().toString());
            params.put("age",age.getText().toString());
            params.put("idnum",idnum.getText().toString());
            params.put("nation",nation.getText().toString());
            params.put("registered_residence",registered_residence.getText().toString());
            params.put("email",email.getText().toString());
            params.put("useridentify",useridentify.getText().toString());
            params.put("phone",phone.getText().toString());
            params.put("address",address.getText().toString());
            params.put("health",health.getText().toString());
            params.put("entrance_time",entrance_time.getText().toString());
            params.put("class_id",select_class_id+"");
            params.put("college_id",select_college_id+"");
            params.put("school_id",select_school_id+"");
            params.put("class_name",select_class_name+"");
            params.put("college_name",select_college_name+"");
            params.put("school_name",select_school_name+"");
            if (userModel!=null){
                params.put("id",userModel.getId());
                userPresenter.updateUser(params);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_modify);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改
            String json = intent.getStringExtra("model");
            userModel = new Gson().fromJson(json, UserModel.class);

            if (userModel!=null){
                Log.e("usermodel",userModel.toString());

                select_college_id = userModel.getCollege_id();
                select_college_name = userModel.getCollege_name();
                select_school_id = userModel.getSchool_id();
                select_school_name = userModel.getSchool_name();
                select_class_id = userModel.getClass_id();
                select_class_name = userModel.getClass_name();

                name.setText(userModel.getUsername());
                pass.setText(userModel.getPassword());
                realname.setText(userModel.getRealname());
                slogan.setText(userModel.getSlogan());
                sex.setText(userModel.getSex());
                age.setText(userModel.getAge());
                idnum.setText(userModel.getIdnum());
                nation.setText(userModel.getNation());
                registered_residence.setText(userModel.getRegistered_residence());
                email.setText(userModel.getEmail());
                useridentify.setText(userModel.getUseridentify());
                phone.setText(userModel.getPhone());
                address.setText(userModel.getAddress());
                health.setText(userModel.getHealth());
                entrance_time.setText(userModel.getEntrance_time());
                college_name.setText(select_school_name +"--"+ select_college_name+"--"+select_class_name);

            }
        }

        userPresenter = new UserPresenter(this);
        userPresenter.setOnCallBack(new UserPresenter.OnCallBack() {
            @Override
            public void login(boolean isSuccess, Object object) {

            }

            @Override
            public void addUser(boolean isSuccess, Object object) {
            }

            @Override
            public void updateUser(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(UserInfoModifyActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    resaveUser();
                    finish();
                }
            }

            @Override
            public void deleteUser(boolean isSuccess, Object object) {

            }

        });
    }

    private void resaveUser(){
        if (userModel!=null){

            userModel.setUsername(name.getText().toString());
            userModel.setPassword(pass.getText().toString());
            userModel.setRealname(realname.getText().toString());
            userModel.setSlogan(slogan.getText().toString());
            userModel.setSex(sex.getText().toString());
            userModel.setAge(age.getText().toString());
            userModel.setIdnum(idnum.getText().toString());
            userModel.setNation(nation.getText().toString());

            userModel.setRegistered_residence(registered_residence.getText().toString());
            userModel.setEmail(email.getText().toString());
            userModel.setUseridentify(useridentify.getText().toString());
            userModel.setPhone(phone.getText().toString());
            userModel.setAddress(address.getText().toString());
            userModel.setHealth(health.getText().toString());
            userModel.setEntrance_time(entrance_time.getText().toString());
            userModel.setClass_id(select_class_id+"");
            userModel.setSchool_id(select_school_id+"");
            userModel.setCollege_id(select_college_id+"");
            userModel.setClass_name(select_class_name+"");
            userModel.setCollege_name(select_college_name+"");
            userModel.setSchool_name(select_school_name+"");

            AccountTool.saveUser(this,userModel);
        }
    }

    @Override
    public void initView() {
        super.initView();
        if (AccountTool.isLogin(this)){
            if (!StringUtil.isEquals(AccountTool.getUserType(this),AccountTool.usertype_student)){
                class_bar.setVisibility(View.GONE);
            }
        }
    }
}
