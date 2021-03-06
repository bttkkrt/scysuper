package com.jshx.aqsczrb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqsczrb.entity.JshxAqsczrb;
import com.jshx.aqsczrb.dao.JshxAqsczrbDao;

@Component("jshxAqsczrbDao")
public class JshxAqsczrbDaoImpl extends BaseDaoImpl implements JshxAqsczrbDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJshxAqsczrbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findJshxAqsczrb(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxAqsczrbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public JshxAqsczrb getById(String id)
	{
		return (JshxAqsczrb)this.getObjectById(JshxAqsczrb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(JshxAqsczrb jshxAqsczrb)
	{
		jshxAqsczrb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxAqsczrb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(JshxAqsczrb jshxAqsczrb)
	{
		this.saveOrUpdateObject(jshxAqsczrb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxAqsczrb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		JshxAqsczrb jshxAqsczrb = (JshxAqsczrb)this.getObjectById(JshxAqsczrb.class, id);
		jshxAqsczrb.setDelFlag(1);
		this.saveObject(jshxAqsczrb);
	}
}
