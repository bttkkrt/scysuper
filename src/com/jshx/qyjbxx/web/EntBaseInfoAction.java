package com.jshx.qyjbxx.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.axis.utils.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.CodeUtil;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.fastdfs.FileManager;
import com.jshx.fastdfs.FileManagerUtils;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.HttpRequestUtils;
import com.jshx.httpData.service.HttpDataService;
import com.jshx.jdjcjh.service.SupPlaService;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.entity.UserRole;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserRoleService;
import com.jshx.module.admin.service.UserService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.entity.HylxBean;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.qyjbxx.util.ClientAuthenticationHandler;
import com.jshx.qywghjdgl.entity.ComGriMan;
import com.jshx.qywghjdgl.service.ComGriManService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.lkdj.frk.FrjcPort;
import com.lkdj.frk.FrjcPortService;
import com.lkdj.frk.GetFRJCInsertRequest;
import com.lkdj.frk.GetFRJCInsertRequest.Frjc;
import com.lkdj.frk.GetFRJCInsertResponse;
import com.lkdj.kzk.GetTkzxxRequest;
import com.lkdj.kzk.GetTkzxxRequest.Tkzxxs;
import com.lkdj.kzk.GetTkzxxResponse;
import com.lkdj.kzk.TkzxxPort;
import com.lkdj.kzk.TkzxxPortService;
import com.lkdj.lkLog.entity.LkLog;
import com.lkdj.lkLog.service.LkLogService;
import com.lkdj.util.ceateFRJCUtil;
import com.lkdj.util.ceateTkzxxUtil;

