package com.jshx.aqscsgcc.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.aqscsgcc.dao.AqscsgccDao;
import com.jshx.aqscsgcc.entity.Aqscsgcc;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;

@Component("aqscsgccDao")
public class AqscsgccDaoImpl extends BaseDaoImpl implements AqscsgccDao
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
		return this.findPageByHqlId("findAqscsgccByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findAqscsgcc(Map<String, Object> paraMap){
		return this.findListByHqlId("findAqscsgccByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscsgcc getById(String id)
	{
		return (Aqscsgcc)this.getObjectById(Aqscsgcc.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Aqscsgcc aqscsgcc)
	{
		aqscsgcc.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(aqscsgcc);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Aqscsgcc aqscsgcc)
	{
		this.saveOrUpdateObject(aqscsgcc);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Aqscsgcc.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Aqscsgcc aqscsgcc = (Aqscsgcc)this.getObjectById(Aqscsgcc.class, id);
		aqscsgcc.setDelFlag(1);
		this.saveObject(aqscsgcc);
	}

	@Override
	public void deleteAqscsgccglbByMap(Map map) {
		// TODO Auto-generated method stub
		try {
			sqlMapClientTemplate.getSqlMapClient().delete("deleteAqscsgccglbByMap",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getAqscsgccIdsByMap(Map map) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		try {
			list = sqlMapClientTemplate.getSqlMapClient().queryForList("getAqscsgccIdsByMap",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
