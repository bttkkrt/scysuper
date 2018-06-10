package com.jshx.cheprolicense.web;

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

import com.jshx.cheprolicense.entity.CheProLic;
import com.jshx.cheprolicense.service.CheProLicService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.qyjbxx.util.ClientAuthenticationHandler;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.xypt.SynchronizeCompanyInfo;
import com.xypt.SynchronizeCompanyInfoService;

public class CheProLicAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private CheProLic cheProLic = new CheProLic();

	/**
	 * 业务类
	 */
	@Autowired
	private CheProLicService cheProLicService;
	
	@Autowired
	private PhotoPicService photoPicService;
	
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private HttpService httpService;
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

	private Date querySubmitDateStart;

	private Date querySubmitDateEnd;

	private Date queryReceptDateStart;

	private Date queryReceptDateEnd;

    private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();//危险化学品生产许可证副本
	
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();//核查结论
	
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();//会审记录
	
	private List<PhotoPic> picList4 = new ArrayList<PhotoPic>();//危险化学品生产许可证申请书
	
	private List<PhotoPic> picList5 = new ArrayList<PhotoPic>();//危险化学品生产许可证申请材料
	
	private List<PhotoPic> picList6 = new ArrayList<PhotoPic>();//安全评价机构出具的安全评价报告
	
	private List<PhotoPic> picList7 = new ArrayList<PhotoPic>();//危化品生产企业安全生产许可证现场核查表
	
	private List<PhotoPic> picList8 = new ArrayList<PhotoPic>();//危化品生产企业安全生产许可证现场审查表
	
	private List<PhotoPic> picList9 = new ArrayList<PhotoPic>();//申请材料登记表
	
	private List<PhotoPic> picList10 = new ArrayList<PhotoPic>();//集体会审记录
	
	private int pageNum=1;
	
	private int totalCount;
		
	private int totalPage;
		
	private List<CheProLic> whpaqscxkList=new ArrayList<CheProLic>();
	
	/**
	 * 审核记录
	 */
	private List<CheckRecord> checkRecords;
	private CheckRecord checkRecord=new CheckRecord();
	private boolean canCheck=false;
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
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
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != cheProLic){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != cheProLic.getAreaId()) && (0 < cheProLic.getAreaId().trim().length())){
				paraMap.put("areaId",  cheProLic.getAreaId().trim() );
			}
			if ((null != cheProLic.getAreaName()) && (0 < cheProLic.getAreaName().trim().length())){
				paraMap.put("areaName", "%" + cheProLic.getAreaName().trim() + "%");
			}

			if ((null != cheProLic.getCompanyName()) && (0 < cheProLic.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + cheProLic.getCompanyName().trim() + "%");
			}

			if ((null != cheProLic.getRatingAgenciesName()) && (0 < cheProLic.getRatingAgenciesName().trim().length())){
				paraMap.put("ratingAgenciesName", "%" + cheProLic.getRatingAgenciesName().trim() + "%");
			}

			if ((null != cheProLic.getIssuingAuthority()) && (0 < cheProLic.getIssuingAuthority().trim().length())){
				paraMap.put("issuingAuthority", "%" + cheProLic.getIssuingAuthority().trim() + "%");
			}

			if ((null != cheProLic.getIsComplete()) && (0 < cheProLic.getIsComplete().trim().length())){
				paraMap.put("isComplete", cheProLic.getIsComplete().trim());
			}

			if (null != queryReceptDateStart){
				paraMap.put("startReceptDate", queryReceptDateStart);
			}

			if (null != queryReceptDateEnd){
				paraMap.put("endReceptDate", queryReceptDateEnd);
			}
			if ((null != cheProLic.getReceptName()) && (0 < cheProLic.getReceptName().trim().length())){
				paraMap.put("receptName", "%" + cheProLic.getReceptName().trim() + "%");
			}

			if ((null != cheProLic.getReviewName()) && (0 < cheProLic.getReviewName().trim().length())){
				paraMap.put("reviewName", "%" + cheProLic.getReviewName().trim() + "%");
			}

			if ((null != cheProLic.getFileNo()) && (0 < cheProLic.getFileNo().trim().length())){
				paraMap.put("fileNo", "%" + cheProLic.getFileNo().trim() + "%");
			}

			if (null != queryIssuingDateStart){
				paraMap.put("startIssuingDate", queryIssuingDateStart);
			}

			if (null != queryIssuingDateEnd){
				paraMap.put("endIssuingDate", queryIssuingDateEnd);
			}
			if ((null != cheProLic.getAuditState()) && (0 < cheProLic.getAuditState().trim().length())){
				paraMap.put("auditState", cheProLic.getAuditState().trim());
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("isComplete","402809812e7f8c28012e7fa82239000c");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|ratingAgenciesName|licenseNumber|issuingAuthority|issuingDate|receptDate|receptName|reviewName|fileNo|isComplete|createUserID|auditState|";
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
		pagination = cheProLicService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != cheProLic)&&(null != cheProLic.getId()))
		{
			cheProLic = cheProLicService.getById(cheProLic.getId());
			//审核记录
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("infoId", cheProLic.getId());
			checkRecords=checkRecordService.findCheckRecord(paraMap);
				if(cheProLic.getLinkId() == null || "".equals(cheProLic.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						cheProLic.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",cheProLic.getLinkId());
							map.put("mkType", "cheprolicense1");
							map.put("picType","cheprolicensefj1");
							picList1 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense2");
							map.put("picType","cheprolicensefj2");
							picList2 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense3");
							map.put("picType","cheprolicensefj3");
							picList3 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense4");
							map.put("picType","cheprolicensefj4");
							picList4 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense5");
							map.put("picType","cheprolicensefj5");
							picList5 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense6");
							map.put("picType","cheprolicensefj6");
							picList6 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense7");
							map.put("picType","cheprolicensefj7");
							picList7 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense8");
							map.put("picType","cheprolicensefj8");
							picList8 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense9");
							map.put("picType","cheprolicensefj9");
							picList9 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense10");
							map.put("picType","cheprolicensefj10");
							picList10 = photoPicService.findPicPath(map);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					cheProLic.setLinkId(linkId);
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
		m.put("itemValue", cheProLic.getAreaId());
		cheProLic.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		cheProLic.setAuditState("待审核");
		if ("add".equalsIgnoreCase(this.flag)){
			cheProLic.setDeptId(this.getLoginUserDepartmentId());
			cheProLic.setDelFlag(0);
			cheProLicService.save(cheProLic);
		}else{
			cheProLicService.update(cheProLic);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != cheProLic)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到cheProLic中去
				
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
			cheProLicService.deleteWithFlag(ids);
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
		cheProLic=cheProLicService.getById(cheProLic.getId());
		if("审核通过并上报信用平台".equals(checkRecord.getCheckResult())){
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
				SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
				EntBaseInfo entBaseInfo = entBaseInfoService.getById(cheProLic.getCompanyId());
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
				"<InfoClass><![CDATA[安监-危险化学品生产企业安全生产许可证审批（变更）信息]]></InfoClass>"+
				"<UploadTime><![CDATA["+sdf.format(new Date())+"]]></UploadTime></Head>"+
				"<Body><FIELDS><![CDATA[发证机关名称,许可证书有效截止日期,许可证书有效起始日期,企业生产地址,企业注册地址,经营范围,经济类型,主要负责人姓名,许可证编号,工商注册号/统一社会信用代码,企业全称（中文）,发证日期,组织机构代码]]></FIELDS>"+
				"<DATA>"+
				"<FZJGMC><![CDATA["+cheProLic.getIssuingAuthority()+"]]></FZJGMC>"+
				"<XKZSYXJZRQ><![CDATA["+sdf1.format(cheProLic.getLicenseValidEnd())+"]]></XKZSYXJZRQ>"+
				"<XKZSYXQSRQ><![CDATA["+sdf1.format(cheProLic.getLicenseValid())+"]]></XKZSYXQSRQ>"+
				"<QYSCDZ><![CDATA["+entBaseInfo.getEnterpriseAddress() +"]]></QYSCDZ>"+
				"<QYZCDZ><![CDATA["+entBaseInfo.getEnterpriseAddress()  +"]]></QYZCDZ>"+
				"<JYFW><![CDATA["+cheProLic.getJyfw() +"]]></JYFW>"+
				"<JJLX><![CDATA["+jjlx +"]]></JJLX>"+
				"<ZYFZRXM><![CDATA["+entBaseInfo.getEnterpriseLegalName() +"]]></ZYFZRXM>"+
				"<XKZBH><![CDATA["+cheProLic.getLicenseNumber() +"]]></XKZBH>"+
				"<GSZCH><![CDATA["+entBaseInfo.getRegistrationNumber() +"]]></GSZCH>"+
				"<QYMC><![CDATA["+entBaseInfo.getEnterpriseName() +"]]></QYMC>"+
				"<FZRQ><![CDATA["+sdf1.format(cheProLic.getIssuingDate())+"]]></FZRQ>"+
				"<ZZJGDM><![CDATA["+entBaseInfo.getEnterpriseCode()+"]]></ZZJGDM>"+
				"</DATA></Body></Request>";
				System.out.println(xml);
				System.out.println(syn.uploadCompanyInfo(xml));
				
				cheProLic.setAuditState("审核通过");
				cheProLicService.update(cheProLic);
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
			cheProLic.setAuditState(checkRecord.getCheckResult());
			cheProLicService.update(cheProLic);
			checkRecord .setCheckUserid(this.getLoginUser().getId());
			checkRecord.setDelFlag(0);
			checkRecord.setCheckUsername(this.getLoginUser().getDisplayName());
			checkRecordService.save(checkRecord);
		}
	    return RELOAD;
	}
	
	public String whpaqscxkList()
	{
		whpaqscxkList = cheProLicService.findAllInfo(null, pageNum, 10);
		totalCount = cheProLicService.findAllInfos(null);
		totalPage = totalCount/10;
		totalPage = totalCount%10==0?totalPage:(totalPage+1);
		return SUCCESS;
	}
	
	public String whpaqscxkContent()
	{
		
		if((null != cheProLic)&&(null != cheProLic.getId()))
		{
			cheProLic = cheProLicService.getById(cheProLic.getId());
				if(cheProLic.getLinkId() == null || "".equals(cheProLic.getLinkId()))
				{
						String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
						cheProLic.setLinkId(linkId);
					}
					else
					{
						try {
							Map map = new HashMap();
							map.put("linkId",cheProLic.getLinkId());
							map.put("mkType", "cheprolicense1");
							map.put("picType","cheprolicensefj1");
							picList1 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense2");
							map.put("picType","cheprolicensefj2");
							picList2 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense3");
							map.put("picType","cheprolicensefj3");
							picList3 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense4");
							map.put("picType","cheprolicensefj4");
							picList4 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense5");
							map.put("picType","cheprolicensefj5");
							picList5 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense6");
							map.put("picType","cheprolicensefj6");
							picList6 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense7");
							map.put("picType","cheprolicensefj7");
							picList7 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense8");
							map.put("picType","cheprolicensefj8");
							picList8 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense9");
							map.put("picType","cheprolicensefj9");
							picList9 = photoPicService.findPicPath(map);
							map.put("mkType", "cheprolicense10");
							map.put("picType","cheprolicensefj10");
							picList10 = photoPicService.findPicPath(map);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					cheProLic.setLinkId(linkId);
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
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != cheProLic){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != cheProLic.getAreaId()) && (0 < cheProLic.getAreaId().trim().length())){
				paraMap.put("areaId",  cheProLic.getAreaId().trim() );
			}
			if ((null != cheProLic.getAreaName()) && (0 < cheProLic.getAreaName().trim().length())){
				paraMap.put("areaName", "%" + cheProLic.getAreaName().trim() + "%");
			}

			if ((null != cheProLic.getCompanyName()) && (0 < cheProLic.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + cheProLic.getCompanyName().trim() + "%");
			}

			if ((null != cheProLic.getRatingAgenciesName()) && (0 < cheProLic.getRatingAgenciesName().trim().length())){
				paraMap.put("ratingAgenciesName", "%" + cheProLic.getRatingAgenciesName().trim() + "%");
			}

			if ((null != cheProLic.getIssuingAuthority()) && (0 < cheProLic.getIssuingAuthority().trim().length())){
				paraMap.put("issuingAuthority", "%" + cheProLic.getIssuingAuthority().trim() + "%");
			}

			if ((null != cheProLic.getIsComplete()) && (0 < cheProLic.getIsComplete().trim().length())){
				paraMap.put("isComplete", cheProLic.getIsComplete().trim());
			}

			if (null != queryReceptDateStart){
				paraMap.put("startReceptDate", queryReceptDateStart);
			}

			if (null != queryReceptDateEnd){
				paraMap.put("endReceptDate", queryReceptDateEnd);
			}
			if ((null != cheProLic.getReceptName()) && (0 < cheProLic.getReceptName().trim().length())){
				paraMap.put("receptName", "%" + cheProLic.getReceptName().trim() + "%");
			}

			if ((null != cheProLic.getReviewName()) && (0 < cheProLic.getReviewName().trim().length())){
				paraMap.put("reviewName", "%" + cheProLic.getReviewName().trim() + "%");
			}

			if ((null != cheProLic.getFileNo()) && (0 < cheProLic.getFileNo().trim().length())){
				paraMap.put("fileNo", "%" + cheProLic.getFileNo().trim() + "%");
			}

			if (null != queryIssuingDateStart){
				paraMap.put("startIssuingDate", queryIssuingDateStart);
			}

			if (null != queryIssuingDateEnd){
				paraMap.put("endIssuingDate", queryIssuingDateEnd);
			}
			if ((null != cheProLic.getAuditState()) && (0 < cheProLic.getAuditState().trim().length())){
				paraMap.put("auditState", cheProLic.getAuditState().trim());
			}
		}
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称或评价机构名称".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("isComplete","402809812e7f8c28012e7fa82239000c");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|ratingAgenciesName|licenseNumber|issuingAuthority|issuingDate|receptDate|receptName|reviewName|fileNo|isComplete|createUserID|auditState|";
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
		pagination = cheProLicService.findByPage(pagination, paraMap);
		
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

	public CheProLic getCheProLic(){
		return this.cheProLic;
	}

	public void setCheProLic(CheProLic cheProLic){
		this.cheProLic = cheProLic;
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

	public Date getQuerySubmitDateStart(){
		return this.querySubmitDateStart;
	}

	public void setQuerySubmitDateStart(Date querySubmitDateStart){
		this.querySubmitDateStart = querySubmitDateStart;
	}

	public Date getQuerySubmitDateEnd(){
		return this.querySubmitDateEnd;
	}

	public void setQuerySubmitDateEnd(Date querySubmitDateEnd){
		this.querySubmitDateEnd = querySubmitDateEnd;
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

	public List<CheProLic> getWhpaqscxkList() {
		return whpaqscxkList;
	}

	public void setWhpaqscxkList(List<CheProLic> whpaqscxkList) {
		this.whpaqscxkList = whpaqscxkList;
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
