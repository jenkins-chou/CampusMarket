package com.jenking.spandroid.api;

import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhouzhenjian on 2018/3/26.
 */

public interface ApiService {

    //模板接口

    @FormUrlEncoded
    @POST("user/login")
    Observable<ResultModel> template(@FieldMap Map<String, String> body);


    @FormUrlEncoded
    @POST("user/login")
    Observable<ResultModel<UserModel>> login(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user/addUser")
    Observable<ResultModel<UserModel>> addUser(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user/updateUser")
    Observable<ResultModel<UserModel>> updateUser(@FieldMap Map<String, String> body);

}
