package com.jshx.inspectItem.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.inspectItem.entity.InspectItem;
import com.jshx.inspectItem.service.InspectItemService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.DeptService;
import com.jshx.safeInspectDistribute.entity.SafeInspectDistribute;

public class InspectItemAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private InspectItem inspectItem = new InspectItem();

	/**
	 * 业务类
	 */
	@Autowired
	private InspectItemService inspectItemService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	private String companyFlag;
	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	/**
	 * 导入结果信息
	 */
	private String logInfo;

	/**
	 * 导入源文件
	 */
	private File importFile;
	
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		
		CompanyBackUp company = companyService.getCompanyByLoginId(deptService.findDeptByDeptCode(this.getLoginUser().getDeptCode().substring(0,6)).getCreateUserID());
		this.companyFlag = company.getId();
		
		if(null != inspectItem){
			inspectItem.setCompanyFlag(this.companyFlag);
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != inspectItem.getCompanyFlag()) && (0 < inspectItem.getCompanyFlag().trim().length())){
				paraMap.put("companyFlag", "%" + inspectItem.getCompanyFlag().trim() + "%");
			}

			if ((null != inspectItem.getInspectType()) && (0 < inspectItem.getInspectType().trim().length())){
				paraMap.put("inspectType", "%" + inspectItem.getInspectType().trim() + "%");
			}

			if ((null != inspectItem.getItem()) && (0 < inspectItem.getItem().trim().length())){
				paraMap.put("item", "%" + inspectItem.getItem().trim() + "%");
			}

			if ((null != inspectItem.getRequirement()) && (0 < inspectItem.getRequirement().trim().length())){
				paraMap.put("requirement", "%" + inspectItem.getRequirement().trim() + "%");
			}

		}
		
		pagination = inspectItemService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != inspectItem)&&(null != inspectItem.getId()))
			inspectItem = inspectItemService.getById(inspectItem.getId());
		User user = this.getLoginUser();
		CompanyBackUp company = companyService.getCompanyByLoginId(deptService.findDeptByDeptCode(this.getLoginUser().getDeptCode().substring(0,6)).getCreateUserID());
		this.companyFlag = company.getId();
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
			User user = this.getLoginUser();
			CompanyBackUp company = companyService.getCompanyByLoginId(deptService.findDeptByDeptCode(this.getLoginUser().getDeptCode().substring(0,6)).getCreateUserID());
			inspectItem.setCompanyFlag(company.getId());
			inspectItem.setDeptId(this.getLoginUserDepartmentId());
			inspectItem.setDelFlag(0);
			inspectItemService.save(inspectItem);
		}else{
			inspectItemService.update(inspectItem);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			inspectItemService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 获取json列表
	 */
	public void jsonList(){
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != inspectItem){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != inspectItem.getCompanyFlag()) && (0 < inspectItem.getCompanyFlag().trim().length())){
				paraMap.put("companyFlag", "%" + inspectItem.getCompanyFlag().trim() + "%");
			}

			if ((null != inspectItem.getInspectType()) && (0 < inspectItem.getInspectType().trim().length())){
				paraMap.put("inspectType", "%" + inspectItem.getInspectType().trim() + "%");
			}

			if ((null != inspectItem.getItem()) && (0 < inspectItem.getItem().trim().length())){
				paraMap.put("item", "%" + inspectItem.getItem().trim() + "%");
			}

			if ((null != inspectItem.getRequirement()) && (0 < inspectItem.getRequirement().trim().length())){
				paraMap.put("requirement", "%" + inspectItem.getRequirement().trim() + "%");
			}

		}
		pagination.setPageSize(1000);
		pagination = inspectItemService.findByPage(pagination, paraMap);
		
		List<InspectItem> itemList = pagination.getListOfObject();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for (InspectItem inspectItem : itemList) {
			Map<String,String> map = new HashMap<String,String>();
			map.put("item",inspectItem.getItem());
			map.put("requirement",inspectItem.getRequirement());
			list.add(map);
		}
		
		JSON json = JSONSerializer.toJSON(list);
		try {
			this.getResponse().getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化导入 
	 * GY-UPDATE 2015-02-02
	 */
	public String initImport()
	{
		return SUCCESS;
	}
	
	/**
	 * 
	 */
	public String importSave()
	{
		StringBuffer log = new StringBuffer("导入安全检查项配置：<br>");
		Workbook workbook = null;
		try
		{
			workbook = Workbook.getWorkbook(this.importFile);
		}
		catch (BiffException e)
		{
			log.append("读取Excle文件出错，请检查!");
			this.logInfo = log.toString();
			return SUCCESS;
		}
		catch (IOException e)
		{
			log.append("读取Excle文件出错，请检查!");
			this.logInfo = log.toString();
			return SUCCESS;
		}

		Sheet sheet = workbook.getSheet(0);
		int column = sheet.getColumns();
		if (column < 3)
		{
			log.append("缺少列信息；<br>");
			workbook.close();
		}
		else
		{
			int row = sheet.getRows();
			int k = 0;
			for (int i = 1; i < row; ++i)
			{
				++k;
				Cell[] cells = new Cell[column];
				Cell[] cellsTmp = sheet.getRow(i);
				for (int j = 0; j < cellsTmp.length; ++j)
				{
					cells[j] = cellsTmp[j];
				}

				InspectItem item = new InspectItem();
				CompanyBackUp company = companyService.getCompanyByLoginId(deptService.findDeptByDeptCode(this.getLoginUser().getDeptCode().substring(0,6)).getCreateUserID());
				item.setCompanyFlag(company.getId());
				// 处理安全检查类型
				item.setInspectType(cells[0].getContents());
				// 处理巡检项
				item.setItem(cells[1].getContents());
				// 巡检要求
				item.setRequirement(cells[2].getContents());
				item.setDelFlag(0);
				item.setId(null);
				inspectItemService.save(item);
				log.append("保存安全检查项配置").append(cells[1].getContents().trim()).append("的信息<br>");
			}
			 workbook.close();
		}
		this.logInfo = log.toString();
		return SUCCESS;
	}
	
	public String getSuggestion() throws IOException {
		String inspectItem = getRequest().getParameter("inspectItem");// 得到输入的值
		String inspecttype = getRequest().getParameter("inspecttype");//检查类型
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("inspectType", inspecttype.trim());
		paraMap.put("item", "%" + inspectItem.trim() + "%");
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		pagination.setPageSize(1000);
		pagination = inspectItemService.findByPage(pagination, paraMap);
		List<InspectItem> itemList = pagination.getListOfObject();
		JSONArray jarray = new JSONArray();// 下面是组装成json格式
		for (int i = 0; i < itemList.size(); i++) {
			JSONObject jo = new JSONObject();
			InspectItem obj = (InspectItem) itemList.get(i);
			jo.put("id", obj.getId());
			jo.put("item", obj.getItem());
			jo.put("requirement", obj.getRequirement());
			jarray.add(jo);
		}
		getResponse().getWriter().print(jarray);// 送回客户端
		
		return NONE;
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

	public InspectItem getInspectItem(){
		return this.inspectItem;
	}

	public void setInspectItem(InspectItem inspectItem){
		this.inspectItem = inspectItem;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public String getCompanyFlag() {
		return companyFlag;
	}

	public void setCompanyFlag(String companyFlag) {
		this.companyFlag = companyFlag;
	}

	public String getLogInfo()
	{
		return logInfo;
	}

	public void setLogInfo(String logInfo)
	{
		this.logInfo = logInfo;
	}

	public File getImportFile()
	{
		return importFile;
	}

	public void setImportFile(File importFile)
	{
		this.importFile = importFile;
	}
	
}
