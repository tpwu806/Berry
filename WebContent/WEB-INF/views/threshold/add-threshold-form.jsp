<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>新增设备</title>
    <!-- Bootstrap -->
    
    <link rel="stylesheet" href="<c:url value="/js/kindeditor/themes/default/default.css"/>" />
	<link rel="stylesheet" href="<c:url value="/js/kindeditor/plugins/code/prettify.css"/>" />
	
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/summernote.css"/>" media="screen" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/font-awesome.min.css"/>" media="screen" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/dashboard.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/kilo.css"/>" rel="stylesheet">
    
	<%-- <script type="text/javascript" charset="utf-8" src="<c:url value="/js/kindeditor/kindeditor.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/kindeditor/lang/zh_CN.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/kindeditor/plugins/code/prettify.js"/>"></script>
	
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content"]', {
				autoHeightMode : true,
				cssPath : '<%=request.getContextPath()%>/js/kindeditor/plugins/code/prettify.css',
				uploadJson : '<%=request.getContextPath()%>/js/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '<%=request.getContextPath()%>/js/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					this.loadPlugin('autoheight');
					
				},
				afterChange : function() {
					K('.word_count2').html(this.count('text'));
					 if (this.count()>0) {
				        $('#submitButton').prop('disabled', false);
			         } else {
			            $('#submitButton').prop('disabled', true);
			         }
				},
				afterBlur: function(){
					this.sync();
				}
			});
			prettyPrint();
		});
	</script> --%>

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

        <div class="col-sm-9 col-sm-offset-3 col-md-offset-2 col-md-10 main">
            <div class="qy-con">

                <form:form method="POST" commandName="deviceForm"
                           action="${pageContext.request.contextPath}/device/newdeviceform/newdevice"
                           accept-charset="UTF-8">

                    <div class="news">

                        <!-- Left side labels -->
                        <div class="news-l">
                            <div class="hy-list text-r">设备名称 *:</div>
                            <div class="hy-sm">&nbsp;</div>
                            <div class="hy-list text-r">ip *:</div>
                             <div class="hy-sm">&nbsp;</div>
                            <div class="hy-list text-r">端口号 *:</div>
                            <div class="hy-sm">&nbsp;</div>
                            <div class="hy-list text-r">设备类型*:</div>
                            <div class="hy-list text-r">&nbsp;</div>
                        </div>

                        <!-- Right side entry fields -->
                        <div class="news-r">
                            <div class="hy-list">
                                <form:input id="deviceName" path="devicename" type="text" class="news-title-in" maxlength="10"/>
                            </div>
                            <div class="hy-sm">&nbsp;</div>
                            <div class="hy-list">
                                <form:input id="deviceIp" path="deviceip" type="text" class="news-ip-in" maxlength="15"/>
                            </div>
                            <div class="hy-sm">&nbsp;</div>
                            <div class="hy-list">
                                <form:input id="devicePort" path="deviceport" type="text" class="news-port-in" maxlength="5"/>
                            </div>
                            <div class="hy-sm">&nbsp;</div>
                            <div class="hy-list">
                                <form:input id="deviceType" path="devicetype" type="text" class="news-type-in" maxlength="10"/>
                            </div>

                        </div>

                        <div class="clear"></div>
                        <div class="btn-news"> <button id="submitButton" class="btn btn-lg btn-primary" type="submit">保存</button></div>

                    </div>

                </form:form>

            </div>
        </div>
    </div>
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<c:url value="/js/jquery-1.11.1.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/summernote.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/locales/summernote-zh-CN.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootbox.js"/>" type="text/javascript"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>

<script type="text/javascript" src="<c:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>

<script>

	$(document).ready(function() {
	
	    
	
	    /* if ($('#regUserOnly').is(':checked')) {
	        $('#warningMsg').show();
	    } else {
	        $('#warningMsg').hide();
	    } */
	
	    bootbox.setDefaults({
	        locale: "zh_CN"
	    });
	
	});
	
    /* $('#regUserOnly').on('change', function() {
        if ($('#regUserOnly').is(':checked')) {
            $('#warningMsg').show();
        } else {
            $('#warningMsg').hide();
        }
    }); */

    $('#submitButton').on('click', function(e) {
    	
    	if ($('#deviceName').val() == '' || $('#devicePort').val() == ''
    		|| $('#deviceIp').val() == ''|| $('#deviceType').val() == '') {
	        alert("信息填写不完整");
	        return false;
	    }
        e.preventDefault();
        $('form').submit();
    });


</script>


</body>
</html>
