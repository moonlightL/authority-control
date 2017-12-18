package com.light.ac.service;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;

public interface BaseService<T> {
	
	void save(T t);

	void update(T t);

	void deleteById(Serializable id);
	
	T getById(Serializable id);
	
	List<T> getList();
	
	PageInfo<T> getListByPage(int currentNum, int pageSize);
}
