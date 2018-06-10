/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-26        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.base.entity;

import java.util.Map;

/**  
 * JDBC操作时需要传入的参数 
 * 
 * @author   Chenjian
 * @version 创建时间：2011-1-26 上午10:20:40  
 * 
 */
public class JdbcParameter {
	
	/** 表名 */
	private String tableName;
	
	/** 查询条件 */
	private Map<String, Object> queryMap;
	
	/** BLOB字段 */
	private Map<String, Object> blobMap;
	
	/** CLOB字段 */
	private Map<String, String> clobMap;
	
	/** 一般字段 */
	Map<String, Object> paraMap;

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the queryMap
	 */
	public Map<String, Object> getQueryMap() {
		return queryMap;
	}

	/**
	 * @param queryMap the queryMap to set
	 */
	public void setQueryMap(Map<String, Object> queryMap) {
		this.queryMap = queryMap;
	}

	/**
	 * @return the blobMap
	 */
	public Map<String, Object> getBlobMap() {
		return blobMap;
	}

	/**
	 * @param blobMap the blobMap to set
	 */
	public void setBlobMap(Map<String, Object> blobMap) {
		this.blobMap = blobMap;
	}

	/**
	 * @return the clobMap
	 */
	public Map<String, String> getClobMap() {
		return clobMap;
	}

	/**
	 * @param clobMap the clobMap to set
	 */
	public void setClobMap(Map<String, String> clobMap) {
		this.clobMap = clobMap;
	}

	/**
	 * @return the paraMap
	 */
	public Map<String, Object> getParaMap() {
		return paraMap;
	}

	/**
	 * @param paraMap the paraMap to set
	 */
	public void setParaMap(Map<String, Object> paraMap) {
		this.paraMap = paraMap;
	}
}
