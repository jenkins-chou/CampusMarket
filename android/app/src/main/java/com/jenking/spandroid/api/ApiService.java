package com.jenking.spandroid.api;

import com.jenking.spandroid.models.base.CommodityModel;
import com.jenking.spandroid.models.base.OrderModel;
import com.jenking.spandroid.models.base.PlateModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.ShippingAddressModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.models.base.WishListModel;
import com.jenking.spandroid.models.business.account.AccountRechargeModel;
import com.jenking.spandroid.models.business.account.BalanceAndCoupon;
import com.jenking.spandroid.models.business.account.BankCardModel;
import com.jenking.spandroid.models.business.account.OrderStateModel;

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
    @POST("base_user/login")
    Observable<ResultModel<UserModel>> login(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_user/register")
    Observable<ResultModel<UserModel>> addUser(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_user/update")
    Observable<ResultModel<UserModel>> updateUser(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_plate/getAllMobile")
    Observable<ResultModel<PlateModel>> getAllPlate(@FieldMap Map<String, String> body);




    @FormUrlEncoded
    @POST("base_commodity/add")
    Observable<ResultModel<CommodityModel>> addCommodity(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_commodity/delete")
    Observable<ResultModel<CommodityModel>> deleteCommodity(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_commodity/update")
    Observable<ResultModel<CommodityModel>> updateCommodity(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_commodity/searchAllMobile")
    Observable<ResultModel<CommodityModel>> searchAllMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_commodity/getAllMobile")
    Observable<ResultModel<CommodityModel>> getAllCommodity(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_commodity/getAllByPlateMobile")
    Observable<ResultModel<CommodityModel>> getAllByPlateMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_commodity/getAllByProviderMobile")
    Observable<ResultModel<CommodityModel>> getAllCommodityByProvider(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_commodity/purchase")
    Observable<ResultModel<CommodityModel>> purchaseCommodity(@FieldMap Map<String, String> body);




    @FormUrlEncoded
    @POST("base_collection/add")
    Observable<ResultModel<CommodityModel>> collectCommodity(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_collection/getAllCollectByProvider")
    Observable<ResultModel<CommodityModel>> getAllCollectByProvider(@FieldMap Map<String, String> body);


    @FormUrlEncoded
    @POST("base_wishlist/add")
    Observable<ResultModel<WishListModel>> addWishList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_wishlist/update")
    Observable<ResultModel<WishListModel>> updateWishList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_wishlist/delete")
    Observable<ResultModel<WishListModel>> deleteWishList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_wishlist/getAllMobile")
    Observable<ResultModel<WishListModel>> getAllMobile(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_wishlist/getAllByProviderMobile")
    Observable<ResultModel<WishListModel>> getAllByProviderMobile(@FieldMap Map<String, String> body);

    //account
    @FormUrlEncoded
    @POST("account/getBalanceAndCoupon")
    Observable<ResultModel<BalanceAndCoupon>> getBalanceAndCoupon(@FieldMap Map<String, String> body);

    //充值
    @FormUrlEncoded
    @POST("account_recharge/recharge")
    Observable<ResultModel> recharge(@FieldMap Map<String, String> body);

    //充值记录
    @FormUrlEncoded
    @POST("account_recharge/getAllEntityByUserId")
    Observable<ResultModel<AccountRechargeModel>> getRechargeRecord(@FieldMap Map<String, String> body);

    //获取用户所有银行卡
    @FormUrlEncoded
    @POST("bankcard/getAllEntityByUserId")
    Observable<ResultModel<BankCardModel>> getBankCard(@FieldMap Map<String, String> body);

    //绑定银行卡
    @FormUrlEncoded
    @POST("bankcard/addEntity")
    Observable<ResultModel> bindBankCard(@FieldMap Map<String, String> body);

    //重置银行卡
    @FormUrlEncoded
    @POST("bankcard/deleteEntity")
    Observable<ResultModel> deleteBankCard(@FieldMap Map<String, String> body);

    //收货地址 添加
    @FormUrlEncoded
    @POST("shipping_address/add")
    Observable<ResultModel<ShippingAddressModel>> addShippingAddress(@FieldMap Map<String, String> body);

    //收货地址 删除
    @FormUrlEncoded
    @POST("shipping_address/delete")
    Observable<ResultModel<ShippingAddressModel>> deleteShippingAddress(@FieldMap Map<String, String> body);

    //收货地址 设置默认
    @FormUrlEncoded
    @POST("shipping_address/setDefault")
    Observable<ResultModel<ShippingAddressModel>> setDefaultShippingAddress(@FieldMap Map<String, String> body);

    //收货地址 设置默认
    @FormUrlEncoded
    @POST("shipping_address/getShippingAddressByUserId")
    Observable<ResultModel<ShippingAddressModel>> getShippingAddressByUserId(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_order/getMineSellList")
    Observable<ResultModel<OrderModel>> getMineSellList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_order/getMinePurchaseList")
    Observable<ResultModel<OrderModel>> getMinePurchaseList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_order/confirmReceipt")
    Observable<ResultModel<OrderModel>> updateOrder(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_order/getOrderStateList")
    Observable<ResultModel<OrderStateModel>> getOrderStateList(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_order_state/delete")
    Observable<ResultModel<OrderStateModel>> deleteOrderState(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("base_order_state/add")
    Observable<ResultModel<OrderStateModel>> addOrderState(@FieldMap Map<String, String> body);


}
