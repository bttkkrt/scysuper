package com.jshx.gawjzzjggl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.gawjzzjggl.entity.PoliceInstitutions;
import com.jshx.gawjzzjggl.dao.PoliceInstitutionsDao;

@Component("policeInstitutionsDao")
public class PoliceInstitutionsDaoImpl extends BaseDaoImpl implements PoliceInstitutionsDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPoliceInstitutionsByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findPoliceInstitutions(Map<String, Object> paraMap){
		return this.findListByHqlId("findPoliceInstitutionsByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PoliceInstitutions getById(String id)
	{
		return (PoliceInstitutions)this.getObjectById(PoliceInstitutions.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PoliceInstitutions policeInstitutions)
	{
		policeInstitutions.setId(null);
		this.saveOrUpdateObject(policeInstitutions);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PoliceInstitutions policeInstitutions)
	{
		this.saveOrUpdateObject(policeInstitutions);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PoliceInstitutions.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PoliceInstitutions policeInstitutions = (PoliceInstitutions)this.getObjectById(PoliceInstitutions.class, id);
		policeInstitutions.setDelFlag(1);
		this.saveObject(policeInstitutions);
	}
}
