/**
 * Class Name: OccupationalEvaluatePlanServiceImpl
 * Class Description：职业病危害预评价报告表备案通知书
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.occupationalEvaluatePlan.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.occupationalEvaluatePlan.dao.OccupationalEvaluatePlanDao;
import com.jshx.occupationalEvaluatePlan.entity.OccupationalEvaluatePlan;
import com.jshx.occupationalEvaluatePlan.service.OccupationalEvaluatePlanService;

@Service("occupationalEvaluatePlanService")
public class OccupationalEvaluatePlanServiceImpl extends BaseServiceImpl implements OccupationalEvaluatePlanService
{
	/**
	 * Dao类
	 */
	@Resource
	private OccupationalEvaluatePlanDao occupationalEvaluatePlanDao;

	/**
	 * Function Name:findByPage
	 * Function Description：分页查询
	 * Parameters：page,paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return occupationalEvaluatePlanDao.findByPage(page, paraMap);
	}

	/**
	 * Function Name:getById
	 * Function Description：根据主键ID查询信息
	 * Parameters：id
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public OccupationalEvaluatePlan getById(String id)
	{
		return occupationalEvaluatePlanDao.getById(id);
	}

	/**
	 * Function Name:save
	 * Function Description：保存信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void save(OccupationalEvaluatePlan occupationalEvaluatePlan)
	{
		occupationalEvaluatePlanDao.save(occupationalEvaluatePlan);
	}

	/**
	 * Function Name:update
	 * Function Description：修改信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void update(OccupationalEvaluatePlan occupationalEvaluatePlan)
	{
		occupationalEvaluatePlanDao.update(occupationalEvaluatePlan);
	}

	/**
	 * Function Name ： delete
	 * Function Description：物理删除信息
	 * Parameters：ids
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void delete(String[] ids)
	{
		List list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects=occupationalEvaluatePlanDao.findOccupationalEvaluatePlan(paraMap);
		
		occupationalEvaluatePlanDao.removeAll(objects);
	}

	/**
	 * Function Name ： deleteWithFlag
	 * Function Description： 逻辑删除信息
	 * Parameters：ids
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void deleteWithFlag(String ids)
	{
	    String[] idArray = ids.split("\\|");
		if(null != idArray)
		{
			for(String id : idArray)
			{
			    if(id!=null && !id.trim().equals(""))
				    occupationalEvaluatePlanDao.deleteWithFlag(id);
			}
		}
	}
}
