<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息系统安全概论</title>
</head>
<body>
	<div id="cover">内容要点</div>
	<p>
	0.前端对密码的安全性的检测（js）<br>
	1.	密码通过与用户名（salt）连接,将hash值存入数据库的密码字段：<br>
	2.	通过预编译防止sql注入<br>
	3.	注册时通过邮箱激活账号，忘记密码时通过邮箱重置密码（其中链接经过加密；有效期为一天）<br>
	4.	登陆，注册，重置密码等界面设有验证码，防止爬虫<br>
	5.	自定义异常页面，避免在系统出现异常时，网页中泄露系统信息<br>
	6.	基础的日志功能<br>
	7.	登录次数限制，一天只能登录失败五次
	</p>
	
	
	
	<div id="member">小组成员</div>
	<p>
	曹宸 程庚 窦芙蓉 刘艳琳 魏雪田子 徐航(6人)<br>
	全组成员一起讨论，收集资料，测试<br>
	程庚负责实现
	</p>
	<a href="${pageContext.request.contextPath}/Login/" id="Enter">Enter</a>
</body>
</html>