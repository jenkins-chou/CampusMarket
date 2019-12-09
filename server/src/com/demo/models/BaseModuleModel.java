package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

//genarate with database table base_module
//arp.addMapping("base_module", BaseModuleModel.class);
public class BaseModuleModel extends Model<BaseModuleModel>{
	public static final BaseModuleModel dao = new BaseModuleModel();
	
	public Page<BaseModuleModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from base_module order by create_time asc");
	}
}