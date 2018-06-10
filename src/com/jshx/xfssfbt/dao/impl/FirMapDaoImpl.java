package com.jshx.xfssfbt.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xfssfbt.entity.FirMap;
import com.jshx.xfssfbt.dao.FirMapDao;

@Component("firMapDao")
public class FirMapDaoImpl extends BaseDaoImpl implements FirMapDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findFirMapByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findFirMap(Map<String, Object> paraMap){
		return this.findListByHqlId("findFirMapByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public FirMap getById(String id)
	{
		return (FirMap)this.getObjectById(FirMap.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(FirMap firMap)
	{
		firMap.setId(null);
		this.saveOrUpdateObject(firMap);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(FirMap firMap)
	{
		this.saveOrUpdateObject(firMap);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(FirMap.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		FirMap firMap = (FirMap)this.getObjectById(FirMap.class, id);
		firMap.setDelFlag(1);
		this.saveObject(firMap);
	}
}
