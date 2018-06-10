/**
 * Class Name: DestroyDangerousPlanServiceImpl
 * Class Description：危险化学品重大危险源核销告知书
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.destroyDangerousChemicalsPlan.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.destroyDangerousChemicalsPlan.dao.DestroyDangerousPlanDao;
import com.jshx.destroyDangerousChemicalsPlan.entity.DestroyDangerousPlan;
import com.jshx.destroyDangerousChemicalsPlan.service.DestroyDangerousPlanService;

@Service("destroyDangerousPlanService")
public class DestroyDangerousPlanServiceImpl extends BaseServiceImpl implements DestroyDangerousPlanService
{
	/**
	 * Dao类
	 */
	@Resource
	private DestroyDangerousPlanDao destroyDangerousPlanDao;

	/**
	 * Function Name:findByPage
	 * Function Description：分页查询
	 * Parameters：page,paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return destroyDangerousPlanDao.findByPage(page, paraMap);
	}

	/**
	 * Function Name:getById
	 * Function Description：根据主键ID查询信息
	 * Parameters：id
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public DestroyDangerousPlan getById(String id)
	{
		return destroyDangerousPlanDao.getById(id);
	}

	/**
	 * Function Name:save
	 * Function Description：保存信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void save(DestroyDangerousPlan destroyDangerousPlan)
	{
		destroyDangerousPlanDao.save(destroyDangerousPlan);
	}

	/**
	 * Function Name:update
	 * Function Description：修改信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	@Transactional
	public void update(DestroyDangerousPlan destroyDangerousPlan)
	{
		destroyDangerousPlanDao.update(destroyDangerousPlan);
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
		List objects=destroyDangerousPlanDao.findDestroyDangerousPlan(paraMap);
		
		destroyDangerousPlanDao.removeAll(objects);
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
				    destroyDangerousPlanDao.deleteWithFlag(id);
			}
		}
	}
}
