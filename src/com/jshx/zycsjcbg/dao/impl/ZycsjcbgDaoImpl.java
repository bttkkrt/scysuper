package com.jshx.zycsjcbg.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zycsjcbg.entity.Zycsjcbg;
import com.jshx.zycsjcbg.dao.ZycsjcbgDao;

@Component("zycsjcbgDao")
public class ZycsjcbgDaoImpl extends BaseDaoImpl implements ZycsjcbgDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findZycsjcbgByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Zycsjcbg> findZycsjcbg(Map<String, Object> paraMap){
		return this.findListByHqlId("findZycsjcbgByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zycsjcbg getById(String id)
	{
		return (Zycsjcbg)this.getObjectById(Zycsjcbg.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zycsjcbg zycsjcbg)
	{
		zycsjcbg.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zycsjcbg);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zycsjcbg zycsjcbg)
	{
		this.saveOrUpdateObject(zycsjcbg);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zycsjcbg.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zycsjcbg zycsjcbg = (Zycsjcbg)this.getObjectById(Zycsjcbg.class, id);
		zycsjcbg.setDelFlag(1);
		this.saveObject(zycsjcbg);
	}
}
