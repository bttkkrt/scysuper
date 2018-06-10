/**
 * Class Name: DangerousChemicalsPlanDao
 * Class Description：危险化学品重大危险源备案告知书
 * Writer：陆婷
 * CreateTime：2013-11-18
 */
package com.jshx.dangerousChemicalsPlan.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.dangerousChemicalsPlan.entity.DangerousChemicalsPlan;


public interface DangerousChemicalsPlanDao extends BaseDao
{
	/**
	 * Function Name:findByPage
	 * Function Description：分页查询
	 * Parameters：page,paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);
	
	/**
	 * Function Name:findDangerousChemicalsPlan
	 * Function Description：查找列表
	 * Parameters：paraMap
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public List findDangerousChemicalsPlan(Map<String, Object> paraMap);

	/**
	 * Function Name:getById
	 * Function Description：根据主键ID查询信息
	 * Parameters：id
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public DangerousChemicalsPlan getById(String id);

	/**
	 * Function Name:save
	 * Function Description：保存信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void save(DangerousChemicalsPlan model);

	/**
	 * Function Name:update
	 * Function Description：修改信息
	 * Parameters：model
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void update(DangerousChemicalsPlan model);

	/**
	 * Function Name ： delete
	 * Function Description：物理删除信息
	 * Parameters：ids
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void delete(String id);

	/**
	 * Function Name ： deleteWithFlag
	 * Function Description： 逻辑删除信息
	 * Parameters：ids
	 * Writer：陆婷
	 * CreateTime：2013-11-18
	 */
	public void deleteWithFlag(String id);
}
