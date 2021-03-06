package com.jshx.qyjcb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qyjcb.entity.JshxQyjcb;
import com.jshx.qyjcb.dao.JshxQyjcbDao;

@Component("jshxQyjcbDao")
public class JshxQyjcbDaoImpl extends BaseDaoImpl implements JshxQyjcbDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJshxQyjcbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findJshxQyjcb(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxQyjcbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public JshxQyjcb getById(String id)
	{
		return (JshxQyjcb)this.getObjectById(JshxQyjcb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(JshxQyjcb jshxQyjcb)
	{
		jshxQyjcb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxQyjcb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(JshxQyjcb jshxQyjcb)
	{
		this.saveOrUpdateObject(jshxQyjcb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxQyjcb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		JshxQyjcb jshxQyjcb = (JshxQyjcb)this.getObjectById(JshxQyjcb.class, id);
		jshxQyjcb.setDelFlag(1);
		this.saveObject(jshxQyjcb);
	}
}
