<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//获取绝对路径路径 
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>    
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>" />
<title>用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script type="text/javascript" src="resource/js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="resource/validation/jquery.validate.js"></script>
<script type="text/javascript" src="resource/validation/localization/messages_zh.js"></script>
<link href="resource/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="resource/assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="resource/assets/css/ace.min.css" />
<link rel="stylesheet" href="resource/assets/css/ace-rtl.min.css" /><title>首页</title>
</head>
<script type="text/javascript">
$(function(){
	$("#loginForm").validate();
})
	/* function check(){
		if($("#userId").val() == ""){
			alert("账户不能为空")
			return false;
		}
		if($("#userPw").val()==""){
			alert("密码不能为空")
			return false;
		}
		
		return true;
	} */
</script>
<body class="login-layout">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<i class="icon-leaf green"></i> <span class="red">柠檬</span> <span
									class="white">企业费用管理系统</span>
							</h1>
							<h4 class="blue"></h4>
						</div>
						<div class="space-6"></div>
						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header red lighter bigger">
											<i class="icon-coffee red"></i> ${tip==null?'请输入您的账号和密码':tip }
										</h4>

										<div class="space-6"></div>
										<form action="system/doLogin" method="post" id="loginForm" onsubmit="return check()">
												
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input id="userId"
													required="required"	name="uAccout" type="text" class="form-control"
														placeholder="请输入账号" value="${user.userAccount }" /> <i class="icon-user"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input id="userPw"
													required="required"	name="uPwd" type="password" class="form-control"
														placeholder="请输入密码" /> <i class="icon-lock"></i>
												</span>
												</label>

												<div class="clearfix">
											

													<button type="submit"
														class="width-35 pull-right btn btn-sm btn-primary">
														<i class="icon-key"></i> 登陆
													</button>
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>

									</div>

								</div>

							</div>


					</div>

				</div>
			</div>

		</div>
</div>
	</div>

</body>

</html>
