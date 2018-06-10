package com.jshx.checkResult.service.impl;

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
import com.jshx.checkResult.dao.CheckResultDao;
import com.jshx.checkResult.entity.CheckResult;
import com.jshx.checkResult.service.CheckResultService;

@Service("checkResultService")
public class CheckResultServiceImpl extends BaseServiceImpl implements CheckResultService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("checkResultDao")
	private CheckResultDao checkResultDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return checkResultDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheckResult getById(String id)
	{
		return checkResultDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(CheckResult checkResult)
	{
		checkResultDao.save(checkResult);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(CheckResult checkResult)
	{
		checkResultDao.update(checkResult);
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
		List objects=checkResultDao.findCheckResult(paraMap);
		
		checkResultDao.removeAll(objects);
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
				    checkResultDao.deleteWithFlag(id);
			}
		}
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCheckResult(Map<String, Object> paraMap){
		return checkResultDao.findCheckResult(paraMap);
	}
}
