package com.lkdj.lkLog.dao.impl;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.lkdj.lkLog.dao.LkLogDao;
import com.lkdj.lkLog.entity.LkLog;

@Component("lkLogDao")
public class LkLogDaoImpl extends BaseDaoImpl implements LkLogDao
{

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(LkLog lkLog)
	{
		lkLog.setId(null);
		this.saveOrUpdateObject(lkLog);
	}

}
