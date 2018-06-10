package com.jshx.xkzcxxx.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xkzcxxx.entity.LicCanInf;
import com.jshx.xkzcxxx.dao.LicCanInfDao;

@Component("licCanInfDao")
public class LicCanInfDaoImpl extends BaseDaoImpl implements LicCanInfDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findLicCanInfByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findLicCanInf(Map<String, Object> paraMap){
		return this.findListByHqlId("findLicCanInfByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public LicCanInf getById(String id)
	{
		return (LicCanInf)this.getObjectById(LicCanInf.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(LicCanInf licCanInf)
	{
		licCanInf.setId(null);
		this.saveOrUpdateObject(licCanInf);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(LicCanInf licCanInf)
	{
		this.saveOrUpdateObject(licCanInf);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(LicCanInf.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		LicCanInf licCanInf = (LicCanInf)this.getObjectById(LicCanInf.class, id);
		licCanInf.setDelFlag(1);
		this.saveObject(licCanInf);
	}
}
