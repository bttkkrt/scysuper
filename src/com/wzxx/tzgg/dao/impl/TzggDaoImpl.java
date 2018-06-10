package com.wzxx.tzgg.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.wzxx.tzgg.dao.TzggDao;
import com.wzxx.tzgg.entity.Tzgg;

@Component("tzggDao")
public class TzggDaoImpl extends BaseDaoImpl implements TzggDao
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
		return this.findPageByHqlId("findTzggByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findTzgg(Map<String, Object> paraMap){
		return this.findListByHqlId("findTzggByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Tzgg getById(String id)
	{
		return (Tzgg)this.getObjectById(Tzgg.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Tzgg tzgg)
	{
		tzgg.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(tzgg);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Tzgg tzgg)
	{
		this.saveOrUpdateObject(tzgg);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Tzgg.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Tzgg tzgg = (Tzgg)this.getObjectById(Tzgg.class, id);
		tzgg.setDelFlag("1");
		this.saveObject(tzgg);
	}
	
	/**
	 * 获取未读公告

	 */
	public int findAllInfos(Map<String, Object> paraMap)
  	{
    	int size = (Integer)sqlMapClientTemplate.queryForObject("findTzggListSize",paraMap);
    	return size;
  	}
	
	/**
	 * 获取未读公告列表分页
	 */
	public List<Tzgg> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		int s = (totalPageNum-1)*pageSize;
		int l = pageSize;
		List<Tzgg> list =sqlMapClientTemplate.queryForList("findTzggList",paraMap,s,l);
		return list;
	}
}
