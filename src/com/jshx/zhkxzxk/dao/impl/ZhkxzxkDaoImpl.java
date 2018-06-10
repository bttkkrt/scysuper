package com.jshx.zhkxzxk.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zhkxzxk.entity.Zhkxzxk;
import com.jshx.zhkxzxk.dao.ZhkxzxkDao;

@Component("zhkxzxkDao")
public class ZhkxzxkDaoImpl extends BaseDaoImpl implements ZhkxzxkDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findZhkxzxkByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Zhkxzxk> findZhkxzxk(Map<String, Object> paraMap){
		return this.findListByHqlId("findZhkxzxkByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zhkxzxk getById(String id)
	{
		return (Zhkxzxk)this.getObjectById(Zhkxzxk.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zhkxzxk zhkxzxk)
	{
		zhkxzxk.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zhkxzxk);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zhkxzxk zhkxzxk)
	{
		this.saveOrUpdateObject(zhkxzxk);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zhkxzxk.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zhkxzxk zhkxzxk = (Zhkxzxk)this.getObjectById(Zhkxzxk.class, id);
		zhkxzxk.setDelFlag(1);
		this.saveObject(zhkxzxk);
	}
}
