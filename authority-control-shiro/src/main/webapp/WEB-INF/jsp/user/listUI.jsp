<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户列表</title>
	<link rel="stylesheet" href="/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/resources/css/font-awesome.min.css" />
	<link rel="stylesheet" href="/resources/js/layer/skin/default/layer.css" />
	<link rel="stylesheet" href="/resources/js/bootstrap-table/bootstrap-table.css" />
</head>
<body>
	<div style="margin-left: auto;margin-right: auto;width: 95%">
	<div class="row">
		<div class="col-xs-12">
			<div class="row">
				<div class="col-xs-12">
					<div style="position: absolute;top:15px" id="btns">
						<shiro:hasPermission name="user:setRole">
							<a href="#" class="btn btn-success  btn-sm" data-code="user:setRole">
								<i class="icon-cog"></i>设置角色
							</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="user:add">
							<a href="#" class="btn btn-primary  btn-sm" data-code="user:add">
								<i class="icon-ok"></i>新增
							</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="user:update">
							<a href="#" class="btn btn-warning btn-sm" data-code="user:update">
								<i class="icon-edit"></i>编辑
							</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="user:delete">
							<a href="#" class="btn btn-danger btn-sm" data-code="user:delete">
								<i class="icon-trash"></i>删除
							</a>
						</shiro:hasPermission>
					</div>
						<table id="table"></table>
				</div>
			</div>
		</div>
	</div>
	
	<div id="roleUI" class="col-xs-12 col-sm-12" style="display: none">
		<label for="form-field-select-2">选择角色</label>
		<select class="form-control" id="roleIds" multiple="multiple"></select>
	</div>
</div>	
<%@ include file="../common.jsp" %>
<script src="/resources/js/user/list.js"></script>
</body>
</html>