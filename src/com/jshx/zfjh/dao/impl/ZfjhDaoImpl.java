package com.jshx.zfjh.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zfjh.entity.Zfjh;
import com.jshx.zfjh.dao.ZfjhDao;

@Component("zfjhDao")
public class ZfjhDaoImpl extends BaseDaoImpl implements ZfjhDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findZfjhByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZfjh(Map<String, Object> paraMap){
		return this.findListByHqlId("findZfjhByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zfjh getById(String id)
	{
		return (Zfjh)this.getObjectById(Zfjh.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zfjh zfjh)
	{
		zfjh.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zfjh);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zfjh zfjh)
	{
		this.saveOrUpdateObject(zfjh);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zfjh.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zfjh zfjh = (Zfjh)this.getObjectById(Zfjh.class, id);
		zfjh.setDelFlag(1);
		this.saveObject(zfjh);
	}
}
