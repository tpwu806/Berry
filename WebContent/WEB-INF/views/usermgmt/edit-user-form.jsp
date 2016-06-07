<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>


<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户详情编辑</title>

    <!-- Bootstrap -->
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/bootstrap-combobox.css"/>" media="screen" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/qilu.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/dashboard.css"/>" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="<c:url value="/js/html5shiv.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/respond.min.js"/>" type="text/javascript"></script>
    <![endif]-->
</head>

<body>
<!-- Header bar -->
<%@ include file="../includes/header.jspf" %>

<style>
.user-edit-textarea{width:48%; height:90px; resize:none;}
</style>
<div class="container-yh-copy">
    <!-- Top action bar -->
    <div class="yh-title">
        <sec:authorize access="hasRole('ROLE_ORGADMIN')">
           		<span class="font-s">
           			<a id="deleteuser" href="${pageContext.request.contextPath}/admin/user/deleteuser/${userObject.userId}">删除</a>
           		</span>
        </sec:authorize>
    </div>

    <div class="qy-con">



        <form:form method="POST" commandName="userObject"
                   action="${pageContext.request.contextPath}/mod/user/edituserform/edituser"
                   target="_parent"
                   accept-charset="UTF-8">

            <div class="hy-input">



                <!-- Left hand label section -->
                <div class="yh-l">
                    <div class="hy-tx text-r"></div>
                    <div class="hy-sm">&nbsp;</div>
                    <div class="hy-list text-r">姓名 *:</div>
                    <div class="hy-sm">&nbsp;</div>
                    <div class="hy-list text-r">昵称 *:</div>
                    <div class="hy-list text-r">手机:</div>
                    <div class="hy-list text-r">办公电话:</div>
                    <div class="hy-list text-r">企业名称 *:</div>
                    <div class="hy-list text-r">邮箱:</div>
                    <div class="hy-list text-r">性别:</div>
                    <div class="hy-list text-r">岗位:</div>
                    <div class="hy-list text-r">生日日期（年-月-日）:</div>

                    <sec:authorize access="hasRole('ROLE_ORGADMIN')">
                        <div class="hy-list text-r">权限:</div>
                        <div class="hy-list text-r">状态:</div>
                        <div name="blackdiv" style="display:none">
                       		<div class="hy-list text-r">加入黑名单时间:</div>
                        	<div class="hy-list text-r">黑名单时长（单位：天）*:</div>
                        	<div class="hy-list text-r">黑名单原因:</div>
                        </div>
                    </sec:authorize>
                </div>

                <!-- Right hand user info section -->
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
                            <c:url var="imageURL" value="/images/toux.png"/>
                        </c:if>
                        <img id="profileImg" style="cursor:pointer" src="${imageURL}" width="70">
                        <img id="deleteImg" style="cursor:pointer" src="<c:url value="/images/trash.png"/>">
                        <input type="hidden" id="imageurl" name="imageurl" value="${userObject.imageurl}" />
                    </div>
                    <div class="hy-sm">
                        只支持JPG或者JPEG格式
                    </div>

                    <div class="hy-list">
                        <form:input path="gName" type="text" class="hy-in" maxlength="30" />
                        <form:errors path="gName" class="error"/>
                    </div>
                    <div class="hy-sm">必须使用真实姓名</div>
                    <div class="hy-list">
                        <form:input path="nickname" type="text" class="hy-in" maxlength="20" />
                        <form:errors path="nickname" class="error"/>
                    </div>
                    <div class="hy-list">
                            ${userObject.username}<form:hidden path="username" /><form:hidden path="userId"/>
                    </div>
                    <div class="hy-list">
                        <form:input path="jobPhonenum" type="text" class="hy-in" maxlength="15" />
                        <form:errors path="jobPhonenum" class="error"/>
                    </div>
                    <div class="hy-list">
                        <div style="width:235px; float: left; padding-top: 3px;cursor:pointer">
                            <form:select path="orgId"  class="combobox input-large form-control">
                                <form:option value="" label="" />
                                <form:options items="${orgList}" />
                            </form:select>
                        </div>
                        <form:errors path="orgId" class="error"></form:errors>
                    </div>
                    <div class="hy-list">
                        <form:input path="email" type="text" class="hy-in" maxlength="50" />
                        <form:errors path="email" class="error"></form:errors>
                    </div>
                    <div class="hy-list">
                        <form:radiobutton path="gender" value="M" />&nbsp; 男 &nbsp;&nbsp;
                        <form:radiobutton path="gender" value="F" />&nbsp; 女
                    </div>
                    <div class="hy-list">
                        <form:select path="jobClass" class="hy-in">
                            <form:option value="JOBCLASS_BASIC" label="员工"/>
                            <form:option value="JOBCLASS_MID" label="中层"/>
                            <form:option value="JOBCLASS_EXEC" label="高管"/>
                        </form:select>
                    </div>
                    <div class="hy-list">
                        <fmt:formatDate var="fmtDate" value="${userObject.birthdate}" pattern="yyyy-MM-dd"/>
                        <c:if test="${empty fmtDate}">
                            <c:set var="fmtDate" value="${BAD_DATE}"/>
                        </c:if>
                        <input name="birthdate" value="${fmtDate}" type="text" class="hy-in" maxlength="10" placeholder="例如: 1990-03-20"/>
                        <form:errors path="birthdate" class="error"></form:errors>
                    </div>

                    <sec:authorize access="hasRole('ROLE_ORGADMIN')">
                        <!--  only orgadmin can change these -->
                        <div class="hy-list">
                            <form:select path="group" class="hy-in">
                                <form:option value="G_USRREG" label="注册用户"/>
                                <form:option value="G_USRMGMT" label="企业用户"/>
                                <form:option value="G_ORGREG" label="园区用户"/>
                                <form:option value="G_ORGMGMT" label="园区领导用户"/>
                                <form:option value="G_ORGMOD" label="监管用户"/>
                                <form:option value="G_ORGADMIN" label="系统管理员"/>
                            </form:select>
                        </div>
                        <div class="hy-list">
                            <form:select path="userstatus" class="hy-in" id="userstatusfield">
                                <form:option value="NORMAL" label="正常"/>
                                <form:option value="BLACKLISTED" label="黑名单"/>
                            </form:select>
                        </div>
                        <div name="blackdiv" style="display:none">
                        	<div class="hy-list">
	                    		<fmt:formatDate var="blackcreatedate" value="${userObject.blackcreatedate}" pattern="yyyy-MM-dd"/>
	                                    ${blackcreatedate}&nbsp;&nbsp;
	                            <span><fmt:formatDate type="TIME" value="${userObject.blackcreatedate}" pattern="HH:mm"/></span>
	                    	</div>
                        	<div class = "hy-list">
	                        	<c:choose>
								   <c:when test="${empty userObject.blacktime}">
								   		<form:select path="blacktime" class="hy-in" id="userblacktime">
			                                <form:option value="10" label="10"/>
			                                <form:option value="15" label="15"/>
			                                <form:option value="30" label="30"/>
			                                <form:option value="60" label="60"/>
			                                <form:option value="90" label="90"/>
		                            	</form:select>
								   </c:when>
								   <c:otherwise>
								   		<form:input path="blacktime" class="hy-in" id="userblacktime" readOnly="readOnly" />
								   </c:otherwise>
							    </c:choose>
                       		</div>
	                        <div class = "hy-textarea">
	                       		<form:textarea path="blackreason" class="user-edit-textarea" id="userblackreason" maxlength="50"/>
	                       </div>
	                       <input type="hidden" name="blackcreatedate" value="${userObject.blackcreatedate}" />
                        </div>
                    </sec:authorize>

                </div>

                <button class="btn btn-lg btn-primary btn-login" id="submitClicked" type="submit">保存</button>
                <div style="display: block;height: 0px;clear: both;visibility: hidden;">&nbsp;</div>
            </div>

        </form:form>

    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<c:url value="/js/jquery-1.11.1.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootstrap-combobox.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootbox.js"/>" type="text/javascript"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>

