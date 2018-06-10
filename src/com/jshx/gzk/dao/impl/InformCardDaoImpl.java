package com.jshx.gzk.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.gzk.entity.InformCard;
import com.jshx.gzk.dao.InformCardDao;

@Component("informCardDao")
public class InformCardDaoImpl extends BaseDaoImpl implements InformCardDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findInformCardByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findInformCard(Map<String, Object> paraMap){
		return this.findListByHqlId("findInformCardByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public InformCard getById(String id)
	{
		return (InformCard)this.getObjectById(InformCard.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(InformCard informCard)
	{
		informCard.setId(null);
		this.saveOrUpdateObject(informCard);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(InformCard informCard)
	{
		this.saveOrUpdateObject(informCard);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(InformCard.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		InformCard informCard = (InformCard)this.getObjectById(InformCard.class, id);
		informCard.setDelFlag(1);
		this.saveObject(informCard);
	}
}
