package com.jshx.ssjjc.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.ssjjc.dao.SsjjcDao;
import com.jshx.ssjjc.entity.Ssjjc;
import com.jshx.ssjjc.entity.SsjjcBean;
import com.jshx.ssjjc.entity.SsjjcGz;

@Component("ssjjcDao")
public class SsjjcDaoImpl extends BaseDaoImpl implements SsjjcDao
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
		return this.findPageByHqlId("findSsjjcByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Ssjjc> findSsjjc(Map<String, Object> paraMap){
		return this.findListByHqlId("findSsjjcByMaps", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Ssjjc getById(String id)
	{
		return (Ssjjc)this.getObjectById(Ssjjc.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Ssjjc ssjjc)
	{
		ssjjc.setId(null);
		this.saveOrUpdateObject(ssjjc);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Ssjjc ssjjc)
	{
		this.saveOrUpdateObject(ssjjc);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Ssjjc.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Ssjjc ssjjc = (Ssjjc)this.getObjectById(Ssjjc.class, id);
		ssjjc.setDelFlag(1);
		this.saveObject(ssjjc);
	}

	@Override
	public List<SsjjcBean> findSsjjcBean(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return this.findListByHqlId("findSsjjcBeanByMap", paraMap);
	}

	@Override
	public List<EntBaseInfo> getQyListByLxAndWg(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getQyListByLxAndWg",map);
	}

	@Override
	public List<User> getRyListByLdOrZd(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getRyListByLdOrZd",map);
	}

	@Override
	public SsjjcGz getSsjjcGzByMap(Map map) {
		// TODO Auto-generated method stub
		List<SsjjcGz> list = this.findListByHqlId("getSsjjcGzByMap", map);
		if(list.size() != 0)
		{
			return list.get(0);
		}
		return new SsjjcGz();
	}

	@Override
	public void saveSsjjcGz(SsjjcGz ssjjcGz) {
		// TODO Auto-generated method stub
		ssjjcGz.setId(null);
		this.saveOrUpdateObject(ssjjcGz);
	}

	@Override
	public void updateSsjjcGz(SsjjcGz ssjjcGz) {
		// TODO Auto-generated method stub
		this.saveOrUpdateObject(ssjjcGz);
	}

	@Override
	public void saveSsjjcBean(SsjjcBean ssjjcBean) {
		// TODO Auto-generated method stub
		ssjjcBean.setId(null);
		this.saveOrUpdateObject(ssjjcBean);
	}

	@Override
	public SsjjcBean getSsjjcBeanById(String id) {
		// TODO Auto-generated method stub
		return (SsjjcBean)this.getObjectById(SsjjcBean.class, id);
	}
}
