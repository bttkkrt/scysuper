/**
 * Class Name: DangerstorgeAction
 * Class Description：危险化学品贮存情况列表
 */
package com.jshx.dangerStorge.web;

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
import com.jshx.dangerStorge.entity.Dangerstorge;
import com.jshx.dangerStorge.service.DangerstorgeService;
import com.jshx.module.admin.service.DeptService;
import com.jshx.safetysheet.service.SafetysheetService;

public class DangerstorgeAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Dangerstorge dangerstorge = new Dangerstorge();

	/**
	 * 业务类
	 */
	@Autowired
	private DangerstorgeService dangerstorgeService;
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
    
    private Date createTimeStart;

	private Date createTimeEnd;
	
	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

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
		    
		if(null != dangerstorge){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != dangerstorge.getDangername()) && (0 < dangerstorge.getDangername().trim().length())){
				paraMap.put("dangername", "%" + dangerstorge.getDangername().trim() + "%");
			}
			if ((null != dangerstorge.getDeptId()) && (0 < dangerstorge.getDeptId().trim().length())){
				paraMap.put("deptCodes", dangerstorge.getDeptId().trim());
			}
			if ((null != dangerstorge.getQymc()) && (0 < dangerstorge.getQymc().trim().length())){
				paraMap.put("qymc", "%" + dangerstorge.getQymc().trim() + "%");
			}
			if ((null != dangerstorge.getSzc() )&& (0 < dangerstorge.getSzc().trim().length())){
				paraMap.put("szc",dangerstorge.getSzc().trim());
			}
			if ((null != dangerstorge.getDannum() )&& (0 < dangerstorge.getDannum().trim().length())){
				paraMap.put("dannum", "%" +dangerstorge.getDannum().trim() + "%");
			}
			if ((null != dangerstorge.getUnnum() )&& (0 < dangerstorge.getUnnum().trim().length())){
				paraMap.put("unnum", "%" +dangerstorge.getUnnum().trim() + "%");
			}
		}
		if (null != createTimeStart){
			paraMap.put("startCreateTime", createTimeStart);
		}

		if (null != createTimeEnd){
			paraMap.put("endCreateTime", createTimeEnd);
		}
		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		 
		pagination = dangerstorgeService.findByPage(pagination, paraMap);
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != dangerstorge)&&(null != dangerstorge.getId()))
			dangerstorge = dangerstorgeService.getById(dangerstorge.getId());
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
			dangerstorge.setDelFlag(0);
			//街道名称 街道id
			dangerstorge.setSzz(deptService.findDeptByDeptCode(company.getDwdz1()).getDeptName());
			dangerstorge.setDeptId(company.getDwdz1());
			dangerstorge.setComid(company.getId());
			dangerstorge.setQymc(company.getCompanyname());
			//企业类型 qylx 行业分类 hyfl 企业规模 qygm  企业注册类型  qyzclx
			dangerstorge.setQylxc(company.getQylx());
			dangerstorge.setHyfl(company.getHyfl());
			dangerstorge.setQygm(company.getQygm());
			dangerstorge.setQyzclx(company.getQyzclx());
			
			dangerstorge.setIfwhpqylx(company.getIfwhpqylx());
			dangerstorge.setIfyhbzjyqy(company.getIfyhbzjyqy());
			dangerstorge.setIfzywhqylx(company.getIfzywhqylx());
			dangerstorge.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
			dangerstorge.setDelFlag(0);
			dangerstorge.setCreateTime(new Date());
			dangerstorge.setCreateUserID(this.getLoginUserId());
			dangerstorge.setSzc(company.getSzc());
			dangerstorge.setSzcname(company.getSzcname());
			dangerstorge.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			dangerstorge.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			dangerstorgeService.save(dangerstorge);
		}else{
			dangerstorge.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			dangerstorge.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			dangerstorgeService.update(dangerstorge);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			dangerstorgeService.deleteWithFlag(ids);
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

	public Dangerstorge getDangerstorge(){
		return this.dangerstorge;
	}

	public void setDangerstorge(Dangerstorge dangerstorge){
		this.dangerstorge = dangerstorge;
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
