package com.wzxx.csgl.dao.impl;

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
import com.wzxx.aqwh.entity.SafCul;
import com.wzxx.csgl.entity.CityMan;
import com.wzxx.csgl.dao.CityManDao;

@Component("cityManDao")
public class CityManDaoImpl extends BaseDaoImpl implements CityManDao
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
		return this.findPageByHqlId("findCityManByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCityMan(Map<String, Object> paraMap){
		return this.findListByHqlId("findCityManByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CityMan getById(String id)
	{
		return (CityMan)this.getObjectById(CityMan.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CityMan cityMan)
	{
		cityMan.setId(null);
		this.saveOrUpdateObject(cityMan);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CityMan cityMan)
	{
		this.saveOrUpdateObject(cityMan);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CityMan.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CityMan cityMan = (CityMan)this.getObjectById(CityMan.class, id);
		cityMan.setDelFlag(1);
		this.saveObject(cityMan);
	}
	/**
	 * 获取城市管理
	 */
	public int findAllInfos(Map<String, Object> paraMap){
		int size = (Integer)sqlMapClientTemplate.queryForObject("findCsglListSize",paraMap);
    	return size;
	}
	
	/**
	 * 获取城市管理列表分页
	 */
	public List<CityMan> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize){
		int s = (totalPageNum-1)*pageSize;
		int l = pageSize;
		List<CityMan> list =sqlMapClientTemplate.queryForList("findCsglList",paraMap,s,l);
		return list;
	}
	
}
