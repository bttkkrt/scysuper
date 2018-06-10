package com.jshx.gmys.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.aqscjchzxzz.entity.AqscjchzxzzBean;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.gmys.entity.Gmys;
import com.jshx.gmys.entity.GmysBean;
import com.jshx.gmys.dao.GmysDao;

@Component("gmysDao")
public class GmysDaoImpl extends BaseDaoImpl implements GmysDao
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
		return this.findPageByHqlId("findGmysByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findGmys(Map<String, Object> paraMap){
		return this.findListByHqlId("findGmysByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Gmys getById(String id)
	{
		return (Gmys)this.getObjectById(Gmys.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Gmys gmys)
	{
		gmys.setId(null);
		this.saveOrUpdateObject(gmys);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Gmys gmys)
	{
		this.saveOrUpdateObject(gmys);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Gmys.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Gmys gmys = (Gmys)this.getObjectById(Gmys.class, id);
		gmys.setDelFlag(1);
		this.saveObject(gmys);
	}
	 public List<GmysBean> getGmysListByMap(Map map){
		 return sqlMapClientTemplate.queryForList("getGmysListByMap",map);
	    }
		
	public GmysBean getTotalGmysListByMap(Map map){
		return (GmysBean)sqlMapClientTemplate.queryForObject("getTotalGmysListByMap",map);
	}
}
