package com.jshx.yhbzxzxk.web;

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
import com.jshx.yhbzxzxk.entity.FirLic;
import com.jshx.yhbzxzxk.service.FirLicService;
import com.xypt.SynchronizeCompanyInfo;
import com.xypt.SynchronizeCompanyInfoService;

public class FirLicAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private FirLic firLic = new FirLic();

	/**
	 * 业务类
	 */
	@Autowired
	private FirLicService firLicService;
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

    private int pageNum=1;
	
	private int totalCount;
		
	private int totalPage;
		
	private List<FirLic> yhbzjyxkzList=new ArrayList<FirLic>();
	
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
	
	/**
	 * 初始化 用于判断审核角色
	 */
	private String roleName;
	public String init(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			if("A02".equals(ur.getRole().getRoleCode())){
				canCheck=true;
			}
			
			if(ur.getRole().getRoleCode().equals("A03")||ur.getRole().getRoleCode().equals("A04")) 
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
		if(null != firLic){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != firLic.getAreaId()) && (0 < firLic.getAreaId().trim().length())){
				paraMap.put("areaId", firLic.getAreaId().trim());
			}

			if ((null != firLic.getCompanyName()) && (0 < firLic.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + firLic.getCompanyName().trim() + "%");
			}

			if ((null != firLic.getItemNo()) && (0 < firLic.getItemNo().trim().length())){
				paraMap.put("itemNo", "%" + firLic.getItemNo().trim() + "%");
			}

			if ((null != firLic.getCheckPerson()) && (0 < firLic.getCheckPerson().trim().length())){
				paraMap.put("checkPerson", "%" + firLic.getCheckPerson().trim() + "%");
			}

			if ((null != firLic.getReceivePerson()) && (0 < firLic.getReceivePerson().trim().length())){
				paraMap.put("receivePerson", "%" + firLic.getReceivePerson().trim() + "%");
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
			if ((null != firLic.getAuditState()) && (0 < firLic.getAuditState().trim().length())){
				paraMap.put("auditState", firLic.getAuditState().trim());
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|itemNo|checkPerson|receivePerson|receiveDate|dealDate|licenseValid|createUserID|auditState|";
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
		pagination = firLicService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != firLic)&&(null != firLic.getId()))
		{
			firLic = firLicService.getById(firLic.getId());
			//审核记录
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("infoId", firLic.getId());
			checkRecords=checkRecordService.findCheckRecord(paraMap);
			if(firLic.getLinkId() == null || "".equals(firLic.getLinkId()))
			{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					firLic.setLinkId(linkId);
				}
				else
				{
					Map map = new HashMap();
					map.put("linkId",firLic.getLinkId());
					map.put("mkType", "yhbzxzxk");
					map.put("picType","xzxk1");
				    picList = photoPicService.findPicPath(map);//获取执法文书材料
				    map.put("picType","xzxk2");
				    picList2 = photoPicService.findPicPath(map);//获取执法文书材料
				    map.put("picType","xzxk3");
				    picList3 = photoPicService.findPicPath(map);//获取执法文书材料
				    map.put("picType","xzxk4");
				    picList4 = photoPicService.findPicPath(map);//获取执法文书材料
				}
			}
		else
		{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			firLic.setLinkId(linkId);
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
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", firLic.getAreaId());
		firLic.setAreaName(codeService.findCodeValueByMap(m)
				.getItemText());
		firLic.setAuditState("待审核");
		if ("add".equalsIgnoreCase(this.flag)){
			firLic.setDeptId(this.getLoginUserDepartmentId());
			firLic.setDelFlag(0);
			firLicService.save(firLic);
		}else{
			firLicService.update(firLic);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != firLic)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到firLic中去
				
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
			firLicService.deleteWithFlag(ids);
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
		firLic=firLicService.getById(firLic.getId());
		if("审核通过并上报信用平台".equals(checkRecord.getCheckResult())){
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
				SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
				EntBaseInfo entBaseInfo = entBaseInfoService.getById(firLic.getCompanyId());
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
				"<InfoClass><![CDATA[安监-烟花爆竹经营许可证登记（变更）审批]]></InfoClass>"+
				"<UploadTime><![CDATA["+sdf.format(new Date())+"]]></UploadTime></Head>"+
				"<Body><FIELDS><![CDATA[组织机构代码,企业全称（中文）,工商注册号/统一社会信用代码,证书编号,主要负责人姓名,注册地址,变更日期,仓库设施地址,许可经营范围,许可证书有效起始日期,许可证书有效截止日期,发证机关名称,发证日期,经济类型]]></FIELDS>"+
				"<DATA>"+
				"<ZZJGDM><![CDATA["+entBaseInfo.getEnterpriseCode()+"]]></ZZJGDM>"+
				"<QYMC><![CDATA["+entBaseInfo.getEnterpriseName() +"]]></QYMC>"+
				"<GSZCH><![CDATA["+entBaseInfo.getRegistrationNumber() +"]]></GSZCH>"+
				"<ZSBH><![CDATA["+firLic.getZsbh()+"]]></ZSBH>"+
				"<ZYFZRXM><![CDATA["+entBaseInfo.getEnterpriseLegalName() +"]]></ZYFZRXM>"+
				"<ZCDZ><![CDATA["+entBaseInfo.getEnterpriseAddress() +"]]></ZCDZ>"+
				"<BGRQ><![CDATA["+sdf1.format(firLic.getBgrq())+"]]></BGRQ>"+
				"<CKSSDZ><![CDATA["+firLic.getCkssdz() +"]]></CKSSDZ>"+
				"<XKJYFW><![CDATA["+firLic.getJyfw() +"]]></XKJYFW>"+
				"<XKZSYXQSRQ><![CDATA["+sdf1.format(firLic.getLicenseValid())+"]]></XKZSYXQSRQ>"+
				"<XKZSYXJZRQ><![CDATA["+sdf1.format(firLic.getLicenseValidEnd())+"]]></XKZSYXJZRQ>"+
				"<FZJGMC><![CDATA["+firLic.getLssuingUnit() +"]]></FZJGMC>"+
				"<FZRQ><![CDATA["+sdf1.format(firLic.getFzrq())+"]]></FZRQ>"+
				"<JJLX><![CDATA["+jjlx+"]]></JJLX>"+
				"</DATA></Body></Request>";
				System.out.println(xml);
				System.out.println(syn.uploadCompanyInfo(xml));
				
				firLic.setAuditState("审核通过");
				firLicService.update(firLic);
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
			firLic.setAuditState(checkRecord.getCheckResult());
			firLicService.update(firLic);
			checkRecord .setCheckUserid(this.getLoginUser().getId());
			checkRecord.setDelFlag(0);
			checkRecord.setCheckUsername(this.getLoginUser().getDisplayName());
			checkRecordService.save(checkRecord);
		}
	    return RELOAD;
	}
	
	public String yhbzjyxkzList()
	{
		yhbzjyxkzList = firLicService.findAllInfo(null, pageNum, 10);
		totalCount = firLicService.findAllInfos(null);
		totalPage = totalCount/10;
		totalPage = totalCount%10==0?totalPage:(totalPage+1);
		return SUCCESS;
	}
	
	public String yhbzjyxkzContent()
	{
		
		if((null != firLic)&&(null != firLic.getId()))
		{
			firLic = firLicService.getById(firLic.getId());
			if(firLic.getLinkId() == null || "".equals(firLic.getLinkId()))
			{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					firLic.setLinkId(linkId);
				}
				else
				{
					Map map = new HashMap();
					map.put("linkId",firLic.getLinkId());
					map.put("mkType", "yhbzxzxk");
					map.put("picType","xzxk1");
				    picList = photoPicService.findPicPath(map);//获取执法文书材料
				    map.put("picType","xzxk2");
				    picList2 = photoPicService.findPicPath(map);//获取执法文书材料
				    map.put("picType","xzxk3");
				    picList3 = photoPicService.findPicPath(map);//获取执法文书材料
				    map.put("picType","xzxk4");
				    picList4 = photoPicService.findPicPath(map);//获取执法文书材料
				}
			}
		else
		{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			firLic.setLinkId(linkId);
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
			
			if(ur.getRole().getRoleCode().equals("A03")||ur.getRole().getRoleCode().equals("A04")) 
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
		if(null != firLic){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != firLic.getAreaId()) && (0 < firLic.getAreaId().trim().length())){
				paraMap.put("areaId", firLic.getAreaId().trim());
			}

			if ((null != firLic.getCompanyName()) && (0 < firLic.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + firLic.getCompanyName().trim() + "%");
			}

			if ((null != firLic.getItemNo()) && (0 < firLic.getItemNo().trim().length())){
				paraMap.put("itemNo", "%" + firLic.getItemNo().trim() + "%");
			}

			if ((null != firLic.getCheckPerson()) && (0 < firLic.getCheckPerson().trim().length())){
				paraMap.put("checkPerson", "%" + firLic.getCheckPerson().trim() + "%");
			}

			if ((null != firLic.getReceivePerson()) && (0 < firLic.getReceivePerson().trim().length())){
				paraMap.put("receivePerson", "%" + firLic.getReceivePerson().trim() + "%");
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
			if ((null != firLic.getAuditState()) && (0 < firLic.getAuditState().trim().length())){
				paraMap.put("auditState", firLic.getAuditState().trim());
			}
		}
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称或档案号".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|itemNo|checkPerson|receivePerson|receiveDate|dealDate|licenseValid|createUserID|auditState|";
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
		pagination = firLicService.findByPage(pagination, paraMap);
		
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

	public FirLic getFirLic(){
		return this.firLic;
	}

	public void setFirLic(FirLic firLic){
		this.firLic = firLic;
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

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	public List<PhotoPic> getPicList() {
		return picList;
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

	public List<FirLic> getYhbzjyxkzList() {
		return yhbzjyxkzList;
	}

	public void setYhbzjyxkzList(List<FirLic> yhbzjyxkzList) {
		this.yhbzjyxkzList = yhbzjyxkzList;
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
