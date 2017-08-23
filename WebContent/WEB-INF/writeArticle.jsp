<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>写文章</title>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
</head>
<body>
<div style="float: right;">
	<a href="main">返回主页</a>
</div>
<div>
	<b>文章题目：</b><br/>
	<input type="text" id="title" name="title"/>
</div>
<div>
	<b>正文：</b><br/>
	<textarea rows="30" cols="150" id="content" name="content"></textarea>
</div>
<div style="margin-top: 6px">
	<input type="button" style="cursor: pointer;" id="save" name="save" onclick="saveArticle()"  value="保存"/>
</div>

<script type="text/javascript">
	function saveArticle(){
		//$("#save").attr("disabled","disabled");
		//$("#reg-btn").attr("disabled","disabled");
		
		var title=$("#title").val();
		var content=$("#content").val();
		if(!title){
			alert("文章题目不能为空")
			return;
		}
		if(!content){
			alert("文章内容不能为空");
			return;
		}
		
		$.ajax({
			url:'saveArticle',
			data:{Title:title,Content:content},
			type:'post',
			datatype:'json',
			
			success:function(data){
				if(data.success){
					alert("文章保存成功！");
					
					$("#title").val("");//文章保存成功后，将题目还有正文内容清空，防止重复提交
					$("#content").val("");
				}else{
					alert("文章保存失败！");
					//$("#save").removeAttr("disabled");//利用使按钮失效的方法，防止文章重复提交
				}
			},
		});
		
	}


</script>
</body>
</html>