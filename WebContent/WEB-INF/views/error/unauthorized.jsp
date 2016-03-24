<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<%--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  ~ Grasshopper Technologies Corporation CONFIDENTIAL
  ~
  ~ Copyright (c) Grasshopper Technologies Corporation 2012 - 2015
  ~ All rights reserved.
  ~
  ~ NOTICE: This unpublished material is proprietary to Grasshopper
  ~ Technologies Corporation. All information contained herein is,
  ~ and remains the property of Grasshopper Technologies Corporation.
  ~ The methods and techniques contained herein are proprietary to
  ~ Grasshopper Technologies Corporation and are considered trade
  ~ secrets and/or confidential, and may be covered by China, U.S.
  ~ and Foreign Patents, patents in process, and are protected by
  ~ trade secret or copyright law. Reproduction or distribution, in
  ~ whole or in part, is strictly forbidden except by express written
  ~ permission of Grasshopper Technologies Corporation.
  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~--%>

<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE html>
<html>
<head>
	<title>齐鲁软件园服务平台</title>
</head>
<body>

<c:url var="logoutUrl" value="/logout"/>

<h1 style="text-align: center; color: red;">
    您没有权限访问此页面, 有疑问请跟济南齐鲁软件园发展中心联系.  请点击这里<span><a href="${logoutUrl}">退出</a></span>.
</h1>


</body>
</html>
