package com.jshx.qygsrdqk.dao.impl;

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
import com.jshx.gmys.entity.GmysBean;
import com.jshx.qygsrdqk.entity.Qygsrdqk;
import com.jshx.qygsrdqk.entity.QygsrdqkBean;
import com.jshx.qygsrdqk.dao.QygsrdqkDao;

@Component("qygsrdqkDao")
public class QygsrdqkDaoImpl extends BaseDaoImpl implements QygsrdqkDao
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
		return this.findPageByHqlId("findQygsrdqkByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findQygsrdqk(Map<String, Object> paraMap){
		return this.findListByHqlId("findQygsrdqkByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Qygsrdqk getById(String id)
	{
		return (Qygsrdqk)this.getObjectById(Qygsrdqk.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Qygsrdqk qygsrdqk)
	{
		qygsrdqk.setId(null);
		this.saveOrUpdateObject(qygsrdqk);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Qygsrdqk qygsrdqk)
	{
		this.saveOrUpdateObject(qygsrdqk);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Qygsrdqk.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Qygsrdqk qygsrdqk = (Qygsrdqk)this.getObjectById(Qygsrdqk.class, id);
		qygsrdqk.setDelFlag(1);
		this.saveObject(qygsrdqk);
	}
	  public List<QygsrdqkBean> getQygsrdqkListByMap(Map map){
		  return sqlMapClientTemplate.queryForList("getQygsrdqkListByMap",map);
	   }
		
		public QygsrdqkBean getTotalQygsrdqkListByMap(Map map){
			return (QygsrdqkBean)sqlMapClientTemplate.queryForObject("getTotalQygsrdqkListByMap",map);
		}
}
