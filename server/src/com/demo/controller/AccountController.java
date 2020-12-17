package com.demo.controller;

import java.util.List;
import com.alibaba.fastjson.JSONObject;
import com.demo.models.AccountModel;
import com.demo.models.CouponUserModel;
import com.demo.utils.CheckUtils;
import com.demo.utils.Const;
import com.demo.utils.CrossOrigin;
import com.demo.utils.Log;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.demo.utils.RecordJson;
import com.demo.utils.StatusJson;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

@CrossOrigin
public class AccountController extends Controller{
	public static final String DB_TABLE = "account";//
	public static final String HTML_KEY = "account";//
	
	
	@CrossOrigin
	public void getBalanceAndCoupon(){
		
		String userId = getPara(Const.KEY_DB_USER_ID);
		try {
			
			List<AccountModel> accountModelList = AccountModel.dao.find(AccountModel.getQueryAllByUserId(userId));

			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			
			JSONObject modelJson = new JSONObject();
			if(CheckUtils.checkArrayIsNotNull(accountModelList)){
				modelJson.put("balance", accountModelList.get(0).get("balance")+"");
			}else{
				modelJson.put("balance", "0");
			}
			
			js.put(Const.KEY_RES_MODEL_JSON, modelJson.toString());
			renderJson(js.toJSONString());
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
	public void getEntityById(){
		AccountModel model = (AccountModel) AccountModel.dao.findById(getPara(Const.KEY_DB_ID));
		renderJson(JsonKit.toJson(new RecordJson(Const.KEY_RES_CODE_200, Const.OPTION_SUCCESS, model)));
	}
	
	@CrossOrigin
	public void getAllEntity() {
		ParamUtil param = new ParamUtil(getRequest());
		Page<AccountModel> page = AccountModel.dao.paginate(param.getPageNumber(),
				param.getPageSize(), "select * ", "from "+DB_TABLE+" where del != 'delete'");
		System.out.println(page.getList().toString());
		renderJson(JsonKit.toJson(new PageJson<AccountModel>("0", "", page)));
	}
	
	@CrossOrigin
	public void getAllEntityByUserId() {
		String sql = "select * from "+DB_TABLE+" where user_id = '"+getPara(Const.KEY_DB_USER_ID)+"' and del != 'delete';";
		Log.i(sql);
		List<AccountModel> list = AccountModel.dao.find(sql);
		JSONObject js = new JSONObject();
		js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
		js.put(Const.KEY_RES_DATA, list);
		renderJson(js);
	}
	
	@CrossOrigin
	public void addEntity(){
		try {
			AccountModel model = (AccountModel) getModel(AccountModel.class, "", true);
			model.set(Const.KEY_DB_CREATE_TIME, System.currentTimeMillis()/1000+"");
			model.set(Const.KEY_DB_DEL, "normal");
			System.out.println("model:"+model);
			model.save();
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			renderJson(js.toJSONString());
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
	public void deleteEntity(){
		try {
			AccountModel model = (AccountModel) getModel(AccountModel.class, "", true);
			model.set(Const.KEY_DB_DEL, Const.OPTION_DB_DELETE);
			model.update();
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			renderJson(js.toJSONString());
		} catch (Exception e) {
			// TODO: handle exception
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_500);
			js.put(Const.KEY_RES_MESSAGE, e.toString());
			renderJson(js.toJSONString());
		}
	}
	
	@CrossOrigin
	public void deleteSelectEntity(){
		try {
			String[] ids = getParaValues(Const.KEY_DB_ID);
			for (String id : ids) {
				AccountModel model = (AccountModel) AccountModel.dao.findById(id);
				model.set(Const.KEY_DB_DEL, Const.OPTION_DB_DELETE);
				model.update();
			}
			renderJson(JsonKit.toJson(new StatusJson(Const.KEY_RES_CODE_200, Const.OPTION_SUCCESS, true)));
		} catch (Exception e) {
			// TODO: handle exception
			renderJson(JsonKit.toJson(new StatusJson(Const.KEY_RES_CODE_500,Const.OPTION_FAILED, true)));
		}
	}
	
	//���½ӿ�
	@CrossOrigin
	public void updateEntity(){
		try {
			AccountModel model = (AccountModel) getModel(AccountModel.class, "", true);
			System.out.println(model.toJson());
			model.update();
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			renderJson(js.toJSONString());
		} catch (Exception e) {
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_500);
			js.put(Const.KEY_RES_MESSAGE, e.toString());
			renderJson(js.toJSONString());
		}
	}
	
	//��ʾ�б�
	@CrossOrigin
	public void showHtmlList() {

		Log.i("HTML_KEY:"+HTML_KEY);
		setAttr(Const.KEY_HTML, HTML_KEY);
		render("list.html");
	}
	
	//��ʾ���ҳ
	@CrossOrigin
	public void showHtmlAdd() {

		Log.i("HTML_KEY:"+HTML_KEY);
		setAttr(Const.KEY_HTML, HTML_KEY);
		render("add.html");
	}
	
	//��ʾ�޸�ҳ
	@CrossOrigin
	public void showHtmlModify() {

		Log.i("htmlKey:"+HTML_KEY);
		setAttr(Const.KEY_HTML, HTML_KEY);
		setAttr(Const.KEY_DB_ID, getPara(Const.KEY_DB_ID));
		render("add.html");
	}


}