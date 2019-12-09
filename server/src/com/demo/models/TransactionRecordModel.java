package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

//genarate with database table transaction_record
//arp.addMapping("transaction_record", TransactionRecordModel.class);
public class TransactionRecordModel extends Model<TransactionRecordModel>{
	public static final TransactionRecordModel dao = new TransactionRecordModel();
	
	public Page<TransactionRecordModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from transaction_record order by create_time asc");
	}
}