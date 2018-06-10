package com.jshx.zdwxyjbtz.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zdwxyjbtz.entity.Zdwxyjbtz;
import com.jshx.zdwxyjbtz.dao.ZdwxyjbtzDao;

@Component("zdwxyjbtzDao")
public class ZdwxyjbtzDaoImpl extends BaseDaoImpl implements ZdwxyjbtzDao
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
		return this.findPageByHqlId("findZdwxyjbtzByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZdwxyjbtz(Map<String, Object> paraMap){
		return this.findListByHqlId("findZdwxyjbtzByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zdwxyjbtz getById(String id)
	{
		return (Zdwxyjbtz)this.getObjectById(Zdwxyjbtz.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zdwxyjbtz zdwxyjbtz)
	{
		zdwxyjbtz.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zdwxyjbtz);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zdwxyjbtz zdwxyjbtz)
	{
		this.saveOrUpdateObject(zdwxyjbtz);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zdwxyjbtz.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zdwxyjbtz zdwxyjbtz = (Zdwxyjbtz)this.getObjectById(Zdwxyjbtz.class, id);
		zdwxyjbtz.setDelFlag(1);
		this.saveObject(zdwxyjbtz);
	}

	@Override
	public void deleteWhpByMap(Map map) {
		// TODO Auto-generated method stub
		try {
			sqlMapClientTemplate.getSqlMapClient().delete("deleteWhpByMap",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
