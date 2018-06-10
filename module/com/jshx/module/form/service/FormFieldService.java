package com.jshx.module.form.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;


import com.jshx.core.base.vo.Pagination;
import com.jshx.module.form.dao.impl.FormFieldDao;
import com.jshx.module.form.dao.impl.FormTableDao;
import com.jshx.module.form.dbddl.IDateBaseDDL;
import com.jshx.module.form.entity.FormField;
import com.jshx.module.form.entity.FormTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Nils
 * 
 */ 
@Service("formFieldService")
public class FormFieldService  {

	
	String SQLKeyword = "数据库保留关键字";
	private IDateBaseDDL testDDL;
	
	@Autowired
    private FormFieldDao formFieldDao;
	
	@Autowired
	private FormTableDao formTableDao;

	
	public FormField get(String fieldId)
	{
		FormField ff = (FormField)formFieldDao.getObjectById(FormField.class, fieldId.toString());
		return ff;
	}
	
	@Transactional
	public void save(FormField formField) {
		
		formFieldDao.saveObject(formField);
	}
	
	@Transactional
	public void saveList(List<FormField> formFieldList) {
		
		formFieldDao.saveAllOrUpdateAll(formFieldList);
	}
	
	
	
	@Transactional
	public void update(FormField formField) {
		
		formFieldDao.merge(formField);
		formFieldDao.flush();
	}
	
	@Transactional
	public void delete(String fieldId) {
		formFieldDao.removeObjectById(FormField.class, fieldId.toString());
	}
	
	
	/**
	 * 判断该数据库用户下是否存在所提供的表名
	 * @param physicalName
	 * @return
	 */
	public boolean isExist(String tableId,String columName) throws Exception {
		FormTable formTable = (FormTable)formTableDao.getObjectById(FormTable.class, tableId);
		String tablephysicalName = formTable.getPhysicalName();
		return formFieldDao.isExist(tablephysicalName, columName);
		
	}
	
	
	public int getMaxSortSQ(String tableId){
		return formFieldDao.getMaxSortSQ(tableId);
	}
	
	
	/**
	 * 根据条件分页查找字段
	 * @param page
	 * @param paraMap
	 * @return
	 */
	public Pagination findFieldByPage(Pagination page, Map<String, Object> paraMap)
	{
		return formFieldDao.findFieldByPage(page, paraMap);
	}
	
	@Transactional
	public void updateStatus(FormField field){
		FormField field1 = this.get(field.getId());
		field1.setIsQueryCondition(field.getIsQueryCondition());
		field1.setIsSortField(field.getIsSortField());
		field1.setDispInGrid(field.getDispInGrid());
		if(field.getDispInGrid()==1)
			field1.setColumnWidth(field.getColumnWidth());
		this.save(field1);
	}
	
	/**
	 * 页面更新字段的一些值
	 * 
	 * @param fieldId
	 * @param fieldDisplayName
	 * @param gridMultiRows     是否折行
	 * @param controlWidth      列宽度
	 * @param dispInGrid        是否显示
	 * @param isQueryCondition  是否查询
	 * @param isSortField       是否排序
	 */
	@Transactional
	public void updateStatusInfo(String[] fieldId,String[] dispInGrid,String[] isQueryCondition,String[] isSortField){
		Map<String,String> m_dispInGrid = new HashMap<String, String>();
		if(dispInGrid!=null){
			for (int i = 0; i < dispInGrid.length; i++) {
				m_dispInGrid.put(dispInGrid[i],null);
			}
		}
		Map<String, String> m_isQueryCondition = new HashMap<String, String>();
		if(isQueryCondition!=null){
			for (int i = 0; i < isQueryCondition.length; i++) {
				m_isQueryCondition.put(isQueryCondition[i],null);
			}
		}
		Map<String,String> m_isSortField = new HashMap<String,String>();
		if(isSortField!=null){
			for (int i = 0; i < isSortField.length; i++) {
				m_isSortField.put(isSortField[i],null);
			}
		}
		
		if (fieldId==null) {
			return;
			
		}
		for (int i = 0; i < fieldId.length; i++) { 
			if(fieldId[i]==null || fieldId[i].trim().equals(""))
				continue;
			int v_dispInGrid;
			if(m_dispInGrid.containsKey(fieldId[i])){
				v_dispInGrid = 1;
			}else{
				v_dispInGrid = 0;
			}
			int v_isQueryCondition;
			if(m_isQueryCondition.containsKey(fieldId[i])){
				v_isQueryCondition = 1;
			}else{
				v_isQueryCondition = 0;
			}
			int v_isSortField;
			if(m_isSortField.containsKey(fieldId[i])){
				v_isSortField = 1;
			}else{
				v_isSortField = 0;
			}
			FormField field = (FormField)formFieldDao.getObjectById(FormField.class, fieldId[i].toString());
			if (field.getFieldName().equals("ROW_ID")) {
				continue;
				
			}
			//field.setFieldDisplayName(fieldDisplayName[i]);
			//field.setGridMultiRows(gridMultiRows[i]);
			//field.setControlWidth(controlWidth[i]);
			field.setDispInGrid(v_dispInGrid);
			field.setIsQueryCondition(v_isQueryCondition);
			field.setIsSortField(v_isSortField);
			
			formFieldDao.saveObject(field);
		}		
	}
	
	/**
	 * 新增字段
	 * 
	 * @param tableId
	 * @param field
	 */
	@Transactional
    public void addColumn(String tableId, FormField field) throws Exception
    {   
		FormTable formTable = (FormTable)formTableDao.getObjectById(FormTable.class, tableId.toString());
		String tablephysicalName = formTable.getPhysicalName();
		formFieldDao.addColumn(tablephysicalName, field);
    }
    
