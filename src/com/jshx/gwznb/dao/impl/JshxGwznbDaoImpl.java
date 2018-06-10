package com.jshx.gwznb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.gwznb.entity.JshxGwznb;
import com.jshx.gwznb.dao.JshxGwznbDao;

@Component("jshxGwznbDao")
public class JshxGwznbDaoImpl extends BaseDaoImpl implements JshxGwznbDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJshxGwznbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findJshxGwznb(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxGwznbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public JshxGwznb getById(String id)
	{
		return (JshxGwznb)this.getObjectById(JshxGwznb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(JshxGwznb jshxGwznb)
	{
		jshxGwznb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxGwznb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(JshxGwznb jshxGwznb)
	{
		this.saveOrUpdateObject(jshxGwznb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxGwznb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		JshxGwznb jshxGwznb = (JshxGwznb)this.getObjectById(JshxGwznb.class, id);
		jshxGwznb.setDelFlag(1);
		this.saveObject(jshxGwznb);
	}
}
