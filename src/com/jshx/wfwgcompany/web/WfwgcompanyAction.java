package com.jshx.wfwgcompany.web;

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
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.wfwgcompany.entity.Wfwgcompany;
import com.jshx.wfwgcompany.service.WfwgcompanyService;

public class WfwgcompanyAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Wfwgcompany wfwgcompany = new Wfwgcompany();

	/**
	 * 业务类
	 */
	@Autowired
	private WfwgcompanyService wfwgcompanyService;
	@Autowired
	private DeptService deptService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private List<Department> deptlist = new ArrayList<Department>();
	
	
	public String init()
	{
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != wfwgcompany){
			 //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != wfwgcompany.getCompanyname()) && (0 < wfwgcompany.getCompanyname().trim().length())){
				paraMap.put("companyname", "%"+wfwgcompany.getCompanyname().trim()+"%");
			}
			if ((null != wfwgcompany.getFzr()) && (0 < wfwgcompany.getFzr().trim().length())){
				paraMap.put("fzr", "%"+wfwgcompany.getFzr().trim()+"%");
			}
			if ((null != wfwgcompany.getSzzid()) && (0 < wfwgcompany.getSzzid().trim().length())){
				paraMap.put("szzid", wfwgcompany.getSzzid().trim());
			}
			if ((null != wfwgcompany.getSzc()) && (0 < wfwgcompany.getSzc().trim().length())){
				paraMap.put("szc", wfwgcompany.getSzc().trim());
			}
			if ((null != wfwgcompany.getHyfl()) && (0 < wfwgcompany.getHyfl().trim().length())){
				paraMap.put("hyfl", wfwgcompany.getHyfl().trim());
			}
			if ((null != wfwgcompany.getWgwflb()) && (0 < wfwgcompany.getWgwflb().trim().length())){
				paraMap.put("wgwflb", wfwgcompany.getWgwflb().trim());
			}
			if ((null != wfwgcompany.getIfwhpqylx()) && (0 < wfwgcompany.getIfwhpqylx().trim().length())){
				paraMap.put("ifwhpqylx", wfwgcompany.getIfwhpqylx().trim());
			}
			if ((null != wfwgcompany.getIfwxqy()) && (0 < wfwgcompany.getIfwxqy().trim().length())){
				paraMap.put("ifwxqy", wfwgcompany.getIfwxqy().trim());
			}
			if ((null != wfwgcompany.getIfyyzz()) && (0 < wfwgcompany.getIfyyzz().trim().length())){
				paraMap.put("ifyyzz", wfwgcompany.getIfyyzz().trim());
			}
			if ((null != wfwgcompany.getIfjb()) && (0 < wfwgcompany.getIfjb().trim().length())){
				paraMap.put("ifjb", wfwgcompany.getIfjb().trim());
			}
			if ((null != wfwgcompany.getIfssjjcf()) && (0 < wfwgcompany.getIfssjjcf().trim().length())){
				paraMap.put("ifssjjcf", wfwgcompany.getIfssjjcf().trim());
			}
		}
		
		pagination = wfwgcompanyService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != wfwgcompany)&&(null != wfwgcompany.getId()))
		{
			wfwgcompany = wfwgcompanyService.getById(wfwgcompany.getId());
			deptlist = deptService.findDeptByParentDeptCode(wfwgcompany.getSzzid());
		}
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
			wfwgcompany.setDeptId(this.getLoginUserDepartmentId());
			wfwgcompany.setDelFlag(0);
			wfwgcompanyService.save(wfwgcompany);
		}else{
			wfwgcompanyService.update(wfwgcompany);
		}
		flag ="1";
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			wfwgcompanyService.deleteWithFlag(ids);
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

	public Wfwgcompany getWfwgcompany(){
		return this.wfwgcompany;
	}

	public void setWfwgcompany(Wfwgcompany wfwgcompany){
		this.wfwgcompany = wfwgcompany;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<Department> getDeptlist() {
		return deptlist;
	}

	public void setDeptlist(List<Department> deptlist) {
		this.deptlist = deptlist;
	}
       
    
}
