package com.jshx.zywsglzd.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zywsglzd.entity.Zywsglzd;
import com.jshx.zywsglzd.dao.ZywsglzdDao;

@Component("zywsglzdDao")
public class ZywsglzdDaoImpl extends BaseDaoImpl implements ZywsglzdDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findZywsglzdByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZywsglzd(Map<String, Object> paraMap){
		return this.findListByHqlId("findZywsglzdByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zywsglzd getById(String id)
	{
		return (Zywsglzd)this.getObjectById(Zywsglzd.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zywsglzd zywsglzd)
	{
		zywsglzd.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zywsglzd);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zywsglzd zywsglzd)
	{
		this.saveOrUpdateObject(zywsglzd);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zywsglzd.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zywsglzd zywsglzd = (Zywsglzd)this.getObjectById(Zywsglzd.class, id);
		zywsglzd.setDelFlag(1);
		this.saveObject(zywsglzd);
	}
}
