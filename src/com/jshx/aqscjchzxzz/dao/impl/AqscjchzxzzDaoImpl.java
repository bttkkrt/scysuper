package com.jshx.aqscjchzxzz.dao.impl;

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
import com.jshx.aqscjchzxzz.entity.Aqscjchzxzz;
import com.jshx.aqscjchzxzz.entity.AqscjchzxzzBean;
import com.jshx.aqscjchzxzz.dao.AqscjchzxzzDao;

@Component("aqscjchzxzzDao")
public class AqscjchzxzzDaoImpl extends BaseDaoImpl implements AqscjchzxzzDao
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
		return this.findPageByHqlId("findAqscjchzxzzByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findAqscjchzxzz(Map<String, Object> paraMap){
		return this.findListByHqlId("findAqscjchzxzzByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscjchzxzz getById(String id)
	{
		return (Aqscjchzxzz)this.getObjectById(Aqscjchzxzz.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Aqscjchzxzz aqscjchzxzz)
	{
		aqscjchzxzz.setId(null);
		this.saveOrUpdateObject(aqscjchzxzz);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Aqscjchzxzz aqscjchzxzz)
	{
		this.saveOrUpdateObject(aqscjchzxzz);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Aqscjchzxzz.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Aqscjchzxzz aqscjchzxzz = (Aqscjchzxzz)this.getObjectById(Aqscjchzxzz.class, id);
		aqscjchzxzz.setDelFlag(1);
		this.saveObject(aqscjchzxzz);
	}
	public List<AqscjchzxzzBean>  getAqscjchzxzzListByMap(Map map){
		return sqlMapClientTemplate.queryForList("getAqscjchzxzzListByMap",map);
	}
	
	public AqscjchzxzzBean getTotalAqscjchzxzzListByMap(Map map){
		return (AqscjchzxzzBean)sqlMapClientTemplate.queryForObject("getTotalAqscjchzxzzListByMap",map);
	}
}
