/**
 * Class Name: YjzbwzypAction
 * Class Description：应急装备、物资和药品列表
 */
package com.jshx.yjzbwzyp.web;

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
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.safetysheet.service.SafetysheetService;
import com.jshx.yjzbwzyp.entity.Yjzbwzyp;
import com.jshx.yjzbwzyp.service.YjzbwzypService;

public class YjzbwzypAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Yjzbwzyp yjzbwzyp = new Yjzbwzyp();

	/**
	 * 业务类
	 */
	@Autowired
	private YjzbwzypService yjzbwzypService;

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
	 * @date 8yue 26 
  *用于区分监管部门0和企业1
  */
 private String role;
 /** @author gq
	 * @date 8yue 26
  *用于区分监管部门0和更高级别1
  */
 private String visable;
	/**
	 * @author gq
	 * @date 8yue 16
	 * @function 存放附件列表
	 */
	private List<PhotoPic> list = new ArrayList<PhotoPic>();
	/**
	 * @author gq
	 * @date 8yue 26
	 * @function 初始化安全生产页面
	 * @return 上传附件页面
	 */
	public String initlist() throws Exception{
	
		try {
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
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();

			if(pagination==null)
			    pagination = new Pagination(this.getRequest());
			    
			if(null != yjzbwzyp){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != yjzbwzyp.getQymc()) && (0 < yjzbwzyp.getQymc().trim().length())){
					paraMap.put("qymc", "%" + yjzbwzyp.getQymc().trim() + "%");
				}

				if ((null != yjzbwzyp.getDeptId()) && (0 < yjzbwzyp.getDeptId().trim().length())){
					paraMap.put("deptCodes", yjzbwzyp.getDeptId().trim());
				}

				if ((null != yjzbwzyp.getYjlx()) && (0 < yjzbwzyp.getYjlx().trim().length())){
					paraMap.put("yjlx", yjzbwzyp.getYjlx().trim());
				}

				if ((null != yjzbwzyp.getZbmc()) && (0 < yjzbwzyp.getZbmc().trim().length())){
					paraMap.put("zbmc", "%" + yjzbwzyp.getZbmc().trim() + "%");
				}

				if ((null != yjzbwzyp.getCcdd()) && (0 < yjzbwzyp.getCcdd().trim().length())){
					paraMap.put("ccdd", "%" + yjzbwzyp.getCcdd().trim() + "%");
				}
				if ((null != yjzbwzyp.getSzc() )&& (0 < yjzbwzyp.getSzc().trim().length())){
					paraMap.put("szc",yjzbwzyp.getSzc().trim());
				}
			}
			//hanxc 20141223 修改查询条件 start
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			String deptRole = this.getLoginUser().getDeptRole();
			if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
				paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
			}
			//hanxc 20141223 修改查询条件 end
			
			pagination = yjzbwzypService.findByPage(pagination, paraMap);
			
			convObjectToJson(pagination,null);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != yjzbwzyp)&&(null != yjzbwzyp.getId()))
			yjzbwzyp = yjzbwzypService.getById(yjzbwzyp.getId());
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
			yjzbwzyp.setDelFlag(0);
			yjzbwzyp.setSzz(deptService.findDeptByDeptCode(company.getDwdz1()).getDeptName());
			yjzbwzyp.setDeptId(company.getDwdz1());
			yjzbwzyp.setComid(company.getId());
			yjzbwzyp.setQymc(company.getCompanyname());
				//企业类型 qylx 行业分类 hyfl 企业规模 qygm  企业注册类型  qyzclx
			yjzbwzyp.setQylx(company.getQylx());
			yjzbwzyp.setHyfl(company.getHyfl());
				yjzbwzyp.setQygm(company.getQygm());
				yjzbwzyp.setQyzclx(company.getQyzclx());
				yjzbwzyp.setIfwhpqylx(company.getIfwhpqylx());
				yjzbwzyp.setIfyhbzjyqy(company.getIfyhbzjyqy());
				yjzbwzyp.setIfzywhqylx(company.getIfzywhqylx());
				yjzbwzyp.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
				yjzbwzyp.setDelFlag(0);
				yjzbwzyp.setCreateUserID(this.getLoginUserId());
				yjzbwzyp.setCreateTime(new Date());
				yjzbwzyp.setSzc(company.getSzc());
				yjzbwzyp.setSzcname(company.getSzcname());
				yjzbwzyp.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
				yjzbwzyp.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			yjzbwzypService.save(yjzbwzyp);
		}else{
			yjzbwzyp.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			yjzbwzyp.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			yjzbwzypService.update(yjzbwzyp);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			yjzbwzypService.deleteWithFlag(ids);
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

	public Yjzbwzyp getYjzbwzyp(){
		return this.yjzbwzyp;
	}

	public void setYjzbwzyp(Yjzbwzyp yjzbwzyp){
		this.yjzbwzyp = yjzbwzyp;
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
