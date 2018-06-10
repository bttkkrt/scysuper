package com.jshx.aqscfytqqk.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqscfytqqk.entity.SecProFeeExt;
import com.jshx.aqscfytqqk.dao.SecProFeeExtDao;

@Component("secProFeeExtDao")
public class SecProFeeExtDaoImpl extends BaseDaoImpl implements SecProFeeExtDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSecProFeeExtByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<SecProFeeExt> findSecProFeeExt(Map<String, Object> paraMap){
		return this.findListByHqlId("findSecProFeeExtByMaps", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SecProFeeExt getById(String id)
	{
		return (SecProFeeExt)this.getObjectById(SecProFeeExt.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SecProFeeExt secProFeeExt)
	{
		secProFeeExt.setId(null);
		this.saveOrUpdateObject(secProFeeExt);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SecProFeeExt secProFeeExt)
	{
		this.saveOrUpdateObject(secProFeeExt);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SecProFeeExt.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SecProFeeExt secProFeeExt = (SecProFeeExt)this.getObjectById(SecProFeeExt.class, id);
		secProFeeExt.setDelFlag(1);
		this.saveObject(secProFeeExt);
	}
}
