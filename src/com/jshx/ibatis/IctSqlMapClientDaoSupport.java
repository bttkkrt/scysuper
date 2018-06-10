package com.jshx.ibatis;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;
@SuppressWarnings("unchecked")
@Component
public class IctSqlMapClientDaoSupport {
	protected final Log logger = LogFactory.getLog(getClass());
	private SqlMapClientTemplate sqlMapClientTemplate;

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
	/**
	  * Executes a mapped SQL SELECT statement that returns data to populate
	  * the supplied result object.
	  * <p/>
	  * The parameter object is generally used to supply the input
	  * data for the WHERE clause parameter(s) of the SELECT statement.
	  *
	  * @param id              The name of the statement to execute.
	  * @param parameterObject The parameter object (e.g. JavaBean, Map, XML etc.).
	  * @param resultObject    The result object instance that should be populated with result data.
	  * @return The single result object as supplied by the resultObject parameter, populated with the result set data,
	  *         or null if no result was found
	  * @throws org.springframework.dao.DataAccessException If more than one result was found, or if any other error occurs.
	  */
	public Object queryForObject(String sqlMapId, Object paramObject, Object resultObject) throws RuntimeException {
		return getSqlMapClientTemplate().queryForObject(sqlMapId, paramObject, resultObject);
	}
	/**
	  * Executes a mapped SQL SELECT statement that returns data to populate
	  * a number of result objects.
	  * <p/>
	  * This overload assumes no parameter is needed.
	  *
	  * @param id              The name of the statement to execute.
	  * @return A List of result objects.
	  * @throws org.springframework.dao.DataAccessException If an error occurs.
	  */
	public List queryForList(String sqlMapId){
		return getSqlMapClientTemplate().queryForList(sqlMapId);
	}
	/**
	 * Executes a mapped SQL SELECT statement that returns data to populate
	 * a number of result objects within a certain range.
	 * <p/>
	 * This overload assumes no parameter is needed.
	 *
	 * @param id              The name of the statement to execute.
	 * @param skip            The number of results to ignore.
	 * @param max             The maximum number of results to return.
	 * @return A List of result objects.
	 * @throws org.springframework.dao.DataAccessException If an error occurs.
	 */
	public List queryForList(String sqlMapId, int start, int limit){
		return getSqlMapClientTemplate().queryForList(sqlMapId, start, limit);
	}
	
	/**
	  * Executes a mapped SQL SELECT statement that returns data to populate
	  * a number of result objects.
	  * <p/>
	  * The parameter object is generally used to supply the input
	  * data for the WHERE clause parameter(s) of the SELECT statement.
	  *
	  * @param id              The name of the statement to execute.
	  * @param parameterObject The parameter object (e.g. JavaBean, Map, XML etc.).
	  * @return A List of result objects.
	  */
	public List queryForList(String sqlMapId, Object paramObject){
		return getSqlMapClientTemplate().queryForList(sqlMapId, paramObject);
	}
	
	  /**
	   * Executes a mapped SQL SELECT statement that returns data to populate
	   * a number of result objects within a certain range.
	   * <p/>
	   * The parameter object is generally used to supply the input
	   * data for the WHERE clause parameter(s) of the SELECT statement.
	   *
	   * @param id              The name of the statement to execute.
	   * @param parameterObject The parameter object (e.g. JavaBean, Map, XML etc.).
	   * @param skip            The number of results to ignore.
	   * @param max             The maximum number of results to return.
	   * @return A List of result objects.
	   * @throws org.springframework.dao.DataAccessException If an error occurs.
	   */
	public List queryForList(String sqlMapId, Object paramObject, int start, int limit){
		return getSqlMapClientTemplate().queryForList(sqlMapId, paramObject, start, limit);
	}
	public int update(String sqlMapId) throws Exception {
		return update(sqlMapId, null);
	}
	
	  /**
	   * Executes a mapped SQL UPDATE statement.
	   * Update can also be used for any other update statement type,
	   * such as inserts and deletes.  Update returns the number of
	   * rows effected.
	   * <p/>
	   * The parameter object is generally used to supply the input
	   * data for the UPDATE values as well as the WHERE clause parameter(s).
	   *
	   * @param id              The name of the statement to execute.
	   * @param parameterObject The parameter object (e.g. JavaBean, Map, XML etc.).
	   * @return The number of rows effected.
	   * @throws org.springframework.dao.DataAccessException If an error occurs.
	   */
	public int update(String sqlMapId, Object paramObject) throws Exception {
		return getSqlMapClientTemplate().update(sqlMapId, paramObject);
	}
	
