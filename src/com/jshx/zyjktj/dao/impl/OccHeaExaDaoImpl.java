package com.jshx.zyjktj.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zyjktj.dao.OccHeaExaDao;
import com.jshx.zyjktj.entity.OccHeaExa;
import com.jshx.zyjktj.entity.OccHeaExaList;

@Component("occHeaExaDao")
public class OccHeaExaDaoImpl extends BaseDaoImpl implements OccHeaExaDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findOccHeaExaByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findOccHeaExa(Map<String, Object> paraMap){
		return this.findListByHqlId("findOccHeaExaByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public OccHeaExa getById(String id)
	{
		return (OccHeaExa)this.getObjectById(OccHeaExa.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(OccHeaExa occHeaExa)
	{
		occHeaExa.setId(null);
		this.saveOrUpdateObject(occHeaExa);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(OccHeaExa occHeaExa)
	{
		this.saveOrUpdateObject(occHeaExa);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(OccHeaExa.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		OccHeaExa occHeaExa = (OccHeaExa)this.getObjectById(OccHeaExa.class, id);
		occHeaExa.setDelFlag(1);
		this.saveObject(occHeaExa);
	}
	
	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void saveOccHeaExaList(OccHeaExaList occHeaExaList)
	{
		occHeaExaList.setId(null);
		this.saveOrUpdateObject(occHeaExaList);
	}
	
	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteOccHeaExaListWithFlag(String id)
	{
		OccHeaExaList occHeaExaList = (OccHeaExaList)this.getObjectById(OccHeaExaList.class, id);
		occHeaExaList.setDelFlag(1);
		this.saveObject(occHeaExaList);
	}
	
}
