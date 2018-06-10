package com.jshx.xxdjbczjtzs.service.impl;

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
import com.jshx.xxdjbczjtzs.dao.NoticeEvidenceDao;
import com.jshx.xxdjbczjtzs.entity.NoticeEvidence;
import com.jshx.xxdjbczjtzs.service.NoticeEvidenceService;

@Service("noticeEvidenceService")
public class NoticeEvidenceServiceImpl extends BaseServiceImpl implements NoticeEvidenceService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("noticeEvidenceDao")
	private NoticeEvidenceDao noticeEvidenceDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return noticeEvidenceDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public NoticeEvidence getById(String id)
	{
		return noticeEvidenceDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(NoticeEvidence noticeEvidence)
	{
		noticeEvidenceDao.save(noticeEvidence);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(NoticeEvidence noticeEvidence)
	{
		noticeEvidenceDao.update(noticeEvidence);
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
		List objects=noticeEvidenceDao.findNoticeEvidence(paraMap);
		
		noticeEvidenceDao.removeAll(objects);
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
				    noticeEvidenceDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<NoticeEvidence> findNoticeEvidence(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return noticeEvidenceDao.findNoticeEvidence(paraMap);
	}
}
