package com.jshx.aqscData.dao.impl;

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
import com.jshx.aqscData.entity.AqscData;
import com.jshx.aqscData.dao.AqscDataDao;

@Component("aqscDataDao")
public class AqscDataDaoImpl extends BaseDaoImpl implements AqscDataDao
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
		return this.findPageByHqlId("findAqscDataByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findAqscData(Map<String, Object> paraMap){
		return this.findListByHqlId("findAqscDataByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public AqscData getById(String id)
	{
		return (AqscData)this.getObjectById(AqscData.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(AqscData aqscData)
	{
		aqscData.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(aqscData);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(AqscData aqscData)
	{
		this.saveOrUpdateObject(aqscData);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(AqscData.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		AqscData aqscData = (AqscData)this.getObjectById(AqscData.class, id);
		aqscData.setDelFlag(1);
		this.saveObject(aqscData);
	}

	@Override
	public AqscData getAqscDataByMap(Map map) {
		// TODO Auto-generated method stub
		AqscData aqscData = new AqscData();
		try {
			aqscData = (AqscData) sqlMapClientTemplate.getSqlMapClient().queryForObject("getAqscDataByMap",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aqscData;
	}
}
