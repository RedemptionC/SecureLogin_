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
<title>Register</title>
</head>

<body class="hold-transition login-page">
<div class="login-box">

  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">fill in the form to get a new account</p>

    <form action="Register" method="post" id="login" onsubmit="return confirm2()">
      <div class="form-group has-feedback">
        <input class="form-control" placeholder="name" id="name" name="name" type="text">
        <span id="namemsg" class="errormsg"></span>
      </div>
       <div class="form-group has-feedback">
        <input class="form-control" placeholder="email" id="email" name="email" type="email">
                <span id="emailmsg" class="errormsg"></span>
        
      </div>
      <div class="form-group has-feedback">
        <input class="form-control" placeholder="Password" type="password" id="pwd" name="pwd" onkeyup="return check_pwd()">
      </div>
     <div id="pwdLength" class="col-md-1">
          <span style=" background-color:#999;display:none;">弱</span>
          <span style=" background-color:#666;display:none;">中</span>
          <span style=" background-color:#555;display:none;">强</span>
     </div>
           <div class="form-group has-feedback">
        <input class="form-control" placeholder="ConfirmPassword" type="password" id="ConfirmPwd" name="ConfirmPwd" >
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
          				<span id="errormsg">${errormsg}</span><br>
          		<a href="/SSM/Login/">Back to Login</a>
          	
          
        </div>
        <!-- /.col -->
      </div>
    </form>


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
function confirm2()
{
	var name="用户名 :"+document.getElementById("name").value;
	var email="email :"+document.getElementById("email").value;
	var c=confirm(name+email);
	if(c==true)
		return true;
	else
		return false;
}
var reg1 = /(^\d{6,}$)|(^[a-zA-Z]{6,}$)|(^[^a-zA-Z0-9]{6,}$)/; //数字，字母或符号其中的一种
var reg7 = /\d*\D*((\d+[a-zA-Z]+[^0-9a-zA-Z]+)|(\d+[^0-9a-zA-Z]+[a-zA-Z]+)|([a-zA-Z]+\d+[^0-9a-zA-Z]+)|([a-zA-Z]+[^0-9a-zA-Z]+\d+)|([^0-9a-zA-Z]+[a-zA-Z]+\d+)|([^0-9a-zA-Z]+\d+[a-zA-Z]+))\d*\D*/; //数字字母字符任意组合
function check_pwd(){
    var pwd = document.getElementById("pwd").value;
    if (pwd.length < 6) {
        $("#pwdPrompt div:eq(1)").html("密码长度不能小于6位");
        return false;
    } else {
        $("#pwdPrompt div:eq(1)").css("display", "none");
        $("#pwdPrompt div:eq(0)").css("display", "block");
        if (reg1.test(pwd)) {
            $("#pwdLength span:eq(0)").css("display", "block");
            $("#pwdLength span:eq(1)").css("display", "none");
            $("#pwdLength span:eq(2)").css("display", "none");
            return true;
        }
        else if (!reg7.test(pwd)) {
            $("#pwdLength span:eq(0)").css("display", "none");
            $("#pwdLength span:eq(1)").css("display", "block");
            $("#pwdLength span:eq(2)").css("display", "none");
            return true;
        }
        else {
            $("#pwdLength span:eq(0)").css("display", "none");
            $("#pwdLength span:eq(1)").css("display", "none");
            $("#pwdLength span:eq(2)").css("display", "block");
            return true;
        }
        return true;
    }
}
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
			pwd:{
				required:"true",
				minlength:8
			},
            code:"required",
            email:"required",
            ConfirmPwd:{
            	equalTo:"#pwd"
            }
        },
        messages: {
            name: "*",
			pwd:{
				required:"*",
				minlength:"密码不得少于八位"
			},
            code:"*",
            email:"*",
            ConfirmPwd:{
            	equalTo:"两次密码不一致"
            }
        }

    })

})
</script>
</html>