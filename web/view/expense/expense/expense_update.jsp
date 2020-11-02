<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//获取绝对路径路径 
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>用户管理-用户添加</title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<!-- <link href="resource/css/main.css" rel="stylesheet" /> -->
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript" src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="resource/validation/jquery.validate.js"></script>
<script type="text/javascript">

	$(function(){
		
		//保存未提交
		$("#but").click(function(){
			$("#expenseState").val("0");
			$("#f1").submit();
		});
		
		function init(){
			//给文本框绑定事件
			$("input[id=money]").blur(function(){
				var sum = 0;
				$("input[id=money]").each(function(){
					
					sum+=parseFloat($(this).val());
				});
				
				 $("#expenseTotal").val(sum);
				
			});
		}
	
		init();
		//选择费用明细
		$("#selected").click(function(){
			//获取第一个表格
			var tab1 = $("#tab1");
			//获取选中复选框  数组
			var costIds = $("input[id=costId]:checked");
			//总金额
			var expenseTotal = $("#expenseTotal");
			//遍历数组
			costIds.each(function(){
				//$(this) 当前复选框 
				//$(this).parent().parent() 找到tr
				//.find("td"); 找到tr下面td
				//数组
				var td = $(this).parent().parent().find("td");
				
				tab1.append("<tr>"+
								"<td>"+td[1].innerHTML+"<input type='hidden' name='costIds' value='"+td[1].innerHTML+"' ></td>"+
								"<td>"+td[2].innerHTML+"</td>"+
								"<td><input type='text' value='0' id='money' name='detailMoneys' class='form-control' style='width:100px'></td>"+
								"<td><input type='text' name='detailDescs' class='form-control'></td>"+
								"<td><a href='javascript:void(0)' class='btn btn-danger' onclick='del(this)' >删除</a></td>"+
						" </tr>");
				
				//给文本框绑定事件 只能定义在这个地方
				init();
				
			});
			
			//隐藏
			$("#myModal").modal("hide");
		});
		
	})
	
	function del(obj){
		if(confirm("确定要删除该费用")){
			//获取tr
			var tr = $(obj).parent().parent();
			//删除tr包括tr里面td
			tr.remove();
			var sum = 0;
			$("input[id=money]").each(function(){
				
				sum+=parseFloat($(this).val());
			});
			
			 $("#expenseTotal").val(sum);
		}
	}


</script>	

</head>
<body>
	<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>报销管理</li>
			<li>我的报销单->修改</li>
		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert"
		style="display:${tip==null?'none':'block' };margin-bottom: 0px;">
		<h4 align="center" style="color: red">${tip }</h4>
	</div>
	<form action="expense/updateExpenseDetail" id="f1" method="post"
		class="form-horizontal">
		<input type="hidden" name="eId" id="expenseId" value="${expemse.eId }"/>
		<input type="hidden" name="eState" id="expenseState" value="1"/>
		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
		<!-- 开始1 -->
		<div class="row">
			<div class="col-xs-7">
				<div class="form-group ">
					<label class="col-xs-3 control-label">报销原因</label>
					<div class="col-xs-9 ">
						<input type="text" class="form-control" required name="eName"
							value="${expemse.eName }" placeholder="请输入报销原因" />
					</div>
				</div>
			</div>
			<div class="col-xs-7">
				<div class="form-group ">
					<label class="col-xs-3 control-label">报销总金额</label>
					<div class="col-xs-5 ">
						<input type="text" class="form-control" readonly="readonly" id="expenseTotal" name="eToltel"
							value="${expemse.eToltel==null?'0':expemse.eToltel }" placeholder="请输入报销总金额" />
					</div>
				</div>
			</div>
			<div class="col-xs-7">
				<div class="form-group ">
					<label class="col-xs-3 control-label">报销详情</label>
					<div class="col-xs-9 ">
						<textarea rows="10" class="form-control" name="eDesc" cols=""
							placeholder="请输入报销详情">${expemse.eDesc }</textarea>
					</div>
				</div>
			</div>
		</div>
		<!--结束1 -->
		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">
			报销单明细
			<button type="button" class="btn btn-primary btn-xs"
				data-toggle="modal" data-target="#myModal">选择费用明细</button>
		</h5>
		<table class="table" id="tab1" >
			<tr>
				<th>费用编号</th>
				<th>费用名称</th>
				<th>具体金额</th>
				<th>费用说明</th>
				<th>删除</th>
			</tr>
			<c:forEach items="${expemseDetali }" var="detail">
				<tr>
					
					<td>${detail.cId }
						<input type="hidden" name="cId" value="${detail.cId }"/>
					</td>
					<td>${detail.cName }</td>
					<td>
					<input type='text' value='${detail.d_Monery }' id='money'  name='d_Monery' class='form-control' style='width:100px'>
					</td>
					<td>
					<input type='text' name='d_Desc' value="${detail.d_Desc }" class='form-control'>
					</td>
					<td><a href='javascript:void(0)' class='btn btn-danger' onclick='del(this)' >删除</a></td>
				</tr>
			
			</c:forEach>
		
		</table>
				<div class="row">
			<div class="col-xs-3 col-xs-offset-4">
				<input type="button" id="but" class="btn btn-primary" value="保存未提交" />
				<input type="submit" class="btn btn-success" value="保存并提交" />
			</div>

		</div>
		
		
<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">
			审核历史记录
		</h5>
		<table class="table"  >
			<tr>
				<th>历史记录编号</th>
				<th>审核人</th>
				<th>审核状态</th>
				<th>审核时间</th>
				<th>审核描述</th>
			</tr>
			<c:forEach items="${auditRecs }" var="record">
				<tr>
					<td>${record.a_Id }</td>
					<td>${record.uName }</td>
					<td>${record.a_StateHtml }</td>
					<td>${record.a_Date}</td>
					<td>${record.a_Sugg }</td>
				</tr>
			
			</c:forEach>
		</table>

	</form>
	
	<!-- 弹出框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">费用明细</h4>
      </div>
      <div class="modal-body">
        	<table class="table" id="tab2">
        		<tr>
        			<th></th>
        			<th>费用编号</th>
        			<th>费用名称</th>
        			<th>费用描述</th>
        		</tr>
        		<c:forEach items="${Cotslist }" var="cost">
        			<tr>
        				<td> <input type="checkbox" id="costId" value="${cost.cId }"/> </td>
        				<td>${cost.cId }</td>
        				<td>${cost.cName }</td>
        				<td>${cost.cDesc }</td>
        			</tr>
        		</c:forEach>
        		
        	</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" id="selected">选择</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>