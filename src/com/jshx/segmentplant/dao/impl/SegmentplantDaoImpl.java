package com.jshx.segmentplant.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.segmentplant.entity.Segmentplant;
import com.jshx.segmentplant.dao.SegmentplantDao;

@Component("segmentplantDao")
public class SegmentplantDaoImpl extends BaseDaoImpl implements SegmentplantDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSegmentplantByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSegmentplant(Map<String, Object> paraMap){
		return this.findListByHqlId("findSegmentplantByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Segmentplant getById(String id)
	{
		return (Segmentplant)this.getObjectById(Segmentplant.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Segmentplant segmentplant)
	{
		segmentplant.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(segmentplant);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Segmentplant segmentplant)
	{
		this.saveOrUpdateObject(segmentplant);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Segmentplant.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Segmentplant segmentplant = (Segmentplant)this.getObjectById(Segmentplant.class, id);
		segmentplant.setDelFlag(1);
		this.saveObject(segmentplant);
	}
}
