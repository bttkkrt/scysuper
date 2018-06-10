/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-25         Huairu.Li          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.form.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.Constants;
import com.jshx.module.form.entity.FormCategory;
import com.jshx.module.form.service.FormCategoryService;

/**  
 * @author   Huairu.Li
 * @version 创建时间：2011-1-25 下午02:51:57 
 */
public class FormCategoryAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	@Autowired() 
	@Qualifier("formCategoryService")
	private FormCategoryService formCategoryService;
	
	private Pagination pagination;
	
	private String categoryNum;
	
	private Long length;
	
	private FormCategory formCategory;
	
	private Integer status=0;
	
	private String id;
	
	/**
	 * 显示下一层节点
	 */
	public void findChildCate() {
		try {
			if (null == formCategory) {
				// 初始化类别树
				List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
				Map<String, Object> root = new HashMap<String, Object>();
				root.put("id", "");
				root.put("text", "表单类别");
				root.put("state", "opened");
				List<FormCategory> cateList = formCategoryService.findCateByparent("");
				List<Map<String, Object>> elements = new ArrayList<Map<String, Object>>();
				for (FormCategory cate : cateList) {
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("id", cate.getCategoryNum());
					item.put("text", cate.getCategoryName());
					if (formCategoryService.findCateByparent(cate.getCategoryNum())
							.size() > 0) {
						item.put("state", "closed");
					}
					elements.add(item);
				}
				root.put("children", elements);
				items.add(root);
				JSONArray json = JSONArray.fromObject(items);
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("application/json;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				response.setHeader("Charset", "utf-8");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().println(json.toString());
			} else {
				List<FormCategory> cateList = formCategoryService.findCateByparent(formCategory.getCategoryNum());
				List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
				for (FormCategory cate : cateList) {
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("id", cate.getCategoryNum());
					item.put("text", cate.getCategoryName());
					if (formCategoryService.findCateByparent(cate.getCategoryNum())
							.size() > 0) {
						item.put("state", "closed");
					}
					items.add(item);
				}
				JSONArray json = JSONArray.fromObject(items);
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("application/json;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				response.setHeader("Charset", "utf-8");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().println(json.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String initList(){
		return SUCCESS;
	}
	
	/**
	 * 搜索表单类别列表
	 * @return
	 */
	public void listCategory(){
		
		pagination = new Pagination(super.getRequest());
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(categoryNum!=null && !categoryNum.trim().equals("0")){
			paraMap.put("categoryNum", categoryNum+"%");
			length=categoryNum.length()+2L;
		}else{
			categoryNum = "0";
			paraMap.put("categoryNum", categoryNum+"%");
			length = 2L;
		}
		paraMap.put("length", length);
		pagination = formCategoryService.findFormCategoryPage(pagination, paraMap);
		
		StringBuffer data = new StringBuffer("{\n");
		data.append("  \"total\":").append(pagination.getTotalCount()).append(",\n");
		data.append("  \"rows\":\n");
		
		final String colNames = new String("categoryNum|categoryName|sortSQ|id|");
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter(){
			public boolean apply(Object source, String name, Object value) { 
				if(colNames.indexOf(name+"|")!=-1)
					return false;
				else
					return true;
			}
		});
		JSONArray json = JSONArray.fromObject(pagination.getListOfObject(), config);
		data.append(json.toString());
		data.append("  \n").append("}");
		
		try{
			this.getResponse().getWriter().println(data);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加子分类
	 * @return
	 */
	public String editCategory(){
		formCategory=new FormCategory();
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(categoryNum!=null && !categoryNum.trim().equals("0")){
			paraMap.put("categoryNum", categoryNum+"%");
			length=categoryNum.length()+2L;
		}else{
			categoryNum="0";
			paraMap.put("categoryNum", categoryNum+"%");
			length=2L;
		}
		paraMap.put("length", length);
		String cateNum=formCategoryService.generateCategoryNum(paraMap);
		if(cateNum==null){
			status=2;
			return EDIT;
		}else{
			if(!categoryNum.equals("0"))
				cateNum=categoryNum+cateNum;
			formCategory.setCategoryNum(cateNum);
		}

		formCategory.setParentCategory(formCategoryService.findCategoryByNum(categoryNum));
		
		return EDIT;
	}
	
	/**
	 * 保存分类
	 * @return
	 */
	public String saveCategory(){
		BasalException ex = null;
		if(formCategory!=null){
			
			/* 后台验证 */
			if(formCategory.getSortSQ()==null)
				formCategory.setSortSQ(0);
			if(formCategory.getCategoryName()==null || formCategory.getCategoryName().trim().equals("")){
				ex = new BasalException(BasalException.NO, Constants.FORM_NAME_NULL_ERROR);
				throw ex;
			}
			
			if(formCategory.getId()==null){
				/*后台验证*/
				if(formCategory.getCategoryNum()==null || formCategory.getCategoryNum().trim().equals("")){
					ex = new BasalException(BasalException.NO, Constants.FORM_CATE_NAME_NULL_ERROR);
					throw ex;
				}else{
					Pattern p=Pattern.compile("^[0-9]+$");
					if(!p.matcher(formCategory.getCategoryNum().trim()).matches()){
						ex = new BasalException(BasalException.NO, "表单类别名称必须为数字");
						throw ex;
					}else{
						if(formCategory.getCategoryNum().trim().length()==1){
							ex = new BasalException(BasalException.NO, "表单类别不存在父类别");
							throw ex;
						}
						if(formCategory.getCategoryNum().trim().length()>2){
							String parentNum=formCategory.getCategoryNum().trim().substring(formCategory.getCategoryNum().trim().length()-2);
							FormCategory parent=formCategoryService.findCategoryByNum(parentNum);
							if(parent==null){
								ex = new BasalException(BasalException.NO, "表单类别编号不合法");
								throw ex;
							}
						}
						FormCategory temp=formCategoryService.findCategoryByNum(formCategory.getCategoryNum().trim());
						if(temp!=null){
							ex = new BasalException(BasalException.NO, Constants.FORM_CATE_CODE_EXITS);
							throw ex;
						}
					}
				}
				
				formCategoryService.saveFormCategory(formCategory);
				status=1;
				return RELOAD;
			}else{
				FormCategory temp=formCategoryService.findCategoryById(formCategory.getId());
				temp.setCategoryName(formCategory.getCategoryName().trim());
				temp.setSortSQ(formCategory.getSortSQ());
				formCategoryService.saveFormCategory(temp);
				try{
					getResponse().getWriter().println("{\"result\":\"true\"}");
				}catch(Exception e){
					
				}
				return null;
			}
		}else{
			ex = new BasalException(BasalException.NO, Constants.FORMCATE_NULL_ERROR);
			throw ex;
		}

	}
	
	/**
	 * 显示表单类别主页面
	 * @return
	 */
	public String index(){
		return SUCCESS;
	}
	
	/**
	 * 显示表单类别树
	 * @return
	 */
	public String showTree(){
		return SUCCESS;
	}
	
	/**
	 * 删除表单类别
	 * @return
	 */
	public void deleteCategory(){
		String[] ids = id.split("\\|");
		for(String idd:ids){
			if(idd!=null && !id.trim().equals(""))
				formCategoryService.deleteCategory(idd);
		}
		try{
			getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			
		}
	}
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String getCategoryNum() {
		return categoryNum;
	}

	public void setCategoryNum(String categoryNum) {
		this.categoryNum = categoryNum;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public FormCategory getFormCategory() {
		return formCategory;
	}

	public void setFormCategory(FormCategory formCategory) {
		this.formCategory = formCategory;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
