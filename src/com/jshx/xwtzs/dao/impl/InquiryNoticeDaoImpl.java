package com.jshx.xwtzs.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xwtzs.entity.InquiryNotice;
import com.jshx.xwtzs.dao.InquiryNoticeDao;

@Component("inquiryNoticeDao")
public class InquiryNoticeDaoImpl extends BaseDaoImpl implements InquiryNoticeDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findInquiryNoticeByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<InquiryNotice> findInquiryNotice(Map<String, Object> paraMap){
		return this.findListByHqlId("findInquiryNoticeByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public InquiryNotice getById(String id)
	{
		return (InquiryNotice)this.getObjectById(InquiryNotice.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(InquiryNotice inquiryNotice)
	{
		inquiryNotice.setId(null);
		this.saveOrUpdateObject(inquiryNotice);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(InquiryNotice inquiryNotice)
	{
		this.saveOrUpdateObject(inquiryNotice);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(InquiryNotice.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		InquiryNotice inquiryNotice = (InquiryNotice)this.getObjectById(InquiryNotice.class, id);
		inquiryNotice.setDelFlag(1);
		this.saveObject(inquiryNotice);
	}
}
