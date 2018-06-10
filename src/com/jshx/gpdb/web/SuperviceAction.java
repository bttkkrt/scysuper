package com.jshx.gpdb.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.LobHelper;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.yhb.service.TroManService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.gpdb.entity.Supervice;
import com.jshx.gpdb.service.SuperviceService;
import com.jshx.http.service.HttpService;

public class SuperviceAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Supervice supervice = new Supervice();

	/**
	 * 业务类
	 */
	@Autowired
	private SuperviceService superviceService;
	@Autowired
	private HttpService httpService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryListingTimeStart;

	private Date queryListingTimeEnd;

	private Date queryRectificationTermStart;

	private Date queryRectificationTermEnd;

	private Date queryRecfinishTimeStart;

	private Date queryRecfinishTimeEnd;

	private Date queryAcceptTimeStart;
	private String linkId;
	private Date queryAcceptTimeEnd;
	private int pageNo;
	private int pageSize;
	private String searchLike;


	//private String uRole;//综合处：zhc；网格监管人员：wgjgry；街道安监办：jdajb；安委会、安监局领导：ajjld
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private UserService userService;
	@Autowired
	private TroManService troManService;
	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList4 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList5 = new ArrayList<PhotoPic>();
	private List<PhotoPic> picList6 = new ArrayList<PhotoPic>();
	/**
	 * 审核记录
	 */
	private List<CheckRecord> checkRecords;
	
	private CheckRecord checkRecord=new CheckRecord();
	@Autowired
	private CheckRecordService checkRecordService;
	private SimpleDateFormat sdfFormat=new SimpleDateFormat("yyyy-MM-dd");
	
	private String roleCode;
	
	private String nextOperation;
	/**
	 * 是否具有审核记录
	 */
	private String ifChecked;
	//整改历史记录
	private List<HashMap> rectInfos = new ArrayList<HashMap>();
	
	public  String init(){
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A03")){
			roleCode="A03";
		}else if(httpService.judgeRoleCode(userId, "A04")){
			roleCode="A04";
		}else if(httpService.judgeRoleCode(userId, "A02")){
			roleCode="A02";
		}else if(httpService.judgeRoleCode(userId, "A23")){ 
			roleCode="A23";	
		}else{
			roleCode="other";
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
		    
		if(null != supervice){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != supervice.getAreaId()) && (0 < supervice.getAreaId().trim().length())){
				paraMap.put("areaId", supervice.getAreaId() );
			}

			if ((null != supervice.getCompanyName()) && (0 < supervice.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + supervice.getCompanyName().trim() + "%");
			}

			if ((null != supervice.getDangerName()) && (0 < supervice.getDangerName().trim().length())){
				paraMap.put("dangerName", "%" + supervice.getDangerName().trim() + "%");
			}

			if ((null != supervice.getDangerSort()) && (0 < supervice.getDangerSort().trim().length())){
				paraMap.put("dangerSort", supervice.getDangerSort().trim());
			}

			if ((null != supervice.getDangerLevel()) && (0 < supervice.getDangerLevel().trim().length())){
				paraMap.put("dangerLevel", supervice.getDangerLevel().trim());
			}

			if ((null != supervice.getRectificationState()) && (0 < supervice.getRectificationState().trim().length())){
				paraMap.put("rectificationState", supervice.getRectificationState().trim());
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
codeMap.put("dangerSort","402880084196a4a301419759b28e0249");
codeMap.put("dangerLevel","402880084196a4a301419759d52a024b");
//codeMap.put("rectificationState","402880084196a4a30141975da934025d");
codeMap.put("rectificationLevel","4028e53c50a747090150a74e0b9a000b");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|dangerName|dangerSort|dangerLevel|rectificationState|rectificationLevel|createUserID|nextRoleCode|nextOperation|";
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
		pagination = superviceService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	@SuppressWarnings("unchecked")
	public String view() throws Exception{
		if((null != supervice)&&(null != supervice.getId())){
			supervice = superviceService.getById(supervice.getId());
			if(supervice.getLinkId() == null || "".equals(supervice.getLinkId())){
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				supervice.setLinkId(linkId);
			}else{
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("linkId",supervice.getLinkId());
				map.put("mkType", "gpdb");
				map.put("picType","jcwsfj");
				picList1 = photoPicService.findPicPath(map); 
				map.put("mkType", "gpdb");
				map.put("picType","zgqtpfj");
				picList2 = photoPicService.findPicPath(map); 
				map.put("mkType", "gpdb");
				map.put("picType","zgfafj");
				picList3 = photoPicService.findPicPath(map); 
				map.put("mkType", "gpdb");
				map.put("picType","ffcsfj");
				picList4 = photoPicService.findPicPath(map); 
//				map.put("mkType", "gpdb");
//				map.put("picType","fcwsfj");
//				picList5 = photoPicService.findPicPath(map); 
//				map.put("mkType", "gpdb");
//				map.put("picType","zghtpfj");
//				picList6 = photoPicService.findPicPath(map); 
				
			}
			//审核记录
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("infoId", supervice.getId());
			checkRecords=checkRecordService.findCheckRecord(paraMap);
			if(checkRecords.size()>0){
				ifChecked="Y";
			}else{
				ifChecked="N";
			}
			
			
			//整改历史记录
			Map map = new HashMap();
			map.put("linkId",supervice.getLinkId());
			map.put("mkType", "gpdb");
			
			//此处进行调整 整改的信息为列表展示
			map.put("yhbId", supervice.getId());
			rectInfos = troManService.queryRectInfosByMap(map);
			if(rectInfos!=null&&!rectInfos.isEmpty()){
				for(Map m:rectInfos){
					map.put("linkId", m.get("linkId"));
					map.put("picType","zghtpfj");
					List<PhotoPic> pics = photoPicService.findPicPath(map);//获取整改后图片
					m.put("zghtpfj", pics);
				}
				for(Map m:rectInfos){
					map.put("linkId", m.get("linkId"));
					map.put("picType","fcwsfj");
					List<PhotoPic> pics = photoPicService.findPicPath(map);//获取整改后图片
					m.put("fcwsfj", pics);
				}
			}
			
		}else{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			supervice.setLinkId(linkId);
		}
		
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A03")||httpService.judgeRoleCode(userId, "A04")){//综合处处长、工作人员
			return "viewZHC";
		}else if(httpService.judgeRoleCode(userId, "A12")){//(网格监管人员)安监中队队员
			return "viewWGJGRY";
//			}else if(httpService.judgeRoleCode(userId, "A25")){
		}else if(httpService.judgeRoleCode(userId, "A11")){//11-27 fq 修改 没有‘街道安监办’这角色，改为‘安监中队队长’
			return "viewJDAJB";
		}else if(httpService.judgeRoleCode(userId, "A02")){//安监局局领导
			return "viewAJJLD";
		}else{
			return VIEW;
		}
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
//		String userId=this.getLoginUser().getId();
//		if(httpService.judgeRoleCode(userId, "A03")||httpService.judgeRoleCode(userId, "A04")){
		if("add".equals(flag)||"mod".equals(flag)){
			return "editZHC";
		}else{
			linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			return "editWGJGRY";
		}
//		}else if(httpService.judgeRoleCode(userId, "A12")){
//			linkId = java.util.UUID.randomUUID().toString().replace("-", "");
//			return "editWGJGRY";
////		}else if(httpService.judgeRoleCode(userId, "A25")){
//		}else if(httpService.judgeRoleCode(userId, "A11")){//11-27 fq 修改 没有‘街道安监办’这角色，改为‘安监中队队长’
//			return "editJDAJB";
//		}else if(httpService.judgeRoleCode(userId, "A02")){
//			return "editAJJLD";
//		}else{
//			return EDIT;
//		}
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
	
		if ("add".equalsIgnoreCase(this.flag)){
			supervice.setDeptId(this.getLoginUserDepartmentId());
			supervice.setDelFlag(0);
			supervice.setRectificationState("待审核");
			supervice.setNextRoleCode("A03");
			supervice.setNextOperation("check1");
			superviceService.save(supervice);
		}else if("mod".equalsIgnoreCase(this.flag)){
			supervice.setRectificationState("待审核");
			supervice.setNextRoleCode("A03");
			supervice.setNextOperation("check1");
			superviceService.update(supervice);
		}else{
			if("待整改".equals(supervice.getRectificationState())){
				supervice.setRectificationState("已整改待审核");
			}
			supervice.setRectUserId(this.getLoginUser().getId());
			supervice.setNextRoleCode("A03");
			supervice.setNextOperation("check3");
			superviceService.update(supervice);
			//money,rect_time,userId,insert_time,yhb_id,link_id,state,remark
			Map map = new HashMap();
			//整改资金
			map.put("money", supervice.getRectificationMoney());
			//隐患整改数
			map.put("state",supervice.getDanrecNum());//企业上报整改信息 默认整改完成 lj 2015-12-07
			//整改完成时间
			map.put("rectTime", sdfFormat.format(supervice.getRecfinishTime()));
			//验收时间
			map.put("insertTime", sdfFormat.format(supervice.getAcceptTime()));
			map.put("userId", this.getLoginUser().getId());
			map.put("yhbId", supervice.getId());
			map.put("linkId", linkId);
			troManService.saveRectInfo(map);
			
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != supervice)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到supervice中去
				
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
			superviceService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	/**
	 * 审核信息（包括新增和修改）
	 * fq 2015-10-27
	 */
	public String check() throws Exception{
		view();
		if("check1".equals(flag)){
			return "check1";
		}else{
			return "check2";
		}
	}
	
	
	/**
	 * 保存审核信息
	 *  fq 2015-10-27
	 */
	public String checkSave() throws Exception{
		supervice=superviceService.getById(supervice.getId());
		supervice.setRectificationState(checkRecord.getCheckResult());
		if("check1".equals(supervice.getNextOperation())){
			if("审核通过".equals(checkRecord.getCheckResult())){
				supervice.setNextOperation("check2");
				supervice.setNextRoleCode("A02");
				supervice.setRectificationState("待审批");
			}else{
				supervice.setNextOperation("update1");
				supervice.setNextRoleCode("A04");
				supervice.setRectificationState("待修改");
			}
		}else if("check2".equals(supervice.getNextOperation())){
			if("审批通过".equals(checkRecord.getCheckResult())){
				supervice.setNextOperation("update2");
				supervice.setNextRoleCode("A04");
				supervice.setRectificationState("待整改");
			}else{
				supervice.setNextOperation("update1");
				supervice.setNextRoleCode("A04");
				supervice.setRectificationState("待修改");
			}
		}else if("check3".equals(supervice.getNextOperation())){
			if("审核通过".equals(checkRecord.getCheckResult())){
				supervice.setNextOperation("check4");
				supervice.setNextRoleCode("A02");
				supervice.setRectificationState("已整改待审批");
			}else{
				supervice.setNextOperation("update2");
				supervice.setNextRoleCode("A04");
				supervice.setRectificationState("待整改");
			}
		}else if("check4".equals(supervice.getNextOperation())){
			if("审批通过".equals(checkRecord.getCheckResult())){
				supervice.setNextOperation("finish");
				supervice.setNextRoleCode("finish");
				supervice.setRectificationState("完成");
			}else{
				supervice.setNextOperation("update2");
				supervice.setNextRoleCode("A04");
				supervice.setRectificationState("待整改");
			}
		}else {
			
		}
		superviceService.update(supervice);
		checkRecord .setCheckUserid(this.getLoginUser().getId());
		checkRecord.setDelFlag(0);
		checkRecord.setCheckUsername(this.getLoginUser().getDisplayName());
		checkRecordService.save(checkRecord);
	    return RELOAD;
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
		User user=userService.findUserByLoginId("zhcbsy");
		setSessionAttribute("curr_user", user);
		String userId=user.getId();
		if(httpService.judgeRoleCode(userId, "A03")){
			roleCode="A03";
		}else if(httpService.judgeRoleCode(userId, "A04")){
			roleCode="A04";
		}else if(httpService.judgeRoleCode(userId, "A02")){
			roleCode="A02";
		}else if(httpService.judgeRoleCode(userId, "A23")){ 
			roleCode="A23";	
		}else{
			roleCode="other";
		}
		return SUCCESS;
	}
	
	public String gpdb(){
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A03")){
			roleCode="A03";
		}else if(httpService.judgeRoleCode(userId, "A04")){
			roleCode="A04";
		}else if(httpService.judgeRoleCode(userId, "A02")){
			roleCode="A02";
		}else if(httpService.judgeRoleCode(userId, "A23")){ 
			roleCode="A23";	
		}else{
			roleCode="other";
		}
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		User u=this.getLoginUser();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != supervice){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != supervice.getAreaId()) && (0 < supervice.getAreaId().trim().length())){
				paraMap.put("areaId", supervice.getAreaId() );
			}

			if ((null != supervice.getCompanyName()) && (0 < supervice.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + supervice.getCompanyName().trim() + "%");
			}

			if ((null != supervice.getDangerName()) && (0 < supervice.getDangerName().trim().length())){
				paraMap.put("dangerName", "%" + supervice.getDangerName().trim() + "%");
			}

			if ((null != supervice.getDangerSort()) && (0 < supervice.getDangerSort().trim().length())){
				paraMap.put("dangerSort", supervice.getDangerSort().trim());
			}

			if ((null != supervice.getDangerLevel()) && (0 < supervice.getDangerLevel().trim().length())){
				paraMap.put("dangerLevel", supervice.getDangerLevel().trim());
			}

			if ((null != supervice.getRectificationState()) && (0 < supervice.getRectificationState().trim().length())){
				paraMap.put("rectificationState", supervice.getRectificationState().trim());
			}
		}
		
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称或隐患名称".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		codeMap.put("dangerSort","402880084196a4a301419759b28e0249");
		codeMap.put("dangerLevel","402880084196a4a301419759d52a024b");
		//codeMap.put("rectificationState","402880084196a4a30141975da934025d");
		codeMap.put("rectificationLevel","4028e53c50a747090150a74e0b9a000b");
				config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|dangerName|dangerSort|dangerLevel|rectificationState|rectificationLevel|createUserID|nextRoleCode|nextOperation|";
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
		pagination = superviceService.findByPage(pagination, paraMap);
		
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

	public Supervice getSupervice(){
		return this.supervice;
	}

	public void setSupervice(Supervice supervice){
		this.supervice = supervice;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryListingTimeStart(){
		return this.queryListingTimeStart;
	}

	public void setQueryListingTimeStart(Date queryListingTimeStart){
		this.queryListingTimeStart = queryListingTimeStart;
	}

	public Date getQueryListingTimeEnd(){
		return this.queryListingTimeEnd;
	}

	public void setQueryListingTimeEnd(Date queryListingTimeEnd){
		this.queryListingTimeEnd = queryListingTimeEnd;
	}

	public Date getQueryRectificationTermStart(){
		return this.queryRectificationTermStart;
	}

	public void setQueryRectificationTermStart(Date queryRectificationTermStart){
		this.queryRectificationTermStart = queryRectificationTermStart;
	}

	public Date getQueryRectificationTermEnd(){
		return this.queryRectificationTermEnd;
	}

	public void setQueryRectificationTermEnd(Date queryRectificationTermEnd){
		this.queryRectificationTermEnd = queryRectificationTermEnd;
	}

	public Date getQueryRecfinishTimeStart(){
		return this.queryRecfinishTimeStart;
	}

	public void setQueryRecfinishTimeStart(Date queryRecfinishTimeStart){
		this.queryRecfinishTimeStart = queryRecfinishTimeStart;
	}

	public Date getQueryRecfinishTimeEnd(){
		return this.queryRecfinishTimeEnd;
	}

	public void setQueryRecfinishTimeEnd(Date queryRecfinishTimeEnd){
		this.queryRecfinishTimeEnd = queryRecfinishTimeEnd;
	}

	public Date getQueryAcceptTimeStart(){
		return this.queryAcceptTimeStart;
	}

	public void setQueryAcceptTimeStart(Date queryAcceptTimeStart){
		this.queryAcceptTimeStart = queryAcceptTimeStart;
	}

	public Date getQueryAcceptTimeEnd(){
		return this.queryAcceptTimeEnd;
	}

	public void setQueryAcceptTimeEnd(Date queryAcceptTimeEnd){
		this.queryAcceptTimeEnd = queryAcceptTimeEnd;
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
	public String getIfChecked() {
		return ifChecked;
	}
	public void setIfChecked(String ifChecked) {
		this.ifChecked = ifChecked;
	}
	public List<HashMap> getRectInfos() {
		return rectInfos;
	}
	public void setRectInfos(List<HashMap> rectInfos) {
		this.rectInfos = rectInfos;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getNextOperation() {
		return nextOperation;
	}
	public void setNextOperation(String nextOperation) {
		this.nextOperation = nextOperation;
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
