package com.lkdj.lkLog.dao;

import com.jshx.core.base.dao.BaseDao;
import com.lkdj.lkLog.entity.LkLog;


public interface LkLogDao extends BaseDao
{
	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(LkLog model);

}
