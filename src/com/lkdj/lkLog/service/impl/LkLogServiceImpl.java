package com.lkdj.lkLog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.lkdj.lkLog.dao.LkLogDao;
import com.lkdj.lkLog.entity.LkLog;
import com.lkdj.lkLog.service.LkLogService;

@Service("lkLogService")
public class LkLogServiceImpl extends BaseServiceImpl implements LkLogService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("lkLogDao")
	private LkLogDao lkLogDao;


	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(LkLog lkLog)
	{
		lkLogDao.save(lkLog);
	}

}
