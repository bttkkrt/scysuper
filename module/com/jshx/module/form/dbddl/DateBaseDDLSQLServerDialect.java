package com.jshx.module.form.dbddl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;


import com.jshx.core.base.dao.JdbcUtil;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.Constants;
import com.jshx.module.form.entity.FormField;

public class DateBaseDDLSQLServerDialect  implements IDateBaseDDL {

	@Autowired
	private JdbcUtil jdbcUtil;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	
	 public Pagination getImportForm(Pagination tablePage,String modelphysicalName)
	    {
		 return null;
	    }
	/**
	 * 获得一个数据库链接
	 * 
	 * @return
	 * @throws Exception
	 */
	private Connection getConnection() throws Exception{
		return DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
	}
	
	/**
	 * 释放数据库链接
	 * 
	 * @param conn
	 */
	private void releaseConnection(Connection conn){
		DataSourceUtils.releaseConnection(conn, jdbcTemplate.getDataSource());
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.form.dbddl.IDateBaseDDL#CreatTB(java.lang.String)
	 * 功能：创建新表
	 * 参数1：表名
	 */
	public boolean CreatTB(String TableName) {

		StringBuilder sb = new StringBuilder();

		sb.append(" CREATE TABLE [dbo].[" + TableName + "] ( ");

		//加入通用字段
		//单位编码（Session[]）、操作人（Session[]）、操作日期（Session[]）、自增长ID、GUID、所属台帐年度（Session[]）、
		sb.append(" [Row_ID] [varchar](40) NOT NULL , ");
		
		sb.append(" [CREATETIME] [Datetime],");
		sb.append(" [UPDATETIME] [Datetime],");
		sb.append(" [CREATEUSERID] [varchar] (32),");
		sb.append(" [UPDATEUSERID] [varchar] (32),");
		sb.append(" [DEPTID] [varchar] (50),");
		sb.append(" [DelFlag] [int] DEFAULT((0)),");

		sb.append("CONSTRAINT [PK_" + TableName + "] PRIMARY KEY NONCLUSTERED");
		sb.append("(");
		sb.append("    [Row_ID] ASC");
		sb.append(")");

		sb.append(") ON [PRIMARY] ");
		try {
			jdbcUtil.executeSql(sb.toString().toUpperCase(),null);
		} catch (RuntimeException e) {
			BasalException ex = new BasalException(BasalException.ERROR, Constants.CREATING_TABLE_ERROR, e);
			throw ex;
		}
		return true;

	}
	
	private String transFieldType(String nameString) {
		
		if (nameString.equals("varchar")||nameString.equals("nvarchar")) {
			nameString="nvarchar";			
		}
		else if (nameString.equals("number")||nameString.equals("decimal")) {
			nameString="decimal";			
		}
		else if (nameString.equals("date")||nameString.equals("datetime")) {
			nameString="datetime";			
		}
		else if (nameString.equals("clob")||nameString.equals("ntext")) {
			nameString="ntext";			
		}
		else if (nameString.equals("blob")||nameString.equals("image")) {
			nameString="image";			
		}
		
		
		return nameString;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.form.dbddl.IDateBaseDDL#CreatTB(java.lang.String)
	 * 功能：创建新表
	 * 参数1：表ID，参数2：表的属性集合
	 */
	public boolean AddColumn(String tableName, FormField field) {
		String fieldType = transFieldType(field.getFieldType());
		String fieldName = field.getFieldName();
		Integer fieldLength = field.getFieldLength();
		Integer fieldDecimalLength = field.getDecimalLength();
		//FormTable table = (FormTable)formTableDao.getObjectById(FormTable.class, tableId.toString());//this.get(tableId);
		//String tableName = table.getPhysicalName();

		boolean bResult = false;
		String SQL = "Alter Table " + tableName;
		SQL += " ADD [" + fieldName + "]";
		SQL += "[" + fieldType + "]";

		if (fieldType.toLowerCase().trim().equals("nvarchar")
				|| fieldType.toLowerCase().trim().equals("varchar")) {
			SQL += " (" + fieldLength + ") ";
		} else if (fieldType.toLowerCase().trim().equals("decimal")||fieldType.toLowerCase().trim().equals("number")) {

			SQL += " (" + 38// 直接限制成38，有问题再改！！！:((fieldLength.intValue() > 38) ? 38 : fieldLength)
					+ "," + fieldDecimalLength + ") ";
		}
		if (fieldType.toLowerCase().trim().equals("image")) {
			SQL += "," + fieldName + "_ContentType nvarchar(50) NULL";
			SQL += "," + fieldName + "_ContentName nvarchar(100) NULL";
		}

		try {
			jdbcUtil.executeSql(SQL,null);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
		bResult = true;

		return bResult;
	}

	public boolean renameTable(String OldTableName, String NewTableName) {
		String SQL = "sp_rename '" + OldTableName + "','" + NewTableName + "'";
		try {
			jdbcUtil.executeSql(SQL,null);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean DelColumn(String tableName, String fieldName, String fieldType) {

		//FormTable table = (FormTable)formTableDao.getObjectById(FormTable.class, tableId.toString());//this.get(tableId);
		
		//String tableName = table.getPhysicalName();

		String SQL = "ALTER TABLE  " + tableName + " DROP COLUMN [" + fieldName
				+ "] ";
		if (fieldType.toLowerCase().equals("image")) {
			SQL += " ALTER TABLE " + tableName + "  DROP COLUMN [" + fieldName
					+ "_ContentType ]";
			SQL += " ALTER TABLE " + tableName + "  DROP COLUMN [" + fieldName
			+ "_ContentName ]";
		}
		try {
			jdbcUtil.executeSql(SQL,null);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean resetLength(String tableName, String fieldName,
			String fieldType, int fieldLength, int fieldDecimalLength) {
		return true;
	}

	public boolean renameColumn(String tableName, String oldFieldName,
			String fieldName,String fieldType) {
		/*
         String SQL = "EXEC sp_rename '" + tableName + "." + oldFieldName + "', '" + fieldName + "', 'COLUMN' ";//
         if (fieldType.toLowerCase().equals("image"))
         {
             SQL += "EXEC sp_rename '" + tableName + "." + oldFieldName + "_ContentType', '" + fieldName + "_ContentType', 'COLUMN' ";//
             SQL += "EXEC sp_rename '" + tableName + "." + oldFieldName + "_ContentName', '" + fieldName + "_ContentName', 'COLUMN' ";//
             
         }
         try {
 			jdbcUtil.executeSql(SQL,null);
 		} catch (RuntimeException e) {
 			e.printStackTrace();
 			return false;
 		}
 		*/
		return true;
	}
	
	public boolean isColExist(String tableName, String fieldName)
	{
		//oracle
		//String sql = "select count(*) from all_tab_columns where table_name='"+table.getPhysicalName().toUpperCase()+"' and column_name='"+columName.toUpperCase()+"' and owner='"+curuser+"'";
		//sqlserver select syscolumns.* from syscolumns, sysobjects where sysobjects.name = 'ZHAOJ_TEST2' and sysobjects.id = syscolumns.id
		String sql = "select count(*) from syscolumns, sysobjects where sysobjects.name='"+tableName.toUpperCase()+"' and sysobjects.id = syscolumns.id  and syscolumns.name = '" + fieldName.toUpperCase() + "'";
		Map<String, Object> num  = jdbcUtil.findBySql(sql);
		//if(num!=null&&(num.get(0)).intValue()>0){
		//	return true;
		//}
		return false;
	}
	
	
	public boolean InsertBlob(Map<String, File>blobMap, int rowId,String tableName)
	{
		Connection conn = null;
		PreparedStatement stmt =null;
		try {
		//InputStream input; // Person应该插入的Photo，通过网页上传获得，将插入image类型字段
		conn = getConnection(); //实际的Connection对象
		
		ResultSet rs = null;
		conn.setAutoCommit(false); // 设置 auto commit = false;
		//sql = "update person set photo = null where id = ?"; // 初始化image数据类型的指针
		StringBuffer sql = new StringBuffer("update ");
		sql.append(tableName).append(" set ");
		Iterator<String> keyIt = blobMap.keySet().iterator();
		int i = 0;
		int size = blobMap.keySet().size();
		
		while(keyIt.hasNext()){
			String key = keyIt.next()+"=? ";
			sql.append(key);
			
			
			if(i<size-1)
				sql.append(", ");
			i++;
			
		}
		sql.append(" where row_id=?");
		
		System.out.println(sql.toString());
		stmt = conn.prepareStatement(sql.toString());
		i=0;
		keyIt = blobMap.keySet().iterator();
		while(keyIt.hasNext()){
			String key = keyIt.next();
			File file = blobMap.get(key);
		stmt.setBinaryStream(i+1,new java.io.FileInputStream(file),(int)file.length());
		i++;
		}
		// 即使在insert时插入image类型的值为null，数据库依然不会创建指针。可以使用update创建指针。
	
		stmt.setInt(i+1, rowId);
		stmt.executeUpdate();
		conn.commit();
		}catch (Exception e) {
			e.printStackTrace();
			
			return false;
		} finally {
			if (conn != null) {
				try {
					releaseConnection(conn);
					conn = null;
				} catch (Exception e) {
					
					return false;
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
					
					
				}
			}
		}
		
		
		
		return true;
	}
	
	public String getBlankBlob()	{
		return "'wait'";
	}
	
	/*
    public boolean reCreateTable(Integer tableId)
    {
        FormTable table = this.get(tableId);
        //临时表名，原表名_精确到毫秒的时间戳_3随机数
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String tempTableName = table.getPhysicalName() + "_" + s.format(d) + "_" + gen3Random();
        //将表结构复制到临时表
        String sql = "SELECT * INTO " + tempTableName + " FROM " + table.getPhysicalName() + " WHERE 1=2";
        jdbcUtil.executeSql(SQL,null);
        //删除原表
        sql = "DROP TABLE " + table.getPhysicalName();
        jdbcUtil.executeSql(SQL,null);
        //修改临时表名为原表名
        sql = "sp_rename '" + tempTableName + "','" + table.getPhysicalName() + "'";
        jdbcUtil.executeSql(SQL,null);
        //增加主键
        sql = "alter table " + table.getPhysicalName() + " add PRIMARY KEY (\"ROW_ID\")";
        jdbcUtil.executeSql(SQL,null);
        return true;
    }
*/
    /**
     * 产生3位的随机数
     * @return
     
    private String gen3Random()
    {
        Random random = new Random();
        String temp = String.valueOf(random.nextInt(1000));
        if (3 > temp.length())
        {
            temp = "000".substring(0, 3 - temp.length()) + temp;
        }
        return temp;
    }
    */
	
	public String copyTable(String copyTableName,String tableName)
    {
    	return "select * into " + copyTableName + "  from "+ tableName + " where 1<>1 ";
    }
	
	
	public boolean isTableExist(String tableName)
	{
		try {
			Connection con = getConnection();
			ResultSet rs;
			rs = con.getMetaData().getTables(null, null,
					tableName.toUpperCase(), null);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	   public List<FormField> getFieldByPhyName(String PhyName)
	    {
	    	return null;
	    }
}
