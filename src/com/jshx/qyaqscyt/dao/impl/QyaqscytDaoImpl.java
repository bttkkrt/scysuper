package com.jshx.qyaqscyt.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.aqscsgqk.entity.AqscsgqkBean;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qyaqscyt.entity.Qyaqscyt;
import com.jshx.qyaqscyt.entity.QyaqscytBean;
import com.jshx.qyaqscyt.dao.QyaqscytDao;

@Component("qyaqscytDao")
public class QyaqscytDaoImpl extends BaseDaoImpl implements QyaqscytDao
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
		return this.findPageByHqlId("findQyaqscytByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findQyaqscyt(Map<String, Object> paraMap){
		return this.findListByHqlId("findQyaqscytByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Qyaqscyt getById(String id)
	{
		return (Qyaqscyt)this.getObjectById(Qyaqscyt.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Qyaqscyt qyaqscyt)
	{
		qyaqscyt.setId(null);
		this.saveOrUpdateObject(qyaqscyt);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Qyaqscyt qyaqscyt)
	{
		this.saveOrUpdateObject(qyaqscyt);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Qyaqscyt.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Qyaqscyt qyaqscyt = (Qyaqscyt)this.getObjectById(Qyaqscyt.class, id);
		qyaqscyt.setDelFlag(1);
		this.saveObject(qyaqscyt);
	}
	
	 public List<QyaqscytBean> getQyaqscytListByMap(Map map){
		 return sqlMapClientTemplate.queryForList("getQyaqscytListByMap",map);
	   }
		
		public QyaqscytBean getTotalQyaqscytListByMap(Map map){
			return (QyaqscytBean)sqlMapClientTemplate.queryForObject("getTotalQyaqscytListByMap",map);
		}
}
