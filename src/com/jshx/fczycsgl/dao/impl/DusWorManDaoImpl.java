package com.jshx.fczycsgl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.fczycsgl.entity.DusWorMan;
import com.jshx.fczycsgl.dao.DusWorManDao;

@Component("dusWorManDao")
public class DusWorManDaoImpl extends BaseDaoImpl implements DusWorManDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findDusWorManByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findDusWorMan(Map<String, Object> paraMap){
		return this.findListByHqlId("findDusWorManByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public DusWorMan getById(String id)
	{
		return (DusWorMan)this.getObjectById(DusWorMan.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(DusWorMan dusWorMan)
	{
		dusWorMan.setId(null);
		this.saveOrUpdateObject(dusWorMan);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(DusWorMan dusWorMan)
	{
		this.saveOrUpdateObject(dusWorMan);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(DusWorMan.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		DusWorMan dusWorMan = (DusWorMan)this.getObjectById(DusWorMan.class, id);
		dusWorMan.setDelFlag(1);
		this.saveObject(dusWorMan);
	}
}
