package com.jshx.activiti.service;

import com.jshx.core.base.service.BaseService;

public interface BusinessEntityService extends BaseService{
	/**
	 * 保存工作流通用业务实体类
	 * @param businessEntity
	 */
	public void saveBusinessEntity(Object businessEntity);
	
	/**
	 * 通过ID获取BusinessEntity实体对象
	 * @param id
	 * @return
	 */
	public Object getBusinessEntity(Class<?> clazz, String id);
}
