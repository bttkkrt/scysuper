package com.jshx.zybfzjf.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zybfzjf.entity.Zybfzjf;
import com.jshx.zybfzjf.dao.ZybfzjfDao;

@Component("zybfzjfDao")
public class ZybfzjfDaoImpl extends BaseDaoImpl implements ZybfzjfDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findZybfzjfByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZybfzjf(Map<String, Object> paraMap){
		return this.findListByHqlId("findZybfzjfByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zybfzjf getById(String id)
	{
		return (Zybfzjf)this.getObjectById(Zybfzjf.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zybfzjf zybfzjf)
	{
		zybfzjf.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zybfzjf);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zybfzjf zybfzjf)
	{
		this.saveOrUpdateObject(zybfzjf);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zybfzjf.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zybfzjf zybfzjf = (Zybfzjf)this.getObjectById(Zybfzjf.class, id);
		zybfzjf.setDelFlag(1);
		this.saveObject(zybfzjf);
	}
}
