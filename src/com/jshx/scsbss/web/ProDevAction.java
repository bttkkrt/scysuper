package com.jshx.scsbss.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.Constants;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.sccjgl.service.WorkshopService;
import com.jshx.scsbss.entity.ProDev;
import com.jshx.scsbss.service.ProDevService;
import com.jshx.scxcgl.entity.ProductionManage;
import com.jshx.shjl.service.CheckRecordService;

public class ProDevAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private ProDev proDev = new ProDev();

	/**
	 * 业务类
	 */
	@Autowired
	private ProDevService proDevService;
	@Autowired
	private WorkshopService workshopService;
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private CheckRecordService checkRecordService;
	
	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryDeviceManufactureDateStart;

	private Date queryDeviceManufactureDateEnd;

	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	private HttpService httpService;
	
	private String loginUserId;
	private String roleName;
	
	private File userFile;
	private String message;
	
	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
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
		loginUserId = this.getLoginUser().getId();
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		roleName = "1";
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
		    
		if(null != proDev){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != proDev.getAreaId()) && (0 < proDev.getAreaId().trim().length())){
				paraMap.put("areaId",   proDev.getAreaId() );
			}

			if ((null != proDev.getCompanyName()) && (0 < proDev.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + proDev.getCompanyName().trim() + "%");
			}

			if ((null != proDev.getDeviceCode()) && (0 < proDev.getDeviceCode().trim().length())){
				paraMap.put("deviceCode", "%" + proDev.getDeviceCode().trim() + "%");
			}

			if ((null != proDev.getDeviceName()) && (0 < proDev.getDeviceName().trim().length())){
				paraMap.put("deviceName", "%" + proDev.getDeviceName().trim() + "%");
			}
			if ((null != proDev.getDeviceWorkshopName()) && (0 < proDev.getDeviceWorkshopName().trim().length())){
				paraMap.put("deviceWorkshopName", "%" + proDev.getDeviceWorkshopName().trim() + "%");
			}

			if ((null != proDev.getDeviceWorkshopId()) && (0 < proDev.getDeviceWorkshopId().trim().length())){
				paraMap.put("deviceWorkshopId",proDev.getDeviceWorkshopId().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
//codeMap.put("deviceWorkshopName","402880fc501c2be401501d18b73e0223");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|deviceWorkshopName|deviceName|deviceCode|";
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
		pagination = proDevService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != proDev)&&(null != proDev.getId())){
			proDev = proDevService.getById(proDev.getId());
			if(proDev.getLinkId() == null || "".equals(proDev.getLinkId())){
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				proDev.setLinkId(linkId);
			}else{
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("linkId",proDev.getLinkId());
				map.put("mkType", "scsbss");
				map.put("picType","cchgzfj");
				picList1 = photoPicService.findPicPath(map); 
				map.put("mkType", "scsbss");
				map.put("picType","sywhsmfj");
				picList2 = photoPicService.findPicPath(map); 
				map.put("mkType", "scsbss");
				map.put("picType","azjswjfj");
				picList3 = photoPicService.findPicPath(map); 
			}
		}else{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			proDev.setAreaId(enBaseInfo.getEnterprisePossession());
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("codeName", "企业属地");
			m.put("itemValue", enBaseInfo.getEnterprisePossession());
			proDev.setAreaName(codeService.findCodeValueByMap(m).getItemText());
			proDev.setCompanyId(enBaseInfo.getId());
			proDev.setCompanyName(enBaseInfo.getEnterpriseName());
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			proDev.setLinkId(linkId);
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
		
		//根据车间id查找车间名称，存到设备信息表
		if(null!=proDev.getDeviceWorkshopId()&&!"".equals(proDev.getDeviceWorkshopId())){
			proDev.setDeviceWorkshopName(workshopService.getById(proDev.getDeviceWorkshopId()).getWorkshopName());
		}
	
		if ("add".equalsIgnoreCase(this.flag)){
			proDev.setDeptId(this.getLoginUserDepartmentId());
			proDev.setDelFlag(0);
			proDevService.save(proDev);
		}else{
			proDevService.update(proDev);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != proDev)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到proDev中去
				
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
			proDevService.deleteWithFlag(ids);
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
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		proDev.setCompanyId(enBaseInfo.getId());
		return SUCCESS;
	}
	
	
	/**
	 * 导入生产设备设施
	 */
	public String importProDev(){
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
			List<ProDev> proDevList = new ArrayList<ProDev>();
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
					ProDev proDev = new ProDev();
					proDev.setAreaId(enBaseInfo.getEnterprisePossession());
					proDev.setAreaName(codeService.findCodeValueByMap(m).getItemText());
					proDev.setCompanyId(enBaseInfo.getId());
					proDev.setCompanyName(enBaseInfo.getEnterpriseName());
					proDev.setDeptId(this.getLoginUserDepartmentId());
					proDev.setDelFlag(0);
					proDev.setDeviceWorkshopId(ids);
					StringBuffer colError = null;
					if (cells[0]==null || cells[0].getContents().equals("")) {
						if(colError==null)
							colError = new StringBuffer();
						colError.append("<span style='color:red'>错误：设备名称输入有误，不能为空，请检查!</span><br>");
					}else{
						proDev.setDeviceName(cells[0].getContents().trim());
					}
					if (cells[1] != null){
						proDev.setDeviceUse(cells[1].getContents().trim());
					}
					if (cells[2] != null){
						proDev.setDeviceCode(cells[2].getContents().trim());
					}
					if (cells[3] != null){
						proDev.setDeviceType(cells[3].getContents().trim());
					}
					if (cells[4] != null){
						if(!"".equals(cells[4].getContents().trim())){
							try {
								if(cells[4].getType() == CellType.DATE)
								{
									 DateCell c00 = (DateCell)cells[4]; 
								     TimeZone tz = TimeZone.getTimeZone("GMT"); 
								     sdf.setTimeZone(tz) ; 
								     proDev.setDeviceManufactureDate(c00.getDate());
								}
					            else
								{
					            	proDev.setDeviceManufactureDate(sdf.parse(cells[4].getContents().trim()));
								}
								
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								if(colError==null)
									colError = new StringBuffer();
								colError.append("<span style='color:red'>错误：设备出厂日期格式错误，应为yyyy/MM/dd，请检查!</span><br>");
							}
						}
					}
					if (cells[5] != null){
						proDev.setDeviceProductCommpnay(cells[5].getContents().trim());
					}
					if (cells[6] != null){
						proDev.setDeviceProductCommpnay(cells[6].getContents().trim());
					}
					
					if(colError==null){
						proDevList.add(proDev);
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
				for(ProDev s:proDevList){
					proDevService.save(s);
				}
			} catch (Exception e) {
				workbook.close();
					message = "导入失败！";
				BasalException ex = new BasalException(BasalException.ERROR, Constants.SAVING_DB_ERROR, e.getCause());
				throw ex;
			}
			workbook.close();
			if(message.equals("") && proDevList.size() == 0)
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
		roleName = "1";
		User user=userService.findUserByLoginId("zdz1");
		setSessionAttribute("curr_user", user);
		String userId=user.getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			roleName = "0";
		}
		return SUCCESS;
	}
     

   public String scsbss(){
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
		    
		if(null != proDev){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != proDev.getAreaId()) && (0 < proDev.getAreaId().trim().length())){
				paraMap.put("areaId",   proDev.getAreaId() );
			}

			if ((null != proDev.getCompanyName()) && (0 < proDev.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + proDev.getCompanyName().trim() + "%");
			}

			if ((null != proDev.getDeviceCode()) && (0 < proDev.getDeviceCode().trim().length())){
				paraMap.put("deviceCode", "%" + proDev.getDeviceCode().trim() + "%");
			}

			if ((null != proDev.getDeviceName()) && (0 < proDev.getDeviceName().trim().length())){
				paraMap.put("deviceName", "%" + proDev.getDeviceName().trim() + "%");
			}
			if ((null != proDev.getDeviceWorkshopName()) && (0 < proDev.getDeviceWorkshopName().trim().length())){
				paraMap.put("deviceWorkshopName", "%" + proDev.getDeviceWorkshopName().trim() + "%");
			}

			if ((null != proDev.getDeviceWorkshopId()) && (0 < proDev.getDeviceWorkshopId().trim().length())){
				paraMap.put("deviceWorkshopId",proDev.getDeviceWorkshopId().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|deviceWorkshopName|deviceName|deviceCode|";
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
		pagination = proDevService.findByPage(pagination, paraMap);
		
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

	public ProDev getProDev(){
		return this.proDev;
	}

	public void setProDev(ProDev proDev){
		this.proDev = proDev;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryDeviceManufactureDateStart(){
		return this.queryDeviceManufactureDateStart;
	}

	public void setQueryDeviceManufactureDateStart(Date queryDeviceManufactureDateStart){
		this.queryDeviceManufactureDateStart = queryDeviceManufactureDateStart;
	}

	public Date getQueryDeviceManufactureDateEnd(){
		return this.queryDeviceManufactureDateEnd;
	}

	public void setQueryDeviceManufactureDateEnd(Date queryDeviceManufactureDateEnd){
		this.queryDeviceManufactureDateEnd = queryDeviceManufactureDateEnd;
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

	public List<PhotoPic> getPicList3() {
		return picList3;
	}

	public void setPicList3(List<PhotoPic> picList3) {
		this.picList3 = picList3;
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
