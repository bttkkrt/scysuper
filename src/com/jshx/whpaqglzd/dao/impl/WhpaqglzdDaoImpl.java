package com.jshx.whpaqglzd.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.whpaqglzd.entity.Whpaqglzd;
import com.jshx.whpaqglzd.dao.WhpaqglzdDao;

@Component("whpaqglzdDao")
public class WhpaqglzdDaoImpl extends BaseDaoImpl implements WhpaqglzdDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findWhpaqglzdByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findWhpaqglzd(Map<String, Object> paraMap){
		return this.findListByHqlId("findWhpaqglzdByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Whpaqglzd getById(String id)
	{
		return (Whpaqglzd)this.getObjectById(Whpaqglzd.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Whpaqglzd whpaqglzd)
	{
		whpaqglzd.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(whpaqglzd);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Whpaqglzd whpaqglzd)
	{
		this.saveOrUpdateObject(whpaqglzd);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Whpaqglzd.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Whpaqglzd whpaqglzd = (Whpaqglzd)this.getObjectById(Whpaqglzd.class, id);
		whpaqglzd.setDelFlag(1);
		this.saveObject(whpaqglzd);
	}
}
