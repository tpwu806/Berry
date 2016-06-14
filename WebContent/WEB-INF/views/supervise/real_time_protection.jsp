<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>实时监控</title>
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
	<!-- Bootstrap core JavaScript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<c:url value="/js/jquery-1.11.1.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>
	
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>
    <script type="text/javascript">
    	//异步请求开始
        window.onload=repeatFlush();
	    function repeatFlush(){
	    	var xmlhttp;
	    	var url="http://localhost:8080/Berry/supervise/getdht11";//aimat是目标servlet或页面
	    	if(window.XMLHttpRequest){
	    		xmlhttp=new XMLHttpRequest();
	    	}else{
	    		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	    	}
	    	xmlhttp.onreadystatechange=function(){
	    		if(xmlhttp.readyState==4&&xmlhttp.status==200){
	    			document.getElementById("temp").innerHTML=xmlhttp.responseText;
	    			setTimeout("repeatFlush()",1000);//2秒刷新一次
	    		}
	    	}
	    	xmlhttp.open("POST",url,true);
	    	xmlhttp.send();
	    }
	    //异步请求结束
	    
	    //Highcharts绘画折线图开始
		/* $(function () {
		    $(document).ready(function () {
		        Highcharts.setOptions({
		            global: {
		                useUTC: false
		            }
		        });
		
		        $('#container').highcharts({
		            chart: {
		                type: 'spline',
		                animation: Highcharts.svg, // don't animate in old IE
		                marginRight: 10,
		                events: {
		                    load: function () {
		
		                        // set up the updating of the chart each second
		                        var series = this.series[0];
		                        setInterval(function () {
		                            var x = (new Date()).getTime(), // current time
		                                y = Math.random();
		                            series.addPoint([x, y], true, true);
		                        }, 1000);
		                    }
		                }
		            },
		            title: {
		                text: 'Live random data'
		            },
		            xAxis: {
		                type: 'datetime',
		                tickPixelInterval: 150
		            },
		            yAxis: {
		                title: {
		                    text: 'Value'
		                },
		                plotLines: [{
		                    value: 0,
		                    width: 1,
		                    color: '#808080'
		                }]
		            },
		            tooltip: {
		                formatter: function () {
		                    return '<b>' + this.series.name + '</b><br/>' +
		                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
		                        Highcharts.numberFormat(this.y, 2);
		                }
		            },
		            legend: {
		                enabled: false
		            },
		            exporting: {
		                enabled: false
		            },
		            series: [{
		                name: 'Random data',
		                data: (function () {
		                    // generate an array of random data
		                    var data = [],
		                        time = (new Date()).getTime(),
		                        i;
		
		                    for (i = -19; i <= 0; i += 1) {
		                        data.push({
		                            x: time + i * 1000,
		                            y: Math.random()
		                        });
		                    }
		                    return data;
		                }())
		            }]
		        });
		    });
	}); */
	//Highcharts绘画折线图结束
    </script>
</head>

<body>
<%@ include file="../includes/header.jspf" %>
<div class="container-fluid">
    <div class="row">
    <%@ include file="../includes/outermenu.jspf" %>
	    <div class="col-sm-9 col-sm-offset-3 col-md-offset-2 col-md-10 main">
	    <h1>欢迎使用Berry系统</h1>
	    <h3>目前激活的任务：</h3>
	    <th>
           		<h4>
           		<td>任务序号：${task.id}</td>
           		&nbsp;&nbsp;<td>设备id：${task.deviceid}</td>
           		&nbsp;&nbsp;<td>任务开启时间：
           		<fmt:formatDate var="date" value="${task.starttime}" pattern="yyyy-MM-dd HH:mm:ss" />
                ${date} 
                            </td>
           		
                </h4>
        </th>
	    
	    <tr align="center">
		温度为：<span id="temp">null</span>
		</tr>
		
		<!-- <div><input id="dhtbtn" type="button" value="开始采集" onclick="repeatFlush();"></div> -->
	    
		</div>
		<c:if test="${not empty MESSAGE_KEY}">
                <nav class="search">
                    ${MESSAGE_KEY}
                </nav>
            </c:if>
	</div>
</div>


    

</body>
</html>