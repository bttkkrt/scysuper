package com.jshx.zjjtzsbs.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.zjjtzsbs.dao.ZjjtzsbsDao;
import com.jshx.zjjtzsbs.entity.Zjjtzsbs;

@Component("zjjtzsbsDao")
public class ZjjtzsbsDaoImpl extends BaseDaoImpl implements ZjjtzsbsDao
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
		return this.findPageByHqlId("findZjjtzsbsByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZjjtzsbs(Map<String, Object> paraMap){
		return this.findListByHqlId("findZjjtzsbsByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zjjtzsbs getById(String id)
	{
		return (Zjjtzsbs)this.getObjectById(Zjjtzsbs.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zjjtzsbs zjjtzsbs)
	{
		zjjtzsbs.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zjjtzsbs);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zjjtzsbs zjjtzsbs)
	{
		this.saveOrUpdateObject(zjjtzsbs);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zjjtzsbs.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zjjtzsbs zjjtzsbs = (Zjjtzsbs)this.getObjectById(Zjjtzsbs.class, id);
		zjjtzsbs.setDelFlag(1);
		this.saveObject(zjjtzsbs);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		this.sqlMapClientTemplate.update("updateAllZjjtzsbs");
	}
}
