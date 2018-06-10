/**
 * Class Name: SafeleaderAction
 * Class Description：设施台帐列表
 */
package com.jshx.safeleader.web;

import java.text.SimpleDateFormat;
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
import com.jshx.safeleader.entity.Safeleader;
import com.jshx.safeleader.service.SafeleaderService;
import com.jshx.safetysheet.service.SafetysheetService;

public class SafeleaderAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Safeleader safeleader = new Safeleader();

	/**
	 * 业务类
	 */
	@Autowired
	private SafeleaderService safeleaderService;

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
	
	/**
	 * @author gq
	 * @date 8yue 16
	 * @function 存放附件列表
	 */
	private List<PhotoPic> list = new ArrayList<PhotoPic>();
	
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
	 * @date 8yue 16
	 * @function 初始化安全生产页面
	 * @return 上传附件页面
	 */
	public String initlist() throws Exception{
		
		if(this.getLoginUser().getDept().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))//监管部门以下的角色
		    this.visable="0";
		else 
			 this.visable="1";
		try {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @author gq
	 * @date 8yue19
	 * @function 
	 * 执行查询的方法，返回台账json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != safeleader){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != safeleader.getBasetype1()) && (0 < safeleader.getBasetype1().trim().length())){
				paraMap.put("basetype", safeleader.getBasetype1().trim());
			}
			if ((null != safeleader.getBasetype2()) && (0 < safeleader.getBasetype2().trim().length())){
				paraMap.put("basetype", safeleader.getBasetype2().trim());
			}
			if ((null != safeleader.getBasetype3()) && (0 < safeleader.getBasetype3().trim().length())){
				paraMap.put("basetype", safeleader.getBasetype3().trim());
			}
			if ((null != safeleader.getLeadgertype()) && (0 < safeleader.getLeadgertype().trim().length())){
				paraMap.put("leadgertype", safeleader.getLeadgertype().trim());
			}
			if ((null != safeleader.getSsmc()) && (0 < safeleader.getSsmc().trim().length())){
				paraMap.put("ssmc", "%" + safeleader.getSsmc().trim() + "%");
			}
			if(safeleader.getComName()!=null&&safeleader.getComName().length()>0)
				paraMap.put("comName", "%"+safeleader.getComName()+"%");
			
			if(safeleader.getDeptId()!=null&&safeleader.getDeptId().length()>0)
				paraMap.put("deptCodes", safeleader.getDeptId());
			if ((null != safeleader.getSzc() )&& (0 < safeleader.getSzc().trim().length())){
				paraMap.put("szc",safeleader.getSzc().trim());
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
		 
		pagination = safeleaderService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * @author gq
	 * @date 8yue19
	 * 查看台账的详细信息
	 */
	public String view() throws Exception{
		if((null != safeleader)&&(null != safeleader.getId()))
			safeleader = safeleaderService.getById(safeleader.getId());
		if(safeleader.getLinkid()==null)
		{
			safeleader.setLinkid(UUID.randomUUID().toString());
		}
		Map map = new HashMap();
		map.put("taskProId",safeleader.getLinkid());
		map.put("picType","aqsbdjtz");
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
	    return EDIT;
	}

	/**
	 * @author gq 
	 * @date 8yue 19
	 * @function 添加或更新台账信息
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		safeleader.setSsmc(sdf.format(new Date()));
		company=companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());//根据用户id查询出单位信息，从而得到单位名
		if ("add".equalsIgnoreCase(this.flag)){
			safeleader.setDelFlag(0);
			   safeleader.setDeptName(deptService.findDeptByDeptCode(company.getDwdz1()).getDeptName());//根据所属街道查询出街道代码和名称
			   /**
			    * 判断类型并把其值设置到台账类别中
			    */
			   if(safeleader.getBasetype1()!=null&&safeleader.getBasetype1().length()>0)
				   safeleader.setBasetype(safeleader.getBasetype1());
			   else if(safeleader.getBasetype2()!=null&&safeleader.getBasetype2().length()>0)
				   safeleader.setBasetype(safeleader.getBasetype2());
			   else
				   safeleader.setBasetype(safeleader.getBasetype3());
				   
				   /**
				    * 街道代码
				    */
				   
				safeleader.setDeptId(company.getDwdz1());
				  /**
				    * 公司id
				    */
				safeleader.setComId(company.getId());
				 /**
				    * 公司名称
				    */
				safeleader.setComName(company.getCompanyname());
				//企业类型 qylx 行业分类 hyfl 企业规模 qygm  企业注册类型  qyzclx
				safeleader.setQylx(company.getQylx());
				safeleader.setHyfl(company.getHyfl());
				safeleader.setQygm(company.getQygm());
				safeleader.setQyzclx(company.getQyzclx());
				safeleader.setIfwhpqylx(company.getIfwhpqylx());
				safeleader.setIfyhbzjyqy(company.getIfyhbzjyqy());
				safeleader.setIfzywhqylx(company.getIfzywhqylx());
				safeleader.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
				safeleader.setCreateUserID(this.getLoginUserId());
				safeleader.setCreateTime(new Date());
				safeleader.setSzc(company.getSzc());
				safeleader.setSzcname(company.getSzcname());
				safeleader.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
				safeleader.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			safeleaderService.save(safeleader);
		}else{
			   /**
			    * 判断类型并把其值设置到台账类别中
			    */
			   if(safeleader.getBasetype1()!=null&&safeleader.getBasetype1().length()>0)
				   safeleader.setBasetype(safeleader.getBasetype1());
			   else if(safeleader.getBasetype2()!=null&&safeleader.getBasetype2().length()>0)
				   safeleader.setBasetype(safeleader.getBasetype2());
			   else
				   safeleader.setBasetype(safeleader.getBasetype3());
			   safeleader.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			   safeleader.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			safeleaderService.update(safeleader);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			safeleaderService.deleteWithFlag(ids);
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

	public Safeleader getSafeleader(){
		return this.safeleader;
	}

	public void setSafeleader(Safeleader safeleader){
		this.safeleader = safeleader;
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
