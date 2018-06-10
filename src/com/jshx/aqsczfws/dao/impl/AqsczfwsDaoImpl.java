package com.jshx.aqsczfws.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.aqsczfws.dao.AqsczfwsDao;
import com.jshx.aqsczfws.entity.Aqsczfws;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;

@Component("aqsczfwsDao")
public class AqsczfwsDaoImpl extends BaseDaoImpl implements AqsczfwsDao
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
		return this.findPageByHqlId("findAqsczfwsByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findAqsczfws(Map<String, Object> paraMap){
		return this.findListByHqlId("findAqsczfwsByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqsczfws getById(String id)
	{
		return (Aqsczfws)this.getObjectById(Aqsczfws.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Aqsczfws aqsczfws)
	{
		aqsczfws.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(aqsczfws);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Aqsczfws aqsczfws)
	{
		this.saveOrUpdateObject(aqsczfws);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Aqsczfws.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Aqsczfws aqsczfws = (Aqsczfws)this.getObjectById(Aqsczfws.class, id);
		aqsczfws.setDelFlag(1);
		this.saveObject(aqsczfws);
	}

	@Override
	public void deleteAqsczfwsglbByMap(Map map) {
		// TODO Auto-generated method stub
		try {
			sqlMapClientTemplate.getSqlMapClient().delete("deleteAqsczfwsglbByMap",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getAqsczfwsIdsByMap(Map map) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		try {
			list = sqlMapClientTemplate.getSqlMapClient().queryForList("getAqsczfwsIdsByMap",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
