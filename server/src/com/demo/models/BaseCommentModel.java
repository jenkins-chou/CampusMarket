package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

//genarate with database table base_comment
//arp.addMapping("base_comment", BaseCommentModel.class);
public class BaseCommentModel extends Model<BaseCommentModel>{
	public static final BaseCommentModel dao = new BaseCommentModel();
	
	public Page<BaseCommentModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from base_comment order by create_time asc");
	}
}