    /**
     * 删除字段
     * 
     * @param tableId
     * @param fieldId
     */
	@Transactional
    public void deleteColumn(String tableId, String fieldId)
    {
		FormTable formTable = (FormTable)formTableDao.getObjectById(FormTable.class, tableId);
		String tablephysicalName = formTable.getPhysicalName();
		formFieldDao.deleteColumn(tablephysicalName, fieldId);
       
    }

    
    
    /**
     * 修改字段信息
     * 屏蔽修改字段名称，修改字段长度的功能！
     * @param field
     */
    @Transactional
    public void modifyField(FormField newField){
    	if (newField!=null) {
 //   		FormTable table = (FormTable)formTableDao.getObjectById(FormTable.class, tableId.toString());
        	FormField field =(FormField) formFieldDao.getObjectById(FormField.class, newField.getId());
//        	if(!field.getFieldName().equals(newField.getFieldName())){
//        		testDDL.renameColumn(table.getPhysicalName(),field.getFieldName(),newField.getFieldName(),field.getFieldType());
//        	}
//        	if(field.getFieldLength()!=newField.getFieldLength()||field.getDecimalLength()!=newField.getDecimalLength()){
//        		testDDL.resetLength(table.getPhysicalName(),newField.getFieldName(),field.getFieldType(),newField.getFieldLength(),newField.getDecimalLength());
//        	}
        	
        	newField.setFieldType(field.getFieldType());
        	newField.setFieldName(field.getFieldName());
        	newField.setFieldLength(field.getFieldLength());
        	//newField.setFieldDisplayType(field.getFieldDisplayType());
        	newField.setDecimalLength(field.getDecimalLength());
    		update(newField);
			
		}
    	
    }
   
    
    public LinkedHashMap<String, String> getColumnType(String columnType,ServletContext cx){
	
		LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
		try{

		
			
			if (columnType.equals("varchar")) {
				result.put("TextBox","文本框");
				result.put("DropdownList","下拉菜单");
				result.put("Radio","单项选择");
				result.put("CheckBox","多项选择");
				result.put("TextArea","多行文本");
				result.put("MultiFiles","多文件上传");
			}
			else if (columnType.equals("number")) {
				result.put("TextBox","文本框");
			}
			else if (columnType.equals("int")) {
				result.put("TextBox","文本框");
			}
			else if (columnType.equals("date")) {
				result.put("DatePick","日期选择");
			}
			else if (columnType.equals("clob")) {
				result.put("TextBox","文本框");
				result.put("TextArea","多行文本");
				result.put("Html","HTML文本");
			}
			else if (columnType.equals("blob")) {
				result.put("ImageUpload","文件上传");
			}
			
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
    
    public List<FormField> getFieldByPhyName(String PhyName)
    {
    	
		return formFieldDao.getFieldByPhyName(PhyName);
    	
    }
	
    /**
     * 获得该表添加字段
     * @param tableId
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<FormField> getAllField(String tableId){
    	String hql = "from FormField t where t.table='"+tableId+"' order by t.sortSQ";
    	List<FormField> list = formFieldDao.findListByHql(hql);
    	return list;
    }
    
    /**
     * 获得该表配置了可以“详细显示”的字段
     * @param tableId
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<FormField> getDisplayDetailField(String tableId)
    {
    	String hql="from FormField t where t.table='"+tableId+"' and t.dispInDetail=1 order by t.sortSQ";// 暂时查询所有信息出来
    	return  formFieldDao.findListByHql(hql);
    }
    
    /**
     * 获得列表显示字段
     * @param tableId
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<FormField> getDisplayField(String tableId){
    	String hql = "from FormField t where t.table='"+tableId+"' and t.dispInGrid=1 order by t.sortSQ";
    	return  formFieldDao.findListByHql(hql);
    }
    
    /**
     * 获得查询显示字段
     * @param tableId
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<FormField> getQueryField(String tableId){
    	String hql = "from FormField t where t.table='"+tableId+"' and t.isQueryCondition=1 order by t.sortSQ";
    	return  formFieldDao.findListByHql(hql);
    }
    
    /**
     * 获得排序显示字段
     * @param tableId
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<FormField> getOrderField(String tableId){
    	String hql = "from FormField t where t.table='"+tableId+"' and t.isSortField=1 order by t.sortSQ";
    	return  formFieldDao.findListByHql(hql);
    }
    
    /**
     * 获得排序显示字段
     * @param tableId
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<FormField> getUniqueField(String tableId){
    	String hql = "from FormField t where t.table='"+tableId+"' and t.uniqueField=1 order by t.sortSQ";
    	return  formFieldDao.findListByHql(hql);
    }
    
    
    
    /**
     * 根据表名和字段名查找字段
     * 
     * @Title: getDisplayFieldByTableAndColumn 
     * @Description: 
     * @param tableId
     * @param columnName
     * @return FormField  
     */
    @SuppressWarnings("unchecked")
	public FormField getDisplayFieldByTableAndColumn(String tableId, String columnName){
    	Map<String, Object> paraMap = new HashMap<String, Object>();
    	paraMap.put("fieldDisplayName", columnName);
    	FormTable table = (FormTable)formTableDao.getObjectById(FormTable.class, tableId);
    	paraMap.put("table", table);
    	String hql = "from FormField t where t.table='"+table+"' and t.dispInGrid=1 and t.fieldDisplayName="+columnName;
    	List<FormField> fieldList = formFieldDao.findListByHql(hql);
    	if(fieldList==null || fieldList.size()==0)
    		return null;
    	else
    		return fieldList.get(0);
    }


}
