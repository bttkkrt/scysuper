package com.jshx.tzzyPxb.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.tzzyPxb.dao.JshxTzzyPxbDao;
import com.jshx.tzzyPxb.entity.JshxTzzyPxb;

@Component("jshxTzzyPxbDao")
public class JshxTzzyPxbDaoImpl extends BaseDaoImpl implements JshxTzzyPxbDao
{
	@Autowired
	@Qualifier("sqlMapClientTemplate") 
	private SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJshxTzzyPxbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public List<JshxTzzyPxb> findJshxTzzyPxb(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxTzzyPxbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public JshxTzzyPxb getById(String id)
	{
		return (JshxTzzyPxb)this.getObjectById(JshxTzzyPxb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public void save(JshxTzzyPxb jshxTzzyPxb)
	{
		jshxTzzyPxb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxTzzyPxb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public void update(JshxTzzyPxb jshxTzzyPxb)
	{
		this.saveOrUpdateObject(jshxTzzyPxb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-17
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxTzzyPxb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-17
	 */
	public void deleteWithFlag(String id)
	{
		JshxTzzyPxb jshxTzzyPxb = (JshxTzzyPxb)this.getObjectById(JshxTzzyPxb.class, id);
		jshxTzzyPxb.setDelFlag(1);
		this.saveObject(jshxTzzyPxb);
	}

	@Override
	public List<JshxTzzyPxb> getJshxTzzyPxbListByMap(Map map, int start,
			int limit) {
		// TODO Auto-generated method stub
		int s = (start-1)*limit;
		int l = limit;
		return sqlMapClientTemplate.queryForList("getJshxTzzyPxbListByMap",map,s,l);
	}

	@Override
	public int getJshxTzzyPxbListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		List list = sqlMapClientTemplate.queryForList("getJshxTzzyPxbListByMap",map);
		return list.size();
	}
	@Override
	public void deleteByQyid(Map map) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.update("deleteTzzyByQyid",map);
	}
}
