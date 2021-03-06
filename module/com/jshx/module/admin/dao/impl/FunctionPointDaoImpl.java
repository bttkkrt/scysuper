package com.jshx.module.admin.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.FunctionPoint;
import com.jshx.module.admin.dao.FunctionPointDao;

@Component("functionPointDao")
public class FunctionPointDaoImpl extends BaseDaoImpl implements FunctionPointDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findFunctionPointByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findFunctionPoint(Map<String, Object> paraMap){
		return this.findListByHqlId("findFunctionPointByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public FunctionPoint getById(String id)
	{
		return (FunctionPoint)this.getObjectById(FunctionPoint.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(FunctionPoint functionPoint)
	{
		functionPoint.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(functionPoint);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(FunctionPoint functionPoint)
	{
		this.saveOrUpdateObject(functionPoint);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(FunctionPoint.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		FunctionPoint functionPoint = (FunctionPoint)this.getObjectById(FunctionPoint.class, id);
		functionPoint.setDelFlag(1);
		this.saveObject(functionPoint);
	}
}
