package com.jshx.yhbzxzxk.dao.impl;

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
import com.jshx.whpxzxk.entity.CheManLic;
import com.jshx.yhbzxzxk.entity.FirLic;
import com.jshx.yhbzxzxk.dao.FirLicDao;

@Component("firLicDao")
public class FirLicDaoImpl extends BaseDaoImpl implements FirLicDao
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
		return this.findPageByHqlId("findFirLicByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findFirLic(Map<String, Object> paraMap){
		return this.findListByHqlId("findFirLicByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public FirLic getById(String id)
	{
		return (FirLic)this.getObjectById(FirLic.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(FirLic firLic)
	{
		firLic.setId(null);
		this.saveOrUpdateObject(firLic);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(FirLic firLic)
	{
		this.saveOrUpdateObject(firLic);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(FirLic.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		FirLic firLic = (FirLic)this.getObjectById(FirLic.class, id);
		firLic.setDelFlag(1);
		this.saveObject(firLic);
	}
	/**
	 * 获取烟花爆竹经营许可证

	 */
	public int findAllInfos(Map<String, Object> paraMap)
  	{
    	int size = (Integer)sqlMapClientTemplate.queryForObject("findYhbzjyxkzListSize",paraMap);
    	return size;
  	}
	
	/**
	 * 获取烟花爆竹经营许可证
	 */
	public List<FirLic> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		int s = (totalPageNum-1)*pageSize;
		int l = pageSize;
		List<FirLic> list =sqlMapClientTemplate.queryForList("findYhbzjyxkzList",paraMap,s,l);
		return list;
	}
}
