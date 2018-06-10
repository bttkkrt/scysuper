package com.jshx.zzbw.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zzbw.entity.JshxZzbw;
import com.jshx.zzbw.dao.JshxZzbwDao;

@Component("jshxZzbwDao")
public class JshxZzbwDaoImpl extends BaseDaoImpl implements JshxZzbwDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJshxZzbwByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public List<JshxZzbw> findJshxZzbw(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxZzbwByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public JshxZzbw getById(String id)
	{
		return (JshxZzbw)this.getObjectById(JshxZzbw.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public void save(JshxZzbw jshxZzbw)
	{
		jshxZzbw.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxZzbw);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public void update(JshxZzbw jshxZzbw)
	{
		this.saveOrUpdateObject(jshxZzbw);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-20
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxZzbw.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-20
	 */
	public void deleteWithFlag(String id)
	{
		JshxZzbw jshxZzbw = (JshxZzbw)this.getObjectById(JshxZzbw.class, id);
		jshxZzbw.setDelFlag(1);
		this.saveObject(jshxZzbw);
	}
}
