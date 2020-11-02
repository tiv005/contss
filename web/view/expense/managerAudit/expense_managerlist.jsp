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
<script type="text/javascript"
	src="resource/My97DatePicker/WdatePicker.js"></script>	
<script type="text/javascript">
$(function(){
	

	
})

</script>	
</head>
<body>
<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>报销管理</li>
			<li>经理审批</li>
		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert" style="display:${info==null?'none':'block' };margin-bottom: 0px;">
     	<h4 align="center" style="color: red">${info }</h4>
    </div>
	<form action="expense/pageManagerlistServlet" id="f1" method="post" class="form-inline">
		<div class="row alert alert-info" style="margin: 0px; padding: 5px;">
			<div class="form-group">
			<label>报销人:</label> <input type="text" class="form-control"     name="uName" value="${expemse.uName }" placeholder="请输入报销人姓名" />
			<label>开始时间:</label> <input type="text" class="form-control " type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="startDate" value='<fmt:formatDate value="${expemse.startDate }" pattern="yyyy-MM-dd" type="both"/>' placeholder="请输入开始时间" />
			<label>结束时间:</label> <input type="text" class="form-control" name="endDate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   value='<fmt:formatDate value="${expemse.endDate }" pattern="yyyy-MM-dd"  type="both"/>' placeholder="请输入结束时间" />
			<label>报销原因:</label> <input type="text" class="form-control"     name="eName" value="${expemse.eName }" placeholder="请输入报销原因" />
		
			</div>
			<input type="submit" class="btn btn-danger" value="查询"> 
		</div>
		
		<div class="row" style="padding: 15px;">
			<d:table class="table table-hover table-condensed" name="list" pagesize="3" requestURI="expense/pageManagerlistServlet">
				
				<d:column property="eId" title="报销编号"></d:column>
				<d:column property="uName" title="报销人"></d:column>
				<d:column property="eName" title="报销原因"></d:column>
				<d:column property="eDate" title="报销时间"></d:column>
				<d:column property="eStateHtml" title="报销状态"></d:column>
				<d:column property="eToltel" title="报销总金额"></d:column>
				<d:column property="manageAduit" title="操作"></d:column>
				<%--<d:column value="审核" title="审核" href="expense/manageAduitExpense"  paramId="aduitId" paramProperty="eId"></d:column>--%>
				
			</d:table>
		</div>
	</form>
</body>
</html>