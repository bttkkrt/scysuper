/**
 * Class Name: DestroyDangerousPlanDaoImpl
 * Class Description：危险化学品重大危险源核销告知书
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.destroyDangerousChemicalsPlan.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.destroyDangerousChemicalsPlan.dao.DestroyDangerousPlanDao;
import com.jshx.destroyDangerousChemicalsPlan.entity.DestroyDangerousPlan;

@Component("destroyDangerousPlanDao")
public class DestroyDangerousPlanDaoImpl extends BaseDaoImpl implements DestroyDangerousPlanDao
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
		return this.findPageByHqlId("findDestroyDangerousPlanByMap", paraMap, page);
	}
	
	/**
	 * Function Name:findDestroyDangerousPlan
	 * Function Description：查询所有记录
	 * Parameters：paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public List findDestroyDangerousPlan(Map<String, Object> paraMap){
		return this.findListByHqlId("findDestroyDangerousPlanByMap", paraMap);
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
		return (DestroyDangerousPlan)this.getObjectById(DestroyDangerousPlan.class, id);
	}

	/**
	 * Function Name:save
	 * Function Description：保存信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void save(DestroyDangerousPlan destroyDangerousPlan)
	{
		destroyDangerousPlan.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(destroyDangerousPlan);
	}

	/**
	 * Function Name:update
	 * Function Description：修改信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void update(DestroyDangerousPlan destroyDangerousPlan)
	{
		this.saveOrUpdateObject(destroyDangerousPlan);
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
		this.removeObjectById(DestroyDangerousPlan.class, id);
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
		DestroyDangerousPlan destroyDangerousPlan = (DestroyDangerousPlan)this.getObjectById(DestroyDangerousPlan.class, id);
		destroyDangerousPlan.setDelFlag(1);
		this.saveObject(destroyDangerousPlan);
	}
}
