package com.jshx.aqsczj.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqsczj.entity.SecProMaj;
import com.jshx.aqsczj.dao.SecProMajDao;

@Component("secProMajDao")
public class SecProMajDaoImpl extends BaseDaoImpl implements SecProMajDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSecProMajByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<SecProMaj> findSecProMaj(Map<String, Object> paraMap){
		return this.findListByHqlId("findSecProMajByMaps", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SecProMaj getById(String id)
	{
		return (SecProMaj)this.getObjectById(SecProMaj.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SecProMaj secProMaj)
	{
		secProMaj.setId(null);
		this.saveOrUpdateObject(secProMaj);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SecProMaj secProMaj)
	{
		this.saveOrUpdateObject(secProMaj);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SecProMaj.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SecProMaj secProMaj = (SecProMaj)this.getObjectById(SecProMaj.class, id);
		secProMaj.setDelFlag(1);
		this.saveObject(secProMaj);
	}
}
