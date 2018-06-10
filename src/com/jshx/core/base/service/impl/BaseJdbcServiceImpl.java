/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-26        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.base.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.JdbcUtil;
import com.jshx.core.base.entity.JdbcParameter;
import com.jshx.core.base.service.BaseJdbcService;
import com.jshx.core.base.vo.Pagination;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-1-26 上午10:26:11  
 * 类说明  
 */
public class BaseJdbcServiceImpl implements BaseJdbcService {
	
	private JdbcUtil jdbcUtil;

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#batchDelete(java.util.List)
	 */
	
	public void batchDelete(List<JdbcParameter> paramList) {
		if(paramList==null || paramList.size()==0)
			return;
		for(JdbcParameter param : paramList){
			String tableName = param.getTableName();
			Map<String, Object> queryMap = param.getQueryMap();
			jdbcUtil.remove(tableName, queryMap);
		}
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#batchExecute(java.lang.String, java.util.List)
	 */
	
	public int[] batchExecute(String sql, List<Object[]> paramList) {
		return jdbcUtil.batchExecute(sql, paramList);
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#batchInsert(java.util.List)
	 */
	
	public void batchInsert(List<JdbcParameter> paramList) {
		if(paramList==null || paramList.size()==0)
			return;
		for(JdbcParameter param : paramList){
			String tableName = param.getTableName();
			Map<String, Object> blobMap = param.getBlobMap();
			Map<String, String> clobMap = param.getClobMap();
			Map<String, Object> paraMap = param.getParaMap();
			jdbcUtil.insert(tableName, paraMap, blobMap, clobMap);
		}
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#batchUpdate(java.util.List)
	 */
	
	public void batchUpdate(List<JdbcParameter> paramList) {
		if(paramList==null || paramList.size()==0)
			return;
		for(JdbcParameter param : paramList){
			String tableName = param.getTableName();
			Map<String, Object> blobMap = param.getBlobMap();
			Map<String, String> clobMap = param.getClobMap();
			Map<String, Object> paraMap = param.getParaMap();
			Map<String, Object> queryMap = param.getQueryMap();
			jdbcUtil.update(tableName, paraMap, blobMap, clobMap, queryMap);
		}
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#find(java.lang.String, java.util.LinkedHashMap)
	 */
	
	public Map<String, Object> find(String sqlId,
			LinkedHashMap<String, Object> paraMap) {
		return jdbcUtil.find(sqlId, paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#findBySql(java.lang.String, java.lang.Object[])
	 */
	
	public Map<String, Object> findBySql(String querySql, Object... params) {
		return jdbcUtil.findBySql(querySql, params);
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#findList(java.lang.String, java.util.LinkedHashMap)
	 */
	
	public List<Map<String, Object>> findList(String sqlId,
			LinkedHashMap<String, Object> paraMap) {
		return jdbcUtil.findList(sqlId, paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#findListBySize(java.lang.String, int, int, java.util.LinkedHashMap)
	 */
	
	public List<Map<String, Object>> findListBySize(String sqlId, int startRow,
			int pageSize, LinkedHashMap<String, Object> paraMap) {
		return jdbcUtil.findListBySize(sqlId, startRow, pageSize, paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#findListBySizeAndSql(java.lang.String, int, int, java.lang.Object[])
	 */
	
	public List<Map<String, Object>> findListBySizeAndSql(String querySql,
			int startRow, int pageSize, Object... params) {
		return jdbcUtil.findListBySizeAndSql(querySql, startRow, pageSize, params);
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#findListBySql(java.lang.String, java.lang.Object[])
	 */
	
	public List<Map<String, Object>> findListBySql(String querySql,
			Object... params) {
		return jdbcUtil.findListBySql(querySql, params);
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#findPage(java.lang.String, com.jshx.core.base.vo.Pagination, java.util.LinkedHashMap)
	 */
	
	public Pagination findPage(String sqlId, Pagination page,
			LinkedHashMap<String, Object> paraMap) {
		return jdbcUtil.findPage(sqlId, page, paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#findPageBySql(java.lang.String, com.jshx.core.base.vo.Pagination, java.lang.Object[])
	 */
	
	public Pagination findPageBySql(String querySql, Pagination page,
			Object... params) {
		return jdbcUtil.findPageBySql(querySql, page, params);
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#getColumnNames(java.lang.String)
	 */
	
	public List<String> getColumnNames(String tableName) {
		return jdbcUtil.getColumnNames(tableName);
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#getColumnTypes(java.lang.String)
	 */
	
	public Map<String, String> getColumnTypes(String tableName) {
		return jdbcUtil.getColumnTypes(tableName);
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#getPrimaryKeys(java.lang.String)
	 */
	
	public List<String> getPrimaryKeys(String tableName) {
		return jdbcUtil.getPrimaryKeys(tableName);
	}

	/**
	 * @return the jdbcUtil
	 */
	public JdbcUtil getJdbcUtil() {
		return jdbcUtil;
	}

	/**
	 * @param jdbcUtil the jdbcUtil to set
	 */
	public void setJdbcUtil(JdbcUtil jdbcUtil) {
		this.jdbcUtil = jdbcUtil;
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#executeSql(java.lang.String, java.lang.Object[])
	 */
	
	public int executeSql(String sql, Object[] params) {
		return jdbcUtil.executeSql(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#delete(com.jshx.core.base.entity.JdbcParameter)
	 */
	
	public void delete(JdbcParameter param) {
		String tableName = param.getTableName();
		Map<String, Object> queryMap = param.getQueryMap();
		jdbcUtil.remove(tableName, queryMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#insert(com.jshx.core.base.entity.JdbcParameter)
	 */
	
	public void insert(JdbcParameter param) {
		String tableName = param.getTableName();
		Map<String, Object> blobMap = param.getBlobMap();
		Map<String, String> clobMap = param.getClobMap();
		Map<String, Object> paraMap = param.getParaMap();
		jdbcUtil.insert(tableName, paraMap, blobMap, clobMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.core.base.service.BaseJdbcService#update(com.jshx.core.base.entity.JdbcParameter)
	 */
	
	public void update(JdbcParameter param) {
		String tableName = param.getTableName();
		Map<String, Object> blobMap = param.getBlobMap();
		Map<String, String> clobMap = param.getClobMap();
		Map<String, Object> paraMap = param.getParaMap();
		Map<String, Object> queryMap = param.getQueryMap();
		jdbcUtil.update(tableName, paraMap, blobMap, clobMap, queryMap);
	}
}
