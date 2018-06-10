package com.jshx.scxcgl.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts2.ServletActionContext;
import org.hibernate.LobHelper;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.jshx.bzzpx.entity.TeaLeaTra;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.Constants;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.sccjgl.entity.Workshop;
import com.jshx.scxcgl.entity.ProductionManage;
import com.jshx.scxcgl.service.ProductionManageService;

public class ProductionManageAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private ProductionManage productionManage = new ProductionManage();

	/**
	 * 业务类
	 */
	@Autowired
	private ProductionManageService productionManageService;
	
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private HttpService httpService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	
	private String roleName;
	
	private File userFile;
	private String message;
	
	private Date queryJobTimeStart;
	
	private Date queryJobTimeEnd;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();//作业许可证
	
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();//施工图片
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;
	
	
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
	
	public String init(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				roleName = "0";
				break;
			}
		}
		return SUCCESS;
	}
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != productionManage){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != productionManage.getAreaId()) && (0 < productionManage.getAreaId().trim().length())){
				paraMap.put("areaId",productionManage.getAreaId().trim() );
			}

			if ((null != productionManage.getPersonInCharge()) && (0 < productionManage.getPersonInCharge().trim().length())){
				paraMap.put("personInCharge", "%" + productionManage.getPersonInCharge().trim() + "%");
			}

			if ((null != productionManage.getPersonName()) && (0 < productionManage.getPersonName().trim().length())){
				paraMap.put("personName", "%" + productionManage.getPersonName().trim() + "%");
			}

			if ((null != productionManage.getCompanyName()) && (0 < productionManage.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + productionManage.getCompanyName().trim() + "%");
			}
			if (null != queryJobTimeStart){
				paraMap.put("startJobTime",queryJobTimeStart);
			}
			
			if (null != queryJobTimeEnd){
				paraMap.put("endJobTime",queryJobTimeEnd);
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|jobTime|personInCharge|";
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
		pagination = productionManageService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != productionManage)&&(null != productionManage.getId()))
		{
			productionManage = productionManageService.getById(productionManage.getId());
				if(productionManage.getLinkId() == null || "".equals(productionManage.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						productionManage.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",productionManage.getLinkId());
							map.put("mkType", "scxcgl1");
							map.put("picType","scxcglfj1");
							picList1 = photoPicService.findPicPath(map);
							map.put("mkType", "scxcgl2");
							map.put("picType","scxcglfj2");
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
					productionManage.setLinkId(linkId);
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
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		productionManage.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		productionManage.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		productionManage.setCompanyId(enBaseInfo.getId());
		productionManage.setCompanyName(enBaseInfo.getEnterpriseName());
		if ("add".equalsIgnoreCase(this.flag)){
			productionManage.setDeptId(this.getLoginUserDepartmentId());
			productionManage.setDelFlag(0);
			

			productionManageService.save(productionManage);
		}else{
			productionManageService.update(productionManage);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != productionManage)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到productionManage中去
				
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
			productionManageService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	/**
	 * 导入初始化，需要传递参数
	 */
	public String initImport(){
		return SUCCESS;
	}
	
	
	/**
	 * 导入生产现场管理培训
	 */
	public String importProductionManage(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		StringBuffer errorInfo = new StringBuffer();
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
			Workbook workbook = null;
			try {
				workbook = Workbook.getWorkbook(userFile);
			} catch (Exception e) {
				e.printStackTrace();
				message = "导入失败，请使用系统模板！";
			}
			Sheet sheet = workbook.getSheet(0);
			
			int column = sheet.getColumns();
			int row = sheet.getRows();
			List<ProductionManage> productionManageList = new ArrayList<ProductionManage>();
			for (int i = 1; i < row; i++) {
				
				try {
					Cell[] cells = new Cell[column];
					Cell[] cellsTmp = sheet.getRow(i);
					for (int j = 0; j < cellsTmp.length; j++) {
						cells[j] = cellsTmp[j];
					}
					
					if ((cells[0]==null ||cells[0].getContents().equals("")) ){
						workbook.close();
						break;
					}
					ProductionManage productionManage = new ProductionManage();
					productionManage.setAreaId(enBaseInfo.getEnterprisePossession());
					productionManage.setAreaName(codeService.findCodeValueByMap(m).getItemText());
					productionManage.setCompanyId(enBaseInfo.getId());
					productionManage.setCompanyName(enBaseInfo.getEnterpriseName());
					productionManage.setDeptId(this.getLoginUserDepartmentId());
					productionManage.setDelFlag(0);
					StringBuffer colError = null;
					if (cells[0]==null || cells[0].getContents().equals("")) {
						if(colError==null)
							colError = new StringBuffer();
						colError.append("<span style='color:red'>错误：项目负责人输入有误，不能为空，请检查!</span><br>");
					}else{
						productionManage.setPersonInCharge(cells[0].getContents().trim());
					}
					if (cells[1] != null){
						productionManage.setPersonName(cells[1].getContents().trim());
					}
					if (cells[2] != null){
						productionManage.setJobContent(cells[2].getContents().trim());
					}
					if (cells[3] != null){
						productionManage.setHazardAnalysiss(cells[3].getContents().trim());
					}
					if (cells[4] != null){
						productionManage.setSafeMeasures(cells[4].getContents().trim());
					}
					if (cells[5] != null){
						productionManage.setEmerMeasure(cells[5].getContents().trim());
					}
					if (cells[6] != null){
						String[] zylxs = cells[6].getContents().trim().split("-");
						String jobType = "";
						for(String zylx:zylxs)
						{
							Map codeMap = new HashMap();
							codeMap.put("codeName", "生产作业类型");
							codeMap.put("itemText", zylx);
							String s = codeService.findCodeValueByMap(codeMap).getItemValue();
							if(s != null && !"".equals(s))
							{
								jobType += s +",";
							}
						}
						if(jobType.length() != 0)
						{
							jobType = jobType.substring(0,jobType.length()-1);
						}
						productionManage.setJobType(jobType);
					}
					//作业日期
					if (cells[7] != null){
						if(!"".equals(cells[7].getContents().trim())){
							try {
								if(cells[7].getType() == CellType.DATE)
								{
									 DateCell c00 = (DateCell)cells[7]; 
								     TimeZone tz = TimeZone.getTimeZone("GMT"); 
								     sdf.setTimeZone(tz) ; 
								     productionManage.setJobTime(c00.getDate());
								}
					            else
								{
					            	productionManage.setJobTime(sdf.parse(cells[7].getContents().trim()));
								}
								
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								if(colError==null)
									colError = new StringBuffer();
								colError.append("<span style='color:red'>错误：作业时间格式错误，应为yyyy/MM/dd，请检查!</span><br>");
							}
						}
					}
					
					if(colError==null){
						productionManageList.add(productionManage);
						errorInfo.append("导入第").append(i).append("条记录成功！<br><br>");
					}else{
						errorInfo.append("导入第").append(i).append("条记录失败，错误信息如下：<br>");
						errorInfo.append(colError).append("<br>");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			message = errorInfo.toString();
			try {
				for(ProductionManage s:productionManageList){
					productionManageService.save(s);
				}
			} catch (Exception e) {
				workbook.close();
					message = "导入失败！";
				BasalException ex = new BasalException(BasalException.ERROR, Constants.SAVING_DB_ERROR, e.getCause());
				throw ex;
			}
			workbook.close();
			if(message.equals("") && productionManageList.size() == 0)
			{
				message = "导入失败，未读取到数据，请使用系统模板！";
			}
			if(message.contains("失败")){
				return ERROR;
			}else{
				message="";
				return SUCCESS;
			}
		
	}
	
	public String zwtInit(){
		
//		String url="http://172.25.127.9/services/authorization/validateredirect";
//    	Map<String,String> dataMap = new HashMap<String,String>();
//        dataMap.put("token", "2dbe362a536d4311931a7ea22b4b2095");
//        dataMap.put("appId", "1DBE552B23D440128A12BA5D6ECE72B2");
//        JSONObject j=HttpRequestUtils.httpPost(url,JSONObject.fromObject(dataMap));
//        if("200".equals(j.get("code").toString())){
//        	String loginName=j.get("username").toString();
//        	User u=userService.findUserByLoginId(loginName);
//			setSessionAttribute("curr_user", u);
//        	ids=u.getId();
//        	String deptCode =u.getDeptCode();
//    		if(httpService.judgeRoleCode(ids, "A17")){
//    			userType="1";
//    		}else if(httpService.judgeRoleCode(ids, "A12")){//此处修改为安监中队队员才可以上报整改信息 lj 2015-12-14
//    			userType= "2";
//    		}else if(httpService.judgeRoleCode(ids, "A11")){
//    			userType= "3";
//    		}else if(httpService.judgeRoleCode(ids, "A09")){
//    			userType= "4";
//    		}else if(httpService.judgeRoleCode(ids, "A02")){
//    			userType= "5";
//    		}else if(deptCode.startsWith("002")&&deptCode.length()==6&&!"002001".equals(deptCode)){
//    			userType= "6";
//    		}else{
//    			userType= "7";
//    		}
//        	return SUCCESS;
//        }else{
//        	return ERROR;
//        }
		User user=userService.findUserByLoginId("zdz1");
		setSessionAttribute("curr_user", user);
		String userId=user.getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			roleName = "0";
		}
		return SUCCESS;
	}

   public String scxcgl(){
	   
	   return SUCCESS;
   }
	
	
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != productionManage){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != productionManage.getAreaId()) && (0 < productionManage.getAreaId().trim().length())){
				paraMap.put("areaId",  productionManage.getAreaId().trim() );
			}

			if ((null != productionManage.getPersonInCharge()) && (0 < productionManage.getPersonInCharge().trim().length())){
				paraMap.put("personInCharge", "%" + productionManage.getPersonInCharge().trim() + "%");
			}

			if ((null != productionManage.getPersonName()) && (0 < productionManage.getPersonName().trim().length())){
				paraMap.put("personName", "%" + productionManage.getPersonName().trim() + "%");
			}

			if ((null != productionManage.getCompanyName()) && (0 < productionManage.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + productionManage.getCompanyName().trim() + "%");
			}
			if (null != queryJobTimeStart){
				paraMap.put("startJobTime",queryJobTimeStart);
			}
			
			if (null != queryJobTimeEnd){
				paraMap.put("endJobTime",queryJobTimeEnd);
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|jobTime|personInCharge|";
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
		pagination = productionManageService.findByPage(pagination, paraMap);
		
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

	public ProductionManage getProductionManage(){
		return this.productionManage;
	}

	public void setProductionManage(ProductionManage productionManage){
		this.productionManage = productionManage;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public Date getQueryJobTimeStart() {
		return queryJobTimeStart;
	}
	public void setQueryJobTimeStart(Date queryJobTimeStart) {
		this.queryJobTimeStart = queryJobTimeStart;
	}
	public Date getQueryJobTimeEnd() {
		return queryJobTimeEnd;
	}
	public void setQueryJobTimeEnd(Date queryJobTimeEnd) {
		this.queryJobTimeEnd = queryJobTimeEnd;
	}

	
       
    
}
