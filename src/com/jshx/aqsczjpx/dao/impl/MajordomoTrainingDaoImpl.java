package com.jshx.aqsczjpx.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqsczjpx.entity.MajordomoTraining;
import com.jshx.aqsczjpx.dao.MajordomoTrainingDao;

@Component("majordomoTrainingDao")
public class MajordomoTrainingDaoImpl extends BaseDaoImpl implements MajordomoTrainingDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findMajordomoTrainingByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findMajordomoTraining(Map<String, Object> paraMap){
		return this.findListByHqlId("findMajordomoTrainingByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public MajordomoTraining getById(String id)
	{
		return (MajordomoTraining)this.getObjectById(MajordomoTraining.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(MajordomoTraining majordomoTraining)
	{
		majordomoTraining.setId(null);
		this.saveOrUpdateObject(majordomoTraining);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(MajordomoTraining majordomoTraining)
	{
		this.saveOrUpdateObject(majordomoTraining);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(MajordomoTraining.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		MajordomoTraining majordomoTraining = (MajordomoTraining)this.getObjectById(MajordomoTraining.class, id);
		majordomoTraining.setDelFlag(1);
		this.saveObject(majordomoTraining);
	}
}
