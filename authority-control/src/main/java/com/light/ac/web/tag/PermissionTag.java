package com.light.ac.web.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.light.ac.domain.Permission;

public class PermissionTag extends SimpleTagSupport{
	
	private String type;

	private Object items;
    
    private String var;
	
	@Override
	@SuppressWarnings("unchecked")
    public void doTag() throws JspException, IOException {
		List<Permission> permissionList = (List<Permission>) items;
		for (Permission permission : permissionList) {
			if (permission.getType() == 3 && permission.getCode().indexOf(type) > -1) {
				this.getJspContext().setAttribute(var, permission);
                this.getJspBody().invoke(null);
			}
		}
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setItems(Object items) {
		this.items = items;
	}

	public void setVar(String var) {
		this.var = var;
	}
}
