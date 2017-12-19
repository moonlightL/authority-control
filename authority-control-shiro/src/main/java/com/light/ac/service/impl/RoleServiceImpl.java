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
import com.light.ac.domain.Role;
import com.light.ac.mapper.RoleMapper;
import com.light.ac.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	protected Mapper<Role> getMapper() {
		return this.roleMapper;
	}

	@Override
	public PageInfo<Role> getListByPage(int currentNum, int pageSize,String name) {
		
		Example cond = new Example(Role.class);
        if (!StringUtils.isEmpty(name)) {
        	try {
				name = new String(name.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            cond.createCriteria().andLike("name",name);
        }
		
        PageHelper.startPage(currentNum, pageSize);
        List<Role> list = this.roleMapper.selectByExample(cond);
        
		return new PageInfo<Role>(list);
	}

	@Override
	public void saveRolePermission(int roleId, String permissionIdsStr) {
		// 解绑
		this.roleMapper.deleteRolePermissionByRoleId(roleId);
		
		if (StringUtils.isNotEmpty(permissionIdsStr)) {
			// 绑定
			String[] permissionIds = permissionIdsStr.split(",");
			List<Map<String,Integer>> params = new ArrayList<>(permissionIds.length);
	        Map<String,Integer> param = null;
	        for (String permissionId : permissionIds) {
	            param = new HashMap<>(2);
	            param.put("roleId",roleId);
	            param.put("permissionId",Integer.parseInt(permissionId));
	            params.add(param);
	        }
			     
	        this.roleMapper.saveRolePermission(params);
		}
	}

	@Override
	public List<Role> getRoleListByUserId(int userId) {
		List<Role> list = this.roleMapper.getRoleListByUserId(userId);
		List<Role> roleList = this.getList();
		
		for (Role role : roleList) {
			for (Role r : list) {
				if (role.getId() == r.getId()) {
					role.setSelected(true);
				}
			}
		}
		
		return roleList;
	}

	@Override
	public void deleteBatchByIds(String[] idsStr) {
		this.roleMapper.deleteBatchByIds(idsStr);
	}


}
