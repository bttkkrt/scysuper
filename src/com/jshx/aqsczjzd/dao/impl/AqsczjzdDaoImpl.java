package com.jshx.aqsczjzd.dao.impl;

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
import com.jshx.yhbzlsd.entity.YhbzlsdBean;
import com.jshx.aqsczjzd.entity.Aqsczjzd;
import com.jshx.aqsczjzd.entity.AqsczjzdBean;
import com.jshx.aqsczjzd.dao.AqsczjzdDao;

@Component("aqsczjzdDao")
public class AqsczjzdDaoImpl extends BaseDaoImpl implements AqsczjzdDao
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
		return this.findPageByHqlId("findAqsczjzdByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findAqsczjzd(Map<String, Object> paraMap){
		return this.findListByHqlId("findAqsczjzdByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqsczjzd getById(String id)
	{
		return (Aqsczjzd)this.getObjectById(Aqsczjzd.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Aqsczjzd aqsczjzd)
	{
		aqsczjzd.setId(null);
		this.saveOrUpdateObject(aqsczjzd);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Aqsczjzd aqsczjzd)
	{
		this.saveOrUpdateObject(aqsczjzd);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Aqsczjzd.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Aqsczjzd aqsczjzd = (Aqsczjzd)this.getObjectById(Aqsczjzd.class, id);
		aqsczjzd.setDelFlag(1);
		this.saveObject(aqsczjzd);
	}
	public List<AqsczjzdBean> getAqsczjzdListByMap(Map map){
		return sqlMapClientTemplate.queryForList("getAqsczjzdListByMap",map);
    }
	
	public AqsczjzdBean getTotalAqsczjzdListByMap(Map map){
		return (AqsczjzdBean)sqlMapClientTemplate.queryForObject("getTotalAqsczjzdListByMap",map);
	}
}
