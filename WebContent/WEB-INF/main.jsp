<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
</head>
<body>
<!-- 如果不是登陆用户，则不用显示当前用户几个字，也不有退出登陆按钮，而需要有新用户注册和用户登陆按钮 -->
<div>
	<div style="float: left;">
		<c:if test="${session_user.username!=null}">
			当前用户：${session_user.username }
		</c:if>
	</div>
	<div style="float: right;">
		<c:if test="${session_user.username==null}">
			<a style="text-decoration: none;" href="login" style="margin-right:6px">登陆</a>
			<a style="text-decoration: none;" href="register" style="margin-right:6px">新用户注册</a>
		</c:if>
		<c:if test="${session_user.username!=null}">
			<input style="margin-right:6px; cursor: pointer; " type="button" id="wrt-but" onclick="writeArtical()" value="写文章"/>
			<a style="text-decoration: none;" href="logout">退出登陆</a>
		</c:if>
	</div>
</div>
<br>
	<div style="background-color: pink;margin-top:30px;text-align:center;" id="article-list">
	
	</div>

<script type="text/javascript">
	function writeArtical(){
		window.location.href="writeArticle";
	}
	
	$(document).ready(function(){
		
		$.ajax({
			url:'listInfo',
			type:'post',
			datatype:'json',
			
			success:function(data1){
				if(data1.success){
					//遍历循环
					$.each(data1.data, function(index, value, array) {
						  var a = '<a style="text-decoration:none" href="article_info?aid='+value.article_id+'">'+value.title+'</a><br>';
						  $("#article-list").append(a);
						  //var c=value.content;
						  $("#article-list").append("添加文章的描述内容");
						  $("#article-list").append("<br>");
						});
				}else{
					
				}
			},
		});
		
	});
	
	
	

</script>	



</body>
</html>