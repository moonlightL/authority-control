package com.light.ac.service.impl;

import java.io.Serializable;
import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.light.ac.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	protected abstract Mapper<T> getMapper();
	
	@Override
	public void save(T t) {
		getMapper().insert(t);
	}

	@Override
	public void update(T t) {
		getMapper().updateByPrimaryKeySelective(t);
	}

	@Override
	public void deleteById(Serializable id) {
		getMapper().deleteByPrimaryKey(id);
	}

	@Override
	public T getById(Serializable id) {
		return getMapper().selectByPrimaryKey(id);
	}

	@Override
	public List<T> getList() {
		return getMapper().selectAll();
	}

	@Override
	public PageInfo<T> getListByPage(int currentNum, int pageSize) {
		PageHelper.startPage(currentNum, pageSize);
		List<T> list = this.getList();
		return new PageInfo<T>(list);
	}

}
