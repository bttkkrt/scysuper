package com.jshx.module.form.dbddl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oracle.sql.BLOB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.jshx.core.base.dao.JdbcUtil;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.Constants;
import com.jshx.module.form.entity.FormField;

public class DateBaseDDLOracleDialect implements IDateBaseDDL {

	@Autowired
	private JdbcUtil jdbcUtil;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 默认字段不需要放入FormField记录中.
	String[] defaultString = { "ROW_ID", "CREATETIME", "UPDATETIME",
			"CREATEUSERID", "UPDATEUSERID", "DEPTID", "DELFLAG", "VERSION" };


	

	public boolean CreatTB(String TableName) {
		
		StringBuffer sb = new StringBuffer();

		sb.append("begin Execute immediate'CREATE TABLE " + TableName + "( ");

		sb.append(" ROW_ID varchar (40)  NOT NULL , ");
		sb.append(" CREATETIME Date,");
		sb.append(" UPDATETIME Date,");
		sb.append(" CREATEUSERID varchar (32),");
		sb.append(" UPDATEUSERID varchar (32),");
		sb.append(" DEPTID varchar (50),");
		sb.append(" DELFLAG NUMBER(10,0) DEFAULT(0),");

		sb.append("CONSTRAINT PK_" + TableName + " PRIMARY KEY (Row_ID) ");

		sb.append(")' ;end;");
		try {
			jdbcUtil.executeSql(sb.toString().toUpperCase(), null);
		} catch (RuntimeException e) {
			BasalException ex = new BasalException(BasalException.ERROR, Constants.CREATING_TABLE_ERROR, e);
			throw ex;
		}

		// 创建自增的触发器
		/*
		 * sb = new StringBuffer(); sb.append("CREATE TRIGGER TRG_" + TableName
		 * + " BEFORE"); sb.append(" INSERT  ON " + TableName);
		 * sb.append(" FOR EACH ROW WHEN (NEW.Row_ID is null)  ");
		 * sb.append("begin SELECT hibernate_sequence.nextval ");
		 * sb.append("INTO :NEW.Row_ID "); sb.append("FROM DUAL; ");
		 * sb.append("End TRG_" + TableName + "; ");
		 * 
		 * try { jdbcTemplate.execute(sb.toString()); } catch (RuntimeException
		 * e) {  e.printStackTrace(); return
		 * false; }
		 */
		return true;

	}

	private String transFieldType(String nameString) {
		if (nameString.equals("varchar") || nameString.equals("nvarchar")) {
			nameString = "varchar";
		} else if (nameString.equals("number") || nameString.equals("decimal")) {
			nameString = "number";
		} else if (nameString.equals("date") || nameString.equals("datetime")) {
			nameString = "date";
		} else if (nameString.equals("clob") || nameString.equals("ntext")) {
			nameString = "clob";
		} else if (nameString.equals("blob") || nameString.equals("image")) {
			nameString = "blob";
		}

		return nameString;
	}

	public boolean AddColumn(String tableName, FormField field) {
		// 在表中添加字段
		String fieldType = transFieldType(field.getFieldType());
		String fieldName = field.getFieldName();
		Integer fieldLength = field.getFieldLength();
		Integer fieldDecimalLength = field.getDecimalLength();

		StringBuffer sql = new StringBuffer("alter table ");
		sql.append(tableName).append(" add ").append(fieldName).append(" ");
		if (fieldType.toLowerCase().trim().equals("varchar")) {
			sql.append("varchar2").append("(" + fieldLength + ")");
		} else if (fieldType.toLowerCase().trim().equals("int")) {
			sql.append("NUMERIC(10)");
		} else if (fieldType.toLowerCase().trim().equals("number")) {
			sql.append("NUMERIC(" + fieldLength + "," + (fieldDecimalLength==null?0:fieldDecimalLength)
					+ ")");
		}

		else {
			sql.append(fieldType);
		}
		/*
		 * if(field.getDefaultValue()!=null){ sql.append(" default '");
		 * sql.append(field.getDefaultValue()); sql.append("'"); }
		 */
		try {
			jdbcUtil.executeSql(sql.toString(), null);
			if (fieldType.toLowerCase().trim().equals("blob")) {
				sql = new StringBuffer("alter table ");
				sql.append(tableName).append(" add ");
				sql.append(fieldName).append("_contentName nvarchar2(200)");
				jdbcUtil.executeSql(sql.toString(), null);

				sql = new StringBuffer("alter table ");
				sql.append(tableName).append(" add ");
				sql.append(fieldName).append("_contentType nvarchar2(200)");
				jdbcUtil.executeSql(sql.toString(), null);
			}

		} catch (RuntimeException e) {
			
			// e.printStackTrace();
			throw e;
		}

		return true;

	}

