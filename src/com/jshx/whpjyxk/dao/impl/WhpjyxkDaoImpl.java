package com.jshx.whpjyxk.dao.impl;

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
import com.jshx.whpjyxk.entity.Whpjyxk;
import com.jshx.whpjyxk.dao.WhpjyxkDao;

@Component("whpjyxkDao")
public class WhpjyxkDaoImpl extends BaseDaoImpl implements WhpjyxkDao
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
		return this.findPageByHqlId("findWhpjyxkByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findWhpjyxk(Map<String, Object> paraMap){
		return this.findListByHqlId("findWhpjyxkByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Whpjyxk getById(String id)
	{
		return (Whpjyxk)this.getObjectById(Whpjyxk.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Whpjyxk whpjyxk)
	{
		whpjyxk.setId(null);
		this.saveOrUpdateObject(whpjyxk);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Whpjyxk whpjyxk)
	{
		this.saveOrUpdateObject(whpjyxk);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Whpjyxk.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Whpjyxk whpjyxk = (Whpjyxk)this.getObjectById(Whpjyxk.class, id);
		whpjyxk.setDelFlag(1);
		this.saveObject(whpjyxk);
	}
	public List<Whpjyxk> getWhpjyxkListByMap(Map map){
		return sqlMapClientTemplate.queryForList("getWhpjyxkListByMap",map);
	}
	
}
