/**
 * Class Name: JshxAqfjAction
 * Class Description：安全附件
 */
package com.jshx.aqfj.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.aqfj.entity.JshxAqfj;
import com.jshx.aqfj.service.JshxAqfjService;
import com.jshx.common.util.Condition;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;

public class JshxAqfjAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxAqfj jshxAqfj = new JshxAqfj();

	/**
	 * 业务类
	 */
	@Autowired
	private JshxAqfjService jshxAqfjService;
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
	
	//投用日期开始
	private Date queryTyrqStart;
	//投用日期结束
	private Date queryTyrqEnd;
	//上次检测日期开始
	private Date queryScjcsjStart;
	//上次检测日期结束
	private Date queryScjcsjEnd;
	//下次检测日期开始
	private Date queryXcjcsjStart;
	//下次检测日期结束
	private Date queryXcjcsjEnd;

	/**
	 * 初始化安全附件情况列表
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
	 * 查询安全附件情况列表
	 * author：陆婷
	 * 2013-08-19
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxAqfj){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxAqfj.getQymc()) && (0 < jshxAqfj.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxAqfj.getQymc().trim() + "%");
			}
			if ((null != jshxAqfj.getAqfjmc()) && (0 < jshxAqfj.getAqfjmc().trim().length())){
				paraMap.put("aqfjmc", "%" + jshxAqfj.getAqfjmc().trim() + "%");
			}

			if ((null != jshxAqfj.getSyzt()) && (0 < jshxAqfj.getSyzt().trim().length())){
				paraMap.put("syzt", "%" + jshxAqfj.getSyzt().trim() + "%");
			}
			
			if ((null != jshxAqfj.getAqzrr()) && (0 < jshxAqfj.getAqzrr().trim().length())){
				paraMap.put("aqzrr", "%" + jshxAqfj.getAqzrr().trim() + "%");
			}
			
			if ((null != jshxAqfj.getSzzid()) && (0 < jshxAqfj.getSzzid().trim().length())){
				paraMap.put("szzid",  jshxAqfj.getSzzid().trim() );
			}

			if (null != queryScjcsjStart){
				paraMap.put("startScjcsj", queryScjcsjStart);
			}

			if (null != queryScjcsjEnd){
				paraMap.put("endScjcsj", queryScjcsjEnd);
			}
			if (null != queryXcjcsjStart){
				paraMap.put("startXcjcsj", queryXcjcsjStart);
			}

			if (null != queryXcjcsjEnd){
				paraMap.put("endXcjcsj", queryXcjcsjEnd);
			}
			if ((null != jshxAqfj.getSzc() )&& (0 < jshxAqfj.getSzc().trim().length())){
				paraMap.put("szc",jshxAqfj.getSzc().trim());
			}
		}

		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		 
		pagination = jshxAqfjService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看安全附件情况详情
	 * author：陆婷
	 * 2013-08-19
	 */
	public String view() throws Exception{
		if((null != jshxAqfj)&&(null != jshxAqfj.getId()))
			jshxAqfj = jshxAqfjService.getById(jshxAqfj.getId());
		
		return VIEW;
	}

	/**
	 * 初始化安全附件情况修改信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存安全附件情况信息
	 * author：陆婷
	 * 2013-08-19
	 * 修改：增加企业类型、行业分类、企业规模、企业注册类型 2013-08-20 by 陆婷
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			jshxAqfj.setSzzid(dept.getDeptCode());
			jshxAqfj.setSzzname(dept.getDeptName());
			jshxAqfj.setQyid(company.getId());
			jshxAqfj.setQymc(company.getCompanyname());
			jshxAqfj.setDeptId(this.getLoginUserDepartmentId());
			jshxAqfj.setDelFlag(0);
			jshxAqfj.setCreateUserID(this.getLoginUserId());
			jshxAqfj.setCreateTime(new Date());
			jshxAqfj.setQylx(company.getQylx());
			jshxAqfj.setHyfl(company.getHyfl());
			jshxAqfj.setQygm(company.getQygm());
			jshxAqfj.setQyzclx(company.getQyzclx());
			jshxAqfj.setIfwhpqylx(company.getIfwhpqylx());
			jshxAqfj.setIfyhbzjyqy(company.getIfyhbzjyqy());
			jshxAqfj.setIfzywhqylx(company.getIfzywhqylx());
			jshxAqfj.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
			jshxAqfj.setSzc(company.getSzc());
			jshxAqfj.setSzcname(company.getSzcname());
			jshxAqfj.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxAqfj.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxAqfjService.save(jshxAqfj);
		}else{
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			jshxAqfj.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxAqfj.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			
			jshxAqfjService.update(jshxAqfj);
		}
		return RELOAD;
	}

	/**
	 * 删除安全附件情况信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public String delete() throws Exception{
	    try{
			jshxAqfjService.deleteWithFlag(ids);
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

	public JshxAqfj getJshxAqfj(){
		return this.jshxAqfj;
	}

	public void setJshxAqfj(JshxAqfj jshxAqfj){
		this.jshxAqfj = jshxAqfj;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryTyrqStart(){
		return this.queryTyrqStart;
	}

	public void setQueryTyrqStart(Date queryTyrqStart){
		this.queryTyrqStart = queryTyrqStart;
	}

	public Date getQueryTyrqEnd(){
		return this.queryTyrqEnd;
	}

	public void setQueryTyrqEnd(Date queryTyrqEnd){
		this.queryTyrqEnd = queryTyrqEnd;
	}

	public Date getQueryScjcsjStart(){
		return this.queryScjcsjStart;
	}

	public void setQueryScjcsjStart(Date queryScjcsjStart){
		this.queryScjcsjStart = queryScjcsjStart;
	}

	public Date getQueryScjcsjEnd(){
		return this.queryScjcsjEnd;
	}

	public void setQueryScjcsjEnd(Date queryScjcsjEnd){
		this.queryScjcsjEnd = queryScjcsjEnd;
	}

	public Date getQueryXcjcsjStart(){
		return this.queryXcjcsjStart;
	}

	public void setQueryXcjcsjStart(Date queryXcjcsjStart){
		this.queryXcjcsjStart = queryXcjcsjStart;
	}

	public Date getQueryXcjcsjEnd(){
		return this.queryXcjcsjEnd;
	}

	public void setQueryXcjcsjEnd(Date queryXcjcsjEnd){
		this.queryXcjcsjEnd = queryXcjcsjEnd;
	}

}
