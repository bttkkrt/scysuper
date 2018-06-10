package com.jshx.yhb.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.Blob;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.yhb.entity.TjYhBean;
import com.jshx.yhb.entity.TroMan;
import com.jshx.yhb.dao.TroManDao;

@Component("troManDao")
public class TroManDaoImpl extends BaseDaoImpl implements TroManDao
{
	@Resource(name="sqlMapClientTemplate")
	SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	@Override
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findTroManByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	@Override
	public List findTroMan(Map<String, Object> paraMap){
		return this.findListByHqlId("findTroManByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public TroMan getById(String id)
	{
		return (TroMan)this.getObjectById(TroMan.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(TroMan troMan)
	{
		troMan.setId(null);
		this.saveOrUpdateObject(troMan);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(TroMan troMan)
	{
		this.saveOrUpdateObject(troMan);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(TroMan.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		TroMan troMan = (TroMan)this.getObjectById(TroMan.class, id);
		troMan.setDelFlag(1);
		this.saveObject(troMan);
	}

	@Override
	public void saveRectInfo(Map map) {
		this.sqlMapClientTemplate.insert("insert_yhb_rect_byMap",map);
	}

	@Override
	public List<HashMap> queryRectInfosByMap(Map map) {
		return this.sqlMapClientTemplate.queryForList("query_yhb_rectInfos_byMap",map);
	}
	@Override
	public List<TjYhBean> getTjYhListFromQy(String sqlId,Map map) {
		return sqlMapClientTemplate.queryForList(sqlId,map);
	}
	@Override
	public TjYhBean getTjYhDataFromQy(String sqlId,Map map) {
		return (TjYhBean)sqlMapClientTemplate.queryForObject(sqlId,map);
	}
	
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	@Override
	public Pagination findTjYhBeanByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("query_tjyh_list_data_company", paraMap, page);
	}

	@Override
	public List<EntBaseInfo> getYhsbqyByMap(Map map,int start,int limit) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("query_yhsbqy",map, start,limit);
	}

	@Override
	public int getYhsbqyTotalByMap(Map map) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapClientTemplate.queryForObject("query_yhsbqy_total",map);
	}

	@Override
	public List<EntBaseInfo> getYhwsbqyByMap(Map map,int start,int limit) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("query_yhwsbqy",map, start,limit);
	}

	@Override
	public int getYhwsbqyTotalByMap(Map map) {
		// TODO Auto-generated method stub
		return (Integer)sqlMapClientTemplate.queryForObject("query_yhwsbqy_total",map);
	}
}
