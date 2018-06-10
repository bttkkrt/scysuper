package com.jshx.zycsjcry.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.zycsjcry.dao.ZycsjcryDao;
import com.jshx.zycsjcry.entity.Hyfl;
import com.jshx.zycsjcry.entity.Qylb;
import com.jshx.zycsjcry.entity.Whys;
import com.jshx.zycsjcry.entity.Xzqy;
import com.jshx.zycsjcry.entity.Zclx;
import com.jshx.zycsjcry.entity.Zycsjcry;

@Component("zycsjcryDao")
public class ZycsjcryDaoImpl extends BaseDaoImpl implements ZycsjcryDao
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
		return this.findPageByHqlId("findZycsjcryByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZycsjcry(Map<String, Object> paraMap){
		return this.findListByHqlId("findZycsjcryByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zycsjcry getById(String id)
	{
		return (Zycsjcry)this.getObjectById(Zycsjcry.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zycsjcry zycsjcry)
	{
		zycsjcry.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zycsjcry);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zycsjcry zycsjcry)
	{
		this.saveOrUpdateObject(zycsjcry);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zycsjcry.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zycsjcry zycsjcry = (Zycsjcry)this.getObjectById(Zycsjcry.class, id);
		zycsjcry.setDelFlag(1);
		this.saveObject(zycsjcry);
	}
	
	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlags(String id)
	{
		Zycsjcry zycsjcry = (Zycsjcry)this.getObjectById(Zycsjcry.class, id);
		zycsjcry.setDelFlags(1);
		this.saveObject(zycsjcry);
	}

	@Override
	public String getTotalCountByMap(Map map) {
		// TODO Auto-generated method stub
		String s = "0";
		s = (String) sqlMapClientTemplate.queryForObject("getZycsjcryTotalCountByMap",map);
		return s;
	}

	@Override
	public List<Zycsjcry> getZycsjcryListByMap(Map map, int start,int limit) {
		// TODO Auto-generated method stub
		int s = (start-1)*limit;
		int l = limit;
		return sqlMapClientTemplate.queryForList("getZycsjcryListByMap",map,s,l);
	}

	@Override
	public int getZycsjcryListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		List list = sqlMapClientTemplate.queryForList("getZycsjcryListByMap",map);
		return list.size();
	}

	@Override
	public List<Hyfl> getHyflListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getHyflListByMap",map);
	}

	@Override
	public List<Qylb> getQylbListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getQylbListByMap",map);
	}


	@Override
	public List<Whys> getWhysListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getWhysListByMap",map);
	}

	@Override
	public List<Xzqy> getXzqyListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getXzqyListByMap",map);
	}

	@Override
	public List<Zclx> getZclxListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getZclxListByMap",map);
	}

	@Override
	public Whys getWhysByMap(Map map) {
		// TODO Auto-generated method stub
		return (Whys) sqlMapClientTemplate.queryForObject("getWhysByMap",map);
	}

	@Override
	public Xzqy getXzqyByMap(Map map) {
		// TODO Auto-generated method stub
		return (Xzqy) sqlMapClientTemplate.queryForObject("getXzqyByMap",map);
	}
}
