/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-25         Huairu.Li          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.form.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.form.dao.FormCategoryDao;
import com.jshx.module.form.entity.FormCategory;
import com.jshx.module.form.service.FormCategoryService;

/**  
 * @author   Huairu.Li
 * @version 创建时间：2011-1-25 下午02:51:57 
 */
@Service("formCategoryService")
public class FormCategoryServiceImpl extends BaseServiceImpl implements FormCategoryService{

	@Autowired() 
	@Qualifier("formCategoryDao")
	private FormCategoryDao formCategoryDao;
	
	public Pagination findFormCategoryPage(Pagination page, Map<String, Object> paraMap) {
		return formCategoryDao.findFormCategoryPage(page, paraMap);
	}
	
	public Integer findMaxCategoryNum(Map<String, Object> paraMap){
		return formCategoryDao.findMaxCategoryNum(paraMap);
	}
	
	public FormCategory findCategoryByNum(String categoryNum){
		return (FormCategory)formCategoryDao.getObjectByProperty(FormCategory.class, "categoryNum", categoryNum);
	}
	
	@Transactional
	public void saveFormCategory(FormCategory formCategory){
		formCategoryDao.saveOrUpdateObject(formCategory);
	}
	
	
	@Transactional
	public void deleteCategory(String id){
		FormCategory category=(FormCategory)formCategoryDao.getObjectById(FormCategory.class, id);
		
		//删除类别节点
		formCategoryDao.removeObjectById(FormCategory.class, id);
		
		//删除类别下所有子类别节点
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("categoryNum", category.getCategoryNum()+"%");
		List<FormCategory> categoryList=formCategoryDao.findFormCategoryList(paraMap);
		formCategoryDao.removeAll(categoryList);
	}
	
	public boolean hasChild(String categoryNum){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(categoryNum.equals("0")){
			paraMap.put("length", 2L);
		}else{
			paraMap.put("categoryNum", categoryNum+"%");
			paraMap.put("length", categoryNum.length()+2L);
		}
		List<FormCategory> result=formCategoryDao.findFormCategoryList(paraMap);
		if(result!=null && result.size()!=0)
			return true;
		else
			return false;
	}
	
	public String generateCategoryNum(Map<String, Object> paraMap){	
		Integer maxNum=this.findMaxCategoryNum(paraMap);
		
		if(maxNum==null)
			return "01";
		
		if(maxNum==99){
			paraMap.put("sortSQ", false);
			List<FormCategory> result=formCategoryDao.findFormCategoryList(paraMap);
			if(result.size()==99)
				return null;
			else{
				int i=0;
				for(;i<result.size();i++){
					String tempNum=result.get(i).getCategoryNum();
					int value=Integer.parseInt(tempNum.substring(tempNum.length()-2));
					if(value!=i+1)
						break;
				}
				maxNum=i;
			}
		}
		
		if(maxNum<9)
			return "0"+(++maxNum);
		else
			return String.valueOf(++maxNum);
	}
	
	public FormCategory findCategoryById(String id){
		return (FormCategory)formCategoryDao.getObjectById(FormCategory.class, id);
	}
	
	/**setter and getter**/
	public FormCategoryDao getFormCategoryDao() {
		return formCategoryDao;
	}

	public void setFormCategoryDao(FormCategoryDao formCategoryDao) {
		this.formCategoryDao = formCategoryDao;
	}

	public List<FormCategory> findCateByparent(String categoryNum) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(categoryNum.equals("")){
			paraMap.put("length", 2L);
		}else{
			paraMap.put("categoryNum", categoryNum+"%");
			paraMap.put("length", categoryNum.length()+2L);
		}
		
		return formCategoryDao.findFormCategoryList(paraMap);
	}

}
