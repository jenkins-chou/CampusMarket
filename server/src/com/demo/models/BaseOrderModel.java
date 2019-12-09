package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

//genarate with database table base_order
//arp.addMapping("base_order", BaseOrderModel.class);
public class BaseOrderModel extends Model<BaseOrderModel>{
	public static final BaseOrderModel dao = new BaseOrderModel();
	
	public Page<BaseOrderModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from base_order order by create_time asc");
	}
}