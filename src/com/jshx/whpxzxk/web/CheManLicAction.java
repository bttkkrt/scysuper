package com.jshx.whpxzxk.web;

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
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.qyjbxx.util.ClientAuthenticationHandler;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.whpxzxk.entity.CheManLic;
import com.jshx.whpxzxk.service.CheManLicService;
import com.xypt.SynchronizeCompanyInfo;
import com.xypt.SynchronizeCompanyInfoService;


public class CheManLicAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private CheManLic cheManLic = new CheManLic();

	/**
	 * 业务类
	 */
	@Autowired
	private CheManLicService cheManLicService;
	@Autowired
	private CheckRecordService checkRecordService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryReceiveDateStart;

	private Date queryReceiveDateEnd;

	private Date queryDealDateStart;

	private Date queryDealDateEnd;

	private Date queryLicenseValidStart;

	private Date queryLicenseValidEnd;
	
	/**
	 * 审核记录
	 */
	private List<CheckRecord> checkRecords;
	private CheckRecord checkRecord=new CheckRecord();
	private boolean canCheck=false;

	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private CodeService codeService;
	/**
	 * 执行查询的方法，返回json数据
	 */
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList4 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList5 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList6 = new ArrayList<PhotoPic>();
	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}
	
	private int pageNum=1;
	
	private int totalCount;
		
	private int totalPage;
		
	private List<CheManLic> whpjyxkzList=new ArrayList<CheManLic>();
	 /**
	 * 初始化 用于判断审核角色
	 */
	private String roleName;
	
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
	public String init(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			if("A02".equals(ur.getRole().getRoleCode())){
				canCheck=true;
			}
			if(ur.getRole().getRoleCode().equals("A05")||ur.getRole().getRoleCode().equals("A06")) 
			{
				roleName = "1";
				break;
			}
		}
		return SUCCESS;
	}
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		if(this.getLoginUser().getDeptCode().equals("009"))
		{
			Map map = new HashMap();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			paraMap.put("companyId", entBaseInfo.getId());
		}   
		if(null != cheManLic){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != cheManLic.getAreaId()) && (0 < cheManLic.getAreaId().trim().length())){
				paraMap.put("areaId", cheManLic.getAreaId().trim());
			}

			if ((null != cheManLic.getCompanyName()) && (0 < cheManLic.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + cheManLic.getCompanyName().trim() + "%");
			}

			if ((null != cheManLic.getRatingAgencies()) && (0 < cheManLic.getRatingAgencies().trim().length())){
				paraMap.put("ratingAgencies", "%" + cheManLic.getRatingAgencies().trim() + "%");
			}

			if (null != queryReceiveDateStart){
				paraMap.put("startReceiveDate", queryReceiveDateStart);
			}

			if (null != queryReceiveDateEnd){
				paraMap.put("endReceiveDate", queryReceiveDateEnd);
			}
			if (null != queryDealDateStart){
				paraMap.put("startDealDate", queryDealDateStart);
			}

			if (null != queryDealDateEnd){
				paraMap.put("endDealDate", queryDealDateEnd);
			}
			if (null != queryLicenseValidStart){
				paraMap.put("startLicenseValid", queryLicenseValidStart);
			}

			if (null != queryLicenseValidEnd){
				paraMap.put("endLicenseValid", queryLicenseValidEnd);
			}
			if ((null != cheManLic.getReceivePerson()) && (0 < cheManLic.getReceivePerson().trim().length())){
				paraMap.put("receivePerson", "%" + cheManLic.getReceivePerson().trim() + "%");
			}

			if ((null != cheManLic.getCheckPerson()) && (0 < cheManLic.getCheckPerson().trim().length())){
				paraMap.put("checkPerson", "%" + cheManLic.getCheckPerson().trim() + "%");
			}
			if ((null != cheManLic.getAuditState()) && (0 < cheManLic.getAuditState().trim().length())){
				paraMap.put("auditState", cheManLic.getAuditState().trim());
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|ratingAgencies|receiveDate|dealDate|licenseValid|receivePerson|checkPerson|createUserID|auditState|";
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
		pagination = cheManLicService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != cheManLic)&&(null != cheManLic.getId()))
		{
			cheManLic = cheManLicService.getById(cheManLic.getId());
			//审核记录
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("infoId", cheManLic.getId());
			checkRecords=checkRecordService.findCheckRecord(paraMap);
			if(cheManLic.getLinkId() == null || "".equals(cheManLic.getLinkId()))
			{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					cheManLic.setLinkId(linkId);
				}
				else
				{
					Map map = new HashMap();
					map.put("linkId",cheManLic.getLinkId());
					map.put("mkType", "whpxzxk");
					map.put("picType","xzxk1");
				    picList = photoPicService.findPicPath(map);//获取执法文书材料
				    map.put("picType","xzxk2");
				    picList2 = photoPicService.findPicPath(map);//获取执法文书材料
				    map.put("picType","xzxk3");
				    picList3 = photoPicService.findPicPath(map);//获取执法文书材料
				    map.put("picType","xzxk4");
				    picList4 = photoPicService.findPicPath(map);//获取执法文书材料
				    map.put("picType","xzxk5");
				    picList5 = photoPicService.findPicPath(map);//获取执法文书材料
				    map.put("picType","xzxk6");
				    picList6 = photoPicService.findPicPath(map);//获取执法文书材料
				}
			}
			else
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				cheManLic.setLinkId(linkId);
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
		try {
			Map m = new HashMap();
			m.put("codeName", "企业属地");
			m.put("itemValue", cheManLic.getAreaId());
			cheManLic.setAreaName(codeService.findCodeValueByMap(m)
					.getItemText());
			cheManLic.setAuditState("待审核");
			if ("add".equalsIgnoreCase(this.flag)) {
				cheManLic.setDeptId(this.getLoginUserDepartmentId());
				cheManLic.setDelFlag(0);
				cheManLicService.save(cheManLic);
			} else {
				cheManLicService.update(cheManLic);
			}
		} catch (Exception e) {e.printStackTrace();}
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != cheManLic)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到cheManLic中去
				
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
			cheManLicService.deleteWithFlag(ids);
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
		cheManLic=cheManLicService.getById(cheManLic.getId());
		if("审核通过并上报信用平台".equals(checkRecord.getCheckResult())){
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
				SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
				EntBaseInfo entBaseInfo = entBaseInfoService.getById(cheManLic.getCompanyId());
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
				Map map = new HashMap();
				map.put("codeName", "行业类别");
				map.put("itemValue", entBaseInfo.getEnterpriseCategory());
				String jjlx=(null==codeService.findCodeValueByMap(map).getItemText()?"":codeService.findCodeValueByMap(map).getItemText());
				String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request><Head>"+
				"<DeptID><![CDATA[54]]></DeptID>"+
				"<InfoClass><![CDATA[安监-危险化学品经营许可证登记（变更）审批信息]]></InfoClass>"+
				"<UploadTime><![CDATA["+sdf.format(new Date())+"]]></UploadTime></Head>"+
				"<Body><FIELDS><![CDATA[发证日期,发证机关名称,许可证书有效截止日期,许可证书有效起始日期,经营场所地址,组织机构代码,经营范围,主要负责人姓名,许可证编号,工商注册号/统一社会信用代码,企业全称（中文）,经济类型]]></FIELDS>"+
				"<DATA>"+
				"<FZRQ><![CDATA["+sdf1.format(cheManLic.getFzrq())+"]]></FZRQ>"+
				"<FZJGMC><![CDATA["+cheManLic.getLssuingUnit()+"]]></FZJGMC>"+
				"<XKZSYXJZRQ><![CDATA["+sdf1.format(cheManLic.getLicenseValidEnd())+"]]></XKZSYXJZRQ>"+
				"<XKZSYXQSRQ><![CDATA["+sdf1.format(cheManLic.getLicenseValid()) +"]]></XKZSYXQSRQ>"+
				"<JYCSDZ><![CDATA["+entBaseInfo.getEnterpriseAddress() +"]]></JYCSDZ>"+
				"<ZZJGDM><![CDATA["+entBaseInfo.getEnterpriseCode() +"]]></ZZJGDM>"+
				"<JYFW><![CDATA["+cheManLic.getJyfw() +"]]></JYFW>"+
				"<ZYFZRXM><![CDATA["+entBaseInfo.getEnterpriseLegalName() +"]]></ZYFZRXM>"+
				"<XKZBH><![CDATA["+cheManLic.getXkzbh() +"]]></XKZBH>"+
				"<GSZCH><![CDATA["+entBaseInfo.getRegistrationNumber()+"]]></GSZCH>"+
				"<QYMC><![CDATA["+entBaseInfo.getEnterpriseName()+"]]></QYMC>"+
				"<JJLX><![CDATA["+jjlx+"]]></JJLX>"+
				"</DATA></Body></Request>";
				System.out.println(xml);
				System.out.println(syn.uploadCompanyInfo(xml));
				
				cheManLic.setAuditState("审核通过");
				cheManLicService.update(cheManLic);
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
			cheManLic.setAuditState(checkRecord.getCheckResult());
			cheManLicService.update(cheManLic);
			checkRecord .setCheckUserid(this.getLoginUser().getId());
			checkRecord.setDelFlag(0);
			checkRecord.setCheckUsername(this.getLoginUser().getDisplayName());
			checkRecordService.save(checkRecord);
		}
	    return RELOAD;
	}
	
	public String whpjyxkzList()
	{
		whpjyxkzList = cheManLicService.findAllInfo(null, pageNum, 10);
		totalCount = cheManLicService.findAllInfos(null);
		totalPage = totalCount/10;
		totalPage = totalCount%10==0?totalPage:(totalPage+1);
		return SUCCESS;
	}
	
	public String whpjyxkzContent()
	{
		
		if((null != cheManLic)&&(null != cheManLic.getId()))
		{
			cheManLic = cheManLicService.getById(cheManLic.getId());
			if(cheManLic.getLinkId() == null || "".equals(cheManLic.getLinkId()))
			{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					cheManLic.setLinkId(linkId);
				}
				else
				{
					Map map = new HashMap();
					map.put("linkId",cheManLic.getLinkId());
					map.put("mkType", "whpxzxk");
					map.put("picType","xzxk1");
				    picList = photoPicService.findPicPath(map);//获取执法文书材料
				    map.put("picType","xzxk2");
				    picList2 = photoPicService.findPicPath(map);//获取执法文书材料
				    map.put("picType","xzxk3");
				    picList3 = photoPicService.findPicPath(map);//获取执法文书材料
				    map.put("picType","xzxk4");
				    picList4 = photoPicService.findPicPath(map);//获取执法文书材料
				    map.put("picType","xzxk5");
				    picList5 = photoPicService.findPicPath(map);//获取执法文书材料
				    map.put("picType","xzxk6");
				    picList6 = photoPicService.findPicPath(map);//获取执法文书材料
				}
			}
			else
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				cheManLic.setLinkId(linkId);
			}
		
		return SUCCESS;
	}
	
	public String zwtInit(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			if("A02".equals(ur.getRole().getRoleCode())){
				canCheck=true;
			}
			if(ur.getRole().getRoleCode().equals("A05")||ur.getRole().getRoleCode().equals("A06")) 
			{
				roleName = "1";
				break;
			}
		}
		return SUCCESS;
	}
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		if(this.getLoginUser().getDeptCode().equals("009"))
		{
			Map map = new HashMap();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			paraMap.put("companyId", entBaseInfo.getId());
		}   
		if(null != cheManLic){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != cheManLic.getAreaId()) && (0 < cheManLic.getAreaId().trim().length())){
				paraMap.put("areaId", cheManLic.getAreaId().trim());
			}

			if ((null != cheManLic.getCompanyName()) && (0 < cheManLic.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + cheManLic.getCompanyName().trim() + "%");
			}

			if ((null != cheManLic.getRatingAgencies()) && (0 < cheManLic.getRatingAgencies().trim().length())){
				paraMap.put("ratingAgencies", "%" + cheManLic.getRatingAgencies().trim() + "%");
			}

			if (null != queryReceiveDateStart){
				paraMap.put("startReceiveDate", queryReceiveDateStart);
			}

			if (null != queryReceiveDateEnd){
				paraMap.put("endReceiveDate", queryReceiveDateEnd);
			}
			if (null != queryDealDateStart){
				paraMap.put("startDealDate", queryDealDateStart);
			}

			if (null != queryDealDateEnd){
				paraMap.put("endDealDate", queryDealDateEnd);
			}
			if (null != queryLicenseValidStart){
				paraMap.put("startLicenseValid", queryLicenseValidStart);
			}

			if (null != queryLicenseValidEnd){
				paraMap.put("endLicenseValid", queryLicenseValidEnd);
			}
			if ((null != cheManLic.getReceivePerson()) && (0 < cheManLic.getReceivePerson().trim().length())){
				paraMap.put("receivePerson", "%" + cheManLic.getReceivePerson().trim() + "%");
			}

			if ((null != cheManLic.getCheckPerson()) && (0 < cheManLic.getCheckPerson().trim().length())){
				paraMap.put("checkPerson", "%" + cheManLic.getCheckPerson().trim() + "%");
			}
			if ((null != cheManLic.getAuditState()) && (0 < cheManLic.getAuditState().trim().length())){
				paraMap.put("auditState", cheManLic.getAuditState().trim());
			}
		}
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称或评价机构".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|ratingAgencies|receiveDate|dealDate|licenseValid|receivePerson|checkPerson|createUserID|auditState|";
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
		pagination = cheManLicService.findByPage(pagination, paraMap);
		
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

	public CheManLic getCheManLic(){
		return this.cheManLic;
	}

	public void setCheManLic(CheManLic cheManLic){
		this.cheManLic = cheManLic;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryReceiveDateStart(){
		return this.queryReceiveDateStart;
	}

	public void setQueryReceiveDateStart(Date queryReceiveDateStart){
		this.queryReceiveDateStart = queryReceiveDateStart;
	}

	public Date getQueryReceiveDateEnd(){
		return this.queryReceiveDateEnd;
	}

	public void setQueryReceiveDateEnd(Date queryReceiveDateEnd){
		this.queryReceiveDateEnd = queryReceiveDateEnd;
	}

	public Date getQueryDealDateStart(){
		return this.queryDealDateStart;
	}

	public void setQueryDealDateStart(Date queryDealDateStart){
		this.queryDealDateStart = queryDealDateStart;
	}

	public Date getQueryDealDateEnd(){
		return this.queryDealDateEnd;
	}

	public void setQueryDealDateEnd(Date queryDealDateEnd){
		this.queryDealDateEnd = queryDealDateEnd;
	}

	public Date getQueryLicenseValidStart(){
		return this.queryLicenseValidStart;
	}

	public void setQueryLicenseValidStart(Date queryLicenseValidStart){
		this.queryLicenseValidStart = queryLicenseValidStart;
	}

	public Date getQueryLicenseValidEnd(){
		return this.queryLicenseValidEnd;
	}

	public void setQueryLicenseValidEnd(Date queryLicenseValidEnd){
		this.queryLicenseValidEnd = queryLicenseValidEnd;
	}

	public void setPicList2(List<PhotoPic> picList2) {
		this.picList2 = picList2;
	}

	public List<PhotoPic> getPicList2() {
		return picList2;
	}

	public void setPicList3(List<PhotoPic> picList3) {
		this.picList3 = picList3;
	}

	public List<PhotoPic> getPicList3() {
		return picList3;
	}

	public void setPicList4(List<PhotoPic> picList4) {
		this.picList4 = picList4;
	}

	public List<PhotoPic> getPicList4() {
		return picList4;
	}

	public void setPicList5(List<PhotoPic> picList5) {
		this.picList5 = picList5;
	}

	public List<PhotoPic> getPicList5() {
		return picList5;
	}

	public void setPicList6(List<PhotoPic> picList6) {
		this.picList6 = picList6;
	}

	public List<PhotoPic> getPicList6() {
		return picList6;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<CheManLic> getWhpjyxkzList() {
		return whpjyxkzList;
	}

	public void setWhpjyxkzList(List<CheManLic> whpjyxkzList) {
		this.whpjyxkzList = whpjyxkzList;
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

}
