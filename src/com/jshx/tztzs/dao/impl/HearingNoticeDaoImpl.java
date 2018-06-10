package com.jshx.tztzs.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.tztzs.entity.HearingNotice;
import com.jshx.tztzs.dao.HearingNoticeDao;

@Component("hearingNoticeDao")
public class HearingNoticeDaoImpl extends BaseDaoImpl implements HearingNoticeDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findHearingNoticeByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<HearingNotice> findHearingNotice(Map<String, Object> paraMap){
		return this.findListByHqlId("findHearingNoticeByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public HearingNotice getById(String id)
	{
		return (HearingNotice)this.getObjectById(HearingNotice.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(HearingNotice hearingNotice)
	{
		hearingNotice.setId(null);
		this.saveOrUpdateObject(hearingNotice);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(HearingNotice hearingNotice)
	{
		this.saveOrUpdateObject(hearingNotice);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(HearingNotice.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		HearingNotice hearingNotice = (HearingNotice)this.getObjectById(HearingNotice.class, id);
		hearingNotice.setDelFlag(1);
		this.saveObject(hearingNotice);
	}
}
