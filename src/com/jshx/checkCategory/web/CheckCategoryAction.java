package com.jshx.checkCategory.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONArray;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.checkCategory.entity.CheckCategory;
import com.jshx.checkCategory.service.CheckCategoryService;

public class CheckCategoryAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private CheckCategory checkCategory = new CheckCategory();

	/**
	 * 业务类
	 */
	@Autowired
	private CheckCategoryService checkCategoryService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;

	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if (pagination == null)
			pagination = new Pagination(this.getRequest());

		if (null != checkCategory)
		{
			// 设置查询条件，开发人员可以在此增加过滤条件
			if ((null != checkCategory.getContent()) && (0 < checkCategory.getContent().trim().length()))
			{
				paraMap.put("content", "%" + checkCategory.getContent().trim() + "%");
			}

			if ((null != checkCategory.getIsusing()) && (0 < checkCategory.getIsusing().trim().length()))
			{
				paraMap.put("isusing", checkCategory.getIsusing().trim());
			}

		}

		pagination = checkCategoryService.findByPage(pagination, paraMap);

		outputJsonList(Integer.valueOf(this.pagination.getTotalCount()),
				"id|content|indexNum|isusing|", this.pagination
						.getListOfObject());
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception
	{
		if ((null != checkCategory) && (null != checkCategory.getId()))
			checkCategory = checkCategoryService.getById(checkCategory.getId());

		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception
	{
		view();
		return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception
	{
		if ("add".equalsIgnoreCase(this.flag))
		{
			checkCategory.setDeptId(this.getLoginUserDepartmentId());
			checkCategory.setDelFlag(0);
			checkCategoryService.save(checkCategory);
		}
		else
		{
			checkCategoryService.update(checkCategory);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception
	{
		try
		{
			checkCategoryService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}
		catch (Exception e)
		{
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}

	/**
	 * 检查类别树
	 */
	public String categoryTree()
	{
		return SUCCESS;
	}

	/**
	 * 查询检查类别列表
	 */
	public void queryCategory()
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		List<CheckCategory> categoryList = checkCategoryService.findCheckCategory(paraMap);
		List items = new ArrayList();
		for (CheckCategory m : categoryList)
		{
			Map item = new HashMap();
			item.put("id", m.getId());
			item.put("text", m.getContent());
			item.put("iconCls", "icon-module");

			items.add(item);
		}
		writerJSONArray(items);
	}

	public String getIds()
	{
		return ids;
	}

	public void setIds(String ids)
	{
		this.ids = ids;
	}

	public Pagination getPagination()
	{
		return pagination;
	}

	public void setPagination(Pagination pagination)
	{
		this.pagination = pagination;
	}

	public CheckCategory getCheckCategory()
	{
		return this.checkCategory;
	}

	public void setCheckCategory(CheckCategory checkCategory)
	{
		this.checkCategory = checkCategory;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}
}
