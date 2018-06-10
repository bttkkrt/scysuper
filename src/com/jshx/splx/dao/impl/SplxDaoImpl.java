package com.jshx.splx.dao.impl;

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
import com.jshx.splx.entity.Splx;
import com.jshx.splx.dao.SplxDao;

@Component("splxDao")
public class SplxDaoImpl extends BaseDaoImpl implements SplxDao
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
		return this.findPageByHqlId("findSplxByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSplx(Map<String, Object> paraMap){
		return this.findListByHqlId("findSplxByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Splx getById(String id)
	{
		return (Splx)this.getObjectById(Splx.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Splx splx)
	{
		splx.setId(null);
		this.saveOrUpdateObject(splx);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Splx splx)
	{
		this.saveOrUpdateObject(splx);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Splx.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Splx splx = (Splx)this.getObjectById(Splx.class, id);
		splx.setDelFlag(1);
		this.saveObject(splx);
	}
	public List<Splx> findSplxList(Map map){
		 return sqlMapClientTemplate.queryForList("findSplxList",map);
	}

	@Override
	public void deleteSplxByMap(Map map) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.delete("deleteSplxByMap",map);
	}
}
