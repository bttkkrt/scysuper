package com.jshx.xwbl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xwbl.entity.InqRecRecord;
import com.jshx.xwbl.entity.InquiryRecord;
import com.jshx.xwbl.dao.InquiryRecordDao;

@Component("inquiryRecordDao")
public class InquiryRecordDaoImpl extends BaseDaoImpl implements InquiryRecordDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findInquiryRecordByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<InquiryRecord> findInquiryRecord(Map<String, Object> paraMap){
		return this.findListByHqlId("findInquiryRecordByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public InquiryRecord getById(String id)
	{
		return (InquiryRecord)this.getObjectById(InquiryRecord.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(InquiryRecord inquiryRecord)
	{
		inquiryRecord.setId(null);
		this.saveOrUpdateObject(inquiryRecord);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(InquiryRecord inquiryRecord)
	{
		this.saveOrUpdateObject(inquiryRecord);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(InquiryRecord.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		InquiryRecord inquiryRecord = (InquiryRecord)this.getObjectById(InquiryRecord.class, id);
		inquiryRecord.setDelFlag(1);
		this.saveObject(inquiryRecord);
	}

	@Override
	public void deleteInqRecRecord(String ids) {
		// TODO Auto-generated method stub
		this.removeObjectById(InqRecRecord.class, ids);
	}

	@Override
	public void deleteInqRecRecordWithFlag(String ids) {
		// TODO Auto-generated method stub
		InqRecRecord inqRecRecord = (InqRecRecord)this.getObjectById(InqRecRecord.class, ids);
		inqRecRecord.setDelFlag(1);
		this.saveObject(inqRecRecord);
	}

	@Override
	public List<InqRecRecord> findInqRecRecord(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return this.findListByHqlId("findInqRecRecordByMap", paraMap);
	}

	@Override
	public InqRecRecord getInqRecRecordById(String id) {
		// TODO Auto-generated method stub
		return (InqRecRecord)this.getObjectById(InqRecRecord.class, id);
	}

	@Override
	public void saveInqRecRecord(InqRecRecord model) {
		// TODO Auto-generated method stub
		model.setId(null);
		this.saveOrUpdateObject(model);
	}

	@Override
	public void updateInqRecRecord(InqRecRecord model) {
		// TODO Auto-generated method stub
		this.saveOrUpdateObject(model);
	}
}
