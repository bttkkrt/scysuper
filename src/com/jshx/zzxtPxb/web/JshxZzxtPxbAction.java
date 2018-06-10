/**
 * Class Name: JshxZzxtPxbAction
 * Class Description：员工再培训及转岗、下岗、脱岗培训
 */
package com.jshx.zzxtPxb.web;

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
import com.jshx.zzxtPxb.entity.JshxZzxtPxb;
import com.jshx.zzxtPxb.service.JshxZzxtPxbService;

public class JshxZzxtPxbAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxZzxtPxb jshxZzxtPxb = new JshxZzxtPxb();

	/**
	 * 业务类
	 */
	@Autowired
	private JshxZzxtPxbService jshxZzxtPxbService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private JshxXrcPxbService jshxXrcPxbService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	//培训开始时间
	private Date queryPxsjStart;
	//培训结束时间
	private Date queryPxsjEnd;
private String shortname;
	private List<JshxXrcPxb> userList = new ArrayList<JshxXrcPxb>();
	private String isshow;
	
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	/**
	 * 初始化员工再培训及转岗、下岗、脱岗培训列表
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
	 * 查询员工再培训及转岗、下岗、脱岗培训列表
	 * author：陆婷
	 * 2013-08-19
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxZzxtPxb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxZzxtPxb.getQyid()) && (0 < jshxZzxtPxb.getQyid().trim().length())){
				paraMap.put("qyid", jshxZzxtPxb.getQyid().trim());
			}
			else
			{
				CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
				paraMap.put("qyid", company.getId());
			}
			if ((null != jshxZzxtPxb.getPersonName()) && (0 < jshxZzxtPxb.getPersonName().trim().length())){
				paraMap.put("personName", "%" + jshxZzxtPxb.getPersonName().trim() + "%");
			}

			if (null != queryPxsjStart){
				paraMap.put("startPxsj", queryPxsjStart);
			}

			if (null != queryPxsjEnd){
				paraMap.put("endPxsj", queryPxsjEnd);
			}
			if ((null != jshxZzxtPxb.getSzc() )&& (0 < jshxZzxtPxb.getSzc().trim().length())){
				paraMap.put("szc",jshxZzxtPxb.getSzc().trim());
			}
		}
		pagination = jshxZzxtPxbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	public void lists() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxZzxtPxb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxZzxtPxb.getQymc()) && (0 < jshxZzxtPxb.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxZzxtPxb.getQymc().trim() + "%");
			}
			if ((null != jshxZzxtPxb.getSzzid()) && (0 < jshxZzxtPxb.getSzzid().trim().length())){
				paraMap.put("szzid",  jshxZzxtPxb.getSzzid().trim() );
			}
			if ((null != jshxZzxtPxb.getSzc() )&& (0 < jshxZzxtPxb.getSzc().trim().length())){
				paraMap.put("szc",jshxZzxtPxb.getSzc().trim());
			}
		}

		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		List<JshxZzxtPxb> jshxZzxtPxbList = new ArrayList<JshxZzxtPxb>();
		jshxZzxtPxbList = jshxZzxtPxbService.getJshxZzxtPxbListByMap(paraMap,pagination.getPageNumber(),pagination.getPageSize());
		int size = jshxZzxtPxbService.getJshxZzxtPxbListSizeByMap(paraMap);
		pagination.setTotalCount(size);
		pagination.setList(jshxZzxtPxbList);
//		pagination = jshxZzxtPxbService.findByPages(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看员工再培训及转岗、下岗、脱岗培训详情
	 * author：陆婷
	 * 2013-08-19
	 */
	public String view() throws Exception{
		if((null != jshxZzxtPxb)&&(null != jshxZzxtPxb.getId()))
			jshxZzxtPxb = jshxZzxtPxbService.getById(jshxZzxtPxb.getId());
//		Map map = new HashMap();
//		map.put("createUserID",this.getLoginUserId());
//		userList = jshxXrcPxbService.findJshxXrcPxb(map);
		return VIEW;
	}
	
	public String queryPerson() throws Exception{
		return SUCCESS;
	}
	/**
	 * 初始化员工再培训及转岗、下岗、脱岗培训修改信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存员工再培训及转岗、下岗、脱岗培训信息
	 * author：陆婷
	 * 2013-08-19
	 * 修改：增加企业类型、行业分类、企业规模、企业注册类型 2013-08-20 by 陆婷
	 */
	public String save() throws Exception{
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		JshxXrcPxb jshxXrcPxb = jshxXrcPxbService.getById(jshxZzxtPxb.getPersonId());
		jshxZzxtPxb.setPersonName(jshxXrcPxb.getPersonName());
		jshxZzxtPxb.setSex(jshxXrcPxb.getSex());
		jshxZzxtPxb.setWhcd(jshxXrcPxb.getWhcd());
		jshxZzxtPxb.setCsgw(jshxXrcPxb.getCsgw());
		jshxZzxtPxb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
		jshxZzxtPxb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
		if ("add".equalsIgnoreCase(this.flag)){
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			jshxZzxtPxb.setSzzid(dept.getDeptCode());
			jshxZzxtPxb.setSzzname(dept.getDeptName());
			jshxZzxtPxb.setQyid(company.getId());
			jshxZzxtPxb.setQymc(company.getCompanyname());
			jshxZzxtPxb.setDeptId(this.getLoginUserDepartmentId());
			jshxZzxtPxb.setDelFlag(0);
			jshxZzxtPxb.setCreateUserID(this.getLoginUserId());
			jshxZzxtPxb.setCreateTime(new Date());
			jshxZzxtPxb.setQylx(company.getQylx());
			jshxZzxtPxb.setHyfl(company.getHyfl());
			jshxZzxtPxb.setQygm(company.getQygm());
			jshxZzxtPxb.setQyzclx(company.getQyzclx());
			jshxZzxtPxb.setIfwhpqylx(company.getIfwhpqylx());
			jshxZzxtPxb.setIfyhbzjyqy(company.getIfyhbzjyqy());
			jshxZzxtPxb.setIfzywhqylx(company.getIfzywhqylx());
			jshxZzxtPxb.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山
			jshxZzxtPxb.setSzc(company.getSzc());
			jshxZzxtPxb.setSzcname(company.getSzcname());
			jshxZzxtPxbService.save(jshxZzxtPxb);
		}else{
			jshxZzxtPxbService.update(jshxZzxtPxb);
		}
		return RELOAD;
	}

	/**
	 * 删除员工再培训及转岗、下岗、脱岗培训信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public String delete() throws Exception{
	    try{
			jshxZzxtPxbService.deleteWithFlag(ids);
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

	public JshxZzxtPxb getJshxZzxtPxb(){
		return this.jshxZzxtPxb;
	}

	public void setJshxZzxtPxb(JshxZzxtPxb jshxZzxtPxb){
		this.jshxZzxtPxb = jshxZzxtPxb;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryPxsjStart(){
		return this.queryPxsjStart;
	}

	public void setQueryPxsjStart(Date queryPxsjStart){
		this.queryPxsjStart = queryPxsjStart;
	}

	public Date getQueryPxsjEnd(){
		return this.queryPxsjEnd;
	}

	public void setQueryPxsjEnd(Date queryPxsjEnd){
		this.queryPxsjEnd = queryPxsjEnd;
	}
	public List<JshxXrcPxb> getUserList() {
		return userList;
	}
	public void setUserList(List<JshxXrcPxb> userList) {
		this.userList = userList;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

}
