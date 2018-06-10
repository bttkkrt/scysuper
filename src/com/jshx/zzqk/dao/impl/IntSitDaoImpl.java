package com.jshx.zzqk.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zzqk.entity.IntSit;
import com.jshx.zzqk.dao.IntSitDao;

@Component("intSitDao")
public class IntSitDaoImpl extends BaseDaoImpl implements IntSitDao
{
	@Resource(name="sqlMapClientTemplate")
	SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findIntSitByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<IntSit> findIntSit(Map<String, Object> paraMap){
		return sqlMapClientTemplate.queryForList("findIntSitByMaps", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public IntSit getById(String id)
	{
		return (IntSit)this.getObjectById(IntSit.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(IntSit intSit)
	{
		intSit.setId(null);
		this.saveOrUpdateObject(intSit);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(IntSit intSit)
	{
		this.saveOrUpdateObject(intSit);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(IntSit.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		IntSit intSit = (IntSit)this.getObjectById(IntSit.class, id);
		intSit.setDelFlag(1);
		this.saveObject(intSit);
	}

	@Override
	public List<IntSit> findIntSits(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return this.findListByHqlId("findIntSitByMapss", paraMap);
	}
}
