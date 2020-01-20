package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

//genarate with database table base_wishlist
//arp.addMapping("base_wishlist", BaseWishlistModel.class);
public class BaseWishlistModel extends Model<BaseWishlistModel>{
	public static final BaseWishlistModel dao = new BaseWishlistModel();
	
	public Page<BaseWishlistModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from base_wishlist order by create_time asc");
	}
}