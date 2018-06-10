package com.jshx.sgtj.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.sgtj.entity.JshxSgtj;
import com.jshx.sgtj.dao.JshxSgtjDao;

@Component("jshxSgtjDao")
public class JshxSgtjDaoImpl extends BaseDaoImpl implements JshxSgtjDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJshxSgtjByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	@SuppressWarnings("unchecked")
	public List<JshxSgtj> findJshxSgtj(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxSgtjByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public JshxSgtj getById(String id)
	{
		return (JshxSgtj)this.getObjectById(JshxSgtj.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(JshxSgtj jshxSgtj)
	{
		jshxSgtj.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxSgtj);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(JshxSgtj jshxSgtj)
	{
		this.saveOrUpdateObject(jshxSgtj);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxSgtj.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		JshxSgtj jshxSgtj = (JshxSgtj)this.getObjectById(JshxSgtj.class, id);
		jshxSgtj.setDelFlag(1);
		this.saveObject(jshxSgtj);
	}
}
