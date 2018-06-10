/**
 * Class Name:DangerousChemicalsPlanService
 * Class Description：应急救援预案备案事项告知书
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.emergencyRescuePlan.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.emergencyRescuePlan.dao.EmergencyRescuePlanDao;
import com.jshx.emergencyRescuePlan.entity.EmergencyRescuePlan;
import com.jshx.emergencyRescuePlan.service.EmergencyRescuePlanService;

@Service("emergencyRescuePlanService")
public class EmergencyRescuePlanServiceImpl extends BaseServiceImpl implements EmergencyRescuePlanService
{
	/**
	 * Dao类
	 */
	@Resource
	private EmergencyRescuePlanDao emergencyRescuePlanDao;

	/**
	 * Function Name:findByPage
	 * Function Description：分页查询
	 * Parameters：page,paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return emergencyRescuePlanDao.findByPage(page, paraMap);
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
		return emergencyRescuePlanDao.getById(id);
	}

	/**
	 * Function Name:save
	 * Function Description：保存信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void save(EmergencyRescuePlan emergencyRescuePlan)
	{
		emergencyRescuePlanDao.save(emergencyRescuePlan);
	}

	/**
	 * Function Name:update
	 * Function Description：修改信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void update(EmergencyRescuePlan emergencyRescuePlan)
	{
		emergencyRescuePlanDao.update(emergencyRescuePlan);
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
		List objects=emergencyRescuePlanDao.findEmergencyRescuePlan(paraMap);
		
		emergencyRescuePlanDao.removeAll(objects);
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
				    emergencyRescuePlanDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<EmergencyRescuePlan> findEmergencyRescuePlan(
			Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return emergencyRescuePlanDao.findEmergencyRescuePlan(paraMap);
	}
}
