package com.wzxx.wzInfoManage.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.wzxx.wzInfoManage.entity.WzInfoManage;
import com.wzxx.wzInfoManage.dao.WzInfoManageDao;

@Component("wzInfoManageDao")
public class WzInfoManageDaoImpl extends BaseDaoImpl implements WzInfoManageDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findWzInfoManageByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findWzInfoManage(Map<String, Object> paraMap){
		return this.findListByHqlId("findWzInfoManageByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public WzInfoManage getById(String id)
	{
		return (WzInfoManage)this.getObjectById(WzInfoManage.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(WzInfoManage wzInfoManage)
	{
		wzInfoManage.setId(null);
		this.saveOrUpdateObject(wzInfoManage);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(WzInfoManage wzInfoManage)
	{
		this.saveOrUpdateObject(wzInfoManage);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(WzInfoManage.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		WzInfoManage wzInfoManage = (WzInfoManage)this.getObjectById(WzInfoManage.class, id);
		wzInfoManage.setDelFlag(1);
		this.saveObject(wzInfoManage);
	}
}
