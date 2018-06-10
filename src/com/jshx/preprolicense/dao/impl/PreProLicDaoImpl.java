package com.jshx.preprolicense.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.preprolicense.entity.PreProLic;
import com.jshx.preprolicense.dao.PreProLicDao;

@Component("preProLicDao")
public class PreProLicDaoImpl extends BaseDaoImpl implements PreProLicDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPreProLicByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findPreProLic(Map<String, Object> paraMap){
		return this.findListByHqlId("findPreProLicByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PreProLic getById(String id)
	{
		return (PreProLic)this.getObjectById(PreProLic.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PreProLic preProLic)
	{
		preProLic.setId(null);
		this.saveOrUpdateObject(preProLic);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PreProLic preProLic)
	{
		this.saveOrUpdateObject(preProLic);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PreProLic.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PreProLic preProLic = (PreProLic)this.getObjectById(PreProLic.class, id);
		preProLic.setDelFlag(1);
		this.saveObject(preProLic);
	}
}
