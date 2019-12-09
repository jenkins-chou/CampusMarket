package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

//genarate with database table base_collection
//arp.addMapping("base_collection", BaseCollectionModel.class);
public class BaseCollectionModel extends Model<BaseCollectionModel>{
	public static final BaseCollectionModel dao = new BaseCollectionModel();
	
	public Page<BaseCollectionModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from base_collection order by create_time asc");
	}
}