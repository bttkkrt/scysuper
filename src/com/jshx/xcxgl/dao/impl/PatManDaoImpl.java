package com.jshx.xcxgl.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xcxgl.dao.PatManDao;
import com.jshx.xcxgl.entity.PatMan;

@Component("patManDao")
public class PatManDaoImpl extends BaseDaoImpl implements PatManDao
{	
	@Resource(name="sqlMapClientTemplate")
	SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPatManByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findPatMan(Map<String, Object> paraMap){
		return this.findListByHqlId("findPatManByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PatMan getById(String id)
	{
		return (PatMan)this.getObjectById(PatMan.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PatMan patMan)
	{
		patMan.setId(null);
		this.saveOrUpdateObject(patMan);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PatMan patMan)
	{
		this.saveOrUpdateObject(patMan);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PatMan.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PatMan patMan = (PatMan)this.getObjectById(PatMan.class, id);
		patMan.setDelFlag(1);
		this.saveObject(patMan);
	}

	@Override
	public List<Map> queryTypes(Map map) {
		try {
			return sqlMapClientTemplate.getSqlMapClient().queryForList("query_xcx_type_list",map) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
