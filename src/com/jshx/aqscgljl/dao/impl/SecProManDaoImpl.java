package com.jshx.aqscgljl.dao.impl;

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
import com.jshx.aqscgljl.entity.SecProMan;
import com.jshx.aqscgljl.dao.SecProManDao;

@Component("secProManDao")
public class SecProManDaoImpl extends BaseDaoImpl implements SecProManDao
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
		return this.findPageByHqlId("findSecProManByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<SecProMan> findSecProMan(Map<String, Object> paraMap){
		return this.findListByHqlId("findSecProManByMaps", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SecProMan getById(String id)
	{
		return (SecProMan)this.getObjectById(SecProMan.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SecProMan secProMan)
	{
		secProMan.setId(null);
		this.saveOrUpdateObject(secProMan);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SecProMan secProMan)
	{
		this.saveOrUpdateObject(secProMan);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SecProMan.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SecProMan secProMan = (SecProMan)this.getObjectById(SecProMan.class, id);
		secProMan.setDelFlag(1);
		this.saveObject(secProMan);
	}
	/**
	 * 获取手机号
	 */
	public List<SecProMan> getTelephone(Map map){
		return sqlMapClientTemplate.queryForList("aqscgljlTelephone", map);
	}
}
