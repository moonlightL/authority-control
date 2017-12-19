package com.light.ac.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.light.ac.domain.User;
import com.light.ac.mapper.UserMapper;
import com.light.ac.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	protected Mapper<User> getMapper() {
		return this.userMapper;
	}

	@Override
	public User findUserByUserName(String userName) {
		return this.userMapper.getUserByUserName(userName);
	}

	@Override
	public void deleteBatchByIds(String[] idsStr) {
		this.userMapper.deleteBatchByIds(idsStr);
	}

	@Override
	public PageInfo<User> getListByPage(int currentNum, int pageSize,String name) {
		
		Example cond = new Example(User.class);
        if (!StringUtils.isEmpty(name)) {
        	try {
				name = new String(name.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            cond.createCriteria().andLike("userName",name);
        }
		
        PageHelper.startPage(currentNum, pageSize);
        List<User> list = this.userMapper.selectByExample(cond);
        
		return new PageInfo<User>(list);
	}

	@Override
	public void saveUserRole(int userId, String roleIdsStr) {
		// 解绑
		this.userMapper.deleteUserRoleByUserId(userId);
		
		if (StringUtils.isNotEmpty(roleIdsStr)) {
			// 绑定
			String[] roleIds = roleIdsStr.split(",");
			List<Map<String,Integer>> params = new ArrayList<>(roleIds.length);
	        Map<String,Integer> param = null;
	        for (String roleId : roleIds) {
	            param = new HashMap<>(2);
	            param.put("userId",userId);
	            param.put("roleId",Integer.parseInt(roleId));
	            params.add(param);
	        }
	        
	        this.userMapper.saveUserRole(params);
		}
	}


}
