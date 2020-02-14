package com.demo.controller;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.AccountModel;
import com.demo.models.BaseCommodityModel;
import com.demo.models.BaseOrderModel;
import com.demo.models.BaseOrderStateModel;
import com.demo.utils.CheckUtils;
import com.demo.utils.Const;
import com.demo.utils.CrossOrigin;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.demo.utils.DatabaseUtil;
import com.demo.utils.Log;
import com.demo.utils.StringUtil;

@CrossOrigin
public class BaseCommodityController  extends Controller {
	
	public static final String DB_TABLE = "base_commodity";
	
	public static final Map<String,String> tableFilter = new HashMap();
	
	static{
		tableFilter.put("id","hidden");
		tableFilter.put("create_time","hidden");
		tableFilter.put("del","hidden");
	}
	
	public enum FilterType{
		hidden,//�����ֶ�
		custom,//�Զ���
		normal//Ĭ��
	}
	

	
	public void purchase(){
		try {
			String user_id = getPara("party_b");
			String money = getPara("money");
			Log.i(money);
			Log.i(StringUtil.isNumber(money)+"");
			
			List<AccountModel> accountModels = AccountModel.dao.find("select * from account where user_id = '"+user_id+"' and del != 'delete'");
			if(CheckUtils.checkArrayIsNotNull(accountModels)){
				AccountModel accountModel = accountModels.get(0);
				if(StringUtil.isNumber(money) || (StringUtil.isNotEmpty(money) && money.equals("0.0"))){
					double preBalance = accountModel.getDouble("balance");
					double couponMoneyDouble = Double.parseDouble(money);
					if(preBalance>couponMoneyDouble){
					
						
						accountModel.set("balance", preBalance-couponMoneyDouble);
						accountModel.update();
						
						BaseOrderModel orderModel = getModel(BaseOrderModel.class,"");
						orderModel.set(Const.KEY_DB_CREATE_TIME, System.currentTimeMillis()/1000+"");
						orderModel.set(Const.KEY_DB_DEL, Const.OPTION_DB_NORMAL);
						orderModel.save();
						
						BaseOrderStateModel stateModel = new BaseOrderStateModel();
						stateModel.set("order_id", orderModel.get("id"));
						stateModel.set("state", "create");
						stateModel.set(Const.KEY_DB_CREATE_TIME, System.currentTimeMillis()/1000+"");
						stateModel.set(Const.KEY_DB_DEL, Const.OPTION_DB_NORMAL);
						stateModel.save();
					
						JSONObject js = new JSONObject();
						js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
						renderJson(js.toJSONString());
						
					}else{
						JSONObject js = new JSONObject();
						js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
						js.put(Const.KEY_RES_MESSAGE, "余额不足0");
						renderJson(js.toJSONString());
					}
				}else{
					JSONObject js = new JSONObject();
					js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
					js.put(Const.KEY_RES_MESSAGE, "余额不足1");
					renderJson(js.toJSONString());
				}
				
			}else{
				JSONObject js = new JSONObject();
				js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
				js.put(Const.KEY_RES_MESSAGE, "余额不足2");
				renderJson(js.toJSONString());
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_500);
			js.put(Const.KEY_RES_MESSAGE, e.toString());
			renderJson(js.toJSONString());
		}
	}
	
	@CrossOrigin
	public void searchAllMobile(){
		String keyword = getPara("keyword");
		List<BaseCommodityModel> models = BaseCommodityModel.dao.find("select * from "+DB_TABLE+" where commodity_name like '%"+keyword+"%' and del != 'delete'");
		JSONObject js = new JSONObject();
		if(models!=null&&models.size()>=1){
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			js.put("list", models);
			System.out.println(JsonKit.toJson(js));
			renderJson(JsonKit.toJson(js));
		}else{
			System.out.println("model:");
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
			renderJson(js.toJSONString());
		}
	}
	
	@CrossOrigin
	public void getAllMobile(){
		List<BaseCommodityModel> models = BaseCommodityModel.dao.find("select * from "+DB_TABLE+" where del != 'delete'");
		JSONObject js = new JSONObject();
		if(models!=null&&models.size()>=1){
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			js.put("list", models);
			System.out.println(JsonKit.toJson(js));
			renderJson(JsonKit.toJson(js));
		}else{
			System.out.println("model:");
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
			renderJson(js.toJSONString());
		}
	}
	
	@CrossOrigin
	public void getAllByPlateMobile(){
		String plate_id = getPara("plate_id");
		List<BaseCommodityModel> models = BaseCommodityModel.dao.find("select * from "+DB_TABLE+" where plate_id = "+plate_id+" and del != 'delete'");
		JSONObject js = new JSONObject();
		if(models!=null&&models.size()>=1){
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			js.put("list", models);
			System.out.println(JsonKit.toJson(js));
			renderJson(JsonKit.toJson(js));
		}else{
			System.out.println("model:");
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
			renderJson(js.toJSONString());
		}
	}
	
