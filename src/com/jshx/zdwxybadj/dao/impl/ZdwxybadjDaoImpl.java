package com.jshx.zdwxybadj.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zdwxybadj.entity.Zdwxybadj;
import com.jshx.zdwxybadj.dao.ZdwxybadjDao;

@Component("zdwxybadjDao")
public class ZdwxybadjDaoImpl extends BaseDaoImpl implements ZdwxybadjDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findZdwxybadjByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZdwxybadj(Map<String, Object> paraMap){
		return this.findListByHqlId("findZdwxybadjByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zdwxybadj getById(String id)
	{
		return (Zdwxybadj)this.getObjectById(Zdwxybadj.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zdwxybadj zdwxybadj)
	{
		zdwxybadj.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zdwxybadj);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zdwxybadj zdwxybadj)
	{
		this.saveOrUpdateObject(zdwxybadj);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zdwxybadj.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zdwxybadj zdwxybadj = (Zdwxybadj)this.getObjectById(Zdwxybadj.class, id);
		zdwxybadj.setDelFlag(1);
		this.saveObject(zdwxybadj);
	}
}
