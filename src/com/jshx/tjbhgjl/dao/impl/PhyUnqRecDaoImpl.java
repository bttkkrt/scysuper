package com.jshx.tjbhgjl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.tjbhgjl.entity.PhyUnqRec;
import com.jshx.tjbhgjl.dao.PhyUnqRecDao;

@Component("phyUnqRecDao")
public class PhyUnqRecDaoImpl extends BaseDaoImpl implements PhyUnqRecDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPhyUnqRecByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findPhyUnqRec(Map<String, Object> paraMap){
		return this.findListByHqlId("findPhyUnqRecByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PhyUnqRec getById(String id)
	{
		return (PhyUnqRec)this.getObjectById(PhyUnqRec.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PhyUnqRec phyUnqRec)
	{
		phyUnqRec.setId(null);
		this.saveOrUpdateObject(phyUnqRec);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PhyUnqRec phyUnqRec)
	{
		this.saveOrUpdateObject(phyUnqRec);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PhyUnqRec.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PhyUnqRec phyUnqRec = (PhyUnqRec)this.getObjectById(PhyUnqRec.class, id);
		phyUnqRec.setDelFlag(1);
		this.saveObject(phyUnqRec);
	}
}
