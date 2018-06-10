package com.jshx.zsgl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zsgl.entity.JshxZsgl;
import com.jshx.zsgl.dao.JshxZsglDao;

@Component("jshxZsglDao")
public class JshxZsglDaoImpl extends BaseDaoImpl implements JshxZsglDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJshxZsglByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findJshxZsgl(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxZsglByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public JshxZsgl getById(String id)
	{
		return (JshxZsgl)this.getObjectById(JshxZsgl.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(JshxZsgl jshxZsgl)
	{
		jshxZsgl.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxZsgl);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(JshxZsgl jshxZsgl)
	{
		this.saveOrUpdateObject(jshxZsgl);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxZsgl.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		JshxZsgl jshxZsgl = (JshxZsgl)this.getObjectById(JshxZsgl.class, id);
		jshxZsgl.setDelFlag(1);
		this.saveObject(jshxZsgl);
	}
}
