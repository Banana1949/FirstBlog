<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文章的详细信息</title>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>

</head>
<body>
<div>
	<a href="main">返回主页</a>
</div>



<div style="margin-left: 50px;margin-top: 50px;" id="title_id">
	<h1 >${requestScope.get("title") }</h1>
</div>

<div style="margin-top: 30px;margin-left: 30px;" id="content_id">
	${requestScope.get("content") }

</div>

<script type="text/javascript">
	
	/* $(document).ready(function(){
		var Ohref=window.location.href;
		var article_id=Ohref.split("?aid=");
		
		$.ajax({
			url:'info',
			data:{article_id:article_id},
			type:'post',
			datatype:'json',
			
			success:function(data){
				if(data.success){
					
				}
			}
		});
	}); */
	

</script>






</body>
</html>