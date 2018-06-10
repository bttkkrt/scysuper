package com.jshx.zjjtzsbzyry.dao.impl;

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
import com.jshx.zjjtzsbzyry.entity.Zjjtzsbzyry;
import com.jshx.zjjtzsbzyry.dao.ZjjtzsbzyryDao;

@Component("zjjtzsbzyryDao")
public class ZjjtzsbzyryDaoImpl extends BaseDaoImpl implements ZjjtzsbzyryDao
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
		return this.findPageByHqlId("findZjjtzsbzyryByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZjjtzsbzyry(Map<String, Object> paraMap){
		return this.findListByHqlId("findZjjtzsbzyryByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zjjtzsbzyry getById(String id)
	{
		return (Zjjtzsbzyry)this.getObjectById(Zjjtzsbzyry.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zjjtzsbzyry zjjtzsbzyry)
	{
		zjjtzsbzyry.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zjjtzsbzyry);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zjjtzsbzyry zjjtzsbzyry)
	{
		this.saveOrUpdateObject(zjjtzsbzyry);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zjjtzsbzyry.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zjjtzsbzyry zjjtzsbzyry = (Zjjtzsbzyry)this.getObjectById(Zjjtzsbzyry.class, id);
		zjjtzsbzyry.setDelFlag(1);
		this.saveObject(zjjtzsbzyry);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		this.sqlMapClientTemplate.update("updateAllZjjtzsbzyry");
	}
}
