package com.jshx.prosafeassess.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.prosafeassess.entity.ProSafAss;
import com.jshx.prosafeassess.dao.ProSafAssDao;

@Component("proSafAssDao")
public class ProSafAssDaoImpl extends BaseDaoImpl implements ProSafAssDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findProSafAssByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findProSafAss(Map<String, Object> paraMap){
		return this.findListByHqlId("findProSafAssByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ProSafAss getById(String id)
	{
		return (ProSafAss)this.getObjectById(ProSafAss.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ProSafAss proSafAss)
	{
		proSafAss.setId(null);
		this.saveOrUpdateObject(proSafAss);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ProSafAss proSafAss)
	{
		this.saveOrUpdateObject(proSafAss);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ProSafAss.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ProSafAss proSafAss = (ProSafAss)this.getObjectById(ProSafAss.class, id);
		proSafAss.setDelFlag(1);
		this.saveObject(proSafAss);
	}
}
