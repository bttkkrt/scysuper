package com.jshx.dfzwglb.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.dfzwglb.dao.DfzwglbDao;
import com.jshx.dfzwglb.entity.Dfzwglb;

@Component("dfzwglbDao")
public class DfzwglbDaoImpl extends BaseDaoImpl implements DfzwglbDao
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
		return this.findPageByHqlId("findDfzwglbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Dfzwglb> findDfzwglb(Map<String, Object> paraMap){
		return this.findListByHqlId("findDfzwglbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Dfzwglb getById(String id)
	{
		return (Dfzwglb)this.getObjectById(Dfzwglb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Dfzwglb dfzwglb)
	{
		dfzwglb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(dfzwglb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Dfzwglb dfzwglb)
	{
		this.saveOrUpdateObject(dfzwglb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Dfzwglb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Dfzwglb dfzwglb = (Dfzwglb)this.getObjectById(Dfzwglb.class, id);
		dfzwglb.setDelFlag(1);
		this.saveObject(dfzwglb);
	}

	@Override
	public Dfzwglb getDfzwglbByMap(Map map) {
		// TODO Auto-generated method stub
		Dfzwglb dfzwglb = new Dfzwglb();
		try {
			dfzwglb = (Dfzwglb) sqlMapClientTemplate.getSqlMapClient().queryForObject("getDfzwglbByMap",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dfzwglb;
	}
}
