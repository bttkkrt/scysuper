package com.jshx.regulationsLevel.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.regulationsLevel.entity.RegulationsLevel;
import com.jshx.regulationsLevel.dao.RegulationsLevelDao;

@Component("regulationsLevelDao")
public class RegulationsLevelDaoImpl extends BaseDaoImpl implements RegulationsLevelDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findRegulationsLevelByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findRegulationsLevel(String sql,Map<String, Object> paraMap){
		return this.findListByHqlId(sql, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public RegulationsLevel getById(String id)
	{
		return (RegulationsLevel)this.getObjectById(RegulationsLevel.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(RegulationsLevel regulationsLevel)
	{
		regulationsLevel.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(regulationsLevel);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(RegulationsLevel regulationsLevel)
	{
		this.saveOrUpdateObject(regulationsLevel);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(RegulationsLevel.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		RegulationsLevel regulationsLevel = (RegulationsLevel)this.getObjectById(RegulationsLevel.class, id);
		regulationsLevel.setDelFlag(1);
		this.saveObject(regulationsLevel);
	}
}
