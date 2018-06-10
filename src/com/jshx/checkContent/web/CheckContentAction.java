package com.jshx.checkContent.web;

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
import com.jshx.checkContent.entity.CheckContent;
import com.jshx.checkContent.service.CheckContentService;

public class CheckContentAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private CheckContent checkContent = new CheckContent();

	/**
	 * 业务类
	 */
	@Autowired
	private CheckContentService checkContentService;

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

		if (null != checkContent)
		{
			// 设置查询条件，开发人员可以在此增加过滤条件
			if ((null != checkContent.getContent()) && (0 < checkContent.getContent().trim().length()))
			{
				paraMap.put("content", "%" + checkContent.getContent().trim() + "%");
			}
			if ((null != checkContent.getIsusing()) && (0 < checkContent.getIsusing().trim().length()))
			{
				paraMap.put("isusing", checkContent.getIsusing().trim());
			}
			if ((null != checkContent.getCategory()) && (null != checkContent.getCategory().getId()))
			{
				paraMap.put("categoryId", checkContent.getCategory().getId().trim());
			}
		}

		pagination = checkContentService.findByPage(pagination, paraMap);

		outputJsonList(Integer.valueOf(this.pagination.getTotalCount()),
				"id|category|content|indexNum|isusing|", this.pagination
						.getListOfObject());
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception
	{
		if ((null != checkContent) && (null != checkContent.getId()))
			checkContent = checkContentService.getById(checkContent.getId());

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
			checkContent.setDeptId(this.getLoginUserDepartmentId());
			checkContent.setDelFlag(0);
			checkContentService.save(checkContent);
		}
		else
		{
			checkContentService.update(checkContent);
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
			checkContentService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}
		catch (Exception e)
		{
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
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

	public CheckContent getCheckContent()
	{
		return this.checkContent;
	}

	public void setCheckContent(CheckContent checkContent)
	{
		this.checkContent = checkContent;
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
