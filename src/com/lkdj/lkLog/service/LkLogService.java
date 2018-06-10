package com.lkdj.lkLog.service;

import com.jshx.core.base.service.BaseService;
import com.lkdj.lkLog.entity.LkLog;

public interface LkLogService extends BaseService
{


	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(LkLog model);

}
