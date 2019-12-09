window.onload = function(){
	//alert(server_url);
	readLocal();
}

var readLocal = function(){
	$("#username").val($.cookie(KEY_USERNAME_CACHE));
	$("#password").val($.cookie(KEY_PASSWORD_CACHE));
	if (!isEmpty($.cookie(KEY_USERINFO))) {
		//说明已经登录
		window.location.href = '../';

	}
}

var loginClick = function(){
	var username = $("#username").val();
	var password = $("#password").val();
	var saveLocal = $("#saveLocal").is(':checked');
	loginRequest(username,password,saveLocal);
}
var loginRequest = function(username,password,saveLocal){
	if (username == null ||username == ''||username == undefined) {
		alert('请输入用户名');
	}else if (password == null ||password == ''||password == undefined) {
		alert('请输入密码');
	}else{
		$("#tips").html('');
		showLoading('正在登录');
		$.ajax({
			type: "post",
			dataType: "json",
			url: server_url+URI_LOGIN,
			data: {username:username,password:password},
			success: function (data) {       //data回调信息
			if (data != "" && data.code == 200) {
				//stopLoading();
				console.log(JSON.stringify(data.data));
				setUserInfo(JSON.stringify(data.data));

				if (saveLocal == true) {
					$.cookie(KEY_USERNAME_CACHE,username,{expires:7});
					$.cookie(KEY_PASSWORD_CACHE,password,{expires:7});
				}
				window.location.href = '../';
			}else{
				toastr.error("密码错误或账号不存在","温馨提示");
			}
			},
			complete:function(){
				//console.log('complete');
				stopLoading();
			}
		});
	}
}