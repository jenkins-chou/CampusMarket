package com.jenking.spandroid.api;

import android.content.Context;

import com.jenking.spandroid.tools.AccountTool;

import java.util.HashMap;
import java.util.Map;

//基本请求参数类
public class RS {

    /**
     * 获取基本请求参数
     * @param context
     * @return Map<String,String>  enum:token,device
     */
    public static Map<String,String> getBaseParams(Context context){
        Map<String,String> params = new HashMap<>();
        if (AccountTool.isLogin(context)){
            params.put("user_id", AccountTool.getLoginUser(context).getId());
        }
        return params;
    }
}
