package com.jshx.zybwhzl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zybwhzl.entity.Zybwhzl;
import com.jshx.zybwhzl.dao.ZybwhzlDao;

@Component("zybwhzlDao")
public class ZybwhzlDaoImpl extends BaseDaoImpl implements ZybwhzlDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findZybwhzlByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZybwhzl(Map<String, Object> paraMap){
		return this.findListByHqlId("findZybwhzlByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zybwhzl getById(String id)
	{
		return (Zybwhzl)this.getObjectById(Zybwhzl.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zybwhzl zybwhzl)
	{
		zybwhzl.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zybwhzl);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zybwhzl zybwhzl)
	{
		this.saveOrUpdateObject(zybwhzl);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zybwhzl.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zybwhzl zybwhzl = (Zybwhzl)this.getObjectById(Zybwhzl.class, id);
		zybwhzl.setDelFlag(1);
		this.saveObject(zybwhzl);
	}
}
