package com.jenking.spandroid.intent;

import android.content.Context;
import android.content.Intent;

import com.jenking.spandroid.activity.common.AccountBalanceActivity;
import com.jenking.spandroid.activity.common.AccountPayPwdActivity;
import com.jenking.spandroid.activity.common.BindBankCardActivity;
import com.jenking.spandroid.activity.common.MineShippingAddressActivity;
import com.jenking.spandroid.activity.common.MineShippingAddressEditActivity;
import com.jenking.spandroid.activity.common.RechargeActivity;
import com.jenking.spandroid.activity.common.RechargeRecordActivity;

public class IntentManager {
//    /**
//     * 跳转登录
//     * @param context
//     */
//    public static void intentToLogin(Context context){
//        Intent intent = new Intent(context,LoginActivity.class);
//        context.startActivity(intent);
//    }
//
//    /**
//     * 跳转到设置
//     * @param context
//     */
//    public static void intentToSetting(Context context){
//        Intent intent = new Intent(context,SettingActivity.class);
//        context.startActivity(intent);
//    }
//
//    /**
//     * 跳转到用户信息设置
//     * @param context
//     */
//    public static void intentToUserinfoSetting(Context context){
//        Intent intent = new Intent(context,UserinfoSettingActivity.class);
//        context.startActivity(intent);
//    }
//
//    /**
//     * 跳转到用户信息头像
//     * @param context
//     */
//    public static void intentToUserinfoAvatar(Context context){
//        Intent intent = new Intent(context,UserInfoAvatarActivity.class);
//        context.startActivity(intent);
//    }
//
//    /**
//     * 跳转到用户的优惠券列表
//     * @param context
//     */
//    public static void intentToCouponListUser(Context context){
//        Intent intent = new Intent(context,CouponListUserActivity.class);
//        context.startActivity(intent);
//    }
//
    /**
     * 跳转到用户余额页面
     * @param context
     */
    public static void intentToBalance(Context context){
        Intent intent = new Intent(context, AccountBalanceActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到充值页面
     * @param context
     */
    public static void intentToRecharge(Context context){
        Intent intent = new Intent(context, RechargeActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到银行卡绑定界面
     * @param context
     */
    public static void intentToBindBankcard(Context context){
        Intent intent = new Intent(context, BindBankCardActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到充值记录界面
     * @param context
     */
    public static void intentToRechargeRecord(Context context){
        Intent intent = new Intent(context, RechargeRecordActivity.class);
        context.startActivity(intent);
    }
//
//    /**
//     * 跳转到消费记录
//     * @param context
//     */
//    public static void intentToConsumeRecord(Context context){
//        Intent intent = new Intent(context,ConsumeRecordActivity.class);
//        context.startActivity(intent);
//    }
//
//    /**
//     * 跳转到优惠券商店
//     * @param context
//     */
//    public static void intentToCouponStore(Context context){
//        Intent intent = new Intent(context,CouponStoreActivity.class);
//        context.startActivity(intent);
//    }
//
    /**
     * 跳转到支付密码设置界面
     * @param context
     */
    public static void intentToAccountPayPwd(Context context){
        Intent intent = new Intent(context, AccountPayPwdActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到我的收货地址
     * @param context
     */
    public static void intentToMineShippingAddress(Context context){
        Intent intent = new Intent(context, MineShippingAddressActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到我的收货地址 - 添加
     * @param context
     */
    public static void intentToMineShippingAddressEdit(Context context){
        Intent intent = new Intent(context, MineShippingAddressEditActivity.class);
        context.startActivity(intent);
    }
}
