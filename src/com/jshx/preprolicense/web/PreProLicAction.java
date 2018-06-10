package com.jshx.preprolicense.web;

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
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.preprolicense.entity.PreProLic;
import com.jshx.preprolicense.service.PreProLicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.qyjbxx.util.ClientAuthenticationHandler;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.xypt.SynchronizeCompanyInfo;
import com.xypt.SynchronizeCompanyInfoService;

public class PreProLicAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private PreProLic preProLic = new PreProLic();

	/**
	 * 业务类
	 */
	@Autowired
	private PreProLicService preProLicService;
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
	
	
	private Date queryLicenseValidStart;

	private Date queryLicenseValidEnd;

	private Date queryIssuingDateStart;

	private Date queryIssuingDateEnd;

	private Date queryReceptDateStart;

	private Date queryReceptDateEnd;

	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	
	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList4 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList5 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList6 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList7 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList8 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList9 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList10 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList11 = new ArrayList<PhotoPic>();
	
	@Autowired
	private HttpService httpService;
	
	private String operateRight="";
	
	private String roleName;
	
	/**
	 * 审核记录
	 */
	private List<CheckRecord> checkRecords;
	private CheckRecord checkRecord=new CheckRecord();
	private boolean canCheck=false;
	
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
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != preProLic){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != preProLic.getAreaId()) && (0 < preProLic.getAreaId().trim().length())){
				paraMap.put("areaId",  preProLic.getAreaId().trim()  );
			}

			if ((null != preProLic.getCompanyName()) && (0 < preProLic.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + preProLic.getCompanyName().trim() + "%");
			}

			if (null != queryLicenseValidStart){
				paraMap.put("startLicenseValid", queryLicenseValidStart);
			}

			if (null != queryLicenseValidEnd){
				paraMap.put("endLicenseValid", queryLicenseValidEnd);
			}
			if ((null != preProLic.getReviewName()) && (0 < preProLic.getReviewName().trim().length())){
				paraMap.put("reviewName", "%" + preProLic.getReviewName().trim() + "%");
			}

			if (null != queryReceptDateStart){
				paraMap.put("startReceptDate", queryReceptDateStart);
			}

			if (null != queryReceptDateEnd){
				paraMap.put("endReceptDate", queryReceptDateEnd);
			}
			if ((null != preProLic.getReceptName()) && (0 < preProLic.getReceptName().trim().length())){
				paraMap.put("receptName", "%" + preProLic.getReceptName().trim() + "%");
			}

			if (null != queryIssuingDateStart){
				paraMap.put("startIssuingDate", queryIssuingDateStart);
			}

			if (null != queryIssuingDateEnd){
				paraMap.put("endIssuingDate", queryIssuingDateEnd);
			}
			if ((null != preProLic.getFileNo()) && (0 < preProLic.getFileNo().trim().length())){
				paraMap.put("fileNo", "%" + preProLic.getFileNo().trim() + "%");
			}
			
			if ((null != preProLic.getLicenseNumber()) && (0 < preProLic.getLicenseNumber().trim().length())){
				paraMap.put("licenseNumber", "%" + preProLic.getLicenseNumber().trim() + "%");
			}
			if ((null != preProLic.getAuditState()) && (0 < preProLic.getAuditState().trim().length())){
				paraMap.put("auditState", preProLic.getAuditState().trim());
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|licenseNumber|productFlow|licenseValid|issuingAuthority|issuingDate|receptDate|receptName|reviewName|fileNo|createUserID|auditState|";
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
		pagination = preProLicService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != preProLic)&&(null != preProLic.getId())){
			preProLic = preProLicService.getById(preProLic.getId());
			//审核记录
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("infoId", preProLic.getId());
			checkRecords=checkRecordService.findCheckRecord(paraMap);
			if(preProLic.getLinkId() == null || "".equals(preProLic.getLinkId())){
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				preProLic.setLinkId(linkId);
			}else{
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("linkId",preProLic.getLinkId());
				map.put("mkType", "preprolicense");
				map.put("picType","hsjlfj");
				picList1 = photoPicService.findPicPath(map); 
				map.put("mkType", "preprolicense");
				map.put("picType","fyplyzdhxpscxkzsqsfj");
				picList2 = photoPicService.findPicPath(map); 
				map.put("mkType", "preprolicense");
				map.put("picType","gsyyzzfj");
				picList3 = photoPicService.findPicPath(map); 
				map.put("mkType", "preprolicense");
				map.put("picType","yzdhxpglzdaqscglzdfj");
				picList4 = photoPicService.findPicPath(map); 
				map.put("mkType", "preprolicense");
				map.put("picType","dwfddbrhzyfzrhjsglryjbyzdhxpygzsdzmclyjwdpfzjldzmclfj");
				picList5 = photoPicService.findPicPath(map); 
				map.put("mkType", "preprolicense");
				map.put("picType","dwfddbrhzyfzrhaqscglryjaqscjdglbmpxhghbfdaqzgzshzyzgzsfj");
				picList6 = photoPicService.findPicPath(map); 
				map.put("mkType", "preprolicense");
				map.put("picType","hjtfsjyjyafj");
				picList7 = photoPicService.findPicPath(map); 
				map.put("mkType", "preprolicense");
				map.put("picType","bmcpdmchhxmhtymhxfzshcfdcpbzsmshsysmsfj");
				picList8 = photoPicService.findPicPath(map); 
				map.put("mkType", "preprolicense");
				map.put("picType","sywxhxpscqydhxtjwxhxpscyqaaqscxkzhwxhxpdjzfj");
				picList9 = photoPicService.findPicPath(map); 
				map.put("mkType", "preprolicense");
				map.put("picType","scsbccsshwrwclssqksmclfj");
				picList10 = photoPicService.findPicPath(map); 
				map.put("mkType", "preprolicense");
				map.put("picType","flfghgzgddqttjdzmwjzlfj");
				picList11 = photoPicService.findPicPath(map); 
				
			}
		}else{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			preProLic.setLinkId(linkId);
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
		preProLic.setAuditState("待审核");
		if ("add".equalsIgnoreCase(this.flag)){
			preProLic.setDeptId(this.getLoginUserDepartmentId());
			preProLic.setDelFlag(0);
			preProLicService.save(preProLic);
		}else{
			preProLicService.update(preProLic);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != preProLic)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到preProLic中去
				
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
			preProLicService.deleteWithFlag(ids);
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
		preProLic=preProLicService.getById(preProLic.getId());
		if("审核通过并上报信用平台".equals(checkRecord.getCheckResult())){
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
				SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
				EntBaseInfo entBaseInfo = entBaseInfoService.getById(preProLic.getCompanyId());
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
				"<InfoClass><![CDATA[安监-非药品类易制毒化学品生产经营备案（变更）信息]]></InfoClass>"+
				"<UploadTime><![CDATA["+sdf.format(new Date())+"]]></UploadTime></Head>"+
				"<Body><FIELDS><![CDATA[企业全称（中文）,发证机关名称,许可证书有效截止日期,许可证书有效起始日期,产品流向,注册地址,许可内容/产品流向,主要负责人姓名,许可证编号,生产/经营 范围,组织机构代码,发证日期,工商注册号/统一社会信用代码]]></FIELDS>"+
				"<DATA>"+
				"<QYMC><![CDATA["+entBaseInfo.getEnterpriseName()+"]]></QYMC>"+
				"<FZJGMC><![CDATA["+preProLic.getIssuingAuthority()+"]]></FZJGMC>"+
				"<XKZSYXJZRQ><![CDATA["+sdf1.format(preProLic.getLicenseValidEnd())+"]]></XKZSYXJZRQ>"+
				"<XKZSYXQSRQ><![CDATA["+sdf1.format(preProLic.getLicenseValid())+"]]></XKZSYXQSRQ>"+
				"<CPLX><![CDATA["+preProLic.getProductFlow()+"]]></CPLX>"+
				"<ZCDZ><![CDATA["+entBaseInfo.getEnterpriseAddress()+"]]></ZCDZ>"+
				"<XKNR><![CDATA["+preProLic.getLicenseContent()+"]]></XKNR>"+
				"<ZYFZRXM><![CDATA["+entBaseInfo.getEnterpriseLegalName()+"]]></ZYFZRXM>"+
				"<XKZBH><![CDATA["+preProLic.getLicenseNumber()+"]]></XKZBH>"+
				"<SCJYFW><![CDATA["+preProLic.getJyfw()+"]]></SCJYFW>"+
				"<ZZJGDM><![CDATA["+entBaseInfo.getEnterpriseCode()+"]]></ZZJGDM>"+
				"<FZRQ><![CDATA["+sdf1.format(preProLic.getIssuingDate())+"]]></FZRQ>"+
				"<GSZCH><![CDATA["+entBaseInfo.getRegistrationNumber()+"]]></GSZCH>"+
				"</DATA></Body></Request>";
				System.out.println(xml);
				System.out.println(syn.uploadCompanyInfo(xml));
				
				preProLic.setAuditState("审核通过");
				preProLicService.update(preProLic);
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
			preProLic.setAuditState(checkRecord.getCheckResult());
			preProLicService.update(preProLic);
			checkRecord .setCheckUserid(this.getLoginUser().getId());
			checkRecord.setDelFlag(0);
			checkRecord.setCheckUsername(this.getLoginUser().getDisplayName());
			checkRecordService.save(checkRecord);
		}
	    return RELOAD;
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
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != preProLic){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != preProLic.getAreaId()) && (0 < preProLic.getAreaId().trim().length())){
				paraMap.put("areaId",  preProLic.getAreaId().trim()  );
			}

			if ((null != preProLic.getCompanyName()) && (0 < preProLic.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + preProLic.getCompanyName().trim() + "%");
			}

			if (null != queryLicenseValidStart){
				paraMap.put("startLicenseValid", queryLicenseValidStart);
			}

			if (null != queryLicenseValidEnd){
				paraMap.put("endLicenseValid", queryLicenseValidEnd);
			}
			if ((null != preProLic.getReviewName()) && (0 < preProLic.getReviewName().trim().length())){
				paraMap.put("reviewName", "%" + preProLic.getReviewName().trim() + "%");
			}

			if (null != queryReceptDateStart){
				paraMap.put("startReceptDate", queryReceptDateStart);
			}

			if (null != queryReceptDateEnd){
				paraMap.put("endReceptDate", queryReceptDateEnd);
			}
			if ((null != preProLic.getReceptName()) && (0 < preProLic.getReceptName().trim().length())){
				paraMap.put("receptName", "%" + preProLic.getReceptName().trim() + "%");
			}

			if (null != queryIssuingDateStart){
				paraMap.put("startIssuingDate", queryIssuingDateStart);
			}

			if (null != queryIssuingDateEnd){
				paraMap.put("endIssuingDate", queryIssuingDateEnd);
			}
			if ((null != preProLic.getFileNo()) && (0 < preProLic.getFileNo().trim().length())){
				paraMap.put("fileNo", "%" + preProLic.getFileNo().trim() + "%");
			}
			
			if ((null != preProLic.getLicenseNumber()) && (0 < preProLic.getLicenseNumber().trim().length())){
				paraMap.put("licenseNumber", "%" + preProLic.getLicenseNumber().trim() + "%");
			}
			if ((null != preProLic.getAuditState()) && (0 < preProLic.getAuditState().trim().length())){
				paraMap.put("auditState", preProLic.getAuditState().trim());
			}
		}
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称、许可证编号或档案编号".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|licenseNumber|productFlow|licenseValid|issuingAuthority|issuingDate|receptDate|receptName|reviewName|fileNo|createUserID|auditState|";
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
		pagination = preProLicService.findByPage(pagination, paraMap);
		
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

	public PreProLic getPreProLic(){
		return this.preProLic;
	}

	public void setPreProLic(PreProLic preProLic){
		this.preProLic = preProLic;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
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

	public Date getQueryIssuingDateStart(){
		return this.queryIssuingDateStart;
	}

	public void setQueryIssuingDateStart(Date queryIssuingDateStart){
		this.queryIssuingDateStart = queryIssuingDateStart;
	}

	public Date getQueryIssuingDateEnd(){
		return this.queryIssuingDateEnd;
	}

	public void setQueryIssuingDateEnd(Date queryIssuingDateEnd){
		this.queryIssuingDateEnd = queryIssuingDateEnd;
	}

	public Date getQueryReceptDateStart(){
		return this.queryReceptDateStart;
	}

	public void setQueryReceptDateStart(Date queryReceptDateStart){
		this.queryReceptDateStart = queryReceptDateStart;
	}

	public Date getQueryReceptDateEnd(){
		return this.queryReceptDateEnd;
	}

	public void setQueryReceptDateEnd(Date queryReceptDateEnd){
		this.queryReceptDateEnd = queryReceptDateEnd;
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

	public List<PhotoPic> getPicList4() {
		return picList4;
	}

	public void setPicList4(List<PhotoPic> picList4) {
		this.picList4 = picList4;
	}

	public List<PhotoPic> getPicList5() {
		return picList5;
	}

	public void setPicList5(List<PhotoPic> picList5) {
		this.picList5 = picList5;
	}

	public List<PhotoPic> getPicList6() {
		return picList6;
	}

	public void setPicList6(List<PhotoPic> picList6) {
		this.picList6 = picList6;
	}

	public List<PhotoPic> getPicList7() {
		return picList7;
	}

	public void setPicList7(List<PhotoPic> picList7) {
		this.picList7 = picList7;
	}

	public List<PhotoPic> getPicList8() {
		return picList8;
	}

	public void setPicList8(List<PhotoPic> picList8) {
		this.picList8 = picList8;
	}

	public List<PhotoPic> getPicList9() {
		return picList9;
	}

	public void setPicList9(List<PhotoPic> picList9) {
		this.picList9 = picList9;
	}

	public List<PhotoPic> getPicList10() {
		return picList10;
	}

	public void setPicList10(List<PhotoPic> picList10) {
		this.picList10 = picList10;
	}

	public List<PhotoPic> getPicList11() {
		return picList11;
	}

	public void setPicList11(List<PhotoPic> picList11) {
		this.picList11 = picList11;
	}
	public String getOperateRight() {
		return operateRight;
	}
	public void setOperateRight(String operateRight) {
		this.operateRight = operateRight;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
