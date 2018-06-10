package com.jshx.jccbgl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.jccbgl.entity.DetOverStaMan;
import com.jshx.jccbgl.dao.DetOverStaManDao;

@Component("detOverStaManDao")
public class DetOverStaManDaoImpl extends BaseDaoImpl implements DetOverStaManDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findDetOverStaManByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findDetOverStaMan(Map<String, Object> paraMap){
		return this.findListByHqlId("findDetOverStaManByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public DetOverStaMan getById(String id)
	{
		return (DetOverStaMan)this.getObjectById(DetOverStaMan.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(DetOverStaMan detOverStaMan)
	{
		detOverStaMan.setId(null);
		this.saveOrUpdateObject(detOverStaMan);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(DetOverStaMan detOverStaMan)
	{
		this.saveOrUpdateObject(detOverStaMan);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(DetOverStaMan.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		DetOverStaMan detOverStaMan = (DetOverStaMan)this.getObjectById(DetOverStaMan.class, id);
		detOverStaMan.setDelFlag(1);
		this.saveObject(detOverStaMan);
	}
}
