package com.wzxx.aqwh.web;

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
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.wzxx.aqwh.entity.SafCul;
import com.wzxx.aqwh.service.SafCulService;
import com.wzxx.gzdt.entity.Gzdt;
import com.wzxx.gzdt.service.GzdtService;
import com.wzxx.tzgg.entity.Tzgg;
import com.wzxx.tzgg.service.TzggService;

public class SafCulAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private SafCul safCul = new SafCul();

	/**
	 * 业务类
	 */
	@Autowired
	private SafCulService safCulService;
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
	
	
	private Date queryPublicDateStart;

	private Date queryPublicDateEnd;
	
    private int pageNum=1;
	
	private int totalCount;
	
	private int totalPage;
	
	private List<SafCul> aqwhList=new ArrayList<SafCul>();
	
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();

	private List<Tzgg> tzggList = new ArrayList<Tzgg>();
	
	private List<Gzdt> gzdtList = new ArrayList<Gzdt>();
	
	private List<Map<String, Object>> wxhxpList = new ArrayList<Map<String, Object>>();//危险化学品
	
	
	public List<Tzgg> getTzggList() {
		return tzggList;
	}
	public void setTzggList(List<Tzgg> tzggList) {
		this.tzggList = tzggList;
	}
	public List<Gzdt> getGzdtList() {
		return gzdtList;
	}
	public void setGzdtList(List<Gzdt> gzdtList) {
		this.gzdtList = gzdtList;
	}
	public List<Map<String, Object>> getWxhxpList() {
		return wxhxpList;
	}
	public void setWxhxpList(List<Map<String, Object>> wxhxpList) {
		this.wxhxpList = wxhxpList;
	}
	@Autowired
	private TzggService tzggService;//通知公告
	@Autowired
	private GzdtService gzdtService;//通知公告
	@Autowired
	private HttpService httpService;
	/**
	 * 从页面传递的信息内容
	 */
	private String infoContent;
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != safCul){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != safCul.getInfoTitle()) && (0 < safCul.getInfoTitle().trim().length())){
				paraMap.put("infoTitle", "%" + safCul.getInfoTitle().trim() + "%");
			}

			if (null != queryPublicDateStart){
				paraMap.put("startPublicDate", queryPublicDateStart);
			}

			if (null != queryPublicDateEnd){
				paraMap.put("endPublicDate", queryPublicDateEnd);
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|publicDate|infoTitle|clickTime|";
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
		pagination = safCulService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != safCul)&&(null != safCul.getId()))
		{
			safCul = safCulService.getById(safCul.getId());
			if(safCul.getLinkId() == null || "".equals(safCul.getLinkId()))
			{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					safCul.setLinkId(linkId);
				}
				else
				{
					try {
						Map map = new HashMap();
						map.put("linkId",safCul.getLinkId());
						map.put("mkType", "wzInfo");
						map.put("picType","wzInfofj");
						picList = photoPicService.findPicPath(map);//获取执法文书材料
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		    infoContent = safCul.getInfoContent();
		
		}else{
			
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			safCul.setLinkId(linkId);
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
		Date d = new Date();
		safCul.setPublicDate(d);
		if ("add".equalsIgnoreCase(this.flag)){
			safCul.setDeptId(this.getLoginUserDepartmentId());
			safCul.setDelFlag(0);
			safCul.setInfoContent(infoContent);
			safCulService.save(safCul);
		}else{
			safCul.setInfoContent(infoContent);
			safCulService.update(safCul);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != safCul)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到safCul中去
				
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
			safCulService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	public String aqwhList()
	{
		aqwhList = safCulService.findAllInfo(null, pageNum, 10);
		totalCount = safCulService.findAllInfos(null);
		totalPage = totalCount/10;
		totalPage = totalCount%10==0?totalPage:(totalPage+1);
		return SUCCESS;
	}
	
	public String aqwhContent()
	{
		tzggList = tzggService.findAllInfo(null, 1, 7);
		for(Tzgg tzgg:tzggList)
		{
			
			String s = tzgg.getInfoTitle();
			if(s.length()>20)
			{
				s = s.substring(0,20) + "...";
			}
			tzgg.setInfoTitle(s);
		}
		gzdtList = gzdtService.findAllInfo(null, 1, 7);
		for(Gzdt gzdt:gzdtList)
		{
		
			String s = gzdt.getInfoTitle();
			if(s.length()>20)
			{
				s = s.substring(0,20) + "...";
			}
			gzdt.setInfoTitle(s);
		}
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sqlId", "getAwhgzListByPage");
		paraMap.put("start", 1);
		paraMap.put("limit", 7);
		paraMap.put("infoType", "4");
		wxhxpList=httpService.findListDataByMap(paraMap);
		safCul = safCulService.getById(safCul.getId());
		String content = safCul.getInfoContent();
		String sb = "";

		String[] s1 = content.split("style");

		for (int i = 0; i < s1.length; i++) {

			if (i==0) {

				sb = sb + s1[0];

				continue;

			}

			int ii = s1[i].indexOf(">", 0);

			if(ii != -1)
			{
				sb = sb + s1[i].substring(ii);
			}
			else
			{
				sb = sb + s1[i];
			}
		}
		
		safCul.setInfoContent(sb.toString());
		if(safCul.getLinkId() == null || "".equals(safCul.getLinkId()))
		{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				safCul.setLinkId(linkId);
			}
			else
			{
				try {
					Map map = new HashMap();
					map.put("linkId",safCul.getLinkId());
					map.put("mkType", "wzInfo");
					map.put("picType","wzInfofj");
					picList = photoPicService.findPicPath(map);//获取执法文书材料
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		int t=safCul.getClickTime();
		safCul.setClickTime(t+1);
		safCulService.update(safCul);
		return SUCCESS;
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

	public SafCul getSafCul(){
		return this.safCul;
	}

	public void setSafCul(SafCul safCul){
		this.safCul = safCul;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryPublicDateStart(){
		return this.queryPublicDateStart;
	}

	public void setQueryPublicDateStart(Date queryPublicDateStart){
		this.queryPublicDateStart = queryPublicDateStart;
	}

	public Date getQueryPublicDateEnd(){
		return this.queryPublicDateEnd;
	}

	public void setQueryPublicDateEnd(Date queryPublicDateEnd){
		this.queryPublicDateEnd = queryPublicDateEnd;
	}

	public String getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<SafCul> getAqwhList() {
		return aqwhList;
	}

	public void setAqwhList(List<SafCul> aqwhList) {
		this.aqwhList = aqwhList;
	}

	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	

}
