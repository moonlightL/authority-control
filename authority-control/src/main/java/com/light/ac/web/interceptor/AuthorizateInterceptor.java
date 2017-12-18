package com.light.ac.web.interceptor;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.light.ac.domain.Permission;
import com.light.ac.domain.User;
import com.light.ac.web.annotation.RequirePermission;

public class AuthorizateInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		
		// 1.获取访问接口对应的权限
        HandlerMethod method = (HandlerMethod)handler;
        RequirePermission requirePermission = method.getMethod().getAnnotation(RequirePermission.class);
        if (requirePermission == null) {
            return true;
        }
		
        // 2.获取用户所有权限（目录+菜单+按钮）
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		List<Permission> permissionList = user.getPermissionList();
		
		// 3.当前请求方法需要的权限
        String privilege = requirePermission.value();
        // 判断权限
        for (Permission permission : permissionList) {
            if (permission.getType() != 1) {
                if (privilege.indexOf(permission.getCode()) > -1) {
                    return true;
                }
            }
        }
        
        response.sendRedirect("/403.jsp");
		return false;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

}