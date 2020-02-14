package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

//genarate with database table shipping_address
//arp.addMapping("shipping_address", ShippingAddressModel.class);
public class ShippingAddressModel extends Model<ShippingAddressModel>{
	public static final ShippingAddressModel dao = new ShippingAddressModel();
	
	public Page<ShippingAddressModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from shipping_address order by create_time asc");
	}
}