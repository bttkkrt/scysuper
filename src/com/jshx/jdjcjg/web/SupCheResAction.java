package com.jshx.jdjcjg.web;

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
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.jdjcjg.entity.SupCheRes;
import com.jshx.jdjcjg.service.SupCheResService;

public class SupCheResAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private SupCheRes supCheRes = new SupCheRes();

	/**
	 * 业务类
	 */
	@Autowired
	private SupCheResService supCheResService;
	
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private PhotoPicService photoPicService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryCheckTimeStart;

	private Date queryCheckTimeEnd;
	
	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();//检查文书
		
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();//检查图片

	
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
		    
		if(null != supCheRes){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != supCheRes.getAreaId()) && (0 < supCheRes.getAreaId().trim().length())){
				paraMap.put("areaId", supCheRes.getAreaId().trim() );
			}

			if ((null != supCheRes.getCompanyName()) && (0 < supCheRes.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + supCheRes.getCompanyName().trim() + "%");
			}

			if (null != queryCheckTimeStart){
				paraMap.put("startCheckTime", queryCheckTimeStart);
			}

			if (null != queryCheckTimeEnd){
				paraMap.put("endCheckTime", queryCheckTimeEnd);
			}
			if ((null != supCheRes.getCheckUsername()) && (0 < supCheRes.getCheckUsername().trim().length())){
				paraMap.put("checkUsername", "%" + supCheRes.getCheckUsername().trim() + "%");
			}

			if ((null != supCheRes.getCheckInstrumentNum()) && (0 < supCheRes.getCheckInstrumentNum().trim().length())){
				paraMap.put("checkInstrumentNum", "%" + supCheRes.getCheckInstrumentNum().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
	

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|checkTime|checkUsername|checkInstrumentNum|";
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
		pagination = supCheResService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != supCheRes)&&(null != supCheRes.getId()))
		{
			supCheRes = supCheResService.getById(supCheRes.getId());
				if(supCheRes.getLinkId() == null || "".equals(supCheRes.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						supCheRes.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",supCheRes.getLinkId());
							map.put("mkType", "jdjcjg1");
							map.put("picType","jdjcjgfj1");
							picList1 = photoPicService.findPicPath(map);
							map.put("mkType", "jdjcjg2");
							map.put("picType","jdjcjgfj2");
							picList2 = photoPicService.findPicPath(map);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					supCheRes.setLinkId(linkId);
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
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", supCheRes.getAreaId());
		supCheRes.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		if ("add".equalsIgnoreCase(this.flag)){
			supCheRes.setDeptId(this.getLoginUserDepartmentId());
			supCheRes.setDelFlag(0);
			Date d = new Date();
			supCheRes.setCheckTime(d);
			supCheResService.save(supCheRes);
		}else{
			supCheResService.update(supCheRes);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != supCheRes)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到supCheRes中去
				
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
			supCheResService.deleteWithFlag(ids);
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

	public SupCheRes getSupCheRes(){
		return this.supCheRes;
	}

	public void setSupCheRes(SupCheRes supCheRes){
		this.supCheRes = supCheRes;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryCheckTimeStart(){
		return this.queryCheckTimeStart;
	}

	public void setQueryCheckTimeStart(Date queryCheckTimeStart){
		this.queryCheckTimeStart = queryCheckTimeStart;
	}

	public Date getQueryCheckTimeEnd(){
		return this.queryCheckTimeEnd;
	}

	public void setQueryCheckTimeEnd(Date queryCheckTimeEnd){
		this.queryCheckTimeEnd = queryCheckTimeEnd;
	}

	public List<PhotoPic> getPicList1() {
		return picList1;
	}

	public void setPicList1(List<PhotoPic> picList1) {
		this.picList1 = picList1;
	}

	public List<PhotoPic> getPicList2() {
		return picList2;
	}

	public void setPicList2(List<PhotoPic> picList2) {
		this.picList2 = picList2;
	}

}
