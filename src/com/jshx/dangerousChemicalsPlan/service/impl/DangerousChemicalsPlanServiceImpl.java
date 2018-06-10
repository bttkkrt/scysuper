/**
 * Class Name: DangerousChemicalsPlanServiceImpl
 * Class Description：危险化学品重大危险源备案告知书
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.dangerousChemicalsPlan.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.dangerousChemicalsPlan.dao.DangerousChemicalsPlanDao;
import com.jshx.dangerousChemicalsPlan.entity.DangerousChemicalsPlan;
import com.jshx.dangerousChemicalsPlan.service.DangerousChemicalsPlanService;

@Service("dangerousChemicalsPlanService")
public class DangerousChemicalsPlanServiceImpl extends BaseServiceImpl implements DangerousChemicalsPlanService
{
	/**
	 * Dao类
	 */
	@Resource
	private DangerousChemicalsPlanDao dangerousChemicalsPlanDao;

	/**
	 * Function Name:findByPage
	 * Function Description：分页查询
	 * Parameters：page,paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return dangerousChemicalsPlanDao.findByPage(page, paraMap);
	}

	/**
	 * Function Name:getById
	 * Function Description：根据主键ID查询信息
	 * Parameters：id
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public DangerousChemicalsPlan getById(String id)
	{
		return dangerousChemicalsPlanDao.getById(id);
	}

	/**
	 * Function Name:save
	 * Function Description：保存信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void save(DangerousChemicalsPlan dangerousChemicalsPlan)
	{
		dangerousChemicalsPlanDao.save(dangerousChemicalsPlan);
	}

	/**
	 * Function Name:update
	 * Function Description：修改信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void update(DangerousChemicalsPlan dangerousChemicalsPlan)
	{
		dangerousChemicalsPlanDao.update(dangerousChemicalsPlan);
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
		List objects=dangerousChemicalsPlanDao.findDangerousChemicalsPlan(paraMap);
		
		dangerousChemicalsPlanDao.removeAll(objects);
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
				    dangerousChemicalsPlanDao.deleteWithFlag(id);
			}
		}
	}
}
