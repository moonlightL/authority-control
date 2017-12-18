package com.light.ac.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="t_role")
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="role_id")
	private Integer id;
	
	private String name;
	
	private String descr;
	
	@Transient
	private Boolean selected;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	

	public Boolean isSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", descr=" + descr
				+ ", selected=" + selected + "]";
	}

}
