/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * Jan 11, 2011        john.zhang          create
 * Jan 27, 2011        Chenjian          添加saveOrUpdate以及重新设置对象状态等方法
 * ---------------------------------------------------------------
 */

package com.jshx.core.base.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Session;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.core.base.vo.Pagination;

/**
 *  数据访问接口DAO（Data Access Object）,该接口为所有的数据库访问操作提供通用方法
 * 
 * @author john.zhang
 * @version 创建时间：Jan 11, 2011 8:52:42 AM 
 */
@SuppressWarnings("rawtypes")
public interface BaseDao {
	/**
	 * 保存实体类
	 * 
	 * @param object
	 * @return void
	 */
	void saveObject(Object object);

	/**
	 * 保存实体类（BaseModel子类）
	 * 
	 * @param object
	 * @return void
	 */
	void saveBaseModelObject(BaseModel object);

	/**
	 * 保存或更新, 没有主键就执行插入
	 * @param collection
	 * @return void
	 */
	void saveAllOrUpdateAll(Collection collection);

	/**
	 * 更新实体类
	 * 
	 * @param object
	 * @return void
	 */
	void updateObject(Object object);

	/**
	 * 更新实体类（BaseModel子类）
	 * 
	 * @param object
	 * @return void
	 */
	void updateBaseModelObject(BaseModel object);

	/**
	 * 删除实体类
	 * 
	 * @param object
	 * @return void
	 */
	void removeObject(Object object);

	/**
	 * 根据实体类的主键删除记录
	 * 
	 * @param entity 实体类
	 * @param id  主键
	 * @return void
	 */
	void removeObjectById(Class entity, String id);

	/**
	 * 删除集合内的每个对象
	 * 
	 * @param collection
	 * @return void
	 */
	void removeAll(Collection collection);

	/**
	 * 根据sqlid ，与传入的参数查询数据库，返回列表对象
	 * 
	 * @param sqlId
	 * @param paraMap
	 * @return List
	 */
	List findListBySqlId(String sqlId, Map<?, ?> paraMap);
	
	
	/**
	 * 根据sql ，与分页对象查询数据库，返回查询后分页对象
	 */
	public Pagination findPageBySql(final String sql,
			Pagination pagination) ;
	/**
	 * 根据sql ，查询数据库，返回列表对象
	 * 
	 * @param sql
	 * @param paraMap
	 * @return List   
	 */
	public List findListBySql(final String sql);
	/**
	 * 根据hql与分页对象查询数据库，返回查询后分页对象
	 * 
	 * @param hql
	 * @param paraMap
	 * @param pagination
	 * @return Pagination  
	 */
	public Pagination findPageByHql(String hql, 
			Pagination pagination) ;
	
	/**
	 * 根据sqlid查询数据库，取到一定范围内的数据
	 * 
	 * @param sqlId
	 * @param paraMap
	 * @param beginNumber
	 * @param endNumber
	 * @return List
	 */
	List findListBySizeAndSqlId(String sqlId, Map<?, ?> paraMap, int beginNumber,
			int endNumber);
	
	public List findListBySqlIdReturnMap(String sqlId, Map<?, ?> paraMap);
	public List findListBySizeAndSqlIdReturnMap(String sqlId, Map<?, ?> paraMap,int beginNumber,
	        int endNumber);
	public Pagination findPageBySqlIdReturnMap(final String sqlId, final Map<?, ?> paraMap,
	            final Pagination pagination);

	/**
	 * 根据hqlid ，查询数据库，返回列表对象
	 * 
	 * @param hqlId
	 * @param paraMap
	 * @return List
	 */
	List findListByHqlId(String hqlId, Map<?, ?> paraMap);

	/**
	 * 根据hql ，查询数据库，返回列表对象
	 * 
	 * @param hql
	 * @param paraMap
	 * @return List
	 */
	List findListByHql(String hql);

	/**
	 * 根据hqlid查询数据库，取到一定范围内的数据
	 * 
	 * @param hqlId
	 * @param paraMap
	 * @param beginNumber
	 * @param endNumber
	 * @return List
	 */
	List findListBySizeAndHqlId(String hqlId, Map<?, ?> paraMap, int beginNumber,
			int endNumber);

