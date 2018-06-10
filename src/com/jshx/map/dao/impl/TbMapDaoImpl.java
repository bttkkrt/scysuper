package com.jshx.map.dao.impl;

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
import com.jshx.map.entity.TbMap;
import com.jshx.map.dao.TbMapDao;

@Component("tbMapDao")
public class TbMapDaoImpl extends BaseDaoImpl implements TbMapDao
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
		return this.findPageByHqlId("findTbMapByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findTbMap(Map<String, Object> paraMap){
		return this.findListByHqlId("findTbMapByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public TbMap getById(String id)
	{
		return (TbMap)this.getObjectById(TbMap.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(TbMap tbMap)
	{
		tbMap.setId(null);
		this.saveOrUpdateObject(tbMap);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(TbMap tbMap)
	{
		this.saveOrUpdateObject(tbMap);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(TbMap.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		TbMap tbMap = (TbMap)this.getObjectById(TbMap.class, id);
		tbMap.setDelFlag(1);
		this.saveObject(tbMap);
	}

	@Override
	public Map getMapDetailByMap(String sqlID, Map map) {
		return (Map) this.sqlMapClientTemplate.queryForObject(sqlID, map);
	}

	@Override
	public List<Map> queryMapListByMap(String sqlID, Map map) {
		return this.sqlMapClientTemplate.queryForList(sqlID,map);
	}
}
