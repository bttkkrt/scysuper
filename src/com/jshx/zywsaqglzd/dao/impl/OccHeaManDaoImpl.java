package com.jshx.zywsaqglzd.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zywsaqglzd.entity.OccHeaMan;
import com.jshx.zywsaqglzd.dao.OccHeaManDao;

@Component("occHeaManDao")
public class OccHeaManDaoImpl extends BaseDaoImpl implements OccHeaManDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findOccHeaManByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findOccHeaMan(Map<String, Object> paraMap){
		return this.findListByHqlId("findOccHeaManByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public OccHeaMan getById(String id)
	{
		return (OccHeaMan)this.getObjectById(OccHeaMan.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(OccHeaMan occHeaMan)
	{
		occHeaMan.setId(null);
		this.saveOrUpdateObject(occHeaMan);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(OccHeaMan occHeaMan)
	{
		this.saveOrUpdateObject(occHeaMan);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(OccHeaMan.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		OccHeaMan occHeaMan = (OccHeaMan)this.getObjectById(OccHeaMan.class, id);
		occHeaMan.setDelFlag(1);
		this.saveObject(occHeaMan);
	}
}
