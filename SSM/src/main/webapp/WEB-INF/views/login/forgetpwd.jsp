<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
 <!-- Font Awesome -->
 <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/font-awesome.css">
 <!-- Ionicons -->
 <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/ionicons.css">
 <!-- Theme style -->
 <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/AdminLTE.css">
 <!-- iCheck -->
 <link rel="stylesheet" href="${pageContext.request.contextPath}/adminlte/blue.css">
<style type="text/css">
#errormsg{
	color:red;
	font-size: 10px;
}
</style>
<title>forgerPassword</title>
</head>

<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="https://adminlte.io/themes/AdminLTE/index2.html"><b>Forget</b> password?</a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">fill in to reset your password</p>

    <form action="forgetpwd1" method="post" id="login">
      <div class="form-group has-feedback">
        <input class="form-control" placeholder="name" id="name" name="name" type="text">
      </div>
      <div class="form-group has-feedback">
        <input class="form-control" placeholder="email" type="email" id="email" name="email">
      </div>
	   <div class="form-group has-feedback">
        <input class="form-control" placeholder="code" type="text" id="code" name="code">
		</div>
      	   <div class="form-group">
		<img  id="codeimg" class="col-md-5" src="<%=request.getContextPath()%>/checkcode/" onclick="refresh()">
		</div>
      <div class="row">
        <!-- /.col -->
        <div class="col-md-offset-7 col-md-5">
          <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
          				<span id="errormsg">${errormsg}</span>
          
        </div>
        <!-- /.col -->
      </div>
    </form>


	<br><a href="/SSM/Login/">Back to Login</a>

  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->


<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
  });
</script>
<script src="${pageContext.request.contextPath}/jquery/jquery.validate.min.js"></script>
<script type="text/javascript">
function getRootPath() {
	  var pathName = window.location.pathname.substring(1);
	  var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
	  if (webName == "") {
	    return window.location.protocol + '//' + window.location.host;
	  }
	  else {
	    return window.location.protocol + '//' + window.location.host + '/' + webName;
	  }
	}
function refresh() {  
    var url = getRootPath()+ "/checkcode/?number="+Math.random();  
    $("#codeimg").attr("src",url); 
}  
$(document).ready(function () {
    $("#login").validate({
        rules: {
            name: "required",
            pwd:"required",
            code:"required"
        },
        messages: {
            name: "*",
            pwd:"*",
            code:"*"
        }

    })

})
</script>
</html>