package com.jshx.aqscb.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.aqscb.dao.JshxAqscbDao;
import com.jshx.aqscb.entity.JshxAqscb;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;

@Component("jshxAqscbDao")
public class JshxAqscbDaoImpl extends BaseDaoImpl implements JshxAqscbDao
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
		return this.findPageByHqlId("findJshxAqscbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findJshxAqscb(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxAqscbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public JshxAqscb getById(String id)
	{
		return (JshxAqscb)this.getObjectById(JshxAqscb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(JshxAqscb jshxAqscb)
	{
		jshxAqscb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxAqscb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(JshxAqscb jshxAqscb)
	{
		this.saveOrUpdateObject(jshxAqscb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxAqscb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		JshxAqscb jshxAqscb = (JshxAqscb)this.getObjectById(JshxAqscb.class, id);
		jshxAqscb.setDelFlag(1);
		this.saveObject(jshxAqscb);
	}

	@Override
	public String getLaterMonth(Map map) {
		// TODO Auto-generated method stub
		return (String)sqlMapClientTemplate.queryForObject("query_laterMonth_byMap",map);
	}

	@Override
	public List<String> getAqscIdsByMap(Map map) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list = sqlMapClientTemplate.queryForList("getAqscIdsByMap",map);
		return list;
	}
}
