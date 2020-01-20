//后台服务器访问地址
var server_url = 'http://zzj.mynatapp.cc';

//接口URI  --不用修改
var URI_LOGIN = '/base_user/login';
var URI_REGISTER = '/base_user/register';
var URI_UPDATE = '/base_user/updateUserInfo';
var URI_GET_ALL_USERTYPE = '/base_user_type/getAll';
var URI_GET_MODULE = '/base_menu/getModuleList';
var URI_GET_MENU = '/base_menu/getMenuList';
var URI_ADD_MENU = '/base_menu/addMenu';
var URI_DELETE_MENU = '/base_menu/deleteMenu';

//公共增删改查URI   --不用修改
var URI_BASE_GET_ALL = "/getAll";
var URI_BASE_DELETE = "/delete";
var URI_BASE_ADD = "/add";
var URI_BASE_GET = "/get";
var URI_BASE_UPDATE = "/update";
var URI_BASE_SEARCH = "/search";
var URI_BASE_GET_TABLEINFO = "/getTableInfo";

//业务部分URI
var URI_BASE_COLLECTION = "/base_collection";
var URI_BASE_COMMENT = "/base_comment";
var URI_BASE_COMMODITY = "/base_commodity";
var URI_BASE_MENU = "/base_menu";
var URI_BASE_MESSAGE = "/base_message";
var URI_BASE_MODULE = "/base_module";
var URI_BASE_ORDER = "/base_order";
var URI_BASE_PLATE = "/base_plate";
var URI_BASE_TYPE = "/base_type";
var URI_BASE_USER = "/base_user";
var URI_BASE_USER_TYPE = "/base_user_type";
var URI_TRANSACTION_RECORD = "/transaction_record";



/*cookie 缓存key*/
const KEY_USERNAME_CACHE = "username_cache";
const KEY_PASSWORD_CACHE = "password_cache";
const KEY_USERINFO = "userinfo";

var showLoading = function(msg){
	$('body').loading({
			loadingWidth:240,
			title:'温馨提示',
			name:'loading',
			discription:msg,
			direction:'column',
			type:'origin',
			// originBg:'#71EA71',
			originDivWidth:40,
			originDivHeight:40,
			originWidth:6,
			originHeight:6,
			smallLoading:false,
			loadingMaskBg:'rgba(0,0,0,0.2)'
		});
}
var setUserInfo = function(userinfo){
	$.cookie(KEY_USERINFO,userinfo,{expires:7});
}
var getUserInfo = function(){
	return $.cookie(KEY_USERINFO);
}
var logout = function(){
	$.cookie(KEY_USERINFO,null);
}

var stopLoading = function(){
	removeLoading('loading');
}

var isEmpty = function(text){
 	return text == null ||text == ''||text == undefined ||text == 'null' ||text =='NULL';
}

var equals = function(val1,val2){
	return val1 === val2;
}

var dialog = function(msg){
	alert(msg);
}

var getText = function(text,defaultText){
	if (isEmpty(text)) {
		return defaultText;
	}else{
		return text;
	}
}

//获取url的参数
function getQueryVariable(variable)
{
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
}

//获取模块列表
var getModuleList = function(){
	$content = $("#module-bar")
	var userinfo = JSON.parse(getUserInfo());
	var user_type = userinfo.type;
	$.ajax({
			type: "post",
			dataType: "json",
			url: server_url+URI_GET_MODULE,
			data: {user_type:user_type},
			success: function (data) {       //data回调信息
				//console.log("getModuleList"+JSON.stringify(data));
			if (data != "" && data.code == 200) {
				//dialog('注册成功，请前往登录')
				showModule($content,data.data);
			}
			},
			complete:function(){
				//console.log('complete');
				stopLoading();
			}
		});
}

var showModule = function($content,moduleList){
	if (moduleList == null) {
		return;
	}
	$content.empty();
	indexModuleMenu(moduleList);
	for (var i = 0; i < moduleList.length; i++) {
		//console.log(moduleList[i]);
		if (i==0) {
			var html = "<li class='active'><a href = '#' onclick='getMenu("+moduleList[i].id+")'>"+moduleList[i].menu_name+"</a></li>";
			getMenu(moduleList[i].id);
		}else{
			var html = "<li><a href = '#' onclick='getMenu("+moduleList[i].id+")'>"+moduleList[i].menu_name+"</a></li>";
		}
		$content.append(html);
	}
	initModuleListStyle();
}

var indexModuleMenu = function(moduleList){
	for (var i = 0; i < moduleList.length; i++) {
		for (var j = i+1; j < moduleList.length; j++) {
			if (moduleList[i].menu_index > moduleList[j].menu_index) {
				var temp = moduleList[i];
				moduleList[i] = moduleList[j];
				moduleList[j] = temp;
			}
		}
	}
}

var initModuleListStyle =  function(){
	let allModule = document.querySelectorAll("#module-bar li");
    for (var i = 0; i < allModule.length; i++) {
    	allModule[i].onclick = function(){
    		navigateModuleList(this);
    	}
    }
}

var navigateModuleList = function(element){
	let _class = 'active';  // 需要添加的类名
	let allModule = document.querySelectorAll("#module-bar li");
	for (var j = allModule.length - 1; j >= 0; j--) {
    	$(allModule[j]).removeClass(_class);
    }
    $(element).addClass(_class);
}

//获取左侧菜单
var getMenu = function(module_id){
	var userinfo = JSON.parse(getUserInfo());
	var user_type = userinfo.type;

	$.ajax({
			type: "post",
			dataType: "json",
			url: server_url+URI_GET_MENU,
			data: {user_type:user_type,module_id:module_id},
			success: function (data) {       //data回调信息
			//console.log("getMenu"+JSON.stringify(data));
			if (data != "" && data.code == 200) {
				//dialog('注册成功，请前往登录')
				showMenu(data.data);
			}else{
				resetMenu();
			}
			},
			complete:function(){
				//console.log('complete');
				stopLoading();
			}
		});
}

var showMenu = function(menuList){
	resetMenu();
	var container = $("#sidebar-menu");
	if (menuList == null || menuList.length ==0) {
		return;
	}
	indexModuleMenu(menuList);
	for (var i = 0; i < menuList.length; i++) {
		//menuList[i]
		var html;
		html = "<li><a href='"+menuList[i].menu_url+"' target='m' ><i class='fa "+getText(menuList[i].menu_icon,"fa-th-large")+"'></i> <span>"+menuList[i].menu_name+"</span></a></li>";
		container.append(html);
	}
	initMenuStyle();
}

var resetMenu = function(){
	var container = $("#sidebar-menu");
	container.empty();
	container.append("<li class='header'>HEADER</li>");
}

var initMenuStyle =  function (){
    //alert(1);
    let allMenu = document.querySelectorAll("#sidebar-menu a");
    for (var i = 0; i < allMenu.length; i++) {
      allMenu[i].onclick=function(){
        if (!$(this.parentNode).hasClass('treeview')) {
            navigateMenu(this);
        }
        var text = $(this).text();
        $('#page-detail').text(text);
      }
    }
  }
var navigateMenu = function (element){
    let _class = 'active';  // 需要添加的类名
    let url = '';     // 需要跳转的URL
    let parent = element.parentNode.parentNode;
    let allMenu = document.querySelectorAll("#sidebar-menu li");
    for (var i = 0; i < allMenu.length; i++) {
      if (!$(allMenu[i]).hasClass('treeview')) {
        $(allMenu[i]).removeClass(_class);
      }
    }
    $(element.parentNode).addClass(_class);
}
