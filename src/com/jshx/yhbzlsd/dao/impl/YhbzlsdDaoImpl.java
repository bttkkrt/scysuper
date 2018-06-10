package com.jshx.yhbzlsd.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.fcsbzzqk.entity.FcsbzzqkBean;
import com.jshx.yhbzlsd.entity.Yhbzlsd;
import com.jshx.yhbzlsd.entity.YhbzlsdBean;
import com.jshx.yhbzlsd.dao.YhbzlsdDao;

@Component("yhbzlsdDao")
public class YhbzlsdDaoImpl extends BaseDaoImpl implements YhbzlsdDao
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
		return this.findPageByHqlId("findYhbzlsdByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findYhbzlsd(Map<String, Object> paraMap){
		return this.findListByHqlId("findYhbzlsdByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Yhbzlsd getById(String id)
	{
		return (Yhbzlsd)this.getObjectById(Yhbzlsd.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Yhbzlsd yhbzlsd)
	{
		yhbzlsd.setId(null);
		this.saveOrUpdateObject(yhbzlsd);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Yhbzlsd yhbzlsd)
	{
		this.saveOrUpdateObject(yhbzlsd);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Yhbzlsd.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Yhbzlsd yhbzlsd = (Yhbzlsd)this.getObjectById(Yhbzlsd.class, id);
		yhbzlsd.setDelFlag(1);
		this.saveObject(yhbzlsd);
	}
	
	public List<YhbzlsdBean> getYhbzlsdListByMap(Map map){
		 return sqlMapClientTemplate.queryForList("getYhbzlsdListByMap",map);
	}
	
	public YhbzlsdBean getTotalYhbzlsdListByMap(Map map){
		return (YhbzlsdBean)sqlMapClientTemplate.queryForObject("getTotalYhbzlsdListByMap",map);
	}
}
