package com.jshx.aqscfysytz.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
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
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.aqscfysytz.entity.SecProFeeAcc;
import com.jshx.aqscfysytz.service.SecProFeeAccService;
import com.jshx.aqscfytqqk.entity.SecProFeeExt;
import com.jshx.aqscfytqqk.service.SecProFeeExtService;

public class SecProFeeAccAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private SecProFeeAcc secProFeeAcc = new SecProFeeAcc();

	/**
	 * 业务类
	 */
	@Autowired
	private SecProFeeAccService secProFeeAccService;
	@Autowired
	private SecProFeeExtService secProFeeExtService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;
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
	
	
	private String queryFeeAccountMonthStart;

	private String queryFeeAccountMonthEnd;
	
	private String roleName;
	
	private List<SecProFeeExt> list = new ArrayList<SecProFeeExt>();

	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public String init(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				roleName = "0";
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
		    
		if(null != secProFeeAcc){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != secProFeeAcc.getAreaId()) && (0 < secProFeeAcc.getAreaId().trim().length())){
				paraMap.put("areaId", secProFeeAcc.getAreaId().trim());
			}

			if ((null != secProFeeAcc.getCompanyName()) && (0 < secProFeeAcc.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + secProFeeAcc.getCompanyName().trim() + "%");
			}

			if (null != queryFeeAccountMonthStart){
				paraMap.put("startFeeAccountMonth", queryFeeAccountMonthStart);
			}
			if ((null != secProFeeAcc.getFeeAccountProject()) && (0 < secProFeeAcc.getFeeAccountProject().trim().length())){
				paraMap.put("feeAccountProject", secProFeeAcc.getFeeAccountProject().trim());
			}

			if (null != queryFeeAccountMonthEnd){
				paraMap.put("endFeeAccountMonth", queryFeeAccountMonthEnd);
			}

		}
		try {
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
			Map<String, String> codeMap = new HashMap<String, String>();
			//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
			config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
			final String filter = "id|areaName|companyName|feeAccountAmount|feeAccountMonth|companyId|feeAccountRemark|";
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
			pagination = secProFeeAccService.findByPage(pagination, paraMap);
			
			convObjectToJson(pagination, config);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(null != secProFeeAcc){
			if ((null != secProFeeAcc.getCompanyId()) && (0 < secProFeeAcc.getCompanyId().trim().length())){
				paraMap.put("companyId",secProFeeAcc.getCompanyId().trim());
			}

			if ((null != secProFeeAcc.getFeeAccountMonth()) && (0 < secProFeeAcc.getFeeAccountMonth().trim().length())){
				paraMap.put("feeAccountMonth", secProFeeAcc.getFeeAccountMonth());
			}
			
			list  =  secProFeeExtService.findSecProFeeExt(paraMap);
			
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
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		secProFeeAcc.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		secProFeeAcc.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		secProFeeAcc.setCompanyId(enBaseInfo.getId());
		secProFeeAcc.setCompanyName(enBaseInfo.getEnterpriseName());
		if ("add".equalsIgnoreCase(this.flag)){
			secProFeeAcc.setDeptId(this.getLoginUserDepartmentId());
			secProFeeAcc.setDelFlag(0);
			secProFeeAccService.save(secProFeeAcc);
		}else{
			secProFeeAccService.update(secProFeeAcc);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != secProFeeAcc)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到secProFeeAcc中去
				
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
			secProFeeAccService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void exportXls() {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(flag == null || "".equals(flag))
		{
			secProFeeAcc = (SecProFeeAcc) getSessionAttribute("secProFeeAcc");
			queryFeeAccountMonthStart = (String) getSessionAttribute("queryFeeAccountMonthStart");
			queryFeeAccountMonthEnd = (String) getSessionAttribute("queryFeeAccountMonthEnd");
		}
		if(null != secProFeeAcc){
			setSessionAttribute("secProFeeAcc", secProFeeAcc);
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != secProFeeAcc.getAreaId()) && (0 < secProFeeAcc.getAreaId().trim().length())){
				paraMap.put("areaId", secProFeeAcc.getAreaId().trim());
			}

			if ((null != secProFeeAcc.getCompanyName()) && (0 < secProFeeAcc.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + secProFeeAcc.getCompanyName().trim() + "%");
			}

			if (null != queryFeeAccountMonthStart){
				paraMap.put("startFeeAccountMonth", queryFeeAccountMonthStart);
				setSessionAttribute("queryFeeAccountMonthStart", queryFeeAccountMonthStart);
			}
			if ((null != secProFeeAcc.getFeeAccountProject()) && (0 < secProFeeAcc.getFeeAccountProject().trim().length())){
				paraMap.put("feeAccountProject", secProFeeAcc.getFeeAccountProject().trim());
			}

			if (null != queryFeeAccountMonthEnd){
				paraMap.put("endFeeAccountMonth", queryFeeAccountMonthEnd);
				setSessionAttribute("queryFeeAccountMonthEnd", queryFeeAccountMonthEnd);
			}

		}
		try {
			List<SecProFeeAcc> list = secProFeeAccService.findSecProFeeAcc(paraMap);
			
			HttpServletResponse res=ServletActionContext.getResponse();
			res.setContentType("application/vnd.ms-excel") ;//excel的MIME类型
			String fileName="安全生产费用使用台账.xls";
			try {
				res.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("安全生产费用使用台账");
			sheet.setColumnWidth(0, 4000);
			sheet.setColumnWidth(1, 10000);
			sheet.setColumnWidth(2, 4000);
			sheet.setColumnWidth(3, 8000);
			
			CellStyle style = wb.createCellStyle();
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
			style.setFont(font);
			
			HSSFRow row = sheet.createRow(0); 
			row.setHeight((short)500);
			HSSFCell numberCell = row.createCell(0);  
			numberCell.setCellValue("所在区域");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(1);
			numberCell.setCellValue("企业名称");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(2);
	    	numberCell.setCellValue("使用月份 ");
	    	numberCell.setCellStyle(style);
			
	    	numberCell = row.createCell(3);
	    	numberCell.setCellValue("支出金额 ");
	    	numberCell.setCellStyle(style);
			
			for (int i = 0; i < list.size(); i++) {
				secProFeeAcc = list.get(i);
				row = sheet.createRow(i+1); 
				row.setHeight((short)500);				
				numberCell = row.createCell(0);  
				numberCell.setCellValue(secProFeeAcc.getAreaName());
								
				numberCell = row.createCell(1);
				numberCell.setCellValue(secProFeeAcc.getCompanyName());
								
				numberCell = row.createCell(2);
				numberCell.setCellValue(secProFeeAcc.getFeeAccountMonth());
								
				numberCell = row.createCell(3);
				numberCell.setCellValue(secProFeeAcc.getFeeAccountAmount());
	    	}
			try
			{
				wb.write(res.getOutputStream());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}catch (Exception e)
		{
			e.printStackTrace();
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
		if(httpService.judgeRoleCode(userId, "A23")){
			roleName = "0";
		}
		return SUCCESS;
	}

   public String aqscfysytz(){
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
		    
		if(null != secProFeeAcc){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != secProFeeAcc.getAreaId()) && (0 < secProFeeAcc.getAreaId().trim().length())){
				paraMap.put("areaId", secProFeeAcc.getAreaId().trim());
			}

			if ((null != secProFeeAcc.getCompanyName()) && (0 < secProFeeAcc.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + secProFeeAcc.getCompanyName().trim() + "%");
			}

			if (null != queryFeeAccountMonthStart){
				paraMap.put("startFeeAccountMonth", queryFeeAccountMonthStart);
			}
			if ((null != secProFeeAcc.getFeeAccountProject()) && (0 < secProFeeAcc.getFeeAccountProject().trim().length())){
				paraMap.put("feeAccountProject", secProFeeAcc.getFeeAccountProject().trim());
			}

			if (null != queryFeeAccountMonthEnd){
				paraMap.put("endFeeAccountMonth", queryFeeAccountMonthEnd);
			}

		}
		
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称".equals(searchLike)){
			paraMap.put("companyName", "%" + searchLike.trim() + "%");
		}
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|feeAccountAmount|feeAccountMonth|companyId|feeAccountRemark|";
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
		pagination = secProFeeAccService.findByPage(pagination, paraMap);
		
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

	public SecProFeeAcc getSecProFeeAcc(){
		return this.secProFeeAcc;
	}

	public void setSecProFeeAcc(SecProFeeAcc secProFeeAcc){
		this.secProFeeAcc = secProFeeAcc;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public String getQueryFeeAccountMonthStart(){
		return this.queryFeeAccountMonthStart;
	}

	public void setQueryFeeAccountMonthStart(String queryFeeAccountMonthStart){
		this.queryFeeAccountMonthStart = queryFeeAccountMonthStart;
	}

	public String getQueryFeeAccountMonthEnd(){
		return this.queryFeeAccountMonthEnd;
	}

	public void setQueryFeeAccountMonthEnd(String queryFeeAccountMonthEnd){
		this.queryFeeAccountMonthEnd = queryFeeAccountMonthEnd;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<SecProFeeExt> getList() {
		return list;
	}

	public void setList(List<SecProFeeExt> list) {
		this.list = list;
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
