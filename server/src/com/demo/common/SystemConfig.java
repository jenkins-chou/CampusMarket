package com.demo.common;

import java.util.HashMap;



import java.util.List;
import java.util.Map;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal3.JFinal3BeetlRenderFactory;

import com.demo.controller.AccountController;
import com.demo.controller.AccountRechargeController;
import com.demo.controller.BankCardController;
import com.demo.controller.BaseCollectionController;
import com.demo.controller.BaseCommentController;
import com.demo.controller.BaseCommodityController;
import com.demo.controller.BaseMenuController;
import com.demo.controller.BaseMessageController;
import com.demo.controller.BaseModuleController;
import com.demo.controller.BaseOrderController;
import com.demo.controller.BaseOrderStateController;
import com.demo.controller.BasePlateController;
import com.demo.controller.BaseTypeController;
import com.demo.controller.BaseUserController;
import com.demo.controller.BaseUserTypeController;
import com.demo.controller.BaseWishlistController;
import com.demo.controller.IndexController;
import com.demo.controller.ShippingAddressController;
import com.demo.controller.StatisticsController;
import com.demo.controller.TransactionRecordController;
import com.demo.controller.UploadController;
import com.demo.models.AccountModel;
import com.demo.models.AccountRechargeModel;
import com.demo.models.BankCardModel;
import com.demo.models.BaseCollectionModel;
import com.demo.models.BaseCommentModel;
import com.demo.models.BaseCommodityModel;
import com.demo.models.BaseMenuModel;
import com.demo.models.BaseMessageModel;
import com.demo.models.BaseModuleModel;
import com.demo.models.BaseOrderModel;
import com.demo.models.BaseOrderStateModel;
import com.demo.models.BasePlateModel;
import com.demo.models.BaseTypeModel;
import com.demo.models.BaseUserModel;
import com.demo.models.BaseUserTypeModel;
import com.demo.models.BaseWishlistModel;
import com.demo.models.CouponUserModel;
import com.demo.models.ShippingAddressModel;
import com.demo.models.TransactionRecordModel;
import com.demo.utils.CrossInterceptor;
import com.demo.utils.DatabaseUtil;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class SystemConfig extends JFinalConfig {
	
	public static final int port = 8888;
	
	public static void main(String[] args) {
		//PathKit.setWebRootPath("/WebRoot");
		
		JFinal.start("WebRoot", port, "/", 5);
		
	}
	
	public void init(){
		PropKit.use("db_config.txt");
		DatabaseUtil.init();
	}
	
	public void configConstant(Constants me) {
		
		init();
		
		//PropKit.use("db_config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", true));//热更新调试模式
		me.setViewType(ViewType.JSP); 	
		
		JFinal3BeetlRenderFactory rf = new JFinal3BeetlRenderFactory();
		rf.config();
		me.setRenderFactory(rf);
		GroupTemplate gt = rf.groupTemplate;
		Map<String, Object> shard = new HashMap<String, Object>();// 鍏变韩鍙橀噺
		shard.put("ctx", JFinal.me().getContextPath());// 娣诲姞鍏变韩鍙橀噺涓婁笅鏂囪矾锟�?
		gt.setSharedVars(shard);// 璁剧疆鍏变韩鍙橀噺
		me.setMaxPostSize(1200000000);
		
		//me.setBaseUploadPath("/upload"); 
	}
	
	public void configRoute(Routes me) {
		me.add("/", IndexController.class);	
		me.add("/upload", UploadController.class);
		
		me.add("base_collection",BaseCollectionController.class);
		me.add("base_comment",BaseCommentController.class);
		me.add("base_commodity",BaseCommodityController.class);
		me.add("base_menu",BaseMenuController.class);
		me.add("base_message",BaseMessageController.class);
		me.add("base_module",BaseModuleController.class);
		me.add("base_order",BaseOrderController.class);
		me.add("base_order_state",BaseOrderStateController.class);
		me.add("base_plate",BasePlateController.class);
		me.add("base_type",BaseTypeController.class);
		me.add("base_user",BaseUserController.class);
		me.add("base_user_type",BaseUserTypeController.class);
		me.add("transaction_record",TransactionRecordController.class);
		me.add("base_wishlist",BaseWishlistController.class);
		
		me.add("/account", AccountController.class);
		me.add("/account_recharge", AccountRechargeController.class);
		me.add("/bankcard", BankCardController.class);
		
		me.add("shipping_address",ShippingAddressController.class);
		
		me.add("statistics",StatisticsController.class);
		
		
	
	}
	 
	public void configPlugin(Plugins me) {
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		me.add(c3p0Plugin);
		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
	
//		arp.addMapping("base_user_type", UserTypeModel.class);
//		arp.addMapping("base_menu", MenuModel.class);
//		
//		
		arp.addMapping("base_collection", BaseCollectionModel.class);
		arp.addMapping("base_comment", BaseCommentModel.class);
		arp.addMapping("base_commodity", BaseCommodityModel.class);
		arp.addMapping("base_menu", BaseMenuModel.class);
		arp.addMapping("base_message", BaseMessageModel.class);
		arp.addMapping("base_module", BaseModuleModel.class);
		arp.addMapping("base_order", BaseOrderModel.class);
		arp.addMapping("base_order_state", BaseOrderStateModel.class);
		arp.addMapping("base_plate", BasePlateModel.class);
		arp.addMapping("base_type", BaseTypeModel.class);
		arp.addMapping("base_user", BaseUserModel.class);
		arp.addMapping("base_user_type", BaseUserTypeModel.class);
		arp.addMapping("transaction_record", TransactionRecordModel.class);
		arp.addMapping("base_wishlist", BaseWishlistModel.class);
		
		arp.addMapping("account", AccountModel.class);
		arp.addMapping("account_recharge", AccountRechargeModel.class);
		arp.addMapping("bankcard", BankCardModel.class);
		
		arp.addMapping("shipping_address", ShippingAddressModel.class);


	}
	
	public void configInterceptor(Interceptors me) {
		me.add(new CrossInterceptor());
	}
	
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("ctx"));
	}
	
	

	@Override
	public void configEngine(Engine arg0) {
		// TODO Auto-generated method stub
	}
}
