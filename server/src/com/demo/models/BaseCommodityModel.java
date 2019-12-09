package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

//genarate with database table base_commodity
//arp.addMapping("base_commodity", BaseCommodityModel.class);
public class BaseCommodityModel extends Model<BaseCommodityModel>{
	public static final BaseCommodityModel dao = new BaseCommodityModel();
	
	public Page<BaseCommodityModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from base_commodity order by create_time asc");
	}
}