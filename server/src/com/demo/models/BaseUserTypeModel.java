package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

//genarate with database table base_user_type
//arp.addMapping("base_user_type", BaseUserTypeModel.class);
public class BaseUserTypeModel extends Model<BaseUserTypeModel>{
	public static final BaseUserTypeModel dao = new BaseUserTypeModel();
	
	public Page<BaseUserTypeModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from base_user_type order by create_time asc");
	}
}