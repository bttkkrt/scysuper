/**
 * Class Name: MaintenpAction
 * Class Description：检修作业规程管理
 */
package com.jshx.maintenp.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.common.util.Condition;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.maintenp.entity.Maintenp;
import com.jshx.maintenp.service.MaintenpService;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.safetysheet.service.SafetysheetService;

public class MaintenpAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Maintenp maintenp = new Maintenp();

	/**
	 * 业务类
	 */
	@Autowired
	private MaintenpService maintenpService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	/**
	 * 业务类
	 */
	@Autowired
	private SafetysheetService safetysheetService;
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
	/**
	 * 分页信息
	 */
	private Pagination pagination;
	/**
	 * @author gq
	 * @date 8yue 15
	 * @function 存放附件列表
	 */
	private List<PhotoPic> list = new ArrayList<PhotoPic>();
	  
    /** @author gq
	 * @date 8yue 15 
     *用于区分监管部门0和企业1
     */
    private String role;
    /** @author gq
	 * @date 8yue 16 
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
	 * @date 8yue 15
	 * @function 初始化安全生产页面
	 * @return 上传附件页面
	 */
	public String initlist() throws Exception{
		if(this.getLoginUser().getDept().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))//监管部门以下的角色
		    this.visable="0";
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
	 * @author gq 
	 * @date 8yue 15
	 * 执行查询的方法，返回maintenp对象数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != maintenp){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != maintenp.getProducename()) && (0 < maintenp.getProducename().trim().length())){
				paraMap.put("producename", maintenp.getProducename().trim());
			}
			if(maintenp.getComName()!=null&&maintenp.getComName().length()>0)
				paraMap.put("comName", "%"+maintenp.getComName()+"%");
			
			if(maintenp.getDeptId()!=null&&maintenp.getDeptId().length()>0)
				paraMap.put("deptCode", maintenp.getDeptId());
			if ((null != maintenp.getSzc() )&& (0 < maintenp.getSzc().trim().length())){
				paraMap.put("szc",maintenp.getSzc().trim());
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
		 
		pagination = maintenpService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != maintenp)&&(null != maintenp.getId()))
			maintenp = maintenpService.getById(maintenp.getId());
		if(maintenp.getLinkid()==null)
		{
			maintenp.setLinkid(UUID.randomUUID().toString());
		}
		Map map = new HashMap();
		map.put("taskProId",maintenp.getLinkid());
		map.put("picType","jxzy");
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
		company=companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			maintenp.setDelFlag(0);
			maintenp.setDeptName(deptService.findDeptByDeptCode(company.getDwdz1()).getDeptName());
			maintenp.setDeptId(company.getDwdz1());
			maintenp.setComId(company.getId());
			maintenp.setComName(company.getCompanyname());
			

			//企业类型 qylx 行业分类 hyfl 企业规模 qygm  企业注册类型  qyzclx
			maintenp.setQylx(company.getQylx());
			maintenp.setHyfl(company.getHyfl());
			maintenp.setQygm(company.getQygm());
			maintenp.setQyzclx(company.getQyzclx());
			
			maintenp.setIfwhpqylx(company.getIfwhpqylx());
			maintenp.setIfyhbzjyqy(company.getIfyhbzjyqy());
			maintenp.setIfzywhqylx(company.getIfzywhqylx());
			maintenp.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
			maintenp.setDelFlag(0);
			maintenp.setCreateTime(new Date());
			maintenp.setCreateUserID(this.getLoginUserId());
			maintenp.setSzc(company.getSzc());
			maintenp.setSzcname(company.getSzcname());
			maintenp.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			maintenp.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			maintenpService.save(maintenp);
		}else{
			maintenp.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			maintenp.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			maintenpService.update(maintenp);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			maintenpService.deleteWithFlag(ids);
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

	public Maintenp getMaintenp(){
		return this.maintenp;
	}

	public void setMaintenp(Maintenp maintenp){
		this.maintenp = maintenp;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
	public List<PhotoPic> getList() {
		return list;
	}
	public void setList(List<PhotoPic> list) {
		this.list = list;
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
       
    
}
