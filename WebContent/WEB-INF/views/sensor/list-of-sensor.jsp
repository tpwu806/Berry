<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>传感器列表</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/dashboard.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/berry.css"/>" rel="stylesheet">

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
        <%-- <%@ include file="../includes/outermenu.jspf" %> --%>

        <!-- Inner menu bar -->
        <%-- <%@ include file="include/innermenu.jspf" %> --%> 


        <div class="col-sm-9  col-md-offset-sensor col-md-10 main">
                      

             <div>
              <h3>设备简介</h3>
          		<th>
           		<h4><td>设备名称：${device.devicename}</td>
           		&nbsp;&nbsp;<td>设备ip：${device.deviceip}</td>
           		&nbsp;&nbsp;<td>设备port：${device.deviceport}</td></h4>
          		</th>
         	 </div>
             <div style="width: 240px;float:right;padding-bottom: 10px;
              padding-top: 10px;">
		         <div class="page-dropdown" style="width: 140px;"> 
		                               共&nbsp;${sensorNum}&nbsp;个传感器
		         </div>	
	         </div>
            
			 <div class="table-responsive">
                <table class="table table-striped text-center">
                    <thead>
                    <tr>
                        <th class="text-center">传感器名称</th>
                        <th class="text-center">传感器型号</th>
                        <th class="text-center">传感器参数1</th>
                        <th class="text-center">传感器参数2</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:catch var="exception">
                        <c:forEach var="thread" items="${sensorList.content}">

                            <tr>
                                <td class="text-center">                                
                                    <a class="urlLink" href="${pageContext.request.contextPath}/sensor/viewsensorarticle/${thread.id}" target="_blank">
                                            ${thread.sensorname}
                                    </a>
                                </td>                                                              
                                <td>${thread.sensortype}</td>
                                <td>${thread.sensorparameter}</td>
                                <td>${thread.sensorparameter2}</td>
                            </tr>
                        </c:forEach>
                    </c:catch>
                    </tbody>

                </table>
            </div>
            <c:if test="${exception == null}">
                <nav class="search">
                    <ul class="pagination pagination-lg">
                            ${sensorList.printLeftArrows()}
                            ${sensorList.printPageNumbers()}
                            ${sensorList.printRightArrows()}
                    </ul>
                </nav>
            </c:if>
            <c:if test="${not empty MESSAGE_KEY}">
                <nav class="search">
                    ${MESSAGE_KEY}
                </nav>
            </c:if>
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
    var receiptstate = "${sensortype}";
    if (searchKey != "" && searchKey != null) {
    	$("input[name='searchKey']").val(searchKey);
    }
    if (sensortype != "" && sensortype != null) {
    	$("select[name='sensortype']").val(sensortype);
    }
	//查询条件回填——结束

</script>



</body>
</html>
