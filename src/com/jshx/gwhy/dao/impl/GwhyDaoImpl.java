package com.jshx.gwhy.dao.impl;

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
import com.jshx.gwhy.entity.Gwhy;
import com.jshx.gwhy.entity.GwhyBean;
import com.jshx.gwhy.dao.GwhyDao;

@Component("gwhyDao")
public class GwhyDaoImpl extends BaseDaoImpl implements GwhyDao
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
		return this.findPageByHqlId("findGwhyByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findGwhy(Map<String, Object> paraMap){
		return this.findListByHqlId("findGwhyByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Gwhy getById(String id)
	{
		return (Gwhy)this.getObjectById(Gwhy.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Gwhy gwhy)
	{
		gwhy.setId(null);
		this.saveOrUpdateObject(gwhy);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Gwhy gwhy)
	{
		this.saveOrUpdateObject(gwhy);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Gwhy.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Gwhy gwhy = (Gwhy)this.getObjectById(Gwhy.class, id);
		gwhy.setDelFlag(1);
		this.saveObject(gwhy);
	}
	 public List<GwhyBean> getGwhyListByMap(Map map){
		 return sqlMapClientTemplate.queryForList("getGwhyListByMap",map);
	   }
		
	public  GwhyBean getTotalGwhyListByMap(Map map){
		return (GwhyBean)sqlMapClientTemplate.queryForObject("getTotalGwhyListByMap",map);
	}
}
