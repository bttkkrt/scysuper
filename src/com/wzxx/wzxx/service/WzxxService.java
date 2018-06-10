package com.wzxx.wzxx.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.wzxx.wzxx.entity.ViewWzxx;

public interface WzxxService extends BaseService
{
	/**
	 * 获取搜索列表
	 */
	public int findAllInfos(Map<String, Object> paraMap);
	
	/**
	 * 获取搜索列表分页
	 */
	public List<ViewWzxx> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize);
}
