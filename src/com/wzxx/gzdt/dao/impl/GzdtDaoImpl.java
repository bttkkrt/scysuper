package com.wzxx.gzdt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.wzxx.gzdt.dao.GzdtDao;
import com.wzxx.gzdt.entity.Gzdt;

@Component("gzdtDao")
public class GzdtDaoImpl extends BaseDaoImpl implements GzdtDao
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
		return this.findPageByHqlId("findGzdtByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findGzdt(Map<String, Object> paraMap){
		return this.findListByHqlId("findGzdtByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Gzdt getById(String id)
	{
		return (Gzdt)this.getObjectById(Gzdt.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Gzdt gzdt)
	{
		gzdt.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(gzdt);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Gzdt gzdt)
	{
		this.saveOrUpdateObject(gzdt);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Gzdt.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Gzdt gzdt = (Gzdt)this.getObjectById(Gzdt.class, id);
		gzdt.setDelFlag("1");
		this.saveObject(gzdt);
	}
	
	/**
	 * 获取未读公告

	 */
	public int findAllInfos(Map<String, Object> paraMap)
  	{
    	int size = (Integer)sqlMapClientTemplate.queryForObject("findGzdtListSize",paraMap);
    	return size;
  	}
	
	/**
	 * 获取未读公告列表分页
	 */
	public List<Gzdt> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		int s = (totalPageNum-1)*pageSize;
		int l = pageSize;
		List<Gzdt> list =sqlMapClientTemplate.queryForList("findGzdtList",paraMap,s,l);
		return list;
	}
}
