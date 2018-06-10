/**
 * Class Name: AnpjbgAction
 * Class Description：安全评价报告管理
 */
package com.jshx.anpjbg.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.anpjbg.entity.Anpjbg;
import com.jshx.anpjbg.service.AnpjbgService;
import com.jshx.common.util.Condition;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.safetysheet.service.SafetysheetService;

public class AnpjbgAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Anpjbg anpjbg = new Anpjbg();

	/**
	 * 业务类
	 */
	@Autowired
	private AnpjbgService anpjbgService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	/**
	 * 业务类
	 */
	@Autowired
	private SafetysheetService safetysheetService;
	 /**
	 * 图片业务类
	 */
	 @Autowired
	private SzwxPhotoService szwxPhotoService;
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
 /** @author gq
	 * @date 8yue 24 
  *用于区分监管部门0和企业1
  */
 private String role;
 /** @author gq
	 * @date 8yue 24
  *用于区分监管部门0和更高级别1
  */
 private String visable;
	/**
	 * @author gq
	 * @date 8yue 14
	 * @function 存放附件列表
	 */
	private List<PhotoPic> list = new ArrayList<PhotoPic>();
	
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
	 * @date 8yue 24
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
		      
		if(null != anpjbg){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != anpjbg.getDeptId()) && (0 < anpjbg.getDeptId().trim().length())){
				paraMap.put("deptCodes", anpjbg.getDeptId().trim());
			}
			if ((null != anpjbg.getQymc()) && (0 < anpjbg.getQymc().trim().length())){
				paraMap.put("qymc", "%" + anpjbg.getQymc().trim() + "%");
			}
			if ((null != anpjbg.getPjbgmc() )&& (0 < anpjbg.getPjbgmc().trim().length())){
				paraMap.put("pjbgmc", "%" + anpjbg.getPjbgmc().trim() + "%");
			}
			if ((null != anpjbg.getSzc() )&& (0 < anpjbg.getSzc().trim().length())){
				paraMap.put("szc",anpjbg.getSzc().trim());
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
		
		pagination = anpjbgService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != anpjbg)&&(null != anpjbg.getId()))
			anpjbg = anpjbgService.getById(anpjbg.getId());
		if(anpjbg.getLinkid()==null)
		{
			anpjbg.setLinkid(UUID.randomUUID().toString());
		}
		Map map = new HashMap();
		map.put("taskProId",anpjbg.getLinkid());
		map.put("picType","aqpjbg");
	    list = szwxPhotoService.findPicPath(map);//获取执法文书材料
	    
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
		if ("add".equalsIgnoreCase(this.flag)){
			anpjbg.setDelFlag(0);
			company=companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			anpjbg.setSzz(deptService.findDeptByDeptCode(company.getDwdz1()).getDeptName());
			anpjbg.setDeptId(company.getDwdz1());
			anpjbg.setComid(company.getId());
			anpjbg.setQymc(company.getCompanyname());
				//企业类型 qylx 行业分类 hyfl 企业规模 qygm  企业注册类型  qyzclx
			anpjbg.setQylx(company.getQylx());
			anpjbg.setHyfl(company.getHyfl());
				anpjbg.setQygm(company.getQygm());
				anpjbg.setQyzclx(company.getQyzclx());
				anpjbg.setCreateTime(new Date());
				anpjbg.setDelFlag(0);
				anpjbg.setCreateUserID(this.getLoginUserId());
				anpjbg.setIfwhpqylx(company.getIfwhpqylx());
				anpjbg.setIfyhbzjyqy(company.getIfyhbzjyqy());
				anpjbg.setIfzywhqylx(company.getIfzywhqylx());
				anpjbg.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
				anpjbg.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
				anpjbg.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
				anpjbg.setSzc(company.getSzc());
				anpjbg.setSzcname(company.getSzcname());
				
			anpjbgService.save(anpjbg);
		}else{
			company=companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			anpjbg.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			anpjbg.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			
			anpjbgService.update(anpjbg);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			anpjbgService.deleteWithFlag(ids);
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

	public Anpjbg getAnpjbg(){
		return this.anpjbg;
	}

	public void setAnpjbg(Anpjbg anpjbg){
		this.anpjbg = anpjbg;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public String getVisable() {
		return visable;
	}



	public void setVisable(String visable) {
		this.visable = visable;
	}



	public List<PhotoPic> getList() {
		return list;
	}



	public void setList(List<PhotoPic> list) {
		this.list = list;
	}
       
    
}
