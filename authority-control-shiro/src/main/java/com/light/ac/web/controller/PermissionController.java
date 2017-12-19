package com.light.ac.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.light.ac.domain.Permission;
import com.light.ac.service.PermissionService;
import com.light.ac.vo.Result;

@Controller
@RequestMapping("permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@RequiresPermissions("permission:listUI")
	@RequestMapping("listUI")
	public String listUI() {
		return "permission/listUI";
	}
	
	@RequiresPermissions("permission:listUI")
	@RequestMapping("list")
	@ResponseBody
	public Result list(int offset, int limit,String search) {
		PageInfo<Permission> pageInfo = this.permissionService.getListByPage(offset / limit + 1, limit, search);
		return Result.succeed(pageInfo);
	}
	
	
	@RequiresPermissions("role:setPermission")
	@RequestMapping("getPermissionListWithChecked/{roleId}")
	@ResponseBody
	public Result getPermissionListWithChecked(@PathVariable("roleId") int roleId) {
		List<Permission> permissionList = this.permissionService.getPermissionListByRoleId(roleId);
		return Result.succeed(permissionList);
	}
	
	
//===================================保存/修改/删除方法=======================================	
	
	@RequestMapping("saveUI")
	public String saveUI(Integer id,HttpServletRequest request) {
		if (id != null) {
			Permission permission = this.permissionService.getById(id);
			if (permission != null) {
				request.setAttribute("permission", permission);
			}
		}
		List<Permission> parentList = this.permissionService.getPermissionWithoutButton();
		List<Permission> rt = new ArrayList<>();
        recurData(parentList, rt, "━━");
        
		request.setAttribute("parentList", rt);
		return "permission/saveUI";
	}
	
	@RequiresPermissions(value={"permission:add","permission:update"})
	@RequestMapping("save")
	public String save(Permission permission) {
		if (permission.getId() != null) {
			this.permissionService.update(permission);
		} else {
			this.permissionService.save(permission);
		}
		return "redirect:/permission/listUI";
	}
	
	@RequiresPermissions("permission:delete")
	@RequestMapping("delete/{id}")
	@ResponseBody
	public Result delete(@PathVariable("id") int id) {
		try {
			this.permissionService.deleteById(id);
			return Result.succeed();
		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail(403, e.getMessage());
		}
	}
	
	 private void recurData(List<Permission> source, List<Permission> dest, String prefix) {
	        for (Permission permission : source) {
	        	permission.setName("┣" + prefix + permission.getName());
	            dest.add(permission);
	            if (permission.getChildren() != null && !permission.getChildren().isEmpty()) {
	                recurData(permission.getChildren(), dest, prefix + prefix);
	            }
	        }
	    }
}
