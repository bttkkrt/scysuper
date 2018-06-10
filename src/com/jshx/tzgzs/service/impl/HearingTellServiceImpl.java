package com.jshx.tzgzs.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.tzgzs.dao.HearingTellDao;
import com.jshx.tzgzs.entity.HearingTell;
import com.jshx.tzgzs.service.HearingTellService;

@Service("hearingTellService")
public class HearingTellServiceImpl extends BaseServiceImpl implements HearingTellService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("hearingTellDao")
	private HearingTellDao hearingTellDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return hearingTellDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public HearingTell getById(String id)
	{
		return hearingTellDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(HearingTell hearingTell)
	{
		hearingTellDao.save(hearingTell);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(HearingTell hearingTell)
	{
		hearingTellDao.update(hearingTell);
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
		List objects=hearingTellDao.findHearingTell(paraMap);
		
		hearingTellDao.removeAll(objects);
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
				    hearingTellDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<HearingTell> findHearingTell(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return hearingTellDao.findHearingTell(paraMap);
	}
}
