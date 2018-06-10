package com.jshx.kcbl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.kcbl.entity.InquestRecord;
import com.jshx.kcbl.dao.InquestRecordDao;

@Component("inquestRecordDao")
public class InquestRecordDaoImpl extends BaseDaoImpl implements InquestRecordDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findInquestRecordByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<InquestRecord> findInquestRecord(Map<String, Object> paraMap){
		return this.findListByHqlId("findInquestRecordByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public InquestRecord getById(String id)
	{
		return (InquestRecord)this.getObjectById(InquestRecord.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(InquestRecord inquestRecord)
	{
		inquestRecord.setId(null);
		this.saveOrUpdateObject(inquestRecord);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(InquestRecord inquestRecord)
	{
		this.saveOrUpdateObject(inquestRecord);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(InquestRecord.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		InquestRecord inquestRecord = (InquestRecord)this.getObjectById(InquestRecord.class, id);
		inquestRecord.setDelFlag(1);
		this.saveObject(inquestRecord);
	}
}
