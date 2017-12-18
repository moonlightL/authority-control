package com.light.ac.service.impl;

import java.io.Serializable;
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
import com.light.ac.domain.Permission;
import com.light.ac.mapper.PermissionMapper;
import com.light.ac.service.PermissionService;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission>
		implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	protected Mapper<Permission> getMapper() {
		return this.permissionMapper;
	}
	
	@Override
	public void save(Permission permission) {
		if (permission.getPid() != null && permission.getPid() != 0) {
			Permission parent = this.getById(permission.getPid());
			permission.setParentName(parent.getName());
		}
		
		this.getMapper().insert(permission);
	}
	
	@Override
	public void update(Permission permission) {
		if (permission.getPid() != null && permission.getPid() != 0) {
			Permission parent = this.getById(permission.getPid());
			permission.setParentName(parent.getName());
		}
		
		this.getMapper().updateByPrimaryKeySelective(permission);
	}

	@Override
	public Map<String, List<Permission>> getPermissionMapByUserId(Integer userId) {
		List<Permission> permissionList = this.permissionMapper.getPermissionList(userId);

		List<Permission> menuList = new ArrayList<>();
		Map<Integer, Permission> map = new HashMap<>();

		// 筛选目录
		for (Permission permission : permissionList) {
			if (permission.getType() != 3 && permission.getPid() == 0L) {
				map.put(permission.getId(), permission);
				menuList.add(permission);
			}
		}

		// 封装菜单
		for (Permission permission : permissionList) {
			if (permission.getType() != 3 && map.get(permission.getPid()) != null) {
				Permission parent = map.get(permission.getPid());
				parent.getChildren().add(permission);
			}
		}
		
		Map<String,List<Permission>> resultMap = new HashMap<>(2);
		resultMap.put("menuList", menuList);
		resultMap.put("permissionList", permissionList);
		
		return resultMap;
	}

	@Override
	public PageInfo<Permission> getListByPage(int currentNum, int pageSize, String name) {
		
		Example cond = new Example(Permission.class);
        if (!StringUtils.isEmpty(name)) {
        	try {
				name = new String(name.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            cond.createCriteria().andLike("name",name);
        }
		
        PageHelper.startPage(currentNum, pageSize);
        List<Permission> list = this.permissionMapper.selectByExample(cond);
        
		return new PageInfo<Permission>(list);
	}

	@Override
	public void deleteById(Serializable id) {
		Permission permission = this.getById(id);
		if (permission != null) {
			if (permission.getType() != 3) {
				int count = this.permissionMapper.getChildrenCount(id);
				if (count > 0) {
					throw new RuntimeException("该权限包含子权限，不能删除!如要删除，请将子权限删除再进行操作");
				}
			}
			this.getMapper().deleteByPrimaryKey(id);
		}
	}

	@Override
	public List<Permission> getPermissionWithoutButton() {
		return this.permissionMapper.getPermissionWithoutButton();
	}

	@Override
	public List<Permission> getPermissionListByRoleId(int roleId) {
		List<Permission> list = this.permissionMapper.getPermissionListByRoleId(roleId);
		List<Permission> permissionList = this.getList();
		
		for (Permission permission : permissionList) {
			for (Permission p : list) {
				if (permission.getId() == p.getId()) {
					permission.setChecked(true);
				}
			}
		}
		 
		return permissionList;
	}
}
