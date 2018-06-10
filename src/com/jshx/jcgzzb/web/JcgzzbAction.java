/**
 * Class Name: JcgzzbAction
 * Class Description：监察周报列表
 */
package com.jshx.jcgzzb.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.jcgzzb.entity.Jcgzzb;
import com.jshx.jcgzzb.service.JcgzzbService;

public class JcgzzbAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Jcgzzb jcgzzb = new Jcgzzb();

	/**
	 * 业务类
	 */
	@Autowired
	private JcgzzbService jcgzzbService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private String createUserID;
	
	private Date queryTjkssjStart;

	private Date queryTjkssjEnd;

	private Date queryTjjssjStart;

	private Date queryTjjssjEnd;

	private Date queryTbrqStart;

	private Date queryTbrqEnd;

	/**
	 * 初始化监察周报列表
	 * author：陆婷
	 * 2013-10-31
	 */
	public String init()
	{
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			flag = "1";
		}
		else
		{
			flag = "0";
		}
		createUserID = this.getLoginUserId();
		return SUCCESS;
	}
	/**
	 * 查询监察周报列表
	 * author：陆婷
	 *2013-10-31
	 */

	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jcgzzb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if (null != queryTjkssjStart){
				paraMap.put("startTjkssj", queryTjkssjStart);
			}

			if (null != queryTjkssjEnd){
				paraMap.put("endTjkssj", queryTjkssjEnd);
			}
			if (null != queryTjjssjStart){
				paraMap.put("startTjjssj", queryTjjssjStart);
			}

			if (null != queryTjjssjEnd){
				paraMap.put("endTjjssj", queryTjjssjEnd);
			}
			if ((null != jcgzzb.getTbr()) && (0 < jcgzzb.getTbr().trim().length())){
				paraMap.put("tbr", "%" + jcgzzb.getTbr().trim() + "%");
			}
			if ((null != jcgzzb.getSzzid()) && (0 < jcgzzb.getSzzid().trim().length())){
				paraMap.put("szzid", jcgzzb.getSzzid().trim());
			}
		}
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			paraMap.put("deptCodes", this.getLoginUserDepartment().getChildDeptIds());
		}
		pagination = jcgzzbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看监察周报详情
	 * author：陆婷
	 * 2013-10-31
	 */

	public String view() throws Exception{
		if((null != jcgzzb)&&(null != jcgzzb.getId()))
		{
			jcgzzb = jcgzzbService.getById(jcgzzb.getId());
		}
		else
		{
			jcgzzb.setSzzname(this.getLoginUserDepartment().getDeptName());
			jcgzzb.setTbr(this.getLoginUser().getDisplayName());
			jcgzzb.setTbrq(new Date());
		}
		return VIEW;
	}

	/**
	 * 初始化监察周报修改信息
	 * author：陆婷
	 * 2013-10-31
	 */

	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存监察周报信息
	 * author：陆婷
	 * 2013-10-31
	 */

	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			jcgzzb.setSzzid(this.getLoginUserDepartment().getDeptCode());
			jcgzzb.setCreateTime(new Date());
			jcgzzb.setCreateUserID(this.getLoginUserId());
			jcgzzb.setDeptId(this.getLoginUserDepartmentId());
			jcgzzb.setDelFlag(0);
			jcgzzbService.save(jcgzzb);
		}else{
			jcgzzbService.update(jcgzzb);
		}
		return RELOAD;
	}

	/**
	 * 删除监察周报信息
	 * author：陆婷
	 * 2013-10-31
	 */

	public String delete() throws Exception{
	    try{
			jcgzzbService.deleteWithFlag(ids);
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

	public Jcgzzb getJcgzzb(){
		return this.jcgzzb;
	}

	public void setJcgzzb(Jcgzzb jcgzzb){
		this.jcgzzb = jcgzzb;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryTjkssjStart(){
		return this.queryTjkssjStart;
	}

	public void setQueryTjkssjStart(Date queryTjkssjStart){
		this.queryTjkssjStart = queryTjkssjStart;
	}

	public Date getQueryTjkssjEnd(){
		return this.queryTjkssjEnd;
	}

	public void setQueryTjkssjEnd(Date queryTjkssjEnd){
		this.queryTjkssjEnd = queryTjkssjEnd;
	}

	public Date getQueryTjjssjStart(){
		return this.queryTjjssjStart;
	}

	public void setQueryTjjssjStart(Date queryTjjssjStart){
		this.queryTjjssjStart = queryTjjssjStart;
	}

	public Date getQueryTjjssjEnd(){
		return this.queryTjjssjEnd;
	}

	public void setQueryTjjssjEnd(Date queryTjjssjEnd){
		this.queryTjjssjEnd = queryTjjssjEnd;
	}

	public Date getQueryTbrqStart(){
		return this.queryTbrqStart;
	}

	public void setQueryTbrqStart(Date queryTbrqStart){
		this.queryTbrqStart = queryTbrqStart;
	}

	public Date getQueryTbrqEnd(){
		return this.queryTbrqEnd;
	}

	public void setQueryTbrqEnd(Date queryTbrqEnd){
		this.queryTbrqEnd = queryTbrqEnd;
	}
	public String getCreateUserID() {
		return createUserID;
	}
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

}
