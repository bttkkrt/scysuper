package com.jshx.aqsczfwsglb.dao.impl;

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
import com.jshx.dfzwglb.entity.Dfzwglb;
import com.jshx.aqsczfwsglb.entity.Aqsczfwsglb;
import com.jshx.aqsczfwsglb.dao.AqsczfwsglbDao;

@Component("aqsczfwsglbDao")
public class AqsczfwsglbDaoImpl extends BaseDaoImpl implements AqsczfwsglbDao
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
		return this.findPageByHqlId("findAqsczfwsglbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Aqsczfwsglb> findAqsczfwsglb(Map<String, Object> paraMap){
		return this.findListByHqlId("findAqsczfwsglbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqsczfwsglb getById(String id)
	{
		return (Aqsczfwsglb)this.getObjectById(Aqsczfwsglb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Aqsczfwsglb aqsczfwsglb)
	{
		aqsczfwsglb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(aqsczfwsglb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Aqsczfwsglb aqsczfwsglb)
	{
		this.saveOrUpdateObject(aqsczfwsglb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Aqsczfwsglb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Aqsczfwsglb aqsczfwsglb = (Aqsczfwsglb)this.getObjectById(Aqsczfwsglb.class, id);
		aqsczfwsglb.setDelFlag(1);
		this.saveObject(aqsczfwsglb);
	}

	@Override
	public Aqsczfwsglb getAqsczfwsglbByMap(Map map) {
		// TODO Auto-generated method stub
		Aqsczfwsglb aqsczfwsglb = new Aqsczfwsglb();
		try {
			aqsczfwsglb = (Aqsczfwsglb) sqlMapClientTemplate.getSqlMapClient().queryForObject("getAqsczfwsglbByMap",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aqsczfwsglb;
	}
}
