package com.jshx.xxdjbczjtzs.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xxdjbczjtzs.entity.NoticeEvidence;
import com.jshx.xxdjbczjtzs.dao.NoticeEvidenceDao;

@Component("noticeEvidenceDao")
public class NoticeEvidenceDaoImpl extends BaseDaoImpl implements NoticeEvidenceDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findNoticeEvidenceByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<NoticeEvidence> findNoticeEvidence(Map<String, Object> paraMap){
		return this.findListByHqlId("findNoticeEvidenceByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public NoticeEvidence getById(String id)
	{
		return (NoticeEvidence)this.getObjectById(NoticeEvidence.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(NoticeEvidence noticeEvidence)
	{
		noticeEvidence.setId(null);
		this.saveOrUpdateObject(noticeEvidence);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(NoticeEvidence noticeEvidence)
	{
		this.saveOrUpdateObject(noticeEvidence);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(NoticeEvidence.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		NoticeEvidence noticeEvidence = (NoticeEvidence)this.getObjectById(NoticeEvidence.class, id);
		noticeEvidence.setDelFlag(1);
		this.saveObject(noticeEvidence);
	}
}
