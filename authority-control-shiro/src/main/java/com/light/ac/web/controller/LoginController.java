package com.light.ac.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.light.ac.domain.User;
import com.light.ac.service.PermissionService;
import com.light.ac.service.UserService;
import com.light.ac.vo.Result;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping("login")
	@ResponseBody
	public Result login(String userName,String password) {
		
		UsernamePasswordToken token = new UsernamePasswordToken(userName.trim(), DigestUtils.md5Hex(password).toCharArray());
		Subject subject = SecurityUtils.getSubject();
		
		try {
			subject.login(token);
			
		}catch (UnknownAccountException e) {
			return Result.fail(403, "用户名不存在");
			
		}catch(IncorrectCredentialsException e) {
			return Result.fail(403, "密码不正确");
			
		}
		
		return Result.succeed("/manageUI");
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public Result logout(HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return Result.succeed("/index.jsp");
	}
	
	@RequestMapping("manageUI")
	public String manageUI(HttpServletRequest request) {
		
		//主体
		Subject subject = SecurityUtils.getSubject();
		
		User user = (User) subject.getPrincipal();
        
		request.setAttribute("loginUser", user);
		
		return "manageUI";
	}
}
