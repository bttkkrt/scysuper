package com.jshx.whpxzxk.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.cheprolicense.entity.CheProLic;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.whpxzxk.entity.CheManLic;
import com.jshx.whpxzxk.dao.CheManLicDao;

@Component("cheManLicDao")
public class CheManLicDaoImpl extends BaseDaoImpl implements CheManLicDao
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
		return this.findPageByHqlId("findCheManLicByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCheManLic(Map<String, Object> paraMap){
		return this.findListByHqlId("findCheManLicByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheManLic getById(String id)
	{
		return (CheManLic)this.getObjectById(CheManLic.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CheManLic cheManLic)
	{
		cheManLic.setId(null);
		this.saveOrUpdateObject(cheManLic);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CheManLic cheManLic)
	{
		this.saveOrUpdateObject(cheManLic);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CheManLic.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CheManLic cheManLic = (CheManLic)this.getObjectById(CheManLic.class, id);
		cheManLic.setDelFlag(1);
		this.saveObject(cheManLic);
	}
	/**
	 * 获取危化品经营许可证

	 */
	public int findAllInfos(Map<String, Object> paraMap)
  	{
    	int size = (Integer)sqlMapClientTemplate.queryForObject("findWhpjyxkzListSize",paraMap);
    	return size;
  	}
	
	/**
	 * 获取危化品经营许可证
	 */
	public List<CheManLic> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		int s = (totalPageNum-1)*pageSize;
		int l = pageSize;
		List<CheManLic> list =sqlMapClientTemplate.queryForList("findWhpjyxkzList",paraMap,s,l);
		return list;
	}
}
