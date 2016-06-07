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
    <title>编辑阈值</title>
    <!-- Bootstrap -->   	
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/font-awesome.min.css"/>" media="screen" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/dashboard.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/kilo.css"/>" rel="stylesheet">
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

                <form:form method="POST" commandName="thresholdForm"
                           action="${pageContext.request.contextPath}/threshold/editthreshold"
                           accept-charset="UTF-8">

                    <div class="news">

                        <!-- Left side labels -->
                        <div class="news-l">
                            <div class="hy-list text-r">温度*:</div>
                            <div class="hy-sm">&nbsp;</div>                      
                             <div class="hy-list text-r">湿度 *:</div>
                            <div class="hy-sm">&nbsp;</div> 
                        </div>

                        <!-- Right side entry fields -->
                        <div class="news-r">
                            <div class="hy-list">
		                        <form:hidden path="id"/>
		                        <form:input id="maxtemperature" path="maxtemperature" type="text" class="news-title-in" maxlength="10"/>
		                        <form:input id="mintemperature" path="mintemperature" type="text" class="news-title-in" maxlength="10"/>		                     
	                        </div> 
	                        <div class="hy-list">		                      
		                        <form:input id="maxhumidity" path="maxhumidity" type="text" class="news-title-in" maxlength="10"/>
		                        <form:input id="minhumidity" path="minhumidity" type="text" class="news-title-in" maxlength="10"/>		                        
	                        </div>                                                  
						</div>
                        <div class="clear"></div>
                        <div class="btn-news"> <button id="submitButton" class="btn btn-lg btn-primary" type="submit">保存</button></div>
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
<script src="<c:url value="/js/bootbox.js"/>" type="text/javascript"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>

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
