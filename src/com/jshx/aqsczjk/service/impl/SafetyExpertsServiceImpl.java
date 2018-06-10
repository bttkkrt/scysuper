package com.jshx.aqsczjk.service.impl;

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
import com.jshx.aqsczj.entity.SecProMaj;
import com.jshx.aqsczjk.dao.SafetyExpertsDao;
import com.jshx.aqsczjk.entity.SafetyExperts;
import com.jshx.aqsczjk.service.SafetyExpertsService;

@Service("safetyExpertsService")
public class SafetyExpertsServiceImpl extends BaseServiceImpl implements SafetyExpertsService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("safetyExpertsDao")
	private SafetyExpertsDao safetyExpertsDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return safetyExpertsDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SafetyExperts getById(String id)
	{
		return safetyExpertsDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(SafetyExperts safetyExperts)
	{
		safetyExpertsDao.save(safetyExperts);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(SafetyExperts safetyExperts)
	{
		safetyExpertsDao.update(safetyExperts);
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
		List objects=safetyExpertsDao.findSafetyExperts(paraMap);
		
		safetyExpertsDao.removeAll(objects);
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
				    safetyExpertsDao.deleteWithFlag(id);
			}
		}
	}
	/**
	 * 获取安全专家
	 */
	public int findAllInfos(Map<String, Object> paraMap)
  	{
    	return safetyExpertsDao.findAllInfos(paraMap);
  	}
	
	/**
	 * 获取安全专家
	 */
	public List<SafetyExperts> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		return safetyExpertsDao.findAllInfo(paraMap, totalPageNum, pageSize);
	}
}
