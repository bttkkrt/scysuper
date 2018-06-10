/**
 * Class Name : DangerousChemicalsPlanDaoImpl
 * Class Description：危险化学品重大危险源备案告知书
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.dangerousChemicalsPlan.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.dangerousChemicalsPlan.entity.DangerousChemicalsPlan;
import com.jshx.dangerousChemicalsPlan.dao.DangerousChemicalsPlanDao;

@Component("dangerousChemicalsPlanDao")
public class DangerousChemicalsPlanDaoImpl extends BaseDaoImpl implements DangerousChemicalsPlanDao
{
	/**
	 * Function Name:findByPage
	 * Function Description：分页查询
	 * Parameters：page,paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findDangerousChemicalsPlanByMap", paraMap, page);
	}
	
	/**
	 * Function Name:findDangerousChemicalsPlan
	 * Function Description：查找列表
	 * Parameters：paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public List findDangerousChemicalsPlan(Map<String, Object> paraMap){
		return this.findListByHqlId("findDangerousChemicalsPlanByMap", paraMap);
	}

	/**
	 * Function Name:save
	 * Function Description：保存信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public DangerousChemicalsPlan getById(String id)
	{
		return (DangerousChemicalsPlan)this.getObjectById(DangerousChemicalsPlan.class, id);
	}

	/**
	 * Function Name:save
	 * Function Description：保存信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void save(DangerousChemicalsPlan dangerousChemicalsPlan)
	{
		dangerousChemicalsPlan.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(dangerousChemicalsPlan);
	}

	/**
	 * Function Name:update
	 * Function Description：修改信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void update(DangerousChemicalsPlan dangerousChemicalsPlan)
	{
		this.saveOrUpdateObject(dangerousChemicalsPlan);
	}

	/**
	 * Function Name ： delete
	 * Function Description：物理删除信息
	 * Parameters：ids
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void delete(String id)
	{
		this.removeObjectById(DangerousChemicalsPlan.class, id);
	}

	/**
	 * Function Name ： deleteWithFlag
	 * Function Description： 逻辑删除信息
	 * Parameters：ids
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void deleteWithFlag(String id)
	{
		DangerousChemicalsPlan dangerousChemicalsPlan = (DangerousChemicalsPlan)this.getObjectById(DangerousChemicalsPlan.class, id);
		dangerousChemicalsPlan.setDelFlag(1);
		this.saveObject(dangerousChemicalsPlan);
	}
}
