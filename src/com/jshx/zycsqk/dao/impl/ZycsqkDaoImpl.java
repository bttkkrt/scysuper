package com.jshx.zycsqk.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.zycsqk.dao.ZycsqkDao;
import com.jshx.zycsqk.entity.Zybwhda;
import com.jshx.zycsqk.entity.Zycsqk;
import com.jshx.zycsqk.entity.Zywhys;

@Component("zycsqkDao")
public class ZycsqkDaoImpl extends BaseDaoImpl implements ZycsqkDao
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
		return this.findPageByHqlId("findZycsqkByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZycsqk(Map<String, Object> paraMap){
		return this.findListByHqlId("findZycsqkByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zycsqk getById(String id)
	{
		return (Zycsqk)this.getObjectById(Zycsqk.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zycsqk zycsqk)
	{
		zycsqk.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zycsqk);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zycsqk zycsqk)
	{
		this.saveOrUpdateObject(zycsqk);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zycsqk.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zycsqk zycsqk = (Zycsqk)this.getObjectById(Zycsqk.class, id);
		zycsqk.setDelFlag(1);
		this.saveObject(zycsqk);
	}

	@Override
	public Zywhys getZywhysByMap(Map map) {
		// TODO Auto-generated method stub
		Zywhys  zywhys = new Zywhys();
		List<Zywhys> list = sqlMapClientTemplate.queryForList("getZywhysByMap",map);
		if(list.size() != 0)
		{
			zywhys = list.get(0);
		}
		return zywhys;
	}

	@Override
	public String getTotalCountByMap(Map map) {
		// TODO Auto-generated method stub
		String s = "0";
		s = (String) sqlMapClientTemplate.queryForObject("getZycsTotalCountByMap",map);
		return s;
	}
	
	@Override
	public List<Zybwhda> getZybwhdaListByMap(Map map, int start, int limit) {
		// TODO Auto-generated method stub
		int s = (start-1)*limit;
		int l = limit;
		return sqlMapClientTemplate.queryForList("getZybwhdaListByMap",map,s,l);
	}

	@Override
	public int getZybwhdaListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		List list = sqlMapClientTemplate.queryForList("getZybwhdaListByMap",map);
		return list.size();
	}

	@Override
	public List<Zybwhda> getZybwhdaListExportByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getZybwhdaListByMap",map);
	}
}
