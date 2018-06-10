package com.jshx.aqscsgccglb.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.aqscsgccglb.dao.AqscsgccglbDao;
import com.jshx.aqscsgccglb.entity.Aqscsgccglb;
import com.jshx.aqsczfwsglb.entity.Aqsczfwsglb;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.dfzwglb.entity.Dfzwglb;

@Component("aqscsgccglbDao")
public class AqscsgccglbDaoImpl extends BaseDaoImpl implements AqscsgccglbDao
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
		return this.findPageByHqlId("findAqscsgccglbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Aqscsgccglb> findAqscsgccglb(Map<String, Object> paraMap){
		return this.findListByHqlId("findAqscsgccglbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscsgccglb getById(String id)
	{
		return (Aqscsgccglb)this.getObjectById(Aqscsgccglb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Aqscsgccglb aqscsgccglb)
	{
		aqscsgccglb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(aqscsgccglb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Aqscsgccglb aqscsgccglb)
	{
		this.saveOrUpdateObject(aqscsgccglb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Aqscsgccglb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Aqscsgccglb aqscsgccglb = (Aqscsgccglb)this.getObjectById(Aqscsgccglb.class, id);
		aqscsgccglb.setDelFlag(1);
		this.saveObject(aqscsgccglb);
	}

	@Override
	public Aqscsgccglb getAqscsgccglbByMap(Map map) {
		// TODO Auto-generated method stub
		Aqscsgccglb aqscsgccglb = new Aqscsgccglb();
		try {
			aqscsgccglb = (Aqscsgccglb) sqlMapClientTemplate.getSqlMapClient().queryForObject("getAqscsgccglbByMap",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aqscsgccglb;
	}
}
