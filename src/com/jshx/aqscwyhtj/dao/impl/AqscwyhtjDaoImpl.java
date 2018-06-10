package com.jshx.aqscwyhtj.dao.impl;

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
import com.jshx.gmys.entity.GmysBean;
import com.jshx.aqscwyhtj.entity.Aqscwyhtj;
import com.jshx.aqscwyhtj.entity.AqscwyhtjBean;
import com.jshx.aqscwyhtj.dao.AqscwyhtjDao;

@Component("aqscwyhtjDao")
public class AqscwyhtjDaoImpl extends BaseDaoImpl implements AqscwyhtjDao
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
		return this.findPageByHqlId("findAqscwyhtjByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findAqscwyhtj(Map<String, Object> paraMap){
		return this.findListByHqlId("findAqscwyhtjByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscwyhtj getById(String id)
	{
		return (Aqscwyhtj)this.getObjectById(Aqscwyhtj.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Aqscwyhtj aqscwyhtj)
	{
		aqscwyhtj.setId(null);
		this.saveOrUpdateObject(aqscwyhtj);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Aqscwyhtj aqscwyhtj)
	{
		this.saveOrUpdateObject(aqscwyhtj);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Aqscwyhtj.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Aqscwyhtj aqscwyhtj = (Aqscwyhtj)this.getObjectById(Aqscwyhtj.class, id);
		aqscwyhtj.setDelFlag(1);
		this.saveObject(aqscwyhtj);
	}
	
	 public List<AqscwyhtjBean> getAqscwyhtjListByMap(Map map){
		 return sqlMapClientTemplate.queryForList("getAqscwyhtjListByMap",map);
	   }
		
		public AqscwyhtjBean getTotalAqscwyhtjListByMap(Map map){
			return (AqscwyhtjBean)sqlMapClientTemplate.queryForObject("getTotalAqscwyhtjListByMap",map);
		}
}
