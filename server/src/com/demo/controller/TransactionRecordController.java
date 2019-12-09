package com.demo.controller;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.TransactionRecordModel;
import com.demo.utils.Const;
import com.demo.utils.CrossOrigin;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.demo.utils.DatabaseUtil;

@CrossOrigin
public class TransactionRecordController  extends Controller {
	
	public static final String DB_TABLE = "transaction_record";
	
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
	
	@CrossOrigin
	public void getAll(){
		List<TransactionRecordModel> models = TransactionRecordModel.dao.find("select * from "+DB_TABLE+" where del != 'delete'");
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
		List<TransactionRecordModel> models = TransactionRecordModel.dao.find("select * from "+DB_TABLE+" where id = "+id+" and  del != 'delete'");
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
			TransactionRecordModel model = getModel(TransactionRecordModel.class, "", true);
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
			TransactionRecordModel model = getModel(TransactionRecordModel.class, "", true);
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
			TransactionRecordModel model = getModel(TransactionRecordModel.class, "", true);
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
		List<TransactionRecordModel> models = TransactionRecordModel.dao.find("select * from "+DB_TABLE+" where "+map+" like '%"+key+"%' and del != 'delete'");
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
