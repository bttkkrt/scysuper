/**
 * 
 */
package com.jshx.module.form.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;

import com.jshx.module.admin.entity.Code;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.form.entity.FormField;
import com.jshx.module.form.entity.FormTable;
import com.jshx.module.form.service.FormFieldService;
import com.jshx.module.form.service.FormTableService;
/**
 * @author caron
 *
 */
@SuppressWarnings("serial")
public class FormFieldAction  extends BaseAction {
	
	@Autowired
	private FormTableService formTableService;
	
	@Autowired
	private FormFieldService formFieldService;
		
	@Autowired
	private CodeService codeService;
	
	private String tableId;
	
	private FormField field;
	
	//private String dbType;
	
	private String ids;
	
	
	private Pagination pagination;
	
	private int flag; //操作标识 1保存成功 2修改成功 
	
	private List<Code> codes;//编码集合
	
	private String message;
	
	private FormTable formTable;
	
	private String[] dispInGrid;
	private String[] isQueryCondition;
	private String[] isSortField;

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public FormField getField() {
		return field;
	}

	public void setField(FormField field) {
		this.field = field;
	}
	
	public String initList(){
		formTable = formTableService.get(tableId);
		return SUCCESS;
	}

	
	public FormTable getFormTable() {
		return formTable;
	}

	public void setFormTable(FormTable formTable) {
		this.formTable = formTable;
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.web.AbstractActionSupport#save()
	 */
	public void save() throws Exception {
		//System.out.println("displayed name : "+field.getFieldDisplayName());
		try{
			if(field!=null){
				if(field.getId()!=null&&field.getId().length()!=0){
					field.setTable(new FormTable(tableId));
					formFieldService.modifyField(field);
					flag=2;
					codes = codeService.getAllCode();
					message="修改成功！";
				}else{
					int maxSortSQ = formFieldService.getMaxSortSQ(tableId);
					if (field.getSortSQ()==null||field.getSortSQ()==0) {
						field.setSortSQ(maxSortSQ+1);
					}
					
					field.setTable(new FormTable(tableId));
					field.setFieldName(field.getFieldName().trim());
					if(field.getGridMultiRows()==null)
						field.setGridMultiRows(1);
					if(field.getFieldLength()==null){
						field.setFieldLength(255);
					}
					if(field.getDecimalLength()==null){
						field.setDecimalLength(0);
					}
					formFieldService.addColumn(tableId, field);
					flag=1;
//					field = new FormField();
//					field.setFieldType("varchar");
//					field.setFieldDisplayType("TextBox");
					getAllCodes();
					message="新建成功！";
				}
			}
			try{
				getResponse().getWriter().println("{\"result\":true,\"message\":\""+message+"\"}");	
			}catch(Exception e){
				
			}			
		}catch(Exception ex){
			message = ex.getLocalizedMessage();
			try{
				getResponse().getWriter().println("{\"result\":false,\"message\":\""+message+"\"}");	
			}catch(Exception e){
				
			}
		}
	}
	
	public void checkFieldName() throws Exception{
		boolean flag = true;
		String oldFieldName = this.field.getFieldName();
		String fieldId = this.field.getId();
		String fieldName = this.getRequestParameter("param");
	    
		if(!"".equals(oldFieldName) && oldFieldName.equals(fieldName) && !"".equals(fieldId)){

		}else{
			if(formFieldService.isExist(this.tableId,fieldName)){
				flag = false;
			}
		}
		
		try{
			if(flag==false){
				getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"该字段名称不符合命名规范或者在数据表中已存在！\"}");
			}else{
				getResponse().getWriter().println("{\"status\":\"y\"}");
			}
		}catch(Exception e){
		}			
		
		
	}

	public void delete() throws Exception {
		try{
			String[] idArray = ids.split("\\|");
			for (int i = 0; i < idArray.length; i++) {
				String id = idArray[i];
				if(id!=null && !id.trim().equals(""))
					formFieldService.deleteColumn(tableId, id);
			}
			try{
				getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
				
			}
		}catch(Exception ex){
			try{
				getResponse().getWriter().println("{\"result\":\"false\"}");
			}catch(Exception e){
				
			}
		}
	}


	public String get() throws Exception {
		if(field!=null&&field.getId()!=null&&field.getId()!="-1"){
			field = formFieldService.get(field.getId());
		}
		else
		{
			field = new FormField();
		}
		getAllCodes();
		message="";
		return SUCCESS;
	}
	
	private void getAllCodes()
	{
		codes = codeService.getAllCode();
	}

	
	public void list() throws Exception {
		
		pagination = new Pagination(super.getRequest());
		pagination.setPageSize(1000);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		
		if (tableId!=null&&tableId.length()!=0) {
			paraMap.put("tableId", tableId);
			//String hql = "from FormField f where f.table="+tableId+" order by f.sortSQ";
			pagination = formFieldService.findFieldByPage(pagination, paraMap);
		}
		
		outputJsonList(pagination.getTotalCount(), "columnWidth|fieldDisplayName|fieldName|fieldDisplayType|fieldType|dispInGrid|isQueryCondition|isSortField|id|", pagination.getListOfObject());
		
		
	}

	/**
	 * 保存列表改动
	 * @return
	 * @throws Exception
	 */
	public void listUp() throws Exception{
		try{
			formFieldService.updateStatus(field);
			try{
				getResponse().getWriter().println("{\"result\":\"true\"}");
			}catch(Exception e){
				
			}
		}catch(Exception e){
			try{
				getResponse().getWriter().println("{\"result\":\"false\"}");
			}catch(Exception ex){
				
			}
		}
	}
	
	/**用于手机代码生成，根据tableId得出字段列表**/
	public void findFieldListByTableId() throws Exception{
		try{
			List<FormField> fieldList = null;
			String fieldNameList="";
			if (tableId!=null&&tableId.length()!=0) {
				fieldList = formFieldService.getAllField(tableId);
			}
			if(null != fieldList && fieldList.size()>0){
				for(int i=0;i<fieldList.size();i++){
					if(i==0)
						fieldNameList+=fieldList.get(i).getFieldName();
					else
						fieldNameList+="|"+fieldList.get(i).getFieldName();
				}
			}
			try{
				//getResponse().getWriter().println("{\"result\":\"true\"}");
				getResponse().getWriter().println("{\"result\":\""+fieldNameList+"\"}");
			}catch(Exception e){
				
			}
		}catch(Exception ex){
			try{
				getResponse().getWriter().println("{\"result\":\"false\"}");
			}catch(Exception e){
				
			}
		}
	}
		
	public String[] getDispInGrid() {
		return dispInGrid;
	}

	public void setDispInGrid(String[] dispInGrid) {
		this.dispInGrid = dispInGrid;
	}

	public String[] getIsQueryCondition() {
		return isQueryCondition;
	}

	public void setIsQueryCondition(String[] isQueryCondition) {
		this.isQueryCondition = isQueryCondition;
	}

	public String[] getIsSortField() {
		return isSortField;
	}

	public void setIsSortField(String[] isSortField) {
		this.isSortField = isSortField;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Code> getCodes() {
		return codes;
	}

	public void setCodes(List<Code> codes) {
		this.codes = codes;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

}
