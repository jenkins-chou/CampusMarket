package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

//genarate with database table base_user
//arp.addMapping("base_user", BaseUserModel.class);
public class BaseUserModel extends Model<BaseUserModel>{
	public static final BaseUserModel dao = new BaseUserModel();
	
	public Page<BaseUserModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from base_user order by create_time asc");
	}
}