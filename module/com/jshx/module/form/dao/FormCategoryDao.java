/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-24         Huairu.Li           create
 * ---------------------------------------------------------------
 */
package com.jshx.module.form.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.form.entity.FormCategory;

/**
 * @author Huairu.Li
 * @version 创建时间：2011-1-24 上午10:11:35 
 * 类说明
 */
public interface FormCategoryDao extends BaseDao{

	/**
	 * 根据条件分页查找表单类别
	 * @param page
	 * @param paraMap
	 * @return
	 */
	public Pagination findFormCategoryPage(Pagination page, Map<String, Object> paraMap);
	
	/**
	 * 查找分类下子分类编号最大值
	 * @param paraMap
	 * @return
	 */
	public Integer findMaxCategoryNum(Map<String, Object> paraMap);
	
	/**
	 * 根据条件查找所有表单类别
	 */
	public List<FormCategory> findFormCategoryList(Map<String, Object> paraMap);
}
