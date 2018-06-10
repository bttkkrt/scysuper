package com.jshx.zybwhysjc.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zybwhysjc.entity.Zybwhysjc;
import com.jshx.zybwhysjc.dao.ZybwhysjcDao;

@Component("zybwhysjcDao")
public class ZybwhysjcDaoImpl extends BaseDaoImpl implements ZybwhysjcDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findZybwhysjcByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZybwhysjc(Map<String, Object> paraMap){
		return this.findListByHqlId("findZybwhysjcByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zybwhysjc getById(String id)
	{
		return (Zybwhysjc)this.getObjectById(Zybwhysjc.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zybwhysjc zybwhysjc)
	{
		zybwhysjc.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zybwhysjc);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zybwhysjc zybwhysjc)
	{
		this.saveOrUpdateObject(zybwhysjc);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zybwhysjc.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zybwhysjc zybwhysjc = (Zybwhysjc)this.getObjectById(Zybwhysjc.class, id);
		zybwhysjc.setDelFlag(1);
		this.saveObject(zybwhysjc);
	}
}
