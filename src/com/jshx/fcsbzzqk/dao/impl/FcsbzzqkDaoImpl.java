package com.jshx.fcsbzzqk.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.aqscjchzxzz.entity.AqscjchzxzzBean;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.fcsbzzqk.entity.Fcsbzzqk;
import com.jshx.fcsbzzqk.entity.FcsbzzqkBean;
import com.jshx.fcsbzzqk.dao.FcsbzzqkDao;

@Component("fcsbzzqkDao")
public class FcsbzzqkDaoImpl extends BaseDaoImpl implements FcsbzzqkDao
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
		return this.findPageByHqlId("findFcsbzzqkByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findFcsbzzqk(Map<String, Object> paraMap){
		return this.findListByHqlId("findFcsbzzqkByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Fcsbzzqk getById(String id)
	{
		return (Fcsbzzqk)this.getObjectById(Fcsbzzqk.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Fcsbzzqk fcsbzzqk)
	{
		fcsbzzqk.setId(null);
		this.saveOrUpdateObject(fcsbzzqk);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Fcsbzzqk fcsbzzqk)
	{
		this.saveOrUpdateObject(fcsbzzqk);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Fcsbzzqk.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Fcsbzzqk fcsbzzqk = (Fcsbzzqk)this.getObjectById(Fcsbzzqk.class, id);
		fcsbzzqk.setDelFlag(1);
		this.saveObject(fcsbzzqk);
	}
	 public List<FcsbzzqkBean> getFcsbzzqkListByMap(Map map){
		 return sqlMapClientTemplate.queryForList("getFcsbzzqkListByMap",map);
	   }
		
	public FcsbzzqkBean getTotalFcsbzzqkListByMap(Map map){
		return (FcsbzzqkBean)sqlMapClientTemplate.queryForObject("getTotalFcsbzzqkListByMap",map);
	}
}
