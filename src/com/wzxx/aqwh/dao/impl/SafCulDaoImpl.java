package com.wzxx.aqwh.dao.impl;

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
import com.wzxx.aqwh.dao.SafCulDao;
import com.wzxx.tzgg.entity.Tzgg;

@Component("safCulDao")
public class SafCulDaoImpl extends BaseDaoImpl implements SafCulDao
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
		return this.findPageByHqlId("findSafCulByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSafCul(Map<String, Object> paraMap){
		return this.findListByHqlId("findSafCulByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SafCul getById(String id)
	{
		return (SafCul)this.getObjectById(SafCul.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SafCul safCul)
	{
		safCul.setId(null);
		this.saveOrUpdateObject(safCul);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SafCul safCul)
	{
		this.saveOrUpdateObject(safCul);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SafCul.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SafCul safCul = (SafCul)this.getObjectById(SafCul.class, id);
		safCul.setDelFlag(1);
		this.saveObject(safCul);
	}
	/**
	 * 获取安全文化

	 */
	public int findAllInfos(Map<String, Object> paraMap)
  	{
    	int size = (Integer)sqlMapClientTemplate.queryForObject("findAqwhListSize",paraMap);
    	return size;
  	}
	
	/**
	 * 获取安全文化列表分页
	 */
	public List<SafCul> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		int s = (totalPageNum-1)*pageSize;
		int l = pageSize;
		List<SafCul> list =sqlMapClientTemplate.queryForList("findAqwhList",paraMap,s,l);
		return list;
	}
}
