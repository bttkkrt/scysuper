package com.jshx.wzcompany.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.wzcompany.entity.Wzcompany;
import com.jshx.wzcompany.service.WzcompanyService;

public class WzcompanyAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Wzcompany wzcompany = new Wzcompany();

	/**
	 * 业务类
	 */
	@Autowired
	private WzcompanyService wzcompanyService;
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
	
	private List<Department> deptlist = new ArrayList<Department>();
	
	public String init()
	{
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != wzcompany){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != wzcompany.getCompanyname()) && (0 < wzcompany.getCompanyname().trim().length())){
				paraMap.put("companyname", "%"+wzcompany.getCompanyname().trim()+"%");
			}
			if ((null != wzcompany.getFzr()) && (0 < wzcompany.getFzr().trim().length())){
				paraMap.put("fzr", "%"+wzcompany.getFzr().trim()+"%");
			}
			if ((null != wzcompany.getSzzid()) && (0 < wzcompany.getSzzid().trim().length())){
				paraMap.put("szzid", wzcompany.getSzzid().trim());
			}
			if ((null != wzcompany.getSzc()) && (0 < wzcompany.getSzc().trim().length())){
				paraMap.put("szc", wzcompany.getSzc().trim());
			}
			if ((null != wzcompany.getHyfl()) && (0 < wzcompany.getHyfl().trim().length())){
				paraMap.put("hyfl", wzcompany.getHyfl().trim());
			}
			if ((null != wzcompany.getJycsxz()) && (0 < wzcompany.getJycsxz().trim().length())){
				paraMap.put("jycsxz", wzcompany.getJycsxz().trim());
			}
			if ((null != wzcompany.getIfwhpqylx()) && (0 < wzcompany.getIfwhpqylx().trim().length())){
				paraMap.put("ifwhpqylx", wzcompany.getIfwhpqylx().trim());
			}
			if ((null != wzcompany.getIfzywhqylx()) && (0 < wzcompany.getIfzywhqylx().trim().length())){
				paraMap.put("ifzywhqylx", wzcompany.getIfzywhqylx().trim());
			}
			if ((null != wzcompany.getIfwxqy()) && (0 < wzcompany.getIfwxqy().trim().length())){
				paraMap.put("ifwxqy", wzcompany.getIfwxqy().trim());
			}
		}
		
		pagination = wzcompanyService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != wzcompany)&&(null != wzcompany.getId()))
		{
			wzcompany = wzcompanyService.getById(wzcompany.getId());
			deptlist = deptService.findDeptByParentDeptCode(wzcompany.getSzzid());
		}
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
		if(StringUtils.isEmpty(wzcompany.getIfwhpqylx())){
			wzcompany.setWhpqylx("0");
		}
		if(StringUtils.isEmpty(wzcompany.getIfzywhqylx())){
			wzcompany.setIfzywhqylx("0");
		}
		if ("add".equalsIgnoreCase(this.flag)){
			wzcompany.setDeptId(this.getLoginUserDepartmentId());
			wzcompany.setDelFlag(0);
			wzcompanyService.save(wzcompany);
		}else{
			wzcompanyService.update(wzcompany);
		}
		flag="1";
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			wzcompanyService.deleteWithFlag(ids);
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

	public Wzcompany getWzcompany(){
		return this.wzcompany;
	}

	public void setWzcompany(Wzcompany wzcompany){
		this.wzcompany = wzcompany;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<Department> getDeptlist() {
		return deptlist;
	}

	public void setDeptlist(List<Department> deptlist) {
		this.deptlist = deptlist;
	}
       
    
}
