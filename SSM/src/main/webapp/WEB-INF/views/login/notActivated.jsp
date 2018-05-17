<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>not activated</title>
<style type="text/css">
#errormsg{
	color:red;
	font-size: 50px;
}
</style>
</head>
<body>
<span id="errormsg">请先激活</span>
<a href="${pageContext.request.contextPath}/Login/activate">点击即发送激活邮件到邮箱</a>
	<br><a href="/SSM/Login/">Back to Login</a>

</body>
</html>