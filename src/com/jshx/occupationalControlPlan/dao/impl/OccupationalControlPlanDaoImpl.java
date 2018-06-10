/**
 * Class Name: OccupationalControlPlanDaoImpl
 * Class Description：职业病危害控制效果评价报告表备案通知书
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.occupationalControlPlan.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.occupationalControlPlan.entity.OccupationalControlPlan;
import com.jshx.occupationalControlPlan.dao.OccupationalControlPlanDao;

@Component("occupationalControlPlanDao")
public class OccupationalControlPlanDaoImpl extends BaseDaoImpl implements OccupationalControlPlanDao
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
		return this.findPageByHqlId("findOccupationalControlPlanByMap", paraMap, page);
	}
	
	/**
	 * Function Name: findOccupationalControlPlan
	 * Function Description：查询所有记录
	 * Parameters：paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public List findOccupationalControlPlan(Map<String, Object> paraMap){
		return this.findListByHqlId("findOccupationalControlPlanByMap", paraMap);
	}

	/**
	 * Function Name:getById
	 * Function Description：根据主键ID查询信息
	 * Parameters：id
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public OccupationalControlPlan getById(String id)
	{
		return (OccupationalControlPlan)this.getObjectById(OccupationalControlPlan.class, id);
	}

	/**
	 * Function Name:save
	 * Function Description：保存信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void save(OccupationalControlPlan occupationalControlPlan)
	{
		occupationalControlPlan.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(occupationalControlPlan);
	}

	/**
	 * Function Name:update
	 * Function Description：修改信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void update(OccupationalControlPlan occupationalControlPlan)
	{
		this.saveOrUpdateObject(occupationalControlPlan);
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
		this.removeObjectById(OccupationalControlPlan.class, id);
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
		OccupationalControlPlan occupationalControlPlan = (OccupationalControlPlan)this.getObjectById(OccupationalControlPlan.class, id);
		occupationalControlPlan.setDelFlag(1);
		this.saveObject(occupationalControlPlan);
	}
}
