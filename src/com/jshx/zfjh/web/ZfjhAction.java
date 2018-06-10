package com.jshx.zfjh.web;

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
import com.jshx.zfjh.entity.Zfjh;
import com.jshx.zfjh.service.ZfjhService;

public class ZfjhAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zfjh zfjh = new Zfjh();

	/**
	 * 业务类
	 */
	@Autowired
	private ZfjhService zfjhService;

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
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != zfjh){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zfjh.getProId()) && (0 < zfjh.getProId().trim().length())){
				paraMap.put("proId", "%" + zfjh.getProId().trim() + "%");
			}

			if ((null != zfjh.getZfjhYear()) && (0 < zfjh.getZfjhYear().trim().length())){
				paraMap.put("zfjhYear", "%" + zfjh.getZfjhYear().trim() + "%");
			}

			if ((null != zfjh.getZfjhWeek()) && (0 < zfjh.getZfjhWeek().trim().length())){
				paraMap.put("zfjhWeek", "%" + zfjh.getZfjhWeek().trim() + "%");
			}

			if ((null != zfjh.getZgqyName()) && (0 < zfjh.getZgqyName().trim().length())){
				paraMap.put("zgqyName", "%" + zfjh.getZgqyName().trim() + "%");
			}

			if ((null != zfjh.getZgqyDay()) && (0 < zfjh.getZgqyDay().trim().length())){
				paraMap.put("zgqyDay", "%" + zfjh.getZgqyDay().trim() + "%");
			}

			if ((null != zfjh.getZgqyContent()) && (0 < zfjh.getZgqyContent().trim().length())){
				paraMap.put("zgqyContent", "%" + zfjh.getZgqyContent().trim() + "%");
			}

			if ((null != zfjh.getHylyName()) && (0 < zfjh.getHylyName().trim().length())){
				paraMap.put("hylyName", "%" + zfjh.getHylyName().trim() + "%");
			}

			if ((null != zfjh.getHylyDay()) && (0 < zfjh.getHylyDay().trim().length())){
				paraMap.put("hylyDay", "%" + zfjh.getHylyDay().trim() + "%");
			}

			if ((null != zfjh.getHylyContent()) && (0 < zfjh.getHylyContent().trim().length())){
				paraMap.put("hylyContent", "%" + zfjh.getHylyContent().trim() + "%");
			}

			if ((null != zfjh.getZhjgName()) && (0 < zfjh.getZhjgName().trim().length())){
				paraMap.put("zhjgName", "%" + zfjh.getZhjgName().trim() + "%");
			}

			if ((null != zfjh.getZhjgDay()) && (0 < zfjh.getZhjgDay().trim().length())){
				paraMap.put("zhjgDay", "%" + zfjh.getZhjgDay().trim() + "%");
			}

			if ((null != zfjh.getZhjgContent()) && (0 < zfjh.getZhjgContent().trim().length())){
				paraMap.put("zhjgContent", "%" + zfjh.getZhjgContent().trim() + "%");
			}

			if ((null != zfjh.getXqzfName()) && (0 < zfjh.getXqzfName().trim().length())){
				paraMap.put("xqzfName", "%" + zfjh.getXqzfName().trim() + "%");
			}

			if ((null != zfjh.getXqzfDay()) && (0 < zfjh.getXqzfDay().trim().length())){
				paraMap.put("xqzfDay", "%" + zfjh.getXqzfDay().trim() + "%");
			}

			if ((null != zfjh.getXqzfContent()) && (0 < zfjh.getXqzfContent().trim().length())){
				paraMap.put("xqzfContent", "%" + zfjh.getXqzfContent().trim() + "%");
			}

		}
		
		pagination = zfjhService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != zfjh)&&(null != zfjh.getId()))
			zfjh = zfjhService.getById(zfjh.getId());
		
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
			zfjh.setDeptId(this.getLoginUserDepartmentId());
			zfjh.setDelFlag(0);
			zfjhService.save(zfjh);
		}else{
			zfjhService.update(zfjh);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			zfjhService.deleteWithFlag(ids);
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

	public Zfjh getZfjh(){
		return this.zfjh;
	}

	public void setZfjh(Zfjh zfjh){
		this.zfjh = zfjh;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
