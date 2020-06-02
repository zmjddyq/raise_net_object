<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/common/include-head.jsp"%>

<body>

	<%@ include file="/WEB-INF/common/include-nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/common/include-sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li><a href="/admin/toAdmin-main.html">首页</a></li>
					<li><a href="/admin/admin-page">数据列表</a></li>
					<li class="active">更新</li>
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
						<form action="admin/updateAdmin.html" method="post" role="form">
							<input type="hidden" name="id" value="${requestScope.editAdmin.id }" />
							<input type="hidden" name="pageNum" value="${param.pageNum }" />
							<input type="hidden" name="keyword" value="${param.keyword }" />
							<div class="form-group">
								<label for="exampleInputPassword1">登录账号</label>
								<input
									name="loginAcct" 
									value="${requestScope.editAdmin.loginAcct }"
									type="text" class="form-control"
									id="exampleInputPassword1" placeholder="请输入登录账号">
								<p class="help-block label label-warning">${requestScope.errorInfo_edit.loginAcct}</p>
							</div>
							<div class="form-group">
								<label for="exampleInputPassword2">用户昵称</label>
								<input
									name="userName"
									value="${requestScope.editAdmin.userName }"
									type="text" class="form-control"
									id="exampleInputPassword2" placeholder="请输入用户名称">
								<p class="help-block label label-warning">${requestScope.errorInfo_edit.userName}</p>
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">邮箱地址</label>
								<input type="email"
									name="email" 
									value="${requestScope.editAdmin.email }" class="form-control" id="exampleInputEmail1"
									placeholder="请输入邮箱地址">
								<p class="help-block label label-warning">${requestScope.errorInfo_edit.email}</p>
							</div>
							<button type="submit" class="btn btn-success">
								<i class="glyphicon glyphicon-edit"></i> 更新
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