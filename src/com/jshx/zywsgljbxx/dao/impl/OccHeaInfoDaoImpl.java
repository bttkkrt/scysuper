package com.jshx.zywsgljbxx.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zywsgljbxx.entity.OccHeaInfo;
import com.jshx.zywsgljbxx.dao.OccHeaInfoDao;

@Component("occHeaInfoDao")
public class OccHeaInfoDaoImpl extends BaseDaoImpl implements OccHeaInfoDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findOccHeaInfoByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findOccHeaInfo(Map<String, Object> paraMap){
		return this.findListByHqlId("findOccHeaInfoByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public OccHeaInfo getById(String id)
	{
		return (OccHeaInfo)this.getObjectById(OccHeaInfo.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(OccHeaInfo occHeaInfo)
	{
		occHeaInfo.setId(null);
		this.saveOrUpdateObject(occHeaInfo);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(OccHeaInfo occHeaInfo)
	{
		this.saveOrUpdateObject(occHeaInfo);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(OccHeaInfo.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		OccHeaInfo occHeaInfo = (OccHeaInfo)this.getObjectById(OccHeaInfo.class, id);
		occHeaInfo.setDelFlag(1);
		this.saveObject(occHeaInfo);
	}
	
}
