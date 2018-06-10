package com.jshx.aqsczjk.dao.impl;

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
import com.jshx.aqsczj.entity.SecProMaj;
import com.jshx.aqsczjk.entity.SafetyExperts;
import com.jshx.aqsczjk.dao.SafetyExpertsDao;

@Component("safetyExpertsDao")
public class SafetyExpertsDaoImpl extends BaseDaoImpl implements SafetyExpertsDao
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
		return this.findPageByHqlId("findSafetyExpertsByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSafetyExperts(Map<String, Object> paraMap){
		return this.findListByHqlId("findSafetyExpertsByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SafetyExperts getById(String id)
	{
		return (SafetyExperts)this.getObjectById(SafetyExperts.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SafetyExperts safetyExperts)
	{
		safetyExperts.setId(null);
		this.saveOrUpdateObject(safetyExperts);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SafetyExperts safetyExperts)
	{
		this.saveOrUpdateObject(safetyExperts);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SafetyExperts.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SafetyExperts safetyExperts = (SafetyExperts)this.getObjectById(SafetyExperts.class, id);
		safetyExperts.setDelFlag(1);
		this.saveObject(safetyExperts);
	}
	/**
	 * 获取安全专家

	 */
	public int findAllInfos(Map<String, Object> paraMap)
  	{
    	int size = (Integer)sqlMapClientTemplate.queryForObject("findAqsczjListSize",paraMap);
    	return size;
  	}
	
	/**
	 * 获取安全专家
	 */
	public List<SafetyExperts> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		int s = (totalPageNum-1)*pageSize;
		int l = pageSize;
		List<SafetyExperts> list =sqlMapClientTemplate.queryForList("findAqsczjList",paraMap,s,l);
		return list;
	}
}
