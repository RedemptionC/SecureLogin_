<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/jquery/jquery-3.3.1.min.js"></script>
<!-- 
<script>
$(document).ready(function(){
	$("#username").keyup(function(){
		var name=$(this).val();
		$.ajax({
			type:"post",
			url:"ajax",
			data:{"username":name},
			dataType:"html",
			success:function()
			{
				$("#ajax").html("ajax suc");
			}
			
		})
	});
	$("#password").keyup(function(){
		$.ajax({
			type:"post",
			url:"ajaxjson",
			dataType:"json",
			success:function(msg){
				$.each(msg,function(index,value){
					$("#ajaxjson").append(value+" ")
				})
				
			}
			
		})
		
	})
})
</script>
 -->

</head>
<body>
	index.jsp
	<form action="Login" method="post">
		<label style="width: 50px; float: left; text-align: right;">用户名</label>
		<input id="username" name="username" type="text" /><br /> <label
			style="width: 50px; float: left; text-align: right;">密码</label> <input
			id="password" name="password" type="text" /> <span id="ajax"></span>
		<img id="img" src="<%=request.getContextPath()%>user/check.jpg"
			onclick="refresh()"> <br> <input type="submit" value="登录" />
		<span id="ajaxjson"></span>

	</form>
</body>
</html>