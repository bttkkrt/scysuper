/**
 * Class Name: JshxTzsbAction
 * Class Description：特种设备
 */
package com.jshx.tzsb.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.common.util.Condition;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.tzsb.entity.JshxTzsb;
import com.jshx.tzsb.service.JshxTzsbService;

public class JshxTzsbAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxTzsb jshxTzsb = new JshxTzsb();

	/**
	 * 业务类
	 */
	@Autowired
	private JshxTzsbService jshxTzsbService;
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
	
	//上次检测日期开始
	private Date queryScjcrqStart;
	//上次检测日期结束
	private Date queryScjcrqEnd;
	//下次检测日期开始
	private Date queryXcjcrqStart;
	//下次检测日期结束
	private Date queryXcjcrqEnd;

	/**
	 * 初始化特种设备情况列表
	 * author：陆婷
	 * 2013-08-19
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
	 * 查询特种设备情况列表
	 * author：陆婷
	 * 2013-08-19
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxTzsb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxTzsb.getQymc()) && (0 < jshxTzsb.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxTzsb.getQymc().trim() + "%");
			}
			
			if ((null != jshxTzsb.getSbmc()) && (0 < jshxTzsb.getSbmc().trim().length())){
				paraMap.put("sbmc", "%" + jshxTzsb.getSbmc().trim() + "%");
			}

			if ((null != jshxTzsb.getSyzt()) && (0 < jshxTzsb.getSyzt().trim().length())){
				paraMap.put("syzt", "%" + jshxTzsb.getSyzt().trim() + "%");
			}
			
			if ((null != jshxTzsb.getAqzrr()) && (0 < jshxTzsb.getAqzrr().trim().length())){
				paraMap.put("aqzrr", "%" + jshxTzsb.getAqzrr().trim() + "%");
			}

			if (null != queryScjcrqStart){
				paraMap.put("startScjcrq", queryScjcrqStart);
			}

			if (null != queryScjcrqEnd){
				paraMap.put("endScjcrq", queryScjcrqEnd);
			}
			if (null != queryXcjcrqStart){
				paraMap.put("startXcjcrq", queryXcjcrqStart);
			}

			if (null != queryXcjcrqEnd){
				paraMap.put("endXcjcrq", queryXcjcrqEnd);
			}
			if ((null != jshxTzsb.getSzzid()) && (0 < jshxTzsb.getSzzid().trim().length())){
				paraMap.put("szzid",  jshxTzsb.getSzzid().trim() );
			}
			if ((null != jshxTzsb.getSzc() )&& (0 < jshxTzsb.getSzc().trim().length())){
				paraMap.put("szc",jshxTzsb.getSzc().trim());
			}
		}

		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		 
		pagination = jshxTzsbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看特种设备情况详情
	 * author：陆婷
	 * 2013-08-19
	 */
	public String view() throws Exception{
		if((null != jshxTzsb)&&(null != jshxTzsb.getId()))
			jshxTzsb = jshxTzsbService.getById(jshxTzsb.getId());
		
		return VIEW;
	}

	/**
	 * 初始化特种设备情况修改信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存特种设备情况信息
	 * author：陆婷
	 * 2013-08-19
	 * 修改：增加企业类型、行业分类、企业规模、企业注册类型 2013-08-20 by 陆婷
	 */
	public String save() throws Exception{
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			jshxTzsb.setSzzid(dept.getDeptCode());
			jshxTzsb.setSzzname(dept.getDeptName());
			jshxTzsb.setQyid(company.getId());
			jshxTzsb.setQymc(company.getCompanyname());
			jshxTzsb.setDeptId(this.getLoginUserDepartmentId());
			jshxTzsb.setDelFlag(0);
			jshxTzsb.setCreateUserID(this.getLoginUserId());
			jshxTzsb.setCreateTime(new Date());
			jshxTzsb.setQylx(company.getQylx());
			jshxTzsb.setHyfl(company.getHyfl());
			jshxTzsb.setQygm(company.getQygm());
			jshxTzsb.setQyzclx(company.getQyzclx());
			jshxTzsb.setIfwhpqylx(company.getIfwhpqylx());
			jshxTzsb.setIfyhbzjyqy(company.getIfyhbzjyqy());
			jshxTzsb.setIfzywhqylx(company.getIfzywhqylx());
			jshxTzsb.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
			jshxTzsb.setSzc(company.getSzc());
			jshxTzsb.setSzcname(company.getSzcname());
			jshxTzsb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxTzsb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxTzsbService.save(jshxTzsb);
		}else{
			jshxTzsb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxTzsb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxTzsbService.update(jshxTzsb);
		}
		return RELOAD;
	}

	/**
	 * 删除特种设备情况信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public String delete() throws Exception{
	    try{
			jshxTzsbService.deleteWithFlag(ids);
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

	public JshxTzsb getJshxTzsb(){
		return this.jshxTzsb;
	}

	public void setJshxTzsb(JshxTzsb jshxTzsb){
		this.jshxTzsb = jshxTzsb;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryScjcrqStart(){
		return this.queryScjcrqStart;
	}

	public void setQueryScjcrqStart(Date queryScjcrqStart){
		this.queryScjcrqStart = queryScjcrqStart;
	}

	public Date getQueryScjcrqEnd(){
		return this.queryScjcrqEnd;
	}

	public void setQueryScjcrqEnd(Date queryScjcrqEnd){
		this.queryScjcrqEnd = queryScjcrqEnd;
	}

	public Date getQueryXcjcrqStart(){
		return this.queryXcjcrqStart;
	}

	public void setQueryXcjcrqStart(Date queryXcjcrqStart){
		this.queryXcjcrqStart = queryXcjcrqStart;
	}

	public Date getQueryXcjcrqEnd(){
		return this.queryXcjcrqEnd;
	}

	public void setQueryXcjcrqEnd(Date queryXcjcrqEnd){
		this.queryXcjcrqEnd = queryXcjcrqEnd;
	}

}
