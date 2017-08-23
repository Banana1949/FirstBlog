<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户登陆</title>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript">
function checkInfo(){
	
	var userName=$("#userName").val();
	var passWord=$("#passWord").val();
	if(!userName || !passWord){
		alert("用户名或密码不能为空！");
		return;
	}
	$.ajax({
		url:"login",
		//data:$("#loginForm").serialize(),
		data:{userName:userName,passWord:passWord},
		type:"post",
		datatype:'json',
		success:function(data){
			if(data.success){
				//alert("登陆成功");
				window.location.href="main";
			}else{
				alert(data.msg);
			}
		},
		
	});
}

</script>
</head>
<body>
<div align="center">
<form id="loginForm" >
	<table>
		<tr>
			<th>用户登陆</th>
		</tr>
		<tr>
			<td>用户名：</td>
			<td><input type="text" id="userName" name="userName"></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type="password" id="passWord" name="passWord"></td>
		</tr>
		<tr>
			<td><input id="sub-bon" type="button" style="cursor: pointer;" onclick="checkInfo()" value="登陆"/></td>
			<td><a href="register">新用户注册</a></td>
		</tr>
	</table>
	
</form>
</div>

</body>
</html>