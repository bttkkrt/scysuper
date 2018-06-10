package com.jshx.xfzzjggl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xfzzjggl.entity.FirIns;
import com.jshx.xfzzjggl.dao.FirInsDao;

@Component("firInsDao")
public class FirInsDaoImpl extends BaseDaoImpl implements FirInsDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findFirInsByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findFirIns(Map<String, Object> paraMap){
		return this.findListByHqlId("findFirInsByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public FirIns getById(String id)
	{
		return (FirIns)this.getObjectById(FirIns.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(FirIns firIns)
	{
		firIns.setId(null);
		this.saveOrUpdateObject(firIns);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(FirIns firIns)
	{
		this.saveOrUpdateObject(firIns);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(FirIns.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		FirIns firIns = (FirIns)this.getObjectById(FirIns.class, id);
		firIns.setDelFlag(1);
		this.saveObject(firIns);
	}
}
