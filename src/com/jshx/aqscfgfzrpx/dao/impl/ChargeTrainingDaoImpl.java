package com.jshx.aqscfgfzrpx.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqscfgfzrpx.entity.ChargeTraining;
import com.jshx.aqscfgfzrpx.dao.ChargeTrainingDao;

@Component("chargeTrainingDao")
public class ChargeTrainingDaoImpl extends BaseDaoImpl implements ChargeTrainingDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findChargeTrainingByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findChargeTraining(Map<String, Object> paraMap){
		return this.findListByHqlId("findChargeTrainingByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ChargeTraining getById(String id)
	{
		return (ChargeTraining)this.getObjectById(ChargeTraining.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ChargeTraining chargeTraining)
	{
		chargeTraining.setId(null);
		this.saveOrUpdateObject(chargeTraining);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ChargeTraining chargeTraining)
	{
		this.saveOrUpdateObject(chargeTraining);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ChargeTraining.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ChargeTraining chargeTraining = (ChargeTraining)this.getObjectById(ChargeTraining.class, id);
		chargeTraining.setDelFlag(1);
		this.saveObject(chargeTraining);
	}
}
