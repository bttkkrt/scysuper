package com.jshx.module.infomation.web;

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
import com.jshx.module.infomation.entity.ContentAttachs;
import com.jshx.module.infomation.service.ContentAttachsService;

public class ContentAttachsAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private ContentAttachs contentAttachs = new ContentAttachs();

	/**
	 * 业务类
	 */
	@Autowired
	private ContentAttachsService contentAttachsService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	
	public String initList(){
	    return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回查询结果的json数据
	 */
	public String list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != contentAttachs){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != contentAttachs.getInfoId()) && (0 < contentAttachs.getInfoId().trim().length())){
				paraMap.put("infoId", "%" + contentAttachs.getInfoId().trim() + "%");
			}

			if ((null != contentAttachs.getDocUrl()) && (0 < contentAttachs.getDocUrl().trim().length())){
				paraMap.put("docUrl", "%" + contentAttachs.getDocUrl().trim() + "%");
			}

			if ((null != contentAttachs.getDocType()) && (0 < contentAttachs.getDocType().trim().length())){
				paraMap.put("docType", contentAttachs.getDocType().trim());
			}

		}
		
		pagination = contentAttachsService.findByPage(pagination, paraMap);
		
		//将查询结果转为json数据给datagrid展现
		StringBuffer data = new StringBuffer("{\n");
		data.append("  \"total\":").append(pagination.getTotalCount()).append(",\n");
		data.append("  \"rows\":\n");
		
		JSONArray json = JSONArray.fromObject(pagination.getListOfObject());
		data.append(json.toString());
		data.append("  \n").append("}");
		
		getResponse().getWriter().println(data);
		return null;
	}

	/**
	 * 查看信息附件的详细信息
	 */
	public String view() throws Exception{
		if((null != contentAttachs)&&(null != contentAttachs.getId()))
			contentAttachs = contentAttachsService.getById(contentAttachs.getId());
		
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			contentAttachs.setDeptId(this.getLoginUserDepartmentId());
			contentAttachs.setDelFlag(0);
			contentAttachsService.save(contentAttachs);
		}else{
			contentAttachsService.update(contentAttachs);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			contentAttachsService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	public String getIds(){
		return ids;
	}

	public void setIds(String ids){
		this.ids = ids;
	}

	public Pagination getPagination(){
		return pagination;
	}

	public void setPagination(Pagination pagination){
		this.pagination = pagination;
	}

	public ContentAttachs getContentAttachs(){
		return this.contentAttachs;
	}

	public void setContentAttachs(ContentAttachs contentAttachs){
		this.contentAttachs = contentAttachs;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
