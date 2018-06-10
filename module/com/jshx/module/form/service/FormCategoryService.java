/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-25         Huairu.Li          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.form.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.form.entity.FormCategory;

/**  
 * @author   Huairu.Li
 * @version 创建时间：2011-1-25 下午02:51:57 
 */
public interface FormCategoryService extends BaseService{
	
	public List<FormCategory> findCateByparent(String categoryNum);

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
	 * 根据分类编号查找分类
	 * @param paraMap
	 * @return
	 */
	public FormCategory findCategoryByNum(String categoryNum);
	
	/**
	 * 根据分类ID查找分类
	 * @param paraMap
	 * @return
	 */
	public FormCategory findCategoryById(String id);
	
	/**
	 * 保存分类
	 * @param formCategory
	 */
	public void saveFormCategory(FormCategory formCategory);
	
	
	/**
	 * 删除类别记录
	 * @param id
	 */
	public void deleteCategory(String id);
	
	/**
	 * 查找分类是否拥有子节点
	 * @param categoryNum
	 * @return
	 */
	public boolean hasChild(String categoryNum);
	
	/**
	 * 生成子分类的编号
	 * @param categoryNum
	 * @return
	 */
	public String generateCategoryNum(Map<String, Object> paraMap);
}
