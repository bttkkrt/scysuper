package com.jshx.xkzcxxx.web;

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
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.qyjbxx.util.ClientAuthenticationHandler;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.xkzcxxx.entity.LicCanInf;
import com.jshx.xkzcxxx.service.LicCanInfService;
import com.xypt.SynchronizeCompanyInfo;
import com.xypt.SynchronizeCompanyInfoService;

public class LicCanInfAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private LicCanInf licCanInf = new LicCanInf();

	/**
	 * 业务类
	 */
	@Autowired
	private LicCanInfService licCanInfService;
	
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private CheckRecordService checkRecordService;
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
	
	/**
	 * 审核记录
	 */
	private List<CheckRecord> checkRecords;
	private CheckRecord checkRecord=new CheckRecord();
	
	private boolean canCheck=false;
	
	
	private Date queryApprovalDateStart;

	private Date queryApprovalDateEnd;
	
	private int pageNo;
	
	private int pageSize;
	
	private String searchLike;
	
	
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

	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	private String roleName;
	public String init(){
		List<UserRight> urs=(List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight r:urs){
			
			if("A02".equals(r.getRole().getRoleCode())){
				canCheck=true;
				
			}
			if(r.getRole().getRoleCode().equals("A03")||r.getRole().getRoleCode().equals("A04")) 
			{
				roleName = "1";
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
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != licCanInf){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != licCanInf.getAreaId()) && (0 < licCanInf.getAreaId().trim().length())){
				paraMap.put("areaId",licCanInf.getAreaId().trim() );
			}

			if ((null != licCanInf.getAreaName()) && (0 < licCanInf.getAreaName().trim().length())){
				paraMap.put("areaName", licCanInf.getAreaName().trim());
			}

			if ((null != licCanInf.getCompanyId()) && (0 < licCanInf.getCompanyId().trim().length())){
				paraMap.put("companyId", "%" + licCanInf.getCompanyId().trim() + "%");
			}

			if ((null != licCanInf.getCompanyName()) && (0 < licCanInf.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + licCanInf.getCompanyName().trim() + "%");
			}

			if ((null != licCanInf.getLicenseName()) && (0 < licCanInf.getLicenseName().trim().length())){
				paraMap.put("licenseName", "%" + licCanInf.getLicenseName().trim() + "%");
			}

			if ((null != licCanInf.getLicenseNumber()) && (0 < licCanInf.getLicenseNumber().trim().length())){
				paraMap.put("licenseNumber", "%" + licCanInf.getLicenseNumber().trim() + "%");
			}
			if ((null != licCanInf.getAuditState()) && (0 < licCanInf.getAuditState().trim().length())){
				paraMap.put("auditState", licCanInf.getAuditState().trim() );
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|licenseName|licenseNumber|auditState|createUserID|";
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
		try {
			pagination = licCanInfService.findByPage(pagination, paraMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != licCanInf)&&(null != licCanInf.getId()))
			licCanInf = licCanInfService.getById(licCanInf.getId());
		//审核记录
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("infoId", licCanInf.getId());
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
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", licCanInf.getAreaId());
		licCanInf.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		licCanInf.setAuditState("待审核");
		if ("add".equalsIgnoreCase(this.flag)){
			licCanInf.setDeptId(this.getLoginUserDepartmentId());
			licCanInf.setDelFlag(0);
			licCanInfService.save(licCanInf);
		}else{
			licCanInfService.update(licCanInf);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != licCanInf)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到licCanInf中去
				
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
			licCanInfService.deleteWithFlag(ids);
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
		licCanInf=licCanInfService.getById(licCanInf.getId());
		if("审核通过并上报信用平台".equals(checkRecord.getCheckResult())){
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
				SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
				EntBaseInfo entBaseInfo = entBaseInfoService.getById(licCanInf.getCompanyId());
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
				"<InfoClass><![CDATA[安监-许可证注（撤）销信息]]></InfoClass>"+
				"<UploadTime><![CDATA["+sdf.format(new Date())+"]]></UploadTime></Head>"+
				"<Body><FIELDS><![CDATA[组织机构代码,企业全称（中文）,工商注册号/统一社会信用代码,许可证编号,批准日期,注（撤、吊）销文号,注（撤、吊）销原因,批准机关名称,许可证名称]]></FIELDS>"+
				"<DATA>"+
				"<ZZJGDM><![CDATA["+entBaseInfo.getEnterpriseCode()+"]]></ZZJGDM>"+
				"<QYMC><![CDATA["+entBaseInfo.getEnterpriseName() +"]]></QYMC>"+
				"<GSZCH><![CDATA["+entBaseInfo.getRegistrationNumber() +"]]></GSZCH>"+
				"<XKZBH><![CDATA["+licCanInf.getLicenseNumber() +"]]></XKZBH>"+
				"<PZRQ><![CDATA["+sdf1.format(licCanInf.getApprovalDate()) +"]]></PZRQ>"+
				"<ZXWH><![CDATA["+licCanInf.getCancellationNumber() +"]]></ZXWH>"+
				"<ZXYY><![CDATA["+licCanInf.getCancelReason() +"]]></ZXYY>"+
				"<PZJGMC><![CDATA["+licCanInf.getApprovalAuthority() +"]]></PZJGMC>"+
				"<XKZMC><![CDATA["+licCanInf.getLicenseName()+"]]></XKZMC>"+
				"</DATA></Body></Request>";
				System.out.println(syn.uploadCompanyInfo(xml));
				
				licCanInf.setAuditState("审核通过");
				licCanInfService.update(licCanInf);
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
			licCanInf.setAuditState(checkRecord.getCheckResult());
			licCanInfService.update(licCanInf);
			checkRecord .setCheckUserid(this.getLoginUser().getId());
			checkRecord.setDelFlag(0);
			checkRecord.setCheckUsername(this.getLoginUser().getDisplayName());
			checkRecordService.save(checkRecord);
		}
	    return RELOAD;
	}
	
	
	public String zwtInit(){
		List<UserRight> urs=(List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight r:urs){
			
			if("A02".equals(r.getRole().getRoleCode())){
				canCheck=true;
				
			}
			if(r.getRole().getRoleCode().equals("A03")||r.getRole().getRoleCode().equals("A04")) 
			{
				roleName = "1";
				break;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != licCanInf){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != licCanInf.getAreaId()) && (0 < licCanInf.getAreaId().trim().length())){
				paraMap.put("areaId",licCanInf.getAreaId().trim() );
			}

			if ((null != licCanInf.getAreaName()) && (0 < licCanInf.getAreaName().trim().length())){
				paraMap.put("areaName", licCanInf.getAreaName().trim());
			}

			if ((null != licCanInf.getCompanyId()) && (0 < licCanInf.getCompanyId().trim().length())){
				paraMap.put("companyId", "%" + licCanInf.getCompanyId().trim() + "%");
			}

			if ((null != licCanInf.getCompanyName()) && (0 < licCanInf.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + licCanInf.getCompanyName().trim() + "%");
			}

			if ((null != licCanInf.getLicenseName()) && (0 < licCanInf.getLicenseName().trim().length())){
				paraMap.put("licenseName", "%" + licCanInf.getLicenseName().trim() + "%");
			}

			if ((null != licCanInf.getLicenseNumber()) && (0 < licCanInf.getLicenseNumber().trim().length())){
				paraMap.put("licenseNumber", "%" + licCanInf.getLicenseNumber().trim() + "%");
			}
			if ((null != licCanInf.getAuditState()) && (0 < licCanInf.getAuditState().trim().length())){
				paraMap.put("auditState", licCanInf.getAuditState().trim() );
			}

		}
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称、许可证名称或许可证编号".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|licenseName|licenseNumber|auditState|createUserID|";
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
		pagination = licCanInfService.findByPage(pagination, paraMap);
		
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

	public LicCanInf getLicCanInf(){
		return this.licCanInf;
	}

	public void setLicCanInf(LicCanInf licCanInf){
		this.licCanInf = licCanInf;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryApprovalDateStart(){
		return this.queryApprovalDateStart;
	}

	public void setQueryApprovalDateStart(Date queryApprovalDateStart){
		this.queryApprovalDateStart = queryApprovalDateStart;
	}

	public Date getQueryApprovalDateEnd(){
		return this.queryApprovalDateEnd;
	}

	public void setQueryApprovalDateEnd(Date queryApprovalDateEnd){
		this.queryApprovalDateEnd = queryApprovalDateEnd;
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

	public boolean isCanCheck() {
		return canCheck;
	}

	public void setCanCheck(boolean canCheck) {
		this.canCheck = canCheck;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
