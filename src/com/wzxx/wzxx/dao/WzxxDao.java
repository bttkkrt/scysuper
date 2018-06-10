package com.wzxx.wzxx.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.wzxx.wzxx.entity.ViewWzxx;


public interface WzxxDao extends BaseDao
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
