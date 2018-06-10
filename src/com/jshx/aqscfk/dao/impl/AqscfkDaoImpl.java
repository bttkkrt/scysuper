package com.jshx.aqscfk.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.aqscfk.dao.AqscfkDao;
import com.jshx.aqscfk.entity.Aqscfk;
import com.jshx.aqscfk.entity.Aqscxzcfglb;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;

@Component("aqscfkDao")
public class AqscfkDaoImpl extends BaseDaoImpl implements AqscfkDao
{
	@Autowired
	@Qualifier("sqlMapClientTemplate") 
	private SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findAqscfkByMap", paraMap, page);
	}
	
	/**
	 * 信息查询
	 * author：杨鹏
	 * 2014-06-03
	 * @param sqlId SQL名称
	 * @param paraMap 查询条件
	 * @return 分页信息
	 */
	public ArrayList<HashMap> findListByPara(String sqlId, Map<String, Object> paraMap)
	{
		return (ArrayList<HashMap>) this.findListBySqlId(sqlId, paraMap) ;
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Aqscfk> findAqscfk(Map<String, Object> paraMap){
		return this.findListByHqlId("findAqscfkByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscfk getById(String id)
	{
		return (Aqscfk)this.getObjectById(Aqscfk.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Aqscfk aqscfk)
	{
		aqscfk.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(aqscfk);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Aqscfk aqscfk)
	{
		this.saveOrUpdateObject(aqscfk);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Aqscfk.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Aqscfk aqscfk = (Aqscfk)this.getObjectById(Aqscfk.class, id);
		aqscfk.setDelFlag(1);
		this.saveObject(aqscfk);
	}

	@Override
	public Aqscxzcfglb getAqscxzcfglbByMap(Map map) {
		// TODO Auto-generated method stub
		return (Aqscxzcfglb)sqlMapClientTemplate.queryForObject("getAqscxzcfglbByMaps",map);
	}
}
