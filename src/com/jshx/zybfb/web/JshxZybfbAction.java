/**
 * Class Name: JshxZybfbAction
 * Class Description：职业病危害因素分布
 */
package com.jshx.zybfb.web;

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
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.zybfb.entity.JshxZybfb;
import com.jshx.zybfb.service.JshxZybfbService;

public class JshxZybfbAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxZybfb jshxZybfb = new JshxZybfb();

	/**
	 * 业务类
	 */
	@Autowired
	private JshxZybfbService jshxZybfbService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private SzwxPhotoService szwxPhotoService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	/**
	 * 初始化列表
	 * @return
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
		    
		if(null != jshxZybfb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxZybfb.getWorkName()) && (0 < jshxZybfb.getWorkName().trim().length())){
				paraMap.put("workName", "%" + jshxZybfb.getWorkName().trim() + "%");
			}

			if ((null != jshxZybfb.getZybName()) && (0 < jshxZybfb.getZybName().trim().length())){
				paraMap.put("zybName", "%" + jshxZybfb.getZybName().trim() + "%");
			}

			if ((null != jshxZybfb.getTbr()) && (0 < jshxZybfb.getTbr().trim().length())){
				paraMap.put("tbr", "%" + jshxZybfb.getTbr().trim() + "%");
			}
			if ((null != jshxZybfb.getQymc()) && (0 < jshxZybfb.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxZybfb.getQymc().trim() + "%");
			}
			if ((null != jshxZybfb.getSzzid()) && (0 < jshxZybfb.getSzzid().trim().length())){
				paraMap.put("szzid",  jshxZybfb.getSzzid().trim() );
			}
			if ((null != jshxZybfb.getSzc() )&& (0 < jshxZybfb.getSzc().trim().length())){
				paraMap.put("szc",jshxZybfb.getSzc().trim());
			}
		}

		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
 
		pagination = jshxZybfbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != jshxZybfb)&&(null != jshxZybfb.getId()))
			jshxZybfb = jshxZybfbService.getById(jshxZybfb.getId());
		
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
			try {
				Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
				jshxZybfb.setSzzid(dept.getDeptCode());
				jshxZybfb.setSzzname(dept.getDeptName());
				jshxZybfb.setQyid(company.getId());
				jshxZybfb.setQymc(company.getCompanyname());
				jshxZybfb.setDeptId(this.getLoginUserDepartmentId());
				jshxZybfb.setDelFlag(0);
				jshxZybfb.setCreateUserID(this.getLoginUserId());
				jshxZybfb.setCreateTime(new Date());
				jshxZybfb.setQylx(company.getQylx());
				jshxZybfb.setHyfl(company.getHyfl());
				jshxZybfb.setQygm(company.getQygm());
				jshxZybfb.setQyzclx(company.getQyzclx());
				jshxZybfb.setDeptId(this.getLoginUserDepartmentId());
				jshxZybfb.setDelFlag(0);
				jshxZybfb.setIfwhpqylx(company.getIfwhpqylx());
				jshxZybfb.setIfyhbzjyqy(company.getIfyhbzjyqy());
				jshxZybfb.setIfzywhqylx(company.getIfzywhqylx());
				jshxZybfb.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山
				
				jshxZybfb.setSzc(company.getSzc());
				jshxZybfb.setSzcname(company.getSzcname());
			} catch (RuntimeException e) {
				e.printStackTrace();
			}//企业名称
			jshxZybfb.setDeptId(this.getLoginUserDepartmentId());
			jshxZybfb.setDelFlag(0);
			jshxZybfb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxZybfb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxZybfbService.save(jshxZybfb);
		}else{
			jshxZybfb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxZybfb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxZybfbService.update(jshxZybfb);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			jshxZybfbService.deleteWithFlag(ids);
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

	public JshxZybfb getJshxZybfb(){
		return this.jshxZybfb;
	}

	public void setJshxZybfb(JshxZybfb jshxZybfb){
		this.jshxZybfb = jshxZybfb;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
