package com.jshx.dailyInspection.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.dailyInspection.entity.Dailyinspection;
import com.jshx.dailyInspection.dao.DailyinspectionDao;

@Component("dailyinspectionDao")
public class DailyinspectionDaoImpl extends BaseDaoImpl implements DailyinspectionDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findDailyinspectionByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findDailyinspection(Map<String, Object> paraMap){
		return this.findListByHqlId("findDailyinspectionByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Dailyinspection getById(String id)
	{
		return (Dailyinspection)this.getObjectById(Dailyinspection.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Dailyinspection dailyinspection)
	{
		dailyinspection.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(dailyinspection);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Dailyinspection dailyinspection)
	{
		this.saveOrUpdateObject(dailyinspection);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Dailyinspection.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Dailyinspection dailyinspection = (Dailyinspection)this.getObjectById(Dailyinspection.class, id);
		dailyinspection.setDelFlag(1);
		this.saveObject(dailyinspection);
	}
}
