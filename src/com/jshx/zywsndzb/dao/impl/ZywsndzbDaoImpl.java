package com.jshx.zywsndzb.dao.impl;

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
import com.jshx.fcsbzzqk.entity.FcsbzzqkBean;
import com.jshx.zywsndzb.entity.Zywsndzb;
import com.jshx.zywsndzb.entity.ZywsndzbBean;
import com.jshx.zywsndzb.dao.ZywsndzbDao;

@Component("zywsndzbDao")
public class ZywsndzbDaoImpl extends BaseDaoImpl implements ZywsndzbDao
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
		return this.findPageByHqlId("findZywsndzbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZywsndzb(Map<String, Object> paraMap){
		return this.findListByHqlId("findZywsndzbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zywsndzb getById(String id)
	{
		return (Zywsndzb)this.getObjectById(Zywsndzb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zywsndzb zywsndzb)
	{
		zywsndzb.setId(null);
		this.saveOrUpdateObject(zywsndzb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zywsndzb zywsndzb)
	{
		this.saveOrUpdateObject(zywsndzb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zywsndzb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zywsndzb zywsndzb = (Zywsndzb)this.getObjectById(Zywsndzb.class, id);
		zywsndzb.setDelFlag(1);
		this.saveObject(zywsndzb);
	}
	public List<ZywsndzbBean> getZywsndzbListByMap(Map map){
		return sqlMapClientTemplate.queryForList("getZywsndzbListByMap",map);
    }
	
	public ZywsndzbBean getTotalZywsndzbListByMap(Map map){
		return (ZywsndzbBean)sqlMapClientTemplate.queryForObject("getTotalZywsndzbListByMap",map);
	}
}
