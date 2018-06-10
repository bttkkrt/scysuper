/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-10          Chenjian         create
 * ---------------------------------------------------------------
 */
package com.jshx.core.base.dao;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Component;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.Constants;

/**
 * 提供基于JDBC的数据库常用操作，本类中对数据库的操作均不含事务，所有事务在Service层进行管理
 * 
 * @author Chenjian
 * @version 创建时间：2011-1-10 下午06:54:10
 */
@SuppressWarnings("deprecation")
@Component("jdbcUtil")
public class JdbcUtil {

	protected Logger logger = LoggerFactory.getLogger(JdbcUtil.class);

	@Autowired() 
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired() @Qualifier("sqlMapClient")
	private SqlMapClient jdbcSqlMapClient;

	@Autowired() @Qualifier("oracleLobHandler")
	private LobHandler lobHandler;

	/**
	 * 获取Spring的JdbcTemplate
	 * 
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public SqlMapClient getJdbcSqlMapClient() {
		return jdbcSqlMapClient;
	}

	public void setJdbcSqlMapClient(SqlMapClient jdbcSqlMapClient) {
		this.jdbcSqlMapClient = jdbcSqlMapClient;
	}

	public LobHandler getLobHandler() {
		return lobHandler;
	}

	public void setLobHandler(LobHandler lobHandler) {
		this.lobHandler = lobHandler;
	}

	/**
	 * 手动获得一个数据库连接，需要手动关闭
	 * 
	 * @return Connection 
	 */
	public Connection getConnection() {
		try {
			Connection conn = DataSourceUtils.getConnection(jdbcTemplate
					.getDataSource());
			return conn;
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, Constants.GET_CONN_ERROR, e.getCause());
		}
	}

	/**
	 * 手动释放一个数据库连接
	 * 
	 * @param conn 数据库连接对象
	 * @return void 
	 */
	public void releaseConn(Connection conn)  {
		try {
			DataSourceUtils.releaseConnection(conn, jdbcTemplate
					.getDataSource());
			conn = null;
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, Constants.CLOSE_CONN_ERROR, e
					.getCause());
		}
	}
	
	/**
	 * 根据参入对象动态生成sql，并将参数Map转为数组
	 * 
	 * @param sqlId           在sql-map中保存的sql语句的id
	 * @param paraMap         传入参数Map必须为LinkedHashMap，其插入顺序应与iBatis配置文件中的SQL查询条件顺序一致
	 * @return Map            返回Map包括两部分，一个是key为 “SQL”的sql语句，一个是key为“PARAMS”的参数数组
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Map getDynamicStatement(String sqlId, Map<String, Object> paraMap) {
		if (jdbcSqlMapClient == null) {
			logger.error("No IBATIS sqlMapClient setted!");
			throw new BasalException(BasalException.ERROR, Constants.IBATIS_NOT_FIND);
		}
		Map map = new HashMap();
		String dynamicSql = null;
		ExtendedSqlMapClient extendedSqlMapClient = (ExtendedSqlMapClient) jdbcSqlMapClient;
		MappedStatement mappedStatement = extendedSqlMapClient
				.getMappedStatement(sqlId);
		if (mappedStatement != null) {
			
			SessionScope sessionScope = new SessionScope();     
	        sessionScope.incrementRequestStackDepth();     
	        StatementScope statementScope = new StatementScope(sessionScope);
	        statementScope.setStatement(mappedStatement);
			dynamicSql = mappedStatement.getSql().getSql(statementScope, paraMap);
	        
		}
		
		//处理参数，将其变为数组
		if(paraMap!=null){
			Object[] params = new Object[paraMap.keySet().size()];
			Iterator<String> keyIt = paraMap.keySet().iterator();
			int i = 0;
			while(keyIt.hasNext()){
				String key = keyIt.next();
				params[i] = paraMap.get(key);
				String id = ":"+key;
				dynamicSql = dynamicSql.replaceAll(id, "?");
				i++;
			}
			map.put("PARAMS", params);
			map.put("SQL", dynamicSql);
		}
		return map;
	}
	
	/**
	 * 根据iBatis配置翻页查询
	 * 
	 * @param sqlId     在sql-map中保存的sql语句的id
	 * @param page      分页对象，需要设置分页大小，页数
	 * @param paraMap                     传入参数Map必须为LinkedHashMap，其插入顺序应与iBatis配置文件中的SQL查询条件顺序一致
	 * @return Pagination   查询结果，数据集保存在对象的list中，总记录数保存在totalCnt中
	 */
	@SuppressWarnings("rawtypes")
	public Pagination findPage(String sqlId, final Pagination page,
			LinkedHashMap<String, Object> paraMap) {

		Map result = this.getDynamicStatement(sqlId, paraMap);
		String querySql = (String)result.get("SQL");
		Object[] params = (Object[])result.get("PARAMS");
		if (querySql == null || querySql.trim().equals("")) {
			logger.warn(Constants.IBATIS_SQL_NOT_FIND + "(SQL ID: " + sqlId
					+ ")");
			throw new BasalException(BasalException.ERROR, Constants.IBATIS_SQL_NOT_FIND
					+ "(SQL ID: " + sqlId + ")");
		}
		try {
			// 查找总记录数
			String countSql = this.sqlCountRows(querySql);
			int totalCount = jdbcTemplate.queryForInt(countSql, params);
			page.setTotalCount(totalCount);

			int pageNo = page.getPageNumber();
			final int pageSize = page.getPageSize();
			final int startRow = (pageNo - 1) * pageSize;
			List list = findListBySizeAndSql(querySql, startRow, pageSize, params);
			page.setList(list);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, Constants.JDBC_PAGE_ERROR, e.getCause());
		}
		return page;
	}

	/**
	 * 简单的去除order by语句，并生成计数语句<br>
	 * 注：在sql语句中如果有排序，排序关键字需要设为"order by"
	 * 
	 * @param querySql 查询语句
	 * @return String  查询总记录数的语句
	 */
	private String sqlCountRows(String querySql) {
		int pos = querySql.toLowerCase().lastIndexOf("order by");
		if (pos == -1)
			return "select count(*) from (" + querySql + ") t";
		else {
			return "select count(*) from (" + querySql.substring(0, pos) + ") t";
		}
	}

	/**
	 * 根据iBatis配置的SQL查找指定位置的记录集，返回为字段与字段值一一对应的Map列表
	 * 
	 * @param sqlId     在sql-map中配置的sql语句的id
	 * @param startRow  开始查找的记录数
	 * @param pageSize  分页数
	 * @param paraMap                   传入参数Map必须为LinkedHashMap，其插入顺序应与iBatis配置文件中的SQL查询条件顺序一致
	 * @return List<Map<String,Object>>   
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, Object>> findListBySize(String sqlId,
			final int startRow, final int pageSize, LinkedHashMap<String, Object> paraMap) {
		final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map result = this.getDynamicStatement(sqlId, paraMap);
		String querySql = (String)result.get("SQL");
		Object[] params = (Object[])result.get("PARAMS");
		if (querySql == null || querySql.trim().equals("")) {
			logger.warn(Constants.IBATIS_SQL_NOT_FIND + "(SQL ID: " + sqlId
					+ ")");
			throw new BasalException(BasalException.ERROR, Constants.IBATIS_SQL_NOT_FIND
					+ "(SQL ID: " + sqlId + ")");
		}
		try {
			jdbcTemplate.query(querySql, params, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					// 根据查询的字段将查询结果封装为Map
					ResultSetMetaData rsmd = rs.getMetaData();
					int numberOfColumns = rsmd.getColumnCount();
					String[] columnNames = new String[numberOfColumns];
					for (int i = 1; i <= numberOfColumns; i++) {
						columnNames[i - 1] = rsmd.getColumnName(i);
					}
					rs.setFetchSize(pageSize);
					
					int currentRow = 0;
					while (rs.next() && currentRow < startRow + pageSize) {
						 if (currentRow >= startRow) { 
							 Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
								for (String columnName : columnNames) {
									Object o = rs.getObject(columnName);
									if(o instanceof Clob && o!=null){
										//CLOB处理
										String value = ((Clob)o).getSubString((long) 1, (int)((Clob)o).length());
										resultMap.put(columnName, value);
									}else if(o instanceof Blob && o!=null){
										Blob blob = (Blob)o;
										byte[]   but   =   new   byte[   (int)   blob.length()]; 
						                try{
						                	blob.getBinaryStream().read(but,   0,   but.length);
						                }catch(Exception e){
						                	throw new BasalException(BasalException.ERROR, Constants.READ_BLOB_ERROR, e.getCause());
						                }
										resultMap.put(columnName, but);
									}else if(o!= null && o instanceof Date){
										resultMap.put(columnName, rs.getTimestamp(columnName));
									}else
										resultMap.put(columnName, o);
								}
								list.add(resultMap);
						 }
						currentRow++;
					}
					return list;
				}
			});
			
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, Constants.JDBC_LIST_ERROR, e.getCause());
		}
		return list;
	}

	/**
	 * 根据iBatis配置的SQL查找记录集，返回为字段与字段值一一对应的Map列表
	 * 
	 * @param sqlId   在sql-map中配置的sql语句的id
	 * @param paraMap                    传入参数Map必须为LinkedHashMap，其插入顺序应与iBatis配置文件中的SQL查询条件顺序一致
	 * @return List<Map<String,Object>>   
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, Object>> findList(String sqlId, LinkedHashMap<String, Object> paraMap) {
		final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map result = this.getDynamicStatement(sqlId, paraMap);
		String querySql = (String)result.get("SQL");
		Object[] params = (Object[])result.get("PARAMS");
		if (querySql == null || querySql.trim().equals("")) {
			logger.warn(Constants.IBATIS_SQL_NOT_FIND + "(SQL ID: " + sqlId
					+ ")");
			throw new BasalException(BasalException.ERROR, Constants.IBATIS_SQL_NOT_FIND
					+ "(SQL ID: " + sqlId + ")");
		}
		try {
			jdbcTemplate.query(querySql, params, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					// 根据查询的字段将查询结果封装为Map
					ResultSetMetaData rsmd = rs.getMetaData();
					int numberOfColumns = rsmd.getColumnCount();
					String[] columnNames = new String[numberOfColumns];
					for (int i = 1; i <= numberOfColumns; i++) {
						columnNames[i - 1] = rsmd.getColumnName(i);
					}
					while (rs.next()) {
						Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
						for (String columnName : columnNames) {
							Object o = rs.getObject(columnName);
							if(o instanceof Clob && o!=null){
								//CLOB处理
								String value = ((Clob)o).getSubString((long) 1, (int)((Clob)o).length());
								resultMap.put(columnName, value);
							}else if(o instanceof Blob && o!=null){
								Blob blob = (Blob)o;
								byte[]   but   =   new   byte[   (int)   blob.length()]; 
				                try{
				                	blob.getBinaryStream().read(but,   0,   but.length);
				                }catch(Exception e){
				                	throw new BasalException(BasalException.ERROR, Constants.READ_BLOB_ERROR, e.getCause());
				                }
								resultMap.put(columnName, but);
							}else if(o!= null && o instanceof Date){
								resultMap.put(columnName, rs.getTimestamp(columnName));
							}else
								resultMap.put(columnName, o);
						}
						list.add(resultMap);
					}
					return list;
				}
			});
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, Constants.JDBC_LIST_ERROR, e.getCause());
		}
		return list;
	}

	/**
	 * 根据iBatis配置的SQL查找单条记录，并返回字段名与字段值相对应的Map对象
	 * 
	 * @param sqlId 在sql-map中配置的sql语句的id
	 * @param paraMap             传入参数Map必须为LinkedHashMap，其插入顺序应与iBatis配置文件中的SQL查询条件顺序一致
	 * @return Map<String,Object>   
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Object> find(String sqlId, LinkedHashMap<String, Object> paraMap) {
		Map result = this.getDynamicStatement(sqlId, paraMap);
		String querySql = (String)result.get("SQL");
		Object[] params = (Object[])result.get("PARAMS");
		if (querySql == null || querySql.trim().equals("")) {
			logger.warn(Constants.IBATIS_SQL_NOT_FIND + "(SQL ID: " + sqlId
					+ ")");
			throw new BasalException(BasalException.ERROR, Constants.IBATIS_SQL_NOT_FIND
					+ "(SQL ID: " + sqlId + ")");
		}
		final Map<String, Object> valueMap = new HashMap<String, Object>();
		try {
			jdbcTemplate.query(querySql, params, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException,
						DataAccessException {

					// 根据查询的字段将查询结果封装为Map
					ResultSetMetaData rsmd = rs.getMetaData();
					int numberOfColumns = rsmd.getColumnCount();
					String[] columnNames = new String[numberOfColumns];
					for (int i = 1; i <= numberOfColumns; i++) {
						columnNames[i - 1] = rsmd.getColumnName(i);
					}
					if (rs.next()) {
						for (String columnName : columnNames) {
							Object o = rs.getObject(columnName);
							if(o instanceof Clob && o!=null){
								//CLOB处理
								String value = ((Clob)o).getSubString((long) 1, (int)((Clob)o).length());
								valueMap.put(columnName, value);
							}else if(o instanceof Blob && o!=null){
								Blob blob = (Blob)o;
								byte[]   but   =   new   byte[   (int)   blob.length()]; 
				                try{
				                	blob.getBinaryStream().read(but,   0,   but.length);
				                }catch(Exception e){
				                	throw new BasalException(BasalException.ERROR, Constants.READ_BLOB_ERROR, e.getCause());
				                }
				                valueMap.put(columnName, but);
							}else if(o!= null && o instanceof Date){
								valueMap.put(columnName, rs.getTimestamp(columnName));
							}else
								valueMap.put(columnName, o);
						}
					}
					return valueMap;
				}
			});
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, Constants.JDBC_LIST_ERROR, e.getCause());
		}
		return valueMap;
	}

	/**
	 * 根据SQL配置翻页查询
	 * 
	 * @param querySql 查询的sql语句
	 * @param page  页面对象，包括分页数和页数
	 * @param params 查询参数
	 * @return Pagination   查询结果，数据集保存在对象的list中，总记录数保存在totalCnt中
	 */
	public Pagination findPageBySql(String querySql, final Pagination page,
			Object... params)  {

		try {
			// 查找总记录数
			String countSql = this.sqlCountRows(querySql);
			int totalCount = jdbcTemplate.queryForInt(countSql, params);
			page.setTotalCount(totalCount);

			int pageNo = page.getPageNumber();
			final int pageSize = page.getPageSize();
			final int startRow = (pageNo - 1) * pageSize;
			List<?> list = findListBySizeAndSql(querySql, startRow, pageSize,
					params);
			page.setList(list);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, Constants.JDBC_PAGE_ERROR, e.getCause());
		}
		return page;
	}

	/**
	 * 根据SQL查找指定位置的记录集，返回为字段与字段值一一对应的Map列表
	 * 
	 * @param querySql 查询的sql语句
	 * @param startRow 查询记录的起始
	 * @param pageSize 分页数
	 * @param params 查询参数
	 * @return List<Map<String,Object>>   
	 */
	public List<Map<String, Object>> findListBySizeAndSql(String querySql,
			final int startRow, final int pageSize, Object... params){
		final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		;

		try {
			jdbcTemplate.query(querySql, params, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					// 根据查询的字段将查询结果封装为Map
					ResultSetMetaData rsmd = rs.getMetaData();
					int numberOfColumns = rsmd.getColumnCount();
					String[] columnNames = new String[numberOfColumns];
					for (int i = 1; i <= numberOfColumns; i++) {
						columnNames[i - 1] = rsmd.getColumnName(i);
					}
					
					int currentRow = 0;
					while (rs.next() && currentRow < startRow + pageSize) {
						if (currentRow >= startRow) { 
							 Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
								for (String columnName : columnNames) {
									Object o = rs.getObject(columnName);
									if(o instanceof Clob && o!=null){
										//CLOB处理
										String value = ((Clob)o).getSubString((long) 1, (int)((Clob)o).length());
										resultMap.put(columnName, value);
									}else if(o instanceof Blob && o!=null){
										Blob blob = (Blob)o;
										byte[]   but   =   new   byte[   (int)   blob.length()]; 
						                try{
						                	blob.getBinaryStream().read(but,   0,   but.length);
						                }catch(Exception e){
						                	throw new BasalException(BasalException.ERROR, Constants.READ_BLOB_ERROR,e.getCause());
						                }
										resultMap.put(columnName, but);
									}else if(o!= null && o instanceof Date){
										resultMap.put(columnName, rs.getTimestamp(columnName));
									}else
										resultMap.put(columnName, o);
								}
								list.add(resultMap);
						 }
						currentRow++;
					}
					return list;
				}
			});
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, Constants.JDBC_LIST_ERROR, e.getCause());
		}
		return list;
	}

	/**
	 * 根据SQL查找记录集，返回为字段与字段值一一对应的Map列表
	 * 
	 * @param querySql 查询的sql语句
	 * @param params 查询参数
	 * @return List<Map<String,Object>>   
	 */
	public List<Map<String, Object>> findListBySql(String querySql,
			Object... params) {
		final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		;

		try {
			jdbcTemplate.query(querySql, params, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					// 根据查询的字段将查询结果封装为Map
					ResultSetMetaData rsmd = rs.getMetaData();
					int numberOfColumns = rsmd.getColumnCount();
					String[] columnNames = new String[numberOfColumns];
					for (int i = 1; i <= numberOfColumns; i++) {
						columnNames[i - 1] = rsmd.getColumnName(i);
					}
					while (rs.next()) {
						Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
						for (String columnName : columnNames) {
							Object o = rs.getObject(columnName);
							if(o instanceof Clob && o!=null){
								//CLOB处理
								String value = ((Clob)o).getSubString((long) 1, (int)((Clob)o).length());
								resultMap.put(columnName, value);
							}else if(o instanceof Blob && o!=null){
								Blob blob = (Blob)o;
								byte[]   but   =   new   byte[   (int)   blob.length()]; 
				                try{
				                	blob.getBinaryStream().read(but,   0,   but.length);
				                }catch(Exception e){
				                	throw new BasalException(BasalException.ERROR, Constants.READ_BLOB_ERROR, e.getCause());
				                }
								resultMap.put(columnName, but);
							}else if(o!= null && o instanceof Date){
								resultMap.put(columnName, rs.getTimestamp(columnName));
							}else
								resultMap.put(columnName, o);
						}
						list.add(resultMap);
					}
					return list;
				}
			});
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, Constants.JDBC_LIST_ERROR, e.getCause());
		}
		return list;
	}

	/**
	 * 根据SQL查找单条记录，并返回字段名与字段值相对应的Map对象
	 * 
	 * @param querySql 查询的sql语句
	 * @param params 查询参数
	 * @return Map<String,Object>
	 */
	public Map<String, Object> findBySql(String querySql, Object... params){

		final Map<String, Object> valueMap = new HashMap<String, Object>();
		try {
			jdbcTemplate.query(querySql, params, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException,
						DataAccessException {

					// 根据查询的字段将查询结果封装为Map
					ResultSetMetaData rsmd = rs.getMetaData();
					int numberOfColumns = rsmd.getColumnCount();
					String[] columnNames = new String[numberOfColumns];
					for (int i = 1; i <= numberOfColumns; i++) {
						columnNames[i - 1] = rsmd.getColumnName(i);
					}
					if (rs.next()) {
						for (String columnName : columnNames) {
							Object o = rs.getObject(columnName);
							if(o instanceof Clob && o!=null){
								//CLOB处理
								String value = ((Clob)o).getSubString((long) 1, (int)((Clob)o).length());
								valueMap.put(columnName, value);
							}else if(o instanceof Blob && o!=null){
								Blob blob = (Blob)o;
								byte[]   but   =   new   byte[   (int)   blob.length()]; 
				                try{
				                	blob.getBinaryStream().read(but,   0,   but.length);
				                }catch(Exception e){
				                	throw new BasalException(BasalException.ERROR, Constants.READ_BLOB_ERROR,e.getCause());
				                }
								valueMap.put(columnName, but);
							}else if(o!= null && o instanceof Date){
								valueMap.put(columnName, rs.getTimestamp(columnName));
							}else
								valueMap.put(columnName, o);
						}
					}
					return valueMap;
				}
			});
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, Constants.JDBC_LIST_ERROR, e.getCause());
		}
		return valueMap;
	}

	/**
	 * 插入一条新纪录
	 * 
	 * @param tableName 表名
	 * @param paraMap
	 *            要插入的字段及其值，字段名为map的Key，值为map的value
	 * @param blobMap
	 *            BLOB字段以及对应的BLOB内容
	 * @param clobMap
	 *            CLOB字段以及对应的CLOB内容
	 * @return void
	 */
	public void insert(String tableName, Map<String, Object> paraMap,
			Map<String, Object> blobMap, Map<String, String> clobMap){
		int paramLength = 0;
		if (paraMap != null)
			paramLength = paraMap.keySet().size();
		int blobLength = 0;
		if (blobMap != null)
			blobLength = blobMap.keySet().size();
		int clobLength = 0;
		if (clobMap != null)
			clobLength = clobMap.keySet().size();
		final Object[] params = new Object[paramLength];
		final Object[] blobs = new Object[blobLength];
		final String[] clobs = new String[clobLength];
		final int totalLength = paramLength + blobLength + clobLength;
		// 构建insert语句
		StringBuffer sql = new StringBuffer("insert into ");
		sql.append(tableName).append("(");
		if (paraMap != null) {
			int i = 0;
			Iterator<String> keyIt = paraMap.keySet().iterator();
			while (keyIt.hasNext()) {
				String key = keyIt.next();
				params[i] = paraMap.get(key);
				sql.append(key);
				if (i < paramLength - 1)
					sql.append(",");
				i++;
			}
		}
		if (blobMap != null && blobLength>0) {
			sql.append(",");
			int i = 0;
			Iterator<String> keyIt = blobMap.keySet().iterator();
			while (keyIt.hasNext()) {
				String key = keyIt.next();
				blobs[i] = blobMap.get(key);
				sql.append(key);
				if (i < blobLength - 1)
					sql.append(",");
				i++;
			}
		}
		if (clobMap != null && clobLength>0) {
			sql.append(",");
			int i = 0;
			Iterator<String> keyIt = clobMap.keySet().iterator();
			while (keyIt.hasNext()) {
				String key = keyIt.next();
				clobs[i] = clobMap.get(key);
				sql.append(key);
				if (i < blobLength - 1)
					sql.append(",");
				i++;
			}
		}
		sql.append(") values (");
		for (int i = 0; i < totalLength; i++) {
			sql.append("?");
			if (i < totalLength - 1)
				sql.append(",");
		}
		sql.append(")");
		
		try {
			jdbcTemplate.execute(sql.toString(),
					new AbstractLobCreatingPreparedStatementCallback(
							this.lobHandler) {

						@Override
						protected void setValues(PreparedStatement pstmt,
								LobCreator lobCreator) throws SQLException,
								DataAccessException {
							int j = 1;
							for (int i = 0; i < params.length; i++) {
								pstmt.setObject(j, params[i]);
								j++;
							}
							for (int i = 0; i < blobs.length; i++) {
								lobCreator.setBlobAsBytes(pstmt, j,
										(byte[]) blobs[i]);
								j++;
							}
							for (int i = 0; i < clobs.length; i++) {
								lobCreator.setClobAsString(pstmt, j, clobs[i]);
								j++;
							}
						}
					});
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, Constants.SQL_ERROR, e.getCause());
		}
		
	}

	/**
	 * 修改一条记录
	 * 
	 * @param tableName 表名
	 * @param paraMap
	 *            需要修改的字段及其值，字段名为map的Key，值为map的value
	 * @param blobMap
	 *            BLOB字段以及对应的BLOB内容
	 * @param clobMap
	 *            CLOB字段以及对应的CLOB内容
	 * @param queryMap
	 *            记录的查询条件，字段名为map的Key，值为map的value
	 * @return void
	 */
	public void update(String tableName, final Map<String, Object> paraMap,
			final Map<String, Object> blobMap, final Map<String, String> clobMap,
			Map<String, Object> queryMap){
		int paramLength = 0;
		if (paraMap != null)
			paramLength = paraMap.keySet().size();
		int blobLength = 0;
		if (blobMap != null)
			blobLength = blobMap.keySet().size();
		int clobLength = 0;
		if (clobMap != null)
			clobLength = clobMap.keySet().size();
		int queryLength = 0;
		if (queryMap != null)
			queryLength = queryMap.keySet().size();
		final Object[] params = new Object[paramLength];
		final Object[] blobs = new Object[blobLength];
		final String[] clobs = new String[clobLength];
		final Object[] querys = new Object[queryLength];

		// 构建insert语句
		StringBuffer sql = new StringBuffer("update ");
		sql.append(tableName).append(" set ");
		if (paraMap != null) {
			int i = 0;
			Iterator<String> keyIt = paraMap.keySet().iterator();
			while (keyIt.hasNext()) {
				String key = keyIt.next();
				params[i] = paraMap.get(key);
				sql.append(key).append("=?");
				if (i < paramLength - 1)
					sql.append(",");
				i++;
			}
		}
		if (blobMap != null && blobLength>0) {
			sql.append(",");
			int i = 0;
			Iterator<String> keyIt = blobMap.keySet().iterator();
			while (keyIt.hasNext()) {
				String key = keyIt.next();
				blobs[i] = blobMap.get(key);
				sql.append(key).append("=?");
				if (i < blobLength - 1)
					sql.append(",");
				i++;
			}
		}
		if (clobMap != null && clobLength>0) {
			sql.append(",");
			int i = 0;
			Iterator<String> keyIt = clobMap.keySet().iterator();
			while (keyIt.hasNext()) {
				String key = keyIt.next();
				clobs[i] = clobMap.get(key);
				sql.append(key).append("=?");
				if (i < blobLength - 1)
					sql.append(",");
				i++;
			}
		}
		if (queryMap != null) {
			sql.append(" where 1=1");
			Iterator<String> keyIt = queryMap.keySet().iterator();
			int i = 0;
			while (keyIt.hasNext()) {
				String key = keyIt.next();
				querys[i] = queryMap.get(key);
				sql.append(" and ").append(key).append("=?");
			}
		}
		
		try {
			jdbcTemplate.execute(sql.toString(),
					new AbstractLobCreatingPreparedStatementCallback(
							this.lobHandler) {

						@Override
						protected void setValues(PreparedStatement pstmt,
								LobCreator lobCreator) throws SQLException,
								DataAccessException {
							int j = 1;
							for (int i = 0; i < params.length; i++) {
								pstmt.setObject(j, params[i]);
								j++;
							}
							for (int i = 0; i < blobs.length; i++) {
								lobCreator.setBlobAsBytes(pstmt, j,
										(byte[]) blobs[i]);
								j++;
							}
							for (int i = 0; i < clobs.length; i++) {
								lobCreator.setClobAsString(pstmt, j, clobs[i]);
								j++;
							}
							for (int i = 0; i < querys.length; i++) {
								pstmt.setObject(j, querys[i]);
								j++;
							}
						}
					});
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, Constants.SQL_ERROR, e.getCause());
		}
	}

	/**
	 * 删除记录
	 * 
	 * @param tableName 表名
	 * @param queryMap
	 *            记录的查询条件，字段名为map的Key，值为map的value；map为空的话表示删除整表数据
	 * @return void
	 */
	public void remove(String tableName, final Map<String, Object> queryMap){
		StringBuffer sql = new StringBuffer("delete from ");
		sql.append(tableName);
		Object[] params = null;
		if (queryMap != null) {
			sql.append(" where 1=1");
			params = new Object[queryMap.keySet().size()];
			Iterator<String> keyIt = queryMap.keySet().iterator();
			int i = 0;
			while (keyIt.hasNext()) {
				String key = keyIt.next();
				params[i] = queryMap.get(key);
				sql.append(" and ").append(key).append("=?");
			}
		}
		
		try {
			jdbcTemplate.update(sql.toString(), params);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, Constants.SQL_ERROR, e.getCause());
		}
	}
	
	/**
	 * 执行SQL语句
	 * 
	 * @param sql 需要执行的sql语句
	 * @param params 参数
	 * @return int   
	 */
	public int executeSql(String sql, final Object[] params) {
		try{			
			return jdbcTemplate.update(sql, params );
		}catch(Exception e){
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, Constants.SQL_ERROR, e.getCause());
		}
	}

	/**
	 * 批处理SQL
	 * 
	 * @param sql 需要执行的sql语句
	 * @param paramList 参数
	 * @return int[]
	 */
	public int[] batchExecute(String sql, final List<Object[]> paramList) {
		
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				Object[] params = paramList.get(i);
				if(params!=null){
					for(int j=0;j<params.length;j++){
						ps.setObject(j+1, params[j]);
					}
				}
			}
			
			public int getBatchSize() {
				if(paramList!=null)
					return paramList.size();
				else
					return 0;
			}
		};

		try{
			return jdbcTemplate.batchUpdate(sql, setter);
		}catch(Exception e){
			logger.error(e.getLocalizedMessage(), e.getCause());
			throw new BasalException(BasalException.ERROR, Constants.SQL_ERROR, e.getCause());
		}
	}

	/**
	 * 获得表的主键名
	 * 
	 * @param tableName 表名
	 * @return List<String>
	 */
	public List<String> getPrimaryKeys(String tableName){
		List<String> primaryKeys = new ArrayList<String>();
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection();
			DatabaseMetaData dbmd = conn.getMetaData();
			rs = dbmd.getPrimaryKeys(null, null, tableName);
			
			while (rs.next()) {
				primaryKeys.add(rs.getString("COLUMN_NAME"));
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.fillInStackTrace());
			throw new BasalException(BasalException.ERROR, Constants.SQL_ERROR, e
					.fillInStackTrace());
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e
							.fillInStackTrace());
					throw new BasalException(BasalException.ERROR, Constants.CLOSE_CONN_ERROR, e
							.fillInStackTrace());
				}
			}
			if (conn != null) {
				try {
					releaseConn(conn);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e
							.fillInStackTrace());
					throw new BasalException(BasalException.ERROR, Constants.CLOSE_CONN_ERROR, e
							.fillInStackTrace());
				}
			}
		}
		return primaryKeys;
	}

	/**
	 * 获得数据表的字段名
	 * 
	 * @param tableName 表名
	 * @return List<String>
	 */
	public List<String> getColumnNames(String tableName){
		List<String> columnNames = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = conn.createStatement().executeQuery(
					"select * from " + tableName);
			ResultSetMetaData rss = rs.getMetaData();
			int columnCount = rss.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				columnNames.add(rss.getColumnName(i));
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.fillInStackTrace());
			throw new BasalException(BasalException.ERROR, Constants.SQL_ERROR, e
					.fillInStackTrace());
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e
							.fillInStackTrace());
					throw new BasalException(BasalException.ERROR, Constants.CLOSE_CONN_ERROR, e
							.fillInStackTrace());
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e
							.fillInStackTrace());
					throw new BasalException(BasalException.ERROR, Constants.CLOSE_CONN_ERROR, e
							.fillInStackTrace());
				}
			}
			if (conn != null) {
				try {
					releaseConn(conn);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e
							.fillInStackTrace());
					throw new BasalException(BasalException.ERROR, Constants.CLOSE_CONN_ERROR, e
							.fillInStackTrace());
				}
			}
		}
		return columnNames;
	}

	/**
	 * 获得数据表的字段以及字段类型
	 * 
	 * @param tableName 表名
	 * @return Map<String,String> key为字段名，value为字段类型
	 */
	public Map<String, String> getColumnTypes(String tableName){
		Map<String, String> columnTypes = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection();
			pstmt = conn.prepareStatement("select * from " + tableName);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();// 取得列数

			for (int col = 1; col <= colCount; col++) {
				String columnName = rsmd.getColumnName(col);
				String columnType = rsmd.getColumnTypeName(col);
				columnTypes.put(columnName, columnType);
			}

		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.fillInStackTrace());
			throw new BasalException(BasalException.ERROR, Constants.SQL_ERROR, e
					.fillInStackTrace());
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e
							.fillInStackTrace());
					throw new BasalException(BasalException.ERROR, Constants.CLOSE_CONN_ERROR, e
							.fillInStackTrace());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
					pstmt = null;
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e
							.fillInStackTrace());
					throw new BasalException(BasalException.ERROR, Constants.CLOSE_CONN_ERROR, e
							.fillInStackTrace());
				}
			}
			if (conn != null) {
				try {
					releaseConn(conn);
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e
							.fillInStackTrace());
					throw new BasalException(BasalException.ERROR, Constants.CLOSE_CONN_ERROR, e
							.fillInStackTrace());
				}
			}
		}
		return columnTypes;
	}
}
