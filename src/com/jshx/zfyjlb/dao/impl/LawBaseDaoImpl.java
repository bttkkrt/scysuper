package com.jshx.zfyjlb.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zfyjlb.dao.LawBaseDao;
import com.jshx.zfyjlb.entity.LawBase;

@Component("lawBaseDao")
public class LawBaseDaoImpl extends BaseDaoImpl implements LawBaseDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findLawBaseByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findLawBase(Map<String, Object> paraMap){
		return this.findListByHqlId("findLawBaseByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public LawBase getById(String id)
	{
		return (LawBase)this.getObjectById(LawBase.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(LawBase lawBase)
	{
		lawBase.setId(null);
		this.saveOrUpdateObject(lawBase);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(LawBase lawBase)
	{
		this.saveOrUpdateObject(lawBase);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(LawBase.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		LawBase lawBase = (LawBase)this.getObjectById(LawBase.class, id);
		lawBase.setDelFlag(1);
		this.saveObject(lawBase);
	}
}
