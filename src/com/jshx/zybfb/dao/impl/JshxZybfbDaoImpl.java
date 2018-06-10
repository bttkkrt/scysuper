package com.jshx.zybfb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zybfb.entity.JshxZybfb;
import com.jshx.zybfb.dao.JshxZybfbDao;

@Component("jshxZybfbDao")
public class JshxZybfbDaoImpl extends BaseDaoImpl implements JshxZybfbDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJshxZybfbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findJshxZybfb(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxZybfbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public JshxZybfb getById(String id)
	{
		return (JshxZybfb)this.getObjectById(JshxZybfb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(JshxZybfb jshxZybfb)
	{
		jshxZybfb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxZybfb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(JshxZybfb jshxZybfb)
	{
		this.saveOrUpdateObject(jshxZybfb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxZybfb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		JshxZybfb jshxZybfb = (JshxZybfb)this.getObjectById(JshxZybfb.class, id);
		jshxZybfb.setDelFlag(1);
		this.saveObject(jshxZybfb);
	}
}
