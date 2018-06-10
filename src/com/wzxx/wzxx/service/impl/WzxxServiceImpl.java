package com.wzxx.wzxx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.wzxx.wzxx.dao.WzxxDao;
import com.wzxx.wzxx.entity.ViewWzxx;
import com.wzxx.wzxx.service.WzxxService;

@Service("wzxxService")
public class WzxxServiceImpl extends BaseServiceImpl implements WzxxService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("wzxxDao")
	private WzxxDao wzxxDao;
	
	/**
	 * 获取搜索列表
	 */
	public int findAllInfos(Map<String, Object> paraMap){
		return wzxxDao.findAllInfos(paraMap);
	}
	
	/**
	 * 获取搜索列表分页
	 */
	public List<ViewWzxx> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize){
		return wzxxDao.findAllInfo(paraMap, totalPageNum, pageSize);
	}

}
