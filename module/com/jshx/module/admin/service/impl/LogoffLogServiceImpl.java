package com.jshx.module.admin.service.impl;

import java.util.Arrays;
import java.util.Date;
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
import com.jshx.module.admin.dao.LogoffLogDao;
import com.jshx.module.admin.entity.LogoffLog;
import com.jshx.module.admin.service.LogoffLogService;

@Service("logoffLogService")
public class LogoffLogServiceImpl extends BaseServiceImpl implements LogoffLogService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("logoffLogDao")
	private LogoffLogDao logoffLogDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return logoffLogDao.findByPage(page, paraMap);
	}
	
	public Pagination findLogByPage(Pagination pagination, LogoffLog logoffLog, Date queryLogoffDateStart, Date queryLogoffDateEnd){
		return logoffLogDao.findLogByPage(pagination, logoffLog, queryLogoffDateStart, queryLogoffDateEnd);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public LogoffLog getById(String id)
	{
		return logoffLogDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(LogoffLog logoffLog)
	{
		logoffLogDao.save(logoffLog);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(LogoffLog logoffLog)
	{
		logoffLogDao.update(logoffLog);
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
		List objects=logoffLogDao.findLogoffLog(paraMap);
		
		logoffLogDao.removeAll(objects);
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
				    logoffLogDao.deleteWithFlag(id);
			}
		}
	}
}
