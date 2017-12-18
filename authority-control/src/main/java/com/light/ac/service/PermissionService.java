package com.light.ac.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.ac.domain.Permission;

public interface PermissionService extends BaseService<Permission>{

	/**
	 * 获取用户可以查看的菜单列表（封装2个list,(菜单+目录) 和 (菜单+目录+按钮)）
	 * @param id
	 * @return
	 */
	Map<String, List<Permission>> getPermissionMapByUserId(Integer id);

	PageInfo<Permission> getListByPage(int currentNum, int pageSize, String name);

	List<Permission> getPermissionWithoutButton();

	List<Permission> getPermissionListByRoleId(int roleId);

}
