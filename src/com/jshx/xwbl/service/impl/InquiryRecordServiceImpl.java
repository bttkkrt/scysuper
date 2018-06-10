package com.jshx.xwbl.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xwbl.dao.InquiryRecordDao;
import com.jshx.xwbl.entity.InqRecRecord;
import com.jshx.xwbl.entity.InquiryRecord;
import com.jshx.xwbl.service.InquiryRecordService;

@Service("inquiryRecordService")
public class InquiryRecordServiceImpl extends BaseServiceImpl implements InquiryRecordService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("inquiryRecordDao")
	private InquiryRecordDao inquiryRecordDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return inquiryRecordDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public InquiryRecord getById(String id)
	{
		return inquiryRecordDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(InquiryRecord inquiryRecord)
	{
		inquiryRecordDao.save(inquiryRecord);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(InquiryRecord inquiryRecord)
	{
		inquiryRecordDao.update(inquiryRecord);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void delete(String[] ids)
	{
		List list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects=inquiryRecordDao.findInquiryRecord(paraMap);
		
		inquiryRecordDao.removeAll(objects);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void deleteWithFlag(String ids)
	{
	    String[] idArray = ids.split("\\|");
		if(null != idArray)
		{
			for(String id : idArray)
			{
			    if(id!=null && !id.trim().equals(""))
				    inquiryRecordDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<InquiryRecord> findInquiryRecord(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return inquiryRecordDao.findInquiryRecord(paraMap);
	}

	@Transactional
	public void deleteInqRecRecord(String[] ids) {
		// TODO Auto-generated method stub
		List list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects=inquiryRecordDao.findInqRecRecord(paraMap);
		
		inquiryRecordDao.removeAll(objects);
	}

	@Transactional
	public void deleteInqRecRecordWithFlag(String ids) {
		// TODO Auto-generated method stub
		 String[] idArray = ids.split("\\|");
		if(null != idArray)
		{
			for(String id : idArray)
			{
			    if(id!=null && !id.trim().equals(""))
				    inquiryRecordDao.deleteInqRecRecordWithFlag(id);
			}
		}
	}

	@Override
	public List<InqRecRecord> findInqRecRecord(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return inquiryRecordDao.findInqRecRecord(paraMap);
	}

	@Override
	public InqRecRecord getInqRecRecordById(String id) {
		// TODO Auto-generated method stub
		return inquiryRecordDao.getInqRecRecordById(id);
	}

	@Transactional
	public void saveInqRecRecord(InqRecRecord model) {
		// TODO Auto-generated method stub
		inquiryRecordDao.saveInqRecRecord(model);
	}

	@Transactional
	public void updateInqRecRecord(InqRecRecord model) {
		// TODO Auto-generated method stub
		inquiryRecordDao.updateInqRecRecord(model);
	}
}
