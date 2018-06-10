package com.jshx.hmd.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.hmd.entity.Hmd;
import com.jshx.hmd.service.HmdService;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.service.UserService;

public class HmdAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Hmd hmd = new Hmd();

	/**
	 * 业务类
	 */
	@Autowired
	private HmdService hmdService;
	@Autowired
	private UserService userService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	private Date startProDate;
	private Date endProDate;
	
	@Autowired
	private HttpService httpService;
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	private int pageNo;
	private int pageSize;
	private String searchLike;
	public String init(){
		return SUCCESS;
	}
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != hmd){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != hmd.getAreaId()) && (0 < hmd.getAreaId().trim().length())){
				paraMap.put("areaId",  hmd.getAreaId().trim() );
			}

			if ((null != hmd.getCompanyName()) && (0 < hmd.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + hmd.getCompanyName().trim() + "%");
			}

			if ((null != hmd.getStartTime()) && (0 < hmd.getStartTime().trim().length())){
				paraMap.put("startTime", "%" + hmd.getStartTime().trim() + "%");
			}
			
			if (null != startProDate){
				paraMap.put("startProDate", new SimpleDateFormat("yyyy-MM-dd").format(startProDate));
			}
			
			if (null != endProDate){
				paraMap.put("endProDate", new SimpleDateFormat("yyyy-MM-dd").format(endProDate));
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|startTime|illegalActivity|";
		if (filter != null && filter.length() > 1) {
			config.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String name, Object value) {
					if (filter.indexOf(name + "|") != -1)
						return false;
					else
						return true;
				}
			});
		}
		pagination = hmdService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != hmd)&&(null != hmd.getId())){
			hmd = hmdService.getById(hmd.getId());
			hmd.setCreateUserID(userService.findUserById(hmd.getCreateUserID()).getDisplayName());
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
		FileInputStream in = null;
		try
		{
			//设置Blob字段
			setBlobField(in);
		}
		finally
		{
			if (null != in)
			{
				try
				{
					in.close();
				}
				catch (Exception ex)
				{
				}
			}
		}		
	
		if ("add".equalsIgnoreCase(this.flag)){
			hmd.setDeptId(this.getLoginUserDepartmentId());
			hmd.setDelFlag(0);
			hmdService.save(hmd);
		}else{
			hmdService.update(hmd);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != hmd)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到hmd中去
				
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			hmdService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != hmd){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != hmd.getAreaId()) && (0 < hmd.getAreaId().trim().length())){
				paraMap.put("areaId",  hmd.getAreaId().trim() );
			}

			if ((null != hmd.getCompanyName()) && (0 < hmd.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + hmd.getCompanyName().trim() + "%");
			}

			if ((null != hmd.getStartTime()) && (0 < hmd.getStartTime().trim().length())){
				paraMap.put("startTime", "%" + hmd.getStartTime().trim() + "%");
			}
			
			if (null != startProDate){
				paraMap.put("startProDate", new SimpleDateFormat("yyyy-MM-dd").format(startProDate));
			}
			
			if (null != endProDate){
				paraMap.put("endProDate", new SimpleDateFormat("yyyy-MM-dd").format(endProDate));
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|startTime|illegalActivity|";
		if (filter != null && filter.length() > 1) {
			config.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String name, Object value) {
					if (filter.indexOf(name + "|") != -1)
						return false;
					else
						return true;
				}
			});
		}
		
		
		if(null!=searchLike&&!"".equals(searchLike)){
			paraMap.put("companyName", "%" + searchLike.trim() + "%");
		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = hmdService.findByPage(pagination, paraMap);
			
		JSONObject json=new JSONObject();
		JSONArray ja = JSONArray.fromObject(pagination.getListOfObject(),
				config);
		json.put("result", ja);
		json.put("count", pagination.getTotalCount());
		int totalPage;
		totalPage = (pagination.getTotalCount()%pageSize==0?pagination.getTotalCount()/pageSize:(pagination.getTotalCount()/pageSize+1));
		json.put("totalPage", totalPage);
		json.put("pageNo", pageNo);
		try {
			this.getResponse().getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public Hmd getHmd(){
		return this.hmd;
	}

	public void setHmd(Hmd hmd){
		this.hmd = hmd;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public Date getStartProDate() {
		return startProDate;
	}

	public void setStartProDate(Date startProDate) {
		this.startProDate = startProDate;
	}

	public Date getEndProDate() {
		return endProDate;
	}

	public void setEndProDate(Date endProDate) {
		this.endProDate = endProDate;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getSearchLike() {
		return searchLike;
	}
	public void setSearchLike(String searchLike) {
		this.searchLike = searchLike;
	}
       
    
}
