package com.light.ac.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.light.ac.domain.User;
import com.light.ac.service.RoleService;
import com.light.ac.service.UserService;
import com.light.ac.vo.Result;
import com.light.ac.web.annotation.RequirePermission;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequirePermission("user:listUI")
	@RequestMapping("listUI")
	public String listUI(HttpServletRequest request) {
		return "user/listUI";
	}
	
	@RequirePermission("user:listUI")
	@RequestMapping("list")
	@ResponseBody
	public Result list(int offset, int limit,String search) {
		
		PageInfo<User> pageInfo = this.userService.getListByPage(offset / limit + 1, limit, search);
		return Result.succeed(pageInfo);
	}
	
	
//===================================保存/修改/删除方法=======================================	
	
	@RequestMapping("saveUI")
	public String saveUI(Integer id,HttpServletRequest request) {
		if (id != null) {
			User user = this.userService.getById(id);
			if (user != null) {
				request.setAttribute("user", user);
			}
		}
		return "user/saveUI";
	}
	
	
	@RequirePermission("user:add;user:update")
	@RequestMapping("save")
	public String add(User user) {
		if (user.getId() != null) {
			this.userService.update(user);
		} else {
			user.setCreateTime(new Date());
			user.setUpdateTime(user.getCreateTime());
			user.setPassword(DigestUtils.md5Hex("123456"));
			this.userService.save(user);
		}
		return "redirect:/user/listUI";
	}
	
	@RequirePermission("user:delete")
	@RequestMapping("delete/{ids}")
	@ResponseBody
	public Result delete(@PathVariable("ids") String ids) {
		
		String[] idsStr = ids.split(",");
		if (idsStr.length == 1) {
			this.userService.deleteById(Integer.parseInt(idsStr[0]));
		} else {
			this.userService.deleteBatchByIds(idsStr);
		}
		return Result.succeed();
	}
	
	@RequirePermission("user:setRole")
	@RequestMapping("setRole")
	@ResponseBody
	public Result setRole(int userId,String roleIds) {
		
		this.userService.saveUserRole(userId,roleIds);
		
		return Result.succeed();
	}
	
}
