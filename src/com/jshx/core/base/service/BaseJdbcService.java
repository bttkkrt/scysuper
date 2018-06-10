/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-26        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.base.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jshx.core.base.entity.JdbcParameter;
import com.jshx.core.base.vo.Pagination;

/** 
 * 封装基础的JDBC操作 
 *  
 * @author   Chenjian
 * @version 创建时间：2011-1-26 上午10:06:13  
 * 
 */
public interface BaseJdbcService {
	
	/**
	 * 根据iBatis配置翻页查询
	 * 
	 * @param sqlId         配置在iBatis中的sql语句ID
	 * @param page          页面对象
	 * @param paraMap       传入参数Map必须为LinkedHashMap，其插入顺序应与iBatis配置文件中的SQL查询条件顺序一致
	 * @return Pagination   
	 */
	public Pagination findPage(String sqlId, final Pagination page,
			LinkedHashMap<String, Object> paraMap);
	
	/**
	 * 根据iBatis配置的SQL查找指定位置的记录集，返回为字段与字段值一一对应的Map列表
	 * 
	 * @param sqlId            配置在iBatis中的sql语句ID
	 * @param startRow         数据开始记录数
	 * @param pageSize         查询出的记录数
	 * @param paraMap          传入参数Map必须为LinkedHashMap，其插入顺序应与iBatis配置文件中的SQL查询条件顺序一致
	 * @return List<Map<String,Object>>   
	 */
	public List<Map<String, Object>> findListBySize(String sqlId,
			final int startRow, final int pageSize, LinkedHashMap<String, Object> paraMap);
	
	/**
	 * 根据iBatis配置的SQL查找记录集，返回为字段与字段值一一对应的Map列表
	 * 
	 * @param sqlId    配置在iBatis中的sql语句ID
	 * @param paraMap  传入参数Map必须为LinkedHashMap，其插入顺序应与iBatis配置文件中的SQL查询条件顺序一致
	 * @return List<Map<String,Object>>   
	 */
	public List<Map<String, Object>> findList(String sqlId, LinkedHashMap<String, Object> paraMap);
	
	/**
	 * 根据iBatis配置的SQL查找单条记录，并返回字段名与字段值相对应的Map对象
	 * 
	 * @param sqlId    配置在iBatis中的sql语句ID
	 * @param paraMap  传入参数Map必须为LinkedHashMap，其插入顺序应与iBatis配置文件中的SQL查询条件顺序一致
	* @return Map<String,Object>   
	 */
	public Map<String, Object> find(String sqlId, LinkedHashMap<String, Object> paraMap);
	
	/**
	 * 根据SQL配置翻页查询
	 * 
	 * @param querySql   查询的sql语句
	 * @param page       页面对象
	 * @param params     查询参数
	 * @return Pagination   
	 */
	public Pagination findPageBySql(String querySql, final Pagination page,
			Object... params);
	
	/**
	 * 根据SQL查找指定位置的记录集，返回为字段与字段值一一对应的Map列表
	 * 
	 * @param querySql   查询语句
	 * @param startRow   数据记录开始数
	 * @param pageSize   查询出的记录数
	 * @param params     查询条件
	 * @return List<Map<String,Object>>   
	 */
	public List<Map<String, Object>> findListBySizeAndSql(String querySql,
			final int startRow, final int pageSize, Object... params);
	
	/**
	 * 根据SQL查找记录集，返回为字段与字段值一一对应的Map列表
	 * 
	 * @param querySql  查询语句
	 * @param params    查询参数
	 * @return List<Map<String,Object>>   
	 */
	public List<Map<String, Object>> findListBySql(String querySql,
			Object... params);
	
	/**
	 * 根据SQL查找单条记录，并返回字段名与字段值相对应的Map对象
	 * 
	 * @param querySql  查询语句
	 * @param params    查询参数
	 * @return Map<String,Object>
	 */
	public Map<String, Object> findBySql(String querySql, Object... params);
	
	/**
	 * 批处理SQL
	 * 
	 * @param sql         需要执行的sql语句
	 * @param paramList   sql语句的参数
	 * @return int[]      执行结果
	 */
	public int[] batchExecute(String sql, final List<Object[]> paramList);
	
	/**
	 * 获得表的主键名
	 * 
	 * @param tableName      表名
	 * @return List<String>  主键值
	 */
	public List<String> getPrimaryKeys(String tableName);
	
	/**
	 * 获得数据表的字段名
	 * 
	 * @param tableName      表名
	 * @return List<String>  字段名
	 */
	public List<String> getColumnNames(String tableName);
	
	/**
	 * 获得数据表的字段以及字段类型
	 * 
	 * @param tableName  表名
	 * @return Map<String,String> key为字段名，value为字段类型
	 */
	public Map<String, String> getColumnTypes(String tableName);
	
	/**
	 * 批量插入记录
	 * 
	 * @param paramList  插入记录的参数
	 * @return void 
	 */
	public void batchInsert(List<JdbcParameter> paramList);
	
	/**
	 * 批量更新记录
	 * 
	 * @param paramList 更新记录的参数
	 * @return void  
	 */
	public void batchUpdate(List<JdbcParameter> paramList);
	
	/**
	 * 批量删除记录
	 * 
	 * @param paramList 删除记录的参数
	 * @return void
	 */
	public void batchDelete(List<JdbcParameter> paramList);
	
	/**
	 * 执行SQL
	 *  
	 * @param sql     需要执行的sql语句
	 * @param params  参数
	 * @return int    执行结果
	 */
	public int executeSql(String sql, final Object[] params);
	
	/**
	 * 插入一条记录
	 * 
	 * @param param  插入记录的参数
	 * @return void 
	 */
	public void insert(JdbcParameter param);
	
	/**
	 * 修改一条记录
	 *  
	 * @param param  更新记录的参数
	 * @return void 
	 */
	public void update(JdbcParameter param);
	
	/**
	 * 删除一条记录
	 * 
	 * @param param  删除记录的参数
	 * @return void 
	 */
	public void delete(JdbcParameter param);
}