public class EntBaseInfoAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private EntBaseInfo entBaseInfo = new EntBaseInfo();
	
	private ComGriMan comGriMan = new ComGriMan();

	/**
	 * 业务类
	 */
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private CheckRecordService checkRecordService;
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private ComGriManService comGriManService;
	@Autowired
	private LkLogService lkLogService;
	@Autowired
	private HttpDataService httpDataService;
	/**
	 * 审核记录
	 */
	private List<CheckRecord> checkRecords;
	private CheckRecord checkRecord=new CheckRecord();
	
	private List<PhotoPic> picList =  new ArrayList<PhotoPic>();
	//上传文件
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	private MultipartFile picture;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private String checkDeptId;//检查部门ID
	
	private String checkCompanyId;//检查企业ID
	
	private String planId;
	
	private String checkUserId;
	
	private String deptCode;
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private HttpService httpService;
	
	@Autowired
	private SupPlaService supPlaService;
	
	private String loginId;
	
	private String token;
	private String appId;
	
	private int pageNo;
	
	private int pageSize;
	
	private int total1=0;
	private int total2=0;
	
	private String entBaseInfoId;
	private String enterpriseCode;
	private String enterpriseName;
	private String password;
	
	private List<HylxBean> hylist = new ArrayList<HylxBean>();
	
	private User u=new User();
	public String init() throws Exception{
		deptCode = this.getLoginUser().getDeptCode();
		if(deptCode.startsWith("009"))
		{
			initEdit();
			return EDIT;
		}
		else
		{
			Map map=new HashMap();
			map.put("planId", planId);
			checkCompanyId=supPlaService.findCheckCompanyIds(map);
			loginId = "0";
			if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A29") || httpService.judgeRoleCode(this.getLoginUser().getId(), "A01")){
				loginId = "1";
			}
			deptCode = this.getLoginUser().getDeptCode();
			return SUCCESS;
		}
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("loginId", this.getLoginUser().getLoginId());
		}

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		if(null != entBaseInfo){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != entBaseInfo.getEnterpriseName()) && (0 < entBaseInfo.getEnterpriseName().trim().length())){
				paraMap.put("enterpriseName", "%" + entBaseInfo.getEnterpriseName().trim() + "%");
			}

			if ((null != entBaseInfo.getRegistrationNumber()) && (0 < entBaseInfo.getRegistrationNumber().trim().length())){
				paraMap.put("registrationNumber", "%" + entBaseInfo.getRegistrationNumber().trim() + "%");
			}

			if ((null != entBaseInfo.getEnterpriseCode()) && (0 < entBaseInfo.getEnterpriseCode().trim().length())){
				paraMap.put("enterpriseCode", "%" + entBaseInfo.getEnterpriseCode().trim() + "%");
			}
			
			if ((null != entBaseInfo.getEnterpriseLegalName()) && (0 < entBaseInfo.getEnterpriseLegalName().trim().length())){
				paraMap.put("enterpriseLegalName", "%" + entBaseInfo.getEnterpriseLegalName().trim() + "%");
			}
			
			if ((null != entBaseInfo.getEnterpriseNature()) && (0 < entBaseInfo.getEnterpriseNature().trim().length())){
				paraMap.put("enterpriseNature",entBaseInfo.getEnterpriseNature().trim() );
			}
			if ((null != entBaseInfo.getGridName()) && (0 < entBaseInfo.getGridName().trim().length())){
				paraMap.put("gridName", entBaseInfo.getGridName().trim() );
			}
			if ((null != entBaseInfo.getBasePass()) && (0 < entBaseInfo.getBasePass().trim().length())){
				paraMap.put("basePass",entBaseInfo.getBasePass().trim() );
			}
			if ((null != entBaseInfo.getEnterprisePossession()) && (0 < entBaseInfo.getEnterprisePossession().trim().length())){
				paraMap.put("enterprisePossession",entBaseInfo.getEnterprisePossession().trim() +"%");
			}
			if ((null != entBaseInfo.getGridManageteamCode()) && (0 < entBaseInfo.getGridManageteamCode().trim().length())){
				paraMap.put("gridManageteamCode",entBaseInfo.getGridManageteamCode().trim() );
			}
			
			
			if ((null != entBaseInfo.getEnterpriseCategory()) && (0 < entBaseInfo.getEnterpriseCategory().trim().length())){
				paraMap.put("enterpriseCategory",entBaseInfo.getEnterpriseCategory().trim() );
			}
			
			if ((null != entBaseInfo.getEnterpriseScale()) && (0 < entBaseInfo.getEnterpriseScale().trim().length())){
				paraMap.put("enterpriseScale",entBaseInfo.getEnterpriseScale().trim() );
			}
			
			if ((null != entBaseInfo.getEnterpriseType()) && (0 < entBaseInfo.getEnterpriseType().trim().length())){
				paraMap.put("enterpriseType","%"+entBaseInfo.getEnterpriseType().trim() +"%");
			}
			
			if (total1>0){
				paraMap.put("total1",total1);
			}
			if (total2>0){
				paraMap.put("total2",total2);
			}
			
			
			
			
			if ((null != entBaseInfo.getId()) && (0 < entBaseInfo.getId().trim().length())){
				String str = entBaseInfo.getId();//获取企业ids
				String a [] = str.split(",");
				ArrayList b = new ArrayList();
				for(int i=0;i<a.length;i++){
				b.add(a[i]);
				}
				paraMap.put("ids",b);
			}
			
			if(httpService.judgeRoleCode(userId, "A11")){//中队长
				Map<String, Object> paraMapEnt = new HashMap<String, Object>();
				paraMapEnt.put("sqlId","findCompanyIdsByZdzDeptCode");
				paraMapEnt.put("deptCode",this.getLoginUser().getDeptCode());
				List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
				String companmyIds="";
				for(int i=0;i< ents.size();i++){
					companmyIds+=ents.get(i).get("row_id")+",";
				}
				if("".equals(companmyIds)){
					companmyIds="0";
				}
				paraMap.put("companmyIds", companmyIds);
			}
			if(httpService.judgeRoleCode(userId, "A12") || httpService.judgeRoleCode(userId, "A24")){//中队队员或网格监管人员
				Map<String, Object> paraMapEnt = new HashMap<String, Object>();
				paraMapEnt.put("sqlId","findCompanyIdsByZddyId");
				paraMapEnt.put("userId",userId);
				List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
				String companmyIds="";
				for(int i=0;i< ents.size();i++){
					companmyIds+=ents.get(i).get("row_id")+",";
				}
				if("".equals(companmyIds)){
					companmyIds="0";
				}
				paraMap.put("companmyIds", companmyIds);
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("enterpriseNature", "40288008416c6c1a01416ca5177c003d");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|enterpriseName|registrationNumber|enterpriseCode|enterpriseLegalName|enterpriseNature|gridName|enterprisePossession|enterprisePossessionName|basePass|gridManageId|grid|ifCz|gridManageteamCode|";
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
		
		pagination = entBaseInfoService.findByPage(pagination, paraMap);
		
		List<EntBaseInfo> list = pagination.getListOfObject();
		for(EntBaseInfo e:list)
		{
			if(e.getGrid() != null && !"".equals(e.getGrid()))
			{
				ComGriMan comGriMan = comGriManService.getById(e.getGrid());
				e.setGridManageteamCode(comGriMan.getGridManageDept());
			}
		}
		
		convObjectToJson(pagination, config);
	}
	
	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if(!StringUtils.isEmpty(entBaseInfoId)){
			entBaseInfo = entBaseInfoService.getById(entBaseInfoId);
			//审核记录
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("infoId",entBaseInfoId);
			checkRecords=checkRecordService.findCheckRecord(paraMap);
			
			/*Map map = new HashMap();
			map.put("linkId",entBaseInfo.getLinkId());
			map.put("mkType", "qyxx");
			map.put("picType","cqtp");
			picList = photoPicService.findPicPath(map);//获取执法文书材料
			
			if(entBaseInfo.getGrid() != null && !"".equals(entBaseInfoId))
			{
				comGriMan = comGriManService.getById(entBaseInfo.getGrid());
			}*/
			getHylx();
		}
		/*if((null != entBaseInfo)&&(null != entBaseInfo.getId())){
			entBaseInfo = entBaseInfoService.getById(entBaseInfo.getId());
			//审核记录
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("infoId", entBaseInfo.getId());
			checkRecords=checkRecordService.findCheckRecord(paraMap);
			
			Map map = new HashMap();
			map.put("linkId",entBaseInfo.getLinkId());
			map.put("mkType", "qyxx");
			map.put("picType","cqtp");
			//picList = photoPicService.findPicPath(map);//获取执法文书材料
			
			if(entBaseInfo.getGrid() != null && !"".equals(entBaseInfo.getGrid()))
			{
				comGriMan = comGriManService.getById(entBaseInfo.getGrid());
			}
			getHylx();
		}*/
		
		return VIEW;
	}
	
	public String initView(){
		entBaseInfoId = entBaseInfo.getId();
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		Map map = new HashMap();
		if((null != entBaseInfoId)&&(!"".equals(entBaseInfoId))){
			entBaseInfo = entBaseInfoService.getById(entBaseInfoId);
		}else{
			map.put("loginId", this.getLoginUser().getLoginId());
			entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		}
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("infoId", entBaseInfo.getId());
		checkRecords=checkRecordService.findCheckRecord(paraMap);
		if(!StringUtils.isEmpty(entBaseInfo.getLinkId())){
			map.put("linkId",entBaseInfo.getLinkId());
			map.put("mkType", "qyxx");
			map.put("picType","cqtp");
			picList = photoPicService.findPicPath(map);//获取执法文书材料
		}
		
		if(entBaseInfo.getGrid() != null && !"".equals(entBaseInfo.getGrid()))
		{
			comGriMan = comGriManService.getById(entBaseInfo.getGrid());
		}
		getHylx();
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
	
//		Map m = new HashMap();
//		m.put("codeName", "企业属地");
//		m.put("itemValue", entBaseInfo.getEnterprisePossession());
//		entBaseInfo.setEnterprisePossessionName(codeService.findCodeValueByMap(m).getItemText());
        entBaseInfo.setBasePass("0");
        double rstotal = 0;
        if(entBaseInfo.getEnterpriseManagerCount() != null && !"".equals(entBaseInfo.getEnterpriseManagerCount()))
        {
        	rstotal += Double.valueOf(entBaseInfo.getEnterpriseManagerCount());
        }
        if(entBaseInfo.getEnterpriseWorkerCount() != null && !"".equals(entBaseInfo.getEnterpriseWorkerCount()))
        {
        	rstotal += Double.valueOf(entBaseInfo.getEnterpriseWorkerCount());
        }
        entBaseInfo.setEnterpriseStaffCount(rstotal+"");
        User user = this.getLoginUser();
        String userId = user.getId();
        if(StringUtils.isEmpty(entBaseInfo.getId())){
        	entBaseInfo.setDelFlag(0);
			entBaseInfo.setBasePass("0");
        	entBaseInfo.setLoginId(user.getLoginId());
        	entBaseInfo.setPassword(user.getPassword());
        	entBaseInfoService.save(entBaseInfo);
        }else{
        	entBaseInfoService.update(entBaseInfo);
        }
        String entType = entBaseInfo.getEnterpriseType();
        if(!StringUtils.isEmpty(entType)){
        	
        	// 删除用户原有角色
    		userService.delByUser(userId);
    		user = userService.findUserById(userId);
    		//默认添加企业角色
    		UserRight right = new UserRight();
			UserRole role = userRoleService.findRoleByCode("A23");
			right.setUser(user);
			right.setRole(role);
			userService.saveRight(right);
    		//获得企业类型对应的K-V
    		List<CodeValue> codeValueList = codeService.findCodeValueByCode("2c99fe8457ff40a90157ff4b36c5004c");
    		String [] entTypes = entType.split(",");
    		for (CodeValue codeValue : codeValueList) {
				if(entType.contains(codeValue.getItemValue())){
					// 添加角色
					String roleCode = "";
					switch (Integer.valueOf(codeValue.getItemValue())) {
					case 2:
						roleCode = "A19";//危化企业
						break;
					case 4:
						roleCode = "A26";//粉尘涉爆企业
						break;
					case 6:
						roleCode = "A40";//涉氨企业
						break;
					case 5:
						roleCode = "A41";//冶金企业
						break;
					case 8:
						roleCode = "A39";//受限空间
						break;

					default:
						break;
					}
					UserRight right2 = new UserRight();
					UserRole role2 = userRoleService.findRoleByCode(roleCode);
					right.setUser(user);
					right.setRole(role2);
					userService.saveRight(right2);
				}
			}
        }
//		URL restURL = new URL("http://58.210.9.131/ZHAJ/sphandler.ashx?paras=[{\"field\":\"_type\",\"value\":\"geonecomidjson\"},{\"field\":\"ComJson\",\"value\":\"" + URLEncoder.encode(entBaseInfo.getEnterpriseCode(), "utf-8") + "\"},{\"field\":\"QYMC\",\"value\":\"" + URLEncoder.encode(entBaseInfo.getEnterpriseName(), "utf-8") + "\"},{\"field\":\"ADDRESS\",\"value\":\"" + URLEncoder.encode(entBaseInfo.getFactoryAddress(), "utf-8") + "\"},{\"field\":\"Region\",\"value\":\"" + URLEncoder.encode(entBaseInfo.getEnterprisePossessionName(), "utf-8")+ "\"}]"); 
//		HttpURLConnection conn = (HttpURLConnection) restURL.openConnection(); 
//		conn.setRequestMethod("GET"); 
//		conn.setDoOutput(true); 
//		conn.setAllowUserInteraction(false); 
//		BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
//	    String line,resultStr=""; 
//	    while(null != (line=bReader.readLine())) 
//	    { 
//	        resultStr +=line; 
//	    } 
//	    bReader.close(); 
		return RELOAD;
	}
	
	public void entBaseInfoPicSave(){
		try {
			//获取图片  
	        //MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) getRequest();  
//	      根据前台name名称得到上传的文件  
			String filename = "";
  			String rename = "";
  			String id = "";
  			JSONObject jn = new JSONObject();
			if ((Filedata != null) && (!Filedata.isEmpty())) {
				//文件服务器上传文件
				File file = (File)Filedata.get(0);
				filename = (String)FiledataFileName.get(0);
				String fileId = FileManagerUtils.upload(file);
				rename = fileId + "?filename=" + filename;
				String linkId = "";
				if((null != entBaseInfo)&&(null != entBaseInfo.getId())){
					entBaseInfo = entBaseInfoService.getById(entBaseInfo.getId());
				}else{
					Map<String, String> map = new HashMap<String, String>();
					map.put("loginId", this.getLoginUser().getLoginId());
					entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
				}
				linkId = entBaseInfo.getLinkId();
				if(null == linkId || "".endsWith(linkId)){
					linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					entBaseInfo.setLinkId(linkId);
					entBaseInfoService.update(entBaseInfo);
				}
				PhotoPic taskPic = new PhotoPic();
				taskPic.setCreateTime(new Date());
				taskPic.setLinkId(linkId);
//			taskPic.setPicName(rename);
				taskPic.setHttpUrl(rename);
				taskPic.setDelFlag(0);
				taskPic.setMkType("qyxx");
				taskPic.setPicType("cqtp");
				taskPic.setFileName(FiledataFileName.get(0));//保存原文件的名称 李军 2013-07-19
				photoPicService.save(taskPic);//在此处调用图片的保存
				id = taskPic.getId();
				jn.put("docurl", rename);
				jn.put("filename", filename);
				jn.put("id", id);
				jn.put("result", 1);
			}else{
				jn.put("result", 0);
			}
			
			this.getResponse().getWriter().write(jn.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUploadFile(){
		Map<String ,Object> map = new HashMap<String, Object>();
		JSONObject jn = new JSONObject();
		if(!StringUtils.isEmpty(entBaseInfo.getLinkId())){
			map.put("linkId",entBaseInfo.getLinkId());
			map.put("mkType", "qyxx");
			map.put("picType","cqtp");
			picList = photoPicService.findPicPath(map);//获取执法文书材料
			jn.put("title", "厂区图片");
			jn.put("id", "123");
			jn.put("start", "0");
			List<JSONObject> list = new ArrayList<JSONObject>();
			for (PhotoPic pic : picList) {
				JSONObject data = new JSONObject();
				data.put("alt", pic.getFileName());
				data.put("pid", pic.getId());
				data.put("src", pic.getHttpUrl());
				data.put("thumb", pic.getFileName());
				list.add(data);
			}
			jn.put("data", list);
		}
		try {
			this.getResponse().getWriter().write(jn.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != entBaseInfo)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到entBaseInfo中去
				
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
	    	String[] idArray = ids.split("\\|");
			if(null != idArray)
			{
				for(String id : idArray)
				{
				    if(id!=null && !id.trim().equals(""))
				    {
				    	EntBaseInfo ent = entBaseInfoService.getById(id);
				    	ent.setDelFlag(1);
				    	entBaseInfoService.update(ent);
				    	User user = userService.findUserByLoginId(ent.getLoginId());
				    	userService.delByUser(user.getId());
	            		userService.delUser(user.getId());
				    }
				}
			}
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	/**
	 * 企业注册
	 * luting 2015-11-13
	 */
	public String register() throws Exception{
	    return EDIT;
	}
	
	/**
	 * 企业注册
	 * luting 2015-11-13
	 */
	public String nextRegister() throws Exception{
		if(enterpriseCode == null || "".equals(enterpriseCode))
		{
			return EDIT;
		}
		else
		{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			entBaseInfo.setLinkId(linkId);
			entBaseInfo.setDelFlag(0);
			entBaseInfo.setBasePass("0");
			entBaseInfo.setLoginId(loginId);
			entBaseInfo.setPassword(password);
			entBaseInfo.setEnterpriseCode(enterpriseCode);
			entBaseInfo.setEnterpriseName(enterpriseName);
			Map m = new HashMap();
			m.put("codeName", "行业类别");
			m.put("itemValue", entBaseInfo.getEnterpriseCategory());
			entBaseInfo.setEnterpriseCategoryName(codeService.findCodeValueByMap(m).getItemText());
			m.clear();
			m.put("codeName", "行业类别");
			m.put("itemValue", entBaseInfo.getJjlx());
			entBaseInfo.setJjlxname(codeService.findCodeValueByMap(m).getItemText());
			entBaseInfoService.save(entBaseInfo);
			
			User user=new User();
			user.setDelFlag(0);
			user.setDeptCode("009");
			user.setCssId("default");
			user.setDisplayName(entBaseInfo.getEnterpriseName().trim());
			user.setLoginId(entBaseInfo.getLoginId().trim());
			user.setPassword(CodeUtil.encode(entBaseInfo.getPassword().trim(), "MD5"));
			user.setSortSq(1);
			user.setDept(deptService.findDeptByDeptCode("009"));
			userService.saveUser(user);
			//关联权限
			UserRight right = new UserRight();
			right.setUser(user);
	        UserRole role = userRoleService.findRoleByCode("A23");
	        right.setRole(role);
	        //保存用户权限
	        userService.saveRight(right);
			/*Map map = new HashMap();
			if(entBaseInfo.getEnterpriseCode() != null && !"".equals(entBaseInfo.getEnterpriseCode()))
			{
				map.put("enterpriseCode", entBaseInfo.getEnterpriseCode());
			}
			EntBaseInfo ent = entBaseInfoService.findEntBaseInfoByMap(map);
			if(ent != null && ent.getId() != null && !"".equals(ent.getId()))
			{
				return EDIT;
			}
			else
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				entBaseInfo.setLinkId(linkId);
				getHylx();
				//根据组织机构代码获取法人信息 2015-12-17 luting
				//初始化信息获取
				WebServiceFactory web = new WebServiceFactory();
				String jsonStr = web.getFrxxInfoByOrgCode(entBaseInfo.getEnterpriseCode().trim());
				if(jsonStr != null && !"".equals(jsonStr))
				{
					entBaseInfo.setIfCz("1");
					StringReader read = new StringReader(jsonStr);
			        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			        InputSource source = new InputSource(read);
			        //创建一个新的SAXBuilder
			        SAXBuilder sb = new SAXBuilder();
			        //通过输入源构造一个Document
			        Document doc = sb.build(source);
		            try {
		            	XPath xpath1 =XPath.newInstance("/ns0:dataQuery1Table/ns0:企业名称");
					    xpath1.addNamespace("ns0","http://www.apusic.com/esb/DataQuery/xsd");
					    Element e1 = (Element)xpath1.selectSingleNode(doc);
						entBaseInfo.setEnterpriseName(e1.getText());//企业名称 
						XPath xpath2 =XPath.newInstance("/ns0:dataQuery1Table/ns0:企业注册号");
					    xpath2.addNamespace("ns0","http://www.apusic.com/esb/DataQuery/xsd");
					    Element e2 = (Element)xpath2.selectSingleNode(doc);
						entBaseInfo.setRegistrationNumber(e2.getText());//工商注册号
						XPath xpath3 =XPath.newInstance("/ns0:dataQuery1Table/ns0:注册地址");
					    xpath3.addNamespace("ns0","http://www.apusic.com/esb/DataQuery/xsd");
					    Element e3 = (Element)xpath3.selectSingleNode(doc);
						entBaseInfo.setEnterpriseAddress(e3.getText());//地址
						XPath xpath7 =XPath.newInstance("/ns0:dataQuery1Table/ns0:法定代表人");
					    xpath7.addNamespace("ns0","http://www.apusic.com/esb/DataQuery/xsd");
					    Element e7 = (Element)xpath7.selectSingleNode(doc);
						entBaseInfo.setEnterpriseLegalName(e7.getText());//法人姓名
						XPath xpath8 =XPath.newInstance("/ns0:dataQuery1Table/ns0:注册资本");
					    xpath8.addNamespace("ns0","http://www.apusic.com/esb/DataQuery/xsd");
					    Element e8 = (Element)xpath8.selectSingleNode(doc);
					    double a = 0;
					    if(e8.getText() != null && !"".equals(e8.getText()))
					    {
					    	a = Double.valueOf(e8.getText())/10000.0;
					    }
						entBaseInfo.setEnterpriseRegisterMoney(a+"");//注册资本
						XPath xpath9 =XPath.newInstance("/ns0:dataQuery1Table/ns0:经营范围");
					    xpath9.addNamespace("ns0","http://www.apusic.com/esb/DataQuery/xsd");
					    Element e9 = (Element)xpath9.selectSingleNode(doc);
						entBaseInfo.setEnterpriseScope(e9.getText());//注册资本
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return "edit21";
				}
				else
				{
					entBaseInfo.setIfCz("0");
					return "edit20";
				}
			}*/
		}
		return SUCCESS;
	}
	
	/**
	 * 保存企业注册信息（包括新增和修改） 
	 * luting 2015-11-16
	 */
	public String saveRegister() throws Exception{
	
			try {
				if(entBaseInfo!=null && entBaseInfo.getEnterpriseCode() != null && !"".equals(entBaseInfo.getEnterpriseCode()))
				{
					Map map = new HashMap();
					if(entBaseInfo.getEnterpriseCode() != null && !"".equals(entBaseInfo.getEnterpriseCode()))
					{
						map.put("enterpriseCode", entBaseInfo.getEnterpriseCode());
					}
					EntBaseInfo e = entBaseInfoService.findEntBaseInfoByMap(map);
					if(e != null && e.getId() != null && !"".equals(e.getId()))
					{
						
					}
					else
					{
						entBaseInfo.setDelFlag(0);
						entBaseInfo.setBasePass("0");
						Map m = new HashMap();
						m.put("codeName", "企业属地");
						m.put("itemValue", entBaseInfo.getEnterprisePossession());
						entBaseInfo.setEnterprisePossessionName(codeService.findCodeValueByMap(m).getItemText());
						
						
						URL restURL = new URL("http://58.210.9.131/ZHAJ/sphandler.ashx?paras=[{\"field\":\"_type\",\"value\":\"geonecomidjson\"},{\"field\":\"ComJson\",\"value\":\"" + URLEncoder.encode(entBaseInfo.getEnterpriseCode()!=null?entBaseInfo.getEnterpriseCode():"", "utf-8") + "\"},{\"field\":\"QYMC\",\"value\":\"" + URLEncoder.encode(entBaseInfo.getEnterpriseName()!=null?entBaseInfo.getEnterpriseName():"", "utf-8") + "\"},{\"field\":\"ADDRESS\",\"value\":\"" + URLEncoder.encode(entBaseInfo.getFactoryAddress()!=null?entBaseInfo.getFactoryAddress():"", "utf-8") + "\"},{\"field\":\"Region\",\"value\":\"" + URLEncoder.encode(entBaseInfo.getEnterprisePossessionName()!=null?entBaseInfo.getEnterprisePossessionName():"", "utf-8")+ "\"}]"); 
						HttpURLConnection conn = (HttpURLConnection) restURL.openConnection(); 
						conn.setRequestMethod("GET"); 
						conn.setDoOutput(true); 
						conn.setAllowUserInteraction(false); 
						BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
					    String line,resultStr=""; 
					    while(null != (line=bReader.readLine())) 
					    { 
					        resultStr +=line; 
					    } 
					    bReader.close(); 
					    
					    
					    URL restURL1 = new URL("http://58.210.9.131/ZHAJ/sphandler.ashx?paras=[{'field':'_type','value':'ComidJson'},{'field':'ComJson','value':'" + entBaseInfo.getEnterpriseCode() + "'}]"); 
						HttpURLConnection conn1 = (HttpURLConnection) restURL1.openConnection(); 
						conn1.setRequestMethod("GET"); 
						conn1.setDoOutput(true); 
						conn1.setAllowUserInteraction(false); 
						BufferedReader bReader1 = new BufferedReader(new InputStreamReader(conn1.getInputStream(),"UTF-8")); 
						String line1,resultStr1=""; 
						while(null != (line1=bReader1.readLine())) 
						{ 
						   resultStr1 +=line1 ; 
						} 
						bReader1.close(); 
						JSONArray ja = JSONArray.fromObject(resultStr1);
						JSONObject j0  = ja.getJSONObject(0);
						if(j0.getString("SHAPE") != null && !"[]".equals(j0.getString("SHAPE")))
						{
							JSONArray jb = JSONArray.fromObject(j0.get("SHAPE"));
							JSONObject j1  = jb.getJSONObject(0);
							String mapKey = j1.getString("Code");
							//根据mapkey值获取网格信息
					        if(mapKey != null && !"".equals(mapKey))
					        {
					        	m.put("mapKey", mapKey);
					        	ComGriMan cgm = comGriManService.getComGriManByMap(m);
					        	if(cgm.getId() != null)
					        	{
					        		entBaseInfo.setGrid(cgm.getId());
					        		entBaseInfo.setGridName(cgm.getGridName());
					        		entBaseInfo.setGridManageteamCode(cgm.getGridManageDept());
					        		entBaseInfo.setGridManageteamName(cgm.getGridManageDeptName());
					        	}
					        }
						}
				       
				        double rstotal = 0;
				        if(entBaseInfo.getEnterpriseManagerCount() != null && !"".equals(entBaseInfo.getEnterpriseManagerCount()))
				        {
				        	rstotal += Double.valueOf(entBaseInfo.getEnterpriseManagerCount());
				        }
				        if(entBaseInfo.getEnterpriseWorkerCount() != null && !"".equals(entBaseInfo.getEnterpriseWorkerCount()))
				        {
				        	rstotal += Double.valueOf(entBaseInfo.getEnterpriseWorkerCount());
				        }
				        entBaseInfo.setEnterpriseStaffCount(rstotal+"");
						entBaseInfoService.save(entBaseInfo);
						
						User user=new User();
						user.setDelFlag(0);
						user.setDeptCode("009");
						user.setCssId("default");
						user.setDisplayName(entBaseInfo.getEnterpriseName().trim());
						user.setLoginId(entBaseInfo.getLoginId().trim());
						user.setPassword(CodeUtil.encode(entBaseInfo.getPassword().trim(), "MD5"));
						user.setSortSq(1);
						user.setDept(deptService.findDeptByDeptCode("009"));
						userService.saveUser(user);
						//关联权限
						UserRight right = new UserRight();
						right.setUser(user);
				        UserRole role = userRoleService.findRoleByCode("A23");
				        right.setRole(role);
				        //保存用户权限
				        userService.saveRight(right);
					}
				}
		        this.getResponse().getWriter().println("{\"result\":true}");
			}catch(Exception e){
				this.getResponse().getWriter().println("{\"result\":false}");
			}
			return SUCCESS;
	}
	
	
	/**
	 * 初始审核信息
	 * fq 2015-11-6
	 */
	public String check() throws Exception{
		view();
	    return EDIT;
	}
	/**
	 * 保存审核信息 
	 * fq 2015-11-6
	 */
	public String checkSave() throws Exception{
		EntBaseInfo ent = entBaseInfoService.getById(entBaseInfo.getId());
		if(ent.getGrid() != null && !"".equals(ent.getGrid()))
		{
			if(checkRecord.getCheckResult().equals("审核通过"))
			{
				ent.setBasePass("1");
				entBaseInfoService.update(ent);
				
				User user = userService.findUserByLoginId(ent.getLoginId());
				
				user.setDisplayName(ent.getEnterpriseName().trim());
				
				userService.modify(user, null);
				
				try {
					userService.delByUser(user.getId());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				UserRight userRight=new UserRight();
				userRight.setUser(user);
				userRight.setRole(userRoleService.findRoleByCode("A23"));
				userService.saveRight(userRight);
				
				String enterpriseType = ent.getEnterpriseType();
				if(enterpriseType != null)
				{
					if(enterpriseType.contains("2")) //危化
			        {
						UserRight right1 = new UserRight();
						right1.setUser(user);
				        UserRole role1 = userRoleService.findRoleByCode("A19");
				        right1.setRole(role1);
				        //保存用户权限
				        userService.saveRight(right1);
			        }
			        if(enterpriseType.contains("3"))//职业卫生
			        {
			        	UserRight right2 = new UserRight();
						right2.setUser(user);
				        UserRole role2 = userRoleService.findRoleByCode("A20");
				        right2.setRole(role2);
				        //保存用户权限
				        userService.saveRight(right2);
			        }
			        if(enterpriseType.contains("4"))//粉尘涉爆
			        {
			        	UserRight right3 = new UserRight();
						right3.setUser(user);
				        UserRole role3 = userRoleService.findRoleByCode("A26");
				        right3.setRole(role3);
				        //保存用户权限
				        userService.saveRight(right3);
			        }
			        if(enterpriseType.contains("7"))//烟花爆竹
			        {
			        	UserRight right4 = new UserRight();
						right4.setUser(user);
				        UserRole role4 = userRoleService.findRoleByCode("A21");
				        right4.setRole(role4);
				        //保存用户权限
				        userService.saveRight(right4);
			        }
				}
		        
		        URL restURL = new URL("http://58.210.9.131/ZHAJ/sphandler.ashx?paras=[{\"field\":\"_type\",\"value\":\"geonecomidjson\"},{\"field\":\"ComJson\",\"value\":\"" + URLEncoder.encode(ent.getEnterpriseCode()!=null?ent.getEnterpriseCode():"", "utf-8") + "\"},{\"field\":\"QYMC\",\"value\":\"" + URLEncoder.encode(ent.getEnterpriseName()!=null?ent.getEnterpriseName():"", "utf-8") + "\"},{\"field\":\"ADDRESS\",\"value\":\"" + URLEncoder.encode(ent.getFactoryAddress()!=null?ent.getFactoryAddress():"", "utf-8") + "\"},{\"field\":\"Region\",\"value\":\"" + URLEncoder.encode(ent.getEnterprisePossessionName()!=null?ent.getEnterprisePossessionName():"", "utf-8")+ "\"}]"); 
				HttpURLConnection conn = (HttpURLConnection) restURL.openConnection(); 
				conn.setRequestMethod("GET"); 
				conn.setDoOutput(true); 
				conn.setAllowUserInteraction(false); 
				BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			    String line,resultStr=""; 
			    while(null != (line=bReader.readLine())) 
			    { 
			        resultStr +=line; 
			    } 
			    bReader.close(); 
				
				
		        try {
		        	String zjhm = NullToString(ent.getRegistrationNumber()).replaceAll(" ", "");
					if(zjhm != null && !"".equals(zjhm))
		        	{
						LkLog log1 = new LkLog();
						LkLog log2 = new LkLog();
						log1.setNrid(ent.getId());
						log1.setLb("企业基本信息");
						log2.setNrid(ent.getId());
						log2.setLb("企业扩展信息");
		        		//对接两库
			        	GetFRJCInsertRequest frqq = new GetFRJCInsertRequest();
						ceateFRJCUtil ceateFRJCUtil = new ceateFRJCUtil();
						Frjc frjc = ceateFRJCUtil.createFrjc(
								NullToString(zjhm),//工商注册号
								ssqy(ent.getEnterprisePossession()),//所属区域
								NullToString(ent.getEnterpriseLegalName()),//法定代表人姓名
								NullToString(ent.getEnterpriseScope()),//经营范围 
								"",//开业日期
								"",//地税登记日期
								"",//国税登记日期
								"",//工商登记日期
								"",//注册类型
								NullToString(ent.getEnterpriseAddress()),//注册地址
								NullToString(ent.getEnterpriseRegisterMoney()),//注册资金
								bz(ent.getEnterpriseRegisterMoneyDw()),//注册币种
								NullToString(ent.getFactoryAddress()),//实际经营地址
								"10",//企业状态
								 NullToString(ent.getEnterpriseLegalPhone()),//联系电话
								NullToString(ent.getEnterpriseZipcode()),//邮政编码
								"",//单位代码-社保公积金
								"",//纳税人识别号-国税
								"",//纳税人编码
								NullToString(ent.getEnterpriseCode()),//组织机构代码
								"",//机构类型
								NullToString(ent.getEnterpriseName()) ,//机构名称
								"",//企业性质
								NullToString(ent.getEnterpriseStaffCount())	,//企业人数
								NullToString(ent.getEnterpriseCategory()));//所属行业
		            	frqq.getFrjc().add(frjc);
		            	//法人扩展数据
		    			GetTkzxxRequest kzqq = new GetTkzxxRequest();
		    			ceateTkzxxUtil ceateTkzxxUtil = new ceateTkzxxUtil();
		    			JSONArray ja = new JSONArray();
						String enterpriseFoundDate = ent.getEnterpriseFoundDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(ent.getEnterpriseFoundDate()):"";
						String enterpriseProductDate = ent.getEnterpriseProductDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(ent.getEnterpriseProductDate()):"";
						
						ja.add((new JSONObject()).put("QYGM" , qygm(ent.getEnterpriseScale())));	//企业规模
						ja.add((new JSONObject()).put("QYFL" , qyfl(ent.getEnterpriseType())));	//企业分类
						ja.add((new JSONObject()).put("TZFGJ" , NullToString(ent.getEnterpriseNationnality())));	//投资方国籍
						ja.add((new JSONObject()).put("FRDBXB" , NullToString(ent.getEnterpriseLegalSex())));	//法人代表性别
						ja.add((new JSONObject()).put("FRDBNL" , NullToString(ent.getEnterpriseLegalAge())));	//法人代表年龄
						ja.add((new JSONObject()).put("FRTEPHONE" , NullToString(ent.getEnterpriseLegalPhone())));	//法人代表联系电话
						ja.add((new JSONObject()).put("FREMAIL" , NullToString(ent.getEnterpriseLegalCardnum())));	//法人代表电子邮箱
						ja.add((new JSONObject()).put("FRDBZW" , NullToString(ent.getEnterpriseLegalZw())));	//法人代表职务
						ja.add((new JSONObject()).put("QYSLRQ" , enterpriseFoundDate));	//企业设立日期
						ja.add((new JSONObject()).put("QYTCRQ" , enterpriseProductDate));	//企业投产日期
						ja.add((new JSONObject()).put("TZZE" , NullToString(ent.getEnterpriseInvestMoney())));	//投资总额
						ja.add((new JSONObject()).put("TZZEDW" , bz(ent.getEnterpriseInvestMoneyDw())));	//投资总额单位
						ja.add((new JSONObject()).put("GDZCZE" , NullToString(ent.getEnterpriseFixedassetMoney())));	//固定资产总额
						ja.add((new JSONObject()).put("GDZCZEDW" , bz(ent.getEnterpriseFixedassetMoneyDw())));	//固定资产总额单位
						ja.add((new JSONObject()).put("ZDMJ" , NullToString(ent.getEnterpriseFloorArea())));	//占地面积（m2）
						ja.add((new JSONObject()).put("BGLJZMJ" , NullToString(ent.getEnterpriseOfficeArea())));	//办公楼建筑面积（m2）
						ja.add((new JSONObject()).put("CJJZMJ" , NullToString(ent.getEnterpriseWorkshopArea())));	//车间厂房建筑面积（m2）
						ja.add((new JSONObject()).put("CKJZMJ" , NullToString(ent.getEnterpriseWearhouseArea())));	//仓库建筑面积（m2）
						ja.add((new JSONObject()).put("CFGS" , NullToString(ent.getEnterprisWorkshopOwn())));	//厂房归属
						ja.add((new JSONObject()).put("CZF" , NullToString(ent.getHouseOwner())));	//出租方
						ja.add((new JSONObject()).put("CZFTEPHONE" , NullToString(ent.getOwnerTel())));	//出租方联系方式
						ja.add((new JSONObject()).put("GLRS" , NullToString(ent.getEnterpriseManagerCount())));	//管理人员数
						ja.add((new JSONObject()).put("GRS" , NullToString(ent.getEnterpriseWorkerCount())));	//工人数
						JSONObject jo = new JSONObject();
						jo.put("frkzxx", ja.toString());
						String kzxx = jo.toString();
						Tkzxxs tkzxxs = ceateTkzxxUtil.ceateTkzxxs("qyjbxxb",ent.getCreateTime(), kzxx, "", zjhm);
						kzqq.getTkzxxs().add(tkzxxs);
					
						
						FrjcPortService frjcPortService = new FrjcPortService();
						frjcPortService.setHandlerResolver(new HandlerResolver() {
							
							@Override
							public List<Handler> getHandlerChain(PortInfo arg0) {
								List<Handler> handlerList = new ArrayList();
								handlerList.add(new ClientAuthenticationHandler(
										"Zhaj_seivice@APP-00199",
										"zhaj"));
								return handlerList;
							}
						});
						FrjcPort frjcPort = frjcPortService.getFrjcPortSoap11();
						GetFRJCInsertResponse getFRJCInsertResponse = frjcPort.getFRJCInsert(frqq);
						System.out.println("企业基本信息"+getFRJCInsertResponse.getMsg());
						log1.setResult(getFRJCInsertResponse.getMsg());
						lkLogService.save(log1);
						
						TkzxxPortService  tkzxxPortService= new TkzxxPortService();
						tkzxxPortService.setHandlerResolver(new HandlerResolver() {
							
							@Override
							public List<Handler> getHandlerChain(PortInfo arg0) {
								List<Handler> handlerList = new ArrayList();
								handlerList.add(new ClientAuthenticationHandler(
										"Zhaj_seivice@APP-00199",
										"zhaj"));
								return handlerList;
							}
						});
						TkzxxPort tkzxxPort = tkzxxPortService.getTkzxxPortSoap11();
						GetTkzxxResponse getTkzxxResponse = tkzxxPort.getTkzxx(kzqq);
						System.out.println("企业扩展信息"+getTkzxxResponse.getMsg());
						log2.setResult(getTkzxxResponse.getMsg());
						lkLogService.save(log2);
		        	}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else
			{
				ent.setBasePass("2");
				entBaseInfoService.update(ent);
			}
			checkRecord.setCheckUserid(this.getLoginUser().getId());
			checkRecord.setDelFlag(0);
			checkRecord.setCheckUsername(this.getLoginUser().getDisplayName());
			checkRecordService.save(checkRecord);
		}
		else
		{
			if(entBaseInfo.getGrid() != null && !"".equals(entBaseInfo.getGrid()))
			{
				ComGriMan cgm = comGriManService.getById(entBaseInfo.getGrid());
	        	if(cgm.getId() != null)
	        	{
	        		ent.setGrid(cgm.getId());
	        		ent.setGridName(cgm.getGridName());
	        		ent.setGridManageteamCode(cgm.getGridManageDept());
	        		ent.setGridManageteamName(cgm.getGridManageDeptName());
	        		entBaseInfoService.update(ent);
	        	}
			}
		}
	    return RELOAD;
	}
	
	
	/**
	 * 初始审核信息
	 * fq 2015-11-6
	 */
	public String initEdits() throws Exception{
		view();
	    return EDIT;
	}
	/**
	 * 保存审核信息 
	 * fq 2015-11-6
	 */
	public String saves() throws Exception{
		EntBaseInfo ent = entBaseInfoService.getById(entBaseInfo.getId());
		ComGriMan cgm = comGriManService.getById(entBaseInfo.getGrid());
    	if(cgm.getId() != null)
    	{
    		ent.setGrid(cgm.getId());
    		ent.setGridName(cgm.getGridName());
    		ent.setGridManageteamCode(cgm.getGridManageDept());
    		ent.setGridManageteamName(cgm.getGridManageDeptName());
    	}
    	if(entBaseInfo.getGridManageId() != null && !"".equals(entBaseInfo.getGridManageId()))
    	{
    		ent.setGridManageId(entBaseInfo.getGridManageId());
    		User user = userService.findUserById(entBaseInfo.getGridManageId());
    		ent.setGridManageName(user.getDisplayName());
    	}
		entBaseInfoService.update(ent);
	    return SUCCESS;
	}
	
	/**
	 * 判断注册信息是否存在
	 * @throws IOException
	 */
	public void isUserExit() throws IOException{
		Map<String,String> map = new HashMap<String,String>();
		boolean result = true;
		if(loginId != null && !"".equals(loginId)){
			if(userService.isReg(loginId)){
				result = false;
			}
		}else{
			if(enterpriseCode != null && !"".equals(enterpriseCode)){
				map.put("enterpriseCode", enterpriseCode);
			}
			EntBaseInfo entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			if(entBaseInfo!=null && entBaseInfo.getId() != null && !"".equals(entBaseInfo.getId()))
			{
				result = false;
			}
		}
		this.getResponse().getWriter().println("{\"valid\":"+result+"}");//注册信息已存在
		/*try {
			if(entBaseInfo!=null)
			{
				String orgCode = "";
				Map map = new HashMap();
				if(entBaseInfo.getLoginId() != null && !"".equals(entBaseInfo.getLoginId()))
				{
					if(userService.isReg(entBaseInfo.getLoginId()))
					{
						this.getResponse().getWriter().println("{\"result\":\"3\"}");//注册信息已存在
					}
					else
					{
						this.getResponse().getWriter().println("{\"result\":\"1\"}");//注册信息可以
					}
				}
				else
				{
					if(entBaseInfo.getEnterpriseName() != null && !"".equals(entBaseInfo.getEnterpriseName()))
					{
						map.put("enterpriseName", entBaseInfo.getEnterpriseName());
					}
					if(entBaseInfo.getRegistrationNumber() != null && !"".equals(entBaseInfo.getRegistrationNumber()))
					{
						map.put("registrationNumber", entBaseInfo.getRegistrationNumber());
					}
					if(entBaseInfo.getEnterpriseCode() != null && !"".equals(entBaseInfo.getEnterpriseCode()))
					{
						map.put("enterpriseCode", entBaseInfo.getEnterpriseCode());
						orgCode = entBaseInfo.getEnterpriseCode().trim();
					}
					entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
					if(entBaseInfo!=null && entBaseInfo.getId() != null && !"".equals(entBaseInfo.getId()))
					{
						this.getResponse().getWriter().println("{\"result\":\"3\"}");//注册信息已存在
					}
					else
					{
						this.getResponse().getWriter().println("{\"result\":\"1\"}");//注册信息可以
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.getResponse().getWriter().println("{\"result\":\"2\"}");//组织机构代码获取网格信息失败
		}*/
	}
	
	
	
	
	
	
	public String zwtInit(){
//		String url="http://172.25.127.9/services/authorization/validateredirect";
//    	Map<String,String> dataMap = new HashMap<String,String>();
//        dataMap.put("token", "2dbe362a536d4311931a7ea22b4b2095");
//        dataMap.put("appId", "1DBE552B23D440128A12BA5D6ECE72B2");
//        JSONObject j=HttpRequestUtils.httpPost(url,JSONObject.fromObject(dataMap));
//        if("200".equals(j.get("code").toString())){
//        	return SUCCESS;
//        }else{
//        	return ERROR;
//        }
        return SUCCESS;
	}
	
	public void zwtList(){
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		if(null != entBaseInfo){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != entBaseInfo.getEnterpriseName()) && (0 < entBaseInfo.getEnterpriseName().trim().length())){
				paraMap.put("enterpriseName", "%" + entBaseInfo.getEnterpriseName().trim() + "%");
			}

			if ((null != entBaseInfo.getRegistrationNumber()) && (0 < entBaseInfo.getRegistrationNumber().trim().length())){
				paraMap.put("registrationNumber", "%" + entBaseInfo.getRegistrationNumber().trim() + "%");
			}

			if ((null != entBaseInfo.getEnterpriseCode()) && (0 < entBaseInfo.getEnterpriseCode().trim().length())){
				paraMap.put("enterpriseCode", "%" + entBaseInfo.getEnterpriseCode().trim() + "%");
			}
			
			if ((null != entBaseInfo.getEnterpriseLegalName()) && (0 < entBaseInfo.getEnterpriseLegalName().trim().length())){
				paraMap.put("enterpriseLegalName", "%" + entBaseInfo.getEnterpriseLegalName().trim() + "%");
			}
			
			if ((null != entBaseInfo.getEnterpriseNature()) && (0 < entBaseInfo.getEnterpriseNature().trim().length())){
				paraMap.put("enterpriseNature",entBaseInfo.getEnterpriseNature().trim() );
			}
			if ((null != entBaseInfo.getGridName()) && (0 < entBaseInfo.getGridName().trim().length())){
				paraMap.put("gridName", entBaseInfo.getGridName().trim() );
			}
			if ((null != entBaseInfo.getBasePass()) && (0 < entBaseInfo.getBasePass().trim().length())){
				paraMap.put("basePass",entBaseInfo.getBasePass().trim() );
			}
			if ((null != entBaseInfo.getEnterprisePossession()) && (0 < entBaseInfo.getEnterprisePossession().trim().length())){
				paraMap.put("enterprisePossession",entBaseInfo.getEnterprisePossession().trim() );
			}
			if ((null != entBaseInfo.getGridManageteamCode()) && (0 < entBaseInfo.getGridManageteamCode().trim().length())){
				paraMap.put("gridManageteamCode",entBaseInfo.getGridManageteamCode().trim() );
			}
			if ((null != entBaseInfo.getId()) && (0 < entBaseInfo.getId().trim().length())){
				String str = entBaseInfo.getId();//获取企业ids
				String a [] = str.split(",");
				ArrayList b = new ArrayList();
				for(int i=0;i<a.length;i++){
				b.add(a[i]);
				}
				paraMap.put("ids",b);
			}
		}
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A11")){//中队长
			Map<String, Object> paraMapEnt = new HashMap<String, Object>();
			paraMapEnt.put("sqlId","findCompanyIdsByZdzDeptCode");
			paraMapEnt.put("deptCode",this.getLoginUser().getDeptCode());
			List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
			String companmyIds="";
			for(int i=0;i< ents.size();i++){
				companmyIds+=ents.get(i).get("row_id")+",";
			}
			if("".equals(companmyIds)){
				companmyIds="0";
			}
			paraMap.put("companmyIds", companmyIds);
		}
		if(httpService.judgeRoleCode(userId, "A12") || httpService.judgeRoleCode(userId, "A24")){//中队队员或网格监管人员
			Map<String, Object> paraMapEnt = new HashMap<String, Object>();
			paraMapEnt.put("sqlId","findCompanyIdsByZddyId");
			paraMapEnt.put("userId",userId);
			List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
			String companmyIds="";
			for(int i=0;i< ents.size();i++){
				companmyIds+=ents.get(i).get("row_id")+",";
			}
			if("".equals(companmyIds)){
				companmyIds="0";
			}
			paraMap.put("companmyIds", companmyIds);
		}
		
		
		if(!"搜索企业名称或组织机构代码".equals(ids)&&null!=ids&&!"".equals(ids)){
			paraMap.put("nameOrCode", "%" + ids+ "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("enterpriseNature", "40288008416c6c1a01416ca5177c003d");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|enterpriseName|registrationNumber|enterpriseCode|enterpriseLegalName|enterpriseNature|gridName|enterprisePossession|enterprisePossessionName|basePass|gridManageId|grid|ifCz|gridManageteamCode|";
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
		pagination = entBaseInfoService.findByPage(pagination, paraMap);
		JSONObject json=new JSONObject();
		JSONArray ja = JSONArray.fromObject(pagination.getListOfObject(),
				config);
		json.put("result", ja);
		json.put("count", pagination.getTotalCount());
		int totalPage=(pagination.getTotalCount()%pageSize==0?pagination.getTotalCount()/pageSize:(pagination.getTotalCount()/pageSize+1));
		json.put("totalPage", totalPage);
		json.put("pageNo", pageNo);
		try {
			this.getResponse().getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String zhcxTab(){
		if(entBaseInfo.getId() != null && !"".equals(entBaseInfo.getId()))
		{
			entBaseInfo = entBaseInfoService.getById(entBaseInfo.getId());
		}
		else if(entBaseInfo.getEnterpriseCode() != null && !"".equals(entBaseInfo.getEnterpriseCode()))
		{
			Map m = new HashMap();
			m.put("enterpriseCode", entBaseInfo.getEnterpriseCode());
			m.put("enterpriseName", entBaseInfo.getEnterpriseName());
			entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(m);
		}
		return SUCCESS;
	}
	
	
	/**
	 * 根据部门选择网格人员
	 * 费谦
	 * 2015-10-28
	 */
	public String deptChange() throws Exception{
		
		try{
			ComGriMan cgm = comGriManService.getById(entBaseInfo.getGrid());
			Department d=deptService.findDeptByDeptCode(cgm.getGridManageDept());
			List<User> users=d.getUsers();
			String select="<select id='gridManageId' style='width:50%' name='entBaseInfo.gridManageId'><option value='' >请选择</option>";
			for(User u:users){
				if(!httpService.judgeRoleCode(u.getId(), "A11")){
					select+="<option value='"+u.getId()+"' >"+u.getDisplayName()+"</option>";
				} 
			}
			select+="</select>";
			JSONObject json=new JSONObject();
			json.put("result", true);
			json.put("s", select);
			this.getResponse().getWriter().println(json.toString());
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public String zwtJcxx(){
		appId=SysPropertiesUtil.getProperty("zwtJcxxAppId");
		return tongyirenzheng();
	}
	
	public String zwtJcxxs(){
		u=this.getLoginUser();
		if(null==u){
			flag="请先登录！";
			return ERROR;
		}else{
			loginId = "0";
			if(httpService.judgeRoleCode(u.getId(), "A29") || httpService.judgeRoleCode(u.getId(), "A01")){
				loginId = "1";
			}
			deptCode = u.getDeptCode();
			return SUCCESS;
		}
	}
	
	public String zwtJdyw(){
		appId=SysPropertiesUtil.getProperty("zwtJdywAppId");
		return tongyirenzheng();
	}
	
	public String zwtJgxx(){
		appId=SysPropertiesUtil.getProperty("zwtJgxxAppId");;
		return tongyirenzheng();
	}
	
	public String zwtGgxx(){
		appId=SysPropertiesUtil.getProperty("zwtGgxxAppId");;
		return tongyirenzheng();
	}
	public String zwtCxtj(){
		appId=SysPropertiesUtil.getProperty("zwtCxtjAppId");;
		return tongyirenzheng();
	}
	
	
	
	
	public String tongyirenzheng(){
//		if(null!=this.getSessionAttribute("token")){
//			u=this.getLoginUser();
//			return SUCCESS;
//		}else{
			if(null!=token){
				String url="http://172.25.127.58:80/services/authorization/validateredirect";
				Map<String,String> dataMap = new HashMap<String,String>();
				dataMap.put("token", token);
				dataMap.put("appId", appId);
				System.out.println("token:"+token);
				System.out.println("appId:"+appId);
				JSONObject j=HttpRequestUtils.httpPost(url,JSONObject.fromObject(dataMap));
//				JSONObject j=new JSONObject();
//				j.put("code", "200");
//				j.put("error_description", "测试错误");
//				j.put("username", "zhccz");
				if(null!=j){
					if("200".equals(j.get("code").toString())){
						this.setSessionAttribute("token", token);
						this.setSessionAttribute("appId", appId);
						String loginName=j.get("username").toString();
						System.out.println(loginName);
						u=userService.findUserByLoginId(loginName);
						if(null==u){
							flag="该账号未在智慧安监系统中注册！";
							return ERROR;
						}else{
							loginId = "0";
							if(httpService.judgeRoleCode(u.getId(), "A29") || httpService.judgeRoleCode(u.getId(), "A01")){
								loginId = "1";
							}
							deptCode = u.getDeptCode();
							return SUCCESS;
						}
					}else{
						flag="错误代码："+j.get("code")+"，错误描述："+j.get("error_description");
						return ERROR;
					}
				}else{
					flag="连接失败！";
					return ERROR;
				}
			}else{ 
				flag="请先登录！";
				return ERROR;
//				u=userService.findUserByLoginId("zhangfei");
//				loginId = "0";
//				if(httpService.judgeRoleCode(u.getId(), "A29") || httpService.judgeRoleCode(u.getId(), "A01")){
//					loginId = "1";
//				}
//				deptCode = u.getDeptCode();
//				return SUCCESS;
			}
//		}
		
//		User user=userService.findUserByLoginId("jcdddz");
//		setSessionAttribute("curr_user", user);
//		String userId=user.getId();
//		if(httpService.judgeRoleCode(userId, "A02")){
//			roleName = "1";
//			setCanCheck(true);
//		}else if(httpService.judgeRoleCode(userId, "A23")){
//			roleName = "0";
//		}else{
//		}
//		return SUCCESS;
	}
	
	
	
	public String chajian1(){
		entBaseInfo = entBaseInfoService.getById(entBaseInfo.getId());
		return SUCCESS;
	}
	
	public String baseInfo(){
		try {
			view();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void getHylx()
	{
		try {
			JSONArray ja = new JSONArray();
			if(entBaseInfo.getEnterpriseCategory() != null)
			{
				if(entBaseInfo.getEnterpriseCategory().equals("A01"))
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "A011");
					jj1.put("name", "谷物种植");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "A012");
					jj2.put("name", "豆类、油料和薯类种植");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "A013");
					jj3.put("name", "棉、麻、糖、烟草种植");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "A014");
					jj4.put("name", "蔬菜、食用菌及园艺作物种植");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "A015");
					jj5.put("name", "水果种植");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "A016");
					jj6.put("name", "坚果、含油果、香料和饮料作物种植");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "A017");
					jj7.put("name", "中药材种植");
					ja.add(jj7);
					JSONObject jj8 = new JSONObject();
					jj8.put("id", "A019");
					jj8.put("name", "其他农业");
					ja.add(jj8);
				}
			
				
				else if(entBaseInfo.getEnterpriseCategory().equals("A02"))//林业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "A021");
					jj1.put("name", "林木育种和育苗");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "A022");
					jj2.put("name", "造林和更新");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "A023");
					jj3.put("name", "森林经营和管护");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "A024");
					jj4.put("name", "木材和竹材采运");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "A025");
					jj5.put("name", "林产品采集");
					ja.add(jj5);
				}
				
				
					
				else if(entBaseInfo.getEnterpriseCategory().equals("A03"))//畜牧业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "A031");
					jj1.put("name", "牲畜饲养");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "A032");
					jj2.put("name", "家禽饲养");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "A033");
					jj3.put("name", "狩猎和捕捉动物");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "A039");
					jj4.put("name", "其他畜牧业");
					ja.add(jj4);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("A04"))//渔业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "A041");
					jj1.put("name", "水产养殖");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "A042");
					jj2.put("name", "水产捕捞");
					ja.add(jj2);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("A05"))//农、林、牧、渔服务业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "A051");
					jj1.put("name", "农业服务业");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "A052");
					jj2.put("name", "林业服务业");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "A053");
					jj3.put("name", "畜牧服务业");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "A054");
					jj4.put("name", "渔业服务业");
					ja.add(jj4);
				}
					
					
					
				else if(entBaseInfo.getEnterpriseCategory().equals("B06"))//煤炭开采和洗选业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "B061");
					jj1.put("name", "烟煤和无烟煤开采洗选");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "B062");
					jj2.put("name", "褐煤开采洗选");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "B069");
					jj3.put("name", "其他煤炭采选");
					ja.add(jj3);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("B07"))//石油和天然气开采业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "B071");
					jj1.put("name", "石油开采");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "B072");
					jj2.put("name", "天然气开采");
					ja.add(jj2);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("B08"))//黑色金属矿采选业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "B081");
					jj1.put("name", "铁矿采选");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "B082");
					jj2.put("name", "锰矿、铬矿采选");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "B089");
					jj3.put("name", "其他黑色金属矿采选");
					ja.add(jj3);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("B09"))//有色金属矿采选业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "B091");
					jj1.put("name", "常用有色金属矿采选");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "B092");
					jj2.put("name", "贵金属矿采选");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "B093");
					jj3.put("name", "稀有稀土金属矿采选");
					ja.add(jj3);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("B10"))//非金属矿采选业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "B101");
					jj1.put("name", "土砂石开采");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "B102");
					jj2.put("name", "化学矿开采");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "B103");
					jj3.put("name", "采盐");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "B109");
					jj4.put("name", "石棉及其他非金属矿采选");
					ja.add(jj4);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("B11"))//开采辅助活动
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "B111");
					jj1.put("name", "煤炭开采和洗选辅助活动");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "B112");
					jj2.put("name", "石油和天然气开采辅助活动");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "B119");
					jj3.put("name", "其他开采辅助活动");
					ja.add(jj3);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("B12"))//其他采矿业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "B120");
					jj.put("name", "其他采矿业");
					ja.add(jj);
				}
					
							
							
				else if(entBaseInfo.getEnterpriseCategory().equals("C13"))//农副食品加工业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C131");
					jj1.put("name", "谷物磨制");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C132");
					jj2.put("name", "饲料加工");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C133");
					jj3.put("name", "植物油加工");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C134");
					jj4.put("name", "制糖业");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C135");
					jj5.put("name", "屠宰及肉类加工");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "C136");
					jj6.put("name", "水产品加工");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "C137");
					jj7.put("name", "蔬菜、水果和坚果加工");
					ja.add(jj7);
					JSONObject jj8 = new JSONObject();
					jj8.put("id", "C139");
					jj8.put("name", "其他农副食品加工");
					ja.add(jj8);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("C14"))//食品制造业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C141");
					jj1.put("name", "焙烤食品制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C142");
					jj2.put("name", "糖果、巧克力及蜜饯制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C143");
					jj3.put("name", "方便食品制造");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C144");
					jj4.put("name", "乳制品制造");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C145");
					jj5.put("name", "罐头食品制造");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "C146");
					jj6.put("name", "调味品、发酵制品制造");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "C149");
					jj7.put("name", "其他食品制造");
					ja.add(jj7);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("C15"))//酒、饮料和精制茶制造业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C151");
					jj1.put("name", "酒的制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C152");
					jj2.put("name", "饮料制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C153");
					jj3.put("name", "精制茶加工");
					ja.add(jj3);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("C16"))//烟草制品业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C161");
					jj1.put("name", "烟叶复烤");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C162");
					jj2.put("name", "卷烟制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C169");
					jj3.put("name", "其他烟草制品制造");
					ja.add(jj3);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("C17"))//纺织业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C171");
					jj1.put("name", "棉纺织及印染精加工");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C172");
					jj2.put("name", "毛纺织及染整精加工");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C173");
					jj3.put("name", "麻纺织及染整精加工");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C174");
					jj4.put("name", "丝绢纺织及印染精加工");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C175");
					jj5.put("name", "化纤织造及印染精加工");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "C176");
					jj6.put("name", "针织或钩针编织物及其制品制造");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "C177");
					jj7.put("name", "家用纺织制成品制造");
					ja.add(jj7);
					JSONObject jj8 = new JSONObject();
					jj8.put("id", "C178");
					jj8.put("name", "非家用纺织制成品制造");
					ja.add(jj8);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("C18"))//纺织服装、服饰业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C181");
					jj1.put("name", "机织服装制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C182");
					jj2.put("name", "针织或钩针编织服装制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C183");
					jj3.put("name", "服饰制造");
					ja.add(jj3);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("C19"))//皮革、毛皮、羽毛及其制品和制鞋业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C191");
					jj1.put("name", "皮革鞣制加工");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C192");
					jj2.put("name", "皮革制品制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C193");
					jj3.put("name", "毛皮鞣制及制品加工");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C194");
					jj4.put("name", "羽毛(绒)加工及制品制造");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C195");
					jj5.put("name", "制鞋业");
					ja.add(jj5);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("C20"))//木材加工和木、竹、藤、棕、草制品业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C201");
					jj1.put("name", "木材加工");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C202");
					jj2.put("name", "人造板制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C203");
					jj3.put("name", "木制品制造");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C204");
					jj4.put("name", "竹、藤、棕、草等制品制造");
					ja.add(jj4);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("C21"))//家具制造业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C211");
					jj1.put("name", "木质家具制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C212");
					jj2.put("name", "竹、藤家具制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C213");
					jj3.put("name", "金属家具制造");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C214");
					jj4.put("name", "塑料家具制造");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C219");
					jj5.put("name", "其他家具制造");
					ja.add(jj5);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("C22"))//造纸和纸制品业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C221");
					jj1.put("name", "纸浆制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C222");
					jj2.put("name", "造纸");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C223");
					jj3.put("name", "纸制品制造");
					ja.add(jj3);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("C23"))//印刷和记录媒介复制业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C231");
					jj1.put("name", "印刷");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C232");
					jj2.put("name", "装订及印刷相关服务");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C233");
					jj3.put("name", "记录媒介复制");
					ja.add(jj3);
				}
					
				else if(entBaseInfo.getEnterpriseCategory().equals("C24"))//			文教、工美、体育和娱乐用品制造业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C241");
					jj1.put("name", "文教办公用品制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C242");
					jj2.put("name", "乐器制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C243");
					jj3.put("name", "工艺美术品制造");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C244");
					jj4.put("name", "体育用品制造");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C245");
					jj5.put("name", "玩具制造");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "C246");
					jj6.put("name", "游艺器材及娱乐用品制造");
					ja.add(jj6);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C25"))//			石油加工、炼焦和核燃料加工业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C251");
					jj1.put("name", "精炼石油产品制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C252");
					jj2.put("name", "炼焦");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C253");
					jj3.put("name", "核燃料加工");
					ja.add(jj3);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C26"))//			化学原料和化学制品制造业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C261");
					jj1.put("name", "基础化学原料制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C262");
					jj2.put("name", "肥料制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C263");
					jj3.put("name", "农药制造");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C264");
					jj4.put("name", "涂料、油墨、颜料及类似产品制造");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C265");
					jj5.put("name", "合成材料制造");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "C266");
					jj6.put("name", "专用化学产品制造");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "C267");
					jj7.put("name", "炸药、火工及焰火产品制造");
					ja.add(jj7);
					JSONObject jj8 = new JSONObject();
					jj8.put("id", "C268");
					jj8.put("name", "日用化学产品制造");
					ja.add(jj8);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C27"))//			医药制造业
				{
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C271");
					jj1.put("name", "化学药品原料药制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C272");
					jj2.put("name", "化学药品制剂制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C273");
					jj3.put("name", "中药饮片加工");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C274");
					jj4.put("name", "中成药生产");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C275");
					jj5.put("name", "兽用药品制造");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "C276");
					jj6.put("name", "生物药品制造");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "C277");
					jj7.put("name", "卫生材料及医药用品制造");
					ja.add(jj7);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C28"))//			化学纤维制造业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "C281");
					jj.put("name", "纤维素纤维原料及纤维制造");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C282");
					jj1.put("name", "合成纤维制造");
					ja.add(jj1);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C29"))//			橡胶和塑料制品业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "C291");
					jj.put("name", "橡胶制品业");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C292");
					jj1.put("name", "塑料制品业");
					ja.add(jj1);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C30"))//			非金属矿物制品业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "C301");
					jj.put("name", "水泥、石灰和石膏制造");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C302");
					jj1.put("name", "石膏、水泥制品及类似制品制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C303");
					jj2.put("name", "砖瓦、石材等建筑材料制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C304");
					jj3.put("name", "玻璃制造");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C305");
					jj4.put("name", "玻璃制品制造");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C306");
					jj5.put("name", "玻璃纤维和玻璃纤维增强塑料制品制造");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "C307");
					jj6.put("name", "陶瓷制品制造");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "C308");
					jj7.put("name", "耐火材料制品制造");
					ja.add(jj7);
					JSONObject jj8 = new JSONObject();
					jj8.put("id", "C309");
					jj8.put("name", "石墨及其他非金属矿物制品制造");
					ja.add(jj8);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C31"))//			黑色金属冶炼和压延加工业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "C311");
					jj.put("name", "炼铁");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C312");
					jj1.put("name", "炼钢");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C313");
					jj2.put("name", "黑色金属铸造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C314");
					jj3.put("name", "钢压延加工");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C315");
					jj4.put("name", "铁合金冶炼");
					ja.add(jj4);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C32"))//			有色金属冶炼和压延加工业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "C321");
					jj.put("name", "常用有色金属冶炼");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C322");
					jj1.put("name", "贵金属冶炼");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C323");
					jj2.put("name", "稀有稀土金属冶炼");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C324");
					jj3.put("name", "有色金属合金制造");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C325");
					jj4.put("name", "有色金属铸造");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C326");
					jj5.put("name", "有色金属压延加工");
					ja.add(jj5);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C33"))//			金属制品业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "C331");
					jj.put("name", "结构性金属制品制造");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C332");
					jj1.put("name", "金属工具制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C333");
					jj2.put("name", "集装箱及金属包装容器制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C334");
					jj3.put("name", "金属丝绳及其制品制造");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C335");
					jj4.put("name", "建筑、安全用金属制品制造");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C336");
					jj5.put("name", "金属表面处理及热处理加工");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "C337");
					jj6.put("name", "搪瓷制品制造");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "C338");
					jj7.put("name", "金属制日用品制造");
					ja.add(jj7);
					JSONObject jj8 = new JSONObject();
					jj8.put("id", "C339");
					jj8.put("name", "其他金属制品制造");
					ja.add(jj8);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C34"))//			通用设备制造业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "C341");
					jj.put("name", "锅炉及原动设备制造");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C342");
					jj1.put("name", "金属加工机械制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C343");
					jj2.put("name", "物料搬运设备制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C344");
					jj3.put("name", "泵、阀门、压缩机及类似机械制造");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C345");
					jj4.put("name", "轴承、齿轮和传动部件制造");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C346");
					jj5.put("name", "烘炉、风机、衡器、包装等设备制造");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "C347");
					jj6.put("name", "文化、办公用机械制造");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "C348");
					jj7.put("name", "通用零部件制造");
					ja.add(jj7);
					JSONObject jj8 = new JSONObject();
					jj8.put("id", "C349");
					jj8.put("name", "其他通用设备制造业");
					ja.add(jj8);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C35"))//			专用设备制造业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "C351");
					jj.put("name", "采矿、冶金、建筑专用设备制造");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C352");
					jj1.put("name", "化工、木材、非金属加工专用设备制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C353");
					jj2.put("name", "食品、饮料、烟草及饲料生产专用设备制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();				
					jj3.put("id", "C354");
					jj3.put("name", "印刷、制药、日化及日用品生产专用设备制造");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C355");
					jj4.put("name", "纺织、服装和皮革加工专用设备制造");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C356");
					jj5.put("name", "电子和电工机械专用设备制造");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "C357");
					jj6.put("name", "农、林、牧、渔专用机械制造");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "C358");
					jj7.put("name", "医疗仪器设备及器械制造");
					ja.add(jj7);
					JSONObject jj8 = new JSONObject();
					jj8.put("id", "C359");
					jj8.put("name", "环保、社会公共服务及其他专用设备制造");
					ja.add(jj8);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C36"))//			汽车制造业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "C361");
					jj.put("name", "汽车整车制造");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C362");
					jj1.put("name", "改装汽车制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C363");
					jj2.put("name", "低速载货汽车制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C364");
					jj3.put("name", "电车制造");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C365");
					jj4.put("name", "汽车车身、挂车制造");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C366");
					jj5.put("name", "汽车零部件及配件制造");
					ja.add(jj5);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C37"))//			铁路、船舶、航空航天和其他运输设备制造业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "C371");
					jj.put("name", "铁路运输设备制造");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C372");
					jj1.put("name", "城市轨道交通设备制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C373");
					jj2.put("name", "船舶及相关装置制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C374");
					jj3.put("name", "航空、航天器及设备制造");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C375");
					jj4.put("name", "摩托车制造");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C376");
					jj5.put("name", "自行车制造");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "C377");
					jj6.put("name", "非公路休闲车及零配件制造");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "C379");
					jj7.put("name", "潜水救捞及其他未列明运输设备制造");
					ja.add(jj7);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C38"))//			电气机械和器材制造业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "C381");
					jj.put("name", "电机制造");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C382");
					jj1.put("name", "输配电及控制设备制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C383");
					jj2.put("name", "电线、电缆、光缆及电工器材制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C384");
					jj3.put("name", "电池制造");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C385");
					jj4.put("name", "家用电力器具制造");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C386");
					jj5.put("name", "非电力家用器具制造");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "C387");
					jj6.put("name", "照明器具制造");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "C389");
					jj7.put("name", "其他电气机械及器材制造");
					ja.add(jj7);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C39"))//			计算机、通信和其他电子设备制造业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "C391");
					jj.put("name", "计算机制造");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C392");
					jj1.put("name", "通信设备制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C393");
					jj2.put("name", "广播电视设备制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C394");
					jj3.put("name", "雷达及配套设备制造");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C395");
					jj4.put("name", "视听设备制造");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C396");
					jj5.put("name", "电子器件制造");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "C397");
					jj6.put("name", "电子元件制造");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "C399");
					jj7.put("name", "其他电子设备制造");
					ja.add(jj7);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C40"))//			仪器仪表制造业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "C401");
					jj.put("name", "通用仪器仪表制造");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C402");
					jj1.put("name", "专用仪器仪表制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C403");
					jj2.put("name", "钟表与计时仪器制造");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C404");
					jj3.put("name", "光学仪器及眼镜制造");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C409");
					jj4.put("name", "其他仪器仪表制造业");
					ja.add(jj4);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C41"))//			其他制造业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "C411");
					jj.put("name", "日用杂品制造");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C412");
					jj1.put("name", "煤制品制造");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C413");
					jj2.put("name", "核辐射加工");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C419");
					jj3.put("name", "其他未列明制造业");
					ja.add(jj3);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C42"))//			废弃资源综合利用业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "C421");
					jj.put("name", "金属废料和碎屑加工处理");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C422");
					jj1.put("name", "非金属废料和碎屑加工处理");
					ja.add(jj1);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("C43"))//			金属制品、机械和设备修理业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "C431");
					jj.put("name", "金属制品修理");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "C432");
					jj1.put("name", "通用设备修理");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "C433");
					jj2.put("name", "专用设备修理");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "C434");
					jj3.put("name", "铁路、船舶、航空航天等运输设备修理");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "C435");
					jj4.put("name", "电气设备修理");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "C436");
					jj5.put("name", "仪器仪表修理");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "C439");
					jj6.put("name", "其他机械和设备修理业");
					ja.add(jj6);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("D44"))//			电力、热力生产和供应业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "D441");
					jj.put("name", "电力生产");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "D442");
					jj1.put("name", "电力供应");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "D443");
					jj2.put("name", "热力生产和供应");
					ja.add(jj2);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("D45"))//			燃气生产和供应业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "D450");
					jj.put("name", "燃气生产和供应业");
					ja.add(jj);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("D46"))//			水的生产和供应业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "D461");
					jj.put("name", "自来水生产和供应");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "D462");
					jj1.put("name", "污水处理及其再生利用");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "D469");
					jj2.put("name", "其他水的处理、利用与分配");
					ja.add(jj2);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("E47"))//			房屋建筑业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "E470");
					jj.put("name", "房屋建筑业");
					ja.add(jj);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("E48"))//			土木工程建筑业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "E481");
					jj.put("name", "铁路、道路、隧道和桥梁工程建筑");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "E482");
					jj1.put("name", "水利和内河港口工程建筑");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "E483");
					jj2.put("name", "海洋工程建筑");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "E484");
					jj3.put("name", "工矿工程建筑");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "E485");
					jj4.put("name", "架线和管道工程建筑");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "E489");
					jj5.put("name", "其他土木工程建筑");
					ja.add(jj5);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("E49"))//			建筑安装业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "E491");
					jj.put("name", "电气安装");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "E492");
					jj1.put("name", "管道和设备安装");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "E499");
					jj2.put("name", "其他建筑安装业");
					ja.add(jj2);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("E50"))//			建筑装饰和其他建筑业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "E501");
					jj.put("name", "建筑装饰业");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "E502");
					jj1.put("name", "工程准备活动");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "E503");
					jj2.put("name", "提供施工设备服务");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "E509");
					jj3.put("name", "其他未列明建筑业");
					ja.add(jj3);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("F51"))//			批发业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "F511");
					jj.put("name", "农、林、牧产品批发");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "F512");
					jj1.put("name", "食品、饮料及烟草制品批发");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "F513");
					jj2.put("name", "纺织、服装及家庭用品批发");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "F514");
					jj3.put("name", "文化、体育用品及器材批发");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "F515");
					jj4.put("name", "医药及医疗器材批发");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "F516");
					jj5.put("name", "矿产品、建材及化工产品批发");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "F517");
					jj6.put("name", "机械设备、五金产品及电子产品批发");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "F518");
					jj7.put("name", "贸易经纪与代理");
					ja.add(jj7);
					JSONObject jj8 = new JSONObject();
					jj8.put("id", "F519");
					jj8.put("name", "其他批发业");
					ja.add(jj8);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("F52"))//			零售业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "F521");
					jj.put("name", "综合零售");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "F522");
					jj1.put("name", "食品、饮料及烟草制品专门零售");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "F523");
					jj2.put("name", "纺织、服装及日用品专门零售");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "F524");
					jj3.put("name", "文化、体育用品及器材专门零售");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "F525");
					jj4.put("name", "医药及医疗器材专门零售");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "F526");
					jj5.put("name", "汽车、摩托车、燃料及零配件专门零售");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "F527");
					jj6.put("name", "家用电器及电子产品专门零售");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "F528");
					jj7.put("name", "五金、家具及室内装饰材料专门零售");
					ja.add(jj7);
					JSONObject jj8 = new JSONObject();
					jj8.put("id", "F529");
					jj8.put("name", "货摊、无店铺及其他零售业");
					ja.add(jj8);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("G53"))//			铁路运输业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "G531");
					jj.put("name", "铁路旅客运输");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "G532");
					jj1.put("name", "铁路货物运输");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "G533");
					jj2.put("name", "铁路运输辅助活动");
					ja.add(jj2);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("G54"))//			道路运输业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "G541");
					jj.put("name", "城市公共交通运输");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "G542");
					jj1.put("name", "公路旅客运输");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "G543");
					jj2.put("name", "道路货物运输");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "G544");
					jj3.put("name", "道路运输辅助活动");
					ja.add(jj3);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("G55"))//			水上运输业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "G551");
					jj.put("name", "水上旅客运输");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "G552");
					jj1.put("name", "水上货物运输");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "G553");
					jj2.put("name", "水上运输辅助活动");
					ja.add(jj2);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("G56"))//			航空运输业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "G561");
					jj.put("name", "航空客货运输");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "G562");
					jj1.put("name", "通用航空服务");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "G563");
					jj2.put("name", "航空运输辅助活动");
					ja.add(jj2);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("G57"))//			管道运输业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "G570");
					jj.put("name", "管道运输业");
					ja.add(jj);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("G58"))//			装卸搬运和运输代理业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "G581");
					jj.put("name", "装卸搬运");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "G582");
					jj1.put("name", "运输代理业");
					ja.add(jj1);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("G59"))//			仓储业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "G591");
					jj.put("name", "谷物、棉花等农产品仓储");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "G599");
					jj1.put("name", "其他仓储业");
					ja.add(jj1);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("G60"))//			邮政业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "G601");
					jj.put("name", "邮政基本服务");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "G602");
					jj1.put("name", "快递服务");
					ja.add(jj1);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("H61"))//			住宿业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "H611");
					jj.put("name", "旅游饭店");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "H612");
					jj1.put("name", "一般旅馆");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "H619");
					jj2.put("name", "其他住宿业");
					ja.add(jj2);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("H62"))//			餐饮业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "H621");
					jj.put("name", "正餐服务");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "H622");
					jj1.put("name", "快餐服务");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "H623");
					jj2.put("name", "饮料及冷饮服务");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "H629");
					jj3.put("name", "其他餐饮业");
					ja.add(jj3);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("I63"))//			电信、广播电视和卫星传输服务
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "I631");
					jj.put("name", "电信");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "I632");
					jj1.put("name", "广播电视传输服务");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "I633");
					jj2.put("name", "卫星传输服务");
					ja.add(jj2);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("I64"))//			互联网和相关服务
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "I641");
					jj.put("name", "互联网接入及相关服务");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "I642");
					jj1.put("name", "互联网信息服务");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "I649");
					jj2.put("name", "其他互联网服务");
					ja.add(jj2);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("I65"))//			软件和信息技术服务业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "I651");
					jj.put("name", "软件开发");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "I652");
					jj1.put("name", "信息系统集成服务");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "I653");
					jj2.put("name", "信息技术咨询服务");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "I654");
					jj3.put("name", "数据处理和存储服务");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "I655");
					jj4.put("name", "集成电路设计");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "I659");
					jj5.put("name", "其他信息技术服务业");
					ja.add(jj5);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("J66"))//			货币金融服务
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "J661");
					jj.put("name", "中央银行服务");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "J662");
					jj1.put("name", "货币银行服务");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "J663");
					jj2.put("name", "非货币银行服务");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "J664");
					jj3.put("name", "银行监管服务");
					ja.add(jj3);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("J67"))//			资本市场服务
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "J671");
					jj.put("name", "证券市场服务");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "J672");
					jj1.put("name", "期货市场服务");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "J673");
					jj2.put("name", "证券期货监管服务");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "J674");
					jj3.put("name", "资本投资服务");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "J679");
					jj4.put("name", "其他资本市场服务");
					ja.add(jj4);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("J68"))//			保险业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "J681");
					jj.put("name", "人身保险");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "J682");
					jj1.put("name", "财产保险");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "J683");
					jj2.put("name", "再保险");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "J684");
					jj3.put("name", "养老金");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "J685");
					jj4.put("name", "保险经纪与代理服务");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "J686");
					jj5.put("name", "保险监管服务");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "J689");
					jj6.put("name", "其他保险活动");
					ja.add(jj6);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("J69"))//			其他金融业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "J691");
					jj.put("name", "金融信托与管理服务");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "J692");
					jj1.put("name", "控股公司服务");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "J693");
					jj2.put("name", "非金融机构支付服务");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "J694");
					jj3.put("name", "金融信息服务");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "J699");
					jj4.put("name", "其他未列明金融业");
					ja.add(jj4);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("K70"))//			房地产业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "K701");
					jj.put("name", "房地产开发经营");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "K702");
					jj1.put("name", "物业管理");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "K703");
					jj2.put("name", "房地产中介服务");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "K704");
					jj3.put("name", "自有房地产经营活动");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "K709");
					jj4.put("name", "其他房地产业");
					ja.add(jj4);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("L71"))//			租赁业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "L711");
					jj.put("name", "机械设备租赁");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "L712");
					jj1.put("name", "文化及日用品出租");
					ja.add(jj1);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("L72"))//			商务服务业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "L721");
					jj.put("name", "企业管理服务");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "L722");
					jj1.put("name", "法律服务");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "L723");
					jj2.put("name", "咨询与调查");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "L724");
					jj3.put("name", "广告业");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "L725");
					jj4.put("name", "知识产权服务");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "L726");
					jj5.put("name", "人力资源服务");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "L727");
					jj6.put("name", "旅行社及相关服务");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "L728");
					jj7.put("name", "安全保护服务");
					ja.add(jj7);
					JSONObject jj8 = new JSONObject();
					jj8.put("id", "L729");
					jj8.put("name", "其他商务服务业");
					ja.add(jj8);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("M73"))//			研究和试验发展
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "M731");
					jj.put("name", "自然科学研究和试验发展");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "M732");
					jj1.put("name", "工程和技术研究和试验发展");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "M733");
					jj2.put("name", "农业科学研究和试验发展");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "M734");
					jj3.put("name", "医学研究和试验发展");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "M735");
					jj4.put("name", "社会人文科学研究");
					ja.add(jj4);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("M74"))//			专业技术服务业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "M741");
					jj.put("name", "气象服务");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "M742");
					jj1.put("name", "地震服务");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "M743");
					jj2.put("name", "海洋服务");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "M744");
					jj3.put("name", "测绘服务");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "M745");
					jj4.put("name", "质检技术服务");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "M746");
					jj5.put("name", "环境与生态监测");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "M747");
					jj6.put("name", "地质勘查");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "M748");
					jj7.put("name", "工程技术");
					ja.add(jj7);
					JSONObject jj8 = new JSONObject();
					jj8.put("id", "M749");
					jj8.put("name", "其他专业技术服务业");
					ja.add(jj8);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("M75"))//			科技推广和应用服务业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "M751");
					jj.put("name", "技术推广服务");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "M752");
					jj1.put("name", "科技中介服务");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "M759");
					jj2.put("name", "其他科技推广和应用服务业");
					ja.add(jj2);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("N76"))//			水利管理业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "N761");
					jj.put("name", "防洪除涝设施管理");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "N762");
					jj1.put("name", "水资源管理");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "N763");
					jj2.put("name", "天然水收集与分配");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "N764");
					jj3.put("name", "水文服务");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "N769");
					jj4.put("name", "其他水利管理业");
					ja.add(jj4);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("N77"))//			生态保护和环境治理业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "N771");
					jj.put("name", "生态保护");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "N772");
					jj1.put("name", "环境治理业");
					ja.add(jj1);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("N78"))//			公共设施管理业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "N781");
					jj.put("name", "市政设施管理");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "N782");
					jj1.put("name", "环境卫生管理");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "N783");
					jj2.put("name", "城乡市容管理");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "N784");
					jj3.put("name", "绿化管理");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "N785");
					jj4.put("name", "公园和游览景区管理");
					ja.add(jj4);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("O79"))//			居民服务业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "O791");
					jj.put("name", "家庭服务");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "O792");
					jj1.put("name", "托儿所服务");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "O793");
					jj2.put("name", "洗染服务");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "O794");
					jj3.put("name", "理发及美容服务");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "O795");
					jj4.put("name", "洗浴服务");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "O796");
					jj5.put("name", "保健服务");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "O797");
					jj6.put("name", "婚姻服务");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "O798");
					jj7.put("name", "殡葬服务");
					ja.add(jj7);
					JSONObject jj8 = new JSONObject();
					jj8.put("id", "O799");
					jj8.put("name", "其他居民服务业");
					ja.add(jj8);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("O80"))//			机动车、电子产品和日用产品修理业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "O801");
					jj.put("name", "汽车、摩托车修理与维护");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "O802");
					jj1.put("name", "计算机和办公设备维修");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "O803");
					jj2.put("name", "家用电器修理");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "O809");
					jj3.put("name", "其他日用产品修理业");
					ja.add(jj3);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("O81"))//			其他服务业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "O811");
					jj.put("name", "清洁服务");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "O819");
					jj1.put("name", "其他未列明服务业");
					ja.add(jj1);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("P82"))//			教育
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "P821");
					jj.put("name", "学前教育");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "P822");
					jj1.put("name", "初等教育");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "P823");
					jj2.put("name", "中等教育");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "P824");
					jj3.put("name", "高等教育");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "P825");
					jj4.put("name", "特殊教育");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "P829");
					jj5.put("name", "技能培训、教育辅助及其他教育");
					ja.add(jj5);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("Q83"))//			卫生
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "Q831");
					jj.put("name", "医院");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "Q832");
					jj1.put("name", "社区医疗与卫生院");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "Q833");
					jj2.put("name", "门诊部（所）");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "Q834");
					jj3.put("name", "计划生育技术服务活动");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "Q835");
					jj4.put("name", "妇幼保健院（所、站）");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "Q836");
					jj5.put("name", "专科疾病防治院（所、站）");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "Q837");
					jj6.put("name", "疾病预防控制中心");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "Q839");
					jj7.put("name", "其他卫生活动");
					ja.add(jj7);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("Q84"))//			社会工作
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "Q841");
					jj.put("name", "提供住宿社会工作");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "Q842");
					jj1.put("name", "不提供住宿社会工作");
					ja.add(jj1);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("R85"))//			新闻和出版业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "R851");
					jj.put("name", "新闻业");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "R852");
					jj1.put("name", "出版业");
					ja.add(jj1);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("R86"))//			广播、电视、电影和影视录音制作业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "R861");
					jj.put("name", "广播");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "R862");
					jj1.put("name", "电视");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "R863");
					jj2.put("name", "电影和影视节目制作");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "R864");
					jj3.put("name", "电影和影视节目发行");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "R865");
					jj4.put("name", "电影放映");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "R866");
					jj5.put("name", "录音制作");
					ja.add(jj5);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("R87"))//			文化艺术业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "R871");
					jj.put("name", "文艺创作与表演");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "R872");
					jj1.put("name", "艺术表演场馆");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "R873");
					jj2.put("name", "图书馆与档案馆");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "R874");
					jj3.put("name", "文物及非物质文化遗产保护");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "R875");
					jj4.put("name", "博物馆");
					ja.add(jj4);
					JSONObject jj5 = new JSONObject();
					jj5.put("id", "R876");
					jj5.put("name", "烈士陵园、纪念馆");
					ja.add(jj5);
					JSONObject jj6 = new JSONObject();
					jj6.put("id", "R877");
					jj6.put("name", "群众文化活动");
					ja.add(jj6);
					JSONObject jj7 = new JSONObject();
					jj7.put("id", "R879");
					jj7.put("name", "其他文化艺术业");
					ja.add(jj7);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("R88"))//			体育
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "R881");
					jj.put("name", "体育组织");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "R882");
					jj1.put("name", "体育场馆");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "R883");
					jj2.put("name", "休闲健身活动");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "R889");
					jj3.put("name", "其他体育");
					ja.add(jj3);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("R89"))//			娱乐业
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "R891");
					jj.put("name", "室内娱乐活动");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "R892");
					jj1.put("name", "游乐园");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "R893");
					jj2.put("name", "彩票活动");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "R894");
					jj3.put("name", "文化、娱乐、体育经纪代理");
					ja.add(jj3);
					JSONObject jj4 = new JSONObject();
					jj4.put("id", "R899");
					jj4.put("name", "其他娱乐业");
					ja.add(jj4);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("S90"))//			中国共产党机关
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "S900");
					jj.put("name", "中国共产党机关");
					ja.add(jj);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("S91"))//			国家机构
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "S911");
					jj.put("name", "国家权力机构");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "S912");
					jj1.put("name", "国家行政机构");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "S913");
					jj2.put("name", "人民法院和人民检察院");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "S919");
					jj3.put("name", "其他国家机构");
					ja.add(jj3);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("S92"))//			人民政协、民主党派
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "S921");
					jj.put("name", "人民政协");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "S922");
					jj1.put("name", "民主党派");
					ja.add(jj1);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("S93"))//			社会保障
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "S930");
					jj.put("name", "社会保障");
					ja.add(jj);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("S94"))//			群众团体、社会团体和其他成员组织
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "S941");
					jj.put("name", "群众团体");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "S942");
					jj1.put("name", "社会团体");
					ja.add(jj1);
					JSONObject jj2 = new JSONObject();
					jj2.put("id", "S943");
					jj2.put("name", "基金会");
					ja.add(jj2);
					JSONObject jj3 = new JSONObject();
					jj3.put("id", "S944");
					jj3.put("name", "宗教组织");
					ja.add(jj3);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("S95"))//			基层群众自治组织
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "S951");
					jj.put("name", "社区自治组织");
					ja.add(jj);
					JSONObject jj1 = new JSONObject();
					jj1.put("id", "S952");
					jj1.put("name", "村民自治组织");
					ja.add(jj1);
				}
				else if(entBaseInfo.getEnterpriseCategory().equals("T96"))//			国际组织
				{
					JSONObject jj = new JSONObject();
					jj.put("id", "T960");
					jj.put("name", "国际组织");
					ja.add(jj);
				}
			}
			HylxBean bb = new HylxBean();
			bb.setId("");
			bb.setName("请选择");
			hylist.add(bb);
			for(int i=0;i<ja.size();i++)
			{
				JSONObject jj = (JSONObject) ja.get(i);
				String id= jj.getString("id");
				String name = jj.getString("name");
				if(entBaseInfo.getJjlx() != null && !"".equals(entBaseInfo.getJjlx()))
				{
					if(entBaseInfo.getJjlx().equals(id))
					{
						entBaseInfo.setJjlxname(name);
					}
				}
				HylxBean bean = new HylxBean();
				bean.setId(id);
				bean.setName(name);
				hylist.add(bean);
			}
			PrintWriter out = getResponse().getWriter();
			out.print(ja.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

	public EntBaseInfo getEntBaseInfo(){
		return this.entBaseInfo;
	}

	public void setEntBaseInfo(EntBaseInfo entBaseInfo){
		this.entBaseInfo = entBaseInfo;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }


	public List<CheckRecord> getCheckRecords() {
		return checkRecords;
	}


	public void setCheckRecords(List<CheckRecord> checkRecords) {
		this.checkRecords = checkRecords;
	}


	public CheckRecord getCheckRecord() {
		return checkRecord;
	}


	public void setCheckRecord(CheckRecord checkRecord) {
		this.checkRecord = checkRecord;
	}


	public String getCheckDeptId() {
		return checkDeptId;
	}


	public void setCheckDeptId(String checkDeptId) {
		this.checkDeptId = checkDeptId;
	}

	public String getCheckCompanyId() {
		return checkCompanyId;
	}

	public void setCheckCompanyId(String checkCompanyId) {
		this.checkCompanyId = checkCompanyId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public ComGriMan getComGriMan() {
		return comGriMan;
	}

	public void setComGriMan(ComGriMan comGriMan) {
		this.comGriMan = comGriMan;
	}

	public String getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
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

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public int getTotal1() {
		return total1;
	}

	public void setTotal1(int total1) {
		this.total1 = total1;
	}

	public int getTotal2() {
		return total2;
	}

	public void setTotal2(int total2) {
		this.total2 = total2;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}
	
	
	public void sendInfo()
	{
		try {
			//对接企业信息
			List<EntBaseInfo> entlist = entBaseInfoService.findEntBaseInfo(null);
			int total = entlist.size();
			int num = total/100;
			int ys = total%100;
			if(ys != 0)
			{
				num = num + 1;
			}
			for(int i=0;i<num;i++)
			{
				int start = 100*i;
				int end = 100*(i+1);
				if(end > total)
				{
					end = total;
				}
				GetFRJCInsertRequest frqq = new GetFRJCInsertRequest();
				ceateFRJCUtil ceateFRJCUtil = new ceateFRJCUtil();
				GetTkzxxRequest kzqq = new GetTkzxxRequest();
				ceateTkzxxUtil ceateTkzxxUtil = new ceateTkzxxUtil();
				for(int j=start;j<end;j++)
				{
					EntBaseInfo ent = entlist.get(j);
					String zjhm = NullToString(ent.getRegistrationNumber()).replaceAll(" ", "");
					if(zjhm != null && !"".equals(zjhm))
		        	{
		        		//对接两库
						Frjc frjc = ceateFRJCUtil.createFrjc(
								NullToString(zjhm),//工商注册号
								ssqy(ent.getEnterprisePossession()),//所属区域
								NullToString(ent.getEnterpriseLegalName()),//法定代表人姓名
								NullToString(ent.getEnterpriseScope()),//经营范围 
								"",//开业日期
								"",//地税登记日期
								"",//国税登记日期
								"",//工商登记日期
								"",//注册类型
								NullToString(ent.getEnterpriseAddress()),//注册地址
								NullToString(ent.getEnterpriseRegisterMoney()),//注册资金
								bz(ent.getEnterpriseRegisterMoneyDw()),//注册币种
								NullToString(ent.getFactoryAddress()),//实际经营地址
								"10",//企业状态
								 NullToString(ent.getEnterpriseLegalPhone()),//联系电话
								NullToString(ent.getEnterpriseZipcode()),//邮政编码
								"",//单位代码-社保公积金
								"",//纳税人识别号-国税
								"",//纳税人编码
								NullToString(ent.getEnterpriseCode()),//组织机构代码
								"",//机构类型
								NullToString(ent.getEnterpriseName()) ,//机构名称
								"",//企业性质
								NullToString(ent.getEnterpriseStaffCount())	,//企业人数
								NullToString(ent.getEnterpriseCategory()));//所属行业
		            	frqq.getFrjc().add(frjc);
		            	//法人扩展数据
		    			JSONArray ja = new JSONArray();
						String enterpriseFoundDate = ent.getEnterpriseFoundDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(ent.getEnterpriseFoundDate()):"";
						String enterpriseProductDate = ent.getEnterpriseProductDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(ent.getEnterpriseProductDate()):"";
						
						ja.add((new JSONObject()).put("QYGM" , qygm(ent.getEnterpriseScale())));	//企业规模
						ja.add((new JSONObject()).put("QYFL" , qyfl(ent.getEnterpriseType())));	//企业分类
						ja.add((new JSONObject()).put("TZFGJ" , NullToString(ent.getEnterpriseNationnality())));	//投资方国籍
						ja.add((new JSONObject()).put("FRDBXB" , NullToString(ent.getEnterpriseLegalSex())));	//法人代表性别
						ja.add((new JSONObject()).put("FRDBNL" , NullToString(ent.getEnterpriseLegalAge())));	//法人代表年龄
						ja.add((new JSONObject()).put("FRTEPHONE" , NullToString(ent.getEnterpriseLegalPhone())));	//法人代表联系电话
						ja.add((new JSONObject()).put("FREMAIL" , NullToString(ent.getEnterpriseLegalCardnum())));	//法人代表电子邮箱
						ja.add((new JSONObject()).put("FRDBZW" , NullToString(ent.getEnterpriseLegalZw())));	//法人代表职务
						ja.add((new JSONObject()).put("QYSLRQ" , enterpriseFoundDate));	//企业设立日期
						ja.add((new JSONObject()).put("QYTCRQ" , enterpriseProductDate));	//企业投产日期
						ja.add((new JSONObject()).put("TZZE" , NullToString(ent.getEnterpriseInvestMoney())));	//投资总额
						ja.add((new JSONObject()).put("TZZEDW" , bz(ent.getEnterpriseInvestMoneyDw())));	//投资总额单位
						ja.add((new JSONObject()).put("GDZCZE" , NullToString(ent.getEnterpriseFixedassetMoney())));	//固定资产总额
						ja.add((new JSONObject()).put("GDZCZEDW" , bz(ent.getEnterpriseFixedassetMoneyDw())));	//固定资产总额单位
						ja.add((new JSONObject()).put("ZDMJ" , NullToString(ent.getEnterpriseFloorArea())));	//占地面积（m2）
						ja.add((new JSONObject()).put("BGLJZMJ" , NullToString(ent.getEnterpriseOfficeArea())));	//办公楼建筑面积（m2）
						ja.add((new JSONObject()).put("CJJZMJ" , NullToString(ent.getEnterpriseWorkshopArea())));	//车间厂房建筑面积（m2）
						ja.add((new JSONObject()).put("CKJZMJ" , NullToString(ent.getEnterpriseWearhouseArea())));	//仓库建筑面积（m2）
						ja.add((new JSONObject()).put("CFGS" , NullToString(ent.getEnterprisWorkshopOwn())));	//厂房归属
						ja.add((new JSONObject()).put("CZF" , NullToString(ent.getHouseOwner())));	//出租方
						ja.add((new JSONObject()).put("CZFTEPHONE" , NullToString(ent.getOwnerTel())));	//出租方联系方式
						ja.add((new JSONObject()).put("GLRS" , NullToString(ent.getEnterpriseManagerCount())));	//管理人员数
						ja.add((new JSONObject()).put("GRS" , NullToString(ent.getEnterpriseWorkerCount())));	//工人数
						JSONObject jo = new JSONObject();
						jo.put("frkzxx", ja.toString());
						String kzxx = jo.toString();
						Tkzxxs tkzxxs = ceateTkzxxUtil.ceateTkzxxs("qyjbxxb",ent.getCreateTime(), kzxx, "", zjhm);
						kzqq.getTkzxxs().add(tkzxxs);
		        	}
				}
				FrjcPortService frjcPortService = new FrjcPortService();
				frjcPortService.setHandlerResolver(new HandlerResolver() {
					
					@Override
					public List<Handler> getHandlerChain(PortInfo arg0) {
						List<Handler> handlerList = new ArrayList();
						handlerList.add(new ClientAuthenticationHandler(
								"Zhaj_seivice@APP-00199",
								"zhaj"));
						return handlerList;
					}
				});
				FrjcPort frjcPort = frjcPortService.getFrjcPortSoap11();
				GetFRJCInsertResponse getFRJCInsertResponse = frjcPort.getFRJCInsert(frqq);
				System.out.println("企业基本信息"+getFRJCInsertResponse.getStatus());
				
				
				TkzxxPortService  tkzxxPortService= new TkzxxPortService();
				tkzxxPortService.setHandlerResolver(new HandlerResolver() {
					
					@Override
					public List<Handler> getHandlerChain(PortInfo arg0) {
						List<Handler> handlerList = new ArrayList();
						handlerList.add(new ClientAuthenticationHandler(
								"Zhaj_seivice@APP-00199",
								"zhaj"));
						return handlerList;
					}
				});
				TkzxxPort tkzxxPort = tkzxxPortService.getTkzxxPortSoap11();
				GetTkzxxResponse getTkzxxResponse = tkzxxPort.getTkzxx(kzqq);
				System.out.println("企业扩展信息"+getTkzxxResponse.getStatus());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//所属区域
	public String ssqy(String qycode)
	{
		if(qycode != null)
		{
			if(qycode.equals("1") || qycode.equals("2") || qycode.equals("3"))
			{
				return "1";//中新合作区
			}
			else if(qycode.equals("7"))
			{
				return "2";//娄葑街道
			}
			else if(qycode.equals("9"))
			{
				return "3";//唯亭街道
			}
			else if(qycode.equals("10"))
			{
				return "4";//胜浦街道
			}
			else if(qycode.equals("8"))
			{
				return "6";//斜街道
			}
		}
		return "5";//其它
	}
	
	//币种
	public String bz(String dw)
	{
		if(dw != null)
		{
			if(dw.equals("1"))//万元
			{
				return "01";
			}
			else if(dw.equals("2"))//万美元
			{
				return "02";
			}
			else if(dw.equals("3"))//万日元
			{
				return "492";
			}
			else if(dw.equals("4"))//万欧元
			{
				return "979";
			}
		}
		return "";
	}
	
       
	public String NullToString(String s)
	{
		if(s == null)
		{
			return "";
		}
		else
		{
			return s;
		}
	}
	
	public String qygm(String gm)
	{
		if(gm != null)
		{
			if(gm.equals("01"))//微型
			{
				return "wx";
			}
			else if(gm.equals("02"))//小型
			{
				return "xx";
			}
			else if(gm.equals("03"))//中型
			{
				return "zx";
			}
			else if(gm.equals("04"))//大型
			{
				return "dx";
			}
		}
		return "";
	}
	
	public String qyfl(String fl)
	{
		String s = "";
		if(fl != null)
		{
			String[] ss = s.replaceAll(" ", "").split(",");
			for(String a:ss)
			{
				if(a.equals("1"))
				{
					s += "gm,";//工贸
				}
				else if(a.equals("2"))
				{
					s += "wh,";//危化
				}
				else if(a.equals("3"))
				{
					s += "zyws,";//职业卫生
				}
				else if(a.equals("4"))
				{
					s += "fcsb,";//粉尘涉爆
				}
				else if(a.equals("5"))
				{
					s += "ldmj,";//劳动密集
				}
				else if(a.equals("6"))
				{
					s += "saqy,";//涉氨企业
				}
				else if(a.equals("7"))
				{
					s += "yhbz,";//烟花爆竹
				}
				else if(a.equals("8"))
				{
					s += "xfzd,";//消防重点
				}
			}
			if(s.length() != 0)
			{
				s = s.substring(0,s.length()-1);
			}
		}
		return s;
	}
	
	
	public void sendSaj()
	{
//		try {
//			int count = httpDataService.queryCompanysCountByMap(null);
//			for(int i =0;i<count;){
//				List<Map> coms = httpDataService.queryCompanysByMap(null, i, 1000);
//				if(coms!=null&&!coms.isEmpty()){
//					for(Map m:coms){
//						if(m.get("LINKID") != null && !"".equals(m.get("LINKID")))
//						{
//							Map map = new HashMap();
//							map.put("linkId",(String) m.get("LINKID"));
//							map.put("mkType", "qyxx");
//							map.put("picType","cqtp");
//							String qypmt = httpDataService.getPhotoById(map);
//							m.put("QYPMT", qypmt);
//						}
//					}
//					boolean bool  = UploadCompanysTools.uploadCompanys(coms);
//					if(bool){//上传成功 更新该数据为已传
//						System.out.println("上传成功!!!!!!"+i);
//					}
//				}
//				i = i+1000;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//网格信息
//		try {
//			
//			int count = httpDataService.queryWgxxCountByMap(null);
//			for(int i =0;i<count;){
//				List<Map> coms = httpDataService.queryWgxxByMap(null, i, 1000);
//				if(coms!=null&&!coms.isEmpty()){
//					boolean bool  = UploadWgxxTools.uploadCompanys(coms);
//					if(bool){//上传成功 更新该数据为已传
//						System.out.println("上传成功!!!!!!"+i);
//						
//					}
//				}
//				i = i+1000;
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	
		//网格员信息
//		try {
//			
//			int count = httpDataService.queryWgyxxCountByMap(null);
//			for(int i =0;i<count;){
//				List<Map> coms = httpDataService.queryWgyxxByMap(null, i, 1000);
//				if(coms!=null&&!coms.isEmpty()){
//					boolean bool  = UploadWgyxxTools.uploadCompanys(coms);
//					if(bool){//上传成功 更新该数据为已传
//						System.out.println("上传成功!!!!!!"+i);
//						
//					}
//				}
//				i = i+1000;
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		
		//网格员负责企业
//		try {
//			
//			int count = httpDataService.queryWgyfzqyCountByMap(null);
//			for(int i =0;i<count;){
//				List<Map> coms = httpDataService.queryWgyfzqyByMap(null, i, 10000);
//				if(coms!=null&&!coms.isEmpty()){
//					boolean bool  = UploadWgyfzqyTools.uploadCompanys(coms);
//					if(bool){//上传成功 更新该数据为已传
//						System.out.println("上传成功!!!!!!"+i);
//						
//					}
//				}
//				i = i+10000;
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//街镇安监办部门信息
//		try {
//			
//			int count = httpDataService.queryJzajbbmxxCountByMap(null);
//			for(int i =0;i<count;){
//				List<Map> coms = httpDataService.queryJzajbbmxxByMap(null, i, 10000);
//				if(coms!=null&&!coms.isEmpty()){
//					boolean bool  = UploadJzajbbmxxTools.uploadCompanys(coms);
//					if(bool){//上传成功 更新该数据为已传
//						System.out.println("上传成功!!!!!!"+i);
//						
//					}
//				}
//				i = i+10000;
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//街镇安监办人员基本信息
//		try {
//			
//			int count = httpDataService.queryJzajbryxxCountByMap(null);
//			for(int i =0;i<count;){
//				List<Map> coms = httpDataService.queryJzajbryxxByMap(null, i, 10000);
//				if(coms!=null&&!coms.isEmpty()){
//					boolean bool  = UploadJzajbryxxTools.uploadCompanys(coms);
//					if(bool){//上传成功 更新该数据为已传
//						System.out.println("上传成功!!!!!!"+i);
//						
//					}
//				}
//				i = i+10000;
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//企业帐号信息
//		try {
//			
//			int count = httpDataService.queryQyzhxxCountByMap(null);
//			for(int i =0;i<count;){
//				List<Map> coms = httpDataService.queryQyzhxxByMap(null, i, 10000);
//				if(coms!=null&&!coms.isEmpty()){
//					boolean bool  = UploadQyzhxxTools.uploadCompanys(coms);
//					if(bool){//上传成功 更新该数据为已传
//						System.out.println("上传成功!!!!!!"+i);
//						
//					}
//				}
//				i = i+10000;
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//安监机构信息
		try {
			
			int count = httpDataService.queryQygljgxxCountByMap(null);
			for(int i =0;i<count;){
				List<Map> coms = httpDataService.queryQygljgxxByMap(null, i, 10000);
				if(coms!=null&&!coms.isEmpty()){
//					boolean bool  = UploadQygljgxxTools.uploadCompanys(coms);
//					if(bool){//上传成功 更新该数据为已传
//						System.out.println("上传成功!!!!!!"+i);
//						
//					}
				}
				i = i+10000;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void test()
	{
		try {
			File file = new File("D:/总表.xls");
			FileInputStream fis=new FileInputStream(file);
			HSSFWorkbook workbook=new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheetAt(0);
			for (int i = 1; i < 3622; i++) {
				System.out.println(i);
				HSSFRow r=sheet.getRow(i);
				//用人单位名称	
				String qymc = r.getCell(0).getStringCellValue();
				//组织机构代码	
				String zzjgdm = r.getCell(1).getStringCellValue();
				//工商注册号
				String gszch = r.getCell(2).getStringCellValue();
				//信用代码
				String xydm = r.getCell(3).getStringCellValue();
				
				//所属行业	
				String sshy = r.getCell(4).getStringCellValue();
				//行业小类
				String hyxl = r.getCell(5).getStringCellValue();
				
				//行业小类
				String zclx = r.getCell(6).getStringCellValue();
				
				Map map = new HashMap();
				map.put("enterpriseName", qymc);
				EntBaseInfo e = entBaseInfoService.findEntBaseInfoByMap(map);
				Map m = new HashMap();
				if(e.getId() != null && !"".equals(e.getId()))
				{
					if(e.getRegistrationNumber() == null || "".equals(e.getRegistrationNumber()))
					{
						e.setRegistrationNumber(gszch.trim());
					}
					e.setEnterpriseCategory(sshy.trim());
					e.setJjlx(hyxl.trim());
					e.setQyzclx(zclx.trim());
					entBaseInfoService.update(e);
				}
			}
			fis.close();
			FileOutputStream fos=new FileOutputStream("D:/总表备份.xls");
			workbook.write(fos);
			fos.close();//关闭文件输出流
			System.out.println("成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public List<HylxBean> getHylist() {
		return hylist;
	}

	public void setHylist(List<HylxBean> hylist) {
		this.hylist = hylist;
	}

	public String getEntBaseInfoId() {
		return entBaseInfoId;
	}

	public void setEntBaseInfoId(String entBaseInfoId) {
		this.entBaseInfoId = entBaseInfoId;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<File> getFiledata() {
		return Filedata;
	}

	public void setFiledata(List<File> filedata) {
		Filedata = filedata;
	}

	public List<String> getFiledataFileName() {
		return FiledataFileName;
	}

	public void setFiledataFileName(List<String> filedataFileName) {
		FiledataFileName = filedataFileName;
	}

	public List<String> getFiledataContentType() {
		return FiledataContentType;
	}

	public void setFiledataContentType(List<String> filedataContentType) {
		FiledataContentType = filedataContentType;
	}

}
