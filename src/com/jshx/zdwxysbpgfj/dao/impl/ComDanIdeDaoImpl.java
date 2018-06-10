package com.jshx.zdwxysbpgfj.dao.impl;

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
import com.jshx.zdwxysbpgfj.entity.ComDanIde;
import com.jshx.zdwxysbpgfj.dao.ComDanIdeDao;

@Component("comDanIdeDao")
public class ComDanIdeDaoImpl extends BaseDaoImpl implements ComDanIdeDao
{
	@Resource(name="sqlMapClientTemplate")
	SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findComDanIdeByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findComDanIde(Map<String, Object> paraMap){
		return this.findListByHqlId("findComDanIdeByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ComDanIde getById(String id)
	{
		return (ComDanIde)this.getObjectById(ComDanIde.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ComDanIde comDanIde)
	{
		comDanIde.setId(null);
		this.saveOrUpdateObject(comDanIde);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ComDanIde comDanIde)
	{
		this.saveOrUpdateObject(comDanIde);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ComDanIde.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ComDanIde comDanIde = (ComDanIde)this.getObjectById(ComDanIde.class, id);
		comDanIde.setDelFlag(1);
		this.saveObject(comDanIde);
	}
	
	  public List<Map<String,String>> findXcryList(Map<String, String> paraMap){
			
			return sqlMapClientTemplate.queryForList("findXcryList",paraMap);
			
		}
	  
	  public List<ComDanIde> getWxyPlans(Map map){
		  return sqlMapClientTemplate.queryForList("query_Wxyplans",map);
		}
}
