/**
 * Class Name: ZybwhzlAction
 * Class Description：职业病危害因素种类
 */
package com.jshx.zybwhzl.web;

import java.util.Date;
import java.util.HashMap;
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
import com.jshx.zybwhzl.entity.Zybwhzl;
import com.jshx.zybwhzl.service.ZybwhzlService;

public class ZybwhzlAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zybwhzl zybwhzl = new Zybwhzl();

	/**
	 * 业务类
	 */
	@Autowired
	private ZybwhzlService zybwhzlService;
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
	
	/**
	 * 初始化安全附件情况列表
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
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			flag = "2";
		}
		else
		{
			flag = "3";
		}
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != zybwhzl){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zybwhzl.getQymc()) && (0 < zybwhzl.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zybwhzl.getQymc().trim() + "%");
			}
			if ((null != zybwhzl.getSzzid()) && (0 < zybwhzl.getSzzid().trim().length())){
				paraMap.put("szzid",  zybwhzl.getSzzid().trim() );
			}
			if ((null != zybwhzl.getSzc() )&& (0 < zybwhzl.getSzc().trim().length())){
				paraMap.put("szc",zybwhzl.getSzc().trim());
			}
		}
		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		 
		pagination = zybwhzlService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != zybwhzl)&&(null != zybwhzl.getId()))
			zybwhzl = zybwhzlService.getById(zybwhzl.getId());
		
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
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			zybwhzl.setSzzid(dept.getDeptCode());
			zybwhzl.setSzzname(dept.getDeptName());
			zybwhzl.setQyid(company.getId());
			zybwhzl.setQymc(company.getCompanyname());
			zybwhzl.setDeptId(this.getLoginUserDepartmentId());
			zybwhzl.setDelFlag(0);
			zybwhzl.setCreateUserID(this.getLoginUserId());
			zybwhzl.setCreateTime(new Date());
			zybwhzl.setQylx(company.getQylx());
			zybwhzl.setHyfl(company.getHyfl());
			zybwhzl.setQygm(company.getQygm());
			zybwhzl.setQyzclx(company.getQyzclx());

			zybwhzl.setIfwhpqylx(company.getIfwhpqylx());
			zybwhzl.setIfyhbzjyqy(company.getIfyhbzjyqy());
			zybwhzl.setIfzywhqylx(company.getIfzywhqylx());
			zybwhzl.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山
			
			zybwhzl.setSzc(company.getSzc());
			zybwhzl.setSzcname(company.getSzcname());
			zybwhzl.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			zybwhzl.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			zybwhzlService.save(zybwhzl);
		}else{
			zybwhzl.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			zybwhzl.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			zybwhzlService.update(zybwhzl);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			zybwhzlService.deleteWithFlag(ids);
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

	public Zybwhzl getZybwhzl(){
		return this.zybwhzl;
	}

	public void setZybwhzl(Zybwhzl zybwhzl){
		this.zybwhzl = zybwhzl;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
