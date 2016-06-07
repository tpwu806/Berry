<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>编辑通知</title>
    <!-- Bootstrap -->
    
    <link rel="stylesheet" href="<c:url value="/js/kindeditor/themes/default/default.css"/>" />
	<link rel="stylesheet" href="<c:url value="/js/kindeditor/plugins/code/prettify.css"/>" />
	
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/summernote.css"/>" media="screen" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/font-awesome.min.css"/>" media="screen" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/dashboard.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/qilu.css"/>" rel="stylesheet">
    
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/kindeditor/kindeditor.js"/>"></script>
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
		
		//自动格式化日期
		//window.onload=function(){
		 	
			//var deadline=document.getElementById("regdeadline").value;//.WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});
			
		//	alert("deadline");
			//var imageurl = document.getElementById("imageurls").value;
			//var sel = document.getElementById("titleSelect");
			//for (var i = 0; i < sel.length; i++) {
			//	if (sel[i].value == imageurl) {		
			//	sel[i].selected = true;
			//	}
			//}
		//}
	</script>
	

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

<div class="container-yh">
    
         <div class="qy-con">
                <form:form method="POST" commandName="noticeForm"
                           action="${pageContext.request.contextPath}/mod/notice/editnoticeform/editnotice/${noticeForm.id}"
                           accept-charset="UTF-8">

                    <div class="news">

                        <!-- Left side labels -->
                        <div class="news-l">
                            <div class="hy-list text-r">通知标题 *:</div>
                            <div class="hy-sm">&nbsp;</div>                      
                            <div class="hy-list text-r news-lm">内容 *:</div>
                            <div class="hy-list text-r">&nbsp;</div>
                        </div>

                        <!-- Right side entry fields -->
                        <div class="news-r">
                            <div class="hy-list">
	                        <form:hidden path="id"/>
	                        <form:input id="inputTitle" path="title" type="text" class="news-title-in" maxlength="30"/>
	                        <form:errors path="title" class="error" />
	                    </div>
                            <div class="hy-sm">标题只限30字</div>

                            <div class="hy-textarea">
                                <div class="news-t"></div>
                                <form:textarea id="content" name="content" path="content" cols="100" rows="8" style="width:700px;height:300px;visibility:hidden;"></form:textarea>
								<p>
									您当前输入了 <span class="word_count2">0</span> 个文字。（字数统计包含纯文本、IMG、EMBED，不包含换行符，IMG和EMBED算一个文字。）
								</p>
                            </div>

                            <div class="news-checkbox hy-list" >
                                <form:checkbox path="receiptsign" value="1" id="regUserOnly"/> 是否需要回执
                            </div>
                            <div id="warningMsg">
                            	报名截至时间：&nbsp;&nbsp;<form:input id="regdeadline" path="regdeadline" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" />
							</div>
                        </div>

                        <div class="clear"></div>
                        <div class="btn-news"> <button id="submitButton" class="btn btn-lg btn-primary" type="submit">发布</button></div>
                        <div class="btx">*必填项</div>

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
		
	    if ($('#regUserOnly').is(':checked')) {
	        $('#warningMsg').show();
	    } else {
	        $('#warningMsg').hide();
	    }
	
	    bootbox.setDefaults({
	        locale: "zh_CN"
	    });
	
	});
	
    function enableSubmitButton() {
        var editorEmpty = $("#content").val();
        if ($('#inputTitle').val() != '' && editorEmpty != '') {
            $('#submitButton').prop('disabled', false);
        } else {
            $('#submitButton').prop('disabled', true);
        }
    }

    $('#inputTitle').on('input', function() {
        enableSubmitButton();
    });

    $('#regUserOnly').on('change', function() {
        if ($('#regUserOnly').is(':checked')) {
            $('#warningMsg').show();
        } else {
            $('#warningMsg').hide();
        }
    });

    $('#submitButton').on('click', function(e) {
        e.preventDefault();
        $('form').submit();
    });


</script>


</body>
</html>
