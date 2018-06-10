package com.jshx.aqscfzrypx.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqscfzrypx.entity.SecProChaTra;
import com.jshx.aqscfzrypx.dao.SecProChaTraDao;

@Component("secProChaTraDao")
public class SecProChaTraDaoImpl extends BaseDaoImpl implements SecProChaTraDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSecProChaTraByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSecProChaTra(Map<String, Object> paraMap){
		return this.findListByHqlId("findSecProChaTraByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SecProChaTra getById(String id)
	{
		return (SecProChaTra)this.getObjectById(SecProChaTra.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SecProChaTra secProChaTra)
	{
		secProChaTra.setId(null);
		this.saveOrUpdateObject(secProChaTra);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SecProChaTra secProChaTra)
	{
		this.saveOrUpdateObject(secProChaTra);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SecProChaTra.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SecProChaTra secProChaTra = (SecProChaTra)this.getObjectById(SecProChaTra.class, id);
		secProChaTra.setDelFlag(1);
		this.saveObject(secProChaTra);
	}
}
