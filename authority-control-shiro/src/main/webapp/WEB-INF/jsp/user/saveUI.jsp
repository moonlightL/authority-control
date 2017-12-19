<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resources/css/font-awesome.min.css" />
<link rel="stylesheet" href="/resources/css/ace.min.css" />
<link rel="stylesheet" href="/resources/js/layer/skin/default/layer.css" />
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>用户管理</h1>
		</div>
		<!-- /.page-header -->
		<div class="row">
			<div class="col-xs-12">
				<form id="save-form" class="form-horizontal" role="form" action="/user/save" method="post">
					<input type="hidden" name="id" value="${user.id}" />
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">用户名</label>
						<div class="col-sm-9">
							<input type="text" id="userName" name="userName" value="${user.userName }" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">电子邮箱</label>
						<div class="col-sm-9">
							<input type="email" id="email" name="email" value="${user.email }"
								class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">手机号</label>
						<div class="col-sm-9">
							<input type="text" id="phone" name="phone" value="${user.phone }"
								class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">状态</label>
						<div class="col-sm-9">
							<label> 开启：<input name="status" class="ace" type="radio"
								value="1" ${user.status == 1 ? 'checked':'' }/> <span class="lbl"></span>
							</label> <label> 禁用：<input name="status" class="ace" type="radio"
								value="0" ${user.status == 0 ? 'checked':'' } /> <span class="lbl"></span>
							</label>
						</div>
					</div>
					<div class="clearfix form-actions">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn btn-info" type="button" id="save-btn">
								<i class="icon-ok bigger-110"></i> 保存
							</button>
							&nbsp; &nbsp; &nbsp;
							<button class="btn" type="button" onclick="history.go(-1)">
								<i class="icon-undo bigger-110"></i> 返回
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="/resources/js/jquery-1.11.3.js"></script>
	<script src="/resources/js/layer/layer.js"></script>
	<script src="/resources/js/user/saveUI.js"></script>
</body>
</html>