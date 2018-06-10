package com.jshx.jdhxp.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.jdhxp.entity.Jdhxp;
import com.jshx.jdhxp.dao.JdhxpDao;

@Component("jdhxpDao")
public class JdhxpDaoImpl extends BaseDaoImpl implements JdhxpDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJdhxpByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findJdhxp(Map<String, Object> paraMap){
		return this.findListByHqlId("findJdhxpByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Jdhxp getById(String id)
	{
		return (Jdhxp)this.getObjectById(Jdhxp.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Jdhxp jdhxp)
	{
		jdhxp.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jdhxp);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Jdhxp jdhxp)
	{
		this.saveOrUpdateObject(jdhxp);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Jdhxp.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Jdhxp jdhxp = (Jdhxp)this.getObjectById(Jdhxp.class, id);
		jdhxp.setDelFlag(1);
		this.saveObject(jdhxp);
	}
}
