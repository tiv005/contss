<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
	//获取绝对路径路径 
	String path = request.getContextPath();

			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
	%>   
<%@ taglib prefix="d" uri="http://displaytag.sf.net" %>	   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title></title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript"
	src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resource/My97DatePicker/WdatePicker.js"></script>	

</head>
<body>
<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>系统管理</li>
			<li>我的薪资查询</li>
			
		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert" style="display:${info==null?'none':'block' };margin-bottom: 0px;">
     	<h4 align="center" style="color: red">${info }</h4>
    </div>
	<form action="system/mySalaryServlet" id="f1" method="post" class="form-inline">
		<div class="row alert alert-info" style="margin: 0px; padding: 5px;">
			<div class="form-group">
				
				<label>发放月份:</label>
				<input type="text" class="form-control" name="rMonth" value='<fmt:formatDate value="${salaryRec.rMonth }" pattern="yyyy-MM"/>' onClick="WdatePicker({dateFmt:'yyyy-MM'})"  placeholder="请输入费用月份" />
			</div>
			<input type="submit" class="btn btn-danger" value="查询"> 
		
		</div>
		
		<div class="row" style="padding: 15px;">
			<d:table class="table table-hover table-condensed" name="mySalaryList" pagesize="4" requestURI="system/mySalaryServlet">
				
				<d:column property="rId" title="薪资发放编号"></d:column>
				<d:column property="rMonth" title="发放月份" format="{0,date,yyyy-MM}"></d:column>
				<d:column property="rSalary" title="基本薪资"></d:column>
				<d:column property="rConn" title="薪资提成"></d:column>
				<d:column property="rDate" title="发放时间" format="{0,date,yyyy-MM-dd hh:mm:ss}"></d:column>
			</d:table>
		</div>
	</form>
</body>
</html>