package com.jshx.zjjcb.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.zjjcb.dao.JshxZjjcbDao;
import com.jshx.zjjcb.entity.JshxZjjcb;

@Component("jshxZjjcbDao")
public class JshxZjjcbDaoImpl extends BaseDaoImpl implements JshxZjjcbDao
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
		return this.findPageByHqlId("findJshxZjjcbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findJshxZjjcb(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxZjjcbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public JshxZjjcb getById(String id)
	{
		return (JshxZjjcb)this.getObjectById(JshxZjjcb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(JshxZjjcb jshxZjjcb)
	{
		jshxZjjcb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxZjjcb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(JshxZjjcb jshxZjjcb)
	{
		this.saveOrUpdateObject(jshxZjjcb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxZjjcb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		JshxZjjcb jshxZjjcb = (JshxZjjcb)this.getObjectById(JshxZjjcb.class, id);
		jshxZjjcb.setDelFlag(1);
		this.saveObject(jshxZjjcb);
	}

	@Override
	public JshxZjjcb getJshxZjjcbByMap(Map map) {
		// TODO Auto-generated method stub
		JshxZjjcb j = new JshxZjjcb();
		List<JshxZjjcb>  list = this.findListByHqlId("findJshxZjjcbByMap", map);
		if(list.size() != 0)
		{
			j = list.get(0);
		}
		return j;
	}

	@Override
	public List<JshxZjjcb> getCompanyListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getZjjcCompanyListByMap",map);
	}
}
