package com.wzxx.jgdl.dao.impl;

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
import com.wzxx.csgl.entity.CityMan;
import com.wzxx.jgdl.entity.Jgdl;
import com.wzxx.jgdl.dao.JgdlDao;

@Component("jgdlDao")
public class JgdlDaoImpl extends BaseDaoImpl implements JgdlDao
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
		return this.findPageByHqlId("findJgdlByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findJgdl(Map<String, Object> paraMap){
		return this.findListByHqlId("findJgdlByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Jgdl getById(String id)
	{
		return (Jgdl)this.getObjectById(Jgdl.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Jgdl jgdl)
	{
		jgdl.setId(null);
		this.saveOrUpdateObject(jgdl);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Jgdl jgdl)
	{
		this.saveOrUpdateObject(jgdl);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Jgdl.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Jgdl jgdl = (Jgdl)this.getObjectById(Jgdl.class, id);
		jgdl.setDelFlag(1);
		this.saveObject(jgdl);
	}
	/**
	 * 获取机关党建
	 */
	public int findAllInfos(Map<String, Object> paraMap){
		int size = (Integer)sqlMapClientTemplate.queryForObject("findJgdlListSize",paraMap);
    	return size;
	}
	
	/**
	 * 获取机关党建列表分页
	 */
	public List<Jgdl> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize){
		int s = (totalPageNum-1)*pageSize;
		int l = pageSize;
		List<Jgdl> list =sqlMapClientTemplate.queryForList("findJgdlList",paraMap,s,l);
		return list;
	}
}
