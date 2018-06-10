package com.wzxx.bgxz.web;

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
import com.wzxx.bgxz.entity.Bgxz;
import com.wzxx.bgxz.service.BgxzService;

public class BgxzAction extends BaseAction
{
	private static final long serialVersionUID = 3967636502860318998L;

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Bgxz bgxz = new Bgxz();
	
	/**
	 * 业务类
	 */
	@Autowired
	private BgxzService bgxzService;
	
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
	
	
	
	private User user;
	
	/*查询条件中的发布人*/
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	/**
	 * 执行查询的方法，返回json数据<br>
	 * json包含的属性："infoTitle|infoType|topFlag|expireFlag|delFlag|user|user.displayName|dept|dept.deptName|publicDate|time|id|"
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != bgxz){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != bgxz.getInfoTitle()) && (0 < bgxz.getInfoTitle().trim().length())){
				paraMap.put("infoTitle", "%" + bgxz.getInfoTitle().trim() + "%");
			}
			
			if ((null != bgxz.getBgzl()) && (0 < bgxz.getBgzl().trim().length())){
				paraMap.put("bgzl",  bgxz.getBgzl().trim() );
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
		final String filter = "infoTitle|publicDate|time|id|createUserID|bgzl|";
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
		
		pagination = bgxzService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
		
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != bgxz)&&(null != bgxz.getId()))
		{
			bgxz = bgxzService.getById(bgxz.getId());
			if(bgxz.getLinkId() != null && !"".equals(bgxz.getLinkId()))
			{
				Map map = new HashMap();
				map.put("linkId",bgxz.getLinkId());
				map.put("mkType", "bgxz");
				map.put("picType","bgxzfj");
				picList = photoPicService.findPicPath(map);//获取执法文书材料
			}
		  }
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		if((null != bgxz)&&(null != bgxz.getId()))
		{
			bgxz = bgxzService.getById(bgxz.getId());
			if(bgxz.getLinkId() == null || "".equals(bgxz.getLinkId()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				bgxz.setLinkId(linkId);
			}
			else
			{
				try {
					Map map = new HashMap();
					map.put("linkId",bgxz.getLinkId());
					map.put("mkType", "bgxz");
					map.put("picType","bgxzfj");
					picList = photoPicService.findPicPath(map);//获取执法文书材料
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		  }else
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				bgxz.setLinkId(linkId);
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
					bgxz.setUserId(this.getLoginUser().getId());
					bgxz.setDeptId(this.getLoginUserDepartment().getId());
					bgxz.setDelFlag("0");
					bgxz.setPublicDate(d);
					bgxz.setCreateTime(d);
					bgxz.setCreateUserID(getLoginUser().getId());
					bgxzService.save(bgxz);
				}else{
					//更新公告
					Date d = new Date();
					bgxz.setPublicDate(d);
					bgxzService.update(bgxz);
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
	    	bgxzService.deleteWithFlag(ids);
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

	public Bgxz getBgxz(){
		return this.bgxz;
	}

	public void setBgxz(Bgxz bgxz){
		this.bgxz = bgxz;
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


}