	public boolean renameTable(String OldTableName, String NewTableName) {
		System.out.println("Oracle renameTable Creat Success!");
		return true;
	}

	public boolean DelColumn(String tableName, String fieldName,
			String fieldType) {
		// FormTable table =
		// (FormTable)formTableDao.getObjectById(FormTable.class,
		// tableId.toString());//this.get(tableId);
		// String tableName = table.getPhysicalName();

		String SQL = "alter table " + tableName + " DROP COLUMN " + fieldName
				+ "";
		try {
			jdbcUtil.executeSql(SQL, null);
		} catch (RuntimeException e) {
			
			e.printStackTrace();
			return false;
		}
		if (fieldType.toLowerCase().equals("blob")) {
			SQL = " alter table " + tableName + "  DROP COLUMN " + fieldName
					+ "_contentType";
			try {
				jdbcUtil.executeSql(SQL, null);
			} catch (RuntimeException e) {
				
				e.printStackTrace();
				return false;
			}
			SQL = " ALTER TABLE " + tableName + "  DROP COLUMN " + fieldName
					+ "_contentName ";
			try {
				jdbcUtil.executeSql(SQL, null);
			} catch (RuntimeException e) {
				
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}

	public boolean resetLength(String tableName, String fieldName,
			String fieldType, int fieldLength, int fieldDecimalLength) {
		return true;
	}

	public boolean renameColumn(String tableName, String oldFieldName,
			String fieldName, String fieldType) {
		String sql = "alter table " + tableName + "   rename column "
				+ oldFieldName + " to " + fieldName + "";
		try {
			jdbcUtil.executeSql(sql, null);
		} catch (RuntimeException e) {

			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean isColExist(String tableName, String fieldName) {

		LinkedHashMap<String, Object> paraMap = new LinkedHashMap<String, Object>();
		paraMap.put("tableName", tableName.toUpperCase());
		paraMap.put("fieldName", fieldName.toUpperCase());
		List<Map<String, Object>> num = jdbcUtil.findList("oracle.isColExist",paraMap);

		if (num == null || num.size() == 0) {
			return false;
		}

		return true;
	}

	public boolean InsertBlob(Map<String, File> blobMap, int rowId,
			String tableName) {

		StringBuffer sql = new StringBuffer("select ");
		Iterator<String> keyIt = blobMap.keySet().iterator();
		int i = 0;
		int size = blobMap.keySet().size();
		while (keyIt.hasNext()) {
			String key = keyIt.next();
			sql.append(key);
			if (i < size - 1)
				sql.append(", ");
			i++;
		}
		sql.append(" from ").append(tableName).append(" for update");
		System.out.println(sql);
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			conn.setAutoCommit(false);
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				keyIt = blobMap.keySet().iterator();

				while (keyIt.hasNext()) {
					String columnName = keyIt.next();
					File file = blobMap.get(columnName);

					BLOB blob = (BLOB) rs.getBlob(columnName.toUpperCase());
					System.out.println(blob);
					OutputStream os = blob.getBinaryOutputStream();
					BufferedOutputStream output = new BufferedOutputStream(os);
					System.out.println(columnName + "   " + file);
					if (file != null) {
						FileInputStream input = new FileInputStream(file);
						try {
							byte[] buff = new byte[2048];
							int bytesRead;
							while (-1 != (bytesRead = input.read(buff, 0,
									buff.length))) {
								output.write(buff, 0, bytesRead);
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							output.close();
							input.close();
						}
					}

				}
			}
			conn.commit();
		} catch (Exception e) {
			// e.printStackTrace();

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

	public boolean UpdateBlob() {
		return true;
	}

	public boolean ReadBlob() {
		return true;
	}

	public String getBlankBlob() {
		return "empty_blob()";
	}



	public String copyTable(String copyTableName, String tableName) {
		return "create table " + copyTableName + " as ( 	select * from "
				+ tableName + "   where 1<>1)";

	}

	public boolean isTableExist(String tableName) throws Exception {
		try {


			LinkedHashMap<String, Object> paraMap = new LinkedHashMap<String, Object>();
			paraMap.put("tableName", tableName.toUpperCase());
			
			List<Map<String, Object>> num = jdbcUtil.findList("oracle.isTabExist",paraMap);
			
			
		
			if (num == null || num.size() == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw e;
		}

	}

	public Pagination getImportForm(Pagination tablePage,
			String modelphysicalName) {
		
		LinkedHashMap<String, Object> paraMap = new LinkedHashMap<String, Object>();
		if (modelphysicalName != null && !"".equals(modelphysicalName)) {
			paraMap.put("modelphysicalName","%"+ modelphysicalName.toUpperCase()+"%");
		}
		// System.out.print(querySql);
		return jdbcUtil.findPage("oracle.getImportForm",tablePage,paraMap);
	}

	
	public List<FormField> getFieldByPhyName(String PhyName) {
		Map<String, Object> containMap = new HashMap<String, Object>();	//哈希表
		String querySql = "";	//查询语句
		int sortsqint = 1;	//排序
		StringBuffer sql;	//用来执行的SQL语句
		
		
		if (PhyName != null && !"".equals(PhyName)) {
			List<FormField> ffList = new ArrayList<FormField>();
			// 处理主键为ROW_ID
			LinkedHashMap<String, Object> paraMap = new LinkedHashMap<String, Object>();
			paraMap.put("PhyName", PhyName.toUpperCase());
			
			List<Map<String, Object>> keyList = jdbcUtil
					.findList("oracle.getPriKey",paraMap);

			if (keyList.size() > 1) {
				// 主键数大于1暂时不处理
				return ffList;
			} else if (keyList.size() == 0) {
				// 没有主键则创建一个名为ROW_ID的主键

			} else if (keyList.size() == 1) {
				// 主键数为1且主键名不为ROW_ID则将主键改名为ROW_ID，若为ROW_ID则不作处理
				String keyName = keyList.get(0)
						.get("COLUMN_NAME".toUpperCase()).toString();
				if (!"ROW_ID".equals(keyName)) {
					// 重命名主键为ROW_ID
					renameColumn(PhyName.toUpperCase(), keyName, "ROW_ID", null);
				}

			}

			// 查出该表所有字段
			
			List<Map<String, Object>> MapList = jdbcUtil
					.findList("oracle.getUserCols",paraMap);

			for (Map<String, Object> map : MapList) {

				// 是否默认字段
				boolean isDefault = false;

				String columnName = map.get("column_name".toUpperCase())
						.toString();
				// 在默认字段列表中的不需要保存
				for (String ss : defaultString) {
					if (ss.equalsIgnoreCase(columnName)) {
						isDefault = true;
						break;
					}
				}
				// 如果是我们自己创建的blob字段的辅助字段，则跳过

				if (columnName.contains("_contentName".toUpperCase())
						|| columnName.contains("_contentType".toUpperCase())) {
					continue;
				}

				// 是默认字段不存入管理表，且不再创建
				if (isDefault) {
					containMap.put(map.get("column_name".toUpperCase())
							.toString(), "1");
					continue;
				}

				// 将该字段纳入管理表
				FormField ff = new FormField();

				// 属性的设置
				ff.setDataSource("Blank");
				ff.setDataSourceType("-1");

				ff.setDispInGrid(1);
				ff.setFieldDefaultValue("-1");

				ff.setMustFill(0);
				ff.setGridMultiRows(1);
				ff.setValueUrl("无");
				ff.setDispInDetail(1);
				ff.setSortSQ(sortsqint++);

				ff.setFieldDisplayName(map.get("column_name".toUpperCase())
						.toString());
				ff
						.setFieldName(map.get("column_name".toUpperCase())
								.toString());

				Object lenth = map.get("data_length".toUpperCase());
				if (lenth == null)
					ff.setFieldLength(0);
				else {
					ff.setFieldLength(Integer.parseInt(lenth.toString()));
				}
				ff.setSortSQ(Integer.parseInt(map
						.get("column_id".toUpperCase()).toString()));
				String datetype = map.get("data_type".toUpperCase()).toString();

				String nullable = map.get("nullable".toUpperCase()).toString();
				if ("N".equals(nullable)) {
					ff.setMustFill(1);
				}

				if ("NUMBER".equals(datetype)) {

					Object nlenth = map.get("DATA_PRECISION".toUpperCase());
					if (nlenth != null) {
						ff.setFieldLength(Integer.parseInt(nlenth.toString()));
					}
					Object slenth = map.get("DATA_SCALE".toUpperCase());
					if (slenth != null) {
						ff.setDecimalLength(Integer.parseInt(slenth
										.toString()));
					}
					else
					{
						ff.setDecimalLength(0);
					}
		

					ff.setFieldType("number");
					ff.setFieldDisplayType("TextBox");
				} else {
					ff.setDecimalLength(0);

					if (datetype.equals("DATE")) {
						ff.setFieldType("date");
						ff.setFieldDisplayType("DatePick");
					} else if (datetype.equals("CLOB")) {
						ff.setFieldType("clob");
						ff.setFieldDisplayType("Html");
					} else if (datetype.equals("BLOB")) {
						// 此时还需要同时创建2个辅助字段

						try {
							sql = new StringBuffer("alter table ");
							sql.append(PhyName).append(" add ");
							sql.append(
									map.get("column_name".toUpperCase())
											.toString()).append(
									"_contentName nvarchar2(200)");
							jdbcUtil.executeSql(sql.toString(), null);

							sql = new StringBuffer("alter table ");
							sql.append(PhyName).append(" add ");
							sql.append(
									map.get("column_name".toUpperCase())
											.toString()).append(
									"_contentType nvarchar2(200)");
							jdbcUtil.executeSql(sql.toString(), null);
						} catch (Exception e) {
							// 如果创建报错，证明其中已有这两个字段，跳过即可
						}

						ff.setFieldType("blob");
						ff.setFieldDisplayType("ImageUpload");
					} else if (datetype.contains("VARCHAR")) {
						ff.setFieldType("varchar");
						ff.setFieldDisplayType("TextBox");
					} else {
						ff.setFieldType("varchar");
						ff.setFieldDisplayType("TextBox");
					}

				}

				ffList.add(ff);

			}
			// 3.在该表内加入默认字段（要通过判断是否那些字段是否已存在）

			/*
			 * if(!containMap.containsKey("ROW_ID")) { sql = new
			 * StringBuffer("alter table ");
			 * sql.append(PhyName).append(" add ROW_ID nvarchar2 (40)  NOT NULL "
			 * );  jdbcUtil.executeSql(sql.toString(),null); }
			 */

			if (!containMap.containsKey("CREATETIME")) {
				sql = new StringBuffer("alter table ");
				sql.append(PhyName).append(" add CREATETIME Date ");
				jdbcUtil.executeSql(sql.toString(), null);
			}

			if (!containMap.containsKey("UPDATETIME")) {
				sql = new StringBuffer("alter table ");
				sql.append(PhyName).append(" add UPDATETIME Date ");
				jdbcUtil.executeSql(sql.toString(), null);
			}

			if (!containMap.containsKey("CREATEUSERID")) {
				sql = new StringBuffer("alter table ");
				sql.append(PhyName).append(" add CREATEUSERID nvarchar2 (32) ");
				jdbcUtil.executeSql(sql.toString(), null);
			}

			if (!containMap.containsKey("UPDATEUSERID")) {
				sql = new StringBuffer("alter table ");
				sql.append(PhyName).append(" add UPDATEUSERID nvarchar2 (32) ");
				jdbcUtil.executeSql(sql.toString(), null);
			}

			if (!containMap.containsKey("DEPTID")) {
				sql = new StringBuffer("alter table ");
				sql.append(PhyName).append(" add DEPTID nvarchar2 (50) ");
				jdbcUtil.executeSql(sql.toString(), null);
			}

			if (!containMap.containsKey("DELFLAG")) {
				sql = new StringBuffer("alter table ");
				sql.append(PhyName).append(
						" add DELFLAG NUMBER(10,0) DEFAULT(0) ");
				jdbcUtil.executeSql(sql.toString(), null);
			}

			return ffList;

		} else {
			return null;
		}

	}
	
	/**
	 * 获得一个数据库链接
	 * 
	 * @return
	 * @throws Exception
	 */
	private Connection getConnection() throws Exception {
		return DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
	}

	/**
	 * 释放数据库链接
	 * 
	 * @param conn
	 */
	private void releaseConnection(Connection conn) {
		DataSourceUtils.releaseConnection(conn, jdbcTemplate.getDataSource());
	}
}
