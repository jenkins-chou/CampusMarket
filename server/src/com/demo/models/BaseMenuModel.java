package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

//genarate with database table base_menu
//arp.addMapping("base_menu", BaseMenuModel.class);
public class BaseMenuModel extends Model<BaseMenuModel>{
	public static final BaseMenuModel dao = new BaseMenuModel();
	
	public Page<BaseMenuModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from base_menu order by create_time asc");
	}
}