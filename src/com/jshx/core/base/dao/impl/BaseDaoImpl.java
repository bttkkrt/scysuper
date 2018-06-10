/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * Jan 11, 2011        john.zhang          create
 * Jan 20, 2011        Chenjian        修改基础DAO类，适应annotation
 * Jan 27, 2011        Chenjian          添加saveOrUpdate以及重新设置对象状态等方法
 * Feb 16, 2012        john.zhang          修改分页查询的总数计算和当前页面查询顺序.删除按列排序
 * Feb 16, 2012        john.zhang          修改分页总数计算时sql截取Bug
 * ---------------------------------------------------------------
 */

package com.jshx.core.base.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

//import javax.annotation.Resource;


import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;
import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.entity.BaseModel;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.StringUtil;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Module;

/**
 *  BaseDao的实现类
 * 
 * @see com.jshx.core.base.dao.BaseDao
 * @author john.zhang
 * @version 创建时间：Jan 11, 2011 10:08:32 AM 
 */
@SuppressWarnings(value={"deprecation", "rawtypes"})
public class BaseDaoImpl implements BaseDao {
	protected final Logger logger;

	public BaseDaoImpl() {
		this.logger = LoggerFactory.getLogger(getClass());
	}

	@Autowired() 
	@Qualifier("sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	//@Autowired() @Qualifier("hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	/**
	 * 获取Spring的HiernateTemplate
	 * 
	 * @return the hibernateTemplate
	 **/
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	
	@Override
	/**
	 * @see com.jshx.core.base.dao.BaseDao#getSession()
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
//		return this.hibernateTemplate.getSessionFactory().getCurrentSession();
	}	

	/**
	 * public void setSqlMapClient(SqlMapClient sqlMapClient) {
	 * this.sqlMapClient = sqlMapClient; }
	 */
	/**
	 * @see com.jshx.core.base.dao.BaseDao#findListBy(Class entity, String name, Object value)
	 */
	@Override
	public List findListBy(final Class entity, final String name, final Object value) {
		Criteria criteria = getSession().createCriteria(entity);
		criteria.add(Restrictions.eq(name, value));
		return criteria.list();		
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#findListByHqlId(String hqlId, Map<?, ?> paraMap)
	 */
	@Override
	public List findListByHqlId(final String hqlId, final Map<?, ?> paraMap){
		
		List restList = null;
		
		String hsql = getSqlStatementById(hqlId, paraMap);
		if (hsql != null) {
			Query query = getSession().createQuery(hsql);
			query.setCacheable(SysPropertiesUtil.getBoolean("use_query_cache", false));
			setQueryParameters(query, paraMap);
			restList = query.list();
		} else {
			throw new BasalException(BasalException.ERROR, "未在 SqlMap配置文件中配置, ID为" + hqlId
					+ "hql 语句");
		}
		return restList;
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#findListBySizeAndHqlId(String hqlId, Map<?, ?> paraMap,int beginNumber, int endNumber)
	 */
	public List findListBySizeAndHqlId(final String hqlId, final Map<?, ?> paraMap,
			final int beginNumber, final int endNumber) {
		
		List list = null;
		try {
			String hql = getSqlStatementById(hqlId, paraMap);
			if (StringUtils.isNotBlank(hql)) {
				Query query = getSession().createQuery(hql);
				query.setCacheable(SysPropertiesUtil.getBoolean("use_query_cache", false));
				setQueryParameters(query, paraMap);
				query.setFirstResult(beginNumber);
				query.setMaxResults(endNumber);
				list = query.list();
			} else {
				throw new BasalException(BasalException.ERROR, "未在 SqlMap配置文件中配置, ID为" + hqlId
						+ "hql 语句");
			}
		} catch (Exception e) {
			throw new BasalException(BasalException.ERROR, e.getCause().getMessage(), e.getCause());
		}
		return list;
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#findListBySizeAndSqlId(String sqlId, Map<?, ?> paraMap, int beginNumber, int endNumber)
	 */
	public List findListBySizeAndSqlId(final String sqlId, final Map<?, ?> paraMap,
			final int beginNumber, final int endNumber) {
		
		List list = null;
		try {
			String hql = getSqlStatementById(sqlId, paraMap);
			if (StringUtils.isNotBlank(hql)) {
				Query query = getSession().createSQLQuery(hql);
				//query.setCacheable(SysPropertiesUtil.getBoolean("use_query_cache", false));
				setQueryParameters(query, paraMap);
				query.setFirstResult(beginNumber);
				query.setMaxResults(endNumber);
				list = query.list();
			} else {
				throw new BasalException(BasalException.ERROR, "未在 SqlMap配置文件中配置, ID为" + sqlId
						+ "hql 语句");
			}
		} catch (Exception e) {
			throw new BasalException(BasalException.ERROR, e.getCause().getMessage(), e.getCause());
		}
		return list;
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#findListBySqlId(String sqlId, Map<?, ?> paraMap)
	 */
	public List findListBySqlId(final String sqlId, final Map<?, ?> paraMap) {
		
		List list = null;
		try {
			String hql = getSqlStatementById(sqlId, paraMap);
			if (StringUtils.isNotBlank(hql)) {
				Query query = getSession().createSQLQuery(hql).addEntity(Module.class);//暂时只留给module类使用，如有需求，可扩展该方法
				//query.setCacheable(SysPropertiesUtil.getBoolean("use_query_cache", false));
				setQueryParameters(query, paraMap);
				list = query.list();
			} else {
				throw new BasalException(BasalException.ERROR, "未在 SqlMap配置文件中配置, ID为" + sqlId
						+ "hql 语句");
			}
		} catch (Exception e) {
			throw new BasalException(BasalException.ERROR, e.getCause().getMessage(), e.getCause());
		}
		return list;
	}
	
	public List findListBySizeAndSqlIdReturnMap(final String sqlId, final Map<?, ?> paraMap,
            final int beginNumber, final int endNumber) {
        
        List list = null;
        try {
            String hql = getSqlStatementById(sqlId, paraMap);
            if (StringUtils.isNotBlank(hql)) {
                Query query = getSession().createSQLQuery(hql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                //query.setCacheable(SysPropertiesUtil.getBoolean("use_query_cache", false));
                setQueryParameters(query, paraMap);
                query.setFirstResult(beginNumber);
                query.setMaxResults(endNumber);
                list = query.list();
            } else {
                throw new BasalException(BasalException.ERROR, "未在 SqlMap配置文件中配置, ID为" + sqlId
                        + "sql 语句");
            }
        } catch (Exception e) {
            throw new BasalException(BasalException.ERROR, e.getCause().getMessage(), e.getCause());
        }
        return list;
    }
	
	public List findListBySqlIdReturnMap(final String sqlId, final Map<?, ?> paraMap) {
        
        List list = null;
        try {
            String hql = getSqlStatementById(sqlId, paraMap);
            if (StringUtils.isNotBlank(hql)) {
                Query query = getSession().createSQLQuery(hql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                //query.setCacheable(SysPropertiesUtil.getBoolean("use_query_cache", false));
                setQueryParameters(query, paraMap);
                list = query.list();
            } else {
                throw new BasalException(BasalException.ERROR, "未在 SqlMap配置文件中配置, ID为" + sqlId
                        + "sql 语句");
            }
        } catch (Exception e) {
            throw new BasalException(BasalException.ERROR, e.getCause().getMessage(), e.getCause());
        }
        return list;
    }
	
	public Pagination findPageBySqlIdReturnMap(final String sqlId, final Map<?, ?> paraMap,
            final Pagination pagination) {
        
        String hsql = getSqlStatementById(sqlId, paraMap);
        String countSql = convertToCountSqlStatement(hsql);
        try {
            // Query the totalCount
            int count = 0;
            if (countSql != null ) {
                Query countQuery = getSession().createSQLQuery(countSql);
                setQueryParameters(countQuery, paraMap);
                count = Integer.parseInt(countQuery.uniqueResult().toString());
                pagination.setTotalCount(count);
            }
            if (hsql != null && 0 != count) {
                // prepare sort info
                StringBuffer buffHSQL = new StringBuffer(hsql);
                // 添加查询条件
                if(pagination.getSortType()!=null && pagination.getSortCriterion()!=null){
                    if(hsql.toLowerCase().contains("order by")){
                        buffHSQL.append(", ").append(pagination.getSortCriterion()).append(" ").append(pagination.getSortType());
                    }else{
                        buffHSQL.append("order by ").append(pagination.getSortCriterion()).append(" ").append(pagination.getSortType());
                    }
                }
                
                // Prepare Query
                Query query = getSession().createSQLQuery(buffHSQL.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                //Query query = getSession().createSQLQuery(buffHSQL.toString());                
                //query.setCacheable(SysPropertiesUtil.getBoolean("use_query_cache", false));
                setQueryParameters(query, paraMap);
                query.setFirstResult(pagination.getFirstResult());
                query.setMaxResults(pagination.getPageSize());
                
                // Execute Query List
                List restList = query.list();
                // set result List info
                pagination.setList(restList);
            } else if (null == hsql) {
                throw new BasalException(BasalException.ERROR, "未在 SqlMap配置文件中配置, ID为" + sqlId
                        + "的hql 语句");
            } else if (0 == count) {
                pagination.setList(new ArrayList());
            }
        } catch (Exception e) {
            throw new BasalException(BasalException.ERROR, e.getCause().getMessage(), e.getCause());
        }
        return pagination;
        
        
    }

	/**
	 * @see com.jshx.core.base.dao.BaseDao#findPageByHqlId(String hqlId, Map<?, ?> paraMap,	Pagination pagination)
	 */
	public Pagination findPageByHqlId(final String hqlId, final Map<?, ?> paraMap,
			final Pagination pagination) {
		
		String hsql = getSqlStatementById(hqlId, paraMap);
		String countSql = convertToCountSqlStatement(hsql);
		try {
			// Query the totalCount
			int count = 0;
			if (countSql != null ) {
				Query countQuery = getSession().createQuery(countSql);
				setQueryParameters(countQuery, paraMap);
				Object obj = countQuery.uniqueResult();
				count = Integer.valueOf(obj.toString());
//				String countTemp = countQuery.uniqueResult().toString();
//				count =  ((Number)countQuery.uniqueResult()).intValue();

				pagination.setTotalCount(count);
			}
			if (null != hsql && 0 != count) {
				// prepare sort info
				StringBuffer buffHSQL = new StringBuffer(hsql);
				// 添加排序条件
				if(pagination.getSortType()!=null && pagination.getSortCriterion()!=null){
					if(hsql.toLowerCase().contains("order by")){
						buffHSQL.append(", ").append(pagination.getSortCriterion()).append(" ").append(pagination.getSortType());
					}else{
						buffHSQL.append("order by ").append(pagination.getSortCriterion()).append(" ").append(pagination.getSortType());
					}
				}
				
				// Prepare Query
				Query query = getSession().createQuery(buffHSQL.toString());
				setQueryParameters(query, paraMap);
				query.setCacheable(SysPropertiesUtil.getBoolean("use_query_cache", false));
				query.setFirstResult(pagination.getFirstResult());
				query.setMaxResults(pagination.getPageSize());
				
				// Execute Query List
				List restList = query.list();
				// set result List info
				pagination.setList(restList);
			} else if (null == hsql) {
				throw new BasalException(BasalException.ERROR, "未在 SqlMap配置文件中配置, ID为" + hqlId
						+ "的hql 语句");
			} else if (0 == count) {
				pagination.setList(new ArrayList());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BasalException(BasalException.ERROR, e.getCause().getMessage(), e.getCause());
		}

		return pagination;
		
		
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#findPageBySqlId(String sqlId, Map<?, ?> paraMap,	Pagination pagination)
	 */
	public Pagination findPageBySqlId(final String sqlId, final Map<?, ?> paraMap,
			final Pagination pagination) {
		
		String hsql = getSqlStatementById(sqlId, paraMap);
		String countSql = convertToCountSqlStatement(hsql);
		try {
			// Query the totalCount
			int count = 0;
			if (countSql != null ) {
				Query countQuery = getSession().createSQLQuery(countSql);
				setQueryParameters(countQuery, paraMap);
				count = Integer.parseInt(countQuery.uniqueResult().toString());
				pagination.setTotalCount(count);
			}
			if (hsql != null && 0 != count) {
				// prepare sort info
				StringBuffer buffHSQL = new StringBuffer(hsql);
				// 添加查询条件
				if(pagination.getSortType()!=null && pagination.getSortCriterion()!=null){
					if(hsql.toLowerCase().contains("order by")){
						buffHSQL.append(", ").append(pagination.getSortCriterion()).append(" ").append(pagination.getSortType());
					}else{
						buffHSQL.append("order by ").append(pagination.getSortCriterion()).append(" ").append(pagination.getSortType());
					}
				}
				
				// Prepare Query
				Query query = getSession().createSQLQuery(buffHSQL.toString());
				//query.setCacheable(SysPropertiesUtil.getBoolean("use_query_cache", false));
				setQueryParameters(query, paraMap);
				query.setFirstResult(pagination.getFirstResult());
				query.setMaxResults(pagination.getPageSize());
				
				// Execute Query List
				List restList = query.list();
				// set result List info
				pagination.setList(restList);
			} else if (null == hsql) {
				throw new BasalException(BasalException.ERROR, "未在 SqlMap配置文件中配置, ID为" + sqlId
						+ "的hql 语句");
			} else if (0 == count) {
				pagination.setList(new ArrayList());
			}
		} catch (Exception e) {
			throw new BasalException(BasalException.ERROR, e.getCause().getMessage(), e.getCause());
		}
		return pagination;
		
		
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#flush()
	 */
	public void flush() {
		getSession().flush();
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#getObjectById(Class entity, String id)
	 */
	public Object getObjectById(Class entity, String id) {
		return getSession().get(entity, id);
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#getObjectByProperty(Class entity, String name, Object value)
	 */
	public Object getObjectByProperty(final Class entity, final String name, final Object value) {
		
		Criteria criteria = getSession().createCriteria(entity);
		criteria.add(Restrictions.eq(name, value));
		criteria.setMaxResults(1);
		return criteria.uniqueResult();	
		
		
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#merge(Object object)
	 */
	public void merge(Object object) {
		getSession().merge(object);
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#removeAll(Collection collection)
	 */
	public void removeAll(Collection collection) {
		for(Object entity: collection)
			getSession().delete(entity);
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#removeObject(Object object)
	 */
	public void removeObject(Object object) {
		getSession().delete(object);
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#removeObjectById(Class entity, String id)
	 */
	public void removeObjectById(Class entity, String id) {
		getSession().delete(getObjectById(entity, id));
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#refresh(Object obj, LockMode mode)
	 */
	public void refresh(Object obj, LockMode mode) {
		this.getSession().refresh(obj, mode);
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#saveAllOrUpdateAll(Collection collection)
	 */
	public void saveAllOrUpdateAll(Collection collection) {
		for(Object entity: collection)
			getSession().saveOrUpdate(entity);
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#saveBaseModelObject(BaseModel entity)
	 */
	public void saveBaseModelObject(BaseModel entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#saveOrUpdateBaseModelObject(BaseModel entity)
	 */
	public void saveOrUpdateBaseModelObject(BaseModel entity) {
		if (StringUtil.isNotNull(entity.getId())) {
			getSession().update(entity);
		} else {
			getSession().save(entity);
		}
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#saveOrUpdateObject(Object object)
	 */
	public void saveOrUpdateObject(Object object) {
		getSession().saveOrUpdate(object);
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#saveObject(Object object)
	 */
	public void saveObject(Object object) {
		if (object instanceof BaseModel) {
			if (StringUtil.isNotNullAndNotEmpty(((BaseModel) object).getId())) {
				getSession().update(object);
			} else {
				getSession().save(object);
			}
		} else {
			getSession().saveOrUpdate(object);
		}
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#updateBaseModelObject(BaseModel entity)
	 */
	public void updateBaseModelObject(BaseModel entity) {
		getSession().update(entity);
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#updateObject(BaseModel entity)
	 */
	public void updateObject(Object object) {
		getSession().update(object);

	}

	/**
	 * 设置查询条件
	 * 
	 * @param query
	 * @param paraMap
	 */
	protected void setQueryParameters(Query query, Map paraMap) {
		if (query != null && paraMap != null && !paraMap.isEmpty()) {
			List namedParms = Arrays.asList(query.getNamedParameters());
			Iterator iter = paraMap.keySet().iterator();
			while (iter.hasNext()) {
				String paraName = (String) iter.next();
				if (namedParms.contains(paraName)) {
					Object value = paraMap.get(paraName);
					if (value instanceof List) {
						query.setParameterList(paraName, (List) value);
					} else {
						query.setParameter(paraName, value);
					}
				}
			}
		}
	}

	/**
	 * 将查询语句转为计数语句
	 * 
	 * @param selectSQL
	 * @return
	 */
	protected String convertToCountSqlStatement(String selectSQL) {
		if (!StringUtil.isNull(selectSQL)) {
			String patternStr = "\\sfrom\\s";
			Pattern pattern = Pattern.compile(patternStr);
			String[] splitArr = pattern.split(selectSQL.toLowerCase());
			if (splitArr != null && splitArr.length > 0) {
				int indexOfFrom = splitArr[0].length();
				int indexOfOderBy = selectSQL.toLowerCase().indexOf(" order ");
				if (indexOfOderBy >= 0) {
					return convertCountSql("select count(*) " + selectSQL.substring(indexOfFrom, indexOfOderBy));
				} else {
					return convertCountSql("select count(*) " + selectSQL.substring(indexOfFrom));
				}
			}
		}
		return null;
	}

	/**
	 * sql语句的括号匹配
	 * 
	 * @param sql
	 * @return
	 */
	protected String  convertCountSql(String sql){
		String tempSql=sql;
        int leftBrackets = tempSql.length()-tempSql.replace("(", "").length();
        int rightBrackets = tempSql.length()-tempSql.replace(")", "").length();
		if(leftBrackets>rightBrackets){
			String temp="";
			for(int i=0;i<(leftBrackets-rightBrackets);i++){
				temp=temp+")";
			}
			sql=sql+temp;
		}
		return sql;
	}

	/**
	 * 根据sql-map配置的sql语句id查找sql语句
	 * 
	 * @param sqlId
	 * @param paramObject
	 * @return
	 */
	protected String getSqlStatementById(String sqlId, Object paramObject) {
		if (sqlMapClient == null) {
			logger.error("IBATIS sqlMapClient 未设置!");
			throw new RuntimeException("IBATIS sqlMapClient 未设置!");
		}
		String sql = null;
		ExtendedSqlMapClient extendedSqlMapClient = (ExtendedSqlMapClient) sqlMapClient;
		MappedStatement mappedStatement = extendedSqlMapClient
				.getMappedStatement(sqlId);
		if (mappedStatement != null) {
			SessionScope sessionScope = new SessionScope();
			sessionScope.incrementRequestStackDepth();
			StatementScope statementScope = new StatementScope(sessionScope);
			statementScope.setStatement(mappedStatement);
			sql = mappedStatement.getSql().getSql(statementScope, paramObject);
		}
		return sql;
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#executeUpdateBySqlId(String sqlid, Map paraMap)
	 */
	@Override
	public int executeUpdateBySqlId(final String sqlid, final Map<?, ?> paraMap) {
		
		String hsql = getSqlStatementById(sqlid, paraMap);
		if (hsql != null) {
			// Prepare Query
			Query query = getSession().createSQLQuery(hsql);
			setQueryParameters(query, paraMap);

			// Execute Query List
			return query.executeUpdate();
		}
		return 0;
		
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#executeUpdateByHqlId(String hqlid, Map paraMap)
	 */
	@Override
	public int executeUpdateByHqlId(final String hqlid, final Map<?, ?> paraMap) {	
		
		String hsql = getSqlStatementById(hqlid, paraMap);
		if (hsql != null) {
			// Prepare Query
			Query query = getSession().createQuery(hsql);
			setQueryParameters(query, paraMap);

			// Execute Query List
			return query.executeUpdate();
		}
		return 0;
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#findPageBySql(final String sql, Pagination pagination)
	 */
	public Pagination findPageBySql(final String sql, final Pagination pagination) {
		
		
		String countSql = convertToCountSqlStatement(sql);

		if (sql != null) {
			// prepare sort info
			StringBuffer buffHSQL = new StringBuffer(sql);
			
			// 添加排序条件
			if(pagination.getSortType()!=null && pagination.getSortCriterion()!=null){
				if(sql.toLowerCase().contains("order by")){
					buffHSQL.append(", ").append(pagination.getSortCriterion()).append(" ").append(pagination.getSortType());
				}else{
					buffHSQL.append("order by ").append(pagination.getSortCriterion()).append(" ").append(pagination.getSortType());
				}
			}

			// Prepare Query
			Query query = getSession().createSQLQuery(buffHSQL.toString());
			// setQueryParameters(query, paraMap);
			query.setFirstResult(pagination.getFirstResult());
			query.setMaxResults(pagination.getPageSize());
			//query.setCacheable(SysPropertiesUtil.getBoolean("use_query_cache", false));
			// Execute Query List
			List restList = query.list();

			// set result List info
			pagination.setList(restList);

			// Query the totalCount
			if (countSql != null ) {
				Query countQuery = getSession().createSQLQuery(countSql);
				// setQueryParameters(countQuery, paraMap);
				int count = Integer.parseInt(countQuery.uniqueResult()
						.toString());
				pagination.setTotalCount(count);
			}
		}

		return pagination;
		
		
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#findListBySql(final String sql)
	 */
	public List findListBySql(final String sql) {
		
		List restList = null;
		if (sql != null) {
			// Prepare Query
			Query query = getSession().createSQLQuery(sql);
			//query.setCacheable(SysPropertiesUtil.getBoolean("use_query_cache", false));
			// setQueryParameters(query, paraMap);

			// Execute Query List
			restList = query.list();
		}
		return restList;	
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#findPageByHql(String hql, Pagination pagination)
	 */
	public Pagination findPageByHql(final String hql, final Pagination pagination) {
		
		// String hsql = getSqlStatementById(hqlId, paraMap);
		String countSql = convertToCountSqlStatement(hql);
		try {
			if (hql != null) {
				// prepare sort info
				StringBuffer buffHSQL = new StringBuffer(hql);
				// 添加排序条件
				if(pagination.getSortType()!=null && pagination.getSortCriterion()!=null){
					if(hql.toLowerCase().contains("order by")){
						buffHSQL.append(", ").append(pagination.getSortCriterion()).append(" ").append(pagination.getSortType());
					}else{
						buffHSQL.append("order by ").append(pagination.getSortCriterion()).append(" ").append(pagination.getSortType());
					}
				}

				// Prepare Query
				Query query = getSession().createQuery(buffHSQL.toString());
				// setQueryParameters(query, paraMap);
				query.setFirstResult(pagination.getFirstResult());
				query.setMaxResults(pagination.getPageSize());
				query.setCacheable(SysPropertiesUtil.getBoolean("use_query_cache", false));
				// Execute Query List
				List restList = query.list();

				// set result List info
				pagination.setList(restList);

				// Query the totalCount
				if (countSql != null ) {
					Query countQuery = getSession().createQuery(countSql);
					// setQueryParameters(countQuery, paraMap);
					int count = Integer.parseInt(countQuery.uniqueResult()
							.toString());
					pagination.setTotalCount(count);
				}
			} else {
				throw new BasalException(BasalException.ERROR, "请传入hql语句！");
			}
		} catch (Exception e) {
			throw new BasalException(BasalException.ERROR, e.getCause().getMessage(), e.getCause());
		}

		return pagination;
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#findListByHql(String hql)
	 */
	public List findListByHql(final String hql) {
		
		List restList = null;
		try {
			if (hql != null) {
				// Prepare Query
				Query query = getSession().createQuery(hql);
				// setQueryParameters(query, paraMap);
				query.setCacheable(SysPropertiesUtil.getBoolean("use_query_cache", false));
				// Execute Query List
				restList = query.list();
			}
		} catch (Exception e) {
			throw new BasalException(BasalException.ERROR, e.getCause().getMessage(), e.getCause());
		}
		return restList;
		
		
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#findObjectByFieldsMap(Class clazz, Map paraMap)
	 */
	@Override
	public Object findObjectByFieldsMap(final Class clazz, final Map<?, ?> paraMap) {
		
		Criteria criteria = getSession().createCriteria(clazz);
		for (Iterator it = paraMap.keySet().iterator(); it.hasNext();) {
			String key = it.next().toString();
			criteria.add(Restrictions.eq(key, paraMap.get(key)));
		}
		criteria.setMaxResults(1);
		return criteria.uniqueResult();
		
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#getAllObject(Class clazz)
	 */
	public List getAllObject(Class clazz) {
		return getSession().createCriteria(clazz).list();
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#callProcedure(final String procedureName, final Object... objects)
	 */
	public List callProcedure(final String procedureName,
			final Object... objects) {
		try {
			SQLQuery query = getSession().createSQLQuery(procedureName);
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					query.setParameter(i, objects[i]);
				}
			}
			return query.list();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @see com.jshx.core.base.dao.BaseDao#callProcedure(String procedure, Map<Integer, Object> inParams, Map<Integer, Integer> outParams)
	 */
	public Map<Integer, Object> callProcedure(String procedure,
			Map<Integer, Object> inParams, Map<Integer, Integer> outParams) {
		Map<Integer, Object> resultMap = new HashMap<Integer, Object>();
		Connection con = null;
		try {
			con = SessionFactoryUtils.getDataSource(getSession().getSessionFactory()).getConnection();
			CallableStatement cStatement = con.prepareCall(procedure);
			// 设置输入参数
			if (inParams != null) {
				Iterator<Integer> keyIt = inParams.keySet().iterator();
				while (keyIt.hasNext()) {
					Integer pos = keyIt.next();
					cStatement.setObject(pos, inParams.get(pos));
				}
			}
			// 设置输出参数
			if (outParams != null) {
				Iterator<Integer> keyIt = outParams.keySet().iterator();
				while (keyIt.hasNext()) {
					Integer pos = keyIt.next();
					cStatement.registerOutParameter(pos, outParams.get(pos));
				}
			}
			cStatement.executeUpdate();
			// 获得输出参数
			if (outParams != null) {
				Iterator<Integer> keyIt = outParams.keySet().iterator();
				while (keyIt.hasNext()) {
					Integer pos = keyIt.next();
					resultMap.put(pos, cStatement.getObject(pos));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				con = null;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return resultMap;
	}

}
