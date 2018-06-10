package com.jshx.zjjtzsb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.zjjtzsb.entity.Zjjtzsb;
import com.jshx.zjjtzsb.dao.ZjjtzsbDao;

@Component("zjjtzsbDao")
public class ZjjtzsbDaoImpl extends BaseDaoImpl implements ZjjtzsbDao
{
	@Autowired
	@Qualifier("sqlMapClientTemplate") 
	private SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findZjjtzsbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZjjtzsb(Map<String, Object> paraMap){
		return this.findListByHqlId("findZjjtzsbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zjjtzsb getById(String id)
	{
		return (Zjjtzsb)this.getObjectById(Zjjtzsb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zjjtzsb zjjtzsb)
	{
		zjjtzsb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zjjtzsb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zjjtzsb zjjtzsb)
	{
		this.saveOrUpdateObject(zjjtzsb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zjjtzsb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zjjtzsb zjjtzsb = (Zjjtzsb)this.getObjectById(Zjjtzsb.class, id);
		zjjtzsb.setDelFlag(1);
		this.saveObject(zjjtzsb);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		this.sqlMapClientTemplate.update("updateAllZjjtzsb");
	}
}
