package com.jshx.dfzw.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.dfzw.dao.DfzwDao;
import com.jshx.dfzw.entity.Dfzw;

@Component("dfzwDao")
public class DfzwDaoImpl extends BaseDaoImpl implements DfzwDao
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
		return this.findPageByHqlId("findDfzwByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findDfzw(Map<String, Object> paraMap){
		return this.findListByHqlId("findDfzwByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Dfzw getById(String id)
	{
		return (Dfzw)this.getObjectById(Dfzw.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Dfzw dfzw)
	{
		dfzw.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(dfzw);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Dfzw dfzw)
	{
		this.saveOrUpdateObject(dfzw);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Dfzw.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Dfzw dfzw = (Dfzw)this.getObjectById(Dfzw.class, id);
		dfzw.setDelFlag(1);
		this.saveObject(dfzw);
	}

	@Override
	public void deleteDfzwglbByMap(Map map) {
		// TODO Auto-generated method stub
		try {
			sqlMapClientTemplate.getSqlMapClient().delete("deleteDfzwglbByMap",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getDfzwIdsByMap(Map map) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		try {
			list = sqlMapClientTemplate.getSqlMapClient().queryForList("getDfzwIdsByMap",map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
