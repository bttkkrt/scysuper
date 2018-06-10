package com.jshx.zyjktj.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.Constants;
import com.jshx.gzk.entity.InformCard;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.zyjktj.entity.OccHeaExa;
import com.jshx.zyjktj.entity.OccHeaExaList;
import com.jshx.zyjktj.service.OccHeaExaService;
import com.jshx.zywsfgfzr.entity.OccChaInf;
import com.jshx.zywsgljbxx.entity.OccHeaInfo;

public class OccHeaExaAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private OccHeaExa occHeaExa = new OccHeaExa();
	private List<OccHeaExaList> occHeaExaLists=new ArrayList<OccHeaExaList>();
	private OccHeaExaList occHeaExaList=new OccHeaExaList();
	/**
	 * 业务类
	 */
	@Autowired
	private OccHeaExaService occHeaExaService;
	@Autowired
	private DeptService deptService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private File userFile;
	private String message;
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private HttpService httpService;
	
	private String roleName;
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
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
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				setRoleName("0");
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
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != occHeaExa){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != occHeaExa.getAreaId()) && (0 < occHeaExa.getAreaId().trim().length())){
				paraMap.put("areaId",  occHeaExa.getAreaId().trim() );
			}

			if ((null != occHeaExa.getAreaName()) && (0 < occHeaExa.getAreaName().trim().length())){
				paraMap.put("areaName", occHeaExa.getAreaName().trim());
			}

			if ((null != occHeaExa.getCompanyName()) && (0 < occHeaExa.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + occHeaExa.getCompanyName().trim() + "%");
			}

			if ((null != occHeaExa.getMedicalInstitutionName()) && (0 < occHeaExa.getMedicalInstitutionName().trim().length())){
				paraMap.put("medicalInstitutionName", "%" + occHeaExa.getMedicalInstitutionName().trim() +"%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|medicalInstitution|medicalInstitutionName|createTime|";
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
		pagination = occHeaExaService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	@SuppressWarnings("unchecked")
	public String view() throws Exception{
		if((null != occHeaExa)&&(null != occHeaExa.getId())){
			occHeaExa = occHeaExaService.getById(occHeaExa.getId());
			if(null!=occHeaExa){
				if(null!=occHeaExa.getId()){
					Map<String, Object> paraMap1 = new HashMap<String, Object>();
					paraMap1.put("occHeaExaId", occHeaExa.getId());
					occHeaExaLists=occHeaExaService.findOccHeaExaLists(paraMap1);
				}
			}else{
				occHeaExa=new OccHeaExa();
			}
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
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			occHeaExa.setAreaId(enBaseInfo.getEnterprisePossession());
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("codeName", "企业属地");
			m.put("itemValue", enBaseInfo.getEnterprisePossession());
			occHeaExa.setAreaName(codeService.findCodeValueByMap(m).getItemText());
			occHeaExa.setCompanyId(enBaseInfo.getId());
			occHeaExa.setCompanyName(enBaseInfo.getEnterpriseName());
			
			if ("add".equalsIgnoreCase(this.flag)){
				occHeaExa.setDeptId(this.getLoginUserDepartmentId());
				occHeaExa.setDelFlag(0);
				occHeaExaService.save(occHeaExa);
			}else{
				occHeaExaService.update(occHeaExa);
			}
			
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("sqlId", "deleteOccHeaExaListByOccHeaExaId");
			paraMap.put("occHeaExaId", occHeaExa.getId());
			httpService.updateByMap(paraMap);
			if(null!=occHeaExaList){
				if(null!=occHeaExaList.getOccupationalDiseasName()){
					String[] occupationalDiseasNames = occHeaExaList.getOccupationalDiseasName() .replaceAll(" ", "").split(",");
					String[] preOccupationHealthNumbers = occHeaExaList.getPreOccupationHealthNumber() .replaceAll(" ", "").split(",");
					String[] postOccupationalHealths = occHeaExaList.getPostOccupationalHealth() .replaceAll(" ", "").split(",");
					String[] postOccupationHealthNumbers = occHeaExaList.getPostOccupationHealthNumber() .replaceAll(" ", "").split(",");
					String[] foundPostsNumbers = occHeaExaList.getFoundPostsNumber() .replaceAll(" ", "").split(",");
					String[] actualPositionNumbers = occHeaExaList.getActualPositionNumber().replaceAll(" ", "").split(",");
					for(int i=0;i<occupationalDiseasNames.length;i++){
						OccHeaExaList oci=new OccHeaExaList();
						oci.setOccHeaExaId(occHeaExa.getId());
						oci.setDelFlag(0);
						oci.setDeptId(this.getLoginUserDepartmentId());
						oci.setOccupationalDiseasName(occupationalDiseasNames[i]);
						if(i<preOccupationHealthNumbers.length){
							oci.setPreOccupationHealthNumber(preOccupationHealthNumbers[i]);
						}else{
							oci.setPreOccupationHealthNumber("");
						}
						if(i<postOccupationalHealths.length){
							oci.setPostOccupationalHealth(postOccupationalHealths[i]);
						}else{
							oci.setPostOccupationalHealth("");
						}
						if(i<postOccupationHealthNumbers.length){
							oci.setPostOccupationHealthNumber(postOccupationHealthNumbers[i]);
						}else{
							oci.setPostOccupationHealthNumber("");
						}
						if(i<foundPostsNumbers.length){
							oci.setFoundPostsNumber(foundPostsNumbers[i]);
						}else{
							oci.setFoundPostsNumber("");
						}
						if(i<actualPositionNumbers.length){
							oci.setActualPositionNumber(actualPositionNumbers[i]);
						}else{
							oci.setActualPositionNumber("");
						}
						occHeaExaService.saveOccHeaExaList(oci);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != occHeaExa)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到occHeaExa中去
				
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
			occHeaExaService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	/**
	 * 删除信息 occHeaExaList
	 */
	public String occHeaExaListDel() throws Exception{
	    try{
			occHeaExaService.deleteOccHeaExaListWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	/**
	 * 导入初始化，需要传递参数
	 */
	public String initImport(){
		return SUCCESS;
	}
	
	
	/**
	 * 导入告知卡
	 */
	public String importOccHeaExa(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		StringBuffer errorInfo = new StringBuffer();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		occHeaExa.setAreaId(enBaseInfo.getEnterprisePossession());
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		occHeaExa.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		occHeaExa.setCompanyId(enBaseInfo.getId());
		occHeaExa.setCompanyName(enBaseInfo.getEnterpriseName());
		occHeaExa.setDeptId(this.getLoginUserDepartmentId());
		occHeaExa.setDelFlag(0);
		occHeaExaService.save(occHeaExa);
		
			Workbook workbook = null;
			try {
				workbook = Workbook.getWorkbook(userFile);
			} catch (Exception e) {
				e.printStackTrace();
				message = "导入失败，请使用系统模板！";
			}
			Sheet sheet = workbook.getSheet(0);
			
			int column = sheet.getColumns();
			int row = sheet.getRows();
			List<OccHeaExaList> productionManageList = new ArrayList<OccHeaExaList>();
			for (int i = 1; i < row; i++) {
				
				try {
					Cell[] cells = new Cell[column];
					Cell[] cellsTmp = sheet.getRow(i);
					for (int j = 0; j < cellsTmp.length; j++) {
						cells[j] = cellsTmp[j];
					}
					
					if ((cells[0]==null ||cells[0].getContents().equals(""))&&(cells[1]==null ||cells[1].getContents().equals(""))&&(cells[2]==null ||cells[2].getContents().equals(""))&&(cells[3]==null ||cells[3].getContents().equals(""))&&(cells[4]==null ||cells[4].getContents().equals(""))&&(cells[5]==null ||cells[5].getContents().equals("")) ){
						workbook.close();
						break;
					}
					
					OccHeaExaList oci = new OccHeaExaList();
					oci.setOccHeaExaId(occHeaExa.getId());
					oci.setDelFlag(0);
					oci.setDeptId(this.getLoginUserDepartmentId());
					StringBuffer colError = null;
					
					if (cells[0] != null){
						oci.setOccupationalDiseasName(cells[0].getContents().trim());
					}
					if(cells[1] != null){
						oci.setPreOccupationHealthNumber(cells[1].getContents().trim());
					}
					if(cells[2] != null){
						oci.setPostOccupationalHealth(cells[2].getContents().trim());
					}
					if(cells[3] != null){
						oci.setPostOccupationHealthNumber(cells[3].getContents().trim());
					}
					if(cells[4] != null){
						oci.setFoundPostsNumber(cells[4].getContents().trim());
					}
					if(cells[5] != null){
						oci.setActualPositionNumber(cells[5].getContents().trim());
					}
					
					if(colError==null){
						productionManageList.add(oci);
						errorInfo.append("导入第").append(i).append("条记录成功！<br><br>");
					}else{
						errorInfo.append("导入第").append(i).append("条记录失败，错误信息如下：<br>");
						errorInfo.append(colError).append("<br>");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			message = errorInfo.toString();
			try {
				for(OccHeaExaList s:productionManageList){
					occHeaExaService.saveOccHeaExaList(s);
				}
			} catch (Exception e) {
				workbook.close();
					message = "导入失败，请使用系统模板！";
				BasalException ex = new BasalException(BasalException.ERROR, Constants.SAVING_DB_ERROR, e.getCause());
				throw ex;
			}
			workbook.close();
			if(message.equals("") && productionManageList.size() == 0)
			{
				message = "导入失败，未读取到数据，请使用系统模板！";
			}
			if(message.contains("失败")){
				return ERROR;
			}else{
				message="";
				return SUCCESS;
			}
		
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
		String userId=user.getId();
		if(httpService.judgeRoleCode(userId, "A23")){//企业角色
			setRoleName("0");
		} 
		return SUCCESS;
		
	}
    /**
     * 政务通 职业健康体检
     * lj
     * 2016-06-07
     * @throws Exception
     */
    public String zyjktj(){
    	return SUCCESS;
    }

	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != occHeaExa){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != occHeaExa.getAreaId()) && (0 < occHeaExa.getAreaId().trim().length())){
				paraMap.put("areaId",  occHeaExa.getAreaId().trim() );
			}

			if ((null != occHeaExa.getAreaName()) && (0 < occHeaExa.getAreaName().trim().length())){
				paraMap.put("areaName", occHeaExa.getAreaName().trim());
			}

			if ((null != occHeaExa.getCompanyName()) && (0 < occHeaExa.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + occHeaExa.getCompanyName().trim() + "%");
			}

			if ((null != occHeaExa.getMedicalInstitutionName()) && (0 < occHeaExa.getMedicalInstitutionName().trim().length())){
				paraMap.put("medicalInstitutionName", "%" + occHeaExa.getMedicalInstitutionName().trim() +"%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|medicalInstitution|medicalInstitutionName|createTime|";
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
		
		
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称或体检机构".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = occHeaExaService.findByPage(pagination, paraMap);
		
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

	public OccHeaExa getOccHeaExa(){
		return this.occHeaExa;
	}

	public void setOccHeaExa(OccHeaExa occHeaExa){
		this.occHeaExa = occHeaExa;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<OccHeaExaList> getOccHeaExaLists() {
		return occHeaExaLists;
	}

	public void setOccHeaExaLists(List<OccHeaExaList> occHeaExaLists) {
		this.occHeaExaLists = occHeaExaLists;
	}

	public OccHeaExaList getOccHeaExaList() {
		return occHeaExaList;
	}

	public void setOccHeaExaList(OccHeaExaList occHeaExaList) {
		this.occHeaExaList = occHeaExaList;
	}

	public File getUserFile() {
		return userFile;
	}

	public void setUserFile(File userFile) {
		this.userFile = userFile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
       
    
}
