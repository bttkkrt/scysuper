package com.jshx.qyzcyhglb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qyzcyhglb.entity.Qyzcyhglb;
import com.jshx.qyzcyhglb.dao.QyzcyhglbDao;

@Component("qyzcyhglbDao")
public class QyzcyhglbDaoImpl extends BaseDaoImpl implements QyzcyhglbDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findQyzcyhglbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Qyzcyhglb> findQyzcyhglb(Map<String, Object> paraMap){
		return this.findListByHqlId("findQyzcyhglbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Qyzcyhglb getById(String id)
	{
		return (Qyzcyhglb)this.getObjectById(Qyzcyhglb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Qyzcyhglb qyzcyhglb)
	{
		qyzcyhglb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(qyzcyhglb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Qyzcyhglb qyzcyhglb)
	{
		this.saveOrUpdateObject(qyzcyhglb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Qyzcyhglb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Qyzcyhglb qyzcyhglb = (Qyzcyhglb)this.getObjectById(Qyzcyhglb.class, id);
		qyzcyhglb.setDelFlag(1);
		this.saveObject(qyzcyhglb);
	}
}
