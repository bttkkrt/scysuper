package com.jshx.xgfgl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xgfgl.entity.PartyManage;
import com.jshx.xgfgl.dao.PartyManageDao;

@Component("partyManageDao")
public class PartyManageDaoImpl extends BaseDaoImpl implements PartyManageDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPartyManageByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findPartyManage(Map<String, Object> paraMap){
		return this.findListByHqlId("findPartyManageByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PartyManage getById(String id)
	{
		return (PartyManage)this.getObjectById(PartyManage.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PartyManage partyManage)
	{
		partyManage.setId(null);
		this.saveOrUpdateObject(partyManage);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PartyManage partyManage)
	{
		this.saveOrUpdateObject(partyManage);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PartyManage.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PartyManage partyManage = (PartyManage)this.getObjectById(PartyManage.class, id);
		partyManage.setDelFlag(1);
		this.saveObject(partyManage);
	}
}