	@CrossOrigin
	public void getAllByProviderMobile(){
		String provider = getPara("user_id");
		List<BaseCommodityModel> models = BaseCommodityModel.dao.find("select * from "+DB_TABLE+" where commodity_provider = "+provider+" and del != 'delete'");
		JSONObject js = new JSONObject();
		if(models!=null&&models.size()>=1){
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			js.put("list", models);
			System.out.println(JsonKit.toJson(js));
			renderJson(JsonKit.toJson(js));
		}else{
			System.out.println("model:");
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
			renderJson(js.toJSONString());
		}
	}
	
	@CrossOrigin
	public void getAll(){
		List<BaseCommodityModel> models = BaseCommodityModel.dao.find("select * from "+DB_TABLE+" where del != 'delete'");
		JSONObject js = new JSONObject();
		if(models!=null&&models.size()>=1){
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			js.put(Const.KEY_RES_DATA, models);
			System.out.println(JsonKit.toJson(js));
			renderJson(JsonKit.toJson(js));
		}else{
			System.out.println("model:");
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
			renderJson(js.toJSONString());
		}
	}
	
	@CrossOrigin
	public void get(){
		String id = getPara("id");
		List<BaseCommodityModel> models = BaseCommodityModel.dao.find("select * from "+DB_TABLE+" where id = "+id+" and  del != 'delete'");
		JSONObject js = new JSONObject();
		if(models!=null&&models.size()>=1){
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			js.put(Const.KEY_RES_DATA, models.get(0));
			System.out.println(JsonKit.toJson(js));
			renderJson(JsonKit.toJson(js));
		}else{
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
			renderJson(js.toJSONString());
		}
	}
	
	@CrossOrigin
	public void add(){
		JSONObject js = new JSONObject();
		try{
			BaseCommodityModel model = getModel(BaseCommodityModel.class, "", true);
			model.set(Const.KEY_DB_CREATE_TIME, System.currentTimeMillis()/1000+"");
			model.set(Const.KEY_DB_DEL, Const.OPTION_DB_NORMAL);
			System.out.println("model:"+model);
			model.save();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			renderJson(JsonKit.toJson(js));
		}catch(Exception e){
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
			renderJson(JsonKit.toJson(js));
		}
	}
	
	@CrossOrigin
	public void update(){
		JSONObject js = new JSONObject();
		try{
			BaseCommodityModel model = getModel(BaseCommodityModel.class, "", true);
			System.out.println("model:"+model);
			model.update();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			renderJson(JsonKit.toJson(js));
		}catch(Exception e){
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
			renderJson(JsonKit.toJson(js));
		}
	}
	
	@CrossOrigin
	public void delete(){
		JSONObject js = new JSONObject();
		try{
			BaseCommodityModel model = getModel(BaseCommodityModel.class, "", true);
			System.out.println("model:"+model);
			model.set(Const.KEY_DB_DEL, Const.OPTION_DB_DELETE);
			model.update();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			renderJson(JsonKit.toJson(js));
		}catch(Exception e){
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
			renderJson(JsonKit.toJson(js));
		}
	}

	
	
	@CrossOrigin
	public void getTableInfo(){
		JSONObject js = new JSONObject();
		try{
			List nameList = DatabaseUtil.getTableInfo(DB_TABLE,DatabaseUtil.TableInfoEnum._ColumnNames);
			List commentList = DatabaseUtil.getTableInfo(DB_TABLE,DatabaseUtil.TableInfoEnum._ColumnComments);
			List filterList = new ArrayList();
			for(int i=0;i<nameList.size();i++){
				System.out.println("private String "+nameList.get(i)+";\n");
				String filterTypeName = tableFilter.get(nameList.get(i));
				if(filterTypeName == null || filterTypeName == ""){
					filterList.add(FilterType.normal);
				}else{
					filterList.add(filterTypeName);
				}	
			}
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			js.put("column_name", nameList);
			js.put("column_filter", filterList);
			js.put("column_comment", commentList);
			renderJson(JsonKit.toJson(js));
		}catch(Exception e){
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
			renderJson(JsonKit.toJson(js));
		}
	}
	
	@CrossOrigin
	public void search(){
		String map = "";
		String key = getPara("key");
		List<BaseCommodityModel> models = BaseCommodityModel.dao.find("select * from "+DB_TABLE+" where "+map+" like '%"+key+"%' and del != 'delete'");
		JSONObject js = new JSONObject();
		if(models!=null && models.size()>=1){
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			js.put(Const.KEY_RES_DATA, models);
			System.out.println(JsonKit.toJson(js));
			renderJson(JsonKit.toJson(js));
		}else{
			System.out.println("model:");
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201); 
			renderJson(js.toJSONString());
		}
	}
}
