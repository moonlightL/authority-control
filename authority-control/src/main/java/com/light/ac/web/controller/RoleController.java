package com.light.ac.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.light.ac.domain.Role;
import com.light.ac.service.RoleService;
import com.light.ac.vo.Result;
import com.light.ac.web.annotation.RequirePermission;

@Controller
@RequestMapping("role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequirePermission("role:listUI")
	@RequestMapping("listUI")
	public String listUI() {
		
		return "role/listUI";
	}
	
	@RequirePermission("role:listUI")
	@RequestMapping("list")
	@ResponseBody
	public Result list(int offset, int limit,String search) {
		PageInfo<Role> pageInfo = this.roleService.getListByPage(offset / limit + 1, limit, search);
		return Result.succeed(pageInfo);
	}
	
	@RequirePermission("user:setRole")
	@RequestMapping("getRoleListWithSelected/{userId}")
	@ResponseBody
	public Result getRoleListWithSelected(@PathVariable("userId")int userId) {
		List<Role> roleList = this.roleService.getRoleListByUserId(userId);
		return Result.succeed(roleList);
	}
	
	
//===================================保存/修改/删除方法=======================================	
	
	@RequestMapping("saveUI")
	public String saveUI(Integer id, HttpServletRequest request) {
		if (id != null) {
			Role role = this.roleService.getById(id);
			if (role != null) {
				request.setAttribute("role", role);
			}
		}
		return "role/saveUI";
	}
	
	
	@RequirePermission("role:add;role:update")
	@RequestMapping("save")
	public String save(Role role) {
		if (role.getId() == null) {
			this.roleService.save(role);
		} else {
			this.roleService.update(role);
		}
		return "redirect:/role/listUI";
	}
	
	
	@RequirePermission("role:delete")
	@RequestMapping("delete/{ids}")
	@ResponseBody
	public Result delete(@PathVariable("ids") String ids) {
		String[] idsStr = ids.split(",");
		if (idsStr.length == 1) {
			this.roleService.deleteById(Integer.parseInt(idsStr[0]));
		} else {
			this.roleService.deleteBatchByIds(idsStr);
		}
		return Result.succeed();
	}
	
	@RequirePermission("role:setPermission")
	@RequestMapping("setPermission")
	@ResponseBody
	public Result setPermission(int roleId,String permissionIds) {
		this.roleService.saveRolePermission(roleId,permissionIds);
		return Result.succeed();
	}
}
