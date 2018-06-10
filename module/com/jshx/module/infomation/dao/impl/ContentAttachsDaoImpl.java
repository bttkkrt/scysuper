package com.jshx.module.infomation.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.infomation.entity.ContentAttachs;
import com.jshx.module.infomation.dao.ContentAttachsDao;

@Component("contentAttachsDao")
public class ContentAttachsDaoImpl extends BaseDaoImpl implements ContentAttachsDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findContentAttachsByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<ContentAttachs> findContentAttachs(Map<String, Object> paraMap){
		return this.findListByHqlId("findContentAttachsByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ContentAttachs getById(String id)
	{
		return (ContentAttachs)this.getObjectById(ContentAttachs.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ContentAttachs contentAttachs)
	{
		contentAttachs.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(contentAttachs);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ContentAttachs contentAttachs)
	{
		this.saveOrUpdateObject(contentAttachs);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ContentAttachs.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ContentAttachs contentAttachs = (ContentAttachs)this.getObjectById(ContentAttachs.class, id);
		contentAttachs.setDelFlag(1);
		this.saveObject(contentAttachs);
	}
}
