package com.wzxx.wzxx.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.wzxx.tzgg.entity.Tzgg;
import com.wzxx.wzxx.dao.WzxxDao;
import com.wzxx.wzxx.entity.ViewWzxx;

@Component("wzxxDao")
public class WzxxDaoImpl extends BaseDaoImpl implements WzxxDao
{
	@Autowired
	@Qualifier("sqlMapClientTemplate") 
	private SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 获取搜索列表
	 */
	public int findAllInfos(Map<String, Object> paraMap){
		int size = (Integer)sqlMapClientTemplate.queryForObject("findSousuoListSize",paraMap);
    	return size;
	}
	
	/**
	 * 获取搜索列表分页
	 */
	public List<ViewWzxx> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize){
		int s = (totalPageNum-1)*pageSize;
		int l = pageSize;
		List<ViewWzxx> list =sqlMapClientTemplate.queryForList("findSousuoList",paraMap,s,l);
		return list;
	}
}
