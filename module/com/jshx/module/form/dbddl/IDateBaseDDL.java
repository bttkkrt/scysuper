package com.jshx.module.form.dbddl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.jshx.core.base.vo.Pagination;
import com.jshx.module.form.entity.FormField;

public interface IDateBaseDDL {
	
	
	public boolean CreatTB(String TableName) throws Exception;
	
	public boolean AddColumn(String tableName, FormField field) throws Exception;
	
	public boolean DelColumn(String tableName, String fieldId,String fieldType ) throws Exception;
	
	public boolean renameTable(String OldTableName, String NewTableName) throws Exception;
	
	 
	public boolean isColExist(String tableName, String fieldName) throws Exception;
	
	public boolean isTableExist(String tableName) throws Exception;
 
	
	public boolean renameColumn(String tableName, String oldFieldName,String fieldName,String fieldType) throws Exception;

	public boolean InsertBlob(Map<String, File>blobMap,int rowId, String tableName) throws Exception;
	
	
	public String getBlankBlob() throws Exception;
	
	public String copyTable(String copyTableName,String tableName) throws Exception;
	
	/**
	 * 得到所有可以导入的表单
	 */
	public Pagination getImportForm(Pagination tablePage,String modelphysicalName);
	
	
	/**
	 * 将表名为PhyName的表的所有字段纳入管理表中，同时加入必须的管理字段
	 */
	 public List<FormField> getFieldByPhyName(String PhyName);
	//public boolean reCreateTable(Integer tableId);
}
