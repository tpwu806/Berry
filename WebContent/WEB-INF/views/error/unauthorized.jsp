<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE html>
<html>
<head>
	<title>Berry</title>
</head>
<body>

<c:url var="logoutUrl" value="/logout"/>

<h1 style="text-align: center; color: red;">
    您没有权限访问此页面, 有疑问请跟Berry联系.  请点击这里<span><a href="${logoutUrl}">退出</a></span>.
</h1>


</body>
</html>
