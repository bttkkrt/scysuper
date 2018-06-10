package com.jshx.ldmjx.dao.impl;

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
import com.jshx.ldmjx.entity.Ldmjx;
import com.jshx.ldmjx.entity.LdmjxBean;
import com.jshx.ldmjx.dao.LdmjxDao;

@Component("ldmjxDao")
public class LdmjxDaoImpl extends BaseDaoImpl implements LdmjxDao
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
		return this.findPageByHqlId("findLdmjxByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findLdmjx(Map<String, Object> paraMap){
		return this.findListByHqlId("findLdmjxByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Ldmjx getById(String id)
	{
		return (Ldmjx)this.getObjectById(Ldmjx.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Ldmjx ldmjx)
	{
		ldmjx.setId(null);
		this.saveOrUpdateObject(ldmjx);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Ldmjx ldmjx)
	{
		this.saveOrUpdateObject(ldmjx);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Ldmjx.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Ldmjx ldmjx = (Ldmjx)this.getObjectById(Ldmjx.class, id);
		ldmjx.setDelFlag(1);
		this.saveObject(ldmjx);
	}
	
	 public List<LdmjxBean> getLdmjxListByMap(Map map){
		 return sqlMapClientTemplate.queryForList("getLdmjxListByMap",map);
	   }
		
		public LdmjxBean getTotalLdmjxListByMap(Map map){
			return (LdmjxBean)sqlMapClientTemplate.queryForObject("getTotalLdmjxListByMap",map);
		}
}