<script type="text/javascript">
    var file;
    var fileSelector = $('<input type="file" id="imgfile" />');

    $(document).ready(function() {

        var mainWindow = window.opener.opener;

        if ($('#profileImg').attr('src') == ''
                || $('#profileImg').attr('src') == '${pageContext.request.contextPath}/images/toux.png') {
            $('#deleteImg').hide();
        }

        // Initilize the combobox - todo: select the large combobox.
        $('.combobox').combobox();

        // If message_key is not empty, display server message upon page load in bootbox
        <c:if test="${not empty MESSAGE_KEY}">
        showServerMessage('${MESSAGE_KEY}');
        </c:if>

        bootbox.setDefaults({
            locale: "zh_CN"
        });

        if ( $("#userstatusfield").val() == 'BLACKLISTED') {
        	$("div[name='blackdiv']").show();
        }

    });

    $(fileSelector).change(function() {
        file = $(fileSelector)[0].files[0];
        sendFile(file);
    });

    function prepareUpload(event) {
        file = $('#imgfile')[0].files[0];
        alert(file);
        sendFile(file);
    }

    $('#profileImg').click(function(){
        fileSelector.click();
    });

    function sendFile(file) {
        data = new FormData();
        data.append("file", file);
        $.ajax({
            data: data,
            type: "POST",
            url: "${properties['image.upload.server.prefix']}${pageContext.request.contextPath}/api/user/uploadfile",
            cache: false,
            async: false,
            contentType: false,
            processData: false,
            success: function(url) {
                $('#profileImg').attr('src', '${properties['image.serving.path.prefix']}/' + url);
                $('#imageurl').val(url);
                $('#deleteImg').show();
            }
        });
    }

    $('#deleteImg').click(function() {
        $('#profileImg').attr('src', '${pageContext.request.contextPath}/images/toux.png');
        $('#profileImg').hide();
        $('#imageurl').val('');
        $('#deleteImg').hide();
        $('#profileImg').show();
        // Hiding the image and then reshowing it is a kludge to get the
        // image to disappear
    });

    $('#deleteuser').on('click', function(e) {
        e.preventDefault();
        bootbox.confirm("用户删除后将无法登录系统，确认要删除该用户吗?", function(result) {
            if (result) {
                window.location.href = $('#deleteuser').attr('href');
            }
        });
    });

    $('#userstatusfield').on('change', function(e) {
        e.preventDefault();
        if($('#userstatusfield').val() == 'BLACKLISTED') {
            bootbox.alert("此用户一旦被列入黑名单将无法登录系统");
            $("div[name='blackdiv']").show();
        } else {
        	$("div[name='blackdiv']").hide();
        	if ("${userObject.blacktime}" != "") {
        		$("#userblacktime").val("${userObject.blacktime}");
        	} else {
        		$("#userblacktime").val("10");
        	}
        	
        	if ("${userObject.blackreason}" != "") {
        		$("#userblackreason").val("${userObject.blackreason}");
        	} else {
        		$("#userblackreason").val("");
        	}
        }
        
    });

    function showServerMessage(message) {
        bootbox.alert(message);
    }

    $('#submitClicked').on('click', function(e){
        this.submit;
        //window.close();
    });

</script>



</body>
</html>
