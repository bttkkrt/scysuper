/**
 * Class Name: GzzdAction
 * Class Description：规章制度
 */
package com.jshx.gzzd.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.gzzd.entity.Gzzd;
import com.jshx.gzzd.service.GzzdService;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;

public class GzzdAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Gzzd gzzd = new Gzzd();

	/**
	 * 业务类
	 */
	@Autowired
	private GzzdService gzzdService;
	@Autowired
	private CompanyService companyService;
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
	
	/**
	 * 初始化列表
	 * @return
	 */
	public String init()
	{
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			flag = "1";
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			flag = "2";
		}
		else
		{
			flag = "3";
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
		    
		if(null != gzzd){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != gzzd.getQymc()) && (0 < gzzd.getQymc().trim().length())){
				paraMap.put("qymc", "%" + gzzd.getQymc().trim() + "%");
			}
			if ((null != gzzd.getSzzid()) && (0 < gzzd.getSzzid().trim().length())){
				paraMap.put("szzid",  gzzd.getSzzid().trim() );
			}
			if ((null != gzzd.getSzc() )&& (0 < gzzd.getSzc().trim().length())){
				paraMap.put("szc",gzzd.getSzc().trim());
			}
		}
		
		pagination = gzzdService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != gzzd)&&(null != gzzd.getId()))
			gzzd = gzzdService.getById(gzzd.getId());
		
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
			gzzd.setDeptId(this.getLoginUserDepartmentId());
			gzzd.setDelFlag(0);
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			gzzd.setSzzid(dept.getDeptCode());
			gzzd.setSzzname(dept.getDeptName());
			gzzd.setQyid(company.getId());
			gzzd.setQymc(company.getCompanyname());
			gzzd.setDeptId(this.getLoginUserDepartmentId());
			gzzd.setDelFlag(0);
			gzzd.setCreateUserID(this.getLoginUserId());
			gzzd.setCreateTime(new Date());
			gzzd.setQylx(company.getQylx());
			gzzd.setHyfl(company.getHyfl());
			gzzd.setQygm(company.getQygm());
			gzzd.setQyzclx(company.getQyzclx());
			gzzd.setSzc(company.getSzc());
			gzzd.setSzcname(company.getSzcname());
			gzzdService.save(gzzd);
		}else{
			gzzdService.update(gzzd);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			gzzdService.deleteWithFlag(ids);
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

	public Gzzd getGzzd(){
		return this.gzzd;
	}

	public void setGzzd(Gzzd gzzd){
		this.gzzd = gzzd;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
