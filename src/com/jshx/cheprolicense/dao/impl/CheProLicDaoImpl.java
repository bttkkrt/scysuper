package com.jshx.cheprolicense.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.aqsczjk.entity.SafetyExperts;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.cheprolicense.entity.CheProLic;
import com.jshx.cheprolicense.dao.CheProLicDao;

@Component("cheProLicDao")
public class CheProLicDaoImpl extends BaseDaoImpl implements CheProLicDao
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
		return this.findPageByHqlId("findCheProLicByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCheProLic(Map<String, Object> paraMap){
		return this.findListByHqlId("findCheProLicByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheProLic getById(String id)
	{
		return (CheProLic)this.getObjectById(CheProLic.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CheProLic cheProLic)
	{
		cheProLic.setId(null);
		this.saveOrUpdateObject(cheProLic);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CheProLic cheProLic)
	{
		this.saveOrUpdateObject(cheProLic);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CheProLic.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CheProLic cheProLic = (CheProLic)this.getObjectById(CheProLic.class, id);
		cheProLic.setDelFlag(1);
		this.saveObject(cheProLic);
	}
	/**
	 * 获取危化品安全生产许可证

	 */
	public int findAllInfos(Map<String, Object> paraMap)
  	{
    	int size = (Integer)sqlMapClientTemplate.queryForObject("findWhpaqscxkListSize",paraMap);
    	return size;
  	}
	
	/**
	 * 获取危化品安全生产许可证
	 */
	public List<CheProLic> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		int s = (totalPageNum-1)*pageSize;
		int l = pageSize;
		List<CheProLic> list =sqlMapClientTemplate.queryForList("findWhpaqscxkList",paraMap,s,l);
		return list;
	}
	
}
