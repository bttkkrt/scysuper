package com.jshx.aqscgljlpx.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqscgljlpx.entity.ManagerTraining;
import com.jshx.aqscgljlpx.dao.ManagerTrainingDao;

@Component("managerTrainingDao")
public class ManagerTrainingDaoImpl extends BaseDaoImpl implements ManagerTrainingDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findManagerTrainingByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findManagerTraining(Map<String, Object> paraMap){
		return this.findListByHqlId("findManagerTrainingByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ManagerTraining getById(String id)
	{
		return (ManagerTraining)this.getObjectById(ManagerTraining.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ManagerTraining managerTraining)
	{
		managerTraining.setId(null);
		this.saveOrUpdateObject(managerTraining);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ManagerTraining managerTraining)
	{
		this.saveOrUpdateObject(managerTraining);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ManagerTraining.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ManagerTraining managerTraining = (ManagerTraining)this.getObjectById(ManagerTraining.class, id);
		managerTraining.setDelFlag(1);
		this.saveObject(managerTraining);
	}
}
