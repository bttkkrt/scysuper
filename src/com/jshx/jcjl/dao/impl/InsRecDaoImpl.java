package com.jshx.jcjl.dao.impl;

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
import com.jshx.jcjl.entity.InsRec;
import com.jshx.jcjl.dao.InsRecDao;

@Component("insRecDao")
public class InsRecDaoImpl extends BaseDaoImpl implements InsRecDao
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
		return this.findPageByHqlId("findInsRecByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findInsRec(Map<String, Object> paraMap){
		return this.findListByHqlId("findInsRecByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public InsRec getById(String id)
	{
		return (InsRec)this.getObjectById(InsRec.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(InsRec insRec)
	{
		insRec.setId(null);
		this.saveOrUpdateObject(insRec);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(InsRec insRec)
	{
		this.saveOrUpdateObject(insRec);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(InsRec.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		InsRec insRec = (InsRec)this.getObjectById(InsRec.class, id);
		insRec.setDelFlag(1);
		this.saveObject(insRec);
	}

	@Override
	public List<InsRec> getInsRecListByUserAndType(Map map, int start, int limit) {
		// TODO Auto-generated method stub
		int s = (start-1)*limit;
		int l = limit;
		return sqlMapClientTemplate.queryForList("getInsRecListByUserAndType",map,s,l);
	}

	@Override
	public int getInsRecListSizeByUserAndType(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("getInsRecListSizeByUserAndType",map);
	}
}
