package com.jshx.sjzfgpdb.dao.impl;

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
import com.jshx.gwhy.entity.GwhyBean;
import com.jshx.sjzfgpdb.entity.Sjzfgpdb;
import com.jshx.sjzfgpdb.entity.SjzfgpdbBean;
import com.jshx.sjzfgpdb.dao.SjzfgpdbDao;

@Component("sjzfgpdbDao")
public class SjzfgpdbDaoImpl extends BaseDaoImpl implements SjzfgpdbDao
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
		return this.findPageByHqlId("findSjzfgpdbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSjzfgpdb(Map<String, Object> paraMap){
		return this.findListByHqlId("findSjzfgpdbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Sjzfgpdb getById(String id)
	{
		return (Sjzfgpdb)this.getObjectById(Sjzfgpdb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Sjzfgpdb sjzfgpdb)
	{
		sjzfgpdb.setId(null);
		this.saveOrUpdateObject(sjzfgpdb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Sjzfgpdb sjzfgpdb)
	{
		this.saveOrUpdateObject(sjzfgpdb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Sjzfgpdb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Sjzfgpdb sjzfgpdb = (Sjzfgpdb)this.getObjectById(Sjzfgpdb.class, id);
		sjzfgpdb.setDelFlag(1);
		this.saveObject(sjzfgpdb);
	}
	public List<SjzfgpdbBean> getSjzfgpdListByMap(Map map){
		 return sqlMapClientTemplate.queryForList("getSjzfgpdListByMap",map);
	 }
		
	public SjzfgpdbBean getTotalSjzfgpdListByMap(Map map){
			return (SjzfgpdbBean)sqlMapClientTemplate.queryForObject("getTotalSjzfgpdListByMap",map);
	}
}
