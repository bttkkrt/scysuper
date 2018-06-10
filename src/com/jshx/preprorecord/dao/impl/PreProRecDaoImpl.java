package com.jshx.preprorecord.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.preprorecord.entity.PreProRec;
import com.jshx.preprorecord.dao.PreProRecDao;

@Component("preProRecDao")
public class PreProRecDaoImpl extends BaseDaoImpl implements PreProRecDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPreProRecByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findPreProRec(Map<String, Object> paraMap){
		return this.findListByHqlId("findPreProRecByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PreProRec getById(String id)
	{
		return (PreProRec)this.getObjectById(PreProRec.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PreProRec preProRec)
	{
		preProRec.setId(null);
		this.saveOrUpdateObject(preProRec);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PreProRec preProRec)
	{
		this.saveOrUpdateObject(preProRec);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PreProRec.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PreProRec preProRec = (PreProRec)this.getObjectById(PreProRec.class, id);
		preProRec.setDelFlag(1);
		this.saveObject(preProRec);
	}
}
