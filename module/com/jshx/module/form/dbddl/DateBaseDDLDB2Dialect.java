/**
 * 
 */
package com.jshx.module.form.dbddl;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.jshx.core.base.vo.Pagination;
import com.jshx.module.form.entity.FormField;

/**
 * @author f_cheng
 *
 */
public class DateBaseDDLDB2Dialect implements IDateBaseDDL {

	/* (non-Javadoc)
	 * @see com.jshx.module.form.dbddl.IDateBaseDDL#AddColumn(java.lang.String, com.jshx.module.form.entity.FormField)
	 */
	@Override
	public boolean AddColumn(String tableName, FormField field)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.form.dbddl.IDateBaseDDL#CreatTB(java.lang.String)
	 */
	@Override
	public boolean CreatTB(String TableName) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.form.dbddl.IDateBaseDDL#DelColumn(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean DelColumn(String tableName, String fieldId, String fieldType)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.form.dbddl.IDateBaseDDL#InsertBlob(java.util.Map, int, java.lang.String)
	 */
	@Override
	public boolean InsertBlob(Map<String, File> blobMap, int rowId,
			String tableName) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.form.dbddl.IDateBaseDDL#copyTable(java.lang.String, java.lang.String)
	 */
	@Override
	public String copyTable(String copyTableName, String tableName)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.form.dbddl.IDateBaseDDL#getBlankBlob()
	 */
	@Override
	public String getBlankBlob() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.form.dbddl.IDateBaseDDL#getFieldByPhyName(java.lang.String)
	 */
	@Override
	public List<FormField> getFieldByPhyName(String PhyName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.form.dbddl.IDateBaseDDL#getImportForm(com.jshx.core.base.vo.Pagination, java.lang.String)
	 */
	@Override
	public Pagination getImportForm(Pagination tablePage,
			String modelphysicalName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.form.dbddl.IDateBaseDDL#isColExist(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isColExist(String tableName, String fieldName)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.form.dbddl.IDateBaseDDL#isTableExist(java.lang.String)
	 */
	@Override
	public boolean isTableExist(String tableName) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.form.dbddl.IDateBaseDDL#renameColumn(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean renameColumn(String tableName, String oldFieldName,
			String fieldName, String fieldType) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.form.dbddl.IDateBaseDDL#renameTable(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean renameTable(String OldTableName, String NewTableName)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
