package com.jshx.zfyj.web;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
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

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.Constants;
import com.jshx.zfyj.entity.LawBasis;
import com.jshx.zfyj.service.LawBasisService;
import com.jshx.zfyjlb.entity.LawBase;
import com.jshx.zfyjlb.service.LawBaseService;

public class LawBasisAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private LawBasis lawBasis = new LawBasis();

	/**
	 * 业务类
	 */
	@Autowired
	private LawBasisService lawBasisService;
	@Autowired
	private LawBaseService lawBaseService;

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
	
	private List<LawBasis> list = new ArrayList<LawBasis>();
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public String init(){
		return SUCCESS;
	}
	
	public String inits()
	{
		if(ids != null && !"".equals(ids))
		{
			String ss[] = ids.replaceAll(" ", "").split(",");
			List<String> s = new ArrayList<String>();
			for(String a:ss)
			{
				s.add(a);
			}
			Map map = new HashMap();
			map.put("ids", s);
			list = lawBasisService.findLawBasis(map);
		}
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != lawBasis){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != lawBasis.getLawName()) && (0 < lawBasis.getLawName().trim().length())){
				paraMap.put("lawName", "%" + lawBasis.getLawName().trim() + "%");
			}
			
			if ((null != lawBasis.getLawId()) && (0 < lawBasis.getLawId().trim().length())){
				paraMap.put("lawId", lawBasis.getLawId().trim() );
			}

			if ((null != lawBasis.getLawProvision()) && (0 < lawBasis.getLawProvision().trim().length())){
				paraMap.put("lawProvision", "%" + lawBasis.getLawProvision().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|lawName|lawProvision|lawContent|";
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
		pagination = lawBasisService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != lawBasis)&&(null != lawBasis.getId()))
			lawBasis = lawBasisService.getById(lawBasis.getId());
		
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
		LawBase lawBase = lawBaseService.getById(lawBasis.getLawId());
		lawBasis.setLawName(lawBase.getLawName());
	
		if ("add".equalsIgnoreCase(this.flag)){
			lawBasis.setDeptId(this.getLoginUserDepartmentId());
			lawBasis.setDelFlag(0);
			lawBasisService.save(lawBasis);
		}else{
			lawBasisService.update(lawBasis);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != lawBasis)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到lawBasis中去
				
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
			lawBasisService.deleteWithFlag(ids);
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
	public String importLawBasis(){
		LawBase lawBase = lawBaseService.getById(lawBasis.getLawId());
		StringBuffer errorInfo = new StringBuffer();
		
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
		List<LawBasis> productionManageList = new ArrayList<LawBasis>();
		for (int i = 1; i < row; i++) {
			
			try {
				Cell[] cells = new Cell[column];
				Cell[] cellsTmp = sheet.getRow(i);
				for (int j = 0; j < cellsTmp.length; j++) {
					cells[j] = cellsTmp[j];
				}
				
				if (cells[0]==null ||cells[0].getContents().equals("")){
					workbook.close();
					break;
				}
				LawBasis ll = new LawBasis();
				ll.setSort(i);
				ll.setLawId(lawBase.getId());
				ll.setLawName(lawBase.getLawName());
				ll.setDelFlag(0);
				ll.setDeptId(this.getLoginUserDepartmentId());
				StringBuffer colError = null;
				if (cells[0]==null || cells[0].getContents().equals("")) {
					if(colError==null)
						colError = new StringBuffer();
					colError.append("<span style='color:red'>错误：法律法规条款项输入有误，不能为空，请检查!</span><br>");
				}else{
					ll.setLawProvision(cells[0].getContents().trim());
				}
				if(cells[1] != null){
					ll.setLawContent(cells[1].getContents().trim());
				}
				if(cells[2] != null){
					ll.setRemark(cells[2].getContents().trim());
				}
				
				if(colError==null){
					productionManageList.add(ll);
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
			for(LawBasis s:productionManageList){
				lawBasisService.save(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
			workbook.close();
				message = "导入失败！";
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

	public LawBasis getLawBasis(){
		return this.lawBasis;
	}

	public void setLawBasis(LawBasis lawBasis){
		this.lawBasis = lawBasis;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
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

	public List<LawBasis> getList() {
		return list;
	}

	public void setList(List<LawBasis> list) {
		this.list = list;
	}
       
    
}
