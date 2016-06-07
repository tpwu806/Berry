<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
          <style>
    	.search-admin {
		    width: 640px;
		    margin: 0 auto;
		    padding-left: 10px;
		}
		.admin-searchrole {
		    width: 25%;
		    height: 30px;
		    padding: 3px 5px;
		    font-size: 14px;
		    line-height: 1.42857143;
		    color: #555;
		    font-weight: normal;
		    background-color: #fff;
		    background-image: none;
		    border: 1px solid #ccc;
		    border-radius: 4px;
		}
		.admin-searchjob {
		    width: 25%;
		    height: 30px;
		    padding: 3px 5px;
		    font-size: 14px;
		    line-height: 1.42857143;
		    color: #555;
		    font-weight: normal;
		    background-color: #fff;
		    background-image: none;
		    border: 1px solid #ccc;
		    border-radius: 4px;
		}
		
		.admin-searchblack {
		    width: 17%;
		    height: 30px;
		    padding: 3px 5px;
		    font-size: 14px;
		    line-height: 1.42857143;
		    color: #555;
		    font-weight: normal;
		    background-color: #fff;
		    background-image: none;
		    border: 1px solid #ccc;
		    border-radius: 4px;
		}
		
    </style>
</head>

<body>

<!-- Header bar -->
<%@ include file="../includes/header.jspf" %>

<div class="container-fluid">

  <div>
    ${MESSAGE_KEY}
  </div>

  <div class="row">

    <!-- Leftmost menu bar -->
    <%@ include file="../includes/outermenu.jspf" %>

    <!-- Inner menu bar -->
    <%@ include file="include/innermenu.jspf" %>

    <div class="col-sm-9 col-sm-offset-3 col-md-offset-2 col-md-10  main">

      <form method="GET"  action="${pageContext.request.contextPath}/qilu/user/searchuser"
            accept-charset="UTF-8">
          <div class="search-admin" >
          <!-- Search box and buttons -->
          <div class="page-header">
            <input type="text" class="form-control" placeholder="请输入人员姓名或企业名称" name="searchKey" size=15" maxlength="30">
            <input type="hidden" name="page" value="0">
            <input type="hidden" name="size" value="${properties['paging.numitems']}">
            <button class="btn btn-lg btn-primary btn-success" type="submit"><img src="<c:url value="/images/search_16.png"/>"></button>
          </div>

          <!-- Dropdown for usertype attr -->
          <div class="page-header">
            <div  class="page-header page-dropdown" >权限&nbsp;
              <select name="userRole" class="admin-searchrole">
                <option value="">全部</option>
                <option value="G_USRREG">注册用户</option>
                <option value="G_USRMGMT">企业用户</option>
                <option value="G_ORGREG">园区用户</option>
                <option value="G_ORGMGMT">园区领导用户</option>
                <option value="G_ORGMOD">监管用户</option>
                <option value="G_ORGADMIN">系统管理员</option>
              </select>

              &nbsp;岗位&nbsp;
              <!-- Dropdown for jobclass attr -->
              <select name="jobClass" class="admin-searchjob">
                <option value="">全部</option>
                <option value="JOBCLASS_EXEC">高管</option>
                <option value="JOBCLASS_MID">中层</option>
                <option value="JOBCLASS_BASIC">员工</option>
              </select>       
              
               &nbsp;用户状态&nbsp;
              <!-- Dropdown for jobclass attr -->
              <select name="userState" class="admin-searchblack">
                <option value="">全部</option>
                <option value="USER_BLACK">黑名单用户</option>
                <option value="USER_NORMAL">正常用户</option>
              </select>                  
            </div>
          </div>
        </div> 
         <div style="width: 240px;float:right;padding-bottom: 10px;">
			         <div class="page-dropdown" style="width: 140px;"> 
		                               共&nbsp;${number}&nbsp;个用户
		         </div>	
	     </div>
           
      </form>

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
            <th class="text-center">用户状态</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="user" items="${userList.content}">
            <tr>
              <td>
                <a class="urlLink" href="${pageContext.request.contextPath}/qilu/user/viewuserdetails/${user.userId}">
                    ${user.gName}
                </a>
              </td>
              <td>${user.phonenum}</td>
              <td>${user.jobPhonenum}</td>

              <!-- Translate gender information into Readable Chinese -->
              <c:choose>
                <c:when test="${user.gender eq 'M'}"><td>男</td></c:when>
                <c:when test="${user.gender eq 'F'}"><td>女</td></c:when>
                <c:otherwise><td></td></c:otherwise>
              </c:choose>

              <!-- Translate jobclass information into Readable Chinese -->
              <c:choose>
                <c:when test="${user.jobClass eq 'JOBCLASS_BASIC'}"><td>员工</td></c:when>
                <c:when test="${user.jobClass eq 'JOBCLASS_MID'}"><td>中层</td></c:when>
                <c:when test="${user.jobClass eq 'JOBCLASS_EXEC'}"><td>高管</td></c:when>
                <c:otherwise><td></td></c:otherwise>
              </c:choose>

              <!-- Translate group/role information into Readable Chinese -->
              <c:choose>
                <c:when test="${user.group eq 'G_USRREG'}"><td>注册用户</td></c:when>
                <c:when test="${user.group eq 'G_USRMGMT'}"><td>企业用户</td></c:when>
                <c:when test="${user.group eq 'G_ORGREG'}"><td>园区用户</td></c:when>
                <c:when test="${user.group eq 'G_ORGMGMT'}"><td>园区领导用户</td></c:when>
                <c:when test="${user.group eq 'G_ORGMOD'}"><td>监管用户</td></c:when>
                <c:when test="${user.group eq 'G_ORGADMIN'}"><td>系统管理员</td></c:when>
                <c:otherwise><td></td></c:otherwise>
              </c:choose>

              <td>${user.orgname}</td>
               <c:choose>
                <c:when test="${user.blacklisted eq true}"><td>黑名单用户</td></c:when>
                <c:when test="${user.blacklisted eq false}"><td>正常用户</td></c:when>
                <c:otherwise><td></td></c:otherwise>
              </c:choose>
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

      <c:if test="${not empty MESSAGE_KEY}">
        <nav class="search">
            ${MESSAGE_KEY}
        </nav>
      </c:if>
      

    </div>
  </div>
</div>

<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<c:url value="/js/jquery-1.11.1.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/bootbox.js"/>" type="text/javascript"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>

<script>

    $('.urlLink').on('click', function(e) {
        e.preventDefault();
        newWin = window.open($(this).attr('href'));
        newWin.onbeforeunload = function(f) {
            // reload page
            setTimeout(function(){location.reload(true);},500);
        };

    });
	//查询条件回填——开始
    var searchKey = "${searchKey}";
    var userRole = "${userRole}";
    var jobClass = "${jobClass}";
    var userState = "${userState}";
    if (searchKey != "" && searchKey != null) {
    	$("input[name='searchKey']").val(searchKey);
    }
    if (userRole != "" && userRole != null) {
    	$("select[name='userRole']").val(userRole);
    }
	if (jobClass != "" && jobClass != null) {
		$("select[name='jobClass']").val(jobClass);
    }
	if(userState != "" && userState != null){
		$("select[name='userState']").val(userState);
	}
	//查询条件回填——结束
</script>


</body>
</html>
