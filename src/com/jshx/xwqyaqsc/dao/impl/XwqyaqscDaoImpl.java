package com.jshx.xwqyaqsc.dao.impl;

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
import com.jshx.xwqyaqsc.entity.Xwqyaqsc;
import com.jshx.xwqyaqsc.entity.XwqyaqscBean;
import com.jshx.xwqyaqsc.dao.XwqyaqscDao;

@Component("xwqyaqscDao")
public class XwqyaqscDaoImpl extends BaseDaoImpl implements XwqyaqscDao
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
		return this.findPageByHqlId("findXwqyaqscByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findXwqyaqsc(Map<String, Object> paraMap){
		return this.findListByHqlId("findXwqyaqscByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Xwqyaqsc getById(String id)
	{
		return (Xwqyaqsc)this.getObjectById(Xwqyaqsc.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Xwqyaqsc xwqyaqsc)
	{
		xwqyaqsc.setId(null);
		this.saveOrUpdateObject(xwqyaqsc);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Xwqyaqsc xwqyaqsc)
	{
		this.saveOrUpdateObject(xwqyaqsc);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Xwqyaqsc.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Xwqyaqsc xwqyaqsc = (Xwqyaqsc)this.getObjectById(Xwqyaqsc.class, id);
		xwqyaqsc.setDelFlag(1);
		this.saveObject(xwqyaqsc);
	}
	 public List<XwqyaqscBean> getXwqyaqscListByMap(Map map){
		 return sqlMapClientTemplate.queryForList("getXwqyaqscListByMap",map);
	 }
		
     public XwqyaqscBean getTotalXwqyaqscListByMap(Map map){
    	 return (XwqyaqscBean)sqlMapClientTemplate.queryForObject("getTotalXwqyaqscListByMap",map);
    }
}
