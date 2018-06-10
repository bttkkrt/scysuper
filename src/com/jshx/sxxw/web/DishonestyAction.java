package com.jshx.sxxw.web;

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
import com.jshx.hmd.entity.Hmd;
import com.jshx.hmd.service.HmdService;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.qyjbxx.util.ClientAuthenticationHandler;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.sxxw.entity.Dishonesty;
import com.jshx.sxxw.service.DishonestyService;
import com.xypt.SynchronizeCompanyInfo;
import com.xypt.SynchronizeCompanyInfoService;

public class DishonestyAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Dishonesty dishonesty = new Dishonesty();

	/**
	 * 业务类
	 */
	@Autowired
	private DishonestyService dishonestyService;
	@Autowired
	private HmdService hmdService;
	@Autowired
	private UserService userService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryPenalizingDateStart;

	private Date queryPenalizingDateEnd;

	private Date queryPublicStartDateStart;

	private Date queryPublicStartDateEnd;

	private Date queryPublicEndDateStart;

	private Date queryPublicEndDateEnd;

	private Date queryFinishTimeStart;

	private Date queryFinishTimeEnd;
	private Date manageTerm;
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private CheckRecordService checkRecordService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private HttpService httpService;
	//是否监察大队队长角色
	private boolean check1=false;
	//是否安监局领导角色
	private boolean check2=false;
	private int pageNo;
	
	private int pageSize;
	
	private String searchLike;
	/**
	 * 审核记录
	 */
	private List<CheckRecord> checkRecords;
	
	private CheckRecord checkRecord=new CheckRecord();
	
	private String roleName;
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	public String init(){
		List<UserRight> urs=(List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight r:urs){
			if("A09".equals(r.getRole().getRoleCode())){
				check1=true;
				break;
			}
			if("A02".equals(r.getRole().getRoleCode())){
				check2=true;
				break;
			}
			if("A11".equals(r.getRole().getRoleCode())){
				roleName = "0";
				break;
			}
			if("A23".equals(r.getRole().getRoleCode())){
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
		    
		if(null != dishonesty){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != dishonesty.getAreaId()) && (0 < dishonesty.getAreaId().trim().length())){
				paraMap.put("areaId",  dishonesty.getAreaId() );
			}

			if ((null != dishonesty.getCompanyName()) && (0 < dishonesty.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + dishonesty.getCompanyName().trim() + "%");
			}

			if ((null != dishonesty.getPunishName()) && (0 < dishonesty.getPunishName().trim().length())){
				paraMap.put("punishName", "%" + dishonesty.getPunishName().trim() + "%");
			}

			if ((null != dishonesty.getSymbolDecision()) && (0 < dishonesty.getSymbolDecision().trim().length())){
				paraMap.put("symbolDecision", "%" + dishonesty.getSymbolDecision().trim() + "%");
			}

			if ((null != dishonesty.getPunishedSpecies()) && (0 < dishonesty.getPunishedSpecies().trim().length())){
				paraMap.put("punishedSpecies", dishonesty.getPunishedSpecies().trim());
			}

			if ((null != dishonesty.getCreditRating()) && (0 < dishonesty.getCreditRating().trim().length())){
				paraMap.put("creditRating", dishonesty.getCreditRating().trim());
			}

			if ((null != dishonesty.getCheckStatus()) && (0 < dishonesty.getCheckStatus().trim().length())){
				paraMap.put("checkStatus", "%" + dishonesty.getCheckStatus().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		codeMap.put("punishedSpecies","402880fc50460b9a01504659ef3c014d");
		codeMap.put("creditRating","402880fc50460b9a0150465bffd20158");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|checkComment|areaId|companyName|punishName|symbolDecision|punishedSpecies|creditRating|checkStatus|createUserID|";
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
		pagination = dishonestyService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != dishonesty)&&(null != dishonesty.getId())){
			dishonesty = dishonestyService.getById(dishonesty.getId());
			//审核记录
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("infoId", dishonesty.getId());
			checkRecords=checkRecordService.findCheckRecord(paraMap);
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
		dishonesty.setCheckStatus("待审核");
		if ("add".equalsIgnoreCase(this.flag)){
			dishonesty.setDeptId(this.getLoginUserDepartmentId());
			dishonesty.setDelFlag(0);
			dishonestyService.save(dishonesty);
		}else{
			dishonestyService.update(dishonesty);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != dishonesty)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到dishonesty中去
				
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
			dishonestyService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	/**
	 * 审核信息
	 * 费谦 2015-10-22
	 */
	public String check1() throws Exception{
		view();
	    return "check1";
	}
	
	/**
	 * 审批信息
	 * 费谦 2015-10-22
	 */
	public String check2() throws Exception{
		view();
	    return "check2";
	}
	/**
	 * 保存审核/审批信息
	 * 费谦 2015-10-22
	 */
	public String checkSave() throws Exception{
		dishonesty=dishonestyService.getById(dishonesty.getId());
		
		if("审批通过并上报信用平台".equals(checkRecord.getCheckResult())){
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
				SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
				EntBaseInfo entBaseInfo = entBaseInfoService.getById(dishonesty.getCompanyId());
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
				map.put("codeName", "处罚种类");
				map.put("itemValue", dishonesty.getPunishedSpecies());
				String cfzl=(null==codeService.findCodeValueByMap(map).getItemText()?"":codeService.findCodeValueByMap(map).getItemText());
				map.put("codeName", "失信等级");
				map.put("itemValue", dishonesty.getCreditRating());
				String sxdj=(null==codeService.findCodeValueByMap(map).getItemText()?"":codeService.findCodeValueByMap(map).getItemText());
				map.put("codeName", "是或否");
				map.put("itemValue", dishonesty.getIsPublic());
				String sfgs=(null==codeService.findCodeValueByMap(map).getItemText()?"":codeService.findCodeValueByMap(map).getItemText());
				String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request><Head>"+
				"<DeptID><![CDATA[54]]></DeptID>"+
				"<InfoClass><![CDATA[安监-失信行为信息]]></InfoClass>"+
				"<UploadTime><![CDATA["+sdf.format(new Date())+"]]></UploadTime></Head>"+
				"<Body><FIELDS><![CDATA[组织机构代码,入库日期,工商注册号/统一社会信用代码,处罚名称,处罚决定书文号,被处罚当事人,被处罚当事人证件号码,处罚事由,处罚种类,行政处罚依据,行政处罚结论,没收违法所得,罚款金额,失信等级（严重、较重、一般）,处罚机关全称,行政处罚日期,是否公示,公示起日期,公示止日期,执行完成日期,执行情况,企业全称（中文）]]></FIELDS>"+
				"<DATA>"+
				"<ZZJGDM><![CDATA["+entBaseInfo.getEnterpriseCode()+"]]></ZZJGDM>"+
				"<RKRQ><![CDATA["+sdf1.format(new Date())+"]]></RKRQ>"+
				"<GSZCH><![CDATA["+entBaseInfo.getRegistrationNumber() +"]]></GSZCH>"+
				"<CFMC><![CDATA["+dishonesty.getPunishName() +"]]></CFMC>"+
				"<CFJDSWH><![CDATA["+dishonesty.getSymbolDecision() +"]]></CFJDSWH>"+
				"<BCFDSR><![CDATA["+dishonesty.getPunishedParty() +"]]></BCFDSR>"+
				"<BCFDSRZJHM><![CDATA["+dishonesty.getPunishedNumber() +"]]></BCFDSRZJHM>"+
				"<CFSY><![CDATA["+dishonesty.getPunishedSubject() +"]]></CFSY>"+
				"<CFZL><![CDATA["+cfzl+"]]></CFZL>"+
				"<XZCFYJ><![CDATA["+dishonesty.getPunishedBasis()+"]]></XZCFYJ>"+
				"<XZCFJL><![CDATA["+dishonesty.getPunishedConclusion()+"]]></XZCFJL>"+
				"<MSWFSD><![CDATA["+dishonesty.getIllegalIncome()+"]]></MSWFSD>"+
				"<FKJE><![CDATA["+dishonesty.getFines()+"]]></FKJE>"+
				"<SXDJ><![CDATA["+sxdj+"]]></SXDJ>"+
				"<CFJGQC><![CDATA["+dishonesty.getPenalizingOrgan()+"]]></CFJGQC>"+
				"<XZCFRQ><![CDATA["+sdf1.format(dishonesty.getPenalizingDate())+"]]></XZCFRQ>"+
				"<SFGS><![CDATA["+sfgs+"]]></SFGS>"+
				"<GSQRQ><![CDATA["+sdf1.format(dishonesty.getPublicStartDate())+"]]></GSQRQ>"+
				"<GSZRQ><![CDATA["+sdf1.format(dishonesty.getPublicEndDate())+"]]></GSZRQ>"+
				"<ZXWCRQ><![CDATA["+sdf1.format(dishonesty.getFinishTime())+"]]></ZXWCRQ>"+
				"<ZXQK><![CDATA["+dishonesty.getImplementation()+"]]></ZXQK>"+
				"<QYMC><![CDATA["+entBaseInfo.getEnterpriseName() +"]]></QYMC>"+
				"</DATA></Body></Request>";
				System.out.println(xml);
				System.out.println(syn.uploadCompanyInfo(xml));
				
				dishonesty.setCheckStatus("审批通过");
				dishonestyService.update(dishonesty);
				checkRecord .setCheckUserid(this.getLoginUser().getId());
				checkRecord.setDelFlag(0);
				checkRecord.setCheckUsername(this.getLoginUser().getDisplayName());
				checkRecordService.save(checkRecord);
				
				if(checkRecord.getCheckResult().startsWith("审批通过")&&"3".equals(dishonesty.getCreditRating())){
					Hmd hmd = new Hmd();
					hmd.setAreaId(dishonesty.getAreaId());
					hmd.setAreaName(dishonesty.getAreaName());
					hmd.setCompanyId(dishonesty.getCompanyId());
					hmd.setCompanyName(dishonesty.getCompanyName());
					hmd.setDelFlag(0);
					hmd.setIllegalActivity(dishonesty.getPunishedSubject());
					hmd.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(dishonesty.getPenalizingDate()));
					hmd.setCreateUserID(this.getLoginUser().getId());
					hmd.setManageTerm(new SimpleDateFormat("yyyy-MM-dd").format(manageTerm));
					hmdService.save(hmd);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		else
		{
			if(checkRecord.getCheckResult().equals("审核通过"))
			{
				dishonesty.setCheckStatus("待审批");
			}
			else
			{
				dishonesty.setCheckStatus(checkRecord.getCheckResult());
			}
			dishonestyService.update(dishonesty);
			checkRecord .setCheckUserid(this.getLoginUser().getId());
			checkRecord.setDelFlag(0);
			checkRecord.setCheckUsername(this.getLoginUser().getDisplayName());
			checkRecordService.save(checkRecord);
			
			if(checkRecord.getCheckResult().startsWith("审批通过")&&"3".equals(dishonesty.getCreditRating())){
				Hmd hmd = new Hmd();
				hmd.setAreaId(dishonesty.getAreaId());
				hmd.setAreaName(dishonesty.getAreaName());
				hmd.setCompanyId(dishonesty.getCompanyId());
				hmd.setCompanyName(dishonesty.getCompanyName());
				hmd.setDelFlag(0);
				hmd.setIllegalActivity(dishonesty.getPunishedSubject());
				hmd.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(dishonesty.getPenalizingDate()));
				hmd.setCreateUserID(this.getLoginUser().getId());
				hmd.setManageTerm(new SimpleDateFormat("yyyy-MM-dd").format(manageTerm));
				hmdService.save(hmd);
			}
		}
	    return RELOAD;
	}
	
	public String zwtInit(){
		User user=userService.findUserByLoginId("zdz1");
		setSessionAttribute("curr_user", user);
		if(httpService.judgeRoleCode(user.getId(), "A09")){
			check1=true;
		}else if(httpService.judgeRoleCode(user.getId(), "A02")){
			check2=true;
		}else if(httpService.judgeRoleCode(user.getId(), "A11")){
			roleName = "0";
		}else if(httpService.judgeRoleCode(user.getId(), "A23")){
			roleName = "1";
		}else if(httpService.judgeRoleCode(user.getId(), "A09")){
			check1=true;
		}else {
			
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
		    
		if(null != dishonesty){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != dishonesty.getAreaId()) && (0 < dishonesty.getAreaId().trim().length())){
				paraMap.put("areaId",  dishonesty.getAreaId() );
			}

			if ((null != dishonesty.getCompanyName()) && (0 < dishonesty.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + dishonesty.getCompanyName().trim() + "%");
			}

			if ((null != dishonesty.getPunishName()) && (0 < dishonesty.getPunishName().trim().length())){
				paraMap.put("punishName", "%" + dishonesty.getPunishName().trim() + "%");
			}

			if ((null != dishonesty.getSymbolDecision()) && (0 < dishonesty.getSymbolDecision().trim().length())){
				paraMap.put("symbolDecision", "%" + dishonesty.getSymbolDecision().trim() + "%");
			}

			if ((null != dishonesty.getPunishedSpecies()) && (0 < dishonesty.getPunishedSpecies().trim().length())){
				paraMap.put("punishedSpecies", dishonesty.getPunishedSpecies().trim());
			}

			if ((null != dishonesty.getCreditRating()) && (0 < dishonesty.getCreditRating().trim().length())){
				paraMap.put("creditRating", dishonesty.getCreditRating().trim());
			}

			if ((null != dishonesty.getCheckStatus()) && (0 < dishonesty.getCheckStatus().trim().length())){
				paraMap.put("checkStatus", "%" + dishonesty.getCheckStatus().trim() + "%");
			}

		}
		
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称、处罚名称或文书号".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		codeMap.put("punishedSpecies","402880fc50460b9a01504659ef3c014d");
		codeMap.put("creditRating","402880fc50460b9a0150465bffd20158");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|checkComment|areaId|companyName|punishName|symbolDecision|punishedSpecies|creditRating|checkStatus|createUserID|";
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
		pagination = dishonestyService.findByPage(pagination, paraMap);
		
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

	public Dishonesty getDishonesty(){
		return this.dishonesty;
	}

	public void setDishonesty(Dishonesty dishonesty){
		this.dishonesty = dishonesty;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryPenalizingDateStart(){
		return this.queryPenalizingDateStart;
	}

	public void setQueryPenalizingDateStart(Date queryPenalizingDateStart){
		this.queryPenalizingDateStart = queryPenalizingDateStart;
	}

	public Date getQueryPenalizingDateEnd(){
		return this.queryPenalizingDateEnd;
	}

	public void setQueryPenalizingDateEnd(Date queryPenalizingDateEnd){
		this.queryPenalizingDateEnd = queryPenalizingDateEnd;
	}

	public Date getQueryPublicStartDateStart(){
		return this.queryPublicStartDateStart;
	}

	public void setQueryPublicStartDateStart(Date queryPublicStartDateStart){
		this.queryPublicStartDateStart = queryPublicStartDateStart;
	}

	public Date getQueryPublicStartDateEnd(){
		return this.queryPublicStartDateEnd;
	}

	public void setQueryPublicStartDateEnd(Date queryPublicStartDateEnd){
		this.queryPublicStartDateEnd = queryPublicStartDateEnd;
	}

	public Date getQueryPublicEndDateStart(){
		return this.queryPublicEndDateStart;
	}

	public void setQueryPublicEndDateStart(Date queryPublicEndDateStart){
		this.queryPublicEndDateStart = queryPublicEndDateStart;
	}

	public Date getQueryPublicEndDateEnd(){
		return this.queryPublicEndDateEnd;
	}

	public void setQueryPublicEndDateEnd(Date queryPublicEndDateEnd){
		this.queryPublicEndDateEnd = queryPublicEndDateEnd;
	}

	public Date getQueryFinishTimeStart(){
		return this.queryFinishTimeStart;
	}

	public void setQueryFinishTimeStart(Date queryFinishTimeStart){
		this.queryFinishTimeStart = queryFinishTimeStart;
	}

	public Date getQueryFinishTimeEnd(){
		return this.queryFinishTimeEnd;
	}

	public void setQueryFinishTimeEnd(Date queryFinishTimeEnd){
		this.queryFinishTimeEnd = queryFinishTimeEnd;
	}

	public boolean getCheck1() {
		return check1;
	}

	public void setCheck1(boolean check1) {
		this.check1 = check1;
	}

	public boolean getCheck2() {
		return check2;
	}

	public void setCheck2(boolean check2) {
		this.check2 = check2;
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


	public Date getManageTerm() {
		return manageTerm;
	}


	public void setManageTerm(Date manageTerm) {
		this.manageTerm = manageTerm;
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
