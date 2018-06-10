package com.wzxx.tzgg.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.wzxx.tzgg.entity.Tzgg;
import com.wzxx.tzgg.service.TzggService;

public class TzggAction extends BaseAction
{
	private static final long serialVersionUID = 3967636502860318998L;

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Tzgg tzgg = new Tzgg();
	
	/**
	 * 业务类
	 */
	@Autowired
	private TzggService tzggService;
	
	@Autowired
	private UserService userService;
	
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
	
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	
	/**
	 * 从页面传递的信息内容
	 */
	private String infoContent;
	
	private User user;
	
	/*查询条件中的发布人*/
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}

	/**
	 * 执行查询的方法，返回json数据<br>
	 * json包含的属性："infoTitle|infoType|topFlag|expireFlag|delFlag|user|user.displayName|dept|dept.deptName|publicDate|time|id|"
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != tzgg){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != tzgg.getInfoTitle()) && (0 < tzgg.getInfoTitle().trim().length())){
				paraMap.put("infoTitle", "%" + tzgg.getInfoTitle().trim() + "%");
			}

			if ((null != this.getUsername()) && (0 < this.getUsername().trim().length())){
				paraMap.put("username", "%" + this.getUsername().trim() + "%");
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
		final String filter = "infoTitle|user|user.displayName|dept|dept.deptName|publicDate|time|id|createUserID|topTime|";
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
		pagination = tzggService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != tzgg)&&(null != tzgg.getId()))
		{
			tzgg = tzggService.getById(tzgg.getId());
			if(tzgg.getLinkId() != null && !"".equals(tzgg.getLinkId()))
			{
				Map map = new HashMap();
				map.put("linkId",tzgg.getLinkId());
				map.put("mkType", "wzInfo");
				map.put("picType","wzInfofj");
				picList = photoPicService.findPicPath(map);//获取执法文书材料
			}
			infoContent = tzgg.getInfoContent();
			user = this.getLoginUser();
		}
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		user=this.getLoginUser();
		if((null != tzgg)&&(null != tzgg.getId()))
		{
			tzgg = tzggService.getById(tzgg.getId());
			if(tzgg.getLinkId() == null || "".equals(tzgg.getLinkId()))
			{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					tzgg.setLinkId(linkId);
				}
				else
				{
					try {
						Map map = new HashMap();
						map.put("linkId",tzgg.getLinkId());
						map.put("mkType", "wzInfo");
						map.put("picType","wzInfofj");
						picList = photoPicService.findPicPath(map);//获取执法文书材料
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			infoContent = tzgg.getInfoContent();
		}else
		{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			tzgg.setLinkId(linkId);
		}
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
			try {
				if ("add".equalsIgnoreCase(this.flag)){
					Date d = new Date();
					tzgg.setUserId(this.getLoginUser().getId());
					tzgg.setDeptId(this.getLoginUserDepartment().getId());
					tzgg.setDelFlag("0");
					tzgg.setInfoContent(null);
					tzgg.setPublicDate(d);
					tzgg.setCreateTime(d);
					tzgg.setCreateUserID(getLoginUser().getId());
					tzgg.setReadNum(0);
					tzgg.setTopTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2001-01-01 00:00:01"));
					tzggService.save(tzgg);
					tzgg.setInfoContent(infoContent);
					tzggService.update(tzgg);
				}else{
					//更新公告
					Date d = new Date();
					tzgg.setPublicDate(d);
					tzgg.setInfoContent(infoContent);
				    tzggService.update(tzgg);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return RELOAD;
	
		
	}
	
    

	/**
	 * 删除信息,返回json{result:true/false}
	 */
	public String delete() throws Exception{
	    try{
			tzggService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 获取文件后缀字符串
	 */
	public String getFileSuffix(String fileName) {
		String filesuffix = null;
		StringTokenizer fx = new StringTokenizer(fileName, ". ");
		while (fx.hasMoreTokens()) {
			filesuffix = fx.nextToken();
		}
		return filesuffix;
	}
	
  	public boolean compare(String s1,String s2){
  		boolean a=false;
  		if(!s1.equals("")){
  		String ids1[]=s1.split(",");//S1表示部门ids拼接字符串，S2表示传入的字符串
  		for(int i=0;i<ids1.length;i++){
  			if(s2.contains(ids1[i])){
  				a=true;
  			}else{
  				a=false;
  				break;
  			}
  		}
  		}
  		return a;
  	}
  	
  	/**
	 * 置顶信息
	 * fq 2016-2-18
	 */
	public String tzggToTop() throws Exception{
	    try{
	    	tzgg=tzggService.getById(ids);
	    	tzgg.setTopTime(new Date());
	    	tzggService.update(tzgg);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	/**
	 * 取消置顶信息
	 * fq 2016-2-18
	 */
	public String tzggToTopCancel() throws Exception{
	    try{
	    	tzgg=tzggService.getById(ids);
	    	tzgg.setTopTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2001-01-01 00:00:01"));
	    	tzggService.update(tzgg);
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

	public Tzgg getTzgg(){
		return this.tzgg;
	}

	public void setTzgg(Tzgg tzgg){
		this.tzgg = tzgg;
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

	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
