<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//获取绝对路径路径 
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作台</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="resource/css/bootstrap.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="resource/js/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resource/js/highcharts.js"></script>
<script type="text/javascript" src="resource/js/jquery.highchartTable.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	//初始化，将那个一个table转换为图表
	  $('table.salaryChart').highchartTable();
});
</script>
</head>
<body>
<div style="padding:0px; margin:0px;">
 <ul class="breadcrumb" style=" padding:0px; padding-left:20px;" >
  <li ><a href="#">首页</a></li>
  <li>工作台</li>
</ul>
</div>
<div class="row">
	<div class="col-xs-6" >
    	 <div class="panel panel-default" >
          <div class="panel-heading"  >
            <span class="glyphicon glyphicon-refresh"></span>薪资发放
          </div>
              <div class="panel-body">
              	<table class="salaryChart" style="display: none;" data-graph-container-before="1" data-graph-height="250" data-graph-type="column">
				<thead>
					<tr>
						<th>月份</th>
						<th>底薪</th>
						<th>提成</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${salaryRecs }" var="sc">
					<tr>
						<td><fmt:formatDate value="${sc.rMonth }"  type="both" pattern="yyyy-MM"/></td>
						<td>${sc.rSalaryTotal }</td>
						<td>${sc.rConnTotal }</td>
					</tr>
				</c:forEach>	
				</tbody>
				</table>	
              </div>
        </div>
    </div>
    <div class="col-xs-6" >
 	 <div class="panel panel-default" >
          <div class="panel-heading"  >
            <span class="glyphicon glyphicon-refresh"></span>薪资发放
          </div>
              <div class="panel-body">
                          	<table class="salaryChart" style="display: none;" data-graph-container-before="1" data-graph-height="250" data-graph-type="pie">
				<thead>
					<tr>
						<th>月份</th>
						<th>底薪</th>
				
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${salaryRecs }" var="sc">
					<tr>
						<td><fmt:formatDate value="${sc.rMonth }"  type="both" pattern="yyyy-MM"/></td>
						<td>${sc.rSalaryTotal }</td>
					
					</tr>
				</c:forEach>	
				</tbody>
				</table>	
              </div>
        </div>
    </div>


</div>
</body>
</html>