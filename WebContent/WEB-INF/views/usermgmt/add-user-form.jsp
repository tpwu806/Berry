<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>新增用户</title>

    <!-- Bootstrap -->
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/bootstrap-combobox.css"/>" media="screen" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/dashboard.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/qilu.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/datepicker3.css"/>" rel="stylesheet">

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

<div class="container-fluid">

    <div class="row">

        <!-- Leftmost menu bar -->
        <%@ include file="../includes/outermenu.jspf" %>

        <!-- Inner menu bar -->
        <%@ include file="include/innermenu.jspf" %>


        <div class="col-sm-9 col-sm-offset-3 col-md-offset-2 col-md-10  main">
            <div class="qy-con">

                <div>
                    <form:form method="POST" commandName="userObject"
                               action="${pageContext.request.contextPath}/mod/user/createuserform/createuser"
                               accept-charset="UTF-8">

                        <div class="hy-input">

                            <div class="hy-list" style="color:red; text-align: center;">${MESSAGE_KEY}</div>

                            <!-- Left hand label section -->
                            <div class="yh-l">
                                <div class="hy-tx text-r"></div>
                                <div class="hy-sm">&nbsp;</div>
                                <div class="hy-list text-r">姓名 *:</div>
                                <div class="hy-sm">&nbsp;</div> 
                                <div class="hy-list text-r">昵称 *:</div>
                                <div class="hy-list text-r">手机 *:</div>
                                <div class="hy-sm">&nbsp;</div>
                                <div class="hy-list text-r">办公电话:</div>
                                <div class="hy-list text-r">企业名称 *:</div>
                                <div class="hy-list text-r">邮箱:</div>
                                <div class="hy-list text-r">性别:</div>
                                <div class="hy-list text-r">岗位:</div>
                                <div class="hy-list text-r">生日日期（年-月-日）:</div>
                                <sec:authorize access="hasRole('ROLE_ORGADMIN')">
                                    <!-- Only the admin can see these -->
                                    <div class="hy-list text-r">权限:</div>
                                    <div class="hy-list text-r">状态:</div>
                                </sec:authorize>
                            </div>

                            <!-- Right hand user info section -->
                            <div class="yh-r">
                                <div class="hy-tx">
                                    <img id="profileImg" src="<c:url value="/images/toux.png"/>" width="70">
                                    <img id="deleteImg" src="<c:url value="/images/trash.png"/>">
                                    <input type="hidden" id="imageurl" name="imageurl" value="${userObject.imageurl}"/>
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
                                    <form:input path="nickname" type="text" class="hy-in" maxlength="20"/>
                                    <form:errors path="nickname" class="error"/>
                                </div>
                                <div class="hy-list">
                                    <form:input path="username" type="text" class="hy-in" maxlength="20" />
                                    <form:errors path="username" class="error"/>
                                </div>
                                <div class="hy-sm">必须使用真实手机号</div>
                                <div class="hy-list">
                                    <form:input path="jobPhonenum" type="text" class="hy-in" maxlength="15"/>
                                    <form:errors path="jobPhonenum" class="error"/>
                                </div>
                                <div class="hy-list">
                                    <div style="width:235px; float: left; padding-top: 3px;">
                                        <form:select path="orgId" class="combobox input-large form-control hy-in">
                                            <form:option value="" label=""/>
                                            <form:options items="${orgList}"/>
                                        </form:select>
                                    </div>
                                    <div style="float: left; padding-left: 5px">
                                        <form:errors path="orgId" class="error"/>
                                    </div>
                                </div>
                                <div class="hy-list">
                                    <form:input path="email" type="text" class="hy-in" maxlength="50"/>
                                    <form:errors path="email" class="error"/>
                                </div>
                                <div class="hy-list">
                                    <form:radiobutton path="gender" value="M" checked="checked"/>&nbsp; 男 &nbsp;&nbsp;
                                    <form:radiobutton path="gender" value="F"/>&nbsp; 女
                                    <form:errors path="gender" class="error"/>
                                </div>
                                <div class="hy-list">
                                    <form:select path="jobClass" class="hy-in">
                                        <form:option value="JOBCLASS_BASIC" label="员工"/>
                                        <form:option value="JOBCLASS_MID" label="中层"/>
                                        <form:option value="JOBCLASS_EXEC" label="高管"/>
                                    </form:select>
                                    <form:errors path="jobClass" class="error"/>
                                </div>
                                <div  class="hy-list">
                                    <!-- See controller for explanation for date handling -->
                                    <fmt:formatDate var="fmtDate" value="${userObject.birthdate}" pattern="yyyy-MM-dd"/>
                                    <c:if test="${empty fmtDate}">
                                        <c:set var="fmtDate" value="${BAD_DATE}"/>
                                    </c:if>
                                    <input name="birthdate" value="${fmtDate}" type="text" class="hy-in" maxlength="10" placeholder="例如: 1990-03-20"/>
                                    <form:errors path="birthdate" class="error"/>
                                </div>

                                <sec:authorize access="hasRole('ROLE_ORGADMIN')">
                                    <!-- only orgadmin can change these -->
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
                                         <form:input path="" readOnly="readOnly" type="text" class="hy-in" value="正常" />
                                         <input type="hidden" name="userstatus" value="NORMAL" />
                                    </div>
                                </sec:authorize>

                            </div>

                            <button class="btn btn-lg btn-primary btn-login" type="submit">保存</button>
                            <div style="display: block;height: 0px;clear: both;visibility: hidden;">&nbsp;</div>
                        </div>

                    </form:form>

                </div>

            </div>

        </div>
    </div>
</div>

<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<c:url value="/js/jquery-1.11.1.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootstrap-combobox.js"/>" type="text/javascript"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>

<script type="text/javascript">
    var file;
    var fileSelector = $('<input type="file" id="imgfile" />');

    $(document).ready(function () {
        if ($('#profileImg').attr('src') == ''
                || $('#profileImg').attr('src') == '${pageContext.request.contextPath}/images/toux.png') {
            $('#deleteImg').hide();
        }

        // Initilize the combobox - todo: select the large combobox.
        $('.combobox').combobox();

        // Needed to remove the break in <span><br/></span> generate by spring error messages
        $("span br").remove();

        bootbox.setDefaults({
            locale: "zh_CN"
        });


    });

    $(fileSelector).change(function () {
        file = $(fileSelector)[0].files[0];
        sendFile(file);
    });

    function prepareUpload(event) {
        file = $('#imgfile')[0].files[0];
        sendFile(file);
    }

    $('#profileImg').click(function () {
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
            success: function (url) {
                $('#profileImg').attr('src', '${properties['image.serving.path.prefix']}/' + url);
                $('#imageurl').val(url);
                $('#deleteImg').show();
            }
        });
    }

    $('#deleteImg').click(function () {
        $('#profileImg').attr('src', '${pageContext.request.contextPath}/images/toux.png');
        $('#profileImg').hide();
        $('#imageurl').val('');
        $('#deleteImg').hide();
        $('#profileImg').show();
        // Hiding the image and then reshowing it is a kludge to get the
        // image to disappear
    });

    $('#deleteuser').on('click', function (e) {
        e.preventDefault();
        bootbox.confirm("是否应继续?", function (result) {
            if (result) {
                window.location.href = $('#deleteuser').attr('href');
            }
        });
    });

</script>


</body>
</html>
