package com.jshx.xzcfgzs.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xzcfgzs.entity.PenaltyNotice;
import com.jshx.xzcfgzs.dao.PenaltyNoticeDao;

@Component("penaltyNoticeDao")
public class PenaltyNoticeDaoImpl extends BaseDaoImpl implements PenaltyNoticeDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPenaltyNoticeByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<PenaltyNotice> findPenaltyNotice(Map<String, Object> paraMap){
		return this.findListByHqlId("findPenaltyNoticeByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PenaltyNotice getById(String id)
	{
		return (PenaltyNotice)this.getObjectById(PenaltyNotice.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PenaltyNotice penaltyNotice)
	{
		penaltyNotice.setId(null);
		this.saveOrUpdateObject(penaltyNotice);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PenaltyNotice penaltyNotice)
	{
		this.saveOrUpdateObject(penaltyNotice);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PenaltyNotice.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PenaltyNotice penaltyNotice = (PenaltyNotice)this.getObjectById(PenaltyNotice.class, id);
		penaltyNotice.setDelFlag(1);
		this.saveObject(penaltyNotice);
	}
}
