package com.jshx.yhqd.web;

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

import com.jshx.activiti.service.ActivityManageService;
import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.common.util.Condition;
import com.jshx.company.entity.Company;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.StringUtil;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.yhqd.entity.Yhqd;
import com.jshx.yhqd.service.YhqdService;

public class YhqdAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;
	private String taskId;
	/**
	 * 实体类
	 */
	private Yhqd yhqd = new Yhqd();

	/**
	 * 业务类
	 */
	@Autowired
	private YhqdService yhqdService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryChecktimeStart;

	private Date queryChecktimeEnd;

	private Date queryZgqxStart;

	private Date queryZgqxEnd;

	private Date queryYssjStart;

	private Date queryYssjEnd;
	@Autowired
	private ActivityManageService activityManageService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private SzwxPhotoService szwxPhotoService;
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != yhqd){
		    //设置查询条件，开发人员可以在此增加过滤条件

			if (null != queryChecktimeStart){
				paraMap.put("startChecktime", queryChecktimeStart);
			}

			if (null != queryChecktimeEnd){
				paraMap.put("endChecktime", queryChecktimeEnd);
			}
			if (null != yhqd.getYhContent()){
				paraMap.put("yhContent", "%"+yhqd.getYhContent()+"%");
			}
			if (null != yhqd.getBasic()&&yhqd.getBasic().getCompany()!=null){
				paraMap.put("qymc", "%"+yhqd.getBasic().getCompany().getCompanyname()+"%");
			}
		}
		String dpcode = this.getLoginUserDepartment().getDeptCode();//判断是否是企业部门
		if(dpcode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode"))){//表示企业登录
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			paraMap.put("qyId", company.getCompanyId());
		}
		pagination = yhqdService.findByPage(pagination, paraMap);
		List<Yhqd> data =pagination.getListOfObject();
		for (Yhqd yhqd : data) {
			CheckBasic tcheckBasic = yhqd.getBasic();
			Company tcompany = tcheckBasic.getCompany();
			CheckBasic checkBasic = new CheckBasic();
			Company company = new Company();
			company.setCompanyname(tcompany.getCompanyname());
			checkBasic.setCompany(company);
			checkBasic.setCreateUserID(tcheckBasic.getCreateUserID());
			checkBasic.setCheckTime(tcheckBasic.getCheckTime());
			yhqd.setBasic(checkBasic);
		}
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != yhqd)&&(null != yhqd.getId()))
			yhqd = yhqdService.getById(yhqd.getId());
			Map map = new HashMap();
			map.put("taskProId", yhqd.getId());
			List<PhotoPic> list = szwxPhotoService.findPicPath(map);
			yhqd.setPicList(list);
			map.put("taskProId", "ZG"+yhqd.getId());
			List<PhotoPic> zglist = szwxPhotoService.findPicPath(map);
			yhqd.setZgpicList(zglist);
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		if(this.flag.equalsIgnoreCase("zhenggai")){
			return "zhenggai";
		}else if(this.flag.equalsIgnoreCase("yanshou")){
			return "yanshou";
		}
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			yhqd.setDeptId(this.getLoginUserDepartmentId());
			yhqd.setDelFlag(0);
			yhqdService.save(yhqd);
		}else{
			String oldId = yhqd.getId();
			yhqd.setId(null);
			yhqd.setDefId("");
			yhqdService.save(yhqd);
			//物理删除原来对象，方便重新开启流程
			yhqdService.delete(new String[]{oldId});
		}
		
		if(yhqd.getPicList()!=null){
			for (PhotoPic tt : yhqd.getPicList()) {
				PhotoPic photoPic = szwxPhotoService.getById(tt.getId());
				photoPic.setTaskRemark(tt.getTaskRemark());
				photoPic.setTaskProId(yhqd.getId());
				photoPic.setDelFlag(tt.getDelFlag());
				szwxPhotoService.update(photoPic);
			}
		}
		//开启隐患整改流程
 		Map<String,Object> variables = new HashMap<String,Object> ();
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		//deptCode = deptCode.substring(0,deptCode.length()-3);
		variables.put("deptCode", deptCode);
		variables.put("moveStatus",  "zhenggaitask");
		variables.put("flag", "zhenggai");
		variables.put("workFlowModelId",  yhqd.getId());
		try {
			String defId = activityManageService.StartProcessInstance(yhqd.getId(), "checkTrouble", getLoginUserId(), variables) ;
			Yhqd tyhqd  = yhqdService.getById( yhqd.getId());
			tyhqd.setDefId(defId);
			tyhqd.setEnded(0);
			yhqdService.update(tyhqd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			//yhqdService.deleteWithFlag(ids);
			 String[] idArray = ids.split("\\|");
				if(null != idArray)
				{
					for(String id : idArray)
					{
						Yhqd tyhqd = yhqdService.getById(id);
						if(StringUtil.isNotNullAndNotEmpty(tyhqd.getDefId())){
							activityManageService.endProcess(tyhqd.getDefId());
						}
						tyhqd.setDelFlag(1);
						tyhqd.setEnded(-1);
						yhqdService.update(tyhqd);
					}
				}
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

	public Yhqd getYhqd(){
		return this.yhqd;
	}

	public void setYhqd(Yhqd yhqd){
		this.yhqd = yhqd;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryChecktimeStart(){
		return this.queryChecktimeStart;
	}

	public void setQueryChecktimeStart(Date queryChecktimeStart){
		this.queryChecktimeStart = queryChecktimeStart;
	}

	public Date getQueryChecktimeEnd(){
		return this.queryChecktimeEnd;
	}

	public void setQueryChecktimeEnd(Date queryChecktimeEnd){
		this.queryChecktimeEnd = queryChecktimeEnd;
	}

	public Date getQueryZgqxStart(){
		return this.queryZgqxStart;
	}

	public void setQueryZgqxStart(Date queryZgqxStart){
		this.queryZgqxStart = queryZgqxStart;
	}

	public Date getQueryZgqxEnd(){
		return this.queryZgqxEnd;
	}

	public void setQueryZgqxEnd(Date queryZgqxEnd){
		this.queryZgqxEnd = queryZgqxEnd;
	}

	public Date getQueryYssjStart(){
		return this.queryYssjStart;
	}

	public void setQueryYssjStart(Date queryYssjStart){
		this.queryYssjStart = queryYssjStart;
	}

	public Date getQueryYssjEnd(){
		return this.queryYssjEnd;
	}

	public void setQueryYssjEnd(Date queryYssjEnd){
		this.queryYssjEnd = queryYssjEnd;
	}

	/**  
	 * 获取taskId  
	 * @return taskId taskId  
	 */
	public String getTaskId() {
		return taskId;
	}

	/**  
	 * 设置taskId  
	 * @param taskId taskId  
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
