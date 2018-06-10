package com.jshx.zywsxzxk.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.aqscfk.entity.Aqscxzcfglb;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.zywsxzxk.entity.Zywsxzxk;
import com.jshx.zywsxzxk.dao.ZywsxzxkDao;

@Component("zywsxzxkDao")
public class ZywsxzxkDaoImpl extends BaseDaoImpl implements ZywsxzxkDao
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
		return this.findPageByHqlId("findZywsxzxkByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Zywsxzxk> findZywsxzxk(Map<String, Object> paraMap){
		return this.findListByHqlId("findZywsxzxkByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zywsxzxk getById(String id)
	{
		return (Zywsxzxk)this.getObjectById(Zywsxzxk.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zywsxzxk zywsxzxk)
	{
		zywsxzxk.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zywsxzxk);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zywsxzxk zywsxzxk)
	{
		this.saveOrUpdateObject(zywsxzxk);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zywsxzxk.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zywsxzxk zywsxzxk = (Zywsxzxk)this.getObjectById(Zywsxzxk.class, id);
		zywsxzxk.setDelFlag(1);
		this.saveObject(zywsxzxk);
	}

	@Override
	public Aqscxzcfglb getWhpZywsXkzByMap(Map map) {
		// TODO Auto-generated method stub
		return (Aqscxzcfglb)sqlMapClientTemplate.queryForObject("getWhpZywsXkzByMap",map);
	}
}