	/**
	 * 根据当前当前的sqlid、参数查询数据库，返回分页对象
	 * 
	 * @param sqlId
	 * @param paraMap
	 * @param pagination
	 * @return Pagination
	 */
	Pagination findPageBySqlId(String sqlId, Map<?, ?> paraMap, Pagination pagination);

	/**
	 * 根据当前当前的hqlid、参数查询数据库，返回分页对象
	 * 
	 * @param hqlId
	 * @param paraMap
	 * @param pagination
	 * @return Pagination
	 */
	Pagination findPageByHqlId(String hqlId, Map<?, ?> paraMap, Pagination pagination);

	/**
	 * 根据实体的类、属性名、属性值查询数据库，返回实体的列表对象
	 * 
	 * @param entity
	 * @param name
	 * @param value
	 * @return List
	 */
	List findListBy(Class entity, String name, Object value);
	
	/**
	 * 根据实体的类查找所有对象
	 * 
	 * @param clazz
	 * @return
	 */
	List getAllObject(Class clazz);  
	
	/**
	 * 根据实体的类、属性名、属性值查询数据库，返回实体对象
	 * 
	 * @param entity
	 * @param name
	 * @param value
	 * @return Object
	 */
	Object getObjectByProperty(Class entity, String name, Object value);

	/**
	 * 通过实体的类与主键返回实体对象
	 * 
	 * @param entity
	 * @param id
	 * @return Object
	 */
	Object getObjectById(Class entity, String id);

	/**
	 * 根据sqlid以及参数执行更新操作
	 * 
	 * @param sqlid 在sql-map中配置的sql语句的id
	 * @param paraMap
	 * @return int :0成功
	 */
	public int executeUpdateBySqlId(String sqlid, Map<?, ?> paraMap);
	
	/**
	 * 根据hqlid以及参数执行更新操作
	 * 
	 * @param hqlid 在sql-map中配置的sql语句的id
	 * @param paraMap
	 * @return int :0成功
	 */
	public int executeUpdateByHqlId(String hqlid, Map<?, ?> paraMap);

	/**
	 * 将session中id相同的两个纪录合并
	 * 
	 * @param object
	 * @return void
	 */
	void merge(Object object);

	/**
	 * 进行清理缓存的操作,执行一系列的SQL语句,但不会提交事务
	 * 
	 * @return void
	 */
	void flush();
	
	/**
	 * 保存或更新对象
	 * 
	 * @param object
	 * @return void   
	 */
	public void saveOrUpdateObject(Object object);
	
	/**
	 * 保存或更新
	 * 
	 * @param entity
	 * @return void  
	 */
	public void saveOrUpdateBaseModelObject(BaseModel entity);
	
	/**
	 * 重新设置对象状态
	 * 
	 * @param obj
	 * @param mode
	 * @return void  
	 */
	public void refresh(Object obj, LockMode mode);
	
	/**
	 * 获得Session
	 * 
	 * @return Session   
	 */
	public Session getSession();

	/**
	 * 根据查询条件查找对象
	 * 
	 * @param clazz
	 * @param paraMap
	 * @return
	 */
	public Object findObjectByFieldsMap(Class clazz, Map<?, ?> paraMap);
	
	/**
	 * 调用存储过程(有返回结果集的存储过程)
	 * 
	 * @param procedure
	 * @param objects
	 * @return
	 */	
	public List callProcedure(final String procedure, final Object... objects);
	
	/**
	 * 调用存储过程(有输出参数的存储过程)
	 * 
	 * @param procedure
	 * @param inParams     存储过程的输入参数，key表示输入参数的位置，value表示输入参数的值
	 * @param outParams    存储过程输出参数，key表示输出参数的位置，value表示输出参数的类型
	 * @return             存储过程输出参数值，key表示输出参数的位置，value表示输出参数的值
	 */
	public Map<Integer, Object> callProcedure(String procedure, Map<Integer, Object> inParams, Map<Integer, Integer> outParams);
}
