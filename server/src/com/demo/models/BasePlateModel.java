package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

//genarate with database table base_plate
//arp.addMapping("base_plate", BasePlateModel.class);
public class BasePlateModel extends Model<BasePlateModel>{
	public static final BasePlateModel dao = new BasePlateModel();
	
	public Page<BasePlateModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from base_plate order by create_time asc");
	}
}