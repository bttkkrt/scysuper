package com.jshx.sbsskjzywszydbj.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.sbsskjzywszydbj.entity.EquAndFac;
import com.jshx.sbsskjzywszydbj.dao.EquAndFacDao;

@Component("equAndFacDao")
public class EquAndFacDaoImpl extends BaseDaoImpl implements EquAndFacDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findEquAndFacByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findEquAndFac(Map<String, Object> paraMap){
		return this.findListByHqlId("findEquAndFacByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public EquAndFac getById(String id)
	{
		return (EquAndFac)this.getObjectById(EquAndFac.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(EquAndFac equAndFac)
	{
		equAndFac.setId(null);
		this.saveOrUpdateObject(equAndFac);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(EquAndFac equAndFac)
	{
		this.saveOrUpdateObject(equAndFac);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(EquAndFac.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		EquAndFac equAndFac = (EquAndFac)this.getObjectById(EquAndFac.class, id);
		equAndFac.setDelFlag(1);
		this.saveObject(equAndFac);
	}
}
