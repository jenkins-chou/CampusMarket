package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

//genarate with database table base_message
//arp.addMapping("base_message", BaseMessageModel.class);
public class BaseMessageModel extends Model<BaseMessageModel>{
	public static final BaseMessageModel dao = new BaseMessageModel();
	
	public Page<BaseMessageModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from base_message order by create_time asc");
	}
}