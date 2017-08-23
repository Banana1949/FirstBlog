<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新用户注册</title>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
</head>
<body>
<div align="center">
<form>
	<table>
		<tr>
			<th>新用户注册</th>
		</tr>
		<tr>
			<td>用户名</td>
			<td><input type="text" id="username" name="username" onblur="checkUserName()"/> <span id="status"></span></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type="password" id="password" name="password" onblur="CheckPassword()" /> <span id="checkPWD"></span></td>
		</tr>
		<tr>
			<td>确认密码：</td>
			<td><input type="password" id="confirmpassword" name="confirmpassword" onblur="ConfirmPassword()"/><span id="confirm"></span></td>
		</tr>
		<tr>
			<td><input id="reg-btn" style="cursor: pointer;" type="button" onclick="Register()" value="注册"/></td>
			<td><a href="login" >账号登陆</a></td>
		</tr>
	
	
	</table>
</form>

</div>
<script type="text/javascript">
	
	//验证用户名是否已存在
	function checkUserName(){
		var username=$("#username").val();
		if(!username){		
			$("#status").html("<font color='red'>用户名不能为空</font>");
			return;
		}
		$.ajax({
			url:'check',
			data:{username:username},
			type:'post',
			datatype:'json',
			async:false,
			success:function(data){
				if(data.success){
					$("#status").html("<font color='red'>用户名已存在</font>");
				}else{
					$("#status").html("");
				}
			},
		});
	}
	
	//验证密码
	function CheckPassword(){
		var password=$("#password").val();
		var confirmpassword=$("#confirmpassword").val();
		if(!password){
			$("#checkPWD").html("<font color='red'>密码不能为空</font>");
			return;
		}else{
			$("#checkPWD").html("");
			return;
		}
		
		if(confirmpassword=="" || confirmpassword==null){
			return;
		}
		if(!(password==confirmpassword)){
			$("#confirm").html("<font color='red'>确认密码和密码不一致</font>");
		}else{
			$("#confirm").html("");
			
		}
		
	}
	
	
	//检查两次输入密码是否一致
	function ConfirmPassword(){
		var password=$("#password").val();
		var confirmpassword=$("#confirmpassword").val();
		if(password){
			if(!(password==confirmpassword)){
				$("#confirm").html("<font color='red'>确认密码和密码不一致</font>");
			}else{
				$("#confirm").html("");
				
			}
		}
	}
	
	//进行注册
	function Register(){
		$("#reg-btn").attr("disabled","disabled");
		var username=$("#username").val();
		var password=$("#password").val();
		/* var confirmpassword=$("#confirmpassword").val();
		//判断用户名不能为空
		if(!username){
			$("#status").html("<font color='red'>用户名不能为空</font>");
		}else{
			$("#status").html("");
		}
		//判断密码不能为空
		if(!password){
			$("#checkPWD").html("<font color='red'>密码不能为空</font>");
		}else{
			$("#checkPWD").html("");
		}
		
		//判断密码和确认密码是否相同
		if(!(password==confirmpassword)){
			$("#confirm").html("<font color='red'>密码错误</font>");
		}else{
			$("#confirm").html("");
			
		} */
		checkUserName();
		CheckPassword();
		ConfirmPassword();
		
		//判断没有红字
		if(($("#confirm").html()=="") && ($("#status").html()=="") && ($("#checkPWD").html()=="")){
			$.ajax({
				url:'register',
				data:{username:username,password:password},
				type:'post',
				datatype:'json',
				
				success:function(data){
					if(data.success){
						alert("注册成功");
						window.location.href="main";
					}
				},
				
			});
		}else{
			$("#reg-btn").removeAttr("disabled");
		}
		
	}


</script>

</body>
</html>