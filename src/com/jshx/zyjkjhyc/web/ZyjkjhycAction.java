/**
 * Class Name: ZyjkjhycAction
 * Class Description：职业相关异常追踪管理
 */
package com.jshx.zyjkjhyc.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.zycsjcry.entity.Zycsjcry;
import com.jshx.zycsjcry.service.ZycsjcryService;
import com.jshx.zyjkjhyc.entity.Zyjkjhyc;
import com.jshx.zyjkjhyc.service.ZyjkjhycService;

public class ZyjkjhycAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zyjkjhyc zyjkjhyc = new Zyjkjhyc();

	/**
	 * 业务类
	 */
	@Autowired
	private ZyjkjhycService zyjkjhycService;
	@Autowired
	private ZycsjcryService zycsjcryService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	//体检开始日期
	private String queryTjrqStart;
	//体检结束日期
	private String queryTjrqEnd;
	
	/**
	 * 初始化职业卫生情况列表
	 * author：陆婷
	 * 2013-11-21
	 */
	public String init()
	{
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			flag = "1";
		}
		else
		{
			flag = "2";
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
		    
		if(null != zyjkjhyc){
		    //设置查询条件，开发人员可以在此增加过滤条件

			if ((null != zyjkjhyc.getProid()) && (0 < zyjkjhyc.getProid().trim().length())){
				paraMap.put("proid", zyjkjhyc.getProid().trim());
			}
		}
		if(queryTjrqStart != null)
		{
			paraMap.put("queryTjrqStart",queryTjrqStart);
		}
		if(queryTjrqEnd != null)
		{
			paraMap.put("queryTjrqEnd",queryTjrqEnd);
		}
		pagination = zyjkjhycService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != zyjkjhyc)&&(null != zyjkjhyc.getId()))
		{
			zyjkjhyc = zyjkjhycService.getById(zyjkjhyc.getId());
		}
		else
		{
			Zycsjcry zycsjcry = zycsjcryService.getById(zyjkjhyc.getProid());
			zyjkjhyc.setSfz(zycsjcry.getSfz());
			zyjkjhyc.setXm(zycsjcry.getXm());
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
		if ("add".equalsIgnoreCase(this.flag)){
			zyjkjhyc.setDeptId(this.getLoginUserDepartmentId());
			zyjkjhyc.setDelFlag(0);
			zyjkjhycService.save(zyjkjhyc);
		}else{
			zyjkjhycService.update(zyjkjhyc);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			zyjkjhycService.deleteWithFlag(ids);
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

	public Zyjkjhyc getZyjkjhyc(){
		return this.zyjkjhyc;
	}

	public void setZyjkjhyc(Zyjkjhyc zyjkjhyc){
		this.zyjkjhyc = zyjkjhyc;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public String getQueryTjrqStart() {
		return queryTjrqStart;
	}

	public void setQueryTjrqStart(String queryTjrqStart) {
		this.queryTjrqStart = queryTjrqStart;
	}

	public String getQueryTjrqEnd() {
		return queryTjrqEnd;
	}

	public void setQueryTjrqEnd(String queryTjrqEnd) {
		this.queryTjrqEnd = queryTjrqEnd;
	}
       
    
}
