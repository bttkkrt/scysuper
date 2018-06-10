package com.jshx.jjrktg.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.jpush.api.JPushClient;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.jjrktg.entity.ShuHol;
import com.jshx.jjrktg.service.ShuHolService;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.entity.UserRole;
import com.jshx.module.admin.service.CodeService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;

public class ShuHolAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private ShuHol shuHol = new ShuHol();

	/**
	 * 业务类
	 */
	@Autowired
	private ShuHolService shuHolService;
	@Autowired
	private CheckRecordService checkRecordService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	private int pageNo;
	private int pageSize;
	private String searchLike;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryHolidayTimeStart;

	private Date queryHolidayTimeEnd;

	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;


	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	
	/**
	 * 审核记录
	 */
	private List<CheckRecord> checkRecords;
	private CheckRecord checkRecord=new CheckRecord();
	private boolean canCheck=false;
	@Autowired
	private HttpService httpService;
	private String loginUserId;
	private String roleName;
	public String init(){
		loginUserId = this.getLoginUser().getId();
		List<UserRight> urs=(List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight r:urs){
			if("A05".equals(r.getRole().getRoleCode())){
				canCheck=true;
			}
			if(r.getRole().getRoleCode().equals("A23")) 
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
	@SuppressWarnings("unchecked")
	public void list() throws Exception{
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());

		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != shuHol){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != shuHol.getAreaId()) && (0 < shuHol.getAreaId().trim().length())){
				paraMap.put("areaId",   shuHol.getAreaId() );
			}

			if ((null != shuHol.getCompanyName()) && (0 < shuHol.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + shuHol.getCompanyName().trim() + "%");
			}

			if (null != queryHolidayTimeStart){
				paraMap.put("startHolidayTime", queryHolidayTimeStart);
			}

			if (null != queryHolidayTimeEnd){
				paraMap.put("endHolidayTime", queryHolidayTimeEnd);
			}
			if ((null != shuHol.getIfStart()) && (0 < shuHol.getIfStart().trim().length())){
				paraMap.put("ifStart", shuHol.getIfStart().trim());
			}
			
			if ((null != shuHol.getAuditState()) && (0 < shuHol.getAuditState().trim().length())){
				paraMap.put("auditState", shuHol.getAuditState().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		codeMap.put("ifStart","402809812e7f8c28012e7fa82239000c");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
//		final String filter = "id|areaId|companyName|holidayTimeStart|holidayTimeEnd|ifStart|auditState|";
//		if (filter != null && filter.length() > 1) {
//			config.setJsonPropertyFilter(new PropertyFilter() {
//				public boolean apply(Object source, String name, Object value) {
//					if (filter.indexOf(name + "|") != -1)
//						return false;
//					else
//						return true;
//				}
//			});
//		}
		pagination = shuHolService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}
	/**
	 * 政务通 节假日开停工 
	 * lj
	 * 2016-06-08
	 * 
	 */
	public String jjrktg(){
		try {
			loginUserId = this.getLoginUser().getId();
			List<UserRight> urs=(List<UserRight>) this.getLoginUser().getUserRoles();
			for(UserRight r:urs){
				if("A05".equals(r.getRole().getRoleCode())){
					canCheck=true;
				}
				if(r.getRole().getRoleCode().equals("A23")) 
				{
					roleName = "0";
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	@SuppressWarnings("unchecked")
	public void zwtList() throws Exception{
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());

		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != shuHol){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != shuHol.getAreaId()) && (0 < shuHol.getAreaId().trim().length())){
				paraMap.put("areaId",   shuHol.getAreaId() );
			}

			if ((null != shuHol.getCompanyName()) && (0 < shuHol.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + shuHol.getCompanyName().trim() + "%");
			}

			if (null != queryHolidayTimeStart){
				paraMap.put("startHolidayTime", queryHolidayTimeStart);
			}

			if (null != queryHolidayTimeEnd){
				paraMap.put("endHolidayTime", queryHolidayTimeEnd);
			}
			if ((null != shuHol.getIfStart()) && (0 < shuHol.getIfStart().trim().length())){
				paraMap.put("ifStart", shuHol.getIfStart().trim());
			}
			
			if ((null != shuHol.getAuditState()) && (0 < shuHol.getAuditState().trim().length())){
				paraMap.put("auditState", shuHol.getAuditState().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		codeMap.put("ifStart","402809812e7f8c28012e7fa82239000c");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
//		final String filter = "id|areaId|companyName|holidayTimeStart|holidayTimeEnd|ifStart|auditState|";
//		if (filter != null && filter.length() > 1) {
//			config.setJsonPropertyFilter(new PropertyFilter() {
//				public boolean apply(Object source, String name, Object value) {
//					if (filter.indexOf(name + "|") != -1)
//						return false;
//					else
//						return true;
//				}
//			});
//		}
		
		if(null!=searchLike&&!"".equals(searchLike)){
			paraMap.put("companyName", "%" + searchLike.trim() + "%");
		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = shuHolService.findByPage(pagination, paraMap);
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
	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		
		if((null != shuHol)&&(null != shuHol.getId())){
			shuHol = shuHolService.getById(shuHol.getId());
			if(shuHol.getLinkId() == null || "".equals(shuHol.getLinkId())){
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				shuHol.setLinkId(linkId);
			}else{
				Map map = new HashMap();
				map.put("linkId",shuHol.getLinkId());
				map.put("mkType", "jjrktg");
				map.put("picType","jjrktgfj");
				picList = photoPicService.findPicPath(map);//获取执法文书材料
			}
			//审核记录
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("infoId", shuHol.getId());
			checkRecords=checkRecordService.findCheckRecord(paraMap);
		}else{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			shuHol.setLinkId(linkId);
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
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		shuHol.setAreaId(enBaseInfo.getEnterprisePossession());
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		shuHol.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		shuHol.setCompanyId(enBaseInfo.getId());
		shuHol.setCompanyName(enBaseInfo.getEnterpriseName());
		
		shuHol.setAuditState("待审核");
		if ("add".equalsIgnoreCase(this.flag)){
			shuHol.setDeptId(this.getLoginUserDepartmentId());
			shuHol.setDelFlag(0);
			shuHolService.save(shuHol);
		}else{
			shuHolService.update(shuHol);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != shuHol)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到shuHol中去
				
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
			shuHolService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
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
		shuHol=shuHolService.getById(shuHol.getId());
		shuHol.setAuditState(checkRecord.getCheckResult());
		shuHolService.update(shuHol);
		checkRecord .setCheckUserid(this.getLoginUser().getId());
		checkRecord.setDelFlag(0);
		checkRecord.setCheckUsername(this.getLoginUser().getDisplayName());
		checkRecordService.save(checkRecord);
		
		try {
			if("审核通过".equals(checkRecord.getCheckResult())){
				// 信息推送
				Map send = new HashMap();
				Map map = new HashMap();
				map.put("sqlID", "queryCompanyInfoHttp");
				map.put("id", shuHol.getCompanyId());
				Map comp = httpService.getMapByMap(map);
				if(comp!=null&&!comp.isEmpty()){//企业存在

					String[] userIds = new String[]{(String) comp.get("userId")};
					send.put("type", "6");
					JSONObject json = new JSONObject();
					json.put("id", shuHol.getId());
					send.put("content",json.toString());
						//信息推送
					JPushClient jpush = new JPushClient();
					jpush.sendAndroidNotificationWithAlias("园区安监","节假日开停工时间："+shuHol.getHolidayTimeStart(), send,userIds);
				
					}
				}
			} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return RELOAD;
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

	public ShuHol getShuHol(){
		return this.shuHol;
	}

	public void setShuHol(ShuHol shuHol){
		this.shuHol = shuHol;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryHolidayTimeStart(){
		return this.queryHolidayTimeStart;
	}

	public void setQueryHolidayTimeStart(Date queryHolidayTimeStart){
		this.queryHolidayTimeStart = queryHolidayTimeStart;
	}

	public Date getQueryHolidayTimeEnd(){
		return this.queryHolidayTimeEnd;
	}

	public void setQueryHolidayTimeEnd(Date queryHolidayTimeEnd){
		this.queryHolidayTimeEnd = queryHolidayTimeEnd;
	}
	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
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

	public boolean getCanCheck() {
		return canCheck;
	}

	public void setCanCheck(boolean canCheck) {
		this.canCheck = canCheck;
	}

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
