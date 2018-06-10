package com.jshx.aqscfzry.dao.impl;

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
import com.jshx.aqscfzry.entity.SecProChaPer;
import com.jshx.aqscfzry.dao.SecProChaPerDao;

@Component("secProChaPerDao")
public class SecProChaPerDaoImpl extends BaseDaoImpl implements SecProChaPerDao
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
		return this.findPageByHqlId("findSecProChaPerByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<SecProChaPer> findSecProChaPer(Map<String, Object> paraMap){
		return this.findListByHqlId("findSecProChaPerByMaps", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SecProChaPer getById(String id)
	{
		return (SecProChaPer)this.getObjectById(SecProChaPer.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SecProChaPer secProChaPer)
	{
		secProChaPer.setId(null);
		this.saveOrUpdateObject(secProChaPer);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SecProChaPer secProChaPer)
	{
		this.saveOrUpdateObject(secProChaPer);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SecProChaPer.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SecProChaPer secProChaPer = (SecProChaPer)this.getObjectById(SecProChaPer.class, id);
		secProChaPer.setDelFlag(1);
		this.saveObject(secProChaPer);
	}
	/**
	 * 获取手机号
	 * 
	 */
	public List<SecProChaPer> getTelephone(Map map){
		return sqlMapClientTemplate.queryForList("aqscfzryTelephone", map);
	}
}
