package com.jshx.carequipment.web;

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
import com.jshx.carequipment.entity.Carequipment;
import com.jshx.carequipment.service.CarequipmentService;

public class CarequipmentAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Carequipment carequipment = new Carequipment();

	/**
	 * 业务类
	 */
	@Autowired
	private CarequipmentService carequipmentService;

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
		    
		if(null != carequipment){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != carequipment.getCompanyname()) && (0 < carequipment.getCompanyname().trim().length())){
				paraMap.put("companyname", "%" + carequipment.getCompanyname().trim() + "%");
			}

			if ((null != carequipment.getDetailname()) && (0 < carequipment.getDetailname().trim().length())){
				paraMap.put("detailname", "%" + carequipment.getDetailname().trim() + "%");
			}

			if ((null != carequipment.getGuid()) && (0 < carequipment.getGuid().trim().length())){
				paraMap.put("guid", "%" + carequipment.getGuid().trim() + "%");
			}

			if ((null != carequipment.getPuid()) && (0 < carequipment.getPuid().trim().length())){
				paraMap.put("puid", "%" + carequipment.getPuid().trim() + "%");
			}

			if ((null != carequipment.getStreamid()) && (0 < carequipment.getStreamid().trim().length())){
				paraMap.put("streamid", "%" + carequipment.getStreamid().trim() + "%");
			}

		}
		
		pagination = carequipmentService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != carequipment)&&(null != carequipment.getId()))
			carequipment = carequipmentService.getById(carequipment.getId());
		
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
			carequipment.setDeptId(this.getLoginUserDepartmentId());
			carequipment.setDelFlag(0);
			carequipmentService.save(carequipment);
		}else{
			carequipmentService.update(carequipment);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			carequipmentService.deleteWithFlag(ids);
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

	public Carequipment getCarequipment(){
		return this.carequipment;
	}

	public void setCarequipment(Carequipment carequipment){
		this.carequipment = carequipment;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
