package com.jshx.aqscsgyhpc.dao.impl;

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
import com.jshx.gwhy.entity.GwhyBean;
import com.jshx.aqscsgyhpc.entity.Aqscsgyhpc;
import com.jshx.aqscsgyhpc.entity.AqscsgyhpcBean;
import com.jshx.aqscsgyhpc.dao.AqscsgyhpcDao;

@Component("aqscsgyhpcDao")
public class AqscsgyhpcDaoImpl extends BaseDaoImpl implements AqscsgyhpcDao
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
		return this.findPageByHqlId("findAqscsgyhpcByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findAqscsgyhpc(Map<String, Object> paraMap){
		return this.findListByHqlId("findAqscsgyhpcByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscsgyhpc getById(String id)
	{
		return (Aqscsgyhpc)this.getObjectById(Aqscsgyhpc.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Aqscsgyhpc aqscsgyhpc)
	{
		aqscsgyhpc.setId(null);
		this.saveOrUpdateObject(aqscsgyhpc);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Aqscsgyhpc aqscsgyhpc)
	{
		this.saveOrUpdateObject(aqscsgyhpc);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Aqscsgyhpc.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Aqscsgyhpc aqscsgyhpc = (Aqscsgyhpc)this.getObjectById(Aqscsgyhpc.class, id);
		aqscsgyhpc.setDelFlag(1);
		this.saveObject(aqscsgyhpc);
	}
	 public List<AqscsgyhpcBean> getAqscsgyhpcListByMap(Map map){
		 return sqlMapClientTemplate.queryForList("getAqscsgyhpcListByMap",map);
	    }
		
		public AqscsgyhpcBean getTotalAqscsgyhpcListByMap(Map map){
			return (AqscsgyhpcBean)sqlMapClientTemplate.queryForObject("getTotalAqscsgyhpcListByMap",map);
		}
}
