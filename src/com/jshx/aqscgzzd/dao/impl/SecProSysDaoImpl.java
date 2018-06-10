package com.jshx.aqscgzzd.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqscgzzd.entity.SecProSys;
import com.jshx.aqscgzzd.dao.SecProSysDao;

@Component("secProSysDao")
public class SecProSysDaoImpl extends BaseDaoImpl implements SecProSysDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSecProSysByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSecProSys(Map<String, Object> paraMap){
		return this.findListByHqlId("findSecProSysByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SecProSys getById(String id)
	{
		return (SecProSys)this.getObjectById(SecProSys.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SecProSys secProSys)
	{
		secProSys.setId(null);
		this.saveOrUpdateObject(secProSys);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SecProSys secProSys)
	{
		this.saveOrUpdateObject(secProSys);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SecProSys.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SecProSys secProSys = (SecProSys)this.getObjectById(SecProSys.class, id);
		secProSys.setDelFlag(1);
		this.saveObject(secProSys);
	}
}
