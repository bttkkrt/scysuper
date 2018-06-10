package com.jshx.zywsgljbxx.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.zywsfgfzr.entity.OccChaInf;
import com.jshx.zywsfgfzr.service.OccChaInfService;
import com.jshx.zywsgljbxx.entity.OccHeaInfo;
import com.jshx.zywsgljbxx.service.OccHeaInfoService;

public class OccHeaInfoAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private OccHeaInfo occHeaInfo = new OccHeaInfo();
	
	private OccChaInf occChaInf = new OccChaInf();
	private List<OccChaInf> occChaInfs=new ArrayList<OccChaInf>();

	/**
	 * 业务类
	 */
	@Autowired
	private OccHeaInfoService occHeaInfoService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private HttpService httpService;
	@Autowired
	private OccChaInfService occChaInfService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private String trainingDateStart;
	private String trainingDateEnd;
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;
	
	
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
		    
		if(null != occHeaInfo){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != occHeaInfo.getAreaId()) && (0 < occHeaInfo.getAreaId().trim().length())){
				paraMap.put("areaId", occHeaInfo.getAreaId().trim());
			}

			if ((null != occHeaInfo.getCompanyName()) && (0 < occHeaInfo.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + occHeaInfo.getCompanyName().trim() + "%");
			}

			if ((null != occHeaInfo.getHazardIndustryCategory()) && (0 < occHeaInfo.getHazardIndustryCategory().trim().length())){
				paraMap.put("hazardIndustryCategory", occHeaInfo.getHazardIndustryCategory().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
codeMap.put("hazardIndustryCategory","40288005501cbfab01501d03cb7b0053");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
//		final String filter = "id|areaId|companyName|hazardIndustryCategory|femaleWorkersDiseasesNumber|";
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
		pagination = occHeaInfoService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}
	/**
	 * 政务通   职业健康基本信息
	 * lj
	 * 2016-06-06
	 * @return
	 */
	public String zyjkjbxx(){
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
		    
		if(null != occHeaInfo){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != occHeaInfo.getAreaId()) && (0 < occHeaInfo.getAreaId().trim().length())){
				paraMap.put("areaId", occHeaInfo.getAreaId().trim());
			}

			if ((null != occHeaInfo.getCompanyName()) && (0 < occHeaInfo.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + occHeaInfo.getCompanyName().trim() + "%");
			}

			if ((null != occHeaInfo.getHazardIndustryCategory()) && (0 < occHeaInfo.getHazardIndustryCategory().trim().length())){
				paraMap.put("hazardIndustryCategory", occHeaInfo.getHazardIndustryCategory().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		codeMap.put("hazardIndustryCategory","40288005501cbfab01501d03cb7b0053");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
//		final String filter = "id|areaId|companyName|hazardIndustryCategory|femaleWorkersDiseasesNumber|";
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
		pagination = occHeaInfoService.findByPage(pagination, paraMap);
		
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
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("createUserID", userId);
			occHeaInfo=(OccHeaInfo)occHeaInfoService.findObjectByMap(OccHeaInfo.class,paraMap );
			if(null!=occHeaInfo){
				if(null!=occHeaInfo.getId()){
					Map<String, Object> paraMap1 = new HashMap<String, Object>();
					paraMap1.put("occHeaInfoId", occHeaInfo.getId());
					occChaInfs=occChaInfService.findOccChaInf(paraMap1);
				}
			}else{
				occHeaInfo=new OccHeaInfo();
			}
		}else{
			occHeaInfo = occHeaInfoService.getById(occHeaInfo.getId());
			Map<String, Object> paraMap1 = new HashMap<String, Object>();
			paraMap1.put("occHeaInfoId", occHeaInfo.getId());
			occChaInfs=occChaInfService.findOccChaInf(paraMap1);
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
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
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
		occHeaInfo.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		occHeaInfo.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		occHeaInfo.setCompanyId(enBaseInfo.getId());
		occHeaInfo.setCompanyName(enBaseInfo.getEnterpriseName());
	
		if (null==occHeaInfo.getId()||"".equals(occHeaInfo.getId())){
			occHeaInfo.setDeptId(this.getLoginUserDepartmentId());
			occHeaInfo.setDelFlag(0);
			occHeaInfoService.save(occHeaInfo);
		}else{
			occHeaInfoService.update(occHeaInfo);
		}
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sqlId", "deleteOccChaInfByOccHeaInfoId");
		paraMap.put("occHeaInfoId", occHeaInfo.getId());
		httpService.updateByMap(paraMap);
		if(null!=occChaInf){
			if(null!=occChaInf.getJshxName()){
				String[] names = occChaInf.getJshxName() .replaceAll(" ", "").split(",");
				String[] dutys = occChaInf.getDuty() .replaceAll(" ", "").split(",");
				String[] telephones = occChaInf.getTelephone() .replaceAll(" ", "").split(",");
				String[] mobiles = occChaInf.getMobile() .replaceAll(" ", "").split(",");
				String[] degreeEducations = occChaInf.getDegreeEducation() .replaceAll(" ", "").split(",");
				String[] professionals = occChaInf.getProfessional().replaceAll(" ", "").split(",");
				String[] trainingDateStarts = trainingDateStart.replaceAll(" ", "").split(",");
				String[] trainingDateEnds = trainingDateEnd.replaceAll(" ", "").split(",");
				String[] trainingCertificatNumbers = occChaInf.getTrainingCertificatNumber() .replaceAll(" ", "").split(",");
				for(int i=0;i<names.length;i++){
					OccChaInf oci=new OccChaInf();
					oci.setOccHeaInfoId(occHeaInfo.getId());
					oci.setDelFlag(0);
					oci.setDeptId(this.getLoginUserDepartmentId());
					oci.setJshxName(names[i]);
					if(i<dutys.length){
						oci.setDuty(dutys[i]);
					}else{
						oci.setDuty("");
					}
					if(i<telephones.length){
						oci.setTelephone(telephones[i]);
					}else{
						oci.setTelephone("");
					}
					if(i<mobiles.length){
						oci.setMobile(mobiles[i]);
					}else{
						oci.setMobile("");
					}
					if(i<degreeEducations.length){
						oci.setDegreeEducation(degreeEducations[i]);
					}else{
						oci.setDegreeEducation("");
					}
					if(i<professionals.length){
						oci.setProfessional(professionals[i]);
					}else{
						oci.setProfessional("");
					}
					if(i<trainingCertificatNumbers.length){
						oci.setTrainingCertificatNumber(trainingCertificatNumbers[i]);
					}else{
						oci.setTrainingCertificatNumber("");
					}
					if(i<trainingDateStarts.length){
						if(!"".equals(trainingDateStarts[i])&&null!=trainingDateStarts[i]){
							oci.setTrainingDateStart(sdf.parse(trainingDateStarts[i]));
						}else{
							oci.setTrainingDateStart(null);
						}
					}else{
						oci.setTrainingDateStart(null);
					}
					if(i<trainingDateEnds.length){
						if(!"".equals(trainingDateEnds[i])&&null!=trainingDateEnds[i]){
							oci.setTrainingDateEnd(sdf.parse(trainingDateEnds[i]));
						}else{
							oci.setTrainingDateEnd(null);
						}
					}else{
						oci.setTrainingDateEnd(null);
					}
					occChaInfService.save(oci);
				}
			}
		}
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != occHeaInfo)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到occHeaInfo中去
				
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
			occHeaInfoService.deleteWithFlag(ids);
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
		User user=userService.findUserByLoginId("zdz1");
		setSessionAttribute("curr_user", user);
//		String userId=user.getId();
//		if(httpService.judgeRoleCode(userId, "A23")){//企业角色
//			setRoleName("0");
//		} 
		return SUCCESS;
		
	}


	public void zwtList1() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != occHeaInfo){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != occHeaInfo.getAreaId()) && (0 < occHeaInfo.getAreaId().trim().length())){
				paraMap.put("areaId", occHeaInfo.getAreaId().trim());
			}

			if ((null != occHeaInfo.getCompanyName()) && (0 < occHeaInfo.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + occHeaInfo.getCompanyName().trim() + "%");
			}

			if ((null != occHeaInfo.getHazardIndustryCategory()) && (0 < occHeaInfo.getHazardIndustryCategory().trim().length())){
				paraMap.put("hazardIndustryCategory", occHeaInfo.getHazardIndustryCategory().trim());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		codeMap.put("hazardIndustryCategory","40288005501cbfab01501d03cb7b0053");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称".equals(searchLike)){
			paraMap.put("companyName", "%" + searchLike.trim() + "%");
		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = occHeaInfoService.findByPage(pagination, paraMap);
		
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

	public OccHeaInfo getOccHeaInfo(){
		return this.occHeaInfo;
	}

	public void setOccHeaInfo(OccHeaInfo occHeaInfo){
		this.occHeaInfo = occHeaInfo;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public OccChaInf getOccChaInf() {
		return occChaInf;
	}

	public void setOccChaInf(OccChaInf occChaInf) {
		this.occChaInf = occChaInf;
	}

	public List<OccChaInf> getOccChaInfs() {
		return occChaInfs;
	}

	public void setOccChaInfs(List<OccChaInf> occChaInfs) {
		this.occChaInfs = occChaInfs;
	}

	public String getTrainingDateStart() {
		return trainingDateStart;
	}

	public void setTrainingDateStart(String trainingDateStart) {
		this.trainingDateStart = trainingDateStart;
	}

	public String getTrainingDateEnd() {
		return trainingDateEnd;
	}

	public void setTrainingDateEnd(String trainingDateEnd) {
		this.trainingDateEnd = trainingDateEnd;
	}

       
    
}
