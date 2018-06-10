package com.wzxx.zhzf.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.wzxx.aqwh.entity.SafCul;
import com.wzxx.zhzf.entity.Zhzf;
import com.wzxx.zhzf.dao.ZhzfDao;

@Component("zhzfDao")
public class ZhzfDaoImpl extends BaseDaoImpl implements ZhzfDao
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
		return this.findPageByHqlId("findZhzfByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZhzf(Map<String, Object> paraMap){
		return this.findListByHqlId("findZhzfByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zhzf getById(String id)
	{
		return (Zhzf)this.getObjectById(Zhzf.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zhzf zhzf)
	{
		zhzf.setId(null);
		this.saveOrUpdateObject(zhzf);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zhzf zhzf)
	{
		this.saveOrUpdateObject(zhzf);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zhzf.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zhzf zhzf = (Zhzf)this.getObjectById(Zhzf.class, id);
		zhzf.setDelFlag(1);
		this.saveObject(zhzf);
	}
	/**
	 * 获取综合执法

	 */
	public int findAllInfos(Map<String, Object> paraMap)
  	{
    	int size = (Integer)sqlMapClientTemplate.queryForObject("findZhzfListSize",paraMap);
    	return size;
  	}
	
	/**
	 * 获取综合执法列表分页
	 */
	public List<Zhzf> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		int s = (totalPageNum-1)*pageSize;
		int l = pageSize;
		List<Zhzf> list =sqlMapClientTemplate.queryForList("findZhzfList",paraMap,s,l);
		return list;
	}
}
