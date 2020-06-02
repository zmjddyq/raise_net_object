<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/common/include-head.jsp"%>
<script type="text/javascript">
	$(function () {
		/*后端校验账号*/
/*		$("#exampleInputPassword1").blur(function () {
			var Acct = $.trim($("#exampleInputPassword1").val());
			$.ajax({
				type:"post",
				url:"admin/checkLoginAcct.html",
				data:{loginAcct:Acct},
				dataType:"text",
				success:function (response) {
					console.log(response);
					$("#loginAcct_error").text(response);
				}
			})
		})*/
		/*前端校验密码*/
		$("#exampleInputPassword3").blur(function () {
			if ($("#exampleInputPassword2").val() != $("#exampleInputPassword3").val()){
				$("#password_error").text("密码不一致!");
			} else {
				$("#password_error").text("");
			}
		})

	})
</script>
<body>

	<%@ include file="/WEB-INF/common/include-nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/common/include-sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li><a href="admin/toAdmin-main.html">首页</a></li>
					<li><a href="admin/admin-page">数据列表</a></li>
					<li class="active">新增</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						表单数据
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body">
						<form action="admin/saveAdmin.html" method="post" role="form">
							<p>${requestScope.exception.message }</p>
							<div class="form-group">
								<label for="exampleInputPassword1">登录账号</label>
								<input
									name="loginAcct"
									type="text" class="form-control" id="exampleInputPassword1"
									placeholder="请输入登录账号" value="${requestScope.errorAdmin.loginAcct}">
								<p class="help-block label label-warning" >${requestScope.errorInfo.loginAcct}</p>
							</div>
							<div class="form-group">
								<label for="exampleInputPassword2">登录密码</label>
								<input
									name="userPswd"
									type="password" class="form-control" id="exampleInputPassword2"
									placeholder="请输入登录密码">
								<p class="help-block label label-warning" >${requestScope.errorInfo.userPswd}</p>
							</div>
							<div class="form-group">
								<label for="exampleInputPassword3">确认密码</label>
								<input
									type="password" class="form-control" id="exampleInputPassword3"
									placeholder="请输入登录密码">
								<p class="help-block label label-warning" id="password_error"></p>
							</div>
							<div class="form-group">
								<label for="exampleInputPassword4">用户昵称</label>
								<input
									name="userName"
									type="text" class="form-control" id="exampleInputPassword4"
									placeholder="请输入用户名称" value="${requestScope.errorAdmin.userName}">
								<p class="help-block label label-warning" >${requestScope.errorInfo.userName}</p>
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">邮箱地址</label>
								<input type="email"
									name="email"
									class="form-control" id="exampleInputEmail1"
									placeholder="请输入邮箱地址" value="${requestScope.errorAdmin.email}">
								<p class="help-block label label-warning">${requestScope.errorInfo.email}</p>
							</div>
							<button type="submit" class="btn btn-success">
								<i class="glyphicon glyphicon-plus"></i> 新增
							</button>
							<button type="reset" class="btn btn-danger">
								<i class="glyphicon glyphicon-refresh"></i> 重置
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>