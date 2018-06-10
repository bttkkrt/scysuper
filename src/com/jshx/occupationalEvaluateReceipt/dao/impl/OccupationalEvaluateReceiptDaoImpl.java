/**
 * Class Name: OccupationalEvaluatePlanDaoImpl
 * Class Description：职业病危害预评价报告表备案通知书
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.occupationalEvaluateReceipt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.occupationalEvaluateReceipt.dao.OccupationalEvaluateReceiptDao;
import com.jshx.occupationalEvaluateReceipt.entity.OccupationalEvaluateReceipt;

@Component("occupationalEvaluateReceiptDao")
public class OccupationalEvaluateReceiptDaoImpl extends BaseDaoImpl implements OccupationalEvaluateReceiptDao
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
		return this.findPageByHqlId("findOccupationalEvaluateReceiptByMap", paraMap, page);
	}
	
	/**
	 * Function Name: findOccupationalEvaluatePlan
	 * Function Description：查询所有记录
	 * Parameters：paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public List findOccupationalEvaluateReceipt(Map<String, Object> paraMap){
		return this.findListByHqlId("findOccupationalEvaluateReceiptByMap", paraMap);
	}

	/**
	 * Function Name:getById
	 * Function Description：根据主键ID查询信息
	 * Parameters：id
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public OccupationalEvaluateReceipt getById(String id)
	{
		return (OccupationalEvaluateReceipt)this.getObjectById(OccupationalEvaluateReceipt.class, id);
	}

	/**
	 * Function Name:save
	 * Function Description：保存信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void save(OccupationalEvaluateReceipt occupationalEvaluateReceipt)
	{
		occupationalEvaluateReceipt.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(occupationalEvaluateReceipt);
	}

	/**
	 * Function Name:update
	 * Function Description：修改信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void update(OccupationalEvaluateReceipt occupationalEvaluateReceipt)
	{
		this.saveOrUpdateObject(occupationalEvaluateReceipt);
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
		this.removeObjectById(OccupationalEvaluateReceipt.class, id);
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
		OccupationalEvaluateReceipt occupationalEvaluateReceipt = (OccupationalEvaluateReceipt)this.getObjectById(OccupationalEvaluateReceipt.class, id);
		occupationalEvaluateReceipt.setDelFlag(1);
		this.saveObject(occupationalEvaluateReceipt);
	}
}
