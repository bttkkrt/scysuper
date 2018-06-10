package com.jshx.mxbz.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.mxbz.entity.Signs;
import com.jshx.mxbz.dao.SignsDao;

@Component("signsDao")
public class SignsDaoImpl extends BaseDaoImpl implements SignsDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSignsByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSigns(Map<String, Object> paraMap){
		return this.findListByHqlId("findSignsByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Signs getById(String id)
	{
		return (Signs)this.getObjectById(Signs.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Signs signs)
	{
		signs.setId(null);
		this.saveOrUpdateObject(signs);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Signs signs)
	{
		this.saveOrUpdateObject(signs);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Signs.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Signs signs = (Signs)this.getObjectById(Signs.class, id);
		signs.setDelFlag(1);
		this.saveObject(signs);
	}
}
