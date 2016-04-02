<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/css/font-awesome.min.css"/>" media="screen" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/kilo.css"/>" media="screen" rel="stylesheet" type="text/css">
<style>
.login-input{
    margin-top:10px;
	width:15%;
	height:25px;
	padding:3px 5px;
	font-size:14px;
	color:#333;
	background-color:#fff;
	background-image:none;
	border:1px solid #ccc;
	border-radius:4px;
}

.login-body{
    text-align:center;
	background-color:#DDDDDD;
}
.login-core{
	text-align:center;
	width:400px; 
	height:250px;
	background-color:#FFFFFF;
}
.login-img{
	margin-top:80px;
}

.btn-lg{
    width: 15%;
	margin-top:20px;
	background-color:#66CC00;
}
.label-link{
	font-color:#66CC00;
	font-size:15px;
}
.login-font{
	margin-top:10px;
}
</style>
</head>

<body class="login-body">

	<form:form method="POST" commandName="userForm"
            action="${pageContext.request.contextPath}/login"
            accept-charset="UTF-8"> 
        <div><img src="${pageContext.request.contextPath}/images/login_berry.png" width="60" height="60" id="login_berry" class="login-img"></div>
       
        <div style="margin-top:20px;" > </div>
        
        <div class="">
        	<div class="login-font" >   
	        	Username or email address  
	        </div>
	        <div>       
	            <form:input id="username" path="username" type="text" class="login-input" maxlength="30"/>      
	        </div>
	        <div class="login-font" >
	        	Password &nbsp&nbsp&nbsp<a href="" class="label-link">Forgot password?</a>
	        </div>
	        <div>
	        	<form:input id="userpass" path="userpass" type="text" class="login-input" maxlength="30"/>
	        </div>
	        <div>        
	      		<button id="submitButton" class="btn-lg" type="submit">登录</button>        
	        </div>
	     </div>
 	</form:form>  


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<c:url value="/js/jquery-1.11.1.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootbox.js"/>" type="text/javascript"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>

<script>

   // $('#submitButton').on('click', function(e) {
		
       // e.preventDefault();
        //$('form').submit();
   // });


</script>


</body>

</html>



