package com.jshx.lawsRegulations.web;

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
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.regulationsLevel.service.RegulationsLevelService;
import com.jshx.lawsRegulations.entity.LawsRegulations;
import com.jshx.lawsRegulations.service.LawsRegulationsService;

public class LawsRegulationsAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private LawsRegulations lawsRegulations = new LawsRegulations();

	/**
	 * 业务类
	 */
	@Autowired
	private LawsRegulationsService lawsRegulationsService;
	
	@Autowired
	private RegulationsLevelService regulationsLevelService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date querySxtimeStart;

	private Date querySxtimeEnd;

	private Date queryFbtimeStart;

	private Date queryFbtimeEnd;

	/**
	 * 初始化列表
	 * @return
	 */
	public String init()
	{
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(this.getLoginUser().getLoginId().equals("admin")){//管理员可以添加，企业人员只能查看
			flag = "1";
		}else{
			flag = "0";
		}
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != lawsRegulations){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != lawsRegulations.getTitle()) && (0 < lawsRegulations.getTitle().trim().length())){
				paraMap.put("title", "%" + lawsRegulations.getTitle().trim() + "%");
			}

			if (null != queryFbtimeStart){
				paraMap.put("startFbtime", queryFbtimeStart);
			}

			if (null != queryFbtimeEnd){
				paraMap.put("endFbtime", queryFbtimeEnd);
			}
			if (null != querySxtimeStart){
				paraMap.put("startSxtime", querySxtimeStart);
			}

			if (null != querySxtimeEnd){
				paraMap.put("endSxtime", querySxtimeEnd);
			}
			if ((null != lawsRegulations.getFabr()) && (0 < lawsRegulations.getFabr().trim().length())){
				paraMap.put("fabr", "%" + lawsRegulations.getFabr().trim() + "%");
			}

			if ((null != lawsRegulations.getFgstatus()) && (0 < lawsRegulations.getFgstatus().trim().length())){
				paraMap.put("fgstatus", "%" + lawsRegulations.getFgstatus().trim() + "%");
			}

			if ((null != lawsRegulations.getRegutionsid()) && (0 < lawsRegulations.getRegutionsid().trim().length())){
				paraMap.put("regutionsid", "%" + lawsRegulations.getRegutionsid().trim() + "%");
			}

		}
		
		pagination = lawsRegulationsService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != lawsRegulations)&&(null != lawsRegulations.getId()))
			lawsRegulations = lawsRegulationsService.getById(lawsRegulations.getId());
			if("view".equalsIgnoreCase(this.flag)){
				String name = regulationsLevelService.findName(lawsRegulations.getRegutionsid());
				lawsRegulations.setRegutionsid(name);
			}
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		User user = this.getLoginUser();
		view();
		if("add".equalsIgnoreCase(this.flag)){
			lawsRegulations.setFabr(user.getDisplayName());
		}
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			lawsRegulations.setDeptId(this.getLoginUserDepartmentId());
			lawsRegulations.setDelFlag(0);
			lawsRegulationsService.save(lawsRegulations);
		}else{
			lawsRegulationsService.update(lawsRegulations);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			lawsRegulationsService.deleteWithFlag(ids);
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

	public LawsRegulations getLawsRegulations(){
		return this.lawsRegulations;
	}

	public void setLawsRegulations(LawsRegulations lawsRegulations){
		this.lawsRegulations = lawsRegulations;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQuerySxtimeStart(){
		return this.querySxtimeStart;
	}

	public void setQuerySxtimeStart(Date querySxtimeStart){
		this.querySxtimeStart = querySxtimeStart;
	}

	public Date getQuerySxtimeEnd(){
		return this.querySxtimeEnd;
	}

	public void setQuerySxtimeEnd(Date querySxtimeEnd){
		this.querySxtimeEnd = querySxtimeEnd;
	}

	public Date getQueryFbtimeStart(){
		return this.queryFbtimeStart;
	}

	public void setQueryFbtimeStart(Date queryFbtimeStart){
		this.queryFbtimeStart = queryFbtimeStart;
	}

	public Date getQueryFbtimeEnd(){
		return this.queryFbtimeEnd;
	}

	public void setQueryFbtimeEnd(Date queryFbtimeEnd){
		this.queryFbtimeEnd = queryFbtimeEnd;
	}

}
