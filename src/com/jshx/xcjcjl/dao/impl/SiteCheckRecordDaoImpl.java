package com.jshx.xcjcjl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xcjcjl.entity.SiteCheckRecord;
import com.jshx.xcjcjl.dao.SiteCheckRecordDao;

@Component("siteCheckRecordDao")
public class SiteCheckRecordDaoImpl extends BaseDaoImpl implements SiteCheckRecordDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSiteCheckRecordByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<SiteCheckRecord> findSiteCheckRecord(Map<String, Object> paraMap){
		return this.findListByHqlId("findSiteCheckRecordByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SiteCheckRecord getById(String id)
	{
		return (SiteCheckRecord)this.getObjectById(SiteCheckRecord.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SiteCheckRecord siteCheckRecord)
	{
		siteCheckRecord.setId(null);
		this.saveOrUpdateObject(siteCheckRecord);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SiteCheckRecord siteCheckRecord)
	{
		this.saveOrUpdateObject(siteCheckRecord);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SiteCheckRecord.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SiteCheckRecord siteCheckRecord = (SiteCheckRecord)this.getObjectById(SiteCheckRecord.class, id);
		siteCheckRecord.setDelFlag(1);
		this.saveObject(siteCheckRecord);
	}
}
