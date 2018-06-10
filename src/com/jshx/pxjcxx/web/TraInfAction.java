package com.jshx.pxjcxx.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.LobHelper;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.pxjcxx.entity.TraInf;
import com.jshx.pxjcxx.service.TraInfService;
import com.jshx.qyjbxx.service.EntBaseInfoService;

public class TraInfAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private TraInf traInf = new TraInf();

	/**
	 * 业务类
	 */
	@Autowired
	private TraInfService traInfService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryStartTimeStart;

	private Date queryStartTimeEnd;

	private Date queryEndTimeStart;

	private Date queryEndTimeEnd;

	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	 /**
	 * 初始化 用于判断审核角色
	 */
	private String roleName;
	public String init(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			
			if(ur.getRole().getRoleCode().equals("A15")) 
			{
				roleName = "1";
				break;
			}
		}
		return SUCCESS;
	}

	/**
	 * 执行查询的方法，返回json数据
	 */
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;


	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != traInf){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != traInf.getTrainTheme()) && (0 < traInf.getTrainTheme().trim().length())){
				paraMap.put("trainTheme", "%" + traInf.getTrainTheme().trim() + "%");
			}

			if (null != queryStartTimeStart){
				paraMap.put("startStartTime", queryStartTimeStart);
			}

			if (null != queryStartTimeEnd){
				paraMap.put("endStartTime", queryStartTimeEnd);
			}
			if (null != queryEndTimeStart){
				paraMap.put("startEndTime", queryEndTimeStart);
			}

			if (null != queryEndTimeEnd){
				paraMap.put("endEndTime", queryEndTimeEnd);
			}
			if ((null != traInf.getTrainMethod()) && (0 < traInf.getTrainMethod().trim().length())){
				paraMap.put("trainMethod", "%" + traInf.getTrainMethod().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|trainTheme|startTime|endTime|trainMethod|certificateNum|createUserID|";
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
		pagination = traInfService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != traInf)&&(null != traInf.getId()))
		{
			traInf = traInfService.getById(traInf.getId());
			if(traInf.getLinkId() == null || "".equals(traInf.getLinkId()))
			{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					traInf.setLinkId(linkId);
				}
				else
				{
					Map map = new HashMap();
					map.put("linkId",traInf.getLinkId());
					map.put("mkType", "pxjcxx");
					map.put("picType","pxfj");
				    picList = photoPicService.findPicPath(map);//获取执法文书材料
				}
			}
			else
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				traInf.setLinkId(linkId);
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
			traInf.setDeptId(this.getLoginUserDepartmentId());
			traInf.setDelFlag(0);
			traInfService.save(traInf);
		}else{
			traInfService.update(traInf);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != traInf)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到traInf中去
				
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
			traInfService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
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

	public TraInf getTraInf(){
		return this.traInf;
	}

	public void setTraInf(TraInf traInf){
		this.traInf = traInf;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryStartTimeStart(){
		return this.queryStartTimeStart;
	}

	public void setQueryStartTimeStart(Date queryStartTimeStart){
		this.queryStartTimeStart = queryStartTimeStart;
	}

	public Date getQueryStartTimeEnd(){
		return this.queryStartTimeEnd;
	}

	public void setQueryStartTimeEnd(Date queryStartTimeEnd){
		this.queryStartTimeEnd = queryStartTimeEnd;
	}

	public Date getQueryEndTimeStart(){
		return this.queryEndTimeStart;
	}

	public void setQueryEndTimeStart(Date queryEndTimeStart){
		this.queryEndTimeStart = queryEndTimeStart;
	}

	public Date getQueryEndTimeEnd(){
		return this.queryEndTimeEnd;
	}

	public void setQueryEndTimeEnd(Date queryEndTimeEnd){
		this.queryEndTimeEnd = queryEndTimeEnd;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

}
