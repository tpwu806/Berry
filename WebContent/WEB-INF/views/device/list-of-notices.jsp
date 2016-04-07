<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>新闻列表</title>
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

<div class="container-fluid">
    <div class="row">

        <!-- Leftmost menu bar -->
        <%@ include file="../includes/outermenu.jspf" %>

        <!-- Inner menu bar -->
        <%@ include file="include/innermenu.jspf" %>


        <div class="col-sm-9 col-sm-offset-3 col-md-offset-2 col-md-10 main">
        
        	<form method="GET"  action="${pageContext.request.contextPath}/qilu/notice/searchnotice" accept-charset="UTF-8">

                <div class="search-admin">
                    <div class="page-header">
                        <input type="text" class="form-control" name="searchKey" maxlength="20" placeholder="请输入通知标题">
                        <input type="hidden" name="page" value="0">
                        <input type="hidden" name="size" value="${properties['paging.numitems']}">
                        <button class="btn btn-lg btn-primary btn-success" type="submit"><img src="<c:url value="/images/search_16.png"/>"></button>
                    </div>
                    <div class="page-header ">
                        <div class="page-header page-dropdown">是否含回执
                             <select name="receiptstate" class="admin-search">
				                <option value="">全部</option>
				                <option value="RECEIPT_YES">含回执</option>
				                <option value="RECEIPT_NO">不含回执</option>
				              </select>   
                        </div>
                    </div>
                </div>

            </form>
			 <div class="table-responsive">
                <table class="table table-striped text-center">
                    <thead>
                    <tr>
                        <th class="text-center">标题</th>
                        <th class="text-center">发布者</th>
                        <th class="text-center">发布时间</th>
                        <th class="text-center">是否含回执</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:catch var="exception">
                        <c:forEach var="thread" items="${noticeList.content}">

                            <tr>
                                <td class="text-center">
                                 
                                       <a class="urlLink" href="${pageContext.request.contextPath}/qilu/notice/viewnotice/viewnoticearticle/${thread.id}" target="_blank">
                                               ${thread.title}
                                       </a>
                                </td>
                                <td>${thread.authorname}</td>
                                <td>
                                    <fmt:formatDate var="date" value="${thread.publisheddate}" pattern="yyyy-MM-dd HH:mm:ss" />
                                        ${date}
                                </td>
                                <td>
                                	 <c:if test="${thread.receiptsign[0] == 0}" >
                                	 	不含回执
                                	 </c:if>
                                	 
                                	  <c:if test="${thread.receiptsign[0] == 1}" >
                                	 	含回执
                                	 </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:catch>
                    </tbody>

                </table>
            </div>
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

<script>

    //查询条件回填——开始
    var searchKey = "${searchKey}";
    var receiptstate = "${receiptstate}";
    if (searchKey != "" && searchKey != null) {
    	$("input[name='searchKey']").val(searchKey);
    }
    if (receiptstate != "" && receiptstate != null) {
    	$("select[name='receiptstate']").val(receiptstate);
    }
	//查询条件回填——结束

</script>



</body>
</html>
