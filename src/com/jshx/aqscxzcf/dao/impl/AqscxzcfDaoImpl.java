package com.jshx.aqscxzcf.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.aqscxzcf.dao.AqscxzcfDao;
import com.jshx.aqscxzcf.entity.Aqscxzcf;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;

@Component("aqscxzcfDao")
public class AqscxzcfDaoImpl extends BaseDaoImpl implements AqscxzcfDao
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
		return this.findPageByHqlId("findAqscxzcfByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Aqscxzcf> findAqscxzcf(Map<String, Object> paraMap){
		return this.findListByHqlId("findAqscxzcfByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscxzcf getById(String id)
	{
		return (Aqscxzcf)this.getObjectById(Aqscxzcf.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Aqscxzcf aqscxzcf)
	{
		aqscxzcf.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(aqscxzcf);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Aqscxzcf aqscxzcf)
	{
		this.saveOrUpdateObject(aqscxzcf);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Aqscxzcf.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Aqscxzcf aqscxzcf = (Aqscxzcf)this.getObjectById(Aqscxzcf.class, id);
		aqscxzcf.setDelFlag(1);
		this.saveObject(aqscxzcf);
	}

	@Override
	public void deleteAqscxzcfglbByMap(Map map) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.delete("deleteAqscxzcfglbByMap",map);
	}

	@Override
	public List<String> getAqscxzcfIdsByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getAqscxzcfIdsByMap",map);
	}
}
