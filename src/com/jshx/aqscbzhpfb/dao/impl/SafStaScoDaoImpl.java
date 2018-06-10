package com.jshx.aqscbzhpfb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqscbzhpfb.entity.SafStaSco;
import com.jshx.aqscbzhpfb.dao.SafStaScoDao;

@Component("safStaScoDao")
public class SafStaScoDaoImpl extends BaseDaoImpl implements SafStaScoDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSafStaScoByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSafStaSco(Map<String, Object> paraMap){
		return this.findListByHqlId("findSafStaScoByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SafStaSco getById(String id)
	{
		return (SafStaSco)this.getObjectById(SafStaSco.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SafStaSco safStaSco)
	{
		safStaSco.setId(null);
		this.saveOrUpdateObject(safStaSco);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SafStaSco safStaSco)
	{
		this.saveOrUpdateObject(safStaSco);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SafStaSco.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SafStaSco safStaSco = (SafStaSco)this.getObjectById(SafStaSco.class, id);
		safStaSco.setDelFlag(1);
		this.saveObject(safStaSco);
	}
}
