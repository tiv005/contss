<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
	//获取绝对路径路径 
	String path = request.getContextPath();

			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
	%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>费用管理-费用添加</title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<!-- <link href="resource/css/main.css" rel="stylesheet" /> -->
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript"
	src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resource/validation/jquery.validate.js"></script>
<script type="text/javascript">
$(function(){
	
	 $("#f1").validate();
	 

})
</script>	
<style type="text/css">.error{color: red;}</style>

</head>
<body>
<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>系统管理</li>
			<li>费用管理</li>
			<li>修改费用</li>
		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert" style="display:${tip==null?'none':'block' };margin-bottom: 0px;">
     	<h4 align="center" style="color: red">${tip }</h4>
    </div>
<form action="system/updateCost" id="f1" method="post" class="form-horizontal">
	
<input type="hidden" name="cId" value="${cost.cId }"/>

		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
		<!-- 开始1 -->
		<div class="row">
			<div class="col-xs-9">
				<div class="form-group ">
					<label class="col-xs-3 control-label">费用名称</label>
					<div class="col-xs-9 ">
						<input type="text" class="form-control" required name="cName" value="${cost.cName }" placeholder="请输入费用名称" />
					</div>
				</div>
			</div>
			<div class="col-xs-9">
				<div class="form-group ">
					<label class="col-xs-3 control-label">费用描述</label>
					<div class="col-xs-9 ">
						<textarea rows="" cols="" class="form-control" name="cDesc">${cost.cDesc }</textarea>
					</div>
				</div>
			</div>
		</div>
		<!--结束1 -->



		<div class="row">
			<div class="col-xs-3 col-xs-offset-4">
				<input type="submit" class="btn btn-success" value="保存费用" /> <input
					type="reset" class="btn btn-danger" value="重置信息" />
			</div>

		</div>

	</form>
</body>
</html>