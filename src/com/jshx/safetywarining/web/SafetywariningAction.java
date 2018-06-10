/**
 * Class Name: SafetywariningAction
 * Class Description：安全警示标示设置列表
 */
package com.jshx.safetywarining.web;

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
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.safetysheet.service.SafetysheetService;
import com.jshx.safetywarining.entity.Safetywarining;
import com.jshx.safetywarining.service.SafetywariningService;

public class SafetywariningAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Safetywarining safetywarining = new Safetywarining();

	/**
	 * 业务类
	 */
	@Autowired
	private SafetywariningService safetywariningService;

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
		 * @author gq
		 * @date 8yue 20
		 * @function 存放附件列表
		 */
		private List<PhotoPic> list = new ArrayList<PhotoPic>();
		  
	    /** @author gq
		 * @date 8yue 20 
	     *用于区分监管部门0和企业1
	     */
	    private String role;
	    /** @author gq
		 * @date 8yue 20 
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
		 * @date 8yue 20
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
	 * @author gq
	 * @date 8yue 20
	 * @function 查询企业安全警示标示情况
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != safetywarining){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != safetywarining.getQymc()) && (0 < safetywarining.getQymc().trim().length())){
				paraMap.put("qymc", "%" + safetywarining.getQymc().trim() + "%");
			}
			if ((null != safetywarining.getWxmc()) && (0 < safetywarining.getWxmc().trim().length())){
				paraMap.put("wxmc", "%" + safetywarining.getWxmc().trim() + "%");
			}
			if ((null != safetywarining.getDeptId()) && (0 < safetywarining.getDeptId().trim().length())){
				paraMap.put("deptCode", safetywarining.getDeptId().trim());
			}
			if ((null != safetywarining.getSzc() )&& (0 < safetywarining.getSzc().trim().length())){
				paraMap.put("szc",safetywarining.getSzc().trim());
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
		
		pagination = safetywariningService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * @author gq
	 * @date 8yue20
	 * @frunctioin 查看记录详细信息，没有附件关联的linkid，则生成
	 * 查看详细信息
	 */
	@SuppressWarnings("unchecked")
	public String view() throws Exception{
		if((null != safetywarining)&&(null != safetywarining.getId()))
			safetywarining = safetywariningService.getById(safetywarining.getId());
		if(safetywarining.getLinkid()==null)
		{
			safetywarining.setLinkid(UUID.randomUUID().toString());
		}
		Map map = new HashMap();
		map.put("taskProId",safetywarining.getLinkid());
		map.put("picType","aqjsbz");
	    list = szwxPhotoService.findPicPath(map);//获取图片或附件列表
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
		company=companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		safetywarining.setQymc(company.getCompanyname());
	    return EDIT;
	}

	/**
	 * @author gq
	 * @data 8yue 20
	 * @function 保存企业填报的安全警示信息
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			safetywarining.setDelFlag(0);
			company=companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			//街道名称 街道id
			safetywarining.setSzz(deptService.findDeptByDeptCode(company.getDwdz1()).getDeptName());
			safetywarining.setDeptId(company.getDwdz1());
			safetywarining.setComId(company.getId());
			safetywarining.setQymc(company.getCompanyname());
			//企业类型 qylx 行业分类 hyfl 企业规模 qygm  企业注册类型  qyzclx
			safetywarining.setQylx(company.getQylx());
			safetywarining.setHyfl(company.getHyfl());
			safetywarining.setQygm(company.getQygm());
			safetywarining.setQyzclx(company.getQyzclx());
			safetywarining.setIfwhpqylx(company.getIfwhpqylx());
			safetywarining.setIfyhbzjyqy(company.getIfyhbzjyqy());
			safetywarining.setIfzywhqylx(company.getIfzywhqylx());
			safetywarining.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
			safetywarining.setDelFlag(0);
			safetywarining.setCreateUserID(this.getLoginUserId());
			safetywarining.setCreateTime(new Date());
			safetywarining.setSzc(company.getSzc());
			safetywarining.setSzcname(company.getSzcname());
			safetywarining.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			safetywarining.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			safetywariningService.save(safetywarining);
		}else{
			company=companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			safetywarining.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			safetywarining.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			safetywariningService.update(safetywarining);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			safetywariningService.deleteWithFlag(ids);
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

	public Safetywarining getSafetywarining(){
		return this.safetywarining;
	}

	public void setSafetywarining(Safetywarining safetywarining){
		this.safetywarining = safetywarining;
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
