package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

//genarate with database table base_order
//arp.addMapping("base_order", BaseOrderModel.class);
public class BaseOrderStateModel extends Model<BaseOrderStateModel>{
	public static final BaseOrderStateModel dao = new BaseOrderStateModel();
	
	public Page<BaseOrderStateModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from base_order_state order by create_time asc");
	}
}