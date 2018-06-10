package com.jshx.sgyhjb.dao.impl;

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
import com.jshx.module.admin.entity.Department;
import com.jshx.sgyhjb.entity.Sgyhjb;
import com.jshx.sgyhjb.dao.SgyhjbDao;

@Component("sgyhjbDao")
public class SgyhjbDaoImpl extends BaseDaoImpl implements SgyhjbDao
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
		return this.findPageByHqlId("findSgyhjbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Sgyhjb> findSgyhjb(Map<String, Object> paraMap){
		return this.findListByHqlId("findSgyhjbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Sgyhjb getById(String id)
	{
		return (Sgyhjb)this.getObjectById(Sgyhjb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Sgyhjb sgyhjb)
	{
		sgyhjb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(sgyhjb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Sgyhjb sgyhjb)
	{
		this.saveOrUpdateObject(sgyhjb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Sgyhjb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Sgyhjb sgyhjb = (Sgyhjb)this.getObjectById(Sgyhjb.class, id);
		sgyhjb.setDelFlag(1);
		this.saveObject(sgyhjb);
	}

	@Override
	public List<Department> getClbmListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getClbmListByMap",map);
	}

	@Override
	public List<Department> getJbbmListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getJbbmListByMap",map);
	}
}
