/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-24         Huairu.Li           create
 * ---------------------------------------------------------------
 */
package com.jshx.module.form.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.form.dao.FormCategoryDao;
import com.jshx.module.form.entity.FormCategory;

/**
 * @author Huairu.Li
 * @version 创建时间：2011-1-24 上午10:11:35 
 * 类说明
 */
@Component("formCategoryDao")
public class FormCategoryDaoImpl extends BaseDaoImpl implements FormCategoryDao{

	public Pagination findFormCategoryPage(Pagination page, Map<String, Object> paraMap) {
		return this.findPageByHqlId("findFormCategory", paraMap, page);
	}

	@SuppressWarnings("unchecked")
	public Integer findMaxCategoryNum(Map<String, Object> paraMap) {
		List results=this.findListByHqlId("findMaxCategoryNum", paraMap);
		if(results!=null && results.size()!=0)
			return (Integer) results.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<FormCategory> findFormCategoryList(Map<String, Object> paraMap){
		return (List<FormCategory>)this.findListByHqlId("findFormCategory", paraMap);
	}
}
