package com.jshx.zhajxxh.dao.impl;

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
import com.jshx.gmys.entity.GmysBean;
import com.jshx.zhajxxh.entity.Zhajxxh;
import com.jshx.zhajxxh.entity.ZhajxxhBean;
import com.jshx.zhajxxh.dao.ZhajxxhDao;

@Component("zhajxxhDao")
public class ZhajxxhDaoImpl extends BaseDaoImpl implements ZhajxxhDao
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
		return this.findPageByHqlId("findZhajxxhByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZhajxxh(Map<String, Object> paraMap){
		return this.findListByHqlId("findZhajxxhByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zhajxxh getById(String id)
	{
		return (Zhajxxh)this.getObjectById(Zhajxxh.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zhajxxh zhajxxh)
	{
		zhajxxh.setId(null);
		this.saveOrUpdateObject(zhajxxh);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zhajxxh zhajxxh)
	{
		this.saveOrUpdateObject(zhajxxh);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zhajxxh.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zhajxxh zhajxxh = (Zhajxxh)this.getObjectById(Zhajxxh.class, id);
		zhajxxh.setDelFlag(1);
		this.saveObject(zhajxxh);
	}
	public List<ZhajxxhBean> getZhajxxhListByMap(Map map){
		 return sqlMapClientTemplate.queryForList("getZhajxxhListByMap",map);
	}
	
	public ZhajxxhBean getTotalZhajxxhListByMap(Map map){
		return (ZhajxxhBean)sqlMapClientTemplate.queryForObject("getTotalZhajxxhListByMap",map);
	}
}
