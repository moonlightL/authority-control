package com.light.ac.service;

import com.github.pagehelper.PageInfo;
import com.light.ac.domain.User;

public interface UserService extends BaseService<User>{

	User findUserByUserName(String userName);

	/**
	 * 批量删除
	 * @param idsStr
	 */
	void deleteBatchByIds(String[] idsStr);

	PageInfo<User> getListByPage(int currentNum, int pageSize, String name);

	/**
	 * 绑定用户和角色
	 * @param userId
	 * @param roleIds
	 */
	void saveUserRole(int userId, String roleIds);

}
