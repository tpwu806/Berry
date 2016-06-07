<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户密码</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/qilu.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/dashboard.css"/>" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<%@ include file="../includes/header.jspf" %>

<div class="container-qy">
    <div class="qy-con min-h">

        <form:form method="POST" commandName="pwdObject"
                   action="${pageContext.request.contextPath}/user/updatepasswordform/updatepassword"
                   accept-charset="UTF-8">

        <div class="qy-input">

            <div class="yh-l">
                <div class="hy-list text-r">旧密码 *:</div>
                <div class="hy-list text-r">新密码 *:</div>
                <div class="hy-list text-r">确认密码 *:</div>
            </div>
            <div class="yh-r">
                <div class="hy-list">
                    <form:input path="oldPassword" type="password" maxlength="20" class="hy-in" placeholder="请输入旧密码"></form:input>
                    <form:errors path="oldPassword" class="error"/>
                </div>
                <div class="hy-list">
                    <form:input path="newPassword1" type="password" maxlength="20" placeholder="请输入新密码" class="hy-in"></form:input>
                    <form:errors path="newPassword1" class="error"/>
                </div>
                <div class="hy-list">
                    <form:input path="newPassword2" type="password" maxlength="20" placeholder="请输入新密码" class="hy-in"></form:input>
                    <form:errors path="newPassword2" class="error"/>
                </div>
            </div>
            <button class="btn btn-lg btn-primary btn-login" type="submit">保存</button>
            <p class="btxx">*必填项</p>
        </div>

        </form:form>

    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<c:url value="/js/jquery-1.11.1.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootbox.js"/>" type="text/javascript"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script>

    $(document).ready(function() {

        // If message_key is not empty, display server message upon page load in bootbox
        <c:if test="${not empty MESSAGE_KEY}">
            showServerMessage('${MESSAGE_KEY}');
        </c:if>

        bootbox.setDefaults({
            locale: "zh_CN"
        });

    });

    function showServerMessage(message) {
        bootbox.alert(message);
    }
</script>



</body>
</html>



