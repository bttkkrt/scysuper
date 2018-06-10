package com.wzxx.gzdt.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.Constants;
import com.jshx.czgc.entity.OpePro;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.wzxx.gzdt.entity.Gzdt;
import com.wzxx.gzdt.service.GzdtService;

public class GzdtAction extends BaseAction
{
	private static final long serialVersionUID = 3967636502860318998L;

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Gzdt gzdt = new Gzdt();
	
	private File userFile;
	private String message;
	
	/**
	 * 业务类
	 */
	@Autowired
	private GzdtService gzdtService;
	
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
		    
		if(null != gzdt){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != gzdt.getInfoTitle()) && (0 < gzdt.getInfoTitle().trim().length())){
				paraMap.put("infoTitle", "%" + gzdt.getInfoTitle().trim() + "%");
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
		final String filter = "infoTitle|user|user.displayName|dept|dept.deptName|publicDate|time|id|createUserID|";
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
		
		pagination = gzdtService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
		
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != gzdt)&&(null != gzdt.getId()))
		{
			gzdt = gzdtService.getById(gzdt.getId());
			if(gzdt.getLinkId() != null && !"".equals(gzdt.getLinkId()))
			{
				Map map = new HashMap();
				map.put("linkId",gzdt.getLinkId());
				map.put("mkType", "wzInfo");
				map.put("picType","wzInfofj");
				picList = photoPicService.findPicPath(map);//获取执法文书材料
			}
			infoContent = gzdt.getInfoContent();
			user = this.getLoginUser();
		}
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		user=this.getLoginUser();
		if((null != gzdt)&&(null != gzdt.getId()))
		{
			gzdt = gzdtService.getById(gzdt.getId());
			if(gzdt.getLinkId() == null || "".equals(gzdt.getLinkId()))
			{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					gzdt.setLinkId(linkId);
				}
				else
				{
					try {
						Map map = new HashMap();
						map.put("linkId",gzdt.getLinkId());
						map.put("mkType", "wzInfo");
						map.put("picType","wzInfofj");
						picList = photoPicService.findPicPath(map);//获取执法文书材料
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			infoContent = gzdt.getInfoContent();
		  }else
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				gzdt.setLinkId(linkId);
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
					gzdt.setUserId(this.getLoginUser().getId());
					gzdt.setDeptId(this.getLoginUserDepartment().getId());
					gzdt.setDelFlag("0");
					gzdt.setInfoContent(null);
					gzdt.setPublicDate(d);
					gzdt.setCreateTime(d);
					gzdt.setCreateUserID(getLoginUser().getId());
					gzdt.setReadNum(0);
					gzdtService.save(gzdt);
					gzdt.setInfoContent(infoContent);
					gzdtService.update(gzdt);
				}else{
					//更新公告
					Date d = new Date();
					gzdt.setPublicDate(d);
					gzdt.setInfoContent(infoContent);
					gzdtService.update(gzdt);
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
	    	gzdtService.deleteWithFlag(ids);
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
	 * 导入初始化，需要传递参数
	 */
	public String initImport(){
	
		return SUCCESS;
	}
	
	/**
	 * 导入工作动态
	 */
	public String importGzdt(){
		StringBuffer errorInfo = new StringBuffer();
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(userFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Sheet sheet = workbook.getSheet(0);
		int column = sheet.getColumns();
		int row = sheet.getRows();
		List<Gzdt> gzdtlist=new ArrayList<Gzdt>();
		try {
			for (int i = 0; i < row; i++ ){
				Cell[] cells = new Cell[column];
				Cell[] cellsTmp = sheet.getRow(i);
				for (int j = 0; j < cellsTmp.length; j++) {
					cells[j] = cellsTmp[j];
				}
				if ((cells[0]==null ||cells[0].getContents().equals("")) ){
					workbook.close();
					break;
				}
				Gzdt gzdt = new Gzdt();
				Date d = new Date();
				gzdt.setUserId(this.getLoginUser().getId());
				gzdt.setDeptId(this.getLoginUserDepartment().getId());
				gzdt.setDelFlag("0");
				gzdt.setPublicDate(d);
				gzdt.setCreateTime(d);
				gzdt.setCreateUserID(getLoginUser().getId());
				gzdt.setReadNum(0);
				StringBuffer colError = null;
				
				if (cells[0]!=null) {
					
					gzdt.setInfoTitle(cells[0].getContents().trim());
				}
				if (cells.length > 1 && cells[1]!=null) {
					gzdt.setInfoContent(cells[1].getContents().trim());
				}
				if(colError==null){
					gzdtlist.add(gzdt);
					errorInfo.append("导入第").append(i).append("条记录成功！<br><br>");
				}else{
					errorInfo.append("导入第").append(i).append("条记录失败，错误信息如下：<br>");
					errorInfo.append(colError).append("<br>");
				}
				
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		message = errorInfo.toString();
		try {
			for(Gzdt s:gzdtlist){
				gzdtService.save(s);
			}
		} catch (Exception e) {
			workbook.close();
			BasalException ex = new BasalException(BasalException.ERROR, Constants.SAVING_DB_ERROR, e.getCause());
			throw ex;
		}
		workbook.close();
		if(message.contains("失败")){
			return ERROR;
		}else{
			message="";
			return SUCCESS;
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

	public Gzdt getGzdt(){
		return this.gzdt;
	}

	public void setGzdt(Gzdt gzdt){
		this.gzdt = gzdt;
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

	public File getUserFile() {
		return userFile;
	}

	public void setUserFile(File userFile) {
		this.userFile = userFile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