	public void updateBatch(String sqlMapId, Object[] objs) throws Exception {
	    try {
            getSqlMapClientTemplate().getSqlMapClient().startBatch();
            for (Object object : objs) {
                getSqlMapClientTemplate().update(sqlMapId, object);
            }
            getSqlMapClientTemplate().getSqlMapClient().executeBatch();
        } catch (SQLException e) {
            logger.error(e);
        }
	}
	
	
	public void deleteBatch(String sqlMapId, Object[] objs) throws Exception {
	    try {
            getSqlMapClientTemplate().getSqlMapClient().startBatch();
            for (Object object : objs) {
                getSqlMapClientTemplate().delete(sqlMapId, object);
            }
            getSqlMapClientTemplate().getSqlMapClient().executeBatch();
        } catch (SQLException e) {
            logger.error(e);
        }
	}
	
	public Object insert(String sqlMapId) throws Exception {
		return insert(sqlMapId, null);
	}
	
	public void insertBatch(String sqlMapId, Object[] objs) throws Exception {
	    try {
	        getSqlMapClientTemplate().getSqlMapClient().startBatch();
            for (Object object : objs) {
                getSqlMapClientTemplate().insert(sqlMapId, object);
            }
            getSqlMapClientTemplate().getSqlMapClient().executeBatch();
        } catch (SQLException e) {
            logger.error(e);
        }
	}
	
	  /**
	   * Executes a mapped SQL INSERT statement.
	   * Insert is a bit different from other update methods, as it
	   * provides facilities for returning the primary key of the
	   * newly inserted row (rather than the effected rows).  This
	   * functionality is of course optional.
	   * <p/>
	   * The parameter object is generally used to supply the input
	   * data for the INSERT values.
	   *
	   * @param id              The name of the statement to execute.
	   * @param parameterObject The parameter object (e.g. JavaBean, Map, XML etc.).
	   * @return The primary key of the newly inserted row.  This might be automatically
	   *         generated by the RDBMS, or selected from a sequence table or other source.
	   * @throws org.springframework.dao.DataAccessException If an error occurs.
	   */
	public Object insert(String sqlMapId, Object paramObject) throws Exception {
		@SuppressWarnings("unused")
		Object obj = getSqlMapClientTemplate().insert(sqlMapId, paramObject);
		return obj;
	}
	  /**
	   * Executes a mapped SQL SELECT statement that returns data to populate
	   * a single object instance.
	   * <p/>
	   * The parameter object is generally used to supply the input
	   * data for the WHERE clause parameter(s) of the SELECT statement.
	   *
	   * @param id              The name of the statement to execute.
	   * @param parameterObject The parameter object (e.g. JavaBean, Map, XML etc.).
	   * @return The single result object populated with the result set data,
	   *         or null if no result was found
	   * @throws org.springframework.dao.DataAccessException If more than one result was found, or if any other error occurs.
	   */
	public int delete(String sqlMapId) throws Exception {
		return getSqlMapClientTemplate().delete(sqlMapId);
	}
	  /**
	   * Executes a mapped SQL DELETE statement.
	   * Delete returns the number of rows effected.
	   * <p/>
	   * The parameter object is generally used to supply the input
	   * data for the WHERE clause parameter(s) of the DELETE statement.
	   *
	   * @param id              The name of the statement to execute.
	   * @param parameterObject The parameter object (e.g. JavaBean, Map, XML etc.).
	   * @return The number of rows effected.
	   * @throws org.springframework.dao.DataAccessException If an error occurs.
	   */
	public int delete(String sqlMapId, Object paramObject) throws Exception {
		return getSqlMapClientTemplate().delete(sqlMapId, paramObject);
	}
	
	@SuppressWarnings("unused")
	private String buildExceptionMessage(String sqlMapId){
		return buildExceptionMessage(sqlMapId, null);
	}
	private String buildExceptionMessage(String sqlMapId, Object paramObject){
		StringBuffer buf = new StringBuffer();
		buf.append("执行SQL脚本出错:");
		if(sqlMapId != null){
			buf.append("sqlMapId=");
			buf.append(sqlMapId);
			buf.append(";");
		}
		if(paramObject != null){
			buf.append("paramObject=");
			buf.append(paramObject.toString());
			buf.append(";");
			buf.append("paramObjectClass=");
			buf.append(paramObject.getClass().getName());
			buf.append(";");
		}
		return  buf.toString();
	}
}
