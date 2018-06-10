package com.jshx.xrcPxb.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.xrcPxb.dao.JshxXrcPxbDao;
import com.jshx.xrcPxb.entity.JshxXrcPxb;

@Component("jshxXrcPxbDao")
public class JshxXrcPxbDaoImpl extends BaseDaoImpl implements JshxXrcPxbDao
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
		return this.findPageByHqlId("findJshxXrcPxbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public List<JshxXrcPxb> findJshxXrcPxb(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxXrcPxbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public JshxXrcPxb getById(String id)
	{
		return (JshxXrcPxb)this.getObjectById(JshxXrcPxb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public void save(JshxXrcPxb jshxXrcPxb)
	{
		jshxXrcPxb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxXrcPxb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public void update(JshxXrcPxb jshxXrcPxb)
	{
		this.saveOrUpdateObject(jshxXrcPxb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-17
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxXrcPxb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-17
	 */
	public void deleteWithFlag(String id)
	{
		JshxXrcPxb jshxXrcPxb = (JshxXrcPxb)this.getObjectById(JshxXrcPxb.class, id);
		jshxXrcPxb.setDelFlag(1);
		this.saveObject(jshxXrcPxb);
	}
	@Override
	public List<JshxXrcPxb> getJshxXrcPxbListByMap(Map map, int start, int limit) {
		// TODO Auto-generated method stub
		int s = (start-1)*limit;
		int l = limit;
		return sqlMapClientTemplate.queryForList("getJshxXrcPxbListByMap",map,s,l);
	}
	@Override
	public int getJshxXrcPxbListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		List list = sqlMapClientTemplate.queryForList("getJshxXrcPxbListByMap",map);
		return list.size();
	}
}
