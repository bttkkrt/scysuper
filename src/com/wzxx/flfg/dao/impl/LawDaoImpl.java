package com.wzxx.flfg.dao.impl;

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
import com.wzxx.flfg.entity.Law;
import com.wzxx.flfg.dao.LawDao;

@Component("lawDao")
public class LawDaoImpl extends BaseDaoImpl implements LawDao
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
		return this.findPageByHqlId("findLawByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findLaw(Map<String, Object> paraMap){
		return this.findListByHqlId("findLawByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Law getById(String id)
	{
		return (Law)this.getObjectById(Law.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Law law)
	{
		law.setId(null);
		this.saveOrUpdateObject(law);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Law law)
	{
		this.saveOrUpdateObject(law);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Law.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Law law = (Law)this.getObjectById(Law.class, id);
		law.setDelFlag(1);
		this.saveObject(law);
	}
	/**
	 * 获取安全文化

	 */
	public int findAllInfos(Map<String, Object> paraMap)
  	{
    	int size = (Integer)sqlMapClientTemplate.queryForObject("findFlfgListSize",paraMap);
    	return size;
  	}
	
	/**
	 * 获取安全文化列表分页
	 */
	public List<Law> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		int s = (totalPageNum-1)*pageSize;
		int l = pageSize;
		List<Law> list =sqlMapClientTemplate.queryForList("findFlfgList",paraMap,s,l);
		return list;
	}
}
