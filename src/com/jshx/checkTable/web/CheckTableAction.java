package com.jshx.checkTable.web;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.checkResult.entity.CheckResult;
import com.jshx.checkResult.service.CheckResultService;
import com.jshx.checkTable.entity.CheckTable;
import com.jshx.checkTable.service.CheckTableService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;

public class CheckTableAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private CheckTable checkTable = new CheckTable();

	/**
	 * 业务类
	 */
	@Autowired
	private CheckTableService checkTableService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CheckResultService checkResultService;
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
	
	
	private Date queryCheckTimeStart;

	private Date queryCheckTimeEnd;
	/**
	 * excel中的检查内容
	 */
	private List<String> checkContentList = new ArrayList<String>();
	
	private   List<CheckResult> checkResultList = new ArrayList<CheckResult>();
	private String roleName = "";
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	
	public String init(){
		if(httpService.judgeRoleCode(this.getLoginUserId(), "A23")){
			roleName = "0";
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
		    
		if(null != checkTable){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != checkTable.getCompanyName()) && (0 < checkTable.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + checkTable.getCompanyName().trim() + "%");
			}

			if (null != queryCheckTimeStart){
				paraMap.put("startCheckTime", queryCheckTimeStart);
			}

			if (null != queryCheckTimeEnd){
				paraMap.put("endCheckTime", queryCheckTimeEnd);
			}
			
			if ((null != checkTable.getCompanyType()) && (0 < checkTable.getCompanyType().trim().length())){
				paraMap.put("companyType", checkTable.getCompanyType().trim());
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|companyName|checkTime|areaName|";
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
		pagination = checkTableService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != checkTable)&&(null != checkTable.getId())){
			checkTable = checkTableService.getById(checkTable.getId());
			Map map = new HashMap();
			map.put("checkTableId", checkTable.getId());
			checkResultList=checkResultService.findCheckResult(map);
		}
		
		if(httpService.judgeRoleCode(this.getLoginUserId(), "A23")){
			Map map = new HashMap();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo e = entBaseInfoService.findEntBaseInfoByMap(map);
			checkTable.setAreaId(e.getEnterprisePossession());
			checkTable.setAreaName(e.getEnterprisePossessionName());
			checkTable.setCompanyId(e.getId());
			checkTable.setCompanyName(e.getEnterpriseName());
		}
		
		String root = this.getRequest().getRealPath("/"); 
		root = root.replaceAll("\\\\", "/");
		String fileName = "";
		if("1".equals(checkTable.getCompanyType())){
			fileName = "1hg.xls";
		}else if("2".equals(checkTable.getCompanyType())){
			fileName = "2zyws.xls";
		}else if("3".equals(checkTable.getCompanyType())){
			fileName = "3sazl.xls";
		}else if("4".equals(checkTable.getCompanyType())){
			fileName = "4sbfc.xls";
		}else if("5".equals(checkTable.getCompanyType())){
			fileName = "5yj.xls";
		}else if("6".equals(checkTable.getCompanyType())){
			fileName = "6sxkjzy.xls";
		}
		
		InputStream is= new FileInputStream(root + fileName);
		Workbook wb = Workbook.getWorkbook(is);
		Sheet sheet = wb.getSheet(0);
		int row = sheet.getRows();
		for (int i = 1; i < row; i++) {
			Cell[] cells = sheet.getRow(i);
			checkContentList.add(cells[0].getContents().trim());
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
	
		if ("add".equalsIgnoreCase(this.flag)){
			checkTable.setDeptId(this.getLoginUserDepartmentId());
			checkTable.setDelFlag(0);
			checkTableService.save(checkTable);
			for(CheckResult cr:checkResultList){
				cr.setCheckTableId(checkTable.getId());
				cr.setDelFlag(0);
				checkResultService.update(cr);
			}
		}else{
			checkTableService.update(checkTable);
			//删除之前的检查结果
			Map map = new HashMap();
			map.put("checkTableId", checkTable.getId());
			map.put("sqlId", "deleteOldResults");
			httpService.updateByMap(map);
			//保存检查结果
			for(CheckResult cr:checkResultList){
				cr.setCheckTableId(checkTable.getId());
				cr.setDelFlag(0);
				checkResultService.update(cr);
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
		if (null != checkTable)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到checkTable中去
				
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
			checkTableService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
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

	public CheckTable getCheckTable(){
		return this.checkTable;
	}

	public void setCheckTable(CheckTable checkTable){
		this.checkTable = checkTable;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryCheckTimeStart(){
		return this.queryCheckTimeStart;
	}

	public void setQueryCheckTimeStart(Date queryCheckTimeStart){
		this.queryCheckTimeStart = queryCheckTimeStart;
	}

	public Date getQueryCheckTimeEnd(){
		return this.queryCheckTimeEnd;
	}

	public void setQueryCheckTimeEnd(Date queryCheckTimeEnd){
		this.queryCheckTimeEnd = queryCheckTimeEnd;
	}

	public List<String> getCheckContentList() {
		return checkContentList;
	}

	public void setCheckContentList(List<String> checkContentList) {
		this.checkContentList = checkContentList;
	}
	public List<CheckResult> getCheckResultList() {
		return checkResultList;
	}
	public void setCheckResultList(List<CheckResult> checkResultList) {
		this.checkResultList = checkResultList;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	

}
