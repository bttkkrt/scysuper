package com.jshx.xwtzs.service.impl;

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
import com.jshx.xwtzs.dao.InquiryNoticeDao;
import com.jshx.xwtzs.entity.InquiryNotice;
import com.jshx.xwtzs.service.InquiryNoticeService;

@Service("inquiryNoticeService")
public class InquiryNoticeServiceImpl extends BaseServiceImpl implements InquiryNoticeService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("inquiryNoticeDao")
	private InquiryNoticeDao inquiryNoticeDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return inquiryNoticeDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public InquiryNotice getById(String id)
	{
		return inquiryNoticeDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(InquiryNotice inquiryNotice)
	{
		inquiryNoticeDao.save(inquiryNotice);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(InquiryNotice inquiryNotice)
	{
		inquiryNoticeDao.update(inquiryNotice);
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
		List objects=inquiryNoticeDao.findInquiryNotice(paraMap);
		
		inquiryNoticeDao.removeAll(objects);
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
				    inquiryNoticeDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<InquiryNotice> findInquiryNotice(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return inquiryNoticeDao.findInquiryNotice(paraMap);
	}
}
