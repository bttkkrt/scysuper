package com.jshx.rybzxx.web;

import java.io.FileInputStream;
import java.io.IOException;
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

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.qyjbxx.util.ClientAuthenticationHandler;
import com.jshx.rybzxx.entity.HonRec;
import com.jshx.rybzxx.service.HonRecService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.xypt.SynchronizeCompanyInfo;
import com.xypt.SynchronizeCompanyInfoService;

public class HonRecAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private HonRec honRec = new HonRec();

	/**
	 * 业务类
	 */
	@Autowired
	private HonRecService honRecService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryBzyearStart;

	private Date queryBzyearEnd;
	@Autowired
	private HttpService httpService;
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;

	/**
	 * 审核记录
	 */
	private List<CheckRecord> checkRecords;
	private CheckRecord checkRecord=new CheckRecord();
	private boolean canCheck=false;
	@Autowired
	private CheckRecordService checkRecordService;
	
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;
	/**
	 * 初始化 用于判断审核角色
	 */
	private String roleName;
	public String init(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			//登录人 可以审核
			if(ur.getRole().getRoleCode().equals("A02")) 
			{
				roleName = "1";
				setCanCheck(true);
				break;
			}
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				roleName = "0";
				break;
			}
		}
		return SUCCESS;
	}

	
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		else
		{
			paraMap.put("state", "待提交");
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != honRec){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != honRec.getAreaId()) && (0 < honRec.getAreaId().trim().length())){
				paraMap.put("areaId", honRec.getAreaId().trim());
			}

			if ((null != honRec.getRecognitionDept()) && (0 < honRec.getRecognitionDept().trim().length())){
				paraMap.put("recognitionDept", "%" + honRec.getRecognitionDept().trim() + "%");
			}

			if ((null != honRec.getHonor()) && (0 < honRec.getHonor().trim().length())){
				paraMap.put("honor", "%" + honRec.getHonor().trim() + "%");
			}

			if ((null != honRec.getCompanyName()) && (0 < honRec.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + honRec.getCompanyName().trim() + "%");
			}
			if ((null != honRec.getCompanyId()) && (0 < honRec.getCompanyId().trim().length())){
				paraMap.put("companyId",  honRec.getCompanyId().trim()  );
			}
			if ((null != honRec.getAuditResult())
					&& (0 < honRec.getAuditResult().trim().length())) {
				paraMap.put("auditResult", "%"
						+ honRec.getAuditResult().trim() + "%");
			}
			if ((null != honRec.getAuditState())
					&& (0 < honRec.getAuditState().trim().length())) {
				paraMap.put("auditState", honRec.getAuditState().trim());
			}

			
			if (null != queryBzyearStart){
				paraMap.put("startBzyearStart", queryBzyearStart);
			}

			if (null != queryBzyearEnd){
				paraMap.put("endBzyearStart", queryBzyearEnd);
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|honor|recognitionDept|bzyear|auditResult|auditState|createUserID|";
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
		pagination = honRecService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != honRec)&&(null != honRec.getId()))
		{
			honRec = honRecService.getById(honRec.getId());
			if(honRec.getLinkId() == null || "".equals(honRec.getLinkId()))
			{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					honRec.setLinkId(linkId);
				}
				else
				{
					Map map = new HashMap();
					map.put("linkId",honRec.getLinkId());
					map.put("mkType", "rybzxx");
					map.put("picType","ryfj");
				    picList = photoPicService.findPicPath(map);//获取执法文书材料
				}
			}
			else
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				honRec.setLinkId(linkId);
			}
		//审核记录
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("infoId", honRec.getId());
		checkRecords=checkRecordService.findCheckRecord(paraMap);
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
		honRec.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		honRec.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		honRec.setCompanyId(enBaseInfo.getId());
		honRec.setCompanyName(enBaseInfo.getEnterpriseName());
		if ("add".equalsIgnoreCase(this.flag)){
			honRec.setDeptId(this.getLoginUserDepartmentId());
			honRec.setDelFlag(0);
			honRecService.save(honRec);
		}else{
			honRecService.update(honRec);
		}
		
		return RELOAD;
	}
	/**
	 * 审核信息
	 */
	public String check() throws Exception{
		view();
	    return "check";
	}
	
	/**
	 * 保存审核信息
	 */
	public String checkSave() throws Exception{
		honRec=honRecService.getById(honRec.getId());
		if("审核通过并上报信用平台".equals(checkRecord.getCheckResult())){
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
				EntBaseInfo entBaseInfo = entBaseInfoService.getById(honRec.getCompanyId());
				SynchronizeCompanyInfoService service = new SynchronizeCompanyInfoService();
				service.setHandlerResolver(new HandlerResolver() {
					
					@Override
					public List<Handler> getHandlerChain(PortInfo arg0) {
						List<Handler> handlerList = new ArrayList();
						handlerList.add(new ClientAuthenticationHandler(
								"Zhaj_seivice@APP-00199",
								"zhaj"));
						return handlerList;
					}
				});
				SynchronizeCompanyInfo syn = service.getSynchronizeCompanyInfoPort();
				String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request><Head>"+
					"<DeptID><![CDATA[54]]></DeptID>"+
					"<InfoClass><![CDATA[安监-荣誉表彰信息]]></InfoClass>"+
					"<UploadTime><![CDATA["+sdf.format(new Date())+"]]></UploadTime></Head>"+
					"<Body><FIELDS><![CDATA[组织机构代码,企业全称（中文）,工商注册号/统一社会信用代码,批准文号,表彰部门,荣誉称号,地区,年度,表彰日期,表彰内容]]></FIELDS>"+
					"<DATA>"+
					"<ZZJGDM><![CDATA["+entBaseInfo.getEnterpriseCode()+"]]></ZZJGDM>"+
					"<QYMC><![CDATA["+entBaseInfo.getEnterpriseName() +"]]></QYMC>"+
					"<GSZCH><![CDATA["+entBaseInfo.getRegistrationNumber() +"]]></GSZCH>"+
					"<PZWH><![CDATA["+honRec.getApprovalNumber() +"]]></PZWH>"+
					"<BZBM><![CDATA["+honRec.getRecognitionDept() +"]]></BZBM>"+
					"<RYCH><![CDATA["+honRec.getHonor()+"]]></RYCH>"+
					"<DQ><![CDATA["+honRec.getArea() +"]]></DQ>"+
					"<ND><![CDATA["+new SimpleDateFormat("yyyy").format(honRec.getBzyear()) +"]]></ND>"+
					"<BZRQ><![CDATA["+honRec.getRecognitionDate()+"]]></BZRQ>"+
					"<BZNR><![CDATA["+honRec.getRecognitionContent()+"]]></BZNR>"+
					"</DATA></Body></Request>";
				System.out.println(xml);
				System.out.println(syn.uploadCompanyInfo(xml));
				
				honRec.setAuditState("审核通过");
				honRec.setCheckRemark(checkRecord.getCheckRemark());
				honRecService.update(honRec);
				checkRecord .setCheckUserid(this.getLoginUser().getId());
				checkRecord.setDelFlag(0);
				checkRecord.setCheckUsername(this.getLoginUser().getDisplayName());
				checkRecordService.save(checkRecord);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		else
		{
			honRec.setAuditState(checkRecord.getCheckResult());
			honRec.setCheckRemark(checkRecord.getCheckRemark());
			honRecService.update(honRec);
			checkRecord .setCheckUserid(this.getLoginUser().getId());
			checkRecord.setDelFlag(0);
			checkRecord.setCheckUsername(this.getLoginUser().getDisplayName());
			checkRecordService.save(checkRecord);
		}
	    return RELOAD;
	}
	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != honRec)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到honRec中去
				
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
			honRecService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
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
		User user=userService.findUserByLoginId("test2");
		setSessionAttribute("curr_user", user);
		String userId=user.getId();
		if(httpService.judgeRoleCode(userId, "A02")){
			roleName = "1";
			setCanCheck(true);
		}else if(httpService.judgeRoleCode(userId, "A23")){
			roleName = "0";
		}else{
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
		else
		{
			paraMap.put("state", "待提交");
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != honRec){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != honRec.getAreaId()) && (0 < honRec.getAreaId().trim().length())){
				paraMap.put("areaId", honRec.getAreaId().trim());
			}

			if ((null != honRec.getRecognitionDept()) && (0 < honRec.getRecognitionDept().trim().length())){
				paraMap.put("recognitionDept", "%" + honRec.getRecognitionDept().trim() + "%");
			}

			if ((null != honRec.getHonor()) && (0 < honRec.getHonor().trim().length())){
				paraMap.put("honor", "%" + honRec.getHonor().trim() + "%");
			}

			if ((null != honRec.getCompanyName()) && (0 < honRec.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + honRec.getCompanyName().trim() + "%");
			}
			if ((null != honRec.getAuditResult())
					&& (0 < honRec.getAuditResult().trim().length())) {
				paraMap.put("auditResult", "%"
						+ honRec.getAuditResult().trim() + "%");
			}
			if ((null != honRec.getAuditState())
					&& (0 < honRec.getAuditState().trim().length())) {
				paraMap.put("auditState", honRec.getAuditState().trim());
			}

			
			if (null != queryBzyearStart){
				paraMap.put("startBzyearStart", queryBzyearStart);
			}

			if (null != queryBzyearEnd){
				paraMap.put("endBzyearStart", queryBzyearEnd);
			}

		}
			
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称、年度、荣誉称号或表彰部门".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
			
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|honor|recognitionDept|bzyear|auditResult|auditState|";
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
		pagination = honRecService.findByPage(pagination, paraMap);
		
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
	
	public String rybz(){
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			//登录人 可以审核
			if(ur.getRole().getRoleCode().equals("A02")) 
			{
				roleName = "1";
				setCanCheck(true);
				break;
			}
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				roleName = "0";
				break;
			}
		}
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

	public HonRec getHonRec(){
		return this.honRec;
	}

	public void setHonRec(HonRec honRec){
		this.honRec = honRec;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryBzyearStart(){
		return this.queryBzyearStart;
	}

	public void setQueryBzyearStart(Date queryBzyearStart){
		this.queryBzyearStart = queryBzyearStart;
	}

	public Date getQueryBzyearEnd(){
		return this.queryBzyearEnd;
	}

	public void setQueryBzyearEnd(Date queryBzyearEnd){
		this.queryBzyearEnd = queryBzyearEnd;
	}

	public void setCheckRecord(CheckRecord checkRecord) {
		this.checkRecord = checkRecord;
	}

	public CheckRecord getCheckRecord() {
		return checkRecord;
	}

	public void setCheckRecords(List<CheckRecord> checkRecords) {
		this.checkRecords = checkRecords;
	}

	public List<CheckRecord> getCheckRecords() {
		return checkRecords;
	}

	public void setCanCheck(boolean canCheck) {
		this.canCheck = canCheck;
	}

	public boolean isCanCheck() {
		return canCheck;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
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
