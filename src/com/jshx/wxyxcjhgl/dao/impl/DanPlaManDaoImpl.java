package com.jshx.wxyxcjhgl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.wxyxcjhgl.entity.DanPlaMan;
import com.jshx.wxyxcjhgl.dao.DanPlaManDao;

@Component("danPlaManDao")
public class DanPlaManDaoImpl extends BaseDaoImpl implements DanPlaManDao
{
	@Resource(name="sqlMapClientTemplate")
	SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findDanPlaManByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findDanPlaMan(Map<String, Object> paraMap){
		return this.findListByHqlId("findDanPlaManByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public DanPlaMan getById(String id)
	{
		return (DanPlaMan)this.getObjectById(DanPlaMan.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(DanPlaMan danPlaMan)
	{
		danPlaMan.setId(null);
		this.saveOrUpdateObject(danPlaMan);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(DanPlaMan danPlaMan)
	{
		this.saveOrUpdateObject(danPlaMan);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(DanPlaMan.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		DanPlaMan danPlaMan = (DanPlaMan)this.getObjectById(DanPlaMan.class, id);
		danPlaMan.setDelFlag(1);
		this.saveObject(danPlaMan);
	}

	@Override
	public List<DanPlaMan> getAllPlans(Map map) {
		return sqlMapClientTemplate.queryForList("query_all_plans",map);
	}
	
	@Override
	public List<DanPlaMan> getDanPlans(Map map) {
		return sqlMapClientTemplate.queryForList("query_Danplans",map);
	}
}
