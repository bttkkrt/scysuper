/**
 * Class Name:DangerousChemicalsPlanService
 * Class Description：应急救援预案备案事项告知书
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.emergencyRescuePlan.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.emergencyRescuePlan.entity.EmergencyRescuePlan;
import com.jshx.emergencyRescuePlan.dao.EmergencyRescuePlanDao;

@Component("emergencyRescuePlanDao")
public class EmergencyRescuePlanDaoImpl extends BaseDaoImpl implements EmergencyRescuePlanDao
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
		return this.findPageByHqlId("findEmergencyRescuePlanByMap", paraMap, page);
	}
	
	/**
	 * Function Name: findEmergencyRescuePlan
	 * Function Description：查询所有记录
	 * Parameters：id
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public List<EmergencyRescuePlan> findEmergencyRescuePlan(Map<String, Object> paraMap){
		return this.findListByHqlId("findEmergencyRescuePlanByMap", paraMap);
	}

	/**
	 * Function Name:getById
	 * Function Description：根据主键ID查询信息
	 * Parameters：id
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public EmergencyRescuePlan getById(String id)
	{
		return (EmergencyRescuePlan)this.getObjectById(EmergencyRescuePlan.class, id);
	}

	/**
	 * Function Name:save
	 * Function Description：保存信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void save(EmergencyRescuePlan emergencyRescuePlan)
	{
		emergencyRescuePlan.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(emergencyRescuePlan);
	}

	/**
	 * Function Name:update
	 * Function Description：修改信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void update(EmergencyRescuePlan emergencyRescuePlan)
	{
		this.saveOrUpdateObject(emergencyRescuePlan);
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
		this.removeObjectById(EmergencyRescuePlan.class, id);
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
		EmergencyRescuePlan emergencyRescuePlan = (EmergencyRescuePlan)this.getObjectById(EmergencyRescuePlan.class, id);
		emergencyRescuePlan.setDelFlag(1);
		this.saveObject(emergencyRescuePlan);
	}
}
