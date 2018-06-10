package com.jshx.czgc.web;

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
import com.jshx.sccjgl.entity.Workshop;
import com.jshx.sccjgl.service.WorkshopService;
import com.jshx.scxcgl.entity.ProductionManage;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.czgc.entity.OpePro;
import com.jshx.czgc.service.OpeProService;
import com.jshx.http.service.HttpService;

public class OpeProAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private OpePro opePro = new OpePro();

	/**
	 * 业务类
	 */
	@Autowired
	private OpeProService opeProService;
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private WorkshopService workshopService;
	@Autowired
	private HttpService httpService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	
	private String companyId;
	
	private String roleName;
	
	private File userFile;
	private String message;
	
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
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
		    
		if(null != opePro){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != opePro.getAreaId()) && (0 < opePro.getAreaId().trim().length())){
				paraMap.put("areaId", opePro.getAreaId().trim() );
			}

			if ((null != opePro.getCompanyName()) && (0 < opePro.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + opePro.getCompanyName().trim() + "%");
			}
			if ((null != opePro.getOperationPostname()) && (0 < opePro.getOperationPostname().trim().length())){
				paraMap.put("operationPostname", "%" + opePro.getOperationPostname().trim() + "%");
			}
			if ((null != opePro.getOperationWorkshopId()) && (0 < opePro.getOperationWorkshopId().trim().length())){
				paraMap.put("operationWorkshopId", "%" + opePro.getOperationWorkshopId().trim() + "%");
			}
			if ((null != opePro.getOperationWorkshopName()) && (0 < opePro.getOperationWorkshopName().trim().length())){
				paraMap.put("operationWorkshopName", "%" + opePro.getOperationWorkshopName().trim() + "%");
			}
			if ((null != opePro.getOperationCode()) && (0 < opePro.getOperationCode().trim().length())){
				paraMap.put("operationCode", "%" + opePro.getOperationCode().trim() + "%");
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|operationWorkshopName|operationPostname|operationCode|";
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
		pagination = opeProService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != opePro)&&(null != opePro.getId()))
		{
			opePro = opeProService.getById(opePro.getId());
				if(opePro.getLinkId() == null || "".equals(opePro.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						opePro.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",opePro.getLinkId());
							map.put("mkType", "czgc");
							map.put("picType","czgcfj");
							picList = photoPicService.findPicPath(map);//获取执法文书材料
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					opePro.setLinkId(linkId);
				}
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		Map map2 = new HashMap();
		map2.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map2);
		companyId=enBaseInfo.getId();
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
		opePro.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		opePro.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		opePro.setCompanyId(enBaseInfo.getId());
		opePro.setCompanyName(enBaseInfo.getEnterpriseName());
		
		//根据车间id查找车间名称，存到设备信息表
		if(null!=opePro.getOperationWorkshopId()&&!"".equals(opePro.getOperationWorkshopId())){
			opePro.setOperationWorkshopName(workshopService.getById(opePro.getOperationWorkshopId()).getWorkshopName());
		}
		if ("add".equalsIgnoreCase(this.flag)){
			opePro.setDeptId(this.getLoginUserDepartmentId());
			opePro.setDelFlag(0);
			opeProService.save(opePro);
		}else{
			opeProService.update(opePro);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != opePro)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到opePro中去
				
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
			opeProService.deleteWithFlag(ids);
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
		opePro.setCompanyId(enBaseInfo.getId());
		return SUCCESS;
	}
	
	/**
	 * 导入操作规程
	 */
	public String importOpePro(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		StringBuffer errorInfo = new StringBuffer();
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		Workshop workshop = workshopService.getById(ids);
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
			List<OpePro> opeproList = new ArrayList<OpePro>();
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
					
					OpePro opePro = new OpePro();
					opePro.setAreaId(enBaseInfo.getEnterprisePossession());
					opePro.setAreaName(codeService.findCodeValueByMap(m).getItemText());
					opePro.setCompanyId(enBaseInfo.getId());
					opePro.setCompanyName(enBaseInfo.getEnterpriseName());
					opePro.setDeptId(this.getLoginUserDepartmentId());
					opePro.setDelFlag(0);
					opePro.setOperationWorkshopId(ids);
					opePro.setOperationWorkshopName(workshop.getWorkshopName());
					StringBuffer colError = null;
					//岗位名称
					if (cells[0]==null || cells[0].getContents().equals("")) {
						if(colError==null)
							colError = new StringBuffer();
						colError.append("<span style='color:red'>错误：岗位名称输入有误，不能为空，请检查!</span><br>");
					}else{
						opePro.setOperationPostname(cells[0].getContents().trim());
					}
					//最大工作时间
					if (cells[1] != null){
						opePro.setOperationMostWorktime(cells[1].getContents().trim());
					}
					//岗位员工数
					if (cells[2] != null){
						opePro.setOperationPostCount(cells[2].getContents().trim());
					}
					//是否倒班
					if (cells[3] != null){
						if(!"".equals(cells[3].getContents().trim())){
							Map codeMap = new HashMap();
							codeMap.put("codeName", "是或否");
							codeMap.put("itemText", cells[3].getContents().trim());
							opePro.setOperationShiftsOrnot(null==codeService.findCodeValueByMap(codeMap).getItemValue()?"":codeService.findCodeValueByMap(codeMap).getItemValue());
						}
					}
					//倒班总人数
					if (cells[4] != null){
						opePro.setOperationShiftsPersons(cells[4].getContents().trim());
					}
					//起草人
					if (cells[5] != null){
						opePro.setOperationDraftPerson(cells[5].getContents().trim());
					}
					//批准人
					if (cells[6] != null){
						opePro.setOperationAuthorization(cells[6].getContents().trim());
					}
					//操作规程编号
					if (cells[7] != null){
						opePro.setOperationCode(cells[7].getContents().trim());
					}
					//有效时间
					if (cells[8] != null){
						if(!"".equals(cells[8].getContents().trim())){
							try {
								if(cells[8].getType() == CellType.DATE)
								{
									 DateCell c00 = (DateCell)cells[8]; 
								     TimeZone tz = TimeZone.getTimeZone("GMT"); 
								     sdf.setTimeZone(tz) ; 
								     opePro.setEffectiveDate(c00.getDate());
								}
					            else
								{
					            	opePro.setEffectiveDate(sdf.parse(cells[8].getContents().trim()));
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								if(colError==null)
									colError = new StringBuffer();
								colError.append("<span style='color:red'>错误：有效时间格式错误，应为yyyy/MM/dd，请检查!</span><br>");
							}
						}
					}
					if(colError==null){
						opeproList.add(opePro);
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
				for(OpePro s:opeproList){
					opeProService.save(s);
				}
			} catch (Exception e) {
				workbook.close();
					message = "导入失败！";
				BasalException ex = new BasalException(BasalException.ERROR, Constants.SAVING_DB_ERROR, e.getCause());
				throw ex;
			}
			workbook.close();
			if(message.equals("") && opeproList.size() == 0)
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


	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != opePro){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != opePro.getAreaId()) && (0 < opePro.getAreaId().trim().length())){
				paraMap.put("areaId", opePro.getAreaId().trim() );
			}

			if ((null != opePro.getCompanyName()) && (0 < opePro.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + opePro.getCompanyName().trim() + "%");
			}
			if ((null != opePro.getOperationPostname()) && (0 < opePro.getOperationPostname().trim().length())){
				paraMap.put("operationPostname", "%" + opePro.getOperationPostname().trim() + "%");
			}
			if ((null != opePro.getOperationWorkshopId()) && (0 < opePro.getOperationWorkshopId().trim().length())){
				paraMap.put("operationWorkshopId", "%" + opePro.getOperationWorkshopId().trim() + "%");
			}
			if ((null != opePro.getOperationWorkshopName()) && (0 < opePro.getOperationWorkshopName().trim().length())){
				paraMap.put("operationWorkshopName", "%" + opePro.getOperationWorkshopName().trim() + "%");
			}
			if ((null != opePro.getOperationCode()) && (0 < opePro.getOperationCode().trim().length())){
				paraMap.put("operationCode", "%" + opePro.getOperationCode().trim() + "%");
			}
		}
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称、车间名称或岗位名称".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|operationWorkshopName|operationPostname|operationCode|";
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
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = opeProService.findByPage(pagination, paraMap);
		
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
	
	public String czgc(){
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

	public OpePro getOpePro(){
		return this.opePro;
	}

	public void setOpePro(OpePro opePro){
		this.opePro = opePro;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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
