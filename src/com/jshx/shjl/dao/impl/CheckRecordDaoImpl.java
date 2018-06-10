package com.jshx.shjl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.dao.CheckRecordDao;

@Component("checkRecordDao")
public class CheckRecordDaoImpl extends BaseDaoImpl implements CheckRecordDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCheckRecordByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCheckRecord(Map<String, Object> paraMap){
		return this.findListByHqlId("findCheckRecordByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheckRecord getById(String id)
	{
		return (CheckRecord)this.getObjectById(CheckRecord.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CheckRecord checkRecord)
	{
		checkRecord.setId(null);
		this.saveOrUpdateObject(checkRecord);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CheckRecord checkRecord)
	{
		this.saveOrUpdateObject(checkRecord);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CheckRecord.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CheckRecord checkRecord = (CheckRecord)this.getObjectById(CheckRecord.class, id);
		checkRecord.setDelFlag(1);
		this.saveObject(checkRecord);
	}
}
