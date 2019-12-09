var registerClick = function(){
	var username = $("#username").val();
	var password = $("#password").val();
	var confirm_pass = $("#confirm_pass").val();
	if (isEmpty(username) || isEmpty(password) || isEmpty(confirm_pass)) {
		dialog('请输入必填信息');
	}else{
		if (!equals(password,confirm_pass)) {
			dialog('密码不一致');
		}else{
			$("#confirm_pass").val('');
			registerRequest(username,password);
		}
	}
}

var registerRequest = function(username,password){
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
			url: server_url+URI_REGISTER,
			data: {username:username,password:password,type:'1'},
			success: function (data) {       //data回调信息
			if (data != "" && data.code == 200) {
				//dialog('注册成功，请前往登录')
				toastr.success("注册成功，请前往登录","温馨提示");
			}else{
				//$("#tips").html('注册失败');
				toastr.error("注册失败","温馨提示");
			}
			},
			complete:function(){
				console.log('complete');
				stopLoading();
			}
		});
	}
}