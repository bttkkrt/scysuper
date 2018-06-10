package com.jshx.chesafedesign.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.chesafedesign.entity.CheSafDes;
import com.jshx.chesafedesign.dao.CheSafDesDao;

@Component("cheSafDesDao")
public class CheSafDesDaoImpl extends BaseDaoImpl implements CheSafDesDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCheSafDesByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCheSafDes(Map<String, Object> paraMap){
		return this.findListByHqlId("findCheSafDesByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheSafDes getById(String id)
	{
		return (CheSafDes)this.getObjectById(CheSafDes.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CheSafDes cheSafDes)
	{
		cheSafDes.setId(null);
		this.saveOrUpdateObject(cheSafDes);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CheSafDes cheSafDes)
	{
		this.saveOrUpdateObject(cheSafDes);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CheSafDes.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CheSafDes cheSafDes = (CheSafDes)this.getObjectById(CheSafDes.class, id);
		cheSafDes.setDelFlag(1);
		this.saveObject(cheSafDes);
	}
}
