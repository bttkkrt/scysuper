package com.jshx.aqscpxqk.dao.impl;

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
import com.jshx.aqscpxqk.entity.Aqscpxqk;
import com.jshx.aqscpxqk.entity.AqscpxqkBean;
import com.jshx.aqscpxqk.dao.AqscpxqkDao;

@Component("aqscpxqkDao")
public class AqscpxqkDaoImpl extends BaseDaoImpl implements AqscpxqkDao
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
		return this.findPageByHqlId("findAqscpxqkByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findAqscpxqk(Map<String, Object> paraMap){
		return this.findListByHqlId("findAqscpxqkByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscpxqk getById(String id)
	{
		return (Aqscpxqk)this.getObjectById(Aqscpxqk.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Aqscpxqk aqscpxqk)
	{
		aqscpxqk.setId(null);
		this.saveOrUpdateObject(aqscpxqk);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Aqscpxqk aqscpxqk)
	{
		this.saveOrUpdateObject(aqscpxqk);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Aqscpxqk.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Aqscpxqk aqscpxqk = (Aqscpxqk)this.getObjectById(Aqscpxqk.class, id);
		aqscpxqk.setDelFlag(1);
		this.saveObject(aqscpxqk);
	}
	public List<AqscpxqkBean> getAqscpxqkListByMap(Map map){
		return sqlMapClientTemplate.queryForList("getAqscpxqkListByMap",map);
	}
	
	public AqscpxqkBean getTotalAqscpxqkListByMap(Map map){
		return (AqscpxqkBean)sqlMapClientTemplate.queryForObject("getTotalAqscpxqkListByMap",map);
	}
	
}
