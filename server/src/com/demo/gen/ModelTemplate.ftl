package ${classPath};
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

//genarate with database table ${tableName}
//arp.addMapping("${tableName}", ${className}.class);
public class ${className} extends Model<${className}>{
	public static final ${className} dao = new ${className}();
	
	public Page<${className}> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from ${tableName} order by create_time asc");
	}
}