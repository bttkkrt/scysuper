package com.jshx.echarts.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.echarts.entity.Echarts;
import com.jshx.echarts.dao.EchartsDao;

@Component("echartsDao")
public class EchartsDaoImpl extends BaseDaoImpl implements EchartsDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findEchartsByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findEcharts(Map<String, Object> paraMap){
		return this.findListByHqlId("findEchartsByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Echarts getById(String id)
	{
		return (Echarts)this.getObjectById(Echarts.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Echarts echarts)
	{
		echarts.setId(null);
		this.saveOrUpdateObject(echarts);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Echarts echarts)
	{
		this.saveOrUpdateObject(echarts);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Echarts.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Echarts echarts = (Echarts)this.getObjectById(Echarts.class, id);
		echarts.setDelFlag(1);
		this.saveObject(echarts);
	}
}
