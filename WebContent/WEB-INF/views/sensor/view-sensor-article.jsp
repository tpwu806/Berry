<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>公告详情</title>
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
<!-- Header bar -->
<%@ include file="../includes/header.jspf" %>

<div class="container-yh">
    <div class="qy-con">
        <div class="news">   
         
		<c:if test="${noticeForm.receiptsign[0] == 1}">
            <div class="news-title">
                <h3>${noticeForm.title}</h3>
                <h5 class="f-title">
                        	发布人：&nbsp;${noticeForm.authorname}<br/><br/>
                        	发布时间: &nbsp;<fmt:formatDate type="DATE" value="${noticeForm.publisheddate}" pattern="yyyy-MM-dd" /><br/><br/>                      	
                        	                     	
                </h5><br/>
            </div>
            
            <div class="new-con"  style="word-break:break-all">
                    ${noticeForm.content}
            </div>
            <div class="new-con"  style="word-break:break-all">
                                                           回执截至日期: &nbsp;<fmt:formatDate type="DATE" value="${noticeForm.regdeadline}" pattern="yyyy-MM-dd" /> 
            </div>
            <div style="display: block;height: 0px;clear: both;visibility: hidden; "></div>
	            <sec:authorize access="hasAnyRole('ROLE_ORGMOD', 'ROLE_ORGADMIN')">	            
	                    <a href="${pageContext.request.contextPath}/mod/notice/editnoticeform/${noticeForm.id}">
	                        <button class="btn btn-lg btn-primary btn-login" type="submit">编辑</button>
	                    </a>
	            </sec:authorize>
	            <sec:authorize access="hasRole('ROLE_ORGADMIN')">
		           	<a id="deleteNotice"  href="${pageContext.request.contextPath}/admin/notice/deletenotice/${noticeForm.id}" style="margin-left:50px" class="btn btn-lg btn-primary btn-login">删除</a>
	            </sec:authorize>
            </div>
       </c:if>
       
       <c:if test="${noticeForm.receiptsign[0] == 0}">
            <div class="news-title">
                <h3>${noticeForm.title}</h3>
                <h5 class="f-title">
                        	发布人：&nbsp;${noticeForm.authorname}<br/><br/>
                        	发布时间: &nbsp;<fmt:formatDate type="DATE" value="${noticeForm.publisheddate}" pattern="yyyy-MM-dd" /><br/><br/>                      	       	
                        	
                </h5><br/>
            </div>
            
            <div class="new-con"  style="word-break:break-all">
                    ${noticeForm.content}
            </div>

            <sec:authorize access="hasAnyRole('ROLE_ORGMOD', 'ROLE_ORGADMIN')">
                <div class="btn-news">
                    <a href="${pageContext.request.contextPath}/mod/notice/editnoticeform/${noticeForm.id}">
                        <button class="btn btn-lg btn-primary btn-login" type="submit">编辑</button>
                    </a>
                </div>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ORGADMIN')">
	           	<a id="deleteNotice"  href="${pageContext.request.contextPath}/admin/notice/deletenotice/${noticeForm.id}" style="margin-left:50px" class="btn btn-lg btn-primary btn-login">删除</a>
            </sec:authorize>
       </c:if>
            

        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<c:url value="/js/jquery-1.11.1.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootbox.js"/>" type="text/javascript"></script>
<!-- Placed at the end of the document so the pages load faster -->


<!-- Edit org button action -->
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>

<script>
	$(document).ready(function() {
		bootbox.setDefaults({
	        locale: "zh_CN"
	    });
	
	   $('.editNews').on('click', function(e) {
	       e.preventDefault();
	       newWin = window.open('${pageContext.request.contextPath}/mod/notice/editnoticeform/${noticeForm.id}', '_self');
	       //newWin.onbeforeunload = function(f) {
	           // reload page
	           //setTimeout(function(){location.reload(true);},500);
	       //};
	
	   });
	   
	   $("#deleteNotice").on('click', function(e) {
	       e.preventDefault();
	   	bootbox.confirm("确认要删除该通知吗?", function(result) {
	         if (result) {
	        	 console.info($('#deleteNotice').attr('href'));
	               window.location.href = $('#deleteNotice').attr('href');	
	           }
	       });
	   });
	   
	})

</script>

</body>
</html>
