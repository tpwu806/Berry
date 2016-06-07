<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户列表</title>

    <!-- Bootstrap -->
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/dashboard.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/qilu.css"/>" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<!-- Header bar -->
<%@ include file="../includes/header.jspf" %>

<div class="container-fluid main">

    <div class="main">
        <div class="table-responsive">
            <table class="table table-striped text-center">
                <thead>
                <tr>
                    <th class="text-center">姓名</th>
                    <th class="text-center">手机</th>
                    <th class="text-center">办公电话</th>
                    <th class="text-center">性别</th>
                    <th class="text-center">岗位</th>
                    <th class="text-center">权限</th>
                    <th class="text-center">企业名称</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${userList.content}">
                    <tr>
                        <td>${user.gName}</td>
                        <td>${user.phonenum}</td>
                        <td>${user.jobPhonenum}</td>

                        <!-- Translate gender information into Readable Chinese -->
                        <c:choose>
                            <c:when test="${user.gender eq 'M'}">
                                <td>男</td>
                            </c:when>
                            <c:when test="${user.gender eq 'F'}">
                                <td>女</td>
                            </c:when>
                            <c:otherwise>
                                <td></td>
                            </c:otherwise>
                        </c:choose>

                        <!-- Translate jobclass information into Readable Chinese -->
                        <c:choose>
                            <c:when test="${user.jobClass eq 'JOBCLASS_BASIC'}">
                                <td>员工</td>
                            </c:when>
                            <c:when test="${user.jobClass eq 'JOBCLASS_MID'}">
                                <td>中层</td>
                            </c:when>
                            <c:when test="${user.jobClass eq 'JOBCLASS_EXEC'}">
                                <td>高管</td>
                            </c:when>
                            <c:otherwise>
                                <td></td>
                            </c:otherwise>
                        </c:choose>

                        <!-- Translate group/role information into Readable Chinese -->
                        <c:choose>
                            <c:when test="${user.group eq 'G_USRREG'}">
                                <td>注册用户</td>
                            </c:when>
                            <c:when test="${user.group eq 'G_USRMGMT'}">
                                <td>企业用户</td>
                            </c:when>
                            <c:when test="${user.group eq 'G_ORGREG'}">
                                <td>园区用户</td>
                            </c:when>
                            <c:when test="${user.group eq 'G_ORGMGMT'}">
                                <td>园区领导用户</td>
                            </c:when>
                            <c:when test="${user.group eq 'G_ORGMOD'}">
                                <td>监管用户</td>
                            </c:when>
                            <c:when test="${user.group eq 'G_ORGADMIN'}">
                                <td>系统管理员</td>
                            </c:when>
                            <c:otherwise>
                                <td></td>
                            </c:otherwise>
                        </c:choose>

                        <td>${user.orgname}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <nav class="search">
            <ul class="pagination pagination-lg">
                ${userList.printLeftArrows()}
                ${userList.printPageNumbers()}
                ${userList.printRightArrows()}
            </ul>
        </nav>

    </div>
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<c:url value="/js/jquery-1.11.1.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootbox.js"/>" type="text/javascript"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>


</body>
</html>
