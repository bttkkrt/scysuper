package com.jshx.tzsb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.tzsb.entity.JshxTzsb;
import com.jshx.tzsb.dao.JshxTzsbDao;

@Component("jshxTzsbDao")
public class JshxTzsbDaoImpl extends BaseDaoImpl implements JshxTzsbDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJshxTzsbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public List findJshxTzsb(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxTzsbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public JshxTzsb getById(String id)
	{
		return (JshxTzsb)this.getObjectById(JshxTzsb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public void save(JshxTzsb jshxTzsb)
	{
		jshxTzsb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxTzsb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public void update(JshxTzsb jshxTzsb)
	{
		this.saveOrUpdateObject(jshxTzsb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-19
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxTzsb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-19
	 */
	public void deleteWithFlag(String id)
	{
		JshxTzsb jshxTzsb = (JshxTzsb)this.getObjectById(JshxTzsb.class, id);
		jshxTzsb.setDelFlag(1);
		this.saveObject(jshxTzsb);
	}
}
