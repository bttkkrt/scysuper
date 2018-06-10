package com.jshx.dsrcssbbl.web;

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
import com.jshx.module.form.service.AttachfileService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.dsrcssbbl.entity.PartyStateNote;
import com.jshx.dsrcssbbl.service.PartyStateNoteService;

public class PartyStateNoteAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private PartyStateNote partyStateNote = new PartyStateNote();

	/**
	 * 业务类
	 */
	@Autowired
	private PartyStateNoteService partyStateNoteService;

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
	public String init(){
		return SUCCESS;
	}
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != partyStateNote){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != partyStateNote.getStateDefense()) && (0 < partyStateNote.getStateDefense().trim().length())){
				paraMap.put("stateDefense", "%" + partyStateNote.getStateDefense().trim() + "%");
			}

			if ((null != partyStateNote.getRecorder()) && (0 < partyStateNote.getRecorder().trim().length())){
				paraMap.put("recorder", "%" + partyStateNote.getRecorder().trim() + "%");
			}

			if ((null != partyStateNote.getUndertaker()) && (0 < partyStateNote.getUndertaker().trim().length())){
				paraMap.put("undertaker", "%" + partyStateNote.getUndertaker().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("sex","402880fc501be96f01501c10f8470063");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|relatedId|startTime|endTime|stateDefense|sex|position|undertaker|recorder|";
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
		pagination = partyStateNoteService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != partyStateNote)&&(null != partyStateNote.getId()))
			partyStateNote = partyStateNoteService.getById(partyStateNote.getId());
		
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
			partyStateNote.setDeptId(this.getLoginUserDepartmentId());
			partyStateNote.setDelFlag(0);
			partyStateNoteService.save(partyStateNote);
		}else{
			partyStateNoteService.update(partyStateNote);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != partyStateNote)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到partyStateNote中去
				
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
			partyStateNoteService.deleteWithFlag(ids);
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

	public PartyStateNote getPartyStateNote(){
		return this.partyStateNote;
	}

	public void setPartyStateNote(PartyStateNote partyStateNote){
		this.partyStateNote = partyStateNote;
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

}
