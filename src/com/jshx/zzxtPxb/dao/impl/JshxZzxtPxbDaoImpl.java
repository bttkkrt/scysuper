package com.jshx.zzxtPxb.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.zzxtPxb.dao.JshxZzxtPxbDao;
import com.jshx.zzxtPxb.entity.JshxZzxtPxb;

@Component("jshxZzxtPxbDao")
public class JshxZzxtPxbDaoImpl extends BaseDaoImpl implements JshxZzxtPxbDao
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
	 * 2013-08-19
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJshxZzxtPxbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public List<JshxZzxtPxb> findJshxZzxtPxb(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxZzxtPxbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public JshxZzxtPxb getById(String id)
	{
		return (JshxZzxtPxb)this.getObjectById(JshxZzxtPxb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public void save(JshxZzxtPxb jshxZzxtPxb)
	{
		jshxZzxtPxb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxZzxtPxb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public void update(JshxZzxtPxb jshxZzxtPxb)
	{
		this.saveOrUpdateObject(jshxZzxtPxb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-19
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxZzxtPxb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 * author：陆婷
	 * 2013-08-19
	 */
	public void deleteWithFlag(String id)
	{
		JshxZzxtPxb jshxZzxtPxb = (JshxZzxtPxb)this.getObjectById(JshxZzxtPxb.class, id);
		jshxZzxtPxb.setDelFlag(1);
		this.saveObject(jshxZzxtPxb);
	}
	@Override
	public List<JshxZzxtPxb> getJshxZzxtPxbListByMap(Map map, int start,
			int limit) {
		// TODO Auto-generated method stub
		int s = (start-1)*limit;
		int l = limit;
		return sqlMapClientTemplate.queryForList("getJshxZzxtPxbListByMap",map,s,l);
	}
	@Override
	public int getJshxZzxtPxbListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		List list = sqlMapClientTemplate.queryForList("getJshxZzxtPxbListByMap",map);
		return list.size();
	}
}
