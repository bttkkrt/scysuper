/**
 * Class Name: FactorypictureAction
 * Class Description：工厂图片
 */
package com.jshx.factorypicture.web;

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
import com.jshx.factorypicture.entity.Factorypicture;
import com.jshx.factorypicture.service.FactorypictureService;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.safetysheet.service.SafetysheetService;

public class FactorypictureAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Factorypicture factorypicture = new Factorypicture();

	/**
	 * 业务类
	 */
	@Autowired
	private FactorypictureService factorypictureService;
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

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	/**
	 * @author gq
	 * @date 8yue 16
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
	/** @author gq
	 * @date 8yue 16 
     *用于区分监管部门0和企业1
     */
    private String role;
    /** @author gq
	 * @date 8yue 16 
     *用于区分监管部门0和更高级别1
     */
    private String visable;
	private String isshow;
	
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	/**
	 * @author gq
	 * @date 8yue 16
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
	 * @date 8yue 16 
	 * @function 查询数据
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
	
		if(null != factorypicture){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != factorypicture.getPhysicalplantname()) && (0 < factorypicture.getPhysicalplantname().trim().length())){
				paraMap.put("physicalplantname", "%" + factorypicture.getPhysicalplantname().trim() + "%");
			}
			if(factorypicture.getComName()!=null&&factorypicture.getComName().length()>0)
				paraMap.put("comName", "%"+factorypicture.getComName()+"%");
			
			if(factorypicture.getDeptId()!=null&&factorypicture.getDeptId().length()>0)
				paraMap.put("deptCode", factorypicture.getDeptId());
			if(factorypicture.getComId()!=null&&factorypicture.getComId().trim().length()>0)
			{
				paraMap.put("comId", factorypicture.getComId().trim());
			}
			if ((null != factorypicture.getSzc() )&& (0 < factorypicture.getSzc().trim().length())){
				paraMap.put("szc",factorypicture.getSzc().trim());
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
		 
		pagination = factorypictureService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * @author gq
	 * @date 8yue 16
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != factorypicture)&&(null != factorypicture.getId()))
			factorypicture = factorypictureService.getById(factorypicture.getId());
		if(factorypicture.getLinkid()==null)
		{
			factorypicture.setLinkid(UUID.randomUUID().toString());
		}
		Map map = new HashMap();
		map.put("taskProId",factorypicture.getLinkid());
		map.put("picType","cqtp");
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
	 * @author gq
	 * @date 8yue 16
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * @author gq
	 * @date 8yue 16
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		company=companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			factorypicture.setDelFlag(0);
		   factorypicture.setDeptName(deptService.findDeptByDeptCode(company.getDwdz1()).getDeptName());
			factorypicture.setDeptId(company.getDwdz1());
			factorypicture.setComId(company.getId());
			factorypicture.setComName(company.getCompanyname());
			
			
			//企业类型 qylx 行业分类 hyfl 企业规模 qygm  企业注册类型  qyzclx
			factorypicture.setQylx(company.getQylx());
			factorypicture.setHyfl(company.getHyfl());
			factorypicture.setQygm(company.getQygm());
			factorypicture.setQyzclx(company.getQyzclx());
			
			factorypicture.setIfwhpqylx(company.getIfwhpqylx());
			factorypicture.setIfyhbzjyqy(company.getIfyhbzjyqy());
			factorypicture.setIfzywhqylx(company.getIfzywhqylx());
			factorypicture.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
			
			factorypicture.setSzc(company.getSzc());
			factorypicture.setSzcname(company.getSzcname());
			
			factorypicture.setDelFlag(0);
			factorypicture.setCreateTime(new Date());
			factorypicture.setCreateUserID(this.getLoginUserId());
			factorypicture.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			factorypicture.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			factorypictureService.save(factorypicture);
		}else{
			factorypicture.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			factorypicture.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			factorypictureService.update(factorypicture);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			factorypictureService.deleteWithFlag(ids);
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

	public Factorypicture getFactorypicture(){
		return this.factorypicture;
	}

	public void setFactorypicture(Factorypicture factorypicture){
		this.factorypicture = factorypicture;
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
