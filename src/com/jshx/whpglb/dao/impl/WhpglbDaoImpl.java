package com.jshx.whpglb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.whpglb.entity.Whpglb;
import com.jshx.whpglb.dao.WhpglbDao;

@Component("whpglbDao")
public class WhpglbDaoImpl extends BaseDaoImpl implements WhpglbDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findWhpglbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Whpglb> findWhpglb(Map<String, Object> paraMap){
		return this.findListByHqlId("findWhpglbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Whpglb getById(String id)
	{
		return (Whpglb)this.getObjectById(Whpglb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Whpglb whpglb)
	{
		whpglb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(whpglb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Whpglb whpglb)
	{
		this.saveOrUpdateObject(whpglb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Whpglb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Whpglb whpglb = (Whpglb)this.getObjectById(Whpglb.class, id);
		whpglb.setDelFlag(1);
		this.saveObject(whpglb);
	}
}
