package com.jshx.yjjgydw.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.yjjgydw.entity.EmeAge;
import com.jshx.yjjgydw.dao.EmeAgeDao;

@Component("emeAgeDao")
public class EmeAgeDaoImpl extends BaseDaoImpl implements EmeAgeDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findEmeAgeByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findEmeAge(Map<String, Object> paraMap){
		return this.findListByHqlId("findEmeAgeByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public EmeAge getById(String id)
	{
		return (EmeAge)this.getObjectById(EmeAge.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(EmeAge emeAge)
	{
		emeAge.setId(null);
		this.saveOrUpdateObject(emeAge);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(EmeAge emeAge)
	{
		this.saveOrUpdateObject(emeAge);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(EmeAge.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		EmeAge emeAge = (EmeAge)this.getObjectById(EmeAge.class, id);
		emeAge.setDelFlag(1);
		this.saveObject(emeAge);
	}
}
