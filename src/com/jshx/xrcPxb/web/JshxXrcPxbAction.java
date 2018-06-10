/**
 * Class Name: JshxXrcPxbAction
 * Class Description：新入厂员工培训
 */
package com.jshx.xrcPxb.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.jshx.xrcPxb.entity.JshxXrcPxb;
import com.jshx.xrcPxb.service.JshxXrcPxbService;

public class JshxXrcPxbAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxXrcPxb jshxXrcPxb = new JshxXrcPxb();

	/**
	 * 业务类
	 */
	@Autowired
	private JshxXrcPxbService jshxXrcPxbService;
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
	
	//厂级培训开始时间
	private Date queryFacpxsjStart;
	//厂级培训结束时间
	private Date queryFacpxsjEnd;
	//车间培训开始时间
	private Date queryCjpxsjStart;
	//车间培训结束时间
	private Date queryCjpxsjEnd;
	//班组培训开始时间
	private Date queryBzpxsjStart;
	//班组培训结束时间
	private Date queryBzpxsjEnd;
	private String isshow;
	
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	/**
	 * 初始化新入厂员工培训列表
	 * author：陆婷
	 * 2013-08-17
	 */
	public String init()
	{
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			flag = "1";
		}
		else 
		{
			flag = "2";
		}
		return SUCCESS;
	}
	public String inits()
	{
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			flag = "1";
		}
		else
		{
			flag = "2";
		}
		return SUCCESS;
	}
	/**
	 * 查询新入厂员工培训列表
	 * author：陆婷
	 * 2013-08-17
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxXrcPxb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxXrcPxb.getQyid()) && (0 < jshxXrcPxb.getQyid().trim().length())){
				paraMap.put("qyid", jshxXrcPxb.getQyid().trim());
			}
			else
			{
				CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
				paraMap.put("qyid", company.getId());
			}
			if ((null != jshxXrcPxb.getPersonName()) && (0 < jshxXrcPxb.getPersonName().trim().length())){
				paraMap.put("personName", "%" + jshxXrcPxb.getPersonName().trim() + "%");
			}
			if ((null != jshxXrcPxb.getSex()) && (0 < jshxXrcPxb.getSex().trim().length())){
				paraMap.put("sex", jshxXrcPxb.getSex().trim());
			}
			if ((null != jshxXrcPxb.getWhcd()) && (0 < jshxXrcPxb.getWhcd().trim().length())){
				paraMap.put("whcd",  jshxXrcPxb.getWhcd().trim() );
			}
			if ((null != jshxXrcPxb.getCsgw()) && (0 < jshxXrcPxb.getCsgw().trim().length())){
				paraMap.put("csgw", "%" + jshxXrcPxb.getCsgw().trim() + "%");
			}
			if ((null != jshxXrcPxb.getSzc() )&& (0 < jshxXrcPxb.getSzc().trim().length())){
				paraMap.put("szc",jshxXrcPxb.getSzc().trim());
			}
		}
		pagination = jshxXrcPxbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	
	public void lists() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxXrcPxb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxXrcPxb.getQymc()) && (0 < jshxXrcPxb.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxXrcPxb.getQymc().trim() + "%");
			}
			if ((null != jshxXrcPxb.getSzzid()) && (0 < jshxXrcPxb.getSzzid().trim().length())){
				paraMap.put("szzid",  jshxXrcPxb.getSzzid().trim() );
			}
			if ((null != jshxXrcPxb.getSzc() )&& (0 < jshxXrcPxb.getSzc().trim().length())){
				paraMap.put("szc",jshxXrcPxb.getSzc().trim());
			}
		}

		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		List<JshxXrcPxb> jshxXrcPxbList = new ArrayList<JshxXrcPxb>();
		jshxXrcPxbList = jshxXrcPxbService.getJshxXrcPxbListByMap(paraMap,pagination.getPageNumber(),pagination.getPageSize());
		int size = jshxXrcPxbService.getJshxXrcPxbListSizeByMap(paraMap);
		pagination.setTotalCount(size);
		pagination.setList(jshxXrcPxbList);
//		pagination = jshxXrcPxbService.findByPages(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看新入厂员工培训详情
	 * author：陆婷
	 * 2013-08-17
	 */
	public String view() throws Exception{
		if((null != jshxXrcPxb)&&(null != jshxXrcPxb.getId()))
			jshxXrcPxb = jshxXrcPxbService.getById(jshxXrcPxb.getId());
		
		return VIEW;
	}

	/**
	 * 初始化新入厂员工培训修改信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存新入厂员工培训信息
	 * author：陆婷
	 * 2013-08-17
	 * 修改：增加企业类型、行业分类、企业规模、企业注册类型 2013-08-20 by 陆婷
	 */
	public String save() throws Exception{
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			jshxXrcPxb.setSzzid(dept.getDeptCode());
			jshxXrcPxb.setSzzname(dept.getDeptName());
			jshxXrcPxb.setQyid(company.getId());
			jshxXrcPxb.setQymc(company.getCompanyname());
			jshxXrcPxb.setDeptId(this.getLoginUserDepartmentId());
			jshxXrcPxb.setDelFlag(0);
			jshxXrcPxb.setCreateUserID(this.getLoginUserId());
			jshxXrcPxb.setCreateTime(new Date());
			jshxXrcPxb.setQylx(company.getQylx());
			jshxXrcPxb.setHyfl(company.getHyfl());
			jshxXrcPxb.setQygm(company.getQygm());
			jshxXrcPxb.setQyzclx(company.getQyzclx());
			jshxXrcPxb.setIfwhpqylx(company.getIfwhpqylx());
			jshxXrcPxb.setIfyhbzjyqy(company.getIfyhbzjyqy());
			jshxXrcPxb.setIfzywhqylx(company.getIfzywhqylx());
			jshxXrcPxb.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
			jshxXrcPxb.setSzc(company.getSzc());
			jshxXrcPxb.setSzcname(company.getSzcname());
			jshxXrcPxb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxXrcPxb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxXrcPxbService.save(jshxXrcPxb);
		}else{
			jshxXrcPxb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxXrcPxb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxXrcPxbService.update(jshxXrcPxb);
		}
		return RELOAD;
	}

	/**
	 * 删除新入厂员工培训信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public String delete() throws Exception{
	    try{
			jshxXrcPxbService.deleteWithFlag(ids);
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

	public JshxXrcPxb getJshxXrcPxb(){
		return this.jshxXrcPxb;
	}

	public void setJshxXrcPxb(JshxXrcPxb jshxXrcPxb){
		this.jshxXrcPxb = jshxXrcPxb;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryFacpxsjStart(){
		return this.queryFacpxsjStart;
	}

	public void setQueryFacpxsjStart(Date queryFacpxsjStart){
		this.queryFacpxsjStart = queryFacpxsjStart;
	}

	public Date getQueryFacpxsjEnd(){
		return this.queryFacpxsjEnd;
	}

	public void setQueryFacpxsjEnd(Date queryFacpxsjEnd){
		this.queryFacpxsjEnd = queryFacpxsjEnd;
	}

	public Date getQueryCjpxsjStart(){
		return this.queryCjpxsjStart;
	}

	public void setQueryCjpxsjStart(Date queryCjpxsjStart){
		this.queryCjpxsjStart = queryCjpxsjStart;
	}

	public Date getQueryCjpxsjEnd(){
		return this.queryCjpxsjEnd;
	}

	public void setQueryCjpxsjEnd(Date queryCjpxsjEnd){
		this.queryCjpxsjEnd = queryCjpxsjEnd;
	}

	public Date getQueryBzpxsjStart(){
		return this.queryBzpxsjStart;
	}

	public void setQueryBzpxsjStart(Date queryBzpxsjStart){
		this.queryBzpxsjStart = queryBzpxsjStart;
	}

	public Date getQueryBzpxsjEnd(){
		return this.queryBzpxsjEnd;
	}

	public void setQueryBzpxsjEnd(Date queryBzpxsjEnd){
		this.queryBzpxsjEnd = queryBzpxsjEnd;
	}

}
