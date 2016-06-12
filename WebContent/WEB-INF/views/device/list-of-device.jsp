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
    <title>设备列表</title>
    <!-- Bootstrap -->
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/dashboard.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/kilo.css"/>" rel="stylesheet">

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
                
        	<form method="GET"  action="${pageContext.request.contextPath}/device/searchdevice" accept-charset="UTF-8">

                <div class="search-admin">
                    <div class="page-header">
                        <input type="text" class="form-control" name="searchKey" maxlength="20" placeholder="请输入设备名称">
                        <input type="hidden" name="page" value="0">
                        <input type="hidden" name="size" value="${properties['paging.numitems']}">
                        <button class="btn btn-lg btn-primary btn-success" type="submit"><img src="<c:url value="/images/search_16.png"/>"></button>
                    </div>
                    <div class="page-header ">
                        <div class="page-header page-dropdown">设备型号
                             <select name="devicetype" class="admin-search">
				                <option value="">全部</option>
				                <option value="RASPBERRY">raspberry</option>
				                <option value="AUDROU">audrou</option>
				                <option value="device_OTHER">其他</option>
				              </select>   
                        </div>
                    </div>
                </div>
                <div style="width: 240px;float:right;padding-bottom: 10px;">
			         <div class="page-dropdown" style="width: 140px;"> 
		                               共&nbsp;${deviceNum}&nbsp;个设备
		         </div>	
	            </div>
            </form>
			 <div class="table-responsive">
                <table class="table table-striped text-center">
                    <thead>
                    <tr>
                        <th class="text-center">设备名称</th>
                        <th class="text-center">IP地址</th>
                        <th class="text-center">端口号</th>
                        <th class="text-center">设备型号</th>
                        <th class="text-center">传感器个数</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:catch var="exception">
                        <c:forEach var="thread" items="${deviceList.content}">

                            <tr>
                                <td class="text-center">
                                 
                                       <a class="urlLink" href="${pageContext.request.contextPath}/device/viewdevicearticle/${thread.id}" target="_blank">
                                               ${thread.devicename}
                                       </a>
                                </td>
                                <td>${thread.deviceip}</td>
                                <td>${thread.deviceport}</td>
                                <td>${thread.devicetype}</td> 
                                <td>${thread.sensornumber}</td>                               
                                <td width="200px">  
                                                                                                   
                               	 	<a class="urlLink" href="${pageContext.request.contextPath}/sensor/newsensorform/${thread.id}" target="_blank">
                               	 		<img id="add_sensor" title="新增传感器" src="<c:url value="/images/button/add_btn.png"/>" width="15" height="16" style="margin-bottom:4px;" />
                               	 	</a>
                               	 	<a class="urlLink" href="${pageContext.request.contextPath}/sensor/viewsensor?page=0&size=${properties['paging.numitems']}&deviceId=${thread.id}" target="_blank">
                               	 		<img id="view_recript" title="查看传感器" src="<c:url value="/images/button/view-24.png"/>" width="15" height="16" style="margin-bottom:4px;" />
                               	 	</a>
                               	 	<a class="urlLink" href="${pageContext.request.contextPath}/divice/deletedevice/${thread.id}" target="_blank">
                               	 		<img id="view_recript" title="删除设备" src="<c:url value="/images/button/trash.png"/>" width="15" height="16" style="margin-bottom:4px;" />
                               	 	</a>
                               	 	
                               	 	<c:if test="${thread.status[0] == 0}" >
                                       <a class="urlLink" href="${pageContext.request.contextPath}/divice/startdevice/${thread.id}" target="_blank">
                               	 		<img id="view_recript" title="开启设备" src="<c:url value="/images/button/start4.png"/>" width="15" height="16" style="margin-bottom:4px;" />
                               	 	   </a>
                                        
                                    </c:if>
                                    <c:if test="${thread.status[0] == 1}" >                                     
                                      <a class="urlLink" href="${pageContext.request.contextPath}/divice/stopdevice/${thread.id}" target="_blank">
                               	 		<img id="add_sensor" title="关闭设备" src="<c:url value="/images/button/stop4.png"/>" width="15" height="16" style="margin-bottom:4px;" />
                               	 	  </a>                                   
                                    </c:if>
                                    
                                </td>
                                
                            </tr>
                        </c:forEach>
                    </c:catch>
                    </tbody>

                </table>
            </div>
            
            <c:if test="${exception == null}">
                <nav class="search">
                    <ul class="pagination pagination-lg">
                            ${deviceList.printLeftArrows()}
                            ${deviceList.printPageNumbers()}
                            ${deviceList.printRightArrows()}
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
    var receiptstate = "${devicetype}";
    if (searchKey != "" && searchKey != null) {
    	$("input[name='searchKey']").val(searchKey);
    }
    if (devicetype != "" && devicetype != null) {
    	$("select[name='devicetype']").val(devicetype);
    }
	//查询条件回填——结束

</script>



</body>
</html>
