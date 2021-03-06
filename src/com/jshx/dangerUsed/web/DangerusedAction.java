/**
 * Class Name: DangerstorgeAction
 * Class Description：危险化学品使用情况列表
 */
package com.jshx.dangerUsed.web;

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
import com.jshx.dangerUsed.entity.DangerUsed;
import com.jshx.dangerUsed.service.DangerusedService;
import com.jshx.module.admin.service.DeptService;
import com.jshx.safetysheet.service.SafetysheetService;

@SuppressWarnings("serial")
public class DangerusedAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private DangerUsed dangerused = new DangerUsed();

	/**
	 * 业务类
	 */
	@Autowired
	private DangerusedService dangerusedService;
	/**
	 * 业务类
	 */
	@Autowired
	private SafetysheetService safetysheetService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	 /**
	 * 部门业务类
	 */
 @Autowired
	private DeptService deptService;
 /**
	 * 企业业务类
	 */
	@Autowired
	private CompanyService companyService;
 private CompanyBackUp company=null;
	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
    /** @author gq
	 * @date 8yue 23
     *用于区分监管部门0和企业1
     */
    private String role;
    /** @author gq
	 * @date 8yue 23
     *用于区分监管部门0和更高级别1
     */
    private String visable;
	/**
	 * @author gq
	 * @date 8yue 23
	 * @function 初始化安全生产页面
	 * @return 上传附件页面
	 */
	public String initlist() throws Exception{
	
		if(this.getLoginUser().getDept().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))//监管部门以下的角色
		{
		    this.visable="0";
		}
		else 
			 this.visable="1";
		
		String[] userroles=this.getLoginUser().getRoleIds();
		List<String> roles=new ArrayList<String>();
		for(String role:userroles)
		{
			roles.add(role);
		}
		if(roles.contains(SysPropertiesUtil.getProperty("qiyeUserRoleId"))||roles.contains(SysPropertiesUtil.getProperty("tempqiyeUserRoleId")))
			return "qiye";
			else
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != dangerused){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != dangerused.getDangername()) && (0 < dangerused.getDangername().trim().length())){
				paraMap.put("dangername", "%" + dangerused.getDangername().trim() + "%");
			}
			if ((null != dangerused.getDeptId()) && (0 < dangerused.getDeptId().trim().length())){
				paraMap.put("deptCodes", dangerused.getDeptId().trim());
			}
			if ((null != dangerused.getQymc()) && (0 < dangerused.getQymc().trim().length())){
				paraMap.put("qymc", "%" + dangerused.getQymc().trim() + "%");
			}
			if ((null != dangerused.getSzc() )&& (0 < dangerused.getSzc().trim().length())){
				paraMap.put("szc",dangerused.getSzc().trim());
			}
		}

		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		 
		pagination = dangerusedService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != dangerused)&&(null != dangerused.getId()))
			dangerused = dangerusedService.getById(dangerused.getId());
		String[] userroles=this.getLoginUser().getRoleIds();
		List<String> roles=new ArrayList<String>();
		for(String role:userroles)
		{
			roles.add(role);
		}
	    
		if(roles.contains(SysPropertiesUtil.getProperty("qiyeUserRoleId"))||roles.contains(SysPropertiesUtil.getProperty("tempqiyeUserRoleId")))
			this.role="1";
		else
			this.role="0";
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
		company=companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			dangerused.setDelFlag(0);
			//街道名称 街道id
			dangerused.setSzz(deptService.findDeptByDeptCode(company.getDwdz1()).getDeptName());
			dangerused.setDeptId(company.getDwdz1());
			dangerused.setComid(company.getId());
			dangerused.setQymc(company.getCompanyname());
			//企业类型 qylx 行业分类 hyfl 企业规模 qygm  企业注册类型  qyzclx
			dangerused.setQylxc(company.getQylx());
			dangerused.setHyfl(company.getHyfl());
			dangerused.setQygm(company.getQygm());
			dangerused.setQyzclx(company.getQyzclx());
			dangerused.setIfwhpqylx(company.getIfwhpqylx());
			dangerused.setIfyhbzjyqy(company.getIfyhbzjyqy());
			dangerused.setIfzywhqylx(company.getIfzywhqylx());
			dangerused.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
			dangerused.setDelFlag(0);
			dangerused.setCreateTime(new Date());
			dangerused.setCreateUserID(this.getLoginUserId());
			dangerused.setSzc(company.getSzc());
			dangerused.setSzcname(company.getSzcname());
			dangerused.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			dangerused.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			dangerusedService.save(dangerused);
		}else{
			dangerused.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			dangerused.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			dangerusedService.update(dangerused);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			dangerusedService.deleteWithFlag(ids);
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

	public DangerUsed getDangerused(){
		return this.dangerused;
	}

	public void setDangerused(DangerUsed dangerused){
		this.dangerused = dangerused;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public String getVisable() {
		return visable;
	}

	public void setVisable(String visable) {
		this.visable = visable;
	}
       
    
}
