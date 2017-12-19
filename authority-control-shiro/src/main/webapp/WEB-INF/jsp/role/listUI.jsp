<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色列表</title>
	<link rel="stylesheet" href="/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/resources/css/font-awesome.min.css" />
	<link rel="stylesheet" href="/resources/js/layer/skin/default/layer.css" />
	<link rel="stylesheet" href="/resources/js/bootstrap-table/bootstrap-table.css" />
	<link rel="stylesheet" href="/resources/js/zTree/css/zTreeStyle/metro.css" />
</head>
<body>
	<div style="margin-left: auto;margin-right: auto;width: 95%">
	<div class="row">
		<div class="col-xs-12">
			<div class="row">
				<div class="col-xs-12">
					<div style="position: absolute;top:15px" id="btns">
						<shiro:hasPermission name="role:setPermission">
							<a href="#" class="btn btn-success  btn-sm" data-code="role:setPermission">
								<i class="icon-cog"></i>设置权限
							</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="role:add">
							<a href="#" class="btn btn-primary  btn-sm" data-code="role:add">
								<i class="icon-ok"></i>新增
							</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="role:update">
							<a href="#" class="btn btn-warning btn-sm" data-code="role:update">
								<i class="icon-edit"></i>编辑
							</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="role:delete">
							<a href="#" class="btn btn-danger btn-sm" data-code="role:delete">
								<i class="icon-trash"></i>删除
							</a>
						</shiro:hasPermission>
					</div>
					<table id="table"></table>
				</div>
			</div>
		</div>
	</div>
	<div id="permissionUI" class="col-xs-12 col-sm-12" style="display: none">
		<label for="form-field-select-2">选择权限</label>
		<ul id="permissionTree" class="ztree" style="width:100%; overflow:hidden;"></ul>
	</div>
</div>	
<%@ include file="../common.jsp" %>
<script src="/resources/js/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script src="/resources/js/role/list.js"></script>
</body>
</html>