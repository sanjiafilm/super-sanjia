$(function() {
	$("input[name=username]").blur(
			function() {
				var userName = $(this).val();
				if (!formObj.checkNull("username", "用户名不能为空")) {
					setMsg("username", "用户名不能为空");
				} else {
					
				$.ajax({
					url:"/user_ajax/checkUserName",
					type:"post",
					data:{"userName":userName},
					dataType:"json",
					success:function(result){
						
						if (result.status==1) {
							$("#username_msg").html("用户名已存在");
						} else if (result.status==0) {
							$("#username_msg").html("<font color='green'>用户名可用</font>");
						}
					},
					error:function(){
						
						alertt("请求失败！");
					}
				});
				}
			});
	//给注册表单注册submit事件
	$("form").submit(function(){
		return register();
	});
});
function register(){
	var userName=$("form input[name=username]").val();
	var userPassword=$("form input[name=password]").val();
	var userPassword2=$("form input[name=password2]").val();
	var userNickName=$("form input[name=nickname]").val();
	var userEmail=$("form input[name=email]").val();
	//var vcode=$("form input[name=valistr]").val();
	var flag=formObj.checkForm();
	alert(userPassword,userName,userEmail,userNickName)
	if(flag){
		$.ajax({
			url:"/user/regist",
			type:"post",
			data:{
		//按照pojo的属性名称修改参数
				  "userName":userName,
				  "userPassword":userPassword,
				  "userNickname":userNickName,
				  "userEmail":userEmail,
				 },
			dataType:"json",
			success:function(result){
				if(result.status==1){
					alert(result.msg);
					window.location.href="/page/login";
				}else if(result.status==0){
					alert(result.msg);
				}
			},
			error:function(){
				alert("请求失败！");
			}
		});
	}
	
	return false;
}
var formObj = {
	checkForm : function() {
		var flag = true;
		// 非空验证
		flag = this.checkNull("username", "用户名不能为空!");
		flag = this.checkNull("password", "密码不能为空") && flag;
		flag = this.checkNull("password2", "确认密码不能为空") && flag;
		// 两次输入的密码是否相同
		flag = this.checkPassword("password", "两次密码不相同") && flag;
		// 邮箱格式
		flag = this.checkEmail("email", "邮箱格式不正确") && flag;
		// 返回flag
		return flag;
	},
	checkNull : function(name, msg) {
		var value = $("input[name=" + name + "]").val();
		if ($.trim(value) == "") {
			this.setMsg(name, msg);
			return false;
		}
		return true;
	},
	checkPassword : function(name, msg) {
		var pwd = $("input[name=" + name + "]").val();
		var pwd2 = $("input[name=" + name + "2]").val();
		if ($.trim(pwd) != "" && $.trim(pwd2) != "") {
			if (pwd != pwd2) {
				this.setMsg(name, msg);
				return false;
			}
		}
		return true;
	},
	checkEmail : function(name, msg) {
		var value = $("input[name=" + name + "]").val();
		var reg = /^\w+@\w+(\.\w+)+$/;
		if (!reg.test(value)) {
			this.setMsg(name, msg);
			return false;
		}
		return true;
	},
	setMsg : function(name, msg) {
		$("#" + name + "_msg").text(msg);
	}
};