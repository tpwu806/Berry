<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE html>
<html>
<head>
</head>

<body>

	<form:form method="POST" commandName="userForm"
            action="${pageContext.request.contextPath}/login"
            accept-charset="UTF-8">
       
         
                 <form:input id="username" path="username" type="text" class="news-title-in" maxlength="30"/><br>
                 <form:input id="userpass" path="userpass" type="text" class="news-title-in" maxlength="30"/><br>
    
      			 <button id="submitButton" class="btn btn-lg btn-primary" type="submit">登录</button>
        

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

    $('#submitButton').on('click', function(e) {
		
        e.preventDefault();
        $('form').submit();
    });


</script>


</body>

</html>