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
<title>用户管理-用户添加</title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<!-- <link href="resource/css/main.css" rel="stylesheet" /> -->
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript"
	src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resource/validation/jquery.validate.js"></script>
<script type="text/javascript">
$(function(){
	
	 $("#f1").validate();
	 //监听焦点移开事件
	$("#userAccount").blur(function(){
		$.ajax({
			url:"system/checkUser",
			type:"post",
			dataType:"json",
			data:{
				userAccount:$("#userAccount").val()
			},
			success:function(resp){
				if(resp.state==true){
					$("#accountinfo").html(resp.tip);
					$("#saveBtn").attr("disabled",true);
				}else{
					$("#accountinfo").html("");
					$("#saveBtn").attr("disabled",false);
				}
			}
		});
	});
})
</script>	
<style type="text/css">.error{color: red;}</style>

</head>
<body>
<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>系统管理</li>
			<li>用户管理</li>
			<li>用户添加</li>
		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert" style="display:${tip==null?'none':'block' };margin-bottom: 0px;">
     	<h4 align="center" style="color: red">${tip }</h4>
    </div>
<form action="system/addUser" id="f1" method="post" class="form-horizontal">
	


		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
		<!-- 开始1 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户姓名</label>
					<div class="col-xs-9 ">
						<input type="text" class="form-control" required name="uName" value="${user.uName }" placeholder="请输入用户姓名" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户年龄</label>
					<div class="col-xs-9 ">
						<input type="text" class="form-control" name="uAge" value="${user.uAge }" placeholder="请输入用户年龄" />
					</div>
				</div>
			</div>
		</div>
		<!--结束1 -->
		<!-- 开始2 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户性别</label>
					<div class="col-xs-3 ">
						<select name="uSex" class="form-control">
							<option ${user.uSex=='保密'?'selected':'' } VALUE="保密" >保密</option>
							<option ${user.uSex=='男'?'selected':'' } value="男">男</option>
							<option ${user.uSex=='女'?'selected':'' } value="女">女</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">电话号码</label>
					<div class="col-xs-9 ">
						<input type="text" class="form-control" name="uPhone" value="${user.uPhone }" placeholder="请输入电话号码" />
					</div>
				</div>
			</div>
		</div>
		<!--结束2 -->
		<!-- 开始3 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户角色</label>
					<div class="col-xs-4 ">
						<select name="r_Id" class="form-control">
							<option ${user.r_Id=='1'?'selected':'' } value="1">普通员工</option>
							<option ${user.r_Id=='2'?'selected':'' } value="2">部门经理</option>
							<option ${user.r_Id=='3'?'selected':'' } value="3">财务人员</option>
							<option ${user.r_Id=='4'?'selected':'' } value="4">超级管理员</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户薪资</label>
					<div class="col-xs-9 ">
						<input type="text" class="form-control" name="uSalary"  value="${user.uSalary }" placeholder="请输入薪资" />
					</div>
				</div>
			</div>
		</div>
		<!--结束3 -->

		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">账号信息</h5>
		<!-- 开始5 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户账号</label>
					<div class="col-xs-9">
						<input type="text" class="form-control" required name="uAccout"  value="${user.uAccout }"  id="userAccount" placeholder="请输入用户账号" />
						<span style="display: inline;color: red" id="accountinfo"></span>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户密码</label>
					<div class="col-xs-9 ">
						<input type="text" class="form-control"  value="${user.uPwd }"  required name="uPwd" placeholder="请输入用户密码" />
					</div>
				</div>
			</div>
		</div>
		<!--结束5 -->

		<div class="row">
			<div class="col-xs-3 col-xs-offset-4">
				<input type="submit" class="btn btn-success" id="saveBtn" value="保存用户" /> <input
					type="reset" class="btn btn-danger" value="重置信息" />
			</div>

		</div>

	</form>
</body>
</html>