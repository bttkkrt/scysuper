package com.jshx.zzqk.web;

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

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
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
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.zzqk.entity.IntSit;
import com.jshx.zzqk.service.IntSitService;
import com.lkdj.kzk.GetTkzxxRequest;
import com.lkdj.kzk.GetTkzxxResponse;
import com.lkdj.kzk.TkzxxPort;
import com.lkdj.kzk.TkzxxPortService;
import com.lkdj.kzk.GetTkzxxRequest.Tkzxxs;
import com.lkdj.lkLog.entity.LkLog;
import com.lkdj.lkLog.service.LkLogService;
import com.lkdj.util.ceateTkzxxUtil;
import com.xypt.SynchronizeCompanyInfo;
import com.xypt.SynchronizeCompanyInfoService;
public class IntSitAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private IntSit intSit = new IntSit();

	/**
	 * 业务类
	 */
	@Autowired
	private IntSitService intSitService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryIntelligenceCardDateStart;

	private Date queryIntelligenceCardDateEnd;

	private Date queryIntelligenceValidityStartStart;

	private Date queryIntelligenceValidityStartEnd;

	private Date queryIntelligenceValidityEndStart;

	private Date queryIntelligenceValidityEndEnd;
	@Autowired
	private HttpService httpService;
	@Autowired
	private LkLogService lkLogService;
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private CheckRecordService checkRecordService;
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
	private String roleName;
	
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;
	
	private String companyId;
	
	private String entBaseInfoId;
	
	/**
	 * 初始化 用于判断审核角色
	 */
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
		try {
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
			String url = ServletActionContext.getRequest().getRequestURL().toString();
			if(StringUtils.isNotEmpty(entBaseInfoId)){
				paraMap.put("companyId", "entBaseInfoId");
			}
			if (pagination == null)
				pagination = new Pagination(this.getRequest());
			if (null != intSit) {
				//设置查询条件，开发人员可以在此增加过滤条件
				if ((null != intSit.getAreaId())
						&& (0 < intSit.getAreaId().trim().length())) {
					paraMap.put("areaId", intSit.getAreaId().trim());
				}

				if ((null != intSit.getAreaName())
						&& (0 < intSit.getAreaName().trim().length())) {
					paraMap.put("areaName", intSit.getAreaName().trim());
				}

				if ((null != intSit.getCompanyName())
						&& (0 < intSit.getCompanyName().trim().length())) {
					paraMap.put("companyName", "%"
							+ intSit.getCompanyName().trim() + "%");
				}
				
				if ((null != intSit.getCompanyId())
						&& (0 < intSit.getCompanyId().trim().length())) {
					paraMap.put("companyId",  intSit.getCompanyId().trim()  );
				}

				if ((null != intSit.getIntelligenceCardnum())
						&& (0 < intSit.getIntelligenceCardnum().trim().length())) {
					paraMap.put("intelligenceCardnum", "%"
							+ intSit.getIntelligenceCardnum().trim() + "%");
				}

				if ((null != intSit.getIntelligenceCardname())
						&& (0 < intSit.getIntelligenceCardname().trim()
								.length())) {
					paraMap.put("intelligenceCardname", "%"
							+ intSit.getIntelligenceCardname().trim() + "%");
				}

				if ((null != intSit.getIntelligenceType())
						&& (0 < intSit.getIntelligenceType().trim().length())) {
					paraMap.put("intelligenceType", intSit
							.getIntelligenceType().trim());
				}
				if ((null != intSit.getAuditResult())
						&& (0 < intSit.getAuditResult().trim().length())) {
					paraMap.put("auditResult", "%"
							+ intSit.getAuditResult().trim() + "%");
				}
				if ((null != intSit.getAuditState())
						&& (0 < intSit.getAuditState().trim().length())) {
					paraMap.put("auditState", intSit.getAuditState().trim());
				}

			}
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(java.util.Date.class,
					new DateJsonValueProcessor());
			Map<String, String> codeMap = new HashMap<String, String>();
			//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
			codeMap.put("intelligenceType", "40288008416c6c1a01416ccf6ab100a7");
			config.registerJsonValueProcessor(String.class,
					new CodeJsonValueProcessor(codeMap));
			final String filter = "id|areaName|companyName|intelligenceCardnum|intelligenceCardname|intelligenceType|auditResult|auditState|createUserID|";
			if (filter != null && filter.length() > 1) {
				config.setJsonPropertyFilter(new PropertyFilter() {
					public boolean apply(Object source, String name,
							Object value) {
						if (filter.indexOf(name + "|") != -1)
							return false;
						else
							return true;
					}
				});
			}
			pagination = intSitService.findByPage(pagination, paraMap);
			convObjectToJson(pagination, config);
		} catch (Exception e) {e.printStackTrace();
			// TODO: handle exception
		}
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != intSit)&&(null != intSit.getId()))
		{
			intSit = intSitService.getById(intSit.getId());
			if(intSit.getLinkId() == null || "".equals(intSit.getLinkId()))
			{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					intSit.setLinkId(linkId);
				}
				else
				{
					Map map = new HashMap();
					map.put("linkId",intSit.getLinkId());
					map.put("mkType", "zzqk");
					map.put("picType","zzfj");
				    picList = photoPicService.findPicPath(map);//获取执法文书材料
				}
			}
			else
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				intSit.setLinkId(linkId);
			}
		//审核记录
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("infoId", intSit.getId());
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
		intSit.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		intSit.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		intSit.setCompanyId(enBaseInfo.getId());
		intSit.setCompanyName(enBaseInfo.getEnterpriseName());
		if ("add".equalsIgnoreCase(this.flag)){
			intSit.setDeptId(this.getLoginUserDepartmentId());
			intSit.setDelFlag(0);
			intSitService.save(intSit);
			
		}else{
			intSitService.update(intSit);
		}
		
		
		try {
			String zjhm = NullToString(enBaseInfo.getRegistrationNumber()).replaceAll(" ", "");
			if(zjhm != null && !"".equals(zjhm))
			{
				LkLog log1 = new LkLog();
				log1.setNrid(intSit.getId());
				log1.setLb("intSit扩展信息");
				//对接资质情况
				GetTkzxxRequest kzqq = new GetTkzxxRequest();
				ceateTkzxxUtil ceateTkzxxUtil = new ceateTkzxxUtil();
				JSONArray ja = new JSONArray();
				String intelligenceCardDate = intSit.getIntelligenceCardDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(intSit.getIntelligenceCardDate()):"";
				String intelligenceValidityStart = intSit.getIntelligenceValidityStart() != null?new SimpleDateFormat("yyyy-MM-dd").format(intSit.getIntelligenceValidityStart()):"";
				String intelligenceValidityEnd = intSit.getIntelligenceValidityEnd() != null?new SimpleDateFormat("yyyy-MM-dd").format(intSit.getIntelligenceValidityEnd()):"" ;
				String changeDate = intSit.getChangeDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(intSit.getChangeDate()):"" ;
				ja.add((new JSONObject()).put("QYMC" , NullToString(intSit.getCompanyName())));//企业名称
				ja.add((new JSONObject()).put("ZSBH" , NullToString(intSit.getIntelligenceCardnum())));//证书编号
				ja.add((new JSONObject()).put("ZSMC" , NullToString(intSit.getIntelligenceCardname())));//证书名称
				ja.add((new JSONObject()).put("ZZLX" , zzlx(intSit.getIntelligenceType())));//确认类型
				ja.add((new JSONObject()).put("ZZLR" , NullToString(intSit.getIntelligenceContent()) ));//资质内容
				ja.add((new JSONObject()).put("FZJG" , NullToString(intSit.getIntelligenceInstitution())));//发证机关
				ja.add((new JSONObject()).put("FZRQ" ,intelligenceCardDate ));//发证日期
				ja.add((new JSONObject()).put("YXQQSRQ" , intelligenceValidityStart));//有效期开始日期
				ja.add((new JSONObject()).put("YXQJZRQ" ,intelligenceValidityEnd));//有效期截止时间
				ja.add((new JSONObject()).put("ZZJB" ,NullToString(intSit.getZzjb())));//资质级别
				ja.add((new JSONObject()).put("BGRQ" ,changeDate));//变更日期
				ja.add((new JSONObject()).put("YWFW" ,NullToString(intSit.getBussinessScope())));//业务范围
				JSONObject jo = new JSONObject();
				jo.put("frkzxx", ja.toString());
				String kzxx = jo.toString();
				Tkzxxs tkzxxs = ceateTkzxxUtil.ceateTkzxxs("zzqkb",intSit.getCreateTime(), kzxx, "",zjhm);
				kzqq.getTkzxxs().add(tkzxxs);
				
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
				System.out.println("资质情况"+getTkzxxResponse.getMsg());
				log1.setResult(getTkzxxResponse.getMsg());
				lkLogService.save(log1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		intSit=intSitService.getById(intSit.getId());
		if("审核通过并上报信用平台".equals(checkRecord.getCheckResult())){
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
				SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
				EntBaseInfo entBaseInfo = entBaseInfoService.getById(intSit.getCompanyId());
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
				map.put("codeName", "资质类型");
				map.put("itemValue", intSit.getIntelligenceType());
				String zzlx=(null==codeService.findCodeValueByMap(map).getItemText()?"":codeService.findCodeValueByMap(map).getItemText());
				String a=(null==intSit.getChangeDate()?"":sdf1.format(intSit.getChangeDate()));
				String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request><Head>"+
				"<DeptID><![CDATA[54]]></DeptID>"+
				"<InfoClass><![CDATA[安监-机构资质认定（变更）信息]]></InfoClass>"+
				"<UploadTime><![CDATA["+sdf.format(new Date())+"]]></UploadTime></Head>"+
				"<Body><FIELDS><![CDATA[发证日期,变更日期,组织机构代码,企业全称（中文）,工商注册号/统一社会信用代码,资质名称,资质证书编号,法定代表人证件号码,资质级别,业务范围,注册地址,证书有效起始日期,证书有效截止日期,发证机关名称]]></FIELDS>"+
				"<DATA>"+
				"<FZRQ><![CDATA["+sdf1.format(intSit.getIntelligenceCardDate())+"]]></FZRQ>"+
				"<BGRQ><![CDATA["+a+"]]></BGRQ>"+
				"<ZZJGDM><![CDATA["+entBaseInfo.getEnterpriseCode()+"]]></ZZJGDM>"+
				"<QYMC><![CDATA["+entBaseInfo.getEnterpriseName() +"]]></QYMC>"+
				"<GSZCH><![CDATA["+entBaseInfo.getRegistrationNumber() +"]]></GSZCH>"+
				"<ZZMC><![CDATA["+zzlx +"]]></ZZMC>"+
				"<ZZZSBH><![CDATA["+intSit.getIntelligenceCardnum() +"]]></ZZZSBH>"+
				"<FDDBRZJHM><![CDATA["+entBaseInfo.getEnterpriseLegalCardnum() +"]]></FDDBRZJHM>"+
				"<ZZJB><![CDATA["+intSit.getZzjb() +"]]></ZZJB>"+
				"<YWFW><![CDATA["+intSit.getBussinessScope() +"]]></YWFW>"+
				"<ZCDZ><![CDATA["+entBaseInfo.getEnterpriseAddress() +"]]></ZCDZ>"+
				"<ZSYXQSRQ><![CDATA["+sdf1.format(intSit.getIntelligenceValidityStart())+"]]></ZSYXQSRQ>"+
				"<ZSYXJZRQ><![CDATA["+intSit.getIntelligenceValidityEnd() != null && !"".equals(intSit.getIntelligenceValidityEnd())?sdf1.format(intSit.getIntelligenceValidityEnd()):""+"]]></ZSYXJZRQ>"+
				"<FZJGMC><![CDATA["+intSit.getIntelligenceInstitution()+"]]></FZJGMC>"+
				"</DATA></Body></Request>";
				System.out.println(xml);
				System.out.println(syn.uploadCompanyInfo(xml));
				
				
				intSit.setAuditState("审核通过");
				intSit.setCheckRemark(checkRecord.getCheckRemark());
				intSitService.update(intSit);
				
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
			intSit.setAuditState(checkRecord.getCheckResult());
			intSit.setCheckRemark(checkRecord.getCheckRemark());
			intSitService.update(intSit);
			
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
		if (null != intSit)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到intSit中去
				
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
			intSitService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	public String zwtInit(){
		
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
		try {
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
			if (pagination == null)
				pagination = new Pagination(this.getRequest());
			if (null != intSit) {
				//设置查询条件，开发人员可以在此增加过滤条件
				if ((null != intSit.getAreaId())
						&& (0 < intSit.getAreaId().trim().length())) {
					paraMap.put("areaId", intSit.getAreaId().trim());
				}
				if ((null != intSit.getCompanyId())
						&& (0 < intSit.getCompanyId().trim().length())) {
					paraMap.put("companyId", intSit.getCompanyId().trim());
				}

				if ((null != intSit.getAreaName())
						&& (0 < intSit.getAreaName().trim().length())) {
					paraMap.put("areaName", intSit.getAreaName().trim());
				}

				if ((null != intSit.getCompanyName())
						&& (0 < intSit.getCompanyName().trim().length())) {
					paraMap.put("companyName", "%"
							+ intSit.getCompanyName().trim() + "%");
				}

				if ((null != intSit.getIntelligenceCardnum())
						&& (0 < intSit.getIntelligenceCardnum().trim().length())) {
					paraMap.put("intelligenceCardnum", "%"
							+ intSit.getIntelligenceCardnum().trim() + "%");
				}

				if ((null != intSit.getIntelligenceCardname())
						&& (0 < intSit.getIntelligenceCardname().trim()
								.length())) {
					paraMap.put("intelligenceCardname", "%"
							+ intSit.getIntelligenceCardname().trim() + "%");
				}

				if ((null != intSit.getIntelligenceType())
						&& (0 < intSit.getIntelligenceType().trim().length())) {
					paraMap.put("intelligenceType", intSit
							.getIntelligenceType().trim());
				}
				if ((null != intSit.getAuditResult())
						&& (0 < intSit.getAuditResult().trim().length())) {
					paraMap.put("auditResult", "%"
							+ intSit.getAuditResult().trim() + "%");
				}
				if ((null != intSit.getAuditState())
						&& (0 < intSit.getAuditState().trim().length())) {
					paraMap.put("auditState", intSit.getAuditState().trim());
				}

			}
			
			
			if(null!=companyId&&!"".equals(companyId)){
				paraMap.put("companyId",   companyId.trim() );
			}
			if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称、证书名称或编号".equals(searchLike)){
				paraMap.put("searchLike", "%" + searchLike.trim() + "%");
			}
			
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(java.util.Date.class,
					new DateJsonValueProcessor());
			Map<String, String> codeMap = new HashMap<String, String>();
			//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
			codeMap.put("intelligenceType", "40288008416c6c1a01416ccf6ab100a7");
			config.registerJsonValueProcessor(String.class,
					new CodeJsonValueProcessor(codeMap));
			final String filter = "id|areaName|companyName|intelligenceCardnum|intelligenceCardname|intelligenceType|auditResult|auditState|";
			if (filter != null && filter.length() > 1) {
				config.setJsonPropertyFilter(new PropertyFilter() {
					public boolean apply(Object source, String name,
							Object value) {
						if (filter.indexOf(name + "|") != -1)
							return false;
						else
							return true;
					}
				});
			}
			pagination.setPageNumber(pageNo);
			pagination.setPageSize(pageSize);
			pagination = intSitService.findByPage(pagination, paraMap);
			
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
		} catch (Exception e) {e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public String zzqk(){
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

	public IntSit getIntSit(){
		return this.intSit;
	}

	public void setIntSit(IntSit intSit){
		this.intSit = intSit;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryIntelligenceCardDateStart(){
		return this.queryIntelligenceCardDateStart;
	}

	public void setQueryIntelligenceCardDateStart(Date queryIntelligenceCardDateStart){
		this.queryIntelligenceCardDateStart = queryIntelligenceCardDateStart;
	}

	public Date getQueryIntelligenceCardDateEnd(){
		return this.queryIntelligenceCardDateEnd;
	}

	public void setQueryIntelligenceCardDateEnd(Date queryIntelligenceCardDateEnd){
		this.queryIntelligenceCardDateEnd = queryIntelligenceCardDateEnd;
	}

	public Date getQueryIntelligenceValidityStartStart(){
		return this.queryIntelligenceValidityStartStart;
	}

	public void setQueryIntelligenceValidityStartStart(Date queryIntelligenceValidityStartStart){
		this.queryIntelligenceValidityStartStart = queryIntelligenceValidityStartStart;
	}

	public Date getQueryIntelligenceValidityStartEnd(){
		return this.queryIntelligenceValidityStartEnd;
	}

	public void setQueryIntelligenceValidityStartEnd(Date queryIntelligenceValidityStartEnd){
		this.queryIntelligenceValidityStartEnd = queryIntelligenceValidityStartEnd;
	}

	public Date getQueryIntelligenceValidityEndStart(){
		return this.queryIntelligenceValidityEndStart;
	}

	public void setQueryIntelligenceValidityEndStart(Date queryIntelligenceValidityEndStart){
		this.queryIntelligenceValidityEndStart = queryIntelligenceValidityEndStart;
	}

	public Date getQueryIntelligenceValidityEndEnd(){
		return this.queryIntelligenceValidityEndEnd;
	}

	public void setQueryIntelligenceValidityEndEnd(Date queryIntelligenceValidityEndEnd){
		this.queryIntelligenceValidityEndEnd = queryIntelligenceValidityEndEnd;
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public void sendInfo()
	{
		
		try {
			//对接资质情况
			List<IntSit> zzlist = intSitService.findIntSits(null);
			int total1 = zzlist.size();
			int num1 = total1/100;
			int ys1 = total1%100;
			if(ys1 != 0)
			{
				num1 = num1 + 1;
			}
			for(int i=0;i<num1;i++)
			{
				int start = 100*i;
				int end = 100*(i+1);
				if(end > total1)
				{
					end = total1;
				}
				GetTkzxxRequest kzqq = new GetTkzxxRequest();
				ceateTkzxxUtil ceateTkzxxUtil = new ceateTkzxxUtil();
				for(int j=start;j<end;j++)
				{
					IntSit zzqk = zzlist.get(j);
					if(zzqk.getCompanyId() != null && !"".equals(zzqk.getCompanyId()))
					{
						EntBaseInfo ent = entBaseInfoService.getById(zzqk.getCompanyId());
						if(ent != null)
						{
							//对接资质情况
							String zjhm = NullToString(ent.getRegistrationNumber()).replaceAll(" ", "");
							if(zjhm != null && !"".equals(zjhm))
							{
								//对接资质情况
								JSONArray ja = new JSONArray();
								String intelligenceCardDate = zzqk.getIntelligenceCardDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(zzqk.getIntelligenceCardDate()):"";
								String intelligenceValidityStart = zzqk.getIntelligenceValidityStart() != null?new SimpleDateFormat("yyyy-MM-dd").format(zzqk.getIntelligenceValidityStart()):"";
								String intelligenceValidityEnd = zzqk.getIntelligenceValidityEnd() != null?new SimpleDateFormat("yyyy-MM-dd").format(zzqk.getIntelligenceValidityEnd()):"" ;
								String changeDate = zzqk.getChangeDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(zzqk.getChangeDate()):"" ;
								ja.add((new JSONObject()).put("QYMC" , NullToString(zzqk.getCompanyName())));//企业名称
								ja.add((new JSONObject()).put("ZSBH" , NullToString(zzqk.getIntelligenceCardnum())));//证书编号
								ja.add((new JSONObject()).put("ZSMC" , NullToString(zzqk.getIntelligenceCardname())));//证书名称
								ja.add((new JSONObject()).put("ZZLX" , zzlx(zzqk.getIntelligenceType())));//确认类型
								ja.add((new JSONObject()).put("ZZLR" , NullToString(zzqk.getIntelligenceContent()) ));//资质内容
								ja.add((new JSONObject()).put("FZJG" , NullToString(zzqk.getIntelligenceInstitution())));//发证机关
								ja.add((new JSONObject()).put("FZRQ" ,intelligenceCardDate ));//发证日期
								ja.add((new JSONObject()).put("YXQQSRQ" , intelligenceValidityStart));//有效期开始日期
								ja.add((new JSONObject()).put("YXQJZRQ" ,intelligenceValidityEnd));//有效期截止时间
								ja.add((new JSONObject()).put("ZZJB" ,NullToString(zzqk.getZzjb())));//资质级别
								ja.add((new JSONObject()).put("BGRQ" ,changeDate));//变更日期
								ja.add((new JSONObject()).put("YWFW" ,NullToString(zzqk.getBussinessScope())));//业务范围
								JSONObject jo = new JSONObject();
								jo.put("frkzxx", ja.toString());
								String kzxx = jo.toString();
								Tkzxxs tkzxxs = ceateTkzxxUtil.ceateTkzxxs("zzqkb",zzqk.getCreateTime(), kzxx, "", zjhm);
								kzqq.getTkzxxs().add(tkzxxs);
							}
						}
					}
				}
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
				System.out.println("资质情况"+getTkzxxResponse.getStatus());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public String zzlx(String lx)
	{
		if(lx != null)
		{
			if(lx.equals("1"))
			{
				return "fglx";	//发改立项
			}
			else if(lx.equals("2"))
			{
				return "ghxk";	//规划许可
			}
			else if(lx.equals("3"))
			{
				return "jsxk";	//建设许可
			}
			else if(lx.equals("4"))
			{
				return "hbxk";	//环保许可
			}
			else if(lx.equals("5"))
			{
				return "xfxk";	//消防许可
			}
			else if(lx.equals("6"))
			{
				return "flsc";	//防雷审查
			}
			else if(lx.equals("7"))
			{
				return "tdz";	//土地证
			}
			else if(lx.equals("8"))
			{
				return "fcz";	//房产证
			}
			else if(lx.equals("9"))
			{
				return "yyzz";	//营业执照
			}
			else if(lx.equals("10"))
			{
				return "bzhzs";	//标准化证书
			}
			else if(lx.equals("11"))
			{
				return "ohsaszs";	//OHSAS证书
			}
		}
		return "";
	}

	public String getEntBaseInfoId() {
		return entBaseInfoId;
	}

	public void setEntBaseInfoId(String entBaseInfoId) {
		this.entBaseInfoId = entBaseInfoId;
	}

}
