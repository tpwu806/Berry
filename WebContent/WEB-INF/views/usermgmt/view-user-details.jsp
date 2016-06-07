<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户详情</title>

    <!-- Bootstrap -->
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/qilu.css"/>" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<style>
.hy-list {
    height: 45px;
    line-height: 20px;
}
</style>

<!-- Header bar -->
<%@ include file="../includes/header.jspf" %>


<div class="container-yh-userdetail">
    <div class="hy-con">
        <div class="hy-input">

            <!-- Left hand side labels -->
            <div class="yh-l">
                <div class="hy-tx text-r"></div>
                <div class="hy-list text-r">姓名:</div>
                <div class="hy-list text-r">昵称:</div>
                <div class="hy-list text-r">手机:</div>
                <div class="hy-list text-r">办公电话:</div>
                <div class="hy-list text-r">企业名称:</div>
                <div class="hy-list text-r">邮箱:</div>
                <div class="hy-list text-r">性别:</div>
                <div class="hy-list text-r">岗位:</div>
                <div class="hy-list text-r">生日日期（年-月-日）:</div>
                <div class="hy-list text-r">权限:</div>
                <div class="hy-list text-r">状态:</div>
                <c:if test="${userObject.userstatus eq 'BLACKLISTED'}">
                	<div class="hy-list text-r">加入黑名单时间:</div>
                	<div class="hy-list text-r">黑名单时长（单位：天）*:</div>
                	<div class="hy-list text-r">黑名单原因:</div>
                </c:if>
            </div>

            <!-- Right hand side content (photos and user info) -->
            <div class="yh-r">
                <div class="hy-tx">
                    <c:set var="imageURL" value=""/>
                    <c:if test="${not empty userObject.imageurl}">
                        <c:choose>
                            <c:when test="${fn:contains(userObject.imageurl, 'http://') }">
                                <c:set var="imageURL" value="${userObject.imageurl}"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="imageURL" value="${properties['image.serving.path.prefix']}/${userObject.imageurl}"/>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${empty userObject.imageurl}">
                        <c:url var="imageURL" value="/images/user_icon.png"/>
                    </c:if>
                    <img id="profileImg" src="${imageURL}" width="70">
                </div>
                <div class="hy-list">${userObject.gName}</div>
                <div class="hy-list">${userObject.nickname}</div>
                <div class="hy-list">${userObject.phonenum}</div>
                <div class="hy-list">${userObject.jobPhonenum}</div>
                <div class="hy-list">${userObject.orgname}</div>
                <div class="hy-list">${userObject.email}</div>

                <!-- Translate gender information into Readable Chinese -->
                <c:choose>
                    <c:when test="${userObject.gender eq 'M'}"><div class="hy-list">男</div></c:when>
                    <c:when test="${userObject.gender eq 'F'}"><div class="hy-list">女</div></c:when>
                    <c:otherwise><div class="hy-list"></div></c:otherwise>
                </c:choose>

                <!-- Translate jobclass information into Readable Chinese -->
                <c:choose>
                    <c:when test="${userObject.jobClass eq 'JOBCLASS_BASIC'}"><div class="hy-list">员工</div></c:when>
                    <c:when test="${userObject.jobClass eq 'JOBCLASS_MID'}"><div class="hy-list">中层</div></c:when>
                    <c:when test="${userObject.jobClass eq 'JOBCLASS_EXEC'}"><div class="hy-list">高管</div></c:when>
                    <c:otherwise><div class="hy-list"></div></c:otherwise>
                </c:choose>

                <div class="hy-list">
                    <fmt:formatDate var="fmtDate" value="${userObject.birthdate}" pattern="yyyy-MM-dd"/>
                    ${fmtDate}
                </div>

                <!-- Translate group/role information into Readable Chinese -->
                <c:choose>
                    <c:when test="${userObject.group eq 'G_USRREG'}"><div class="hy-list">注册用户</div></c:when>
                    <c:when test="${userObject.group eq 'G_USRMGMT'}"><div class="hy-list">企业用户</div></c:when>
                    <c:when test="${userObject.group eq 'G_ORGREG'}"><div class="hy-list">园区用户</div></c:when>
                    <c:when test="${userObject.group eq 'G_ORGMGMT'}"><div class="hy-list">园区领导用户</div></c:when>
                    <c:when test="${userObject.group eq 'G_ORGMOD'}"><div class="hy-list">监管用户</div></c:when>
                    <c:when test="${userObject.group eq 'G_ORGADMIN'}"><div class="hy-list">系统管理员</div></c:when>
                    <c:otherwise><div class="hy-list"></div></c:otherwise>
                </c:choose>

                <!-- Translate user status information into Readable Chinese -->
                <c:choose>
                    <c:when test="${userObject.userstatus eq 'NORMAL'}"><div class="hy-list">正常</div></c:when>
                    <c:when test="${userObject.userstatus eq 'BLACKLISTED'}">
                    	<div class="hy-list">黑名单</div>
                    	<div class="hy-list">
                    		<fmt:formatDate var="blackcreatedate" value="${userObject.blackcreatedate}" pattern="yyyy-MM-dd"/>
                                    ${blackcreatedate}&nbsp;&nbsp;
                            <span><fmt:formatDate type="TIME" value="${userObject.blackcreatedate}" pattern="HH:mm"/></span>
                    	</div>
                    	<div class="hy-list">${userObject.blacktime}</div>
                    	<div class="hy-list">${userObject.blackreason}</div>
                    </c:when>
                    <c:otherwise><div class="hy-list"></div></c:otherwise>
                </c:choose>

            </div>

            <sec:authorize access="hasAnyRole('ROLE_ORGMOD', 'ROLE_ORGADMIN')">
                <button class="btn btn-lg btn-primary btn-login" id="editUser" type="submit">编辑</button>
            </sec:authorize>

        </div>
    </div>
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<c:url value="/js/jquery-1.11.1.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>

<!-- Edit user button action -->
<script>

    $('#editUser').on('click', function(e) {
        e.preventDefault();
        newWin = window.open('${pageContext.request.contextPath}/mod/user/edituserform/${userObject.userId}', '_self');
        //newWin.onbeforeunload = function(f) {
            // reload page
            //setTimeout(function(){location.reload(true);},500);
        //};

    });

</script>

</body>
</html>
