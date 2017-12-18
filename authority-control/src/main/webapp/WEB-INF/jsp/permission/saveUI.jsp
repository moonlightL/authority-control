<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理</title>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resources/css/font-awesome.min.css" />
<link rel="stylesheet" href="/resources/css/ace.min.css" />
<link rel="stylesheet" href="/resources/js/layer/skin/default/layer.css" />
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>权限管理</h1>
		</div>
		<!-- /.page-header -->
		<div class="row">
			<div class="col-xs-12">
				<form id="save-form" class="form-horizontal" role="form" action="/permission/save" method="post">
					<input type="hidden" name="id" value="${permission.id}" />
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">权限名称</label>
						<div class="col-sm-9">
							<input type="text" id="name" name="name" value="${permission.name }" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">父级名称</label>
						<div class="col-sm-9">
							<select class="col-xs-10 col-sm-8" name="pid" id="pid">
								<option value="0"></option>
								<c:forEach var="p" items="${parentList }">
									<option value="${p.id}" ${permission.pid == p.id ? 'selected':'' }>${p.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">权限类型</label>
						<div class="col-sm-9">
							<select class="col-xs-10 col-sm-8" name="type" id="type">
								<option value="1" ${permission.type == 1 ? 'selected':'' }>目录</option>
								<option value="2" ${permission.type == 2 ? 'selected':'' }>菜单</option>
								<option value="3" ${permission.type == 3 ? 'selected':'' }>按钮</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">URL</label>
						<div class="col-sm-9">
							<input type="text" id="url" name="url" value="${permission.url }" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">权限代码</label>
						<div class="col-sm-9">
							<input type="text" id="code" name="code" value="${permission.code }" class="col-xs-10 col-sm-8" />
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
	<script src="/resources/js/permission/saveUI.js"></script>
</body>
</html>