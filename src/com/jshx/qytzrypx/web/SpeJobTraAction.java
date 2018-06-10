package com.jshx.qytzrypx.web;

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
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.qytzrypx.entity.SpeJobTra;
import com.jshx.qytzrypx.service.SpeJobTraService;
import com.jshx.tzzyry.entity.SpeJobPer;
import com.jshx.tzzyry.service.SpeJobPerService;

public class SpeJobTraAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private SpeJobTra speJobTra = new SpeJobTra();

	/**
	 * 业务类
	 */
	@Autowired
	private SpeJobTraService speJobTraService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryTrainingTimeStart;

	private Date queryTrainingTimeEnd;

	private Date queryTrainingTimeEndStart;

	private Date queryTrainingTimeEndEnd;
	@Autowired
	private HttpService httpService;
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private SpeJobPerService speJobPerService;

	private File userFile;
	private String message;
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;
	
	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	/**
	 * 初始化 用于判断审核角色
	 */
	private String roleName;
	public String init(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				setRoleName("0");
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
		    
		if(null != speJobTra){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != speJobTra.getAreaId()) && (0 < speJobTra.getAreaId().trim().length())){
				paraMap.put("areaId", speJobTra.getAreaId().trim() );
			}

			if (null != queryTrainingTimeStart){
				paraMap.put("startTrainingTime", queryTrainingTimeStart);
			}

			if (null != queryTrainingTimeEnd){
				paraMap.put("endTrainingTime", queryTrainingTimeEnd);
			}
			if (null != queryTrainingTimeEndStart){
				paraMap.put("startTrainingTimeEnd", queryTrainingTimeEndStart);
			}

			if (null != queryTrainingTimeEndEnd){
				paraMap.put("endTrainingTimeEnd", queryTrainingTimeEndEnd);
			}
			if ((null != speJobTra.getTrainingPersonName()) && (0 < speJobTra.getTrainingPersonName().trim().length())){
				paraMap.put("trainingPersonName", "%" + speJobTra.getTrainingPersonName().trim() + "%");
			}
			
			if ((null != speJobTra.getTrainingCardnum()) && (0 < speJobTra.getTrainingCardnum().trim().length())){
				paraMap.put("trainingCardnum", "%" + speJobTra.getTrainingCardnum().trim() + "%");
			}

			if ((null != speJobTra.getCompanyName()) && (0 < speJobTra.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + speJobTra.getCompanyName().trim() + "%");
			}
			if ((null != speJobTra.getCompanyId()) && (0 < speJobTra.getCompanyId().trim().length())){
				paraMap.put("companyId",  speJobTra.getCompanyId().trim()  );
			}


		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|trainingPersonName|trainingCardnum|trainingTime|trainingTimeEnd|";
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
		pagination = speJobTraService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != speJobTra)&&(null != speJobTra.getId()))
		{
			speJobTra = speJobTraService.getById(speJobTra.getId());
			if(speJobTra.getLinkId() == null || "".equals(speJobTra.getLinkId()))
			{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					speJobTra.setLinkId(linkId);
				}
				else
				{
					Map map = new HashMap();
					map.put("linkId",speJobTra.getLinkId());
					map.put("mkType", "qytzrypx");
					map.put("picType","pxfj");
				    picList = photoPicService.findPicPath(map);//获取执法文书材料
				}
			}
			else
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				speJobTra.setLinkId(linkId);
			}

		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		speJobTra.setCompanyId(enBaseInfo.getId());
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
		speJobTra.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		speJobTra.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		speJobTra.setCompanyId(enBaseInfo.getId());
		speJobTra.setCompanyName(enBaseInfo.getEnterpriseName());

		if ("add".equalsIgnoreCase(this.flag)){
			speJobTra.setDeptId(this.getLoginUserDepartmentId());
			speJobTra.setDelFlag(0);
			speJobTraService.save(speJobTra);
		}else{
			speJobTraService.update(speJobTra);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != speJobTra)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到speJobTra中去
				
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
			speJobTraService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	/**
	 * 导入初始化，需要传递参数
	 * fq 2016-3-4
	 */
	public String initImport(){
		return SUCCESS;
	}
	
	
	/**
	 * 导入特种作业人员培训
	 * fq 2016-3-4
	 */
	public String importSpeJobTra(){
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
			List<SpeJobTra> speJobTraList = new ArrayList<SpeJobTra>();
			for (int i = 1; i < row; i++) {
				
				try {
					Cell[] cells = new Cell[column];
					Cell[] cellsTmp = sheet.getRow(i);
					for (int j = 0; j < cellsTmp.length; j++) {
						cells[j] = cellsTmp[j];
					}
					
					if ((cells[0]==null ||cells[0].getContents().equals(""))&&(cells[1]==null ||cells[1].getContents().equals(""))&&(cells[2]==null ||cells[2].getContents().equals(""))&&(cells[3]==null ||cells[3].getContents().equals(""))&&(cells[4]==null ||cells[4].getContents().equals("")) ){
						workbook.close();
						break;
					}
					
					SpeJobTra sjt = new SpeJobTra();
					sjt.setAreaId(enBaseInfo.getEnterprisePossession());
					sjt.setAreaName(codeService.findCodeValueByMap(m).getItemText());
					sjt.setCompanyId(enBaseInfo.getId());
					sjt.setCompanyName(enBaseInfo.getEnterpriseName());
					sjt.setDeptId(this.getLoginUserDepartmentId());
					sjt.setDelFlag(0);
					
					StringBuffer colError = null;
					//姓名
					if (cells[0]==null || cells[0].getContents().equals("")) {
						if(colError==null)
							colError = new StringBuffer();
						colError.append("<span style='color:red'>错误：姓名输入有误，不能为空，请检查!</span><br>");
					}else{
						sjt.setTrainingPersonName(cells[0].getContents().trim());
					}
					//证书号码
					if (cells[1]==null ||cells[1].getContents().equals("")) {
						if(colError==null)
							colError = new StringBuffer();
						colError.append("<span style='color:red'>错误：证书号码输入有误，不能为空，请检查!</span><br>");
					}else{
						sjt.setTrainingCardnum(cells[1].getContents().trim());
					}
					//培训学时
					if (cells[2]==null || cells[2].getContents().equals("")) {
						if(colError==null)
							colError = new StringBuffer();
						colError.append("<span style='color:red'>错误：培训学时输入有误，不能为空，请检查!</span><br>");
					}else{
						sjt.setTrainingTeacheTime(cells[2].getContents().trim());
					}
					//培训开始时间
					if (cells[3]==null || cells[3].getContents().equals("")) {
						if(colError==null)
							colError = new StringBuffer();
						colError.append("<span style='color:red'>错误：培训开始时间输入有误，不能为空，请检查!</span><br>");
					}else{
						try {
							if(cells[3].getType() == CellType.DATE)
							{
								 DateCell c00 = (DateCell)cells[3]; 
							     TimeZone tz = TimeZone.getTimeZone("GMT"); 
							     sdf.setTimeZone(tz) ; 
							     sjt.setTrainingTime(c00.getDate());
							}
					        else
							{
					        	sjt.setTrainingTime(sdf.parse(cells[3].getContents().trim()));
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							if(colError==null)
								colError = new StringBuffer();
							colError.append("<span style='color:red'>错误：培训开始时间格式错误，应为yyyy/MM/dd，请检查!</span><br>");
						}
					}
					//培训结束时间
					if (cells[4]==null || cells[4].getContents().equals("")) {
						if(colError==null)
							colError = new StringBuffer();
						colError.append("<span style='color:red'>错误：培训结束时间输入有误，不能为空，请检查!</span><br>");
					}else{
						try {
							if(cells[4].getType() == CellType.DATE)
							{
								 DateCell c00 = (DateCell)cells[4]; 
							     TimeZone tz = TimeZone.getTimeZone("GMT"); 
							     sdf.setTimeZone(tz) ; 
							     sjt.setTrainingTimeEnd(c00.getDate());
							}
					        else
							{
					        	sjt.setTrainingTimeEnd(sdf.parse(cells[4].getContents().trim()));
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							if(colError==null)
								colError = new StringBuffer();
							colError.append("<span style='color:red'>错误：培训结束时间格式错误，应为yyyy/MM/dd，请检查!</span><br>");
						}
					}
					
					//证书发证日期
					if (cells[5] != null){
						if(!"".equals(cells[5].getContents().trim())){
							try {
								if(cells[5].getType() == CellType.DATE)
								{
									 DateCell c00 = (DateCell)cells[5]; 
								     TimeZone tz = TimeZone.getTimeZone("GMT"); 
								     sdf.setTimeZone(tz) ; 
								     sjt.setTrainingCardPickDate(c00.getDate());
								}
					            else
								{
					            	sjt.setTrainingCardPickDate(sdf.parse(cells[5].getContents().trim()));
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								if(colError==null)
									colError = new StringBuffer();
								colError.append("<span style='color:red'>错误：证书发证日期格式错误，应为yyyy/MM/dd，请检查!</span><br>");
							}
						}
					}
					
					//有效期
					if (cells[6] != null){
						if(!"".equals(cells[6].getContents().trim())){
							try {
								if(cells[6].getType() == CellType.DATE)
								{
									 DateCell c00 = (DateCell)cells[6]; 
								     TimeZone tz = TimeZone.getTimeZone("GMT"); 
								     sdf.setTimeZone(tz) ; 
								     sjt.setTrainingCardValidity(c00.getDate());
								}
					            else
								{
					            	sjt.setTrainingCardValidity(sdf.parse(cells[6].getContents().trim()));
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								if(colError==null)
									colError = new StringBuffer();
								colError.append("<span style='color:red'>错误：有效期格式错误，应为yyyy/MM/dd，请检查!</span><br>");
							}
						}
					}
					
					//培训单位
					if (cells[7] != null){
						sjt.setTrainingAddress(cells[7].getContents().trim());
					}
					
					//授课人
					if (cells[8] != null){
						sjt.setTrainingTeacher(cells[8].getContents().trim());
					}
					
					//发证单位
					if (cells[9] != null){
						sjt.setFzdw(cells[9].getContents().trim());
					}
					
					//培训内容
					if (cells[10] != null){
						sjt.setTrainingContent(cells[10].getContents().trim());
					}
					
					//备注
					if (cells[11] != null){
						sjt.setTrainingRemark(cells[11].getContents().trim());
					}
					
					
					if(colError==null){
						speJobTraList.add(sjt);
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
				for(SpeJobTra s:speJobTraList){
					speJobTraService.save(s);
				}
			} catch (Exception e) {
				workbook.close();
					message = "导入失败！";
				BasalException ex = new BasalException(BasalException.ERROR, Constants.SAVING_DB_ERROR, e.getCause());
				throw ex;
			}
			workbook.close();
			if(message.equals("") && speJobTraList.size() == 0)
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
		    
		if(null != speJobTra){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != speJobTra.getAreaId()) && (0 < speJobTra.getAreaId().trim().length())){
				paraMap.put("areaId", speJobTra.getAreaId().trim() );
			}

			if (null != queryTrainingTimeStart){
				paraMap.put("startTrainingTime", queryTrainingTimeStart);
			}

			if (null != queryTrainingTimeEnd){
				paraMap.put("endTrainingTime", queryTrainingTimeEnd);
			}
			if (null != queryTrainingTimeEndStart){
				paraMap.put("startTrainingTimeEnd", queryTrainingTimeEndStart);
			}

			if (null != queryTrainingTimeEndEnd){
				paraMap.put("endTrainingTimeEnd", queryTrainingTimeEndEnd);
			}
			if ((null != speJobTra.getTrainingPersonName()) && (0 < speJobTra.getTrainingPersonName().trim().length())){
				paraMap.put("trainingPersonName", "%" + speJobTra.getTrainingPersonName().trim() + "%");
			}
			
			if ((null != speJobTra.getTrainingCardnum()) && (0 < speJobTra.getTrainingCardnum().trim().length())){
				paraMap.put("trainingCardnum", "%" + speJobTra.getTrainingCardnum().trim() + "%");
			}

			if ((null != speJobTra.getCompanyName()) && (0 < speJobTra.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + speJobTra.getCompanyName().trim() + "%");
			}

		}
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称、姓名或证书号码".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|trainingPersonName|trainingCardnum|trainingTime|trainingTimeEnd|";
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
		pagination = speJobTraService.findByPage(pagination, paraMap);
		
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

	public SpeJobTra getSpeJobTra(){
		return this.speJobTra;
	}

	public void setSpeJobTra(SpeJobTra speJobTra){
		this.speJobTra = speJobTra;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryTrainingTimeStart(){
		return this.queryTrainingTimeStart;
	}

	public void setQueryTrainingTimeStart(Date queryTrainingTimeStart){
		this.queryTrainingTimeStart = queryTrainingTimeStart;
	}

	public Date getQueryTrainingTimeEnd(){
		return this.queryTrainingTimeEnd;
	}

	public void setQueryTrainingTimeEnd(Date queryTrainingTimeEnd){
		this.queryTrainingTimeEnd = queryTrainingTimeEnd;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
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

	public Date getQueryTrainingTimeEndStart() {
		return queryTrainingTimeEndStart;
	}

	public void setQueryTrainingTimeEndStart(Date queryTrainingTimeEndStart) {
		this.queryTrainingTimeEndStart = queryTrainingTimeEndStart;
	}

	public Date getQueryTrainingTimeEndEnd() {
		return queryTrainingTimeEndEnd;
	}

	public void setQueryTrainingTimeEndEnd(Date queryTrainingTimeEndEnd) {
		this.queryTrainingTimeEndEnd = queryTrainingTimeEndEnd;
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
