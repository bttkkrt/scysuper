package com.jshx.wxhxp.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.wxhxp.entity.Wxhxp;
import com.jshx.wxhxp.dao.WxhxpDao;

@Component("wxhxpDao")
public class WxhxpDaoImpl extends BaseDaoImpl implements WxhxpDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findWxhxpByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findWxhxp(Map<String, Object> paraMap){
		return this.findListByHqlId("findWxhxpByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Wxhxp getById(String id)
	{
		return (Wxhxp)this.getObjectById(Wxhxp.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Wxhxp wxhxp)
	{
		wxhxp.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(wxhxp);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Wxhxp wxhxp)
	{
		this.saveOrUpdateObject(wxhxp);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Wxhxp.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Wxhxp wxhxp = (Wxhxp)this.getObjectById(Wxhxp.class, id);
		wxhxp.setDelFlag(1);
		this.saveObject(wxhxp);
	}
}
