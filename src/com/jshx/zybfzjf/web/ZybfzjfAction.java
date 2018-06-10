/**
 * Class Name: ZybfzjfAction
 * Class Description：年度职业病防治经费
 */

package com.jshx.zybfzjf.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.zybfzjf.entity.Zybfzjf;
import com.jshx.zybfzjf.service.ZybfzjfService;

public class ZybfzjfAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zybfzjf zybfzjf = new Zybfzjf();

	/**
	 * 业务类
	 */
	@Autowired
	private ZybfzjfService zybfzjfService;
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
		    
		if(null != zybfzjf){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zybfzjf.getJshxYear()) && (0 < zybfzjf.getJshxYear().trim().length())){
				paraMap.put("jshxYear", "%" + zybfzjf.getJshxYear().trim() + "%");
			}
			if ((null != zybfzjf.getQymc()) && (0 < zybfzjf.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zybfzjf.getQymc().trim() + "%");
			}
			if ((null != zybfzjf.getSzzid()) && (0 < zybfzjf.getSzzid().trim().length())){
				paraMap.put("szzid",  zybfzjf.getSzzid().trim() );
			}
			if ((null != zybfzjf.getYt()) && (0 < zybfzjf.getYt().trim().length())){
				paraMap.put("yt",  zybfzjf.getYt().trim() );
			}
			if ((null != zybfzjf.getSzc() )&& (0 < zybfzjf.getSzc().trim().length())){
				paraMap.put("szc",zybfzjf.getSzc().trim());
			}
		}
		//判断登录人是否为企业人员，要是，则查询自己登记
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			paraMap.put("qyid", company.getId());
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr"))) //判断为安监中队，则查看所在镇企业
		{
			if(deptCode.length() == 12)
			{
				paraMap.put("szzid",deptCode);
			}
			else
			{
				paraMap.put("szc", deptCode);
			}
		}
		pagination = zybfzjfService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != zybfzjf)&&(null != zybfzjf.getId()))
			zybfzjf = zybfzjfService.getById(zybfzjf.getId());
		
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
			zybfzjf.setDeptId(this.getLoginUserDepartmentId());
			zybfzjf.setDelFlag(0);
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			zybfzjf.setSzzid(dept.getDeptCode());
			zybfzjf.setSzzname(dept.getDeptName());
			zybfzjf.setQyid(company.getId());
			zybfzjf.setQymc(company.getCompanyname());
			zybfzjf.setDeptId(this.getLoginUserDepartmentId());
			zybfzjf.setDelFlag(0);
			zybfzjf.setCreateUserID(this.getLoginUserId());
			zybfzjf.setCreateTime(new Date());
			zybfzjf.setQylx(company.getQylx());
			zybfzjf.setHyfl(company.getHyfl());
			zybfzjf.setQygm(company.getQygm());
			zybfzjf.setQyzclx(company.getQyzclx());
			zybfzjf.setSzc(company.getSzc());
			zybfzjf.setSzcname(company.getSzcname());
			zybfzjfService.save(zybfzjf);
		}else{
			zybfzjfService.update(zybfzjf);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			zybfzjfService.deleteWithFlag(ids);
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

	public Zybfzjf getZybfzjf(){
		return this.zybfzjf;
	}

	public void setZybfzjf(Zybfzjf zybfzjf){
		this.zybfzjf = zybfzjf;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
