package com.jshx.dangerlicene.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.dangerlicene.entity.Dangerlicene;
import com.jshx.dangerlicene.dao.DangerliceneDao;

@Component("dangerliceneDao")
public class DangerliceneDaoImpl extends BaseDaoImpl implements DangerliceneDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findDangerliceneByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findDangerlicene(Map<String, Object> paraMap){
		return this.findListByHqlId("findDangerliceneByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Dangerlicene getById(String id)
	{
		return (Dangerlicene)this.getObjectById(Dangerlicene.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Dangerlicene dangerlicene)
	{
		dangerlicene.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(dangerlicene);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Dangerlicene dangerlicene)
	{
		this.saveOrUpdateObject(dangerlicene);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Dangerlicene.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Dangerlicene dangerlicene = (Dangerlicene)this.getObjectById(Dangerlicene.class, id);
		dangerlicene.setDelFlag(1);
		this.saveObject(dangerlicene);
	}
}
