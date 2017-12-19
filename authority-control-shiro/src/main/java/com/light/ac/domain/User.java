package com.light.ac.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="t_user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private Integer id;
	
	private String userName;
	
	private String password;
	
	private String email;
	
	private String phone;
	
	private String salt;
	
	private Integer status;
	
	private Date createTime;
	
	private Date updateTime;
	
	/**
	 * 封装目录+菜单
	 */
	@Transient
	private List<Permission> menuList;
	
	/**
	 * 封装目录+菜单+按钮
	 */
	@Transient
	private List<Permission> permissionList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public List<Permission> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Permission> menuList) {
		this.menuList = menuList;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password="
				+ password + ", email=" + email + ", phone=" + phone
				+ ", salt=" + salt + ", status=" + status + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", menuList="
				+ menuList + ", permissionList=" + permissionList + "]";
	}

}
