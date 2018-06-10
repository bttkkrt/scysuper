package com.jshx.aqssdjtz.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqssdjtz.entity.SafLed;
import com.jshx.aqssdjtz.dao.SafLedDao;

@Component("safLedDao")
public class SafLedDaoImpl extends BaseDaoImpl implements SafLedDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSafLedByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSafLed(Map<String, Object> paraMap){
		return this.findListByHqlId("findSafLedByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SafLed getById(String id)
	{
		return (SafLed)this.getObjectById(SafLed.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SafLed safLed)
	{
		safLed.setId(null);
		this.saveOrUpdateObject(safLed);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SafLed safLed)
	{
		this.saveOrUpdateObject(safLed);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SafLed.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SafLed safLed = (SafLed)this.getObjectById(SafLed.class, id);
		safLed.setDelFlag(1);
		this.saveObject(safLed);
	}
}
